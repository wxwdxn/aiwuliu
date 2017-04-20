/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TroubleCodeDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-05
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_Truck_Trouble_Code;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称：TroubleCodeDaoMapper
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 findTroubleCodeByID                     // 根据ID查找故障码信息
 *          02 findTroubleCodeByCode                   // 根据故障码代码值查找信息
 *          03 findAllTroubleCode                      // 查找所有故障码信息
 *          04 findAllTroubleCodeSearchCount           // 查找符合条件的故障码总记录数
 *          05 saveTroubleCode                         // 保存故障码信息
 *          06 updateTroubleCode                       // 更新故障码信息
 *          07 delTroubleCode                          // 删除故障码信息
 *@authot YK
 */
public interface TroubleCodeDaoMapper {

    // 根据ID查找故障码信息
    T_Sys_Truck_Trouble_Code findTroubleCodeByID(@Param("code_id ") String code_id);

    // 根据故障码代码值查找信息
    T_Sys_Truck_Trouble_Code findTroubleCodeByCode(@Param("trouble_code ") String trouble_code,@Param("truck_type") String truck_type);

    // 查找所有故障码信息
    List<T_Sys_Truck_Trouble_Code> findAllTroubleCode(@Param("trouble_code") String trouble_code, @Param("truck_type") String truck_type,@Param("page") Integer page, @Param("rows") Integer rows);

    // 查找符合条件的故障码总记录数
    Integer findAllTroubleCodeSearchCount(@Param("trouble_code") String trouble_code,@Param("truck_type") String truck_type);

    // 保存故障码信息
    void saveTroubleCode(T_Sys_Truck_Trouble_Code troubleCode);

    // 更新故障码信息
    void updateTroubleCode(T_Sys_Truck_Trouble_Code troubleCode);

    // 删除故障码信息
    void delTroubleCode(String code_id);

}
