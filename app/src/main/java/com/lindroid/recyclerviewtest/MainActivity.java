package com.lindroid.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,//方向
                false));//数据是否反向
        initData();
        recyclerAdapter = new RecyclerAdapter(this,datas);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(recyclerAdapter);

        TextView textView1 = new TextView(this);
        textView1.setText("头布局一");
        textView1.setTextSize(20);
        TextView textView2 = new TextView(this);
        textView2.setText("头布局二");
        textView2.setTextSize(20);

        headerAndFooterWrapper.addHeaderView(textView1);
        headerAndFooterWrapper.addHeaderView(textView2);

        recyclerView.setAdapter(headerAndFooterWrapper);
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i <= 70; i++) {
            datas.add("这是数据"+i);
        }
    }
}
