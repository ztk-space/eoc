package com.eoc.dong.activity.order;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.RentOrderShellResponse;
import com.eoc.dong.common.utils.CommonUtil;

import retrofit2.Call;
import retrofit2.Response;

public class OrderDetailsActivityPresenter extends BasePresenter<OrderDetailsActivity> {

    private String protocolId;

    public void getOrderDetail(int orderId) {
        RetrofitHelper.getRetrofit().create(Api.class).getOrderDetail(orderId).enqueue(
                new BaseCallBack<BaseResponse<RentOrderShellResponse>>() {
                    @Override
                    public void onSuccess(Call<BaseResponse<RentOrderShellResponse>> call, Response<BaseResponse<RentOrderShellResponse>> response) {
                        if (response.body().isSuccess()){
                            mView.setOrderDetail(response.body().getData());
                            //protocolId=response.body().getData().getOrder().getEvaluation().getProtocolId();
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }


    /**
     * 提交还款申请
     */
    public void submitRepay(String orderId) {
        RetrofitHelper.getRetrofit().create(Api.class).submitRepay(orderId).enqueue(
                new BaseCallBack<BaseResponse>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body().isSuccess()){
                            //   UIHelper.gotoRenewalDebitActivity(mContext);
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }
}
