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

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: TransportationContractDaoMapper
 * 内容摘要: 运输合同信息管理
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
public interface TransportationContractDaoMapper {

    //根据ID查询findById
    T_Data_Transportation_Contract findById(@Param(value = "contract_id") String contract_id);

    //根据ID查询findById
    T_Data_Transportation_Contract findByCode(@Param(value = "code") String code);

    //添加运输合同信息
    int  saveTransportationContract(T_Data_Transportation_Contract transportation_contract);

    //更新运输合同信息
    void updateTransportationContract(T_Data_Transportation_Contract transportation_contract);


    //查询所有运输合同信息
    List<T_Data_Transportation_Contract> findAllTransportationContract();


    //根据货物类型id查询
    List<T_Data_Transportation_Contract> findByCargoTypeId(@Param(value = "cargo_type_id") String cargo_type_id);

}
