/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OperationWelcomeController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-10-20
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.T_Sys_User;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类 名 称：OperationWelcomeController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot QJ
 */
@Controller
@RequestMapping(value = "/operationWelcome")
public class OperationWelcomeController {
    Logger logger = Logger.getLogger(OperationWelcomeController.class);
    /**
     * 方法名称：home
     * 内容摘要：系统主页。
     *
     * @return string 系统主页
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String home() {
        T_Sys_User user = null;
        try {
            //str = userService.findAll();
            //user = userService.findUserByNP("cyh","123456");
            //str = userService.findUser(1);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError);
//            model.addAttribute("message", MessageUtil.seacheInfoError);
        }
//        model.addAttribute("message","欢迎您："+ session.getAttribute("userName"));
        return "operationManager/vehicleMonitoring/vehicleMonitoring";
    }
    /**
     * 方法名称：report_home
     * 内容摘要：报表统计管理。
     *
     * @return string 报表统计管理
     */
    @RequestMapping(method = RequestMethod.GET, value = "report_home")
    public String report_home(ModelMap model) {
        return "operationManager/reportManager/reportManager";
    }

    /**
     * 方法名称：drivingBehavior_home
     * 内容摘要：驾驶行为。
     *
     * @return string 驾驶行为
     */
    @RequestMapping(method = RequestMethod.GET, value = "drivingBehavior_home")
    public String drivingBehavior_home(ModelMap model) {
        return "operationManager/drivingBehaviorManager/drivingBehaviorManager";
    }

    /**
     * 方法名称：drivingBehaviorDetail_home
     * 内容摘要：驾驶行为详情。
     *
     * @return string 驾驶行为详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "drivingBehaviorDetail_home")
    public String drivingBehaviorDetail_home(ModelMap model) {
        return "operationManager/drivingBehaviorDetailManager/drivingBehaviorDetailManager";
    }

    /**
     * 方法名称：vehicleMonitoring_home
     * 内容摘要：车辆监控。
     *
     * @return string 车辆监控
     */
    @RequestMapping(method = RequestMethod.GET, value = "vehicleMonitoring_home")
    public String vehicleMonitoring_home(ModelMap model) {
        return "operationManager/vehicleMonitoringManager/vehicleMonitoringManager";
    }

    /**
     * 方法名称：truckReplay_home
     * 内容摘要：轨迹回放页面。
     *
     * @return string 轨迹回放页面
     */
    @RequestMapping(method = RequestMethod.GET, value = "truckReplay_home")
    public String truckReplay_home(ModelMap model) {
        return "operationManager/trackThePlayback/trackThePlayback";
    }
    /**
     * 方法名称：intelligentDispatch_home
     * 内容摘要：智能派单管理。
     *
     * @return string 智能派单管理
     */
    @RequestMapping(method = RequestMethod.GET, value = "operationContractManager_home")
    public String operationContractManager_home(ModelMap model) {
        return "operationManager/operationContractManager/operationContractManager";
    }

    /**
     * 方法名称：intelligentDispatchNew_home
     * 内容摘要：运输合同——添加。
     *
     * @return string 运输合同——添加
     */
    @RequestMapping(method = RequestMethod.GET, value = "operationContractManagerNewAdd_home")
    public String operationContractManagerNewAdd_home(ModelMap model) {
        return "operationManager/operationContractManagerNewAdd/operationContractManagerNewAdd";
    }
    /**
     * 方法名称：intelligentDispatchNew_home
     * 内容摘要：运输合同——详情。
     * @return string 运输合同——详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "operationContractManagerDetail_home")
    public String operationContractManagerDetail_home(ModelMap model,String contract_id) {
        return "operationManager/operationContractManagerDetail/operationContractManagerDetail";
    }

    /**
     * 方法名称：vehicle_home
     * 内容摘要：物流管理。
     *
     * @return string 物流管理
     */
    @RequestMapping(method = RequestMethod.GET, value = "vehicle_home")
    public String vehicle_home(ModelMap model) {
        return "operationManager/vehicleManager/vehicleManager";
    }

    /**
     * 方法名称：operationPlanManager_home
     * 内容摘要：运维合同计划。
     *
     * @return string 运维合同计划
     */
    @RequestMapping(method = RequestMethod.GET, value = "operationPlanManager_home")
    public String operationPlanManager_home(ModelMap model) {
        return "operationManager/operationPlanManager/operationPlanManager";
    }

    /**
     * 方法名称：goodTypeManagerNewAdd_home
     * 内容摘要：货物类型新增。
     *
     * @return string 货物类型新增
     */
    @RequestMapping(method = RequestMethod.GET, value = "goodTypeManagerNewAdd_home")
    public String goodTypeManagerNewAdd_home(ModelMap model) {
        return "operationManager/goodTypeManagerNewAdd/goodTypeManagerNewAdd";
    }
    /**
     * 方法名称：goodTypeDetails
     * 内容摘要：货物类型详情页面。
     * @return string 货物类型详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "detail_home")
    public String goodTypeDetails(ModelMap model,String cargoId) {
        return "operationManager/goodTypeManagerDetail/goodTypeManagerDetail";
    }

    /**
     * 方法名称：manualOrderList
     * 内容摘要：手动计划派单页面。
     * @return string
     */
    @RequestMapping(method = RequestMethod.GET, value = "manualOrderList")
    public String manualOrderList(ModelMap model) {
        return "operationManager/transportPlan/transportPlan";
    }

    /**
     * 方法名称：manualTruckList
     * 内容摘要：手动车辆派单页面。
     * @return string
     */
    @RequestMapping(method = RequestMethod.GET, value = "manualTruckList")
    public String manualTruckList(ModelMap model) {
        return "operationManager/vehiclePlan/vehiclePlan";
    }
}