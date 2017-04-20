/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TitleServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-07
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TitleDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Electronic_Agreement_Title;
import com.cn.gazelle.logistics.service.TitleService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;

/**
 * 类 名 称：TitleServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 saveTitle                     保存协议标题信息
 *          02 updateTitle                   更新协议标题信息
 *@authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TitleService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TitleServiceImpl implements TitleService {
    // Log初始化
    Logger logger = Logger.getLogger(TransportationContractServiceImpl.class);
    @Resource
    private TitleDaoMapper titleDaoMapper;

    /**
     * 方法名称：saveTitle
     * 内容摘要：保存协议标题信息
     * @param title 协议标题信息
     */
    public int saveTitle(T_Master_Electronic_Agreement_Title title) {
        int count = 0;
        try {
            count = this.titleDaoMapper.saveTitle(title);
            if (count == 1){
                logger.info(MessageUtil.saveInfo);
            }else if (count ==0){
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(e.getMessage());
        }
        return count;

    }

    /**
     * 方法名称：updateTitle
     * 内容摘要：更新协议标题信息
     * @param title 协议标题信息
     */
    public boolean updateTitle(T_Master_Electronic_Agreement_Title title) {
        boolean b = new Boolean(true);
        try {
            this.titleDaoMapper.updateTitle(title);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }
}
