package com.timmy.tdialog.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * DialogFragment的基类
 * 1.默认的onCreateDialog方法返回的是一个实例化一个系统的Dialog,我们不用
 * 2.在onCreateView方法中返回Dialog需要展示的布局界面,具体返回布局还是交给子类出实现
 * 3.Dialog的展示控制方法onStart处理,需要设置宽高,背景色,位置等控制属性
 * 4.监听回调,很多弹窗需要输入信息,然后将输入的信息通过回调的方法返回
 */
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
        Log.d(TAG, "onAttach");
        if (context instanceof OnDialogResultListener) {
            onDialogResultListener = (OnDialogResultListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
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
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
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

    /**
     * 获得屏幕宽/高
     */
    public int getWindowHeight() {
        return getActivity().getWindowManager().getDefaultDisplay().getHeight();
    }

    public  int getWindowWidth() {
        return getActivity().getWindowManager().getDefaultDisplay().getWidth();
    }
}
