/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Report_Manage.java
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

import java.util.Date;

/**
 * 类 名 称：T_Data_Report_Manage
 * 内容描述：报表管理实体类
 *@authot YK
 */
public class T_Data_Report_Manage {

    private String report_manage_number;                 // 记录编号
    private String report_type_number;                   // 报表类型编号
    private String relevance_info_id;                    // 关联信息id
    private String delete_flag;                          // 删除标识符
    private Date last_update;                            // 最终更新日
    private String last_update_user_id;                  // 最终更新者ID

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

    public String getRelevance_info_id() {
        return relevance_info_id;
    }

    public void setRelevance_info_id(String relevance_info_id) {
        this.relevance_info_id = relevance_info_id;
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
