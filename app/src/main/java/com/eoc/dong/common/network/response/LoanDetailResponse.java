package com.eoc.dong.common.network.response;

/**
 * Created by Administrator on 2017/11/30.
 */

public class LoanDetailResponse {
    /**
     * passTime : null
     * overdueDays : 0
     * wateMoney : 0.02
     * interestMoney : 500
     * overdueMoney : 0
     * loanOrder : {"id":11,"userId":5,"agreementId":14,"agreement":{"id":14,"content":null,"status":"使用","picUrl":"www.baidu.com","type":null},"contractId":"1712181330311324","user":null,"adminId":null,"admin":null,"userCouponId":1,"userCoupon":{"id":1,"userId":1,"user":null,"couponId":11,"coupon":null,"status":1,"saveMoney":5,"inviteePhone":null,"pastDatetime":"2017-12-11 00:00:00","userCount":null,"gmtDatetime":"2017-12-06 14:00:54","uptDatetime":"2017-12-06 14:00:56"},"paramSettingId":40,"paramSetting":{"id":40,"limitDays":7,"allowDays":0,"status":"使用","money":500,"placeServePercent":0.01,"msgAuthPercent":0.01,"riskServePercent":0,"riskPlanPercent":0,"interestPercent":0.02,"overduePercent":0.01,"gmtDatetime":null,"uptDateime":null,"firstReminderTime":3,"secondReminderTime":4},"extendNum":0,"limitDays":14,"giveStatus":"0","payStatus":null,"orderStatus":"待还款","lianPayNum":null,"lianRepayNum":null,"bankName":null,"bankCardNum":null,"orderNumber":"2017121813290001","noAgree":null,"repaymentNo":null,"refuseReason":null,"needPayMoney":500,"borrowMoney":1000,"realMoney":1280,"interestPrecent":null,"passTime":null,"giveTime":null,"realPayTime":null,"gmtDatetime":"2017-12-18 13:29:35","uptDateime":null,"limitPayTime":1514698175000,"channelProfit":0,"outMoneyUserId":null,"backMoneyUserId":null,"collectionStatus":null,"delay":null,"delays":null,"overTimeMoney":0}
     */

    private String passTime;
    private int overdueDays;
    private String wateMoney;
    private String interestMoney;
    private String overdueMoney;
    private String renewalMoney;
    private LoanOrderBean loanOrder;

    public String getRenewalMoney() {
        return renewalMoney;
    }

