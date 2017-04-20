/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Master_Cargo_Truck_Type_Match.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物车厢车厢车厢类型匹配
 * 设计文件：
 * 完成日期：2016-05-13
 * 作    者: WXW
 * 内容摘要：货物车厢车厢车厢类型匹配接口
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Truck_Type_Match;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: CargoTruckTypeMatchMapper
 * 内容摘要: 货物车厢车厢类型的接口
 * 方法描述：该类有6个方法：
 *         01 addCargoTruckType                           添加货物车厢类型
 *         02 delCargoTruckType                           删除货物车厢类型
 *         03 findCargoTruckType                          根据id查询货物车厢类型
 *         04 updateCargoTruckType                        更新货物车厢类型信息
 *         05 findAllCargoTruckType                       查询所有货物车厢类型信息
 *         06 findCargoTruckTypeRowsCount                 查询符合条件的货物车厢类型匹配个数
 *  @author WXW
 */
@WebService
public interface CargoTruckTypeService {
    //  根据货物类型类型添加货物车厢类型
    int addCargoTruckTypeByCargoType(String parameter,String userName);

    // 根据车厢类型添加 货物车厢类型匹配
    int  addCargoTruckTypeByTruckType( String parameter,String userName);

    // 删除货物车厢类型
    void delCargoTruckType(String matchId);

    // 根据id查询货物车厢类型
    T_Master_Cargo_Truck_Type_Match findCargoTruckType(String matchId);

    // 更新货物车厢类型信息
    boolean updateCargoTruckType(T_Master_Cargo_Truck_Type_Match cargoTruckTypeMatch);


    //根据车厢类型货物所有的匹配信息
    List<T_Master_Cargo_Truck_Type_Match> findMatchByTruck(String truckType);

    // 通过货物种类id删除货物车厢类型
    void delByCargoId( String cargoTypeId);

    // 根据车厢类型id和货物id查询match
    T_Master_Cargo_Truck_Type_Match findMatchByTruckId( String truckTypeId,  String cargoTypeId);

    // 通过车厢类型id删除货物车厢类型
    void delByTruckId(String truckTypeId);
}
