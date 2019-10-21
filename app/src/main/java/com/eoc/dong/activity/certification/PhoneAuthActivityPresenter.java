package com.eoc.dong.activity.certification;


import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.PhoneAuthResponse;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.util.CommonUtil;

import retrofit2.Call;
import retrofit2.Response;


public class PhoneAuthActivityPresenter extends BasePresenter<PhoneAuthActivity> {





    public void phoneAuth() {

        RetrofitHelper.getRetrofit().create(Api.class).getPhoneAuth(SharedPreUtil.getInt("id",0)).enqueue(
                new BaseCallBack<BaseResponse<PhoneAuthResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<PhoneAuthResponse>> call, Response<BaseResponse<PhoneAuthResponse>> response) {
                        if (response.body().isSuccess()){
                          mView.getAuthUrl(response.body().getData().url);
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                });

          }

    private boolean checkInput(String phone) {
        if (CommonUtil.isEmpty(phone)) {
            CommonUtil.showToast("手机号码不能为空！");

            return false;
        }
        return true;
    }

}
