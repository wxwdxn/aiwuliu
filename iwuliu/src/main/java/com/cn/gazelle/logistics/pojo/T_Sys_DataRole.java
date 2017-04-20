/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Sys_DataRole.Java
 * 系统编号：Z0001002
 * 系统名称：数据角色实体类
 * 模块编号：01
 * 模块名称：数据角色管理页面
 * 设计文件：
 * 完成日期：2016-01-06
 * 作    者：YK
 * 内容摘要：数据角色实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Sys_DataRole
 * 内容摘要: 数据角色实体类
 * @author YK
 */
public class T_Sys_DataRole {
    private String data_role_id;        // 角色编码
    private String data_role_name;      // 角色名称
    private String data_role_value;     // 数据角色值
    private Date data_role_date;        // 添加日期
    private String delete_states;       // 删除状态
    private String data_role_remark;    // 备注

    public String getData_role_id() {
        return data_role_id;
    }

    public void setData_role_id(String data_role_id) {
        this.data_role_id = data_role_id;
    }

    public String getData_role_name() {
        return data_role_name;
    }

    public void setData_role_name(String data_role_name) {
        this.data_role_name = data_role_name;
    }

    public String getData_role_value() {
        return data_role_value;
    }

    public void setData_role_value(String data_role_value) {
        this.data_role_value = data_role_value;
    }

    public Date getData_role_date() {
        return data_role_date;
    }

    public void setData_role_date(Date data_role_date) {
        this.data_role_date = data_role_date;
    }

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getData_role_remark() {
        return data_role_remark;
    }

    public void setData_role_remark(String data_role_remark) {
        this.data_role_remark = data_role_remark;
    }
}
