package com.example.giggle.oschina2.adpter;

import android.os.Bundle;

/**
 * Created by LeishiFang on 2015-11-19 17:07.
 */
public final class ViewPagerInfo {

    public final String tag;
    public final String title;
    public final Class<?> clss;
    public final Bundle args;

    public ViewPagerInfo(String tag, String title, Class<?> clss, Bundle args) {
        this.tag = tag;
        this.title = title;
        this.clss = clss;
        this.args = args;
    }
}
