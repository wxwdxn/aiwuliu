/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：yeePayController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 类 名 称：yeePayController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/yeePayManager")
public class YeePayController {
    Logger logger = Logger.getLogger(YeePayController.class);


    /**
     * 方法名称：home
     * 内容摘要：易宝支付管理主页。
     *
     * @param model model
     * @return string 易宝支付管理主页
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "yeePayManager/yeePayManager";
    }

    /**
     * 方法名称：home_register
     * 内容摘要：子账户注册。
     *
     * @param model model
     * @return string 子账户注册
     */
    @RequestMapping(value = "home_register")
    public String home_register(ModelMap model) {
        return "yeePayDetailManager/41registerApiRequest";
    }

    /**
     * 方法名称：home_register
     * 内容摘要：子账户注册。
     *
     * @param model model
     * @return string 子账户注册
     */
    @RequestMapping(value = "home_registerResp")
    public String home_registerResp(ModelMap model) {
        return "yeePayDetailManager/41registerApiResponse";
    }

    /**
     * 方法名称：home_modify
     * 内容摘要：账户信息修改。
     *
     * @param model model
     * @return string 账户信息修改
     */
    @RequestMapping(value = "home_modify")
    public String home_modify(ModelMap model) {
        return "yeePayDetailManager/42modifyRequestApiRequest";
    }

    /**
     * 方法名称：home_modifyResp
     * 内容摘要：账户信息修改。
     *
     * @param model model
     * @return string 账户信息修改
     */
    @RequestMapping(value = "home_modifyResp")
    public String home_modifyResp(ModelMap model) {
        return "yeePayDetailManager/42modifyRequestApiResponse";
    }

    /**
     * 方法名称：home_queryModify
     * 内容摘要：账户信息修改查询。
     *
     * @param model model
     * @return string 账户信息修改查询
     */
    @RequestMapping(value = "home_queryModify")
    public String home_queryModify(ModelMap model) {
        return "yeePayDetailManager/43queryModifyRequestApiRequest";
    }


    /**
     * 方法名称：home_queryModifyResp
     * 内容摘要：账户信息修改查询。
     *
     * @param model model
     * @return string 账户信息修改查询
     */
    @RequestMapping(value = "home_queryModifyResp")
    public String home_queryModifyResp(ModelMap model) {
        return "yeePayDetailManager/43queryModifyRequestApiResponse";
    }

    /**
     * 方法名称：home_pay
     * 内容摘要：订单支付。
     *
     * @param model model
     * @return string 订单支付
     */
    @RequestMapping(value = "home_pay")
    public String home_pay(ModelMap model) {
        return "yeePayDetailManager/44payApiRequest";
    }

    /**
     * 方法名称：home_payCallback
     * 内容摘要：订单支付回调。
     *
     * @param model model
     * @return string 订单支付回调
     */
    @RequestMapping(value = "home_payCallback")
    public String home_payCallback(ModelMap model) {
        return "yeePayDetailManager/payApiCallback";
    }

    /**
     * 方法名称：home_payResp
     * 内容摘要：订单支付。
     *
     * @param model model
     * @return string 订单支付
     */
    @RequestMapping(value = "home_payResp")
    public String home_payResp(ModelMap model) {
        return "yeePayDetailManager/44payApiResponse";
    }

    /**
     * 方法名称：home_sendSms
     * 内容摘要：短信验证码发送（无卡直连）。
     *
     * @param model model
     * @return string 订单支付
     */
    @RequestMapping(value = "home_sendSms")
    public String home_sendSms(ModelMap model) {
        return "yeePayDetailManager/45sendSmsApiRequest";
    }

    /**
     * 方法名称：home_sendSmsResp
     * 内容摘要：短信验证码发送（无卡直连）。
     *
     * @param model model
     * @return string 订单支付
     */
    @RequestMapping(value = "home_sendSmsResp")
    public String home_sendSmsResp(ModelMap model) {
        return "yeePayDetailManager/45sendSmsApiResponse";
    }

