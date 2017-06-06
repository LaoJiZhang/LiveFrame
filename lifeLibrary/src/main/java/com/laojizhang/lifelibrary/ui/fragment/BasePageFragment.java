package com.laojizhang.lifelibrary.ui.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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

public abstract class BasePageFragment extends LifecycleFragment implements IPageCreateView {

    private PageViewImpl mPageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BaseCommonLayoutBinding commonLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.base_common_layout, container, false);

        mPageView = new PageViewImpl(this, commonLayoutBinding);

        commonLayoutBinding.setCurrentFlag(mPageView.getCurrentViewFlag());
        commonLayoutBinding.viewContent.addView(createContentView(inflater, container));
        return commonLayoutBinding.getRoot();
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

    public abstract View createContentView(LayoutInflater inflater, @Nullable ViewGroup container);
}
