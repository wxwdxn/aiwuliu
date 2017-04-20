/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：VehicleModelManagerController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：车辆类型管理
 * 设计文件：
 * 完成日期：2017-01-18
 * 作    者: QJ
 * 内容摘要：车辆类型管理页面
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Model;
import com.cn.gazelle.logistics.pojo.T_Data_Vehicle_Model;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.TruckModelService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
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
 * 类 名 称：VehicleModelManagerController
 * 内容描述：车辆类型管理
 * 方法描述：该类有 个方法
 *          01 
 *@authot QJ
 */
@Controller
@RequestMapping(value = "/vehicleModelManager")
public class VehicleModelManagerController {
    Logger logger = Logger.getLogger(VehicleModelManagerController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private TruckModelService truckModelService;

    /**
     * 方法名称：vehicleModelManager_home
     * 内容摘要：车辆类型管理。
     *
     * @return string 车辆类型管理页面
     */
    @RequestMapping(value = "home")
    public String vehicleModelManager_home(ModelMap model) {
        return "operationManager/vehicleModelManager/vehicleModelManager";
    }

    /**
     * 方法名称：vehicleModelManagerNewAdd_home
     * 内容摘要：车辆类型管理新增页面。
     *
     * @return string 车辆类型管理新增页面
     */
    @RequestMapping(value = "vehicleModelManagerNewAdd_home")
    public String vehicleModelManagerNewAdd_home(ModelMap model) {
        return "operationManager/vehicleModelManagerNewAdd/vehicleModelManagerNewAdd";
    }

    /**
     * 方法名称：vehicleModelManagerDetail_home
     * 内容摘要：车辆类型管理详情页面。
     *
     * @return string 车辆类型管理详情页面
     */
    @RequestMapping(value = "vehicleModelManagerDetail_home")
    public String vehicleModelManagerDetail_home(ModelMap model) {
        return "operationManager/vehicleModelManagerDetail/vehicleModelManagerDetail";
    }

    /**
     * 方法名称：vehicleModelManagerList
     * 内容摘要：车辆类型管理列表。
     */
    @RequestMapping(value = "/vehicleModelManagerList")
    public void routerList(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(required = false, defaultValue = "") String truck_brand_id,
                           ModelMap model) {
        List<T_Data_Vehicle_Model> vehicleModelManagerList = null;
        HashMap<String, String> conditions = new HashMap<String, String>();

        try {
            conditions.put("truck_brand_id", truck_brand_id);               // 车辆品牌ID

            vehicleModelManagerList = truckModelService.findVehicleModelManagerList(conditions);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(vehicleModelManagerList));
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：vehicleModelManagerDetail
     * 内容摘要：车辆类型管理详情内容。
     */
    @RequestMapping(value = "/vehicleModelManagerDetail")
    @ResponseBody
    @Transactional
    public void vehicleModelManagerDetail(@RequestParam("truck_model_no") String truck_model_no,
                                          HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        truck_model_no = URLDecoder.decode(truck_model_no, "UTF-8");
        T_Data_Vehicle_Model vehicleModel = null;
        try {
            vehicleModel = truckModelService.findVehicleModelByNo(truck_model_no);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(vehicleModel));
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
    }

    /**
     * 方法名称：saveVehicleModel
     * 内容摘要：保存车辆类型信息。
     */
    @RequestMapping(value = "saveVehicleModel")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult saveVehicleModel(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        try {
            flag = this.truckModelService.saveVehicleModel(list,(String) request.getSession().getAttribute("username"));
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
     * 方法名称：upDateVehicleModel
     * 内容摘要：编辑车辆类型信息。
     */
    @RequestMapping(value = "upDateVehicleModel")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult upDateVehicleModel(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        try {
            flag = this.truckModelService.upDateVehicleModel(list,(String) request.getSession().getAttribute("username"));
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
     * 方法名称：vehicleModelManagerDel
     * 内容摘要：删除车辆类型信息。
     *
     * @param temID    批量删除车辆类型信息ID
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "vehicleModelManagerDel")
    @ResponseBody
    public T_Data_JsonResult vehicleModelManagerDel(String temID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] truck_model_no = temID.split(",");
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            for (int i = 0; i < truck_model_no.length; i++) {
                truckModelService.delTruckModel(truck_model_no[i]);
            }
            result.setResult(0);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
            result.setResult(1);
        }
        return result;
    }
}