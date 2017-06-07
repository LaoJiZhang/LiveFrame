package com.laojizhang.lifelibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * 文件名称： OnRecyclerViewAttachItemClickListener
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-10:16
 * 文件描述：
 * <p>
 */
public abstract class OnRecyclerViewAttachItemClickListener implements RecyclerView.OnChildAttachStateChangeListener {

    @Override
    public void onChildViewAttachedToWindow(final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickEvent(getData(view));
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickEvent(getData(view));
                return false;
            }
        });
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
    }

    private Object getData(View view) {
        return view.getTag();
    }

    public abstract void onItemClickEvent(Object data);

    public abstract void onItemLongClickEvent(Object data);
}
