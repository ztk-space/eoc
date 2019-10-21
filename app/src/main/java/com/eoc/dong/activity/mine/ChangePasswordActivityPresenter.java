package com.eoc.dong.activity.mine;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.LoginResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.UIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivityPresenter extends BasePresenter<ChangePasswordActivity> {
    public void setChangePwd(String oldpwd,String newpwd,String confirmPwd,String changephone){
        RetrofitHelper.getRetrofit().create(Api.class).goChangePassword(oldpwd,newpwd,confirmPwd,changephone).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body().isSuccess()){
                    UIHelper.gotoLoginAvtivity(mContext);
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
