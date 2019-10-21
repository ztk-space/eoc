package com.eoc.dong.activity.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.response.OrderDetailResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.DeviceUuidFactory;
import com.eoc.dong.common.utils.IpUtils;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.common.widget.FeeShowDialog;
import com.eoc.dong.databinding.ActivityPlaceanOrderBinding;

import java.net.SocketException;

public class PlaceanOrderActivity extends BaseActivity<PlaceanOrderActivityPresenter, ActivityPlaceanOrderBinding> {

    private int days;
    private String money;
    private String uniquePsuedoID;
    private String phoneIp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placean_order);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.ld_title));
        days = getIntent().getExtras().getInt("days");
        money = getIntent().getExtras().getString("money");
        mPresenter.getApplyAnOrder(money,days);
        uniquePsuedoID = DeviceUuidFactory.getUniquePsuedoID();
        try {
            String localIPAddress = IpUtils.getLocalIPAddress();
            Log.i("ZTK","内网ip"+localIPAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                phoneIp = IpUtils.GetNetIp();
                Log.i("ZTKK","ip===="+ phoneIp);
            }
        }).start();
    }
    //
    public void setApplyAnOrder(OrderDetailResponse response){
        mBindingView.itemLoanPrice.setContent(response.borrowMoney+getResources().getString(R.string.money));
        mBindingView.itemLoanTime.setContent(response.limitDays+getResources().getString(R.string.lr_day));
        mBindingView.itemRealPrice.setContent(response.realMoney+getResources().getString(R.string.money));
        mBindingView.itemPayPrice.setContent(response.borrowMoney+getResources().getString(R.string.money));
        mBindingView.txtZongfeitong.setText(response.borrowMoney.subtract(response.realMoney)+getResources().getString(R.string.money));
        if (response.bankNum!=null){
            mBindingView.itemBankcard.setText("****"+response.bankNum.substring(response.bankNum.length()-4));
        }
        mBindingView.itemPayOverdue.setContent(response.interest+getResources().getString(R.string.money));
        mBindingView.tvTotalRentMoney.setText(response.needPayMoney+getResources().getString(R.string.money));
        mBindingView.txtLimitPayTime.setText(response.limitPayTime.split(" ")[0]);
        mBindingView.txtRealPayMoney.setText(response.needPayMoney+getResources().getString(R.string.money));
        mBindingView.btnLoanAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uniquePsuedoID.isEmpty()){
                    CommonUtil.showToast("Mời cấp quyền truy cập thông tin của điện thoại.");
                    UIHelper.gotoMainActivityWithAuth(mContext);
                }else{
                    mPresenter.setSubmitOrder(response.id, phoneIp,uniquePsuedoID);
                }

            }
        });

        mBindingView.imgLoanDetailTishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeeShowDialog dialog = new FeeShowDialog(mContext, response.interest.toString(),response.borrowMoney.subtract(response.realMoney).subtract(response.interest).toString());
                dialog.show();
            }
        });
    }
}
