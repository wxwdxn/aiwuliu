/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationScheduleFinanceRecordDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物运输调度单财务流水信息实现
 * 设计文件：
 * 完成日期：2016-07-06
 * 作    者：QJ
 * 内容摘要：货物运输调度单财务流水信息实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Finance_Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TransportationScheduleFinanceRecordDaoMapper
 * 内容摘要: 货物运输调度单财务流水信息实现
 * 方法描述：该类有7个方法：
 *         01 findFinacialFlowById              根据ID查询货物运输调度单财务流水信息
 *         02 findAllFinacialFlow               查询符合条件的货物运输调度单财务流水信息（默认查询所有货物运输调度单财务流水信息）
 *         03 findAllFinacialFlowRowsCount      查询货物运输调度单财务流水信息总记录数
 *         04 saveFinacialFlow                  保存货物运输调度单财务流水信息
 *         05 updateFinacialFlow                更新货物运输调度单财务流水信息
 *         06 delFinacialFlow                   根据ID删除货物运输调度单财务流水信息
 *         07 findFinacialFlowByScheduleId      根据调度单ID查询货物运输调度单财务流水信息
 * @author QJ
 */
public interface TransportationScheduleFinanceRecordDaoMapper {
    // 根据ID查询货物运输调度单财务流水信息
    T_Data_Transportation_Schedule_Finance_Record findFinacialFlowById(@Param(value = "finacial_flow_id") String finacial_flow_id);

    // 查询符合条件的货物运输调度单财务流水信息（默认查询所有货物运输调度单财务流水信息）
    List<T_Data_Transportation_Schedule_Finance_Record> findAllFinacialFlow(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询货物运输调度单财务流水信息总记录数
    Integer findAllFinacialFlowRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存货物运输调度单财务流水信息
    void saveFinacialFlow(T_Data_Transportation_Schedule_Finance_Record finacial_flow);

    // 更新货物运输调度单财务流水信息
    void updateFinacialFlow(T_Data_Transportation_Schedule_Finance_Record finacial_flow);

    // 根据ID删除货物运输调度单财务流水信息
    void delFinacialFlow(@Param(value = "finacial_flow_id") String finacial_flow_id);

    // 根据调度单ID查询货物运输调度单财务流水信息
    List<T_Data_Transportation_Schedule_Finance_Record> findFinacialFlowByScheduleId(@Param("schedule_id") String schedule_id);

    //获取根据调度单id和消费类型查询 消费总额
    int findAmountByScheduleIdAndTypeId(@Param("schedule_id") String schedule_id,@Param(value = "finacial_flow_type") String finacial_flow_type);


}
