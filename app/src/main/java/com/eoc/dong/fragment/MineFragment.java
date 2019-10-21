package com.eoc.dong.fragment;

import android.view.View;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseFragment;
import com.eoc.dong.common.network.response.LvupPowerResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.MineFragmentBinding;

import butterknife.OnClick;

public class MineFragment extends BaseFragment<MineFragmentBinding, MineFragmentPresenter> {
    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initView() {
        hideTitleBar(false);
        hideBackImg();
        setTitle(getResources().getString(R.string.mine_title));
        mBindingView.tvMinePhone.setText(SharedPreUtil.getString("phone",""));
    }

    @Override
    public int setContent() {
        return R.layout.mine_fragment;
    }

    @OnClick({R.id.eco_order, R.id.eco_setting, R.id.eco_back, R.id.eco_msg, R.id.eco_regard, R.id.eco_money})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.eco_setting:           //设置
                UIHelper.gotoSettingActivity(mContext);
                break;
            case R.id.eco_order:        //我的订单
                UIHelper.gotoMyOrderActivity(mContext);
                break;
            case R.id.eco_back:         //我的银行卡
                mPresenter.checkAuthStatus();
                break;
            case R.id.eco_msg:      //消息中心
                UIHelper.gotoMessageActivity(mContext);
                break;
            case R.id.eco_money:      //提额
                mPresenter.getLvupPower();
                break;
            case R.id.eco_regard:           //联系我们
                UIHelper.gotoContactServiceActivity(mContext);
                break;
//                UIHelper.gotoAboutUsActivity(mContext);
//            case R.id.sav_auth:       //我的资料
//                UIHelper.gotoImproveAuthActivity(mContext,1);
//                break;
//            case R.id.sav_increase:
//                mPresenter.getLvupPower();
//                break;
        }
    }
    public void setUpPower(LvupPowerResponse data) {
        if (data.power==0){
            CommonUtil.showToast(getResources().getString(R.string.mine_toast_not_increase));
        }else if (data.power==1){
            UIHelper.gotoAddMoneyActivity(mContext,data.money.toString());
        }else {
            CommonUtil.showToast(getResources().getString(R.string.mine_toast_increasing));
        }
    }

}
