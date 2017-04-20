/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Master_Cargo_Yard.Java
 * 系统编号：Z0001002
 * 系统名称：货场信息表实体类
 * 模块编号：01
 * 模块名称：货场信息表实体类
 * 设计文件：
 * 完成日期：2016-03-01
 * 作    者：WXW
 * 内容摘要：货场信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Master_Cargo_Yard
 * 内容摘要: 货场信息表实体类
 * @author WXW
 */
public class T_Master_Cargo_Yard {
    private String cargo_id;// 货场id
    private String cargo_name;// 货场名称
    private String province_id;// 省ID
    private String city_id;// 城市ID
    private String area_id;// 县区ID
    private String town_street;// 街道
    private String load_begin_time;// 装货开始时间
    private String load_end_time;// 装货结束时间
    private int load_cost;// 装货费用
    private int load_pump_cost;// 装货过泵费用
    private  int average_load_time;//前10趟平均装货时间
    private int load_truck_number_per_day;// 日装货车数
    private String unload_begin_time;// 卸货开始时间
    private String unload_end_time;// 卸货结束时间
    private int unload_cost;// 卸货费用
    private int unload_pump_cost;// 卸货过泵费用
    private  int average_unload_time;//前10趟平均卸货时间
    private int unload_truck_number_per_day;// 日卸货车数
    private String longitude;// 经度
    private String latitude;// 纬度
    private  int deleteFlag;            //删除标识
    private Date last_update;// 最终更新时间
    private String last_update_user_id;// 最终更新者ID

    public int getAverage_load_time() {
        return average_load_time;
    }

    public void setAverage_load_time(int average_load_time) {
        this.average_load_time = average_load_time;
    }

    public int getAverage_unload_time() {
        return average_unload_time;
    }

    public void setAverage_unload_time(int average_unload_time) {
        this.average_unload_time = average_unload_time;
    }

    public int getLoad_truck_number_per_day() {
        return load_truck_number_per_day;
    }

    public void setLoad_truck_number_per_day(int load_truck_number_per_day) {
        this.load_truck_number_per_day = load_truck_number_per_day;
    }

    public int getUnload_truck_number_per_day() {
        return unload_truck_number_per_day;
    }

    public void setUnload_truck_number_per_day(int unload_truck_number_per_day) {
        this.unload_truck_number_per_day = unload_truck_number_per_day;
    }
    public int getLoad_cost() {
        return load_cost;
    }

    public void setLoad_cost(int load_cost) {
        this.load_cost = load_cost;
    }

    public int getLoad_pump_cost() {
        return load_pump_cost;
    }

    public void setLoad_pump_cost(int load_pump_cost) {
        this.load_pump_cost = load_pump_cost;
    }

    public int getUnload_cost() {
        return unload_cost;
    }

    public void setUnload_cost(int unload_cost) {
        this.unload_cost = unload_cost;
    }

    public int getUnload_pump_cost() {
        return unload_pump_cost;
    }

    public void setUnload_pump_cost(int unload_pump_cost) {
        this.unload_pump_cost = unload_pump_cost;
    }

    public String getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(String cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getTown_street() {
        return town_street;
    }

    public void setTown_street(String town_street) {
        this.town_street = town_street;
    }

    public String getLoad_begin_time() {
        return load_begin_time;
    }

    public void setLoad_begin_time(String load_begin_time) {
        this.load_begin_time = load_begin_time;
    }

    public String getLoad_end_time() {
        return load_end_time;
    }

    public void setLoad_end_time(String load_end_time) {
        this.load_end_time = load_end_time;
    }

    public String getUnload_begin_time() {
        return unload_begin_time;
    }

    public void setUnload_begin_time(String unload_begin_time) {
        this.unload_begin_time = unload_begin_time;
    }

    public String getUnload_end_time() {
        return unload_end_time;
    }

    public void setUnload_end_time(String unload_end_time) {
        this.unload_end_time = unload_end_time;
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

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
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
