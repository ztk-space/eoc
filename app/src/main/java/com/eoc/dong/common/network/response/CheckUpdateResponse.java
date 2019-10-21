package com.eoc.dong.common.network.response;

/**
 * Created by jh352160 on 2018/4/9.
 */
public class CheckUpdateResponse {
    /**
     * id : 1
     * code : 1.0.1
     * link : null
     * status : 使用
     * type : android
     * gmtDatetime : 2018-04-09 11:19:33
     * uptDatetime : 2018-04-09 11:19:35
     */

    private int id;
    private String code;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String status;
    private String type;
    private String gmtDatetime;
    private String uptDatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGmtDatetime() {
        return gmtDatetime;
    }

    public void setGmtDatetime(String gmtDatetime) {
        this.gmtDatetime = gmtDatetime;
    }

    public String getUptDatetime() {
        return uptDatetime;
    }

    public void setUptDatetime(String uptDatetime) {
        this.uptDatetime = uptDatetime;
    }
}
