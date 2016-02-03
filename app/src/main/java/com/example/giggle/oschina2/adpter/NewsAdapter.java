package com.example.giggle.oschina2.adpter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.bean.News;
import com.example.giggle.oschina2.util.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Giggle on 2015-11-28.
 */
@SuppressWarnings("unused")
public class NewsAdapter extends BaseListAdapter<News> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mListItemTitleTv.setText((getItem(position)).getTitle());
        viewHolder.mListItemCommentCountTv.setText((getItem(position)).getCommentCount() + "");
        viewHolder.mListItemDescriptionTv.setText((getItem(position)).getBody());
        viewHolder.mListItemSourceTv.setText((getItem(position)).getAuthor());
        viewHolder.mListItemTimeTv.setText((getItem(position)).getPubDate());
        if (!StringUtils.isToday(getItem(position).getPubDate())) {
            viewHolder.mListItemTipIv.setVisibility(View.GONE);
        } else {
            viewHolder.mListItemSourceTv.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        @Nullable
        @Bind(R.id.list_item_tip_iv)
        ImageView mListItemTipIv;
        @Nullable
        @Bind(R.id.list_item_title_tv)
        TextView mListItemTitleTv;
        @Nullable
        @Bind(R.id.list_item_description_tv)
        TextView mListItemDescriptionTv;
        @Nullable
        @Bind(R.id.list_item_source_tv)


        TextView mListItemSourceTv;
        @Nullable
        @Bind(R.id.list_item_time_tv)
        TextView mListItemTimeTv;
        @Nullable
        @Bind(R.id.list_item_comment_count_tv)
        TextView mListItemCommentCountTv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
