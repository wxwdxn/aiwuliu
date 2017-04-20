/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Company.Java
 * 系统编号：Z0001002
 * 系统名称：公司信息实体类
 * 模块编号：01
 * 模块名称：公司信息管理页面
 * 设计文件：
 * 完成日期：2016-02-18
 * 作    者：YK
 * 内容摘要：公司信息实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Company
 * 内容摘要: 会员信息实体类
 * @author YK
 */
public class T_Data_Company {

    private String company_id;                      // 记录ID
    private String company_name;                    // 公司名称
    private String business_licence;                // 营业执照号码
    private String business_licence_pic_id;         // 营业执照照片
    private String company_fixed_phone;             // 公司固定电话
    private String contact_name;                    // 联系人
    private String contact_sex;                     // 性别
    private String contact_mobile_phone;            // 联系人移动电话
    private String company_type;                    // 企业类型
    private String province_id;                     // 所在省ID
    private String city_id;                         // 所在城市ID
    private String area_id;                         // 所在区县ID
    private String town_street;                     // 所在村镇街道
    private double longitude;                       // 经度
    private double latitude;                        // 纬度
    private String start_work_time;                 // 开始工作时间
    private String finish_work_time;                // 结束工作时间
    private String store_pic_id;                    // 店面照片
    private Date register_time;                     // 申请时间
    private String bank_account;                    // 银行帐号
    private String status;                          // 状态
    private String refused_reason;                  // 拒绝理由
    private Date status_update_time;                // 状态更新时间
    private String delete_flag;                     // 删除标识符
    private Date last_update;                       // 最终更新日
    private String last_update_user_id;             // 最终更新者ID
    private String bank_id;                         // 最终更新者ID
    // 新增属性用于回显
    private String contact_sex_value;               // 性别对应的code
    private String province_id_value;               // 所在省ID对应的code
    private String city_id_value;                   // 所在城市ID对应的code
    private String area_id_value;                   // 所在区县ID对应的code
    private String bank_id_value;                   // 开户银行ID对应的code

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBusiness_licence() {
        return business_licence;
    }

    public void setBusiness_licence(String business_licence) {
        this.business_licence = business_licence;
    }

    public String getBusiness_licence_pic_id() {
        return business_licence_pic_id;
    }

    public void setBusiness_licence_pic_id(String business_licence_pic_id) {
        this.business_licence_pic_id = business_licence_pic_id;
    }

    public String getCompany_fixed_phone() {
        return company_fixed_phone;
    }

    public void setCompany_fixed_phone(String company_fixed_phone) {
        this.company_fixed_phone = company_fixed_phone;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_sex() {
        return contact_sex;
    }

    public void setContact_sex(String contact_sex) {
        this.contact_sex = contact_sex;
    }

    public String getContact_mobile_phone() {
        return contact_mobile_phone;
    }

    public void setContact_mobile_phone(String contact_mobile_phone) {
        this.contact_mobile_phone = contact_mobile_phone;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getStart_work_time() {
        return start_work_time;
    }

    public void setStart_work_time(String start_work_time) {
        this.start_work_time = start_work_time;
    }

    public String getFinish_work_time() {
        return finish_work_time;
    }

    public void setFinish_work_time(String finish_work_time) {
        this.finish_work_time = finish_work_time;
    }

    public String getStore_pic_id() {
        return store_pic_id;
    }

    public void setStore_pic_id(String store_pic_id) {
        this.store_pic_id = store_pic_id;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefused_reason() {
        return refused_reason;
    }

    public void setRefused_reason(String refused_reason) {
        this.refused_reason = refused_reason;
    }

    public Date getStatus_update_time() {
        return status_update_time;
    }

    public void setStatus_update_time(Date status_update_time) {
        this.status_update_time = status_update_time;
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

    public String getContact_sex_value() {
        return contact_sex_value;
    }

    public void setContact_sex_value(String contact_sex_value) {
        this.contact_sex_value = contact_sex_value;
    }

    public String getProvince_id_value() {
        return province_id_value;
    }

    public void setProvince_id_value(String province_id_value) {
        this.province_id_value = province_id_value;
    }

    public String getCity_id_value() {
        return city_id_value;
    }

    public void setCity_id_value(String city_id_value) {
        this.city_id_value = city_id_value;
    }

    public String getArea_id_value() {
        return area_id_value;
    }

    public void setArea_id_value(String area_id_value) {
        this.area_id_value = area_id_value;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_id_value() {
        return bank_id_value;
    }

    public void setBank_id_value(String bank_id_value) {
        this.bank_id_value = bank_id_value;
    }
}