/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车查询接口声明
 * 设计文件：
 * 完成日期：2016-03-03
 * 作    者：QJ
 * 内容摘要：卡车查询
 */

package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TruckService
 * 内容摘要: 车辆查询接口
 * 方法描述：该类有55个方法：
 *         01 findTruckByID                     根据绑定车辆id查询卡车信息
 *         02 findTruckByPlateNumber            根据车牌号查找卡车信息
 *         03 findAllTruck                      查询符合条件的卡车列表信息（默认查询所有卡车）
 *         04 findAllTruckRowsCount             查询卡车记录数(包含有条件和无条件)
 *         05 saveTruck                         增卡车信息
 *         06 updateTruck                       更新卡车信息
 *         07 delTruck                          删除卡车信息
 *         08 findTruckByOwnerMemberID          通过车主会员关联ID查询卡车信息列
 *         09 findTruckByDepositMemberID        通过托管对象会员关联ID查询卡车信息列
 *         10 findTruckByManagerMemberID        通过车辆管理者会员关联ID查询卡车信息列表
 *         11 findTruckByMember                 根据账号、车辆的所属身份、车辆状态获取车辆列表
 *         12 findTruckDataByPlateNumber        根据车牌号查找卡车相关信息
 *         13 editTruckByMember                 根据账号修改车辆托管对象以及车辆管理者的信息
 *         14 addTruckInfo                      添加卡车信息
 *         15 setTruckInfo                      车辆信息的设定
 *         16 delTruckInfo                      车辆所属身份信息的删除
 *         17 findWaitTruckByMember             根据账号获取待单中或仅有一单，有司机，未维修的车辆列表信息
 *         18 findLoadWeightByMember            根据账号获取待单有司机车辆运力
 *         19 findTruckByMemberAndPlateNumber   根据账号名、车辆的所属身份、车牌号模糊参数，获取模糊查询后的车辆列表
 *         20 findTruckDataByMember             根据账户名获取我驾驶车辆信息
 *         21 setRepairStatus                   设定车辆修理状态
 *         22 findAllTruckTypeName              检索车辆车类型
 *         23 findAllTruckTypeLength            检索所有车长
 *         24 searchTruckDataByPlateNumber      根据车牌号检索车辆详细信息
 *         25 searchTruckDataByMemberName       根据账号和雇佣关系检索车辆详细信息
 *         26 addTruckInformation               添加卡车的信息
 *         27 searchDriverOften                 检索常跑司机列表
 *         28 editManagerMember                 车辆管理者变更
 *         29 truckDriversChange                车辆行使司机变更
 *         30 joinTheDriver                     通过车牌号和会员号申请加入常跑司机
 *         31 searchDriverByPlateNumber         通过车牌号检索待确认司机列表
 *         32 judgeJoinDriver                   确认是否允许加入常跑司机
 *         33 relieveTruckAndDriver             解除司机与车辆的关系
 *         34 findAllTruckFuelType              检索车辆燃料类型
 *         35 setTruckPermanentAddress          设置车辆的常驻地址
 *         36 editTruckInfo                     修改车辆基本信息
 *         37 findManagerTruckListByMember      根据账号名查询管理者车辆列表_v2.0
 *         38 saveTruckInformation              添加卡车的信息_v2.0
 *         39 searchDriverList                  检索常跑司机列表_v2.0
 *         40 findTruckDataByMemberName         根据账号和雇佣关系检索车辆详细信息_v2.0
 *         41 findTruckDataByTruckPlateNumber   根据车牌号检索车辆详细信息_v2.0
 *         42 relieveTruckAndMember             解除会员与车辆的关系_v2.0
 *         43 findAddTruckAuthority             根据车牌号会员名检索添加车辆权限_v2.0
 *         44 findAllInsuranceCompany           检索所有保险公司名称
 *         45 findAllTruckBrand                 检索所有车辆品牌
 *         46 findAllTruckModeByTruckBrandId    根据品牌ID查询所有的卡车型号
 *         47 findManagerTruckListByDriver      根据账号名查询司机车辆列表_v2.0
 *         48 findByCargoTypeAndLineId          查询符合条件的卡车列表信息（货物类型，干线）
 *         49 findTruckAccount                  车辆账户检索（支付）
 *         50 rechargeTruckAccount              车辆账户充值（支付）
 *         51 withdrawalsTruckAccount           车辆账户提现（支付）
 *         52 findTruckAccountDetail            车辆账户明细检索（支付）
 *         53 checkTruckPaymentPassword         车辆账户密码检索（支付）
 *         54 findAllBank                       检索所有银行
 *         55 scanQRCodePayment                 车辆扫描二维码支付（支付）
 *
 * @author QJ
 */
@WebService
public interface TruckService {

    // 根据绑定车辆id查询卡车信息
    T_Data_Truck findTruckByID(String truck_id);

    // 根据车牌号查找卡车信息
    T_Data_Truck findTruckByPlateNumber(String plate_number);

    // 查询符合条件的卡车列表信息（默认查询所有卡车）
    List<T_Data_Truck> findAllTruck();

    // 查询卡车记录数(包含有条件和无条件)
    Integer findAllTruckRowsCount(String search_type, String name);

    // 增卡车信息
    int saveTruck(T_Data_Truck truck);

    // 更新卡车信息
    boolean updateTruck(T_Data_Truck truck);

    // 删除卡车信息
    void delTruck(String truck_id);

    // 通过车主会员关联ID查询卡车信息列
    List<T_Data_Truck> findTruckByOwnerMemberID(String owner_member_id);

