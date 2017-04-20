/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OrderDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-15
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称：OrderDaoMapper
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
public interface ProtocolOrderDaoMapper {
    // 根据ID查找通讯指令信息
    T_Sys_Order findOrderByID(@Param("order_id") String order_id);

    // 根据通讯指令名查找信息
    List<T_Sys_Order> findOrderByProtocolType(@Param("protocol_type") String protocol_type);

    // 查找所有通讯指令信息
    List<T_Sys_Order> findAllOrder(@Param("protocol_type") String protocol_type, @Param("page") Integer page, @Param("rows") Integer rows);
    
    // 查找符合条件的通讯指令总记录数
    Integer findAllOrderSearchCount(@Param("protocol_type") String protocol_type);

    // 保存通讯指令信息
    void saveOrder(T_Sys_Order order);

    // 更新通讯指令信息
    void updateOrder(T_Sys_Order order);

    // 删除通讯指令信息
    void delOrder(String order_id);
}
