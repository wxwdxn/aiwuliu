/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车信息查询接口实现
 * 设计文件：
 * 完成日期：2016-03-03
 * 作    者：QJ
 * 内容摘要：卡车信息查询
 */

package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Truck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TruckDaoMapper
 * 内容摘要: 卡车信息管理页面
 * 方法描述：该类有22个方法：
 *         01 findTruckByID                                             根据绑定车辆id查询卡车信息
 *         02 findTruckByPlateNumber                                    根据车牌号查询卡车信息
 *         03 findAllTruck                                              查询所有卡车信息
 *         04 findAllTruckRowsCount                                     查询卡车总记录数
 *         05 saveTruck                                                 保存卡车信息
 *         06 updateTruck                                               更新卡车信息
 *         07 examineTruck                                              审核卡车信息
 *         08 delTruck                                                  删除卡车信息
 *         09 findTruckByOwnerMemberID                                  通过车主会员关联ID查询卡车信息列表
 *         10 findTruckByDepositMemberID                                通过管理对象会员关联ID查询卡车信息列表
 *         11 findTruckByManagerMemberID                                通过车辆管理者会员关联ID查询卡车信息列表
 *         12 findTruckByMember                                         根据账号、车辆的所属身份、车辆状态获取车辆列表
 *         13 findTruckDataByPlateNumber                                根据车牌号查找卡车相关信息
 *         14 findTruckByOwnerMemberIDDepositMemberID                   通过车主会员关联ID查询去除与托管对象关联ID相同的车主关联ID信息列表
 *         15 editTruckByMember                                         根据账号修改车辆托管对象以及车辆管理者的信息
 *         16 findTruckByMemberAndPlateNumber                           根据账号、车辆的所属身份、车牌号模糊参数，获取模糊查询后的车辆列表
 *         17 findTruckDataByMember                                     根据member_name获取我驾驶车辆信息(根据司机会员名获取司机驾驶车辆信息)
 *         18 findTruckByOwnerMemberIdAndPlateNumber                    通过车主会员关联ID，车牌号模糊查询卡车信息列表
 *         19 findTruckByDepositMemberIdAndPlateNumber                  通过托管对象会员关联ID，车牌号模糊查询卡车信息列表
 *         20 findTruckByManagerMemberIdAndPlateNumber                  通过车辆管理者会员关联ID，车牌号模糊查询卡车信息列表
 *         21 findTruckByOwnerMemberIDDepositMemberIDAndPlateNumber     通过车主会员ID，车牌号模糊查询去除与托管对象ID，车牌号模糊查询后相同的车主ID信息列表
 *         22 findTruckList                                             查询所有车辆信息不分页
 *         23 findTruckByMuHuNumber                                     根据 车牌号模糊 查找卡车相关信息 wxw
 *         24 findTruckByID2                                             根据绑定车辆id查询卡车信息（查询删除和没有删除的车辆）
 * @author QJ
 */
public interface TruckDaoMapper {

    // 根据绑定车辆id查询卡车信息
    T_Data_Truck findTruckByID2(@Param(value = "truck_id") String truck_id);

    // 根据绑定车辆id查询卡车信息
    T_Data_Truck findTruckByID(@Param(value = "truck_id") String truck_id);

    // 根据车牌号查询卡车信息
    T_Data_Truck findTruckByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 查询所有卡车信息
    List<T_Data_Truck> findAllTruck();

    // 查询卡车总记录数
    Integer findAllTruckRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存卡车信息
    int saveTruck(T_Data_Truck truck);

    // 更新卡车信息
    void updateTruck(T_Data_Truck truck);

    // 审核卡车信息
    void examineTruck(T_Data_Truck truck);

    // 删除卡车信息
    void delTruck(@Param(value = "truck_id") String truck_id);

    // 通过车主会员关联ID查询卡车信息列表
    List<T_Data_Truck> findTruckByOwnerMemberID(@Param(value = "owner_member_id") String owner_member_id);

