/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: UserGroupService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：用户组信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-04
 * 作    者：YK
 * 内容摘要：用户组信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_UserGroup;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: UserGroupService
 * 内容摘要: 用户组信息查询接口
 * 方法描述：该类有9个方法：
 *         01 findUserGroupByID                  根据ID查找用户组信息
 *         02 findAllUserGroup                   查询所有用户组信息
 *         03 findAllGroupName                   查询所有的用户组名称
 *         04 findUserOfGroup                    查询用户组所属用户
 *         05 findAllUserGroupRowsCount          查询用户组总记录数
 *         06 findAllUserGroupSearchCount        查询符合条件的用户组总记录数
 *         07 saveUserGroup                      保存用户组信息
 *         08 updateUserGroup                    更新用户组信息
 *         09 delUserGroup                       删除用户组信息
 * @author YK
 */
@WebService
public interface UserGroupService {

    // 根据ID查找用户组信息
    public String findUserGroupByID(String group_id);

    // 查询所有用户组信息
    public List findAllUserGroup(String group_name, Integer page, Integer rows);

    // 查询所有的用户组名称
    public List findAllGroupName();

    //查询用户组所属用户
    public T_Sys_UserGroup findUserOfGroup(String group_name);

    // 查询用户组总记录数
    public Integer findAllUserGroupRowsCount();

    // 查询符合条件的用户组总记录数
    public Integer findAllUserGroupSearchCount(String group_name);

    // 保存用户组信息
    public boolean saveUserGroup(T_Sys_UserGroup userGroup);

    // 更新用户组信息
    public boolean updateUserGroup(T_Sys_UserGroup userGroup);

    // 删除用户组信息
    public void delUserGroup(String group_id);
}
