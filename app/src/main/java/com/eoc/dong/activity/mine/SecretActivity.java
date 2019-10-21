package com.eoc.dong.activity.mine;

import android.os.Bundle;
import android.text.Html;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.AboutUsDetailResponse;
import com.eoc.dong.databinding.ActivityAgreementBinding;
import com.eoc.dong.util.CommonUtil;


import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by CodeFatCat on 2019/5/17
 */
public class SecretActivity extends BaseActivity<SecretActivityPresenter, ActivityAgreementBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.secret_title));

        getData();
    }

    private void getData() {

            RetrofitHelper.getRetrofit().create(Api.class).getAboutUs(2,"保密政策").enqueue(
                    new BaseCallBack<BaseResponse<List<AboutUsDetailResponse>>>() {
                        @Override
                        public void onSuccess(Call<BaseResponse<List<AboutUsDetailResponse>>> call, Response<BaseResponse<List<AboutUsDetailResponse>>> response) {
                            if (response.body().isSuccess()){
                                List<AboutUsDetailResponse> data = response.body().getData();
                                if (data.size()>0){
                                    if(data.get(0).getText()!=null) {
                                        mBindingView.txtRegisterAgreement.setText(Html.fromHtml(data.get(0).getText()));
                                    }
                                }
                            }else{
                                CommonUtil.showToast(response.body().getMsg());
                            }
                        }
                    });

    }
}
