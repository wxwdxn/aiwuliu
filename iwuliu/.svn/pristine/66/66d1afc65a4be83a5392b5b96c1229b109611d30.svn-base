/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：VirtualAccountController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-19
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_Truck;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.service.TruckService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：VirtualAccountController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/virtualAccountManager")
public class VirtualAccountController {

    Logger logger = Logger.getLogger(VirtualAccountController.class);
    @Resource
    private MemberService memberService;
    @Resource
    private TruckService truckService;

    /**
     * 方法名称:virtualAccount
     * 内容摘要:虚拟账目查看
     *
     * @param model
     * @return String 日志管理界面
     */
    @RequestMapping(value = "virtualAccount")
    public String virtualAccount(ModelMap model) {
        return "operationManager/virtualAccount/virtualAccount";
    }


    /**
     * 方法名称:personalAccountDetail
     * 方法描述：个人账户明细
     *
     * @param
     * @return
     */
    @RequestMapping(value = "personalAccountDetail")
    @ResponseBody
    public List<T_Data_Member> personalAccountDetail() {
        List<T_Data_Member> memberList = null;
        try {
            memberList = this.memberService.queryAccountDetail();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        logger.info("memberList="+ JSONUtil.toJSONString(memberList));
        return memberList;
    }

    /**
     * 方法名称:truckAccountDetail
     * 方法描述：车辆账户明细
     *
     * @param
     * @return
     */
    @RequestMapping(value = "truckAccountDetail")
    @ResponseBody
    public List<T_Data_Truck> truckAccountDetail() {
        List<T_Data_Truck> truckList = null;
        try {
            truckList = this.truckService.findAllTruck();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        logger.info("memberList="+ JSONUtil.toJSONString(truckList));
        return truckList;
    }

    /**
     * 方法名称: searchAccountAmount
     * 方法描述：账户总额度和车辆总额度
     * @return
     */
    @RequestMapping(value = "searchAccountAmount")
    @ResponseBody
    public Map<String,String> searchAccountAmount() {
        Map<String,String> result = new HashMap<String, String>();
        try {
            double personalAccountSum = this.memberService.queryMemberAccountAmountSum();
            double truckAccountSum = this.truckService.queryCashAmount();
            result.put("personalAccountSum",String.valueOf(personalAccountSum));
            result.put("truckAccountSum",String.valueOf(truckAccountSum));
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return result;
    }



}
