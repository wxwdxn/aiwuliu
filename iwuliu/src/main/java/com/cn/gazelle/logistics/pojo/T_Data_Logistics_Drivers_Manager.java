/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Logistics_Drivers_Manager.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-03-05
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

/**
 * 类 名 称：T_Data_Logistics_Drivers_Manager
 * 内容描述：物流公司司机管理信息实体类
 *@authot YK
 */

public class T_Data_Logistics_Drivers_Manager {
    private String person_id ;                                     // 个人id
    private String manager_member_name ;                           // 车组成员
    private String owner_member_name ;                             // 司机姓名
    private String person_mobile_phone ;                           // 手机
    private String id_card_number ;                                // 证件号码
    private String driver_licence_number ;                         // 驾驶证档案编号
    private String line ;                                          // 长跑干线
    private String plate_number ;                                  // 绑定车牌号
    private String verify_status ;                                 // 审核状态
    private String verify_status_value ;                           // 审核状态回显值
    private String truck_id;                                       // 卡车id
    private String verify_refused_reason;                          // 审核拒绝理由
    private String last_update;                                    // 最新更新时间

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getManager_member_name() {
        return manager_member_name;
    }

    public void setManager_member_name(String manager_member_name) {
        this.manager_member_name = manager_member_name;
    }

    public String getOwner_member_name() {
        return owner_member_name;
    }

    public void setOwner_member_name(String owner_member_name) {
        this.owner_member_name = owner_member_name;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(String verify_status) {
        this.verify_status = verify_status;
    }

    public String getVerify_status_value() {
        return verify_status_value;
    }

    public void setVerify_status_value(String verify_status_value) {
        this.verify_status_value = verify_status_value;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public String getVerify_refused_reason() {
        return verify_refused_reason;
    }

    public void setVerify_refused_reason(String verify_refused_reason) {
        this.verify_refused_reason = verify_refused_reason;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}

