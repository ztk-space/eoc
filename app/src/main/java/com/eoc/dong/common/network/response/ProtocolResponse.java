package com.eoc.dong.common.network.response;

/**
 * 杭州融科网络
 * 刘燕创建 on 2018/5/8.
 * 描述：
 */
public class ProtocolResponse {


        public int id;
        public String type;
        public String status;
        public String content;
        public Object picUrl;
        public Object uptDatetime;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public Object getPicUrl() {
                return picUrl;
        }

        public void setPicUrl(Object picUrl) {
                this.picUrl = picUrl;
        }



        public Object getUptDatetime() {
                return uptDatetime;
        }

        public void setUptDatetime(Object uptDatetime) {
                this.uptDatetime = uptDatetime;
        }
}

