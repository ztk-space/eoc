package com.eoc.dong.activity.main;

import android.os.Bundle;
import android.text.Html;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.response.AboutUsDetailResponse;
import com.eoc.dong.databinding.ActivityAboutUsBinding;

import java.util.List;

public class AboutUsActivity extends BaseActivity<AboutActivityPresenter, ActivityAboutUsBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    @Override
    protected void initView() {
        setTitle("关于我们");
        mPresenter.getAboutUsDetail();
    }


    public void setAboutUsDetail(List<AboutUsDetailResponse> content) {
        if (content!=null){

            mBindingView.txtDetail.setText(Html.fromHtml(content.get(0).getText()));
        }
    }
}

