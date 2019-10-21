package com.eoc.dong.activity.register;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.LoginResponse;
import com.eoc.dong.common.network.response.VerificationCodeResponse;
import com.eoc.dong.common.utils.Base64;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SecretUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityRegisterBinding;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity<RegisterActivityPresenter, ActivityRegisterBinding> {
    MyCountDownTimer mc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.login_registered));
    }
    @OnClick({R.id.tv_getcode,R.id.re_getmsgcode,R.id.exit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getcode:
                //获取图形验证码
               getCode();
                break;
            case R.id.re_getmsgcode:
                //获取短信验证码
                getMsgCode();
                break;
            case R.id.exit://注册
                if(mBindingView.etPwd.getText().toString().equals(mBindingView.etPass.getText().toString())){
                    getRegister();
                }else {
                    CommonUtil.showToast(getResources().getString(R.string.passwordtwice));
                }
                break;
        }

    }

    private void getRegister() {
        String pwd = SecretUtil.md5(mBindingView.etPass.getText().toString());
        if (CommonUtil.isEmpty(pwd)){
            CommonUtil.showToast(getResources().getString(R.string.setting_password1));
        }
        else if (CommonUtil.isEmpty(mBindingView.etPhone.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.login_toast_phone));
        }
        else if (CommonUtil.isEmpty(mBindingView.etGetmsgcode.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.login_toast_sms_code));
        }
        else if (CommonUtil.isEmpty(mBindingView.etPwd.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.setting_password));
        }
        else if (CommonUtil.isEmpty(mBindingView.etGetcode.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.login_toast_img_code));
        }else {
            RetrofitHelper.getRetrofit().create(Api.class).goRegister(mBindingView.etPhone.getText().toString(), mBindingView.etGetmsgcode.getText().toString(), pwd, "and").enqueue(new Callback<BaseResponse<LoginResponse>>() {
                @Override
                public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                    if (response.body().isSuccess()) {
                        finish();
                    } else {
                        CommonUtil.showToast(response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {

                }
            });
        }
    }

    private void getMsgCode() {
    RetrofitHelper.getRetrofit().create(Api.class).getMsgCode(mBindingView.etPhone.getText().toString(),mBindingView.etGetcode.getText().toString()).enqueue(new Callback<BaseResponse>() {
        @Override
        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
            if(response.body().isSuccess()){
                CommonUtil.showToast(getResources().getString(R.string.login_hint_img_sms_go));
                mc = new MyCountDownTimer(60000,1000);
                mc.start();
            }else {
                CommonUtil.showToast(response.body().getMsg());
            }
        }

        @Override
        public void onFailure(Call<BaseResponse> call, Throwable t) {
            CommonUtil.showToast(t.toString());
        }
    });
    }

    //获取图形验证码
   public void getCode(){
       RetrofitHelper.getRetrofit().create(Api.class).getGraphic(mBindingView.etPhone.getText().toString()).enqueue(new Callback<BaseResponse<VerificationCodeResponse>>() {
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
        mBindingView.reGetcode.setVisibility(View.GONE);
        mBindingView.imvRet.setVisibility(View.VISIBLE);
        byte[] decode = Base64.decode(base);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        mBindingView.imvRet.setImageBitmap(bitmap);
        mBindingView.liGetmsgcode.setVisibility(View.VISIBLE);
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
        mBindingView.tvGetmsgcode.setEnabled(false);
        mBindingView.tvGetmsgcode.setText(countDownTime + "s");

    }
    public void countDownChange(int countDownTime) {
        mBindingView.tvGetmsgcode.setText(countDownTime + "s");
    }
    public void countDownFinish() {
        mBindingView.tvGetmsgcode.setEnabled(true);
        mBindingView.tvGetmsgcode.setText(getResources().getString(R.string.login_get_code));
    }
}
