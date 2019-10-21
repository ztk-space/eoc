package com.eoc.dong.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.eoc.dong.R;
import com.eoc.dong.common.base.BaseFragment;
import com.eoc.dong.common.network.response.AuthStateResponse;
import com.eoc.dong.common.network.response.IndexTextResponse;
import com.eoc.dong.common.network.response.OrderStatusResponse;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.HomeFragmentBinding;

import java.util.List;

import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomeFragmentBinding,HomeFragmentPresenter> {

    private String minMoney;
    private String maxMoney;
    private List<Integer> days;
    private int bankAuth;
    private int baiscAuth;
    private int identityAuth;
    private String money;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @Override
    protected void initView() {
        mPresenter.getNum();
        mPresenter.getRepaymentStatus();
        mPresenter.getCertificationStatus();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getNum();
        mPresenter.getRepaymentStatus();
        mPresenter.getCertificationStatus();
    }

    @Override
    public int setContent()  {
        return R.layout.home_fragment;
    }

    public void setNum(IndexTextResponse response){
        minMoney = response.minMoney;
        maxMoney = response.maxMoney;
        days = response.days;
        String userMaxMoney = response.userMaxMoney;
        if (minMoney !=null&& maxMoney !=null){
            if (Integer.parseInt(minMoney)==Integer.parseInt(maxMoney)){
                mBindingView.tvNum.setText(minMoney);
                mBindingView.tvMin.setText(minMoney);
                mBindingView.tvMax.setText(maxMoney);
                mBindingView.seekbar.setClickable(false);
                mBindingView.seekbar.setEnabled(false);
                mBindingView.seekbar.setSelected(false);
                mBindingView.seekbar.setFocusable(false);
            }else if(Integer.parseInt(minMoney)<Integer.parseInt(maxMoney)){
                mBindingView.tvNum.setText(minMoney);
                mBindingView.tvMin.setText(minMoney);
                mBindingView.tvMax.setText(maxMoney);
                mBindingView.seekbar.setClickable(true);
                mBindingView.seekbar.setEnabled(true);
                mBindingView.seekbar.setSelected(true);
                mBindingView.seekbar.setFocusable(true);

                mBindingView.seekbar.setMax(Integer.parseInt(maxMoney)-Integer.parseInt(minMoney));
                mBindingView.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mBindingView.tvNum.setText(Integer.parseInt(minMoney)+progress/10000*500000+"");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }else{
                CommonUtil.showToast(getResources().getString(R.string.home_toast_min_max));
                mBindingView.seekbar.setClickable(false);
                mBindingView.seekbar.setEnabled(false);
                mBindingView.seekbar.setSelected(false);
                mBindingView.seekbar.setFocusable(false);
            }
        }
    }
  public void setRepaymentStatus(OrderStatusResponse response){
        if(response.authStatus!=null){
            if (response.authStatus.equals("1")){
                if (response.isPay==0){
                    mBindingView.btAutnExit.setText(getResources().getString(R.string.home_imme_loan));
                }else {
                    mBindingView.btAutnExit.setText(getResources().getString(R.string.home_see_order));
                }
            }else {
                mBindingView.btAutnExit.setText(getResources().getString(R.string.home_imme_apply));
            }
            mBindingView.btAutnExit.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    if(response.authStatus.equals("1")){
                        if(response.isPay==0){// 0/1 下订单界面或者是我的订单界面
                            if(CommonUtil.isNotEmpty(mBindingView.tvNum.getText().toString())){
                                //跳到下订单界面mBindingView.tvNum.getText().toString()
                                money = mBindingView.tvNum.getText().toString();
                                UIHelper.gotoPlaceanOrderActivity(getActivity(),days.get(0),money);
                            }else {
                                CommonUtil.showToast(getResources().getString(R.string.home_toast_lost_money));
                            }
                        }else {
                            //跳到我的订单界面
                            UIHelper.gotoMyOrderActivity(getActivity());
                        }
                    }
                    else {
                        CommonUtil.showToast(getResources().getString(R.string.completecertification));
                    }
                }
            });
        }
  }
    @OnClick({R.id.re_ji,R.id.re_face,R.id.re_bank})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.re_ji:
                //基本信息界面
                if(baiscAuth==1){
                    CommonUtil.showToast(getResources().getString(R.string.auth_toast_identity_authed));
                }else {
                    UIHelper.gotoBasicInformationOneActivity(mContext);
                }
                break;
                case R.id.re_face:
                    if(baiscAuth==1){
                        if(identityAuth==0){
                            UIHelper.gotoAuthenticationActivity(mContext);
                        }else {
                            CommonUtil.showToast(getResources().getString(R.string.auth_toast_identity_authed));
                        }
                    }else {
                        CommonUtil.showToast(getResources().getString(R.string.auth_toast_order));
                    }

                break;
            case R.id.re_bank:
                if(identityAuth==1){
                    if(bankAuth==0){
                        UIHelper.gotoBankCardCertificationActivity(mContext,1);
                    }else {
                        CommonUtil.showToast(getResources().getString(R.string.auth_toast_bank_authed));
                    }
                }else {
                    CommonUtil.showToast(getResources().getString(R.string.auth_toast_order));
                }
                break;
        }

    }
   public void setCertificationStatus(AuthStateResponse response){
       bankAuth = response.getBankAuth();
       baiscAuth = response.getBaiscAuth();
       identityAuth = response.getIdentityAuth();
       Log.i("TAG",bankAuth+"+"+baiscAuth+"+"+identityAuth);
       if(baiscAuth==0){
           mBindingView.tvHomeJi.setText("("+getResources().getText(R.string.mine_state_unauthed)+")");
       }else {
           mBindingView.tvHomeJi.setText("("+getResources().getText(R.string.mine_state_authed)+")");
           mBindingView.tvHomeJi.setTextColor(Color.parseColor("#353A68"));
       }
       if(bankAuth==0){
           mBindingView.tvHomeBank.setText("("+getResources().getText(R.string.mine_state_unauthed)+")");
       }else {
           mBindingView.tvHomeBank.setText("("+getResources().getText(R.string.mine_state_authed)+")");
           mBindingView.tvHomeBank.setTextColor(Color.parseColor("#353A68"));
       }
       if(identityAuth==0){
           mBindingView.tvHomeFace.setText("("+getResources().getText(R.string.mine_state_unauthed)+")");
       }else {
           mBindingView.tvHomeFace.setText("("+getResources().getText(R.string.mine_state_authed)+")");
           mBindingView.tvHomeFace.setTextColor(Color.parseColor("#353A68"));
       }
   }
}
