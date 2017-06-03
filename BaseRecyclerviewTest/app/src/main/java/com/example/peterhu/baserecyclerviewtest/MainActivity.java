package com.example.peterhu.baserecyclerviewtest;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.huterpu.baserecyclerview.BaseRecyclerView;
import com.huterpu.baserecyclerview.FlexibleDividerDecoration.HorizontalDividerItemDecoration;
import com.huterpu.baserecyclerview.GridSpacingItemDecoration;
import com.huterpu.baserecyclerview.MegaBaseRecyclerView;
import com.huterpu.baserecyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

        private MegaBaseRecyclerView recycleview;
        private ArrayList<String> list;
        private Main_Adapter adapter;
        private boolean b;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.recycleview = (MegaBaseRecyclerView) findViewById(R.id.myRecycleView);
            this.list = new ArrayList<>();

            recycleview.setOnItemClick(new MegaBaseRecyclerView.MegaItemClickListener() {
                @Override
                public void onClick(int section, int row) {
                    list.add(list.size(), "leefeng.me" + "==onLoadMore");
                    list.add(list.size(), "leefeng.me" + "==onLoadMore");
                    recycleview.reloadAll();
                    Toast.makeText(MainActivity.this,"Click  section" + section + "row" + row,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLongClick(int section, int row) {
                    Toast.makeText(MainActivity.this,"LongClick section" + section + "row" + row,Toast.LENGTH_SHORT).show();
                }
            });


            recycleview.setMdataSource(new MegaBaseRecyclerView.MegaRecyclerViewDataSourse() {
                @Override
                public Integer numberOfSectionIn(ViewGroup parent) {
                    return 5;
                }

                @Override
                public Integer numberOfColumnIn(int section) {

                    return section + 1;
                }

                @Override
                public ViewGroup getPrototypeCellWith(ViewGroup parent, int section) {
                    int item = R.layout.item;
                    if (section == 1){
                        item = R.layout.item2;
                    }
                    return (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(item,parent,false);
                }

                @Override
                public List getDataListWith(int section) {
                   if (section != 1) {
                       ArrayList<Object> list = new ArrayList<>();
                       for (int i = 0; i <= section + 4; i++) {
                           list.add("The section is " + section);
                       }
                       return  list;
                   }
                    return  list;
                }

                @Override
                public View getHeaderViewWith(ViewGroup parent, int section) {
                    TextView tv = new TextView(MainActivity.this);
                   tv.setText("这是头部sdfsfasdfsadfsadfsadfasdfasdfasdfsdafsdafsadfasdf");
                   tv.setTextColor(Color.WHITE);
                   tv.setGravity(Gravity.CENTER);
                   tv.setBackgroundColor(Color.RED);
                    if (section % 2==0)return tv;
                    return null;

                }

                @Override
                public View getFooterViewWith(ViewGroup parent, int section) {
                    TextView fv = new TextView(MainActivity.this);
                    fv.setText("这是底dffvevewvvevewrwerferfwerfwerfwerfwerfwerfwerfwrewrfwfrwerfew部");
                    fv.setTextColor(Color.WHITE);
                    fv.setGravity(Gravity.CENTER);
                    fv.setBackgroundColor(Color.BLUE);
                    if (section % 2==1)return fv;
                    return null;
                }

                @Override
                public void setCellWithIndexPath(ViewGroup parent, int section, int row) {

                }

                @Override
                public Integer innerSpacingIn(int section) {
                    return 20;
                }

                @Override
                public Boolean inSetEqualToInnerSpacingIn(int section) {
                    return true;
                }

                @Override
                public Rect contentInsetIn(int section) {
                    return new Rect(40,10,12,20);
                }
            });


            recycleview.reloadAllHard();




            list.add(list.size(), "leefeng.me" + "==onLoadMore");
            list.add(list.size(), "leefeng.me" + "==onLoadMore");
//            list.add(list.size(), "leefeng.me" + "==onLoadMore");
//            list.add(list.size(), "leefeng.me" + "==onLoadMore");            list.add(list.size(), "leefeng.me" + "==onLoadMore");
//            list.add(list.size(), "leefeng.me" + "==onLoadMore");
//////
////            recycleview.setLoadMore(true);
////            recycleview.setRefresh(true);
//            recycleview.setBaseRecyclerViewListener(this);
//            recycleview.setAutoLoadMore(true);
////            recycleview.addItemDecoration(new GridSpacingItemDecoration(30, new Rect(0,0,0,70)));
////            recycleview.addItemDecoration(new );
////        recycleview.addItemDecoration(new HorizontalDividerItemDecoration().Builder);
//
//        recycleview.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).size(40).color(Color.GREEN).build());
//        recycleview.addItemDecoration(new GridSpacingItemDecoration(30,new Rect(60,10,50,20)));
//
////        GridLayoutManager manager = new GridLayoutManager(this);
////        recycleview.setLayoutManager(manager);
//
////        recycleview.setLayoutManager();
//            recycleview.setItemAnimator(new DefaultItemAnimator());
//            recycleview.setOnItemClickListener(this);
//            adapter = new Main_Adapter(list);
//            recycleview.setAdapter(adapter);
//
////        ViewGroup group = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item,null);
//
//            TextView tv = new TextView(MainActivity.this);
//            tv.setText("这是头部sdfsfasdfsadfsadfsadfasdfasdfasdfsdafsdafsadfasdf");
//            tv.setTextColor(Color.WHITE);
//            tv.setGravity(Gravity.CENTER);
//            tv.setBackgroundColor(Color.RED);
//
//            TextView fv = new TextView(MainActivity.this);
//            fv.setText("这是底dffvevewvvevewrwerferfwerfwerfwerfwerfwerfwerfwrewrfwfrwerfew部");
//            fv.setTextColor(Color.WHITE);
//            fv.setGravity(Gravity.CENTER);
//            fv.setBackgroundColor(Color.BLUE);
////            recycleview.setHeaderView(tv);
////            recycleview.setFooterView(fv);
////            recycleview.setOrientationAndReverse(RecyclerView.HORIZONTAL,true);
//            recycleview.setColumn(8);
//            recycleview.setOrientationAndReverse(RecyclerView.HORIZONTAL,false);
        }

//
//        @Override
//        public void onClick(int position) {
//            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
//            list.add(list.size(), "leefeng.me" + "==onLoadMore");
//            adapter.notifyItemRangeInserted(list.size()-1,1);
//        }
//
//        @Override
//        public void onLongClick(int po) {
//            Toast.makeText(this, "Long:" + po, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onRefresh() {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    list.add(0, "leefeng.me" + "==onRefresh");
//                    recycleview.stopRefresh(true);
//                    adapter.notifyItemInserted(0);
//                    adapter.notifyItemRangeChanged(0,list.size());
//
//                }
//            }, 2000);
//        }
//
//        @Override
//        public void onLoadMore() {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    list.add(list.size(), "leefeng.me" + "==onLoadMore");
//                    list.add(list.size(), "leefeng.me" + "==onLoadMore");
//
//                    if (list.size() < 20) {
//                        recycleview.stopLoadMore();
//                    }
//                    else {
//                        recycleview.stopLoadAndNoMoreData();
//                    }
////                list.add(list.size(), "leefeng.me" + "==onLoadMore");
//                    adapter.notifyItemRangeInserted(list.size()-2,2);
//
//                }
//            }, 2000);
//        }


    }
