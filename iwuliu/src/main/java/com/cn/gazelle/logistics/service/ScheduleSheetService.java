/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ScheduleSheetService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：调度单的管理和app接口的定义
 * 设计文件：
 * 完成日期：2016-03-10
 * 作    者：WXW
 * 内容摘要：调度单的管理和app接口的定义
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_SchedingSheet;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Sheet;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: ScheduleSheetService
 * 内容摘要: 调度单的管理和app接口的定义
 * 方法描述：该类有11个方法：
 *         01 scheduleSheetDistribute                  调度单的派发
 *         02 updateScheduleStatus                     更新状态
 *         03 findScheduleSheetAppByStatus             根据会员名和调度单状态查询
 *         04 findScheduleSheetAppById                 根据id查询app相关信息
 *         05 findScheduleSheetById                    根据id查询调度单信息
 *         06 findAllScheduleSheet                     查询所有调度单信息（分页）
 *         07 findAllScheduleSheetRowsCount            查询调度单总记录数
 *         08 saveScheduleSheet                        保存调度单信息
 *         09 updateScheduleSheet                      更新调度单信息
 *         10 findSheetList                            获取所有的调度单
 *         11 delScheduleSheet                         删除调度单信息
 * @author WXW
 */
@WebService
public interface ScheduleSheetService {

    // 保存调度单信息
    boolean saveScheduleSheet(T_Data_Transportation_Schedule_Sheet scheduleSheet);

    // 更新调度单信息
    boolean updateScheduleSheet(T_Data_Transportation_Schedule_Sheet scheduleSheet);

    // 删除调度单信息
    void delScheduleSheet(String scheduleSheetId);

    // 查询所有调度单信息
    String findFinishedScheduleSheetByTruckId(String member_name,String personType,String timeType, String time, String status);

    String findFinishScheduleSheetDetails(String member_name, String dispatchId);

    // 根据多个条件条件查找调度单信息 拼接
    List<T_Data_SchedingSheet> findScheduleByConditions(String plateNumber, String planNumber,String lineName,String modelName,
    String receveEnd, String receveStart,String person ,String personPhone);

    //根据调度单id货物信息
    T_Data_Transportation_Schedule_Sheet findScheduleSheetById(String scheduleSheetId);

    // 根据车辆id查询最小的状态值
    String findMinStatusByTruckId(String scheduleTruckId);

}
