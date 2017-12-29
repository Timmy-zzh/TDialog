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

import com.timmy.tdialog.listener.OnDialogResultListener;

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
 *      a.xml布局
 *      b.宽高
 *      c.位置
 *      d.背景色
 *      e.透明度
 *      f.是否可以点击空白处隐藏
 *      控制方法在onStart处理,
 * 4.暴露方法:界面中控件处理和点击事件处理
 * 5.监听回调,很多弹窗需要输入信息,然后将输入的信息通过回调的方法返回
 * 6.当设备Configure属性变化时,数据保存和恢复处理
 *
 * @author Timmy
 * @time 2017/12/28 15:25
 **/
public abstract class BaseDialogFragment extends DialogFragment {

    public static final String TAG = "BaseDialogFragment";
    private static final float DEFAULT_DIMAMOUNT = 0.2F;
    private OnDialogResultListener onDialogResultListener;
    private int dialogWidth = WindowManager.LayoutParams.WRAP_CONTENT;
    private int dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    private int gravity = Gravity.CENTER;

    protected abstract int getLayoutRes();

    protected abstract void bindView(View view);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDialogResultListener) {
            onDialogResultListener = (OnDialogResultListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除Dialog默认头部
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(getCancelOutside());
        View view = inflater.inflate(getLayoutRes(), container, false);
        bindView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    public int getGravity() {
        return gravity;
    }

    public int getDialogHeight() {
        return dialogHeight;
    }

    public int getDialogWidth() {
        return dialogWidth;
    }

    public float getDimAmount() {
        return DEFAULT_DIMAMOUNT;
    }

    /**
     * 默认Dialog外部点击可以取消显示
     */
    public boolean getCancelOutside() {
        return true;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }

}
