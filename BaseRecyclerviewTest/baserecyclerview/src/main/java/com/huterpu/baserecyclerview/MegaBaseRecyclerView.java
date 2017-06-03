package com.huterpu.baserecyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

/**
 * Created by peterhu on 2017/6/3.
 */

public class MegaBaseRecyclerView extends ScrollView {

    private LinearLayout mContainer;
    private MegaRecyclerViewDataSourse mdataSource;
    private MegaItemClickListener mItemClick;


    public  MegaBaseRecyclerView(Context context){
      this(context,null);
    }

    public MegaBaseRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMegaRecycleView();
    }


    public void setMdataSource(MegaRecyclerViewDataSourse mdataSource) {
        this.mdataSource = mdataSource;
    }


    private void initMegaRecycleView(){
        LinearLayout container = new LinearLayout(getContext());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(param);
        container.setOrientation(LinearLayout.VERTICAL);
        addView(container);
        this.mContainer = container;
    }

    private void initData(){
        if (mdataSource != null){
            int sectionCount = mdataSource.numberOfSectionIn(this);
            for(int i = 0;i < sectionCount;i++){
                BaseRecyclerView recyV = new BaseRecyclerView(getContext());
                ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                recyV.setLayoutParams(param);
                Integer spacing = mdataSource.innerSpacingIn(i);
                Rect contentRect = mdataSource.contentInsetIn(i);
                Boolean inSetEqualSpacing = mdataSource.inSetEqualToInnerSpacingIn(i);
                if (mItemClick != null) {
                    final int finalI1 = i;
                    recyV.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            mItemClick.onClick(finalI1,position);
                        }

                        @Override
                        public void onLongClick(int position) {
                            mItemClick.onLongClick(finalI1,position);
                        }
                    });
                }
                if (spacing != null){
                    if (contentRect==null) {
                        boolean includeEdge = false;
                        if ((inSetEqualSpacing != null) && (inSetEqualSpacing)) {
                            includeEdge = true;
                        }
                        recyV.addItemDecoration(new GridSpacingItemDecoration(spacing, includeEdge));
                    }else
                    {
                        recyV.addItemDecoration(new GridSpacingItemDecoration(spacing,contentRect));
                    }
                }
                List<Object> objectList = mdataSource.getDataListWith(i);
                MegaBaseAdapter<Object> adapter = new MegaBaseAdapter<>(objectList);
                adapter.setSectionIndex(i);
                final int finalI = i;
                adapter.setmDelegate(new MegaBaseAdapter.MegaBaseAdapterDelegate() {
                    @Override
                    public ViewGroup getprototype(ViewGroup parent, int viewType) {
                        return mdataSource.getPrototypeCellWith(parent,finalI);
                    }

                    @Override
                    public void cellForItem(BaseRecyclerView parent, ViewGroup container, int position) {
                        mdataSource.setCellWithIndexPath(container,finalI,position);
                    }
                });
                View headerV = mdataSource.getHeaderViewWith(recyV,i);
                View footerV = mdataSource.getFooterViewWith(recyV,i);

                if (headerV != null)recyV.setHeaderView(headerV);
                if (footerV != null)recyV.setFooterView(footerV);
                recyV.setColumn(mdataSource.numberOfColumnIn(i));
                recyV.setAdapter(adapter);
                Integer tagNum = i + 10;
                recyV.setTag(tagNum);
                mContainer.addView(recyV);
            }
        }
    }





    public void setOnItemClick(MegaItemClickListener mItemClick) {
        this.mItemClick = mItemClick;
    }

    public void setRefresh(boolean isRefresh){
        BaseRecyclerView recyV = (BaseRecyclerView) mContainer.getChildAt(0);
        recyV.setRefresh(isRefresh);
    }

    public void setLoadMore(boolean isLoadMore){
        int count = mContainer.getChildCount();
        BaseRecyclerView recyV = (BaseRecyclerView) mContainer.getChildAt(count - 1);
        recyV.setRefresh(isLoadMore);
    }

    /**
     * 当数据准备完成时或初始化完毕使用
     */
    public void reloadAllHard(){
        mContainer.removeAllViews();
        initData();
    }

    public void reloadAll(){
        int count = mContainer. getChildCount();
        for (int i =0;i < count;i++){
            reloadAt(i);
        }
    }

    public void reloadAt(int section){
        BaseRecyclerView recyV = (BaseRecyclerView) mContainer.getChildAt(section);
        LFRecyclerViewAdapter adapter =  (LFRecyclerViewAdapter)recyV.getAdapter();
        adapter.notifyDataSetChanged();
    }

    public  interface MegaRecyclerViewDataSourse{
        Integer  numberOfSectionIn(ViewGroup parent);
        Integer  numberOfColumnIn(int section);
        Integer innerSpacingIn(int section);
        Rect contentInsetIn(int section);
        Boolean inSetEqualToInnerSpacingIn(int section);
        ViewGroup getPrototypeCellWith(ViewGroup parent, int section);
        List getDataListWith(int section);
        View getHeaderViewWith(ViewGroup parent, int section);
        View getFooterViewWith(ViewGroup parent, int section);
        void setCellWithIndexPath(ViewGroup parent, int section,int row);
    }

    public interface MegaItemClickListener{
        void onClick(int section,int row);
        void onLongClick(int section,int row);
    }
}
