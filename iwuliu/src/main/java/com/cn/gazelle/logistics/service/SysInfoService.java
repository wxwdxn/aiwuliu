/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：SysInfoService.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-23
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Sys_Info;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称：SysInfoService
 * 内容描述：
 * 方法描述：该类有 3 个方法
 *          01 updateSysInfo           // 更新系统信息
 *          02 findAll                 // 查找所有系统信息
 *          03 findSysInfo             // 查找客服电话
 *@authot YK
 */
@WebService
public interface SysInfoService {

    // 更新系统信息
    boolean updateSysInfo(T_Data_Sys_Info info);

    // 查找所有系统信息
    T_Data_Sys_Info findAll();

    // 查找客服电话
    String findSysInfo();

}
