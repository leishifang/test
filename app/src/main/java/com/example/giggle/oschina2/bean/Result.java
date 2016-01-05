package com.example.giggle.oschina2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 操作返回的结果
 * Created by Giggle on 2015-11-26.
 */
@XStreamAlias("result")
public class Result implements Serializable {

    @XStreamAlias("errorCode")
    private int errorCode;

    @XStreamAlias("errorMessage")
    private String errorMessage;

    public boolean OK() {
        return errorCode == 1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
