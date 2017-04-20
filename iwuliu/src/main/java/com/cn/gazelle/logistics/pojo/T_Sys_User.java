/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Sys_User.Java
 * 系统编号：Z0001002
 * 系统名称：用户信息实体类
 * 模块编号：01
 * 模块名称：登录页面
 * 设计文件：
 * 完成日期：2015-12-02
 * 作    者：CYH
 * 内容摘要：用户信息实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Sys_User
 * 内容摘要: 用户信息实体类
 * @author CYH
 */
public class T_Sys_User {
    private String user_id;             // 编号
    private String login_name;          // 登录名
    private String login_password;      // 密码
    private String user_name;           // 用户名
    private String user_sex;            // 性别
    private String user_phone;          // 手机
    private String user_tel;            // 座机
    private String user_mail;           // 邮箱
    private String province_id;         //所在省id
    private String city_id;             //所在市id
    private String area_id;             //所在区id
    private String town_street;         //所在村镇街道
    private Date user_date;             // 添加时间
    private String delete_states;       // 删除状态
    private String user_remark;         // 备注
    private String group_id;            // 组编码
    private String group_name;          // 组名称
    private String funRole_id;          // 功能角色编码
    private String funRole_name;        // 功能角色名称
    private String dataRole_id;         // 数据角色编码
    private String dataRole_name;       // 数据角色名称

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getTown_street() {
        return town_street;
    }

    public void setTown_street(String town_street) {
        this.town_street = town_street;
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

    public Date getUser_date() {
        return user_date;
    }

    public void setUser_date(Date user_date) {
        this.user_date = user_date;
    }

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getUser_remark() {
        return user_remark;
    }

    public void setUser_remark(String user_remark) {
        this.user_remark = user_remark;
    }

    public String getFunRole_id() {
        return funRole_id;
    }

    public void setFunRole_id(String funRole_id) {
        this.funRole_id = funRole_id;
    }

    public String getFunRole_name() {
        return funRole_name;
    }

    public void setFunRole_name(String funRole_name) {
        this.funRole_name = funRole_name;
    }

    public String getDataRole_id() {
        return dataRole_id;
    }

    public void setDataRole_id(String dataRole_id) {
        this.dataRole_id = dataRole_id;
    }

    public String getDataRole_name() {
        return dataRole_name;
    }

    public void setDataRole_name(String dataRole_name) {
        this.dataRole_name = dataRole_name;
    }
}
