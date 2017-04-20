/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TroubleCodeServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-05
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TroubleCodeDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Truck_Trouble_Code;
import com.cn.gazelle.logistics.service.TroubleCodeService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;

/**
 * 类 名 称：TroubleCodeServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01 saveTroubleCode                       // 保存故障码信息
 * 02 updateTroubleCode                     // 更新故障码信息
 * 03 delTroubleCode                        // 删除故障码信息
 *
 * @authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TroubleCodeService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TroubleCodeServiceImpl implements TroubleCodeService {
    // Log初始化
    Logger logger = Logger.getLogger(TroubleCodeServiceImpl.class);

    @Resource
    private TroubleCodeDaoMapper troubleCodeDaoMapper;              // 数据访问层


    /**
     * 方法名称：saveTroubleCode
     * 内容摘要：保存故障码信息
     *
     * @param troubleCode 故障码信息
     * @return boolean true or false
     */
    public boolean saveTroubleCode(T_Sys_Truck_Trouble_Code troubleCode) {
        boolean b = new Boolean(true);
        try {
            this.troubleCodeDaoMapper.saveTroubleCode(troubleCode);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateTroubleCode
     * 内容摘要：更新故障码信息。
     *
     * @param troubleCode 故障码信息
     * @return boolean true or false
     */
    public boolean updateTroubleCode(T_Sys_Truck_Trouble_Code troubleCode) {
        boolean b = new Boolean(true);
        try {
            this.troubleCodeDaoMapper.updateTroubleCode(troubleCode);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delTroubleCode
     * 内容摘要：删除故障码信息。
     *
     * @param code_id 故障码ID
     * @return boolean true or false
     */
    public void delTroubleCode(String code_id) {
        try {
            this.troubleCodeDaoMapper.delTroubleCode(code_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }
}
