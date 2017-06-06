package com.laojizhang.lifelibrary.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.os.Handler;

/**
 * 文件名称： BaseLifeModel
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-11:50
 * 文件描述：
 * <p>
 */

public abstract class BaseLifeModel<D extends Object> extends AndroidViewModel {

    private MutableLiveData<D> mLiveData;
    private MutableLiveData<Handler> mHandlerLiveData;

    public MutableLiveData<D> getLiveData() {
        return mLiveData;
    }

    protected Handler getHandler() {
        return mHandlerLiveData.getValue();
    }

    public BaseLifeModel(Application application) {
        super(application);
        mLiveData = bindDefaultLiveData();
        mHandlerLiveData = new MutableLiveData<>();
        mHandlerLiveData.setValue(new Handler());
    }

    public void onActivityCreated(Bundle savedInstanceState) {
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroyView() {
    }

    public void onDestroy() {
        getHandler().removeCallbacksAndMessages(null);
    }

    public MutableLiveData<D> bindDefaultLiveData() {
        mLiveData = new MutableLiveData<D>();
        return mLiveData;
    }

    public <T> MutableLiveData<T> bindLiveData(T t) {
        MutableLiveData<T> liveData = new MutableLiveData<T>();
        return liveData;
    }
}
