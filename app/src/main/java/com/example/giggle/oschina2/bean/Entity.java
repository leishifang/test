package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Giggle on 2015-11-26.
 */
public class Entity extends Base {

    @XStreamAlias("id")
    protected int id;

    protected String cacheKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
}
