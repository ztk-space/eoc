package com.eoc.dong.api;

import com.eoc.dong.BaseResponse;
import com.eoc.dong.ChannelUuidResponse;
import com.eoc.dong.activity.certification.BankCardsListResopnse;
import com.eoc.dong.activity.order.VNUserInfo;
import com.eoc.dong.common.network.response.AboutUsDetailResponse;
import com.eoc.dong.common.network.response.AuthStateResponse;
import com.eoc.dong.common.network.response.BankAuthResponse;
import com.eoc.dong.common.network.response.CheckUpdateResponse;
import com.eoc.dong.common.network.response.ContactInformateResponse;
import com.eoc.dong.common.network.response.IndexTextResponse;
import com.eoc.dong.common.network.response.LoginResponse;
import com.eoc.dong.common.network.response.LvupPowerResponse;
import com.eoc.dong.common.network.response.MessageListResponse;
import com.eoc.dong.common.network.response.OrderDetailResponse;
import com.eoc.dong.common.network.response.OrderListResponse;
import com.eoc.dong.common.network.response.OrderRecordResponse;
import com.eoc.dong.common.network.response.OrderStatusResponse;
import com.eoc.dong.common.network.response.PayRentResponse;
import com.eoc.dong.common.network.response.PhoneAuthResponse;
import com.eoc.dong.common.network.response.ProtocolResponse;
import com.eoc.dong.common.network.response.RenewalInfoResponse;
import com.eoc.dong.common.network.response.RentOrderShellResponse;
import com.eoc.dong.common.network.response.VerificationCodeResponse;
import com.eoc.dong.common.network.response.VnAllBankInfo;
import com.eoc.dong.common.network.response.WebUrlResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * ztk
 *
 */
public interface Api {
    /**
     * 检查升级
     *
     */
     @FormUrlEncoded
     @POST("loan/SysParam_updateApp.html")
     Call<BaseResponse<CheckUpdateResponse>> checkUpdate(@Field("phoneType") String phoneType, @Field("phoneVersion") String phoneVersion);

    /**
     * 使用手机号登录

     */
    @FormUrlEncoded
    @POST("loan/User_userLoginByPhone.html")
    Call<BaseResponse<LoginResponse>> phoneLogin(@Field("phone") String phone,
                                                 @Field("msgCode") String password, @Field("phoneType") String phoneType,
                                                 @Field("channelName") String channelName
    );
    /**
     * 获取图形验证码

     */
    @FormUrlEncoded
    @POST("loan/CodeRecord_getValicode.html")
    Call<BaseResponse<VerificationCodeResponse>> getGraphic(@Field("phone") String phone
    );
    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @POST("loan/CodeRecord_getMsgCode.html")
    Call<BaseResponse> getMsgCode(@Field("phone") String phone,@Field("code") String code
    );
    /**
     * 注册登录接口
     */
    @FormUrlEncoded
    @POST("loan/User_newUserLoginByPWD.html")
    Call<BaseResponse<LoginResponse>> goRegister(@Field("phone") String phone,@Field("msgCode") String msgCode,@Field("password") String password,@Field("phoneType") String phoneType
    );
    /**
     * 修改密码接口
     */
    @FormUrlEncoded
    @POST("loan/User_newUpdatePWD.html")
    Call<BaseResponse> goChangePassword(@Field("password") String password,@Field("newPwd") String newPwd,@Field("confirmPwd") String confirmPwd,@Field("phone") String phone
    );
    /**
     * 获取借款参数（最大、最小金额，借款天数等）
     *andIs
     */
    @FormUrlEncoded
    @POST("loan/SysRate_getLoanParam.html")
    Call<BaseResponse<IndexTextResponse>> getIndexText(@Field("phoneType") String phoneType);
    /**
     *获取应还款订单信息
     */
    @FormUrlEncoded
    @POST("loan/LoanOrder_getNeedPayMsg.html")
    Call<BaseResponse<OrderStatusResponse>> getNeedPayMsg(@Field("userId") int userId);
    /**
     *申请订单
     */
    @FormUrlEncoded
    @POST("loan/LoanOrder_addNewOrder.html")
    Call<BaseResponse<OrderDetailResponse>> getPlaceanOrder(@Field("borrowMoney") String borrowMoney,
                                                               @Field("limitDays") int limitDays,
                                                               @Field("userId") int userId, @Field("phoneType") String phoneType);
    /**
     *确认提交申请订单
     */
    @FormUrlEncoded
    @POST("loan/LoanOrder_confirmOrderAdd.html")
    Call<BaseResponse> submitOrder(@Field("id") int id,@Field("phoneType") String phoneType,@Field("blockBox") String blockBox,@Field("ipAddress") String phoneIp,@Field("deviceid") String phoneUUID);

