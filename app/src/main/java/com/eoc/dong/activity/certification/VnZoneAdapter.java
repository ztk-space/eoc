package com.eoc.dong.activity.certification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseRecyclerAdapter;
import com.eoc.dong.databinding.DialogItemAuthBinding;

/**
 * Created by Administrator on 2017/11/24.
 */

public class VnZoneAdapter extends BaseRecyclerAdapter<DialogItemAuthBinding,VnZone> {
    public VnZoneAdapter(Context mContext) {
        super(mContext);
    }


    @Override
    protected int setLayoutResId() {
        return R.layout.dialog_item_auth;
    }

    @Override
    public void onBindViewHolder(DialogItemAuthBinding binding, VnZone itemData, RecyclerView.ViewHolder holder, int position) {

          binding.tvAuthVnChannel.setText(itemData.getName());
    }



}
