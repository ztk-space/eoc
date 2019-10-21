package com.eoc.dong.common.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/11/24.
 */

public class BaseRecyclerViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public T binding;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }
}
