/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: UserDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：用户信息查询接口实现
 * 设计文件：
 * 完成日期：2015-12-02
 * 作    者：CYH
 * 内容摘要：用户信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_FunRole;
import com.cn.gazelle.logistics.pojo.T_Sys_User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: UserDaoMapper
 * 内容摘要: 登录页的用户信息及验证码验证
 * 方法描述：该类有13个方法：
 *         01 findUserByID                  根据ID查用户信息
 *         02 findUserByName                根据用户名查找用户信息
 *         03 findFunRoleByName             根据用户登录名查找用户角色信息
 *         04 findUserByNamePassword        根据用户名和密码查询用户信息
 *         05 findAllUser                   查找所有用户信息
 *         06 findAllUserRowsCount          查询用户总记录数
 *         07 findAllUserSearchCount        查询符合条件用户总记录数
 *         08 saveUser                      保存用户信息
 *         09 updateUser                    更新用户信息
 *         10 updateUserDataRole            更新用户数据角色
 *         11 updateUserFunRole             更新用户功能角色
 *         12 updateUserUserGroup           更新用户分组信息
 *         13 delUser                        删除用户信息
 * @author CYH
 */
public interface UserDaoMapper {

    // 根据ID查询用户信息
    T_Sys_User findUserByID(@Param("user_id") String user_id);

    // 根据登录名查询用户信息
    T_Sys_User findUserByLoginName(@Param("login_name") String login_name);

    // 根据用户登录名查询角色信息
    T_Sys_FunRole findFunRoleByLoginName(@Param("login_name") String login_name);

    // 根据用户名和密码查询用户信息
    T_Sys_User findUserByNamePassword(@Param("login_name") String login_name, @Param("login_password") String login_password);

    // 查询所有用户信息
    List<T_Sys_User> findAllUser(@Param(value = "search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询用户总记录数
    Integer findAllUserRowsCount();

    // 查询符合条件的用户总记录数
    Integer findAllUserSearchCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存用户信息
    void saveUser(T_Sys_User user);

    // 更新用户信息
    void updateUser(T_Sys_User user);

    // 更新用户数据角色信息
    void updateUserDataRole(T_Sys_User user);

    // 更新用户功能角色信息
    void updateUserFunRole(T_Sys_User user);

    // 更新用户分组信息
    void updateUserUserGroup(T_Sys_User user);

    // 删除用户信息
    void delUser(String user_id);
}