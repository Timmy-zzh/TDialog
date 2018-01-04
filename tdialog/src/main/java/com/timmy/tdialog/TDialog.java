package com.timmy.tdialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.timmy.tdialog.base.BaseDialogFragment;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.base.TController;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.timmy.tdialog.util.DensityUtils;

/**
 * 设置屏幕宽高比例值
 * 设置弹出键盘
 * 设置返回键是否可以取消
 */
public class TDialog extends BaseDialogFragment {

    private static final String KEY_DJCONTROLLER = "DjController";
    protected TController tController;

    public TDialog() {
        tController = new TController();
    }

    /**
     * 当设备旋转时,会重新调用onCreate,进行数据恢复
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            tController = (TController) savedInstanceState.getSerializable(KEY_DJCONTROLLER);
        }
    }

    /**
     * 进行数据保存
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_DJCONTROLLER, tController);
        super.onSaveInstanceState(outState);
    }

    /**
     * 获取弹窗xml布局界面
     *
     * @return 基类调用
     */
    @Override
    protected int getLayoutRes() {
        return tController.getLayoutRes();
    }

    protected void setLayoutRes(@LayoutRes int layoutRes) {
        tController.setLayoutRes(layoutRes);
    }

    @Override
    protected void bindView(View view) {
        BindViewHolder viewHolder = new BindViewHolder(view, this);
        if (tController.getIds() != null && tController.getIds().length > 0) {
            for (int id : tController.getIds()) {
                viewHolder.addOnClickListener(id);
            }
        }
        if (tController.getBindViewListener() != null) {
            tController.getBindViewListener().bindView(viewHolder);
        }
    }

    @Override
    public int getGravity() {
        return tController.getGravity();
    }

    protected void setGravity(int gravity) {
        tController.setGravity(gravity);
    }

    @Override
    public float getDimAmount() {
        return tController.getDimAmount();
    }

    protected void setDimAmount(@FloatRange(from = 0f, to = 1.0f) float dimAmount) {
        tController.setDimAmount(dimAmount);
    }

    @Override
    public int getDialogHeight() {
        return tController.getHeight();
    }

    protected void setDialogHeight(int height) {
        tController.setHeight(height);
    }

    @Override
    public int getDialogWidth() {
        return tController.getWidth();
    }

    protected void setDialogWidth(int width) {
        tController.setWidth(width);
    }

    @Override
    public boolean getCancelableOutside() {
        return tController.isCancelableOutside();
    }

    protected void setCancelableOutside(boolean cancelable) {
        tController.setCancelableOutside(cancelable);
    }

    @Override
    public String getFragmentTag() {
        return tController.getTag();
    }

    protected void setFragmentTag(String tag) {
        tController.setTag(tag);
    }

    public OnViewClickListener getOnViewClickListener() {
        return tController.getOnViewClickListener();
    }

    protected void setOnViewClickListener(OnViewClickListener listener) {
        tController.setOnViewClickListener(listener);
    }

    public TDialog show() {
        //如果宽高都没有设置,则默认给弹窗提供宽度为800
        if (tController.getWidth() <= 0 && tController.getHeight() <= 0) {
            tController.setWidth(600);
        }
        show(tController.getFragmentManager());
        return this;
    }

    /*********************************************************************
     * 使用Builder模式实现
     *
     */
    public static class Builder {

        TController.TParams params;

        public Builder(FragmentManager fragmentManager) {
            params = new TController.TParams();
            params.fragmentManager = fragmentManager;
        }

        //各种setXXX()方法设置数据
        public Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.layoutRes = layoutRes;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public Builder setScreenWidthAspect(Activity activity, float widthAspect) {
            params.mWidth = (int) (DensityUtils.getWindowWidth(activity) * widthAspect);
            return this;
        }

        public Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        /**
         * 设置屏幕高度比例 0 -1
         */
        public Builder setScreenHeightAspect(Activity activity, float heightAspect) {
            params.mHeight = (int) (DensityUtils.getWindowHeight(activity) * heightAspect);
            return this;
        }

        public Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        public Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public Builder setCancelOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        public Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        public Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        public Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        public Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        //列表数据,需要传入数据和Adapter


        public TDialog create() {
            TDialog dialog = new TDialog();
            //将数据从Buidler的DjParams中传递到DjDialog中
            params.apply(dialog.tController);
            return dialog;
        }
    }
}
