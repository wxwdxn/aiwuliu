/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：SubLineNodeServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：干线路线节点基础息查询接口实现
 * 设计文件：
 * 完成日期：2016-06-22
 * 作    者: QJ
 * 内容摘要：干线路线节点基础息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.SubLineNodeDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Detail;
import com.cn.gazelle.logistics.service.SubLineNodeService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.message.SubLineNodeManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 类 名 称：SubLineNodeServiceImpl
 * 内容描述：干线路线节点基础息查询接口
 * 方法描述：该类有9个方法
 *         01 findSubLineNodeById              根据ID查询干线路线节点基础信息
 *         02 findSubLineNodeByInfoID          根据干线路线ID查询干线路线节点基础信息
 *         03 findSubLineNodeByNodeNo          根据节点编号查询干线路线节点基础信息
 *         04 findSubLineNodeByNodeName        根据节点名称查询干线路线节点基础信息
 *         05 findAllSubLineNode               查询符合条件的干线路线节点列表信息（默认查询干线路线节点列表信息）
 *         06 findAllSubLineNodeRowsCount          查询干线路线节点基础信息总记录数
 *         07 saveSubLineNode                  保存干线路线节点基础信息
 *         08 updateSubLineNode                更新干线路线节点基础信息
 *         09 delSubLineNode                   根据ID删除干线路线节点基础信息
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.SubLineNodeService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class SubLineNodeServiceImpl implements SubLineNodeService {
    // Log初始化
    Logger logger = Logger.getLogger(SubLineNodeServiceImpl.class);
    @Resource
    private SubLineNodeDaoMapper subLineNodeDaoMapper;  // 数据访问层

    /**
     * 方法名称：findSubLineNodeById
     * 内容摘要：根据ID查询干线路线节点基础信息。
     * @param line_id   干线路线节点id
     * @return  T_Master_Sub_Line_Detail 干线路线节点基础信息
     */
    public T_Master_Sub_Line_Detail findSubLineNodeById(String line_id) {
        return subLineNodeDaoMapper.findSubLineNodeById(line_id);
    }

    /**
     * 方法名称：findSubLineNodeByInfoID
     * 内容摘要：根据干线路线ID查询干线路线节点基础信息。
     * @param main_line_info_id 干线路线ID
     * @return  List<T_Master_Sub_Line_Detail>  干线路线节点基础信息
     */
    public List<T_Master_Sub_Line_Detail> findSubLineNodeByInfoID(String main_line_info_id) {
        return subLineNodeDaoMapper.findSubLineNodeByInfoID(main_line_info_id);
    }

    /**
     * 方法名称：findSubLineNodeByNodeNo
     * 内容摘要：根据节点编号查询干线路线节点基础信息。
     * @param node_no 节点编号
     * @return  T_Master_Sub_Line_Detail  干线路线节点基础信息
     */
    public T_Master_Sub_Line_Detail findSubLineNodeByNodeNo(String node_no) {
        T_Master_Sub_Line_Detail sub_line_node = null;
        try {
            sub_line_node = this.subLineNodeDaoMapper.findSubLineNodeByNodeNo(node_no);
            logger.info(SubLineNodeManager_Message.getSubLineNodeDone);
        } catch (Exception e) {
            logger.error(SubLineNodeManager_Message.getSubLineNodeError + e.getMessage());
        }
        return sub_line_node;
    }
//    *         04 findSubLineNodeByNodeName        根据节点名称查询干线路线节点基础信息
    /**
     * 方法名称：findSubLineNodeByNodeName
     * 内容摘要：根据节点名称查询干线路线节点基础信息。
     * @param node_name 节点名称
     * @return  T_Master_Sub_Line_Detail  干线路线节点基础信息
     */
    public T_Master_Sub_Line_Detail findSubLineNodeByNodeName(String node_name) {
        T_Master_Sub_Line_Detail sub_line_node = null;
        try {
            sub_line_node = this.subLineNodeDaoMapper.findSubLineNodeByNodeName(node_name);
            logger.info(SubLineNodeManager_Message.getSubLineNodeDone);
        } catch (Exception e) {
            logger.error(SubLineNodeManager_Message.getSubLineNodeError + e.getMessage());
        }
        return sub_line_node;
    }

    /**
     * 方法名称：findAllSubLineNode
     * 内容摘要：查询符合条件的干线路线节点列表信息（默认查询干线路线节点列表信息）。
     * @param search_type   搜索类型
     * @param name  查询类型
     * @param page  页面页数
     * @param rows  页面显示条数
     * @return List<T_Master_Sub_Line_Detail>  干线路线节点信息列
     */
    public List<T_Master_Sub_Line_Detail> findAllSubLineNode(String search_type, String name, Integer page, Integer rows) {
    return subLineNodeDaoMapper.findAllSubLineNode(search_type, name, page, rows);
}

    /**
     * 方法名称：findAllSubLineNodeRowsCount
     * 内容摘要：查询干线路线节点基础信息总记录数
     * @param search_type   搜索类型
     * @param name  查询类型
     * @return Integer 运营干线记录数
     */
    public Integer findAllSubLineNodeRowsCount(String search_type, String name) {
        return subLineNodeDaoMapper.findAllSubLineNodeRowsCount(search_type, name);
    }

    /**
     * 方法名称：saveSubLineNode
     * 内容摘要：保存干线路线节点基础信息
     * @param sub_line_node 干线路线节点基础信息
     */
    public int saveSubLineNode(T_Master_Sub_Line_Detail sub_line_node) {
        int count = 0;
        try {
            count = this.subLineNodeDaoMapper.saveSubLineNode(sub_line_node);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(SubLineNodeManager_Message.SaveSubLineNodeError+e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateSubLineNode
     * 内容摘要：更新干线路线节点基础信息
     * @param sub_line_node 干线路线节点基础信息
     */
    public boolean updateSubLineNode(T_Master_Sub_Line_Detail sub_line_node) {
        boolean a=new Boolean(true);
        try {
            sub_line_node.setLast_update(DateUtil.getDate());
            subLineNodeDaoMapper.updateSubLineNode(sub_line_node);
        } catch (Exception e) {
            a=false;
            logger.error(SubLineNodeManager_Message.UpdateSubLineNodeError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delSubLineNode
     * 内容摘要：根据ID删除干线路线节点基础信息
     * @param line_id  干线路线节点ID
     */
    public void delSubLineNode(String line_id) {
        try {
            subLineNodeDaoMapper.delSubLineNode(line_id);
            logger.info(SubLineNodeManager_Message.DelSubLineNodeDone);
        } catch (Exception e) {
            logger.error(SubLineNodeManager_Message.DelSubLineNodeError+e.getMessage());
        }
    }
}