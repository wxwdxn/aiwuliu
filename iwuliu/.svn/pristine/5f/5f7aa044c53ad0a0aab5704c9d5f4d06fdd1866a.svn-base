package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Sheet_Plan;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by WXW on 2016/12/14.
 */
@WebService
public interface SchedulePlanService {
    // 添加货物类型
    int  addScheduleSheet(String params,String username);

    //查询未查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findUnLookSheet(String schedule_plan_number);

    //查询已查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findLookSheet(String schedule_plan_number);

    //删除运单
    void deleteScheduleSheet( String planNumber);

    //更新运单
    void  updateScheduleSheet(T_Data_Transportation_Schedule_Sheet_Plan scheduleSheetPlan);

    //通过车辆id查询未查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findUnLookSheetByTruckId(String scheduleTruckId);

    //通过车辆id查询已查看的订单
    List<T_Data_Transportation_Schedule_Sheet_Plan> findLookSheetByTruckId(String scheduleTruckId);


}
