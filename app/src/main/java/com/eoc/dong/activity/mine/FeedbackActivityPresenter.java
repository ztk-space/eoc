package com.eoc.dong.activity.mine;

import android.text.TextUtils;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.request.FeedBackRequest;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.util.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class FeedbackActivityPresenter extends BasePresenter<FeedbackActivity> {
    public void postData( final String content,final String phone) {
        if (!checkInput(content,phone)){
            return;
        }
        commitUserFeedBack(content,phone);
        // uploadfile(list,content,phone,type);
    }

    /**
     * 检验参数合法性
     */
    private boolean checkInput( String content, String phone) {
        if(TextUtils.isEmpty(content)){
            CommonUtil.showToast("请输入反馈内容");
            return false;
        }

        if( !CommonUtil.isMobile(phone)){
            return false;
        }
        return true;
    }

    private void uploadfile(List<String> list, final String content, final String phone, final int type){
        if (list.size()==0){
            //commitUserFeedBack(content,"",phone);
            return;
        }

        List<MultipartBody.Part> imageList=new ArrayList<>();
        for (String imagePath : list) {
            File file = new File(imagePath);
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
            imageList.add(body);
        }

        RetrofitHelper.getRetrofit().create(Api.class).uploadImage(imageList).
                enqueue(new BaseCallBack<BaseResponse<List<String>>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<List<String>>> call, Response<BaseResponse<List<String>>> response) {
                        if (response.body().isSuccess()){
                            StringBuilder sb = new StringBuilder();
                            for (String s : response.body().getData()) {
                                sb.append(s).append("***");
                            }
                            sb.deleteCharAt(sb.length() - 1);
                            sb.deleteCharAt(sb.length() - 1);
                            sb.deleteCharAt(sb.length() - 1);
                            // commitUserFeedBack(type,content,sb.toString(),phone);
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }

    private void commitUserFeedBack(String content,  String phone){
        FeedBackRequest request=new FeedBackRequest(phone,content, SharedPreUtil.getInt("id",0));
        RetrofitHelper.getRetrofit().create(Api.class).commitUserFeedback( SharedPreUtil.getInt("id",0),phone,content)
                .enqueue(new BaseCallBack<BaseResponse>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body().isSuccess()){
                            CommonUtil.showToast("提交成功！");
                            UIHelper.gotoMainActivityWithAuth(mContext);
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }
}
