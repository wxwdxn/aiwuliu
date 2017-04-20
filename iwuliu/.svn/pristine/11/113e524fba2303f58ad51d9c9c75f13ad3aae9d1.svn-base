/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：LongDistanceRunningController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-08-23
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 类 名 称：LongDistanceRunningController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Controller
@RequestMapping(value = "/longDistanceRunningManager")
public class LongDistanceRunningController {
    Logger logger = Logger.getLogger(LongDistanceRunningController.class);

    /**
     * 方法名称：home
     * 内容摘要：车辆长跑路线图。
     * @param model model
     * @return string 车辆长跑路线图
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "longDistanceRunningManager/longDistanceRunningManager";
    }

    /**
     * 方法名称：home
     * 内容摘要：车辆长跑路线图。
     * @return string 车辆长跑路线图
     */
    @RequestMapping(value = "mainPath")
    public String mainPath() {
        return "longDistanceRunningManager/js/main.js";
    }


}
