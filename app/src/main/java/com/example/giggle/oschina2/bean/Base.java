package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 *
 * Created by Giggle on 2015-11-26.
 */
public abstract class Base implements Serializable {

    //别名注解
    @XStreamAlias("notice")
    protected Notice notice;

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
