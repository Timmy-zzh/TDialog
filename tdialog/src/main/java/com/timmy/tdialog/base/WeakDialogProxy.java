package com.timmy.tdialog.base;

import android.content.DialogInterface;

import com.timmy.tdialog.listener.WeakOnCancelListener;
import com.timmy.tdialog.listener.WeakOnDismissListener;
import com.timmy.tdialog.listener.WeakOnShowListener;

/**
 * @author Roshine
 * @date 2020/6/22 15:10
 * @desc
 */
public class WeakDialogProxy {

    public static WeakOnCancelListener proxy(DialogInterface.OnCancelListener real) {
        return new WeakOnCancelListener(real);
    }

    public static WeakOnDismissListener proxy(DialogInterface.OnDismissListener  real) {
        return new WeakOnDismissListener(real);
    }

    public static WeakOnShowListener proxy(DialogInterface.OnShowListener real) {
        return new WeakOnShowListener(real);
    }
}
