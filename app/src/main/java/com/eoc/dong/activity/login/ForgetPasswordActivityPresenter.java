package com.eoc.dong.activity.login;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.utils.CommonUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivityPresenter extends BasePresenter<ForgetPasswordActivity> {
    public void getForgetPassword(String phone,String msgcode,String pwd,String confirmPwd){
        RetrofitHelper.getRetrofit().create(Api.class).getForgetPassword(phone,msgcode,pwd,confirmPwd).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body().isSuccess()){
                    mView.finish();
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
