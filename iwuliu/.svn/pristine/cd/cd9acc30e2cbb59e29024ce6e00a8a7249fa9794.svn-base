/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：DataDictionaryServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-08
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DicdataDaoMapper;
import com.cn.gazelle.logistics.dao.DictionaryDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.pojo.T_Sys_Dictionary;
import com.cn.gazelle.logistics.service.DataDictionaryService;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.DictionaryService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：DataDictionaryServiceImpl
 * 内容描述：单点登录-数据字典信息管理查询接口
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.DataDictionaryService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class DataDictionaryServiceImpl implements DataDictionaryService {
    // Log初始化
    Logger logger = Logger.getLogger(DataDictionaryServiceImpl.class);
    @Resource
    private DictionaryDaoMapper dictionaryDaoMapper;
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;

    /**
     * 方法名称：saveDictionary
     * 内容摘要：新增字典信息
     *
     * @param list     线路信息
     * @param userName 用户名
     * @return flag
     */
    @Transactional
    public int saveDictionary(String list, String userName) {
        Gson gson = new Gson();
        int flag = 0;
        int countDictionary = 0;
        int countDicdata = 0;
        try {
            Map<String, Object> data = gson.fromJson(list, new TypeToken<Map<String, Object>>() {
            }.getType());
            T_Sys_Dictionary dictionary = new T_Sys_Dictionary();
            String dictionary_type = (String) data.get("dictionary_type");
            dictionary.setDictionary_type(dictionary_type);
            dictionary.setDictionary_id(UUIDUtil.getUUID());
            dictionary.setDictionary_date(DateUtil.getDate());
            dictionary.setDelete_states("0");
            countDictionary = this.dictionaryDaoMapper.saveDictionary(dictionary);
            String dictionary_id = dictionary.getDictionary_id();
            if (countDictionary == 1) {
                flag = 1;
                List<Map<String, Object>> dicdataList = (List<Map<String, Object>>) data.get("dicdataList");
                if (dicdataList != null && dicdataList.size() != 0) {
                    for (int i = 0; i < dicdataList.size(); i++) {
                        T_Sys_Dicdata dicdata = new T_Sys_Dicdata();
                        dicdata.setDicdata_id(UUIDUtil.getUUID());
                        dicdata.setDictionary_type(dictionary_type);
                        dicdata.setDictionary_id(dictionary_id);
                        Map temp = new HashMap();
                        temp = (Map) dicdataList.get(i);
                        String dicdata_name = (String) temp.get("dicdata_name");
                        dicdata.setDicdata_name(dicdata_name);
                        String dicdata_code = (String) temp.get("dicdata_code");
                        dicdata.setDicdata_code(dicdata_code);
                        String dicdata_remark = (String) temp.get("dicdata_remark");
                        dicdata.setDicdata_remark(dicdata_remark);
                        dicdata.setDicdata_date(DateUtil.getDate());
                        dicdata.setDelete_states("0");
                        countDicdata = dicdataDaoMapper.saveDicdata(dicdata);
                        if (countDicdata == 1) {
                            flag = 1;
                        } else {
                            flag = 0;
                            throw new RuntimeException(flag + "");
                        }
                    }
                }
            } else {
                flag = 0;
                throw new RuntimeException(flag + "");
            }
        } catch (Exception e) {
            flag = -1;
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    @Transactional
    public int addDicdata(String list, String userName) {
        Gson gson = new Gson();
        int flag = 0;
        int countDictionary = 0;
        int countDicdata = 0;
        T_Sys_Dictionary dictionary = null;
        try {
            Map<String, Object> data = gson.fromJson(list, new TypeToken<Map<String, Object>>() {
            }.getType());
            String dictionary_id = (String) data.get("dictionary_id");
            dictionary = this.dictionaryDaoMapper.findDictionaryByID(dictionary_id);
            if (dictionary != null) {
                String dictionary_type = (String) data.get("dictionary_type");
                dictionary.setDictionary_type(dictionary_type);
                countDictionary = this.dictionaryDaoMapper.updateDictionary(dictionary);
                if (countDictionary == 1) {
                    flag = 1;
                    List<Map<String, Object>> dicdataList = (List<Map<String, Object>>) data.get("dicdataList");
                    for (int i = 0; i < dicdataList.size(); i++) {
                        T_Sys_Dicdata dicdata = new T_Sys_Dicdata();
                        dicdata.setDicdata_id(UUIDUtil.getUUID());
                        dicdata.setDictionary_type(dictionary_type);
                        dicdata.setDictionary_id(dictionary_id);
                        Map temp = new HashMap();
                        temp = (Map) dicdataList.get(i);
                        String dicdata_name = (String) temp.get("dicdata_name");
                        dicdata.setDicdata_name(dicdata_name);
                        String dicdata_code = (String) temp.get("dicdata_code");
                        dicdata.setDicdata_code(dicdata_code);
                        String dicdata_remark = (String) temp.get("dicdata_remark");
                        dicdata.setDicdata_remark(dicdata_remark);
                        dicdata.setDicdata_date(DateUtil.getDate());
                        dicdata.setDelete_states("0");
                        countDicdata = dicdataDaoMapper.saveDicdata(dicdata);
                        if (countDicdata == 1) {
                            flag = 1;
                        } else {
                            flag = 0;   // 字典数据保存失败
                            throw new RuntimeException(flag + "");
                        }
                    }
                } else {
                    flag = 0;   // 字典类型保存失败
                    throw new RuntimeException(flag + "");
                }
            } else {
                flag = 0; // 字典类型不存在
                throw new RuntimeException(flag + "");
            }
        } catch (Exception e) {
            flag = -1;
            throw new RuntimeException(flag + "");
        }
        return flag;
    }
}