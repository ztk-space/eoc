package com.eoc.dong.activity.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseRecyclerAdapter;
import com.eoc.dong.databinding.ItemMessageBinding;


/**
 * Created by Administrator on 2017/11/24.
 */

public class MessageListAdapter extends BaseRecyclerAdapter<ItemMessageBinding,MessageModel> {
    public MessageListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.item_message;
    }

    @Override
    public void onBindViewHolder(ItemMessageBinding binding, MessageModel itemData, RecyclerView.ViewHolder holder, int position) {
        binding.txtTitle.setText(itemData.getTitle());
        binding.messageTime.setText(itemData.getTime());
        binding.messageContent.setText(itemData.getContent());

        holder.itemView.setOnClickListener(v -> {
            MessageDetailDialog dialog = new MessageDetailDialog(mContext);
            dialog.setTitle(itemData.getTitle());
            dialog.setContent(itemData.getContent());
            dialog.show();
        });
    }
}
