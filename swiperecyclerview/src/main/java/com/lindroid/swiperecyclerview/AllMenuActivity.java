package com.lindroid.swiperecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

    private Context context;
    private List<String> titles = new ArrayList<>();

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
        rvAllMenu.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                    Toast.makeText(context, "你点击了" + menuPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });
        rvAllMenu.setAdapter(new RecyclerAdapter(context, titles));
    }

    private void initData() {
        for (int i = 1; i <= 30; i++) {
            titles.add("这是标题" + i);
        }
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        int size = getResources().getDimensionPixelSize(R.dimen.item_height);

        //        int size = 60;
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
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

}
