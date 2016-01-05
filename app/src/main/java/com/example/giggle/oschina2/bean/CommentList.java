package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leiShifang on 2016/1/3 14:58.
 */
@XStreamAlias("oschina")
public class CommentList extends Entity implements EntityList<Comment> {

    public final static int CATALOG_NEWS = 1;
    public final static int CATALOG_POST = 2;
    public final static int CATALOG_TWEET = 3;
    public final static int CATALOG_ACTIVE = 4;
    public final static int CATALOG_MESSAGE = 4;

    @XStreamAlias("pagesize")
    private int pageSize;
    @XStreamAlias("allCount")
    private int allCount;
    @XStreamAlias("comments")
    private final List<Comment> commentlist = new ArrayList<Comment>();

    public static int getCatalogNews() {
        return CATALOG_NEWS;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public List<Comment> getCommentlist() {
        return commentlist;
    }

    @Override
    public List<Comment> getList() {
        return commentlist;
    }
}
