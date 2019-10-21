package com.eoc.dong.common.network.response;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/1/10.
 */

public class OrderListResponse {

        /**
         * bankNum : null
         * overdueNum : 0
         * limitDays : 7
         * paramSettingId : 1
         * agreementUrl : null
         * bankName : null
         * renewalNum : 0
         * realMoney : 800
         * financeName : null
         * collectionName : null
         * realPayTime : null
         * payType : 1
         * id : 1
         * overdueMoney : 0
         * auditorName : admin
         * allOverdueMoney : 0
         * orderNo : 654
         * disCollectTime : null
         * giveTime : null
         * needPayMoney : 1000
         * gmtDatetime : 2018-12-07 14:45:19
         * disAuditorTime : null
         * userId : 1
         * borrowMoney : 1000
         * auditTime : 2018-12-11 14:16:33
         * refuseType : null
         * reLoan : 1
         * realPayMoney : 1000
         * uptDatetime : null
         * disFinanceTime : null
         * limitPayTime : null
         * payStatus : 1
         * refuseReason : null
         * status : 1
         */

        public Object bankNum;
        public int overdueNum;
        public int limitDays;
        public int paramSettingId;
        public Object agreementUrl;
        public Object bankName;
        public int renewalNum;
        public BigDecimal realMoney;
        public Object financeName;
        public Object collectionName;
        public Object realPayTime;
        public int payType;
        public int id;
        public BigDecimal overdueMoney;
        public String auditorName;
        public BigDecimal allOverdueMoney;
        public String orderNo;
        public Object disCollectTime;
        public Object giveTime;
        public BigDecimal needPayMoney;
        public String gmtDatetime;
        public Object disAuditorTime;
        public int userId;
        public BigDecimal borrowMoney;
        public String auditTime;
        public Object refuseType;
        public int reLoan;
        public BigDecimal realPayMoney;
        public Object uptDatetime;
        public Object disFinanceTime;

        public int payStatus;
        public Object refuseReason;
        public int status;
    }