    /**
     * 方法名称：home_confirmSms
     * 内容摘要：短信验证码确认。
     *
     * @param model model
     * @return string 短信验证码确认
     */
    @RequestMapping(value = "home_confirmSms")
    public String home_confirmSms(ModelMap model) {
        return "yeePayDetailManager/46confirmSmsApiRequest";
    }

    /**
     * 方法名称：home_confirmSmsResp
     * 内容摘要：短信验证码确认。
     *
     * @param model model
     * @return string 短信验证码确认
     */
    @RequestMapping(value = "home_confirmSmsResp")
    public String home_confirmSmsResp(ModelMap model) {
        return "yeePayDetailManager/46confirmSmsApiResponse";
    }

    /**
     * 方法名称：home_queryOrder
     * 内容摘要：订单查询。
     *
     * @param model model
     * @return string 订单查询
     */
    @RequestMapping(value = "home_queryOrder")
    public String home_queryOrder(ModelMap model) {
        return "yeePayDetailManager/47queryOrderApiRequest";
    }

    /**
     * 方法名称：home_queryOrderResp
     * 内容摘要：订单查询。
     *
     * @param model model
     * @return string 订单查询
     */
    @RequestMapping(value = "home_queryOrderResp")
    public String home_queryOrderResp(ModelMap model) {
        return "yeePayDetailManager/47queryOrderApiResponse";
    }

    /**
     * 方法名称：home_transfer
     * 内容摘要：转账。
     *
     * @param model model
     * @return string 转账
     */
    @RequestMapping(value = "home_transfer")
    public String home_transfer(ModelMap model) {
        return "yeePayDetailManager/48transferApiRequest";
    }

    /**
     * 方法名称：home_transferResp
     * 内容摘要：转账。
     *
     * @param model model
     * @return string 转账
     */
    @RequestMapping(value = "home_transferResp")
    public String home_transferResp(ModelMap model) {
        return "yeePayDetailManager/48transferApiResponse";
    }

    /**
     * 方法名称：home_transferQuery
     * 内容摘要：转账查询
     *
     * @param model model
     * @return string 转账查询
     */
    @RequestMapping(value = "home_transferQuery")
    public String home_transferQuery(ModelMap model) {
        return "yeePayDetailManager/49transferQueryApiRequest";
    }

    /**
     * 方法名称：home_transferQueryResp
     * 内容摘要：转账查询
     *
     * @param model model
     * @return string 转账查询
     */
    @RequestMapping(value = "home_transferQueryResp")
    public String home_transferQueryResp(ModelMap model) {
        return "yeePayDetailManager/49transferQueryApiResponse";
    }

    /**
     * 方法名称：home_divide
     * 内容摘要：分账
     *
     * @param model model
     * @return string 分账
     */
    @RequestMapping(value = "home_divide")
    public String home_divide(ModelMap model) {
        return "yeePayDetailManager/410divideApiRequest";
    }

    /**
     * 方法名称：home_divide
     * 内容摘要：分账
     *
     * @param model model
     * @return string 分账
     */
    @RequestMapping(value = "home_divideResp")
    public String home_divideResp(ModelMap model) {
        return "yeePayDetailManager/410divideApiResponse";
    }

    /**
     * 方法名称：home_queryDivide
     * 内容摘要：分账查询
     *
     * @param model model
     * @return string 分账查询
     */
    @RequestMapping(value = "home_queryDivide")
    public String home_queryDivide(ModelMap model) {
        return "yeePayDetailManager/411queryDivideApiRequest";
    }

    /**
     * 方法名称：home_queryDivideResp
     * 内容摘要：分账查询
     *
     * @param model model
     * @return string 分账查询
     */
    @RequestMapping(value = "home_queryDivideResp")
    public String home_queryDivideResp(ModelMap model) {
        return "yeePayDetailManager/411queryDivideApiResponse";
    }


