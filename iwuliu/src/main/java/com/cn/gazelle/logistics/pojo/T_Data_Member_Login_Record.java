package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * Created by YK on 2016/2/22.
 */
public class T_Data_Member_Login_Record {

    private String record_id;//履历id
    private String record_name;//履历名称
    private String member_id;//会员id
    private String ip_address;//登录ip
    private String province_id;//所在省id
    private String city_id;//所在城市id
    private String area_id;//所在区县id
    private String app_flag;//登录应用区分
    private String device_flag;//登录终端区分
    private String os_flag;//系统区分
    private String brand;//设备品牌
    private String model;//设备型号
    private String imei;//imei号
    private Date login_time;//登录时间
    private Date last_update;//最终更新日
    private String last_update_user_id;//最终更新者id

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
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

    public String getApp_flag() {
        return app_flag;
    }

    public void setApp_flag(String app_flag) {
        this.app_flag = app_flag;
    }

    public String getDevice_flag() {
        return device_flag;
    }

    public void setDevice_flag(String device_flag) {
        this.device_flag = device_flag;
    }

    public String getOs_flag() {
        return os_flag;
    }

    public void setOs_flag(String os_flag) {
        this.os_flag = os_flag;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
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
