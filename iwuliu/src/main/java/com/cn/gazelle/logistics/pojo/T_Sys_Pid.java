/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Sys_Pid.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-13
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Sys_Pid
 * 内容描述：PID实体类
 *
 * @authot YK
 */
public class T_Sys_Pid {
    private String pid_id;                               // id
    private String pid_code;                             // pid号
    private String pid_content;                          // pid名称
    private String pid_data_type;                        // 数据类型
    private String pid_size;                             // 长度
    private String pid_unit;                             // 单位
    private String truck_type;                           // 车使用类型
    private Date pid_date;                               // 添加时间
    private String delete_states;                        // 删除状态
    private String pid_remark;                           // 解释

    public String getPid_id() {
        return pid_id;
    }

    public void setPid_id(String pid_id) {
        this.pid_id = pid_id;
    }

    public String getPid_code() {
        return pid_code;
    }

    public void setPid_code(String pid_code) {
        this.pid_code = pid_code;
    }

    public String getPid_content() {
        return pid_content;
    }

    public void setPid_content(String pid_content) {
        this.pid_content = pid_content;
    }

    public String getPid_data_type() {
        return pid_data_type;
    }

    public void setPid_data_type(String pid_data_type) {
        this.pid_data_type = pid_data_type;
    }

    public String getPid_size() {
        return pid_size;
    }

    public void setPid_size(String pid_size) {
        this.pid_size = pid_size;
    }

    public String getPid_unit() {
        return pid_unit;
    }

    public void setPid_unit(String pid_unit) {
        this.pid_unit = pid_unit;
    }

    public String getTruck_type() {
        return truck_type;
    }

    public void setTruck_type(String truck_type) {
        this.truck_type = truck_type;
    }

    public Date getPid_date() {
        return pid_date;
    }

    public void setPid_date(Date pid_date) {
        this.pid_date = pid_date;
    }

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getPid_remark() {
        return pid_remark;
    }

    public void setPid_remark(String pid_remark) {
        this.pid_remark = pid_remark;
    }
}