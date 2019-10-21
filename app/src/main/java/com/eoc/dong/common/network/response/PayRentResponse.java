package com.eoc.dong.common.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jh352160 on 2018/3/16.
 */

public class PayRentResponse {
    private String alipay;
    /**
     * package : Sign=WXPay
     * appid : wx49813c36a713fa39
     * sign : 2978869DCD9FAB75BD8356F7D1A141EC
     * partnerid : 1500184812
     * prepayid : wx20180316221823b7842ccd210830668032
     * noncestr : 9doyrz2ed4ucni4av9v5zhj0ytbbv88i
     * timestamp : 1521209900
     */

    @SerializedName("package")
    private String packageX;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getAlipay() {
        return alipay;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
