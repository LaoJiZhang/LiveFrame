package com.laojizhang.liveframe.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.View;

import com.laojizhang.lifelibrary.model.BaseLifeModel;
import com.laojizhang.lifelibrary.ui.IPageCreateView;
import com.laojizhang.lifelibrary.ui.activity.BaseLifeActivity;

/**
 * 文件名称： BaseActivity
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-23:59
 * 文件描述：
 * <p>
 */

public abstract class BaseActivity<D extends ViewDataBinding, M extends BaseLifeModel> extends BaseLifeActivity<D, M> {

    @NonNull
    @Override
    protected IPageCreateView createPageCreateView() {
        return new BasePageCreateView(getActivity(), createNetErrBtnClickListener());
    }

    public View.OnClickListener createNetErrBtnClickListener() {
        return null;
    }
}
