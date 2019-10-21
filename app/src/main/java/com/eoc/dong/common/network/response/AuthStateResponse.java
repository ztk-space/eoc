package com.eoc.dong.common.network.response;

/**
 * Created by Administrator on 2018/1/8.
 */

public class AuthStateResponse {


        /**
         * baiscAuth : 1
         * bankAuth : 0
         * identityAuth : 0
         * gmtDatetime : null
         * authStatus : 0
         * taobaoAuth : 0
         * userId : 13
         * authDatetime : 1544613979000
         * phoneAuth : 0
         * zfbAuth : 0
         * uptDatetime : null
         * id : 6
         * status : 1
         */

        public int baiscAuth;
        public int bankAuth;
        public int identityAuth;
        public Object gmtDatetime;
        public int authStatus;
        public int taobaoAuth;
        public int userId;
        public String authDatetime;
        public int phoneAuth;
        public int zfbAuth;
        public Object uptDatetime;
        public int id;
        public int status;
        public int tripAuth;
        public int dsAuth;
        public int socialAuth;
        public int yysAuth;

    public int getTripAuth() {
        return tripAuth;
    }

    public void setTripAuth(int tripAuth) {
        this.tripAuth = tripAuth;
    }

    public int getDsAuth() {
        return dsAuth;
    }

    public void setDsAuth(int dsAuth) {
        this.dsAuth = dsAuth;
    }

    public int getSocialAuth() {
        return socialAuth;
    }

    public void setSocialAuth(int socialAuth) {
        this.socialAuth = socialAuth;
    }

    public int getYysAuth() {
        return yysAuth;
    }

    public void setYysAuth(int yysAuth) {
        this.yysAuth = yysAuth;
    }

    public int getBaiscAuth() {
        return baiscAuth;
    }

    public void setBaiscAuth(int baiscAuth) {
        this.baiscAuth = baiscAuth;
    }

    public int getBankAuth() {
        return bankAuth;
    }

    public void setBankAuth(int bankAuth) {
        this.bankAuth = bankAuth;
    }

    public int getIdentityAuth() {
        return identityAuth;
    }

    public void setIdentityAuth(int identityAuth) {
        this.identityAuth = identityAuth;
    }



    public void setGmtDatetime(Object gmtDatetime) {
        this.gmtDatetime = gmtDatetime;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public int getTaobaoAuth() {
        return taobaoAuth;
    }

    public void setTaobaoAuth(int taobaoAuth) {
        this.taobaoAuth = taobaoAuth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public int getPhoneAuth() {
        return phoneAuth;
    }

    public void setPhoneAuth(int phoneAuth) {
        this.phoneAuth = phoneAuth;
    }

    public int getZfbAuth() {
        return zfbAuth;
    }

    public void setZfbAuth(int zfbAuth) {
        this.zfbAuth = zfbAuth;
    }

    public Object getUptDatetime() {
        return uptDatetime;
    }

    public void setUptDatetime(Object uptDatetime) {
        this.uptDatetime = uptDatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

