package com.eoc.dong.activity.certification;

/**
 * Created by CodeFatCat on 2019/4/26
 */
public class VnZone {
    private String id;
    private String name;

    public VnZone(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
