package com.timmy.tdialog.base;

import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;

import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;

import java.io.Serializable;

public class DjController implements Serializable {

    private FragmentManager fragmentManager;
    private int layoutRes;
    private int mHeight;
    private int mWidth;
    private float mDimAmount;
    private int mGravity;
    private String mTag;
    private int[] ids;
    private boolean mIsCancelableOutside;
    private OnViewClickListener mOnViewClickListener;
    private OnBindViewListener bindViewListener;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public float getDimAmount() {
        return mDimAmount;
    }

    public int getGravity() {
        return mGravity;
    }

    public String getTag() {
        return mTag;
    }

    public int[] getIds() {
        return ids;
    }

    public boolean isCancelableOutside() {
        return mIsCancelableOutside;
    }

    public OnViewClickListener getOnViewClickListener() {
        return mOnViewClickListener;
    }

    public OnBindViewListener getBindViewListener() {
        return bindViewListener;
    }

    public static class DjParams {

        public FragmentManager fragmentManager;
        public int layoutRes;
        public int mWidth;
        public int mHeight;
        public float mDimAmount;
        public int mGravity;
        public String mTag;
        public int[] ids;
        public boolean mIsCancelableOutside;
        public OnViewClickListener mOnViewClickListener;
        public OnBindViewListener bindViewListener;

        public void apply(DjController djController) {
            if (fragmentManager != null) {
                djController.fragmentManager = fragmentManager;
            }
            if (layoutRes > 0) {
                djController.layoutRes = layoutRes;
            } else {
                throw new IllegalArgumentException("DjDialog.Buidler should set LayoutRes");
            }
            if (mWidth > 0) {
                djController.mWidth = mWidth;
            }
            if (mHeight > 0) {
                djController.mHeight = mHeight;
            }
            if (mDimAmount > 0f) {
                djController.mDimAmount = mDimAmount;
            } else {
                djController.mDimAmount = 0.2f;//默认0.2
            }
            if (mGravity > 0) {
                djController.mGravity = mGravity;
            } else {
                djController.mGravity = Gravity.CENTER;
            }
            if (TextUtils.isEmpty(mTag)) {
                djController.mTag = mTag;
            } else {
                djController.mTag = "DjDialog";
            }
            if (ids != null) {
                djController.ids = ids;
            }
            djController.mIsCancelableOutside = mIsCancelableOutside;
            djController.mOnViewClickListener = mOnViewClickListener;
            djController.bindViewListener = bindViewListener;
        }
    }
}
