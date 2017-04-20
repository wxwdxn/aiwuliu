package com.cn.gazelle.logistics.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WXW on 2016/3/29.
 */
public class CodeUtil {
    //派车单编号
    public static String DCode(Date date) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmsssss");
        String format = bartDateFormat.format(date);
        return "D"+format;
    }
    //订单编号
    public static String TCode(Date date) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmsssss");
        String format = bartDateFormat.format(date);
        return "T"+format;
    }
    //调度单编号
    public static String SCode(Date date) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmsssss");
        String format = bartDateFormat.format(date);
        return "S"+format;
    }
    //合同编号
    public static String CCode(Date date) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = bartDateFormat.format(date);
        return "C"+format;
    }
}
