package com.laojizhang.lifelibrary.ui.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laojizhang.lifelibrary.R;
import com.laojizhang.lifelibrary.databinding.BaseCommonLayoutBinding;
import com.laojizhang.lifelibrary.ui.IPageCreateView;
import com.laojizhang.lifelibrary.ui.PageViewImpl;

/**
 * 文件名称： BasePageFragment
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-15:33
 * 文件描述：
 * <p>
 */

public abstract class BasePageActivity extends LifecycleActivity implements IPageCreateView {

    private PageViewImpl mPageView;

    public PageViewImpl getPageView() {
        return mPageView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        BaseCommonLayoutBinding mCommonLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.base_common_layout, null, false);

        mPageView = new PageViewImpl(createPageCreateView(), mCommonLayoutBinding);

        mCommonLayoutBinding.setCurrentFlag(mPageView.getCurrentViewFlag());
        mCommonLayoutBinding.viewContent.addView(createContentView(savedInstanceState, inflater, null));
        setContentView(mCommonLayoutBinding.getRoot());
        subscribeUi(getDefaultLiveData(), createDefaultObserver());
    }

    @NonNull
    protected IPageCreateView createPageCreateView() {
        return this;
    }

    protected void subscribeUi(MutableLiveData liveData, Observer<?> observer) {
        liveData.observe(this, observer);
    }

    public void showLoadingPage() {
        mPageView.showLoadingPage();
    }

    public void showEmptyPage() {
        mPageView.showEmptyPage();
    }

    public void showErrorPage() {
        mPageView.showErrorPage();
    }

    public void showContentView() {
        mPageView.showContentView();
    }

    @Override
    public View createOwnLoadingView() {
        return null;
    }

    @Override
    public View createOwnEmptyView() {
        return null;
    }

    @Override
    public View createOwnErrorView() {
        return null;
    }

    protected abstract MutableLiveData getDefaultLiveData();

    protected abstract Observer<?> createDefaultObserver();

    protected abstract View createContentView(Bundle savedInstanceState, LayoutInflater inflater, @Nullable ViewGroup container);
}
