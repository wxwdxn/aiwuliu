/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DictionaryService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：字典信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-18
 * 作    者：YK
 * 内容摘要：字典信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_Dictionary;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: DicdataService
 * 内容摘要: 字典数据信息查询接口
 * 方法描述：该类有7个方法：
 *         01 findDictionaryByID                          根据ID查询字典信息
 *         02 findAllDictionary                           查询所有字典信息
 *         03 findAllDictionaryRowsCount                  查询字典总记录数
 *         04 findAllDictionarySearchCount                查询符合条件的字典总记录数
 *         05 saveDictionary                              保存字典信息
 *         06 updateDictionary                            更新字典信息
 *         07 delDictionary                               删除字典信息
 *         08 findDictionaryByDictionaryType              根据字典类型模糊查询字典信息
 * @author YK
 */
@WebService
public interface DictionaryService {

    // 根据ID查询字典信息
    T_Sys_Dictionary findDictionaryByID(String dictionary_id);

    // 查询所有字典信息
    List findAllDictionary(String dictionary_type, Integer page, Integer rows);

    // 查询字典总记录数
    Integer findAllDictionaryRowsCount();

    // 查询符合条件的字典总记录数
    Integer findAllDictionarySearchCount(String dictionary_type);

    // 保存字典信息
    boolean saveDictionary(T_Sys_Dictionary dictionary);

    // 更新字典信息
    boolean updateDictionary(T_Sys_Dictionary dictionary);

    // 删除字典信息
    void delDictionary(String dictionary_id);

    // 根据字典类型模糊查询字典信息
    List<T_Sys_Dictionary> findDictionaryByDictionaryType(String dictionary_type);

    // 根据ID查询字典信息2
    T_Sys_Dictionary findDictionaryByID2(String dictionary_id);

    // 查询所有数据字典信息不分页
    List<T_Sys_Dictionary> findDictionaryList();
}
