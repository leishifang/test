package com.example.giggle.oschina2.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.bean.Comment;
import com.example.giggle.oschina2.ui.CircleImageView;
import com.example.giggle.oschina2.widget.FloorView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by leiShifang on 2016/1/3 20:45.
 */
public class CommentAdapter extends BaseListAdapter<Comment> {
    ViewHolder mViewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null || mViewHolder == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comment, null);
            mViewHolder = new ViewHolder(convertView);
            
        } else {
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_portrait)
        CircleImageView mIvPortrait;
        @Bind(R.id.tv_name)
        TextView mTvName;
        @Bind(R.id.tv_time)
        TextView mTvTime;
        @Bind(R.id.floorview)
        FloorView mFloorview;
        @Bind(R.id.tv_comment)
        TextView mTvComment;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
