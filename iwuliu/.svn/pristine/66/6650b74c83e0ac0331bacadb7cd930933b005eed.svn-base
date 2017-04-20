package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment;

import javax.jws.WebService;
import java.text.ParseException;
import java.util.List;

/**
 * Created by WXW on 2016/8/22.
 */
@WebService
public interface OBDequipmentService {

    //根据设备id查询设备
    T_Data_OBD_Equipment findById(String equipment_id);

    //根据obdId查询设备
    T_Data_OBD_Equipment findByObdId(String obd_id);

    // 添加obd设备信息
    int saveOBDEquipment(T_Data_OBD_Equipment equipment);

    // 更新obd设备信息
    boolean updateOBDInfo(T_Data_OBD_Equipment equipment);

    // 删除obd设备信息
    void OBDInfoDel( String equipment_id);

    //查询所有的不分页
    List<T_Data_OBD_Equipment>findAllOBDList();

    // 新增设备信息
    int saveEquipmentInfo(String info,String userName);

    //  web页面删除obd设备车辆信息
    int delEquipmentInfo(String binding_number);

    // web页面编辑obd信息
    int editEquipmentInfo(String binding_number,String info,String username);

}
