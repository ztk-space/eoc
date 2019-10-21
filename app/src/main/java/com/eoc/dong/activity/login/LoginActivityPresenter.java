package com.eoc.dong.activity.login;


import android.util.Log;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.LoginResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivityPresenter extends BasePresenter<LoginActivity> {
    //登录
  public void goLogin(String phone,String pwd){
      RetrofitHelper.getRetrofit().create(Api.class).goRegister(phone,"000000",pwd,"and").enqueue(new Callback<BaseResponse<LoginResponse>>() {
          @Override
          public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
              if(response.body().isSuccess()){
                  if(response.body().getData()!=null) {
                      landedsuccessfully(response.body().getData());
                  }
              }else {
                  CommonUtil.showToast(response.body().getMsg());
              }
          }

          @Override
          public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {

          }
      });
//      String phones="62"+phone;
//      String channelApkUuid = MyApplication.getString("channelApkUuid", "");
////        Log.i(TAG, "doLogin: "+channelApkUuid);
//      RetrofitHelper.getRetrofit().create(Api.class).phoneLogin(phones,pwd,android.os.Build.BRAND,
//              channelApkUuid
//      ).enqueue(
//              new BaseCallBack<BaseResponse<LoginResponse>>(mContext) {
//                  @Override
//                  public void onSuccess(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
//                      if (response.body().isSuccess()){
//                          landedsuccessfully(response.body().getData());
//                      }else{
//                          Log.i("TAG",response.body().getMsg());
//                          CommonUtil.showToast(response.body().getMsg());
//                      }
//                  }
//              });
  }
    private void landedsuccessfully(LoginResponse response) {
        SharedPreUtil.saveData("uuid",response.getUuid());
        SharedPreUtil.saveData("id",response.getId());
        SharedPreUtil.saveData("payPwd",response.getPayPwd());
        SharedPreUtil.saveData("userName",response.getUserName());
        SharedPreUtil.saveData("password",response.getPassword());
        SharedPreUtil.saveData("phone",response.getPhone());
        SharedPreUtil.saveData("money",response.getMoney());
        SharedPreUtil.saveData("userType",response.getUserType());
        SharedPreUtil.saveData("authStatus",response.getAuthStatus());
        SharedPreUtil.saveData("token",response.getToken());
        SharedPreUtil.saveData("isLogin",true);
        mView.goMain();
    }
}
