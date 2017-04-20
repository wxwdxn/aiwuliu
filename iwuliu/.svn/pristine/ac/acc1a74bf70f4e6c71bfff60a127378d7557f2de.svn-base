/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TransportationScheduleFinanceRecordService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物运输调度单财务流水信息接口声明
 * 设计文件：
 * 完成日期：2016-07-27
 * 作    者: QJ
 * 内容摘要：货物运输调度单财务流水信息接口声明
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Schedule_Finance_Record;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称：TransportationScheduleFinanceRecordService
 * 内容描述：货物运输调度单财务流水信息接口声明
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
@WebService
public interface TransportationScheduleFinanceRecordService {
    // 根据ID查询货物运输调度单财务流水信息
    T_Data_Transportation_Schedule_Finance_Record findFinacialFlowById(String finacial_flow_id);

    // 查询符合条件的货物运输调度单财务流水信息（默认查询所有货物运输调度单财务流水信息）
    List<T_Data_Transportation_Schedule_Finance_Record> findAllFinacialFlow(String search_type, String name, Integer page, Integer rows);

    // 查询货物运输调度单财务流水信息总记录数
    public Integer findAllFinacialFlowRowsCount(String search_type, String name);

    // 保存货物运输调度单财务流水信息
    boolean saveFinacialFlow(T_Data_Transportation_Schedule_Finance_Record finacial_flow);

    // 更新货物运输调度单财务流水信息
    boolean updateFinacialFlow(T_Data_Transportation_Schedule_Finance_Record finacial_flow);

    // 根据ID删除货物运输调度单财务流水信息
    void delFinacialFlow(String finacial_flow_id);

    // 根据调度单ID查询货物运输调度单财务流水信息
    List<T_Data_Transportation_Schedule_Finance_Record> findFinacialFlowByScheduleId(String schedule_id);
}
