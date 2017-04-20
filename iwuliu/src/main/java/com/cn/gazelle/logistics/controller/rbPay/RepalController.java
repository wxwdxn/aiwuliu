/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：RepalController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-03
 * 作    者: zf
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.rbPay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_Member_Payment_History;
import com.cn.gazelle.logistics.service.MemberPayService;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.rbUtil.config.Decipher;
import com.cn.gazelle.logistics.util.rbUtil.config.Md5Utils;
import com.cn.gazelle.logistics.util.rbUtil.config.ReapalConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 类 名 称：RepalController
 * 内容描述：支付回调通知
 * 方法描述：该类有 3个方法
 *          01   notify
 *          02   certifyNotify
 *          03   certifyReturn
 *@author zf
 */
@Controller
@RequestMapping(value = "/repal")
public class RepalController {

    Logger logger = Logger.getLogger(RepalController.class);

    @Resource
    private MemberPayService memberPayService;

    @Resource
    private MemberService memberService;

    /**
     * 方法描述:支付结果回调地址
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/notify")
    @ResponseBody
    public String notify(HttpServletRequest request, HttpServletResponse response){

        logger.info("执行notify方法");
        String verifyStatus = null;
        String key = ReapalConfig.key;
        String decryData = null;
        Double amount_fee = null;                 //会员账户新增金额
        Double memberAccountAmount = null;//会员账户原有金额
        String merchantId = null;
        try {
            merchantId = request.getParameter("merchant_id");
            logger.info("merchantId="+merchantId);
            String data = request.getParameter("data");
            logger.info("data="+data);
            String encryptkey = request.getParameter("encryptkey");
            logger.info("encryptkey="+encryptkey);
            //解析密文数据
            decryData = Decipher.decryptData(encryptkey,data);
            //获取融宝支付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            JSONObject jsonObject = JSON.parseObject(decryData);
            logger.info("jsonObject="+ JSONUtil.toJSONString(jsonObject));
 //           String merchant_id = jsonObject.getString("merchant_id");
            String trade_no = jsonObject.getString("trade_no");
            String order_no = jsonObject.getString("order_no");
            String total_fee = jsonObject.getString("total_fee");
            String status = jsonObject.getString("status");
            String result_code = jsonObject.getString("result_code");
            String result_msg = jsonObject.getString("result_msg");
            String sign = jsonObject.getString("sign");
            String notify_id = jsonObject.getString("notify_id");
//            int total = (Integer.parseInt(total_fee))/100;
//            String totalMoney = String.valueOf(total);
//            Double total_amount = Double.parseDouble(totalMoney);
            logger.info("total_fee(融宝返回的金额 单位:分)="+total_fee);
            BigDecimal bd1 = new BigDecimal(total_fee);
            BigDecimal bd2 = new BigDecimal(String.valueOf(100));
            Double total_amount = bd1.divide(bd2).doubleValue();
            logger.info("total_amount(平台处理后的金额 单位:元)="+total_amount);
            T_Data_Member_Payment_History memberPaymentHistory = memberPayService.findMemberPayMentHistoryByOrderNo(order_no);
            String member_id = memberPaymentHistory.getMember_id();
            T_Data_Member member = memberService.findMemberByID(member_id);
            memberAccountAmount = member.getMember_account_amount();
            Double amount = memberPaymentHistory.getAmount();
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", merchantId);
            map.put("trade_no", trade_no);
            map.put("order_no", order_no);
            map.put("total_fee", total_fee);
            map.put("status", status);
            map.put("result_code", result_code);
            map.put("result_msg", result_msg);
            map.put("notify_id", notify_id);
            //将返回的参数进行验签
            String mysign = Md5Utils.BuildMysign(map,key);
            System.out.println("mysign:" + mysign);
            System.out.println("sign:" + sign);
            //建议校验responseTxt，判读该返回结果是否由融宝发出
            if(mysign.equals(sign) ){
                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                if(status.equals("TRADE_FINISHED")){
                    if (total_amount!=amount){
                        amount_fee = amount;
                    }else{
                        amount_fee = total_amount;
                    }
                    BigDecimal fee = new BigDecimal(Double.toString(amount_fee));
                    System.out.println("fee(新增金额)="+fee);
                    BigDecimal total = new BigDecimal(Double.toString(memberAccountAmount));
                    System.out.println("total(账户原有金额)="+total);
                    Double accountAmount = fee.add(total).doubleValue();
                    logger.info("accountAmount(平台保存到会员表中的金额)="+accountAmount);
                    //Double accountAmount = amount_fee + memberAccountAmount;//更新会员信息表
                    member.setMember_account_amount(accountAmount);
                    member.setLast_update(DateUtil.getDate());
                    memberService.updateMember(member);
                    memberPaymentHistory.setAmount(amount_fee);    //更新会员支付历史信息表
                    memberPaymentHistory.setPayment_result("0");   //支付结果
                    memberPaymentHistory.setReturn_result("1");      //回传状态
                    memberPaymentHistory.setReturn_time(DateUtil.getDate()); //回传时间
                    memberPayService.updateMemberPaymentHistory(memberPaymentHistory);
                }else{                                         //支付失败更新会员支付历史信息表
                    memberPaymentHistory.setPayment_result("1");     //支付结果
                    memberPaymentHistory.setReturn_result("1");      //回传状态
                    memberPaymentHistory.setReturn_time(DateUtil.getDate()); //回传时间
                    memberPaymentHistory.setFailure_result(result_msg);      //失败原因
                    memberPayService.updateMemberPaymentHistory(memberPaymentHistory);
                }

                verifyStatus = "success";
                logger.info("verifyStatus="+verifyStatus);
            }else{
                verifyStatus = "fail";
                logger.info("verifyStatus="+verifyStatus);
            }
        } catch (Exception e) {
            verifyStatus = "fail";
            logger.info("verifyStatus="+verifyStatus);
        }
        return verifyStatus;
    }

    /**
     * 方法描述:招行卡密鉴权异步通知地址
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/certifyNotify")
    @ResponseBody
    public String certifyNotify(HttpServletRequest request, HttpServletResponse response){

        String certifyStatus = null;
        logger.info("执行certifyNotify方法");
        try {
            String key = ReapalConfig.key;
            String merchantId = request.getParameter("merchant_id");
            String data = request.getParameter("data");
            String encryptkey = request.getParameter("encryptkey");
            //解析密文数据
            String decryData = Decipher.decryptData(encryptkey, data);
            System.out.println("decryDatadecryDatadecryDatadecryData========" + decryData);
            //获取融宝支付的通知返回参数
            JSONObject jsonObject = JSON.parseObject(decryData);
            System.out.println("jsonObjectjsonObjectjsonObjectjsonObject========" + jsonObject);

            String bank_card_type = jsonObject.getString("bank_card_type");
            String bank_code = jsonObject.getString("bank_code");
            String bank_name = jsonObject.getString("bank_name");
            String bind_id = jsonObject.getString("bind_id");
            String card_last = jsonObject.getString("card_last");
            String member_id = jsonObject.getString("member_id");
            String merchant_id = jsonObject.getString("merchant_id");
            String order_no = jsonObject.getString("order_no");
            String phone = jsonObject.getString("phone");
            String result_code = jsonObject.getString("result_code");
            String result_msg = jsonObject.getString("result_msg");
            String total_fee = jsonObject.getString("total_fee");
            String trade_no = jsonObject.getString("trade_no");
            String sign = jsonObject.getString("sign");


            Map<String, String> map = new HashMap<String, String>();
            map.put("bank_card_type", bank_card_type);
            map.put("bank_code", bank_code);
            map.put("bank_name", bank_name);
            map.put("bind_id", bind_id);
            map.put("card_last", card_last);
            map.put("member_id", member_id);
            map.put("merchant_id", merchant_id);
            map.put("order_no", order_no);
            map.put("phone", phone);
            map.put("result_code", result_code);
            map.put("result_msg", result_msg);
            map.put("total_fee", total_fee);
            map.put("trade_no", trade_no);
            //将返回的参数进行验签
            String mysign = Md5Utils.BuildMysign(map, key);

            System.out.println("mysign:" + mysign);
            System.out.println("sign:" + sign);

            //建议校验responseTxt，判读该返回结果是否由融宝发出
            if (mysign.equals(sign)) {

                certifyStatus = "success";

                //编写业务逻辑代码
            }else{

                certifyStatus = "fail";
            }
        } catch (Exception e) {
            certifyStatus = "fail";
        }


        return certifyStatus;
    }

    /**
     * 方法描述:招行卡密鉴权同步通知地址
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/certifyReturn")
    @ResponseBody
    public String certifyReturn(HttpServletRequest request, HttpServletResponse response){

        String certifyStatus = null;
        logger.info("执行certifyReturn方法");
        try {
            String key = ReapalConfig.key;
            String merchantId = request.getParameter("merchant_id");
            String data = request.getParameter("data");
            String encryptkey = request.getParameter("encryptkey");



            //解析密文数据
            String decryData = Decipher.decryptData(encryptkey, data);
            System.out.println("decryDatadecryDatadecryDatadecryData========" + decryData);
            //获取融宝支付的通知返回参数
            JSONObject jsonObject = JSON.parseObject(decryData);
            System.out.println("jsonObjectjsonObjectjsonObjectjsonObject========" + jsonObject);

            String bank_card_type = jsonObject.getString("bank_card_type");
            String bank_code = jsonObject.getString("bank_code");
            String bank_name = jsonObject.getString("bank_name");
            String bind_id = jsonObject.getString("bind_id");
            String card_last = jsonObject.getString("card_last");
            String member_id = jsonObject.getString("member_id");
            String merchant_id = jsonObject.getString("merchant_id");
            String order_no = jsonObject.getString("order_no");
            String phone = jsonObject.getString("phone");
            String result_code = jsonObject.getString("result_code");
            String result_msg = jsonObject.getString("result_msg");
            String total_fee = jsonObject.getString("total_fee");
            String trade_no = jsonObject.getString("trade_no");
            String sign = jsonObject.getString("sign");
            Map<String, String> map = new HashMap<String, String>();
            map.put("bank_card_type", bank_card_type);
            map.put("bank_code", bank_code);
            map.put("bank_name", bank_name);
            map.put("bind_id", bind_id);
            map.put("card_last", card_last);
            map.put("member_id", member_id);
            map.put("merchant_id", merchant_id);
            map.put("order_no", order_no);
            map.put("phone", phone);
            map.put("result_code", result_code);
            map.put("result_msg", result_msg);
            map.put("total_fee", total_fee);
            map.put("trade_no", trade_no);
            //将返回的参数进行验签
            String mysign = Md5Utils.BuildMysign(map, key);

            System.out.println("mysign:" + mysign);
            System.out.println("sign:" + sign);

            //建议校验responseTxt，判读该返回结果是否由融宝发出
            if (mysign.equals(sign)) {

                //编写业务逻辑代码
                certifyStatus = "success";
            }else{
                certifyStatus = "fail";
            }
        } catch (Exception e) {
            certifyStatus = "fail";
        }

        return certifyStatus;
    }
}

