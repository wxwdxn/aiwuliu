/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：PidDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-13
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_Pid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称：PidDaoMapper
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 findPidByID                     // 根据ID查找PID信息
 *          02 findPidByCode                   // 根据PID代码值查找信息
 *          03 findAllPid                      // 查找所有PID信息
 *          04 findAllPidSearchCount           // 查找符合条件的PID总记录数
 *          05 savePid                         // 保存PID信息
 *          06 updatePid                       // 更新PID信息
 *          07 delPid                          // 删除PID信息
 *@authot YK
 */
public interface PidDaoMapper {

    // 根据ID查找PID信息
    T_Sys_Pid findPidByID(@Param("pid_id ") String pid_id);

    // 根据PID代码值查找信息
    T_Sys_Pid findPidByCode(@Param("pid_code") String pid_code, @Param("truck_type") String truck_type);

    // 查找所有PID信息
    List<T_Sys_Pid> findAllPid(@Param("pid_code") String pid_code, @Param("truck_type") String truck_type, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查找符合条件的PID总记录数
    Integer findAllPidSearchCount(@Param("pid_code") String pid_code,@Param("truck_type") String truck_type);

    // 保存PID信息
    void savePid(T_Sys_Pid pid);

    // 更新PID信息
    void updatePid(T_Sys_Pid pid);

    // 删除PID信息
    void delPid(String pid_id);

}
