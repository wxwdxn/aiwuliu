/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_OBD_Equipment.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-18
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_OBD_Equipment
 * 内容描述：OBD设备信息表
 *@authot WXW
 */
public class T_Data_OBD_Equipment {

    private String equipment_id;                   // 设备ID
    private String brand_id;                       // 设备品牌id
    private String obd_id;                         // OBD ID
    private String equipment_phone_number;         // 设备手机号
    private Date register_time;                    // 登记时间
    private String delete_flag;                    // 删除状态
    private Date last_update;                      // 最新更新时间
    private String last_update_user_id;            // 最新更新者

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getObd_id() {
        return obd_id;
    }

    public void setObd_id(String obd_id) {
        this.obd_id = obd_id;
    }

    public String getEquipment_phone_number() {
        return equipment_phone_number;
    }

    public void setEquipment_phone_number(String equipment_phone_number) {
        this.equipment_phone_number = equipment_phone_number;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
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