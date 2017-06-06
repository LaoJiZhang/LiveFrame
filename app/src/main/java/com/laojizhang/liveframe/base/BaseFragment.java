package com.laojizhang.liveframe.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.View;

import com.laojizhang.lifelibrary.model.BaseLifeModel;
import com.laojizhang.lifelibrary.ui.IPageCreateView;
import com.laojizhang.lifelibrary.ui.fragment.BaseLifeFragment;

/**
 * 文件名称： BaseFragment
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-00:43
 * 文件描述：
 * <p>
 */

public abstract class BaseFragment<D extends ViewDataBinding, M extends BaseLifeModel> extends BaseLifeFragment<D, M> {

    @NonNull
    @Override
    protected IPageCreateView createPageCreateView() {
        return new BasePageCreateView(getActivity(), createNetErrBtnClickListener());
    }

    public View.OnClickListener createNetErrBtnClickListener() {
        return null;
    }
}
