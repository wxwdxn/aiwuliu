/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：WelcomController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-02-22
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 类 名 称：WelcomeController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot YK
 */
@Controller
@RequestMapping(value = "/welcomeManager")
public class WelcomeController {
    Logger logger = Logger.getLogger(WelcomeController.class);
    @Resource
    private MemberService memberService;

    /**
     * 方法名称：home
     * 内容摘要：系统主页。
     *
     * @return string 系统主页
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String home() {
        try {
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError);
        }
        return "mainMenu/commonMenu";
    }

    /**
     * 方法名称：nav
     * 内容摘要：系统导航。
     *
     * @return string 系统导航
     */
    @RequestMapping(method = RequestMethod.GET, value = "nav")
    public String nav() {
        try {
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError);
        }
        return "mainMenu/commonNav";
    }

    /**
     * 方法名称：roleManager
     * 内容摘要： 角色权限。
     *
     * @return  T_Data_JsonResult
     */
    @RequestMapping("roleManager")
    @ResponseBody
    public T_Data_JsonResult roleManager(HttpSession session, HttpServletRequest request,
                                  HttpServletResponse response) {
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            String memeber = request.getSession().getAttribute("username").toString();
            T_Data_Member member = this.memberService.findMemberByName(memeber);
            // 判断会员关联id是否为平台对应id
            if (member.getRelevance_info_id().equals("742C9E4DFC6940689A5D0F7CF6A69649")){
                result.setResult(1);
            } else {
                result.setResult(0);
            }
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError);
        }
        return result;
    }


}

