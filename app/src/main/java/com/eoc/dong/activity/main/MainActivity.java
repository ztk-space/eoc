package com.eoc.dong.activity.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eoc.dong.R;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.base.BaseFragment;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.common.widget.NavigationView;
import com.eoc.dong.databinding.ActivityMainBinding;
import com.eoc.dong.fragment.HomeFragment;
import com.eoc.dong.fragment.MineFragment;
import com.win.base_module.RouteUtils;

import butterknife.OnClick;
@Route(path = RouteUtils.User_Activity_Auth)
public class MainActivity extends BaseActivity<MainActivityPresenter,ActivityMainBinding> {

    private NavigationView mCurrTab;
    private BaseFragment mCurrFragment;
    private FragmentManager mFragmentManager;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        mCurrTab = mBindingView.nvHome;
        hideTitleBar();
        initFragment();
        mPresenter.checkUpdate();
    }





    public void initFragment() {
        if (mFragmentManager != null) {
            return;
        }
        mFragmentManager = getSupportFragmentManager();
        homeFragment=HomeFragment.newInstance();
        mCurrFragment = homeFragment;
        mFragmentManager.beginTransaction().add(R.id.fl_content, homeFragment).commitAllowingStateLoss();
        mineFragment=MineFragment.newInstance();
    }



    public void changeTab(NavigationView newTab) {
        if (mCurrTab != newTab) {
            mCurrTab.setSelected(false);
            newTab.setSelected(true);
            mCurrTab = newTab;
        }
    }

    public void changeFragment(BaseFragment newFragment) {
        if (mCurrFragment != newFragment) {
            if (!newFragment.isAdded()) {
                mFragmentManager.beginTransaction()
                        .add(R.id.fl_content, newFragment)
                        .hide(mCurrFragment)
                        .commitAllowingStateLoss();
            } else {
                mFragmentManager.beginTransaction()
                        .show(newFragment)
                        .hide(mCurrFragment)
                        .commitAllowingStateLoss();
            }

            mCurrFragment = newFragment;
        }
    }
    @OnClick({R.id.nv_home,R.id.nv_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nv_home://主页
                changeTab(mBindingView.nvHome);
                changeFragment(new HomeFragment());
                break;
            case R.id.nv_mine://我的
                changeTab(mBindingView.nvMine);
                changeFragment(new MineFragment());
                break;
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent!=null&&intent.getBooleanExtra("logout",false)){
            MyApplication.saveString("token", null);
            UIHelper.gotoLoginAvtivity(mContext);
            finish();
        }
        String faceId = intent.getStringExtra("faceId");
       // String faceUrl = MyApplication.getString("faceUrl", "");
        if (CommonUtil.isNotEmpty(faceId)){
            mPresenter.authFace(faceId);
        }

    }

}
