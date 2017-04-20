/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：RouterController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-19
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.OperateMainLineService;
import com.cn.gazelle.logistics.service.SubLineInfoService;
import com.cn.gazelle.logistics.service.SubLineNodeService;
import com.cn.gazelle.logistics.service.RouterService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.OperateMainLineManager_Message;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 类 名 称：RouterController
 * 内容描述：线路管理页面
 * 方法描述：该类有 个方法
 * 01 routerManager_home          线路管理页面调用
 * 02 routerManagerDetail_home    线路管理详情页面调用
 * 03 routerManagerNewAdd_home    线路管理新增页面调用
 * 04 routerList                  线路管理列表
 * 05 lineDetailList              线路节点组
 * 05 routerDetail                线路详情
 * 06 saveRouterInfo              新增路线
 * 07 upDateRouterInfo            编辑路线
 * 08 delRouterInfo               删除线路信息
 *
 * @authot QJ
 */
@Controller
@RequestMapping(value = "/routerManager")
public class RouterController {
    Logger logger = Logger.getLogger(RouterController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private RouterService routerService;
    @Resource
    private SubLineInfoService subLineInfoService;
    @Resource
    private SubLineNodeService subLineNodeService;
    @Resource
    private OperateMainLineService operateMainLineService;

    /**
     * 方法名称：routerManager_home
     * 内容摘要：线路管理页面。
     *
     * @return string 线路管理页面
     */
    @RequestMapping(value = "home")
    public String routerManager_home(ModelMap model) {
        return "operationManager/routerManager/routerManager";
    }

    /**
     * 方法名称：routerManagerDetail_home
     * 内容摘要：线路管理详情页面。
     *
     * @return string 线路管理详情页面
     */
    @RequestMapping(value = "routerManagerDetail_home")
    public String routerManagerDetail_home(ModelMap model) {
        return "operationManager/routerManagerDetail/routerManagerDetail";
    }

    /**
     * 方法名称：routerManagerNewAdd_home
     * 内容摘要：线路管理新增页面。
     *
     * @return string 线路管理新增页面
     */
    @RequestMapping(value = "routerManagerNewAdd_home")
    public String routerManagerNewAdd_home(ModelMap model) {
        return "operationManager/routerManagerNewAdd/routerManagerNewAdd";
    }

    /**
     * 方法名称：routerList
     * 内容摘要：线路管理列表。
     */
    @RequestMapping(value = "/routerList")
    public void routerList(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(required = false, defaultValue = "") String start_city,
                           @RequestParam(required = false, defaultValue = "") String finish_city,
                           @RequestParam(required = false, defaultValue = "") String line_no,
                           @RequestParam(required = false, defaultValue = "") String line_id,
                           ModelMap model) {
        List<T_Data_Router> routerList = null;
        HashMap<String, String> conditions = new HashMap<String, String>();

        try {
            conditions.put("start_city", start_city);               // 起始城市
            conditions.put("finish_city", finish_city);             // 终点城市
            conditions.put("line_no", line_no);                     // 线路编号
            conditions.put("line_id", line_id);                     // 线路id

            routerList = routerService.findRouterList(conditions);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(routerList));
        } catch (Exception e) {
            logger.error(OperateMainLineManager_Message.getOperateMainLineError + e.getMessage());
        }
    }

