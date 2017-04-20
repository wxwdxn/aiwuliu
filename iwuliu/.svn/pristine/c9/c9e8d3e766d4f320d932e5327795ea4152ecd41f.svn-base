/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OrderController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-15
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.dao.ProtocolOrderDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Sys_Order;
import com.cn.gazelle.logistics.service.OrderService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 类 名 称：OrderController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/orderManager")
public class OrderController {
    Logger logger = Logger.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;
    @Resource
    private ProtocolOrderDaoMapper protocolOrderDaoMapper;

    /**
     * 方法名称：home
     * 内容摘要：Order管理页面。
     *
     * @param model model
     * @return string Order管理主页
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "orderManager/orderManager";
    }

    /**
     * 方法名称：orderList
     * 内容摘要：Order列表页。
     *
     * @param request       request
     * @param response      response
     * @param protocol_type 协议类型
     * @param page          页面页数
     * @param rows          页面显示条数
     * @param model         model
     */
    @RequestMapping(value = "orderList")
    public void orderList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String protocol_type,
                          @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer rows, ModelMap model) {
        List list = null;
        try {
            Map result = new HashMap();
            list = protocolOrderDaoMapper.findAllOrder(protocol_type, (page - 1) * rows, rows);
            result.put("rows", list);
            result.put("total", protocolOrderDaoMapper.findAllOrderSearchCount(protocol_type));
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：orderSave
     * 内容摘要：保存Order。
     *
     * @param order    Order信息
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "orderSave")
    @ResponseBody
    public T_Data_JsonResult orderSave(T_Sys_Order order, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        String json = null;
        try {
            order.setOrder_id(UUIDUtil.getUUID());
            order.setField_date(DateUtil.getDate());
            boolean b = orderService.saveOrder(order);
            if (b) {
                jsonResult.setResult(0);
            } else {
                jsonResult.setResult(1);
            }
        } catch (Exception e) {
            logger.error(LogUtil.saveErr + e.getMessage());
        }

//        try {
//            String fullFileName = "F:\\work20160615\\src\\main\\webapp\\order.json";
//
//            File file = new File(fullFileName);
//            Scanner scanner = null;
//            StringBuilder buffer = new StringBuilder();
//            try {
//                scanner = new Scanner(file, "utf-8");
//                while (scanner.hasNextLine()) {
//                    buffer.append(scanner.nextLine());
//                }
//
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//
//            }
//            json = buffer.toString();
//            logger.info(json);
//            // 解析json为数组
//            Gson gson = new Gson();
//            // json转为带泛型的list
//            List<Map<String, String>> retList = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
//            }.getType());
//            for (Map<String, String > map : retList){
//                order.setOrder_id(UUIDUtil.getUUID());
//                order.setField_date(DateUtil.getDate());
//                order.setProtocol_type(map.get("protocol_type"));
//                order.setSender(map.get("sender"));
//                order.setProtocol_sn(map.get("protocol_sn"));
//                order.setEnglish_field(map.get("english_field"));
//                order.setField_name(map.get("field_name"));
//                order.setField_type(map.get("field_type"));
//                order.setField_len(map.get("field_len"));
//
//                boolean b =orderService.saveOrder(order);
//                if (b){
//                    jsonResult.setResult(0);
//                }else {
//                    jsonResult.setResult(1);
//                }
//            }
//
//        } catch (Exception e) {
//            logger.error(LogUtil.saveErr + e.getMessage());
//        }

        return jsonResult;
    }

    /**
     * 方法名称：orderUpdate
     * 内容摘要：更新Order。
     *
     * @param order    Order信息
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "orderUpdate")
    @ResponseBody
    public T_Data_JsonResult orderUpdate(T_Sys_Order order, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        try {
            order.setField_date(DateUtil.getDate());
            boolean b = orderService.updateOrder(order);
            if (b) {
                jsonResult.setResult(0);
            } else {
                jsonResult.setResult(1);
            }
        } catch (Exception e) {
            logger.error(LogUtil.updataErr + e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 方法名称：orderDel
     * 内容摘要：删除Order。
     *
     * @param temID    批量删除Orderid
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "orderDel")
    public void orderDel(String temID, HttpServletRequest request, HttpServletResponse response,
                         ModelMap model) {
        String[] order_id = temID.split(",");
        try {
            for (int i = 0; i < order_id.length; i++) {
                orderService.delOrder(order_id[i]);
            }
        } catch (Exception e) {
            logger.error(LogUtil.delErr + e.getMessage());
        }

    }

}
