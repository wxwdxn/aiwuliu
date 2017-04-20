package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Immediate;

import javax.jws.WebService;

/**
 * 类 名 称: TruckImmediateService
 * 内容摘要: 卡车即时信查询接口
 * 方法描述：该类有1个方法：
 *         01 findTruckImmediateByPlateNumber           通过车牌号查询卡车即时信
 *         02 saveTruckImmediate                        增加卡车即时信
 *         03 delTruckImmediate                         删除卡车即时信
 *         04 findPlateNumberByOBDID                    通过obdID查询车牌号
 *         05 findTruckBrandNameByPlateNumber           通过车牌号获取车辆品牌名称
 *         06 findTruckModelNameByPlateNumber           通过车牌号获取车辆型号名称
 *         07 findTruckCarriageTypeNameByPlateNumber    通过车牌号获取车厢类型名称
 *         08 findTruckContactPhoneByPlateNumber        通过车牌号获取联系方式
 *         09 findDrivingDriverByPlateNumber            根据车牌号获取正在驾驶司机列表
 *         10 findCargoTypeNameByPlateNumber            通过车牌号获取货物类型名称
 *         11 findLoadingCargoAmountByPlateNumber       通过车牌号获取装货数量
 *         12 findSchedulePlanNumberByPlateNumber       通过车牌号获取运输计划编号
 * @author QJ
 */
@WebService
public interface TruckImmediateService {
    // 通过车牌号查询卡车即时信息
    T_Data_Truck_Immediate findTruckImmediateByPlateNumber(String plate_number);

    // 增加卡车即时信息
    int saveTruckImmediate(T_Data_Truck_Immediate TruckImmediate);

    // 跟新卡车即时信息
    int updateTruckImmediate(T_Data_Truck_Immediate TruckImmediate);

    // 删除卡车即时信息
    void delTruckImmediate(String plate_number);

    // 通过obdID查询车牌号
    String findPlateNumberByOBDID(String obd_id);

    // 通过车牌号获取车辆品牌名称
    String findTruckBrandNameByPlateNumber(String plate_number);

    // 通过车牌号获取车辆型号名称
    String findTruckModelNameByPlateNumber(String plate_number);

    // 通过车牌号获取车厢类型名称
    String findTruckCarriageTypeNameByPlateNumber(String plate_number);

    // 通过车牌号获取联系方式
    String findTruckContactPhoneByPlateNumber(String plate_number);

    // 根据车牌号获取正在驾驶司机列表
    String findDrivingDriverByPlateNumber(String plate_number);

    // 通过车牌号获取货物类型名称
    String findCargoTypeNameByPlateNumber(String plate_number);

    // 通过车牌号获取装货数量
    String findLoadingCargoAmountByPlateNumber(String plate_number);

    // 通过车牌号获取运输计划编号
    String findSchedulePlanNumberByPlateNumber(String plate_number);

}
