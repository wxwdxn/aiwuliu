/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: UserServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：用户信息查询接口实现
 * 设计文件：
 * 完成日期：2015-12-02
 * 作    者：CYH
 * 内容摘要：用户信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.UserDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_FunRole;
import com.cn.gazelle.logistics.pojo.T_Sys_User;
import com.cn.gazelle.logistics.service.UserService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: UserServiceImpl
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
@Service
@WebService(endpointInterface="com.cn.gazelle.logistics.service.UserService",targetNamespace="http://service.logistics.gazelle.cn.com/")
public class UserServiceImpl implements UserService {
    // Log初始化
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserDaoMapper userDaoMapper;                 // 数据访问层

    /**
     * 方法名称：findUserByID
     * 内容摘要：根据id查找用户信息。
     * @param user_id 用户id
     * @return T_Sys_User 用户信息
     */
    public T_Sys_User findUserByID(String user_id) {
        T_Sys_User user = null;         // 用户信息
        try{
            user = this.userDaoMapper.findUserByID(user_id);
            logger.info(MessageUtil.seacheInfo);
        }catch (Exception e){
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return user;
    }

    /**
     * 方法名称：findUserByName
     * 内容摘要：根据用户名查找用户
     * @param user_name 用户名
     * @return T_Sys_User 用户信息
     */
    public T_Sys_User findUserByName(String user_name){
        return this.userDaoMapper.findUserByLoginName(user_name);
    }

    /**
     * 方法名称：findFunRoleByName
     * 内容摘要：根据用户名查找用户角色信息
     * @param user_name 用户名
     * @return T_Sys_User 用户信息
     */
    public T_Sys_FunRole findFunRoleByName(String user_name){
        return this.userDaoMapper.findFunRoleByLoginName(user_name);
    }

    /**
     * 方法名称：findUserByNamePassword
     * 内容摘要：根据用户名密码查找
     * @param login_name 用户名
     * @param login_password 登录密码
     * @return T_Sys_User 用户信息
     */
    public T_Sys_User findUserByNamePassword(String login_name,String login_password){
        T_Sys_User user = null;         // 用户信息
        try{
            user = this.userDaoMapper.findUserByNamePassword(login_name,login_password);
        }catch (Exception e){
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return user;
    }

    /**
     * 方法名称：findAllUser
     * 内容摘要：查找所有用户
     * @param search_type 用户类型
     * @param name 用户名
     * @param page 页码
     * @param rows 行数
     * @return List 用户信息列表
     */
    public List findAllUser(String search_type,String name,Integer page,Integer rows){
        List<T_Sys_User> users = null;          // 用户信息
        try{
            users = this.userDaoMapper.findAllUser(search_type,name,page,rows);
            logger.info(MessageUtil.seacheInfo);
        }catch (Exception e){
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return users;
    }

    /**
     * 方法名称：findAllUserRowsCount
     * 内容摘要：查询用户总记录数
     * @return Int 用户总记录数
     */
    public Integer findAllUserRowsCount(){
        int usrCount = 0;
        try{
            usrCount = this.userDaoMapper.findAllUserRowsCount();
        }catch (Exception e){
            logger.error(MessageUtil.getSelectUserCountError);
        }
        return usrCount;
    }

    /**
     * 方法名称：findAllUserSearchCount
     * 内容摘要：查询符合条件用户总记录数
     * @param search_type 用户类型
     * @param name        用户名
     * @return List 用户信息列表
     */
    public Integer findAllUserSearchCount(String search_type,String name) {
        int usrCount = 0;
        try{
            usrCount = this.userDaoMapper.findAllUserSearchCount(search_type,name);
        }catch (Exception e){
            logger.error(MessageUtil.getSelectUserCountError);
        }
        return usrCount;
    }

    /**
     * 方法名称：saveUser
     * 内容摘要：保存用户信息
     * @param user 用户信息
     */
    public boolean saveUser(T_Sys_User user){
        boolean b = new Boolean(true);
        try{
            this.userDaoMapper.saveUser(user);
            logger.info(MessageUtil.saveInfo);
        }catch (Exception e){
            b = false;
            logger.error(MessageUtil.saveInfoError+e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateUser
     * 内容摘要：更新用户信息
     * @param user 用户信息
     */
    public boolean updateUser(T_Sys_User user){
        boolean b = new Boolean(true);
        try{
            this.userDaoMapper.updateUser(user);
        }catch (Exception e){
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateUserDataRole
     * 内容摘要：更新用户数据角色
     * @param user 用户信息
     */
    public boolean updateUserDataRole(T_Sys_User user){
        boolean b = new Boolean(true);
        try{
            this.userDaoMapper.updateUserDataRole(user);
        }catch (Exception e){
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateUserFunRole
     * 内容摘要：更新用户功能角色
     * @param user 用户信息
     */
    public boolean updateUserFunRole(T_Sys_User user){
        boolean b = new Boolean(true);
        try{
            this.userDaoMapper.updateUserFunRole(user);
        }catch (Exception e){
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateUserUserGroup
     * 内容摘要：更新用户分组信息
     * @param user 用户信息
     */
    public boolean updateUserUserGroup(T_Sys_User user){
        boolean b = new Boolean(true);
        try{
            this.userDaoMapper.updateUserUserGroup(user);
        }catch (Exception e){
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：user_id
     * 内容摘要：删除用户信息
     * @param user_id 用户信息
     */
    public void delUser(String user_id){
        try{
            this.userDaoMapper.delUser(user_id);
            logger.info(MessageUtil.delInfo);
        }catch (Exception e){
            logger.error(MessageUtil.delInfoError+e.getMessage());
        }
    }
}