package com.eoc.dong.common.network;

/**
 * Created by jh352160 on 2017/9/8.
 */

public class BaseResponse<T> {
    /**
     * code : SUCCESS
     * data : T
     * msg : 成功
     * success : true
     */

    private String code;
    private T data;
    private String msg;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
