/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ScheduleSheetServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-14
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.SchedulePlanDaoMapper;
import com.cn.gazelle.logistics.dao.TransportationOrderDaoMapper;
import com.cn.gazelle.logistics.dao.TruckDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Plan;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Sheet_Plan;
import com.cn.gazelle.logistics.pojo.T_Data_Truck;
import com.cn.gazelle.logistics.service.SchedulePlanService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：ScheduleSheetServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.SchedulePlanService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class SchedulePlanServiceImpl implements SchedulePlanService {
    Logger logger = Logger.getLogger(SchedulePlanServiceImpl.class);

    @Resource
    private SchedulePlanDaoMapper ScheduleDaoMapper;
    @Resource
    private TruckDaoMapper truckDaoMapper;
    @Resource
    private TransportationOrderDaoMapper orderDaoMapper;



    @Override
    @Transactional
    //保存
    public int addScheduleSheet(String params,String username) {
       int flag;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Gson gson = new Gson();
            Map<String, Object> data = gson.fromJson(params, new TypeToken<Map<String, Object>>() {
            }.getType());
            String planNumber = (String) data.get("planNumber");
            String plateNumber = (String) data.get("plateNumber");
            T_Data_Truck truck = truckDaoMapper.findTruckByPlateNumber(plateNumber);
            String truckId = truck.getTruck_id();
            //设置运单的吨数
            double loadWeight = Double.valueOf(truck.getLoad_weight());
            T_Data_Transportation_Plan transportationPlan = orderDaoMapper.findOrderById(planNumber);
            double redistributeCargoTotal = transportationPlan.getRedistribute_cargo_total();
            int cargoTotal = transportationPlan.getCargo_total();
            transportationPlan.setRedistribute_cargo_total(redistributeCargoTotal+loadWeight);
            transportationPlan.setLast_update(DateUtil.getDate());
            transportationPlan.setLast_update_user_id(username);
            orderDaoMapper.updateTransportateOrder(transportationPlan);
            String beginDate = (String) data.get("beginDate");
            T_Data_Transportation_Schedule_Sheet_Plan scheduleSheetPlan = new T_Data_Transportation_Schedule_Sheet_Plan();
            scheduleSheetPlan.setDeleteFlag(0);
            scheduleSheetPlan.setLastUpdate(DateUtil.getDate());
            scheduleSheetPlan.setLastUpdateUserId(username);
            scheduleSheetPlan.setBeginScheduleTime(format.parse(beginDate));
            scheduleSheetPlan.setSchedule_plan_number(planNumber);
            scheduleSheetPlan.setScheduleTruckId(truckId);
            scheduleSheetPlan.setStatus(0);
            ScheduleDaoMapper.addScheduleSheet(scheduleSheetPlan);
            flag=1;
        }catch (Exception e){
            logger.info(e.getMessage());
            flag=2;
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    @Override
    @Transactional
    //查询未查看的订单
    public List<T_Data_Transportation_Schedule_Sheet_Plan> findUnLookSheet(String schedule_plan_number) {
        List<T_Data_Transportation_Schedule_Sheet_Plan> unLookSheet =null;
        try {
            unLookSheet = ScheduleDaoMapper.findUnLookSheet(schedule_plan_number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unLookSheet;
    }

    @Override
    @Transactional
    //查询已查看的订单
    public List<T_Data_Transportation_Schedule_Sheet_Plan> findLookSheet(String schedule_plan_number) {
        List<T_Data_Transportation_Schedule_Sheet_Plan> LookSheet =null;
        try {
            LookSheet = ScheduleDaoMapper.findLookSheet(schedule_plan_number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LookSheet;
    }

    @Override
    @Transactional
    //删除
    public void deleteScheduleSheet(String planNumber) {
        try {
            ScheduleDaoMapper.deleteScheduleSheet(planNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateScheduleSheet(T_Data_Transportation_Schedule_Sheet_Plan scheduleSheetPlan) {
        try {
            ScheduleDaoMapper.updateScheduleSheet(scheduleSheetPlan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过车辆id查询未查看的订单
    @Override
    public List<T_Data_Transportation_Schedule_Sheet_Plan> findUnLookSheetByTruckId(String scheduleTruckId) {
        List<T_Data_Transportation_Schedule_Sheet_Plan> unLookSheet =null;
        try {
            unLookSheet = ScheduleDaoMapper.findUnLookSheetByTruckId(scheduleTruckId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unLookSheet;
    }
    //通过车辆id查询已查看的订单
    @Override
    public List<T_Data_Transportation_Schedule_Sheet_Plan> findLookSheetByTruckId(String scheduleTruckId) {
        List<T_Data_Transportation_Schedule_Sheet_Plan> LookSheet =null;
        try {
            LookSheet = ScheduleDaoMapper.findLookSheetByTruckId(scheduleTruckId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LookSheet;
    }
}