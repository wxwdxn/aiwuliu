/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Sys_Authority.Java
 * 系统编号：Z0001002
 * 系统名称：权限实体类
 * 模块编号：01
 * 模块名称：权限管理页面
 * 设计文件：
 * 完成日期：2016-01-05
 * 作    者：YK
 * 内容摘要：权限实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Sys_Authority
 * 内容摘要: 权限实体类
 * @author YK
 */
public class T_Sys_Authority {
   private String authority_id;             // 权限编码
   private String authority_name;           // 权限名称
   private String authority_url;            // 权限地址
   private Date authority_date;             // 添加日期
   private String authority_info;           // 权限说明
   private String delete_states;            // 删除状态
   private String authority_remark;         // 备注

    public String getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(String authority_id) {
        this.authority_id = authority_id;
    }

    public String getAuthority_name() {
        return authority_name;
    }

    public void setAuthority_name(String authority_name) {
        this.authority_name = authority_name;
    }

    public String getAuthority_url() {
        return authority_url;
    }

    public void setAuthority_url(String authority_url) {
        this.authority_url = authority_url;
    }

    public Date getAuthority_date() {
        return authority_date;
    }

    public void setAuthority_date(Date authority_date) {
        this.authority_date = authority_date;
    }

    public String getAuthority_info() {
        return authority_info;
    }

    public void setAuthority_info(String authority_info) {
        this.authority_info = authority_info;
    }

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getAuthority_remark() {
        return authority_remark;
    }

    public void setAuthority_remark(String authority_remark) {
        this.authority_remark = authority_remark;
    }
}
