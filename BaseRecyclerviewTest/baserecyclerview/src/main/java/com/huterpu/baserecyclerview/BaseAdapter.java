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

   public  BaseAdapter(List<T> list) {
        this.list=list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getPrototypeCell(parent,viewType);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
          CellForItem(holder.container,position);
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


  public   abstract void CellForItem(ViewGroup containerView,int position);
  public   abstract ViewGroup getPrototypeCell(ViewGroup parent,int viewType);
}



