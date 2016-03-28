package com.example.giggle.oschina2.ui;

import com.example.giggle.oschina2.Fragment.UserInfoFragment;
import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.viewpagerfragment.NewsViewPagerFragment;

public enum MainTab {
    NEWS(0, R.string.main_tab_name_news, R.drawable.tab_icon_news, NewsViewPagerFragment.class),
    TWEET(1, R.string.main_tab_name_tweet, R.drawable.tab_icon_news, NewsViewPagerFragment.class),
    QUICK(2, R.string.main_tab_name_quick, R.drawable.tab_icon_news, NewsViewPagerFragment.class),
    EXPLORE(3, R.string.main_tab_name_explore, R.drawable.tab_icon_news, NewsViewPagerFragment.class),
    ME(4, R.string.main_tab_name_my, R.drawable.tab_icon_news, UserInfoFragment.class);

    private int id;
    private int nameId;
    private int iconId;
    private Class<?> clz;

    MainTab(int i, int nameId, int iconId, Class<?> clz) {
        this.id = i;
        this.nameId = nameId;
        this.iconId = iconId;
        this.clz = clz;
    }

    void setId(int id) {
        this.id = id;
    }

    void setName(int name) {
        this.nameId = name;
    }

    void setIconID(int iconID) {
        this.iconId = iconID;
    }

    void setClz(Class<?> clz) {
        this.clz = clz;
    }

    int getId() {
        return id;
    }

    int getNameId() {
        return nameId;
    }

    int getIconID() {
        return iconId;
    }

    Class<?> getClz() {
        return clz;
    }
}
