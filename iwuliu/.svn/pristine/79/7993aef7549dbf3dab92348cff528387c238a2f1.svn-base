/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：QueryDivideApiController.java
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
import java.util.HashMap;
import java.util.Map;

/**
 * 类 名 称：QueryDivideApiController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Controller
@RequestMapping(value = "/queryDivide")
public class QueryDivideApiController {
    Logger logger = Logger.getLogger(QueryDivideApiController.class);

    public String formatStr(String text) {
        return text == null ? "" : text.trim();
    }

    @RequestMapping(value = "submit_queryDivide")
    protected void submit_queryDivide(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        try {
            //UTF-8编码
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter out	= response.getWriter();

            //获取请求参数
            //String requestid			= formatStr(request.getParameter("requestid"));
            String orderrequestid		= formatStr(request.getParameter("orderrequestid"));
            String dividerequestid		= formatStr(request.getParameter("dividerequestid"));
            String ledgerno				= formatStr(request.getParameter("ledgerno"));

            Map<String, String> params	= new HashMap<String, String>();
            //params.put("requestid", requestid);
            params.put("orderrequestid", orderrequestid);
            params.put("dividerequestid", dividerequestid);
            params.put("ledgerno", ledgerno);

            //第一步 生成密文data
            String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYDIVIDEAPI_REQUEST_HMAC_ORDER);

            //第二步 发起请求
            String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYDIVIDEAPI_NAME);
            Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);

            //第三步 判断请求是否成功，
            if(responseMap.containsKey("code")) {
                out.println(responseMap);
                return;
            }

            //第四步 解密同步响应密文data，获取明文参数
            String responseData	= responseMap.get("data");
            Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);

            System.out.println("易宝的同步响应：" + responseMap);
            System.out.println("data解密后明文：" + responseDataMap);

            //第五步 code=1时，方表示接口处理成功
            if(!"1".equals(responseDataMap.get("code"))) {
                out.println("code = " + responseDataMap.get("code") + "<br>");
                out.println("msg  = " + responseDataMap.get("msg"));
                return;
            }

            //第六步 hmac签名验证
            if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.QUERYDIVIDEAPI_RESPONSE_HMAC_ORDER)) {
                out.println("<br>hmac check error!<br>");
                return;
            }

            //第七步 进行业务处理
            request.setAttribute("responseDataMap", responseDataMap);
            RequestDispatcher view	= request.getRequestDispatcher("/yeePayManager/home_queryDivideResp");
            view.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
