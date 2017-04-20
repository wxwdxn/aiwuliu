/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Truck_Model.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：卡车型号信息表实体类
 * 设计文件：
 * 完成日期：2016-11-03
 * 作    者: QJ
 * 内容摘要：卡车型号信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Truck_Model
 * 内容描述：卡车型号信息表实体类
 *@authot QJ
 */
public class T_Data_Truck_Model {
    private int truck_model_no;                         // 编号
    private String truck_brand_id;                      // 车辆品牌ID
    private String truck_model_name;                    // 车辆型号名称
    private int delete_flag;                            // 删除标识符
    private Date last_update;                           // 最终更新日
    private String last_update_user_id;                 // 最终更新者ID

    public int getTruck_model_no() {
        return truck_model_no;
    }

    public void setTruck_model_no(int truck_model_no) {
        this.truck_model_no = truck_model_no;
    }

    public String getTruck_brand_id() {
        return truck_brand_id;
    }

    public void setTruck_brand_id(String truck_brand_id) {
        this.truck_brand_id = truck_brand_id;
    }

    public String getTruck_model_name() {
        return truck_model_name;
    }

    public void setTruck_model_name(String truck_model_name) {
        this.truck_model_name = truck_model_name;
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