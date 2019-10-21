package com.eoc.dong.activity.main;


import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.AboutUsDetailResponse;
import com.eoc.dong.util.CommonUtil;


import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AboutActivityPresenter extends BasePresenter<AboutUsActivity> {
    public void getAboutUsDetail() {
        RetrofitHelper.getRetrofit().create(Api.class).getAboutUs(5,"关于我们").enqueue(
                new BaseCallBack<BaseResponse<List<AboutUsDetailResponse>>>() {
            @Override
            public void onSuccess(Call<BaseResponse<List<AboutUsDetailResponse>>> call, Response<BaseResponse<List<AboutUsDetailResponse>>> response) {
                if (response.body().isSuccess()){
                    mView.setAboutUsDetail(response.body().getData());
                }else{
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }
}
