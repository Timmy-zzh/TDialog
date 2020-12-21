package com.timmy.tdialog.list;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.timmy.tdialog.R;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.TBaseAdapter;
import com.timmy.tdialog.base.TController;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;

/**
 * 列表弹窗  与TDialog实现分开处理
 *
 * @author Timmy
 * @time 2018/1/11 14:38
 **/
public class TListDialog extends TDialog {


    @Override
    protected void bindView(View view) {
        super.bindView(view);
        if (tController.getAdapter() != null) {//有设置列表
            //列表
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
            if (recyclerView == null) {
                throw new IllegalArgumentException("自定义列表xml布局,请设置RecyclerView的控件id为recycler_view");
            }
            tController.getAdapter().setTDialog(this);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),tController.getOrientation(),false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(tController.getAdapter());
            tController.getAdapter().notifyDataSetChanged();
            if (tController.getAdapterItemClickListener() != null) {
                tController.getAdapter().setOnAdapterItemClickListener(tController.getAdapterItemClickListener());
            }
        }else{
            Log.d("TDialog","列表弹窗需要先调用setAdapter()方法!");
        }
    }

    /*********************************************************************
     * 使用Builder模式实现
     *
     */
    public static class Builder {

        TController.TParams params;

        public Builder(FragmentManager fragmentManager) {
            params = new TController.TParams();
            params.mFragmentManager = fragmentManager;
        }

        //各种setXXX()方法设置数据
        public TListDialog.Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.mLayoutRes = layoutRes;
            return this;
        }

        //设置自定义列表布局和方向
        public TListDialog.Builder setListLayoutRes(@LayoutRes int layoutRes,int orientation) {
            params.listLayoutRes = layoutRes;
            params.orientation = orientation;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public TListDialog.Builder setScreenWidthAspect(Activity activity, float widthAspect) {
            params.mWidth = (int) (getScreenWidth(activity) * widthAspect);
            return this;
        }

        public TListDialog.Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        /**
         * 设置屏幕高度比例 0 -1
         */
        public TListDialog.Builder setScreenHeightAspect(Activity activity, float heightAspect) {
            params.mHeight = (int) (getScreenHeight(activity) * heightAspect);
            return this;
        }

        public TListDialog.Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        public TListDialog.Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public TListDialog.Builder setCancelOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        public TListDialog.Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public TListDialog.Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        public TListDialog.Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        public TListDialog.Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        public TListDialog.Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        //列表数据,需要传入数据和Adapter,和item点击数据
        public <A extends TBaseAdapter> TListDialog.Builder setAdapter(A adapter) {
            params.adapter = adapter;
            return this;
        }

        public TListDialog.Builder setOnAdapterItemClickListener(TBaseAdapter.OnAdapterItemClickListener listener) {
            params.adapterItemClickListener = listener;
            return this;
        }

        public TListDialog.Builder setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
            params.mOnDismissListener = dismissListener;
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
