/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TruckObdOriginalDataDailyService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：OBD信息原始数据日表接口声明
 * 设计文件：
 * 完成日期：2016-07-22
 * 作    者: QJ
 * 内容摘要：OBD信息原始数据日表
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Obd_Original_Data_Daily;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TruckObdOriginalDataDailyDaoMapper
 * 内容摘要: OBD信息原始数据日表实现
 * 方法描述：该类有7个方法：
 *         01 findTruckObdOriginalDataDailyByNumber          根据数据No查询OBD信息原始数据日表信息
 *         02 findAllTruckObdOriginalDataDaily               查询符合条件的OBD信息原始数据日表信息（默认查询OBD信息原始数据日表信息）
 *         03 findAllTruckObdOriginalDataDailyRowsCount      查询OBD信息原始数据日表信息总记录数
 *         04 saveTruckObdOriginalDataDaily                  保存OBD信息原始数据日表信息
 *         05 updateTruckObdOriginalDataDaily                更新OBD信息原始数据日表信息
 *         06 delTruckObdOriginalDataDaily                   根据数据No删除OBD信息原始数据日表信息
 *         07 findTruckObdOriginalDataDailyBySaveContent     根据接收内容查询OBD信息原始数据日表信息
 * @author QJ
 */
@WebService
public interface TruckObdOriginalDataDailyService {
    // 根据数据No查询OBD信息原始数据日表信息
    T_Data_Truck_Obd_Original_Data_Daily findTruckObdOriginalDataDailyByNumber(long data_number);

    // 查询符合条件的OBD信息原始数据日表信息（默认查询OBD信息原始数据日表信息）
    List<T_Data_Truck_Obd_Original_Data_Daily> findAllTruckObdOriginalDataDaily(String search_type, String name, Integer page, Integer rows);

    // 查询OBD信息原始数据日表信息总记录数
    public Integer findAllTruckObdOriginalDataDailyRowsCount(String search_type, String name);

    // 保存OBD信息原始数据日表信息
    boolean saveTruckObdOriginalDataDaily(T_Data_Truck_Obd_Original_Data_Daily truck_obd_original_data);

    // 更新OBD信息原始数据日表信息
    boolean updateTruckObdOriginalDataDaily(T_Data_Truck_Obd_Original_Data_Daily truck_obd_original_data);

    // 根据数据No删除OBD信息原始数据日表信息
    void delTruckObdOriginalDataDaily(long data_number);

    // 根据接收内容查询OBD信息原始数据日表信息
    T_Data_Truck_Obd_Original_Data_Daily findTruckObdOriginalDataDailyBySaveContent(String save_content);
}
