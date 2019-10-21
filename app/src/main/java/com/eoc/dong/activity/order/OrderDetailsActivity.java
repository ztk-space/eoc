package com.eoc.dong.activity.order;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.response.RentOrderShellResponse;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.common.widget.FeeShowDialog;
import com.eoc.dong.databinding.ActivityOrderDetailsBinding;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsActivityPresenter, ActivityOrderDetailsBinding> {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.rd_title));
        mPresenter.getOrderDetail(getIntent().getIntExtra("id",0));

    }



    public void setOrderDetail(RentOrderShellResponse data) {
        setBaseData(data);
    }



    /**
     * 订单基本信息部分
     */
    private void setBaseData(RentOrderShellResponse response) {
        mBindingView.tvOrderNum.setText(getResources().getString(R.string.rd_jkbh)+"："+response.orderNo);
        mBindingView.itemApplyTime.setContent(response.gmtDatetime);
        mBindingView.itemLoanPrice.setContent(response.borrowMoney+getResources().getString(R.string.money));
        mBindingView.itemLoanTime.setContent(response.sysRate.limitDays+getResources().getString(R.string.lr_day));
        mBindingView.itemRealPrice.setContent(response.realMoney+getResources().getString(R.string.money));
        if (response.bankNum!=null){
            mBindingView.itemBankcard.setText("****"+response.bankNum.substring(response.bankNum.length()-4));
        }
        mBindingView.txtZongfeitong.setText(response.borrowMoney.subtract(response.realMoney)+getResources().getString(R.string.money));
        mBindingView.itemPayPrice.setContent(response.borrowMoney+getResources().getString(R.string.money));
        if (response.overdueMoney!=null){
            mBindingView.itemPayOverdue.setVisibility(View.VISIBLE);
            mBindingView.itemPayOverdue.setContent(response.overdueMoney+getResources().getString(R.string.money));
        }else {
            mBindingView.itemPayOverdue.setVisibility(View.GONE);
        }

        mBindingView.tvTotalRentMoney.setText(response.needPayMoney+getResources().getString(R.string.money));
        mBindingView.txtLimitPayTime.setText(response.limitPayTime.split(" ")[0]);
        mBindingView.txtRealPayMoney.setText(response.needPayMoney+getResources().getString(R.string.money));

        if (response.status==3||response.status==4||response.status==5){
            mBindingView.llToRepay.setVisibility(View.VISIBLE);
        }else {
            mBindingView.llToRepay.setVisibility(View.GONE);
        }

        mBindingView.imgLoanDetailTishi.setOnClickListener(v -> {
            FeeShowDialog dialog = new FeeShowDialog(mContext, response.interest.toString(),response.borrowMoney.subtract(response.realMoney).subtract(response.interest).toString());
            dialog.show();
        });
        //还款
        mBindingView.btnToRepay.setOnClickListener(v -> UIHelper.gotoRepaymentActivity(mContext,response.id+"",Integer.valueOf((int) response.needPayMoney.doubleValue())));
        //申请续期
        mBindingView.btnToRenewal.setOnClickListener(v -> UIHelper.gotoRenewalActivity(mContext,response.id));
    }
}
