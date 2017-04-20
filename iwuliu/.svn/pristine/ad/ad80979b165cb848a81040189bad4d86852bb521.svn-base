/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：AgentpayController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-06
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.rbPay;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.rbUtil.config.Decipher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类 名 称：AgentpayController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 * 02
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "agentPay")
public class AgentpayController {
    private static final String RESULT_CODE_SUCCESS = "成功";           // 成功
    private static final String RETURN_RESULT = "1";                    // 已回传
    private static final String PAYMENT_RESULT_ERROR = "1";             // 失败
    private static final String PAYMENT_RESULT_SUCCESS = "0";           // 成功
    private static final String WITHDRAW_APPLY_STATUS_ERROR = "4";      // 支付失败
    private static final String WITHDRAW_APPLY_STATUS_SUCCESS = "5";    // 已成功


    Logger logger = Logger.getLogger(AgentpayController.class);
    @Resource
    private MemberPayService memberPayService;
    @Resource
    private WithdrawApplyService withdrawApplyService;
    @Resource
    private MemberService memberService;
    @Resource
    private TruckPayService truckPayService;
    @Resource
    private TruckService truckService;
    /**
     * 方法描述:代付结果回调地址
     * @param request
     * @param response
     * @return
     */
    /**
     * 推送回调接口，融宝调用该方法返回商户结果
     *
     * @return success：成功，其他返回均认为失败
     * @throws Exception
     */
    @RequestMapping(value = "/payback", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public String payback(Model model, String encryptkey, String data, HttpServletRequest request) {
        Gson gson = new Gson();
        logger.info("进入payback方法");
        logger.info("encryptkey===========>" + encryptkey);
        logger.info("data===========>" + data);
        String result = null;
        String verifyStatus = null;   // 返回融宝响应参数
        System.out.println("推送回调接口encryptkey==========>" + encryptkey);
        System.out.println("推送回调接口data==========>" + data);
        try {
            //解析密文数据
            result = Decipher.decryptData(encryptkey, data);
            System.out.println("推送回调接口payback_result:" + result);
            /*
             * 根据解析后的result做后续处理
        	 * */
            Map<String, String> resultMap = gson.fromJson(result, new TypeToken<Map<String, String>>() {
            }.getType());
            String info[] = resultMap.get("data").split(",");
            System.out.println("info[]="+ JSONUtil.toJSONString(info));
            int len = info.length;
            String batch_amount = info[9];
            logger.info("batch_amount=============>"+batch_amount);
            String third_party_order_id = info[12];
            logger.info("third_party_order_id=============>"+third_party_order_id);
            String return_status = info[13];
            logger.info("return_status=============>"+return_status);
            // 通过第三方订单id查找会员支付历史信息
            T_Data_Member_Payment_History history = this.memberPayService.findMemberPayMentHistoryByOrderNo(third_party_order_id);
            logger.info("history_number==========>"+history.getHistory_number());
            // 通过会员历史支付编号查找提现申请信息
            T_Data_Withdraw_Apply apply = this.withdrawApplyService.findWithdrawApplyByNumber(history.getHistory_number());
            // 通过会员id查找会员
            T_Data_Member member = this.memberService.findMemberByID(history.getMember_id());
            // 判断代付结果是否成功
            if (return_status.equals(RESULT_CODE_SUCCESS)) {
                // 会员历史信息更新
                history.setReturn_result(RETURN_RESULT);
                history.setReturn_time(DateUtil.getDate());
                history.setPayment_result(PAYMENT_RESULT_SUCCESS);
                history.setLast_update(DateUtil.getDate());
                history.setLast_update_user_id("SYSTEM");
                this.memberPayService.updateMemberPaymentHistory(history);
                // 提现申请表更新
                apply.setStatus(WITHDRAW_APPLY_STATUS_SUCCESS);
                apply.setLast_update(DateUtil.getDate());
                apply.setLast_update_user_id("SYSTEM");
                this.withdrawApplyService.updateWithdrawApply(apply);
                // 会员信息表账户余额减少（注释代码需要在异步通知中书写）
                member.setLast_update(DateUtil.getDate());
                member.setLast_update_user_id("SYSTEM");
                member.setMember_account_amount(member.getMember_account_amount() - Double.parseDouble(batch_amount));
                this.memberService.updateMember(member);
                verifyStatus = "success";
            } else {
                String failure_result = info[len - 1];
                logger.info("failure_result=============>" + failure_result);
                // 代付结果失败
                // 会员历史信息更新
                history.setReturn_result(RETURN_RESULT);
                history.setReturn_time(DateUtil.getDate());
                history.setPayment_result(PAYMENT_RESULT_ERROR);
                history.setFailure_result(failure_result);
                history.setLast_update(DateUtil.getDate());
                history.setLast_update_user_id("SYSTEM");
                this.memberPayService.updateMemberPaymentHistory(history);
                // 提现申请表更新
                apply.setStatus(WITHDRAW_APPLY_STATUS_ERROR);
                apply.setDeny_reason(failure_result);
                apply.setLast_update(DateUtil.getDate());
                apply.setLast_update_user_id("SYSTEM");
                this.withdrawApplyService.updateWithdrawApply(apply);
                verifyStatus = "success";
            }
        } catch (Exception e) {
            verifyStatus = "fail";
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        logger.info("verifyStatus=============>"+verifyStatus);
        return verifyStatus;
    }

    /**
     * 方法描述:线下服务商支付结果回调地址
     * @param request
     * @param response
     * @return
     */
    /**
     * 推送回调接口，融宝调用该方法返回商户结果
     *
     * @return success：成功，其他返回均认为失败
     * @throws Exception
     */
    @RequestMapping(value = "/stationPayBack", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public String stationPayBack(Model model, String encryptkey, String data, HttpServletRequest request) {
        Gson gson = new Gson();
        logger.info("进入stationPayBack方法");
        String result = null;
        String verifyStatus = null;   // 返回融宝响应参数
        System.out.println("推送回调接口encryptkey==========>" + encryptkey);
        System.out.println("推送回调接口data==========>" + data);
        try {
            //解析密文数据
            result = Decipher.decryptData(encryptkey, data);
            System.out.println("推送回调接口payback_result:" + result);
            /*
             * 根据解析后的result做后续处理
        	 * */
            Map<String, String> resultMap = gson.fromJson(result, new TypeToken<Map<String, String>>() {
            }.getType());
            String info[] = resultMap.get("data").split(",");
            int len = info.length;
            String batch_amount = info[9];
            logger.info("batch_amount=============>"+batch_amount);
            String third_party_order_id = info[12];
            logger.info("third_party_order_id=============>"+third_party_order_id);
            String return_status = info[13];
            logger.info("return_status=============>"+return_status);
            // 通过第三方订单id查找会员支付历史信息
            T_Data_Truck_Payment_History history = this.truckPayService.findTruckPaymentHistoryByThirdPartyOrderId(third_party_order_id);
            // 判断代付结果是否成功
            if (return_status.equals(RESULT_CODE_SUCCESS)) {
                // 车辆历史信息更新
                history.setReturn_result(RETURN_RESULT);
                history.setReturn_time(DateUtil.getDate());
                history.setPayment_result(PAYMENT_RESULT_SUCCESS);
                history.setLast_update(DateUtil.getDate());
                history.setLast_update_user_id("SYSTEM");
                this.truckPayService.updateTruckPaymentHistory(history);
                verifyStatus = "success";
            } else {
                String failure_result = info[len - 1];
                logger.info("failure_result=============>" + failure_result);
                // 代付结果失败
                // 车辆历史信息更新
                history.setReturn_result(RETURN_RESULT);
                history.setReturn_time(DateUtil.getDate());
                history.setPayment_result(PAYMENT_RESULT_ERROR);
                history.setFailure_result(failure_result);
                history.setLast_update(DateUtil.getDate());
                history.setLast_update_user_id("SYSTEM");
                this.truckPayService.updateTruckPaymentHistory(history);
                // 返回车辆支付金额
                T_Data_Truck truck = truckService.findTruckByID(history.getTruck_id());
                truck.setCash_amount(truck.getCash_amount() + Double.parseDouble(batch_amount));
                this.truckService.updateTruck(truck);
                verifyStatus = "success";
            }
        } catch (Exception e) {
            verifyStatus = "fail";
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        logger.info("verifyStatus=============>"+verifyStatus);
        return "success";
    }


}

