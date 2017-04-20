/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DataDictionaryService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：单点登录-数据字典信息查询接口声明
 * 设计文件：
 * 完成日期：2016-12-08
 * 作    者：QJ
 * 内容摘要：单点登录-数据字典信息查询
 */
package com.cn.gazelle.logistics.service;

import javax.jws.WebService;

/**
 * 类 名 称: DataDictionaryService
 * 内容摘要: 单点登录-数据字典信息查询接口
 * 方法描述：该类有1个方法：
 * 01 saveDictionary                          新增字典信息
 *
 * @author QJ
 */
@WebService
public interface DataDictionaryService {
    // 新增字典信息
    int saveDictionary(String list, String userName);

    // 新增字典数据信息
    int addDicdata(String list, String userName);
}
