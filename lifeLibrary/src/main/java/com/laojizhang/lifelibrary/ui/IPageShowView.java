package com.laojizhang.lifelibrary.ui;

/**
 * 文件名称： IPageShowView
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-15:54
 * 文件描述：
 * <p>
 */

public interface IPageShowView extends IPageView {

    void showLoadingPage();

    void showEmptyPage();

    void showErrorPage();

    void showContentView();
}
