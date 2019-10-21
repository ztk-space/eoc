package com.eoc.dong.activity.login;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupWindow;

import com.eoc.dong.R;

import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.response.LoginResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SecretUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityLoginBinding;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginActivityPresenter, ActivityLoginBinding> {
    PopupWindow mPopWindow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginTrue();
        mBindingView.ivFaceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String pwd = SecretUtil.md5(mBindingView.etPsw.getText().toString());
                 if (CommonUtil.isEmpty(pwd)) {
                    CommonUtil.showToast(getResources().getString(R.string.setting_password));
                }
                else if (CommonUtil.isEmpty(mBindingView.etPhone.getText().toString())) {
                    CommonUtil.showToast(getResources().getString(R.string.login_toast_phone));
                }else {
                     mPresenter.goLogin(mBindingView.etPhone.getText().toString(),pwd);
                 }
                 }
        });
    }

  public void goMain(){

      UIHelper.gotoMainActivityWithAuth(this);
      finish();
     // phoneLogin();
  }

    private void loginTrue() {
        String token = SharedPreUtil.getString("token", "");
        if(!"".equals(token)){
            UIHelper.gotoMainActivityWithAuth(this);
            finish();
        }
    }



    @Override
    protected void initView() {
        hideTitleBar();

//        if(SharedPreUtil.getBoolean("isFirst",true)){
//            showPopupWindow();
//            SharedPreUtil.saveData("isFirst",false);
//        }

    }
    @OnClick({R.id.tv_register,R.id.re_forgetpassword})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:           //注册界面
                UIHelper.gotoRegisterAvtivity(mContext);
                break;
            case R.id.re_forgetpassword:
                UIHelper.gotoForgetPasswordAvtivity(mContext);
                break;
        }

    }
    public static int APP_REQUEST_CODE = 99;

    public void phoneLogin() {
        final Intent intent = new Intent(mContext, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN).setDefaultCountryCode("VN");
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }













































    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.privacy_rights, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());

        Button button = contentView.findViewById(R.id.btn_agree);
        WebView webView=contentView.findViewById(R.id.webview0);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);//主要是这句
        webSettings.setJavaScriptEnabled(true);//启用js
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://www.baidu.com");
        contentView.setFocusable(true); // 这个很重要
        contentView.setFocusableInTouchMode(true);
        mPopWindow.setFocusable(false);// 这个很重要
        mPopWindow.setOutsideTouchable(false);
        contentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mPopWindow.dismiss();
                    finish();
                    return true;
                }
                return false;
            }
        });
        View rootview = LayoutInflater.from(LoginActivity.this).inflate(R.layout.activity_login, null);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        View decorView = LoginActivity.this.getWindow().getDecorView();
        decorView.post(new Runnable() {
            @Override
            public void run() {
                if (null != mPopWindow) {
                    mPopWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
                }
            }
        });
    }


}
