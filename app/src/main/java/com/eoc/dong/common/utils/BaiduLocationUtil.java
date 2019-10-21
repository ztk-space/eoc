package com.eoc.dong.common.utils;

import android.Manifest;
import android.app.Activity;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.tbruyelle.rxpermissions.RxPermissions;

/**
 * Created by Administrator on 2017/12/28.
 * 百度地图工具类
 */

public class BaiduLocationUtil {
    private final String[] NEED_PERMISSION = new String[]{Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private Activity context;
    private LocationClient mLocationClient = null;
    private LocationListener listener;

    public BaiduLocationUtil(Activity context) {
        this.context = context;
    }

    public void getLocation(){
        checkPermission();
    }

    private void checkPermission() {
        new RxPermissions(context).request(NEED_PERMISSION).subscribe(granted -> {
            if (granted){
                startLocation();
            }else{
                CommonUtil.showToast("请允许获取定位权限");
            }
        });
    }

    private void startLocation() {
        //配置LocationClien，需要获取具体地址信息
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);

        //官方推荐使用Application的context
        mLocationClient = new LocationClient(context.getApplicationContext());
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(bdLocation -> {
            if (listener!=null){
                String province = bdLocation.getProvince(); //省份
                String city = bdLocation.getCity();         //城市
                String district = bdLocation.getDistrict(); //区县
                String street = bdLocation.getStreet();     //街道
                listener.getLocationSuccess(province,city,district,street);
            }
        });
        mLocationClient.start();
    }

    public void setLocationListener(LocationListener listener) {
        this.listener = listener;
    }

    public interface LocationListener{
        void getLocationSuccess(String province,String city,String district,String street);
    }
}


