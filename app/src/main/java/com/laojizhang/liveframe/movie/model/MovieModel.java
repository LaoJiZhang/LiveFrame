package com.laojizhang.liveframe.movie.model;

import android.app.Application;

import com.laojizhang.lifelibrary.model.BaseLifeModel;
import com.laojizhang.lifelibrary.utils.LogUtils;
import com.laojizhang.liveframe.network.DouBanResponse;
import com.laojizhang.liveframe.movie.bean.MovieEntity;
import com.laojizhang.liveframe.network.RequestParamsFactory;
import com.laojizhang.liveframe.network.SuperRetrofit;

import java.util.List;

import rx.Observable;

/**
 * 文件名称： MovieModel
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-21:51
 * 文件描述：
 * <p>
 */

public class MovieModel extends BaseLifeModel<List<MovieEntity>> {

    public MovieModel(Application application) {
        super(application);
    }

    @Override
    protected void onFinishInit() {
        getPageViewImpl().showLoadingPage();

        Observable<DouBanResponse<List<MovieEntity>>> observable = SuperRetrofit.getInstance().getCommonReqeust().loadingDouBanTop250(RequestParamsFactory.getDouBanTop250Params());

        SuperRetrofit.getInstance().sendKCRequest(observable, new SuperRetrofit.RequestListener<List<MovieEntity>>() {
            @Override
            public void onSuccessed(List<MovieEntity> data) {
                setDefaultLiveData(data);
            }

            @Override
            public void onFailed(int errorCode, String errMsg) {
                LogUtils.i("aaaa", "fail");
            }
        });
    }
}
