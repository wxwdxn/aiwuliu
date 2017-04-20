/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationContractDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运输合同信息管理实现
 * 设计文件：
 * 完成日期：2016-03-03
 * 作    者：WXW
 * 内容摘要：运输合同信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Contract_Dummy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: DummyContractDaoMapper
 * 内容摘要: 运输合同信息管理辅助运算
 * 方法描述：该类有8个方法：
 *         01 findById                                        根据ID查运输合同信息
 *         02 findByCode                                      根据运输合同编号查找运输合同信息
 *         03 findTransportationContractList                  查找所有运输合同信息（不分页）
 *         04 findAllTransportationContract                   查找所有运输合同信息（分页）
 *         05 findAllTransportationContractRowsCount          查询运输合同总记录数
 *         06 saveTransportationContract                      保存运输合同信息
 *         07 updateTransportationContract                    更新运输合同信息
 *         08 delTransportationContract                       删除运输合同信息
 * @author WXW
 */
public interface DummyContractsDaoMapper {


    //根据合同编号查询所有运输合同信息
    List<T_Data_Transportation_Contract_Dummy> findAllTransportationContract(@Param(value = "searchType") String searchType, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);


    //添加运输合同信息
    int  saveDummyContract(T_Data_Transportation_Contract_Dummy contract);

    //根据ID查询
    T_Data_Transportation_Contract_Dummy findById(@Param(value = "contract_id") String contract_id);
}
