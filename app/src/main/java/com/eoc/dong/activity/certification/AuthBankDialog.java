package com.eoc.dong.activity.certification;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.VnAllBankInfo;
import com.eoc.dong.common.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * type
 * 1   bankinfo
 * 2  zoneid
 */
public class AuthBankDialog extends Dialog {
    private Context context;
    private RecyclerView recyclerView;
    private IBankName bankName;
    private IZoneId zoneId;
    private int type;

    public AuthBankDialog(Context context, IBankName bankName, int type) {
        super(context, R.style.bottom_select_dialog);
        this.context = context;
        this.bankName=bankName;
        this.type=type;
    }

    public AuthBankDialog(Context context, IZoneId zoneId, int type) {
        super(context, R.style.bottom_select_dialog);
        this.context = context;
        this.zoneId=zoneId;
        this.type=type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_recy_bank, null);
        recyclerView = ((RecyclerView) view.findViewById(R.id.recy));

        //AutoUtils.auto(view);
        setContentView(view);
       FullScreen((Activity)context,this,0.8);
        ButterKnife.bind(this);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(false);
        initView();

    }

    private void initView() {

        if (type==1){
            RetrofitHelper.getRetrofit().create(Api.class).getVNAllBankInfo().enqueue(new BaseCallBack<BaseResponse<List<VnAllBankInfo>>>() {
                @Override
                public void onSuccess(Call<BaseResponse<List<VnAllBankInfo>>> call, Response<BaseResponse<List<VnAllBankInfo>>> response) {
                    if (response.body().isSuccess()){
                        List<VnAllBankInfo> data = response.body().getData();
                        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                        VnBankAdapter adapter= new VnBankAdapter(context);
                        recyclerView.setAdapter(adapter);
                        adapter.addAll(data);
                        adapter.setOnItemClickListener((binding, item, position) -> {
                            if (bankName!=null){
                                bankName.getBankinfo(item);
                            }
                            dismiss();
                        });
                    }else {
                        CommonUtil.showToast(response.body().getMsg());
                    }
                }
            });
        }else if (type==2){
            RetrofitHelper.getRetrofit().create(Api.class).getVNZoneId().enqueue(new BaseCallBack<BaseResponse<Map<String, String>>>() {
                @Override
                public void onSuccess(Call<BaseResponse<Map<String, String>>> call, Response<BaseResponse<Map<String, String>>> response) {
                    if (response.body().isSuccess()){
                        Map<String, String> data = response.body().getData();
                        Set<String> strings = data.keySet();
                        List<VnZone> vnZones = new ArrayList<>();
                        for (String id: strings){
                            String value = data.get(id);
                            VnZone vnZone = new VnZone(id, value);
                            vnZones.add(vnZone);
                        }
                        if (vnZones.size()>0){
                            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
                            VnZoneAdapter adapter= new VnZoneAdapter(context);
                            recyclerView.setAdapter(adapter);
                            adapter.addAll(vnZones);
                            adapter.setOnItemClickListener((binding, item, position) -> {
                                if (zoneId!=null){
                                    zoneId.getZone(item.getId());
                                }
                                dismiss();
                            });
                        }


                    }else {
                        CommonUtil.showToast(response.body().getMsg());
                    }
                }
            });


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
