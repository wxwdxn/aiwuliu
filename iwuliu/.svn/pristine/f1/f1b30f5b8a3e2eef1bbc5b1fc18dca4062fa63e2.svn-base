/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：DownloadOrderDocumentApiController.java
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

import com.cn.gazelle.logistics.util.zgtUtil.DownloadOrderDocumentUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 类 名 称：DownloadOrderDocumentApiController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Controller
@RequestMapping(value = "/downloadOrderDocument")
public class DownloadOrderDocumentApiController {
    Logger logger = Logger.getLogger(DivideApiController.class);

    public String formatStr(String text) {
        return text == null ? "" : text.trim();
    }

    @RequestMapping(value = "submit_downloadOrderDocument")
    protected void submit_downloadOrderDocument(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        try {
            //UTF-8编码
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            //获取请求参数
            String checkDate	= formatStr(request.getParameter("checkDate"));
            String orderType	= formatStr(request.getParameter("orderType"));
            String fileType		= formatStr(request.getParameter("fileType"));

            Map<String, String> params	= new HashMap<String, String>();
            params.put("checkDate", checkDate);
            params.put("orderType", orderType);
            params.put("fileType", fileType);

            //获得项目绝对路径
//        String realPath 	= this.getServletConfig().getServletContext().getRealPath("/");
            String realPath= request.getSession().getServletContext().getRealPath("/");
            realPath=realPath.substring(0,realPath.lastIndexOf(File.separator));
            realPath=realPath.substring(0,realPath.lastIndexOf(File.separator));

            //对账文件的存储路径
            String path			= realPath + File.separator + "ZgtOrderDocument";
            //System.out.println("path:" + path);

            //获取对账文件
            String filePath		= DownloadOrderDocumentUtils.getPathOfDownloadOrderDocument(params, path);

            request.setAttribute("filePath", filePath);
            RequestDispatcher view	= request.getRequestDispatcher("/yeePayManager/home_downloadOrderDocumentResp");
            view.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
