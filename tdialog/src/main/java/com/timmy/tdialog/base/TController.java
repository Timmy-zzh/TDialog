package com.timmy.tdialog.base;

import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;

import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;

import java.io.Serializable;

public class TController implements Serializable {

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

    ///////get
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

    /////////////////set
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public void setDimAmount(float mDimAmount) {
        this.mDimAmount = mDimAmount;
    }

    public void setGravity(int mGravity) {
        this.mGravity = mGravity;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public void setCancelableOutside(boolean mIsCancelableOutside) {
        this.mIsCancelableOutside = mIsCancelableOutside;
    }

    public void setOnViewClickListener(OnViewClickListener mOnViewClickListener) {
        this.mOnViewClickListener = mOnViewClickListener;
    }

    public void setBindViewListener(OnBindViewListener bindViewListener) {
        this.bindViewListener = bindViewListener;
    }

    public static class TParams {
        public FragmentManager fragmentManager;
        public int layoutRes;
        public int mWidth;
        public int mHeight;
        public float mDimAmount;
        public int mGravity;
        public String mTag;
        public int[] ids;
        public boolean mIsCancelableOutside = true;
        public OnViewClickListener mOnViewClickListener;
        public OnBindViewListener bindViewListener;

        public void apply(TController tController) {
            if (fragmentManager != null) {
                tController.fragmentManager = fragmentManager;
            }
            if (layoutRes > 0) {
                tController.layoutRes = layoutRes;
            } else {
                throw new IllegalArgumentException("DjDialog.Buidler should set LayoutRes");
            }
            if (mWidth > 0) {
                tController.mWidth = mWidth;
            }
            if (mHeight > 0) {
                tController.mHeight = mHeight;
            }
            if (mDimAmount > 0f) {
                tController.mDimAmount = mDimAmount;
            } else {
                tController.mDimAmount = 0.2f;//默认0.2
            }
            if (mGravity > 0) {
                tController.mGravity = mGravity;
            } else {
                tController.mGravity = Gravity.CENTER;
            }
            if (TextUtils.isEmpty(mTag)) {
                tController.mTag = mTag;
            } else {
                tController.mTag = "DjDialog";
            }
            if (ids != null) {
                tController.ids = ids;
            }
            tController.mIsCancelableOutside = mIsCancelableOutside;
            tController.mOnViewClickListener = mOnViewClickListener;
            tController.bindViewListener = bindViewListener;
        }
    }
}
