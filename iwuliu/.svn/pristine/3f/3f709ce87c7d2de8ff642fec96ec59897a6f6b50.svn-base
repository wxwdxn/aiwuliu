/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: AttributionService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆归属地查询接口声明
 * 设计文件：
 * 完成日期：2016-03-16
 * 作    者：QJ
 * 内容摘要：车辆归属地查询
 */

package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;

import javax.jws.WebService;

/**
 * 类 名 称: AttributionService
 * 内容摘要: 车辆归属地查询接口实现
 * 方法描述：该类有2个方法：
 *         01 findDicdataByDicdataName              通过字典名称查询字典数据信息
 *         02 findAttributionByPlateNumber          根据车牌号检索车辆所属城市(备注信息)
 * @author QJ
 */
@WebService
public interface AttributionService {
    // 通过字典名称查询字典数据信息
    public T_Sys_Dicdata findDicdataByDicdataName(String dicdata_name);

    // 根据车牌号检索车辆所属城市(备注信息)
    public String findAttributionByPlateNumber(String plate_number);
}
