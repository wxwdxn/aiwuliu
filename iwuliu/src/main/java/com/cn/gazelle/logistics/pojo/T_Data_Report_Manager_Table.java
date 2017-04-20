/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Report_Manager_Table.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-26
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

/**
 * 类 名 称：T_Data_Report_Manager_Table
 * 内容描述：
 *@authot YK
 */
public class T_Data_Report_Manager_Table {

    private String report_manage_number;                         // 记录编号
    private String report_type_number;                           // 报表类型编号
    private String report_name;                                  // 报表名称
    private String relevance_info_id;                            // 关联信息ID
    private String relevance_info;                               // 应用对象
    private String last_update;                                  //生成日期

    public String getReport_manage_number() {
        return report_manage_number;
    }

    public void setReport_manage_number(String report_manage_number) {
        this.report_manage_number = report_manage_number;
    }

    public String getReport_type_number() {
        return report_type_number;
    }

    public void setReport_type_number(String report_type_number) {
        this.report_type_number = report_type_number;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getRelevance_info_id() {
        return relevance_info_id;
    }

    public void setRelevance_info_id(String relevance_info_id) {
        this.relevance_info_id = relevance_info_id;
    }

    public String getRelevance_info() {
        return relevance_info;
    }

    public void setRelevance_info(String relevance_info) {
        this.relevance_info = relevance_info;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
