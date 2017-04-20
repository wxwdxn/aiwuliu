/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Company_Vehicle_Driver.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-03-07
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

/**
 * 类 名 称：T_Data_Company_Vehicle_Driver
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot QJ
 */
public class T_Data_Company_Vehicle_Driver {
    private String person_name;                 // 司机姓名
    private String person_mobile_phone;         // 司机手机
    private String id_card_number;              // 身份证
    private String driver_licence_number;       // 驾驶证
    private String username;                    // 当前登录账户

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_mobile_phone() {
        return person_mobile_phone;
    }

    public void setPerson_mobile_phone(String person_mobile_phone) {
        this.person_mobile_phone = person_mobile_phone;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getDriver_licence_number() {
        return driver_licence_number;
    }

    public void setDriver_licence_number(String driver_licence_number) {
        this.driver_licence_number = driver_licence_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}