/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Sys_Info.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-23
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Sys_Info
 * 内容摘要: 系统信息实体类
 *
 * @author YK
 */
public class T_Data_Sys_Info {
    private String member_server_telephone;          // 客服电话
    private int denied_cool_down_time;               // 拒单惩罚时间
    private Date last_update;                        // 最终更新日
    private String last_update_user_id;              // 最终更新者ID
    private String error_payment_password_max_time;  //支付密码每天可接受错误次数

    public String getError_payment_password_max_time() {
        return error_payment_password_max_time;
    }

    public void setError_payment_password_max_time(String error_payment_password_max_time) {
        this.error_payment_password_max_time = error_payment_password_max_time;
    }


    public String getMember_server_telephone() {
        return member_server_telephone;
    }

    public void setMember_server_telephone(String member_server_telephone) {
        this.member_server_telephone = member_server_telephone;
    }

    public int getDenied_cool_down_time() {
        return denied_cool_down_time;
    }

    public void setDenied_cool_down_time(int denied_cool_down_time) {
        this.denied_cool_down_time = denied_cool_down_time;
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
