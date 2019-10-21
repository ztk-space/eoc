package com.eoc.dong.common.network.response;

/**
 * 杭州融科网络
 * 刘燕创建 on 2019/6/27.
 * 描述：
 */
public class MoneyOrderResponse {


        /**
         * address : 呵呵哈哈哈
         * occupation : 好纠结
         * productId : 1
         * gmtDatetime : 2019-06-26 16:23:03
         * passTime : null
         * financialProductVo : {"limiteMoney":null,"rate":15,"gmtDatetime":null,"name":"测试","logo":null,"term":70,"id":null,"status":null}
         * dateEnd : null
         * userName : 哈哈哈
         * idNo : 41444556
         * userId : 17
         * productName : null
         * money : 1000.0
         * dateStart : null
         * phone : 1866666885
         * company : 好好好
         * id : 1
         * status : 1
         */

        public String address;
        public String occupation;
        public int productId;
        public String gmtDatetime;
        public Object passTime;
        public FinancialProductVoBean financialProductVo;
        public Object dateEnd;
        public String userName;
        public String idNo;
        public int userId;
        public Object productName;
        public double money;
        public Object dateStart;
        public String phone;
        public String company;
        public int id;
        public int status;

        public static class FinancialProductVoBean {
            /**
             * limiteMoney : null
             * rate : 15.0
             * gmtDatetime : null
             * name : 测试
             * logo : null
             * term : 70
             * id : null
             * status : null
             */

            public Object limiteMoney;
            public double rate;
            public Object gmtDatetime;
            public String name;
            public Object logo;
            public int term;
            public Object id;
            public Object status;
        }
    }

