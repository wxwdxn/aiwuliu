/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TransportationScheduleFinanceRecordServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物运输调度单财务流水信息接口实现
 * 设计文件：
 * 完成日期：2016-07-27
 * 作    者: QJ
 * 内容摘要：货物运输调度单财务流水信息接口实现
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TransportationScheduleFinanceRecordDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Finance_Record;
import com.cn.gazelle.logistics.service.TransportationScheduleFinanceRecordService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.message.TransportationScheduleFinanceRecordManager_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称：TransportationScheduleFinanceRecordServiceImpl
 * 内容描述：货物运输调度单财务流水信息接口实现
 * 方法描述：该类有7个方法：
 *         01 findFinacialFlowById              根据ID查询货物运输调度单财务流水信息
 *         02 findAllFinacialFlow               查询符合条件的货物运输调度单财务流水信息（默认查询所有货物运输调度单财务流水信息）
 *         03 findAllFinacialFlowRowsCount      查询货物运输调度单财务流水信息总记录数
 *         04 saveFinacialFlow                  保存货物运输调度单财务流水信息
 *         05 updateFinacialFlow                更新货物运输调度单财务流水信息
 *         06 delFinacialFlow                   根据ID删除货物运输调度单财务流水信息
 *         07 findFinacialFlowByScheduleId      根据调度单ID查询货物运输调度单财务流水信息
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TransportationScheduleFinanceRecordService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TransportationScheduleFinanceRecordServiceImpl implements TransportationScheduleFinanceRecordService {
    // Log初始化
    Logger logger = Logger.getLogger(TransportationScheduleFinanceRecordServiceImpl.class);
    @Resource
    private TransportationScheduleFinanceRecordDaoMapper transportationScheduleFinanceRecordDaoMapper;    // 数据访问层

    /**
     * 方法名称：findFinacialFlowById
     * 内容摘要：根据ID查询货物运输调度单财务流水信息。
     * @param finacial_flow_id  ID
     * @return T_Data_Transportation_Schedule_Finance_Record    货物运输调度单财务流水信息
     */
    public T_Data_Transportation_Schedule_Finance_Record findFinacialFlowById(String finacial_flow_id) {
        return transportationScheduleFinanceRecordDaoMapper.findFinacialFlowById(finacial_flow_id);
    }

    /**
     * 方法名称：findAllFinacialFlow
     * 内容摘要：查询符合条件的货物运输调度单财务流水信息（默认查询所有货物运输调度单财务流水信息）
     * @param search_type   搜索类型
     * @param name  查询类型
     * @param page  页面页数
     * @param rows  页面显示条数
     * @return List<T_Data_Transportation_Schedule_Finance_Record>  货物运输调度单财务流水信息列
     */
    public List<T_Data_Transportation_Schedule_Finance_Record> findAllFinacialFlow(String search_type, String name, Integer page, Integer rows) {
        return transportationScheduleFinanceRecordDaoMapper.findAllFinacialFlow(search_type, name, page, rows);
    }

    /**
     * 方法名称：findAllFinacialFlowRowsCount
     * 内容摘要：查询货物运输调度单财务流水信息总记录数
     * @param search_type   搜索类型
     * @param name  查询类型
     * @return Integer 货物运输调度单财务流水信息总记录数
     */
    public Integer findAllFinacialFlowRowsCount(String search_type, String name) {
        return transportationScheduleFinanceRecordDaoMapper.findAllFinacialFlowRowsCount(search_type, name);
    }

    /**
     * 方法名称：saveFinacialFlow
     * 内容摘要：保存货物运输调度单财务流水信息
     * @param finacial_flow 货物运输调度单财务流水信息
     */
    public boolean saveFinacialFlow(T_Data_Transportation_Schedule_Finance_Record finacial_flow) {
        boolean a = new Boolean(true);
        try {
            this.transportationScheduleFinanceRecordDaoMapper.saveFinacialFlow(finacial_flow);
            logger.info(TransportationScheduleFinanceRecordManager_Message.saveFinacialFlowDone);
        } catch (Exception e) {
            a=false;
            logger.error(TransportationScheduleFinanceRecordManager_Message.saveFinacialFlowError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：updateFinacialFlow
     * 内容摘要：更新货物运输调度单财务流水信息
     * @param finacial_flow 货物运输调度单财务流水信息
     */
    public boolean updateFinacialFlow(T_Data_Transportation_Schedule_Finance_Record finacial_flow) {
        boolean a=new Boolean(true);
        try {
            finacial_flow.setLast_update(DateUtil.getDate());
            transportationScheduleFinanceRecordDaoMapper.updateFinacialFlow(finacial_flow);
        } catch (Exception e) {
            a=false;
            logger.error(TransportationScheduleFinanceRecordManager_Message.updateFinacialFlowError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delFinacialFlow
     * 内容摘要：根据ID删除货物运输调度单财务流水信息
     * @param finacial_flow_id  货物运输调度单财务流水信息ID
     */
    public void delFinacialFlow(String finacial_flow_id) {
        try {
            transportationScheduleFinanceRecordDaoMapper.delFinacialFlow(finacial_flow_id);
            logger.info(TransportationScheduleFinanceRecordManager_Message.delFinacialFlowDone);
        } catch (Exception e) {
            logger.error(TransportationScheduleFinanceRecordManager_Message.delFinacialFlowError+e.getMessage());
        }
    }

    /**
     * 方法名称：findFinacialFlowByScheduleId
     * 内容摘要：根据调度单ID查询货物运输调度单财务流水信息
     * @param schedule_id   根据调度单ID
     * @return List<T_Data_Transportation_Schedule_Finance_Record>  货物运输调度单财务流水信息列
     */
    public List<T_Data_Transportation_Schedule_Finance_Record> findFinacialFlowByScheduleId(String schedule_id) {
        List<T_Data_Transportation_Schedule_Finance_Record> finacialFlowByScheduleId=null;
        try {
            finacialFlowByScheduleId = transportationScheduleFinanceRecordDaoMapper.findFinacialFlowByScheduleId(schedule_id);
        } catch (Exception e) {
            logger.error(TransportationScheduleFinanceRecordManager_Message.findFinacialFlowRowsCountDone+e.getMessage());
        }
        return finacialFlowByScheduleId;
    }
}