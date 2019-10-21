package com.eoc.dong.activity.order;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.PayRentResponse;
import com.eoc.dong.common.network.response.RenewalInfoResponse;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.util.CommonUtil;

import retrofit2.Call;
import retrofit2.Response;

public class RenewalActivityPresenter extends BasePresenter<RenewalActivity> {
    private String paramSettingId;
    private String orderId;
    private boolean isPay=false;
    public void getData(String limitDays,int orderId) {
        RetrofitHelper.getRetrofit().create(Api.class).getRenewalInfo(limitDays,orderId).enqueue(
                new BaseCallBack<BaseResponse<RenewalInfoResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<RenewalInfoResponse>> call, Response<BaseResponse<RenewalInfoResponse>> response) {
                        if (response.body().isSuccess()){
                            mView.setData(response.body().getData());
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }

    void rentPay(int orderId, String bindPayMoney) {
        if (isPay){
            return;
        }
        isPay=true;
        //bindPayDay1 支付 2续期     bindPayType 续期天数（还款时传递-1）
        RetrofitHelper.getRetrofit().create(Api.class).payRent(orderId,"",bindPayMoney,"7", SharedPreUtil.getInt("id",0),2).enqueue(
                new BaseCallBack<BaseResponse<PayRentResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<PayRentResponse>> call, Response<BaseResponse<PayRentResponse>> response) {
                        if (response.body().isSuccess()){
                            UIHelper.gotoRepaySuccessActivity(mContext,false);
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        isPay=false;
                    }
                });
    }
}
