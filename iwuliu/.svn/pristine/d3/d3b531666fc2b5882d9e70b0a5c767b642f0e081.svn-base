package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Immediate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by QJ on 2016/11/6.
 */
public interface TruckImmediateDaoMapper {
    // 通过车牌号查询卡车即时信
    T_Data_Truck_Immediate findTruckImmediateByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 保存卡车即时信
    int saveTruckImmediate(T_Data_Truck_Immediate TruckImmediate);

    // 跟新卡车即时信
    int updateTruckImmediate(T_Data_Truck_Immediate TruckImmediate);

    // 删除卡车即时信
    void delTruckImmediate(@Param(value = "plate_number") String plate_number);

    // 通过obdID查询车牌号
    String findPlateNumberByOBDID(@Param(value = "obd_id") String obd_id);

    // 通过车牌号获取车辆品牌名称
    String findTruckBrandNameByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 通过车牌号获取车辆型号名称
    String findTruckModelNameByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 通过车牌号获取车厢类型名称
    String findTruckCarriageTypeNameByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 通过车牌号获取联系方式
    String findTruckContactPhoneByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 根据车牌号获取正在驾驶司机列表
    List<Map> findDrivingDriverByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 通过车牌号获取货物类型名称
    String findCargoTypeNameByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 通过车牌号获取装货数量
    String findLoadingCargoAmountByPlateNumber(@Param(value = "plate_number") String plate_number);

    // 通过车牌号获取运输计划编号
    String findSchedulePlanNumberByPlateNumber(@Param(value = "plate_number") String plate_number);
}
