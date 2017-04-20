/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: AttributionDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车牌归属地查询接口实现
 * 设计文件：
 * 完成日期：2016-03-16
 * 作    者：QJ
 * 内容摘要：车牌归属地查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.pojo.T_Sys_Dictionary;
import org.apache.ibatis.annotations.Param;

/**
 * 类 名 称: AttributionDaoMapper
 * 内容摘要: 车牌归属地查询
 * 方法描述：该类有2个方法：
 *         01 findDicdataByDicdataName                   通过字典名称查询字典数据信息
 *         02 findAttributionByPlateNumber               根据车牌号检索车辆所属城市
 *@author YK
 */
public interface AttributionDaoMapper {
    //通过字典名称查询字典数据信息
    public T_Sys_Dicdata findDicdataByDicdataName(@Param("dicdata_name") String dicdata_name);

    //根据车牌号检索车辆所属城市(备注信息)
    public T_Sys_Dictionary findAttributionByPlateNumber(@Param("plate_number") String plate_number);
}
