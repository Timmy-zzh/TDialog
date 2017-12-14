package com.timmy.tdialog.dialog;

/**
 * DialogFragment弹窗结束时,传值回调接口
 */
public interface OnDialogResultListener<T> {
    void getDialogResult(T t);
}
