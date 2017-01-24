package com.lindroid.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.lindroid.recyclerviewtest.adapter.HeaderAndFooterWrapper;
import com.lindroid.recyclerviewtest.adapter.RecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,
//                2,
//                LinearLayout.VERTICAL,//方向
//                false));//数据是否反向
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                3,
                StaggeredGridLayoutManager.VERTICAL //方向
                ));//数据是否反向
        initData();
        recyclerAdapter = new RecyclerAdapter(this,datas);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(recyclerAdapter);

        //添加头布局
        TextView textView1 = new TextView(this);
        textView1.setText("头布局一");
        textView1.setTextSize(24);
        TextView textView2 = new TextView(this);
        textView2.setText("头布局二");
        textView2.setTextSize(24);

        //添加脚布局
        TextView textView3 = new TextView(this);
        textView3.setText("脚布局一");
        textView3.setTextSize(24);
        TextView textView4 = new TextView(this);
        textView4.setText("脚布局二");
        textView4.setTextSize(24);

        headerAndFooterWrapper.addHeaderView(textView1);
        headerAndFooterWrapper.addHeaderView(textView2);
        headerAndFooterWrapper.addFootView(textView3);
        headerAndFooterWrapper.addFootView(textView4);

        recyclerView.setAdapter(headerAndFooterWrapper);
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 1; i <= 70; i++) {
            datas.add("这是数据"+i);
        }
    }
}
