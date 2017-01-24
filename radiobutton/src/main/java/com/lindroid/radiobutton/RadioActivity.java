package com.lindroid.radiobutton;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RadioActivity extends AppCompatActivity implements RecyclerViewAdapter.RecycleViewClickListener{

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.activity_radio)
    RelativeLayout activityRadio;
    private List<ListItem> list;
    private RecyclerViewAdapter adapter;
    String deviceStatus="后开启";
    String POSITION = "position";
    String ISCHECK = "isCheck";

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        ButterKnife.bind(this);
        initView();
        initListData();
        sp = getSharedPreferences("RadioButton", Context.MODE_PRIVATE);

    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,//方向
                false));//数据是否反向

    }

    private void initListData(){
        list = new ArrayList<>();
        list.add(new ListItem("5分钟","",0));
        list.add(new ListItem("10分钟","",0));
        list.add(new ListItem("15分钟","",0));
        list.add(new ListItem("20分钟","",0));
        list.add(new ListItem("25分钟","",0));
        list.add(new ListItem("30分钟","",0));
        list.add(new ListItem("自定义","",0));
        adapter = new RecyclerViewAdapter(this);
        adapter.setOnRecycleOnClickListener(this);
        if (adapter != null){
            adapter.setList(list);
            recyclerView.setAdapter(adapter);
            initItem();
        }

    }

    private void initItem(){
        sp = getSharedPreferences("RadioButton", Context.MODE_PRIVATE);
        int pos = sp.getInt(POSITION,1);
        int isCheck = sp.getInt(ISCHECK,1);
        list.get(pos).setIsCheck(isCheck);
        if (isCheck == 0){
            list.get(pos).setStateName("");
        }else {
            list.get(pos).setStateName(deviceStatus);
        }
    }

    @Override
    public void onItemClick(int pos) {
        for (int i = 0; i < list.size(); i++) {
            if (i==pos && pos != 6){
                if (list.get(pos).getIsCheck() == 1){
                    list.get(pos).setIsCheck(0);
                    list.get(pos).setStateName("");
                }else {
                    list.get(pos).setIsCheck(1);
                    list.get(pos).setStateName(deviceStatus);
                }
            }else if (i==pos && pos==6){
                if (list.get(pos).getIsCheck()==1){
                    list.get(pos).setIsCheck(0);
                    list.get(pos).setTime("自定义");
                    list.get(pos).setStateName("");
                }else {
                    list.get(pos).setIsCheck(1);
                    list.get(pos).setStateName(deviceStatus);
                }
            }else {
                list.get(i).setIsCheck(0);
                list.get(i).setStateName("");
            }
        }
        //保存用户设置的状态
        sp.edit().putInt(POSITION,pos).commit();
        sp.edit().putInt(ISCHECK,list.get(pos).getIsCheck()).commit();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemLongClick(int pos) {
        //实现RecycleView的Item 长按击触发的方法
        list.remove(pos);
        adapter.notifyItemRangeRemoved(0,pos);
    }
}
