package com.eoc.dong.common.network.response;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/12/1.
 */

public class ProfileResponse {
    /**
     * money : 500
     * user : {"id":5,"gmtDatetime":null,"uptDatetime":"2017-12-18 10:11:18","uuid":"519d6d1084e94bedbe468949c6ca4ae8","userName":"熊磊","channelId":null,"channel":null,"password":"e10adc3949ba59abbe56e057f20f883e","headImg":null,"phone":"18767702333","userType":1,"authStatus":"认证成功","token":"75bddf1f4249a45f659894d7903f673e","status":"正常","defriendReason":null,"payPwd":null,"authScore":60,"phoneSign":"","refuseRemoveTime":null,"bankAuthNum":0,"taobaoAuthNum":0}
     */

    private BigDecimal money;
    private BigDecimal needPayMoney;
    private BigDecimal borrowMoney;
    private String orderStatus;
    private String gmtDatetime;
    private String limitPayTime;
    private String orderId;
    private UserBean user;

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getBorrowMoney() {
        return borrowMoney;
    }

    public String getGmtDatetime() {
        return gmtDatetime;
    }

    public String getLimitPayTime() {
        return limitPayTime;
    }

    public BigDecimal getNeedPayMoney() {
        return needPayMoney;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public UserBean getUser() {
        return user;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public static class UserBean {
        /**
         * id : 5
         * gmtDatetime : null
         * uptDatetime : 2017-12-18 10:11:18
         * uuid : 519d6d1084e94bedbe468949c6ca4ae8
         * userName : 熊磊
         * channelId : null
         * channel : null
         * password : e10adc3949ba59abbe56e057f20f883e
         * headImg : null
         * phone : 18767702333
         * userType : 1
         * authStatus : 认证成功
         * token : 75bddf1f4249a45f659894d7903f673e
         * status : 正常
         * defriendReason : null
         * payPwd : null
         * authScore : 60
         * phoneSign :
         * refuseRemoveTime : null
         * bankAuthNum : 0
         * taobaoAuthNum : 0
         */

        private int id;
        private Object gmtDatetime;
        private String uptDatetime;
        private String uuid;
        private String userName;
        private Object channelId;
        private Object channel;
        private String password;
        private Object headImg;
        private String phone;
        private int userType;
        private String authStatus;
        private String token;
        private String status;
        private Object defriendReason;
        private Object payPwd;
        private int authScore;
        private String phoneSign;
        private Object refuseRemoveTime;
        private int bankAuthNum;
        private int taobaoAuthNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getGmtDatetime() {
            return gmtDatetime;
        }

        public void setGmtDatetime(Object gmtDatetime) {
            this.gmtDatetime = gmtDatetime;
        }

        public String getUptDatetime() {
            return uptDatetime;
        }

        public void setUptDatetime(String uptDatetime) {
            this.uptDatetime = uptDatetime;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getChannelId() {
            return channelId;
        }

        public void setChannelId(Object channelId) {
            this.channelId = channelId;
        }

        public Object getChannel() {
            return channel;
        }

        public void setChannel(Object channel) {
            this.channel = channel;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getHeadImg() {
            return headImg;
        }

        public void setHeadImg(Object headImg) {
            this.headImg = headImg;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(String authStatus) {
            this.authStatus = authStatus;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getDefriendReason() {
            return defriendReason;
        }

        public void setDefriendReason(Object defriendReason) {
            this.defriendReason = defriendReason;
        }

        public Object getPayPwd() {
            return payPwd;
        }

        public void setPayPwd(Object payPwd) {
            this.payPwd = payPwd;
        }

        public int getAuthScore() {
            return authScore;
        }

        public void setAuthScore(int authScore) {
            this.authScore = authScore;
        }

        public String getPhoneSign() {
            return phoneSign;
        }

        public void setPhoneSign(String phoneSign) {
            this.phoneSign = phoneSign;
        }

        public Object getRefuseRemoveTime() {
            return refuseRemoveTime;
        }

        public void setRefuseRemoveTime(Object refuseRemoveTime) {
            this.refuseRemoveTime = refuseRemoveTime;
        }

        public int getBankAuthNum() {
            return bankAuthNum;
        }

        public void setBankAuthNum(int bankAuthNum) {
            this.bankAuthNum = bankAuthNum;
        }

        public int getTaobaoAuthNum() {
            return taobaoAuthNum;
        }

        public void setTaobaoAuthNum(int taobaoAuthNum) {
            this.taobaoAuthNum = taobaoAuthNum;
        }
    }
}
