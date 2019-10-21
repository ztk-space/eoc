package com.eoc.dong.common.base;


import android.content.Context;

/**
 * CodeFatCat
 */

public abstract class BasePresenter<T>{
    public Context mContext;
    public T mView;


    public void setView(T v) {
        this.mView = v;
    }


    public void onDestroy() {
         mView=null;
    }
}