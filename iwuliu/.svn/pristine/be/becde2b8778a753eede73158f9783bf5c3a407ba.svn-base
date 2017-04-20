/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Master_Cargo_Truck_Type_Match.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物车厢车厢车厢类型匹配
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者: WXW
 * 内容摘要：货物车厢车厢车厢类型匹配
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Truck_Type_Match;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 类 名 称: CargoTruckTypeMatchMapper
 * 内容摘要: 货物车厢车厢类型的接口
 * 方法描述：该类有11个方法：
 *         01 addCargoTruckType                           添加货物车厢类型
 *         02 delCargoTruckType                           删除货物车厢类型
 *         03 findCargoTruckType                          根据id查询货物车厢类型
 *         04 updateCargoTruckType                        更新货物车厢类型信息
 *         05 findAllCargoTruckType                       查询所有货物车厢类型信息
 *         06 findCargoTruckTypeRowsCount                 查询符合条件的货物车厢类型匹配个数
 *         07 findTruckNameList                           查询车厢类型
 *         08 findTruckNameCount                          查询车厢类型匹配个数
 *         09 findMatchByTruckId                          根据车厢类型id查询match
 *         10 delByCargoId                                通过货物种类id删除货物车厢类型
 *         11 delByTruckId                                通过车厢类型id删除货物车厢类型
 *  @author WXW
 */
public interface CargoTruckTypeMatchMapper {
    // 添加货物车厢类型
    int addCargoTruckType(T_Master_Cargo_Truck_Type_Match cargoTruckTypeMatch);

    // 删除货物车厢类型
    void delCargoTruckType(@Param(value = "matchId") String matchId);

    // 通过货物种类id删除货物车厢类型
    void delByCargoId(@Param(value = "cargoTypeId") String cargoTypeId);

    // 通过车厢类型id删除货物车厢类型
    void delByTruckId(@Param(value = "truckTypeId") String truckTypeId);

    // 根据id查询货物车厢类型
    T_Master_Cargo_Truck_Type_Match findCargoTruckType(@Param(value = "matchId") String matchId);

    // 更新货物车厢类型信息
    void updateCargoTruckType(T_Master_Cargo_Truck_Type_Match cargoTruckTypeMatch);

    // 查询车厢类型
    List<Map> findTruckNameList(@Param(value = "cargoTypeId") String cargoTypeId, @Param(value = "page") Integer page, @Param(value = "rows") Integer rows);

    // 查询车厢类型匹配个数
    Integer findTruckNameCount(@Param(value = "cargoTypeId") String cargoTypeId);

    // 根据车厢类型id和货物id查询match
    T_Master_Cargo_Truck_Type_Match findMatchByTruckId(@Param(value = "truckTypeId") String truckTypeId, @Param(value = "cargoTypeId") String cargoTypeId);

    // 根据车厢类型id查询match
    List<T_Master_Cargo_Truck_Type_Match> findMatchByTruck(@Param(value = "truckTypeId") String truckTypeId);

    //根据货物类型查询
    List <T_Master_Cargo_Truck_Type_Match>findMatchByCargoId(@Param(value = "cargoTypeId")String cargoTypeId);

    // 根据货物类型删除货物车厢类型
    void delMatchByCargoTypeId(@Param(value = "cargoTypeId") String cargoTypeId);


}
