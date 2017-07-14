package com.lindroid.swiperecyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllMenuActivity extends AppCompatActivity {

    @Bind(R.id.rv_all_menu)
    SwipeMenuRecyclerView rvAllMenu;

    private Activity context;
    private List<String> titles = new ArrayList<>();
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu);
        context = this;
        ButterKnife.bind(this);
        initData();

        rvAllMenu.setLayoutManager(new LinearLayoutManager(context));
        //创建菜单构造器
        rvAllMenu.setSwipeMenuCreator(swipeMenuCreator);
//        rvAllMenu.smoothOpenRightMenu(0);
        adapter = new RecyclerAdapter(context, titles);
        rvAllMenu.setAdapter(adapter);
        rvAllMenu.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                    Toast.makeText(context, "你点击了" + adapterPosition, Toast.LENGTH_SHORT).show();
                    titles.remove(adapterPosition);
                    adapter.notifyItemRemoved(adapterPosition);
                }
            }
        });
    }

    private void initData() {
        for (int i = 1; i <= 30; i++) {
            titles.add("这是标题" + i);
        }

    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {


//        int size = dp2px(60);

        //        int size = 60;
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
//            int size = getResources().getDimensionPixelSize(R.dimen.item_height);
            int size = dp2px(60);
            SwipeMenuItem addItem = new SwipeMenuItem(context)
                    .setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_light))
                    .setText("增加")
                    .setTextColor(Color.WHITE)
                    .setHeight(size)
                    .setWidth(size);
            swipeLeftMenu.addMenuItem(addItem);


            SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                    .setText("删除")
                    .setHeight(size)
                    .setWidth(size)
                    .setImage(android.R.drawable.ic_delete);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };

    private int px2dp(float pxValue) {
        int dpValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue, getResources().getDisplayMetrics());
        return dpValue;
    }

    private int dp2px(float dpValue) {
        int pxValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
        return pxValue;
    }
}
