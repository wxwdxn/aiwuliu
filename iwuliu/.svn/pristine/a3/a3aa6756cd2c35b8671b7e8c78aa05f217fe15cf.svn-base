/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：SubLineInfoServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-06-22
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.SubLineInfoDaoMapper;
import com.cn.gazelle.logistics.dao.SubLineNodeDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Detail;
import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Info;
import com.cn.gazelle.logistics.service.SubLineInfoService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称：SubLineInfoServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 saveSubLineInfo                               // 保存干线路线信息
 *          02 updateSubLineInfo                             // 更新干线路线信息
 *@authot YK
 */
@Service
@WebService(endpointInterface= "com.cn.gazelle.logistics.service.SubLineInfoService",targetNamespace="http://service.logistics.gazelle.cn.com/")
public class SubLineInfoServiceImpl implements SubLineInfoService {
    // Log初始化
    Logger logger = Logger.getLogger(SubLineInfoServiceImpl.class);

    @Resource
    private SubLineInfoDaoMapper subLineInfoDaoMapper;
    @Resource
    private SubLineNodeDaoMapper subLineNodeDaoMapper;

    /**
     * 方法名称：saveSubLineInfo
     * 内容摘要：保存干线路线信息
     * @param lineInfo 线路线信息
     * @return boolean 保存true或false
     */
    public int saveSubLineInfo(T_Master_Sub_Line_Info lineInfo) {
        int count = 0;
        try {
            count = this.subLineInfoDaoMapper.saveSubLineInfo(lineInfo);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(e.getMessage());
        }
        return count;

    }

    /**
     * 方法名称：updateSubLineInfo
     * 内容摘要：更新干线路线信息
     * @param lineInfo 线路线信息
     * @return boolean 更新true或false
     */
    public boolean updateSubLineInfo(T_Master_Sub_Line_Info lineInfo) {
        boolean b = true;
        try {
            this.subLineInfoDaoMapper.updateSubLineInfo(lineInfo);
            b = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            b = false;
        }
        return b;
    }

    // 根据干线路线ID删除干线路线节点信息
    public void delSubLineInfo(String line_id) {
        try {
            subLineInfoDaoMapper.delSubLineInfo(line_id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    // 根据干线路线ID删除干线路线节点信息
    public void delSubLineNodeBySubLineInfoId(String line_id) {
        try {
            List<T_Master_Sub_Line_Detail>subLineDetailList = this.subLineNodeDaoMapper.findSubLineNodeByInfoID(line_id);
            if (subLineDetailList != null) {
                for (T_Master_Sub_Line_Detail subLineDetail : subLineDetailList) {
                    subLineNodeDaoMapper.delSubLineNode(subLineDetail.getLine_id());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    // 根据干线ID查询干线路线信息列表
    public List<T_Master_Sub_Line_Info> findSubLineInfoByOperateMainLineID(String operate_main_line_id) {
        return subLineInfoDaoMapper.findSubLineInfoByOperateID(operate_main_line_id);
    }

    // 根据线路ID查询线路信息表
    public T_Master_Sub_Line_Info findSubLineInfoById(String line_id) {
        return subLineInfoDaoMapper.findSubLineInfoByID(line_id);
    }
}
