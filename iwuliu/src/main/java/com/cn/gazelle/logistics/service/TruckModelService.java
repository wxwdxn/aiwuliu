/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckModelService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆型号查询接口声明
 * 设计文件：
 * 完成日期：2016-11-06
 * 作    者：QJ
 * 内容摘要：车辆型号查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Model;
import com.cn.gazelle.logistics.pojo.T_Data_Vehicle_Model;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: TruckModelService
 * 内容摘要: 车辆型号查询接口
 * 方法描述：该类有2个方法：
 *         01 findTruckModelByNo                根据车辆型号编号查找车辆型号信息
 *         02 findTruckModelLists               查询所有的卡车型号列表不分页用于下拉的回显操作
 *         03 findTruckModelByBrandId           根据车辆品牌ID查询车辆型号列表
 * @author QJ
 */
@WebService
public interface TruckModelService {
    // 根据编号查询卡车型号
    T_Data_Truck_Model findTruckModelByNo(String truck_model_no);

    // 查询所有的卡车型号列表不分页用于下拉的回显操作
    List<T_Data_Truck_Model> findTruckModelLists();

    // 根据车辆品牌ID查询车辆型号列表
    List<T_Data_Truck_Model> findTruckModelByBrandId(String truck_brand_id);

    // 根据条件查询车辆型号列表
    List<T_Data_Vehicle_Model> findVehicleModelManagerList(HashMap<String, String> conditions);

    // 新增车辆型号
    int saveVehicleModel(String list, String userName);

    // 编辑车辆型号
    int upDateVehicleModel(String list, String userName);

    // 删除车辆型号信息
    void delTruckModel(String truck_model_no);

    // 根据编号查询卡车型号、品牌
    T_Data_Vehicle_Model findVehicleModelByNo(String truck_model_no);
}
