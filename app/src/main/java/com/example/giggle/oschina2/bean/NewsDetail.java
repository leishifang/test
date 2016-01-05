package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 咨询详情
 * Created by Giggle on 2015-12-11.
 */
@XStreamAlias("oschina")
public class NewsDetail extends Entity {

    @XStreamAlias("news")
    private News mNews;

    public News getNews() {
        return mNews;
    }

    public void setNews(News news) {
        mNews = news;
    }
}
