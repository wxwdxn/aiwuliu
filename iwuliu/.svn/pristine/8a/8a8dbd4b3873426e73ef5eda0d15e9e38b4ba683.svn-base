/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：LogisticsApprovaManagerController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-03-08
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.service.PersonService;
import com.cn.gazelle.logistics.util.JSONUtil;
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
import java.util.List;

/**
 * 类 名 称：LogisticsApprovaManagerController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 * 02
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/logisticsApprovaManager")
public class LogisticsApprovaManagerController {
    Logger logger = Logger.getLogger(LogisticsApprovaManagerController.class);

    @Resource
    private PersonService personService;
    @Resource
    private MemberService memberService;

    /**
     * 方法名称：home
     * 内容摘要：物流审批列表。
     *
     * @return string 物流审批列表页面
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "operationManager/logisticsApprovaList/logisticsApprovaList";
    }

    /**
     * 方法名称: findDriversList
     * 方法描述：查询验证中的司机列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "findDriversList")
    @ResponseBody
    public List<T_Data_Person> findDriversList(@RequestParam(required = false, defaultValue = "") String company_name,
                                               HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info("company_name = " + company_name + ";");
        List<T_Data_Person> personList = null;
        try {
            String member_name = request.getSession().getAttribute("username").toString();
            T_Data_Member member = this.memberService.findMemberByName(member_name);
            String company_id = member.getRelevance_info_id();
            logger.info("company_id==" + company_id);
            personList = this.personService.findVerifyingDrivers(company_id, company_name);
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
            e.printStackTrace();
        }
        logger.info("personList=======>" + JSONUtil.toJSONString(personList));
        return personList;
    }

    /**
     * 方法名称: agreeDriver
     * 方法描述：审核同意司机加入
     *
     * @param person_id
     * @return T_Data_JsonResult
     */
    @RequestMapping(value = "agreeDriver")
    @ResponseBody
    public T_Data_JsonResult agreeDriver(@RequestParam(required = false, defaultValue = "") String person_id,
                                         HttpServletRequest request, HttpServletResponse response,
                                         ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        // 页面传递的数据
        logger.info("person_id===" + person_id);
        int flag = 0;
        try {
            flag = this.personService.agreeDriver(person_id, "U:" + request.getSession().getAttribute("username"));
            logger.info(MessageUtil.editPassSuccess);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.editPassError);
            flag = -1;
        }
        jsonResult.setResult(flag);
        return jsonResult;
    }

    /**
     * 方法名称: agreeTruck
     * 方法描述：审核同意车辆加入
     *
     * @param plate_number
     * @return T_Data_JsonResult
     */
    @RequestMapping(value = "agreeTruck")
    @ResponseBody
    public T_Data_JsonResult agreeTruck(@RequestParam(required = false, defaultValue = "") String plate_number,
                                        HttpServletRequest request, HttpServletResponse response,
                                        ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        // 页面传递的数据
        logger.info("plate_number===" + plate_number);
        int flag = 0;
        try {
            flag = this.personService.agreeTruck(plate_number, "U:" + request.getSession().getAttribute("username"));
            logger.info(MessageUtil.editPassSuccess);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.editPassError);
            flag = -1;
        }
        jsonResult.setResult(flag);
        return jsonResult;
    }

    /**
     * 方法名称: disagreeDriver
     * 方法描述：拒绝同意司机加入
     *
     * @param person_id
     * @return T_Data_JsonResult
     */
    @RequestMapping(value = "disagreeDriver")
    @ResponseBody
    public T_Data_JsonResult disagreeDriver(@RequestParam(required = false, defaultValue = "") String person_id,
                                            @RequestParam(required = false, defaultValue = "") String verify_refused_reason,
                                            HttpServletRequest request, HttpServletResponse response,
                                            ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        // 页面传递的数据
        logger.info("person_id===" + person_id + ";" + "verify_refused_reason===" + verify_refused_reason + ";");
        int flag = 0;
        try {
            flag = this.personService.disagreeDriver(person_id, verify_refused_reason, "U:" + request.getSession().getAttribute("username"));
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.saveInfoError);
            flag = -1;
        }
        jsonResult.setResult(flag);
        return jsonResult;
    }

    /**
     * 方法名称: findtrucksList
     * 方法描述：查询验证中的车辆列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "findTrucksList")
    @ResponseBody
    public List<T_Data_Vehicle> findTrucksList(@RequestParam(required = false, defaultValue = "") String company_name,
                                               HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info("company_name = " + company_name + ";");
        List<T_Data_Vehicle> trucksList = null;
        try {
            String member_name = request.getSession().getAttribute("username").toString();
            T_Data_Member member = this.memberService.findMemberByName(member_name);
            logger.info("company_id==" + member.getRelevance_info_id());
            trucksList = this.personService.findVerifyingTrucks(member.getRelevance_info_id(), company_name);
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
            e.printStackTrace();
        }
        logger.info("truckList=======>" + JSONUtil.toJSONString(trucksList));
        return trucksList;
    }

    /**
     * 方法名称: disagreeTruck
     * 方法描述：拒绝同意车辆加入
     *
     * @param plate_number
     * @param verify_refused_reason
     * @return T_Data_JsonResult
     */
    @RequestMapping(value = "disagreeTruck")
    @ResponseBody
    public T_Data_JsonResult disagreeTruck(@RequestParam(required = false, defaultValue = "") String plate_number,
                                           @RequestParam(required = false, defaultValue = "") String verify_refused_reason,
                                           HttpServletRequest request, HttpServletResponse response,
                                           ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        // 页面传递的数据
        logger.info("plate_number===" + plate_number + ";" + "verify_refused_reason==" + verify_refused_reason + ";");
        int flag = 0;
        try {
            flag = this.personService.disagreeTruck(plate_number, verify_refused_reason, "U:" + request.getSession().getAttribute("username"));
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.saveInfoError);
            flag = -1;
        }
        jsonResult.setResult(flag);
        return jsonResult;
    }

}

