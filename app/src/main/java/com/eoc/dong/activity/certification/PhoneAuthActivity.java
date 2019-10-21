package com.eoc.dong.activity.certification;

import android.os.Bundle;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.activity.order.VNUserInfo;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityPhoneAuthBinding;
import com.eoc.dong.util.CommonUtil;

import retrofit2.Call;
import retrofit2.Response;

public class PhoneAuthActivity extends BaseActivity<PhoneAuthActivityPresenter, ActivityPhoneAuthBinding> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.auth_phone_title));
        //mPresenter.phoneAuth();
        getUserInfoVN();
    }

    public void getUserInfoVN(){
        RetrofitHelper.getRetrofit().create(Api.class).getVNuserInfo().enqueue(new BaseCallBack<BaseResponse<VNUserInfo>>() {
            @Override
            public void onSuccess(Call<BaseResponse<VNUserInfo>> call, Response<BaseResponse<VNUserInfo>> response) {
                if (response.body().isSuccess()){
                    VNUserInfo data = response.body().getData();
                    if (data!=null){
                        authPhone(data);
                    }
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }

    private void authPhone(VNUserInfo vnUserInfo) {
        VNUserInfo.AuthIdentityBean authIdentity = vnUserInfo.getAuthIdentity();
        if (authIdentity!=null){
            getAuthUrl("https://talosopen.shujumohe.com/box/vn/operator?box_token=a9fa3efbffc740d2acbc3f37783b4b62&passback_params=xxxx&cb=http://www.wyp.com&identity_code="+authIdentity.getIdentityNum()+"&user_mobile="+ SharedPreUtil.getString("phone","")+"&fix=1");
        }else {
            getAuthUrl("https://talosopen.shujumohe.com/box/vn/operator?box_token=a9fa3efbffc740d2acbc3f37783b4b62&passback_params=xxxx&cb=http://www.wyp.com&identity_code=xxxx&user_mobile="+SharedPreUtil.getString("phone","")+"&fix=1");
       }
    }



    public void getAuthUrl(String url) {

        mBindingView.webview.loadUrl(url);
        //声明WebSettings子类
        WebSettings webSettings = mBindingView.webview.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        // 设置Web视图
        mBindingView.webview.setWebViewClient(new MyWebViewClient());
        mBindingView.webview.setWebChromeClient(mWebChromeClientBase);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        // 使用localStorage则必须打开
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
    }
    // 监听 所有点击的链接，如果拦截到我们需要的，就跳转到相对应的页面。
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            //运营商
            if(url.contains("www.wyp.com")){
                //HashMap<String, String> map = new HashMap<>();
                //map.put("type","yys");
                //UIHelper.gotoImproveAuthActivity(mContext,2);
                return true;
            }


            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);

        }
    }

    private WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();

    private class WebChromeClientBase extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            // mActivity.setProgress(newProgress * 1000);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            // TODO Auto-generated method stub
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url,
                                           boolean precomposed) {
            // TODO Auto-generated method stub
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog,
                                      boolean isUserGesture, Message resultMsg) {
            // TODO Auto-generated method stub
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

    }
}
