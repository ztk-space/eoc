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

import com.eoc.dong.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class LanguageDialog extends Dialog {
    private Context context;
    private LanguageInterface languageInterface;

    public LanguageDialog(Context context ,LanguageInterface languageInterface) {
        super(context, R.style.bottom_select_dialog);
        this.context = context;
        this.languageInterface=languageInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_language, null);


        setContentView(view);
       FullScreen((Activity)context,this,0.8);
        ButterKnife.bind(this);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(false);

    }



    @OnClick({R.id.tv_cancel,R.id.rl_vn,R.id.rl_chinese})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.rl_vn:
                if (languageInterface!=null){
                    languageInterface.switchVn();
                }
                dismiss();
                break;
            case R.id.rl_chinese:
                if (languageInterface!=null){
                    languageInterface.switchChinese();
                }
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

    public interface LanguageInterface{
        void switchChinese();
        void switchVn();
    }



}
