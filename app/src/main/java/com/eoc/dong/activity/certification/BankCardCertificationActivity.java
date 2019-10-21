package com.eoc.dong.activity.certification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.VnAllBankInfo;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityBankCardCertificationBinding;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class BankCardCertificationActivity extends BaseActivity<BankCardCertificationActivityPresenter, ActivityBankCardCertificationBinding> {
    private int type=1;
    private VnAllBankInfo bankInfo;
    private int flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_certification);
        change(type);
        flag = getIntent().getIntExtra("flag", 0);
    }
    @OnClick({R.id.tv_bank_type_yhk,R.id.tv_bank_type_cz,R.id.bt_auth_bank_vn,R.id.et_vn_bank_name,R.id.et_vn_zone})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_bank_type_yhk:
                type=1;
                change(type);
                break;
            case R.id.tv_bank_type_cz:
                type=2;
                change(type);
                CommonUtil.showToast("Vui lòng nhập số tài khoản trên thẻ ngân hàng");
                break;
            case R.id.bt_auth_bank_vn:
                checkNotNull();
                break;
            case R.id.et_vn_bank_name:
                AuthBankDialog authBankDialog = new AuthBankDialog(mContext, (IBankName) bankInfo -> {
                    mBindingView.etVnBankName.setText(bankInfo.getBankName());
                    this.bankInfo=bankInfo;
                    //initTest();
                }, 1);
                authBankDialog.show();
                break;
            case R.id.et_vn_zone:
                AuthBankDialog authBankDialog1 = new AuthBankDialog(mContext, (IZoneId) id -> {
                    mBindingView.etVnZone.setText(id);
                }, 2);
                authBankDialog1.show();
                break;
        }
    }

    /**
     * DAB
     */
    private void initTest() {
        RetrofitHelper.getRetrofit().create(Api.class).getPayVNByName(bankInfo.getBankName()).enqueue(new BaseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().isSuccess()){
                    Object data = response.body().getData();
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }

    private void checkNotNull() {
        if (CommonUtil.isEmpty(mBindingView.etVnName.getText().toString())){
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        }else  if (CommonUtil.isEmpty(mBindingView.etVnBankName.getText().toString())){
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        }else  if (CommonUtil.isEmpty(mBindingView.etVnBankNum.getText().toString())){
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        }else {
            if (type==1){
                if (CommonUtil.isEmpty(mBindingView.etVnFenhangName.getText().toString())){
                    CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
                }else  if (CommonUtil.isEmpty(mBindingView.etVnZone.getText().toString())){
                    CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
                }else {
                    authYHK();
                }
            }else if (type==2){
                if (CommonUtil.isEmpty(mBindingView.etVnMonth.getText().toString())){
                    CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
                }else  if (CommonUtil.isEmpty(mBindingView.etVnYear.getText().toString())){
                    CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
                }else {
                    authCZ();
                }
            }
        }
    }


    private void authCZ() {
        RetrofitHelper.getRetrofit().create(Api.class).authVNBank(SharedPreUtil.getInt("id",0),
                mBindingView.etVnBankName.getText().toString(),
                mBindingView.etVnBankNum.getText().toString(),bankInfo.getBankCode(),
                mBindingView.etVnName.getText().toString(),"2",mBindingView.etVnMonth.getText().toString(),
                mBindingView.etVnYear.getText().toString(),"",""
        ).enqueue(new BaseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().isSuccess()){
                    if (flag==1){
                        finish();
                    }else if (flag==2){
                        UIHelper.gotoMainActivityWithAuth(mContext);
                    }
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }

    private void authYHK() {
        RetrofitHelper.getRetrofit().create(Api.class).authVNBank(SharedPreUtil.getInt("id",0),mBindingView.etVnBankName.getText().toString(),
                mBindingView.etVnBankNum.getText().toString(),bankInfo.getBankCode(),mBindingView.etVnName.getText().toString(),
                "3","","",mBindingView.etVnFenhangName.getText().toString(),
                mBindingView.etVnZone.getText().toString()
        ).enqueue(new BaseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().isSuccess()){
                    if (flag==1){
                        finish();
                    }else if (flag==2){
                        UIHelper.gotoMainActivityWithAuth(mContext);
                    }

                }else {
                    Log.i("TAG","ss");
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }
    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.yhk_auth));
    }
    public void change(int flag){
        if (flag==1){
            mBindingView.llYhk.setVisibility(View.VISIBLE);
            mBindingView.llCz.setVisibility(View.GONE);
            mBindingView.tvBankTypeYhk.setBackground(getResources().getDrawable(R.drawable.shape_auth_bank_blue));
            mBindingView.tvBankTypeYhk.setTextColor(getResources().getColor(R.color.colorWhite));
            mBindingView.tvBankTypeCz.setBackground(getResources().getDrawable(R.drawable.shape_auth_bank_white));
            mBindingView.tvBankTypeCz.setTextColor(getResources().getColor(R.color.public_orange));
        }else if (flag==2){
            mBindingView.llYhk.setVisibility(View.GONE);
            mBindingView.llCz.setVisibility(View.VISIBLE);
            mBindingView.tvBankTypeYhk.setBackground(getResources().getDrawable(R.drawable.shape_auth_bank_white));
            mBindingView.tvBankTypeYhk.setTextColor(getResources().getColor(R.color.public_orange));
            mBindingView.tvBankTypeCz.setBackground(getResources().getDrawable(R.drawable.shape_auth_bank_blue));
            mBindingView.tvBankTypeCz.setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
}
