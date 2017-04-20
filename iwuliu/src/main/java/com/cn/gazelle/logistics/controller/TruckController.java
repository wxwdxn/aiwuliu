/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：卡车信息管理页面
 * 设计文件：
 * 完成日期：2016-03-04
 * 作    者：QJ
 * 内容摘要：卡车信息管理页面的CRUD
 */

package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称: TruckController
 * 内容摘要: 卡车信息管理页面的CRUD
 * 方法描述：该类有1个方法：
 *
 *         01 truckPicUpload    车辆信息图片上传
 * @author QJ
 */

@Controller
@RequestMapping(value = "/truckManager")
public class TruckController {
    Logger logger = Logger.getLogger(TruckController.class);

    @Resource
    private MemberService memberService;
    @Value("#{setting[baseUrl]}")
    private String baseUrl;

    /**
     * 方法名称：truckPicUpload
     * 内容摘要：卡车信息图片上传
     * @param member_name 会员名称
     * @param request request
     * @param response response
     * */
    @RequestMapping(value = "/truckPicUpload")
    public void truckPicUpload(String member_name,HttpServletRequest request, HttpServletResponse response) throws IOException {
        T_Data_Member member = null;
        T_Data_Truck truck = null;
        logger.info("member_name="+member_name);
        String domainPath= request.getSession().getServletContext().getRealPath("/");
        domainPath=domainPath.substring(0,domainPath.lastIndexOf(File.separator));
        domainPath=domainPath.substring(0,domainPath.lastIndexOf(File.separator));
        String baseUrl = domainPath+File.separator+this.baseUrl;
        String ecode = null;
        Map result = new HashMap();
        String fileType = null;
        String path = null;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmsssss");
            member = this.memberService.findMemberByName(member_name);
            // 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                // 将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                File fileUp = new File(baseUrl + "/TruckPic");
                while (iter.hasNext()) {
                    // 一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    String[] splitItem = (file.getOriginalFilename()).split("\\.");
                    fileType = splitItem[1];
                    if (file != null) {
                        if (!fileUp.exists()){
                            fileUp.mkdirs();
                        }else {
                            path = "/TruckPic/"+ sdf.format(DateUtil.getDate())+member_name+"."+fileType;
                            logger.info(file.getOriginalFilename());
                            // 上传
                            file.transferTo(new File(baseUrl+path));
                        }
                    }
                    ecode = "1000";
                    result.put("ecode",ecode);
                    result.put("file_url",path);
                }
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode);
            logger.error(LogUtil.saveErr + e.getMessage());
        }
        logger.info("ecode="+ecode);
        response.getWriter().print(JSONUtil.toJSONString(result));
    }
}