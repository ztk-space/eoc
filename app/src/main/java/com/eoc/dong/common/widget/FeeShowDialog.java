package com.eoc.dong.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.eoc.dong.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class FeeShowDialog extends Dialog {
    private Context context;
    private TextView placeServeMoney;
    private TextView interestMoney;
    private String inter;
    private String service;
    public FeeShowDialog(Context context, String inter,String service) {
        super(context, R.style.bottom_select_dialog);
        this.context = context;
        this.inter=inter;
        this.service=service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_fee_show, null);
        placeServeMoney = ((TextView) view.findViewById(R.id.tv_platform_service));
        interestMoney = ((TextView) view.findViewById(R.id.tv_inter));

        //AutoUtils.auto(view);
        setContentView(view);
       FullScreen((Activity)context,this,0.8);
        ButterKnife.bind(this);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(false);
        initView();

    }


    private void initView() {

        placeServeMoney.setText(service+context.getResources().getString(R.string.money));

        interestMoney.setText(inter+context.getResources().getString(R.string.money));
    }

    @OnClick({R.id.tv_know})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_know:
                dismiss();
                break;
        }
    }
    public static void FullScreen(Activity activity, Dialog dialog, double scale) {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        //p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * scale);    //宽度设置为全屏
        dialog.getWindow().setAttributes(p);     //设置生效
    }



}
