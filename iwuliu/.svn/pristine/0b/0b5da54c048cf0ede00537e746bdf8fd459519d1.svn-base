package com.cn.gazelle.logistics.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zf on 2016/12/26.
 */
public class RadomUtil {

    // 订单编号
    public static String getOrderNo(){
        String s1 = Integer.toString((int)((Math.random() * 9000) + 1000));
        String s2 = Integer.toString((int)((Math.random() * 9000) + 1000));
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
        String currentTime =  df.format(new Date());
        String  orderNo = s1 + currentTime+ s2;
        return orderNo;
    }

    // 批次编号
    public static String getBatchNo(){
        String batch_no = null;
        batch_no = Integer.toString((int)((Math.random() * 900000) + 100000));
        return batch_no;
    }
}
