package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类名称:T_Data_Truck_Payment_History
 * 内容摘要:卡车支付历史信息表
 * Created by zf on 2016/12/14.
 */

public class T_Data_Truck_Payment_History {
    private  String history_number;         //历史编号
    private String  truck_id;//卡车ID
    private String  payment_type;//支付类型
    private String  finacial_flow_type;// 支出类型ID
    private String target_id;//对象信息ID
    private String target_bank_account;//支付账户
    private Double amount;//金额
    private Date create_time;//产生时间
    private Double truck_longitude;//      卡车经度
    private Double truck_latitude;//卡车纬度
    private Double terminal_longitude;//终端经度
    private Double terminal_latitude;//终端纬度
    private String deal_person_id;//支出人员ID
    private String return_result;//回传状态
    private Date return_time;//回传时间
    private String payment_result;//支付结果
    private String failure_result;//失败原因
    private String third_party_order_id;//第三方订单ID
    private String delete_flag;//删除标识符
    private Date last_update;//最终更新日
    private String last_update_user_id;//最终更新者ID


    //查询显示辅助变量
    private String payment_text;              //支付类型信息
    private String target_text;               //对象信息
    private String account_money;             //余额
    private String amount_text;               //交易金额
    private String time_text;                 //交易时间

    public String getPayment_text() {
        return payment_text;
    }

    public void setPayment_text(String payment_text) {
        this.payment_text = payment_text;
    }

    public String getTarget_text() {
        return target_text;
    }

    public void setTarget_text(String target_text) {
        this.target_text = target_text;
    }

    public String getAccount_money() {
        return account_money;
    }

    public void setAccount_money(String account_money) {
        this.account_money = account_money;
    }

    public String getAmount_text() {
        return amount_text;
    }

    public void setAmount_text(String amount_text) {
        this.amount_text = amount_text;
    }

    public String getTime_text() {
        return time_text;
    }

    public void setTime_text(String time_text) {
        this.time_text = time_text;
    }

    public String getHistory_number() {
        return history_number;
    }

    public void setHistory_number(String history_number) {
        this.history_number = history_number;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getFinacial_flow_type() {
        return finacial_flow_type;
    }

    public void setFinacial_flow_type(String finacial_flow_type) {
        this.finacial_flow_type = finacial_flow_type;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getTarget_bank_account() {
        return target_bank_account;
    }

    public void setTarget_bank_account(String target_bank_account) {
        this.target_bank_account = target_bank_account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Double getTruck_longitude() {
        return truck_longitude;
    }

    public void setTruck_longitude(Double truck_longitude) {
        this.truck_longitude = truck_longitude;
    }

    public Double getTruck_latitude() {
        return truck_latitude;
    }

    public void setTruck_latitude(Double truck_latitude) {
        this.truck_latitude = truck_latitude;
    }

    public Double getTerminal_longitude() {
        return terminal_longitude;
    }

    public void setTerminal_longitude(Double terminal_longitude) {
        this.terminal_longitude = terminal_longitude;
    }

    public Double getTerminal_latitude() {
        return terminal_latitude;
    }

    public void setTerminal_latitude(Double terminal_latitude) {
        this.terminal_latitude = terminal_latitude;
    }

    public String getDeal_person_id() {
        return deal_person_id;
    }

    public void setDeal_person_id(String deal_person_id) {
        this.deal_person_id = deal_person_id;
    }

    public String getReturn_result() {
        return return_result;
    }

    public void setReturn_result(String return_result) {
        this.return_result = return_result;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public String getPayment_result() {
        return payment_result;
    }

    public void setPayment_result(String payment_result) {
        this.payment_result = payment_result;
    }

    public String getFailure_result() {
        return failure_result;
    }

    public void setFailure_result(String failure_result) {
        this.failure_result = failure_result;
    }

    public String getThird_party_order_id() {
        return third_party_order_id;
    }

    public void setThird_party_order_id(String third_party_order_id) {
        this.third_party_order_id = third_party_order_id;
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
}
