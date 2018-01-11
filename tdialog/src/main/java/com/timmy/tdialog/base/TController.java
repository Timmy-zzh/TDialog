package com.timmy.tdialog.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;

import com.timmy.tdialog.R;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;

import java.io.Serializable;

public class TController<A extends TBaseAdapter> implements Parcelable, Serializable {

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
    private A adapter;
    private TBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
    private int mOrientation;

    public TController() {
    }

    protected TController(Parcel in) {
        layoutRes = in.readInt();
        mHeight = in.readInt();
        mWidth = in.readInt();
        mDimAmount = in.readFloat();
        mGravity = in.readInt();
        mTag = in.readString();
        ids = in.createIntArray();
        mIsCancelableOutside = in.readByte() != 0;
    }

    public static final Creator<TController> CREATOR = new Creator<TController>() {
        @Override
        public TController createFromParcel(Parcel in) {
            return new TController(in);
        }

        @Override
        public TController[] newArray(int size) {
            return new TController[size];
        }
    };

    //内容描述接口,不用管
    @Override
    public int describeContents() {
        return 0;
    }

    //写入接口,
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(layoutRes);
        dest.writeInt(mHeight);
        dest.writeInt(mWidth);
        dest.writeFloat(mDimAmount);
        dest.writeInt(mGravity);
        dest.writeString(mTag);
        dest.writeIntArray(ids);
        dest.writeByte((byte) (mIsCancelableOutside ? 1 : 0));
    }

    //get
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

    public int getOrientation() {
        return mOrientation;
    }

    //set
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

    //列表
    public A getAdapter() {
        return adapter;
    }

    public void setAdapter(A adapter) {
        this.adapter = adapter;
    }

    public TBaseAdapter.OnAdapterItemClickListener getAdapterItemClickListener() {
        return adapterItemClickListener;
    }

    public void setAdapterItemClickListener(TBaseAdapter.OnAdapterItemClickListener adapterItemClickListener) {
        this.adapterItemClickListener = adapterItemClickListener;
    }

    public static class TParams<A extends TBaseAdapter> {
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
        //列表
        public A adapter;
        public TBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
        public int listLayoutRes;
        public int orientation = LinearLayoutManager.VERTICAL;//默认RecyclerView的列表方向为垂直方向

        public void apply(TController tController) {
            if (fragmentManager != null) {
                tController.fragmentManager = fragmentManager;
            }
            if (layoutRes > 0) {
                tController.layoutRes = layoutRes;
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

            if (adapter != null) {
                tController.setAdapter(adapter);
                if (listLayoutRes <= 0) {//使用默认的布局
                    tController.setLayoutRes(R.layout.dialog_recycler);
                } else {
                    tController.setLayoutRes(listLayoutRes);
                }
                tController.mOrientation = orientation;
            } else {
                if (tController.getLayoutRes() <= 0) {
                    throw new IllegalArgumentException("请先调用setLayoutRes()方法设置弹窗所需的xml布局!");
                }
            }

            if (adapterItemClickListener != null) {
                tController.setAdapterItemClickListener(adapterItemClickListener);
            }
        }
    }
}
