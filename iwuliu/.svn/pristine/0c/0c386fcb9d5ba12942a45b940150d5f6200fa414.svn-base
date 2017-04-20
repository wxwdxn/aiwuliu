/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationContractService
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运输合同信息管理实现
 * 设计文件：
 * 完成日期：2016-03-04
 * 作    者：WXW
 * 内容摘要：运输合同信息管理实现
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Contract;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: TransportationContractService
 * 内容摘要: 运输合同信息管理
 * 方法描述：该类有5个方法：
 *         01 findAllTransportationContract                   查找所有运输合同信息（分页）
 *         03 saveTransportationContract                      保存运输合同信息
 * @author WXW
 */
@WebService
public interface TransportationContractService {

    //添加运输合同信息
    int saveTransportationContract(String parameter ,String userName);

    // 修改运输合同干线单价和添加干线
    int updateContractOrAddLineInfo(String parameter ,String userName);

   //根据id查询合同
    T_Data_Transportation_Contract findById(String contractId);

    //根据编号查询合同
    T_Data_Transportation_Contract findByCode(String code);

    //根据编号查询所有运输合同信息
    List<T_Data_Transportation_Contract> findAllTransportationContract();

}