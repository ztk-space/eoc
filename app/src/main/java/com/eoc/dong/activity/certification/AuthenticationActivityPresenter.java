package com.eoc.dong.activity.certification;

import android.util.Log;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.compression.CompressHelper;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class AuthenticationActivityPresenter extends BasePresenter<AuthenticationActivity> {
    public void uploadfile(List<String> list, final int type){
        if (list.size()==0){
            return;
        }

        List<MultipartBody.Part> imageList=new ArrayList<>();
        for (String imagePath : list) {
            File file = new File(imagePath);
        /*    try {
                String fileSize = getFileSize(file);
                Log.i("ddfdfvbvb", "uploadfile: fileSize:"+fileSize);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            File compressToFile = CompressHelper.getDefault(mContext).compressToFile(file);
       /*     try {
                String fileSizeCompress = getFileSize(compressToFile);
                Log.i("ddfdfvbvb", "uploadfile: fileSizeCompress:"+fileSizeCompress);
            } catch (Exception e) {
                e.printStackTrace();
            }*/


            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), compressToFile);
            //MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
            MultipartBody.Part body = MultipartBody.Part.createFormData("uploadImage", compressToFile.getName(), imageBody);
            imageList.add(body);
        }

        RetrofitHelper.getRetrofit().create(Api.class).uploadImageVN(imageList).
                enqueue(new BaseCallBack<BaseResponse<String>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                        if (response.body().isSuccess()){
                            String data = response.body().getData();
                            Log.i("dfdfdferer", "onSuccess: "+data);
                            if (type==1){
                                MyApplication.saveString("idCardFronts",data);
                            }else if (type==2){
                                MyApplication.saveString("idCardBacks",data);
                            } else if (type==3){
                                MyApplication.saveString("faceUrl",data);
                            }
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }


    public static String getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {

            Log.e("获取文件大小", "文件不存在!");
        }
        return toFileSize(size);
    }


    public static String toFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }
}
