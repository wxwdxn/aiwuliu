/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckGsensorDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车重力加速信息实现
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者：QJ
 * 内容摘要：卡车重力加速信息实现
 */
package com.cn.gazelle.logistics.dao;


import com.cn.gazelle.logistics.pojo.T_Data_Truck_Gsensor;
import org.apache.ibatis.annotations.Param;

/**
 * 类 名 称: TruckGsensorDaoMapper
 * 内容摘要: 卡车重力加速信息实现
 * 方法描述：该类有 个方法：
 *         01
 * @author QJ
 */
public interface TruckGsensorDaoMapper {
    // 保存卡车重力加速信息
    public void saveTruckGsensor(T_Data_Truck_Gsensor truck_gsensor);

    // 更新卡车重力加速信息
    public void updateTruckGsensor(T_Data_Truck_Gsensor truck_gsensor);

    // 根据重力加速度No删除卡车重力加速信息
    public void delTruckGsensor(@Param(value = "gsensor_number") String gsensor_number);
}
