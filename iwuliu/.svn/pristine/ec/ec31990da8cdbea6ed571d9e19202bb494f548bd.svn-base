/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ContentService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：会员电子协议内容基础信息service层
 * 设计文件：
 * 完成日期：2016-05-18
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Electronic_Agreement_Content;

import javax.jws.WebService;

/**
 * 类 名 称：ContentService
 * 内容描述：会员电子协议内容基础信息setvice层
 * 方法描述：该类有 个方法
 *          01 saveContent                          保存协议内容信息
 *          02 updateContent                        更新协议内容信息
 *          03 getAgreementContent                  获取注册协议
 *          04 judgeAgreementContent                判断是否是最新协议
 *          05 signAgreementContent                 签订电子协议
 *@authot YK
 */
@WebService
public interface ContentService {

    // 保存协议内容信息
    int saveContent(T_Master_Electronic_Agreement_Content content);

    // 更新协议内容信息
    boolean updateContent(T_Master_Electronic_Agreement_Content content);

    // 获取注册协议
    String getAgreementContent(String member_type);

    // 判断是否是最新协议
    String judgeAgreementContent(String member_name);

    // 签订电子协议
    String signAgreementContent(String member_name, String electronic_agreement_version);

}
