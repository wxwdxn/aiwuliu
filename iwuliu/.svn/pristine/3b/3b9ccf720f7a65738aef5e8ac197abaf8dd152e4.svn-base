/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：singleLoginController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-22
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类 名 称：singleLoginController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Controller
@RequestMapping(value = "/singleLoginManager")
public class SingleLoginController {
    Logger logger = Logger.getLogger(SingleLoginController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    /**
     * 方法名称：singleLogin_welcomeHome
     * 内容摘要：单点登陆欢迎页面
     *
     * @param model model
     * @return string 司机管理详情页面
     */
    @RequestMapping(value = "home")
    public String singleLogin_welcomeHome(ModelMap model) {
        return "welcome/singleLoginWelcome";
    }

    /**
     * 方法名称：singleLogin
     * 内容摘要：登陆页面
     *
     * @param model model
     * @return string 登陆页面
     */
    @RequestMapping(value = "singleLogin")
    public String singleLogin(ModelMap model) {
        return "singleLoginManager/singleLogin/singleLogin";
    }

    /**
     * 方法名称：accessMenuManager
     * 内容摘要：权限管理页面
     *
     * @param model model
     * @return string 权限管理页面
     */
    @RequestMapping(value = "accessMenuManager")
    public String accessMenuManager(ModelMap model) {
        return "singleLoginManager/accessMenuManager/accessMenuManager";
    }

    /**
     * 方法名称：userRolerManager
     * 内容摘要：用户角色管理页面
     *
     * @param model model
     * @return string 用户角色管理页面
     */
    @RequestMapping(value = "userRolerManager")
    public String userRolerManager(ModelMap model) {
        return "singleLoginManager/userRolerManager/userRolerManager";
    }

    /**
     * 方法名称：dataDictionary
     * 内容摘要：数据字典管理页面
     *
     * @param model model
     * @return string 数据字典管理页面
     */
    @RequestMapping(value = "dataDictionary")
    public String dataDictionary(ModelMap model) {
        return "singleLoginManager/dataDictionary/dataDictionary";
    }


    /**
     * 方法名称:systemMonitoring
     *内容摘要:系统监控页面
     *
     * @param model
     * @return string 系统监控界面
     */
    @RequestMapping(value = "systemMonitoring")
    public String systemMonitoring(ModelMap model){
        return "singleLoginManager/systemMonitoring/systemMonitoring";
    }

    /**
     * 方法名称:databaseMonitoring
     * 内容摘要:数据库监控界面
     * @param model
     * @return String 数据库监控页面
     */
    @RequestMapping(value="databaseMonitoring")
    public String databaseMonitoring(ModelMap model){
        return "singleLoginManager/databaseMonitoring/databaseMonitoring";
    }

    /**
     * 方法名称:logManager
     * 内容摘要:日志管理界面
     * @param model
     * @return String 日志管理界面
     */
    @RequestMapping(value="logManager")
    public String logManager(ModelMap model){
        return "singleLoginManager/logManager/logManager";
    }
}

