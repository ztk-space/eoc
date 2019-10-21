package com.eoc.dong.activity.order;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.OrderListResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.UIHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivityPresenter extends BasePresenter<MyOrderActivity> {
    public void getMyOlder(){
        RetrofitHelper.getRetrofit().create(Api.class).getMyOrder(MyApplication.getInt("id",0)).enqueue(new Callback<BaseResponse<List<OrderListResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<OrderListResponse>>> call, Response<BaseResponse<List<OrderListResponse>>> response) {
                if ("OVERTIME".equals(response.body().getCode())) {
                    UIHelper.gotoLoginAvtivity(mContext);
                    return;
                }
                if(response.body().isSuccess()){
                  if(response.body().getData()!=null){
                      mView.getOrder(response.body().getData());
                  } else {
                      CommonUtil.showToast(response.body().getMsg());
                  }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<OrderListResponse>>> call, Throwable t) {

            }
        });
    }
}
