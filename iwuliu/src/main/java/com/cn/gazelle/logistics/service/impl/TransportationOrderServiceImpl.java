/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationOrderServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：订单的管理和app接口的实现
 * 设计文件：
 * 完成日期：2016-03-07
 * 作    者：WXW
 * 内容摘要：订单的管理和app接口的实现
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Plan;
import com.cn.gazelle.logistics.service.TransportationOrderService;
import com.cn.gazelle.logistics.util.message.TransportationOrder_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
/**
 * 类 名 称: TransportationOrderService
 * 内容摘要: 订单信息管理和接口声明
 * 方法描述：该类有8个方法：
 *         01 saveTransportateOrder                 添加订单
 *         02 delTransportateOrderById              删除运输订单
 *         03 updateTransportateOrder               更新运输订单
 *         04 findOrderListByContractIdAndBelongNull 保存运输订单信息
 *         05 findOrderListByBelongCode             根据所属编号
 *         06 findOrderListBelongNull               查询一级运单
 *         07 findPlanByConditions                  根据合同 ，计划状态，干线 查询所有的从属计划为空计划-
 *         08 findBySchedulePlanNumber              根据调度编号查询
 * @author WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TransportationOrderService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TransportationOrderServiceImpl implements TransportationOrderService {
    // Log初始化
    Logger logger = Logger.getLogger("TransportationOrderServiceImpl.class");

    @Resource
    private ScheduleSheetDaoMapper scheduleSheetDaoMapper;
    @Resource
    private MemberDaoMapper memberDaoMapper;
    @Resource
    private TransportationOrderDaoMapper transportationOrderDaoMapper;
    @Resource
    private CargoDaoMapper cargoDaoMapper;
    @Resource
    private TransportationContractDaoMapper contractDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;
    @Resource
    private CargoTypeDaoMapper cargoTypeDaoMapper;

    /**
     * 方法名称：saveTransportateOrder
     * 内容摘要：添加订单
     *
     * @param transportation_order 订单信息
     */
    @Transactional
    public boolean saveTransportateOrder(T_Data_Transportation_Plan transportation_order) {
        boolean a = new Boolean(true);
        try {
            transportationOrderDaoMapper.saveTransportateOrder(transportation_order);
            logger.info(TransportationOrder_Message.SaveTransportationOrderInfo);
        } catch (Exception e) {
            a = false;
            logger.error(TransportationOrder_Message.SaveTransportationOrderError + e.getMessage());

        }
        return a;
    }

    /**
     * 方法名称：delTransportateOrderById
     * 内容摘要：删除运输订单
     *
     * @param order_id 订单id
     */
    @Transactional
    public void delTransportateOrderById(String order_id) {
        try {
            transportationOrderDaoMapper.delTransportateOrderById(order_id);
            logger.info(TransportationOrder_Message.DelTransportationOrderInfo);
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.DelTransportationOrderError + e.getMessage());
        }

    }

    /**
     * 方法名称：updateTransportateOrder
     * 内容摘要：更新运输订单
     *
     * @param transportation_order
     */
    @Transactional
    public boolean updateTransportateOrder(T_Data_Transportation_Plan transportation_order) {
        boolean a = new Boolean(true);
        try {
            transportationOrderDaoMapper.updateTransportateOrder(transportation_order);
            logger.info(TransportationOrder_Message.UpdateTransportationOrderInfo);
        } catch (Exception e) {
            a = false;
            logger.error(TransportationOrder_Message.UpdateTransportationOrderError + e.getMessage());
        }
        return a;
    }




    /**
     * 方法名称：findOrderListByContractIdAndBelongNull
     * 内容摘要：根据合同查询查询一级运单
     *
     * @param contract_id
     */
    @Transactional
    public List<T_Data_Transportation_Plan> findOrderListByContractIdAndBelongNull(String contract_id) {
        List list = null;
        try {
            list = transportationOrderDaoMapper.findOrderListByContractIdAndBelongNull(contract_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 方法名称：findOrderListByBelongCode
     * 内容摘要：根据所属编号
     *
     * @param belong_schedule_plan_number
     */
    @Transactional
    public List<T_Data_Transportation_Plan> findOrderListByBelongCode(String belong_schedule_plan_number) {
        List list =null;
        try {
            list=transportationOrderDaoMapper.findOrderListByBelongCode(belong_schedule_plan_number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 方法名称：findOrderListBelongNull
     * 内容摘要：查询一级运单
     *
     */
    @Transactional
    public List<T_Data_Transportation_Plan> findOrderListBelongNull() {
        List<T_Data_Transportation_Plan> planList=null;
        try {
            planList = transportationOrderDaoMapper.findOrderListBelongNull();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return planList;
    }

    /**
     * 方法名称：findPlanByConditions
     * 内容摘要：根据合同 ，计划状态，干线 查询所有的从属计划为空计划-
     *
     * @param contractId 合同
     * @param lineId 干线
     * @param status 计划状态
     * @return T_Data_Transportation_Plan list
     */
    @Transactional
    public List<T_Data_Transportation_Plan> findPlanByConditions(String contractId, String lineId, String status) {
        List<T_Data_Transportation_Plan> planList=null;
        try {
            planList = transportationOrderDaoMapper.findPlanByConditions(contractId, lineId, status);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return planList;
    }


    /**
     * 方法名称：findBySchedulePlanNumber
     * 内容摘要：根据调度编号查询
     * @param schedule_plan_number 编号
     * @return T_Data_Transportation_Plan list
     */
    @Transactional
    public T_Data_Transportation_Plan findBySchedulePlanNumber(String schedule_plan_number) {
        T_Data_Transportation_Plan transportationPlan=null;
        try {
            transportationPlan = transportationOrderDaoMapper.findBySchedulePlanNumber(schedule_plan_number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportationPlan;
    }

    /**
     * 方法名称：manualOrderList
     * 内容摘要：手动派单
     * @return T_Data_Transportation_Plan list
     */
    @Transactional
    public List<T_Data_Transportation_Plan> manualOrderList() {
        List<T_Data_Transportation_Plan> planList=null;
        try {
            planList = transportationOrderDaoMapper.manualOrderList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planList;
    }


    /**
     * 方法名称：manualOrderList
     * 内容摘要：手动派单
     * @return T_Data_Transportation_Plan list
     */
    @Transactional
    @Override
    public List<T_Data_Transportation_Plan> manualFinishedOrderList() {
        List<T_Data_Transportation_Plan> planList=null;
        try {
            planList = transportationOrderDaoMapper.manualFinishedOrderList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planList;
    }

}
