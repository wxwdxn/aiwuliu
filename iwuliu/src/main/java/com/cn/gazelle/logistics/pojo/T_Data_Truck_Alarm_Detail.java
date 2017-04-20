/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Truck_Alarm_Detail.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：卡车警情明细信息表实体类
 * 模块名称：01
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者: QJ
 * 内容摘要：卡车警情明细信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Truck_Alarm_Detail
 * 内容描述：卡车警情明细信息表实体类
 *@authot QJ
 */
public class T_Data_Truck_Alarm_Detail {
    private String summary_id;// 统计信息No
    private String alarm_type;// 警情类型
    private String new_alarm_flag;// 新警情标志
    private String alarm_value;// 当前值
    private String alarm_threshold;// 警情阈值
    private int delete_flag;// 删除标识符
    private Date last_update;// 最终更新日
    private String last_update_user_id;// 最终更新者ID

    public String getSummary_id() {
        return summary_id;
    }

    public void setSummary_id(String summary_id) {
        this.summary_id = summary_id;
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

    public String getNew_alarm_flag() {
        return new_alarm_flag;
    }

    public void setNew_alarm_flag(String new_alarm_flag) {
        this.new_alarm_flag = new_alarm_flag;
    }

    public String getAlarm_value() {
        return alarm_value;
    }

    public void setAlarm_value(String alarm_value) {
        this.alarm_value = alarm_value;
    }

    public String getAlarm_threshold() {
        return alarm_threshold;
    }

    public void setAlarm_threshold(String alarm_threshold) {
        this.alarm_threshold = alarm_threshold;
    }

    public int getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(int delete_flag) {
        this.delete_flag = delete_flag;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getLast_update_user_id() {
        return last_update_user_id;
    }

    public void setLast_update_user_id(String last_update_user_id) {
        this.last_update_user_id = last_update_user_id;
    }
}