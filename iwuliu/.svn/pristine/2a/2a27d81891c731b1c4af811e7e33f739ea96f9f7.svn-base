/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckCarriageTypeController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：车厢类型基础信息管理页面
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者：QJ
 * 内容摘要：车厢类型基础信息管理页面的CRUD
 */

package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.T_Master_Truck_Carriage_Type;
import com.cn.gazelle.logistics.service.TruckCarriageTypeService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.TruckCarriageTypeManager_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: TruckCarriageTypeController
 * 内容摘要: 车厢类型基础信息管理页面的CRUD
 * 方法描述：该类有1个方法：
 *         01 truck_carriage_type_idJson     车厢类型json文件
 * @author QJ
 */
@Controller
@RequestMapping(value = "/truckCarriageTypeManager")
public class TruckCarriageTypeController {
    Logger logger = Logger.getLogger(TruckCarriageTypeController.class);

    @Resource
    private TruckCarriageTypeService truckCarriageTypeService;


    /**
     * 方法名称：truck_carriage_type_idJson
     * 内容摘要：车厢类型json文件。
     * @param request   request
     * @param response  response
     * @param model     model
     */
    @RequestMapping(value = "truck_carriage_type_idJson")
    public String truck_carriage_type_idJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Master_Truck_Carriage_Type> truckCarriageTypeList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            truckCarriageTypeList = this.truckCarriageTypeService.findAllTruckCarriageTypeByID("");
            if (truckCarriageTypeList.size() > 0 && i < truckCarriageTypeList.size()) {
                for (T_Master_Truck_Carriage_Type truckCarriageType : truckCarriageTypeList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", truckCarriageType.getTruck_carriage_type_id());
                    results.put("name", truckCarriageType.getTruck_carriage_type_name());
                    list.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
            logger.info(TruckCarriageTypeManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckCarriageTypeManager_Message.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
    }
}