package com.timmy.tdialog.listener;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

/**
 * @author Roshine
 * @date 2020/6/22 15:13
 * @desc
 */
public class WeakOnDismissListener implements DialogInterface.OnDismissListener {
    private WeakReference<DialogInterface.OnDismissListener> mRef;

    public WeakOnDismissListener(DialogInterface.OnDismissListener real) {
        this.mRef = new WeakReference<>(real);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        DialogInterface.OnDismissListener real = mRef.get();
        if (real != null) {
            real.onDismiss(dialog);
        }
    }
}
