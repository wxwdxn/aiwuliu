/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationOrderDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运输订单信息管理实现
 * 设计文件：
 * 完成日期：2016-03-07
 * 作    者：WXW
 * 内容摘要：运输订单信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_OrderList;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Plan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TransportationOrderDaoMapper
 * 内容摘要: 运输订单信息管理
 * 方法描述：该类有8个方法：
 *         01 findOrderById                  根据ID查运输订单信息
 *         02 findOrderByCode                根据运输订单编号查找运输订单信息
 *         03 findOrderList                  查找所有运输订单信息（不分页）
 *         04 findAllTransporationOrder      查找所有运输订单信息（分页）
 *         05 findTransportationOrderCount   查询运输订单总记录数
 *         06 saveTransportateOrder          保存运输订单信息
 *         07 updateTransportateOrder        更新运输订单信息
 *         08 delTransportateOrderById        删除运输订单信息
 *         10 updateOrderStatus               更新订单的状态
 * @author WXW
 */
public interface TransportationOrderDaoMapper {


    // 根据ID查询 SCHEDULE_PLAN_NUMBER as schedule_plan_number,
    T_Data_Transportation_Plan findOrderById(@Param(value = "schedule_plan_number") String schedule_plan_number);

    // 添加运输订单
    void saveTransportateOrder(T_Data_Transportation_Plan transportation_order);

    // 删除订单
    void delTransportateOrderById(@Param(value = "schedule_plan_number") String schedule_plan_number);

    // 更新订单
    void updateTransportateOrder(T_Data_Transportation_Plan transportation_order);

    // 根据合同ID查询2222222222222222222
    List findOrderByLineIdAndConId2(@Param(value = "operate_main_line_id") String operate_main_line_id,
                                   @Param(value = "contract_id") String contract_id,
                                   @Param(value = "lat") double lat,
                                   @Param(value = "lng") double lng);

    // 根据合同ID和所属计划为空查询
    List findOrderListByContractIdAndBelongNull(@Param(value = "contract_id") String contract_id );

    //根据所属计划不为空查询
    List <T_Data_Transportation_Plan>findOrderListByBelongCode(@Param(value = "belong_schedule_plan_number") String belong_schedule_plan_number );


    // 所属计划为空全部查询
    List<T_Data_Transportation_Plan> findOrderListBelongNull();


    // 所属计划为空全部查询
    T_Data_Transportation_Plan findBySchedulePlanNumber(@Param(value = "schedule_plan_number") String schedule_plan_number );

    // 根据合同编号 干线 订单状态查询状态所属计划为空全部查询
    List<T_Data_Transportation_Plan> findPlanByConditions(@Param(value = "contract_id")String contractId,@Param(value = "operate_main_line_id")String lineId,@Param(value = "status")String status);



    // 根据合同Id获取一级运单状态最小值
    String findMinStatusByContractId(@Param(value = "contract_id") String contract_id);

    //根据从属计划编号获取二级运单状态最小值
    String findMinStatusByBelongSchedulePlanNumber(@Param(value = "belong_schedule_plan_number") String belong_schedule_plan_number);


    // 手动派单列表为完成
    List<T_Data_Transportation_Plan> manualOrderList();

    // 手动派单列表完成
    List<T_Data_Transportation_Plan> manualFinishedOrderList();

    // 根据手动派单的二级计划编号查询 获取手动派单的信息
    T_Data_Transportation_OrderList findOrderByPlanAndConId(@Param(value = "schedule_plan_number") String schedule_plan_number,
                                                            @Param(value = "contract_id") String contract_id);



}

