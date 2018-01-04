package com.timmy.tdialog.list;

import com.timmy.tdialog.base.TController;

public class TListController<A extends TBaseAdapter> extends TController {

    public A adapter;
    public TBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;

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

    /////////////////////////////////////
    public static class TListParams<A extends TBaseAdapter> extends TParams {
        public A adapter;
        public TBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;

        public void apply(TListController tListController) {
            super.apply(tListController);
            if (adapter!=null){
                tListController.setAdapter(adapter);
            }
            if (adapterItemClickListener!=null){
                tListController.setAdapterItemClickListener(adapterItemClickListener);
            }

        }
    }
}
