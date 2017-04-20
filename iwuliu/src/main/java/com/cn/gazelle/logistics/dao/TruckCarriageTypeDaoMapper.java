/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckCarriageTypeDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车厢类型基础信息查询接口实现
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者：QJ
 * 内容摘要：车厢类型基础信息查询
 */

package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Truck_Carriage_Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TruckCarriageTypeDaoMapper
 * 内容摘要: 车厢类型基础信息查询
 * 方法描述：该类有11个方法：
 *         01 findTruckCarriageTypeByID            根据ID查询车厢类型
 *         02 findTruckCarriageTypeByName          根据类型名查询车厢类型信息
 *         03 findAllTruckCarriageType             查询所有车厢类型所有信息
 *         04 findAllTruckCarriageTypeRowsCount    查询车厢类型记录数
 *         05 findAllTruckCarriageTypeName         查询所有车厢类型
 *         06 saveTruckCarriageType                保存车厢类型信息
 *         07 updateTruckCarriageType              更新车厢类型信息
 *         08 delTruckCarriageType                 删除车厢类型信息
 *         09 findTruckCarriageTypeIDByName        根据车厢类型名称查询车厢类型ID
 *         10 findAllTruckCarriageTypeByID         查询符合条件的车厢类型信息
 *         11 findAllTruckCarriageTypeIdAndName    检索所有车厢类型
 * @author QJ
 */
public interface TruckCarriageTypeDaoMapper {
    // 根据ID查询车厢类型
    public T_Master_Truck_Carriage_Type findTruckCarriageTypeByID(@Param(value = "truck_carriage_type_id") String truck_carriage_type_id);

    // 根据类型名查询车厢类型信息
    public T_Master_Truck_Carriage_Type findTruckCarriageTypeByName(@Param(value = "truck_carriage_type_name") String truck_carriage_type_name);

    // 查询所有车厢类型所有信息
    public List<T_Master_Truck_Carriage_Type> findAllTruckCarriageType(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询车厢类型记录数
    public Integer findAllTruckCarriageTypeRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 查询所有车厢类型
    public List<T_Master_Truck_Carriage_Type> findAllTruckCarriageTypeName();

    // 保存车厢类型信息
    int saveTruckCarriageType(T_Master_Truck_Carriage_Type TruckCarriageType);

    // 更新车厢类型信息
    public void updateTruckCarriageType(T_Master_Truck_Carriage_Type TruckCarriageType);

    // 删除车厢类型信息
    public void delTruckCarriageType(@Param(value = "truck_carriage_type_id") String truck_carriage_type_id);

    // 根据车厢类型名称查询车厢类型ID
    public T_Master_Truck_Carriage_Type findTruckCarriageTypeIDByName(@Param(value = "truck_carriage_type_name") String truck_carriage_type_name);

    // 查询符合条件的车厢类型信息
    public  List<T_Master_Truck_Carriage_Type> findAllTruckCarriageTypeByID(@Param("truck_carriage_type_id") String truck_carriage_type_id);

    // 检索所有车厢类型
    public List<T_Master_Truck_Carriage_Type> findAllTruckCarriageTypeIdAndName();
}
