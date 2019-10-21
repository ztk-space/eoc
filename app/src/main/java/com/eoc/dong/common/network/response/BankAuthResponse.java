package com.eoc.dong.common.network.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 * 银行卡支付共用Response
 */

public class BankAuthResponse implements Serializable {

    public List<String> RESPONSECODE;
    public List<String> RESPONSEMSG;
    public List<String> MCHNTCD;
    public List<String> VERSION;
    public List<String> MCHNTSSN;
}
