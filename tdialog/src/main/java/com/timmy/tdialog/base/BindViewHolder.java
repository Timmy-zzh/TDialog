package com.timmy.tdialog.base;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

import com.timmy.tdialog.TDialog;

/**
 * 借鉴RecyclerView.Adapter的ViewHolder写法
 * 将Dialog的根布局传入,主要处理点击方法
 * @author Timmy
 * @time 2017/12/28 16:18
 **/
public class BindViewHolder {

    public  View bindView;
    private  SparseArray<View> views;
    private  TDialog dialog;

    public BindViewHolder(View bindView, TDialog tDialog) {
        this.bindView = bindView;
        this.views = new SparseArray<>();
        this.dialog = tDialog;
    }

    public BindViewHolder() {
        this.views = new SparseArray<>();
    }

    public void setBindView(View bindView) {
        this.bindView = bindView;
    }

    public void setDialog(TDialog tDialog) {
        this.dialog = tDialog;
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = bindView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BindViewHolder addOnClickListener(@IdRes final int viewId) {
        final View view = getView(viewId);
        if (view != null) {
            if (!view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.getOnItemChildClickListener().onItemChildClick(BindViewHolder.this,view);
                }
            });
        }
        return this;
    }

}
