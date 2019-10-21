package com.eoc.dong.activity.order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityRepaySuccessBinding;

public class RepaySuccessActivity extends BaseActivity<RepaySuccessActivityPresenter, ActivityRepaySuccessBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_success);
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        boolean isRenewal = intent.getBooleanExtra("isRenewal", false);
        if(isRenewal){
            setTitle("还款成功");
            mBindingView.tvHuanInfo.setText("恭喜您！还款成功");
        }else {
            setTitle("续期成功");
            mBindingView.tvHuanInfo.setText("恭喜您！续期成功");

        }

        initListener();
    }



    public void initListener() {
        mBindingView.btnLoanAgain.setOnClickListener(v -> {
            UIHelper.gotoMainActivityWithAuth(mContext);
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            UIHelper.gotoMainActivityWithAuth(mContext);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
