/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckModelServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车型号查询接口实现
 * 设计文件：
 * 完成日期：2016-11-06
 * 作    者：QJ
 * 内容摘要：卡车型号查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DicdataDaoMapper;
import com.cn.gazelle.logistics.dao.TruckModelDaoMapper;
import com.cn.gazelle.logistics.dao.VehicleModelDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Model;
import com.cn.gazelle.logistics.pojo.T_Data_Vehicle_Model;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.TruckModelService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: TruckModelServiceImpl
 * 内容摘要: 卡车型号查询接口
 * 方法描述：该类有2个方法：
 *         01 findTruckModelByNo                根据编号查询卡车型号
 *         02 findTruckModelLists               查询所有的卡车型号列表不分页用于下拉的回显操作
 *         03 findTruckModelByBrandId           根据车辆品牌ID查询车辆型号列表
 *
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckModelService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckModelServiceImpl implements TruckModelService {
    // Log初始化
    Logger logger = Logger.getLogger(TruckModelServiceImpl.class);

    @Resource
    private TruckModelDaoMapper truckModelDaoMapper;            // 数据访问层
    @Resource
    private VehicleModelDaoMapper vehicleModelDaoMapper;        // 数据访问层
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;                  // 数据访问层

    /**
     * 方法名称：findTruckModelByNo
     * 内容摘要：根据编号查询卡车型号
     * @param truck_model_no 编号
     * @return T_Data_Truck_Model 卡车型号信息
     */
    public T_Data_Truck_Model findTruckModelByNo(String truck_model_no) {
        T_Data_Truck_Model truckModel = null;
        try {
            truckModel = this.truckModelDaoMapper.findTruckModelByNo(truck_model_no);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return truckModel;
    }

    /**
     * 方法名称：findTruckModelLists
     * 内容摘要：查询所有的卡车型号列表不分页用于下拉的回显操作
     * @return truckModelLists 所有卡车型号信息
     */
    public List<T_Data_Truck_Model> findTruckModelLists() {
        List<T_Data_Truck_Model> truckModelLists = null;
        try {
            truckModelLists = truckModelDaoMapper.findTruckModelLists();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return truckModelLists;
    }

    // 根据车辆品牌ID查询车辆型号列表
    /**
     * 方法名称：findTruckModelByBrandId
     * 内容摘要：根据车辆品牌ID查询车辆型号列表
     * @return truckModelLists 卡车型号信息列表
     */
    public List<T_Data_Truck_Model> findTruckModelByBrandId(String truck_brand_id){
        List<T_Data_Truck_Model> truckModelLists = null;
        try {
            truckModelLists = truckModelDaoMapper.findTruckModelByBrandId(truck_brand_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return truckModelLists;
    }

    /**
     * 方法名称：findVehicleModelManagerList
     * 内容摘要：根据条件查询线下加盟服务站列表
     *
     * @return findRouterList 线路列表
     */
    public List<T_Data_Vehicle_Model> findVehicleModelManagerList(HashMap<String, String> conditions) {
        List<T_Data_Vehicle_Model> vehicleModelManagerList = null;
        try {
            vehicleModelManagerList = this.vehicleModelDaoMapper.findVehicleModelManagerList(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return vehicleModelManagerList;
    }

    /**
     * 方法名称：saveVehicleModel
     * 内容摘要：新增车辆型号
     *
     * @param list     车辆型号信息
     * @param userName 用户名
     * @return flag
     */
    @Transactional
    public int saveVehicleModel(String list, String userName) {
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(list, new TypeToken<Map<String, Object>>() {
        }.getType());
        int count = 0;
        int flag = 0;
        String truck_brand_id = null;
        T_Sys_Dicdata dicdata = null;
        try {
            T_Data_Truck_Model truckModel = new T_Data_Truck_Model();
            String truck_brand_no = (String) data.get("truck_brand_no");
            dicdata = dicdataDaoMapper.findDicdataByID(truck_brand_no);
            if (dicdata != null) {
                truck_brand_id = dicdata.getDicdata_id();
                truckModel.setTruck_brand_id(truck_brand_id);
                String truck_model_name = (String) data.get("truck_model_name");
                truckModel.setTruck_model_name(truck_model_name);
                truckModel.setLast_update(DateUtil.getDate());
                truckModel.setLast_update_user_id("U:" + userName);
                count = this.truckModelDaoMapper.saveTruckModel(truckModel);
            }
            if (count == 1) {
                flag = 1;   // 保存成功
            } else {
                flag = 0;   // 保存失败
                throw new RuntimeException(flag + "");
            }
        } catch (Exception e) {
            flag = -1;
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：upDateVehicleModel
     * 内容摘要：编辑车辆型号
     *
     * @param list     车辆型号信息
     * @param userName 用户名
     * @return flag
     */
    @Transactional
    public int upDateVehicleModel(String list, String userName) {
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(list, new TypeToken<Map<String, Object>>() {
        }.getType());
        T_Data_Truck_Model truckModel = null;
        int count = 0;
        int flag = 0;
        String truck_brand_id = null;
        T_Sys_Dicdata dicdata = null;
        try {
            String truck_model_no = String.valueOf(data.get("truck_model_no"));
            String truck_brand_code = (String) data.get("truck_brand_code");
            truckModel = this.truckModelDaoMapper.findTruckModelByNo(truck_model_no);
            dicdata = dicdataDaoMapper.findDicdataByID(truck_brand_code);
            if (truckModel != null && dicdata != null) {
                truck_brand_id = dicdata.getDicdata_id();
                String truck_model_name = (String) data.get("truck_model_name");
                truckModel.setTruck_model_name(truck_model_name);
                truckModel.setTruck_brand_id(truck_brand_id);
                truckModel.setLast_update(DateUtil.getDate());
                truckModel.setLast_update_user_id("U:" + userName);
                count = truckModelDaoMapper.updateTruckModel(truckModel);
                if (count == 1) {
                    flag = 1;   // 保存成功
                } else {
                    flag = 0;   // 保存失败
                    throw new RuntimeException(flag + "");
                }
            } else {
                flag = 2; // 不存在
            }
        } catch (Exception e) {
            flag = -1;
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：delTruckModel
     * 内容摘要：删除卡车型号信息
     * @param truck_model_no 卡车型号id
     */
    public void delTruckModel(String truck_model_no) {
        try {
            this.truckModelDaoMapper.delTruckModel(truck_model_no);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：findVehicleModelByNo
     * 内容摘要：根据编号查询卡车型号、品牌
     * @param truck_model_no 编号
     * @return T_Data_Vehicle_Model 卡车车型号、品牌信息
     */
    public T_Data_Vehicle_Model findVehicleModelByNo(String truck_model_no) {
        T_Data_Vehicle_Model vehicleModel = null;
        try {
            vehicleModel = this.vehicleModelDaoMapper.findVehicleModelByNo(truck_model_no);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return vehicleModel;
    }
}