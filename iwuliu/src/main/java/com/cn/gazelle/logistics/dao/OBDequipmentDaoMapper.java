package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by YK on 2016/11/18.
 */
public interface OBDequipmentDaoMapper {
    //根据设备id查询设备
    T_Data_OBD_Equipment findById(@Param(value = "equipment_id")String equipment_id);

    //根据OBD ID查询设备
    T_Data_OBD_Equipment findByObdId(@Param(value = "obd_id")String obd_id);

    // 添加obd设备信息
    int saveOBDEquipment(T_Data_OBD_Equipment equipment);

    // 更新obd设备信息
    void updateOBDInfo(T_Data_OBD_Equipment equipment);

    // 删除obd设备信息
    void OBDInfoDel(@Param(value = "equipment_id") String equipment_id);

    //查询所有的不分页
    List<T_Data_OBD_Equipment>findAllOBDList();

}
