package com.eoc.dong.common.network.request;

/**
 * CodeFatCat
 */

public class ImproveAuthRequest {
    /**
     * phoneAddressBook1 : {"contact":"联系人张三","phone":"1545112121","relationship":"朋友"}
     * phoneAddressBook2 : {"contact":"联系人李四","phone":"1545113434","relationship":"同事"}
     * address : {"province":"浙江省","city":"杭州市","area":"拱墅区","addressDetail":"祥园路666号"}
     * maritalStatus : 已婚
     * education : 本科
     */

    private String contactsNameFirst;
    private String contactsPhoneFirst;
    private String contactsRelationFirst;
    private String contactsNameSecond;
    private String contactsPhoneSecond;
    private String contactsRelationSecond;
    private String homeAddr;
    private String marriage;
    private String diploma;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getContactsNameFirst() {
        return contactsNameFirst;
    }

    public void setContactsNameFirst(String contactsNameFirst) {
        this.contactsNameFirst = contactsNameFirst;
    }

    public String getContactsPhoneFirst() {
        return contactsPhoneFirst;
    }

    public void setContactsPhoneFirst(String contactsPhoneFirst) {
        this.contactsPhoneFirst = contactsPhoneFirst;
    }

    public String getContactsRelationFirst() {
        return contactsRelationFirst;
    }

    public void setContactsRelationFirst(String contactsRelationFirst) {
        this.contactsRelationFirst = contactsRelationFirst;
    }

    public String getContactsNameSecond() {
        return contactsNameSecond;
    }

    public void setContactsNameSecond(String contactsNameSecond) {
        this.contactsNameSecond = contactsNameSecond;
    }

    public String getContactsPhoneSecond() {
        return contactsPhoneSecond;
    }

    public void setContactsPhoneSecond(String contactsPhoneSecond) {
        this.contactsPhoneSecond = contactsPhoneSecond;
    }

    public String getContactsRelationSecond() {
        return contactsRelationSecond;
    }

    public void setContactsRelationSecond(String contactsRelationSecond) {
        this.contactsRelationSecond = contactsRelationSecond;
    }



    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }
}
