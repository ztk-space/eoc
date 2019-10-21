package com.eoc.dong.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eoc.dong.BuildConfig;
import com.eoc.dong.activity.MyApplication;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/1.
 * 描述：
 */

public class CommonUtil {
    private CommonUtil(){}

    public static boolean isTextViewEmpty(TextView textView) {
        return CommonUtils.isEmpty(textView.getText().toString());
    }

    public static boolean isEmpty(String input) {
        return CommonUtils.isEmpty(input);
    }

    public static boolean isNotEmpty(String input) {
        return CommonUtils.isNotEmpty(input);
    }

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 设置未读数
     */
    public static void setUnReadNum(TextView textView, int num) {
        textView.setText(Integer.toString(num));
        if (num > 99) {
            textView.setText("99+");
        }
        if (num > 0) {
            textView.setVisibility(View.VISIBLE);
            if (num > 10) {
                if (num < 100)
                    textView.setPadding(3, 3, 3, 3);
                else textView.setPadding(3, 10, 3, 10);
            } else textView.setPadding(10, 3, 10, 3);

        } else {
            textView.setVisibility(View.GONE);
        }
    }

    public static void setIndicatorwidth(final TabLayout tabs, final int leftDip, final int rightDip){
        tabs.post(() -> setIndicator(tabs,leftDip,rightDip));
    }

    private static void setIndicator(final TabLayout tabs, int leftDip, int rightDip){
        Class<?> tabLayout = tabs.getClass();
        //Field类：与Class类，Method类相似，在反射中使用，根据属性名取得属性
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (tabStrip == null) return;

        //关闭该属性的检查，否则该属性不可修改时会报错
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            //获取tabs下该属性的值
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (llTab == null) return;

        int left = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.
                    LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    public static void showToast(String s) {
        CommonUtils.showToast(MyApplication.getContext(), s);
    }

    /**
     * 将时间戳格式化为字符串格式
     */
    public static String getStringDate(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(time);
    }

    /**
     * 判断是否是手机号码
     */
    public static boolean isMobile(String phoneNumber) {
        if (CommonUtils.isEmpty(phoneNumber)) {
            CommonUtil.showToast("请输入手机号码");
            return false;
        }
        if (!phoneNumber.matches("1[0-9]{10}")) {
            CommonUtil.showToast("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * 自定义dialog全屏展示
     *
     * @param activity
     * @param dialog
     */
    public static void FullScreen(Activity activity, Dialog dialog, double scale) {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (d.getWidth() * scale);    //宽度设置为全屏
        dialog.getWindow().setAttributes(p);     //设置生效
    }

    /**
     * null检测方法，在调用接口前重要参数需要做非空判断，在DEBUG包中会弹出提示，以便定位问题
     */
    public static void checkNull(String valName,Object variable){
       if (BuildConfig.DEBUG) CommonUtil.showToast(String.format("%s is null"));
    }

    /**
     * 获取手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 去除字符串中emoji
     */
    public static String replaceEmoji(String str) {
        if(!CommonUtil.isEmpty(str)){
            return str.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        }else{
            return str;
        }
    }
}
