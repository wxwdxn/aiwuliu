/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: UserGroupServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：用户组信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-04
 * 作    者：YK
 * 内容摘要：用户组信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.UserGroupDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_UserGroup;
import com.cn.gazelle.logistics.service.UserGroupService;
import com.cn.gazelle.logistics.util.JSONUtil;

import com.cn.gazelle.logistics.util.message.UserGroupManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: UserGroupServiceImpl
 * 内容摘要: 用户组信息查询接口
 * 方法描述：该类有9个方法：
 *          01 findUserGroupByID                  根据ID查找用户组信息
 *          02 findAllUserGroup                   查询所有用户组信息
 *          03 findAllGroupName                   查询所有的用户组名称
 *          04 findUserOfGroup                    查询用户组所属用户
 *          05 findAllUserGroupRowsCount          查询用户组总记录数
 *          06 findAllUserGroupSearchCount        查询符合条件的用户组总记录数
 *          07 saveUserGroup                      保存用户组信息
 *          08 updateUserGroup                    更新用户组信息
 *          09 delUserGroup                       删除用户组信息
 * @author YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.UserGroupService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class UserGroupServiceImpl implements UserGroupService {
    Logger logger = Logger.getLogger(UserGroupServiceImpl.class);
    // Log初始化
    @Resource
    private UserGroupDaoMapper userGroupDaoMapper;                            // 数据访问层

    /**
     * 方法名称：findUserGroupByID
     * 内容摘要：根据ID查找用户组信息。
     * @param group_id 用户组id
     * @return String 用户组信息json
     */
    public String findUserGroupByID(String group_id) {
        T_Sys_UserGroup userGroup = null;
        String str = null;
        try {
            userGroup = this.userGroupDaoMapper.findUserGroupByID(group_id);
            str = JSONUtil.toJSONString(userGroup);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return str;
    }

    /**
     * 方法名称：findAllUserGroup
     * 内容摘要：查询所有用户组信息。
     * @param group_name 用户组名称
     * @param page       页面页数
     * @param rows       页面显示条数
     * @return List 用户组信息列
     */
    public List findAllUserGroup(String group_name, Integer page, Integer rows) {
        List<T_Sys_UserGroup> userGroupList = null;
        try {
            userGroupList = this.userGroupDaoMapper.findAllUserGroup(group_name, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return userGroupList;
    }

    /**
     * 方法名称：findAllGroupName
     * 内容摘要：查询所有的用户组组名
     * @return List  公司名称json
     */
    public List findAllGroupName() {
        List<T_Sys_UserGroup> userGroupList = null;
        try {
            userGroupList = this.userGroupDaoMapper.findAllGroupName();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return userGroupList;
    }

    /**
     * 方法名称：findUserOfGroup
     * 内容摘要：查询用户组所属用户信息
     * @return T_Sys_UserGroup  用户组信息
     */
    public T_Sys_UserGroup findUserOfGroup(String group_name) {
        T_Sys_UserGroup userGroup = null;
        try {
            userGroup = this.userGroupDaoMapper.findUserOfGroup(group_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return userGroup;
    }

    /**
     * 方法名称：findAllUserGroupRowsCount
     * 内容摘要：查询用户组总记录数
     * @return Integer  用户组总记录数
     */
    public Integer findAllUserGroupRowsCount() {
        int count = 0;
        try {
            count = this.userGroupDaoMapper.findAllUserGroupRowsCount();
        } catch (Exception e) {
            logger.error(UserGroupManager_Message.getSelectUserGroupCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：findAllUserGroupSearchCount
     * 内容摘要：查询符合条件的用户组总记录数
     * @return Integer  用户组总记录数
     */
    public Integer findAllUserGroupSearchCount(String group_name) {
        int count = 0;
        try {
            count = this.userGroupDaoMapper.findAllUserGroupSearchCount(group_name);
        } catch (Exception e) {
            logger.error(UserGroupManager_Message.getSelectUserGroupCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：saveUserGroup
     * 内容摘要：保存用户组信息
     * @param userGroup 企业名称
     */
    public boolean saveUserGroup(T_Sys_UserGroup userGroup) {
        boolean b = new Boolean(true);
        try {
            this.userGroupDaoMapper.saveUserGroup(userGroup);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateUserGroup
     * 内容摘要：更新用户组信息
     * @param userGroup 用户组信息
     */
    public boolean updateUserGroup(T_Sys_UserGroup userGroup) {
        boolean b = new Boolean(true);

        try {
            this.userGroupDaoMapper.updateUserGroup(userGroup);

        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;

    }

    /**
     * 方法名称：delUserGroup
     * 内容摘要：删除用户组信息
     * @param group_id 用户组id
     */
    public void delUserGroup(String group_id) {
        try {
            this.userGroupDaoMapper.delUserGroup(group_id);

        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }


    }
}
