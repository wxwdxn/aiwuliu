/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckTransportLineDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆常跑路线信息查询接口实现
 * 设计文件：
 * 完成日期：2016-03-20
 * 作    者：QJ
 * 内容摘要：车辆常跑路线信息查询
 */

package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Transport_Line;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TruckTransportLineDaoMapper
 * 内容摘要: 车辆常跑路线信息查询
 * 方法描述：该类有7个方法：
 *         01 findTruckTransportByLineID    根据ID查询车辆常跑路线信息
 *         02 findTruckTransportByTruckID   根据卡车ID查询车辆常跑路线信息
 *         03 findAllTruckTransportLine 查询所有车辆常跑路线信息
 *         04 findAllTruckTransportLineRowsCount    查询车辆常跑路线信息记录数
 *         05 saveTruckTransportLine    保存车辆常跑路线信息
 *         06 updateTruckTransportLine  更新车辆常跑路线信息
 *         07 delTruckTransportLine 删除车辆常跑路线信息
 *         08 findTruckTransportByTruckIDAndCity    根据卡车ID、起点终点城市查询车辆常跑路线信息
 * @author QJ
 */
public interface TruckTransportLineDaoMapper {
    // 根据ID查询车辆常跑路线信息
    public T_Data_Truck_Transport_Line findTruckTransportByLineID(@Param(value = "line_id") String line_id);

    // 根据卡车ID查询车辆常跑路线信息
    public List<T_Data_Truck_Transport_Line> findTruckTransportByTruckID(@Param(value = "truck_id") String truck_id);

    // 查询所有车辆常跑路线信息
    public List<T_Data_Truck_Transport_Line> findAllTruckTransportLine(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询车辆常跑路线信息记录数
    public Integer findAllTruckTransportLineRowsCount(@Param(value = "search_type") String search_type,@Param(value = "name") String name);

    // 保存车辆常跑路线信息
    int saveTruckTransportLine(T_Data_Truck_Transport_Line truckTransportLine);

    // 更新车辆常跑路线信息
    public void updateTruckTransportLine(T_Data_Truck_Transport_Line truckTransportLine);

    // 删除车辆常跑路线信息
    public void delTruckTransportLine(@Param(value = "line_id") String line_id);

    // 根据卡车ID、起点终点城市查询车辆常跑路线信息
    public List<T_Data_Truck_Transport_Line> findTruckTransportByTruckIDAndCity(@Param(value = "truck_id") String truck_id,@Param(value = "start_city_id") String start_city_id,@Param(value = "end_city_id") String end_city_id);
}
