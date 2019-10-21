package com.eoc.dong.activity.mine;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.response.ContactInformateResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.databinding.ActivityContactServiceBinding;

import java.util.List;

public class ContactServiceActivity extends BaseActivity<ContactServiceActivityPressenter, ActivityContactServiceBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_service);
        mBindingView.llSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactServiceActivity.this,SecretActivity.class));
            }
        });
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.mine_about));
        mPresenter.getMessageData();
    }
    public void setPhone(ContactInformateResponse data) {
        if (data!=null){
            if (data.phone!=null){
                mBindingView.tvPhone.setText(data.phone);
                mBindingView.tvVnPhone.setText(data.phone);
                mBindingView.rlPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data1 = Uri.parse("tel:" +data.phone);
                        intent.setData(data1);
                        startActivity(intent);
                    }
                });

            }
            if (data.qq!=null){
                mBindingView.tvQq.setText(data.qq);
                mBindingView.tvQq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openQQ(data.qq);
                    }
                });
            }
            if (data.weixin!=null){
                mBindingView.tvWeixin.setText(data.weixin);
                mBindingView.tvWeixin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWechatApi();
                    }
                });

            }
        }

    }
    /**
     * 跳转到微信
     */
    private void getWechatApi(){
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // TODO: handle exception
            CommonUtil.showToast(getResources().getString(R.string.about_toast_wx));
        }
    }
    private void openQQ(String qqcode) {
        boolean qqClientAvailable = isQQClientAvailable(mContext);
        if(qqClientAvailable){
            String one="mqqwpa://im/chat?chat_type=wpa&uin=";
            String two="&version=1";
            String qq=one+qqcode+two;
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qq)));
        }else {
            CommonUtil.showToast(getResources().getString(R.string.about_toast_qq));
        }
    }

    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
}
