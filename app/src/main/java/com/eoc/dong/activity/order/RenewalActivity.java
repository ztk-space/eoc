package com.eoc.dong.activity.order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.IndexTextResponse;
import com.eoc.dong.common.network.response.RenewalInfoResponse;
import com.eoc.dong.common.network.response.WebUrlResponse;
import com.eoc.dong.common.utils.DigestUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityRenewalBinding;
import com.eoc.dong.util.CommonUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RenewalActivity extends BaseActivity<RenewalActivityPresenter, ActivityRenewalBinding> {
    private String limitDays ="7";

    private long couponId;              //优惠券id
    private BigDecimal realPay;
    private Integer days;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal);
    }

    @Override
    protected void initView() {
        int orderId = getIntent().getIntExtra("id", 0);
        setTitle(getResources().getString(R.string.renewal_title));
        getIndexData();
    }
    void getIndexData() {
        RetrofitHelper.getRetrofit().create(Api.class).getIndexText("andIs").enqueue(
                new BaseCallBack<BaseResponse<IndexTextResponse>>(mContext) {
                    @Override
                    public void onSuccess(Call<BaseResponse<IndexTextResponse>> call, Response<BaseResponse<IndexTextResponse>> response) {
                        if (response.body().isSuccess()) {
                            IndexTextResponse data = response.body().getData();
                            days = data.days.get(0);
                            mBindingView.tvLimitDay.setText(days+"");

                            mPresenter.getData(days+"",getIntent().getIntExtra("id",0));
                        }else{
                            CommonUtil.showToast(response.body().getMsg());
                        }
                    }
                });
    }



/*    @OnClick({R.id.btn_commit_renewal
    })
    public void OnClick(View view){
        switch (view.getId()){

            case R.id.rl_select_coupon://优惠券
              //  UIHelper.gotoCouponActivity(mContext);
                break;
            case R.id.btn_commit_renewal://提交续期
                String realRepay = mBindingView.tvExtendMoney.getText().toString();
                realRepay=realRepay.substring(0,realRepay.length()-1);
                String orderId=getIntent().getStringExtra("orderId");
               // mPresenter.rentPay(orderId,"");
                break;
        }
    }*/



    public void setData(RenewalInfoResponse response) {
        mBindingView.tvLimitPayTime.setText(response.limitPayTime.split(" ")[0]);
        mBindingView.tvNeedPayMoney.setText(String.format("%s"+getResources().getString(R.string.money),response.extendMoney));
        mBindingView.tvInterestMoney.setText(String.format("%s"+getResources().getString(R.string.money),response.interestPercent));
        mBindingView.tvAllWasteMoney.setText(String.format("%s"+getResources().getString(R.string.money),response.serviceCharge));
        mBindingView.tvOverdue.setText(String.format("%s"+getResources().getString(R.string.money),response.overdueMoney));
        mBindingView.tvExtendMoney.setText(String.format("%s"+getResources().getString(R.string.money),response.extendMoney));
        mBindingView.btnCommitRenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPresenter.rentPay(getIntent().getIntExtra("orderId",0),response.extendMoney+"");


                getUserInfoVN(response.extendMoney,getIntent().getIntExtra("orderId",0));
            }
        });
    }

    public void getUserInfoVN(int money, int id){
        RetrofitHelper.getRetrofit().create(Api.class).getVNuserInfo().enqueue(new BaseCallBack<BaseResponse<VNUserInfo>>() {
            @Override
            public void onSuccess(Call<BaseResponse<VNUserInfo>> call, Response<BaseResponse<VNUserInfo>> response) {
                if (response.body().isSuccess()){
                    VNUserInfo data = response.body().getData();
                    if (data!=null){
                       // payVn(money, id,data);
                        getWebUrl(id+"",money);
                    }
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }

public void getWebUrl(String orderId,int bindPayMoney){
    Integer money = Integer.valueOf(bindPayMoney);
    String s = String.valueOf(money);
    RetrofitHelper.getRetrofit().create(Api.class).getWebUrl("2",MyApplication.getInt("id",0)+"",orderId,s).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body().isSuccess()){
                    String data = (String) response.body().getData();
                    UIHelper.gotoWebPayActivity(mContext,data);
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
}
    private void payVn(String money,int id,VNUserInfo info) {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        String merchant_site_code="59255";
        String return_url="http://47.96.24.144/loan/Pay_nlPaymentNoticeSuccess.html";
        String receiver="jackwq83@163.com";
        String transaction_info=id+"~"+"2"+"~"+days+"~"+new Random().nextInt(10000);
        String order_code=id+timeString;
        String price=money;
        String currency="vnd";
        String quantity="1";
        String tax="0";
        String discount="0";
        String fee_cal="0";
        String fee_shipping="0";
        String notify_url="http://47.96.24.144/loan/Pay_nlPaymentNoticeSuccess.html";
        String order_description="test";
        String buyer_info=info.getUser().getName()+"*|*"+info.getAuthBasic().getEmail()+"*|*"+ SharedPreUtil.getString("phone","")+"*|*"+info.getAuthBasic().getHomeAddr();
        String affiliate_code="123456";
        String md5Encrpytion = DigestUtil.MD5Encrpytion(merchant_site_code + ' ' + return_url + ' ' + receiver + ' ' + transaction_info + ' ' + order_code + ' ' + price
                + ' ' + currency + ' ' + quantity + ' ' + tax + ' ' + discount + ' ' + fee_cal + ' ' + fee_shipping  + ' ' + order_description + ' ' +
                buyer_info + ' ' + affiliate_code + ' ' + "dd15e71ff6076125495fff15fd132c5d");
        String secure_code=md5Encrpytion;
        String url="https://www.nganluong.vn/checkout.php?merchant_site_code="+merchant_site_code+"&return_url="+return_url+"&receiver="+receiver+"&transaction_info="+transaction_info+"&order_code="+order_code+"&price="+price+"&currency="+currency+"&quantity="+quantity+"&tax="+tax+"&discount="+discount+"&fee_cal="+fee_cal+"&fee_shipping="+fee_shipping+"&secure_code="+secure_code+"&notify_url="+notify_url+"&order_description="+order_description+"&buyer_info="+buyer_info+"&affiliate_code="+affiliate_code;
        UIHelper.gotoWebPayActivity(mContext,url);
    }

    public void submitScucess() {
        //UIHelper.gotoRenewalDebitActivity(mContext);
        finish();
    }

    private void setCouponData(String saveMoney) {
        mBindingView.tvCouponDiscount.setText(String.format("-%s"+getResources().getString(R.string.money),new BigDecimal(saveMoney).setScale(2).toString()));
        mBindingView.txtYouhuiquan.setText(String.format("%s"+getResources().getString(R.string.money),new BigDecimal(saveMoney).setScale(2).toString()));
        BigDecimal realMoney = realPay.subtract(new BigDecimal(saveMoney)).setScale(2, RoundingMode.DOWN);
        mBindingView.tvExtendMoney.setText(String.format("%s"+getResources().getString(R.string.money),realMoney.toString()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1&&resultCode==RESULT_OK){
            couponId=data.getLongExtra("couponId",-1);
            String saveMoney=data.getStringExtra("saveMoney");
            setCouponData(saveMoney);
        }
    }
}
