/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：UserRoleManagerServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-09
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DicdataDaoMapper;
import com.cn.gazelle.logistics.dao.MemberDaoMapper;
import com.cn.gazelle.logistics.dao.PersonDaoMapper;
import com.cn.gazelle.logistics.dao.UserDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_Person;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.pojo.T_Sys_User;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.service.UserRoleManagerService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.MD5Util;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：UserRoleManagerServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.UserRoleManagerService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class UserRoleManagerServiceImpl implements UserRoleManagerService {
    // Log初始化
    Logger logger = Logger.getLogger(UserRoleManagerServiceImpl.class);
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;
    @Resource
    private MemberService memberService;
    @Resource
    private MemberDaoMapper memberDaoMapper;
    @Resource
    private UserDaoMapper userDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;


    /**
     * 方法名称：saveUserRoleInfo
     * 内容摘要：保存用户角色信息
     *
     * @param info     info
     * @param username username
     * @return int
     */
    @Transactional
    public int saveUserRoleInfo(String info, String username) {
        int flag = 0;
        T_Sys_User user = new T_Sys_User();
        T_Data_Member member = new T_Data_Member();
        T_Data_Person person = new T_Data_Person();
        List<T_Sys_Dicdata> dicdataList_group = null;
        List<T_Sys_Dicdata> dicdataList_role = null;
        Map<String, String> memberMap = new HashMap<String, String>();
        String json = null; // 会员保存返回值
        String code = null; // 响应码
        // 用户表
        String group_name = null;                             // 用户
        String role_name = null;                              // 角色
        // 会员表
        String mobile_phone = null;                          // 手机
        String member_name = null;                           // 账户名称
        String member_password = null;                       // 密码
        String signed_electronica_agreement_no = null;       // 签署电子协议编号
        String account_type = null;                          // 账户类型
        // 个人表
        String person_name = null;                           // 姓名
        String id_card_number = null;                        // 身份证
        Gson gson = new Gson();
        Map<String, String> data = gson.fromJson(info, new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            dicdataList_group = this.dicdataDaoMapper.findAllDicdataByCode("741808870D6F488AA5BEB89502B1AD15",
                    data.get("group_name"));
            group_name = dicdataList_group.get(0).getDicdata_name();
            dicdataList_role = this.dicdataDaoMapper.findAllDicdataByCode("F26ACEE58D0041169AD0A183AAF83D13",
                    data.get("role_name"));
            role_name = dicdataList_role.get(0).getDicdata_name();
            mobile_phone = data.get("mobile_phone");
            member_name = data.get("member_name");
            member_password = data.get("member_password");
            signed_electronica_agreement_no = data.get("signed_electronica_agreement_no");
            person_name = data.get("person_name");
            id_card_number = data.get("id_card_number");
            // 会员表保存
            memberMap.put("member_name", mobile_phone);
            memberMap.put("member_password", member_password);
            memberMap.put("account_type", "0");// 默认是个人
            memberMap.put("member_type", "1");// 默认是司机
            json = this.memberService.saveMember(JSONUtil.toJSONString(memberMap), "");
            Map<String, String> registerMap = gson.fromJson(json, new TypeToken<Map<String, String>>() {
            }.getType());
            code = registerMap.get("ecode");
            if (code.equals("2000")) {
                // 注册失败
                flag = -1;
                throw new RuntimeException(flag + "");
            } else if (code.equals("3002")) {
                // 用户已经注册
                flag = 2;
                throw new RuntimeException(flag + "");
            } else {
                // 用户表保存
                user.setUser_id(UUIDUtil.getUUID());
                user.setGroup_name(group_name);
                user.setFunRole_name(role_name);
                user.setUser_name(person_name);
                user.setLogin_name(mobile_phone);  // 登录名默认手机号码
                user.setUser_phone(mobile_phone);
                user.setLogin_password(MD5Util.md5(member_password));
                user.setUser_date(DateUtil.getDate());
                this.userDaoMapper.saveUser(user);
                // 个人表保存
                member = this.memberDaoMapper.findMemberByName(mobile_phone);
                account_type = member.getAccount_type();
                person.setPerson_name(person_name);
                person.setId_card_number(id_card_number);
                person.setSubmit_relate_time(null);
                person.setConfirm_relate_time(null);
                person.setSubmit_verify_time(null);
                person.setVerify_refused_time(null);
                person.setVerify_passed_time(null);
                person.setPerson_mobile_phone(member_name);
                person.setVerify_status("1");
                person.setPerson_id(UUIDUtil.getUUID());
                person.setLast_update(DateUtil.getDate());
                person.setLast_update_user_id(username);
                person.setPerson_type(member.getMember_type());
                person.setDelete_flag("0");
                this.personDaoMapper.savePerson(person);
                if (account_type == "1") {
                    member.setSigned_electronica_agreement_no(signed_electronica_agreement_no);
                    member.setRelevance_info_id(person.getPerson_id());
                    member.setMember_belong_code(member.getMember_belong_code() + "/" + person.getPerson_id());
                    this.memberDaoMapper.updateMember(member);
                }
                // 默认是司机
                else {
                    member.setSigned_electronica_agreement_no(signed_electronica_agreement_no);
                    member.setRelevance_info_id(person.getPerson_id());
                    member.setMember_belong_code(person.getPerson_id());
                    this.memberDaoMapper.updateMember(member);
                    this.memberDaoMapper.storeMemberByID(member.getMember_id());
                }
            }
            flag = 1;
        } catch (Exception e) {
            if (e.getMessage().equals("2")) {
                flag = 2;
            } else {
                flag = -1;
            }
            logger.error(LogUtil.delErr + e.getMessage());
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：updateUserRoleInfo
     * 内容摘要：更新用户信息。
     *
     * @param info     info
     * @param username username
     * @return int
     */
    @Transactional
    public int updateUserRoleInfo(String member_id, String user_id, String info, String username) {
        int flag = 0;
        T_Data_Member member_raw = new T_Data_Member();
        T_Sys_User user_raw = new T_Sys_User();
        Gson gson = new Gson();
        Map<String, String> data = gson.fromJson(info, new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            member_raw = this.memberDaoMapper.findMemberByID(member_id);
            user_raw = this.userDaoMapper.findUserByID(user_id);
            // 判断账户状态是否为正常（0）
            if (member_raw.getAccount_status().equals("0")) {
                if (data.get("account_status").equals("1")) {
                    member_raw.setLock_time(DateUtil.getDate());
                    member_raw.setAccount_status("1");
                }
            }
            // 判断账户状态是否为锁定（1）
            else if (member_raw.getAccount_status().equals("1")) {
                if (data.get("account_status").equals("0")) {
                    member_raw.setUnlock_time(DateUtil.getDate());
                    member_raw.setAccount_status("0");
                }
            }
            // 修改密码
            if (!data.get("member_password_again").equals("") && data.get("member_password_again") != null) {
                if (MD5Util.md5(data.get("member_password_again")).equals(member_raw.getMember_password())) {
                    // 密码和原密码相同
                    flag = 2;
                    throw new RuntimeException(flag + "");
                } else {
                    member_raw.setMember_password(MD5Util.md5(data.get("member_password_again")));
                    member_raw.setLast_update_user_id(username);
                    member_raw.setLast_update(DateUtil.getDate());
                    // 保存到会员表
                    this.memberService.updateMember(member_raw);
                    // 保存到用户表
                    if (user_raw != null) {
                        user_raw.setLogin_password(MD5Util.md5(data.get("member_password_again")));
                        user_raw.setUser_date(DateUtil.getDate());
                        this.userDaoMapper.updateUser(user_raw);
                    }
                }
            } else {
                member_raw.setLast_update_user_id(username);
                member_raw.setLast_update(DateUtil.getDate());
                // 保存到会员表
                this.memberService.updateMember(member_raw);
                // 保存到用户表
                if (user_raw != null) {
                    user_raw.setUser_date(DateUtil.getDate());
                    this.userDaoMapper.updateUser(user_raw);
                }
            }
            flag = 1;
        } catch (Exception e) {
            if (e.getMessage().equals("2")) {
                flag = 2;
            } else {
                flag = -1;
            }
            logger.error(e.getMessage());
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：delUserRoleInfo
     * 内容摘要：删除用户信息。
     *
     * @param member_id member_id
     * @return int
     */
    @Transactional
    public int delUserRoleInfo(String member_id) {
        int flag = 0;
        T_Data_Member member = null;
        T_Sys_User user = null;
        try {
            member = this.memberDaoMapper.findMemberByID(member_id);
            user = this.userDaoMapper.findUserByLoginName(member.getMember_name());
            this.personDaoMapper.delPerson(member.getRelevance_info_id());
            this.memberDaoMapper.delMember(member_id);
            this.userDaoMapper.delUser(user.getUser_id());
            flag = 1;
        } catch (Exception e) {
            flag = -1;
            logger.error(MessageUtil.delInfoError + e.getMessage());
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

}
