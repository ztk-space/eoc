package com.eoc.dong;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.gms.analytics.CampaignTrackingReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CodeFatCat on 2019/5/29
 * -1
 * 05-29 11:30:21.126 32229-32229/com.yndk.yndk I/dfdfdferercvnb: onReceive: 收到广播的回调mAction=com.android.vending.INSTALL_REFERRER
 * 05-29 11:30:21.126 32229-32229/com.yndk.yndk I/dfdfdferercvnb: onReceive: 旧版获取渠道来源数据
 * 05-29 11:30:21.126 32229-32229/com.yndk.yndk I/dfdfdferercvnb: getInstallReferrerData: referrer:utm_source=testSource
 * 05-29 11:30:21.126 32229-32229/com.yndk.yndk I/dfdfdferercvnb: upLoadinstallReferrer: object{"utm_source":"testSource"}
 */
public class ChannelReceiver extends BroadcastReceiver {
    public static final String TAG = "dfdfdferercvnb";
    private InstallReferrerClient mReferrerClient;
    private Context context;
    private Intent intent;


//
    //    private void postData(String channelName) {
//        RetrofitHelper.getRetrofit().create(UserApi.class).getChannelUuid(channelName).enqueue(new BaseCallBack<BaseResponse<ChannelUuidResponse>>() {
//            @Override
//            public void onSuccess(Call<BaseResponse<ChannelUuidResponse>> call, Response<BaseResponse<ChannelUuidResponse>> response) {
//                if (response.body().isSuccess()){
//                    ChannelUuidResponse data = response.body().getData();
//                    if (data!=null){
//                        String uuid = data.getUuid();
//                        MyApplication.saveString("channelApkUuid",uuid);
//                    }
//                }else {
//                    //CommonUtil.showToast(response.body().getMsg());
//                }
//            }
//        });
//    }




