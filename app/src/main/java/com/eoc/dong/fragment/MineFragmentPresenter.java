package com.eoc.dong.fragment;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.AuthStateResponse;
import com.eoc.dong.common.network.response.LvupPowerResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;

import retrofit2.Call;
import retrofit2.Response;

public class MineFragmentPresenter extends BasePresenter<MineFragment> {
    /**
     * 获取用户认证状态
     */
    public void checkAuthStatus() {
        RetrofitHelper.getRetrofit().create(Api.class).getAuthStatus(SharedPreUtil.getInt("id",0)).enqueue(
                new BaseCallBack<BaseResponse<AuthStateResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<AuthStateResponse>> call, Response<BaseResponse<AuthStateResponse>> response) {
                        if (response.body().isSuccess()){
                            if (response.body().getData().bankAuth==1){
                                UIHelper.gotoBankCardCertificationActivity(mContext,2);
                            }else {
                                CommonUtil.showToast(mContext.getResources().getString(R.string.mine_toast_auth_bank));
                            }
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
    void getLvupPower() {
        RetrofitHelper.getRetrofit().create(Api.class).LvupPower(SharedPreUtil.getInt("id",0)).enqueue(
                new BaseCallBack<BaseResponse<LvupPowerResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<LvupPowerResponse>> call, Response<BaseResponse<LvupPowerResponse>> response) {
                        if (response.body().isSuccess()) {
                            mView.setUpPower(response.body().getData());
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }
}
