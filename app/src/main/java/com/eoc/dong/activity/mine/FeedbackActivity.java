package com.eoc.dong.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.databinding.ActivityFeedbackBinding;

public class FeedbackActivity extends BaseActivity<FeedbackActivityPresenter, ActivityFeedbackBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.setting_suggest));
        initListener();
    }



    public void initListener() {

        mBindingView.btnFeedbackCommit.setOnClickListener(v ->
                mPresenter.postData(mBindingView.edContent.getText().toString(),mBindingView.edFeedbackPhone.getText().toString()));
    }
}