    @Override
    public void onReceive(Context context, Intent intent) {
        int appVersionCode = getAppVersionCode(context);
        this.context = context;
        this.intent = intent;



        String mAction = intent.getAction();
        Log.i(TAG, "onReceive: 收到广播的回调mAction="+mAction);
        //Toast.makeText(context, ""+appVersionCode, Toast.LENGTH_SHORT).show();
        if (appVersionCode < 80837300) {
            Log.i(TAG, "onReceive: 旧版获取渠道来源数据"+appVersionCode);
            getInstallReferrerData();
        } else {
            Log.i(TAG, "onReceive: 新版获取渠道来源数据");
            getConnect();
        }


/*        JSONObject splitData = getSplitData("utm_source=firstSource&utm_medium=firstMedium&utm_term=firstTerm&utm_content=firstContent&utm_campaign=firstName");
        try {
            String utm_source = splitData.getString("utm_source");
            Log.i(TAG, "onReceive: utm_source"+utm_source);
            postData(utm_source);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


    }

    /**
     * Google Play版本<8.3.73时获取安装来源数据
     */
    @SuppressLint("MissingPermission")
    private void getInstallReferrerData() {
        Bundle extras = intent.getExtras();
        String referrer = "";
        if (extras != null) {
            referrer = extras.getString("referrer");
            Log.i(TAG, "getInstallReferrerData: referrer:"+referrer);
            // 格式：utm_source=testSource&utm_medium=testMedium&utm_term=testTerm&utm_content=11
            upLoadinstallReferrer(referrer);
            //Toast.makeText(context, referrer, Toast.LENGTH_SHORT).show();

        }
        new CampaignTrackingReceiver().onReceive(context, intent);//调用谷歌广播的方法
    }


    /**
     * 获取版本号
     *
     * @return Google Play应用的版本号
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo("com.yndk.yndk", 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 与谷歌商店建立连接
     */
    private void getConnect() {
        mReferrerClient = InstallReferrerClient.newBuilder(context).build();
        mReferrerClient.startConnection(installReferrerStateListener);
    }

    private InstallReferrerStateListener installReferrerStateListener = new InstallReferrerStateListener() {
        @Override
        public void onInstallReferrerSetupFinished(int responseCode) {
            switch (responseCode) {
                case InstallReferrerClient.InstallReferrerResponse.OK:
                    // Connection established
//                    Toast.makeText(context, "与谷歌商店连接成功", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "onInstallReferrerSetupFinished: 与谷歌商店连接成功");
                    getMessage();
                    break;
                case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                    // API not available on the current Play Store app
//                    Toast.makeText(context, "与谷歌商店连接失败", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "onInstallReferrerSetupFinished: 与谷歌商店连接失败败：API not available on the current Play Store app");
                    break;
                case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                    // Connection could not be established
                    Log.i(TAG, "onInstallReferrerSetupFinished: InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE");
//                    Toast.makeText(context, "与谷歌商店连接失败", Toast.LENGTH_LONG).show();
                    break;
            }
        }

        @Override
        public void onInstallReferrerServiceDisconnected() {
            // Try to restart the connection on the next request to
            // Google Play by calling the startConnection() method.
            Log.i(TAG, "onInstallReferrerServiceDisconnected: ");
            //重新连接
            getConnect();
        }
    };




    /**
     * Google Play版本>=8.3.73时获取安装来源数据
     */

    @SuppressLint("MissingPermission")
    private void getMessage() {
        try {
            ReferrerDetails response = mReferrerClient.getInstallReferrer();
            String installReferrer = response.getInstallReferrer();
            long referrerClickTimestampSeconds = response.getReferrerClickTimestampSeconds();
            installReferrer = installReferrer + "&" + "referrerClickTimestampSeconds=" + referrerClickTimestampSeconds;
            long installBeginTimestampSeconds = response.getInstallBeginTimestampSeconds();
            installReferrer = installReferrer + "&" + "installBeginTimestampSeconds=" + installBeginTimestampSeconds;

            upLoadinstallReferrer(installReferrer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        new CampaignTrackingReceiver().onReceive(context, intent);//调用谷歌广播的方法
    }


    /**
     * 上传数据到服务器
     * 05-29 11:41:53.905 5547-5547/? I/dfdfdferercvnb: getInstallReferrerData: referrer:utm_medium=testMedium-utm_term=testTerm-utm_content=11
     * 05-29 11:41:53.905 5547-5547/? I/dfdfdferercvnb: upLoadinstallReferrer: object{"utm_medium":"testMedium-utm_term"}
     */
    private void upLoadinstallReferrer(String referer) {
        JSONObject object = getSplitData(referer);
        Log.i(TAG, "upLoadinstallReferrer: object"+object);
        //下面做自己上传数据到服务端的操作，数据为object，要加参数自己加
        try {
            String utm_source = object.getString("utm_source");
            Log.i(TAG, "onReceive: utm_source"+utm_source);
            //postData(utm_source);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    /**
     * 把格式：utm_source=testSource&utm_medium=testMedium&utm_term=testTerm&utm_content=11
     * 这种格式的数据切割成key,value的形式并put进JSONObject对象，用于上传
     * utm_source=firstSource&utm_medium=firstMedium&utm_term=firstTerm&utm_content=firstContent&utm_campaign=firstName
     * @param referer
     * @return
     *
     *{"utm_content":"firstContent","utm_medium":"firstMedium","utm_term":"firstTerm","utm_campaign":"firstName","utm_source":"firstSource"}
    https://play.google.com/store/apps/details?id=com.yndk.yndk&referrer=utm_source%3DfirstSource%26utm_medium%3DfirstMedium%26utm_term%3DfirstTerm%26utm_content%3DfirstContent%26utm_campaign%3DfirstName
     */
    private JSONObject getSplitData(String referer) {
        JSONObject object = new JSONObject();
        for (String data : referer.split("&")) {
            String[] split = data.split("=");
            for (int i = 0; i < split.length; i++) {
                try {
                    object.put(split[0], split[1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

}
