/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：PidServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-13
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.PidDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Pid;
import com.cn.gazelle.logistics.service.PidService;
import com.cn.gazelle.logistics.util.TypeConversionUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;

/**
 * 类 名 称：PidServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.PidService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class PidServiceImpl implements PidService{
    // Log初始化
    Logger logger = Logger.getLogger(PidServiceImpl.class);

    @Resource
    private PidDaoMapper pidDaoMapper;              // 数据访问层


    /**
     * 方法名称：savePid
     * 内容摘要：保存PID信息
     * @param pid PID信息
     * @return boolean true or false
     */
    public boolean savePid(T_Sys_Pid pid) {
        boolean b = new Boolean(true);
        try {
            this.pidDaoMapper.savePid(pid);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updatePid
     * 内容摘要：更新PID信息。
     * @param pid PID信息
     * @return boolean true or false
     */
    public boolean updatePid(T_Sys_Pid pid) {
        boolean b = new Boolean(true);
        try {
            this.pidDaoMapper.updatePid(pid);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delPid
     * 内容摘要：删除PID信息。
     * @param pid_id PIDID
     * @return boolean true or false
     */
    public void delPid(String pid_id) {
        try {
            this.pidDaoMapper.delPid(pid_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：convertDataByPIID
     * 内容摘要：通过pid转化。
     *
     * @param pid_code pid_code
     * @param data     data
     * @return String
     */

    public String convertDataByPID(String pid_code, String data) {
        int dataLen = 0; // 数据长度
        T_Sys_Pid pid = null;
        String json = null;

        // 先查找商用车pid_code对应的数据长度
        pid = this.pidDaoMapper.findPidByCode(pid_code, "1");
        if (pid!=null){
            dataLen = Integer.parseInt(pid.getPid_size());
            if (data != null && !data.equals("")) {
                // 判断数据长度是否为1
                if(dataLen == 1){
                    json = Integer.parseInt(data,16) + "";

                }
                else {
                    if (pid.getPid_data_type().equals("Float")){
                        json = TypeConversionUtil.hexStr2Float(TypeConversionUtil.hexStringReverse(data));
                    }
                    else {
                        json = Integer.parseInt(TypeConversionUtil.hexStringReverse(data),16) + "";
                    }
                }
            }
            else{
                json = dataLen + "";
            }

        }else {
            pid = this.pidDaoMapper.findPidByCode(pid_code, "0");
            dataLen = Integer.parseInt(pid.getPid_size());
            if (data != null && !data.equals("")) {
                // 判断数据长度是否为1
                if(dataLen == 1){
                    json = Integer.parseInt(data,16) + "";

                }
                else {
                    if (pid.getPid_data_type().equals("Float")){
                        json = TypeConversionUtil.hexStr2Float(TypeConversionUtil.hexStringReverse(data));
                    }
                    else {
                        json = Integer.parseInt(TypeConversionUtil.hexStringReverse(data),16) + "";
                    }
                }
            }
            else{
                json = dataLen + "";
            }
        }


        return json;
    }

}
