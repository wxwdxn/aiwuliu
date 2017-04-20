package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类名称:T_Data_Member_Payment_History
 *内容摘要:会员支付历史信息表
 * Created by zf on 2016/12/12.
 */
public class T_Data_Member_Payment_History {
   private String history_number;               // 绑定编号
   private String member_id;                    //会员ID
   private String payment_type;                 //支付类型
   private String target_id;                    //对象信息ID
   private String target_bank_account;          //支付账户
   private Double amount ;                      //金额
   private Date   create_time;                  //产生时间
   private String return_result;                //RETURN_RESULT 回传状态
   private Date return_time;                     //RETURN_TIME   回传时间
    private String failure_result;                //FAILURE_RESULT 失败原因
    private String payment_result;               //支付结果
    private String third_party_order_id ;        //第三方订单ID
    private String delete_flag;                  // 删除标识符
    private Date last_update;                    // 最终更新日
    private String last_update_user_id;          // 最终更新者ID

    //查询显示辅助变量
    private String payment_text;              //支付类型信息
    private String target_text;               //对象信息
    private String account_money;             //余额
    private String amount_text;               //交易金额
    private String time_text;                 //交易时间

    public String getTime_text() {
        return time_text;
    }

    public void setTime_text(String time_text) {
        this.time_text = time_text;
    }

    public String getAmount_text() {
        return amount_text;
    }

    public void setAmount_text(String amount_text) {
        this.amount_text = amount_text;
    }



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



    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public String getReturn_result() {
        return return_result;
    }

    public void setReturn_result(String return_result) {
        this.return_result = return_result;
    }

    public String getFailure_result() {
        return failure_result;
    }

    public void setFailure_result(String failure_result) {
        this.failure_result = failure_result;
    }


    public T_Data_Member_Payment_History() {
    }

    public String getThird_party_order_id() {
        return third_party_order_id;
    }

    public void setThird_party_order_id(String third_party_order_id) {
        this.third_party_order_id = third_party_order_id;
    }



    public String getPayment_result() {
        return payment_result;
    }

    public void setPayment_result(String payment_result) {
        this.payment_result = payment_result;
    }

    public String getHistory_number() {
        return history_number;
    }

    public void setHistory_number(String history_number) {
        this.history_number = history_number;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
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
