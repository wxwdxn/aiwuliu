/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Transaction_Info.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-07
 * 作    者: zf
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

/**
 * 类 名 称：T_Data_Transaction_Info
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@author zf
 */
public class T_Data_Transaction_Info {
    private String account_dept;                                                //所属部门</th>
    private String account_type;                                                //账户类型</th>
    private String account_name;                                                //账户名</th>
    private String order_no;                                                //交易流水号</th>
    private String transaction_time;                                                //交易时间</th>
    private String transaction_type;                                                //交易类别</th>
    private String transaction_name;                                                //交易项目</th>
    private Double transaction_money;                                                //交易金额（元）</th>
    private String transaction_addr;                                                //交易地点</th>
    private Double account_money;                                                //账户余额（元）</th>
    private String transaction_person;                                                //交易人</th>

    public String getAccount_dept() {
        return account_dept;
    }

    public void setAccount_dept(String account_dept) {
        this.account_dept = account_dept;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_name() {
        return transaction_name;
    }

    public void setTransaction_name(String transaction_name) {
        this.transaction_name = transaction_name;
    }

    public Double getTransaction_money() {
        return transaction_money;
    }

    public void setTransaction_money(Double transaction_money) {
        this.transaction_money = transaction_money;
    }

    public String getTransaction_addr() {
        return transaction_addr;
    }

    public void setTransaction_addr(String transaction_addr) {
        this.transaction_addr = transaction_addr;
    }

    public Double getAccount_money() {
        return account_money;
    }

    public void setAccount_money(Double account_money) {
        this.account_money = account_money;
    }

    public String getTransaction_person() {
        return transaction_person;
    }

    public void setTransaction_person(String transaction_person) {
        this.transaction_person = transaction_person;
    }
}

