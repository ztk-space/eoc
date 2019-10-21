package com.eoc.dong.activity.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.activity.register.RegisterActivity;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.VerificationCodeResponse;
import com.eoc.dong.common.utils.Base64;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SecretUtil;
import com.eoc.dong.databinding.ActivityForgetPasswordBinding;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordActivityPresenter, ActivityForgetPasswordBinding> {
    private MyCountDownTimer mc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.login_forget_pwd));
    }
    @OnClick({R.id.forget_exit,R.id.re_forgetgetcode,R.id.re_forget_getmsgcode})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.re_forgetgetcode:
                getCode();
                break;
            case R.id.re_forget_getmsgcode:
                getMsgCode();
                break;
            case R.id.forget_exit:
                String phone = mBindingView.etForgetPhone.getText().toString();
                String msgcode = mBindingView.etForgetGetmsgcode.getText().toString();
                String code = mBindingView.etForgetGetcode.getText().toString();
                String pwd = SecretUtil.md5(mBindingView.etForgetPwd.getText().toString());
                String pass = SecretUtil.md5(mBindingView.etForgetPass.getText().toString());
                if(CommonUtil.isEmpty(phone)){
                    CommonUtil.showToast(getResources().getString(R.string.login_toast_phone));
                } else if(CommonUtil.isEmpty(code)){
                    CommonUtil.showToast(getResources().getString(R.string.login_toast_img_code));
                }else if(CommonUtil.isEmpty(msgcode)){
                    CommonUtil.showToast(getResources().getString(R.string.login_toast_sms_code));
                }else if(CommonUtil.isEmpty(pwd)){
                    CommonUtil.showToast(getResources().getString(R.string.setting_password));
                }else if(CommonUtil.isEmpty(pass)){
                    CommonUtil.showToast(getResources().getString(R.string.setting_password1));
                }else{
                    if(mBindingView.etForgetPwd.getText().toString().equals(mBindingView.etForgetPass.getText().toString())){
                        mPresenter.getForgetPassword(phone,msgcode,pwd,pass);
                    }else {
                        CommonUtil.showToast(getResources().getString(R.string.passwordtwice));
                    }
                 }

                break;
        }
    }
    //获取图形验证码
    public void getCode(){
        RetrofitHelper.getRetrofit().create(Api.class).getGraphic(mBindingView.etForgetPhone.getText().toString()).enqueue(new Callback<BaseResponse<VerificationCodeResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<VerificationCodeResponse>> call, Response<BaseResponse<VerificationCodeResponse>> response) {
                if (response.body().isSuccess()){
                    CommonUtil.showToast(getResources().getString(R.string.login_hint_img_code_go));
                    convertImage(response.body().getData().getBase64());
                    Log.i("TAG",response.body().getData().getBase64());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<VerificationCodeResponse>> call, Throwable t) {

            }
        });

    }
    //获取Base64位验证码转换图片
    private void convertImage(String base) {
        mBindingView.reForgetgetcode.setVisibility(View.GONE);
        mBindingView.imvRet.setVisibility(View.VISIBLE);
        byte[] decode = Base64.decode(base);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        mBindingView.imvRet.setImageBitmap(bitmap);
        mBindingView.liGetmsgcode.setVisibility(View.VISIBLE);
    }
    //获取短信验证码
    private void getMsgCode() {
        RetrofitHelper.getRetrofit().create(Api.class).getMsgCode(mBindingView.etForgetPhone.getText().toString(),mBindingView.etForgetGetcode.getText().toString()).enqueue(new Callback<BaseResponse>() {


            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body().isSuccess()){
                    CommonUtil.showToast(getResources().getString(R.string.login_hint_img_sms_go));
                    mc = new MyCountDownTimer(6000,1000);
                    mc.start();
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            countDownStart((int) (millisInFuture / 1000));
        }

        @Override
        public void onFinish() {
            countDownFinish();
            cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            countDownChange((int) (millisUntilFinished / 1000));
        }
    }
    public void countDownStart(int countDownTime) {
        mBindingView.tvForgetmsgcode.setEnabled(false);
        mBindingView.tvForgetmsgcode.setText(countDownTime + "s");

    }
    public void countDownChange(int countDownTime) {
        mBindingView.tvForgetmsgcode.setText(countDownTime + "s");
    }
    public void countDownFinish() {
        mBindingView.tvForgetmsgcode.setEnabled(true);
        mBindingView.tvForgetmsgcode.setText(getResources().getString(R.string.login_get_code));
    }
}
