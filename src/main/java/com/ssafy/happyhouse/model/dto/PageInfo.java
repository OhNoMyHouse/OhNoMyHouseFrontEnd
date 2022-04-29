package com.ssafy.happyhouse.model.dto;

public class PageInfo {
    boolean isForward;
    String url;

    public PageInfo(boolean isForward, String url) {
        this.isForward = isForward;
        this.url = url;
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
