/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：WorkConditionDetailService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-06
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Work_Condition_Detail;

import javax.jws.WebService;
import java.util.HashMap;

/**
 * 类 名 称：WorkConditionDetailService
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01 saveWorkConditionDetail                      保存卡车工况明细信息
 *
 * @authot YK
 */
@WebService
public interface WorkConditionDetailService {

    // 保存卡车工况明细信息
    boolean saveWorkConditionDetail(T_Data_Work_Condition_Detail work_condition_detail);

    // 更新卡车工况明细信息
    boolean updateWorkConditionDetail(T_Data_Work_Condition_Detail work_condition_detail);

    // 根据工况No删除卡车工况明细信息
    boolean delWorkConditionDetail(String work_condition_number);

    // 查询卡车工况详细信息（驾驶行为分析使用）
    HashMap<String, String> queryWorkConditionDetailOfDriving(String obd_id, String start_time, String end_time);


}
