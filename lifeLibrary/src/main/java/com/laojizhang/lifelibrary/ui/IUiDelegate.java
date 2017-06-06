package com.laojizhang.lifelibrary.ui;

import android.arch.lifecycle.AndroidViewModel;

/**
 * 文件名称： IUiDelegate
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-17:30
 * 文件描述：
 * <p>
 */

public interface IUiDelegate<M extends AndroidViewModel> {

    int getContentId();

    Class<M> getClazz();
}
