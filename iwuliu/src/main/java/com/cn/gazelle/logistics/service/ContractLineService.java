/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ContractLineService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：合同线路匹配信息管理实现
 * 设计文件：
 * 完成日期：2016-04-25
 * 作    者：WXW
 * 内容摘要：运输信息管理实现
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Contract_Line_Match;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: ContractLineService
 * 内容摘要: 合同线路匹配管理
 * 方法描述：该类有6个方法：
 *         01 findById                             根据ID查运输合同线路匹配信息
 *         02 findAll                              查找所有运输合同线路匹配信息（分页）
 *         03 count                                查询运输合同线路匹配总记录数
 *         04 saveContractLine                     保存运输合同线路匹配信息
 *         05 updateContractLine                   更新运输合同线路匹配信息
 *         06 delContractLine                      根据id删除运输合同线路匹配信息
 * @author WXW
 */
@WebService
public interface ContractLineService {

    // 根据id查询运输合同线路匹配
    T_Data_Contract_Line_Match findById(String matchId);

    // 查询匹配列表分页
    List<T_Data_Contract_Line_Match> findAll(String searchType, String name, Integer page, Integer rows);

    // 查询运输合同线路匹配总记录数
    Integer count(String searchType, String name);

    // 增加运输合同线路匹配
    boolean saveContractLine(T_Data_Contract_Line_Match contractLine);

    // 删除运输合同线路匹配
    void delContractLine(String matchId);

    // 更新运输合同线路匹配
    boolean updateContractLine(T_Data_Contract_Line_Match contractLine);
}
