package com.fengniao.myandroidapplication.banner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengniao.myandroidapplication.R;
import com.fengniao.myandroidapplication.friendcircle.FriendCircleActivity;

import java.util.List;


public class FriendCircleListAdapter extends RecyclerView.Adapter<FriendCircleListAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String> list;

    public FriendCircleListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = LayoutInflater.from(context).inflate(R.layout.item_list_friend_circle_header, parent, false);
                return new MyViewHolder(view);
            case 1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_list_friend_circle, parent, false);
                return new MyViewHolder(view1);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == 0) {
            holder.getView(R.id.img_bg).setOnClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_bg:
                ((FriendCircleActivity) context).showDialog();
                break;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(View itemView) {
            super(itemView);
        }

        public View getView(int resId) {
            return itemView.findViewById(resId);
        }
    }
}