    /**
     * 方法名称：home_upload
     * 内容摘要：分账方资质上传接口
     *
     * @param model model
     * @return string 分账方资质上传接口
     */
    @RequestMapping(value = "home_upload")
    public String home_upload(ModelMap model) {
        return "yeePayDetailManager/upload";
    }

    /**
     * 方法名称：home_uploadResp
     * 内容摘要：分账方资质上传接口
     *
     * @param model model
     * @return string 分账方资质上传接口
     */
    @RequestMapping(value = "home_uploadResp")
    public String home_uploadResp(ModelMap model) {
        return "yeePayDetailManager/uploadresponse";
    }

    /**
     * 方法名称：home_refund
     * 内容摘要：订单退款
     *
     * @param model model
     * @return string 订单退款
     */
    @RequestMapping(value = "home_refund")
    public String home_refund(ModelMap model) {
        return "yeePayDetailManager/412refundApiRequest";
    }

    /**
     * 方法名称：home_refundResp
     * 内容摘要：订单退款
     *
     * @param model model
     * @return string 订单退款
     */
    @RequestMapping(value = "home_refundResp")
    public String home_refundResp(ModelMap model) {
        return "yeePayDetailManager/412refundApiResponse";
    }

    /**
     * 方法名称：home_queryRefund
     * 内容摘要：订单退款查询
     *
     * @param model model
     * @return string 订单退款查询
     */
    @RequestMapping(value = "home_queryRefund")
    public String home_queryRefund(ModelMap model) {
        return "yeePayDetailManager/413queryRefundApiRequest";
    }

    /**
     * 方法名称：home_queryRefundResp
     * 内容摘要：订单退款查询
     *
     * @param model model
     * @return string 订单退款查询
     */
    @RequestMapping(value = "home_queryRefundResp")
    public String home_queryRefundResp(ModelMap model) {
        return "yeePayDetailManager/413queryRefundApiResponse";
    }


    /**
     * 方法名称：home_settleConfirm
     * 内容摘要：担保确认
     *
     * @param model model
     * @return string 担保确认
     */
    @RequestMapping(value = "home_settleConfirm")
    public String home_settleConfirm(ModelMap model) {
        return "yeePayDetailManager/414settleConfirmApiRequest";
    }

    /**
     * 方法名称：home_settleConfirmResp
     * 内容摘要：担保确认
     *
     * @param model model
     * @return string 担保确认
     */
    @RequestMapping(value = "home_settleConfirmResp")
    public String home_settleConfirmResp(ModelMap model) {
        return "yeePayDetailManager/414settleConfirmApiResponse";
    }

    /**
     * 方法名称：home_queryBalance
     * 内容摘要：余额查询
     *
     * @param model model
     * @return string 余额查询
     */
    @RequestMapping(value = "home_queryBalance")
    public String home_queryBalance(ModelMap model) {
        return "yeePayDetailManager/415queryBalanceApiRequest";
    }

    /**
     * 方法名称：home_queryBalanceResp
     * 内容摘要：余额查询
     *
     * @param model model
     * @return string 余额查询
     */
    @RequestMapping(value = "home_queryBalanceResp")
    public String home_queryBalanceResp(ModelMap model) {
        return "yeePayDetailManager/415queryBalanceApiResponse";
    }

    /**
     * 方法名称：home_cashTransfer
     * 内容摘要：提现
     *
     * @param model model
     * @return string 提现
     */
    @RequestMapping(value = "home_cashTransfer")
    public String home_cashTransfer(ModelMap model) {
        return "yeePayDetailManager/416cashTransferApiRequest";
    }

    /**
     * 方法名称：home_cashTransferResp
     * 内容摘要：提现
     *
     * @param model model
     * @return string 提现
     */
    @RequestMapping(value = "home_cashTransferResp")
    public String home_cashTransferResp(ModelMap model) {
        return "yeePayDetailManager/416cashTransferApiResponse";
    }

