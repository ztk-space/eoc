package com.eoc.dong.activity.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.response.OrderListResponse;
import com.eoc.dong.databinding.ActivityMyOrderBinding;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends BaseActivity<MyOrderActivityPresenter, ActivityMyOrderBinding> {
    MyOrderAdapter myOrderAdapter;
    private List<OrderListResponse> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.mine_order));
        mPresenter.getMyOlder();
        myOrderAdapter = new MyOrderAdapter(this);
        mBindingView.rcMyOrder.setLayoutManager(new LinearLayoutManager(this));
        mBindingView.rcMyOrder.setAdapter(myOrderAdapter);
    }


    public void getOrder(List<OrderListResponse> listResponses){
        list = new ArrayList<>();
        myOrderAdapter.clear();
        list.clear();
        for (OrderListResponse item : listResponses) {
            if (item.status==6){

            }else {

                list.add(item);
            }
        }
        myOrderAdapter.addAll(list);
    }
}
