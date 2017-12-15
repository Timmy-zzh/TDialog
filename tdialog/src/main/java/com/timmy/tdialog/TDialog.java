package com.timmy.tdialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.timmy.tdialog.base.BaseDialogFragment;

/**
 * 设置屏幕宽高比例值
 * 设置弹出键盘
 * 设置返回键是否可以取消
 */
public class TDialog extends BaseDialogFragment {

    private static final String KEY_LAYOUT_RES = "layoutRes";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_DIMAMOUNT = "dimAmount";
    private static final String KEY_CANCEL_OUTSIDE = "isCancelOutside";

    private FragmentManager fragmentManager;
    private boolean mIsCancelOutside = super.getCancelOutside();
    private String mTag = super.getFragmentTag();
    private float mDimAmount = super.getDimAmount();
    private int mHeight = super.getDialogHeight();
    private int mWidth = super.getDialogWidth();
    private int mGravity = super.getGravity();

    @LayoutRes
    private int layoutRes;
    private OnBindViewListener bindViewListener;

    public static TDialog create(FragmentManager manager) {
        TDialog dialog = new TDialog();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    /**
     * 当设备旋转时,会重新调用onCreate,进行数据恢复
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            layoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mWidth = savedInstanceState.getInt(KEY_WIDTH);
            mDimAmount = savedInstanceState.getFloat(KEY_DIMAMOUNT);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
        }
    }

    /**
     * 进行数据保存
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, layoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
        outState.putInt(KEY_WIDTH, mWidth);
        outState.putFloat(KEY_DIMAMOUNT, mDimAmount);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);
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

    /**
     * 设置屏幕高度比例 0 -1
     */
    public TDialog setScreenHeightAspect(float heightAspect) {
        mHeight = (int) (getWindowHeight() * heightAspect);
        return this;
    }

    public TDialog setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }

    /**
     * 设置弹窗宽度是屏幕宽度的比例 0 -1
     */
    public TDialog setScreenWidthAspect(float widthAspect) {
        mWidth = (int) ((int) getWindowWidth() * widthAspect);
        return this;
    }

    public TDialog setWidth(int width) {
        mWidth = width;
        return this;
    }

    public TDialog setGravity(int gravity) {
        this.mGravity = gravity;
        return this;
    }

    @Override
    public int getGravity() {
        return mGravity;
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
        //判断是否传入了布局id
        show(fragmentManager);
        return this;
    }

}
