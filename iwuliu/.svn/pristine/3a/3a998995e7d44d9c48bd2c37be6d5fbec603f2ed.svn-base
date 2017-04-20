/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: AuthorityServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：权限信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-05
 * 作    者：YK
 * 内容摘要：权限信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.AuthorityDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Authority;
import com.cn.gazelle.logistics.service.AuthorityService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.AuthorityManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: AuthorityServiceImpl
 * 内容摘要: 权限信息查询接口
 * 方法描述：该类有7个方法：
 * 01 findAuthorityByID                 根据ID查找权限信息
 * 02 findAllAuthority                  查找所有权限信息
 * 03 findAllAuthorityRowsCount         查找权限总记录数
 * 04 findAllAuthoritySearchCount       查找符合条件的权限总记录数
 * 05 saveAuthority                     保存权限信息
 * 06 updateAuthority                   更新权限信息
 * 07 delAuthority                      删除权限信息
 *
 * @author YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.AuthorityService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class AuthorityServiceImpl implements AuthorityService {
    // Log初始化
    Logger logger = Logger.getLogger(AuthorityServiceImpl.class);

    @Resource
    private AuthorityDaoMapper authorityDaoMapper;              // 数据访问层

    /**
     * 方法名称：findAuthorityByID
     * 内容摘要：根据ID查找权限信息。
     *
     * @param authority_id 权限id
     * @return String 权限信息json
     */
    public String findAuthorityByID(String authority_id) {
        T_Sys_Authority authority = null;
        String str = null;
        try {
            authority = this.authorityDaoMapper.findAuthorityByID(authority_id);
            str = JSONUtil.toJSONString(authority);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return str;
    }

    /**
     * 方法名称：findAllAuthority
     * 内容摘要：查找所有权限信息
     *
     * @param authority_name 权限名称
     * @return List 权限信息列
     */
    public List findAllAuthority(String authority_name, Integer page, Integer rows) {
        List<T_Sys_Authority> authorityList = null;
        try {
            authorityList = this.authorityDaoMapper.findAllAuthority(authority_name, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }

        return authorityList;
    }

    /**
     * 方法名称：findAllAuthorityRowsCount
     * 内容摘要：查找权限总记录数
     *
     * @return Integer 查找权限总记录数
     */
    public Integer findAllAuthorityRowsCount() {
        int count = 0;
        try {
            count = this.authorityDaoMapper.findAllAuthorityRowsCount();
        } catch (Exception e) {
            logger.error(AuthorityManager_Message.getSelectAuthorityCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：findAllAuthoritySearchCount
     * 内容摘要：查找符合条件的权限总记录数
     *
     * @param authority_name 权限名称
     * @return Integer 权限信息
     */
    public Integer findAllAuthoritySearchCount(String authority_name) {
        int count = 0;
        try {
            count = this.authorityDaoMapper.findAllAuthoritySearchCount(authority_name);
        } catch (Exception e) {
            logger.error(AuthorityManager_Message.getSelectAuthorityCountError);
        }
        return count;
    }

    /**
     * 方法名称：saveAuthority
     * 内容摘要：保存权限信息
     *
     * @param authority 权限信息
     */
    public int saveAuthority(T_Sys_Authority authority) {
        int count = 0;
        try {
            count = this.authorityDaoMapper.saveAuthority(authority);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateAuthority
     * 内容摘要：更新权限信息
     *
     * @param authority 权限信息
     */
    public boolean updateAuthority(T_Sys_Authority authority) {
        boolean b = new Boolean(true);
        try {
            this.authorityDaoMapper.updateAuthority(authority);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delAuthority
     * 内容摘要：删除权限信息
     *
     * @param authority_id 权限id
     */
    public void delAuthority(String authority_id) {
        try {
            this.authorityDaoMapper.delAuthority(authority_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }

    }
}
