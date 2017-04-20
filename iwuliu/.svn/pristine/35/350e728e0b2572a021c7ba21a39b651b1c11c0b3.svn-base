/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：WithdrawalApprovalController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-04
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_Withdraw_Apply_Detail;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.service.TruckService;
import com.cn.gazelle.logistics.service.WithdrawApplyService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：WithdrawalApprovalController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 * 02
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/withdrawalApprovalManager")
public class WithdrawalApprovalController {
    Logger logger = Logger.getLogger(WithdrawalApprovalController.class);
    @Resource
    private WithdrawApplyService withdrawApplyService;
    @Resource
    private MemberService memberService;
    @Resource
    private TruckService truckService;

    /**
     * 方法名称:withdrawalApproval
     * 内容摘要:提现审批
     *
     * @param model
     * @return String 提现审批管理界面
     */
    @RequestMapping(value = "home")
    public String virtualAccount(ModelMap model) {
        return "operationManager/withdrawalApproval/withdrawalApproval";
    }

    /**
     * 方法描述：查询提现审批列表
     * @param transaction_no
     * @param order_no
     * @param account_name
     * @param settle_status
     * @param transaction_name
     * @param check_status
     * @param start_time
     * @param end_time
     * @param response
     * @param request
     */
    @RequestMapping(value = "withdrawalApplyList")
    @ResponseBody
    public void withdrawalApplyList(@RequestParam(required = false, defaultValue = "") String transaction_no,
                                    @RequestParam(required = false, defaultValue = "") String order_no,
                                    @RequestParam(required = false, defaultValue = "") String account_name,
                                    @RequestParam(required = false, defaultValue = "") String settle_status,
                                    @RequestParam(required = false, defaultValue = "") String transaction_name,
                                    @RequestParam(required = false, defaultValue = "") String check_status,
                                    @RequestParam(required = false, defaultValue = "") String start_time,
                                    @RequestParam(required = false, defaultValue = "") String end_time,
                                    HttpServletResponse response, HttpServletRequest request) {
        logger.info("transaction_no=" + transaction_no);
        logger.info("order_no=" + order_no);
        logger.info("account_name=" + account_name);
        logger.info("settle_status=" + settle_status);
        logger.info("transaction_name=" + transaction_name);
        logger.info("check_status=" + check_status);
        logger.info("start_time=" + start_time);
        logger.info("end_time=" + end_time);
        HashMap<String,String> conditions = new HashMap<String, String>();
        List<T_Data_Withdraw_Apply_Detail> applyList = null;
        List<HashMap<String,String>>  list  = new ArrayList<HashMap<String, String>>();
        String member_name = request.getSession().getAttribute("username").toString();
        try {
            T_Data_Member member = memberService.findMemberByName(member_name);
            String company_id = member.getRelevance_info_id();
            if(company_id.equals("742C9E4DFC6940689A5D0F7CF6A69649")){
            conditions.put("company_id","");
            }else{
            conditions.put("company_id",company_id);
            }
            conditions.put("transaction_no",transaction_no);
            conditions.put("order_no",order_no);
            conditions.put("account_name",account_name);
            conditions.put("transaction_name",transaction_name);
            conditions.put("start_time",start_time);
            conditions.put("end_time",end_time);
            if(settle_status.equals("全部") ||settle_status.equals("请选择")){
                conditions.put("settle_status","");
            }else{
                conditions.put("settle_status",settle_status);
            }
            if(check_status.equals("全部") ||check_status.equals("请选择")){
                conditions.put("check_status","");
            }else{
                conditions.put("check_status",check_status);
            }
           applyList = this.withdrawApplyService.findWithdrawApplyDetail(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        ResponseUtil.outWrite(response, JSONUtil.toJSONString(applyList));
    }

    /**
     * 方法名称:
     * 方法描述：
     *
     * @param
     * @return
     */
    @RequestMapping(value = "searchWithdrawalApplyAmount")
    @ResponseBody
    public Map<String, String> searchWithdrawalApplyAmount() {
        Map<String, String> result = new HashMap<String, String>();
        try {
            double personalAccountSum = this.memberService.queryMemberAccountAmountSum();
            double truckAccountSum = this.truckService.queryCashAmount();
            double withdrawApplySumOfMember = this.withdrawApplyService.findWithdrawApplySumOfMember();
            double withdrawApplySumOfTruck = this.withdrawApplyService.findWithdrawApplySumOfTruck();
            result.put("platAmountSum", String.valueOf(personalAccountSum + truckAccountSum));
            result.put("withdrawalSum", String.valueOf(withdrawApplySumOfMember + withdrawApplySumOfTruck));
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return result;
    }


}

