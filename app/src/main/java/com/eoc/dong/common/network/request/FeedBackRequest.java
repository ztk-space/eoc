package com.eoc.dong.common.network.request;

/**
 * CodeFatCat
 */

public class FeedBackRequest {
    /**
     * type : 1
     * content : wwfwef0
     * imgUrl : 12312312
     */

    private String userPhone;
    private String text;
    private int userId;

    public FeedBackRequest(String userPhone, String text, int userId) {
        this.userPhone = userPhone;
        this.text = text;
        this.userId = userId;
    }
}
