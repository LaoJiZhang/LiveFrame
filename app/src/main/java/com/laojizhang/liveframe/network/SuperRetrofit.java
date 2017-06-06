package com.laojizhang.liveframe.network;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.laojizhang.lifelibrary.utils.LogUtils;
import com.laojizhang.liveframe.BuildConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 文件名称： SuperRetrofit
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-19:00
 * 文件描述：
 * <p>
 */

public class SuperRetrofit {

    private static SuperRetrofit sInstance;
    private static final java.lang.String TAG = SuperRetrofit.class.getSimpleName();

    private final Map<String, Retrofit> mRetrofitMap = new HashMap<>();
    private final Map<String, APIService> mRequestMap = new HashMap<>();

    public static SuperRetrofit getInstance() {
        if (sInstance == null) {
            sInstance = new SuperRetrofit();
        }
        return sInstance;
    }

    private SuperRetrofit() {
    }

    public static OkHttpClient createOKHttpClient(long timeOut, TimeUnit unit) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        builder.connectTimeout(timeOut, unit);
        builder.readTimeout(timeOut, unit);
        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                HttpUrl.Builder builder = request.url().newBuilder();
//                Map<String, String> requestParams = Env.sCommonParamsGetter.getCommonParams();
//                for (String key : requestParams.keySet()) {
//                    builder.addQueryParameter(key, requestParams.get(key));
//                }
                HttpUrl httpUrl = builder.build();
                LogUtils.i(TAG, "httpUrl = " + httpUrl.toString());
                request = request.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        });
        return builder.build();
    }

    private OkHttpClient createHttpClient(long timeOut, TimeUnit unit) {
        return createOKHttpClient(timeOut, unit);
    }

    private String createKey(long timeOut, TimeUnit unit) {
        return String.valueOf(timeOut) + "_" + unit;
    }

    private APIService getRequest(long timeOut, TimeUnit unit) {
        String key = createKey(timeOut, unit);
        if (!mRequestMap.containsKey(key)) {
            mRequestMap.put(key, createRetrofit(timeOut, unit).create(APIService.class));
        }
        return mRequestMap.get(key);
    }

    private Retrofit createRetrofit(long timeOut, TimeUnit unit) {
        String key = createKey(timeOut, unit);
        if (!mRetrofitMap.containsKey(key)) {
            Retrofit commonRetrofit = new Retrofit.Builder()
                    .client(createHttpClient(timeOut, unit))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://api.douban.com")
                    .build();
            mRetrofitMap.put(key, commonRetrofit);
        }
        return mRetrofitMap.get(key);
    }

    public Retrofit getCommonRetrofit() {
        return createRetrofit(20, TimeUnit.SECONDS);
    }

    public APIService getCommonReqeust() {
        return getRequest(20, TimeUnit.SECONDS);
    }

    public <D> Subscription sendKCRequest(final Observable<DouBanResponse<D>> observable) {
        return sendKCRequest(observable, null);
    }

    public <D> Subscription sendKCRequest(final Observable<DouBanResponse<D>> observable, RequestListener<D> requestListener) {
        return observable
                .compose(SuperRetrofit.<D>applyNetWorkTransformer())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestSubscriber<D>(requestListener));
    }

    @NonNull
    public static <D> Observable.Transformer<DouBanResponse<D>, D> applyNetWorkTransformer() {
        return new Observable.Transformer<DouBanResponse<D>, D>() {
            @Override
            public Observable<D> call(Observable<DouBanResponse<D>> observable) {
                return observable.flatMap(new Func1<DouBanResponse<D>, Observable<D>>() {
                    @Override
                    public Observable<D> call(DouBanResponse<D> response) {
                        LogUtils.i(TAG, System.currentTimeMillis() + "      response = " + response.toString());
                        if (response.getSubjects() != null) {
                            return Observable.just(response.getSubjects());
                        } else {
                            Observable.error(new MyException(response));
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static class RequestSubscriber<D> extends Subscriber<D> {

        private final RequestListener<D> mRequestListener;

        public RequestSubscriber(SuperRetrofit.RequestListener<D> requestListener) {
            mRequestListener = requestListener;
        }

        public void onSuccess(D data) {
            if (mRequestListener != null)
                mRequestListener.onSuccessed(data);
        }

        public void onFail(int errorcode, String message) {
            LogUtils.i(TAG, System.currentTimeMillis() + " request fail message = " + message + "       errorcode = " + errorcode);
            if (mRequestListener != null)
                mRequestListener.onFailed(errorcode, message);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            LogUtils.i(TAG, e.toString());
        }

        @Override
        public void onNext(D d) {
            onSuccess(d);
        }
    }

    public interface RequestListener<D> {

        void onSuccessed(D data);

        void onFailed(int errorCode, String errMsg);
    }
//
//    public static class ErrorWrapper {
//        private int errorcode;
//        private String message;
//
//        public ErrorWrapper(int errorcode, String message) {
//            this.errorcode = errorcode;
//            this.message = message;
//        }
//
//        public int getErrorcode() {
//            return errorcode;
//        }
//
//        public void setErrorcode(int errorcode) {
//            this.errorcode = errorcode;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }

    public static class MyException extends Exception {

        private final Object tag;

        public MyException(Object tag) {
            this.tag = tag;
        }

        public Object getTag() {
            return tag;
        }
    }
}
