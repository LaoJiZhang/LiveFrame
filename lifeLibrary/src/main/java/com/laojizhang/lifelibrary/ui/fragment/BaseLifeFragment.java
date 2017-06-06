package com.laojizhang.lifelibrary.ui.fragment;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
 * 文件名称： BaseLifeFragment
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-11:27
 * 文件描述：
 * <p>
 */

public abstract class BaseLifeFragment<D extends ViewDataBinding, M extends BaseLifeModel> extends BasePageFragment {

    private static final String TAG = BaseLifeFragment.class.getSimpleName();

    private D mContentBinding;
    private MutableLiveData<M> mBaseModelLiveData;
    private MutableLiveData<FragmentUiDelegate> mUiDelegateLiveData;

    public D getContentBinding() {
        return mContentBinding;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initUiDelegate();
        initModel();
        subscribeUi(getDefaultLiveData(), createDefaultObserver());
    }

    private void initUiDelegate() {
        mUiDelegateLiveData = new MutableLiveData<FragmentUiDelegate>();
        mUiDelegateLiveData.setValue(createUiDelegate());
    }

    private void initModel() {
        mBaseModelLiveData = new MutableLiveData<>();
        mBaseModelLiveData.setValue(createModel(mUiDelegateLiveData.getValue().getClazz()));
        getModel().init(getPageView());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getModel() != null) {
            getModel().onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public View createContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        mContentBinding = DataBindingUtil.inflate(inflater, mUiDelegateLiveData.getValue().getContentId(), container, false);
        mUiDelegateLiveData.getValue().initFragment(mContentBinding);
        return mContentBinding.getRoot();
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
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(TAG, this.getClass().getSimpleName() + "      onDestroyView....");
        if (getModel() != null) {
            getModel().onDestroyView();
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

    protected Application getApplication() {
        return getActivity().getApplication();
    }

    protected M getModel() {
        return mBaseModelLiveData.getValue();
    }

    private M createModel(Class clazz) {
        return (M) ViewModelProviders.of(this).get(clazz);
    }

    protected void subscribeUi(MutableLiveData liveData, Observer<?> observer) {
        liveData.observe(this, observer);
    }

    protected MutableLiveData getDefaultLiveData() {
        return mBaseModelLiveData.getValue().getLiveData();
    }

    protected abstract FragmentUiDelegate createUiDelegate();

    protected abstract Observer<?> createDefaultObserver();

    public abstract class FragmentUiDelegate implements IUiDelegate<M> {

        public abstract void initFragment(D binding);
    }
}
