/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Master_Service_Station.Java
 * 系统编号：Z0001002
 * 系统名称：线下加盟服务站基础信息实体类
 * 模块编号：01
 * 模块名称：线下加盟服务站基础信息管理页面
 * 设计文件：
 * 完成日期：2016-02-24
 * 作    者：QJ
 * 内容摘要：线下加盟服务站基础信息实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Master_Service_Station
 * 内容摘要: 线下加盟服务站基础信息实体类
 * @author QJ
 */

public class  T_Master_Service_Station {
    private String station_id;              // 编号
    private String station_name;            // 服务站名
    private int station_type;               // 服务站类型
    private String province_id;             // 所在省ID
    private String city_id;                 // 所在城市ID
    private String area_id;                 // 所在区县ID
    private String town_street;             // 所在村镇街道
    private String longitude;               // 经度
    private String latitude;                // 纬度
    private String work_begin_time;         // 工作时间起点
    private String work_end_time;           // 工作时间终点
    private String contact_name;            // 联系人
    private String contact_phone;           // 联络电话
    private String bank_id;                 // 开户银行ID
    private String bank_account;            // 银行帐号
    private String bank_account_person_name;// 开户人姓名
    private String bank_account_person_id;  // 开户人身份证号
    private int delete_flag;                // 删除标识符
    private Date last_update;               // 最终更新日
    private String last_update_user_id;     // 最终更新者ID

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public int getStation_type() {
        return station_type;
    }

    public void setStation_type(int station_type) {
        this.station_type = station_type;
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

    public String getWork_begin_time() {
        return work_begin_time;
    }

    public void setWork_begin_time(String work_begin_time) {
        this.work_begin_time = work_begin_time;
    }

    public String getWork_end_time() {
        return work_end_time;
    }

    public void setWork_end_time(String work_end_time) {
        this.work_end_time = work_end_time;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_account_person_name() {
        return bank_account_person_name;
    }

    public void setBank_account_person_name(String bank_account_person_name) {
        this.bank_account_person_name = bank_account_person_name;
    }

    public String getBank_account_person_id() {
        return bank_account_person_id;
    }

    public void setBank_account_person_id(String bank_account_person_id) {
        this.bank_account_person_id = bank_account_person_id;
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