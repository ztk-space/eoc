package com.eoc.dong.common.utils;


import android.content.Context;
import android.content.Intent;

import com.eoc.dong.activity.certification.AuthenticationActivity;
import com.eoc.dong.activity.certification.BankCardCertificationActivity;
import com.eoc.dong.activity.certification.BasicInformationOneActivity;
import com.eoc.dong.activity.login.ForgetPasswordActivity;
import com.eoc.dong.activity.login.LoginActivity;
import com.eoc.dong.activity.main.MainActivity;
import com.eoc.dong.activity.mine.AddMoneyActivity;
import com.eoc.dong.activity.mine.ChangePasswordActivity;
import com.eoc.dong.activity.mine.ContactServiceActivity;
import com.eoc.dong.activity.mine.FeedbackActivity;
import com.eoc.dong.activity.mine.MessageActivity;
import com.eoc.dong.activity.order.MyOrderActivity;
import com.eoc.dong.activity.order.OrderDetailsActivity;
import com.eoc.dong.activity.order.PlaceanOrderActivity;
import com.eoc.dong.activity.order.RenewalActivity;
import com.eoc.dong.activity.order.RepaymentActivity;
import com.eoc.dong.activity.order.WebPayActivity;
import com.eoc.dong.activity.register.RegisterActivity;
import com.eoc.dong.activity.set.SettingActivity;

/**
 * Created by Administrator on 2017/11/21.
 */

public class UIHelper {
    /**
     * 登录页面(登出)
     */
    public static void gotoLoginActivityWithLogOut(Context mContext){
        Intent intent=new Intent(mContext, MainActivity.class);
            intent.putExtra("logout",true);
        mContext.startActivity(intent);
    }
    //跳转到主页面
    public static void gotoMainActivityWithAuth(Context mContext){
        Intent intent=new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
//    //跳转到主页面
//    public static void gotoMainActivityWithAuth1(Context mContext){
//        Intent intent=new Intent(mContext, MainActivity.class);
//        intent.putExtra("logout",false);
//        mContext.startActivity(intent);
//    }
    //跳转到设置界面
    public static void gotoSettingActivity(Context mContext){
        Intent intent=new Intent(mContext, SettingActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到我的订单界面
    public static void gotoLoanRecordActivity(Context mContext){
        Intent intent=new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到消息界面
    public static void gotoMessageActivity(Context mContext){
        Intent intent=new Intent(mContext, MessageActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到关于我们界面
    public static void gotoContactServiceActivity(Context mContext){
        Intent intent=new Intent(mContext, ContactServiceActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到提额界面
    public static void gotoAddMoneyActivity(Context mContext,String money){
        Intent intent=new Intent(mContext, AddMoneyActivity.class);
        intent.putExtra("money",money);
        mContext.startActivity(intent);
    }
    //跳转到修改密码界面
    public static void gotoChangePasswordAvtivity(Context mContext) {
        Intent intent = new Intent(mContext, ChangePasswordActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到忘记密码界面
    public static void gotoForgetPasswordAvtivity(Context mContext) {
        Intent intent = new Intent(mContext, ForgetPasswordActivity.class);

        mContext.startActivity(intent);
    }
    //跳转到意见反馈界面
    public static void gotoFeedbackAvtivity(Context mContext) {
        Intent intent = new Intent(mContext, FeedbackActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到注册界面
    public static void gotoRegisterAvtivity(Context mContext) {
        Intent intent = new Intent(mContext, RegisterActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到登录界面
    public static void gotoLoginAvtivity(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到下订单界面
    public static void gotoPlaceanOrderActivity(Context mContext,int days,String money) {
        Intent intent = new Intent(mContext, PlaceanOrderActivity.class);
        intent.putExtra("days",days);
        intent.putExtra("money",money);
        mContext.startActivity(intent);
    }
    //跳转到订单界面
    public static void gotoMyOrderActivity(Context mContext) {
        Intent intent = new Intent(mContext, MyOrderActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到订单详情
    public static void gotoOrderDetailsActivity(Context mContext,int id) {
        Intent intent = new Intent(mContext, OrderDetailsActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }
    //跳转到续期页面
    public static void gotoRenewalActivity(Context mContext,int id) {
        Intent intent = new Intent(mContext, RenewalActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }
    //跳转到还款页面
    public static void gotoRepaymentActivity(Context mContext,String id,int money) {
        Intent intent = new Intent(mContext, RepaymentActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("money",money);
        mContext.startActivity(intent);
    }
    //直接支付跳转到还款界面
    public static void gotoRepaytActivity(Context mContext,int id,double money) {
        Intent intent = new Intent(mContext, RepaymentActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("money",money);
        mContext.startActivity(intent);
    }
    //跳转到个人认证页面
    public static void gotoBasicInformationOneActivity(Context mContext) {
        Intent intent = new Intent(mContext, BasicInformationOneActivity.class);
        mContext.startActivity(intent);
    }
    //跳转到银行卡认证页面
    public static void gotoBankCardCertificationActivity(Context mContext,int flag) {
        Intent intent = new Intent(mContext, BankCardCertificationActivity.class);
        intent.putExtra("flag",flag);
        mContext.startActivity(intent);
    }
    //跳转到身份认证页面
    public static void  gotoAuthenticationActivity(Context mContext) {
        Intent intent = new Intent(mContext, AuthenticationActivity.class);
        mContext.startActivity(intent);
    }
    //跳转支付Web页面
    public static void  gotoWebPayActivity(Context mContext,String url) {
        Intent intent = new Intent(mContext, WebPayActivity.class);
        intent.putExtra("url",url);
        mContext.startActivity(intent);
    }
    //跳转支付成功页面
    public static void  gotoRepaySuccessActivity(Context mContext) {
        Intent intent = new Intent(mContext, WebPayActivity.class);
        mContext.startActivity(intent);
    }
    //跳转支付成功页面
    public static void  gotoRepaySuccessActivity(Context mContext,boolean isRenewal) {
        Intent intent = new Intent(mContext, WebPayActivity.class);
        intent.putExtra("isRenewal",isRenewal);
        mContext.startActivity(intent);
    }
    }
