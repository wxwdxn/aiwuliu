/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: LoginController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：会员信息管理页面
 * 设计文件：
 * 完成日期：2015-02-17
 * 作    者：YK
 * 内容摘要：会员信息管理页面的CRUD
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.util.*;
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
 * 类 名 称: MemberController
 * 内容摘要: 会员信息管理页面的CRUD
 * 方法描述：该类有6个方法：
 * 01 home              会员信息管理页面调用
 * 02 memberList        会员信息管理页面显示
 * 03 memberUpdate      会员信息更新
 * 04 memberDel         会员信息删除
 * 05 members           所有的调度单(关联id)
 * 06 member            所有的调度单(会员id)
 * 07 headPicSave       上传图片
 *
 * @author YK
 */
@Controller
@RequestMapping(value = "/memberManager")
public class MemberController {
    Logger logger = Logger.getLogger(MemberController.class);

    @Resource
    private MemberService memberService;
    @Value("#{setting[baseUrl]}")
    private String baseUrl;

    /**
     * 方法名称：members
     * 内容摘要：所有的调度单(关联id)。
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "members")
    public String members(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Member> memberList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            memberList = memberService.findAll();
            if (memberList.size() > 0 && i < memberList.size()) {
                for (T_Data_Member member : memberList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", member.getRelevance_info_id());
                    results.put("name", member.getMember_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }

    /**
     * 方法名称：member
     * 内容摘要：所有的调度单(会员id)。
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "member")
    public String member(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Member> memberList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            memberList = memberService.findAll();
            if (memberList.size() > 0 && i < memberList.size()) {
                for (T_Data_Member member : memberList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", member.getMember_id());
                    results.put("name", member.getMember_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }

    /**
     * 方法名称：headPicSave
     * 内容摘要：针对司机版上传图片
     *
     * @param member_name 会员名称
     * @param request     request
     * @param response    response
     */
    @RequestMapping(value = "headPicSave")
    public void headPicSave(String member_name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        T_Data_Member member = null;
        logger.info("member_name=" + member_name);
        String domainPath = request.getSession().getServletContext().getRealPath("/");
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        String baseUrl = domainPath + File.separator + this.baseUrl;
        String ecode = null;
        Map result = new HashMap();
        String fileType = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            member = this.memberService.findMemberByName(member_name); // 会员名是唯一，否则报错
            // 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                // 将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                File fileUp = new File(baseUrl + "/UserHeadPic");
                while (iter.hasNext()) {
                    // 一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    String[] splitItem = (file.getOriginalFilename()).split("\\.");
                    fileType = splitItem[1];
                    if (file != null) {
                        if (!fileUp.exists()) {
                            fileUp.mkdirs();
                        } else {
                            String path = baseUrl + "/UserHeadPic/" + sdf.format(DateUtil.getDate()) + member_name + "." + fileType;
                            logger.info(file.getOriginalFilename());
                            // 上传
                            file.transferTo(new File(path));
                        }
                    }
                    member.setUser_head_pic_id("/UserHeadPic/" + sdf.format(DateUtil.getDate()) + member_name + "." + fileType);
                }
                member.setLast_update(DateUtil.getDate());
                member.setLast_update_user_id("M:" + member_name);
                this.memberService.updateHeadPic(member);
                ecode = "1000";
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(LogUtil.saveErr + e.getMessage());
        }
        logger.info("ecode=" + ecode);
        response.getWriter().print(JSONUtil.toJSONString(result));
    }
}

