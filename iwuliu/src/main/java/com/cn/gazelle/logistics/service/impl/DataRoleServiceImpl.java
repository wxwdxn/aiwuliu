/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DataRoleServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：数据角色信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-06
 * 作    者：YK
 * 内容摘要：数据角色信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DataRoleDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_DataRole;
import com.cn.gazelle.logistics.service.DataRoleService;
import com.cn.gazelle.logistics.util.message.DataRoleManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: DataRoleServiceImpl
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
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.DataRoleService", targetNamespace = "http://service.logistics.gazelle.cn.com/")

public class DataRoleServiceImpl implements DataRoleService {
    // Log初始化
    Logger logger = Logger.getLogger(DataRoleServiceImpl.class);

    @Resource
    private DataRoleDaoMapper dataRoleDaoMapper;                        // 数据访问层

    /**
     * 方法名称：findDataRoleByID
     * 内容摘要：根据ID查询数据角色信息。
     * @param data_role_id 数据角色id
     * @return String 数据角色信息json
     */
    public T_Sys_DataRole findDataRoleByID(String data_role_id) {
        T_Sys_DataRole dataRole = null;
        try {
            dataRole = this.dataRoleDaoMapper.findDataRoleByID(data_role_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dataRole;
    }

    /**
     * 方法名称：findAllDataRole
     * 内容摘要：查询所有数据角色信息
     * @param data_role_name 数据角色名称
     * @param page 页面页数
     * @param rows 页面显示条数
     * @return List<T_Sys_DataRole>  数据角色信息list
     */
    public List<T_Sys_DataRole> findAllDataRole(String data_role_name, Integer page, Integer rows) {
        List<T_Sys_DataRole> dataRoleList = null;
        try {
            dataRoleList = this.dataRoleDaoMapper.findAllDataRole(data_role_name, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dataRoleList;
    }

    /**
     * 方法名称：findAll
     * 内容摘要：查找所有的数据角色
     * @return List<T_Sys_DataRole>  数据角色列
     */
    public List<T_Sys_DataRole> findAll() {
        List<T_Sys_DataRole> dataRoleList = null;
        try {
            dataRoleList = this.dataRoleDaoMapper.findAll();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dataRoleList;
    }

    /**
     * 方法名称：findAllDataRoleRowsCount
     * 内容摘要：查询数据角色总记录数
     * @return Integer  数据角色总记录数
     */
    public Integer findAllDataRoleRowsCount() {
        int count = 0;
        try {
            count = this.dataRoleDaoMapper.findAllDataRoleRowsCount();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(DataRoleManager_Message.getSelectDataRoleCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：findAllDataRoleSearchCount
     * 内容摘要：查询符合条件的数据角色总记录数
     * @param data_role_name 数据角色名称
     * @return Integer  数据角色总记录数
     */
    public Integer findAllDataRoleSearchCount(String data_role_name) {
        int count = 0;
        try {
            count = this.dataRoleDaoMapper.findAllDataRoleSearchCount(data_role_name);
            logger.info(MessageUtil.seacheInfo);

        } catch (Exception e) {
            logger.error(DataRoleManager_Message.getSelectDataRoleCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：saveDataRole
     * 内容摘要：保存数据角色信息
     * @param dataRole 数据角色信息
     */
    public boolean saveDataRole(T_Sys_DataRole dataRole) {
        boolean b = new Boolean(true);
        try {
            this.dataRoleDaoMapper.saveDataRole(dataRole);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateDataRole
     * 内容摘要：更新数据角色信息
     * @param dataRole 数据角色信息
     */
    public boolean updateDataRole(T_Sys_DataRole dataRole) {
        boolean b = new Boolean(true);
        try {
            this.dataRoleDaoMapper.updateDataRole(dataRole);
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 方法名称：delDataRole
     * 内容摘要：删除数据角色信息
     * @param data_role_id 数据角色id
     */
    public void delDataRole(String data_role_id) {
        try {
            this.dataRoleDaoMapper.delDataRole(data_role_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }
}
