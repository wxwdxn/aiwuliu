/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Work_Condition_Summary.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：卡车工况统计信息表实体类
 * 模块名称：01
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者: QJ
 * 内容摘要：卡车工况统计信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Work_Condition_Summary
 * 内容描述：卡车工况统计信息表实体类
 *@authot QJ
 */
public class T_Data_Work_Condition_Summary {
    private String summary_number;// 统计No
    private String obd_id;// OBD ID
    private Date server_acquisition_time;// 接收时间_服务器
    private Date last_accon_time;// 点火时间_设备
    private String total_trip_mileage;// 点火前里程统计
    private String current_trip_mileage;// 点火后里程统计
    private String total_fuel;// 点火前油耗统计
    private String current_fuel;// 点火后油耗统计
    private String vstate;// 终端状态信息
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

    public Date getServer_acquisition_time() {
        return server_acquisition_time;
    }

    public void setServer_acquisition_time(Date server_acquisition_time) {
        this.server_acquisition_time = server_acquisition_time;
    }

    public Date getLast_accon_time() {
        return last_accon_time;
    }

    public void setLast_accon_time(Date last_accon_time) {
        this.last_accon_time = last_accon_time;
    }

    public String getTotal_trip_mileage() {
        return total_trip_mileage;
    }

    public void setTotal_trip_mileage(String total_trip_mileage) {
        this.total_trip_mileage = total_trip_mileage;
    }

    public String getCurrent_trip_mileage() {
        return current_trip_mileage;
    }

    public void setCurrent_trip_mileage(String current_trip_mileage) {
        this.current_trip_mileage = current_trip_mileage;
    }

    public String getTotal_fuel() {
        return total_fuel;
    }

    public void setTotal_fuel(String total_fuel) {
        this.total_fuel = total_fuel;
    }

    public String getCurrent_fuel() {
        return current_fuel;
    }

    public void setCurrent_fuel(String current_fuel) {
        this.current_fuel = current_fuel;
    }

    public String getVstate() {
        return vstate;
    }

    public void setVstate(String vstate) {
        this.vstate = vstate;
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