    /**
     * 我的订单
     */
    @FormUrlEncoded
    @POST("loan/LoanOrder_findMyOrders.html")
    Call<BaseResponse<List<OrderListResponse>>> getMyOrder(@Field("userId") int userId);
    /**
     * 个人信息认证
     */
    @FormUrlEncoded
    @POST("loan/AuthBasic_addUserBasicMsg.html")
    Call<BaseResponse> postVNBasicInfo(
            @Field("diploma") String diploma,@Field("email") String email,@Field("motherName") String motherName
            ,@Field("peopleNum") String peopleNum,@Field("childNum") String childNum,@Field("province") String province
            ,@Field("liveCounty") String liveCounty,@Field("homeAddr") String homeAddr,@Field("familyPostcode") String familyPostcode
            ,@Field("housingCategory") String housingCategory,@Field("liveTime") String liveTime,@Field("companyName") String companyName
            ,@Field("industryCategory") String industryCategory,@Field("position") String position,@Field("workingLife") String workingLife
            ,@Field("salary") String salary,@Field("companyPhone") String companyPhone,@Field("companyWebsite") String companyWebsite
            ,@Field("companyProvince") String companyProvince,@Field("companyCity") String companyCity,@Field("workAddr") String workAddr
            ,@Field("companyPostcode") String companyPostcode,@Field("workImage") String workImage,@Field("contactsNameFirst") String contactsNameFirst
            ,@Field("contactsPhoneFirst") String contactsPhoneFirst,@Field("contactsRelationFirst") String contactsRelationFirst,@Field("contactsNameSecond") String contactsNameSecond
            ,@Field("contactsPhoneSecond") String contactsPhoneSecond,@Field("contactsRelationSecond") String contactsRelationSecond,@Field("contactsNameThird") String contactsNameThird
            ,@Field("contactsPhoneThird") String contactsPhoneThird,@Field("contactsRelationThird") String contactsRelationThird,@Field("contactsNameFourth") String contactsNameFourth
            ,@Field("contactsPhoneFourth") String contactsPhoneFourth,@Field("contactsRelationFourth") String contactsRelationFourth,@Field("userId") int userId,
            @Field("birthday") String birthday

    );
    /**
     *获取用户认证状态
     */
    @FormUrlEncoded
    @POST("loan/AuthStatus_getMyAuthStatus.html")
    Call<BaseResponse<AuthStateResponse>> getAuthStatus(@Field("userId") int userId);

    /**
     *  忘记密码
     */
    @FormUrlEncoded
    @POST("loan/User_newForgetPWD.html")
    Call<BaseResponse> getForgetPassword(@Field("phone") String phone,@Field("msgCode") String msgCode,@Field("newPwd") String newPwd,@Field("confirmPwd") String confirmPwd);
    /**
     *获取越南所有银行信息
     */
    @POST("loan/BankCode_selectAllBankNameYn.html")
    Call<BaseResponse<List<VnAllBankInfo>>> getVNAllBankInfo();
    /**
     *获取越南各区的id和名称
     */
    @POST("loan/AuthBank_nlGetZoneId.html")
    Call<BaseResponse<Map<String,String>>> getVNZoneId();

    /**
     *根据银行名称（越南）获取bankcode
     */
    @FormUrlEncoded
    @POST("loan/BankCode_selectByBankName.html")
    Call<BaseResponse> getPayVNByName(@Field("bankName") String bankName);

