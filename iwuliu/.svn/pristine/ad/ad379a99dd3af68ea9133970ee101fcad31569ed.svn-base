/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: WorkConditionDetailDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车工况明细信息实现
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者：QJ
 * 内容摘要：卡车工况明细信息实现
 */
package com.cn.gazelle.logistics.dao;


import com.cn.gazelle.logistics.pojo.T_Data_Work_Condition_Detail;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * 类 名 称: WorkConditionDetailDaoMapper
 * 内容摘要: 卡车工况明细信息实现
 * 方法描述：该类有 个方法：
 *         01
 * @author QJ
 */
public interface WorkConditionDetailDaoMapper {
    // 保存卡车工况明细信息
    void saveWorkConditionDetail(T_Data_Work_Condition_Detail work_condition_detail);

    // 更新卡车工况明细信息
    void updateWorkConditionDetail(T_Data_Work_Condition_Detail work_condition_detail);

    // 根据工况No删除卡车工况明细信息
    void delWorkConditionDetail(@Param(value = "work_condition_number") String work_condition_number);

    // 查询卡车工况详细信息（驾驶行为分析使用）
    HashMap<String,String> queryWorkConditionDetailOfDriving(@Param(value = "obd_id") String obd_id,
                                                             @Param(value = "start_time") String start_time,
                                                             @Param(value = "end_time") String end_time);
    // 查询卡车工况次数
    int queryWorkConditionDetailCount(@Param(value = "work_condition_type")String work_condition_type,
                                      @Param(value = "obd_id") String obd_id,
                                      @Param(value = "start_time") String start_time,
                                      @Param(value = "end_time") String end_time);

    // 查询卡车工况时长
    int queryWorkConditionDetailSum(@Param(value = "work_condition_type")String work_condition_type,
                                    @Param(value = "obd_id") String obd_id,
                                    @Param(value = "start_time") String start_time,
                                    @Param(value = "end_time") String end_time);

}
