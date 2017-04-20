/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：AppDownLoadController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-18
 * 作    者: zf
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 类 名 称：AppDownLoadController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@author zf
 */
@Controller
@RequestMapping(value = "/appDownLoadManager")
public class AppDownLoadController {

    /**
     * 方法描述:手机APP的下载
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @RequestMapping("fileDownload")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response,
                             ModelMap model) throws IOException {
        Logger logger = Logger.getLogger(ContentController.class);
        String fileUrl = request.getParameter("fileUrl");
        logger.info("fileUrl=" + fileUrl);
        String path =request.getSession().getServletContext().getRealPath("/");
        logger.info("path="+path);
        String path1 =request.getSession().getServletContext().getRealPath("");
        logger.info("path1="+path1);
        File file = new File(path+"WEB-INF/DownLoad/"+fileUrl);
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

