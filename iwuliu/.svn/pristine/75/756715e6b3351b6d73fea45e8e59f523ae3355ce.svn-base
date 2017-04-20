/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: FunRoleService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：功能角色信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-04
 * 作    者：YK
 * 内容摘要：功能角色信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_FunRole;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: CargoYardManagerMatchService
 * 内容摘要: 货场调度人员会员匹配信息查询接口
 * 方法描述：该类有6个方法：
 *         01 findFunRoleByID                 根据ID查询功能角色信息
 *         02 findAllFunRole                  查询所有功能角色信息有分页
 *         03 findAllFunRoleNoPage            查询所有功能角色信息无分页
 *         04 findAllFunRoleRowsCount         查询功能角色总记录数
 *         05 findAllFunRoleSearchCount       查询符合条件的功能角色总记录数
 *         06 saveFunRole                     保存功能角色信息
 *         07 updateFunRole                   更新功能角色信息
 *         08 delFunRole                      删除功能角色信息
 * @author YK
 */
@WebService
public interface FunRoleService {

    // 根据ID查询功能角色信息
    public T_Sys_FunRole findFunRoleByID(String role_id);

    // 查询所有功能角色信息有分页
    public List<T_Sys_FunRole> findAllFunRole(String role_name, Integer page, Integer rows);

    // 查询所有功能角色信息无分页
    public List<T_Sys_FunRole> findAllFunRoleNoPage();

    // 查询功能角色总记录数
    public Integer findAllFunRoleRowsCount();

    // 查询符合条件的功能角色总记录数
    public Integer findAllFunRoleSearchCount(String role_name);

    // 保存功能角色信息
    public boolean saveFunRole(T_Sys_FunRole role);

    // 更新功能角色信息
    public boolean updateFunRole(T_Sys_FunRole role);

    // 删除功能角色信息
    public void delFunRole(String role_id);
}
