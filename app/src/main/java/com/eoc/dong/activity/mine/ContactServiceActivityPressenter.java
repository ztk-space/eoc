package com.eoc.dong.activity.mine;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.ContactInformateResponse;
import com.eoc.dong.common.utils.CommonUtil;

import retrofit2.Call;
import retrofit2.Response;

public class ContactServiceActivityPressenter extends BasePresenter<ContactServiceActivity> {
    public void getMessageData() {
        RetrofitHelper.getRetrofit().create(Api.class).contactInformation().enqueue(
                new BaseCallBack<BaseResponse<ContactInformateResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<ContactInformateResponse>> call, Response<BaseResponse<ContactInformateResponse>> response) {
                        if (response.body().isSuccess()) {
                            mView.setPhone(response.body().getData());
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }
}
