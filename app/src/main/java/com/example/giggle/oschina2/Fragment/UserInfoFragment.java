package com.example.giggle.oschina2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.ui.CircleImageView;
import com.example.giggle.oschina2.ui.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by leishifang on 2016/3/21 15:04.
 */
public class UserInfoFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.rl_no_login)
    RelativeLayout mRlNoLogin;
    @Bind(R.id.iv_portrait)
    CircleImageView mIvPortrait;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_score)
    TextView mTvScore;
    @Bind(R.id.ll_score)
    LinearLayout mLlScore;
    @Bind(R.id.tv_favorite)
    TextView mTvFavorite;
    @Bind(R.id.ll_favorite)
    LinearLayout mLlFavorite;
    @Bind(R.id.tv_followed)
    TextView mTvFollowed;
    @Bind(R.id.ll_followed)
    LinearLayout mLlFollowed;
    @Bind(R.id.tv_follower)
    TextView mTvFollower;
    @Bind(R.id.ll_follower)
    LinearLayout mLlFollower;
    @Bind(R.id.rl_logined)
    LinearLayout mRlLogined;
    @Bind(R.id.tv_message)
    TextView mTvMessage;
    @Bind(R.id.tv_message_count)
    TextView mTvMessageCount;
    @Bind(R.id.ll_option_message)
    LinearLayout mLlOptionMessage;
    @Bind(R.id.tv_blog)
    TextView mTvBlog;
    @Bind(R.id.tv_blog_count)
    TextView mTvBlogCount;
    @Bind(R.id.ll_option_blog)
    LinearLayout mLlOptionBlog;
    @Bind(R.id.tv_note)
    TextView mTvNote;
    @Bind(R.id.ll_option_note)
    LinearLayout mLlOptionNote;
    @Bind(R.id.tv_team)
    TextView mTvTeam;
    @Bind(R.id.ll_option_team)
    LinearLayout mLlOptionTeam;
    @Bind(R.id.ll_option_root)
    LinearLayout mLlOptionRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_information, null);
        ButterKnife.bind(this, root);

        mRlNoLogin.setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        getContext().startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
