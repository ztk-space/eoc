package com.eoc.dong.activity.certification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.response.BankAuthResponse;
import com.eoc.dong.common.utils.AssetsBankInfo;
import com.eoc.dong.common.utils.Base64;
import com.eoc.dong.databinding.ActivityBankCardAuthBinding;
import com.eoc.dong.util.CommonUtil;


import butterknife.OnClick;

public class BankCardAuthActivity extends BaseActivity<BankCardAuthActivityPresenter, ActivityBankCardAuthBinding> {
    private int flag=1;
    String name;
    BankAuthResponse response;
    String ID;
    String phone;
    String bankcard;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_auth);
    }

    @Override
    protected void initView() {
        setTitle("银行卡认证");
       mPresenter.getBankAuthInfo();
    }


    @OnClick({R.id.btn_bankcard_auth})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_bankcard_auth:

                name = mBindingView.edName.getText().toString();

                ID = mBindingView.edID.getText().toString();

                bankcard = mBindingView.edCard.getText().toString();

                phone = mBindingView.edPhone.getText().toString();
                String nameOfBank = AssetsBankInfo.getNameOfBank(mContext, mBindingView.edCard.getText().toString());//获取银行卡的信息
                check();
                break;
        }
    }

    public void countDownStart(int countDownTime) {
        mBindingView.textAuthenCode.setEnabled(false);
        mBindingView.textAuthenCode.setText(String.format("%d秒后重新获取", countDownTime));
    }

    public void countDownChange(int countDownTime) {
        mBindingView.textAuthenCode.setText(String.format("%d秒后重新获取", countDownTime));
    }

    public void countDownFinish() {
        mBindingView.textAuthenCode.setEnabled(true);
        mBindingView.textAuthenCode.setText("获取验证码");
    }

    public void finishActivity() {
        finish();
    }

    public void setBankInfo(BankCardsListResopnse data) {
        //data为null则该用户还未进行银行卡认证
        if (data == null||data.bankNum==null){

            return;
        }

        mBindingView.edName.setText(data.identityName);
        mBindingView.edID.setText(data.identityNum);
        mBindingView.edCard.setText(data.bankNum);
        mBindingView.edPhone.setText(data.bankPhone);
        mBindingView.btnBankcardAuth.setText("解绑银行卡");
        flag=3;

    }

    public void setVerificationCode(String base64) {
        byte[] decode = Base64.decode(base64);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        mBindingView.ivVerificationCode.setImageBitmap(bitmap);
    }

    public void authSuccess() {
        CommonUtil.showToast("提交成功");
        finish();
    }

    public void cancleSuccess() {
        flag=1;
        mBindingView.btnBankcardAuth.setText("修改银行卡");
    }

    public void authSuccess(BankAuthResponse response) {
        this.response=response;
        mBindingView.rlCode.setVisibility(View.VISIBLE);
        flag=2;
    }
    public void check(){
        if(flag==1){
            mPresenter.postData(name, ID, bankcard, phone);
        }else if(flag==2){
            mPresenter.postsData(mBindingView.edCode.getText().toString(),response.MCHNTSSN.get(0));
        }else if (flag==3){
           mPresenter.cancleBank();
        }
    }
}
