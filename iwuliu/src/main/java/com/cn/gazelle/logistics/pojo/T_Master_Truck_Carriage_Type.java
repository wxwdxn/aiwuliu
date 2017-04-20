/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Master_Truck_Carriage_Type.Java
 * 系统编号：Z0001002
 * 系统名称：车厢类型基础信息实体类
 * 模块编号：01
 * 模块名称：车辆类型信息管理页面
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者：QJ
 * 内容摘要：车厢类型基础信息实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Master_Truck_Carriage_Type
 * 内容摘要: 车厢类型基础信息实体类
 * @author QJ
 */
public class T_Master_Truck_Carriage_Type {

    private String truck_carriage_type_id;              // 车厢类型ID
    private String truck_carriage_type_name;            // 车厢类型名称
    private String truck_carriage_type_desc;            // 车厢类型描述
    private String truck_carriage_useable;              // 可用状态
    private int delete_flag;                            // 删除标识符
    private Date last_update;                           // 最终更新日
    private String last_update_user_id;                 // 最终更新者ID

    public String getTruck_carriage_type_id() {
        return truck_carriage_type_id;
    }

    public void setTruck_carriage_type_id(String truck_carriage_type_id) {
        this.truck_carriage_type_id = truck_carriage_type_id;
    }

    public String getTruck_carriage_type_name() {
        return truck_carriage_type_name;
    }

    public void setTruck_carriage_type_name(String truck_carriage_type_name) {
        this.truck_carriage_type_name = truck_carriage_type_name;
    }

    public String getTruck_carriage_type_desc() {
        return truck_carriage_type_desc;
    }

    public void setTruck_carriage_type_desc(String truck_carriage_type_desc) {
        this.truck_carriage_type_desc = truck_carriage_type_desc;
    }

    public String getTruck_carriage_useable() {
        return truck_carriage_useable;
    }

    public void setTruck_carriage_useable(String truck_carriage_useable) {
        this.truck_carriage_useable = truck_carriage_useable;
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
