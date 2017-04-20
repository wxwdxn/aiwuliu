/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckCarriageTypeService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车厢类型查询接口声明
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者：QJ
 * 内容摘要：车厢类型查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Truck_Carriage_Type;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TruckCarriageTypeService
 * 内容摘要: 车厢类型查询接口
 * 方法描述：该类有11个方法：
 *         01 findTruckCarriageTypeByID             根据车厢类型ID查找车厢类型信息
 *         02 findTruckCarriageTypeByName           根据车厢类型名称查询车厢类型信息
 *         03 findAllTruckCarriageType              查询符合条件的车厢类型列表信息（默认查询所有车厢类型）
 *         04 findAllTruckCarriageTypeRowsCount     查询车厢类型记录数(包含有条件和无条件)
 *         05 findAllTruckCarriageTypeName          查询所有车厢类型名称
 *         06 saveTruckCarriageType                 增加车厢类型信息
 *         07 updateTruckCarriageType               更新车厢类型信息
 *         08 delTruckCarriageType                  删除车厢类型信息
 *         09 findTruckCarriageTypeIDByName         根据车厢类型名称查询车厢类型信息
 *         10 findAllTruckCarriageTypeByID          根据车厢类型ID所有信息列
 *         11 findAllTruckCarriageTypeIdAndName     检索所有车厢类型
 * @author QJ
 */
@WebService
public interface TruckCarriageTypeService {

    // 根据车厢类型ID查找车厢类型信息
    T_Master_Truck_Carriage_Type findTruckCarriageTypeByID(String truck_carriage_type_id);

    // 根据车厢类型名称查询车厢类型信息
    T_Master_Truck_Carriage_Type findTruckCarriageTypeByName(String truck_carriage_type_name);

    // 查询符合条件的车厢类型列表信息（默认查询所有车厢类型）
    List<T_Master_Truck_Carriage_Type> findAllTruckCarriageType(String search_type, String name, Integer page, Integer rows);

    // 查询车厢类型记录数(包含有条件和无条件)
    Integer findAllTruckCarriageTypeRowsCount(String search_type, String name);

    // 查询所有车厢类型名称
    String findAllTruckCarriageTypeName();

    // 增加车厢类型信息
    int saveTruckCarriageType(String parameter,String userName);

    // 更新车厢类型信息
    boolean updateTruckCarriageType(T_Master_Truck_Carriage_Type TruckCarriageType);

    // 删除车厢类型信息
    void delTruckCarriageType(String truck_carriage_type_id);

    // 根据车厢类型名称查询车厢类型信息
    T_Master_Truck_Carriage_Type findTruckCarriageTypeIDByName(String truck_carriage_type_name);

    // 根据车厢类型ID所有信息列
    List findAllTruckCarriageTypeByID(String truck_carriage_type_id);

    // 检索所有车厢类型
    String findAllTruckCarriageTypeIdAndName();

    // 查询所有的车厢类型列表信息
    List<T_Master_Truck_Carriage_Type> findAllTruckCarriageType2();
}
