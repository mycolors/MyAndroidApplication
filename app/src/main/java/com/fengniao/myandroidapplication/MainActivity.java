package com.fengniao.myandroidapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fengniao.mlibs.adapter.BaseRecyclerViewAdapter;
import com.fengniao.myandroidapplication.list.BaseRecyclerViewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseRecyclerViewActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void loadData() {
        enableSwipeRefresh(false);
        mList.add(null);
        mList.add(null);
        mList.add(null);
        setListAdapter();
    }


    @Override
    public void onItemClick(BaseRecyclerViewAdapter.FNViewHolder holder, Object o, int positon, long id) {

    }

    @Override
    public View getView(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_lsit, parent, false);
            return view;
        }
        return null;
    }

    @Override
    public void bindDataToView(BaseRecyclerViewAdapter.FNViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

}
