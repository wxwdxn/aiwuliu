/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CargoYardManagerDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货站管理人员信息查询接口实现
 * 设计文件：
 * 完成日期：2016-02-24
 * 作    者：YK
 * 内容摘要：货站管理人员信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Cargo_Yard_Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: CargoYardManagerDaoMapper
 * 内容摘要: 货站管理人员信息管理页面
 * 方法描述：该类有7个方法：
 *         01 findManagerByID                          根据货站管理人员id查询企业信息
 *         02 findManagerByName                        根据货站管理人员名查询企业信息
 *         03 findAllManager                           查询所有货站管理人员信息
 *         04 findAllManagerRowsCount                  查询货站管理人员总记录数
 *         05 saveManager                              保存货站管理人员信息
 *         06 updateManager                            更新货站管理人员信息
 *         07 delManager                               删除货站管理人员信息
 * @author YK
 */
public interface CargoYardManagerDaoMapper {

    // 根据货站管理人员id查询企业信息
    T_Data_Cargo_Yard_Manager findManagerByID(String manager_id);

    // 查询所有货站管理人员信息
    List<T_Data_Cargo_Yard_Manager> findAllManager(@Param(value = "searchType") String searchType, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询货站管理人员总记录数
    Integer findAllManagerRowsCount(@Param(value = "searchType") String searchType, @Param(value = "name") String name);

    // 保存货站管理人员信息
    void saveManager(T_Data_Cargo_Yard_Manager manager);

    // 更新货站管理人员信息
    void updateManager(T_Data_Cargo_Yard_Manager manager);

    // 删除货站管理人员信息
    void delManager(@Param(value = "manager_id") String manager_id);
}
