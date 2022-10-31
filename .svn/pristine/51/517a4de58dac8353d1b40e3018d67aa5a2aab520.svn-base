package org.daomingedu.onecpexam.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xjh on 2016/9/9.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private LayoutInflater mInflater;
    public Context mContext;
    public List<T> list;
    private final int mItemLayoutId;
    boolean isScale;

    public CommonAdapter(Context context, List<T> list, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.list = list;
        this.mItemLayoutId = itemLayoutId;
    }

    public CommonAdapter(Context context, List<T> list, int itemLayoutId, boolean isScale) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.isScale = isScale;
        this.list = list;
        this.mItemLayoutId = itemLayoutId;
    }

    public int getCount() {
        return this.list.size();
    }

    public T getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return (long)position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = this.getViewHolder(convertView, parent);
        this.getView(viewHolder, this.getItem(position), position);
        return viewHolder.getConvertView();
    }

    public abstract void getView(ViewHolder holder, T data, int pos);

    private ViewHolder getViewHolder(View convertView, ViewGroup parent) {
        return ViewHolder.get(convertView, parent, this.mItemLayoutId, this.mInflater, this.isScale);
    }

    public void addAll(List<T> elem) {
        this.list.addAll(elem);
        this.notifyDataSetChanged();
    }

    public void remove(T elem) {
        this.list.remove(elem);
        this.notifyDataSetChanged();
    }

    public void remove(int index) {
        this.list.remove(index);
        this.notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        this.list.clear();
        this.list.addAll(elem);
        this.notifyDataSetChanged();
    }
}