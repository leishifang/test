package com.example.giggle.oschina2.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.giggle.oschina2.Fragment.CommentFragment;
import com.example.giggle.oschina2.Fragment.NewsDetailFragment;
import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.base.BaseFragment;
import com.example.giggle.oschina2.emoji.KJEmojiFragment;
import com.example.giggle.oschina2.emoji.OnSendClickListener;
import com.example.giggle.oschina2.emoji.ToolbarFragment;
import com.example.giggle.oschina2.util.UIHelper;

/**
 * Created by Giggle on 2015-11-30.
 */
public class DetailActivity extends AppCompatActivity implements OnSendClickListener {

    public static final String BUNDLE_KEY_DISPLAY_TYPE = "BUNDLE_KEY_DISPLAY_TYPE";

    public static final int DISPLAY_NEWS = 0;
    public static final int DISPLAY_BLOG = 1;
    public static final int DISPLAY_SOFTWARE = 2;
    public static final int DISPLAY_POST = 3;
    public static final int DISPLAY_TWEET = 4;
    public static final int DISPLAY_EVENT = 5;
    public static final int DISPLAY_TEAM_ISSUE_DETAIL = 6;
    public static final int DISPLAY_TEAM_DISCUSS_DETAIL = 7;
    public static final int DISPLAY_TEAM_TWEET_DETAIL = 8;
    public static final int DISPLAY_TEAM_DIARY = 9;
    public static final int DISPLAY_COMMENT = 10;

    public ToolbarFragment mToolbarFragment = new ToolbarFragment();
    public KJEmojiFragment mKJEmojiFragment = new KJEmojiFragment();
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra(BUNDLE_KEY_DISPLAY_TYPE, DISPLAY_NEWS);

        int actionbarTitleID = 0;
        BaseFragment baseFragment = null;
        switch (type) {
            case DISPLAY_NEWS:
                baseFragment = new NewsDetailFragment();
                actionbarTitleID = R.string.actionbar_title_news;
                break;
            case DISPLAY_BLOG:
                baseFragment = new NewsDetailFragment();
                actionbarTitleID = R.string.actionbar_title_blog;
                break;
            case DISPLAY_SOFTWARE:
                baseFragment = new NewsDetailFragment();
                actionbarTitleID = R.string.actionbar_title_software;
                break;
            case DISPLAY_POST:
                baseFragment = new NewsDetailFragment();
                actionbarTitleID = R.string.share_title_post;
                break;
            case DISPLAY_TWEET:
                baseFragment = new NewsDetailFragment();
                actionbarTitleID = R.string.actionbar_title_tweet;
                break;
            case DISPLAY_EVENT:
                baseFragment = new NewsDetailFragment();
                actionbarTitleID = R.string.actionbar_title_event;
                break;
            case DISPLAY_TEAM_ISSUE_DETAIL:
                baseFragment = new NewsDetailFragment();
                actionbarTitleID = R.string.actionbar_title_event_detail;
                break;
            case DISPLAY_TEAM_DISCUSS_DETAIL:
                actionbarTitleID = R.string.actionbar_title_question;
                baseFragment = new NewsDetailFragment();
                break;
            case DISPLAY_TEAM_TWEET_DETAIL:
                actionbarTitleID = R.string.actionbar_dynamic_detail;
                baseFragment = new NewsDetailFragment();
                break;
            case DISPLAY_TEAM_DIARY:
                actionbarTitleID = R.string.team_diary_detail;
                baseFragment = new NewsDetailFragment();
                break;
            case DISPLAY_COMMENT:
                actionbarTitleID = R.string.actionbar_title_comment;
                baseFragment = new CommentFragment();
                break;
            default:
                break;
        }

        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(actionbarTitleID);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, baseFragment);
        fragmentTransaction.commitAllowingStateLoss();

        if (baseFragment instanceof CommentFragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.emoji_keyboard, mKJEmojiFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.emoji_keyboard, mToolbarFragment).commit();
        }

        mToolbarFragment.setOnClickListener(new ToolbarFragment.OnClickListener() {
            @Override
            public void OnClick(ToolbarFragment.ToolAction action) {
                switch (action) {
                    case ACTION_CHANGE:
                    case ACTION_WRITE_COMMENT:
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.foot_menu_in, R.anim.foot_menu_out);
                        transaction.replace(R.id.emoji_keyboard, mKJEmojiFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case ACTION_VIEW_COMMENT:
                        int id = getIntent().getIntExtra("id", 0);
                        UIHelper.showComment(getApplicationContext(), id, DISPLAY_COMMENT);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mKJEmojiFragment.isShowEmojiKeyBoard()) {
                mKJEmojiFragment.hideAllKeyBoard();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //指定actionbar的触发行为
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickSendButton(Editable str) {

    }

    @Override
    public void onClickFlagButton() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.foot_menu_in, R.anim.foot_menu_out);
        transaction.replace(R.id.emoji_keyboard, mToolbarFragment);
        transaction.commitAllowingStateLoss();
    }
}
