package com.laojizhang.lifelibrary.adapter;

import android.databinding.ViewDataBinding;

/**
 * 文件名称： DatabindingRecyclerDelegate
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-10:16
 * 文件描述：
 * <p>
 */

public interface DatabindingRecyclerDelegate<T> {

    int getItemLayoutResId();

    void onBindItem(ViewDataBinding viewDataBinding, T data, int position);
}
