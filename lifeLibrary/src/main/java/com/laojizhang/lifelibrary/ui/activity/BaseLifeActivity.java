package com.laojizhang.lifelibrary.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laojizhang.lifelibrary.model.BaseLifeModel;
import com.laojizhang.lifelibrary.ui.IUiDelegate;
import com.laojizhang.lifelibrary.utils.LogUtils;

/**
 * 文件名称： BaseLifeActivity
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-11:15
 * 文件描述：
 * <p>
 */

public abstract class BaseLifeActivity<D extends ViewDataBinding, M extends BaseLifeModel> extends BasePageActivity {

    private static final java.lang.String TAG = BaseLifeActivity.class.getSimpleName();
    private MutableLiveData<ActivityUiDelegate> mActivityUiDelegateMutableLiveData;
    private MutableLiveData<M> mModelMutableLiveData;
    private D mContentBinding;

    public D getContentBinding() {
        return mContentBinding;
    }

    public ActivityUiDelegate getActivityUiDelegate() {
        return mActivityUiDelegateMutableLiveData.getValue();
    }

    public M getModel() {
        if (mModelMutableLiveData != null)
            return mModelMutableLiveData.getValue();
        return null;
    }

    @Override
    protected View createContentView(Bundle savedInstanceState, LayoutInflater inflater, @Nullable ViewGroup container) {
        initUIDelegate();
        initModel();
        return initContentView(savedInstanceState, inflater, container);
    }

    private void initUIDelegate() {
        mActivityUiDelegateMutableLiveData = new MutableLiveData<>();
        mActivityUiDelegateMutableLiveData.setValue(createActivityUiDelegate());
    }

    private void initModel() {
        mModelMutableLiveData = new MutableLiveData<>();
        M model = ViewModelProviders.of(this).get(getActivityUiDelegate().getClazz());
        mModelMutableLiveData.setValue(model);
        model.init(getPageView());
    }

    private View initContentView(Bundle savedInstanceState, LayoutInflater inflater, @Nullable ViewGroup container) {
        mContentBinding = DataBindingUtil.inflate(inflater, getActivityUiDelegate().getContentId(), container, false);
        getActivityUiDelegate().initActivity(savedInstanceState, mContentBinding);
        return mContentBinding.getRoot();
    }

    @Override
    protected MutableLiveData getDefaultLiveData() {
        return mModelMutableLiveData.getValue().getLiveData();
    }

    protected void subscribeUi(MutableLiveData liveData, Observer<?> observer) {
        liveData.observe(this, observer);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(TAG, this.getClass().getSimpleName() + "      onStart....");
        if (getModel() != null) {
            getModel().onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(TAG, this.getClass().getSimpleName() + "      onResume....");
        if (getModel() != null) {
            getModel().onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(TAG, this.getClass().getSimpleName() + "      onPause....");
        if (getModel() != null) {
            getModel().onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i(TAG, this.getClass().getSimpleName() + "      onStop....");
        if (getModel() != null) {
            getModel().onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, this.getClass().getSimpleName() + "      onDestroy....");
        if (getModel() != null) {
            getModel().onDestroy();
        }
    }

    public Activity getActivity() {
        return this;
    }

    protected abstract ActivityUiDelegate createActivityUiDelegate();

    public abstract class ActivityUiDelegate implements IUiDelegate<M> {

        public abstract void initActivity(Bundle savedInstanceState, D binding);
    }
}
