package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Model;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TruckModelDaoMapper
 * 内容摘要: 卡车型号的接口
 * 方法描述：该类有2个方法：
 *         01 findTruckModelByNo                            根据编号查询卡车型号
 *         02 findTruckModelLists                           查询所有的卡车型号列表不分页用于下拉的回显操作
 *         03 findTruckModelByBrandId                       根据车辆品牌ID查询车辆型号列表
 *  @author QJ
 */
public interface TruckModelDaoMapper {
    // 根据编号查询卡车型号
    T_Data_Truck_Model findTruckModelByNo(@Param(value = "truck_model_no") String truck_model_no);

    // 查询所有的卡车型号列表不分页用于下拉的回显操作
    List<T_Data_Truck_Model> findTruckModelLists();

    // 根据车辆品牌ID查询车辆型号列表
    List<T_Data_Truck_Model> findTruckModelByBrandId(@Param(value = "truck_brand_id") String truck_brand_id);

    // 保存卡车型号
    int saveTruckModel(T_Data_Truck_Model truckModel);

    // 更新卡车型号
    int updateTruckModel(T_Data_Truck_Model truckModel);

    // 删除卡车型号信息
    void delTruckModel(@Param(value = "truck_model_no") String truck_model_no);
}
