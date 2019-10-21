package com.eoc.dong.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.common.utils.CommonUtils;
import com.jph.takephoto.model.TImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/1.
 * 描述：
 */

public class UIUtil {
    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 启动activity
     *
     * @param t
     * @param map
     */
    public static void startActivity(Class<?> t, Map<String, String> map) {
        Intent intent = new Intent(getContext(), t);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (map != null) {
            for (String key : map.keySet()) {
                intent.putExtra(key, map.get(key));
            }
        }
        getContext().startActivity(intent);
    }

    /**
     * 设置未读数
     */
    public static void setUnReadNum(TextView textView, int num) {
        textView.setText(num + "");
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

    /**
     * 自定义dialog全屏展示(宽高全屏)
     *
     * @param activity
     * @param dialog
     */
    public static void FullAllScreen(Activity activity, Dialog dialog) {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        //p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth());    //宽度设置为全屏
        p.height = (int) (d.getHeight());
        dialog.getWindow().setAttributes(p);     //设置生效
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
        //p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * scale);    //宽度设置为全屏
        dialog.getWindow().setAttributes(p);     //设置生效
    }
    public static void HalfFullScreen(Activity activity, Dialog dialog, double scale) {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        //p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
       // p.width = (int) (d.getWidth() * scale);    //宽度设置为全屏
        dialog.getWindow().setAttributes(p);     //设置生效
    }
    /**
     * 隐藏状态栏
     *
     * @param context
     */
    public static void hideStatusLan(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    /**
     * 隐藏状态栏
     *
     * @param context
     */
    public static void hideStatusLan1(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isMobile(String phoneNumber) {
        if (CommonUtils.isEmpty(phoneNumber)) {
            UIUtil.showToast("请输入手机号码");
            return false;
        }
        if (!phoneNumber.matches("1[0-9]{10}")) {
            UIUtil.showToast("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * showToast
     */
    public static void showToast(String s) {
        CommonUtils.showToast(getContext(), s);
    }







    /**
     * 字符串转list
     * @param ds
     * @return
     */
    public static List<String> sToList(String ds){
        List<String> list=new ArrayList<>();
        if(CommonUtils.isNotEmpty(ds)){
            String[] split = ds.split("\\*");
            for(String s:split){
                list.add(s);
            }
        }
        return list;
    }
    public static List<String> sToList1(String ds){
        List<String> list=new ArrayList<>();
        if(CommonUtils.isNotEmpty(ds)){
            String[] split = ds.split("\\*\\*\\*");
            for(String s:split){
                list.add(s);
            }
        }
        return list;
    }
    public interface SeleterPhotoLisener {
        void onSuccessBack(List<TImage> resultList);
    }





}
