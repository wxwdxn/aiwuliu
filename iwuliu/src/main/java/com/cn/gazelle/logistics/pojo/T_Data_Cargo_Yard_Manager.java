/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Cargo_Yard_Manager.Java
 * 系统编号：Z0001002
 * 系统名称：货场管理人员信息实体类
 * 模块编号：01
 * 模块名称：货场管理人员信息管理页面
 * 设计文件：
 * 完成日期：2016-02-24
 * 作    者：YK
 * 内容摘要：货场管理人员信息实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Cargo_Yard_Manager
 * 内容摘要: 货场管理人员信息实体类
 * @author YK
 */
public class T_Data_Cargo_Yard_Manager {

    private String manager_id;                      // ID
    private String manager_member_id;               // 会员ID
    private String yard_id;                         // 工作货场
    private String delete_flag;                     // 删除标识符
    private Date last_update;                       // 最终更新日
    private String last_update_user_id;             // 最终更新者ID
    private String cargo_name;                      // 关联货场名称

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getManager_member_id() {
        return manager_member_id;
    }

    public void setManager_member_id(String manager_member_id) {
        this.manager_member_id = manager_member_id;
    }

    public String getYard_id() {
        return yard_id;
    }

    public void setYard_id(String yard_id) {
        this.yard_id = yard_id;
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
