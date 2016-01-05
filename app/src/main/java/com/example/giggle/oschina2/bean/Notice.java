package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 通知信息实体
 */
@XStreamAlias("notice")
public class Notice implements Serializable {

    public static final String UTF8 = "UTF-8";
    public static final String Node_Root = "oschina";

    public final static int TYPE_ATME = 1;
    public final static int TYPE_MESSAGE = 2;
    public final static int TYPE_COMMENT = 3;
    public final static int TYPE_NEWFAN = 4;
    public final static int TYPE_NEWLIKE = 5;

    @XStreamAlias("atmeCount")
    private int atmeCount;

    @XStreamAlias("msgCount")
    private int msgCount;

    @XStreamAlias("reviewCount")
    private int reviewCount;

    @XStreamAlias("newFansCount")
    private int newFansCount;

    @XStreamAlias("newLikeCount")
    private int newLikeCount;

    public int getAtmeCount() {
        return atmeCount;
    }

    public void setAtmeCount(int atmeCount) {
        this.atmeCount = atmeCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getNewFansCount() {
        return newFansCount;
    }

    public void setNewFansCount(int newFansCount) {
        this.newFansCount = newFansCount;
    }

    public int getNewLikeCount() {
        return newLikeCount;
    }

    public void setNewLikeCount(int newLikeCount) {
        this.newLikeCount = newLikeCount;
    }
}
