package com.lindroid.radiobutton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lindroid on 2017/2/14.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<ListItem> mList = new ArrayList<>();
    private static RecycleViewClickListener mListener;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext) {
//        this.mList = mList;
        this.mContext = mContext;
    }

    public void setList(List<ListItem> list) {
        mList.clear();
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.setDelayAdapter(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvDelayTime.setText(mList.get(position).getTime());
        holder.tvDevState.setText(mList.get(position).getStateName());
        //根据check值动态创建对应的item
        if (mList.get(position).getIsCheck() == 1) {
            holder.ivCheck.setVisibility(View.VISIBLE);
            holder.ivCheck.setBackgroundResource(android.R.drawable.star_off);
        } else {
            holder.ivCheck.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_delay_time)
        TextView tvDelayTime;
        @Bind(R.id.iv_check)
        ImageView ivCheck;
        @Bind(R.id.tv_dev_state)
        TextView tvDevState;
        private WeakReference<RecyclerViewAdapter> ref;
        private RecyclerViewAdapter recyclerViewAdapter;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //执行Item Click事件
                    mListener.onItemClick(getLayoutPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClick(getLayoutPosition());
                    return true;//这里返回true 就不会在LongClick之后再重复执行onClick
                }
            });
        }

        public void setDelayAdapter(RecyclerViewAdapter adapter) {
            if (adapter != null) {
                ref = new WeakReference<RecyclerViewAdapter>(adapter);
            }
            recyclerViewAdapter = ref.get();

        }
    }

    //设置Item事件监听
    public void setOnRecycleOnClickListener(RecycleViewClickListener itemOnClickListener) {
        mListener = itemOnClickListener;
    }

    //定义Item事件接口
    public interface RecycleViewClickListener {
        void onItemClick(int pos);
        void onItemLongClick(int pos);
    }
}
