package com.eoc.dong.common.network.request;

/**
 * CodeFatCat
 */

public class UploadContractRequest {
    private String name;
    private String phone;
    private String link;
    private int userId;

    public UploadContractRequest(String name, String phone,int userId,String link) {
        this.name = name;
        this.phone = phone;
        this.userId = userId;
        this.link = link;
    }
}
