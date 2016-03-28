package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by leishifang on 2016/3/26 0:43.
 */
@XStreamAlias("oschina")
public class LoginResult extends Entity {

    @XStreamAlias("result")
    private Result result;

    @XStreamAlias("user")
    private User user;

    @XStreamAlias("notice")
    private Notice notice;

    public LoginResult() {
    }

    public LoginResult(Result result, User user, Notice notice) {
        this.result = result;
        this.user = user;
        this.notice = notice;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "result=" + result +
                ", user=" + user +
                ", notice=" + notice +
                '}';
    }
}
