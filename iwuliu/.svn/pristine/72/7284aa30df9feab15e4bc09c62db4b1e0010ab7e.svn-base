/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ContractLineImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：合同线路匹配信息管理实现
 * 设计文件：
 * 完成日期：2016-04-25
 * 作    者：WXW
 * 内容摘要：运输信息管理实现
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.ContractLineDao;
import com.cn.gazelle.logistics.pojo.T_Data_Contract_Line_Match;
import com.cn.gazelle.logistics.service.ContractLineService;
import com.cn.gazelle.logistics.util.message.ContractLine_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
/**
 * 类 名 称: ContractLineImpl
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
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.ContractLineService", targetNamespace = "http:// service.logistics.gazelle.cn.com/")
public class ContractLineImpl implements ContractLineService {
    // Log初始化
    Logger logger = Logger.getLogger(ContractLineImpl.class);
    @Resource
    private ContractLineDao contractLineDao;

    /**
     * 方法名称：findById
     * 内容摘要：根据id查询合同线路匹配
     * @param  matchId   匹配id
     * @return T_Data_Contract_Line_Match match
     */
    public T_Data_Contract_Line_Match findById(String matchId) {
        T_Data_Contract_Line_Match match = null;
        try {
            match = contractLineDao.findById(matchId);
            logger.info(ContractLine_Message.getContractLineDone);
        } catch (Exception e) {
            logger.error(e.getMessage() +ContractLine_Message.getContractLineError);
        }
        return match;
    }
    /**
     * 方法名称：findAll
     * 内容摘要：查询合同线路匹配列表分页
     * @param searchType 查询字段名
     * @param name       查询值
     * @param page       页码
     * @param rows       行数
     * @return T_Data_Contract_Line_Match matchList 合同匹配列表
     */
    public List<T_Data_Contract_Line_Match> findAll(String searchType, String name, Integer page, Integer rows) {
        List<T_Data_Contract_Line_Match> matchList = null;
        try {
            matchList = contractLineDao.findAll(searchType, name, page, rows);
            logger.info(ContractLine_Message.getContractLineDone);
        } catch (Exception e) {
            logger.error(e.getMessage() + ContractLine_Message.getContractLineError);
        }
        return matchList;
    }

    /**
     * 方法名称：count
     * 内容摘要：查询合同线路匹配总记录数
     * @param name          查询值
     * @param searchType   查询字段名
     * @return Int count 合同线路匹配总记录数
     */
    public Integer count(String searchType, String name) {
        Integer count = null;
        try {
            count = contractLineDao.count(searchType, name);
            logger.info(ContractLine_Message.FindContractLineRowsCountDone);
        } catch (Exception e) {
            logger.error(e.getMessage() + ContractLine_Message.FindContractLineRowsCountError);
        }

        return count;
    }

    /**
     * 方法名称：saveContractLine
     * 内容摘要：增加合同线路匹配
     * @param contractLine  合同线路匹配信息
     */
    public boolean saveContractLine(T_Data_Contract_Line_Match contractLine) {
        boolean a=new Boolean(true);
        try {
            contractLineDao.saveContractLine(contractLine);
            logger.info(ContractLine_Message.SaveContractLineDone);
        } catch (Exception e) {
            a=false;
            logger.error(e.getMessage() + ContractLine_Message.SaveContractLineError);
        }
        return a;
    }

    /**
     * 方法名称：delContractLine
     * 内容摘要：删除运输线路合同匹配信息
     * @param matchId 运输线路合同匹配id
     */
    public void delContractLine(String matchId) {
        try {
            contractLineDao.delContractLine(matchId);
            logger.info(ContractLine_Message.DelContractLineDone);
        } catch (Exception e) {
            logger.error(e.getMessage() + ContractLine_Message.DelContractLineError);
        }
    }

    /**
     * 方法名称：updateContractLine
     * 内容摘要：更新运输合同线路信息
     * @param contractLine 运输合同线路信息
     */
    public boolean updateContractLine(T_Data_Contract_Line_Match contractLine) {
        boolean a=new Boolean(true);
        try {
            contractLineDao.updateContractLine(contractLine);
            logger.info(ContractLine_Message.UpdateContractLineDone);
        } catch (Exception e) {
            a=false;
            logger.error(e.getMessage() + ContractLine_Message.UpdateContractLineError);
        }
        return a;
    }

}
