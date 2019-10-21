package com.eoc.dong.activity.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseRecyclerAdapter;
import com.eoc.dong.common.network.response.OrderListResponse;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ItemLoanRecordBinding;


/**
 * Created by Administrator on 2019/9/26.
 */

public class MyOrderAdapter extends BaseRecyclerAdapter<ItemLoanRecordBinding, OrderListResponse> {
    public MyOrderAdapter(Context mContext) {
        super(mContext);
    }


    @Override
    protected int setLayoutResId() {
        return R.layout.item_loan_record;
    }

    @Override
    public void onBindViewHolder(ItemLoanRecordBinding binding, OrderListResponse itemData, RecyclerView.ViewHolder holder, int position) {
        binding.txtOrdernumber.setText(String.format(mContext.getResources().getString(R.string.lr_num)+"：%s",itemData.orderNo));
        binding.txtRepay.setText(String.format("%s"+mContext.getResources().getString(R.string.money),itemData.needPayMoney));
        binding.txtPrice.setText(String.format("%s"+mContext.getResources().getString(R.string.money),itemData.borrowMoney));
        binding.txtTime.setText(itemData.limitDays+mContext.getResources().getString(R.string.lr_day));

        holder.itemView.setOnClickListener(v -> UIHelper.gotoOrderDetailsActivity(mContext,itemData.id));
        binding.btnToRenewal.setOnClickListener(v -> UIHelper.gotoRenewalActivity(mContext,itemData.id));
        binding.btnToRepay.setOnClickListener(v -> UIHelper.gotoRepaytActivity(mContext,itemData.id,itemData.needPayMoney.doubleValue()));

        if (itemData.status==0){
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_sqz));
            binding.llStatus.setVisibility(View.GONE);
        }else if (itemData.status==1){

            binding.llStatus.setVisibility(View.GONE);
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_shz));
        }else if (itemData.status==2){

            binding.llStatus.setVisibility(View.GONE);
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_ddk));
        }else if (itemData.status==3){
            binding.llStatus.setVisibility(View.VISIBLE);
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_dhk));
        }else if (itemData.status==5){

            binding.llStatus.setVisibility(View.VISIBLE);
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_yq));
        }else if (itemData.status==6){
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_yhk));
            binding.llStatus.setVisibility(View.GONE);

        }else if (itemData.status==7){
            binding.llStatus.setVisibility(View.GONE);
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_refuse));
        }else if (itemData.status==8){

            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_hz));
        }else if (itemData.status==9){
            binding.llStatus.setVisibility(View.GONE);
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_ckz));
        }else {
            binding.llStatus.setVisibility(View.GONE);
            binding.orderState.setText(mContext.getResources().getString(R.string.lr_status_cksb));
        }
    }



}
