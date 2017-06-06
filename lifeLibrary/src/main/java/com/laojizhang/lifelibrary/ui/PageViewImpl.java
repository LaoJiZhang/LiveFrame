package com.laojizhang.lifelibrary.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.laojizhang.lifelibrary.databinding.BaseCommonLayoutBinding;

/**
 * 文件名称： PageViewImpl
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-16:28
 * 文件描述：
 * <p>
 */

public class PageViewImpl implements IPageShowView {

    @ViewFlag
    private int mCurrentViewFlag = FALG_CONTENT;
    private final IPageCreateView mPageCreateView;
    private BaseCommonLayoutBinding mCommonLayoutBinding;

    public int getCurrentViewFlag() {
        return mCurrentViewFlag;
    }

    public PageViewImpl(IPageCreateView pageCreateView, BaseCommonLayoutBinding binding) {
        this.mCommonLayoutBinding = binding;
        this.mPageCreateView = pageCreateView;
    }

    @Override
    public void showLoadingPage() {
        showPage(FALG_LOADING, createOwnLoadingView(), (FrameLayout) mCommonLayoutBinding.viewLoading.getRoot());
    }

    public View createOwnLoadingView() {
        if (mPageCreateView != null)
            return mPageCreateView.createOwnLoadingView();
        return null;
    }

    @Override
    public void showEmptyPage() {
        showPage(FALG_EMPTY, createOwnEmptyView(), (FrameLayout) mCommonLayoutBinding.viewEmpty.getRoot());
    }

    public View createOwnEmptyView() {
        if (mPageCreateView != null)
            return mPageCreateView.createOwnEmptyView();
        return null;
    }

    public void showErrorPage() {
        showPage(FALG_ERROR, createOwnErrorView(), (FrameLayout) mCommonLayoutBinding.viewError.getRoot());
    }

    public View createOwnErrorView() {
        if (mPageCreateView != null)
            return mPageCreateView.createOwnErrorView();
        return null;
    }

    private void showPage(@ViewFlag int flag, View newView, FrameLayout oldLayout) {
        mCurrentViewFlag = flag;
        mCommonLayoutBinding.setCurrentFlag(mCurrentViewFlag);
        if (newView != null) {
            oldLayout.removeAllViews();
            oldLayout.addView(newView);
        }
    }

    @Override
    public void showContentView() {
        mCurrentViewFlag = FALG_CONTENT;
        mCommonLayoutBinding.setCurrentFlag(mCurrentViewFlag);
    }
}
