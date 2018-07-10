package com.timmy.tdialog.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.timmy.tdialog.R;

/**
 * DialogFragment的基类
 * 1.系统默认onCreateDialog方法返回一个Dialog对象,对其不做处理
 * 2.主要操作onCreateView方法
 * 因为DialogFragment继承自Fragment,所以可以在onCreteView()方法返回xml布局,
 * 该布局在onActivityCreated()方法中,设置给系统之前创建的Dialog对象
 * //           @Override
 * //            public void onActivityCreated(Bundle savedInstanceState) {
 * //                super.onActivityCreated(savedInstanceState);
 * //
 * //                if (!mShowsDialog) {
 * //                return;
 * //                }
 * //
 * //                View view = getView();
 * //                if (view != null) {
 * //                if (view.getParent() != null) {
 * //                throw new IllegalStateException(
 * //                "DialogFragment can not be attached to a container view");
 * //                }
 * //                mDialog.setContentView(view);
 * //                }
 * //           }
 * 3.对应使用Dialog不同部分包括
 * a.xml布局
 * b.宽高
 * c.位置
 * d.背景色
 * e.透明度
 * f.是否可以点击空白处隐藏
 * 控制方法在onStart处理,
 * 4.暴露方法:界面中控件处理和点击事件处理
 * 5.监听回调,很多弹窗需要输入信息,然后将输入的信息通过回调的方法返回
 * 6.当设备Configure属性变化时,数据保存和恢复处理
 **/
public abstract class BaseDialogFragment extends DialogFragment {

    public static final String TAG = "TDialog";
    private static final float DEFAULT_DIMAMOUNT = 0.2F;

    protected abstract int getLayoutRes();

    protected abstract void bindView(View view);

    protected abstract View getDialogView();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getLayoutRes() > 0) {
            view = inflater.inflate(getLayoutRes(), container, false);
        }
        if (getDialogView() != null) {
            view = getDialogView();
        }
        bindView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //去除Dialog默认头部
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(isCancelableOutside());
        if (dialog.getWindow() != null && getDialogAnimationRes() > 0) {
            dialog.getWindow().setWindowAnimations(getDialogAnimationRes());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置窗体背景色透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置宽高
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            if (getDialogWidth() > 0) {
                layoutParams.width = getDialogWidth();
            } else {
                layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            if (getDialogHeight() > 0) {
                layoutParams.height = getDialogHeight();
            } else {
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            //透明度
            layoutParams.dimAmount = getDimAmount();
            //位置
            layoutParams.gravity = getGravity();
            window.setAttributes(layoutParams);
        }
    }

    //默认弹窗位置为中心
    public int getGravity() {
        return Gravity.CENTER;
    }

    //默认宽高为包裹内容
    public int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public int getDialogWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    //默认透明度为0.2
    public float getDimAmount() {
        return DEFAULT_DIMAMOUNT;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }

    protected boolean isCancelableOutside() {
        return true;
    }

    //获取弹窗显示动画,子类实现
    protected int getDialogAnimationRes() {
        return 0;
    }

    //获取设备屏幕宽度
    public static final int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    //获取设备屏幕高度
    public static final int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
