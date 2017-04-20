/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：PayApiCallbackController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-08-16
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.yeePay;

import com.cn.gazelle.logistics.util.zgtUtil.ZGTUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 类 名 称：PayApiCallbackController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Controller
@RequestMapping(value = "/payApiCallback")
public class PayApiCallbackController {
    Logger logger = Logger.getLogger(PayApiCallbackController.class);

    @RequestMapping(value = "submit_payApiCallback")
    protected void submit_payApiCallback(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        try {
            //UTF-8编码
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter out	= response.getWriter();

            //第一步 获取回调密文data
            String data					= request.getParameter("data");

            //因为ONEKEY-手机一键支付方式的页面回调，不会回调数据，在此demo中做一个简单的判断
            if(data == null) {
                out.println("ONEKEY PAY SUCCESS.");
                return;
            }

            //第二步 解密密文data，获取明文参数
            Map<String, String> dataMap	= ZGTUtils.decryptData(data);

            //第三步 hmac签名验证
            if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
                out.println("<br>hmac check error!<br>");
                return;
            }

            //第四步 判断通知方式，如果是后台通知则需要回写SUCCESS
            String notifytype	= dataMap.get("notifytype");
            if("SERVER".equals(notifytype)) {
                out.println("SUCCESS");
                out.flush();
                out.close();
            } else {
                request.setAttribute("dataMap", dataMap);
                RequestDispatcher view	= request.getRequestDispatcher("/yeePayManager/home_payCallback");
                view.forward(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}

