package com.fengniao.mlibs.adapter;

import android.animation.Keyframe;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fengniao.mlibs.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.FNViewHolder> {

    public static final int TYPE_LOAD_MORE = 20;
    private boolean mShouldLoadMore;   //标记是否需要自动刷新
    private boolean mLoadingMore;     //表示是否已经在加载更多了
    protected List<T> mList;
    private Context mContext;
    private List<View> mFooters;
    private OnItemClickListener mOnItemClickListener;
    private ViewProvider mViewProvider;

    public BaseRecyclerViewAdapter(Context mContext, List<T> mList) {
        this.mList = mList;
        this.mContext = mContext;
        this.mFooters = new ArrayList<>();
    }

    public boolean isLoadingMore() {
        return mLoadingMore;
    }

    public void setLoadingMore(boolean isLoadingMore) {
        this.mLoadingMore = isLoadingMore;
    }

    public void setShouldLoadMore(boolean shouldLoadMore) {
        this.mShouldLoadMore = shouldLoadMore;
        notifyDataSetChanged();
    }

    public boolean isShouldLoadMore() {
        return mShouldLoadMore;
    }

    public View getView(ViewGroup parent, int viewType) {
        if (mViewProvider == null) {
            return new View(mContext);
        }
        return mViewProvider.getView(parent, viewType);
    }


    @Override
    public FNViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOAD_MORE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_footer_load_more, parent, false);
            return new FNViewHolder(view);
        }
        FNViewHolder viewHolder = new FNViewHolder(getView(parent, viewType));
        mViewProvider.onCreateViewHolder(viewHolder, viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FNViewHolder holder, final int position) {
        if (mShouldLoadMore) {
            if (holder.getItemViewType() == TYPE_LOAD_MORE) {
                if (mLoadingMore)
                    return;
                mViewProvider.onLoadMore();
                mLoadingMore = true;
                return;
            }
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != mList.size()) {
                        mOnItemClickListener.onItemClick(holder, mList.get(position), position, getItemId(position));
                    } else {
                        return;
                    }
                }
            });
        }
        if (mViewProvider != null) {
            mViewProvider.bindDataToView(holder, position);
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (mShouldLoadMore) {
            if (position == getItemCount() - 1) {
                return 20;
            }
        }
        if (mViewProvider != null)
            return mViewProvider.getItemViewType(position);
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (mViewProvider == null) {
            return mList.size();
        }
        if (mShouldLoadMore) {
            return mViewProvider.getItemCount() + 1;
        }
        return mViewProvider.getItemCount();
    }

    public static class FNViewHolder extends RecyclerView.ViewHolder {
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_FOOTER = 1;
        public static final int TYPE_NORML = 2;
        private SparseArray<View> mViews = new SparseArray<>();

        public FNViewHolder(View itemView) {
            super(itemView);
        }

        public <T extends View> T getView(int id) {
            T t = (T) mViews.get(id);
            if (t == null) {
                t = (T) itemView.findViewById(id);
                if (t != null) {
                    mViews.put(id, t);
                }
            }
            return t;
        }

        public void setText(int textViewId, CharSequence text) {
            TextView textView = getView(textViewId);
            if (textView != null)
                textView.setText(text);
        }
    }


    public void setViewProvider(ViewProvider provider) {
        mViewProvider = provider;
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }

    public interface ViewProvider {
        View getView(ViewGroup parent, int viewType);

        void bindDataToView(FNViewHolder holder, int position);

        int getItemViewType(int position);

        int getItemCount();

        void onCreateViewHolder(FNViewHolder holder, int viewType);

        void onLoadMore();
    }

    public interface OnItemClickListener<T> {
        void onItemClick(FNViewHolder holder, T t, int positon, long id);
    }
}
