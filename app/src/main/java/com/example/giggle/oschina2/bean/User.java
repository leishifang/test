package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by leishifang on 2016/3/25 23:57.
 */
@XStreamAlias("user")
public class User implements Serializable {

    //登陆账户名
    private String account;
    //登陆密码
    private String password;

    @XStreamAlias("uid")
    private String uid;

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("location")
    private String location;

    @XStreamAlias("followers")
    private int followers;

    @XStreamAlias("fans")
    private int fans;
    //积分
    @XStreamAlias("score")
    private int score;
    //头像
    @XStreamAlias("portrait")
    private String portrait;
    //性别

    @XStreamAlias("favoritecount")
    private String favoritecount;

    @XStreamAlias("gender")
    private int gender;

    public User() {

    }

    public User(String uid, String name, String location, int followers, int fans, int score, String
            portrait, String favoritecount, int gender) {
        this.uid = uid;
        this.name = name;
        this.location = location;
        this.followers = followers;
        this.fans = fans;
        this.score = score;
        this.portrait = portrait;
        this.favoritecount = favoritecount;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", followers=" + followers +
                ", fans=" + fans +
                ", score=" + score +
                ", portrait='" + portrait + '\'' +
                ", favoritecount='" + favoritecount + '\'' +
                ", gender=" + gender +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavoritecount() {
        return favoritecount;
    }

    public void setFavoritecount(String favoritecount) {
        this.favoritecount = favoritecount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
