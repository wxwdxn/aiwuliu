/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：AccessMenuManagerController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-15
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Sys_Menu_Authority_Manager;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：AccessMenuManagerController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Controller
@RequestMapping(value = "/accessMenuManager")
public class AccessMenuManagerController {

    Logger logger = Logger.getLogger(AccessMenuManagerController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;

    /**
     * 方法名称：accessMenuManagerNewAdd
     * 内容摘要：菜单权限。
     *
     * @param request  request
     * @param response response
     * @param model    model
     * @return string 菜单权限新增页面
     */
    @RequestMapping(value = "accessMenuManagerNewAdd")
    public String accessMenuManagerNewAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "singleLoginManager/accessMenuManagerNewAdd/accessMenuManagerNewAdd";
    }

    /**
     * 方法名称：accessMenuManagerList
     * 内容摘要：菜单权限管理页面列表。
     *
     * @param model model
     * @return string 菜单权限管理页面列表
     */
    @RequestMapping(value = "accessMenuManagerList")
    public void accessMenuManagerList(@RequestParam(required = false, defaultValue = "") String menu_name,
                                    @RequestParam(required = false, defaultValue = "") String group_name,
                                    @RequestParam(required = false, defaultValue = "") String role_name,
                                    @RequestParam(required = false, defaultValue = "") String create_time_start,
                                    @RequestParam(required = false, defaultValue = "") String create_time_end,
                                    HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        System.out.println("menu_name="+menu_name);
        System.out.println("group_name="+group_name);
        System.out.println("role_name="+role_name);
        System.out.println("create_time_start="+create_time_start);
        System.out.println("create_time_end="+create_time_end);
        List<T_Sys_Menu_Authority_Manager> menuAuthorityManagerList = null;
        HashMap<String, String> conditions = new HashMap<String, String>();
        try {
            conditions.put("menu_name", menu_name);
            conditions.put("group_name", group_name);
            conditions.put("role_name", role_name);
            conditions.put("create_time_start", create_time_start);
            conditions.put("create_time_end", create_time_end);

//            ResponseUtil.outWrite(response, JSONUtil.toJSONString(userRoleManagerList));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

}
