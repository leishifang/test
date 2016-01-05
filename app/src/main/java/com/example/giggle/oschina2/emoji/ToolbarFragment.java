package com.example.giggle.oschina2.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.base.BaseFragment;

public class ToolbarFragment extends BaseFragment {

    public enum ToolAction {
        ACTION_CHANGE, ACTION_VIEW_COMMENT, ACTION_WRITE_COMMENT, ACTION_FAVORITE, ACTION_SHARE;
    }

    public interface OnClickListener {

        void OnClick(ToolAction action);
    }

    private View mActionWriteComment, mActionViewComment, mActionFavorite,

    mActionReport, mActionShare;
    private View mIvFavorite;
    private boolean mFavorite;
    private TextView mTvCommentCount;
    private View mRootView;
    private OnClickListener mOnClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_detail_tool_bar,
                container, false);
        initView(mRootView);
        return mRootView;
    }

    public View getRootView() {
        return mRootView;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.btn_change).setOnClickListener(this);

        mActionWriteComment = view.findViewById(R.id.write_comment_layout);
        mActionWriteComment.setOnClickListener(this);

        mActionFavorite = view.findViewById(R.id.favorites_layout);
        mActionFavorite.setOnClickListener(this);

        mActionViewComment = view.findViewById(R.id.view_comment_layout);
        mActionViewComment.setOnClickListener(this);

        mActionShare = view.findViewById(R.id.share_layout);
        mActionShare.setOnClickListener(this);

        mActionReport = view.findViewById(R.id.report_layout);
        mActionReport.setOnClickListener(this);

        mIvFavorite = view.findViewById(R.id.action_favor);
        mIvFavorite.setSelected(mFavorite);
        mTvCommentCount = (TextView) view
                .findViewById(R.id.action_comment_count);
        setCommentCount();
    }

    private void setCommentCount() {
        int commentCount = getActivity().getIntent().getIntExtra("comment_count", 0);
        if (commentCount > 0 && mTvCommentCount != null) {
            mTvCommentCount.setText(String.valueOf(commentCount));
            mTvCommentCount.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        ToolAction toolAction = null;
        switch (v.getId()) {
            case R.id.btn_change:
                toolAction = ToolAction.ACTION_CHANGE;
                break;
            case R.id.write_comment_layout:
                toolAction = ToolAction.ACTION_WRITE_COMMENT;
                break;
            case R.id.view_comment_layout:
                toolAction = ToolAction.ACTION_VIEW_COMMENT;
                break;
            case R.id.favorites_layout:
                toolAction = ToolAction.ACTION_FAVORITE;
                break;
            case R.id.share_layout:
                toolAction = ToolAction.ACTION_SHARE;
                break;
            default:
                break;
        }
        if (toolAction != null && mOnClickListener != null) {
            mOnClickListener.OnClick(toolAction);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    public void setFavorite(boolean favorite) {
        mFavorite = favorite;
        if (mIvFavorite != null) {
            mIvFavorite.setSelected(favorite);
        }
    }

    public void showReportButton() {
        mActionReport.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
    }
}