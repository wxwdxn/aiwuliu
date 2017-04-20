/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: WorkConditionSummaryDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车工况统计信息实现
 * 设计文件：
 * 完成日期：2016-07-29
 * 作    者：QJ
 * 内容摘要：卡车工况统计信息实现
 */
package com.cn.gazelle.logistics.dao;


import com.cn.gazelle.logistics.pojo.T_Data_Work_Condition_Summary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: WorkConditionSummaryDaoMapper
 * 内容摘要: 卡车工况统计信息实现
 * 方法描述：该类有 个方法：
 *         01
 * @author QJ
 */
public interface WorkConditionSummaryDaoMapper {
    // 保存卡车工况统计信息
    public void saveWorkConditionSummary(T_Data_Work_Condition_Summary work_condition_summary);

    // 更新卡车工况统计信息
    public void updateWorkConditionSummary(T_Data_Work_Condition_Summary work_condition_summary);

    // 根据统计No删除卡车工况统计信息
    public void delWorkConditionSummary(@Param(value = "summary_number") String summary_number);
}
