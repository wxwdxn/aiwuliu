/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TruckPositionServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车定位基础信息查询接口声明
 * 设计文件：
 * 完成日期：2016-06-24
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TruckPositionDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Position;
import com.cn.gazelle.logistics.service.TruckPositionService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.message.TruckPositionManager_Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 类 名 称：TruckPositionServiceImpl
 * 内容描述：卡车定位基础信息查询
 * 方法描述：该类有7个方法
 * 01 findTruckPositionByPositionNumber         根据位置No查询卡车定位信息
 * 02 findTruckPositionByObdId                  根据OBD ID查询卡车定位信息
 * 03 findAllTruckPosition                      查询符合条件的卡车定位列表信息（默认查询所有卡车定位列表信息）
 * 04 findAllTruckPositionRowsCount             查询卡车定位信息总记录数
 * 05 saveTruckPosition                         保存卡车定位基础信息
 * 06 updateTruckPosition                       更新卡车定位基础信息
 * 07 delTruckPosition                          根据位置No删除卡车定位基础信息
 *
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckPositionService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckPositionServiceImpl implements TruckPositionService {
    // Log初始化
    Logger logger = Logger.getLogger(TruckPositionServiceImpl.class);
    @Resource
    private TruckPositionDaoMapper truckPositionDaoMapper;  // 数据访问层

    /**
     * 方法名称：findTruckPositionByPositionNumber
     * 内容摘要：根据位置No查询卡车定位信息。
     *
     * @param position_number 位置No
     * @return T_Data_Truck_Position 卡车定位信息
     */
    public T_Data_Truck_Position findTruckPositionByPositionNumber(String position_number) {
        return truckPositionDaoMapper.findTruckPositionByPositionNumber(position_number);
    }

    /**
     * 方法名称：findTruckPositionByObdId
     * 内容摘要：根据OBD ID查询卡车定位信息
     *
     * @param obd_id OBD ID
     * @return List<T_Data_Truck_Position> 卡车定位信息列
     */
    public List<T_Data_Truck_Position> findTruckPositionByObdId(String obd_id) {
        return truckPositionDaoMapper.findTruckPositionByObdId(obd_id);
    }

    /**
     * 方法名称：findAllTruckPosition
     * 内容摘要：查询符合条件的卡车定位列表信息（默认查询所有卡车定位列表信息）。
     *
     * @param search_type 搜索类型
     * @param name        查询类型
     * @param page        页面页数
     * @param rows        页面显示条数
     * @return List<T_Master_Operate_Main_Line>  运营干线信息列
     */
    public List<T_Data_Truck_Position> findAllTruckPosition(String search_type, String name, Integer page, Integer rows) {
        return truckPositionDaoMapper.findAllTruckPosition(search_type, name, page, rows);
    }

    /**
     * 方法名称：findAllTruckPositionRowsCount
     * 内容摘要：查询卡车定位信息总记录数
     *
     * @param search_type 搜索类型
     * @param name        查询类型
     * @return Integer 运营干线记录数
     */
    public Integer findAllTruckPositionRowsCount(String search_type, String name) {
        return truckPositionDaoMapper.findAllTruckPositionRowsCount(search_type, name);
    }

    /**
     * 方法名称：saveTruckPosition
     * 内容摘要：保存卡车定位基础信息
     *
     * @param truck_position 卡车定位基础信息
     */
    public boolean saveTruckPosition(T_Data_Truck_Position truck_position) {
        boolean a = new Boolean(true);
        try {
            this.truckPositionDaoMapper.saveTruckPosition(truck_position);
            logger.info(TruckPositionManager_Message.SaveTruckPositionDone);
        } catch (Exception e) {
            a = false;
            logger.error(TruckPositionManager_Message.SaveTruckPositionError + e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：updateTruckPosition
     * 内容摘要：更新卡车定位基础信息
     *
     * @param truck_position 卡车定位基础信息
     */
    public boolean updateTruckPosition(T_Data_Truck_Position truck_position) {
        boolean a = new Boolean(true);
        try {
            truck_position.setLast_update(DateUtil.getDate());
            truckPositionDaoMapper.updateTruckPosition(truck_position);
        } catch (Exception e) {
            a = false;
            logger.error(TruckPositionManager_Message.UpdateTruckPositionError + e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delTruckPosition
     * 内容摘要：根据位置No删除卡车定位基础信息
     *
     * @param position_number 位置No
     */
    public void delTruckPosition(String position_number) {
        try {
            truckPositionDaoMapper.delTruckPosition(position_number);
            logger.info(TruckPositionManager_Message.DelTruckPositionDone);
        } catch (Exception e) {
            logger.error(TruckPositionManager_Message.DelTruckPositionError + e.getMessage());
        }
    }

    /**
     * 方法名称：findDrivingDaysByObdId
     * 内容摘要：查找驾驶天数
     *
     * @param obd_id     obd_id
     * @param start_time
     * @param end_time
     */
    public int findDrivingDaysByObdId(String obd_id, String start_time, String end_time) {
        int count = 0;
        try {
            count = truckPositionDaoMapper.findDrivingDaysByObdId(obd_id, start_time, end_time);
            logger.info(TruckPositionManager_Message.seacheInfo);
        } catch (Exception e) {
            logger.error(TruckPositionManager_Message.seacheInfoError + e.getMessage());
        }
        return count;
    }
}