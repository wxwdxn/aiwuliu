/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TruckObdOriginalDataService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：OBD信息原始数据总表接口声明
 * 设计文件：
 * 完成日期：2016-07-21
 * 作    者: QJ
 * 内容摘要：OBD信息原始数据总表
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Obd_Original_Data;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TruckObdOriginalDataDaoMapper
 * 内容摘要: OBD信息原始数据总表实现
 * 方法描述：该类有7个方法：
 *         01 findTruckObdOriginalDataByNumber          根据数据No查询OBD信息原始数据总表信息
 *         02 findAllTruckObdOriginalData               查询符合条件的OBD信息原始数据总表信息（默认查询OBD信息原始数据总表信息）
 *         03 findAllTruckObdOriginalDataRowsCount      查询OBD信息原始数据总表信息总记录数
 *         04 saveTruckObdOriginalData                  保存OBD信息原始数据总表信息
 *         05 updateTruckObdOriginalData                更新OBD信息原始数据总表信息
 *         06 delTruckObdOriginalData                   根据数据No删除OBD信息原始数据总表信息
 *         07 findTruckObdOriginalDataBySaveContent     根据接收内容查询OBD信息原始数据总表信息
 * @author QJ
 */
@WebService
public interface TruckObdOriginalDataService {
    // 根据数据No查询OBD信息原始数据总表信息
    T_Data_Truck_Obd_Original_Data findTruckObdOriginalDataByNumber(long data_number);

    // 查询符合条件的OBD信息原始数据总表信息（默认查询OBD信息原始数据总表信息）
    List<T_Data_Truck_Obd_Original_Data> findAllTruckObdOriginalData(String search_type, String name, Integer page, Integer rows);

    // 查询OBD信息原始数据总表信息总记录数
    public Integer findAllTruckObdOriginalDataRowsCount(String search_type, String name);

    // 保存OBD信息原始数据总表信息
    boolean saveTruckObdOriginalData(T_Data_Truck_Obd_Original_Data truck_obd_original_data);

    // 更新OBD信息原始数据总表信息
    boolean updateTruckObdOriginalData(T_Data_Truck_Obd_Original_Data truck_obd_original_data);

    // 根据数据No删除OBD信息原始数据总表信息
    void delTruckObdOriginalData(long data_number);

    // 根据接收内容查询OBD信息原始数据总表信息
    T_Data_Truck_Obd_Original_Data findTruckObdOriginalDataBySaveContent(String save_content);
}
