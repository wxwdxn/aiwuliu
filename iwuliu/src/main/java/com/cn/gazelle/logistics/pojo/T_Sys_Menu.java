/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Sys_Menu.Java
 * 系统编号：Z0001002
 * 系统名称：菜单实体类
 * 模块编号：01
 * 模块名称：菜单管理页面
 * 设计文件：
 * 完成日期：2016-01-12
 * 作    者：YK
 * 内容摘要：菜单实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Sys_Menu
 * 内容摘要: 菜单实体类
 * @author YK
 */
public class T_Sys_Menu {
    private String menu_id;                 //菜单编码
    private String menu_pid;                //菜单父编码
    private String menu_name;               //菜单名称
    private String menu_url;                //菜单地址
    private String menu_sn;                 //菜单序号
    private String menu_icon;               //菜单图标
    private Date menu_date;                 //添加时间
    private String group_id;                //用户组编码
    private String group_name;              //用户组名称
    private String delete_states;           //删除状态
    private String menu_remark;             //备注
    private T_Sys_User user;                //用户信息

    public T_Sys_User getUser() {
        return user;
    }

    public void setUser(T_Sys_User user) {
        this.user = user;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_pid() {
        return menu_pid;
    }

    public void setMenu_pid(String menu_pid) {
        this.menu_pid = menu_pid;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getMenu_sn() {
        return menu_sn;
    }

    public void setMenu_sn(String menu_sn) {
        this.menu_sn = menu_sn;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public Date getMenu_date() {
        return menu_date;
    }

    public void setMenu_date(Date menu_date) {
        this.menu_date = menu_date;
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

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getMenu_remark() {
        return menu_remark;
    }

    public void setMenu_remark(String menu_remark) {
        this.menu_remark = menu_remark;
    }
}
