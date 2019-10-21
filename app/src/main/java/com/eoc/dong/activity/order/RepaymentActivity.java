package com.eoc.dong.activity.order;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.WebUrlResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.DigestUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityRepaymentBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepaymentActivity extends BaseActivity<RepaymentActivityPresenter, ActivityRepaymentBinding> {

    private String id;
    private int money;
    private String content;
    public static boolean wxPaySuccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repayment);

        wxPaySuccess = false;
    }


    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.lr_btn_pay));
        id = getIntent().getStringExtra("id");
        money = getIntent().getExtras().getInt("money");
        Log.i("TAG",id+"");
        Log.i("TAG",money+"");
        mBindingView.tvPrice.setText(money+getResources().getString(R.string.money));
    }

    /**
     * change vn pay
     * 1 pay
     * 2 renewal
     * buyer_info  姓名 *|*邮箱 *|*手机号*|* 地址
     *  *|*
     * @param view
     */
    @OnClick({R.id.btn_submit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_submit:
                //mPresenter.rentPay(id,money+"");
                getUserInfoVN();
                break;

        }
    }

    public void getUserInfoVN(){
        RetrofitHelper.getRetrofit().create(Api.class).getVNuserInfo().enqueue(new Callback<BaseResponse<VNUserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<VNUserInfo>> call, Response<BaseResponse<VNUserInfo>> response) {
                if (response.body().isSuccess()){
                    VNUserInfo data = response.body().getData();
                    if (data!=null){
                       // payVn(data);
                        String moneys = String.valueOf(money);
                        getWebUrl(id,moneys);
                    }
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<com.eoc.dong.activity.order.VNUserInfo>> call, Throwable t) {

            }
        });
    }
    public void getWebUrl(String orderId,String bindPayMoney){
        RetrofitHelper.getRetrofit().create(Api.class).getWebUrl("1", MyApplication.getInt("id",0)+"",orderId,bindPayMoney).enqueue(new Callback<BaseResponse>() {
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


    private void payVn(VNUserInfo info) {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        String merchant_site_code="59255";
        String return_url="http://47.96.24.144/loan/Pay_nlPaymentNoticeSuccess.html";
        String receiver="jackwq83@163.com";
        String transaction_info=id+"~"+"1"+"~"+"-1"+"~"+new Random().nextInt(10000);
        String order_code=id+timeString;
        String price=money+"";
        String currency="vnd";
        String quantity="1";
        String tax="0";
        String discount="0";
        String fee_cal="0";
        String fee_shipping="0";
        String notify_url="http://47.96.24.144/loan/Pay_nlPaymentNoticeSuccess.html";
        String order_description="test";
        //String buyer_info="ajksdh|dasjk@163.com|864545|sadsad";
        String buyer_info=info.getUser().getName()+"*|*"+info.getAuthBasic().getEmail()+"*|*"+ SharedPreUtil.getString("phone","")+"*|*"+info.getAuthBasic().getHomeAddr();
        String affiliate_code="123456";
        String md5Encrpytion = DigestUtil.MD5Encrpytion(merchant_site_code + ' ' + return_url + ' ' + receiver + ' ' + transaction_info + ' ' + order_code + ' ' + price
                + ' ' + currency + ' ' + quantity + ' ' + tax + ' ' + discount + ' ' + fee_cal + ' ' + fee_shipping  + ' ' + order_description + ' ' +
                buyer_info + ' ' + affiliate_code + ' ' + "dd15e71ff6076125495fff15fd132c5d");
        String secure_code=md5Encrpytion;
        String url="https://www.nganluong.vn/checkout.php?merchant_site_code="+merchant_site_code+"&return_url="+return_url+"&receiver="+receiver+"&transaction_info="+transaction_info+"&order_code="+order_code+"&price="+price+"&currency="+currency+"&quantity="+quantity+"&tax="+tax+"&discount="+discount+"&fee_cal="+fee_cal+"&fee_shipping="+fee_shipping+"&secure_code="+secure_code+"&notify_url="+notify_url+"&order_description="+order_description+"&buyer_info="+buyer_info+"&affiliate_code="+affiliate_code;

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (wxPaySuccess){
            UIHelper.gotoRepaySuccessActivity(mContext);
        }
    }

    public void sendCode(String code) {

    }
}
