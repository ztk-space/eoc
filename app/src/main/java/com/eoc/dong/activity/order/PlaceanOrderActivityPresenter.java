package com.eoc.dong.activity.order;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.OrderDetailResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.UIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceanOrderActivityPresenter extends BasePresenter<PlaceanOrderActivity> {
    public void getApplyAnOrder(String money,int day){
        RetrofitHelper.getRetrofit().create(Api.class).getPlaceanOrder(money,day, MyApplication.getInt("id",0),"and").enqueue(new Callback<BaseResponse<OrderDetailResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<OrderDetailResponse>> call, Response<BaseResponse<OrderDetailResponse>> response) {
                if(response.body().isSuccess()){
                     mView.setApplyAnOrder(response.body().getData());
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<OrderDetailResponse>> call, Throwable t) {

            }
        });
    }
  public void setSubmitOrder(int id,String ip,String uuid){
      RetrofitHelper.getRetrofit().create(Api.class).submitOrder(id,"and","1",ip,uuid).enqueue(new Callback<BaseResponse>() {
          @Override
          public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
              if(response.body().isSuccess()){
                  UIHelper.gotoMainActivityWithAuth(mContext);
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
