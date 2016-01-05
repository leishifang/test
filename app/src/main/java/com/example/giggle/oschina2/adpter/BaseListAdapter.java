package com.example.giggle.oschina2.adpter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.giggle.oschina2.bean.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giggle on 2015-11-29.
 */
public class BaseListAdapter<T extends Entity> extends BaseAdapter {

    protected ArrayList<T> mList = new ArrayList<T>();

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
