package com.eoc.dong.common.network.response;

import java.util.List;

/**
 * 杭州融科网络
 * 刘燕创建 on 2018/12/14.
 * 描述：
 */
public class OrderStatusResponse {


        /**
         * bankNum : null
         * isPay : 0
         * money : null
         * orderId : null
         * authStatus : null
         * bankName : null
         */

        public Object bankNum;
        public int isPay;
        public Object money;
        public Object orderId;
        public String authStatus;
        public String TKB90;
        public List<PictureResponse> picture;
        public Object bankName;


        public class PictureResponse {
                public String picture;
                public String url;
        }
}

