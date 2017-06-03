package com.huterpu.baserecyclerview;


import android.view.ViewGroup;

import java.util.List;

/**
 * Created by peterhu on 2017/6/3.
 */

public class MegaBaseAdapter<T> extends BaseAdapter<T> {

    private MegaBaseAdapterDelegate mDelegate;
    private int sectionIndex;

    public MegaBaseAdapter(List<T>  list){
        super(list);
    }


    public int getSectionIndex() {
        return sectionIndex;
    }


    public void setSectionIndex(int sectionIndex) {
        this.sectionIndex = sectionIndex;
    }


    public void setmDelegate(MegaBaseAdapterDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    @Override
    public ViewGroup getPrototypeCell(BaseRecyclerView parent, int viewType) {
        return  (mDelegate != null) ? mDelegate.getprototype(parent,viewType) : null;
    }

    @Override
    public void CellForItem(BaseRecyclerView parent, ViewGroup container, int position) {
      if (mDelegate != null) mDelegate.cellForItem( parent,container,position);
    }


    interface MegaBaseAdapterDelegate{
        ViewGroup  getprototype(ViewGroup parent, int viewType);
        void cellForItem(BaseRecyclerView parent, ViewGroup container, int position);

    }
}
