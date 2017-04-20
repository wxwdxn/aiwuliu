/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckFaultDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车故障信息实现
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者：QJ
 * 内容摘要：卡车故障信息实现
 */
package com.cn.gazelle.logistics.dao;


import com.cn.gazelle.logistics.pojo.T_Data_Truck_Fault;
import org.apache.ibatis.annotations.Param;

/**
 * 类 名 称: TruckFaultDaoMapper
 * 内容摘要: 卡车故障信息实现
 * 方法描述：该类有 个方法：
 *         01
 * @author QJ
 */
public interface TruckFaultDaoMapper {
    // 保存卡车故障信息
    public void saveTruckFault(T_Data_Truck_Fault truck_fault);

    // 更新卡车故障信息
    public void updateTruckFault(T_Data_Truck_Fault truck_fault);

    // 根据故障No删除卡车故障信息
    public void delTruckFault(@Param(value = "fault_number") String fault_number);
}
