package com.laojizhang.lifelibrary.ui;

import android.view.View;

/**
 * 文件名称： IPageView
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-15:54
 * 文件描述：
 * <p>
 */

interface IPageView {

    void showLoadingPage();

    View createOwnLoadingView();

    void showEmptyPage();

    View createOwnEmptyView();

    void showErrorPage();

    View createOwnErrorView();

    void showContentView();
}
