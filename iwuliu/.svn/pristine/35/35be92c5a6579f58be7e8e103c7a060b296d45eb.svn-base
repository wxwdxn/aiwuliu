/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OperateMainLineServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运营干线基础信息查询接口实现
 * 设计文件：
 * 完成日期：2016-06-17
 * 作    者: QJ
 * 内容摘要：运营干线基础信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.OperateMainLineDaoMapper;
import com.cn.gazelle.logistics.dao.SubLineInfoDaoMapper;
import com.cn.gazelle.logistics.dao.SubLineNodeDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Operate_Main_Line;
import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Detail;
import com.cn.gazelle.logistics.pojo.T_Master_Sub_Line_Info;
import com.cn.gazelle.logistics.service.OperateMainLineService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.message.OperateMainLineManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 类 名 称：OperateMainLineServiceImpl
 * 内容描述：运营干线基础信息查询接口
 * 方法描述：该类有7个方法
 *          01 findOperateMainLineById           根据ID查运营干线基础信息
 *          02 findOperateMainLineByName         根据线路简称查询运营干线基础信息
 *          03 findAllOperateMainLine            查询符合条件的运营干线列表信息（默认查询运营干线列表信息）
 *          04 findAllOperateMainLineRowsCount   查询运营干线基础信息总记录数
 *          05 saveOperateMainLine               保存运营干线基础信息
 *          06 updateOperateMainLine             更新运营干线基础信息
 *          07 delOperateMainLine                根据ID删除运营干线基础信息
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.OperateMainLineService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class OperateMainLineServiceImpl implements OperateMainLineService {
    // Log初始化
    Logger logger = Logger.getLogger(OperateMainLineServiceImpl.class);
    @Resource
    private OperateMainLineDaoMapper operateMainLineDaoMapper;  // 数据访问层
    @Resource
    private SubLineInfoDaoMapper subLineInfoDaoMapper;
    @Resource
    private SubLineNodeDaoMapper subLineNodeDaoMapper;

    /**
     * 方法名称：findOperateMainLineById
     * 内容摘要：根据ID查运营干线基础信息。
     * @param operate_main_line_id 运营干线ID
     * @return T_Master_Operate_Main_Line 运营干线信息
     */
    public T_Master_Operate_Main_Line findOperateMainLineById(String operate_main_line_id) {
        return operateMainLineDaoMapper.findOperateMainLineById(operate_main_line_id);
    }

    /**
     * 方法名称：findOperateMainLineByName
     * 内容摘要：根据线路简称查询运营干线基础信息。
     * @param operate_main_line_name 线路简称
     * @return T_Master_Operate_Main_Line   运营干线信息
     */
    public T_Master_Operate_Main_Line findOperateMainLineByName(String operate_main_line_name) {
        T_Master_Operate_Main_Line operate_main_line = null;
        try {
            operate_main_line = this.operateMainLineDaoMapper.findOperateMainLineByName(operate_main_line_name);
            logger.info(OperateMainLineManager_Message.getOperateMainLineDone);
        } catch (Exception e) {
            logger.error(OperateMainLineManager_Message.getOperateMainLineError + e.getMessage());
        }
        return operate_main_line;
    }

    /**
     * 方法名称：findAllOperateMainLine
     * 内容摘要：查询符合条件的运营干线列表信息（默认查询运营干线列表信息）。
     * @param search_type   搜索类型
     * @param name  查询类型
     * @param page  页面页数
     * @param rows  页面显示条数
     * @return List<T_Master_Operate_Main_Line>  运营干线信息列
     */
    public List<T_Master_Operate_Main_Line> findAllOperateMainLine(String search_type, String name, Integer page, Integer rows) {
        return operateMainLineDaoMapper.findAllOperateMainLine(search_type, name, page, rows);
    }

    /**
     * 方法名称：findAllOperateMainLineRowsCount
     * 内容摘要：查询运营干线基础信息总记录数
     * @param search_type   搜索类型
     * @param name  查询类型
     * @return Integer 运营干线记录数
     */
    public Integer findAllOperateMainLineRowsCount(String search_type, String name) {
        return operateMainLineDaoMapper.findAllOperateMainLineRowsCount(search_type, name);
    }

    /**
     * 方法名称：saveOperateMainLine
     * 内容摘要：保存运营干线基础信息
     * @param operate_main_line 运营干线基础信息
     */
    public int saveOperateMainLine(T_Master_Operate_Main_Line operate_main_line) {
        int count = 0;
        try {
            count = this.operateMainLineDaoMapper.saveOperateMainLine(operate_main_line);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(OperateMainLineManager_Message.SaveOperateMainLineError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateOperateMainLine
     * 内容摘要：更新运营干线基础信息
     * @param operate_main_line 运营干线基础信息
     */
    public boolean updateOperateMainLine(T_Master_Operate_Main_Line operate_main_line) {
        boolean a=new Boolean(true);
        try {
            operate_main_line.setLast_update(DateUtil.getDate());
            operateMainLineDaoMapper.updateOperateMainLine(operate_main_line);
        } catch (Exception e) {
            a=false;
            logger.error(OperateMainLineManager_Message.UpdateOperateMainLineError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delOperateMainLine
     * 内容摘要：根据ID删除运营干线基础信息
     * @param operate_main_line_id  运营干线ID
     */
    public void delOperateMainLine(String operate_main_line_id) {
        try {
            operateMainLineDaoMapper.delOperateMainLine(operate_main_line_id);
            logger.info(OperateMainLineManager_Message.DelOperateMainLineDone);
        } catch (Exception e) {
            logger.error(OperateMainLineManager_Message.DelOperateMainLineError+e.getMessage());
        }
    }

    /**
     * 方法名称：delSubLineInfoByOperateMainLineId
     * 内容摘要：根据运营干线ID删除干线路线基础信息干线路线节点信息
     * @param operate_main_line_id 运营干线ID
     */
    public void delSubLineInfoByOperateMainLineId(String operate_main_line_id) {
        try {
            List<T_Master_Sub_Line_Info> subLineInfoList = this.subLineInfoDaoMapper.findSubLineInfoByOperateID(operate_main_line_id);
            if (subLineInfoList != null) {
                for (T_Master_Sub_Line_Info subLineInfo : subLineInfoList) {
                    List<T_Master_Sub_Line_Detail>subLineDetailList = this.subLineNodeDaoMapper.findSubLineNodeByInfoID(subLineInfo.getLine_id());
                    if (subLineDetailList != null) {
                        for (T_Master_Sub_Line_Detail subLineDetail : subLineDetailList) {
                            subLineNodeDaoMapper.delSubLineNode(subLineDetail.getLine_id());
                            subLineInfoDaoMapper.delSubLineInfo(subLineInfo.getLine_id());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}