package com.eoc.dong.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.databinding.ActivityAddMoneyBinding;

public class AddMoneyActivity extends BaseActivity<AddMoneyActivityPresenter, ActivityAddMoneyBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.increase_title));
        mBindingView.tvToLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getData(mBindingView.tvContent.getText().toString());
            }
        });
    }
    public void getData() {
        CommonUtil.showToast(getResources().getString(R.string.increase_toast));
        finish();
    }
}
