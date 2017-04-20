package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TruckPaymentHistoryDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_AccountInfo;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Payment_History;
import com.cn.gazelle.logistics.service.TruckPayService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zf on 2016/12/14.
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckPayService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckPayServiceImpl implements TruckPayService {

    // Log初始化
    Logger logger = Logger.getLogger(TruckPayServiceImpl.class);

    @Resource
    private TruckPaymentHistoryDaoMapper truckPaymentHistoryDaoMapper;
    /**
     * 根据历史编号查询卡车支付历史信息
     * @param history_number
     * @return
     */
    @Override
    public T_Data_Truck_Payment_History findTruckPaymentHistoryByAccountNo(String history_number) {

        T_Data_Truck_Payment_History truckPaymentHistory = null;
        try {
            truckPaymentHistory = truckPaymentHistoryDaoMapper.findTruckPaymentHistoryByAccountNo(history_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);
        }
        return truckPaymentHistory;
    }

    /**
     * 根据卡车ID查询卡车支付历史信息
     * @param truck_id
     * @return
     */
    @Override
    public List<T_Data_Truck_Payment_History> findAll(String truck_id) {
        List<T_Data_Truck_Payment_History> truckPaymentHistories = null;
        try {
            truckPaymentHistories = truckPaymentHistoryDaoMapper.findAll(truck_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);

        }
        return truckPaymentHistories;
    }

    @Override
    public List<T_Data_Truck_Payment_History> findHistoryAll(HashMap<String, String> conditions) {
        List<T_Data_Truck_Payment_History> truckPaymentHistories = null;
        try {
            truckPaymentHistories = truckPaymentHistoryDaoMapper.findHistoryAll(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);

        }
        return truckPaymentHistories;
    }

    @Override
    public List<T_Data_Truck_AccountInfo> findTruckAccountInfo(HashMap<String, String> conditions) {
        List<T_Data_Truck_AccountInfo> truckAccountInfos = null;
        try {
            truckAccountInfos = truckPaymentHistoryDaoMapper.findTruckAccountInfo(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.seacheInfoError);
        }
        return truckAccountInfos;
    }

    /**
     * 保存卡车支付历史信息
     * @param truckPaymentHistory
     */
    @Override
    public void saveTruckPaymentHistory(T_Data_Truck_Payment_History truckPaymentHistory) {

        try {
            truckPaymentHistoryDaoMapper.saveTruckPaymentHistory(truckPaymentHistory);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.saveInfoError);
        }
    }

    /**
     * 更新卡车支付历史信息
     * @param truckPaymentHistory
     */
    @Override
    public void updateTruckPaymentHistory(T_Data_Truck_Payment_History truckPaymentHistory) {
        try {
            truckPaymentHistoryDaoMapper.updateTruckPaymentHistory(truckPaymentHistory);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.saveInfoError);
        }
    }

    /**
     * 根据历史编号删除卡车支付历史信息
     * @param history_number
     */
    @Override
    public void delTruckPaymentHistory(String history_number) {
        try {
            truckPaymentHistoryDaoMapper.delTruckPaymentHistory(history_number);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.delInfoError);
        }
    }

    /**
     * 根据第三方订单ID查询卡车支付历史信息
     * @param third_party_order_id
     * @return
     */
    @Override
    public T_Data_Truck_Payment_History findTruckPaymentHistoryByThirdPartyOrderId(String third_party_order_id) {

        T_Data_Truck_Payment_History truckPaymentHistory = null;
        try {
            truckPaymentHistory = truckPaymentHistoryDaoMapper.findTruckPaymentHistoryByThirdPartyOrderId(third_party_order_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);
        }
        return truckPaymentHistory;
    }
}
