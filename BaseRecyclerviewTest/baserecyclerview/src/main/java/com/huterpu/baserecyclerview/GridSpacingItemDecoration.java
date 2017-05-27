package com.huterpu.baserecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by peterhu on 2017/5/27.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {


    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spacing, boolean includeEdge) {
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
         GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        spanCount =  manager.getSpanCount();
        if (spanCount == 1)return;
        int position = parent.getChildAdapterPosition(view);
        LFRecyclerViewAdapter adapter = (LFRecyclerViewAdapter) parent.getAdapter();
        int itemtype = adapter.getItemViewType(position);
        if (itemtype != LFRecyclerViewAdapter.ITEM_TYPE_CONTENT){
            return;
        }

        int correctPosition = (position - adapter.getheaderViewCount());
        // item position
        int column = correctPosition % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (correctPosition < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (correctPosition >= spanCount) {
                outRect.top = spacing; // item top
            }


        }
    }
}
