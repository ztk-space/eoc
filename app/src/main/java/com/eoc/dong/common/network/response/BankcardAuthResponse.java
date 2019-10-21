package com.eoc.dong.common.network.response;

/**
 * Created by Administrator on 2017/11/27.
 */

public class BankcardAuthResponse {
    private String code;
    private DataBean data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public DataBean getData() {
        return data;
    }

    public static class DataBean{
        private String resultCode;
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }
    }
}
