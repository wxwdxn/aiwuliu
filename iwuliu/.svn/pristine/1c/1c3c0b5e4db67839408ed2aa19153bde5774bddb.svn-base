/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：SubLineInfoService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-22
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Info;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称：SubLineInfoService
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 saveSubLineInfo                               // 保存干线路线信息
 *          02 updateSubLineInfo                             // 更新干线路线信息
 *@authot YK
 */
@WebService
public interface SubLineInfoService {

    // 保存干线路线信息
    int saveSubLineInfo(T_Master_Sub_Line_Info lineInfo);

    // 更新干线路线信息
    boolean updateSubLineInfo(T_Master_Sub_Line_Info lineInfo);

    // 更新干线路线信息
    void delSubLineInfo(String line_id);

    // 根据干线路线ID删除干线路线节点信息
    void delSubLineNodeBySubLineInfoId(String line_id);

    // 根据干线ID查询干线路线信息列表
    List<T_Master_Sub_Line_Info> findSubLineInfoByOperateMainLineID(String operate_main_line_id);

    // 根据线路ID查询线路信息表
    T_Master_Sub_Line_Info findSubLineInfoById(String line_id);
}
