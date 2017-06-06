package com.laojizhang.lifelibrary.ui;

import android.view.View;

/**
 * 文件名称： IPageShowView
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-15:54
 * 文件描述：
 * <p>
 */

public interface IPageCreateView extends IPageView {

    View createOwnLoadingView();

    View createOwnEmptyView();

    View createOwnErrorView();
}
