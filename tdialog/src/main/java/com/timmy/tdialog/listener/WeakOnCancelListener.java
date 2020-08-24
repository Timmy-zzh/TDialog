package com.timmy.tdialog.listener;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

/**
 * @author Roshine
 * @date 2020/6/22 15:11
 * @desc
 */
public class WeakOnCancelListener implements DialogInterface.OnCancelListener {
    private WeakReference<DialogInterface.OnCancelListener> mRef;

    public WeakOnCancelListener(DialogInterface.OnCancelListener real) {
        this.mRef = new WeakReference<>(real);
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        DialogInterface.OnCancelListener real = mRef.get();
        if (real != null) {
            real.onCancel(dialog);
        }
    }
}
