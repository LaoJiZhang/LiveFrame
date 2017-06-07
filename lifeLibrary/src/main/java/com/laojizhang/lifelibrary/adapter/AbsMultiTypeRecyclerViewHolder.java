package com.laojizhang.lifelibrary.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * 文件名称： AbsMultiTypeRecyclerViewHolder
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-10:16
 * 文件描述：
 * <p>
 */
public class AbsMultiTypeRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    private ViewDataBinding mViewDataBinding;
    private DatabindingRecyclerDelegate<T> mViewHolderDelegate;

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    public DatabindingRecyclerDelegate<T> getViewHolderDelegate() {
        return mViewHolderDelegate;
    }

    public AbsMultiTypeRecyclerViewHolder(ViewDataBinding viewDataBinding, DatabindingRecyclerDelegate<T> proxy) {
        super(viewDataBinding.getRoot());
        mViewDataBinding = viewDataBinding;
        mViewHolderDelegate = proxy;
    }
}
