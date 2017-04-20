/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_date2.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-09-08
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 类 名 称：T_Data_date2
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
public class T_Data_date2 {
    private String name;
    private String createTime;
    private String date;
    private String time;
    private String type;
    private String flowId;
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //根据距离倒序
    public List<T_Data_date2> dateSort(List<T_Data_date2> list) throws ParseException {
        T_Data_date2 sort= null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i=0;i<list.size();i++){
            for (int j=list.size()-2;j>=i;j--){
                String createTime = list.get(j).getCreateTime();
                String createTime2 = list.get(j+1).getCreateTime();
                Date date = format.parse(createTime);
                Date date2 = format.parse(createTime2);
                if (date.getTime()>=date2.getTime()){
                    sort = list.get(j+1);
                    list.set(j + 1, list.get(j));
                    list.set(j, sort);
                }
            }
        }
        for (int i=0;i<list.size();i++){
            T_Data_date2 date2 = list.get(i);
            String createTime = date2.getCreateTime();
            String date = createTime.substring(0, 10);
            String time = createTime.substring(11, 19);
            date2.setDate(date);
            date2.setTime(time);
            list.set(i,date2);
        }
        return list;
    }
}