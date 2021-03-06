/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：CargoTruckTypeServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物车厢车厢车厢类型匹配接口实现
 * 设计文件：
 * 完成日期：2016-05-13
 * 作    者: WXW
 * 内容摘要：货物车厢车厢车厢类型匹配接口实现
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.CargoTruckTypeMatchMapper;
import com.cn.gazelle.logistics.dao.CargoTypeDaoMapper;
import com.cn.gazelle.logistics.dao.TruckCarriageTypeDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Truck_Type_Match;
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Type;
import com.cn.gazelle.logistics.pojo.T_Master_Truck_Carriage_Type;
import com.cn.gazelle.logistics.service.CargoTruckTypeService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.CargoTruckTypeManager_Message;
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
 * 类 名 称：CargoTruckTypeServiceImpl
 * 内容描述：
 * 方法描述：该类有7个方法：
 *         01 addCargoTruckTypeByCargoType               根据货物类型添加货物车厢类型匹配
 *         02 addCargoTruckTypeByTruckType               根据车厢类型添加货物车厢类型匹配
 *         03 delCargoTruckType                          删除货物车厢类型匹配
 *         04 findCargoTruckType                         根据id查询货物车厢类型匹配
 *         05 updateCargoTruckType                       更新货物车厢类型匹配信息
 *         06 findMatchByTruck                           根据车厢类型查询匹配表
 *         07 delByCargoId                               根据货物类型删除中间表
 *@authot WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.CargoTruckTypeService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class CargoTruckTypeServiceImpl implements CargoTruckTypeService{

    Logger logger= Logger.getLogger(CargoTruckTypeServiceImpl.class);

    @Resource
    private CargoTruckTypeMatchMapper cargoTruckTypeMatchMapper;
    @Resource
    private CargoTypeDaoMapper cargoTypeDaoMapper;
    @Resource
    private TruckCarriageTypeDaoMapper truckCarriageTypeDaoMapper;

    /**
     * 方法名称：addCargoTruckTypeByCargoType
     * 内容摘要：根据货物类型添加货物车厢类型匹配
     * @param parameter
     * @param userName
     */
    @Transactional
    public int  addCargoTruckTypeByCargoType(String parameter,String userName) {
        Gson gson=new Gson();
        int a=1;
        try {
            Map<String, Object> data = gson.fromJson(parameter, new TypeToken<Map<String, Object>>() {
            }.getType());
            String cargoType = (String)data.get("cargoType");
            String cargoDesc = (String)data.get("cargoTypeDesc");
            String cargoTypeE = (String)data.get("cargoTypeE");
            String cargoTypeC = (String)data.get("cargoTypeC");
            String flag=(String)data.get("flag");
            T_Master_Cargo_Type cargo_type = cargoTypeDaoMapper.findCargoType(cargoType);
            cargo_type.setCargoTypeDesc(cargoDesc);
            cargo_type.setLastUpdate(DateUtil.getDate());
            cargo_type.setCargoTypeUnitE(cargoTypeE);
            cargo_type.setCargoTypeUnitC(cargoTypeC);
            //更新货物类型
            cargoTypeDaoMapper.updateCargoType(cargo_type);
            //判断flag 为1时匹配新增操作 为0时更新操作方便后台共用方法
            if (flag.equals("0")){
                //删除匹配信息
                cargoTruckTypeMatchMapper.delByCargoId(cargoType);
            }
            List<String> matchCar =(List) data.get("_goodType_matchCar");
            if (matchCar.size()!=0){
                for (String truckType:matchCar){
                    T_Master_Cargo_Truck_Type_Match match = new T_Master_Cargo_Truck_Type_Match();
                    match.setTruckTypeId(truckType);
                    match.setCargoTypeId(cargoType);
                    match.setDeleteFlag(0);
                    match.setLastUpdate(DateUtil.getDate());
                    match.setLastUpdateUserId(userName);
                    match.setMatchId(UUIDUtil.getUUID());
                    a = cargoTruckTypeMatchMapper.addCargoTruckType(match);
                    if (a==1){//保存成功
                        logger.info(CargoTruckTypeManager_Message.SaveCargoTruckDone);
                    }else if (a==0){//重复
                        logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
                        throw new RuntimeException();
                    }else if (a==-1){
                        logger.error(CargoTruckTypeManager_Message.SaveCargoTruckError);
                        throw new RuntimeException();
                    }
                }
            }else {
               //为0说明没有传送数据，抛异常回滚
                a=0;
                logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
                throw new RuntimeException("0");
            }

        } catch (Exception e) {
            if (a==0){
                throw new RuntimeException("0");
            }else {
                throw new RuntimeException("-1");
            }

        }
        return a;
    }
    /**
     * 方法名称：addCargoTruckTypeByTruckType
     * 内容摘要：根据车厢类型添加货物车厢类型匹配
     * @param parameter
     * @param userName
     */
    @Transactional
    public int addCargoTruckTypeByTruckType(String parameter, String userName) {
        Gson gson=new Gson();
        int a=-1;
        try {
            Map<String, Object> data = gson.fromJson(parameter, new TypeToken<Map<String, Object>>() {
            }.getType());
            String carTypeId = (String)data.get("carTypeId");
            String flag = (String)data.get("flag");
            List<String> matchCar =(List) data.get("_goodType_matchCar");
            String carTypeDesc = (String)data.get("carTypeDesc");
            T_Master_Truck_Carriage_Type carriageType = truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(carTypeId);
            carriageType.setLast_update(DateUtil.getDate());
            carriageType.setTruck_carriage_type_desc(carTypeDesc);
            //更新货物类型
            truckCarriageTypeDaoMapper.updateTruckCarriageType(carriageType);
            //判断flag 为1时匹配新增操作 为0时更新操作方便后台共用方法
            if (flag.equals("0")){
                //删除匹配信息
                cargoTruckTypeMatchMapper.delByTruckId(carTypeId);
            }
            if(matchCar.size()!=0){
                for (String cargoTypeId:matchCar) {
                    T_Master_Cargo_Truck_Type_Match match = new T_Master_Cargo_Truck_Type_Match();
                    match.setTruckTypeId(carTypeId);
                    match.setCargoTypeId(cargoTypeId);
                    match.setDeleteFlag(0);
                    match.setLastUpdate(DateUtil.getDate());
                    match.setLastUpdateUserId(userName);
                    match.setMatchId(UUIDUtil.getUUID());
                    a = cargoTruckTypeMatchMapper.addCargoTruckType(match);
                    if (a==1){//保存成功
                        logger.info(CargoTruckTypeManager_Message.SaveCargoTruckDone);
                    }else if (a==0){//重复
                        logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
                        throw new RuntimeException();
                    }else if (a==-1){
                        logger.error(CargoTruckTypeManager_Message.SaveCargoTruckError);
                        throw new RuntimeException();
                    }
                }
            }else {//为0说明没有传送数据，抛异常回滚
                a=0;
                logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
                throw new RuntimeException();

            }
        } catch (Exception e) {
            if (a==0){
                throw new RuntimeException("0");
            }else {
                throw new RuntimeException("-1");
            }
        }
        return a;
    }

    /**
     * 方法名称：delCargoTruckType
     * 内容摘要：删除货物车厢类型匹配
     * @param matchId
     */
    @Transactional
    public void delCargoTruckType(String matchId) {
        try {
            cargoTruckTypeMatchMapper.delCargoTruckType(matchId);
            logger.info(CargoTruckTypeManager_Message.DelCargoTruckDone);
        } catch (Exception e) {
            logger.error(CargoTruckTypeManager_Message.DelCargoTruckError+e.getMessage());
        }
    }

    /**
     * 方法名称：findCargoTruckType
     * 内容摘要：根据id查询货物车厢类型匹配
     * @param matchId
     */
    @Transactional
    public T_Master_Cargo_Truck_Type_Match findCargoTruckType(String matchId) {
        T_Master_Cargo_Truck_Type_Match cargoTruckType = null;
        try {
            cargoTruckType = cargoTruckTypeMatchMapper.findCargoTruckType(matchId);
            logger.info(CargoTruckTypeManager_Message.getCargoTruckDone);
        } catch (Exception e) {
            logger.error(CargoTruckTypeManager_Message.getCargoTruckError+e.getMessage());
        }
        return cargoTruckType;
    }

    /**
     * 方法名称：updateCargoTruckType
     * 内容摘要：更新货物车厢类型匹配信息
     * @param cargoTruckTypeMatch
     */
    @Transactional
    public boolean updateCargoTruckType(T_Master_Cargo_Truck_Type_Match cargoTruckTypeMatch) {
        boolean a=new Boolean(true);
        try {
            cargoTruckTypeMatchMapper.updateCargoTruckType(cargoTruckTypeMatch);
            logger.info(CargoTruckTypeManager_Message.UpdateCargoTruckDone);
        } catch (Exception e) {
            a=false;
            logger.error(CargoTruckTypeManager_Message.UpdateCargoTruckError+e.getMessage());
        }
            return a;
    }

    /**
     * 方法名称：findMatchByTruck
     * 内容摘要：根据车厢类型查询匹配表
     * @param truckType
     */
    @Transactional
    public List<T_Master_Cargo_Truck_Type_Match> findMatchByTruck(String truckType) {
        List<T_Master_Cargo_Truck_Type_Match> matchList=null;
        try {
            matchList = cargoTruckTypeMatchMapper.findMatchByTruck(truckType);
        } catch (Exception e) {
            logger.error(CargoTruckTypeManager_Message.getCargoTruckError+e.getMessage());
        }
        return matchList;
    }

    /**
     * 方法名称：delByCargoId
     * 内容摘要：根据货物类型删除中间表
     * @param cargoTypeId
     */
    @Transactional
    public void delByCargoId(String cargoTypeId) {
        try {
            cargoTruckTypeMatchMapper.delByCargoId(cargoTypeId);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Override
    @Transactional
    public T_Master_Cargo_Truck_Type_Match findMatchByTruckId( String truckTypeId, String cargoTypeId) {
        T_Master_Cargo_Truck_Type_Match typeMatch=null;
        try {
            typeMatch = cargoTruckTypeMatchMapper.findMatchByTruckId(truckTypeId, cargoTypeId);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return typeMatch;
    }


    /**
     * 方法名称：delByCargoId
     * 内容摘要：根据车厢类型删除中间表
     * @param truckTypeId
     */
    @Override
    public void delByTruckId(String truckTypeId) {
        try {
            cargoTruckTypeMatchMapper.delByTruckId(truckTypeId);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}