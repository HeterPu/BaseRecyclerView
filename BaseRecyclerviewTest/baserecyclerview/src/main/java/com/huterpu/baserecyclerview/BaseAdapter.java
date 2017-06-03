package com.huterpu.baserecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by limxing on 16/7/23.
 *
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
  public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.MasonryView> {

   public final List<T> list;
   public  BaseRecyclerView mContainerView;

   public  BaseAdapter(List<T> list) {
        this.list=list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContainerView = (BaseRecyclerView)parent;
        View view = getPrototypeCell(mContainerView,viewType);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
          CellForItem(mContainerView,holder.container,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MasonryView extends RecyclerView.ViewHolder {
         ViewGroup container;
         MasonryView(View itemView) {
            super(itemView);
             container = (ViewGroup) itemView;
        }
    }


    /**
     * @param parent  Recycle视图自身
     * @param container 容器视图
     * @param position  当前的位置
     */
    public   abstract void CellForItem(BaseRecyclerView parent,ViewGroup container, int position);

    /**
     * @param parent  Recycle视图自身
     * @param viewType 视图类型
     * @return
     */
    public   abstract ViewGroup getPrototypeCell(BaseRecyclerView parent,int viewType);
}



