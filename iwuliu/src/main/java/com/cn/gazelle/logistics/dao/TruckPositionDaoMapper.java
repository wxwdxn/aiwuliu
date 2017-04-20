/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckPositionDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车定位信息管理实现
 * 设计文件：
 * 完成日期：2016-06-23
 * 作    者：QJ
 * 内容摘要：卡车定位信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TruckPositionDaoMapper
 * 内容摘要: 卡车定位信息管理实现
 * 方法描述：该类有8个方法：
 *         01 findTruckPositionByPositionNumber         根据位置No查询卡车定位信息
 *         02 findTruckPositionByObdId                  根据OBD ID查询卡车定位信息
 *         03 findAllTruckPosition                      查询符合条件的卡车定位列表信息（默认查询所有卡车定位列表信息）
 *         04 findAllTruckPositionRowsCount             查询卡车定位信息总记录数
 *         05 saveTruckPosition                         保存卡车定位基础信息
 *         06 updateTruckPosition                       更新卡车定位基础信息
 *         07 delTruckPosition                          根据位置No删除卡车定位基础信息
 *         08 findLastTruckPositionByObdId              根据OBD ID倒叙查询最近10条卡车定位信息
 * @author QJ
 */
public interface TruckPositionDaoMapper {
    // 根据位置No查询卡车定位信息
    T_Data_Truck_Position findTruckPositionByPositionNumber(@Param(value = "position_number") String position_number);

    // 根据OBD ID查询卡车定位信息
    List<T_Data_Truck_Position> findTruckPositionByObdId(@Param(value = "obd_id") String obd_id);

    // 查询符合条件的卡车定位列表信息（默认查询所有卡车定位列表信息）
    List<T_Data_Truck_Position> findAllTruckPosition(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询卡车定位信息总记录数
    Integer findAllTruckPositionRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存卡车定位基础信息
    void saveTruckPosition(T_Data_Truck_Position truck_position);

    // 更新卡车定位基础信息
    void updateTruckPosition(T_Data_Truck_Position truck_position);

    // 根据位置No删除卡车定位基础信息
    void delTruckPosition(@Param(value = "position_number") String position_number);

    // 根据OBD ID倒叙查询最近10条卡车定位信息
    List<T_Data_Truck_Position> findLastTruckPositionByObdId(@Param(value = "obd_id") String obd_id);

    // 根据OBD ID倒叙查询符合时间段的最近卡车定位信息
    List<T_Data_Truck_Position> findTruckPositionDateByObdId(@Param(value = "obd_id") String obd_id,
                                                             @Param(value = "start_time") String start_time,
                                                             @Param(value = "end_time") String end_time);

    // 根据OBD ID查询驾驶天数
    int findDrivingDaysByObdId(@Param(value = "obd_id") String obd_id,
                               @Param(value = "start_time") String start_time,
                               @Param(value = "end_time") String end_time);

    // 根据OBD_ID查询车辆最新的卡车定位信息
    T_Data_Truck_Position findLatestTruckPositionByOBD(@Param(value = "obd_id") String obd_id);
}