    public void setRenewalMoney(String renewalMoney) {
        this.renewalMoney = renewalMoney;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public String getWateMoney() {
        return wateMoney;
    }

    public void setWateMoney(String wateMoney) {
        this.wateMoney = wateMoney;
    }

    public String getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(String interestMoney) {
        this.interestMoney = interestMoney;
    }

    public String getOverdueMoney() {
        return overdueMoney;
    }

    public void setOverdueMoney(String overdueMoney) {
        this.overdueMoney = overdueMoney;
    }

    public LoanOrderBean getLoanOrder() {
        return loanOrder;
    }

    public void setLoanOrder(LoanOrderBean loanOrder) {
        this.loanOrder = loanOrder;
    }

    public static class LoanOrderBean {
        /**
         * id : 11
         * userId : 5
         * agreementId : 14
         * agreement : {"id":14,"content":null,"status":"使用","picUrl":"www.baidu.com","type":null}
         * contractId : 1712181330311324
         * user : null
         * adminId : null
         * admin : null
         * userCouponId : 1
         * userCoupon : {"id":1,"userId":1,"user":null,"couponId":11,"coupon":null,"status":1,"saveMoney":5,"inviteePhone":null,"pastDatetime":"2017-12-11 00:00:00","userCount":null,"gmtDatetime":"2017-12-06 14:00:54","uptDatetime":"2017-12-06 14:00:56"}
         * paramSettingId : 40
         * paramSetting : {"id":40,"limitDays":7,"allowDays":0,"status":"使用","money":500,"placeServePercent":0.01,"msgAuthPercent":0.01,"riskServePercent":0,"riskPlanPercent":0,"interestPercent":0.02,"overduePercent":0.01,"gmtDatetime":null,"uptDateime":null,"firstReminderTime":3,"secondReminderTime":4}
         * extendNum : 0
         * limitDays : 14
         * giveStatus : 0
         * payStatus : null
         * orderStatus : 待还款
         * lianPayNum : null
         * lianRepayNum : null
         * bankName : null
         * bankCardNum : null
         * orderNumber : 2017121813290001
         * noAgree : null
         * repaymentNo : null
         * refuseReason : null
         * needPayMoney : 500
         * borrowMoney : 1000
         * realMoney : 1280
         * interestPrecent : null
         * passTime : null
         * giveTime : null
         * realPayTime : null
         * gmtDatetime : 2017-12-18 13:29:35
         * uptDateime : null
         * limitPayTime : 1514698175000
         * channelProfit : 0
         * outMoneyUserId : null
         * backMoneyUserId : null
         * collectionStatus : null
         * delay : null
         * delays : null
         * overTimeMoney : 0
         */

        private int id;
        private int userId;
        private int agreementId;
        private AgreementBean agreement;
        private String contractId;
        private Object user;
        private Object adminId;
        private Object admin;
        private int userCouponId;
        private UserCouponBean userCoupon;
        private int paramSettingId;
        private ParamSettingBean paramSetting;
        private int extendNum;
        private int limitDays;
        private String giveStatus;
        private Object payStatus;
        private String orderStatus;
        private Object lianPayNum;
        private Object lianRepayNum;
        private Object bankName;
        private String bankCardNum;
        private String orderNumber;
        private Object noAgree;
        private Object repaymentNo;
        private Object refuseReason;
        private String needPayMoney;
        private String borrowMoney;
        private String realMoney;
        private Object interestPrecent;
        private Object passTime;
        private String giveTime;
        private String realPayTime;
        private String gmtDatetime;
        private Object uptDateime;
        private String limitPayTime;
        private int channelProfit;
        private Object outMoneyUserId;
        private Object backMoneyUserId;
        private Object collectionStatus;
        private Object delay;
        private Object delays;
        private int overTimeMoney;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAgreementId() {
            return agreementId;
        }

        public void setAgreementId(int agreementId) {
            this.agreementId = agreementId;
        }

        public AgreementBean getAgreement() {
            return agreement;
        }

        public void setAgreement(AgreementBean agreement) {
            this.agreement = agreement;
        }

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
            this.contractId = contractId;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public Object getAdminId() {
            return adminId;
        }

        public void setAdminId(Object adminId) {
            this.adminId = adminId;
        }

        public Object getAdmin() {
            return admin;
        }

        public void setAdmin(Object admin) {
            this.admin = admin;
        }

        public int getUserCouponId() {
            return userCouponId;
        }

        public void setUserCouponId(int userCouponId) {
            this.userCouponId = userCouponId;
        }

        public UserCouponBean getUserCoupon() {
            return userCoupon;
        }

        public void setUserCoupon(UserCouponBean userCoupon) {
            this.userCoupon = userCoupon;
        }

        public int getParamSettingId() {
            return paramSettingId;
        }

        public void setParamSettingId(int paramSettingId) {
            this.paramSettingId = paramSettingId;
        }

        public ParamSettingBean getParamSetting() {
            return paramSetting;
        }

        public void setParamSetting(ParamSettingBean paramSetting) {
            this.paramSetting = paramSetting;
        }

        public int getExtendNum() {
            return extendNum;
        }

        public void setExtendNum(int extendNum) {
            this.extendNum = extendNum;
        }

        public int getLimitDays() {
            return limitDays;
        }

        public void setLimitDays(int limitDays) {
            this.limitDays = limitDays;
        }

        public String getGiveStatus() {
            return giveStatus;
        }

        public void setGiveStatus(String giveStatus) {
            this.giveStatus = giveStatus;
        }

        public Object getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(Object payStatus) {
            this.payStatus = payStatus;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Object getLianPayNum() {
            return lianPayNum;
        }

        public void setLianPayNum(Object lianPayNum) {
            this.lianPayNum = lianPayNum;
        }

        public Object getLianRepayNum() {
            return lianRepayNum;
        }

        public void setLianRepayNum(Object lianRepayNum) {
            this.lianRepayNum = lianRepayNum;
        }

        public Object getBankName() {
            return bankName;
        }

        public void setBankName(Object bankName) {
            this.bankName = bankName;
        }

        public String getBankCardNum() {
            return bankCardNum;
        }

        public void setBankCardNum(String bankCardNum) {
            this.bankCardNum = bankCardNum;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public Object getNoAgree() {
            return noAgree;
        }

        public void setNoAgree(Object noAgree) {
            this.noAgree = noAgree;
        }

        public Object getRepaymentNo() {
            return repaymentNo;
        }

        public void setRepaymentNo(Object repaymentNo) {
            this.repaymentNo = repaymentNo;
        }

        public Object getRefuseReason() {
            return refuseReason;
        }

        public void setRefuseReason(Object refuseReason) {
            this.refuseReason = refuseReason;
        }

        public String getNeedPayMoney() {
            return needPayMoney;
        }

        public void setNeedPayMoney(String needPayMoney) {
            this.needPayMoney = needPayMoney;
        }

        public String getBorrowMoney() {
            return borrowMoney;
        }

        public void setBorrowMoney(String borrowMoney) {
            this.borrowMoney = borrowMoney;
        }

        public String getRealMoney() {
            return realMoney;
        }

        public void setRealMoney(String realMoney) {
            this.realMoney = realMoney;
        }

        public Object getInterestPrecent() {
            return interestPrecent;
        }

        public void setInterestPrecent(Object interestPrecent) {
            this.interestPrecent = interestPrecent;
        }

        public Object getPassTime() {
            return passTime;
        }

        public void setPassTime(Object passTime) {
            this.passTime = passTime;
        }

        public String getGiveTime() {
            return giveTime;
        }

        public void setGiveTime(String giveTime) {
            this.giveTime = giveTime;
        }

        public String getRealPayTime() {
            return realPayTime;
        }

        public void setRealPayTime(String realPayTime) {
            this.realPayTime = realPayTime;
        }

        public String getGmtDatetime() {
            return gmtDatetime;
        }

        public void setGmtDatetime(String gmtDatetime) {
            this.gmtDatetime = gmtDatetime;
        }

        public Object getUptDateime() {
            return uptDateime;
        }

        public void setUptDateime(Object uptDateime) {
            this.uptDateime = uptDateime;
        }

        public String getLimitPayTime() {
            return limitPayTime;
        }

        public void setLimitPayTime(String limitPayTime) {
            this.limitPayTime = limitPayTime;
        }

        public int getChannelProfit() {
            return channelProfit;
        }

        public void setChannelProfit(int channelProfit) {
            this.channelProfit = channelProfit;
        }

        public Object getOutMoneyUserId() {
            return outMoneyUserId;
        }

        public void setOutMoneyUserId(Object outMoneyUserId) {
            this.outMoneyUserId = outMoneyUserId;
        }

        public Object getBackMoneyUserId() {
            return backMoneyUserId;
        }

        public void setBackMoneyUserId(Object backMoneyUserId) {
            this.backMoneyUserId = backMoneyUserId;
        }

        public Object getCollectionStatus() {
            return collectionStatus;
        }

        public void setCollectionStatus(Object collectionStatus) {
            this.collectionStatus = collectionStatus;
        }

        public Object getDelay() {
            return delay;
        }

        public void setDelay(Object delay) {
            this.delay = delay;
        }

        public Object getDelays() {
            return delays;
        }

        public void setDelays(Object delays) {
            this.delays = delays;
        }

        public int getOverTimeMoney() {
            return overTimeMoney;
        }

        public void setOverTimeMoney(int overTimeMoney) {
            this.overTimeMoney = overTimeMoney;
        }

        public static class AgreementBean {
            /**
             * id : 14
             * content : null
             * status : 使用
             * picUrl : www.baidu.com
             * type : null
             */

            private int id;
            private Object content;
            private String status;
            private String picUrl;
            private Object type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }
        }

        public static class UserCouponBean {
            /**
             * id : 1
             * userId : 1
             * user : null
             * couponId : 11
             * coupon : null
             * status : 1
             * saveMoney : 5
             * inviteePhone : null
             * pastDatetime : 2017-12-11 00:00:00
             * userCount : null
             * gmtDatetime : 2017-12-06 14:00:54
             * uptDatetime : 2017-12-06 14:00:56
             */

            private int id;
            private int userId;
            private Object user;
            private int couponId;
            private Object coupon;
            private int status;
            private int saveMoney;
            private Object inviteePhone;
            private String pastDatetime;
            private Object userCount;
            private String gmtDatetime;
            private String uptDatetime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getUser() {
                return user;
            }

            public void setUser(Object user) {
                this.user = user;
            }

            public int getCouponId() {
                return couponId;
            }

            public void setCouponId(int couponId) {
                this.couponId = couponId;
            }

            public Object getCoupon() {
                return coupon;
            }

            public void setCoupon(Object coupon) {
                this.coupon = coupon;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSaveMoney() {
                return saveMoney;
            }

            public void setSaveMoney(int saveMoney) {
                this.saveMoney = saveMoney;
            }

            public Object getInviteePhone() {
                return inviteePhone;
            }

            public void setInviteePhone(Object inviteePhone) {
                this.inviteePhone = inviteePhone;
            }

            public String getPastDatetime() {
                return pastDatetime;
            }

            public void setPastDatetime(String pastDatetime) {
                this.pastDatetime = pastDatetime;
            }

            public Object getUserCount() {
                return userCount;
            }

            public void setUserCount(Object userCount) {
                this.userCount = userCount;
            }

            public String getGmtDatetime() {
                return gmtDatetime;
            }

            public void setGmtDatetime(String gmtDatetime) {
                this.gmtDatetime = gmtDatetime;
            }

            public String getUptDatetime() {
                return uptDatetime;
            }

            public void setUptDatetime(String uptDatetime) {
                this.uptDatetime = uptDatetime;
            }
        }

        public static class ParamSettingBean {
            /**
             * id : 40
             * limitDays : 7
             * allowDays : 0
             * status : 使用
             * money : 500
             * placeServePercent : 0.01
             * msgAuthPercent : 0.01
             * riskServePercent : 0
             * riskPlanPercent : 0
             * interestPercent : 0.02
             * overduePercent : 0.01
             * gmtDatetime : null
             * uptDateime : null
             * firstReminderTime : 3
             * secondReminderTime : 4
             */

            private int id;
            private int limitDays;
            private int allowDays;
            private String status;
            private int money;
            private double placeServePercent;
            private double msgAuthPercent;
            private int riskServePercent;
            private int riskPlanPercent;
            private double interestPercent;
            private double overduePercent;
            private Object gmtDatetime;
            private Object uptDateime;
            private int firstReminderTime;
            private int secondReminderTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLimitDays() {
                return limitDays;
            }

            public void setLimitDays(int limitDays) {
                this.limitDays = limitDays;
            }

            public int getAllowDays() {
                return allowDays;
            }

            public void setAllowDays(int allowDays) {
                this.allowDays = allowDays;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public double getPlaceServePercent() {
                return placeServePercent;
            }

            public void setPlaceServePercent(double placeServePercent) {
                this.placeServePercent = placeServePercent;
            }

            public double getMsgAuthPercent() {
                return msgAuthPercent;
            }

            public void setMsgAuthPercent(double msgAuthPercent) {
                this.msgAuthPercent = msgAuthPercent;
            }

            public int getRiskServePercent() {
                return riskServePercent;
            }

            public void setRiskServePercent(int riskServePercent) {
                this.riskServePercent = riskServePercent;
            }

            public int getRiskPlanPercent() {
                return riskPlanPercent;
            }

            public void setRiskPlanPercent(int riskPlanPercent) {
                this.riskPlanPercent = riskPlanPercent;
            }

            public double getInterestPercent() {
                return interestPercent;
            }

            public void setInterestPercent(double interestPercent) {
                this.interestPercent = interestPercent;
            }

            public double getOverduePercent() {
                return overduePercent;
            }

            public void setOverduePercent(double overduePercent) {
                this.overduePercent = overduePercent;
            }

            public Object getGmtDatetime() {
                return gmtDatetime;
            }

            public void setGmtDatetime(Object gmtDatetime) {
                this.gmtDatetime = gmtDatetime;
            }

            public Object getUptDateime() {
                return uptDateime;
            }

            public void setUptDateime(Object uptDateime) {
                this.uptDateime = uptDateime;
            }

            public int getFirstReminderTime() {
                return firstReminderTime;
            }

            public void setFirstReminderTime(int firstReminderTime) {
                this.firstReminderTime = firstReminderTime;
            }

            public int getSecondReminderTime() {
                return secondReminderTime;
            }

            public void setSecondReminderTime(int secondReminderTime) {
                this.secondReminderTime = secondReminderTime;
            }
        }
    }


//    private String orderNumber;         //订单号
//    private String bankCardNum;         //银行卡号
//    private String bankName;            //银行卡名称
//    private String limitDays;           //租赁期限（天）
//    private String borrowMoney;         //租赁金额
//    private String realMoney;           //到账金额
//    private String interestMoney;       //利息
//    private String interestPrecent;     //利率
//    private String placeServeMoney;     //平台服务费
//    private String msgAuthMoney;        //信息认证费
//    private String riskServeMoney;      //风控服务费
//    private String riskPlanMoney;       //风险准备金
//    private String wateMoney;           //综合费用
//    private String saveMoney;           //优惠卷节省金额
//    private String needPayMoney;        //应还金额
//    private String realPayMoney;        //实还金额
//    private String gmtDatetime;         //租赁时间
//    private String passTime;            //审核通过时间
//    private String giveTime;            //打款时间
//    private String limitPayTime;        //应还款时间
//    private String overdueTime;         //逾期时间
//    private String realPayTime;         //实际还款时间
//    private String overdueDays;         //逾期天数
//    private String allowDays;           //容限期
//    private String overdueMoney;        //逾期金额
//    private String allowMoney;          //容限期费用
//    private int orderStatus;            //订单状态
//    private String agreementUrl;        //租赁协议
//    private String agreementTwoUrl;
//
//    public String getOrderNumber() {
//        return orderNumber;
//    }
//
//    public String getBankCardNum() {
//        return bankCardNum;
//    }
//
//    public String getBankName() {
//        return bankName;
//    }
//
//    public String getLimitDays() {
//        return limitDays;
//    }
//
//    public String getBorrowMoney() {
//        return borrowMoney;
//    }
//
//    public String getRealMoney() {
//        return realMoney;
//    }
//
//    public String getInterestMoney() {
//        return interestMoney;
//    }
//
//    public String getInterestPrecent() {
//        return interestPrecent;
//    }
//
//    public String getPlaceServeMoney() {
//        return placeServeMoney;
//    }
//
//    public String getMsgAuthMoney() {
//        return msgAuthMoney;
//    }
//
//    public String getRiskServeMoney() {
//        return riskServeMoney;
//    }
//
//    public String getRiskPlanMoney() {
//        return riskPlanMoney;
//    }
//
//    public String getWateMoney() {
//        return wateMoney;
//    }
//
//    public String getSaveMoney() {
//        return saveMoney;
//    }
//
//    public String getNeedPayMoney() {
//        return needPayMoney;
//    }
//
//    public String getRealPayMoney() {
//        return realPayMoney;
//    }
//
//    public String getGmtDatetime() {
//        return gmtDatetime;
//    }
//
//    public String getPassTime() {
//        return passTime;
//    }
//
//    public String getGiveTime() {
//        return giveTime;
//    }
//
//    public String getLimitPayTime() {
//        return limitPayTime;
//    }
//
//    public String getOverdueTime() {
//        return overdueTime;
//    }
//
//    public String getRealPayTime() {
//        return realPayTime;
//    }
//
//    public String getOverdueDays() {
//        return overdueDays;
//    }
//
//    public String getAllowDays() {
//        return allowDays;
//    }
//
//    public String getOverdueMoney() {
//        return overdueMoney;
//    }
//
//    public String getAllowMoney() {
//        return allowMoney;
//    }
//
//    public int getOrderStatus() {
//        return orderStatus;
//    }
//
//    public String getAgreementUrl() {
//        return agreementUrl;
//    }
//
//    public String getAgreementTwoUrl() {
//        return agreementTwoUrl;
//    }
}
