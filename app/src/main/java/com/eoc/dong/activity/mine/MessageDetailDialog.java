package com.eoc.dong.activity.mine;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eoc.dong.R;
import com.zzhoujay.richtext.RichText;

/**
 * Created by Administrator on 2017/11/24.
 */

public class MessageDetailDialog extends Dialog {
    private Context mContext;
    private TextView mTvTitle;
    private TextView mTvContent;

    public MessageDetailDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        this.mContext=context;
        createDialog();
    }

    private void createDialog() {
        View dialogLayoutView = LayoutInflater.from(mContext).
                inflate(R.layout.dialog_message_detail,null);
        this.addContentView(dialogLayoutView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mTvTitle=dialogLayoutView.findViewById(R.id.tv_title);
        mTvContent=dialogLayoutView.findViewById(R.id.tv_content);

        dialogLayoutView.findViewById(R.id.iv_close).setOnClickListener(v -> dismiss());

        //AutoUtils.auto(dialogLayoutView);
        this.setContentView(dialogLayoutView);
        this.setCancelable(false);
    }

    public void setTitle(String title){
        mTvTitle.setText(title);
    }

    public void setContent(String content){
        RichText.fromHtml(content).into(mTvContent);
    }
}