    /**
     * 方法名称：home_queryCashTransfer
     * 内容摘要：提现查询
     *
     * @param model model
     * @return string 提现查询
     */
    @RequestMapping(value = "home_queryCashTransfer")
    public String home_queryCashTransfer(ModelMap model) {
        return "yeePayDetailManager/417queryCashTransferApiRequest";
    }

    /**
     * 方法名称：home_queryCashTransferResp
     * 内容摘要：提现查询
     *
     * @param model model
     * @return string 提现查询
     */
    @RequestMapping(value = "home_queryCashTransferResp")
    public String home_queryCashTransferResp(ModelMap model) {
        return "yeePayDetailManager/417queryCashTransferApiResponse";
    }

    /**
     * 方法名称：home_queryCashTransfer
     * 内容摘要：结算结果查询
     *
     * @param model model
     * @return string 结算结果查询
     */
    @RequestMapping(value = "home_querySettlement")
    public String home_querySettlement(ModelMap model) {
        return "yeePayDetailManager/418querySettlementApiRequest";
    }

    /**
     * 方法名称：home_querySettlementResp
     * 内容摘要：结算结果查询
     *
     * @param model model
     * @return string 结算结果查询
     */
    @RequestMapping(value = "home_querySettlementResp")
    public String home_querySettlementResp(ModelMap model) {
        return "yeePayDetailManager/418querySettlementApiResponse";
    }

   /**
     * 方法名称：home_queryBindCards
     * 内容摘要：查询绑卡列表
     *
     * @param model model
     * @return string 查询绑卡列表
     */
    @RequestMapping(value = "home_queryBindCards")
    public String home_queryBindCards(ModelMap model) {
        return "yeePayDetailManager/419queryBindCardsApiRequest";
    }

    /**
     * 方法名称：home_queryBindCardsResp
     * 内容摘要：查询绑卡列表
     *
     * @param model model
     * @return string 查询绑卡列表
     */
    @RequestMapping(value = "home_queryBindCardsResp")
    public String home_queryBindCardsResp(ModelMap model) {
        return "yeePayDetailManager/419queryBindCardsApiResponse";
    }

   /**
     * 方法名称：home_unbindCard
     * 内容摘要：解绑
     *
     * @param model model
     * @return string 解绑
     */
    @RequestMapping(value = "home_unbindCard")
    public String home_unbindCard(ModelMap model) {
        return "yeePayDetailManager/420unbindCardApiRequest";
    }

    /**
     * 方法名称：home_unbindCardResp
     * 内容摘要：解绑
     *
     * @param model model
     * @return string 解绑
     */
    @RequestMapping(value = "home_unbindCardResp")
    public String home_unbindCardResp(ModelMap model) {
        return "yeePayDetailManager/420unbindCardApiResponse";
    }

     /**
     * 方法名称：home_downloadOrderDocument
     * 内容摘要：对账文件下载
     *
     * @param model model
     * @return string 对账文件下载
     */
    @RequestMapping(value = "home_downloadOrderDocument")
    public String home_downloadOrderDocument(ModelMap model) {
        return "yeePayDetailManager/421downloadOrderDocumentApiRequest";
    }

    /**
     * 方法名称：home_downloadOrderDocumentResp
     * 内容摘要：对账文件下载
     *
     * @param model model
     * @return string 对账文件下载
     */
    @RequestMapping(value = "home_downloadOrderDocumentResp")
    public String home_downloadOrderDocumentResp(ModelMap model) {
        return "yeePayDetailManager/421downloadOrderDocumentApiResponse";
    }

    /**
     * 方法名称：home_showimage
     * 内容摘要：展示图片
     *
     * @param model model
     * @return string 展示图片
     */
    @RequestMapping(value = "home_showimage")
    public String home_showimage(ModelMap model) {
        return "yeePayDetailManager/showimage";
    }


}
