/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_OBD_Equipment_Truck_Binding.java
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
 * 类 名 称：T_Data_OBD_Equipment_Truck_Binding
 * 内容描述：T_Data_OBD_Equipment_Truck_Binding实体类
 *@authot YK
 */
public class T_Data_OBD_Equipment_Truck_Binding {

    private String binding_number;                   // 绑定信息编号
    private String equipment_id;                     // 设备id
    private String truck_id;                         // 车辆
    private Date binding_time;                       // 绑定时间
    private Date unbinding_time;                     // 解绑时间
    private String delete_flag;                      // 删除标识符
    private Date last_update;                        // 最终更新日
    private String last_update_user_id;              // 最终更新者id

    public String getBinding_number() {
        return binding_number;
    }

    public void setBinding_number(String binding_number) {
        this.binding_number = binding_number;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public Date getBinding_time() {
        return binding_time;
    }

    public void setBinding_time(Date binding_time) {
        this.binding_time = binding_time;
    }

    public Date getUnbinding_time() {
        return unbinding_time;
    }

    public void setUnbinding_time(Date unbinding_time) {
        this.unbinding_time = unbinding_time;
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
