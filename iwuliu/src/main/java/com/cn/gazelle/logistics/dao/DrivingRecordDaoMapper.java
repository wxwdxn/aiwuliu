/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DrivingRecordDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆驾驶记录信息查询接口实现
 * 设计文件：
 * 完成日期：2016-05-17
 * 作    者：QJ
 * 内容摘要：车辆驾驶记录信息查询
 */

package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Driving_Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: DrivingRecordDaoMapper
 * 内容摘要: 车辆驾驶记录信息查询
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
public interface DrivingRecordDaoMapper {
    // 根据ID查询车辆驾驶记录信息
    public T_Data_Driving_Record findDrivingRecordByID(@Param(value = "record_id") String record_id);

    // 根据卡车ID查询车辆驾驶记录信息
    public List<T_Data_Driving_Record> findDrivingRecordByTruckID(@Param(value = "truck_id") String truck_id);

    // 查询所有车辆驾驶记录信息
    public List<T_Data_Driving_Record> findAllDrivingRecord(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询车辆驾驶记录信息记录数
    public Integer findAllDrivingRecordRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存车辆驾驶记录信息
    public void saveDrivingRecord(T_Data_Driving_Record drivingRecord);

    // 更新车辆驾驶记录信息
    public void updateDrivingRecord(T_Data_Driving_Record drivingRecord);

    // 删除车辆驾驶记录信息
    public void delDrivingRecord(@Param(value = "record_id") String record_id);
}
