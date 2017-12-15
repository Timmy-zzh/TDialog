package com.timmy.tdialog.base;

/**
 * DialogFragment弹窗结束时,传值回调接口
 */
public interface OnDialogResultListener<T> {
    void getDialogResult(T t);
}
