package com.eoc.dong.common.network.response;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LoginResponse {
    private String uuid;
    private int id;
    private String userName;
    private String password;
    private String phone;
    private String money;
    private int userType;
    private String authStatus;
    private String token;
    private String payPwd;

    public String getUuid() {
        return uuid;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getMoney() {
        return money;
    }

    public int getUserType() {
        return userType;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public String getToken() {
        return token;
    }

    public String getPayPwd() {
        return payPwd;
    }
}
