package com.eoc.dong.common.network.response;

/**
 * Created by Administrator on 2018/1/10.
 */

public class BankCardListResponse {

    /**
     * cardlist : [{"bankcode":"ABC","bindid":"165234267","cardlast":"4375","cardname":"金穗通宝卡(银联卡)","cardtop":"622848","merchantno":"10000469946","phone":"13862035414"}]
     * identityid : 15
     * identitytype : USER_ID
     * merchantno : 10000469946
     */

    private String cardlist;
    private String identityid;
    private String identitytype;
    private String merchantno;

    public String getCardlist() {
        return cardlist;
    }

    public void setCardlist(String cardlist) {
        this.cardlist = cardlist;
    }

    public String getIdentityid() {
        return identityid;
    }

    public void setIdentityid(String identityid) {
        this.identityid = identityid;
    }

    public String getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(String identitytype) {
        this.identitytype = identitytype;
    }

    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno;
    }
}
