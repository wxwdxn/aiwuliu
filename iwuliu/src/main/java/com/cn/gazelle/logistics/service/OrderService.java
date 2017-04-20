/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OrderService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-15
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_Order;

import javax.jws.WebService;

/**
 * 类 名 称：OrderService
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01 saveOrder                       // 保存信息
 * 02 updateOrder                     // 更新信息
 * 03 delOrder                        // 删除信息
 *
 * @authot YK
 */
@WebService
public interface OrderService {
    // 保存信息
    boolean saveOrder(T_Sys_Order order);

    // 更新信息
    boolean updateOrder(T_Sys_Order order);

    // 删除信息
    void delOrder(String order_id);

}
