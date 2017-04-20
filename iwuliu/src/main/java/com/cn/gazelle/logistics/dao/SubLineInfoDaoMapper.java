/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：SubLineInfoDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-22
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称：SubLineInfoDaoMapper
 * 内容描述：
 * 方法描述：该类有 8 个方法
 *          01 findSubLineInfoByID                           // 根据ID查找干线路线信息
 *          02 findSubLineInfoByNo                           // 根据路线编号查找干线路线信息
 *          03 findAllSubLineInfo                            // 查找所有干线路线信息(有参)
 *          04 findAll                                        // 查找所有干线路线信息(无参)
 *          05 findAllSubLineInfoRowsCount                   // 查找符合条件的干线路线总记录数
 *          06 saveSubLineInfo                               // 保存干线路线信息
 *          07 updateSubLineInfo                             // 更新干线路线信息
 *          08 delSubLineInfo                                // 删除干线路线信息
 *@authot YK
 */
public interface SubLineInfoDaoMapper {

    // 根据ID查找干线路线信息
    T_Master_Sub_Line_Info findSubLineInfoByID(@Param("line_id") String line_id);

    // 根据路线编号查找干线路线信息
    T_Master_Sub_Line_Info findSubLineInfoByNo(@Param("line_no") String line_no);

    // 查找所有干线路线信息(有参)
    List<T_Master_Sub_Line_Info> findAllSubLineInfo(@Param("line_no") String line_no, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查找所有干线路线信息(无参)
    List<T_Master_Sub_Line_Info> findAll();

    // 查找符合条件的干线路线总记录数
    Integer findAllSubLineInfoRowsCount(@Param("line_no") String line_no);

    // 保存干线路线信息
    int saveSubLineInfo(T_Master_Sub_Line_Info lineInfo);

    // 更新干线路线信息
    int updateSubLineInfo(T_Master_Sub_Line_Info lineInfo);

    // 删除干线路线信息
    void delSubLineInfo(String line_id);

    // 根据运营干线id查找干线路线信息 wxw
    List<T_Master_Sub_Line_Info> findSubLineInfoByOperateID(@Param("operate_main_line_id") String operate_main_line_id);

    // 查询所有干线路线信息不分页
    List<T_Master_Sub_Line_Info> findSubLineInfoList();

}
