package com.eoc.dong.common.network.response;

import java.math.BigDecimal;

/**
 * 杭州融科网络
 * 刘燕创建 on 2018/7/2.
 * 描述：
 */
public class OrderRecordResponse {




        public int id;
        public int userId;
        public Object user;
        public int bankCardId;
        public int adminId;
        public Object admin;
        public int auditorId;
        public Object auditor;
        public BankCardBean bankCard;
        public int paramSettingId;
        public ParamSettingBean paramSetting;
        public Object userCouponId;
        public Object userCoupon;
        public String name;
        public int type;
        public Object failReason;
        public Object batchNo;
        public Object blackBox;
        public BigDecimal evaluationPrice;
        public double finalMoney;
        public double rentMoney;
        public Object refuseReason;
        public String protocolId;
        public double payMoney;
        public Object assignDatetime;
        public String gmtDatetime;
        public Object uptDatetime;
        public String status;

        public static class BankCardBean {


            public int id;
            public int userId;
            public Object protocolId;
            public String idCardNo;
            public String bankCardNo;
            public String status;
            public String phone;
            public String bankCardName;
            public String bankCardCode;
            public String name;
            public String gmtDatetime;
            public Object uptDatetime;
        }

        public static class ParamSettingBean {
            /**
             * id : 52
             * assessMoney : 20
             * cashPledge : 0
             * rentDayMoney : 0.035
             * decreaseDay : null
             * decreaseproPortion : null
             * leastRentDayMoney : null
             * overtimeDay : 3
             * punishmentOvertimeDay : 5
             * overtimeMoney : 0.001
             * status : 搴熷純
             * punishmentOvertimeMoney : 0.003
             */

            public int id;
            public String assessMoney;
            public String cashPledge;
            public String rentDayMoney;
            public Object decreaseDay;
            public Object decreaseproPortion;
            public Object leastRentDayMoney;
            public String overtimeDay;
            public String punishmentOvertimeDay;
            public String overtimeMoney;
            public String status;
            public String punishmentOvertimeMoney;
        }
    }

