/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Transportation_Plan.Java
 * 系统编号：Z0001002
 * 系统名称：订单信息表实体类
 * 模块编号：01
 * 模块名称：订单信息表实体类
 * 设计文件：
 * 完成日期：2016-03-07
 * 作    者：WXW
 * 内容摘要：订单信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Transportation_Plan
 * 内容摘要: 订单信息表实体类
 * @author WXW
 */
public class  T_Data_Transportation_Plan {
    private String schedule_plan_number;//调度计划编号
    private String contract_id;//合同
    private String belong_schedule_plan_number;//所属调度计划编号
    private String operate_main_line_id;//对应运营干线ID
    private String  loading_cargo_yard_id;//装货货场ID
    private String loading_begin_date;//装货开始时间
    private String loading_contact_name;//装货联系人姓名 
    private String loading_contact_phone;//装货联系人电话
    private String unloading_cargo_yard_id;//卸货货场ID
    private String  unloading_finish_date;//卸货结束时间
    private String unloading_contact_name;//卸货联系人姓名
    private String unloading_contact_phone;//卸货联系人电话
    private String remark;                 // 备注
    private int cargo_total;//货物总数
    private double transport_unit_price;//运输单价
    private double redistribute_cargo_total=0;//已分配货物数量
    private double schedule_cargo_total;//已承接货物数量
    private double transported_cargo_total;//已运输货物数量
    private double transport_completed_cargo_total=0;//已运输完成货物数量
    private Date create_time;//创建时间
    private String accept_time;//审核通过时间
    private String refuse_time;//审核未通过时间
    private String start_schedule_time;//开始时间
    private String finish_time;//完成时间
    private String payed_time;//支付时间
    private String average_transportation_time;//平均运输时间
    private String status;//状态
    private String refused_reason;//拒绝理由
    private  int deleteFlag;//删除标识
    private Date last_update;//最终更新日
    private String last_update_user_id;//最终更新者ID
    private String code;//编号

    public double getRedistribute_cargo_total() {
        return redistribute_cargo_total;
    }

    public void setRedistribute_cargo_total(double redistribute_cargo_total) {
        this.redistribute_cargo_total = redistribute_cargo_total;
    }

    public String getAverage_transportation_time() {
        return average_transportation_time;
    }

    public void setAverage_transportation_time(String average_transportation_time) {
        this.average_transportation_time = average_transportation_time;
    }

    public String getSchedule_plan_number() {
        return schedule_plan_number;
    }

    public void setSchedule_plan_number(String schedule_plan_number) {
        this.schedule_plan_number = schedule_plan_number;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public String getOperate_main_line_id() {
        return operate_main_line_id;
    }

    public void setOperate_main_line_id(String operate_main_line_id) {
        this.operate_main_line_id = operate_main_line_id;
    }

    public String getLoading_cargo_yard_id() {
        return loading_cargo_yard_id;
    }

    public void setLoading_cargo_yard_id(String loading_cargo_yard_id) {
        this.loading_cargo_yard_id = loading_cargo_yard_id;
    }

    public String getLoading_begin_date() {
        return loading_begin_date;
    }

    public void setLoading_begin_date(String loading_begin_date) {
        this.loading_begin_date = loading_begin_date;
    }

    public String getLoading_contact_name() {
        return loading_contact_name;
    }

    public void setLoading_contact_name(String loading_contact_name) {
        this.loading_contact_name = loading_contact_name;
    }

    public String getLoading_contact_phone() {
        return loading_contact_phone;
    }

    public void setLoading_contact_phone(String loading_contact_phone) {
        this.loading_contact_phone = loading_contact_phone;
    }

    public String getUnloading_cargo_yard_id() {
        return unloading_cargo_yard_id;
    }

    public void setUnloading_cargo_yard_id(String unloading_cargo_yard_id) {
        this.unloading_cargo_yard_id = unloading_cargo_yard_id;
    }

    public String getUnloading_finish_date() {
        return unloading_finish_date;
    }

    public void setUnloading_finish_date(String unloading_finish_date) {
        this.unloading_finish_date = unloading_finish_date;
    }

    public String getUnloading_contact_name() {
        return unloading_contact_name;
    }

    public void setUnloading_contact_name(String unloading_contact_name) {
        this.unloading_contact_name = unloading_contact_name;
    }

    public String getUnloading_contact_phone() {
        return unloading_contact_phone;
    }

    public void setUnloading_contact_phone(String unloading_contact_phone) {
        this.unloading_contact_phone = unloading_contact_phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCargo_total() {
        return cargo_total;
    }

    public void setCargo_total(int cargo_total) {
        this.cargo_total = cargo_total;
    }

    public double getTransport_unit_price() {
        return transport_unit_price;
    }

    public void setTransport_unit_price(double transport_unit_price) {
        this.transport_unit_price = transport_unit_price;
    }

    public double getSchedule_cargo_total() {
        return schedule_cargo_total;
    }

    public void setSchedule_cargo_total(double schedule_cargo_total) {
        this.schedule_cargo_total = schedule_cargo_total;
    }

    public double getTransported_cargo_total() {
        return transported_cargo_total;
    }

    public void setTransported_cargo_total(double transported_cargo_total) {
        this.transported_cargo_total = transported_cargo_total;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(String accept_time) {
        this.accept_time = accept_time;
    }

    public String getRefuse_time() {
        return refuse_time;
    }

    public void setRefuse_time(String refuse_time) {
        this.refuse_time = refuse_time;
    }

    public String getStart_schedule_time() {
        return start_schedule_time;
    }

    public void setStart_schedule_time(String start_schedule_time) {
        this.start_schedule_time = start_schedule_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getPayed_time() {
        return payed_time;
    }

    public void setPayed_time(String payed_time) {
        this.payed_time = payed_time;
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

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getTransport_completed_cargo_total() {
        return transport_completed_cargo_total;
    }

    public void setTransport_completed_cargo_total(double transport_completed_cargo_total) {
        this.transport_completed_cargo_total = transport_completed_cargo_total;
    }

    public String getBelong_schedule_plan_number() {
        return belong_schedule_plan_number;
    }

    public void setBelong_schedule_plan_number(String belong_schedule_plan_number) {
        this.belong_schedule_plan_number = belong_schedule_plan_number;
    }
}
