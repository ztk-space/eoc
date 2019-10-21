package com.eoc.dong.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.eoc.dong.R;
import com.eoc.dong.common.utils.CommonUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 公司：杭州融科网络科技
 * 刘宇飞 创建 on 2017/3/14.
 * 描述：
 */

public class WaitDialog extends Dialog {
    private AnimationDrawable animationDrawable;
    private int pro=0;
    private static final int MSG = 1;
    private Context context;
    private String setText;

    public WaitDialog(Context context,String s) {
        super(context, R.style.bottom_select_dialog);
        this.context = context;
        setText=s;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_wait, null);

        ImageView img = (ImageView)view.findViewById(R.id.img);
        TextView textView = (TextView) view.findViewById(R.id.tv_wait);
        textView.setText(setText);
        animationDrawable = (AnimationDrawable) img.getDrawable();
        /*if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        }*/
        animationDrawable.start();

        //AutoUtils.auto(view);
        setContentView(view);
        CommonUtil.FullScreen((Activity)context,this,0.8);
        ButterKnife.bind(this);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(false);
        initView();

    }

    private void initView() {

    }

    @OnClick({R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sure://确定
                dismiss();
                break;
        }
    }
}
