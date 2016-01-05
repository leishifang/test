package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leiShifang on 2016/1/2 23:32.
 */
@XStreamAlias("comment")
public class Comment extends Entity {

    public static final String BUNDLE_KEY_COMMENT = "bundle_key_comment";
    public static final String BUNDLE_KEY_ID = "bundle_key_id";
    public static final String BUNDLE_KEY_CATALOG = "bundle_key_catalog";
    public static final String BUNDLE_KEY_BLOG = "bundle_key_blog";
    public static final String BUNDLE_KEY_OPERATION = "bundle_key_operation";

    public static final int OPT_ADD = 1;
    public static final int OPT_REMOVE = 2;

    public final static int CLIENT_MOBILE = 2;
    public final static int CLIENT_ANDROID = 3;
    public final static int CLIENT_IPHONE = 4;
    public final static int CLIENT_WINDOWS_PHONE = 5;

    @XStreamAlias("authorId")
    private int authorId;

    @XStreamAlias("portrait")
    private String portrait;

    @XStreamAlias("author")
    private String autor;

    @XStreamAlias("content")
    private String content;

    @XStreamAlias("pubDate")
    private String pubDate;

    @XStreamAlias("appClient")
    private String appClient;

    @XStreamAlias("replies")
    private List<Reply> replies = new ArrayList<>();

    @XStreamAlias("refers")
    private List<Refer> refers = new ArrayList<Refer>();

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getAppClient() {
        return appClient;
    }

    public void setAppClient(String appClient) {
        this.appClient = appClient;
    }

    @XStreamAlias("reply")
    public static class Reply implements Serializable {

        @XStreamAlias("rauthor")
        public String rauthor;

        @XStreamAlias("rpubDate")
        public String rpubDate;

        @XStreamAlias("rcontent")
        public String rcontent;

        public String getRauthor() {
            return rauthor;
        }

        public void setRauthor(String rauthor) {
            this.rauthor = rauthor;
        }

        public String getRpubDate() {
            return rpubDate;
        }

        public void setRpubDate(String rpubDate) {
            this.rpubDate = rpubDate;
        }

        public String getRcontent() {
            return rcontent;
        }

        public void setRcontent(String rcontent) {
            this.rcontent = rcontent;
        }
    }

    @XStreamAlias("refer")
    private class Refer implements Serializable {

        @XStreamAlias("refertitle")
        public String refertitle;

        @XStreamAlias("referbody")
        public String referbody;

        public String getRefertitle() {
            return refertitle;
        }

        public void setRefertitle(String refertitle) {
            this.refertitle = refertitle;
        }

        public String getReferbody() {
            return referbody;
        }

        public void setReferbody(String referbody) {
            this.referbody = referbody;
        }
    }
}
