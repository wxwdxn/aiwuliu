/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Withdraw_Apply_Detail.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-10
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

/**
 * 类 名 称：T_Data_Withdraw_Apply_Detail
 * 内容描述：提现申请实体类
 *@authot YK
 */
public class T_Data_Withdraw_Apply_Detail {
    private String   transaction_no;                                          //交易流水号</th>
    private String   order_no;                                                //订单号</th>
    private String   account_dept;                                            //所属公司</th>
    private String   account_name;                                            //账户名</th>
    private String   transaction_name;                                        //交易人姓名</th>
    private String   income_name;                                              //收款方</th>
    private Double   withdraw_money;                                            //提现金额（元）</th>
    private String   settle_status;                                             //结算状态</th>
    private String   check_status;                                               //审核状态</th>
    private String   create_time;                                               //创建时间</th>
    private String   withdraw_remark;                                            //备注</th>



    public String getWithdraw_remark() {
        return withdraw_remark;
    }

    public void setWithdraw_remark(String withdraw_remark) {
        this.withdraw_remark = withdraw_remark;
    }
    public String getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(String transaction_no) {
        this.transaction_no = transaction_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAccount_dept() {
        return account_dept;
    }

    public void setAccount_dept(String account_dept) {
        this.account_dept = account_dept;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getTransaction_name() {
        return transaction_name;
    }

    public void setTransaction_name(String transaction_name) {
        this.transaction_name = transaction_name;
    }

    public String getIncome_name() {
        return income_name;
    }

    public void setIncome_name(String income_name) {
        this.income_name = income_name;
    }

    public Double getWithdraw_money() {
        return withdraw_money;
    }

    public void setWithdraw_money(Double withdraw_money) {
        this.withdraw_money = withdraw_money;
    }

    public String getSettle_status() {
        return settle_status;
    }

    public void setSettle_status(String settle_status) {
        this.settle_status = settle_status;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }


}

