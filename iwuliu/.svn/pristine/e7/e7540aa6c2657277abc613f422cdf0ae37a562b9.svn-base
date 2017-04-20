/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: FunRoleDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：功能角色信息查询接口实现
 * 设计文件：
 * 完成日期：2016-01-04
 * 作    者：YK
 * 内容摘要：功能角色信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_FunRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: FunRoleDaoMapper
 * 内容摘要: 功能角色信息管理页面
 * 方法描述：该类有9个方法：
 *         01 findAll                                  查询所有功能角色信息
 *         02 findFunRoleByID                          根据ID查询功能角色信息
 *         03 findAllFunRole                           查询所有功能角色信息(分页)
 *         04 findAllFunRoleNoPage                     查询所有功能角色信息(无分页)
 *         05 findAllFunRoleRowsCount                  查询功能角色总记录数
 *         06 findAllFunRoleSearchCount                查询符合条件的功能角色总记录数
 *         07 saveFunRole                              保存功能角色信息
 *         08 updateFunRole                            更新功能角色信息
 *         09 delFunRole                               删除功能角色信息
 * @author YK
 */
public interface FunRoleDaoMapper {

    // 查询所有，不分页
    public List<T_Sys_FunRole> findAll();

    // 根据ID查询功能角色信息
    public T_Sys_FunRole findFunRoleByID(@Param("role_id") String role_id);

    // 查询所有功能角色信息有分页
    public List<T_Sys_FunRole> findAllFunRole(@Param("role_name") String role_name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询所有功能角色信息无分页
    public List<T_Sys_FunRole> findAllFunRoleNoPage();

    // 查询功能角色总记录数
    public Integer findAllFunRoleRowsCount();

    // 查询符合条件的功能角色总记录数
    public Integer findAllFunRoleSearchCount(@Param("role_name") String role_name);

    // 保存功能角色信息
    public void saveFunRole(T_Sys_FunRole role);

    // 更新功能角色信息
    public void updateFunRole(T_Sys_FunRole role);

    // 删除功能角色信息
    public void delFunRole(String role_id);

}