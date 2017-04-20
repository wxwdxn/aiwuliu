/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DicdataServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：字典数据信息查询接口
 * 设计文件：
 * 完成日期：2016-02-18
 * 作    者：YK
 * 内容摘要：字典数据信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DicdataDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.DicdataManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.mchange.v2.holders.ThreadSafeIntHolder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: DicdataServiceImpl
 * 内容摘要: 字典数据信息查询接口
 * 方法描述：该类有7个方法：
 *          01 findDicdataByID                          根据ID查询字典数据信息
 *          02 findAllDicdata                           查询字典数据信息
 *          03 findAllDicdataByID                       根据字典ID查询字典数据信息
 *          04 findAllDicdataSearchCount                查询符合条件的字典数据总记录数
 *          05 saveDicdata                              保存字典数据信息
 *          06 updateDicdata                            更新字典数据信息
 *          07 delDicdata                               删除字典数据信息
 * @author YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.DicdataService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class DicdataServiceImpl implements DicdataService {
    // Log初始化
    Logger logger = Logger.getLogger(DicdataServiceImpl.class);

    @Resource
    private DicdataDaoMapper dicdataDaoMapper;                   // 数据访问层

    /**
     * 方法名称：findDicdataByID
     * 内容摘要：根据ID查询字典数据信息。
     * @param dicdata_id 字典数据id
     * @return T_Sys_Dicdata 字典数据信息
     */
    public T_Sys_Dicdata findDicdataByID(String dicdata_id) {
        T_Sys_Dicdata dicdata = null;
        try {
            dicdata = this.dicdataDaoMapper.findDicdataByID(dicdata_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dicdata;
    }

    /**
     * 方法名称：findAllDicdata
     * 内容摘要：查询字典数据信息
     * @param dictionary_id 公司名称
     * @param page          页面页数
     * @param rows          页面显示条数
     * @return List 字典数据信息列
     */
    public List findAllDicdata(String dictionary_id, Integer page, Integer rows) {
        List<T_Sys_Dicdata> dicdataList = null;
        try {
            dicdataList = this.dicdataDaoMapper.findAllDicdata(dictionary_id, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dicdataList;
    }

    /**
     * 方法名称：findAllDicdataByID
     * 内容摘要：根据字典ID查询字典数据信息
     * @param dictionary_id 字典id
     * @param dicdata_code  字典数据编码
     * @return Integer  企业信息记录数
     */
    public List findAllDicdataByID(String dictionary_id, String dicdata_code) {
        List<T_Sys_Dicdata> dicdataList = null;
        try {
            dicdataList = this.dicdataDaoMapper.findAllDicdataByID(dictionary_id, dicdata_code);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dicdataList;
    }

    /**
     * 方法名称：findAllCompanyRowsCount
     * 内容摘要：查询符合条件的字典数据总记录数
     * @param dictionary_id 字典id
     * @return Integer  字典数据总记录数
     */
    public Integer findAllDicdataSearchCount(String dictionary_id) {
        int count = 0;
        try {
            count = this.dicdataDaoMapper.findAllDicdataSearchCount(dictionary_id);
        } catch (Exception e) {
            logger.error(DicdataManager_Message.getSelectDicdataCountError + e.getMessage());
        }

        return count;
    }

//    /**
//     * 方法名称：saveDicdata
//     * 内容摘要：保存字典数据信息
//     * @param dicdata 字典数据信息
//     */
//    public boolean saveDicdata(T_Sys_Dicdata dicdata) {
//        boolean b = new Boolean(true);
//        try {
//            this.dicdataDaoMapper.saveDicdata(dicdata);
//            logger.info(MessageUtil.saveInfo);
//        } catch (Exception e) {
//            b = false;
//            logger.error(MessageUtil.saveInfoError + e.getMessage());
//        }
//        return b;
//    }

    /**
     * 方法名称：updateDicdata
     * 内容摘要：更新字典数据信息
     * @param dicdata 字典数据信息
     */
    public boolean updateDicdata(T_Sys_Dicdata dicdata) {
        boolean b = new Boolean(true);
        try {
            this.dicdataDaoMapper.updateDicdata(dicdata);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delDicdata
     * 内容摘要：更新字典数据信息
     * @param dicdata_id 字典数据id
     */
    public void delDicdata(String dicdata_id) {
        try {
            this.dicdataDaoMapper.delDicdata(dicdata_id);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：delDicdata
     * 内容摘要：更新字典数据信息
     * @param dictionary_id dictionary_id
     * @param dicdata_code dicdata_code
     */
    public List<T_Sys_Dicdata> findAllDicdataByCode(String dictionary_id,String dicdata_code) {
        List<T_Sys_Dicdata> dicdataList = null;
        try {
            dicdataList =  this.dicdataDaoMapper.findAllDicdataByCode(dictionary_id,dicdata_code);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
        return dicdataList;
    }

    /**
     * 方法名称：findDicdataByDictionaryID
     * 内容摘要：根据字典id查询字典数据列表
     * @param dictionary_id dictionary_id
     */
    public List<T_Sys_Dicdata> findDicdataByDictionaryID(String dictionary_id) {
        List<T_Sys_Dicdata> dicdataList = null;
        try {
            dicdataList = this.dicdataDaoMapper.findDicdataByDictionaryID(dictionary_id);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dicdataList;
    }
}
