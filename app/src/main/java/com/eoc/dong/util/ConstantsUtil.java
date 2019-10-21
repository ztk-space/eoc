package com.eoc.dong.util;

/**
 * Created by Administrator on 2017/11/21.
 */

public class ConstantsUtil {
    /**
     * RxBus相关
     */
    public static final int RX_LOAN_DETAIL = 1;
    public static final int RX_HOME_FRAGMENT_AND_MAIN_ACTIVITY = 2;

    public static final int RX_ACTION_REFRESH = 1;
    public static final int RX_ACTION_COMPLETE = 2;


   // public static final String BASE_URL = "http://192.168.0.51:8080/";//线下
    public static final String BASE_URL = "http://147.139.133.108/";

    //客户电话
    public static final String SERVICE_PHONE = "123456789";
    //官方微信
    public static final String SERVICE_WEIXIN = "lr698524520";
    //连连支付商户私钥 商户通过openssl工具生成公私钥，公钥通过商户站上传，私钥用于加签，替换下面的值
    public static final String BUSINESS_PRIVATE_KEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOit5leFUqGJ9LGCCxQzkqNwSbORZdkBdm+LDGfYXpij5AJFujTmnGPJvxWJ8Z1kliPCmJbJ2z9vLuESrUFS5aQD3x8WuANCbeoRg0JOpOcveR0b+av0DjnGCVmdvhokKuL9xtgnOpoP1dY5D/zVyJZndVD0lEbbYeRTvzcSAQCXAgMBAAECgYBPOsfwjWlxsDQuLYXwPw9zr8yo5J21oxXdLY/v3HF5sHT3gR7C3LPsFsqGXw5y9claK+0hi2Ko2wcvhNiSIdQnuyJ1iMJNOKSB5rwDO7aqxJYONncj08zG8T8TZmfzSlHdCfmL9wg++Rn/qbL/S7ZvuDP9UqHQh4zayYkyPkf1YQJBAPWCwypTZlzdMANrGJ7f4Y/l1eFXYOgG/sTl+nRnF+rX1MiEXz/koOd9/jhZ30mDo05CEVOM0Bj9j4UIiTUcxVUCQQDynst7bDURabG2YjNq6xRFCDXgxd/qHH2ENlx7qt6XDpm0VdBm/UB+0+llrT95BeiDqO33lKp1JjhgW2npQW47AkEAw2UZ7RLJUjlb4CyZQMDQJZV3fsvrPV9r3stmZMQ0Hd0+5YOsxw4wHW2CKdklQ7339+3PGl7ktC8BzZA4m0PVrQJAB3plgzRenl7Hn3t/YjhEs6wBHSB8OlcOd1jFmPo3SiilDC3o8oOzzTTy6LNiVXSdKhG24/6tEldPPYkTxqDBqQJBALXWfAbUPbsQG6Xawlcm8RfK06QEmqyT7m0el63fYp5rIenkL4KWliHwb2b/zBELNFdR+H9DVffvUGg/a66xSlg=";
    //连连支付商户号（商户需要替换自己的商户号
    public static final String OID_PARTNER = "201803220001660891";
    //连连支付回调地址
    public static final String LIANLIAN_CALLBACK = "http://app.jsbaitu.com/api/lianpay/registerPayNotifyBack";
    //有盾认证异步通知接口地址
    public static final String YOUDUN_AUTH_CALLBACK_URL = BASE_URL+"loan/AuthIdentity_youDunCallBack.html";
    //有盾认证密钥必传已更换为千里马
    public static final String YOUDUN_KEY = "96620fe5-0f21-48ae-85f1-67db55613682";
    //数据魔盒合作方标识
    public static final String PARTNER_CODE = "mlb_mohe";
    //数据魔盒合作方密钥
    public static final String PARTNER_KEY  = "2f0c8a5b1d8e45eca20a150abe4c10d5";
    //数据魔盒channelCode
    public static final String PARTNER_CHANNEL_CODE = "005003";
    //微信App Id
    public static final String WX_APP_ID = "wx49813c36a713fa39";

    /**
     * @xml commonlibrary colors.xml
     * <color name="color_btn_blue">#09BFFE</color>
     * <color name="color_font_primary">#09BFFE</color>
     * <color name="color_font_white">#FFFFFF</color>
     * <color name="color_txt_for_btn_blue">#09BFFE</color>
     */

    /**
     * @Gradle app:build.gradle
     * defaultConfig:manifestPlaceholders:JPUSH_APPKEY
     */
}
