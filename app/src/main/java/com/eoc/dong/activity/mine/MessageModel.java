package com.eoc.dong.activity.mine;

/**
 * Created by Administrator on 2017/11/24.
 */

public class MessageModel {
    private int id;
    private String title;
    private String content;
    private String time;

    public MessageModel(int id, String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.time=time;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}
