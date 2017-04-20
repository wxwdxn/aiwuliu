/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Member_Remark.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：会员备注实体类
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Member_Remark
 * 内容描述：会员备注实体类
 *@authot QJ
 */
public class T_Data_Member_Remark {
    private String remark_number;// 备注编号
    private String member_id;// 会员ID
    private String remark_content;// 备注内容
    private Date add_time;// 添加时间
    private int delete_flag;// 删除标识符
    private Date last_update;// 最终更新日
    private String last_update_user_id;// 最终更新者ID

    public String getRemark_number() {
        return remark_number;
    }

    public void setRemark_number(String remark_number) {
        this.remark_number = remark_number;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getRemark_content() {
        return remark_content;
    }

    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
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