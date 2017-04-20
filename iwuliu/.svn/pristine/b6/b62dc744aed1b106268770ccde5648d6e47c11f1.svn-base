/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CargoYardManagerController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：货场管理人员管理主页
 * 设计文件：
 * 完成日期：2016-04-25
 * 作    者：YK
 * 内容摘要：货场管理人员管理主页的CRUD
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.dao.CargoDaoMapper;
import com.cn.gazelle.logistics.dao.CargoYardManagerDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Cargo_Yard_Manager;
import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Yard;
import com.cn.gazelle.logistics.service.CargoYardManagerService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: CargoYardManagerController
 * 内容摘要: 货场管理人员管理主页的CRUD
 * 方法描述：该类有5个方法：
 *          01 home                      货场管理人员管理主页
 *          02 managerList               货场管理人员列表页
 *          03 managerSave               添加货场管理员信息
 *          04 managerUpdate             审核货场管理员信息
 *          05 managerMatchDel           删除货场管理员信息
 * @author YK
 */
@Controller
@RequestMapping(value = "/managerManager")
public class CargoYardManagerController {

    Logger logger = Logger.getLogger(CargoYardManagerController.class);

    @Resource
    private CargoYardManagerService cargoYardManagerService;

    @Resource
    private CargoYardManagerDaoMapper cargoYardManagerDaoMapper;

    @Resource
    private CargoDaoMapper cargoDaoMapper;


    /**
     * 方法名称：home
     * 内容摘要：货场管理人员管理主页。
     * @param model model
     * @return string 货场管理人员管理主页
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "managerManager/home";
    }

    /**
     * 方法名称：managerList
     * 内容摘要：货场管理人员列表页。
     * @param request     request
     * @param response    response
     * @param search_type 搜索类型
     * @param name        搜索名称
     * @param page        页面页数
     * @param rows        页面显示条数
     * @param model       model
     */
    @RequestMapping(value = "/managerList")
    public void managerList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String search_type,
                            @RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "10") Integer rows, ModelMap model) {
        List list = null;
        T_Master_Cargo_Yard cargo_yard = null;
        try {
            // 封装查询结果
            Map result = new HashMap();
            list = cargoYardManagerService.findAllManager(search_type, name, (page - 1) * rows, rows);
            result.put("rows", list);
            result.put("total", cargoYardManagerService.findAllManagerRowsCount(search_type, name));
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr+e.getMessage());
        }
    }

    /**
     * 方法名称：managerSave
     * 内容摘要：添加货场管理员信息。
     * @param manager  货场管理员信息
     * @param request  request
     * @param response response
     * @param session session
     * @param model    model
     */
    @RequestMapping(value = "managerSave")
    @ResponseBody
    public T_Data_JsonResult managerSave(T_Data_Cargo_Yard_Manager manager, HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                         ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        try {
            manager.setDelete_flag("0");
            manager.setLast_update(DateUtil.getDate());
            manager.setManager_id(UUIDUtil.getUUID());
            manager.setLast_update_user_id("U:"+ session.getAttribute("userName"));
            boolean b =this.cargoYardManagerService.saveManager(manager);
            if (b){
                jsonResult.setResult(0);
            }else {
                jsonResult.setResult(1);
            }
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 方法名称：managerUpdate
     * 内容摘要：审核货场管理员信息。
     * @param manager  货场管理员信息
     * @param request  request
     * @param response response
     * @param session session
     * @param model    model
     */
    @RequestMapping(value = "managerUpdate")
    @ResponseBody
    public T_Data_JsonResult managerUpdate(T_Data_Cargo_Yard_Manager manager, HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                           ModelMap model) {
        T_Data_Cargo_Yard_Manager manager2 = null;
        T_Master_Cargo_Yard yard = null;
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        try {
            manager2 = cargoYardManagerDaoMapper.findManagerByID(manager.getManager_id());
            // 货场
            yard = this.cargoDaoMapper.findById(manager.getYard_id());
            if (yard==null){
                manager.setYard_id(manager2.getYard_id());
            }
            manager.setLast_update(DateUtil.getDate());
            manager.setYard_id(manager2.getYard_id());
            manager.setLast_update_user_id("U:"+ session.getAttribute("userName"));
            boolean b =cargoYardManagerService.updateManager(manager);
            if (b){
                jsonResult.setResult(0);
            }else {
                jsonResult.setResult(1);
            }
        } catch (Exception e) {
            logger.error(LogUtil.updataErr + e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 方法名称：managerMatchDel
     * 内容摘要：删除货场管理员信息。
     * @param temID    批量删除id
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "managerDel")
    public void managerMatchDel(String temID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] manager_id = temID.split(",");
        try {
            for (int i = 0; i < manager_id.length; i++) {

                cargoYardManagerService.delManager(manager_id[i]);
            }
        } catch (Exception e) {
            logger.error(LogUtil.delErr + e.getMessage());
        }
    }
}
