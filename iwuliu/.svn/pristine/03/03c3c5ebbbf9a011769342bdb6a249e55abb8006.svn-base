/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: UserGroupDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：用户组信息查询接口实现
 * 设计文件：
 * 完成日期：2016-01-04
 * 作    者：YK
 * 内容摘要：用户组信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_UserGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: MenuDaoMapper
 * 内容摘要: 菜单信息管理页面
 * 方法描述：该类有7个方法：
 *         01 findUserGroupByID                           根据ID查找用户组信息
 *         02 findUserGroupByGroupName                    根据组名查询用户组信息
 *         03 findAllUserGroup                            查询所有用户组信息
 *         04 findAllGroupName                            查询所有的用户组名称
 *         05 findUserOfGroup                             查询用户组所属用户
 *         06 findAllUserGroupRowsCount                   查询用户组总记录数
 *         07 findAllUserGroupSearchCount                 查询符合条件的用户组总记录数
 *         08 saveUserGroup                               保存用户组信息
 *         09 updateUserGroup                             更新用户组信息
 *         10 delUserGroup                                删除用户组信息
 * @author YK
 */
public interface UserGroupDaoMapper {

    // 根据ID查找用户组信息
    public T_Sys_UserGroup findUserGroupByID(@Param("group_id") String group_id);

    // 根据组名查询用户组信息
    public T_Sys_UserGroup findUserGroupByGroupName(@Param("group_name") String group_name);

    // 查询所有用户组信息
    public List<T_Sys_UserGroup> findAllUserGroup(@Param("group_name") String group_name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询所有的用户组名称
    public List<T_Sys_UserGroup> findAllGroupName();

    // 查询用户组所属用户
    public T_Sys_UserGroup findUserOfGroup(@Param("group_name") String group_name);

    // 查询用户组总记录数
    public Integer findAllUserGroupRowsCount();

    // 查询符合条件的用户组总记录数
    public Integer findAllUserGroupSearchCount(@Param("group_name") String group_name);

    // 保存用户组信息
    public void saveUserGroup(T_Sys_UserGroup userGroup);

    // 更新用户组信息
    public void updateUserGroup(T_Sys_UserGroup userGroup);

    // 删除用户组信息
    public void delUserGroup(String group_id);
}
