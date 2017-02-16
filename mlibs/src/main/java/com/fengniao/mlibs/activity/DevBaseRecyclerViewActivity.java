package com.fengniao.mlibs.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengniao.mlibs.R;
import com.fengniao.mlibs.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public abstract class DevBaseRecyclerViewActivity<T> extends DevBaseActivity implements
        BaseRecyclerViewAdapter.OnItemClickListener<T>, BaseRecyclerViewAdapter.ViewProvider
        , SwipeRefreshLayout.OnRefreshListener {
    protected final List<T> mList = new ArrayList<>();
    protected RecyclerView mRecyclerView;
    protected BaseRecyclerViewAdapter<T> mAdapter;
    protected View mEmptyView, mLoadingView;
    protected int mDefaultPageNo = 0;
    protected int mPageNo = mDefaultPageNo;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected TextView emptyData;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_dev_base_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setVisibility(View.GONE);
        emptyData = (TextView) findViewById(R.id.text_empty);
        RecyclerView.LayoutManager layoutManager = getLayoutManager(mRecyclerView);
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        }
        mLoadingView = findViewById(R.id.linear_loading);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = getAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        beforeLoadData();
        loadData();
    }


    public abstract void loadData();

    public void beforeLoadData() {

    }


    @Override
    public void onLoadMore() {

    }

    /**
     * 是否启用刷新
     *
     * @param enable
     */
    public void enableSwipeRefresh(boolean enable) {
        mSwipeRefreshLayout.setEnabled(enable);
    }

    /**
     * 获取列表长度
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void showList() {
        if (mAdapter.getItemCount() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
            mLoadingView.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
        } else {
            mEmptyView.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setListAdapter() {
        if (mAdapter == null) {
            mAdapter = getAdapter();
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mSwipeRefreshLayout.setRefreshing(false);
        showList();
        mAdapter.setLoadingMore(false);
    }


    public RecyclerView.LayoutManager getLayoutManager(RecyclerView view) {
        return null;
    }

    @Override
    public void onCreateViewHolder(BaseRecyclerViewAdapter.FNViewHolder holder, int viewType) {

    }

    @Override
    public void onRefresh() {
        mPageNo = mDefaultPageNo;
    }

    public void setDefaultPageNo(int pageNo) {
        mDefaultPageNo = pageNo;
    }

    public BaseRecyclerViewAdapter<T> getAdapter() {
        mAdapter = new BaseRecyclerViewAdapter<T>(getActivity(), mList);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setViewProvider(this);
        return mAdapter;
    }


    public void addItems(List<T> list) {
        int oldSize = mList.size();
        mList.addAll(list);
        notifyItemRangeInserted(oldSize, list.size());
    }

    public void addItems(int location, List<T> list) {
        mList.addAll(location, list);
        notifyItemRangeInserted(location, list.size());
    }

    public void addItem(T t) {
        mList.add(t);
        notifyItemInserted(mList.size() - 1);
    }

    public void addItem(int location, T t) {
        mList.add(location, t);
        notifyItemInserted(location);
    }

    public void deleteItem(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    public void deleteItems(int from, int to) {
        List<T> temp = new ArrayList<>();
        temp = mList.subList(from, to);
        mList.removeAll(temp);
        notifyItemRangeInserted(from, temp.size());
    }

    public void deleteItems(List<T> list) {
        mList.removeAll(list);
    }


    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public void notifyItemRangeInserted(int positionStart, int itemCount) {
        mAdapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    public void notifyItemInserted(int position) {
        mAdapter.notifyItemInserted(position);
    }

    public void notifyItemRemoved(int position) {
        mAdapter.notifyItemRemoved(position);
    }


    public void notifyItemRangeRemoved(int positionStart, int itemCount) {
        mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
    }


    public void notifyItemChanged(int position) {
        mAdapter.notifyItemChanged(position);
    }

    public void notifyItemChanged(int position, Object payload) {
        mAdapter.notifyItemChanged(position, payload);
    }

    public void notifyRangeChanged(int positionStart, int itemCount) {
        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    public void notifyRangeChanged(int positionoStart, int itemCount, Object payload) {
        mAdapter.notifyItemRangeChanged(positionoStart, itemCount, payload);
    }

    //设置当数据为空时的提示信息
    public void setmEmptyData(String text) {
        if (emptyData == null) return;
        emptyData.setText(text);
    }
}
