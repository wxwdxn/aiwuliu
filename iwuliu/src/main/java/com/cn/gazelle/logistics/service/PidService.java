/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：Service.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-13
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_Pid;

import javax.jws.WebService;

/**
 * 类 名 称：Service
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 savePid                       // 保存信息
 *          02 updatePid                     // 更新信息
 *          03 delPid                        // 删除信息
 *@authot YK
 */
@WebService
public interface PidService {
    // 保存信息
    boolean savePid(T_Sys_Pid pid);

    // 更新信息
    boolean updatePid(T_Sys_Pid pid);

    // 删除信息
    void delPid(String pid_id);

    // 根据PID号转换数据
    String convertDataByPID(String pid_code,String data);
    
}
