/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ScheduleSheetDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：调度单信息管理和app接口
 * 设计文件：
 * 完成日期：2016-03-10
 * 作    者：WXW
 * 内容摘要：调度单信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_SchedingSheet;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Sheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: ScheduleSheetDaoMapper
 * 内容摘要: 调度单信息管理和app接口的实现
 * 方法描述：该类有20个方法：
 *         01 findSheetByCode                       根据编号查调度单信息
 *         02 oneMonthRecord                         查询最近一个月的调度单
 *         03 oneWeekRecord                          查询最近一个周的调度单
 *         05 findMinStatusByOrderID                 根据订单id查询最小的状态值
 *         06 saveScheduleSheet                     保存调度单信息
 *         07 updateScheduleStatus                   更新调度单状态
 *         08 delScheduleSheet                       删除调度单信息
 *         09findScheduleFinishedByNameAndMemIdCount 查询已完成订单的个数
 *         10 findScheduleFinishedByTruckIdAndStatusCount 根据车辆id查询未完成订单的个数
 *         12 findScheduleSheetByStatusAndMemId       根据订单状态查询
 *         13 findScheduleSheetById                   根据调度单id查询
 *         14 findAllScheduleSheet                    查询所有调度单信息分页
 *         15 findAllScheduleSheetRowsCount           查询所有调度单信息总记录数
 *         16 findAllLoadingWeights                   根据从属id查询装货量
 *         17 findAllUnLoadingWeights                 根据从属id查询卸货量
 *         18 findScheduleSheetByScheduleTruckID      根据调度车辆ID查询调度单信息表
 *         20 findScheduleFinishedByTruckIdAndStatus  根据车辆id查询未完成订单信息
 * @author WXW
 */
public interface ScheduleSheetDaoMapper {

    // 根据id查询调度单信息
    T_Data_Transportation_Schedule_Sheet findScheduleSheetById(@Param("scheduleSheetId") String scheduleSheetId);


    // 保存调度单信息
    void saveScheduleSheet(T_Data_Transportation_Schedule_Sheet scheduleSheet);

    // 更新调度单信息
    void updateScheduleSheet(T_Data_Transportation_Schedule_Sheet ScheduleSheet);

    // 删除调度单信息
    void delScheduleSheet(@Param("scheduleSheetId") String scheduleSheetId);


    // 根据调度车辆ID查询调度单信息表--QJ
    List<T_Data_Transportation_Schedule_Sheet> findScheduleSheetByScheduleTruckID(@Param(value = "scheduleTruckId") String scheduleTruckId);



    // 根据调度车辆ID查询未完成的调度单信息表
    List<T_Data_Transportation_Schedule_Sheet> findUnFinishSheetByTruckID(@Param(value = "scheduleTruckId") String scheduleTruckId);



    // 根据车辆id查询未完成订单的个数
    Integer findScheduleUnFinishedByTruckIdAndStatusCount(@Param(value = "scheduleTruckId") String scheduleTruckId);


    // 根据车辆id查询最小的状态值
    String findMinStatusByTruckId(@Param(value = "scheduleTruckId") String scheduleTruckId);

    // 根据查询年的调度单
    List<T_Data_Transportation_Schedule_Sheet> yearFinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId,@Param(value = "time") String time);

    // 根据查询月的调度单
    List<T_Data_Transportation_Schedule_Sheet> monthFinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId,@Param(value = "time") String time);

    // 根据查询最近一周的调度单
    List<T_Data_Transportation_Schedule_Sheet> oneWeekFinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId);

    // 根据查询全部的调度单
    List<T_Data_Transportation_Schedule_Sheet> allFinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId);

    // 根据查询年的调度单
    List<T_Data_Transportation_Schedule_Sheet> yearUnfinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId,@Param(value = "time") String time);

    // 根据查询月的调度单
    List<T_Data_Transportation_Schedule_Sheet> monthUnfinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId,@Param(value = "time") String time);

    // 根据查询最近一周的调度单
    List<T_Data_Transportation_Schedule_Sheet> oneWeekUnfinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId);

    // 根据查询全部的调度单
    List<T_Data_Transportation_Schedule_Sheet> allUnfinshedRecord(@Param(value = "scheduleTruckId") String scheduleTruckId);



    // 根据多个条件条件查找调度单信息 拼接
    List<T_Data_SchedingSheet> findScheduleByConditions(
                                                        @Param(value = "plateNumber") String plateNumber,
                                                        @Param(value = "planNumber") String planNumber,
                                                        @Param(value = "lineName") String lineName,
                                                        @Param(value = "modelName") String modelName,
                                                        @Param(value = "receveEnd") String receveEnd,
                                                        @Param(value = "receveStart") String receveStart,
                                                        @Param(value = "person") String person,
                                                        @Param(value = "personPhone") String personPhone);


    // 司机根据查询年的调度单 app端
    List<T_Data_Transportation_Schedule_Sheet> yearFinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember,@Param(value = "time") String time);

    // 司机根据查询月的调度单
    List<T_Data_Transportation_Schedule_Sheet> monthFinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember,@Param(value = "time") String time);

    // 司机根据查询最近一周的调度单
    List<T_Data_Transportation_Schedule_Sheet> oneWeekFinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember);

    // 司机根据查询全部的调度单
    List<T_Data_Transportation_Schedule_Sheet> allFinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember);

    // 司机根据查询年的调度单
    List<T_Data_Transportation_Schedule_Sheet> yearUnfinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember,@Param(value = "time") String time);

    // 司机根据查询月的调度单
    List<T_Data_Transportation_Schedule_Sheet> monthUnfinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember,@Param(value = "time") String time);

    // 司机根据查询最近一周的调度单
    List<T_Data_Transportation_Schedule_Sheet> oneWeekUnfinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember);

    // 司机根据查询全部的调度单
    List<T_Data_Transportation_Schedule_Sheet> allUnfinshedRecordByReceviceMember(@Param(value = "receviceMember") String receviceMember);

}


