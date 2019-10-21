package com.eoc.dong.activity.certification;


import android.os.CountDownTimer;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BasePresenter;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.BankAuthResponse;
import com.eoc.dong.common.utils.AssetsBankInfo;
import com.eoc.dong.common.utils.CommonUtils;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.util.CommonUtil;

import retrofit2.Call;
import retrofit2.Response;

public class BankCardAuthActivityPresenter extends BasePresenter<BankCardAuthActivity> {
    private MyCountDownTimer mc;
    private String picMark;
    String name,  ID,  card,  phone;
    public void getBankAuthInfo() {
        RetrofitHelper.getRetrofit().create(Api.class).getBankCardList(SharedPreUtil.getInt("id",0)).enqueue(
                new BaseCallBack<BaseResponse<BankCardsListResopnse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<BankCardsListResopnse>> call, Response<BaseResponse<BankCardsListResopnse>> response) {
                        if (response.body().isSuccess()) {
                            if ("OVERTIME".equals(response.body().getCode())) {
                                UIHelper.gotoLoginActivityWithLogOut(mContext);
                                return;
                            }

                            if (response.body().getData() != null) {
                                mView.setBankInfo(response.body().getData());
                            }
                        } else {
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }

    /**
     * 发送手机验证码
     */
    public void senCode(String phone,String picCode) {
        if (CommonUtils.isEmpty(phone)){
            CommonUtil.showToast("手机号码不能为空！");
        }

        //开始倒计时
        mc = new MyCountDownTimer(60000, 1000);
        mc.start();

    }

    /**
     * 提交
     */
    public void postData(String name, String ID, String card, String phone) {
        this.name=name;
        this.card=card;
        this.ID=ID;
        this.phone=phone;
        if (!checkInput(name,ID,card,phone)){
            return;
        }

        RetrofitHelper.getRetrofit().create(Api.class).bankcardAuth(SharedPreUtil.getInt("id",0),name,card,ID,phone).
                enqueue(new BaseCallBack<BaseResponse<BankAuthResponse>>(mContext) {
            @Override
            public void onSuccess(Call<BaseResponse<BankAuthResponse>> call, Response<BaseResponse<BankAuthResponse>> response) {
                if (response.body().isSuccess()){
                      mView.authSuccess(response.body().getData());
                }else{
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }
    public void postsData(String code,String reqNum) {

                 if (code!=null){
                     RetrofitHelper.getRetrofit().create(Api.class).fyBankCardAuth(SharedPreUtil.getInt("id",0),name,card,ID,phone,code, AssetsBankInfo.getNameOfBank(mContext,card),reqNum).
                             enqueue(new BaseCallBack<BaseResponse<BankAuthResponse>>(mContext) {
                                 @Override
                                 public void onSuccess(Call<BaseResponse<BankAuthResponse>> call, Response<BaseResponse<BankAuthResponse>> response) {
                                     if (response.body().isSuccess()){
                                         mView.authSuccess();
                                     }else{
                                         CommonUtil.showToast(response.body().getMsg());
                                     }
                                 }
                             });
                 }

    }
    public void cancleBank() {


            RetrofitHelper.getRetrofit().create(Api.class).relieveBindCard(SharedPreUtil.getInt("id",0)).
                    enqueue(new BaseCallBack<BaseResponse<BankAuthResponse>>(mContext) {
                        @Override
                        public void onSuccess(Call<BaseResponse<BankAuthResponse>> call, Response<BaseResponse<BankAuthResponse>> response) {
                            if (response.body().isSuccess()){
                                mView.cancleSuccess();
                            }else{
                                CommonUtil.showToast(response.body().getMsg());
                            }
                        }
                    });

    }


    private boolean checkInput(String name, String id, String card, String phone) {
        if (CommonUtil.isEmpty(name)) {
            CommonUtil.showToast("请输入姓名");
            return false;
        }
        if (CommonUtil.isEmpty(id)) {
            CommonUtil.showToast("请输入身份证号码");
            return false;
        }
        if (CommonUtil.isEmpty(card)) {
            CommonUtil.showToast("请输入银行卡号");
            return false;
        }
        if (CommonUtil.isEmpty(phone)) {
            CommonUtil.showToast("请输入预留手机号码");
            return false;
        }

        return true;
    }

    /**
     * 倒计时
     */
    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            mView.countDownStart((int) (millisInFuture / 1000));
        }

        @Override
        public void onFinish() {
            mView.countDownFinish();
            mc.cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mView.countDownChange((int) (millisUntilFinished / 1000));
        }
    }
}
