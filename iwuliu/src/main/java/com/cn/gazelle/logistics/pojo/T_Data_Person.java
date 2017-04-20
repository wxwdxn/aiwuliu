/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Person.Java
 * 系统编号：Z0001002
 * 系统名称：个人信息实体类
 * 模块编号：01
 * 模块名称：个人信息管理页面
 * 设计文件：
 * 完成日期：2016-02-19
 * 作    者：YK
 * 内容摘要：个人信息实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Person
 * 内容摘要: 个人信息实体类
 * @author YK
 */
public class T_Data_Person {

    private String person_id;                                     //记录id
    private String person_name;                                   //姓名
    private String person_mobile_phone;                           //联系电话
    private String id_card_number;                                //身份证号码
    private String person_type;                                   //身份类型
    private String hold_id_card_pic_id;                           //持证照片
    private String id_card_front_pic_id;                          //身份证图片前
    private String id_card_back_pic_id;                           //身份证图片后
    private String sex;                                           //性别
    private String sex_value;                                     //性别回显值
    private String driver_licence_number;                         //驾驶证号码
    private String driver_licence_main_pic_id;                    //驾驶证主页照片
    private String driver_licence_sub_pic_id;                     //驾驶证副页照片
    private String qualification_certificate_number;              //道路运输从业资格证号码
    private String qualification_certificate_number_pic_id;       //道路运输从业资格证照片
    private String driving_truck_id;                              //关联车辆id
    private String driving_status;                                //驾驶状态
    private Date submit_relate_time;                              //提交车辆关联时间
    private Date confirm_relate_time;                             //确认车辆关联时间
    private Date submit_verify_time;                              //提交审核时间
    private String verify_status;                                 //审核状态
    private String verify_status_value;                           //审核状态回显值
    private String verify_refused_reason;                         //审核拒绝理由
    private Date verify_passed_time;                              //审核通过时间
    private Date verify_refused_time;                             //审核失败时间
    private int driving_evaluate_score;                           //司机行为评价
    private Date last_update;                                     //最终更新日
    private String delete_flag;                                   //删除标识符
    private String last_update_user_id;                           //最终更新者id
    private T_Data_Member member;                                 //关联会员
    private String company_name;                                  //公司姓名
    private String company_id;                                    //公司id


    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

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

    public String getPerson_type() {
        return person_type;
    }

    public void setPerson_type(String person_type) {
        this.person_type = person_type;
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
    }

    public String getHold_id_card_pic_id() {
        return hold_id_card_pic_id;
    }

    public void setHold_id_card_pic_id(String hold_id_card_pic_id) {
        this.hold_id_card_pic_id = hold_id_card_pic_id;
    }

    public String getId_card_front_pic_id() {
        return id_card_front_pic_id;
    }

    public void setId_card_front_pic_id(String id_card_front_pic_id) {
        this.id_card_front_pic_id = id_card_front_pic_id;
    }

    public String getId_card_back_pic_id() {
        return id_card_back_pic_id;
    }

    public void setId_card_back_pic_id(String id_card_back_pic_id) {
        this.id_card_back_pic_id = id_card_back_pic_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex_value() {
        return sex_value;
    }

    public void setSex_value(String sex_value) {
        this.sex_value = sex_value;
    }

    public String getDriver_licence_number() {
        return driver_licence_number;
    }

    public void setDriver_licence_number(String driver_licence_number) {
        this.driver_licence_number = driver_licence_number;
    }

    public String getDriver_licence_main_pic_id() {
        return driver_licence_main_pic_id;
    }

    public void setDriver_licence_main_pic_id(String driver_licence_main_pic_id) {
        this.driver_licence_main_pic_id = driver_licence_main_pic_id;
    }

    public String getDriver_licence_sub_pic_id() {
        return driver_licence_sub_pic_id;
    }

    public void setDriver_licence_sub_pic_id(String driver_licence_sub_pic_id) {
        this.driver_licence_sub_pic_id = driver_licence_sub_pic_id;
    }

    public String getQualification_certificate_number() {
        return qualification_certificate_number;
    }

    public void setQualification_certificate_number(String qualification_certificate_number) {
        this.qualification_certificate_number = qualification_certificate_number;
    }

    public String getQualification_certificate_number_pic_id() {
        return qualification_certificate_number_pic_id;
    }

    public void setQualification_certificate_number_pic_id(String qualification_certificate_number_pic_id) {
        this.qualification_certificate_number_pic_id = qualification_certificate_number_pic_id;
    }

    public String getDriving_truck_id() {
        return driving_truck_id;
    }

    public void setDriving_truck_id(String driving_truck_id) {
        this.driving_truck_id = driving_truck_id;
    }

    public String getDriving_status() {
        return driving_status;
    }

    public void setDriving_status(String driving_status) {
        this.driving_status = driving_status;
    }

    public Date getSubmit_relate_time() {
        return submit_relate_time;
    }

    public void setSubmit_relate_time(Date submit_relate_time) {
        this.submit_relate_time = submit_relate_time;
    }

    public Date getConfirm_relate_time() {
        return confirm_relate_time;
    }

    public void setConfirm_relate_time(Date confirm_relate_time) {
        this.confirm_relate_time = confirm_relate_time;
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

    public String getVerify_status_value() {
        return verify_status_value;
    }

    public void setVerify_status_value(String verify_status_value) {
        this.verify_status_value = verify_status_value;
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

    public int getDriving_evaluate_score() {
        return driving_evaluate_score;
    }

    public void setDriving_evaluate_score(int driving_evaluate_score) {
        this.driving_evaluate_score = driving_evaluate_score;
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

    public T_Data_Member getMember() {
        return member;
    }

    public void setMember(T_Data_Member member) {
        this.member = member;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}