/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DictionaryDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：字典信息查询接口实现
 * 设计文件：
 * 完成日期：2016-01-18
 * 作    者：YK
 * 内容摘要：字典信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: DictionaryDaoMapper
 * 内容摘要: 字典信息查询接口实现
 * 方法描述：该类有8个方法：
 *         01 findDictionaryByID                          根据ID查询字典信息
 *         02 findDictionaryByDictionaryType              根据字典类型查询字典信息
 *         03 findAllDictionary                           查询所有字典信息
 *         04 findAllDictionaryRowsCount                  查询字典总记录数
 *         05 findAllDictionarySearchCount                查询符合条件的字典总记录数
 *         06 saveDictionary                              保存字典信息
 *         07 updateDictionary                            更新字典信息
 *         08 delDictionary                               删除字典信息
 * @author YK
 */
public interface DictionaryDaoMapper {

    // 根据ID查询字典信息
    T_Sys_Dictionary findDictionaryByID(@Param("dictionary_id") String dictionary_id);

    // 根据字典类型查询字典信息
    List<T_Sys_Dictionary> findDictionaryByDictionaryType(@Param("dictionary_type") String dictionary_type);

    // 查询所有字典信息
    List<T_Sys_Dictionary> findAllDictionary(@Param("dictionary_type") String dictionary_type, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询字典总记录数
    Integer findAllDictionaryRowsCount();

    // 查询符合条件的字典总记录数
    Integer findAllDictionarySearchCount(@Param("dictionary_type") String dictionary_type);

    // 保存字典信息
    int saveDictionary(T_Sys_Dictionary dictionary);

    // 更新字典信息
    int updateDictionary(T_Sys_Dictionary dictionary);

    // 删除字典信息
    void delDictionary(String dictionary_id);

    // 查询所有数据字典信息不分页
    List<T_Sys_Dictionary> findDictionaryList();
}
