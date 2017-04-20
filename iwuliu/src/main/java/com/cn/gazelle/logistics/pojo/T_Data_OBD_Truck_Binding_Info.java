/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_OBD_Truck_Binding_Info.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-19
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_OBD_Truck_Binding_Info
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
public class T_Data_OBD_Truck_Binding_Info {
    private String binding_number;                          // 设备品牌
    private String obd_brand;                               // 设备品牌
    private String obd_brand_value;                         // 设备品牌id
    private String obd_id;                                  // 设备id
    private String equipment_phone_number;                  // 设备手机号
    private String register_time;                           // 采购时间
    private String plate_number;                            // 设备绑定车牌号
    private String binding_time;                            // 设备绑定时间
    private String truck_brand;                             // 车辆品牌
    private String truck_model_name;                        // 车辆型号
    private String unbinding_time;                          // 解绑时间

    public String getBinding_number() {
        return binding_number;
    }

    public void setBinding_number(String binding_number) {
        this.binding_number = binding_number;
    }

    public String getObd_brand() {
        return obd_brand;
    }

    public void setObd_brand(String obd_brand) {
        this.obd_brand = obd_brand;
    }

    public String getObd_brand_value() {
        return obd_brand_value;
    }

    public void setObd_brand_value(String obd_brand_value) {
        this.obd_brand_value = obd_brand_value;
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

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getBinding_time() {
        return binding_time;
    }

    public void setBinding_time(String binding_time) {
        this.binding_time = binding_time;
    }

    public String getTruck_brand() {
        return truck_brand;
    }

    public void setTruck_brand(String truck_brand) {
        this.truck_brand = truck_brand;
    }

    public String getTruck_model_name() {
        return truck_model_name;
    }

    public void setTruck_model_name(String truck_model_name) {
        this.truck_model_name = truck_model_name;
    }

    public String getUnbinding_time() {
        return unbinding_time;
    }

    public void setUnbinding_time(String unbinding_time) {
        this.unbinding_time = unbinding_time;
    }
}
