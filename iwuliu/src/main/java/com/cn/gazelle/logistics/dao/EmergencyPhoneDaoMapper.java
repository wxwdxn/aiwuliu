/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：EmergencyPhoneDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-24
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Emergency_Telephone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称：EmergencyPhoneDaoMapper
 * 内容描述：
 * 方法描述：该类有 7 个方法
 *          01 findEmergencyPhoneByID                            // 根据ID查找紧急电话信息
 *          02 findEmergencyPhoneByType                          // 根据紧急电话类型查找信息
 *          03 findAllEmergencyPhone                             // 查找所有紧急电话信息
 *          04 findAllEmergencyPhoneSearchCount                  // 查找符合条件的紧急电话总记录数
 *          05 saveEmergencyPhone                                // 保存紧急电话信息
 *          06 updateEmergencyPhone                              // 更新紧急电话信息
 *          07 delEmergencyPhone                                 // 删除紧急电话信息
 *@authot YK
 */
public interface EmergencyPhoneDaoMapper {

    //根据ID查找紧急电话信息
    T_Data_Emergency_Telephone findEmergencyPhoneByID(@Param("emergency_telephone_id") String emergency_telephone_id);

    // 根据紧急电话类型查找信息
    T_Data_Emergency_Telephone findEmergencyPhoneByType(@Param("emergency_telephone_type") String emergency_telephone_type);

    // 查找所有紧急电话信息
    List<T_Data_Emergency_Telephone> findAllEmergencyPhone(@Param("emergency_telephone_type") String emergency_telephone_type, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查找符合条件的紧急电话总记录数
    Integer findAllEmergencyPhoneSearchCount(@Param("emergency_telephone_type") String emergency_telephone_type);

    // 保存紧急电话信息
    void saveEmergencyPhone(T_Data_Emergency_Telephone telephone);

    // 更新紧急电话信息
    void updateEmergencyPhone(T_Data_Emergency_Telephone telephone);

    // 删除紧急电话信息
    void delEmergencyPhone(String emergency_telephone_id);
}