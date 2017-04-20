/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckPositionService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车定位基础信息查询接口声明
 * 设计文件：
 * 完成日期：2016-06-24
 * 作    者：QJ
 * 内容摘要：卡车定位基础信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Position;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TruckPositionService
 * 内容摘要: 卡车定位基础信息查询
 * 方法描述：该类有7个方法：
 *         01 findTruckPositionByPositionNumber         根据位置No查询卡车定位信息
 *         02 findTruckPositionByObdId                  根据OBD ID查询卡车定位信息
 *         03 findAllTruckPosition                      查询符合条件的卡车定位列表信息（默认查询所有卡车定位列表信息）
 *         04 findAllTruckPositionRowsCount             查询卡车定位信息总记录数
 *         05 saveTruckPosition                         保存卡车定位基础信息
 *         06 updateTruckPosition                       更新卡车定位基础信息
 *         07 delTruckPosition                          根据位置No删除卡车定位基础信息
 * @author QJ
 */
@WebService
public interface TruckPositionService {
    // 根据位置No查询卡车定位信息
    T_Data_Truck_Position findTruckPositionByPositionNumber(String position_number);

    // 根据OBD ID查询卡车定位信息
    List<T_Data_Truck_Position> findTruckPositionByObdId(String obd_id);

    // 查询符合条件的卡车定位列表信息（默认查询所有卡车定位列表信息）
    List<T_Data_Truck_Position> findAllTruckPosition(String search_type, String name, Integer page, Integer rows);

    // 查询卡车定位信息总记录数
    Integer findAllTruckPositionRowsCount(String search_type, String name);

    // 保存卡车定位基础信息
    boolean saveTruckPosition(T_Data_Truck_Position truck_position);

    // 更新卡车定位基础信息
    boolean updateTruckPosition(T_Data_Truck_Position truck_position);

    // 根据位置No删除卡车定位基础信息
    void delTruckPosition(String position_number);

    // 根据OBD ID查询驾驶天数
    int findDrivingDaysByObdId( String obd_id,String start_time,String end_time);


}
