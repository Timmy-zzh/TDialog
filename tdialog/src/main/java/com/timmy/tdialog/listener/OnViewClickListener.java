package com.timmy.tdialog.listener;

import android.view.View;

import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;

public interface OnViewClickListener {
    void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog);
}
