/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: FunRoleServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：功能角色信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-4
 * 作    者：YK
 * 内容摘要：功能角色信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.FunRoleDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_FunRole;
import com.cn.gazelle.logistics.service.FunRoleService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: FunRoleServiceImpl
 * 内容摘要: 功能角色信息查询接口声明
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
@Service
@WebService(endpointInterface= "com.cn.gazelle.logistics.service.FunRoleService",targetNamespace="http://service.logistics.gazelle.cn.com/")
public class FunRoleServiceImpl implements FunRoleService {
    // Log初始化
    Logger logger = Logger.getLogger(FunRoleServiceImpl.class);

    @Resource
    private FunRoleDaoMapper roleDaoMapper;                          // 数据访问层

    /**
     * 方法名称：funList
     * 内容摘要：关联查询所有功能角色信息。
     * @return List<T_Sys_FunRole> 功能角色信息列
     */
    public List<T_Sys_FunRole> funList(){
        return this.roleDaoMapper.findAll();
    }

    /**
     * 方法名称：findFunRoleByID
     * 内容摘要：根据功能角色ID查询角色信息。
     * @param role_id 功能角色ID
     * @return T_Sys_FunRole 功能角色对象
     */
    public T_Sys_FunRole findFunRoleByID(String role_id) {
        return this.roleDaoMapper.findFunRoleByID(role_id);
    }

    /**
     * 方法名称：findAllFunRole
     * 内容摘要：查询功能角色列表有分页
     * @param role_name 功能角色名称
     * @param page 页面页数
     * @param rows 页面显示条数
     * @return List<T_Sys_FunRole> 功能角色信息list
     */
    public List<T_Sys_FunRole> findAllFunRole(String role_name, Integer page, Integer rows) {
        return this.roleDaoMapper.findAllFunRole(role_name,page,rows);
    }

    /**
     * 方法名称：findAllFunRoleNoPage
     * 内容摘要：查询功能角色无分页
     * @return  List<T_Sys_FunRole>  功能角色信息list
     */
    public List<T_Sys_FunRole> findAllFunRoleNoPage(){
       return this.roleDaoMapper.findAllFunRoleNoPage();
    }

    /**
     * 方法名称：findAllFunRoleRowsCount
     * 内容摘要：查询功能角色总记录数
     * @return  Integer  功能角色总记录数
     */
    public Integer findAllFunRoleRowsCount() {
        return this.roleDaoMapper.findAllFunRoleRowsCount();
    }

    /**
     * 方法名称：findAllFunRoleSearchCount
     * 内容摘要：根据条件查询功能角色记录总数
     * @return  Integer  功能角色总记录数
     */
    public Integer findAllFunRoleSearchCount(String role_name) {
        return this.roleDaoMapper.findAllFunRoleSearchCount(role_name);
    }

    /**
     * 方法名称：saveFunRole
     * 内容摘要：保存功能角色信息
     * @param role 功能角色信息
     */
    public boolean saveFunRole(T_Sys_FunRole role) {
        boolean b = new Boolean(true);
        try {
            this.roleDaoMapper.saveFunRole(role);
            logger.info(MessageUtil.saveInfo);
        }catch (Exception e){
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateFunRole
     * 内容摘要：更新功能角色信息
     * @param role 功能角色信息
     */
    public boolean updateFunRole(T_Sys_FunRole role) {
        boolean b = new Boolean(true);
        try {
            this.roleDaoMapper.updateFunRole(role);
            logger.info(MessageUtil.saveInfo);
        }catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delFunRole
     * 内容摘要：根据ID删除功能角色信息
     * @param role_id 功能角色id
     */
    public void delFunRole(String role_id) {
        this.roleDaoMapper.delFunRole(role_id);
    }
}