    // 通过管理对象会员关联ID查询卡车信息列表
    List<T_Data_Truck> findTruckByDepositMemberID(@Param(value = "deposit_member_id") String deposit_member_id);

    // 通过车辆管理者会员关联ID查询卡车信息列表
    List<T_Data_Truck> findTruckByManagerMemberID(@Param(value = "manager_member_id") String manager_member_id);

    // 根据账号、车辆的所属身份、车辆状态获取车辆列表
    List<T_Data_Truck> findTruckByMember(@Param(value = "member_name") String member_name, @Param(value = "identity") String identity, @Param("begin_rows") Integer begin_rows, @Param("rows") Integer end_rows);

    // 根据车牌号查找卡车相关信息
    String findTruckDataByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 通过车主会员关联ID查询去除与托管对象关联ID相同的车主关联ID信息列表
    List<T_Data_Truck> findTruckByOwnerMemberIDDepositMemberID(@Param(value = "owner_member_id") String owner_member_id);

    // 根据账号修改车辆托管对象以及车辆管理者的信息
    String editTruckByMember(String member_name, String plate_number, String deposit_member_name, String manager_member_name, String truck_member_name_json, String line_json, Integer status);

    // 根据账号、车辆的所属身份、车牌号模糊参数，获取模糊查询后的车辆列表
    List<T_Data_Truck> findTruckByMemberAndPlateNumber(@Param(value = "member_name") String member_name, @Param(value = "identity") Integer identity, @Param("begin_rows") Integer begin_rows, @Param("rows") Integer end_rows, @Param(value = "plate_number") String plate_number);

    // 根据member_name获取我驾驶车辆信息(根据司机会员名获取司机驾驶车辆信息)
    String findTruckDataByMember(@Param(value = "member_name") String member_name);

    // 通过车主会员关联ID，车牌号模糊查询卡车信息列表
    List<T_Data_Truck> findTruckByOwnerMemberIdAndPlateNumber(@Param(value = "owner_member_id") String owner_member_id, @Param(value = "plate_number") String plate_number);

    //通过托管对象会员关联ID，车牌号模糊查询卡车信息列表
    List<T_Data_Truck> findTruckByDepositMemberIdAndPlateNumber(@Param(value = "deposit_member_id") String deposit_member_id, @Param(value = "plate_number") String plate_number);

    // 通过车辆管理者会员关联ID，车牌号模糊查询卡车信息列表
    List<T_Data_Truck> findTruckByManagerMemberIdAndPlateNumber(@Param(value = "manager_member_id") String manager_member_id, @Param(value = "plate_number") String plate_number);

    // 通过车主会员ID，车牌号模糊查询去除与托管对象ID，车牌号模糊查询后相同的车主ID信息列表
    List<T_Data_Truck> findTruckByOwnerMemberIDDepositMemberIDAndPlateNumber(@Param(value = "owner_member_id") String owner_member_id, @Param(value = "plate_number") String plate_number);

    // 查询所有车辆信息不分页
    List<T_Data_Truck> findTruckList();

    // 根据 车牌号模糊 查找卡车相关信息 wxw
    List<T_Data_Truck> findTruckByMuHuNumber(@Param(value = "plate_number") String plate_number);

    // 通过车辆管理者会员ID查询查询删除或者没有删除的 卡车信息列表 wxw
    List<T_Data_Truck> findTruckByManagerMemberID2(@Param(value = "manager_member_id") String manager_member_id);

    // 根据货物类型和干线查询可分配的车辆
    List<T_Data_Truck> findByCargoTypeAndLineId(@Param(value = "cargoTypeId") String cargoTypeId,@Param(value = "lineId") String lineId);

    //根据车牌号车主会员不为空查询卡车信息
    List<T_Data_Truck> findTruckByMuHuNumberAndOwerNotNull(@Param(value = "plate_number") String plate_number);

    //  查询车辆账户总额度
    double queryCashAmount();
}
