/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DicdataDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：字典数据信息信息查询接口实现
 * 设计文件：
 * 完成日期：2016-01-20
 * 作    者：YK
 * 内容摘要：字典数据信息信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: DicdataDaoMapper
 * 内容摘要: 字典数据信息管理页面
 * 方法描述：该类有10个方法：
 *         01 findDicdataByID                          根据ID查询字典数据信息
 *         02 findAllDicdata                           查询字典数据信息
 *         03 findAllDicdataByID                       查询符合条件的字典数据信息
 *         04 findAllDicdataByID2                      查询符合条件的字典数据信息2
 *         05 findDicdataByDicdataName                 根据字典数据名称查找字典数据信息
 *         06 findAllDicdataByCode                     根据字典编码查询字典数据信息
 *         07 findAllDicdataSearchCount                查询符合条件的字典数据总记录数
 *         08 saveDicdata                              保存字典数据信息
 *         09 updateDicdata                            更新字典数据信息
 *         10 delDicdata                               删除字典数据信息
 * @author YK
 */
public interface DicdataDaoMapper {

    // 根据ID查询字典数据信息
    T_Sys_Dicdata findDicdataByID(@Param("dicdata_id") String dicdata_id);

    // 查询字典数据信息
    List<T_Sys_Dicdata> findAllDicdata(@Param("dictionary_id") String dictionary_id, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询符合条件的字典数据信息
    List<T_Sys_Dicdata> findAllDicdataByID(@Param("dictionary_id") String dictionary_id, @Param("dicdata_code") String dicdata_code);

    // 查询符合条件的字典数据信息2
    T_Sys_Dicdata findAllDicdataByID2(@Param("dictionary_id") String dictionary_id, @Param("dicdata_name") String dicdata_name);

    // 根据字典数据名称查找字典数据信息
    T_Sys_Dicdata findDicdataByDicdataName(@Param("dictionary_id") String dictionary_id, @Param("dicdata_name") String dicdata_name, @Param("dicdata_code") String dicdata_code);

    // 根据字典编码查询字典数据信息
    List<T_Sys_Dicdata> findAllDicdataByCode(@Param("dictionary_id") String dictionary_id, @Param("dicdata_code") String dicdata_code);

    // 查询符合条件的字典数据总记录数
    Integer findAllDicdataSearchCount(@Param("dictionary_id") String dictionary_id);

    // 保存字典数据信息
    int saveDicdata(T_Sys_Dicdata dicdata);

    // 更新字典数据信息
    void updateDicdata(T_Sys_Dicdata dicdata);

    // 删除字典数据信息
    void delDicdata(String dicdata_id);

    // 根据字典id查找字典数据信息
    List<T_Sys_Dicdata> findDicdataByDictionaryID(@Param("dictionary_id") String dictionary_id);

    // 根据字典编码查询字典数据信息(车辆长度排序)
    List<T_Sys_Dicdata> findAllDicdataTruckTypeLengthByCode(@Param("dictionary_id") String dictionary_id, @Param("dicdata_code") String dicdata_code);

    // 查询字典数据信息不分页
    List<T_Sys_Dicdata> findDicdataList(@Param("dictionary_id") String dictionary_id);

}
