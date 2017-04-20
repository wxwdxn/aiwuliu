/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CargoTypeDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物类型信息查询接口实现
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者：WXW
 * 内容摘要：货物类型信息查询接口实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 类 名 称: CargoTypeDaoMapper
 * 内容摘要: 货物类型的接口
 * 方法描述：该类有7个方法：
 *         01 addCargoType                           添加货物类型
 *         02 delCargoType                           删除货物类型
 *         03 findCaogoType                          根据id查询货物类型
 *         04 updateCargoType                        更新货物类型信息
 *         05 findAllCargoType                       查询所有货物类型信息
 *         06 findCargoTypeLists                     查询所有的货物类型列表不分页用于下拉的回显操作
 *         07 findCargoTypeRowsCount                 查询所有货物类型个数
 *         08  findCargoByName                       根据货物名称查询
 *  @author WXW
 */
public interface CargoTypeDaoMapper {
    // 添加货物类型
    int  addCargoType(T_Master_Cargo_Type cargoType);

    // 删除货物类型
    void delCargoType(@Param(value = "cargoTypeId") String cargoTypeId);

    // 根据id查询货物类型
    T_Master_Cargo_Type findCargoType(@Param(value = "cargoTypeId") String cargoTypeId);

    // 更新货物类型信息
    void updateCargoType(T_Master_Cargo_Type cargoType);


    // 查询所有的货物类型列表不分页用于下拉的回显操作
    List<T_Master_Cargo_Type> findCargoTypeLists();

}
