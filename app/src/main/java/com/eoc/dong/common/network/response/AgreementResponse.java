package com.eoc.dong.common.network.response;

/**
 * Created by Administrator on 2017/12/21.
 */

public class AgreementResponse {
    private int id;
    private String content;
    private String status;
    private Object picUrl;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(Object picUrl) {
        this.picUrl = picUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
