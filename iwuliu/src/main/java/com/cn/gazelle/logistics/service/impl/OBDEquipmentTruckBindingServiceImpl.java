/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OBDEquipmentTruckBindingServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-18
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.OBDEquipmentTruckBindingDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment_Truck_Binding;
import com.cn.gazelle.logistics.pojo.T_Data_OBD_Truck_Binding_Info;
import com.cn.gazelle.logistics.service.OBDEquipmentTruckBindingService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：OBDEquipmentTruckBindingServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.OBDEquipmentTruckBindingService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class OBDEquipmentTruckBindingServiceImpl implements OBDEquipmentTruckBindingService {
    Logger logger = Logger.getLogger(OBDEquipmentTruckBindingServiceImpl.class);
    @Resource
    private OBDEquipmentTruckBindingDaoMapper obdEquipmentTruckBindingDaoMapper;

    /**
     * 方法名称：findByBindingNumber
     * 内容摘要：根据设备id查询OBD设备卡车绑定信息
     *
     * @param binding_number 绑定信息标号
     * @return T_Data_OBD_Equipment_Truck_Binding 设备绑定信息
     */
    public T_Data_OBD_Equipment_Truck_Binding findByBindingNumber(String binding_number) {
        T_Data_OBD_Equipment_Truck_Binding obdEquipmentTruckBinding = null;
        try {
            obdEquipmentTruckBinding = obdEquipmentTruckBindingDaoMapper.findByBindingNumber(binding_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError);
        }
        return obdEquipmentTruckBinding;
    }

    /**
     * 方法名称：findByEquipmentId
     * 内容摘要：根据设备id查询OBD设备卡车绑定信息
     *
     * @param equipment_id 设备id
     * @return List<T_Data_OBD_Equipment_Truck_Binding> 设备绑定信息
     */
    public List<T_Data_OBD_Equipment_Truck_Binding> findByEquipmentId(String equipment_id) {
        List<T_Data_OBD_Equipment_Truck_Binding> obdEquipmentTruckBindingList = null;
        try {
            obdEquipmentTruckBindingList = obdEquipmentTruckBindingDaoMapper.findByEquipmentId(equipment_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError);
        }

        return obdEquipmentTruckBindingList;
    }

    /**
     * 方法名称：saveOBDTruckBindingInfo
     * 内容摘要：添加OBD设备卡车绑定信息
     *
     * @param equipmentTruckBinding 设备卡车绑定信息
     * @return int
     */
    public int saveOBDTruckBindingInfo(T_Data_OBD_Equipment_Truck_Binding equipmentTruckBinding) {
        int i = 0;
        try {
            i = obdEquipmentTruckBindingDaoMapper.saveOBDTruckBindingInfo(equipmentTruckBinding);
            if (i == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (i == 0) {
                logger.info(MessageUtil.saveInfoError);
            }
        } catch (Exception e) {
            i = -1;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return i;
    }

    /**
     * 方法名称：updateOBDTruckBindingInfo
     * 内容摘要：更新OBD设备卡车绑定信息
     *
     * @param equipmentTruckBinding 设备卡车绑定信息
     * @return boolean
     */
    public boolean updateOBDTruckBindingInfo(T_Data_OBD_Equipment_Truck_Binding equipmentTruckBinding) {
        boolean b = new Boolean(true);
        try {
            obdEquipmentTruckBindingDaoMapper.updateOBDTruckBindingInfo(equipmentTruckBinding);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delOBDTruckBindingInfo
     * 内容摘要：删除OBD设备卡车绑定信息
     *
     * @param binding_number 绑定信息标号
     */
    public void delOBDTruckBindingInfo(String binding_number) {
        try {
            obdEquipmentTruckBindingDaoMapper.delOBDTruckBindingInfo(binding_number);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }

    }

    /**
     * 方法名称：findAllOBDTruckBindingList
     * 内容摘要：查询OBD设备卡车绑定信息不分页
     *
     * @return List<T_Data_OBD_Equipment_Truck_Binding>
     */
    public List<T_Data_OBD_Equipment_Truck_Binding> findAllOBDTruckBindingList() {
        List<T_Data_OBD_Equipment_Truck_Binding> bindingList = null;
        try {
            bindingList = obdEquipmentTruckBindingDaoMapper.findAllOBDTruckBindingList();
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return bindingList;
    }

    /**
     * 方法名称：findAllOBDTruckBindingList
     * 内容摘要：查询运维OBD设备管理列表
     *
     * @return List<T_Data_OBD_Truck_Binding_Info>
     */
    public List<T_Data_OBD_Truck_Binding_Info> findOBDTruckBindingInfoList(HashMap<String, String> conditions) {
        List<T_Data_OBD_Truck_Binding_Info> obdTruckBindingInfoList = null;
        try {
            obdTruckBindingInfoList = this.obdEquipmentTruckBindingDaoMapper.findOBDTruckBindingInfoList(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return obdTruckBindingInfoList;

    }

     /**
     * 方法名称：findOBDTruckBindingInfoByTruckID
     * 内容摘要：通过卡车id查找已绑定的卡车绑定信息
     * @param  truck_id
     * @return T_Data_OBD_Equipment_Truck_Binding
     */
    public T_Data_OBD_Equipment_Truck_Binding findOBDTruckBindingInfoByTruckID(String truck_id) {
        T_Data_OBD_Equipment_Truck_Binding truckBinding = null;
        try {
             truckBinding = this.obdEquipmentTruckBindingDaoMapper.findOBDTruckBindingInfoByTruckID(truck_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return truckBinding;
    }
}
