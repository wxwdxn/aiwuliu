/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckModelServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车即时信息查询接口实现
 * 设计文件：
 * 完成日期：2016-11-10
 * 作    者：QJ
 * 内容摘要：卡车即时信息查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TruckImmediateDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Immediate;
import com.cn.gazelle.logistics.service.TruckImmediateService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: TruckImmediateServiceImpl
 * 内容摘要: 卡车即时信息查询接口
 * 方法描述：该类有3个方法：
 * 01 findTruckImmediateByPlateNumber               通过车牌号查询卡车即时信息
 * 02 saveTruckImmediate                            保存卡车即时信息
 * 03 delTruckImmediate                             删除卡车即时信息
 *
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckImmediateService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckImmediateServiceImpl implements TruckImmediateService {
    public static String code;                                 // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(TruckImmediateServiceImpl.class);

    @Resource
    private TruckImmediateDaoMapper truckImmediateDaoMapper;            // 数据访问层

    /**
     * 方法名称：findTruckImmediateByPlateNumber
     * 内容摘要：通过车牌号查询卡车即时信息
     *
     * @param plate_number 车牌号
     * @return T_Data_Truck_Immediate 卡车即时信息
     */
    public T_Data_Truck_Immediate findTruckImmediateByPlateNumber(String plate_number) {
        T_Data_Truck_Immediate truckImmediate = null;
        try {
            truckImmediate = this.truckImmediateDaoMapper.findTruckImmediateByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return truckImmediate;
    }

    /**
     * 方法名称：saveTruckImmediate
     * 内容摘要：保存卡车即时信息
     *
     * @param TruckImmediate 卡车即时信息
     */
    public int saveTruckImmediate(T_Data_Truck_Immediate TruckImmediate) {
        int count = 0;
        try {
            count = this.truckImmediateDaoMapper.saveTruckImmediate(TruckImmediate);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateTruckImmediate
     * 内容摘要：跟新卡车即时信息
     *
     * @param TruckImmediate 卡车即时信息
     */
    public int updateTruckImmediate(T_Data_Truck_Immediate TruckImmediate) {
        int count = 0;
        try {
            count = this.truckImmediateDaoMapper.updateTruckImmediate(TruckImmediate);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：delTruckImmediate
     * 内容摘要：删除卡车即时信息
     *
     * @param plate_number 车牌号
     */
    public void delTruckImmediate(String plate_number) {
        try {
            this.truckImmediateDaoMapper.delTruckImmediate(plate_number);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：findPlateNumberByOBDID
     * 内容摘要：通过obdID查询车牌号
     *
     * @param obd_id OBD ID
     * @return plate_number 车牌号
     */
    public String findPlateNumberByOBDID(String obd_id) {
        String plate_number = null;
        try {
            plate_number = this.truckImmediateDaoMapper.findPlateNumberByOBDID(obd_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return plate_number;
    }

    /**
     * 方法名称：findTruckBrandNameByPlateNumber
     * 内容摘要：通过车牌号获取车辆品牌名称
     *
     * @param plate_number 车牌号
     * @return truck_brand_name 辆品牌名称
     */
    public String findTruckBrandNameByPlateNumber(String plate_number) {
        String truck_brand_name = null;
        try {
            truck_brand_name = this.truckImmediateDaoMapper.findTruckBrandNameByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return truck_brand_name;
    }

    /**
     * 方法名称：findTruckModelNameByPlateNumber
     * 内容摘要：通过车牌号获取车辆型号名称
     *
     * @param plate_number 车牌号
     * @return truck_model_name 辆品型号称
     */
    public String findTruckModelNameByPlateNumber(String plate_number) {
        String truck_model_name = null;
        try {
            truck_model_name = this.truckImmediateDaoMapper.findTruckModelNameByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return truck_model_name;
    }

    /**
     * 方法名称：findTruckCarriageTypeNameByPlateNumber
     * 内容摘要：通过车牌号获取卡车即时信息名称
     *
     * @param plate_number 车牌号
     * @return truck_carriage_type_name 厢类型名称
     */
    public String findTruckCarriageTypeNameByPlateNumber(String plate_number) {
        String truck_carriage_type_name = null;
        try {
            truck_carriage_type_name = this.truckImmediateDaoMapper.findTruckCarriageTypeNameByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return truck_carriage_type_name;
    }

    /**
     * 方法名称：findTruckContactPhoneByPlateNumber
     * 内容摘要：通过车牌号获取联系方式
     *
     * @param plate_number 车牌号
     * @return person_mobile_phone 联系方式
     */
    public String findTruckContactPhoneByPlateNumber(String plate_number) {
        String person_mobile_phone = null;
        try {
            person_mobile_phone = this.truckImmediateDaoMapper.findTruckContactPhoneByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return person_mobile_phone;
    }

    /**
     * 方法名称：findDrivingDriverByPlateNumber
     * 内容摘要：通过车牌号获取联系方式
     *
     * @param plate_number 车牌号
     * @return driving_driver 正在驾驶司机
     */
    public String findDrivingDriverByPlateNumber(String plate_number) {
        List<Map> drivingDriverList = null;
        StringBuffer driving_drivers = new StringBuffer();
        String driving_driver = null;
        try {
            drivingDriverList = this.truckImmediateDaoMapper.findDrivingDriverByPlateNumber(plate_number);
            if (drivingDriverList != null && drivingDriverList.size() != 0) {
                for (int i = 0;i<drivingDriverList.size();i++) {
                    Map drivingDriver = drivingDriverList.get(i);
                    String str = drivingDriver.get("PERSON_NAME") + "、";
                    driving_drivers.append(str);
                }
                driving_driver = driving_drivers.substring(0, driving_drivers.length() - 1) + "";
            } else {
                driving_driver = "none";// 没有司机
            }
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return driving_driver;
    }

    /**
     * 方法名称：findCargoTypeNameByPlateNumber
     * 内容摘要：通过车牌号获取货物类型名称
     *
     * @param plate_number 车牌号
     * @return cargo_type_name 货物类型名称
     */
    public String findCargoTypeNameByPlateNumber(String plate_number) {
        String cargo_type_name = null;
        try {
            cargo_type_name = this.truckImmediateDaoMapper.findCargoTypeNameByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return cargo_type_name;
    }

    /**
     * 方法名称：findLoadingCargoAmountByPlateNumber
     * 内容摘要：通过车牌号获取装货数量
     *
     * @param plate_number 车牌号
     * @return loading_cargo_amount 装货数量
     */
    public String findLoadingCargoAmountByPlateNumber(String plate_number) {
        String loading_cargo_amount = null;
        try {
            loading_cargo_amount = this.truckImmediateDaoMapper.findLoadingCargoAmountByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return loading_cargo_amount;
    }

    /**
     * 方法名称：findSchedulePlanNumberByPlateNumber
     * 内容摘要：通过车牌号获取运输计划编号
     *
     * @param plate_number 车牌号
     * @return schedule_plan_number 运输计划编号
     */
    public String findSchedulePlanNumberByPlateNumber(String plate_number) {
        String schedule_plan_number = null;
        try {
            schedule_plan_number = this.truckImmediateDaoMapper.findSchedulePlanNumberByPlateNumber(plate_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return schedule_plan_number;
    }
}