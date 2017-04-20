package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Sheet_Plan;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by WXW on 2016/12/14.
 * 货物运输计划调度单信息表(算法调试用)
 */
public interface SchedulePlanDaoMapper {

    // 添加货物类型
    void  addScheduleSheet(T_Data_Transportation_Schedule_Sheet_Plan scheduleSheet);

    //查询未查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findUnLookSheet(@RequestParam(value ="schedule_plan_number" )String schedule_plan_number);

    //查询已查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findLookSheet(@RequestParam(value ="schedule_plan_number" )String schedule_plan_number);

    //删除运单
    void deleteScheduleSheet(@RequestParam(value ="planNumber" ) String planNumber);

    //更新运单
    void  updateScheduleSheet(T_Data_Transportation_Schedule_Sheet_Plan scheduleSheet);

    //查询未查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findUnLookSheetByTruckId(@RequestParam(value ="scheduleTruckId" )String scheduleTruckId);

    //查询已查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findLookSheetByTruckId(@RequestParam(value ="scheduleTruckId" )String scheduleTruckId);

    //查询已查看状态为1的订单
    T_Data_Transportation_Schedule_Sheet_Plan findLookSheetByTruckIdAndStatus(@RequestParam(value ="scheduleTruckId" )String scheduleTruckId);


}
