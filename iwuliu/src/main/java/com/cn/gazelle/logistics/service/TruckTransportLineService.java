/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckTransportLineService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆常跑路线查询接口声明
 * 设计文件：
 * 完成日期：2016-03-03
 * 作    者：QJ
 * 内容摘要：车辆常跑路线查询
 */

package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Transport_Line;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TruckTransportLineService
 * 内容摘要: 车辆常跑路线查询接口
 * 方法描述：该类有10个方法：
 *         01 findTruckTransportByLineID            根据常跑路线ID查找车辆常跑路线信息
 *         02 findTruckTransportByTruckID           根据卡车ID查询车辆常跑路线信息列
 *         03 findAllTruckTransportLine             查询符合条件的车辆常跑路线列表信息（默认查询所有车辆常跑路线）
 *         04 findAllTruckTransportLineRowsCount    查询车辆常跑路线记录数(包含有条件和无条件)
 *         05 saveTruckTransportLine                增加车辆常跑路线信息
 *         06 updateTruckTransportLine              更新车辆常跑路线信息
 *         07 delTruckTransportLine                 删除车辆常跑路线信息
 *         08 searchTruckTransportLine              检索车辆常跑路线
 *         09 addTruckTransportLine                 添加车辆常跑路线
 *         10 delTruckTransportLineByID             通过ID删除车辆常跑路线
 *         11 findTruckTransportByTruckIDAndCity    根据卡车ID、起点终点城市查询车辆常跑路线信息
 *
 * @author QJ
 */
@WebService
public interface TruckTransportLineService {

    // 根据常跑路线ID查找车辆常跑路线信息
    public String findTruckTransportByLineID(String line_id);

    // 根据卡车ID查询车辆常跑路线信息列
    public List<T_Data_Truck_Transport_Line> findTruckTransportByTruckID(String truck_id);

    // 查询符合条件的车辆常跑路线列表信息（默认查询所有车辆常跑路线）
    public List<T_Data_Truck_Transport_Line> findAllTruckTransportLine(String search_type, String name, Integer page, Integer rows);

    // 查询车辆常跑路线记录数(包含有条件和无条件)
    public Integer findAllTruckTransportLineRowsCount(String search_type, String name);

    // 增加车辆常跑路线信息
    int saveTruckTransportLine(T_Data_Truck_Transport_Line truckTransportLine);

    // 更新车辆常跑路线信息
    public boolean updateTruckTransportLine(T_Data_Truck_Transport_Line truckTransportLine);

    // 删除车辆常跑路线信息
    public void delTruckTransportLine(String line_id);

    // 检索车辆常跑路线
    public String searchTruckTransportLine(String member_name, String plate_number);

    // 添加车辆常跑路线
    public String addTruckTransportLine(String member_name, String plate_number, String line);

    // 通过ID删除车辆常跑路线
    public String delTruckTransportLineByID(String member_name, String plate_number, String line_id);

    // 根据卡车ID、起点终点城市查询车辆常跑路线信息
    List<T_Data_Truck_Transport_Line> findTruckTransportByTruckIDAndCity(String truck_id, String start_city_id, String end_city_id);
}

