/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DrivingRecordService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆驾驶记录信息查询接口声明
 * 设计文件：
 * 完成日期：2016-05-17
 * 作    者：QJ
 * 内容摘要：车辆驾驶记录信息查询
 */

package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Driving_Record;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: DrivingRecordService
 * 内容摘要: 车辆驾驶记录信息查询接口
 * 方法描述：该类有7个方法：
 *         01 findDrivingRecordByID            根据车辆驾驶记录ID查找车辆驾驶记录信息
 *         02 findDrivingRecordByTruckID       根据卡车ID查询车辆驾驶记录信息列
 *         03 findAllDrivingRecord             查询符合条件的车辆驾驶记录列表信息（默认查询所有车辆驾驶记录）
 *         04 findAllDrivingRecordRowsCount    查询车辆驾驶记录记录数(包含有条件和无条件)
 *         05 saveDrivingRecord                增加车辆驾驶记录信息
 *         06 updateDrivingRecord              更新车辆驾驶记录信息
 *         07 delDrivingRecord                 删除车辆驾驶记录信息
 * @author QJ
 */
@WebService
public interface DrivingRecordService {

    // 根据车辆驾驶记录ID查找车辆驾驶记录信息
    public String findDrivingRecordByID(String record_id);

    // 根据卡车ID查询车辆驾驶记录信息列
    public List<T_Data_Driving_Record> findDrivingRecordByTruckID(String truck_id);

    // 查询符合条件的车辆驾驶记录列表信息（默认查询所有车辆驾驶记录）
    public List<T_Data_Driving_Record> findAllDrivingRecord(String search_type, String name, Integer page, Integer rows);

    // 查询车辆驾驶记录记录数(包含有条件和无条件)
    public Integer findAllDrivingRecordRowsCount(String search_type, String name);

    // 增加车辆驾驶记录信息
    public boolean saveDrivingRecord(T_Data_Driving_Record drivingRecord);

    // 更新车辆驾驶记录信息
    public boolean updateDrivingRecord(T_Data_Driving_Record drivingRecord);

    // 删除车辆驾驶记录信息
    public void delDrivingRecord(String record_id);
}

