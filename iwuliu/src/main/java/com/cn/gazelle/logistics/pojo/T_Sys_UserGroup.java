/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Sys_UserGroup.Java
 * 系统编号：Z0001002
 * 系统名称：用户组实体类
 * 模块编号：01
 * 模块名称：用户组管理页面
 * 设计文件：
 * 完成日期：2016-01-04
 * 作    者：YK
 * 内容摘要：用户组实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;
import java.util.List;

/**
 * 类 名 称: T_Sys_UserGroup
 * 内容摘要: 用户组实体类
 * @author YK
 */
public class T_Sys_UserGroup {
    private String group_id;                // 用户组编码
    private String group_name;              // 用户组名称
    private String group_code;              // 用户组代码
    private Date group_date;                // 添加日期
    private String delete_states;           // 删除状态
    private String group_remark;            // 备注
    private List<T_Sys_User> userList;      // 用户组所属用户信息

    public List<T_Sys_User> getUserList() {
        return userList;
    }

    public void setUserList(List<T_Sys_User> userList) {
        this.userList = userList;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Date getGroup_date() {
        return group_date;
    }

    public void setGroup_date(Date group_date) {
        this.group_date = group_date;
    }

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getGroup_remark() {
        return group_remark;
    }

    public void setGroup_remark(String group_remark) {
        this.group_remark = group_remark;
    }
}
