package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类名称:T_Data_Member_Bank_Account
 *内容摘要:会员银行卡信息表
 * Created by zf on 2016/12/12.
 */
public class T_Data_Member_Bank_Account {
    private String account_no;                              //绑定编号
    private String member_id;                               //会员ID
    private String bank_id;                                 //开户银行ID
    private String bank_account;                            //银行账户
    private String bank_save_mobile_phone;                  //预留手机号
    private String  delete_flag;                                // 删除标识符
    private Date last_update;                               // 最终更新日
    private String last_update_user_id;                     // 最终更新者ID
    private String bind_id;                                 // 绑卡唯一标识

    public String getBind_id() {
        return bind_id;
    }

    public void setBind_id(String bind_id) {
        this.bind_id = bind_id;
    }



    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_save_mobile_phone() {
        return bank_save_mobile_phone;
    }

    public void setBank_save_mobile_phone(String bank_save_mobile_phone) {
        this.bank_save_mobile_phone = bank_save_mobile_phone;
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