    /**
     *越南银行卡认证
     */
    @FormUrlEncoded
    @POST("loan/AuthBank_ynBankCardAuth.html")
    Call<BaseResponse> authVNBank(@Field("userId") int userId, @Field("bankName") String bankName,
                                  @Field("bankNum") String bankNum, @Field("bankCode") String bankCode,
                                  @Field("identityName") String identityName, @Field("accountType") String accountType,
                                  @Field("cardMonth") String cardMonth, @Field("cardYear") String cardYear,
                                  @Field("branchName") String branchName, @Field("zoneId") String zoneId);

    /**
     * 上传图片vn
     */
    @Multipart
    @POST("loan/Upload_upload.html")
    Call<BaseResponse<String>> uploadImageVN(@Part() List<MultipartBody.Part> userId);

    /**
     * vn人脸认证
     */
    @FormUrlEncoded
    @POST("loan/AuthIdentity_faceAuth.html")
    Call<BaseResponse> faceAuthVN(@Field("frontImagePath") String frontImagePath,@Field("backImagePath") String backImagePath,
                                  @Field("livenessId")  String livenessId,@Field("userId") int userId,
                                  @Field("userIdcard") String userIdcard,
                                  @Field("userName") String userName);

    @FormUrlEncoded
    @POST("loan/User_getLvupPower.html")
    Call<BaseResponse<LvupPowerResponse>> LvupPower(@Field("id") int id);

    /**
     * 提额申请
     */
    @FormUrlEncoded
    @POST("loan/RecordLevelup_increaseMoneyApply.html")
    Call<BaseResponse<LvupPowerResponse>> increaseMoneyApply(@Field("userId") int userId,@Field("targetMoney") String targetMoney);
    /**
     * 用户
     */
    @POST("loan/SysParam_getContactInformation.html")
    Call<BaseResponse<ContactInformateResponse>> contactInformation();
    /**
     * 当前登录用户的推送信息列表
     */
    @GET("api/pushMsgRecord/selectListByUser")
    Call<BaseResponse<List<MessageListResponse>>> getMessageList();
    /**
     * 查询订单详情
     */
    @FormUrlEncoded
    @POST("loan/LoanOrder_selectOneById.html")
    Call<BaseResponse<RentOrderShellResponse>> getOrderDetail(@Field("id") int orderId);

    /**
     * 提交还款申请
     */
    @GET("loan/loanOrder/moneyBackStatus")
    Call<BaseResponse> submitRepay(@Query("orderId") String orderId);

    /**
     *获取个人信息
     */
    @POST("loan/User_getMyAuthInfo.html")
    Call<BaseResponse<VNUserInfo>> getVNuserInfo();

    /**
     * 富有绑卡支付
     */
    @FormUrlEncoded
    @POST("loan/Pay_fyBindCardPay.html")
    Call<BaseResponse<PayRentResponse>> payRent(@Field("orderId") int orderId,
                                                @Field("validateCode")String validateCode,
                                                @Field("bindPayMoney")String bindPayMoney,
                                                @Field("bindPayDay")String bindPayDay,
                                                @Field("userId")int userId,
                                                @Field("bindPayType")int bindPayType);
    /**
     * 续期页面信息
     */
    @FormUrlEncoded
    @POST("loan/RecordRenewal_getExtendMsg.html")
    Call<BaseResponse<RenewalInfoResponse>> getRenewalInfo(@Field("days") String days, @Field("id") int id);
    /**
     *渠道统计
     */
    @FormUrlEncoded
    @POST("loan/User_androidFirst.html")
    Call<BaseResponse<ChannelUuidResponse>> getChannelUuid(@Field("channelName") String channelName);

