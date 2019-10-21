package com.eoc.dong.fragment;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.AuthStateResponse;
import com.eoc.dong.common.network.response.IndexTextResponse;
import com.eoc.dong.common.network.response.OrderStatusResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentPresenter extends BasePresenter<HomeFragment> {
    //获取最大值最小值
    public void getNum(){
        RetrofitHelper.getRetrofit().create(Api.class).getIndexText("and").enqueue(new Callback<BaseResponse<IndexTextResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<IndexTextResponse>> call, Response<BaseResponse<IndexTextResponse>> response) {
                if(response.body().isSuccess()){
                    if (response.body().getData()!=null) {
                        mView.setNum(response.body().getData());
                    }
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<IndexTextResponse>> call, Throwable t) {

            }
        });
    }
    //获取应还款订单信息
  public void getRepaymentStatus(){
        RetrofitHelper.getRetrofit().create(Api.class).getNeedPayMsg(MyApplication.getInt("id",0)).enqueue(new Callback<BaseResponse<OrderStatusResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<OrderStatusResponse>> call, Response<BaseResponse<OrderStatusResponse>> response) {
                if("未登录或已过期".equals(response.body().getMsg())){
                    SharedPreUtil.saveData("token","");
                    UIHelper.gotoLoginAvtivity(mContext);
                }
                if (response.body().isSuccess()) {
                    if (response.body().getData()!=null){
                        mView.setRepaymentStatus(response.body().getData());
                    }
                }else{
                    CommonUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<OrderStatusResponse>> call, Throwable t) {

            }
        });
  }
 public void getCertificationStatus(){
        RetrofitHelper.getRetrofit().create(Api.class).getAuthStatus(MyApplication.getInt("id",0)).enqueue(new Callback<BaseResponse<AuthStateResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<AuthStateResponse>> call, Response<BaseResponse<AuthStateResponse>> response) {
                if(response.body().isSuccess()){
                    mView.setCertificationStatus(response.body().getData());
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AuthStateResponse>> call, Throwable t) {

            }
        });
 }
}
