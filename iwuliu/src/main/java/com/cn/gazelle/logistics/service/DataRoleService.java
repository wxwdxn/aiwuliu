/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DataRoleService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：数据角色信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-06
 * 作    者：YK
 * 内容摘要：数据角色信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_DataRole;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: DataRoleService
 * 内容摘要: 货场调度人员会员匹配信息查询接口
 * 方法描述：该类有8个方法：
 *         01 findDataRoleByID                 根据ID查询数据角色信息
 *         02 findAllDataRole                  查询所有数据角色信息
 *         03 findAll                          查询所有的数据角色
 *         04 findAllDataRoleRowsCount         查询数据角色总记录数
 *         05 findAllDataRoleSearchCount       查询符合条件的数据角色总记录数
 *         06 saveDataRole                     保存数据角色信息
 *         07 updateDataRole                   更新数据角色信息
 *         08 delDataRole                      删除数据角色信息
 * @author YK
 */
@WebService
public interface DataRoleService {

    // 根据ID查询数据角色信息
    public T_Sys_DataRole findDataRoleByID( String data_role_id);

    // 查询所有数据角色信息
    public List<T_Sys_DataRole> findAllDataRole( String data_role_name, Integer page, Integer rows);

    // 查询所有的数据角色
    public List<T_Sys_DataRole> findAll();

    // 查询数据角色总记录数
    public Integer findAllDataRoleRowsCount();

    // 查询符合条件的数据角色总记录数
    public Integer findAllDataRoleSearchCount( String data_role_name);

    // 保存数据角色信息
    public boolean saveDataRole(T_Sys_DataRole dataRole);

    // 更新数据角色信息
    public boolean updateDataRole(T_Sys_DataRole dataRole);

    // 删除数据角色信息
    public void delDataRole(String data_role_id);
}
