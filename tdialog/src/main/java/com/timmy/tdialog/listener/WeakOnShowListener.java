package com.timmy.tdialog.listener;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

/**
 * @author Roshine
 * @date 2020/6/22 15:15
 * @desc
 */
public class WeakOnShowListener implements DialogInterface.OnShowListener {
    private WeakReference<DialogInterface.OnShowListener> mRef;

    public WeakOnShowListener(DialogInterface.OnShowListener real) {
        this.mRef = new WeakReference<>(real);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        DialogInterface.OnShowListener real = mRef.get();
        if (real != null) {
            real.onShow(dialog);
        }
    }
}
