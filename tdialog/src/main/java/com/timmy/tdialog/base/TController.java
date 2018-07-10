package com.timmy.tdialog.base;

import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.timmy.tdialog.R;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;

import java.io.Serializable;

/**
 * 数据保存封装的容器类
 *
 * @author Timmy
 * @time 2018/1/24 14:40
 * @GitHub https://github.com/Timmy-zzh/TDialog
 **/
public class TController<A extends TBaseAdapter> implements Parcelable, Serializable {

    private FragmentManager fragmentManager;
    private int layoutRes;
    private int height;
    private int width;
    private float dimAmount;
    private int gravity;
    private String tag;
    private int[] ids;
    private boolean isCancelableOutside;
    private OnViewClickListener onViewClickListener;
    private OnBindViewListener onBindViewListener;
    private A adapter;
    private TBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
    private int orientation;
    private int dialogAnimationRes;
    private View dialogView;
    private DialogInterface.OnDismissListener onDismissListener;


    //////////////////////////////////////////Parcelable持久化//////////////////////////////////////////////////////
    public TController() {
    }

    protected TController(Parcel in) {
        layoutRes = in.readInt();
        height = in.readInt();
        width = in.readInt();
        dimAmount = in.readFloat();
        gravity = in.readInt();
        tag = in.readString();
        ids = in.createIntArray();
        isCancelableOutside = in.readByte() != 0;
        orientation = in.readInt();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(layoutRes);
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeFloat(dimAmount);
        dest.writeInt(gravity);
        dest.writeString(tag);
        dest.writeIntArray(ids);
        dest.writeByte((byte) (isCancelableOutside ? 1 : 0));
        dest.writeInt(orientation);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //get
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int mWidth) {
        this.width = mWidth;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public int getGravity() {
        return gravity;
    }

    public String getTag() {
        return tag;
    }

    public int[] getIds() {
        return ids;
    }

    public boolean isCancelableOutside() {
        return isCancelableOutside;
    }

    public OnViewClickListener getOnViewClickListener() {
        return onViewClickListener;
    }

    public int getOrientation() {
        return orientation;
    }

    public OnBindViewListener getOnBindViewListener() {
        return onBindViewListener;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public View getDialogView() {
        return dialogView;
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

    public int getDialogAnimationRes() {
        return dialogAnimationRes;
    }

    /**************************************************************************
     */
    public static class TParams<A extends TBaseAdapter> {
        public FragmentManager mFragmentManager;
        public int mLayoutRes;
        public int mWidth;
        public int mHeight;
        public float mDimAmount = 0.2f;
        public int mGravity = Gravity.CENTER;
        public String mTag = "TDialog";
        public int[] ids;
        public boolean mIsCancelableOutside = true;
        public OnViewClickListener mOnViewClickListener;
        public OnBindViewListener bindViewListener;
        public int mDialogAnimationRes = 0;//弹窗动画
        //列表
        public A adapter;
        public TBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
        public int listLayoutRes;
        public int orientation = LinearLayoutManager.VERTICAL;//默认RecyclerView的列表方向为垂直方向
        public View mDialogView;//直接使用传入进来的View,而不需要通过解析Xml
        public DialogInterface.OnDismissListener mOnDismissListener;

        public void apply(TController tController) {
            tController.fragmentManager = mFragmentManager;
            if (mLayoutRes > 0) {
                tController.layoutRes = mLayoutRes;
            }
            if (mDialogView != null) {
                tController.dialogView = mDialogView;
            }
            if (mWidth > 0) {
                tController.width = mWidth;
            }
            if (mHeight > 0) {
                tController.height = mHeight;
            }
            tController.dimAmount = mDimAmount;
            tController.gravity = mGravity;
            tController.tag = mTag;
            if (ids != null) {
                tController.ids = ids;
            }
            tController.isCancelableOutside = mIsCancelableOutside;
            tController.onViewClickListener = mOnViewClickListener;
            tController.onBindViewListener = bindViewListener;
            tController.onDismissListener = mOnDismissListener;
            tController.dialogAnimationRes = mDialogAnimationRes;

            if (adapter != null) {
                tController.setAdapter(adapter);
                if (listLayoutRes <= 0) {//使用默认的布局
                    tController.setLayoutRes(R.layout.dialog_recycler);
                } else {
                    tController.setLayoutRes(listLayoutRes);
                }
                tController.orientation = orientation;
            } else {
                if (tController.getLayoutRes() <= 0 && tController.getDialogView() == null) {
                    throw new IllegalArgumentException("请先调用setLayoutRes()方法设置弹窗所需的xml布局!");
                }
            }
            if (adapterItemClickListener != null) {
                tController.setAdapterItemClickListener(adapterItemClickListener);
            }

            //如果宽高都没有设置,则默认给弹窗提供宽度为600
            if (tController.width <= 0 && tController.height <= 0) {
                tController.width = 600;
            }
        }
    }
}
