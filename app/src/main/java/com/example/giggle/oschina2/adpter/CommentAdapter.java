package com.example.giggle.oschina2.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.bean.Comment;

/**
 * Created by leiShifang on 2016/1/3 20:45.
 */
public class CommentAdapter extends BaseListAdapter<Comment> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* if (convertView == null ||convertView.getTag()==null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_iten_comment,null);
        }else {

        }*/
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comment,null);

    }
}
