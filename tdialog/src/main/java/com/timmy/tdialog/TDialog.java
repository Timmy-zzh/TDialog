package com.timmy.tdialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.timmy.tdialog.base.BaseDialogFragment;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.base.DjController;
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
    private DjController djController;

    public TDialog() {
        djController = new DjController();
    }

    /**
     * 当设备旋转时,会重新调用onCreate,进行数据恢复
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            djController = (DjController) savedInstanceState.getSerializable(KEY_DJCONTROLLER);
        }
    }

    /**
     * 进行数据保存
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_DJCONTROLLER, djController);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutRes() {
        return djController.getLayoutRes();
    }

    @Override
    protected void bindView(View view) {
        BindViewHolder viewHolder = new BindViewHolder(view, this);
        if (djController.getIds() != null && djController.getIds().length > 0) {
            for (int id : djController.getIds()) {
                viewHolder.addOnClickListener(id);
            }
        }
        if (djController.getBindViewListener() != null) {
            djController.getBindViewListener().bindView(viewHolder);
        }
    }

    @Override
    public int getGravity() {
        return djController.getGravity();
    }

    @Override
    public float getDimAmount() {
        return djController.getDimAmount();
    }

    @Override
    public int getDialogHeight() {
        return djController.getHeight();
    }

    @Override
    public int getDialogWidth() {
        return djController.getWidth();
    }

    @Override
    public boolean getCancelOutside() {
        return djController.isCancelableOutside();
    }

    @Override
    public String getFragmentTag() {
        return djController.getTag();
    }

    public OnViewClickListener getOnViewClickListener() {
        return djController.getOnViewClickListener();
    }

    public TDialog show() {
        //判断是否传入了布局id
        //如果宽高都没有设置,则默认给弹窗提供宽度为800
        if (djController.getWidth() <= 0 && djController.getHeight() <= 0) {
            djController.setWidth(600);
        }
        show(djController.getFragmentManager());
        return this;
    }

    /*********************************************************************
     * 使用Builder模式实现
     *
     */
    public static class Builder {

        DjController.DjParams params;

        public Builder(FragmentManager fragmentManager) {
            params = new DjController.DjParams();
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

        public Builder setWidth(int width) {
            params.mWidth = width;
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

        public TDialog create() {
            TDialog dialog = new TDialog();
            //将数据从Buidler的DjParams中传递到DjDialog中
            params.apply(dialog.djController);
            return dialog;
        }
    }
}
