/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：SysInfoServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-23
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.SysInfoDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Sys_Info;
import com.cn.gazelle.logistics.service.SysInfoService;
import com.cn.gazelle.logistics.util.JSONUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类 名 称：SysInfoServiceImpl
 * 内容描述：
 * 方法描述：该类有 2 个方法
 *          01 updateSysInfo           // 更新系统信息
 *          02 findAll                 // 查找所有系统信息
 *          02 findSysInfo             // 查找客服电话
 *@authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.SysInfoService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class SysInfoServiceImpl implements SysInfoService {
    // Log初始化
    Logger logger = Logger.getLogger(StationServiceImpl.class);

    @Resource
    private SysInfoDaoMapper sysInfoDaoMapper;          // 数据访问层

    /**
     * 方法名称：updateSysInfo
     * 内容摘要：更新系统信息
     * @param info 系统信息
     * @return boolean true或false
     */
    public boolean updateSysInfo(T_Data_Sys_Info info) {
        boolean b = new Boolean(true);
        try {
            this.sysInfoDaoMapper.updateSysInfo(info);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：findAll
     * 内容摘要：查找所有系统信息
     * @return T_Data_Sys_Info
     */
    public T_Data_Sys_Info findAll() {
        T_Data_Sys_Info sysInfo = null;
        try {
            sysInfo = this.sysInfoDaoMapper.findSysInfo();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return sysInfo;
    }

    /**
     * 方法名称：findSysInfo
     * 内容摘要：查找客服电话
     *  @return String json
     */
    public String findSysInfo() {
        T_Data_Sys_Info info = null;
        String ecode = null; // 代码值
        String member_server_telephone = null;
        Map result = new HashMap();
        try {
            info = this.sysInfoDaoMapper.findSysInfo();
            ecode = "1000";
            member_server_telephone = info.getMember_server_telephone();
            result.put("ecode",ecode);
            result.put("member_server_telephone",member_server_telephone);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode);
            logger.error(e.getMessage());
        }
        return JSONUtil.toJSONString(result);
    }
}
