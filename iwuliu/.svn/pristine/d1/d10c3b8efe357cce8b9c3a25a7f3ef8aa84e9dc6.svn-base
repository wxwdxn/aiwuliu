/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationOrderService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：订单信息管理和接口声明
 * 设计文件：
 * 完成日期：2016-03-07
 * 作    者：WXW
 * 内容摘要：订单信息管理和接口声明
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Plan;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TransportationOrderService
 * 内容摘要: 订单信息管理和接口声明
 * 方法描述：该类有6个方法：
 *         01 findOrderProfitApp                    根据会员名称查询订单收入
 *         02 findAllTransporationOrder             查找所有运输订单信息
 *         03 findTransportationOrderCount          查询运输订单总记录数
 *         04 saveTransportateOrder                 保存运输订单信息
 *         05 updateTransportateOrder               更新运输订单信息
 *         06 delTransportateOrderById              删除运输订单信息
 * @author WXW
 */
@WebService
public interface TransportationOrderService {



    // 添加运输订单
    boolean saveTransportateOrder(T_Data_Transportation_Plan transportation_order);

    // 删除订单
    void delTransportateOrderById(String schedule_plan_number);

    // 更新订单
    boolean updateTransportateOrder(T_Data_Transportation_Plan transportation_order);

    // 根据合同ID和所属计划为空查询
    List<T_Data_Transportation_Plan>  findOrderListByContractIdAndBelongNull( String contract_id );

    //根据合同ID和所属计划不为空查询
    List<T_Data_Transportation_Plan>  findOrderListByBelongCode( String belong_schedule_plan_number );

    // 所属计划为空全部查询
    List<T_Data_Transportation_Plan> findOrderListBelongNull();

    // 根据合同编号 干线 订单状态查询状态所属计划为空全部查询
    List<T_Data_Transportation_Plan> findPlanByConditions(String contract_id,String lineId,String status);

    // 所属计划为空全部查询
    T_Data_Transportation_Plan findBySchedulePlanNumber( String schedule_plan_number );

    // 手动派单列表可用
    List<T_Data_Transportation_Plan> manualOrderList();

    // 手动派单列表已完成
    List<T_Data_Transportation_Plan> manualFinishedOrderList();

}
