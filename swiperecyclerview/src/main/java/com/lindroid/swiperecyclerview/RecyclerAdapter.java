package com.lindroid.swiperecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by Lindroid on 2017/1/21.
 */

public class RecyclerAdapter extends SwipeMenuAdapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<String> datas;

    public RecyclerAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = View.inflate(context, R.layout.item_recycle,null);
//        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        final ViewHolder holder = new ViewHolder(view);
//        holder.view.setLayoutParams(lp);
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "你点击了"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        return holder;
//    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recycle, null);
        return view;
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        final ViewHolder holder = new ViewHolder(realContentView);
//        holder.view.setLayoutParams(lp);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "你点击了第" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
