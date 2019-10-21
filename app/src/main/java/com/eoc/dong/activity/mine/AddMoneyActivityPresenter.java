package com.eoc.dong.activity.mine;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.LvupPowerResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;

import retrofit2.Call;
import retrofit2.Response;

public class AddMoneyActivityPresenter extends BasePresenter<AddMoneyActivity> {
    void getData(String money) {

        RetrofitHelper.getRetrofit().create(Api.class).increaseMoneyApply(SharedPreUtil.getInt("id",0),money).enqueue(
                new BaseCallBack<BaseResponse<LvupPowerResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<LvupPowerResponse>> call, Response<BaseResponse<LvupPowerResponse>> response) {
                        if (response.body().isSuccess()) {
                            mView.getData();
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }
}
