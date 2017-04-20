/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Work_Condition_Detail.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：卡车工况明细信息表实体类
 * 模块名称：01
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者: QJ
 * 内容摘要：卡车工况明细信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Work_Condition_Detail
 * 内容描述：卡车工况明细信息表实体类
 *@authot QJ
 */
public class T_Data_Work_Condition_Detail {
    private String work_condition_number;     // 工况No
    private String obd_id;                    // OBD ID
    private Date client_acquisition_time;     // 采集时间_设备
    private String work_condition_type;       // 工况类型
    private String work_condition_value;      // 工况数据
    private int delete_flag;                  // 删除标识符
    private Date last_update;                 // 最终更新日
    private String last_update_user_id;       // 最终更新者ID

    public String getWork_condition_number() {
        return work_condition_number;
    }

    public void setWork_condition_number(String work_condition_number) {
        this.work_condition_number = work_condition_number;
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

    public String getWork_condition_type() {
        return work_condition_type;
    }

    public void setWork_condition_type(String work_condition_type) {
        this.work_condition_type = work_condition_type;
    }

    public String getWork_condition_value() {
        return work_condition_value;
    }

    public void setWork_condition_value(String work_condition_value) {
        this.work_condition_value = work_condition_value;
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