package com.eoc.dong.common.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Administrator on 2017/4/18.
 */
public class PermissionsUtil {
    private static int REQUEST_CODE_ASK_PERMISSIONS = 123;

    /**
     * SD卡读写权限
     */
    private static final String[] PERMISSION_SD = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_SD = 100;


    /**
     * SD卡读写权限
     */
    public static void verifySDPermissions(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_SD, REQUEST_SD);
        }
    }

    /**
     * 检测SD卡读写权限
     */
    public static boolean checkVerifySDPermissions(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permissionWrite == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean verifySdkVersion() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        } else {
            return true;
        }
    }

    public static void showphonePermissions(Activity activity) {
        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CONTACTS}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
    }
}
