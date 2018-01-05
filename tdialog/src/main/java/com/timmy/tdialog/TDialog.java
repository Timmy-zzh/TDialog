package com.timmy.tdialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.timmy.tdialog.base.BaseDialogFragment;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.base.TBaseAdapter;
import com.timmy.tdialog.base.TController;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;

/**
 * @author Timmy
 * @time 2018/1/4 16:28
 **/
public class TDialog extends BaseDialogFragment {

    private static final String KEY_TCONTROLLER = "TController";
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
            tController = (TController) savedInstanceState.getSerializable(KEY_TCONTROLLER);
        }
    }

    /**
     * 进行数据保存
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_TCONTROLLER, tController);
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

    @Override
    protected void bindView(View view) {
        if (tController.getAdapter() != null) {//有设置列表
            //列表
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
            if (recyclerView == null) {
                throw new IllegalArgumentException("自定义列表xml布局,请设置RecyclerView的控件id为recycler_view");
            }
            tController.getAdapter().setTDialog(this);

            RecyclerView.LayoutManager layoutManager;
            if (tController.getLayoutManager() != null) {
                layoutManager = tController.getLayoutManager();
            } else {
                layoutManager = new LinearLayoutManager(view.getContext());
            }
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(tController.getAdapter());
            tController.getAdapter().notifyDataSetChanged();
            if (tController.getAdapterItemClickListener() != null) {
                tController.getAdapter().setOnAdapterItemClickListener(tController.getAdapterItemClickListener());
            }
        }

        //其他控件点击事件等处理
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

    @Override
    public float getDimAmount() {
        return tController.getDimAmount();
    }

    @Override
    public int getDialogHeight() {
        return tController.getHeight();
    }

    @Override
    public int getDialogWidth() {
        return tController.getWidth();
    }

    @Override
    public boolean getCancelableOutside() {
        return tController.isCancelableOutside();
    }

    @Override
    public String getFragmentTag() {
        return tController.getTag();
    }

    public OnViewClickListener getOnViewClickListener() {
        return tController.getOnViewClickListener();
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

        //设置自定义列表布局和方向
        public Builder setListLayoutRes(@LayoutRes int layoutRes, RecyclerView.LayoutManager layoutManager) {
            params.listLayoutRes = layoutRes;
            params.layoutManager = layoutManager;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public Builder setScreenWidthAspect(Activity activity, float widthAspect) {
            params.mWidth = (int) (getWindowWidth(activity) * widthAspect);
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
            params.mHeight = (int) (getWindowHeight(activity) * heightAspect);
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

        //列表数据,需要传入数据和Adapter,和item点击数据
        public <A extends TBaseAdapter> Builder setAdapter(A adapter) {
            params.adapter = adapter;
            return this;
        }

        public Builder setOnAdapterItemClickListener(TBaseAdapter.OnAdapterItemClickListener listener) {
            params.adapterItemClickListener = listener;
            return this;
        }


        public TDialog create() {
            TDialog dialog = new TDialog();
            //将数据从Buidler的DjParams中传递到DjDialog中
            params.apply(dialog.tController);
            return dialog;
        }
    }
}
