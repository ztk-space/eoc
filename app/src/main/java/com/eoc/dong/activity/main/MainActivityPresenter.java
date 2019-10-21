package com.eoc.dong.activity.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.BuildConfig;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.CheckUpdateResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.common.widget.CommonDialog;

import retrofit2.Call;
import retrofit2.Response;


public class MainActivityPresenter extends BasePresenter<MainActivity> {
        public void checkUpdate() {
            RetrofitHelper.getRetrofit().create(Api.class).checkUpdate("and", getVerName(mContext))
                    .enqueue(new BaseCallBack<BaseResponse<CheckUpdateResponse>>(mContext) {
                        @Override
                        public void onSuccess(Call<BaseResponse<CheckUpdateResponse>> call, Response<BaseResponse<CheckUpdateResponse>> response) {
                            if (response.body().isSuccess()) {
                                //status为一代表该版本为可用版本
                                if (CommonUtil.isNotEmpty(response.body().getData().getUrl())) {
                                    showUpdateDialog(response.body().getData().getUrl());
                                    // showUpdateDialog(response.body().getData().getLink());
                                }
                            } else {
                                CommonUtil.showToast(response.body().getMsg());
                            }
                        }
                    });

        }

        public static String getVerName(Context context) {
            String verName = "";
            try {
                verName = context.getPackageManager().
                        getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return verName;
        }

        private boolean needUpdate(String newVersion) {
            String currentVersion = BuildConfig.VERSION_NAME;
            if (currentVersion.contains("-debug")) {
                currentVersion = currentVersion.replace("-debug", "");
            }

            String[] currentCode = currentVersion.split("[.]");
            String[] newCode = newVersion.split("[.]");

            try {
                for (int i = 0; i < currentCode.length; i++) {
                    if (!currentCode[i].equals(newCode[i])) {
                        return Integer.parseInt(currentCode[i]) < Integer.parseInt(newCode[i]);
                    }
                }
            } catch (Exception e) {
                return false;
            }

            return false;
        }

        /**
         * 弹出提示升级的Dialog
         */
        private void showUpdateDialog(final String downloadUrl) {
            CommonDialog.Builder builder = new CommonDialog.Builder(mContext);
            builder.setTitle("升级提示");
            builder.setMessage("发现新版本，是否更新？");

            builder.setPositiveButton("立即更新", (dialog, which) -> {
//            if (PermissionsUtil.checkVerifySDPermissions(((BaseActivity) mContext))) {
//                dialog.dismiss();
//                Intent intent = new Intent(mContext, UpdateService.class);
//                intent.putExtra("apkUrl", downloadUrl);
//                mContext.startService(intent);
//            }else{
//                PermissionsUtil.verifySDPermissions(((BaseActivity) mContext));
//            }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(downloadUrl);
                intent.setData(content_url);
                mContext.startActivity(intent);
            });

            builder.setNegativeButton("退出应用", (dialog, which) -> ((BaseActivity) mContext).finish());

            builder.create().show();
        }
    public void authFace(String faceId) {
//        public void authFace() {
        RetrofitHelper.getRetrofit().create(Api.class).faceAuthVN(MyApplication.getString("idCardFronts",""),
                MyApplication.getString("idCardBacks",""),
                faceId, SharedPreUtil.getInt("id",0),
                MyApplication.getString("faceNumId",""),
                MyApplication.getString("faceName","")
        ).enqueue(new BaseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().isSuccess()){
                     //UIHelper.gotoImproveAuthActivity(mContext,7);
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }
    }

