/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ReportServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-26
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.ReportDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Report_Manage;
import com.cn.gazelle.logistics.pojo.T_Data_Report_Manager_Table;
import com.cn.gazelle.logistics.service.ReportService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：ReportServiceImpl
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
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.ReportService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class ReportServiceImpl implements ReportService{
    // Log初始化
    Logger logger = Logger.getLogger(ReportServiceImpl.class);

    @Resource
    private ReportDaoMapper reportDaoMapper;          // 数据访问层


    /**
     * 方法名称：findReportByID
     * 内容摘要：根据主键查找报表信息
     * @param report_manage_number 记录编号
     * @return T_Data_Report_Manage 报表信息
     */
    public T_Data_Report_Manage findReportByID(String report_manage_number) {
        T_Data_Report_Manage reportManage = null;
        try {
            reportManage = this.reportDaoMapper.findReportByID(report_manage_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return reportManage;
    }

    /**
     * 方法名称：findReportByTypeNumber
     * 内容摘要：根据报表类型编号查找报表信息
     * @param report_type_number 报表类型编号
     * @return List<T_Data_Report_Manage>  报表信息
     */
    public List<T_Data_Report_Manage> findReportByTypeNumber(String report_type_number) {
        List<T_Data_Report_Manage> reportManageList = null;
        try {
            reportManageList = this.reportDaoMapper.findReportByTypeNumber(report_type_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return reportManageList;
    }

    /**
     * 方法名称：findReportByRelevanceID
     * 内容摘要：根据关联信息ID查找报表信息
     * @param relevance_info_id 关联信息ID
     * @return List<T_Data_Report_Manage>  报表信息
     */
    public List<T_Data_Report_Manage> findReportByRelevanceID(String relevance_info_id) {
        List<T_Data_Report_Manage> reportManageList = null;
        try {
            reportManageList = this.reportDaoMapper.findReportByRelevanceID(relevance_info_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return reportManageList;
    }

    /**
     * 方法名称：saveReport
     * 内容摘要：保存报表信息
     * @param reportManage 报表信息
     */
    public int saveReport(T_Data_Report_Manage reportManage) {
        int count = 0;
        try {
            count = this.reportDaoMapper.saveReport(reportManage);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateReport
     * 内容摘要：更新报表信息
     * @param reportManage 报表信息
     */
    public boolean updateReport(T_Data_Report_Manage reportManage) {
        boolean b = new Boolean(true);
        try {
            this.reportDaoMapper.updateReport(reportManage);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delReport
     * 内容摘要：删除报表信息
     * @param report_manage_number 记录编号
     */
    public boolean delReport(String report_manage_number) {
        boolean b = new Boolean(true);
        try {
            this.reportDaoMapper.delReport(report_manage_number);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：getReportManagerInfo
     * 内容摘要：查询运维OBD设备管理列表
     *
     * @return List<T_Data_Report_Manager_Table>
     */
    public List<T_Data_Report_Manager_Table> getReportManagerInfo(HashMap<String, String> conditions) {
        List<T_Data_Report_Manager_Table> reportManagerTableList = null;
        try {
            reportManagerTableList = this.reportDaoMapper.getReportManagerInfo(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return reportManagerTableList;
    }

}
