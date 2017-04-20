/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OBDEquipmentTruckBindingService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-18
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment_Truck_Binding;
import com.cn.gazelle.logistics.pojo.T_Data_OBD_Truck_Binding_Info;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：OBDEquipmentTruckBindingService
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@WebService
public interface OBDEquipmentTruckBindingService {

    //根据绑定信息标号查询OBD设备卡车绑定信息
    T_Data_OBD_Equipment_Truck_Binding findByBindingNumber(String binding_number);

    //根据设备id查询OBD设备卡车绑定信息
    List<T_Data_OBD_Equipment_Truck_Binding> findByEquipmentId(String equipment_id);

    // 添加OBD设备卡车绑定信息
    int saveOBDTruckBindingInfo(T_Data_OBD_Equipment_Truck_Binding equipmentTruckBinding);

    // 更新OBD设备卡车绑定信息
    boolean updateOBDTruckBindingInfo(T_Data_OBD_Equipment_Truck_Binding equipmentTruckBinding);

    // 删除OBD设备卡车绑定信息
    void delOBDTruckBindingInfo(String binding_number);

    //查询所有的不分页
    List<T_Data_OBD_Equipment_Truck_Binding> findAllOBDTruckBindingList();

    // 查询运维OBD设备管理列表
    List<T_Data_OBD_Truck_Binding_Info> findOBDTruckBindingInfoList(HashMap<String,String> conditions);

    // 通过卡车id查找已绑定的卡车绑定信息
    T_Data_OBD_Equipment_Truck_Binding findOBDTruckBindingInfoByTruckID(String truck_id);

}
