package com.eoc.dong.common.network.response;

import java.util.List;

/**
 * Created by jh352160 on 2018/3/12.
 */

public class IndexTextResponse {


    /**
     * msg : 成功
     * code : SUCCESS
     * data : {"days":[7],"minMoney":"1000","maxMoney":"2000"}
     * success : true
     */

        /**
         * days : [7]
         * minMoney : 1000
         * maxMoney : 2000
         */

        public String minMoney;
        public String maxMoney;
        public String userMaxMoney;
        public List<Integer> days;
    }

