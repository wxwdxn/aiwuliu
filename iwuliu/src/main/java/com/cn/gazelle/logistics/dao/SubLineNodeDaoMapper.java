/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: SubLineNodeDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：干线路线节点基础息管理实现
 * 设计文件：
 * 完成日期：2016-06-22
 * 作    者：QJ
 * 内容摘要：干线节点基础信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Detail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: SubLineNodeDaoMapper
 * 内容摘要: 干线路线节点基础息管理
 * 方法描述：该类有10个方法：
 *         01 findSubLineNodeById              根据ID查询干线路线节点基础信息
 *         02 findSubLineNodeByInfoID          根据干线路线ID查询干线路线节点基础信息
 *         03 findSubLineNodeByNodeNo          根据节点编号查询干线路线节点基础信息
 *         04 findSubLineNodeByNodeName        根据节点名称查询干线路线节点基础信息
 *         05 findAllSubLineNode               查询符合条件的干线路线节点列表信息（默认查询干线路线节点列表信息）
 *         06 findAllSubLineNodeRowsCount      查询干线路线节点基础信息总记录数
 *         07 saveSubLineNode                  保存干线路线节点基础信息
 *         08 updateSubLineNode                更新干线路线节点基础信息
 *         09 delSubLineNode                   根据ID删除干线路线节点基础信息
 *         10 findSubLineNodeList              查询所有干线路线节点信息不分页
 * @author QJ
 */
public interface SubLineNodeDaoMapper {
    // 根据ID查询干线路线节点基础信息
    T_Master_Sub_Line_Detail findSubLineNodeById(@Param(value = "line_id") String line_id);

    // 根据干线路线ID查询干线路线节点基础信息
    List<T_Master_Sub_Line_Detail> findSubLineNodeByInfoID(@Param(value = "main_line_info_id") String main_line_info_id);

    // 根据节点编号查询干线路线节点基础信息
    T_Master_Sub_Line_Detail findSubLineNodeByNodeNo(@Param(value = "node_no") String node_no);

    // 根据节点名称查询干线路线节点基础信息
    T_Master_Sub_Line_Detail findSubLineNodeByNodeName(@Param(value = "node_name") String node_name);

    // 查询符合条件的干线路线节点列表信息（默认查询干线路线节点列表信息）
    List<T_Master_Sub_Line_Detail> findAllSubLineNode(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询干线路线节点基础信息总记录数
    Integer findAllSubLineNodeRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存干线路线节点基础信息
    int saveSubLineNode(T_Master_Sub_Line_Detail sub_line_node);

    // 更新干线路线节点基础信息
    int updateSubLineNode(T_Master_Sub_Line_Detail sub_line_node);

    // 根据ID删除干线路线节点基础信息
    void delSubLineNode(@Param(value = "line_id") String line_id);

    // 查询所有干线路线节点信息不分页
    List<T_Master_Sub_Line_Detail> findSubLineNodeList();
}
