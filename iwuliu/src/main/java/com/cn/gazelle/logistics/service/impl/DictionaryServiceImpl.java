/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DictionaryServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：字典信息查询接口
 * 设计文件：
 * 完成日期：2016-02-18
 * 作    者：YK
 * 内容摘要：字典信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DictionaryDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Dictionary;
import com.cn.gazelle.logistics.service.DictionaryService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.DictionaryManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: DictionaryServiceImpl
 * 内容摘要: 字典信息查询接口
 * 方法描述：该类有7个方法：
 * 01 findDictionaryByID                          根据ID查询字典信息
 * 02 findAllDictionary                           查询所有字典信息
 * 03 findAllDictionaryRowsCount                  查询字典总记录数
 * 04 findAllDictionarySearchCount                查询符合条件的字典总记录数
 * 05 saveDictionary                              保存字典信息
 * 06 updateDictionary                            更新字典信息
 * 07 delDictionary                               删除字典信息
 * 08 findDictionaryByDictionaryType              根据字典类型模糊查询字典信息
 *
 * @author YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.DictionaryService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class DictionaryServiceImpl implements DictionaryService {
    // Log初始化
    Logger logger = Logger.getLogger(DictionaryServiceImpl.class);

    @Resource
    private DictionaryDaoMapper dictionaryDaoMapper;                // 数据访问层

    /**
     * 方法名称：findDictionaryByID
     * 内容摘要：根据ID查询字典信息。
     *
     * @param dictionary_id 字典id
     * @return T_Sys_Dictionary 字典信息
     */
    public T_Sys_Dictionary findDictionaryByID(String dictionary_id) {
        T_Sys_Dictionary dictionary = null;
        try {
            dictionary = this.dictionaryDaoMapper.findDictionaryByID(dictionary_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dictionary;
    }

    /**
     * 方法名称：findCompanyByName
     * 内容摘要：查询所有字典信息
     *
     * @param dictionary_type 字典类型
     * @param page            页面页数
     * @param rows            页面显示条数
     * @return List 字典信息
     */
    public List findAllDictionary(String dictionary_type, Integer page, Integer rows) {
        List<T_Sys_Dictionary> dictionaryList = null;
        try {
            dictionaryList = this.dictionaryDaoMapper.findAllDictionary(dictionary_type, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dictionaryList;
    }

    /**
     * 方法名称：findAllDictionaryRowsCount
     * 内容摘要：查询字典总记录数
     *
     * @return Integer  字典总记录数
     */
    public Integer findAllDictionaryRowsCount() {
        int count = 0;
        try {
            count = this.dictionaryDaoMapper.findAllDictionaryRowsCount();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(DictionaryManager_Message.getSelectDictionaryCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：findAllDictionarySearchCount
     * 内容摘要：查询符合条件的字典总记录数
     *
     * @param dictionary_type 字典类型
     * @return Integer  符合条件的字典总记录数
     */
    public Integer findAllDictionarySearchCount(String dictionary_type) {
        int count = 0;
        try {
            count = this.dictionaryDaoMapper.findAllDictionarySearchCount(dictionary_type);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(DictionaryManager_Message.getSelectDictionaryCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：saveDictionary
     * 内容摘要：保存字典信息
     *
     * @param dictionary 字典信息
     */
    public boolean saveDictionary(T_Sys_Dictionary dictionary) {
        boolean b = new Boolean(true);
        try {
            this.dictionaryDaoMapper.saveDictionary(dictionary);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateDictionary
     * 内容摘要：更新字典信息
     *
     * @param dictionary 字典信息
     */
    public boolean updateDictionary(T_Sys_Dictionary dictionary) {
        boolean b = new Boolean(true);
        try {
            this.dictionaryDaoMapper.updateDictionary(dictionary);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delDictionary
     * 内容摘要：删除字典信息
     *
     * @param dictionary_id 字典id
     */
    public void delDictionary(String dictionary_id) {
        try {
            this.dictionaryDaoMapper.delDictionary(dictionary_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }

    public List<T_Sys_Dictionary> findDictionaryByDictionaryType(String dictionary_type) {
        List<T_Sys_Dictionary> dictionaryList = null;
        try {
            dictionaryList = this.dictionaryDaoMapper.findDictionaryByDictionaryType(dictionary_type);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dictionaryList;
    }

    /**
     * 方法名称：findDictionaryByID2
     * 内容摘要：根据ID查询字典信息2。
     *
     * @param dictionary_id 字典id
     * @return String 字典信息json
     */
    public T_Sys_Dictionary findDictionaryByID2(String dictionary_id) {
        T_Sys_Dictionary dictionary = null;
        try {
            dictionary = this.dictionaryDaoMapper.findDictionaryByID(dictionary_id);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dictionary;
    }

    /**
     * 方法名称：findDictionaryList
     * 内容摘要：查询所有数据字典信息不分页。
     *
     * @return List<T_Sys_Dictionary> 字典信息
     */
    public List<T_Sys_Dictionary> findDictionaryList() {
        List<T_Sys_Dictionary> dictionaryList = null;
        try {
            dictionaryList = this.dictionaryDaoMapper.findDictionaryList();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dictionaryList;
    }
}
