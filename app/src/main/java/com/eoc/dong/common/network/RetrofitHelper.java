package com.eoc.dong.common.network;

import android.util.Log;

import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.common.utils.ConstantsUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jh352160 on 2017/9/8.
 */

public class RetrofitHelper {
    private static Retrofit retrofit;
    private static Retrofit retrofits;

    /**
     * @link com.zyf.fwms.commonlibrary.http.Api
     */
    public static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(ConstantsUtil.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
        }

        return retrofit;
    }
    public static Retrofit getRetrofitUrl(String url){
        if (retrofits==null){
            retrofits=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
        }

        return retrofits;
    }
    private static OkHttpClient getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx", "日志信息：" + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request newRequest=chain.request().newBuilder()
                                            .addHeader("Connection","close")
                                            .addHeader("x-client-token", MyApplication.getString("token",""))
                                            .build();
                    return chain.proceed(newRequest);
                })
                .retryOnConnectionFailure(false)
                .build();
    }
}
