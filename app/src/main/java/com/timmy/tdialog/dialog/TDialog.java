package com.timmy.tdialog.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 *  设置屏幕宽高比例值
 *  设置弹出键盘
 *  设置返回键是否可以取消
 */
public class TDialog extends BaseDialogFragment {

    private FragmentManager fragmentManager;
    private boolean mIsCancelOutside = super.getCancelOutside();
    private String mTag = super.getFragmentTag();
    private float mDimAmount = super.getDimAmount();
    private int mHeight = super.getDialogHeight();
    private int mWidth = super.getDialogWidth();
    private int gravity = super.getGravity();

    @LayoutRes
    private int layoutRes;
    private OnBindViewListener bindViewListener;

    public static TDialog create(FragmentManager manager) {
        TDialog dialog = new TDialog();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutRes() {
        return layoutRes;
    }

    @Override
    protected void bindView(View view) {
        if (bindViewListener != null) {
            bindViewListener.bindView(view);
        }
    }

    public TDialog setFragmentManager(FragmentManager manager) {
        fragmentManager = manager;
        return this;
    }

    public TDialog setOnBindViewListener(OnBindViewListener listener) {
        bindViewListener = listener;
        return this;
    }

    public TDialog setLayoutRes(@LayoutRes int layoutId) {
        layoutRes = layoutId;
        return this;
    }

    public TDialog setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public TDialog setTag(String tag) {
        mTag = tag;
        return this;
    }

    public TDialog setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public TDialog setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }


    public TDialog setWidth(int width) {
        mWidth = width;
        return this;
    }

    public TDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    @Override
    public int getGravity() {
        return gravity;
    }

    @Override
    public float getDimAmount() {
        return mDimAmount;
    }

    @Override
    public int getDialogHeight() {
        return mHeight;
    }

    @Override
    public int getDialogWidth() {
        return mWidth;
    }

    @Override
    public boolean getCancelOutside() {
        return mIsCancelOutside;
    }

    @Override
    public String getFragmentTag() {
        return mTag;
    }

    public interface OnBindViewListener {
        void bindView(View v);
    }

    public TDialog show() {
        show(fragmentManager);
        return this;
    }

}
