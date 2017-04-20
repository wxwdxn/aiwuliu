/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ContractLineDao.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运输合同线路信息管理
 * 设计文件：
 * 完成日期：2016-04-25
 * 作    者：WXW
 * 内容摘要：运输合同线路信息管理
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Contract_Line_Match;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 类 名 称: ContractLineDao
        * 内容摘要: 合同线路匹配管理
        * 方法描述：该类有7个方法：
        *         01 findById                             根据ID查运输合同线路匹配信息
        *         02 findAll                              查找所有运输合同线路匹配信息（分页）
        *         03 count                                查询运输合同线路匹配总记录数
        *         04 saveContractLine                     保存运输合同线路匹配信息
        *         05 updateContractLine                   更新运输合同线路匹配信息
        *         06 delContractLine                      根据id删除运输合同线路匹配信息
        *         07findLineNameList                      根据合同id查询线路名称
        * @author WXW
 */
public interface ContractLineDao {

    // 根据id查询
    T_Data_Contract_Line_Match findById(@Param(value = "matchId") String matchId);

    // 查询匹配列表分页
    List<T_Data_Contract_Line_Match> findAll(@Param(value = "searchType") String searchType, @Param(value = "name") String name, @Param(value = "page") Integer page, @Param(value = "rows") Integer rows);

    // 查询总记录数
    Integer count(@Param(value = "searchType") String searchType, @Param(value = "name") String name);

    // 增加运输合同线路匹配
    void saveContractLine(T_Data_Contract_Line_Match contractLine);

    // 删除运输合同线路匹配
    void delContractLine(@Param(value = "matchId") String matchId);

    // 更新运输合同线路匹配
    void updateContractLine(T_Data_Contract_Line_Match contractLine);

    //根据合同id查询线路名称
    List findLineNameList(@Param(value = "contractId") String contractId, @Param(value = "page") Integer page, @Param(value = "rows") Integer rows);

    //根据合同id查询匹配的个数
    Integer findLineCount(@Param(value = "contractId") String contractId);
}
