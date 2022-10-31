package org.daomingedu.onecpexam.base;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by xjh on 2016/9/9.
 */
public class ViewHolder {
    private final SparseArray<View> mViews = new SparseArray();
    private View mConvertView;

    private ViewHolder(ViewGroup parent, int layoutId, LayoutInflater inflater, boolean isScale) {
        this.mConvertView = inflater.inflate(layoutId, parent, false);


        this.mConvertView.setTag(this);
    }

    public static ViewHolder get(View convertView, ViewGroup parent, int layoutId, LayoutInflater inflater, boolean isScale) {
        return convertView == null?new ViewHolder(parent, layoutId, inflater, isScale):(ViewHolder)convertView.getTag();
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = (View)this.mViews.get(viewId);
        if(view == null) {
            view = this.mConvertView.findViewById(viewId);
            this.mViews.put(viewId, view);
        }

        return (T) view;
    }

    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = (TextView)this.getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = (ImageView)this.getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = (ImageView)this.getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

}