/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：CargoTypeServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物类型信息查询接口实现
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者: WXW
 * 内容摘要：货物类型信息查询接口实现
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.CargoTypeDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Type;
import com.cn.gazelle.logistics.service.CargoTypeService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.CargoTypeManager_Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：CargoTypeServiceImpl
 * 内容摘要: 货物类型的接口
 * 方法描述：该类有7个方法：
 *         01 addCargoType                           添加货物类型
 *         02 delCargoType                           删除货物类型
 *         03 findCaogoType                          根据id查询货物类型
 *         04 updateCargoType                        更新货物类型信息
 *         05 findAllCargoType                       查询所有货物类型信息
 *         06 findCargoTypeLists                     查询所有的货物类型列表不分页用于下拉的回显操作
 *         07 findCargoTypeRowsCount                 查询所有货物类型个数
 *  @author WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.CargoTypeService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class CargoTypeServiceImpl implements CargoTypeService {
    Logger logger = Logger.getLogger(CargoTypeServiceImpl.class);

    @Resource
    private CargoTypeDaoMapper cargoTypeDaoMapper;

    // 添加货物类型
    @Transactional
    public int addCargoType(String cargoTypeDetail, String userName) {
        Gson gson = new Gson();
        int a = -1;
        try {
            Map<String, Object> data = gson.fromJson(cargoTypeDetail, new TypeToken<Map<String, Object>>() {
            }.getType());
            String cargoType = (String) data.get("cargoType");
            String cargoDesc = (String) data.get("cargoDesc");
            String UnitC = (String) data.get("UnitC");
            String UnitE = (String) data.get("UnitE");
            T_Master_Cargo_Type cargo_type = new T_Master_Cargo_Type();
            cargo_type.setCargoTypeId(UUIDUtil.getUUID());
            cargo_type.setCargoTypeUnitC(UnitC);
            cargo_type.setCargoTypeUnitE(UnitE);
            cargo_type.setLastUpdateUserId(userName);
            cargo_type.setCargoTypeDesc(cargoDesc);
            cargo_type.setCargoTypeName(cargoType);
            cargo_type.setDeleteFlag(0);
            cargo_type.setLastUpdate(DateUtil.getDate());
            a = cargoTypeDaoMapper.addCargoType(cargo_type);
            if (a == 1) {//保存成功
                logger.info(CargoTypeManager_Message.SaveCargoTypeDone);
            } else if (a == 0) {//重复
                logger.info(CargoTypeManager_Message.SaveCargoTypeError);
            }
        } catch (Exception e) {
            a = -1;//保存失败
            throw new RuntimeException(a + "");
        }
        return a;
    }

    // 删除货物类型
    public void delCargoType(String cargoTypeId) {
        try {
            cargoTypeDaoMapper.delCargoType(cargoTypeId);
            logger.info(CargoTypeManager_Message.DelCargoTypeDone);
        } catch (Exception e) {
            logger.error(CargoTypeManager_Message.DelCargoTypeError + e.getMessage());
        }
    }

    // 根据id查询货物类型
    public T_Master_Cargo_Type findCargoType(String cargoTypeId) {
        T_Master_Cargo_Type cargoType = null;
        try {
            cargoType = cargoTypeDaoMapper.findCargoType(cargoTypeId);
            logger.info(CargoTypeManager_Message.getCargoTypeDone);
        } catch (Exception e) {
            logger.error(CargoTypeManager_Message.getCargoTypeError + e.getMessage());
        }
        return cargoType;
    }

    // 查询所有的货物类型列表不分页用于下拉的回显操作
    public List<T_Master_Cargo_Type> findCargoTypeLists() {
        List<T_Master_Cargo_Type> cargoTypeLists = null;
        try {
            cargoTypeLists = cargoTypeDaoMapper.findCargoTypeLists();
            logger.info(CargoTypeManager_Message.getCargoTypeDone);
        } catch (Exception e) {
            logger.error(CargoTypeManager_Message.getCargoTypeError + e.getMessage());
        }
        return cargoTypeLists;
    }

}