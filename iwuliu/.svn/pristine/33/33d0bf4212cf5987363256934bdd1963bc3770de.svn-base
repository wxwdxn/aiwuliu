/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TruckObdOriginalDataDailyServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：干线路线节点基础息查询接口实现
 * 设计文件：
 * 完成日期：2016-07-22
 * 作    者: QJ
 * 内容摘要：干线路线节点基础息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TruckObdOriginalDataDailyDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Obd_Original_Data_Daily;
import com.cn.gazelle.logistics.service.TruckObdOriginalDataDailyService;
import com.cn.gazelle.logistics.util.message.TruckObdOriginalDataDailyManager_Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 类 名 称：TruckObdOriginalDataDailyServiceImpl
 * 内容描述：干线路线节点基础息查询接口
 * 方法描述：该类有9个方法
 * 方法描述：该类有7个方法：
 *         01 findTruckObdOriginalDataDailyByNumber          根据数据No查询OBD信息原始数据日表信息
 *         02 findAllTruckObdOriginalDataDaily               查询符合条件的OBD信息原始数据日表信息（默认查询OBD信息原始数据日表信息）
 *         03 findAllTruckObdOriginalDataDailyRowsCount      查询OBD信息原始数据日表信息总记录数
 *         04 saveTruckObdOriginalDataDaily                  保存OBD信息原始数据日表信息
 *         05 updateTruckObdOriginalDataDaily                更新OBD信息原始数据日表信息
 *         06 delTruckObdOriginalDataDaily                   根据数据No删除OBD信息原始数据日表信息
 *         07 findTruckObdOriginalDataDailyBySaveContent     根据接收内容查询OBD信息原始数据日表信息
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckObdOriginalDataDailyService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckObdOriginalDataDailyServiceImpl implements TruckObdOriginalDataDailyService {
    // Log初始化
    Logger logger = Logger.getLogger(TruckObdOriginalDataDailyServiceImpl.class);
    @Resource
    private TruckObdOriginalDataDailyDaoMapper truckObdOriginalDataDailyDaoMapper;  // 数据访问层

    /**
     * 方法名称：findTruckObdOriginalDataDailyByNumber
     * 内容摘要：根据数据No查询OBD信息原始数据总表信息。
     * @param data_number   数据No
     * @return  T_Data_Truck_Obd_Original_Data_Daily OBD信息原始数据
     */
    public T_Data_Truck_Obd_Original_Data_Daily findTruckObdOriginalDataDailyByNumber(long data_number) {
        return truckObdOriginalDataDailyDaoMapper.findTruckObdOriginalDataDailyByNumber(data_number);
    }

    /**
     * 方法名称：findAllTruckObdOriginalDataDaily
     * 内容摘要：查询符合条件的OBD信息原始数据总表信息（默认查询OBD信息原始数据总表信息）
     * @param search_type   搜索类型
     * @param name  查询类型
     * @param page  页面页数
     * @param rows  页面显示条数
     * @return List<T_Data_Truck_Obd_Original_Data_Daily>  OBD信息原始数据总表信息列
     */
    public List<T_Data_Truck_Obd_Original_Data_Daily> findAllTruckObdOriginalDataDaily(String search_type, String name, Integer page, Integer rows) {
    return truckObdOriginalDataDailyDaoMapper.findAllTruckObdOriginalDataDaily(search_type, name, page, rows);
}

    /**
     * 方法名称：findAllTruckObdOriginalDataDailyRowsCount
     * 内容摘要：查询OBD信息原始数据总表信息总记录数
     * @param search_type   搜索类型
     * @param name  查询类型
     * @return Integer OBD信息原始数据总表记录数
     */
    public Integer findAllTruckObdOriginalDataDailyRowsCount(String search_type, String name) {
        return truckObdOriginalDataDailyDaoMapper.findAllTruckObdOriginalDataDailyRowsCount(search_type, name);
    }

    /**
     * 方法名称：saveTruckObdOriginalDataDaily
     * 内容摘要：保存OBD信息原始数据总表信息
     * @param truck_obd_original_data OBD信息原始数据总表信息
     */
    public boolean saveTruckObdOriginalDataDaily(T_Data_Truck_Obd_Original_Data_Daily truck_obd_original_data) {
        boolean a = new Boolean(true);
        try {
            this.truckObdOriginalDataDailyDaoMapper.saveTruckObdOriginalDataDaily(truck_obd_original_data);
            logger.info(TruckObdOriginalDataDailyManager_Message.SaveTruckObdOriginalDataDailyDone);
        } catch (Exception e) {
            a=false;
            logger.error(TruckObdOriginalDataDailyManager_Message.SaveTruckObdOriginalDataDailyError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：updateTruckObdOriginalDataDaily
     * 内容摘要：更新OBD信息原始数据总表信息
     * @param truck_obd_original_data OBD信息原始数据总表信息
     */
    public boolean updateTruckObdOriginalDataDaily(T_Data_Truck_Obd_Original_Data_Daily truck_obd_original_data) {
        boolean a=new Boolean(true);
        try {
            truckObdOriginalDataDailyDaoMapper.updateTruckObdOriginalDataDaily(truck_obd_original_data);
        } catch (Exception e) {
            a=false;
            logger.error(TruckObdOriginalDataDailyManager_Message.UpdateTruckObdOriginalDataDailyError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delTruckObdOriginalDataDaily
     * 内容摘要：根据数据No删除OBD信息原始数据总表信息
     * @param data_number  数据No
     */
    public void delTruckObdOriginalDataDaily(long data_number) {
        try {
            truckObdOriginalDataDailyDaoMapper.delTruckObdOriginalDataDaily(data_number);
            logger.info(TruckObdOriginalDataDailyManager_Message.DelTruckObdOriginalDataDailyDone);
        } catch (Exception e) {
            logger.error(TruckObdOriginalDataDailyManager_Message.DelTruckObdOriginalDataDailyError+e.getMessage());
        }
    }

    /**
     * 方法名称：findTruckObdOriginalDataDailyBySaveContent
     * 内容摘要：根据接收内容查询OBD信息原始数据总表信息。
     * @param save_content   接收内容
     * @return  T_Data_Truck_Obd_Original_Data_Daily OBD信息原始数据
     */
    public T_Data_Truck_Obd_Original_Data_Daily findTruckObdOriginalDataDailyBySaveContent(String save_content) {
        return truckObdOriginalDataDailyDaoMapper.findTruckObdOriginalDataDailyBySaveContent(save_content);
    }
}