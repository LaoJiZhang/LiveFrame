package com.laojizhang.lifelibrary.ui;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 文件名称： IPageShowView
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-15:54
 * 文件描述：
 * <p>
 */

public interface IPageView {

    int FALG_LOADING = 1;
    int FALG_EMPTY = 2;
    int FALG_ERROR = 3;
    int FALG_CONTENT = 4;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FALG_LOADING, FALG_EMPTY, FALG_ERROR, FALG_CONTENT})
    public @interface ViewFlag {
    }
}
