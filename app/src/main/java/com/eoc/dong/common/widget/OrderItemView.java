package com.eoc.dong.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spanned;
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

public class OrderItemView extends FrameLayout{
    private TextView tvContent;
    private TextView tvTitle;
    private Spanned title;

    public OrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_order_item, this, true);
        tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.tv_val);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.OrderItemView);
        String title = ta.getString(R.styleable.OrderItemView_orderTitle);
        String content = ta.getString(R.styleable.OrderItemView_orderVal);
        tvTitle.setText(CommonUtil.isEmpty(title)?"":title);
        tvContent.setText(CommonUtil.isEmpty(content)?"":content);

        ta.recycle();
    }

    public void setContent(String content){
        tvContent.setText(content);
    }

    public String getContent(){
        return tvContent.getText().toString();
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setTitle(Spanned title) {
        tvTitle.setText(title);
    }
}
