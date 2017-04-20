/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TitleService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-07
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Electronic_Agreement_Title;

import javax.jws.WebService;

/**
 * 类 名 称：TitleService
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 saveTitle                     保存协议标题信息
 *          02 updateTitle                   更新协议标题信息
 *@authot YK
 */
@WebService
public interface TitleService {
    // 保存协议标题信息
    int saveTitle(T_Master_Electronic_Agreement_Title title);

    // 更新协议标题信息
    boolean updateTitle(T_Master_Electronic_Agreement_Title title);
}
