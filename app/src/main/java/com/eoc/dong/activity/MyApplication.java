/**
 * Copyright (c) 2017, smuyyh@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */
package com.eoc.dong.activity;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.ChannelUuidResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.PickCityUtil;
import com.eoc.dong.common.language.LanguageUtil;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.utils.CommonUtil;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;

import java.util.Map;

import ai.advance.liveness.lib.GuardianLivenessDetectionSDK;
import ai.advance.liveness.lib.Market;
import retrofit2.Call;
import retrofit2.Response;


public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        /**
         * Erdong_vn_test
         * fe112b29c59a488493fd3ba508b77ce2
         * Erdong_and
         */
        ARouter.init(this);
        PickCityUtil.initPickView(context);

        /**
         * 对于7.0以下，需要在Application创建的时候进行语言切换
         */
        String language = MyApplication.getString("lg", "vn");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(this, language);
        }

        /**
         * access key:
         * secret key:
         *
         */
        //GuardianLivenessDetectionSDK.init(this, "活体检测的 access key值", "6b9c693de8087749", Market.Vietnam);
         GuardianLivenessDetectionSDK.init(this, "f9520b77c7ff1dd5", "f386ee606e5168d3", Market.Vietnam);
        FacebookSdk.setIsDebugEnabled(false);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        initAppsFlyer();
    }


    private void initAppsFlyer() {
        AppsFlyerConversionListener conversionDataListener = new AppsFlyerConversionListener() {

            @Override
            public void onInstallConversionDataLoaded(Map<String, String> map) {

                String is_first_launch = map.get("is_first_launch");
                Boolean value = Boolean.valueOf(is_first_launch);
                if (value){
                    String media_source = map.get("media_source");
                    if (CommonUtil.isNotEmpty(media_source)){
                       postData(media_source);
                    }else {
                        postData(media_source);
                    }
                }

            }

            @Override
            public void onInstallConversionFailure(String s) {

            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {


            }

            @Override
            public void onAttributionFailure(String s) {

            }
        };
        AppsFlyerLib.getInstance().registerConversionListener(this, conversionDataListener);
        AppsFlyerLib.getInstance().init("iZikpo5mccMLMNcNpMv3TJ", conversionDataListener, getApplicationContext());
        AppsFlyerLib.getInstance().setImeiData(getIMEI(context));
        //AppsFlyerLib.getInstance().setDebugLog(true);
        AppsFlyerLib.getInstance().startTracking(this);


    }

    public static final String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            String imei = telephonyManager.getDeviceId();
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }



    private void postData(String channelName) {
        RetrofitHelper.getRetrofit().create(Api.class).getChannelUuid(channelName).enqueue(new BaseCallBack<BaseResponse<ChannelUuidResponse>>() {
            @Override
            public void onSuccess(Call<BaseResponse<ChannelUuidResponse>> call, Response<BaseResponse<ChannelUuidResponse>> response) {
                if (response.body().isSuccess()){
                    ChannelUuidResponse data = response.body().getData();
                    if (data!=null){
                        String uuid = data.getUuid();
                        MyApplication.saveString("channelApkUuid",uuid);
                    }
                }else {
                    //CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }
//
private void test(String channelName) {
    RetrofitHelper.getRetrofit().create(Api.class).getChannelTest(channelName).enqueue(new BaseCallBack<BaseResponse>() {
        @Override
        public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
            if (response.body().isSuccess()){
                //Object data = response.body().getData();
                // CommonUtil.showToast("dff");
            }else {
                //CommonUtil.showToast(response.body().getMsg());
            }
        }
    });
}




    public static Context getContext(){
        return context;
    }



    public static SharedPreferences getPreferences() {
        return getContext().getSharedPreferences("data", MODE_PRIVATE);
    }

    public static String getString(String key, String defValue) {

        return getPreferences().getString(key, defValue);
    }

    public static Boolean getBoolean(String key, Boolean defValue) {

        return getPreferences().getBoolean(key, defValue);
    }
    public static int getInt(String key, int defValue) {

        return getPreferences().getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {

        return getPreferences().getLong(key, defValue);
    }

    /**
     * 保存字符串变量
     */
    public static void saveString( String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }
    public static void saveBoolean( String key, Boolean value) {
        getPreferences().edit().putBoolean(key, value).commit();
    }

    public static void saveInt( String key, int value) {
        getPreferences().edit().putInt(key, value).commit();
    }

    public static void saveLong( String key, long value) {
        getPreferences().edit().putLong(key, value).commit();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
