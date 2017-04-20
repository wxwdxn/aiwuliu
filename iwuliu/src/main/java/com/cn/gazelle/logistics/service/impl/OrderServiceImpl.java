/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OrderServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-15
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.ProtocolOrderDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Order;
import com.cn.gazelle.logistics.service.OrderService;
import com.cn.gazelle.logistics.service.PidService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
/**
 * 类 名 称：OrderServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.OrderService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class OrderServiceImpl implements OrderService {
    // Log初始化
    Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Resource
    private ProtocolOrderDaoMapper protocolOrderDaoMapper;              // 数据访问层

    @Resource
    private PidService pidService;                                      // 数据访问层


    /**
     * 方法名称：saveOrder
     * 内容摘要：保存Order信息
     *
     * @param order order信息
     * @return boolean true or false
     */
    public boolean saveOrder(T_Sys_Order order) {
        boolean b = new Boolean(true);
        try {
            this.protocolOrderDaoMapper.saveOrder(order);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateOrder
     * 内容摘要：更新Order信息。
     *
     * @param order Order信息
     * @return boolean true or false
     */
    public boolean updateOrder(T_Sys_Order order) {
        boolean b = new Boolean(true);
        try {
            this.protocolOrderDaoMapper.updateOrder(order);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delOrder
     * 内容摘要：删除Order信息。
     *
     * @param order_id OrderID
     * @return boolean true or false
     */
    public void delOrder(String order_id) {
        try {
            this.protocolOrderDaoMapper.delOrder(order_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }
}
