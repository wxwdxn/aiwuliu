/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Truck.Java
 * 系统编号：Z0001002
 * 系统名称：卡车信息实体类
 * 模块编号：01
 * 模块名称：卡车信息实体类
 * 设计文件：
 * 完成日期：2016-03-04
 * 作    者：QJ
 * 内容摘要：卡车信息实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Truck
 * 内容摘要: 卡车信息实体类
 * @author QJ
 */
public class T_Data_Truck {
    private String truck_id;                                // 绑定车辆ID
    private String plate_number;                            // 车牌号
    private String truck_model_no;                          // 车辆型号编号
    private String truck_length_id;                         // 车辆长度ID
    private String truck_type_id;                           // 车辆类型ID
    private String truck_fuel_type_id;                      // 车辆燃料类型ID
    private String truck_carriage_type_id;                  // 车辆厢型ID
    private String driving_licence;                         // 行驶证号
    private String vehicle_identify_number;                 // 车架号
    private String engine_number;                           // 发动机号
    private String truck_first_pic_save_path;               // 车辆第一张照片
    private String truck_second_pic_save_path;              // 车辆第二张照片
    private String truck_third_pic_save_path;               // 车辆第三张照片
    private String driving_licence_first_page_save_path;    // 行驶证首页照片
    private String driving_licence_second_page_save_path;   // 行驶证第二页照片
    private String insurance_company_id;                    // 保险公司ID
    private String resident_province_id;                    // 常驻省ID
    private String resident_city_id;                        // 常驻城市ID
    private String resident_area_id;                        // 常驻区县ID
    private String owner_member_id;                         // 车主会员ID
    private String deposit_member_id;                       // 托管对象会员ID
    private String manager_member_id;                       // 车辆管理者会员ID
    private String load_weight;                             // 载货重量
    private String load_volume;                             // 载货体积
    private String operate_status;                          // 运营状态
    private int denied_schedule_count;                      // 拒接单次数
    private Date denied_schedule_last_time;                 // 最新拒接单时间
    private Date operate_status_changed_time;               // 运营状态变更时间
    private Date submit_verify_time;                        // 提交审核时间
    private String verify_status;                           // 审核状态
    private String verify_refused_reason;                   // 审核拒绝理由
    private Date verify_passed_time;                        // 审核通过时间
    private Date verify_refused_time;                       // 审核失败时间
    private String payment_password;                        // 支付密码
    private double cash_amount;                             // 卡车现金额度
    private double credit_total_amount;                     // 卡车授信总额度
    private double credit_useable_amount;                   // 卡车授信可用额度
    private Date payment_password_lock_finish_time;         // 支付密码锁定截至时间
    private int delete_flag;                                // 删除标识符
    private Date last_update;                               // 最终更新日
    private String last_update_user_id;                     // 最终更新者ID

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getTruck_model_no() {
        return truck_model_no;
    }

    public void setTruck_model_no(String truck_model_no) {
        this.truck_model_no = truck_model_no;
    }

    public String getTruck_length_id() {
        return truck_length_id;
    }

    public void setTruck_length_id(String truck_length_id) {
        this.truck_length_id = truck_length_id;
    }

    public String getTruck_type_id() {
        return truck_type_id;
    }

    public void setTruck_type_id(String truck_type_id) {
        this.truck_type_id = truck_type_id;
    }

    public String getTruck_fuel_type_id() {
        return truck_fuel_type_id;
    }

    public void setTruck_fuel_type_id(String truck_fuel_type_id) {
        this.truck_fuel_type_id = truck_fuel_type_id;
    }

    public String getTruck_carriage_type_id() {
        return truck_carriage_type_id;
    }

    public void setTruck_carriage_type_id(String truck_carriage_type_id) {
        this.truck_carriage_type_id = truck_carriage_type_id;
    }

    public String getDriving_licence() {
        return driving_licence;
    }

    public void setDriving_licence(String driving_licence) {
        this.driving_licence = driving_licence;
    }

    public String getVehicle_identify_number() {
        return vehicle_identify_number;
    }

    public void setVehicle_identify_number(String vehicle_identify_number) {
        this.vehicle_identify_number = vehicle_identify_number;
    }

    public String getEngine_number() {
        return engine_number;
    }

    public void setEngine_number(String engine_number) {
        this.engine_number = engine_number;
    }

    public String getTruck_first_pic_save_path() {
        return truck_first_pic_save_path;
    }

    public void setTruck_first_pic_save_path(String truck_first_pic_save_path) {
        this.truck_first_pic_save_path = truck_first_pic_save_path;
    }

    public String getTruck_second_pic_save_path() {
        return truck_second_pic_save_path;
    }

    public void setTruck_second_pic_save_path(String truck_second_pic_save_path) {
        this.truck_second_pic_save_path = truck_second_pic_save_path;
    }

    public String getTruck_third_pic_save_path() {
        return truck_third_pic_save_path;
    }

