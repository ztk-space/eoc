package com.eoc.dong.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.eoc.dong.R;
import com.eoc.dong.common.utils.CommonUtil;


/**
 * Created by Administrator on 2018/1/4.
 */

public class CommonItemView extends FrameLayout{
    private TextView tvContent;

    public CommonItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_common_item, this, true);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.tv_content);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonItemView);
        String title = ta.getString(R.styleable.CommonItemView_title);
        tvTitle.setText(CommonUtil.isEmpty(title)?"":title);

        ta.recycle();
    }

    public void setContent(String content){
        tvContent.setText(content);
    }

    public String getContent(){
        return tvContent.getText().toString();
    }
}
