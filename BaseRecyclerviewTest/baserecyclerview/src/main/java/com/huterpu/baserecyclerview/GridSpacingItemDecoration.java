package com.huterpu.baserecyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by peterhu on 2017/5/27.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {


    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private Rect mPadding;

    public GridSpacingItemDecoration(int spacing, boolean includeEdge) {
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    public GridSpacingItemDecoration(int spacing,Rect contentInset){
        this.spacing = spacing;
        this.includeEdge = true;
        this.mPadding = contentInset;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        spanCount = manager.getSpanCount();
        int position = parent.getChildAdapterPosition(view);
        LFRecyclerViewAdapter adapter = (LFRecyclerViewAdapter) parent.getAdapter();

         boolean reverseLayout =  manager.getReverseLayout();
        if (manager.getOrientation() != OrientationHelper.HORIZONTAL) {

            if (spanCount == 1) {
                int headerCount = adapter.getheaderViewCount();
                int footerCount = adapter.getFooterViewCount();
                if ((mPadding != null)&&((position >= headerCount) && (position <= adapter.getItemCount() - footerCount)))
                {
                    outRect.left = mPadding.left;
                    outRect.right = mPadding.right;
                }
                if ((position >= headerCount + 1) && (position < adapter.getItemCount() - footerCount)) {
                    if(!reverseLayout) {
                        outRect.top = spacing;
                    }else
                    {
                        outRect.bottom = spacing;
                    }
                }
            } else {
                int itemtype = adapter.getItemViewType(position);
                if (itemtype != LFRecyclerViewAdapter.ITEM_TYPE_CONTENT) {
                    return;
                }
                int correctPosition = (position - adapter.getheaderViewCount());
                // item position
                int column = correctPosition % spanCount; // item column

                if (includeEdge) {

                    /**
                     * 当人工设置padding时按照实际padding来，没有设置则包括边框的情况按照spacing来
                     * 计算方法如下
                     a0L = mPadding.left;
                     a0r = totalWidth / spanCount - mPadding.left

                     axl = padding - a1r + f(x-1)
                     Axr = a1r  -f(x)

                     anl = padding - a1r + f（n -1）
                     anr = a1r  -  f(n)

                     Padding + f(n -1) - f(n)  = totalWidth / spanCount

                     f(n) - f(n-1) =  totalWidth / spanCount - padding

                     f(n) = f(1) + (n -1)(totalWidth / spanCount - padding)

                     f(1) = padding - (totalWidth / spanCount - mPadding.left)

                     f(n)_left =  padding - (totalWidth / spanCount - mPadding.left) +  (n -1)(totalWidth / spanCount - padding)
                     f(n)_right =  (totalWidth / spanCount - mPadding.left)  +  (n)(totalWidth / spanCount - padding)
                     *
                     */
                    if (mPadding != null) {
                        int totalWidth = mPadding.left + spacing * (spanCount - 1) + mPadding.right;
                        int cellOriginSpacing = totalWidth / spanCount;
                        if (column == 0) {
                            outRect.left = mPadding.left;
                            outRect.right = cellOriginSpacing - mPadding.left;
                        } else if (column == spanCount - 1) {
                            outRect.right = mPadding.right;
                            outRect.left = cellOriginSpacing - mPadding.right;
                        } else {

                            outRect.left = spacing - (cellOriginSpacing - mPadding.left) - (column - 1) * (cellOriginSpacing - spacing);
                            outRect.right = (cellOriginSpacing - mPadding.left) + (column) * (cellOriginSpacing - spacing);
                        }

                        if (correctPosition < spanCount) { // top edge

                            if(!reverseLayout) {
                                outRect.top = mPadding.top;
                            }
                            else
                            {
                                outRect.bottom = mPadding.bottom;
                            }

                        }
                        else
                        {
                            if(!reverseLayout) {
                                if (mPadding.bottom == 0) {
                                    outRect.top = spacing;
                                }
                            }
                            else
                            {
                                if (mPadding.top == 0) {
                                    outRect.bottom = spacing;
                                }
                            }
                        }

                            if(!reverseLayout) {
                                if (mPadding.bottom != 0) {
                                    outRect.bottom = spacing;
                                }
                            }else
                            {
                                if (mPadding.top != 0) {
                                    outRect.top = spacing;
                                }
                            }

                    }
                    else
                    {
                        outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                        outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                        if (correctPosition < spanCount) { // top edge

                            if(!reverseLayout) {
                                outRect.top = spacing;
                            }
                            else
                            {
                                outRect.bottom = spacing;
                            }

                        }

                        if(!reverseLayout) {
                            outRect.bottom = spacing;
                        }else
                        {
                            outRect.top = spacing;
                        }
                    }
                    // item bottom
                } else {
                    outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (correctPosition >= spanCount) {
                        if(!reverseLayout) {
                            outRect.top = spacing;
                        }else
                        {
                            outRect.bottom = spacing;
                        }
                       // item top
                    }


                }

            }


        }
        else
        {
            if (spanCount == 1) {
                int headerCount = adapter.getheaderViewCount();
                int footerCount = adapter.getFooterViewCount();

                if (mPadding != null&&((position >= headerCount) && (position <= adapter.getItemCount() - footerCount)))
                {
                    outRect.top = mPadding.top;
                    outRect.bottom = mPadding.bottom;
                }

                if ((position >= headerCount + 1) && (position < adapter.getItemCount() - footerCount)) {
                    if(!reverseLayout) {
                        outRect.left = spacing;
                    }else
                    {
                        outRect.right = spacing;
                    }
                }
            } else {

                int itemtype = adapter.getItemViewType(position);
                if (itemtype != LFRecyclerViewAdapter.ITEM_TYPE_CONTENT) {
                    return;
                }
                int correctPosition = (position - adapter.getheaderViewCount());
                // item position
                int column = correctPosition % spanCount; // item column

                if (includeEdge) {

                    if (includeEdge) {

                        /**
                         * 当人工设置padding时按照实际padding来，没有设置则包括边框的情况按照spacing来
                         * 计算方法如下
                         a0L = mPadding.left;
                         a0r = totalWidth / spanCount - mPadding.left

                         axl = padding - a1r + f(x-1)
                         Axr = a1r  -f(x)

                         anl = padding - a1r + f（n -1）
                         anr = a1r  -  f(n)

                         Padding + f(n -1) - f(n)  = totalWidth / spanCount

                         f(n) - f(n-1) =  totalWidth / spanCount - padding

                         f(n) = f(1) + (n -1)(totalWidth / spanCount - padding)

                         f(1) = padding - (totalWidth / spanCount - mPadding.left)

                         f(n)_left =  padding - (totalWidth / spanCount - mPadding.left) +  (n -1)(totalWidth / spanCount - padding)
                         f(n)_right =  (totalWidth / spanCount - mPadding.left)  +  (n)(totalWidth / spanCount - padding)
                         *
                         */
                        if (mPadding != null) {
                            int totalWidth = mPadding.top + spacing * (spanCount - 1) + mPadding.bottom;
                            int cellOriginSpacing = totalWidth / spanCount;
                            if (column == 0) {
                                outRect.top = mPadding.top;
                                outRect.bottom = totalWidth / spanCount - mPadding.top;
                            } else if (column == spanCount - 1) {
                                outRect.bottom = mPadding.bottom;
                                outRect.top = cellOriginSpacing - mPadding.bottom;
                            } else {

                                outRect.top = spacing - (cellOriginSpacing - mPadding.top) - (column - 1) * (cellOriginSpacing - spacing);
                                outRect.bottom = (cellOriginSpacing - mPadding.top) + (column) * (cellOriginSpacing - spacing);
                            }

                            if (correctPosition < spanCount) { // top edge

                                if (!reverseLayout) {
                                    outRect.left = mPadding.left;
                                } else {
                                    outRect.right = mPadding.right;
                                }

                            } else {
                                if (!reverseLayout) {
                                    if (mPadding.right == 0) {
                                        outRect.left = spacing;
                                    }
                                } else {
                                    if (mPadding.left == 0) {
                                        outRect.right = spacing;
                                    }
                                }
                            }

                            if (!reverseLayout) {
                                if (mPadding.right != 0) {
                                    outRect.right = spacing;
                                }
                            } else {
                                if (mPadding.left != 0) {
                                    outRect.left = spacing;
                                }
                            }

                        } else {

                            outRect.top = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                            outRect.bottom = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                            if (correctPosition < spanCount) { // top edge

                                if (!reverseLayout) {
                                    outRect.left = spacing;
                                } else {
                                    outRect.right = spacing;
                                }
                            }

                            if (!reverseLayout) {
                                outRect.right = spacing;
                            } else {
                                outRect.left = spacing;
                            }
                        }
                    }
                    // item bottom
                } else {
                    outRect.top = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.bottom = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (correctPosition >= spanCount) {
                        if(!reverseLayout) {
                            outRect.left = spacing;
                        }else
                        {
                            outRect.right = spacing;
                        }
                       // item top
                    }
                }

            }

        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

    }
}
