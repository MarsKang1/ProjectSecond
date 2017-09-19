package com.common.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 * adapter的公共类
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private OnItemClickListener mOnItemClickListener;

    public CommonAdapter(Context mContext, int mLayoutId, List<T> mDatas) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.NewInstance(mContext, null, parent, mLayoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setData(holder, mDatas.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @SuppressWarnings("unchecked")
    private void setListener(final ViewGroup parent, final ViewHolder holder, int viewType) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener == null) return;
                int position = getPosition(holder);
                mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
            }
        });
        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener == null) return false;
                int position = getPosition(holder);
                return mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
            }
        });
    }

    private int getPosition(ViewHolder holder) {
        return holder.getAdapterPosition();
    }

    public abstract void setData(ViewHolder holder, T t);

}
