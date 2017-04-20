/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckAlarmSummaryDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车警情统计信息实现
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者：QJ
 * 内容摘要：卡车警情统计信息实现
 */
package com.cn.gazelle.logistics.dao;


import com.cn.gazelle.logistics.pojo.T_Data_Truck_Alarm_Summary;
import org.apache.ibatis.annotations.Param;

/**
 * 类 名 称: TruckAlarmSummaryDaoMapper
 * 内容摘要: 卡车警情统计信息实现
 * 方法描述：该类有 个方法：
 *         01
 * @author QJ
 */
public interface TruckAlarmSummaryDaoMapper {
    // 保存卡车警情统计信息
    public void saveTruckAlarmSummary(T_Data_Truck_Alarm_Summary truck_alarm_summary);

    // 更新卡车警情统计信息
    public void updateTruckAlarmSummary(T_Data_Truck_Alarm_Summary truck_alarm_summary);

    // 根据统计信息No删除卡车警情统计信息
    public void delTruckAlarmSummary(@Param(value = "SUMMARY_NUMBER") String summary_number);
}
