package com.eoc.dong.common.network.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jh352160 on 2018/4/8.
 */
public class RentOrderShellResponse {


        /**
         * bindPayDay : null
         * paramSettingId : 1
         * agreementUrl : null
         * userPhone : null
         * returnStatus : null
         * renewalNum : 0
         * realMoney : 800
         * financeName : null
         * realPayTime : null
         * payType : null
         * id : 1501
         * orderIds : []
         * renewalMoney : null
         * orderNo : 686
         * disCollectTime : null
         * sysRate : {"interestPercent":0.3,"limitDays":7,"gmtDatetime":"2018-12-13 13:47:13","uptDatetime":"2018-12-13 13:47:18","id":1,"overduePercent":0.05,"status":1}
         * borrowMoney : 1000
         * auditTime : 2018-12-11 14:16:33
         * realPayMoney : null
         * uptDatetime : null
         * limitPayTime : null
         * status : 3
         * bankNum : null
         * overdueNum : 0
         * renewalDays : null
         * limitDays : 7
         * validateCode : null
         * bankName : null
         * bindPayMoney : null
         * dateEnd : null
         * collectionName : null
         * userVo : null
         * dateStart : null
         * bindPayType : null
         * overdueMoney : 0
         * auditorName : admin
         * allOverdueMoney : 0
         * giveTime : null
         * needPayMoney : 1500
         * gmtDatetime : 2018-12-06 14:44:19
         * disAuditorTime : null
         * requestNo : null
         * userName : null
         * userId : 13
         * yBorderId : null
         * refuseType : null
         * reLoan : null
         * auditStatus : null
         * disFinanceTime : null
         * payStatus : null
         * refuseReason : null
         */

        public Object bindPayDay;
        public int paramSettingId;
        public Object agreementUrl;
        public Object userPhone;
        public Object returnStatus;
        public int renewalNum;
        public BigDecimal realMoney;
        public BigDecimal interest;
        public Object financeName;
        public Object realPayTime;
        public Object payType;
        public int id;
        public Object renewalMoney;
        public String orderNo;
        public Object disCollectTime;
        public SysRateBean sysRate;
        public BigDecimal borrowMoney;
        public String auditTime;
        public Object realPayMoney;
        public Object uptDatetime;
        public String limitPayTime;
        public int status;
        public String bankNum;
        public int overdueNum;
        public Object renewalDays;
        public int limitDays;
        public Object validateCode;
        public Object bankName;
        public Object bindPayMoney;
        public Object dateEnd;
        public Object collectionName;
        public Object userVo;
        public Object dateStart;
        public Object bindPayType;
        public BigDecimal overdueMoney;
        public String auditorName;
        public BigDecimal allOverdueMoney;
        public Object giveTime;
        public BigDecimal needPayMoney;
        public String gmtDatetime;
        public Object disAuditorTime;
        public Object requestNo;
        public Object userName;
        public int userId;
        public Object yBorderId;
        public Object refuseType;
        public Object reLoan;
        public Object auditStatus;
        public Object disFinanceTime;
        public Object payStatus;
        public Object refuseReason;
        public List<?> orderIds;

        public static class SysRateBean {
            /**
             * interestPercent : 0.3
             * limitDays : 7
             * gmtDatetime : 2018-12-13 13:47:13
             * uptDatetime : 2018-12-13 13:47:18
             * id : 1
             * overduePercent : 0.05
             * status : 1
             */

            public double interestPercent;
            public int limitDays;
            public String gmtDatetime;
            public String uptDatetime;
            public int id;
            public double overduePercent;
            public int status;
        }
    }