    /**
     *
     */
    @FormUrlEncoded
    @POST("loan/User_androidAppsFirst.html")
    Call<BaseResponse> getChannelTest(@Field("firstString") String firstString);
    /**
     *天机运营商认证获取h5认证页面
     */
    @FormUrlEncoded
    @POST("loan/AuthPhone_mobileAuthTJ.html")
    Call<BaseResponse<PhoneAuthResponse>> getPhoneAuth(@Field("userId") int userId);
    /**
     * 提交用户反馈
     */
    @FormUrlEncoded
    @POST("loan/RecordOpinion_addAppFeedback.html")
    Call<BaseResponse> commitUserFeedback(@Field("userId") int userId,@Field("userPhone") String userPhone,@Field("text") String text);
    /**
     * 上传图片
     */
    @Multipart
    @POST("api/attachment/upload")
    Call<BaseResponse<List<String>>> uploadImage(@Part() List<MultipartBody.Part> userId);
    /**
     * 我的银行卡
     */
    @FormUrlEncoded
    @POST("loan/AuthBank_getMyBindCard.html")
    Call<BaseResponse<BankCardsListResopnse>> getBankCardList(@Field("userId") int userId);
    /**
     * 银行卡认证
     */
    @FormUrlEncoded
    @POST("loan/AuthBank_fyAuthBindCard.html")
    Call<BaseResponse<BankAuthResponse>> bankcardAuth(@Field("userId") int userId,
                                                      @Field("identityName") String name,
                                                      @Field("bankNum") String bankcardno,
                                                      @Field("identityNum") String idcardno,
                                                      @Field("bankPhone") String phone);
    /**
     * 富有解除绑定银行卡
     */
    @FormUrlEncoded
    @POST("loan/AuthBank_fyRelieveBindCard.html")
    Call<BaseResponse<BankAuthResponse>> relieveBindCard(@Field("userId") int userId);
    /**
     * 租赁协议
     */
    @GET("loan/protocol/selectOneByType")
    Call<BaseResponse<ProtocolResponse>> getLoanType(@Query("type") String type);
    /**
     * 用户是否签署合同
     */
    @GET("loan/yunhetong/getSignYunStatus")
    Call<BaseResponse<String>> getContractStatus(@Query("loanOrderId") String loanOrderId);
    /**
     * 提交回租申请111
     */
    @GET("loan/evaluation/sureApply")
    Call<BaseResponse> getsureApply(@Query("appType") String appType,@Query("contractId") String contractId,@Query("id") String id);


    /**
     * 提交续期申请
     */
    @GET("loan/orderExtend/submitRenewal")
    Call<BaseResponse> submitRenewal(@Query("days") String days,
                                     @Query("money") String money,
                                     @Query("paramSettingId") String paramSettingId,
                                     @Query("userCouponId") String userCouponId,
                                     @Query("orderId") String orderId);
    /**
     * 关于我们
     */
    @FormUrlEncoded
    @POST("loan/SysTemplate_selectTemplate.html")
    Call<BaseResponse<List<AboutUsDetailResponse>>> getAboutUs(@Field("type") int type, @Field("title") String title);
    /**
     * 统一绑卡支付请求
     */
    @GET("api/order/bindCardPay")
    Call<BaseResponse<BankAuthResponse>> bindCardPay(@Query("money") String money);
    /**
     * 评估记录
     */
    @GET("api/evaluation/findMyEvaluation")
    Call<BaseResponse<OrderRecordResponse>> getMyEvaluation();

    /**
     * 富有绑定银行卡短信确认
     */
    @FormUrlEncoded
    @POST("loan/AuthBank_fyBankCardAuth.html")
    Call<BaseResponse<BankAuthResponse>> fyBankCardAuth(@Field("userId") int userId,
                                                        @Field("identityName") String name,
                                                        @Field("bankNum") String bankcardno,
                                                        @Field("identityNum") String idcardno,
                                                        @Field("bankPhone") String phone,
                                                        @Field("validateCode") String validateCode,
                                                        @Field("bankName") String bankName,
                                                        @Field("reqNum") String reqNum);

    @FormUrlEncoded
    @POST("loan/User_userLoginByPhone.html")
    Call<BaseResponse<LoginResponse>> phoneLogin1(@Field("phone") String phone,
                                                 @Field("msgCode") String password,@Field("phoneType") String phoneType,
                                                 @Field("channelName") String channelName);
/*
* 支付成功返回url
* */
    @FormUrlEncoded
    @POST("loan/Pay_getPayUrl.html")
    Call<BaseResponse> getWebUrl(@Field("bindPayType") String bindPayType,
                                                   @Field("userId") String userId, @Field("orderId") String orderId,
                                                   @Field("bindPayMoney") String bindPayMoney);


}




