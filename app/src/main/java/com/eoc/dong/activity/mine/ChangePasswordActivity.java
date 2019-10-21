package com.eoc.dong.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SecretUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityChangePasswordBinding;

import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity<ChangePasswordActivityPresenter, ActivityChangePasswordBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.setting_pwd));
    }
    @OnClick({R.id.changpwdexit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.changpwdexit:
                //修改密码
                String oldpwd = SecretUtil.md5(mBindingView.etOldcode.getText().toString());
                String newpwd = SecretUtil.md5(mBindingView.etNewcode.getText().toString());
                String copwd = SecretUtil.md5(mBindingView.etConfirmcode.getText().toString());
                if (CommonUtil.isEmpty(oldpwd)){
                    CommonUtil.showToast(getResources().getString(R.string.setting_oldpassword));
                } else if (CommonUtil.isEmpty(newpwd)){
                    CommonUtil.showToast(getResources().getString(R.string.setting_password));
                } else if (CommonUtil.isEmpty(copwd)){
                    CommonUtil.showToast(getResources().getString(R.string.setting_password1));
                } else  if (CommonUtil.isEmpty(mBindingView.etChangephone.getText().toString())){
                    CommonUtil.showToast(getResources().getString(R.string.login_toast_phone));
                } else {
                    if(mBindingView.etNewcode.getText().toString().equals(mBindingView.etConfirmcode.getText().toString())){
                        mPresenter.setChangePwd(oldpwd,newpwd,copwd,mBindingView.etChangephone.getText().toString());
                    }else {
                        CommonUtil.showToast(getResources().getString(R.string.passwordtwice));
                    }

                }
                break;
        }
    }
}
