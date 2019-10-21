package com.eoc.dong.activity.certification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eoc.dong.BaseCallBack;
import com.eoc.dong.BaseResponse;
import com.eoc.dong.R;
import com.eoc.dong.activity.MyApplication;
import com.eoc.dong.api.Api;
import com.eoc.dong.common.base.BaseActivity;
import com.eoc.dong.common.network.RetrofitHelper;
import com.eoc.dong.common.utils.BasisTimesUtils;
import com.eoc.dong.common.utils.CommonUtil;
import com.eoc.dong.common.utils.SharedPreUtil;
import com.eoc.dong.common.utils.UIHelper;
import com.eoc.dong.databinding.ActivityBasicInformationOneBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Response;

public class BasicInformationOneActivity extends BaseActivity<BasicInformationOnePresenter, ActivityBasicInformationOneBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information_one);
        mBindingView.llBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        mBindingView.btOneNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSubmit();
            }
        });
    }
    @Override
    protected void initView() {

    }
    private void showDialog() {
//        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//
//                mYear = year;
//                mMonth = month;
//                mDay = dayOfMonth;
//                mBindingView.etOneBirth.setText(getDay());
//
//            }
//        }, mYear, mMonth, mDay);
//        datePickerDialog.show();
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        BasisTimesUtils.showDatePickerDialog(mContext, BasisTimesUtils.THEME_HOLO_LIGHT, "Vui lòng chọn một ngày", year, month, day, new BasisTimesUtils.OnDatePickerListener() {
            @Override
            public void onConfirm(int year, int month, int dayOfMonth) {
                Log.i("TAG",year + "-" + month + "-" + dayOfMonth);
                mBindingView.etOneBirth.setText(dayOfMonth + "-" + month + "-" + year);
                // mBindingView.etOneBirth.setText(dayOfMonth + "-" + month + "-" + year);
            }
            @Override
            public void onCancel() {
            }
        });
    }
    private void clickSubmit() {
        if (CommonUtil.isEmpty(mBindingView.etOneBirth.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etOneXl.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etOneDzyx.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etTwoGsmc.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etTwoZw.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etTwoGznx.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etTwoXz.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etTwoGsdh.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etTwoGswz.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etTwoGsxxdz.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etFirstName.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etFirstPhone.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etFirstRelation.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        } else if (CommonUtil.isEmpty(mBindingView.etSecondName.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        }else if (CommonUtil.isEmpty(mBindingView.etSecondPhone.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        }else if (CommonUtil.isEmpty(mBindingView.etSecondRelation.getText().toString())) {
            CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish));
        }else {
            if((isEmail1(mBindingView.etOneDzyx.getText().toString().trim()) && mBindingView.etOneDzyx.getText().toString().trim().length()<=31)) {
                MyApplication.saveString("birth", mBindingView.etOneBirth.getText().toString());
                ArrayList<String> list = new ArrayList<>();
//                Intent intent = new Intent(this, BasicTwoActivity.class);
//                //出生日期  学历 电子邮件 母亲姓名 家庭成员数量 子女数量 省 县 居住详细地址 住房类别 居住时间
                list.add(mBindingView.etOneXl.getText().toString());
                list.add(mBindingView.etOneDzyx.getText().toString());
                list.add(mBindingView.etTwoGsmc.getText().toString());
                list.add(mBindingView.etTwoZw.getText().toString());
                list.add(mBindingView.etTwoGznx.getText().toString());
                list.add(mBindingView.etTwoXz.getText().toString());
                list.add(mBindingView.etTwoGsdh.getText().toString());
                list.add(mBindingView.etTwoGswz.getText().toString());
                list.add(mBindingView.etTwoGsxxdz.getText().toString());
                list.add(mBindingView.etFirstName.getText().toString());
                list.add(mBindingView.etFirstPhone.getText().toString());
                list.add(mBindingView.etFirstRelation.getText().toString());
                list.add(mBindingView.etSecondName.getText().toString());
                list.add(mBindingView.etSecondPhone.getText().toString());
                list.add(mBindingView.etSecondRelation.getText().toString());
                postData(list);
            }
            else {
                CommonUtil.showToast(getResources().getString(R.string.basic_toast_finish1));
            }
        }
    }


    public boolean isEmail1(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
    /**
     * SharedPreUtil.getint("id")
     * 0 1 2 3 4 5 6  学历 电子邮箱 母亲姓名 家庭成员数量 子女数量 居住省 居住县
     * 7 8 9 10 11 12  居住详细地址 邮政编码 住房类别 居住时间 公司名称 行业类别
     * 13 14 15 16 17 18  职位 工作年限 薪资 公司电话 公司网址(选填) 公司所在省
     * 19 20 21 22 23 24  公司所在县 公司详细地址  公司邮政编码  图片  联系人姓名1 联系人电话1
     * 25 26 27 28 29 30  与联系人关系1 联系人姓名2 联系人电话2 与联系人关系2  联系人姓名3 联系人电话3
     * 31 32 33 34   与联系人关系3  联系人姓名4 联系人电话4 与联系人关系4
     *
     * @param lists
     */
    private void postData(List<String> lists) {
        RetrofitHelper.getRetrofit().create(Api.class).postVNBasicInfo(lists.get(0),lists.get(1),"",""
                ,"","","","","","","",lists.get(2)
                ,"",lists.get(3),lists.get(4),lists.get(5),lists.get(6),lists.get(7),""
                ,"",lists.get(8),"","22222" ,lists.get(9),lists.get(10),lists.get(11)
                ,lists.get(12),lists.get(13),lists.get(14),"","","",""
                ,"","" , SharedPreUtil.getInt("id",0),
                MyApplication.getString("birth","")
        ).enqueue(new BaseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().isSuccess()){
                    // CommonUtil.showToast("success");
                    finish();
                }else {
                    CommonUtil.showToast(response.body().getMsg());
                }
            }
        });
    }
}