    /**
     * 方法名称：lineDetailList
     * 内容摘要：线路节点组
     *
     * @param request  request
     * @param response response
     * @return string 线路节点组
     */
    @RequestMapping(value = "lineDetailList")
    public String lineDetailList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Master_Sub_Line_Info> subLineInfoList = null;
        List<T_Master_Sub_Line_Detail> subLineDetailList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String operate_main_line_id = null;
        T_Master_Operate_Main_Line operateMainLine = null;
        try {
            operateMainLine = operateMainLineService.findOperateMainLineByName(request.getParameter("routerManager_startCity") + "-" + request.getParameter("routerManager_finishCity"));
            // 分装查询结果
            if (operateMainLine != null) {
                subLineInfoList = subLineInfoService.findSubLineInfoByOperateMainLineID(operateMainLine.getOperate_main_line_id());
                if (subLineInfoList.size() > 0 && i < subLineInfoList.size()) {
                    for (T_Master_Sub_Line_Info subLineInfo : subLineInfoList) {
                        Map<String, String> results = new HashMap<String, String>();
                        results.put("id", subLineInfo.getLine_id() + "");
                        results.put("name", subLineInfo.getRemark());
                        list.add(results);
                        i++;
                    }
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
    }

    /**
     * 方法名称：routerDetail
     * 内容摘要：线路详情内容。
     */
    @RequestMapping(value = "/routerDetail")
    @ResponseBody
    @Transactional
    public void routerDetail(@RequestParam("line_id") String line_id,
                             HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        line_id = URLDecoder.decode(line_id, "UTF-8");
        List<Map<String, String>> routerDetails = new ArrayList<Map<String, String>>();
        List<T_Data_Router_Detail> list = null;
        Map result = new HashMap();
        T_Master_Operate_Main_Line operateMainLine = null;
        T_Master_Sub_Line_Info subLineInfo = null;
        List<T_Master_Sub_Line_Info> subLineInfoList = null;
        try {
            subLineInfo = subLineInfoService.findSubLineInfoById(line_id);
            operateMainLine = operateMainLineService.findOperateMainLineById(subLineInfo.getOperate_main_line_id());
            list = routerService.findRouterDetailList(operateMainLine.getOperate_main_line_id());
            subLineInfoList = subLineInfoService.findSubLineInfoByOperateMainLineID(operateMainLine.getOperate_main_line_id());

            List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
            for (T_Master_Sub_Line_Info sub_line_info : subLineInfoList) {
                Map results = new HashMap();
                results.put("sub_line_info_id", sub_line_info.getLine_id());
                results.put("sub_line_info_name", sub_line_info.getRemark());
                List<T_Master_Sub_Line_Detail> subLineDetailList = subLineNodeService.findSubLineNodeByInfoID(sub_line_info.getLine_id());
                List<Map<String, String>> listss = new ArrayList<Map<String, String>>();
                for (T_Master_Sub_Line_Detail sub_line_detail : subLineDetailList) {
                    Map<String, String> resultss = new HashMap<String, String>();
                    resultss.put("sub_line_detail_id", sub_line_detail.getLine_id());
                    resultss.put("node_no", sub_line_detail.getNode_no());
                    resultss.put("node_name", sub_line_detail.getNode_name());
                    resultss.put("longitude", sub_line_detail.getLongitude());
                    resultss.put("latitude", sub_line_detail.getLatitude());
                    listss.add(resultss);
                }
                results.put("subLineDetail", listss);
                lists.add(results);
            }
            result.put("subLineInfo", lists);
            result.put("operate_main_line_id", operateMainLine.getOperate_main_line_id());
            result.put("start_province_id", list.get(0).getStart_province_id());
            result.put("start_province_name", list.get(0).getStart_province_name());
            result.put("start_city_id", list.get(0).getStart_city_id());
            result.put("start_city_name", list.get(0).getStart_city_name());
            result.put("finish_province_id", list.get(0).getFinish_province_id());
            result.put("finish_province_name", list.get(0).getFinish_province_name());
            result.put("finish_city_id", list.get(0).getFinish_city_id());
            result.put("finish_city_name", list.get(0).getFinish_city_name());

            routerDetails.add(result);

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(routerDetails));
        } catch (Exception e) {
            logger.error(OperateMainLineManager_Message.getOperateMainLineError + e.getMessage());
        }
    }

    /**
     * 方法名称：saveRouterInfo
     * 内容摘要：新增线路信息。
     */
    @RequestMapping(value = "saveRouterInfo")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult saveRouterInfo(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        try {
            flag = this.routerService.saveRouterInfo(list,(String) request.getSession().getAttribute("username"));
        } catch (Exception e) {
            e.getMessage();
            logger.info( e.getMessage());
            flag = Integer.parseInt(e.getMessage());
        }
        logger.info("flag="+flag);
        // 判断是否插入数据
        if (flag == 1) {
            jsonResult.setResult(1);
            logger.info(MessageUtil.saveInfo);
        }
        // 判断是否插入数据失败
        else if (flag == -1) {
            jsonResult.setResult(0);
        }
        // 判断是否重复添加数据
        else if (flag == 2) {
            jsonResult.setResult(2);
        } else {
            jsonResult.setResult(3);
        }
        return jsonResult;
    }

    /**
     * 方法名称：upDateRouterInfo
     * 内容摘要：编辑线路信息。
     */
    @RequestMapping(value = "upDateRouterInfo")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult upDateRouterInfo(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        try {
            flag = this.routerService.upDateRouterInfo(list,(String) request.getSession().getAttribute("username"));
        } catch (Exception e) {
            e.getMessage();
            logger.info( e.getMessage());
            flag = Integer.parseInt(e.getMessage());
        }
        logger.info("flag="+flag);
        // 判断是否插入数据
        if (flag == 1) {
            jsonResult.setResult(0);
            logger.info(MessageUtil.saveInfo);
        }
        // 判断是否插入数据失败
        else if (flag == -1) {
            jsonResult.setResult(1);
        }
        // 判断是否重复添加数据
        else if (flag == 0) {
            jsonResult.setResult(2);
        } else {
            jsonResult.setResult(3);
        }
        return jsonResult;
    }

    /**
     * 方法名称：delRouterInfo
     * 内容摘要：删除线路信息。
     */
    @RequestMapping(value = "delRouterInfo")
    @ResponseBody
    @Transactional
    private T_Data_JsonResult delRouterInfo(String line_id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        try {
            T_Master_Sub_Line_Info subLineInfo = subLineInfoService.findSubLineInfoById(line_id);
            String operateMainLineId = subLineInfo.getOperate_main_line_id();
            operateMainLineService.delSubLineInfoByOperateMainLineId(operateMainLineId);
            operateMainLineService.delOperateMainLine(operateMainLineId);
            jsonResult.setResult(0);
            logger.info(OperateMainLineManager_Message.DelOperateMainLineDone);
        } catch (Exception e) {
            jsonResult.setResult(1);
            logger.error(OperateMainLineManager_Message.DelOperateMainLineError + e.getMessage());
        }
        return jsonResult;
    }

    // 获取线路经纬度列表
    @RequestMapping(value = "getLineMap")
    public Map getLineMap(String line_id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        // 封装查询结果
        Map<String, List> result = new HashMap();
        // 获取线路
        List<T_Master_Sub_Line_Detail> detailList = null;
        try {
            if (line_id != null && line_id.length()!= 0) {
                detailList = this.subLineNodeService.findSubLineNodeByInfoID(line_id);
                if (detailList != null && detailList.size() != 0) {
                    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                    List<Map<String, String>> liste = new ArrayList<Map<String, String>>();
                    Map resultse = new HashMap();
                    for (T_Master_Sub_Line_Detail detail : detailList) {
                        Map results = new HashMap();
                        results.put("latitude", detail.getLatitude());
                        results.put("longitude", detail.getLongitude());
                        liste.add(results);
                    }
                    resultse.put("LatLon", liste);
                    list.add(resultse);
                    result.put("list", list);
                    ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
                }
            }
        } catch (Exception e) {
            logger.error("获取经纬度失败" + e.getMessage());
        }
        return result;
    }
}