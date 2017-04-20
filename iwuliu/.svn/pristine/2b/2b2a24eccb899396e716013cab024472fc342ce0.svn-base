/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DicdataService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：字典数据信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-20
 * 作    者：YK
 * 内容摘要：字典数据信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: DicdataService
 * 内容摘要: 字典数据信息查询接口
 * 方法描述：该类有7个方法：
 * 01 findDicdataByID                          根据ID查询字典数据信息
 * 02 findAllDicdata                           查询字典数据信息
 * 03 findAllDicdataByID                       根据字典ID查询字典数据信息
 * 04 findAllDicdataSearchCount                查询符合条件的字典数据总记录数
 * 05 saveDicdata                              保存字典数据信息
 * 06 updateDicdata                            更新字典数据信息
 * 07 delDicdata                               删除字典数据信息
 *
 * @author YK
 */
@WebService
public interface DicdataService {

    // 根据ID查询字典数据信息
    T_Sys_Dicdata findDicdataByID(String dicdata_id);

    // 查询字典数据信息
    List findAllDicdata(String dictionary_id, Integer page, Integer rows);

    // 根据字典ID查询字典数据信息
    List findAllDicdataByID(String dictionary_id, String dicdata_code);

    // 查询符合条件的字典数据总记录数
    Integer findAllDicdataSearchCount(String dictionary_id);

//    // 保存字典数据信息
//    boolean saveDicdata(T_Sys_Dicdata dicdata);

    // 更新字典数据信息
    boolean updateDicdata(T_Sys_Dicdata dicdata);

    // 删除字典数据信息
    void delDicdata(String dicdata_id);

    // 根据字典编码查询字典数据信息
    List<T_Sys_Dicdata> findAllDicdataByCode(String dictionary_id, String dicdata_code);

    // 根据字典id查找字典数据信息
    List<T_Sys_Dicdata> findDicdataByDictionaryID(String dictionary_id);
}
