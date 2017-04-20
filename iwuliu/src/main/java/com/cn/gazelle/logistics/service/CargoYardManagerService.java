/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CargoYardManagerService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货站管理人员信息查询接口声明
 * 设计文件：
 * 完成日期：2016-02-24
 * 作    者：YK
 * 内容摘要：货站管理人员信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Cargo_Yard_Manager;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: CargoYardManagerService
 * 内容摘要: 货站管理人员信息查询接口声明
 * 方法描述：该类有7个方法：
 *         01 findManagerByID                    根据货站管理人员id查询企业信息
 *         02 findManagerByName                  根据货站管理人员名查询企业信息
 *         03 findAllManager                     查询所有货站管理人员信息
 *         04 findAllManagerRowsCount            查询货站管理人员总记录数
 *         05 saveManager                        保存货站管理人员信息
 *         06 updateManager                      更新货站管理人员信息
 *         07 delManager                         删除货站管理人员信息
 * @author YK
 */
@WebService
public interface CargoYardManagerService {

    // 根据货站管理人员id查询企业信息
    String findManagerByID(String manager_id);

    // 根据货站管理人员名查询企业信息

    // 查询所有货站管理人员信息
    List<T_Data_Cargo_Yard_Manager> findAllManager(String search_type, String name, Integer page, Integer rows);

    // 查询货站管理人员总记录数
    Integer findAllManagerRowsCount(String search_type, String name);

    // 保存货站管理人员信息
    boolean saveManager(T_Data_Cargo_Yard_Manager manager);

    // 更新货站管理人员信息
    boolean updateManager(T_Data_Cargo_Yard_Manager manager);

    // 删除货站管理人员信息
    void delManager(String manager_id);
}
