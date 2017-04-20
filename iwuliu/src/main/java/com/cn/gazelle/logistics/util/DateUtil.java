package com.cn.gazelle.logistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CYH on 2015/12/2.
 */
public class DateUtil {

    //将一个日期对象转换成一个yyyy-MM-dd HH:mm:ss格式的日期字符串
    public static String date2HourString(Date date) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return bartDateFormat.format(date);
    }

    // 将一个日期对象转换成一个yyyy-MM-dd格式的日期字符串
    public static String date2String(Date date){
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return bartDateFormat.format(date);
    }


    // 将一个日期对象转换成一个字符串
    public static String date3String(Date date){
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return (String)bartDateFormat.format(date);
    }


    //字符串转换成日期格式
    public static Date stringToDate(String dateStr,String pattem){
        if(null!=dateStr&&!"".equals(dateStr)){
            SimpleDateFormat sdf = new SimpleDateFormat(pattem);
            Date date=null;
            try {
                date= sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }else{
            return null;
        }

    }

    //获取当前日期
    public static Date getDate(){
        Date date = new Date();
        return date;
    }

    // 比较时间和服务器当前时间是否为同一天
    public static boolean compareTime(Date date_data,Date date_server){
        boolean b = new Boolean(true);
        String time_data = null;    // 数据库
        String time_sever = null;   // 服务器
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 判断数据时间是否为空
        if (date_data==null){
            b = false;
        } else {
            time_data = sdf.format(date_data);
            time_sever = sdf.format(date_server);
            // 比较数据时间和服务器当前时间是否相同
            if (time_data.equals(time_sever)){
                b = true;
            } else {
                b = false;
            }
        }
        return b;
    }

}
