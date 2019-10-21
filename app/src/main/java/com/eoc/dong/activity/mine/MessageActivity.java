package com.eoc.dong.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.network.response.MessageListResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityMessageBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MessageActivity extends BaseActivity<MessageActivityPresenter, ActivityMessageBinding> {
    private MessageListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    @Override
    protected void initView() {
        setTitle(getResources().getString(R.string.mine_message));
        adapter = new MessageListAdapter(mContext);
        mBindingView.xRecyclerView.setAdapter(adapter);
        getMessageData();
    }
    /**
     * 获取消息数据
     */
    private void getMessageData() {
        RetrofitHelper.getRetrofit().create(Api.class).getMessageList().enqueue(new BaseCallBack<BaseResponse<List<MessageListResponse>>>() {
            @Override
            public void onSuccess(Call<BaseResponse<List<MessageListResponse>>> call, Response<BaseResponse<List<MessageListResponse>>> response) {
                if ("OVERTIME".equals(response.body().getCode())){
                    UIHelper.gotoLoginActivityWithLogOut(mContext);
                    return;
                }
                if (response.body().isSuccess()){
                    initData(response.body().getData());
                }else{
                    CommonUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    /**
     * 设置消息列表数据
     */
    private void initData(List<MessageListResponse> data) {
        adapter.clear();
        List<MessageModel> dataList=new ArrayList<>();
        for (MessageListResponse item : data) {
            int id = item.getId();
            String time = CommonUtil.getStringDate(item.getTime());
            String content = item.getContent();
            String title = item.getTitle();
            dataList.add(new MessageModel(id,title, content, time));
        }
        adapter.addAll(dataList);

    }
}
