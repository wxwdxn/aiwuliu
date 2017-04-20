/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OperateMainLineController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：运营干线基础信息管理页面
 * 设计文件：
 * 完成日期：2016-06-17
 * 作    者: QJ
 * 内容摘要：运营干线基础信息管理（增，删，改，查）
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.dao.OperateMainLineDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Operate_Main_Line;
import com.cn.gazelle.logistics.service.OperateMainLineService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.OperateMainLineManager_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：OperateMainLineController
 * 内容描述：运营干线基础信息管理：增，删，改，查
 * 方法描述：该类有6个方法
 *          01 operateMainLineManager       运营干线页面调用
 *          02 operateMainLineList          运营干线信息管理页面显示
 *          03 operateMainLineSave          保存运营干线信息
 *          04 operateMainLineUpdate        更新运营干线信息
 *          05 operateMainLineDel           删除运营干线信息
 *          06 operateMainLines             全部的干线信息（不分页 用于编辑回显）
 * @author QJ
 */

@Controller
@RequestMapping(value = "/operateMainLineManager")
public class OperateMainLineController {
    Logger logger = Logger.getLogger(OperateMainLineController.class);
    @Resource
    private OperateMainLineService operateMainLineService;
    @Resource
    private OperateMainLineDaoMapper operateMainLineDaoMapper;


    /**
     * 方法名称：operateMainLines
     * 内容摘要：全部的干线信息（不分页 用于编辑回显）。
     * @param request   request
     * @param response  response
     * @return
     */
    @RequestMapping(value = "operateMainLines")
    public String operateMainLines(HttpServletRequest request, HttpServletResponse response) {
        List<T_Master_Operate_Main_Line> operateMainLines = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            operateMainLines = operateMainLineDaoMapper.findOperateMainLineList();
            if (operateMainLines.size() > 0 && i < operateMainLines.size()) {
                for (T_Master_Operate_Main_Line operateMainLine :operateMainLines) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", operateMainLine.getOperate_main_line_id());
                    results.put("name", operateMainLine.getOperate_main_line_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
            logger.info(OperateMainLineManager_Message.getOperateMainLineDone);
        } catch (Exception e) {
            logger.error(OperateMainLineManager_Message.getOperateMainLineError + e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }
}