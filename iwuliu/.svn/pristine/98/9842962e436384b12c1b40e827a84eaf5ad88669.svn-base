/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: PersonController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：司机信息管理页面
 * 设计文件：
 * 完成日期：2016-02-19
 * 作    者：YK
 * 内容摘要：司机信息管理页面的CRUD
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_Person;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.service.PersonService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
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
import java.util.*;

/**
 * 类 名 称: PersonController
 * 内容摘要: 司机信息管理页面的CRUD
 * 方法描述：该类有6个方法：
 * 01 home              会员信息管理页面调用
 * 02 memberList        会员信息管理页面显示
 * 03 memberUpdate      会员信息更新
 * 04 memberDel         会员信息删除
 * 05 members           所有的调度单(关联id)
 * 06 member            所有的调度单(会员id)
 * 07 personPicUpLoad   个人信息图片上传
 *
 * @author YK
 */
@Controller
@RequestMapping(value = "/personManager")
public class PersonController {
    Logger logger = Logger.getLogger(PersonController.class);

    @Resource
    private PersonService personService;
    @Resource
    private MemberService memberService;
    @Resource
    private DicdataService dicdataService;
    @Value("#{setting[baseUrl]}")
    private String baseUrl;

    /**
     * 方法名称：personPicUpLoad
     * 内容摘要：个人信息图片上传
     *
     * @param member_name   会员名称
     * @param file_property 文件属性
     * @param request       request
     * @param response      response
     */
    @RequestMapping(value = "/personPicUpLoad")
//    public void personPicUpLoad(String member_name, String file_property, HttpServletRequest request, HttpServletResponse response,
//                                ModelMap model) throws IOException {
//        logger.info("member_name=" + member_name);
//        T_Data_Member member = null;
//        T_Data_Person person_old = null;
//        T_Data_Person person_new = new T_Data_Person();
//        String domainPath = request.getSession().getServletContext().getRealPath("/");
//        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
//        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
//        String baseUrl = domainPath + File.separator + this.baseUrl;
//        Map result = new HashMap();
//        String ecode = null;
//        String fileType = null;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            member = this.memberService.findMemberByName(member_name);
//            person_old = this.personService.findPersonByID(member.getRelevance_info_id());
//            // 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
//            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//                    request.getSession().getServletContext());
//            // 检查form中是否有enctype="multipart/form-data"
//            if (multipartResolver.isMultipart(request)) {
//                // 将request变成多部分request
//                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//                // 获取multiRequest 中所有的文件名
//                Iterator iter = multiRequest.getFileNames();
//                File fileUp = new File(baseUrl + "/personPic");
//                while (iter.hasNext()) {
//                    // 一次遍历所有文件
//                    MultipartFile file = multiRequest.getFile(iter.next().toString());
//                    String[] splitItem = (file.getOriginalFilename()).split("\\.");
//                    fileType = splitItem[1];
//                    if (file != null) {
//                        if (!fileUp.exists()) {
//                            fileUp.mkdirs();
//                        } else {
//                            String path = "/personPic/" + sdf.format(DateUtil.getDate()) + member_name + "." + fileType;
//                            logger.info(sdf.format(DateUtil.getDate()));
//                            // 上传
//                            file.transferTo(new File(baseUrl + path));
//                            // 判断该会员在数据库是否存在个人信息
//                            if (person_old == null) {
//                                person_new.setPerson_id(UUIDUtil.getUUID());
//                                person_new.setPerson_name("none");
//                                person_new.setPerson_mobile_phone("none");
//                                person_new.setId_card_number("none");
//                                person_new.setVerify_status("1");
//                                person_new.setLast_update(DateUtil.getDate());
//                                person_new.setLast_update_user_id("M:" + member_name);
//                                person_new.setSubmit_relate_time(null);
//                                person_new.setConfirm_relate_time(null);
//                                person_new.setSubmit_verify_time(null);
//                                person_new.setVerify_refused_time(null);
//                                person_new.setVerify_passed_time(null);
//                                if (file_property.equals("hold_id_card_pic_id")) {
//                                    person_new.setHold_id_card_pic_id(path);
//                                } else if (file_property.equals("id_card_front_pic_id")) {
//                                    person_new.setId_card_front_pic_id(path);
//                                } else if (file_property.equals("id_card_back_pic_id")) {
//                                    person_new.setId_card_back_pic_id(path);
//                                } else if (file_property.equals("driver_licence_main_pic_id")) {
//                                    person_new.setDriver_licence_main_pic_id(path);
//                                } else if (file_property.equals("driver_licence_sub_pic_id")) {
//                                    person_new.setDriver_licence_sub_pic_id(path);
//                                } else if (file_property.equals("qualification_certificate_number_pic_id")) {
//                                    person_new.setQualification_certificate_number_pic_id(path);
//                                }
//                                person_new.setDelete_flag("0");
//                                person_new.setPerson_type(member.getMember_type());
//                                this.personService.savePerson(person_new);
//                                logger.info(person_new.getPerson_id());
//                                member.setRelevance_info_id(person_new.getPerson_id());
//                                member.setMember_belong_code(person_new.getPerson_id());
//                                this.memberService.updateMember(member);
//                                ecode = "1000";
//                                result.put("ecode", ecode);
//                                String ret = JSONUtil.toJSONString(result);
//                                response.getWriter().println(ret);
//                            } else {
//                                if (file_property.equals("hold_id_card_pic_id")) {
//                                    person_old.setHold_id_card_pic_id(path);
//                                } else if (file_property.equals("id_card_front_pic_id")) {
//                                    person_old.setId_card_front_pic_id(path);
//                                } else if (file_property.equals("id_card_back_pic_id")) {
//                                    person_old.setId_card_back_pic_id(path);
//                                } else if (file_property.equals("driver_licence_main_pic_id")) {
//                                    person_old.setDriver_licence_main_pic_id(path);
//                                } else if (file_property.equals("driver_licence_sub_pic_id")) {
//                                    person_old.setDriver_licence_sub_pic_id(path);
//                                } else if (file_property.equals("qualification_certificate_number_pic_id")) {
//                                    person_old.setQualification_certificate_number_pic_id(path);
//                                }
//                                person_old.setLast_update(DateUtil.getDate());
//                                person_old.setLast_update_user_id("M:" + member_name);
//                                this.personService.updatePerson(person_old);
//                                ecode = "1000";
//                                result.put("ecode", ecode);
//                                String ret = JSONUtil.toJSONString(result);
//                                response.getWriter().println(ret);
//                            }
//                        }
//                    }
//
//                }
//            }
//        } catch (Exception e) {
//            ecode = "2000";
//            result.put("ecode", ecode);
//            String ret = JSONUtil.toJSONString(result);
//            response.getWriter().println(ret);
//            logger.error(LogUtil.saveErr + e.getMessage());
//            e.printStackTrace();
//        }
//        logger.info(JSONUtil.toJSONString(result));
//    }
    public void personPicUpLoad(String member_name, String file_property, HttpServletRequest request, HttpServletResponse response,
                                ModelMap model) throws IOException {
        logger.info("member_name=" + member_name);
        logger.info("file_property=" + file_property);
        T_Data_Member member = null;
        T_Data_Person person_old = null;
        T_Data_Person person_new = new T_Data_Person();
        // 获取图片保存tomcat下webapp的路径
        String domainPath = request.getSession().getServletContext().getRealPath("/");
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
        String baseUrl = domainPath + File.separator + this.baseUrl;
        Map result = new HashMap();
        String ecode = null;
        String fileType = null;
        String path = null;
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
                File fileUp = new File(baseUrl + "/personPic");
                while (iter.hasNext()) {
                    // 一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    String[] splitItem = (file.getOriginalFilename()).split("\\.");
                    fileType = splitItem[1];
                    if (file != null) {
                        // 当文件夹不存在时，生成一个
                        if (!fileUp.exists()) {
                            fileUp.mkdirs();
                        } else {
                            path = "/personPic/" + sdf.format(DateUtil.getDate()) + member_name + "." + fileType;
                            // 上传
                            file.transferTo(new File(baseUrl + path));
                        }
                        ecode = "1000";
                        result.put("ecode", ecode);
                        result.put("file_url", path);
                    }
                }
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(LogUtil.saveErr + e.getMessage());
        }
        logger.info("ecode=" + ecode);
        response.getWriter().print(JSONUtil.toJSONString(result));
    }

    /**
     * 方法名称：personList
     * 内容摘要：获取个人信息列表不分页
     *
     * @param request  request
     * @param response response
     * @param model    model
     **/
    @RequestMapping(value = "personJsonList")
    public String personJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Data_Person> personList = null;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        int i = 0;
        try {
            personList = personService.findPersonShippers();
            if (personList.size() > 0 && i < personList.size()) {
                for (T_Data_Person person : personList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", person.getPerson_id());
                    results.put("name", person.getPerson_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error("Error:" + e.getMessage());

        }
        return JSONUtil.toJSONString(lists);
    }
}
