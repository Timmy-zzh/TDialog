package com.timmy.tdialog.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * @author Roshine
 * @date 2020/6/22 14:43
 * @desc 避免内存泄漏的Dialog,在DialogFragment中重写onCreateDialog中返回该dialog
 *
 */
public class WeakDialog extends Dialog {
    protected WeakDialog(@NonNull Context context) {
        super(context);
    }

    public WeakDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WeakDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setOnShowListener(@Nullable DialogInterface.OnShowListener listener) {
        super.setOnShowListener(WeakDialogProxy.proxy(listener));
    }

    @Override
    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener listener) {
        super.setOnDismissListener(WeakDialogProxy.proxy(listener));
    }

    @Override
    public void setOnCancelListener(@Nullable DialogInterface.OnCancelListener listener) {
        super.setOnCancelListener(WeakDialogProxy.proxy(listener));
    }
}
