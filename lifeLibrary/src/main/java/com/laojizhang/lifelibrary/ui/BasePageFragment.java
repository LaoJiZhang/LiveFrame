package com.laojizhang.lifelibrary.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.laojizhang.lifelibrary.R;
import com.laojizhang.lifelibrary.databinding.BaseFragmentCommonLayoutBinding;

/**
 * 文件名称： BasePageFragment
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-15:33
 * 文件描述：
 * <p>
 */

public abstract class BasePageFragment extends LifecycleFragment implements IPageView {

    public static final int FALG_LOADING = 1;
    public static final int FALG_EMPTY = 2;
    public static final int FALG_ERROR = 3;
    public static final int FALG_CONTENT = 4;

    @ViewFlag
    private int mCurrentViewFlag = FALG_CONTENT;
    private BaseFragmentCommonLayoutBinding mCommonLayoutBinding;

    @IntDef({FALG_LOADING, FALG_EMPTY, FALG_ERROR, FALG_CONTENT})
    public @interface ViewFlag {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCommonLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.base_fragment_common_layout, container, false);
        mCommonLayoutBinding.setCurrentFlag(mCurrentViewFlag);
        mCommonLayoutBinding.viewContent.addView(createContentView(inflater, container));
        return mCommonLayoutBinding.getRoot();
    }

    public abstract View createContentView(LayoutInflater inflater, @Nullable ViewGroup container);

    @Override
    public void showLoadingPage() {
        showPage(FALG_LOADING, createOwnLoadingView(), (FrameLayout) mCommonLayoutBinding.viewLoading.getRoot());
    }

    @Override
    public View createOwnLoadingView() {
        return null;
    }

    @Override
    public void showEmptyPage() {
        showPage(FALG_EMPTY, createOwnEmptyView(), (FrameLayout) mCommonLayoutBinding.viewEmpty.getRoot());
    }

    @Override
    public View createOwnEmptyView() {
        return null;
    }

    @Override
    public void showErrorPage() {
        showPage(FALG_ERROR, createOwnErrorView(), (FrameLayout) mCommonLayoutBinding.viewError.getRoot());
    }

    @Override
    public View createOwnErrorView() {
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
