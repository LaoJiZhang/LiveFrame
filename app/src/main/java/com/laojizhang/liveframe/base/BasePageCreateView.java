package com.laojizhang.liveframe.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.laojizhang.lifelibrary.ui.IPageCreateView;
import com.laojizhang.liveframe.R;
import com.laojizhang.liveframe.databinding.LayoutErrNetworkBinding;

/**
 * 文件名称： BasePageCreateView
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-00:31
 * 文件描述：
 * <p>
 */

public class BasePageCreateView implements IPageCreateView {

    private Context mContext;
    private LayoutInflater mInflater = null;
    private View.OnClickListener mNetErrorBtnClickListener;

    public BasePageCreateView(Context context, View.OnClickListener netErrorBtnClickListener) {
        mContext = context;
        mNetErrorBtnClickListener = netErrorBtnClickListener;
    }

    @Override
    public View createOwnLoadingView() {
        return createBinding(R.layout.layout_loading).getRoot();
    }

    @Override
    public View createOwnEmptyView() {
        return createBinding(R.layout.layout_empty).getRoot();
    }

    @Override
    public View createOwnErrorView() {
        LayoutErrNetworkBinding binding = (LayoutErrNetworkBinding) createBinding(R.layout.layout_err_network);
        binding.refreshRetryBtn.setOnClickListener(mNetErrorBtnClickListener);
        return binding.getRoot();
    }

    private ViewDataBinding createBinding(int layoutId) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        return DataBindingUtil.inflate(mInflater, layoutId, null, false);
    }
}
