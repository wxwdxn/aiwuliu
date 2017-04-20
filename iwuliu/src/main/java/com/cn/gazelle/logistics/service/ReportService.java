/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ReportService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-26
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Report_Manage;
import com.cn.gazelle.logistics.pojo.T_Data_Report_Manager_Table;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：ReportService
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 findReportByID                                         根据主键查找报表信息
 *          02 findReportByTypeNumber                                 根据报表类型编号查找信息
 *          03 findReportByRelevanceID                                根据关联信息ID查找信息
 *          04 saveReport                                             保存报表信息
 *          05 updateReport                                           更新报表信息
 *          06 delReport                                              删除报表信息
 *@authot YK
 */
@WebService
public interface ReportService {
    // 根据主键查找报表信息
    T_Data_Report_Manage findReportByID(String report_manage_number);

    // 根据报表类型编号查找信息
    List<T_Data_Report_Manage> findReportByTypeNumber(String report_type_number);

    // 根据关联信息ID查找信息
    List<T_Data_Report_Manage> findReportByRelevanceID(String relevance_info_id);

    // 保存报表信息
    int saveReport(T_Data_Report_Manage reportManage);

    // 更新报表信息
    boolean updateReport(T_Data_Report_Manage reportManage);

    // 删除报表信息
    boolean delReport(String report_manage_number);

    // 获取报表管理列表信息
    List<T_Data_Report_Manager_Table> getReportManagerInfo(HashMap<String,String> conditions);
}
