/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckObdOriginalDataDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：OBD信息原始数据总表实现
 * 设计文件：
 * 完成日期：2016-07-20
 * 作    者：QJ
 * 内容摘要：OBD信息原始数据总表实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Obd_Original_Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TruckObdOriginalDataDaoMapper
 * 内容摘要: OBD信息原始数据总表实现
 * 方法描述：该类有8个方法：
 *         01 findTruckObdOriginalDataByNumber          根据数据No查询OBD信息原始数据总表信息
 *         02 findAllTruckObdOriginalData               查询符合条件的OBD信息原始数据总表信息（默认查询OBD信息原始数据总表信息）
 *         03 findAllTruckObdOriginalDataRowsCount      查询OBD信息原始数据总表信息总记录数
 *         04 saveTruckObdOriginalData                  保存OBD信息原始数据总表信息
 *         05 updateTruckObdOriginalData                更新OBD信息原始数据总表信息
 *         06 delTruckObdOriginalData                   根据数据No删除OBD信息原始数据总表信息
 *         07 findTruckObdOriginalDataList              查询所有OBD信息原始数据总表不分页
 *         08 findTruckObdOriginalDataBySaveContent     根据接收内容查询OBD信息原始数据总表信息
 * @author QJ
 */
public interface TruckObdOriginalDataDaoMapper {
    // 根据数据No查询OBD信息原始数据总表信息
    public T_Data_Truck_Obd_Original_Data findTruckObdOriginalDataByNumber(@Param(value = "data_number") long data_number);

    // 查询符合条件的OBD信息原始数据总表信息（默认查询OBD信息原始数据总表信息）
    public List<T_Data_Truck_Obd_Original_Data> findAllTruckObdOriginalData(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询OBD信息原始数据总表信息总记录数
    public Integer findAllTruckObdOriginalDataRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存OBD信息原始数据总表信息
    public void  saveTruckObdOriginalData(T_Data_Truck_Obd_Original_Data truck_obd_original_data);

    // 更新OBD信息原始数据总表信息
    public void  updateTruckObdOriginalData(T_Data_Truck_Obd_Original_Data truck_obd_original_data);

    // 根据数据No删除OBD信息原始数据总表信息
    public void  delTruckObdOriginalData(@Param(value = "data_number") long data_number);
    
    // 查询所有OBD信息原始数据总表不分页
    public List<T_Data_Truck_Obd_Original_Data> findTruckObdOriginalDataList();

    // 根据接收内容查询OBD信息原始数据总表信息
    public T_Data_Truck_Obd_Original_Data findTruckObdOriginalDataBySaveContent(@Param(value = "save_content") String save_content);
}