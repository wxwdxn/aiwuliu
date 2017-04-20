/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ScheduleSheetController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：调度单管理页面
 * 完成日期：2016-03-25
 * 作    者：WXW
 * 内容摘要：调度单的管理
 */

package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.service.ScheduleSheetService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.ScheduleSheetManager_Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 类 名 称: ScheduleSheetController
 * 内容摘要: 调度单的管理：增，删，改，查
 * 方法描述：该类有7个方法：
 *         01 home              调度单页面调用
 *         02 home_new          调度单页面调用(分角色进入)
 *         03 sheetList         查询所有的调度单
 *         04 scheduleSheetList 分页查询所有的调度单列表
 *         05 saveScheduleSheet 保存调度单
 *         06 scheduleSheetUpdate 更新调度单
 *         07 scheduleSheetDel    删除调度单
 * @author WXW
 */
@Controller
@RequestMapping(value = "/scheduleSheetManager")
public class ScheduleSheetController {

    Logger logger = Logger.getLogger(ScheduleSheetController.class);
    @Resource
    private ScheduleSheetService scheduleSheetService;

    @Value("#{setting[baseUrl]}")//映射下载文件根目录 properties
    private String baseUrl;

    /**
     * 方法名称：scheduleSheetDel
     * 内容摘要：删除调度单。
     * @param request         request
     * @param response        response
     * @param scheduleSheetIds scheduleSheetIds
     * @param model
     */
    @RequestMapping(value = "scheduleSheetDel")
    public void scheduleSheetDel(String scheduleSheetIds, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] scheduleSheetId=scheduleSheetIds.split(",");
        try {
            for (int i=0;i<scheduleSheetId.length;i++){
                scheduleSheetService.delScheduleSheet(scheduleSheetId[i]);
            }
        } catch (Exception e) {
            logger.error(ScheduleSheetManager_Message.DelScheduleSheetError + e.getMessage());
        }
    }

    /**
     * 方法名称：loadingProofUpload
     * 内容摘要：磅单的上传。
     * @param request       request
     * @param response      response
     */
    @RequestMapping(value = "loadingProofUpload")
    public void loadingProofUpload(String member_name, HttpServletRequest request, HttpServletResponse response)throws IOException {
        Map result=new HashMap();
        String ecode = null;
        //获取tomcat项目部署的本地地址
        String domainPath = request.getSession().getServletContext().getRealPath("/");
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        String baseUrl = domainPath + File.separator + this.baseUrl;
        String fileType = null;
        String path=null;
        int i = 1;     //循环次数
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            // 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                // 将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                File fileUp = new File(baseUrl + "/proofImagePic");
                if (!fileUp.exists()) {
                    //创建文件
                    fileUp.mkdirs();
                }
                while (iter.hasNext()) {
                    // 一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    String[] splitItem = (file.getOriginalFilename()).split("\\.");
                    if (splitItem[0] != "") {
                        fileType = splitItem[1];
                        if (file != null) {
                            if (!fileUp.exists()){
                                fileUp.mkdirs();
                            } else {
                                path = "/proofImagePic/" + sdf.format(DateUtil.getDate()) + UUIDUtil.getUUID() +member_name+ "." + fileType;
                                // 上传
                                file.transferTo(new File(baseUrl + path));
                            }
                        }
                        ecode = "1000";
                        result.put("ecode",ecode);
                        result.put("file_url",path);
                    }
                }
            }

        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode);
            logger.error(e.getMessage());
        }
        response.getWriter().print(JSONUtil.toJSONString(result));
    }


    /**
     * 方法名称：expenseImageUpload
     * 内容摘要：消费单据的上传。
     * @param request       request
     * @param response      response
     */
    @RequestMapping(value = "expenseImageUpload")
    public void expenseImageUpload(String member_name, HttpServletRequest request, HttpServletResponse response)throws IOException {
        Map result=new HashMap();
        String ecode = null;
        String path=null;
        //获取tomcat项目部署的本地地址
        String domainPath = request.getSession().getServletContext().getRealPath("/");
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        String baseUrl = domainPath + File.separator + this.baseUrl;
        String fileType = null;
        int i = 1;     //循环次数
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            // 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                // 将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                File fileUp = new File(baseUrl + "/expenseImagePic");
                if (!fileUp.exists()) {
                    //创建文件
                    fileUp.mkdirs();
                }
                while (iter.hasNext()) {
                    // 一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    String[] splitItem = (file.getOriginalFilename()).split("\\.");
                    if (splitItem[0] != "") {
                        fileType = splitItem[1];
                        if (file != null) {
                            if (!fileUp.exists()){
                                fileUp.mkdirs();
                            } else {
                                path = "/expenseImagePic/" + sdf.format(DateUtil.getDate()) + UUIDUtil.getUUID() +member_name+ "." + fileType;
                                // 上传
                                file.transferTo(new File(baseUrl + path));
                            }
                        }
                        ecode = "1000";
                        result.put("ecode",ecode);
                        result.put("fileUrl",path);
                    }
                }
            }

        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode);
            logger.error(e.getMessage());
        }
        response.getWriter().print(JSONUtil.toJSONString(result));
    }

}
