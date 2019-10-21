package com.eoc.dong.activity.set;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivitySettingBinding;

import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingActivityPresenter, ActivitySettingBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.setting_title));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
        @OnClick({R.id.set_pwd, R.id.set_suggest, R.id.set_update, R.id.set_clear_memory,R.id.exit})
        public void OnClick(View view) {
            switch (view.getId()) {
                case R.id.set_pwd:           //修改密码
                   UIHelper.gotoChangePasswordAvtivity(mContext);
                    break;
                case R.id.set_suggest:        //意见反馈
                    UIHelper.gotoFeedbackAvtivity(mContext);
                    break;
                case R.id.set_update:         //版本升级

                    break;
                case R.id.set_clear_memory:      //清理缓存
                    Glide.get(mContext).clearMemory();
                    CommonUtil.showToast(getResources().getString(R.string.setting_toast_clear));
                    break;
                case R.id.exit:      //退出
                    dropOut();
                    break;

            }
        }
   public void dropOut(){
       SharedPreUtil.saveData("uuid","");
       SharedPreUtil.saveData("id","");
       SharedPreUtil.saveData("payPwd","");
       SharedPreUtil.saveData("userName","");
       SharedPreUtil.saveData("password","");
       SharedPreUtil.saveData("phone","");
       SharedPreUtil.saveData("money","");
       SharedPreUtil.saveData("userType","");
       SharedPreUtil.saveData("authStatus","");
       SharedPreUtil.saveData("token","");
       SharedPreUtil.saveData("isLogin",false);
       UIHelper.gotoLoginActivityWithLogOut(mContext);

   }
}
