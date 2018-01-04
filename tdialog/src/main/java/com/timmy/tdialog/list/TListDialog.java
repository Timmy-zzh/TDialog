package com.timmy.tdialog.list;


import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.timmy.tdialog.R;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.TController;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.util.DensityUtils;

/**
 * 列表Dialog
 * 主要功能有:设置adapter展示数据列表
 * 设置Adapter的item点击事件
 */
public class TListDialog extends TDialog {

//    protected TListController tController;

    public TListDialog() {
        super();
        tController = new TListController<>();
        tController.setLayoutRes(R.layout.dialog_recycler);
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        TListController tListController = (TListController) tController;
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        if (tListController.getAdapter() != null && recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(tListController.getAdapter());
            tListController.getAdapter().notifyDataSetChanged();
            if (tListController.getAdapterItemClickListener() != null) {
                tListController.getAdapter().setOnAdapterItemClickListener(tListController.getAdapterItemClickListener());
            }
        }
    }

    public static class Builder<A extends TBaseAdapter> extends TDialog.Builder {

        TListController.TListParams params;

        public Builder(FragmentManager fragmentManager) {
            super(fragmentManager);
            params = new TListController.TListParams();
            params.fragmentManager = fragmentManager;
            params.layoutRes = R.layout.dialog_recycler;
        }

        public Builder setAdapter(A adapter) {
            params.adapter = adapter;
            return this;
        }

        public Builder setOnAdapterItemClickListener(TBaseAdapter.OnAdapterItemClickListener listener) {
            params.adapterItemClickListener = listener;
            return this;
        }

        //各种setXXX()方法设置数据
        public TDialog.Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.layoutRes = layoutRes;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public TDialog.Builder setScreenWidthAspect(Activity activity, float widthAspect) {
            params.mWidth = (int) (DensityUtils.getWindowWidth(activity) * widthAspect);
            return this;
        }

        public TDialog.Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        /**
         * 设置屏幕高度比例 0 -1
         */
        public TDialog.Builder setScreenHeightAspect(Activity activity, float heightAspect) {
            params.mHeight = (int) (DensityUtils.getWindowHeight(activity) * heightAspect);
            return this;
        }

        public TDialog.Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        public TDialog.Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public TDialog.Builder setCancelOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        public TDialog.Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public TDialog.Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        public TDialog.Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        public TListDialog create() {
            TListDialog dialog = new TListDialog();
            //将数据从Buidler的DjParams中传递到DjDialog中
            params.apply(dialog.tController);
            return dialog;
        }

    }

}
