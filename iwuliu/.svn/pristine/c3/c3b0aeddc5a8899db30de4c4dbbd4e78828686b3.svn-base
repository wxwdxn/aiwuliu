/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckAlarmDetailDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车警情明细信息实现
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者：QJ
 * 内容摘要：卡车警情明细信息实现
 */
package com.cn.gazelle.logistics.dao;


import com.cn.gazelle.logistics.pojo.T_Data_Truck_Alarm_Detail;
import org.apache.ibatis.annotations.Param;

/**
 * 类 名 称: TruckAlarmDetailDaoMapper
 * 内容摘要: 卡车警情明细信息实现
 * 方法描述：该类有 个方法：
 *         01
 * @author QJ
 */
public interface TruckAlarmDetailDaoMapper {
    // 保存卡车警情明细信息
    public void saveTruckAlarmDetaill(T_Data_Truck_Alarm_Detail truck_alarm_detail);

    // 更新卡车警情明细信息
    public void updateTruckAlarmDetaill(T_Data_Truck_Alarm_Detail truck_alarm_detail);

    // 根据统计信息No删除卡车警情明细信息
    public void delTruckAlarmDetaill(@Param(value = "summary_id") String summary_id);
}
