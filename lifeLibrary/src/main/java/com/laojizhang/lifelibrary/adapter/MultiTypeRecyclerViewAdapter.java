package com.laojizhang.lifelibrary.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名称： MultiTypeRecyclerViewAdapter
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-10:16
 * 文件描述：
 * <p>
 */

public abstract class MultiTypeRecyclerViewAdapter<D> extends RecyclerView.Adapter<AbsMultiTypeRecyclerViewHolder> {

    private static final int VIEWTYPE_HEADER = 1 << 0;
    private static final int VIEWTYPE_BODY = 1 << 1;
    private static final int VIEWTYPE_FOOTER = 1 << 2;

    private RecyclerView mRecyclerView;
    private Context mContext;
    private AbsMultiTypeRecyclerViewHolder mHeaderViewHolder;
    private AbsMultiTypeRecyclerViewHolder mFooterViewHolder;
    private List mDatas = new ArrayList<>();

    public MultiTypeRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public MultiTypeRecyclerViewAdapter(RecyclerView view) {
        mRecyclerView = view;
        mContext = view.getContext();
    }

    public void setBindingDatas(List datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }


    public void addHeaderView(Object object, DatabindingRecyclerDelegate delegate) {
        checkRecyclerNotNull();
        ViewDataBinding headerBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), delegate.getItemLayoutResId(), mRecyclerView, false);
        mHeaderViewHolder = new AbsMultiTypeRecyclerViewHolder(headerBinding, delegate);
        mDatas.add(0, object);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void removeHeaderView() {
        if (mHeaderViewHolder != null) {
            notifyItemRemoved(0);
            mDatas.remove(0);
            mHeaderViewHolder = null;
        }
    }

    private void checkRecyclerNotNull() {
        if (mRecyclerView == null)
            throw new RuntimeException("如需使用headerView或者footerView 请使用 public MultiTypeRecyclerViewAdapter(RecyclerView view) 构造方法");
    }

    public int getHeaderCount() {
        return mHeaderViewHolder == null ? 0 : 1;
    }

    public void addFooterView(Object object, DatabindingRecyclerDelegate delegate) {
        checkRecyclerNotNull();
        ViewDataBinding headerBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), delegate.getItemLayoutResId(), mRecyclerView, false);
        mFooterViewHolder = new AbsMultiTypeRecyclerViewHolder(headerBinding, delegate);
        mDatas.add(object);
        notifyDataSetChanged();
    }

    public void removeFooterView() {
        if (mHeaderViewHolder != null) {
            int position = mDatas.size() - 1;
            notifyItemRemoved(position);
            mDatas.remove(position);
            mFooterViewHolder = null;
        }
    }

    public int getFooterCount() {
        return mFooterViewHolder == null ? 0 : 1;
    }

    public abstract DatabindingRecyclerDelegate<D> createProxy();

    public ViewDataBinding createBodyViewDataBinding(int layoutId) {
        return DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutId, null, false);
    }

    public AbsMultiTypeRecyclerViewHolder<D> createViewHolder() {
        DatabindingRecyclerDelegate proxy = createProxy();
        return new AbsMultiTypeRecyclerViewHolder<>(createBodyViewDataBinding(proxy.getItemLayoutResId()), proxy);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderViewHolder != null && position < getHeaderCount())
            return VIEWTYPE_HEADER;
        else if (mFooterViewHolder != null && position == getItemCount() - 1)
            return VIEWTYPE_FOOTER;
        else
            return VIEWTYPE_BODY;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public AbsMultiTypeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEWTYPE_HEADER:
                if (mHeaderViewHolder == null)
                    throw new RuntimeException("not add headerView");
                return mHeaderViewHolder;
            case VIEWTYPE_BODY:
                return createViewHolder();
            case VIEWTYPE_FOOTER:
                if (mFooterViewHolder == null)
                    throw new RuntimeException("not add footerView");
                return mFooterViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(AbsMultiTypeRecyclerViewHolder holder, int position) {
        holder.getViewDataBinding().getRoot().setTag(getItem(position));
        holder.getViewHolderDelegate().onBindItem(holder.getViewDataBinding(), getItem(position), position);
    }

    public Object getItem(int position) {
        if (position < mDatas.size()) {
            return mDatas.get(position);
        }
        return null;
    }
}