    public void setTruck_third_pic_save_path(String truck_third_pic_save_path) {
        this.truck_third_pic_save_path = truck_third_pic_save_path;
    }

    public String getDriving_licence_first_page_save_path() {
        return driving_licence_first_page_save_path;
    }

    public void setDriving_licence_first_page_save_path(String driving_licence_first_page_save_path) {
        this.driving_licence_first_page_save_path = driving_licence_first_page_save_path;
    }

    public String getDriving_licence_second_page_save_path() {
        return driving_licence_second_page_save_path;
    }

    public void setDriving_licence_second_page_save_path(String driving_licence_second_page_save_path) {
        this.driving_licence_second_page_save_path = driving_licence_second_page_save_path;
    }

    public String getInsurance_company_id() {
        return insurance_company_id;
    }

    public void setInsurance_company_id(String insurance_company_id) {
        this.insurance_company_id = insurance_company_id;
    }

    public String getResident_province_id() {
        return resident_province_id;
    }

    public void setResident_province_id(String resident_province_id) {
        this.resident_province_id = resident_province_id;
    }

    public String getResident_city_id() {
        return resident_city_id;
    }

    public void setResident_city_id(String resident_city_id) {
        this.resident_city_id = resident_city_id;
    }

    public String getResident_area_id() {
        return resident_area_id;
    }

    public void setResident_area_id(String resident_area_id) {
        this.resident_area_id = resident_area_id;
    }

    public String getOwner_member_id() {
        return owner_member_id;
    }

    public void setOwner_member_id(String owner_member_id) {
        this.owner_member_id = owner_member_id;
    }

    public String getDeposit_member_id() {
        return deposit_member_id;
    }

    public void setDeposit_member_id(String deposit_member_id) {
        this.deposit_member_id = deposit_member_id;
    }

    public String getManager_member_id() {
        return manager_member_id;
    }

    public void setManager_member_id(String manager_member_id) {
        this.manager_member_id = manager_member_id;
    }

    public String getLoad_weight() {
        return load_weight;
    }

    public void setLoad_weight(String load_weight) {
        this.load_weight = load_weight;
    }

    public String getLoad_volume() {
        return load_volume;
    }

    public void setLoad_volume(String load_volume) {
        this.load_volume = load_volume;
    }

    public String getOperate_status() {
        return operate_status;
    }

    public void setOperate_status(String operate_status) {
        this.operate_status = operate_status;
    }

    public int getDenied_schedule_count() {
        return denied_schedule_count;
    }

    public void setDenied_schedule_count(int denied_schedule_count) {
        this.denied_schedule_count = denied_schedule_count;
    }

    public Date getDenied_schedule_last_time() {
        return denied_schedule_last_time;
    }

    public void setDenied_schedule_last_time(Date denied_schedule_last_time) {
        this.denied_schedule_last_time = denied_schedule_last_time;
    }

    public Date getOperate_status_changed_time() {
        return operate_status_changed_time;
    }

    public void setOperate_status_changed_time(Date operate_status_changed_time) {
        this.operate_status_changed_time = operate_status_changed_time;
    }

    public Date getSubmit_verify_time() {
        return submit_verify_time;
    }

    public void setSubmit_verify_time(Date submit_verify_time) {
        this.submit_verify_time = submit_verify_time;
    }

    public String getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(String verify_status) {
        this.verify_status = verify_status;
    }

    public String getVerify_refused_reason() {
        return verify_refused_reason;
    }

    public void setVerify_refused_reason(String verify_refused_reason) {
        this.verify_refused_reason = verify_refused_reason;
    }

    public Date getVerify_passed_time() {
        return verify_passed_time;
    }

    public void setVerify_passed_time(Date verify_passed_time) {
        this.verify_passed_time = verify_passed_time;
    }

    public Date getVerify_refused_time() {
        return verify_refused_time;
    }

    public void setVerify_refused_time(Date verify_refused_time) {
        this.verify_refused_time = verify_refused_time;
    }

    public String getPayment_password() {
        return payment_password;
    }

    public void setPayment_password(String payment_password) {
        this.payment_password = payment_password;
    }

    public double getCash_amount() {
        return cash_amount;
    }

    public void setCash_amount(double cash_amount) {
        this.cash_amount = cash_amount;
    }

    public double getCredit_total_amount() {
        return credit_total_amount;
    }

    public void setCredit_total_amount(double credit_total_amount) {
        this.credit_total_amount = credit_total_amount;
    }

    public double getCredit_useable_amount() {
        return credit_useable_amount;
    }

    public void setCredit_useable_amount(double credit_useable_amount) {
        this.credit_useable_amount = credit_useable_amount;
    }

    public Date getPayment_password_lock_finish_time() {
        return payment_password_lock_finish_time;
    }

    public void setPayment_password_lock_finish_time(Date payment_password_lock_finish_time) {
        this.payment_password_lock_finish_time = payment_password_lock_finish_time;
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
