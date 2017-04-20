/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Truck_Alarm_Summary.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：卡车警情统计信息表实体类
 * 模块名称：01
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者: QJ
 * 内容摘要：卡车警情统计信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Truck_Alarm_Summary
 * 内容描述：卡车警情统计信息表实体类
 *@authot QJ
 */
public class T_Data_Truck_Alarm_Summary {
    private String summary_number;// 统计信息No
    private String obd_id;// OBD编号
    private Date client_acquisition_time;// 采集时间_设备
    private String longitude;// 经度
    private String latitude;// 纬度
    private String speed;// 速度
    private String direction;// 方向
    private String status;// 定位状态
    private String satellite_number;// 定位星数
    private int delete_flag;// 删除标识符
    private Date last_update;// 最终更新日
    private String last_update_user_id;// 最终更新者ID

    public String getSummary_number() {
        return summary_number;
    }

    public void setSummary_number(String summary_number) {
        this.summary_number = summary_number;
    }

    public String getObd_id() {
        return obd_id;
    }

    public void setObd_id(String obd_id) {
        this.obd_id = obd_id;
    }

    public Date getClient_acquisition_time() {
        return client_acquisition_time;
    }

    public void setClient_acquisition_time(Date client_acquisition_time) {
        this.client_acquisition_time = client_acquisition_time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSatellite_number() {
        return satellite_number;
    }

    public void setSatellite_number(String satellite_number) {
        this.satellite_number = satellite_number;
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