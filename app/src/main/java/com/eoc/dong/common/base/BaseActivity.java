package com.eoc.dong.common.base;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.eoc.dong.R;
import com.eoc.dong.common.language.LanguageUtil;
import com.eoc.dong.common.utils.CommonUtils;
import com.eoc.dong.common.utils.TUtil;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.databinding.ActivityBaseBinding;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * CodeFatCat
 */
public abstract class BaseActivity<E extends BasePresenter,SV extends ViewDataBinding> extends FragmentActivity {
    protected SV mBindingView;
    protected Context mContext;
    public E mPresenter;
    @NonNull
    protected ActivityBaseBinding mBaseBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //StatusBarUtil.setTransparent(this);

        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            mPresenter.mContext = this;
            mPresenter.setView(this);
        }
    }
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        //标题栏已经在activity_base 不用到每个布局里面添加
        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        mBindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBindingView.getRoot().setLayoutParams(params);
        FrameLayout mContainer = mBaseBinding.container;
        mContainer.addView(mBindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());
        initState();
        ButterKnife.bind(this);
        // 设置透明状态栏
       // StatusBarUtil.setColor(this, CommonUtils.getColor(this, com.zyf.fwms.commonlibrary.R.color.color999999),0);
        // 设置Activity始终保持横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initLisener();
        mContext =this;
        //根据设计稿设定 preview 切换至对应的尺寸
        //AutoUtils.setSize(this, false, 720, 1280);
        //自适应页面
       // AutoUtils.auto(this);
        initView();


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        //获取我们存储的语言环境 比如 "en","zh",等等
        String language = MyApplication.getString("lg","vn");
        /**
         * attach对应语言环境下的context
         */
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, language));
    }

    protected abstract void initView();

    private void initLisener() {
        mBaseBinding.commonTitle.llLiftBack.setOnClickListener(v -> onBackPressed());
    }

    /**
     * 隐藏标题栏
     */
      protected void hideTitleBar(){
          mBaseBinding.commonTitle.rlTitleBar.setVisibility(View.GONE);
      }
    /**
     * 隐藏返回箭头
     */
    protected void hideBackImg(){
        mBaseBinding.commonTitle.llLiftBack.setVisibility(View.GONE);
    }
    /**
     * 设置标题
     */
     protected void setTitle(String title){
         mBaseBinding.commonTitle.tvTitle.setText(CommonUtils.isNotEmpty(title)?title:"");
     }
    /**
     * 设置右侧文字
     */
    protected void setRightTitle(String rightTitle, View.OnClickListener listener){
        mBaseBinding.commonTitle.tvRightText.setText(CommonUtils.isNotEmpty(rightTitle)?rightTitle:"");
        mBaseBinding.commonTitle.llRightText.setVisibility(View.VISIBLE);
        mBaseBinding.commonTitle.llRightImg.setVisibility(View.GONE);
        if(listener!=null){
            mBaseBinding.commonTitle.llRightText.setOnClickListener(listener);
        }
    }

    protected String getRightTitle(){
        return  mBaseBinding.commonTitle.tvRightText.getText().toString();
    }

    protected void setRighttxtTitle(String content){
        mBaseBinding.commonTitle.tvRightText.setText(content);
    }
    /**
     * 设置右侧图片
     */
    protected void setRightImg(int img, View.OnClickListener listener){
        mBaseBinding.commonTitle.llRightText.setVisibility(View.GONE);
        mBaseBinding.commonTitle.llRightImg.setVisibility(View.VISIBLE);
        if(img>0){
            mBaseBinding.commonTitle.ivRightImg.setImageResource(img);
        }
        if(listener!=null){
            mBaseBinding.commonTitle.llRightImg.setOnClickListener(listener);
        }
    }

    /**
     * 设置右侧图片
     */
    protected void setRightImg(int img, View.OnClickListener listener,float width,float height){
        mBaseBinding.commonTitle.llRightText.setVisibility(View.GONE);
        mBaseBinding.commonTitle.llRightImg.setVisibility(View.VISIBLE);
        if(img>0){
            mBaseBinding.commonTitle.ivRightImg.setImageResource(img);
        }
        if(listener!=null){
            mBaseBinding.commonTitle.llRightImg.setOnClickListener(listener);
        }
        if(width>0&&height>0){
            ViewGroup.LayoutParams layoutParams = mBaseBinding.commonTitle.ivRightImg.getLayoutParams();
            layoutParams.width= CommonUtils.dip2px(mContext,width);
            layoutParams.height= CommonUtils.dip2px(mContext,height);
            mBaseBinding.commonTitle.ivRightImg.setLayoutParams(layoutParams);
        }
    }

    private CompositeSubscription mCompositeSubscription;
    /**
     * 添加网络请求观察者
     * @param s
     */
    public void addSubscription(Subscription s) {
        if(s==null){
            return;
        }
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    /**
     * 移除网络请求
     */
    public void removeSubscription() {
        CommonUtils.getInstance().removeSubscription();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
            this.mCompositeSubscription=null;
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }



    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeSubscription();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        ButterKnife.unbind(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initState() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //LinearLayout linear_bar = (LinearLayout) findViewById(R.id.ll_bar);

            mBaseBinding.llBar.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            int statusHeight = getStatusBarHeight();
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)  mBaseBinding.llBar.getLayoutParams();
            params.height = statusHeight;
            mBaseBinding.llBar.setLayoutParams(params);
        }
    }
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
