/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ContentController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：会员电子协议内容基础信息管理页面
 * 设计文件：
 * 完成日期：2016-05-16
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.service.ContentService;
import com.cn.gazelle.logistics.service.DicdataService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 类 名 称：ContentController
 * 内容描述：会员电子协议内容CRUD
 * 方法描述：该类有 5 个方法
 * 01 home                              会员电子协议内容管理页面
 * 02 contentList                       会员电子协议内容列表页
 * 03 contentSave                       保存协议内容信息
 * 04 contentUpdate                     更新协议内容信息
 * 05 contentDel                        删除协议内容信息
 * 06 contentDownload                   下载协议内容信息
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/contentManager")
public class ContentController {
    Logger logger = Logger.getLogger(ContentController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    /**
     * 方法名称：contentDownload
     * 内容摘要：下载文件信息。
     *
     * @param fileUrl  文件路径
     * @param request  request
     * @param response response
     */
    @RequestMapping("contentDownload")
    public void contentDownload(String fileUrl, HttpServletRequest request, HttpServletResponse response,
                                ModelMap model) throws IOException {

        logger.info("fileUrl=" + fileUrl);
        String domainPath= request.getSession().getServletContext().getRealPath("/");
        domainPath=domainPath.substring(0,domainPath.lastIndexOf(File.separator));
        domainPath=domainPath.substring(0,domainPath.lastIndexOf(File.separator));
        String baseUrl =domainPath+File.separator+this.baseUrl;
        File file = new File(baseUrl + fileUrl);
        if (file.exists()) {
            OutputStream os = response.getOutputStream();
            try {
                response.reset();
                response.setHeader("Content-Disposition", "attachement;filename=" + file.getName());
                response.setContentType("application/octet-stream;charset=utf-8");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
    }
}
