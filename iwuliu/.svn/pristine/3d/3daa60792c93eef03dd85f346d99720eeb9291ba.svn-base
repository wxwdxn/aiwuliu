/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OBDEquipmentTruckBindingDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-18
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment_Truck_Binding;
import com.cn.gazelle.logistics.pojo.T_Data_OBD_Truck_Binding_Info;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：OBDEquipmentTruckBindingDaoMapper
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
public interface OBDEquipmentTruckBindingDaoMapper {

    //根据绑定信息标号查询OBD设备卡车绑定信息
    T_Data_OBD_Equipment_Truck_Binding findByBindingNumber(@Param(value = "binding_number")String binding_number);

    //根据设备id查询OBD设备卡车绑定信息
    List<T_Data_OBD_Equipment_Truck_Binding> findByEquipmentId(@Param(value = "equipment_id")String equipment_id);

    // 添加OBD设备卡车绑定信息
    int saveOBDTruckBindingInfo(T_Data_OBD_Equipment_Truck_Binding equipmentTruckBinding);

    // 更新OBD设备卡车绑定信息
    void updateOBDTruckBindingInfo(T_Data_OBD_Equipment_Truck_Binding equipmentTruckBinding);

    // 删除OBD设备卡车绑定信息
    void delOBDTruckBindingInfo(@Param(value = "binding_number") String binding_number);

    //查询所有的不分页
    List<T_Data_OBD_Equipment_Truck_Binding> findAllOBDTruckBindingList();

    // 查询运维OBD设备管理列表
    List<T_Data_OBD_Truck_Binding_Info> findOBDTruckBindingInfoList(HashMap<String,String> conditions);

    // 通过卡车id查找已绑定的设备
    T_Data_OBD_Equipment_Truck_Binding findOBDTruckBindingInfoByTruckID(@Param(value = "truck_id")String truck_id);

}
