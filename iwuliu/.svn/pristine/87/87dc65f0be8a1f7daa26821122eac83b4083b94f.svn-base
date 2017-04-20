/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckCarriageTypeServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车厢类型查询接口实现
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者：QJ
 * 内容摘要：车厢类型查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TruckCarriageTypeDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Truck_Carriage_Type;
import com.cn.gazelle.logistics.service.TruckCarriageTypeService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.TruckCarriageTypeManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: TruckCarriageTypeServiceImpl
 * 内容摘要: 车厢类型查询接口
 * 方法描述：该类有11个方法：
 *         01 findTruckCarriageTypeByID             根据车厢类型ID查找车厢类型信息
 *         02 findTruckCarriageTypeByName           根据车厢类型名称查询车厢类型信息
 *         03 findAllTruckCarriageType              查询符合条件的车厢类型列表信息（默认查询所有车厢类型）
 *         04 findAllTruckCarriageTypeRowsCount     查询车厢类型记录数(包含有条件和无条件)
 *         05 findAllTruckCarriageTypeName          查询所有车厢类型名称
 *         06 saveTruckCarriageType                 增加车厢类型信息
 *         07 updateTruckCarriageType               更新车厢类型信息
 *         08 delTruckCarriageType                  删除车厢类型信息
 *         09 findTruckCarriageTypeIDByName         根据车厢类型名称查询车厢类型信息
 *         10 findAllTruckCarriageTypeByID          根据车厢类型ID所有信息列
 *         11 findAllTruckCarriageTypeIdAndName     检索所有车厢类型
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckCarriageTypeService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckCarriageTypeServiceImpl implements TruckCarriageTypeService {
    public static String code;                                 // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(TruckCarriageTypeServiceImpl.class);

    @Resource
    private TruckCarriageTypeDaoMapper truckCarriageTypeDaoMapper;            // 数据访问层

    /**
     * 方法名称：findTruckCarriageTypeByID
     * 内容摘要：根据车厢类型ID查找车厢类型信息
     * @param truck_carriage_type_id 车厢类型id
     * @return T_Master_Truck_Carriage_Type 车厢类型信息
     */
    public T_Master_Truck_Carriage_Type findTruckCarriageTypeByID(String truck_carriage_type_id) {
        T_Master_Truck_Carriage_Type truckCarriageType = null;
        try {
            truckCarriageType = this.truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck_carriage_type_id);
            logger.info(TruckCarriageTypeManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
        }
        return truckCarriageType;
    }

    /**
     * 方法名称：findTruckCarriageTypeByName
     * 内容摘要：根据车厢类型名称查询车厢类型信息
     * @param truck_carriage_type_name 车厢类型名称
     * @return T_Master_Truck_Carriage_Type 车厢类型信息
     */
    public T_Master_Truck_Carriage_Type findTruckCarriageTypeByName(String truck_carriage_type_name) {
        T_Master_Truck_Carriage_Type truckCarriageType = null;
        try {
            truckCarriageType = this.truckCarriageTypeDaoMapper.findTruckCarriageTypeByName(truck_carriage_type_name);
            logger.info(TruckCarriageTypeManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
        }
        return truckCarriageType;
    }

    /**
     * 方法名称：findAllTruckCarriageType
     * 内容摘要：查询符合条件的车厢类型列表信息（默认查询所有车厢类型）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @param page 页面页数
     * @param rows 页面显示条数
     * @return List<T_Master_Truck_Carriage_Type>  车厢类型信息列
     */
    public List<T_Master_Truck_Carriage_Type> findAllTruckCarriageType(String search_type, String name, Integer page, Integer rows) {
        List<T_Master_Truck_Carriage_Type> list = null;
        try {
            list = this.truckCarriageTypeDaoMapper.findAllTruckCarriageType(search_type, name, page, rows);
            logger.info(TruckCarriageTypeManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findAllTruckCarriageTypeRowsCount
     * 内容摘要：查询符合条件的车厢类型列表信息（默认查询所有车厢类型）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @return Integer  车厢类型信息记录数
     */
    public Integer findAllTruckCarriageTypeRowsCount(String search_type, String name) {
        int c = 0;
        try {
            c = this.truckCarriageTypeDaoMapper.findAllTruckCarriageTypeRowsCount(search_type, name);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.getSelectTruckCarriageTypeCountError + e.getMessage());
        }
        return c;
    }

    /**
     * 方法名称：findAllTruckCarriageTypeName
     * 内容摘要：查询所有车厢类型名称
     * @return String 返回值json
     */
    //查询所有车厢类型名称
    public String findAllTruckCarriageTypeName() {
        String json = null;
        Map result = new HashMap();
        List<T_Master_Truck_Carriage_Type> list = null;
        List truckCarriageTypeNameList = new ArrayList();
        String ecode = null;
        try {
            list = this.truckCarriageTypeDaoMapper.findAllTruckCarriageTypeName();
            for (T_Master_Truck_Carriage_Type truckCarriageType : list) {
                truckCarriageTypeNameList.add(truckCarriageType.getTruck_carriage_type_name());
            }
            if (truckCarriageTypeNameList != null) {
                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                result.put("truckCarriageTypeNameList", truckCarriageTypeNameList);
            }
        } catch (Exception e) {
            if (truckCarriageTypeNameList == null) {
                ecode = "2000";   // 响应失败
                result.put("ecode","");
                result.put("truckCarriageTypeNameList","响应失败");
                logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
            }
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }


    /**
     * 方法名称：saveTruckCarriageType
     * 内容摘要：增加车厢类型信息
     * @param parameter 车厢类型信息
     * @param userName 用户
     */
    @Transactional
    public int saveTruckCarriageType(String parameter,String userName) {
        Gson gson = new Gson();
        int count=-1;
    try {
        Map<String, Object> data = gson.fromJson(parameter, new TypeToken<Map<String, Object>>() {
        }.getType());
        String carType = (String) data.get("carTypeName");
        String carDesc = (String) data.get("carTypeDesc");
        T_Master_Truck_Carriage_Type carriageType = new T_Master_Truck_Carriage_Type();
        carriageType.setTruck_carriage_useable("1");//1 代表可用
        carriageType.setTruck_carriage_type_id(UUIDUtil.getUUID());
        carriageType.setTruck_carriage_type_name(carType);
        carriageType.setTruck_carriage_type_desc(carDesc);
        carriageType.setDelete_flag(0);
        carriageType.setLast_update(DateUtil.getDate());
        carriageType.setLast_update_user_id(userName);
        count = this.truckCarriageTypeDaoMapper.saveTruckCarriageType(carriageType);
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
     * 方法名称：updateTruckCarriageType
     * 内容摘要：更新车厢类型信息
     * @param truckCarriageType 车厢类型信息
     */
    public boolean updateTruckCarriageType(T_Master_Truck_Carriage_Type truckCarriageType) {
        boolean b = new Boolean(true);
        try {
            this.truckCarriageTypeDaoMapper.updateTruckCarriageType(truckCarriageType);
            logger.info(TruckCarriageTypeManager_Message.updataDone);
        } catch (Exception e) {
            b = false;
            logger.error(TruckCarriageTypeManager_Message.updataErr + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delTruckCarriageType
     * 内容摘要：删除车厢类型信息
     * @param truck_carriage_type_id 车厢类型id
     */
    public void delTruckCarriageType(String truck_carriage_type_id) {
        try {
            this.truckCarriageTypeDaoMapper.delTruckCarriageType(truck_carriage_type_id);
            logger.info(TruckCarriageTypeManager_Message.delDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.delErr + e.getMessage());
        }
    }

    /**
     * 方法名称：findTruckCarriageTypeIDByName
     * 内容摘要：根据车厢类型名称查询车厢类型信息
     * @param truck_carriage_type_name 车厢类型名称
     * @return T_Master_Truck_Carriage_Type 车厢类型信息
     */
    //根据车厢类型名称查询车厢类型
    public T_Master_Truck_Carriage_Type findTruckCarriageTypeIDByName(String truck_carriage_type_name) {
        T_Master_Truck_Carriage_Type truckCarriageType = null;
        try {
            truckCarriageType = this.truckCarriageTypeDaoMapper.findTruckCarriageTypeByName(truck_carriage_type_name);
            logger.info(TruckCarriageTypeManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
        }
        return truckCarriageType;
    }

    /**
     * 方法名称：findAllTruckCarriageTypeByID
     * 内容摘要：根据车厢类型ID所有信息列
     * @param truck_carriage_type_id 车厢类型名称
     * @return List<T_Master_Truck_Carriage_Type> 车厢类型信息
     */
    public List findAllTruckCarriageTypeByID(String truck_carriage_type_id) {
        List<T_Master_Truck_Carriage_Type> list = null;
        try {
            list = this.truckCarriageTypeDaoMapper.findAllTruckCarriageTypeByID(truck_carriage_type_id);
            logger.info(TruckCarriageTypeManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findAllTruckCarriageTypeIdAndName
     * 内容摘要：检索所有车厢类型
     * @return String 返回值json
     */
    public String findAllTruckCarriageTypeIdAndName(){
        String json = null;
        Map result = new HashMap();
        List<T_Master_Truck_Carriage_Type> list = null;
        List truckCarriageTypeNameList = new ArrayList();
        String ecode = null;
        try {
            list = this.truckCarriageTypeDaoMapper.findAllTruckCarriageTypeIdAndName();
            for (T_Master_Truck_Carriage_Type truckCarriageType : list) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("truck_carriage_type_id",truckCarriageType.getTruck_carriage_type_id());
                results.put("truck_carriage_type_name",truckCarriageType.getTruck_carriage_type_name());
                truckCarriageTypeNameList.add(results);
            }
            if (truckCarriageTypeNameList != null) {
                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                result.put("object1", truckCarriageTypeNameList);
            }
        } catch (Exception e) {
            if (truckCarriageTypeNameList == null) {
                ecode = "2000";   // 响应失败
                result.put("ecode","");
                logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
            }
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findAllTruckCarriageType
     * 内容摘要：查询所有的车厢类型列表信息
     * @return List<T_Master_Truck_Carriage_Type>  车厢类型信息列
     */
    public List<T_Master_Truck_Carriage_Type> findAllTruckCarriageType2() {
        List<T_Master_Truck_Carriage_Type> list = null;
        try {
            list = this.truckCarriageTypeDaoMapper.findAllTruckCarriageTypeIdAndName();
            logger.info(TruckCarriageTypeManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
        }
        return list;
    }
}