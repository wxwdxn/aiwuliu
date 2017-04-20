package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Vehicle_Model;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by QJ on 2016/01/20.
 */
public interface VehicleModelDaoMapper {
    // 根据条件查询线下加盟服务站列表
    public List<T_Data_Vehicle_Model> findVehicleModelManagerList(HashMap<String, String> conditions);

    // 根据编号查询卡车型号、品牌
    T_Data_Vehicle_Model findVehicleModelByNo(@Param("truck_model_no") String truck_model_no);
}