    // 通过托管对象会员关联ID查询卡车信息列
    List<T_Data_Truck> findTruckByDepositMemberID(String deposit_member_id);

    // 通过车辆管理者会员关联ID查询卡车信息列表
    List<T_Data_Truck> findTruckByManagerMemberID(String manager_member_id);

    // 根据账号、车辆的所属身份、车辆状态获取车辆列表
//    public String findTruckByMember(String member_name, String identity, Integer begin_rows, Integer end_rows);

    // 根据车牌号查找卡车相关信息
//    public String findTruckDataByPlateNumber(String plate_number);

    // 根据账号修改车辆托管对象以及车辆管理者的信息
//    public String editTruckByMember(String member_name, String plate_number, String deposit_member_name, String manager_member_name, String truck_member_name_json, String line_json, Integer status, Integer repair_status);

    // 添加卡车信息
//    public String addTruckInfo(String member_name, T_Data_Truck truck, String truck_type_name, String model_name, String person_name, String person_mobile_phone);

    // 车辆信息的设定
    String setTruckInfo(String member_name, String plate_number, String deposit_member_name, String manager_member_name, String truck_member_name_json, String line_json);

    // 车辆所属身份信息的删除
    String delTruckInfo(String member_name, String plate_number);

    // 根据账号获取待单中或仅有一单，有司机，未维修的车辆列表信息
//    public String findWaitTruckByMember(String member_name);

    // 根据账号获取待单有司机车辆运力
    int findLoadWeightByMember(String member_name);

    // 根据账号名、车辆的所属身份、车牌号模糊参数，获取模糊查询后的车辆列表
//    public String findTruckByMemberAndPlateNumber(String member_name, Integer identity, Integer begin_rows, Integer end_rows, String plate_number);

    // 根据账户名获取我驾驶车辆信息
//    public String findTruckDataByMember(String member_name);

    // 设定车辆修理状态
//    public String setRepairStatus(String plate_number, Integer repair_status);

    // 检索车辆车类型
    String findAllTruckTypeName();

    // 检索所有车长
    String findAllTruckTypeLength();

    // 根据车牌号检索车辆详细信息
    String searchTruckDataByPlateNumber(String plate_number);

    // 根据账号和雇佣关系检索车辆详细信息
    String searchTruckDataByMemberName(String member_name, String is_employ);

    // 添加卡车的信息
    String addTruckInformation(String member_name, String truck);

    // 检索常跑司机列表
    String searchDriverOften(String plate_number);

    // 车辆管理者变更
    String editManagerMember(String plate_number, String old_manager_member_name, String new_manager_member_name);

    // 车辆行使司机变更
    String truckDriversChange(String plate_number, String new_driving_driver_list);

    // 通过车牌号和会员号申请加入常跑司机
    String joinTheDriver(String plate_number, String member_name);

    // 通过车牌号检索待确认司机列表
    String searchDriverByPlateNumber(String plate_number);

    // 确认是否允许加入常跑司机
    String judgeJoinDriver(String plate_number, String member_name, String accept_join);

    // 解除司机与车辆的关系
    String relieveTruckAndDriver(String plate_number, String member_name);

    // 检索车辆燃料类型
    String findAllTruckFuelType();

    // 设置车辆的常驻地址
    String setTruckPermanentAddress(String plate_number, String resident_province_id, String resident_city_id, String resident_area_id);

    // 修改车辆基本信息
    String editTruckInfo(String member_name, String truck);

    // 根据账号名查询管理者车辆列表_v2.0
    String findManagerTruckListByMember(String member_name);

    // 添加卡车的信息_v2.0
    String saveTruckInformation(String member_name, String truck);

    // 检索常跑司机列表_v2.0
    String searchDriverList(String plate_number);

    // 根据账号和雇佣关系检索车辆详细信息_v2.0
    String findTruckDataByMemberName(String member_name);

    // 根据车牌号检索车辆详细信息_v2.0
    String findTruckDataByTruckPlateNumber(String plate_number);

    // 解除会员与车辆的关系_v2.0
    String relieveTruckAndMember(String plate_number, String member_name);

    // 根据车牌号会员名检索添加车辆权限_v2.0
    String findAddTruckAuthority(String plate_number, String member_name);

    // 检索所有保险公司名称
    String findAllInsuranceCompany();

    // 检索所有车辆品牌
    String findAllTruckBrand();

    // 检索所有车辆品牌下的型号
    String findAllTruckModeByTruckBrandId(String truck_brand_id);

    // 根据账号名查询司机车辆列表_v2.0
    String findManagerTruckListByDriver(String member_name);

    // 查询符合条件的卡车列表信息（货物类型，干线）
    List<T_Data_Truck> findByCargoTypeAndLineId(String cargo_type_id, String lineId);

    // 车辆账户检索（支付）
    String findTruckAccount(String member_name, String identity);

    // 车辆账户充值（支付）
    String rechargeTruckAccount(String member_name, String plate_number, String amount);

    // 车辆账户提现（支付）
    String withdrawalsTruckAccount(String member_name, String plate_number, String amount);

    // 车辆账户明细检索（支付）
    String findTruckAccountDetail(String plate_number, String date);

    // 车辆账户密码检索（支付）
    String checkTruckPaymentPassword(String c, String payment_password, String member_name);

    // 检索所有银行
    String findAllBank();

    // 车辆扫描二维码支付（支付）
    String scanQRCodePayment(String member_name, String station_id, String amount , String plate_number, String finacial_flow_type, String longitude, String latitude);

    //  查询车辆账户总额度
    double queryCashAmount();
}