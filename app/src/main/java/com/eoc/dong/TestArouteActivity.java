package com.eoc.dong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by CodeFatCat on 2019/4/15
 */
@Route(path = "/dfdffgg/tests/activitys")
public class TestArouteActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toast.makeText(this, "success hhh", Toast.LENGTH_SHORT).show();
    }
}
