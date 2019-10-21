package com.eoc.dong.activity.certification;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.eoc.dong.R;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.widget.UIUtil;
import com.eoc.dong.databinding.ActivityAuthenticationBinding;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ai.advance.liveness.sdk.activity.LivenessActivity;
import butterknife.OnClick;
import rx.functions.Action1;

public class AuthenticationActivity extends BaseActivity<AuthenticationActivityPresenter, ActivityAuthenticationBinding> {
    private final int zheng = 10;//正面
    private final int fan = 20;//反面
    private final int man = 30;//人脸
    private String sdcardPathDir;// 位置
    private File file;// 照片文件
    private String path;// 路径
    private Uri photoUri;// 图片URL
    private String absolutePath;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.auth_identity_title));
        MyApplication.saveString("idCardFronts","");
        MyApplication.saveString("idCardBacks","");
        MyApplication.saveString("faceUrl","");
        MyApplication.saveString("faceName","");
        MyApplication.saveString("faceNumId","");
    }
    @OnClick({R.id.iv_zheng,R.id.iv_fan,R.id.btn_new_next})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_zheng:
                if (Build.VERSION.SDK_INT >= 23){
                    new RxPermissions(((BaseActivity) mContext)).request(Manifest.permission.CAMERA).subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean) {

                                new RxPermissions(((BaseActivity) mContext)).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean aBoolean) {
                                        if (aBoolean) {
                                            getImageFromCameraFan(zheng);
                                        } else {
                                            UIUtil.showToast(getResources().getString(R.string.per_store));
                                        }
                                    }
                                });

                            } else {
                                Log.i("TAG","S");
                                UIUtil.showToast(getResources().getString(R.string.per_camera));
                            }
                        }
                    });
                }else {
                    getImageFromCameraFan(zheng);
                }
                break;
            case R.id.iv_fan:
                if (Build.VERSION.SDK_INT >= 23){
                    new RxPermissions(((BaseActivity) mContext)).request(Manifest.permission.CAMERA).subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean) {
                                new RxPermissions(((BaseActivity) mContext)).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean aBoolean) {
                                        if (aBoolean) {
                                            getImageFromCameraFan(fan);
                                        } else {
                                            UIUtil.showToast(getResources().getString(R.string.per_store));
                                        }
                                    }
                                });

                            } else {
                                UIUtil.showToast(getResources().getString(R.string.per_camera));
                            }

                        }
                    });
                }else {
                    getImageFromCameraFan(fan);
                }
                break;
//            case R.id.img_new_renlian:
//                if (Build.VERSION.SDK_INT >= 23){
//                    new RxPermissions(((BaseActivity) mContext)).request(Manifest.permission.CAMERA).subscribe(new Action1<Boolean>() {
//                        @Override
//                        public void call(Boolean aBoolean) {
//                            if (aBoolean) {
//                                new RxPermissions(((BaseActivity) mContext)).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
//                                    @Override
//                                    public void call(Boolean aBoolean) {
//                                        if (aBoolean) {
//                                            getImageFromCameraFan(man);
//                                        } else {
//                                            UIUtil.showToast(getResources().getString(R.string.per_store));
//                                        }
//
//                                    }
//                                });
//                            } else {
//                                UIUtil.showToast(getResources().getString(R.string.per_camera));
//                            }
//
//                        }
//                    });
//                }else {
//                    getImageFromCameraFan(man);
//                }
//                break;
            case R.id.btn_new_next:
                if(TextUtils.isEmpty(mBindingView.etFaceName.getText().toString())){
                    CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
                } else if(TextUtils.isEmpty(mBindingView.etFaceNumId.getText().toString())){
                    CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
                }else if(TextUtils.isEmpty(MyApplication.getString("idCardFronts",""))){
                    UIUtil.showToast(getResources().getString(R.string.upload_camera));
                }else if(TextUtils.isEmpty(MyApplication.getString("idCardBacks",""))){
                    UIUtil.showToast(getResources().getString(R.string.upload_camera));
                } else {
                    MyApplication.saveString("faceName",mBindingView.etFaceName.getText().toString());
                    MyApplication.saveString("faceNumId",mBindingView.etFaceNumId.getText().toString());
                    checkPermission();
                }
                break;
        }
    }
    public void checkPermission() {
        new RxPermissions(((BaseActivity) mContext)).request(Manifest.permission.CAMERA).
                subscribe(aBoolean -> {
                    if (aBoolean){
                        startActivity(new Intent(this, LivenessActivity.class));
                    }else {
                        UIUtil.showToast(getResources().getString(R.string.per_camera));
                    }
                        }
                );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case zheng:
                    if(photoUri.getPath()!=null){
                        List<String> strings = new ArrayList<>();
                        strings.add(photoUri.getPath());
                        mPresenter.uploadfile(strings,1);
                        Glide.with(mContext).load(photoUri).into(mBindingView.ivZheng);
                    }
                    break;
                case fan:
                    if(photoUri.getPath()!=null){
                        List<String> stringList = new ArrayList<>();
                        stringList.add(photoUri.getPath());
                        mPresenter.uploadfile(stringList,2);
                        // mPresenter.uploadfile(photoUri.getPath(),2,photoUri);
                        Glide.with(mContext).load(photoUri).into(mBindingView.ivFan);
                    }
                    break;
//                case man:
//                    if(photoUri.getPath()!=null){
//                        List<String> stringList = new ArrayList<>();
//                        stringList.add(photoUri.getPath());
//                        mPresenter.uploadfile(stringList,3);
//                        //mPresenter.uploadfile(photoUri.getPath(),3,photoUri);
//                        Glide.with(mContext).load(photoUri).into(mBindingView.imgNewRenlian);
//                    }
//                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    // 获取安卓系统照相机拍照
    protected void getImageFromCameraFan(int code) {

        try {
            Intent getImageByCamera = new Intent(
                    "android.media.action.IMAGE_CAPTURE");
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {// 检查是否有存储卡
                // 创建位置
                sdcardPathDir = Environment
                        .getExternalStorageDirectory().getPath()
                        + "/vtdong/";
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {// 目录是否存在
                    fileDir.mkdirs();
                }

                file = new File(sdcardPathDir + System.currentTimeMillis()+ ".PNG");
                if (file != null) {
                    path = file.getPath();
                    absolutePath = file.getAbsolutePath();
                    if (Build.VERSION.SDK_INT >= 24) {
                        getImageByCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        photoUri = FileProvider.getUriForFile(this, mContext.getApplicationContext().getPackageName() + ".provider", file);

                    } else {
                        photoUri = Uri.fromFile(file);
                    }

                    // 知道相机存储的地址
                    getImageByCamera
                            .putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(getImageByCamera, code);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("TAG",e.toString());
            UIUtil.showToast(getResources().getString(R.string.per_camera));
        }
    }
}
