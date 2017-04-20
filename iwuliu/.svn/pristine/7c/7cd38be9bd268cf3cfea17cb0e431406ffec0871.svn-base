/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：UserRoleManagerController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-23
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.dao.PersonDaoMapper;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.*;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mchange.v2.holders.ThreadSafeIntHolder;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：UserRoleManagerController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/userRoleManager")
public class UserRoleManagerController {
    Logger logger = Logger.getLogger(UserRoleManagerController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private MemberService memberService;
    @Resource
    private PersonService personService;
    @Resource
    private PersonDaoMapper personDaoMapper;
    @Resource
    private UserService userService;
    @Resource
    private DicdataService dicdataService;
    @Resource
    private UserRoleManagerService userRoleManagerService;

    /**
     * 方法名称：userRoleManagerList
     * 内容摘要：用户角色管理页面列表。
     *
     * @param model model
     * @return string 用户角色管理页面列表
     */
    @RequestMapping(value = "userRoleManagerList")
    public void userRoleManagerList(@RequestParam(required = false, defaultValue = "") String group_name,
                                    @RequestParam(required = false, defaultValue = "") String role_name,
                                    @RequestParam(required = false, defaultValue = "") String member_name,
                                    @RequestParam(required = false, defaultValue = "") String dept_name,
                                    @RequestParam(required = false, defaultValue = "") String person_name,
                                    @RequestParam(required = false, defaultValue = "") String id_card_number,
                                    @RequestParam(required = false, defaultValue = "") String account_status,
                                    @RequestParam(required = false, defaultValue = "") String register_time_start,
                                    @RequestParam(required = false, defaultValue = "") String register_time_end,
                                    HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        System.out.println("group_name=" + group_name);
        System.out.println("role_name=" + role_name);
        System.out.println("member_name=" + member_name);
        System.out.println("dept_name=" + dept_name);
        System.out.println("person_name=" + person_name);
        System.out.println("id_card_number=" + id_card_number);
        System.out.println("account_status=" + account_status);
        System.out.println("register_time_start=" + register_time_start);
        System.out.println("register_time_end=" + register_time_end);
        List<T_Data_User_Role_Manager> userRoleManagerList = null;
        HashMap<String, String> conditions = new HashMap<String, String>();
        try {
            conditions.put("group_name", group_name);
            conditions.put("role_name", role_name);
            conditions.put("member_name", member_name);
            conditions.put("dept_name", dept_name);
            conditions.put("person_name", person_name);
            conditions.put("id_card_number", id_card_number);
            conditions.put("account_status", account_status);
            conditions.put("register_time_start", register_time_start);
            conditions.put("register_time_end", register_time_end);
            logger.info(JSONUtil.toJSON(conditions));
            userRoleManagerList = this.memberService.queryUserRoleList(conditions);
            logger.info("list=" + JSONUtil.toJSONString(userRoleManagerList));
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(userRoleManagerList));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：userRoleManagerDetail
     * 内容摘要：用户角色管理详情页面。
     *
     * @param request  request
     * @param response response
     * @param model    model
     * @return string 用户角色管理详情页面
     */
    @RequestMapping(value = "userRoleManagerDetail")
    public String userRoleManagerDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "singleLoginManager/userRoleManagerDetail/userRoleManagerDetail";
    }

    /**
     * 方法名称：userRoleManagerNewAdd
     * 内容摘要：用户角色管理新增页面。
     *
     * @param request  request
     * @param response response
     * @param model    model
     * @return string 用户角色管理新增页面
     */
    @RequestMapping(value = "userRoleManagerNewAdd")
    public String userRoleManagerNewAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "singleLoginManager/userRoleManagerNewAdd/userRoleManagerNewAdd";
    }

    /**
     * 方法名称：getUserRoleManagerInfo
     * 内容摘要：通过member_id获取用户角色信息
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @ResponseBody
    @RequestMapping(value = "/getUserRoleManagerInfo")
    public T_Data_User_Role_Manager getUserRoleManagerInfo(String member_id, HttpServletRequest request,
                                                           HttpServletResponse response, ModelMap model) {
        List<T_Data_User_Role_Manager> userRoleManagerList = null;
        T_Data_User_Role_Manager userRoleManager = null;
        HashMap<String, String> conditions = new HashMap<String, String>();
        conditions.put("member_id", member_id);
        try {
            userRoleManagerList = this.memberService.queryUserRoleList(conditions);
            userRoleManager = userRoleManagerList.get(0);
            logger.info("useManagerInfo = " + JSONUtil.toJSONString(userRoleManager));

        } catch (Exception e) {
            logger.info(LogUtil.searchErr + e.getMessage());
        }
        return userRoleManager;
    }

    /**
     * 方法名称：userRoleManagerDel
     * 内容摘要：用户角色管理删除功能。
     *
     * @param member_id member_id
     * @param request   request
     * @param response  response
     * @param model     model
     * @return string用户角色管理删除功能
     */
    @ResponseBody
    @RequestMapping(value = "/userRoleManagerDel")
    public T_Data_JsonResult userRoleManagerDel(String member_id, String user_id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        logger.info("member_id=" + member_id);
        logger.info("user_id=" + user_id);
        int flag = 0;
        try {
            flag = this.userRoleManagerService.delUserRoleInfo(member_id);
            jsonResult.setResult(flag);
        } catch (Exception e) {
            logger.error(LogUtil.delErr + e.getMessage());
            flag = Integer.parseInt(e.getMessage());
            jsonResult.setResult(flag);
        }
        return jsonResult;
    }

    /**
     * 方法名称：newAddUserRoleManager
     * 内容摘要：用户角色新增。
     *
     * @param request  request
     * @param response response
     * @param model    model
     * @return string 用户角色新增
     */
    @ResponseBody
    @RequestMapping(value = "/newAddUserRoleManager")
    public T_Data_JsonResult newAddUserRoleManager(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        String info = request.getParameter("list");
        int flag = 0;
        try {
            flag = this.userRoleManagerService.saveUserRoleInfo(info, (String) request.getSession().getAttribute("username"));
            jsonResult.setResult(flag);
        } catch (Exception e) {
            logger.info(MessageUtil.saveInfoError + e.getMessage());
            flag = Integer.parseInt(e.getMessage());
            jsonResult.setResult(flag);
        }
        return jsonResult;
    }

    /**
     * 方法名称：updateUserRoleInfo
     * 内容摘要：编辑用户角色信息
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserRoleInfo")
    public T_Data_JsonResult updateUserRoleInfo(String member_id, String user_id, HttpServletRequest request, HttpServletResponse response,
                                                ModelMap model) {
        logger.info("member_id=" + member_id);
        logger.info("user_id=" + user_id);
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        String info = request.getParameter("list");
        try {
            flag = this.userRoleManagerService.updateUserRoleInfo(member_id, user_id, info,
                    (String) request.getSession().getAttribute("username"));
            jsonResult.setResult(flag);
        } catch (Exception e) {
            jsonResult.setResult(flag);
            logger.error(e.getMessage());
        }
        return jsonResult;
    }
}
