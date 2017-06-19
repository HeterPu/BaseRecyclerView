package com.example.peterhu.baserecyclerviewtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.huterpu.baserecyclerview.BaseAdapter;
import com.huterpu.baserecyclerview.BaseRecyclerView;

import java.util.List;

/**
 * Created by peterhu on 2017/5/27.
 */



public class Main_Adapter extends BaseAdapter<String> {
  public Main_Adapter(List<String> list){
      super(list);
  }


    @Override
    public ViewGroup getPrototypeCell(BaseRecyclerView parent, int viewType) {
        return (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
    }


    @Override
    public void cellForItem(BaseRecyclerView parent, ViewGroup container, final int position) {
        View  content = (View) container.findViewById(R.id.xxxx);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("asda","" + position);
            }
        });
//        content.setText(list.get(position));
        content.setPadding(0, 20 * position, 0, 0);
    }


}
