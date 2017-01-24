package com.lindroid.recyclerviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lindroid.recyclerviewtest.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends Activity {
    private List<TextBean> datas;
    private RecyclerView recyclerView;
    private CommonAdapter<TextBean> adapter;
    private TextBean textBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,//方向
                false));//数据是否反向
        initData();

        adapter = new CommonAdapter<TextBean>(this,R.layout.item_recycle, datas) {
            @Override
            protected void convert(ViewHolder holder, TextBean textBean, int position) {
                Log.e("Tag","convert调用");
                textBean = datas.get(position);
                Log.e("Tag","data2="+textBean.getTextTitle());
                holder.setText(R.id.text,textBean.getTextTitle());
            }
        };


        recyclerView.setAdapter(adapter);
    }

    private void initData() {

        datas = new ArrayList<>();
        for (int i = 1; i <= 70; i++) {
            textBean = new TextBean();
            textBean.setTextTitle("这是数据"+i);
            datas.add(textBean);
        }

        for (int i = 0; i <70  ; i++) {
            textBean = datas.get(i);
            Log.e("Tag","data="+textBean.getTextTitle());
        }
    }
}
