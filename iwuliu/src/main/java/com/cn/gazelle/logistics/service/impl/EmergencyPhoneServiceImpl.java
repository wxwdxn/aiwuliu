/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：EmergencyPhoneServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-24
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.EmergencyPhoneDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Emergency_Telephone;
import com.cn.gazelle.logistics.service.EmergencyPhoneService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称：EmergencyPhoneServiceImpl
 * 内容描述：
 * 方法描述：该类有 7 个方法
 * 01 findEmergencyPhoneByID                            // 根据ID查找紧急电话信息
 * 02 findEmergencyPhoneByType                          // 根据紧急电话类型查找信息
 * 03 findAllEmergencyPhone                             // 查找所有紧急电话信息
 * 04 findAllEmergencyPhoneSearchCount                  // 查找符合条件的紧急电话总记录数
 * 05 saveEmergencyPhone                                // 保存紧急电话信息
 * 06 updateEmergencyPhone                              // 更新紧急电话信息
 * 07 delEmergencyPhone                                 // 删除紧急电话信息
 *
 * @authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.EmergencyPhoneService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class EmergencyPhoneServiceImpl implements EmergencyPhoneService {

    // Log初始化
    Logger logger = Logger.getLogger(EmergencyPhoneServiceImpl.class);

    @Resource
    private EmergencyPhoneDaoMapper emergencyPhoneDaoMapper;              // 数据访问层

    /**
     * 方法名称：findEmergencyPhoneByID
     * 内容摘要：根据ID查找紧急电话信息。
     *
     * @param emergency_telephone_id 紧急电话id
     * @return String 紧急电话信息json
     */
    public T_Data_Emergency_Telephone findEmergencyPhoneByID(String emergency_telephone_id) {
        T_Data_Emergency_Telephone telephone = null;
        try {
            telephone = this.emergencyPhoneDaoMapper.findEmergencyPhoneByID(emergency_telephone_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return telephone;
    }

    /**
     * 方法名称：findEmergencyPhoneByType
     * 内容摘要：根据紧急电话类型查找信息
     *
     * @param emergency_telephone_type 紧急电话名称
     * @return List 紧急电话信息列
     */
    public T_Data_Emergency_Telephone findEmergencyPhoneByType(String emergency_telephone_type) {
        T_Data_Emergency_Telephone telephone = null;
        try {
            telephone = this.emergencyPhoneDaoMapper.findEmergencyPhoneByType(emergency_telephone_type);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }

        return telephone;
    }

    /**
     * 方法名称：findAllEmergencyPhone
     * 内容摘要：查找所有紧急电话信息
     *
     * @param emergency_telephone_type 紧急电话
     * @return List 紧急电话信息列
     */
    public List findAllEmergencyPhone(String emergency_telephone_type, Integer page, Integer rows) {
        List<T_Data_Emergency_Telephone> telephoneList = null;
        try {
            telephoneList = this.emergencyPhoneDaoMapper.findAllEmergencyPhone(emergency_telephone_type, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }

        return telephoneList;
    }


    /**
     * 方法名称：findAllEmergencyPhoneSearchCount
     * 内容摘要：查找符合条件的紧急电话总记录数
     *
     * @return Integer 查找紧急电话总记录数
     */
    public Integer findAllEmergencyPhoneSearchCount(String emergency_telephone_type) {
        int count = 0;
        try {
            count = this.emergencyPhoneDaoMapper.findAllEmergencyPhoneSearchCount(emergency_telephone_type);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：saveEmergencyPhone
     * 内容摘要：保存紧急电话信息
     *
     * @param telephone 紧急电话信息
     */
    public boolean saveEmergencyPhone(T_Data_Emergency_Telephone telephone) {
        boolean b = new Boolean(true);
        try {
            this.emergencyPhoneDaoMapper.saveEmergencyPhone(telephone);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateEmergencyPhone
     * 内容摘要：更新紧急电话信息
     *
     * @param telephone 紧急电话信息
     */
    public boolean updateEmergencyPhone(T_Data_Emergency_Telephone telephone) {
        boolean b = new Boolean(true);
        try {
            this.emergencyPhoneDaoMapper.updateEmergencyPhone(telephone);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delEmergencyPhone
     * 内容摘要：删除紧急电话信息
     *
     * @param emergency_telephone_id 紧急电话id
     */
    public void delEmergencyPhone(String emergency_telephone_id) {
        try {
            this.emergencyPhoneDaoMapper.delEmergencyPhone(emergency_telephone_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }

    }

}
