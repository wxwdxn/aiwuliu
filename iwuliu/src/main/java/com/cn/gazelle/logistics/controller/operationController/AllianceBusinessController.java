/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：AllianceBusinessController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：线下加盟服务商
 * 设计文件：
 * 完成日期：2016-12-16
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Master_Alliance_Business_Management;
import com.cn.gazelle.logistics.pojo.T_Master_Service_Station;
import com.cn.gazelle.logistics.service.StationService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.StationManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
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
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：AllianceBusinessController
 * 内容描述：线下加盟商管理页面
 * 方法描述：该类有 个方法
 * 01 allianceBusiness_home          线下加盟商管理页面调用
 * 02 allianceBusinessDetail_home    线下加盟商管理详情页面调用
 *
 *@authot QJ
 */
@Controller
@RequestMapping(value = "/allianceBusiness")
public class AllianceBusinessController {
    Logger logger = Logger.getLogger(AllianceBusinessController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private StationService stationService;

    /**
     * 方法名称：allianceBusiness_home
     * 内容摘要：线下加盟商管理页面。
     *
     * @return string 线下加盟商管理页面
     */
    @RequestMapping(value = "home")
    public String allianceBusiness_home(ModelMap model) {
        return "operationManager/allianceBusinessManagement/allianceBusinessManagement";
    }

    /**
     * 方法名称：allianceBusinessNewAdd_home
     * 内容摘要：线下加盟商管理新增页面。
     *
     * @return string 线下加盟商管理新增页面
     */
    @RequestMapping(value = "allianceBusinessNewAdd_home")
    public String allianceBusinessNewAdd_home(ModelMap model) {
        return "operationManager/allianceBusinessManagementNewAdd/allianceBusinessManagementNewAdd";
    }

    /**
     * 方法名称：allianceBusinessDetail_home
     * 内容摘要：线下加盟商管理详情页面。
     *
     * @return string 线下加盟商管理详情页面
     */
    @RequestMapping(value = "allianceBusinessDetail_home")
    public String allianceBusinessDetail_home(ModelMap model) {
        return "operationManager/allianceBusinessManagementDetail/allianceBusinessManagementDetail";
    }

    /**
     * 方法名称：allianceBusinessList
     * 内容摘要：线下加盟商管理列表。
     */
    @RequestMapping(value = "/allianceBusinessList")
    public void routerList(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(required = false, defaultValue = "") String station_name,
                           @RequestParam(required = false, defaultValue = "") String station_type,
                           @RequestParam(required = false, defaultValue = "") String province_id,
                           @RequestParam(required = false, defaultValue = "") String city_id,
                           @RequestParam(required = false, defaultValue = "") String area_id,
                           @RequestParam(required = false, defaultValue = "") String town_street,

                           ModelMap model) {
        List<T_Master_Alliance_Business_Management> allianceBusinessList = null;
        HashMap<String, String> conditions = new HashMap<String, String>();

        try {
            conditions.put("station_name", station_name);               // 服务站名称
            conditions.put("station_type", station_type);             // 服务站类型
            conditions.put("province_id", province_id);                     // 所在省
            conditions.put("city_id", city_id);                     // 所在城市
            conditions.put("area_id", area_id);                     // 所在区县
            conditions.put("town_street", town_street);                     // 所在村镇街道

            allianceBusinessList = stationService.findStationList(conditions);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(allianceBusinessList));
        } catch (Exception e) {
            logger.error(StationManager_Message.seacheInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：allianceBusinessDetail
     * 内容摘要：线下加盟商管理详情内容。
     */
    @ResponseBody
    @RequestMapping(value = "/allianceBusinessDetail")
    public void allianceBusinessDetail(@RequestParam("station_id") String station_id,
                                       HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        station_id = URLDecoder.decode(station_id, "UTF-8");
        T_Master_Alliance_Business_Management allianceBusiness = null;
        try {
            allianceBusiness = stationService.findAllianceBusinessById(station_id);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(allianceBusiness));
        } catch (Exception e) {
            logger.error(StationManager_Message.seacheInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：saveAllianceBusiness
     * 内容摘要：保存线下加盟商信息。
     */
    @RequestMapping(value = "saveAllianceBusiness")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult saveAllianceBusiness(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        try {
            flag = this.stationService.saveAllianceBusiness(list,(String) request.getSession().getAttribute("username"));
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
     * 方法名称：upDateAllianceBusiness
     * 内容摘要：编辑线下加盟商信息。
     */
    @RequestMapping(value = "upDateAllianceBusiness")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult upDateAllianceBusiness(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        try {
            flag = this.stationService.upDateAllianceBusiness(list,(String) request.getSession().getAttribute("username"));
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
            jsonResult.setResult(4);
        }
        // 判断是否重复添加数据
        else if (flag == 0) {
            jsonResult.setResult(0);
        } else if (flag == 3) {
            jsonResult.setResult(3);
        }
        return jsonResult;
    }

    /**
     * 方法名称：allianceBusinessDel
     * 内容摘要：删除线下加盟商信息。
     *
     * @param temID    批量删除线下加盟商信息ID
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "allianceBusinessDel")
    @ResponseBody
    public T_Data_JsonResult allianceBusinessDel(String temID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] station_id = temID.split(",");
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            for (int i = 0; i < station_id.length; i++) {
                stationService.delStation(station_id[i]);
            }
            result.setResult(0);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
            result.setResult(1);
        }
        return result;
    }
}