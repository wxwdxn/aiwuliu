/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: VehicleServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆管理查询接口实现
 * 设计文件：
 * 完成日期：2016-11-10
 * 作    者：QJ
 * 内容摘要：车辆管理查询
 */

package com.cn.gazelle.logistics.service.impl;


import com.cn.gazelle.logistics.dao.VehicleDetailDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Vehicle_Detail;
import com.cn.gazelle.logistics.service.VehicleDetailService;
import com.cn.gazelle.logistics.util.message.TruckManager_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;

/**
 * 类 名 称: VehicleDetailServiceImpl
 * 内容摘要: 车辆详情查询接口
 * 方法描述：该类有1个方法：
 * 01 findVehicleDetail   根据条件查找车辆详情
 *
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.VehicleDetailService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class VehicleDetailServiceImpl implements VehicleDetailService {
    public static String code;                                 // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(VehicleDetailServiceImpl.class);

    @Resource
    private VehicleDetailDaoMapper vehicleDetailDaoMapper;            // 数据访问层

    /**
     * 方法名称：findVehicleDetail
     * 内容摘要：根据条件查找车辆详情
     *
     * @return vehicle 车辆详情
     */
    public T_Data_Vehicle_Detail findVehicleDetail(String plate_number, String organization_type) {
        T_Data_Vehicle_Detail vehicle = null;
        try {
            vehicle = this.vehicleDetailDaoMapper.findVehicleDetail(plate_number,organization_type);
            logger.info(TruckManager_Message.seacheInfo);
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return vehicle;
    }
}