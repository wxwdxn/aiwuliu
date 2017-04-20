/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MemberServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员信息查询接口实现
 * 设计文件：
 * 完成日期：2016-01-12
 * 作    者：YK
 * 内容摘要：会员信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.MemberService;
import com.cn.gazelle.logistics.service.TruckService;
import com.cn.gazelle.logistics.sms.SMSManager;
import com.cn.gazelle.logistics.util.*;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称: MemberServiceImpl
 * 内容摘要: 会员信息查询接口
 * 方法描述：该类有19个方法：
 * 01 findMemberByID                 根据会员ID查询会员信息
 * 02 storeMemberByID                根据会员id实现存储过程
 * 03 findMemberByName               根据会员名查询会员信息
 * 04 findAllMember                  查询所有会员信息有分页
 * 05 findAll                        查询所有会员信息（不分页）
 * 06 findMemberByNameAndPassword    根据会员名和会员密码查询会员信息
 * 07 findAllMemberCount             查询会员记录数(包含有条件和无条件)
 * 08 memberLogin                    会员登录
 * 09 saveMember                     注册会员信息
 * 10 editMember                     当前公司管理账号信息的修改
 * 11 delMember                      删除会员信息
 * 12 phoneCode                      获取验证码(个人)
 * 13 phoneCode2                     获取验证码(公司)
 * 14 vertifyPhoneCode               验证验证码
 * 15 addSubMember                   下属人员的添加
 * 16 delSubMember                   下属人员的删除
 * 17 findAllSubMember               根据账号检索下属人员列表
 * 18 findCarCaptain                 根据账号获取车队长列表
 * 19 findSubBuser                   根据账号检索下属司机(无绑定车辆)列表
 *
 * @author YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.MemberService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class MemberServiceImpl implements MemberService {
    // 全局变量
    public static String code;
    private static String contact_mobile_phone;

    // Log初始化
    Logger logger = Logger.getLogger(MemberServiceImpl.class);

    @Resource
    private MemberDaoMapper memberDaoMapper;                                                                    // 数据访问层
    @Resource
    private CompanyDaoMapper companyDaoMapper;                                                                  // 数据访问层
    @Resource
    private PersonDaoMapper personDaoMapper;                                                                    // 数据访问层
    @Resource
    private TruckDaoMapper truckDaoMapper;                                                                      // 数据访问层
    @Resource
    private TruckService truckService;                                                                          // 数据访问层
    @Resource
    private SysInfoDaoMapper sysInfoDaoMapper;                                                                  // 数据访问层
    @Resource
    private DispatchSheetDaoMapper dispatchSheetDaoMapper;                                                      // 数据访问层
    @Resource
    private VercodeDaoMapper vercodeDaoMapper;                                                                   // 数据访问层
    @Resource
    private TruckPaymentErrorPasswordCommitHistoryDaoMapper truckPaymentErrorPasswordCommitHistoryDaoMapper;     // 数据访问层
    @Resource
    private MemberPaymentHistoryDaoMapper memberPaymentHistoryDaoMapper;                                         // 数据访问层
    @Resource
    private MemberBankAccountDaoMapper memberBankAccountDaoMapper;                                               // 数据访问层

    /**
     * 方法名称：findMemberByID
     * 内容摘要：根据会员id查找会员信息。
     *
     * @param member_id 会员id
     * @return T_Data_Member 会员信息
     */
    public T_Data_Member findMemberByID(String member_id) {
        return memberDaoMapper.findMemberByID(member_id);
    }

    /**
     * 方法名称：storeMemberByID
     * 内容摘要：根据会员id实现存储过程
     *
     * @param member_id 会员id
     * @return T_Data_Member 会员信息
     */
    public T_Data_Member storeMemberByID(String member_id) {
        T_Data_Member member = null;
        try {
            member = this.memberDaoMapper.storeMemberByID(member_id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return member;
    }

    /**
     * 方法名称：findMemberByName
     * 内容摘要：根据会员名查询会员
     *
     * @param member_name 会员名
     * @return T_Data_Member 会员信息
     */
    public T_Data_Member findMemberByName(String member_name) {
        T_Data_Member member = null;
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return member;
    }

    /**
     * 方法名称：findAll
     * 内容摘要：查找所有会员（不分页）
     *
     * @return List<T_Data_Member> 用户信息列
     */
    public List<T_Data_Member> findAll() {
        List<T_Data_Member> memberList = null;
        try {
            memberList = memberDaoMapper.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return memberList;
    }

    /**
     * 方法名称：findMemberByNameAndPassword
     * 内容摘要：根据会员名和会员密码查询会员信息
     *
     * @param member_name     会员名
     * @param member_password 登录密码
     * @param member_type     会员类型
     * @return T_Data_Member 会员信息
     */
    public T_Data_Member findMemberByNameAndPassword(String member_name, String member_password, String member_type) {
        T_Data_Member member = null;        // 会员信息
        member = memberDaoMapper.findMemberByNameAndPassword(member_name, member_password, member_type);
        return member;
    }

    /**
     * 方法名称：memberLogin
     * 内容摘要：会员登录
     *
     * @param memberJson json
     * @return String  返回值json
     */
    public String memberLogin(String memberJson) {
        T_Data_Member t_data_member = null; // 当前用户信息
        Map result = new HashMap();
        Map<String, String> results = new HashMap<String, String>();
        String json = null;
        String ecode = null;
        String lock_reason = null;
        String member_business_manage_code = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        // memberJson解析
        Gson gson = new Gson();
        Type typeClass = new TypeToken<T_Data_Member>() {
        }.getType();
        T_Data_Member member = gson.fromJson(memberJson, typeClass);
        try {
            t_data_member = this.memberDaoMapper.findMemberByName(member.getMember_name());
            if (t_data_member == null) {
                ecode = "3005"; // 用户名不存在！
                result.put("ecode", ecode);
            } else if (t_data_member != null && memberDaoMapper.findMemberByPasswordAndName(member.getMember_name(), MD5Util.md5(member.getMember_password())) == null) {
                ecode = "3001"; // 用户名或密码不正确！
                result.put("ecode", ecode);
            }
            // 判断账号是否处于锁定状态
            else if (t_data_member.getAccount_status() == "1") {
                ecode = "1000"; // 账户已锁定
                results.put("account_status", t_data_member.getAccount_status());
                results.put("account_type", t_data_member.getAccount_status());
                results.put("user_head_pic_id", "none");
                results.put("lock_reason", t_data_member.getLock_reason());
                if (t_data_member.getLock_time() != null) {
                    results.put("lock_time", sdf.format(t_data_member.getLock_time()));
                } else {
                    results.put("lock_time", "none");
                }
                member_business_manage_code = t_data_member.getMember_business_manage_code();
                if (member_business_manage_code != null && member_business_manage_code.equals("") && member_business_manage_code.split("/").length > 1) {
                    results.put("is_employ", "1");
                } else {
                    results.put("is_employ", "0");
                }
                result.put("ecode", ecode);
                result.put("object1", results);
            } else if (memberDaoMapper.findMemberByNameAndPassword(member.getMember_name(), MD5Util.md5(member.getMember_password()), member.getMember_type()) != null) {
                ecode = "1000"; // 登录成功!
                results.put("account_status", t_data_member.getAccount_status());
                results.put("account_type", t_data_member.getAccount_status());
                results.put("user_head_pic_id", t_data_member.getUser_head_pic_id());
                results.put("lock_reason", t_data_member.getLock_reason());
                if (t_data_member.getLock_time() != null) {
                    results.put("lock_time", sdf.format(t_data_member.getLock_time()));
                } else {
                    results.put("lock_time", "none");
                }
                member_business_manage_code = t_data_member.getMember_business_manage_code();
                if (member_business_manage_code != null && member_business_manage_code.equals("") && member_business_manage_code.split("/").length > 1) {
                    results.put("is_employ", "1");
                } else {
                    results.put("is_employ", "0");
                }
                result.put("ecode", ecode);
                result.put("object1", results);
            } else {
                ecode = "4000"; // 登录账号版本错误
                result.put("ecode", ecode);
            }
            logger.info("ecode=" + ecode);
            json = JSONUtil.toJSONString(result);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        return json;

    }

    /**
     * 方法名称：findPassword
     * 内容摘要：找回密码
     *
     * @param memberJson json
     * @return String 返回值json
     */
    public String findPassword(String memberJson) {
        String ecode = null; // 返回值
        Map result = new HashMap();
        // memberJson解析
        Gson gson = new Gson();
        Type typeClass = new TypeToken<T_Data_Member>() {
        }.getType();
        T_Data_Member member_new = gson.fromJson(memberJson, typeClass);
        try {
            T_Data_Member member_old = null; // 原会员对象
            member_old = memberDaoMapper.findMemberByName(member_new.getMember_name());
            if (member_old == null) {
                ecode = "3005"; // 该手机号未注册！
                result.put("ecode", ecode);
            } else if (member_old.getMember_password().equals(MD5Util.md5(member_new.getMember_password()))) {
                ecode = "3002"; // 重置密码与原密码重合！
                result.put("ecode", ecode);
            } else {
                member_old.setMember_password(MD5Util.md5(member_new.getMember_password()));
                this.memberDaoMapper.updatePassword(member_new.getMember_name(), MD5Util.md5(member_new.getMember_password()));
                ecode = "1000"; // 重置密码成功！
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000"; // 响应失败
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        logger.info("ecode=" + ecode);
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：saveMember
     * 内容摘要：注册会员信息
     *
     * @param memberJson   会员json
     * @param company_name 公司名称
     * @return String 返回值json
     */
    public String saveMember(String memberJson, String company_name) {
        String ecode = null; // 返回值
        T_Data_Company company = null;
        Map result = new HashMap();
        // memberJson解析
        Gson gson = new Gson();
        Type typeClass = new TypeToken<T_Data_Member>() {
        }.getType();
        T_Data_Member member = gson.fromJson(memberJson, typeClass);
        try {
            String member_name = member.getMember_name();
            // 判断用户是否已经注册（会员名是唯一的）
            if (memberDaoMapper.findMemberByName(member_name) != null) {
                ecode = "3002"; // 该用户名已经注册！
                result.put("ecode", ecode);
            } else {
                member.setMember_password(MD5Util.md5(member.getMember_password()));// 密码加密
                member.setMember_id(UUIDUtil.getUUID());
                member.setLast_update(DateUtil.getDate());
                member.setAccount_status("0");// 账户状态默认通过
                member.setMobile_phone(member.getMember_name());
                member.setLast_update_user_id("M:" + member_name); // 最新更新者id
                // 判断会员是否隶属于公司
                if (company_name != null && !company_name.equals("")) {
                    // 隶属于公司
                    company = this.companyDaoMapper.findCompanyByName(company_name, "0");
                    member.setRelevance_info_id(company.getCompany_id());  // 关联企业信息id（不会再关联个人id）
                    member.setMember_belong_code(company.getCompany_id()); // 所属关系信息id
                }
                member.setRegister_time(DateUtil.getDate()); // 会员注册时间
                member.setDelete_flag("0");
                this.memberDaoMapper.saveMember(member);
                logger.info("mobile_phone：" + member.getMobile_phone());
                ecode = "1000"; // 注册成功！
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError + e.getMessage());
            ecode = "2000"; // 注册失败！
            result.put("ecode", ecode);
        }
        logger.info("ecode=" + ecode);
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：editMember
     * 内容摘要：当前公司管理账号信息的修改
     *
     * @param member 会员信息
     * @return String 返回值json
     */
    public String editMember(T_Data_Member member) {
        String ecode = null;
        T_Data_Member member2 = null;
        String member_name = member.getMember_name();
        Map result = new HashMap();
        try {
            member2 = this.memberDaoMapper.findMemberByName(member_name);
            if (member2 == null) {
                ecode = "3005"; // 用户名不存在
                result.put("ecode", ecode);
            } else {
                member2.setMobile_phone(member.getMobile_phone());
                member2.setUser_head_pic_id(member.getUser_head_pic_id());
                this.memberDaoMapper.updateMember(member2);
                ecode = "1000"; // 账号信息修改成功
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000"; // 账号信息修改失败
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        return JSONUtil.toJSONString(ecode);
    }

    /**
     * 方法名称：updateMember
     * 内容摘要：更新会员信息
     *
     * @param member 会员信息
     * @return boolean 更新true或false
     */
    public boolean updateMember(T_Data_Member member) {
        boolean b = true;
        try {
            member.setLast_update(DateUtil.getDate());
            this.memberDaoMapper.updateMember(member);
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            b = false;
        }
        return b;
    }

    /**
     * 方法名称：delMember
     * 内容摘要：删除会员信息
     *
     * @param member_id 会员id
     */
    public void delMember(String member_id) {
        this.memberDaoMapper.delMember(member_id);
    }

    /**
     * 方法名称：phoneCode
     * 内容摘要：获取手机验证码(个人)
     *
     * @param tel 手机号码
     * @param request_type 0注册 1是找回密码
     * @return String 返回值ecode
     */
    public String phoneCode(String tel, String request_type) {
        // 此处调用发送验证码方法
        // 1、生成验证码
        // 2、返回给客户端程序
        // 3、将验证码发送到tel手机号里
        String ecode = null;
        String code = null;
        Map result = new HashMap();
        T_Data_Member member = null;
        T_Data_Vercode vercode = new T_Data_Vercode();
        Gson gson = new Gson();
        try {
            member = this.memberDaoMapper.findMemberByName(tel);
            // 注册
            if (request_type.equals("0")) {
                if (member != null) {
                    ecode = "3002"; // 该用户已注册
                    result.put("ecode", ecode);
                } else {
                    code = PhoneCode.getPhoneCode();
                    ecode = "1000";
                    vercode.setMember_name(tel);
                    vercode.setMobile_phone(tel);
                    vercode.setVer_code(code);
                    vercode.setPost_time(DateUtil.getDate());
                    vercode.setDelete_flag("0");
                    String ret = SMSManager.sendSms(code, tel);
                    Map<String, Object> retInfo = gson.fromJson(ret, new TypeToken<Map<String, Object>>() {
                    }.getType());
                    logger.info(retInfo.get("code"));
                    // 判断短信是否未发送成功
                    if ((Double)retInfo.get("code")!=0.0) {
                        // 判断1小时内同一手机号发送次数是否超过3次
                        if ((Double)retInfo.get("code")==22.0){
                            ecode = "2002";     // 验证码类短信1小时内同一手机号发送次数不能超过3次
                            result.put("ecode", ecode);
                        } else {
                            ecode = "2001";
                            result.put("ecode", "2001"); // 短信平台问题
                        }
                    } else {
                        this.vercodeDaoMapper.saveVercode(vercode);
                        result.put("code", code);
                        result.put("ecode", ecode);
                    }
                }
            }
            // 找回密码
            else {
                if (member != null) {
                    code = PhoneCode.getPhoneCode();
                    ecode = "1000";
                    vercode.setMember_name(tel);
                    vercode.setMobile_phone(tel);
                    vercode.setVer_code(code);
                    vercode.setPost_time(DateUtil.getDate());
                    vercode.setDelete_flag("0");
                    String ret = SMSManager.sendSms(code, tel);
                    Map<String, Object> retInfo = gson.fromJson(ret, new TypeToken<Map<String, Object>>() {
                    }.getType());
                    logger.info("ret="+retInfo.get("code"));
                    // 判断短信是否未发送成功
                    if ((Double)retInfo.get("code")!=0.0) {
                        // 判断1小时内同一手机号发送次数是否超过3次
                        if ((Double)retInfo.get("code")==22.0){
                            ecode = "2002";     // 验证码类短信1小时内同一手机号发送次数不能超过3次
                            result.put("ecode", ecode);
                        } else {
                            ecode = "2001";
                            result.put("ecode", "2001"); // 短信平台问题
                        }
                    } else {
                        this.vercodeDaoMapper.saveVercode(vercode);
                        result.put("code", code);
                        result.put("ecode", ecode);
                    }
                } else {
                    ecode = "3004";  // 该用户尚未注册
                    result.put("ecode", ecode);
                }
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            e.printStackTrace();
        }
        logger.info("result="+JSONUtil.toJSONString(result));
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：phoneCode
     * 内容摘要：获取手机验证码(公司)
     *
     * @param phoneNumber  手机号码
     * @param company_name 公司名称
     * @return String 返回值json
     */
    public String phoneCode2(String phoneNumber, String company_name) {
        logger.info("phoneNumber：" + phoneNumber);
        logger.info("company_name：" + company_name);
        String ecode = null;
        Map result = new HashMap();
        T_Data_Company company = null;
        try {
            code = PhoneCode.getPhoneCode();
            ecode = "1000";
            result.put("code", code);
            result.put("ecode", ecode);
            logger.info("code：" + code);
            company = this.companyDaoMapper.findCompanyByName(company_name, "0");
            contact_mobile_phone = company.getContact_mobile_phone();
            logger.info("contact_mobile_phone：" + contact_mobile_phone);
            SMSManager.tplSendSms(phoneNumber, code, contact_mobile_phone);
        } catch (IOException e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        logger.info("contact_mobile_phone：" + contact_mobile_phone);
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：vertifyPhoneCode
     * 内容摘要：验证手机验证码
     *
     * @param mobile_phone 手机验证码
     * @param phoneCode    手机号
     * @return String 返回值json
     */
    public String vertifyPhoneCode(String phoneCode, String mobile_phone) {
        String ecode = null;
        String code = null;
        Map result = new HashMap();
        try {
            Long currentTime = DateUtil.getDate().getTime() / 1000; //获取当前验证时间
            T_Data_Vercode vercode = vercodeDaoMapper.findVercode(mobile_phone);
            Long postTime = vercode.getPost_time().getTime() / 1000; //获取发送手机验证码的时间
            code = vercode.getVer_code();
            if (currentTime - postTime > 60) {
                ecode = "3004"; //验证码超时
                result.put("ecode", ecode);
                vercodeDaoMapper.updateVercode(mobile_phone);
            } else if (phoneCode.equals(code)) {
                ecode = "1000"; // 验证码正确
                result.put("ecode", ecode);
                vercodeDaoMapper.updateVercode(mobile_phone);
            } else {
                ecode = "3003"; // 验证码错误
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(e.getMessage());
            result.put("ecode", ecode);
        }
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：addSubMember
     * 内容摘要：下属人员的添加（确定用户所属关系编码id）
     *
     * @param member_name    会员名称
     * @param member_subName 下属会员名称
     * @return String 返回值json
     */
    public String addSubMember(String member_name, String member_subName) {
        String ecode = null;
        T_Data_Member member = null;
        T_Data_Member member2 = null;
        String relevance_info_id = null;
        String account_type = null;
        String member_type = null;
        Map result = new HashMap();
        logger.info("主账号：" + member_name);
        logger.info("下属账号：" + member_subName);
        try {
            member = this.memberDaoMapper.findMemberByName(member_name); // 主账户
            member2 = this.memberDaoMapper.findMemberByName(member_subName); // 下属人员（司机或车队长都关联个人信息表）
            account_type = member.getAccount_type(); // 主账户是个人（0）或者企业（1）
            member_type = member2.getMember_type(); // 下属账户（个人司机不能添加下属）
            if (member2.getMember_belong_code().equals(member2.getRelevance_info_id())) {
                if (account_type == "0") {
                    // 管车版个人添加管车版个人账号
                    List<T_Data_Member> memberList = this.memberDaoMapper.findMemberByMember_belong_code(member2.getMember_belong_code() + "/");
                    for (T_Data_Member member1 : memberList) {
                        member1.setMember_belong_code(member.getMember_belong_code() + "/" + member1.getMember_belong_code());
                        this.memberDaoMapper.updateMember(member1);
                    }
                    member2.setMember_belong_code(member.getMember_belong_code() + "/" + member2.getRelevance_info_id());
                    this.memberDaoMapper.updateMember(member2);
                } else {
                    // 管车版公司添加司机或者车队长
                    // 所属关系编码id，A公司，B车队长，C司机，长id：A/B,A/B/C,A/B/C/C1等
                    // 管车版公司添加车队长时，要更新车队长下的司机的所属关系id（针对车队长先添加下属司机，然后公司添加车队长）
                    if (member_type == "0") {
                        // 添加车队长
                        List<T_Data_Member> memberList = this.memberDaoMapper.findMemberByMember_belong_code(member2.getMember_belong_code() + "/");
                        for (T_Data_Member member1 : memberList) {
                            member1.setMember_belong_code(member.getMember_belong_code() + "/" + member1.getMember_belong_code());
                            this.memberDaoMapper.updateMember(member1);
                        }
                    }
                    member2.setMember_belong_code(member.getMember_belong_code() + "/" + member2.getRelevance_info_id());
                    this.memberDaoMapper.updateMember(member2);
                }
                ecode = "1000"; // 下属添加成功
                result.put("ecode", ecode);
                // 使用存储过程
                this.memberDaoMapper.storeMemberByID(member.getMember_id());
                this.memberDaoMapper.storeMemberByID(member2.getMember_id());
            } else {
                ecode = "3002"; // 扫描账号存在所属关系
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000"; // 下属添加失败
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        logger.info("ecode=" + ecode);
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：delSubMember
     * 内容摘要：
     * 下属人员的删除
     * 如果要删除的司机不属于管理者名下的车辆的常跑司机，则可直接删除；
     * 如果属于，则需先将名下相关车辆的常跑司机变更后才可删除。
     * 删除人员为车队长时，需同时删除车队长及下属司机的所属物流公司内容；
     * 并且删除车队长时，需查看车队长管理车辆的托管对象是否是物流公司，如果是则不允许删除。
     *
     * @param member_name    车辆管理者账号
     * @param member_subName 管理者下属人员的账号
     * @return String 返回值json
     */
    public String delSubMember(String member_name, String member_subName) {
        // 管车版公司或者管车版个人删除下属
        String json = null;
        String ecode = null;
        String noDelInfo = null;  // 不能删除该司机的原因
        Map result = new HashMap();
        T_Data_Member member = null; // 主账户
        T_Data_Company company = null; // 所属公司
        String account_type = null; // 账户类型：0个人/1企业
        String relevance_info_id = null; // 主账号的关联信息ID
        String member_belong_code = null; // 下属账户的用户所属关系编码
        String relevance_info_id2 = null; // 下属账户的关联信息ID
        T_Data_Member subMember = null; // 下属账户
        List<T_Data_Truck> truck_list = null;
        logger.info("member_name：" + member_name);
        logger.info("member_subName：" + member_subName);
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            subMember = this.memberDaoMapper.findMemberByName(member_subName);
            account_type = member.getAccount_type(); // 主账户
            relevance_info_id = member.getRelevance_info_id(); // 主账户
            relevance_info_id2 = subMember.getRelevance_info_id(); // 下属账户的用户所属关系编码
            member_belong_code = subMember.getMember_belong_code(); // 下属账户的所属关系编码
            if (account_type == "1") {
                // 企业
                company = this.companyDaoMapper.findCompanyByID(relevance_info_id);
                // 车主会员id
                T_Data_Member member2 = this.memberDaoMapper.findMemberByName(company.getContact_mobile_phone());// 企业会员账号
                truck_list = this.truckDaoMapper.findTruckByOwnerMemberID(member2.getMember_id());
                // A管理者账号，A/b1,A/b2，A/b3(三个下属)，车c1,c2,对应的司机是b1,b2,现要删除b3(情况一)
                // A管理者账号，A/b1,A/b2，A/b3(三个下属)，车c1,c2,对应的司机是b1,b2,现要删除b2,需要更改c2的司机为b3（情况二）
                // A管理者账号, A/b1,A/b2,A/b3(三个下属),三个人也有下属，分别为A/b1/b11,A/b2/b22,A/b3/b33(三个下属),车c1,c2,c3等，要删除A/b1，A/b1/b11所属物流公司内容（情况三）
                // 若车队长管理车辆的托管对象是否是物流公司，如果是则不允许删除。（情况四）
                // 如何确定member_subName为车队长？
                logger.info(member_belong_code + "/");
                List<T_Data_Member> member_list = this.memberDaoMapper.findMemberByMember_belong_code(member_belong_code + "/");
                if (member_list == null || member_list.size() == 0) {
                    // 前两种情况
                    T_Data_Person person = null;
                    String truck_id = null;
                    person = this.personDaoMapper.findPersonByID(relevance_info_id2);
                    truck_id = person.getDriving_truck_id(); // 下属人员对应的车id
                    for (T_Data_Truck truck : truck_list) {
                        // 情况二
                        if (truck.getTruck_id().equals(truck_id)) {
                            ecode = "2003"; // 需先将名下相关车辆的常跑司机变更后才可删除
                            result.put("ecode", ecode);
                        }
                        // 情况一
                        else {
                            subMember.setMember_belong_code(subMember.getRelevance_info_id());
                            this.memberDaoMapper.updateMember(subMember);
                            ecode = "1000";
                            result.put("ecode", ecode);
                        }
                    }
                } else {
                    List<T_Data_Truck> truckList = null;
                    truckList = this.truckDaoMapper.findTruckByManagerMemberID(subMember.getMember_id());
                    company = this.companyDaoMapper.findCompanyByID(relevance_info_id);
                    T_Data_Member member1 = this.findMemberByName(company.getContact_mobile_phone());
                    for (T_Data_Truck truck : truckList) {
                        if (truck.getDeposit_member_id().equals(member1.getMember_id())) {
                            ecode = "2004"; // 车队长管理车辆的托管对象是物流公司,不能删除车队长！
                            result.put("ecode", ecode);
                        } else {
                            List<T_Data_Member> memberList = this.memberDaoMapper.findMemberByMember_belong_code(subMember.getMember_belong_code() + "/");
                            for (T_Data_Member member3 : memberList) {
                                member3.setMember_belong_code(member3.getRelevance_info_id());
                                this.memberDaoMapper.updateMember(member3);
                            }
                            subMember.setMember_belong_code(subMember.getRelevance_info_id());
                            this.memberDaoMapper.updateMember(subMember);
                            ecode = "1000";
                            result.put("ecode", ecode);
                        }

                    }
                }
            }
            // 个人
            else {
                truck_list = this.truckDaoMapper.findTruckByOwnerMemberID(member.getMember_id());
                T_Data_Person person = null;
                String truck_id = null;
                person = this.personDaoMapper.findPersonByID(relevance_info_id2);
                truck_id = person.getDriving_truck_id(); //下属人员对应的车id
                for (T_Data_Truck truck : truck_list) {
                    // 情况二
                    if (truck.getTruck_id().equals(truck_id) == true) {
                        ecode = "2003"; // 需先将名下相关车辆的常跑司机变更后才可删除
                        result.put("ecode", ecode);
                    }
                    // 情况一
                    else {
                        subMember.setMember_belong_code(subMember.getRelevance_info_id());
                        this.memberDaoMapper.updateMember(subMember);
                        ecode = "1000";
                        result.put("ecode", ecode);
                    }
                }

            }
            json = JSONUtil.toJSONString(result);

        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        logger.info("ecode=" + ecode);
        // 使用存储过程
        this.memberDaoMapper.storeMemberByID(member.getMember_id());
        this.memberDaoMapper.storeMemberByID(subMember.getMember_id());
        return json;
    }

    /**
     * 方法名称：findAllSubMember
     * 内容摘要：根据账号检索下属人员列表
     *
     * @param member_name                 会员名称
     * @param member_identity             会员身份
     * @param business_supervise_function 业务管理职能
     * @param begin_rows                  开始页
     * @param end_rows                    结束页
     * @return String 返回值json
     */
    public String findAllSubMember(String member_name, int member_identity, int business_supervise_function, int begin_rows, int end_rows) {
        logger.info("member_name：" + member_name);
        logger.info("member_identity：" + member_identity);
        logger.info("business_supervise_function:" + business_supervise_function);
        logger.info("begin_rows：" + begin_rows);
        logger.info("end_rows：" + end_rows);
        String json = null;
        String ecode = null;
        T_Data_Member member = null; // 主账号信息
        T_Data_Person person = null; // 下属人员个人信息
        T_Data_Person person2 = null; // 车队信息
        String member_type = null; // 用户类型
        String account_type = null; // 账户类型
        String relevance_info_id = null; // 账号的关联id
        String member_belong_code = null; // 下属人员的用户所属关系编码
        List<T_Data_Member> allList = new ArrayList<T_Data_Member>(); // 下属人员列
        List<T_Data_Member> subList = new ArrayList<T_Data_Member>(); // 车队长下级所有车队长
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
        int total = 0; // 获取下属人员总数
        Map result = new HashMap(); // 保存json数据
        try {
            // 获取主账号的信息
            member = this.memberDaoMapper.findMemberByName(member_name);
            member_belong_code = member.getMember_belong_code();
            // 根据会员身份确定用户类型
            member_type = member_identity + "";
            allList = this.memberDaoMapper.findAllSubMemberInfo(member_type, member_belong_code + "/");
            subList = this.memberDaoMapper.findAllSubMemberInfo(member_type, member_belong_code + "/%/");
            if (allList == null || allList.size() == 0) {
                ecode = "3005"; // 列表为空
                result.put("ecode", ecode);
            } else {
                if (subList.size() != 0 && subList != null) {
                    for (int j = 0; j < subList.size(); j++) {
                        for (int i = 0; i < allList.size(); i++) {
                            boolean b = subList.get(j).getMember_belong_code().equals(allList.get(i).getMember_belong_code());// 去重
                            if (b) {
                                allList.remove(i);
                                i--;
                            }
                        }
                    }
                    for (T_Data_Member member2 : allList) {
                        Map<String, String> results = new HashMap<String, String>();
                        results.put("member_name", member2.getMember_name());
                        results.put("member_identity", member2.getMember_type() + "");
                        results.put("rate", "1星");
                        person = this.personDaoMapper.findPersonByID(member2.getRelevance_info_id());
                        if (person != null) {
                            results.put("person_name", person.getPerson_name());
                        }
                        member_belong_code = member2.getMember_belong_code();
                        String[] longID = member_belong_code.split("/");
                        person2 = this.personDaoMapper.findPersonByID(longID[longID.length - 2]);
                        if (person2 != null) {
                            results.put("person_supername", person2.getPerson_name());
                        } else {
                            results.put("person_supername", "none"); // 没有上级单位
                        }
                        lists.add(results);
                    }
                    ecode = "1000"; // 响应成功
                    result.put("ecode", ecode);
                    result.put("list", lists);
                } else {
                    for (T_Data_Member member2 : allList) {
                        Map<String, String> results = new HashMap<String, String>();
                        results.put("member_name", member2.getMember_name());
                        results.put("member_identity", member2.getMember_type() + "");
                        results.put("rate", "1星");
                        person = this.personDaoMapper.findPersonByID(member2.getRelevance_info_id());
                        if (person != null) {
                            results.put("person_name", person.getPerson_name());
                        }
                        member_belong_code = member2.getMember_belong_code();
                        String[] longID = member_belong_code.split("/");
                        person2 = this.personDaoMapper.findPersonByID(longID[longID.length - 2]);
                        if (person2 != null) {
                            results.put("person_supername", person2.getPerson_name());
                        } else {
                            results.put("person_supername", "none"); // 没有上级单位
                        }
                        lists.add(results);
                    }
                    ecode = "1000"; // 响应成功
                    result.put("ecode", ecode);
                    result.put("list", lists);
                }
            }

        } catch (Exception e) {
            ecode = "2000"; // 响应失败
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        logger.info("ecode=" + ecode);
        return json;

    }

    /**
     * 方法名称：findCarCaptain
     * 内容摘要：根据账号获取车队长列表
     *
     * @param member_name 会员名称
     * @return String 返回值json
     */
    public String findCarCaptain(String member_name) {
        logger.info("member_name：" + member_name);
        String json = null;
        String ecode = null;
        String person_power = null;
        int person_power_sum = 0; // 待单状态车辆运力之和
        T_Data_Member member = null; // 管理者账号信息
        String member_belong_code = null; // 下属人员的用户所属关系编码
        T_Data_Person person = null; // 车队长个人信息
        List<T_Data_Truck> truckList = null; // 车队长下的车辆
        List<T_Data_Member> allList = new ArrayList<T_Data_Member>(); // 所有车队长列
        List<T_Data_Member> subList = new ArrayList<T_Data_Member>(); // 车队长下级所有车队长
        List<T_Data_Member> sublist = new ArrayList<T_Data_Member>();
        Map result = new HashMap(); // 保存json数据
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            member_belong_code = member.getMember_belong_code();
            // 获取车队长列表
            allList = this.memberDaoMapper.findAllSubMemberInfo("0", member_belong_code + "/");
            subList = this.memberDaoMapper.findAllSubMemberInfo("0", member_belong_code + "/%/");
            if (allList == null || allList.size() == 0) {
                // 车队长为空
                ecode = "3005";
                result.put("ecode", ecode);
            } else {
                // 判断车队长的下属车队长是否存在
                if (subList.size() != 0 && subList != null) {
                    for (int j = 0; j < subList.size(); j++) {
                        for (int i = 0; i < allList.size(); i++) {
                            boolean b = subList.get(j).getMember_belong_code().equals(allList.get(i).getMember_belong_code());//去重
                            if (b) {
                                allList.remove(i);
                                i--;
                            }
                        }
                    }
                    for (T_Data_Member member2 : allList) {
                        Map<String, String> results = new HashMap<String, String>();
                        results.put("member_name", member2.getMember_name());
                        person = this.personDaoMapper.findPersonByID(member2.getRelevance_info_id());
                        results.put("person_name", person.getPerson_name());
                        results.put("mobile_phone", person.getPerson_mobile_phone());
                        person_power_sum += this.truckService.findLoadWeightByMember(member2.getMember_name());
                        sublist = this.memberDaoMapper.findAllSubMemberInfo("0", member2.getMember_belong_code() + "/");
                        for (T_Data_Member member3 : sublist) {
                            person_power_sum += this.truckService.findLoadWeightByMember(member3.getMember_name());
                        }
                        person_power = person_power_sum + "";
                        results.put("person_power", person_power);
                        lists.add(results);
                        person_power_sum = 0; // 归零
                    }
                    ecode = "1000"; // 响应成功
                    result.put("ecode", ecode);
                    result.put("list", lists);
                } else {
                    for (T_Data_Member member2 : allList) {
                        Map<String, String> results = new HashMap<String, String>();
                        results.put("member_name", member2.getMember_name());
                        person = this.personDaoMapper.findPersonByID(member2.getRelevance_info_id());
                        results.put("person_name", person.getPerson_name());
                        results.put("mobile_phone", person.getPerson_mobile_phone());
                        person_power_sum += this.truckService.findLoadWeightByMember(member2.getMember_name());
                        for (T_Data_Member member3 : subList) {
                            person_power_sum += this.truckService.findLoadWeightByMember(member3.getMember_name());
                        }
                        person_power = person_power_sum + "";
                        results.put("person_power", person_power);
                        lists.add(results);

                    }
                    ecode = "1000"; // 响应成功
                    result.put("ecode", ecode);
                    result.put("list", lists);
                }
            }
        } catch (Exception e) {
            result.put("ecode", "2000");
            logger.error(e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findSubBuser
     * 内容摘要：根据账号检索下属司机(无绑定车辆)列表
     *
     * @param member_name 会员名称
     * @return String 返回值json
     */
    public String findSubBuser(String member_name) {
        String ecode = null;
        String json = null;
        T_Data_Member member = null; // 账号信息
        List<T_Data_Member> allList = new ArrayList<T_Data_Member>();
        List<T_Data_Member> subList = new ArrayList<T_Data_Member>();
        T_Data_Person person = null;
        Map result = new HashMap(); // 保存json数据
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            allList = this.memberDaoMapper.findAllSubMemberInfo("1", member.getMember_belong_code() + "/");
            subList = this.memberDaoMapper.findAllSubMemberInfo("1", member.getMember_belong_code() + "/%/");
            if (allList == null || allList.size() == 0) {
                ecode = "3005";
                result.put("ecode", ecode);
            } else if (subList != null && subList.size() != 0) {
                for (int j = 0; j < subList.size(); j++) {
                    for (int i = 0; i < allList.size(); i++) {
                        boolean b = subList.get(j).getMember_belong_code().equals(allList.get(i).getMember_belong_code());// 去重
                        if (b) {
                            allList.remove(i);
                            i--;
                        }
                    }
                }
                int k = 0;
                for (T_Data_Member member2 : allList) {
                    Map<String, String> results = new HashMap<String, String>();
                    person = this.personDaoMapper.findPersonByID(member2.getRelevance_info_id());
                    if (person.getDriving_truck_id() == null || person.getDriving_truck_id().equals("")) {
                        results.put("member_name", member2.getMember_name());
                        results.put("person_name", person.getPerson_name());
                        results.put("rate", "1星级");
                        k++;
                        lists.add(results);
                    }

                }
                // 判断是否有未绑定车辆的下属司机
                if (k == 0) {
                    ecode = "3005";
                    result.put("ecode", ecode);
                } else {
                    ecode = "1000";
                    result.put("ecode", ecode);
                    result.put("list", lists);
                }
            } else {
                int k = 0;
                for (T_Data_Member member2 : allList) {
                    Map<String, String> results = new HashMap<String, String>();
                    person = this.personDaoMapper.findPersonByID(member2.getRelevance_info_id());
                    if (person.getDriving_truck_id() == null || person.getDriving_truck_id().equals("")) {
                        results.put("member_name", member2.getMember_name());
                        results.put("person_name", person.getPerson_name());
                        results.put("rate", "1星级");
                        k++;
                        lists.add(results);
                    }

                }
                // 判断是否有未绑定车辆的下属司机
                if (k == 0) {
                    ecode = "3005";
                    result.put("ecode", ecode);
                } else {
                    ecode = "1000";
                    result.put("ecode", ecode);
                    result.put("list", lists);
                }
            }

        } catch (Exception e) {
            result.put("ecode", "2000");
            logger.error(e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    // 重置密码
    public boolean updatePassword(String member_name, String member_password) {
        boolean b = new Boolean(true);
        try {
            this.memberDaoMapper.updatePassword(member_name, member_password);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：findMemberIdentity
     * 内容摘要：查找会员身份，管理者或行驶司机
     *
     * @param member_name 会员名称
     * @return String 返回值json
     */
    public String findMemberIdentity(String member_name) {
        String json = null;
        String ecode = null;
        String memberIdentity = null;
        String driving_truck_id = null;
        String driving_status = null;
        T_Data_Member member = null;
        T_Data_Person person = null;
        T_Data_Truck truck = null;
        List<T_Data_Truck> truckList = null;
        Map result = new HashMap();

        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            person = this.personDaoMapper.findPersonByID(member.getRelevance_info_id());
            // 用户刚注册，没有个人信息
            if (person == null) {
                memberIdentity = "3"; // 没有车辆
            } else {
                // 判断会员是否为车辆管理者
                truckList = this.truckDaoMapper.findTruckByManagerMemberID(person.getPerson_id());
                driving_truck_id = person.getDriving_truck_id();
                driving_status = person.getDriving_status();
                if (truckList != null && truckList.size() != 0) {
                    if ("2".equals(driving_status)) {
                        memberIdentity = "2"; // 管理者&行驶司机
                    } else {
                        memberIdentity = "0"; // 管理者
                    }
                } else if (driving_truck_id != null) {
                    if ("0".equals(driving_status)) {
                        memberIdentity = "4"; // 待确认司机
                    } else if ("1".equals(driving_status)) {
                        memberIdentity = "5"; // 待机司机
                    } else if ("2".equals(driving_status)) {
                        memberIdentity = "1"; // 行驶司机
                    } else {
                        memberIdentity = "3"; // 没有车辆
                    }
                } else {
                    memberIdentity = "3"; // 没有车辆
                }
            }
            ecode = "1000";
            result.put("memberIdentity", memberIdentity);
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        logger.info("json=" + json);
        return json;
    }

    /**
     * 方法名称：findMemberIdentityByScheduleSheet
     * 内容摘要：根据派车单查找会员身份，管理者或行驶司机
     *
     * @param member_name 会员名称
     * @return String 返回值json
     */
    public String findMemberIdentityByScheduleSheet(String member_name, String plate_number, String schedule_sheet_id) {
        String json = null;
        String ecode = null;
        String memberIdentity = null;
        T_Data_Member member = null;
        T_Data_Person person = null;
        T_Data_Truck truck = null;
        Map result = new HashMap();

        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            person = this.personDaoMapper.findPersonByID(member.getRelevance_info_id());
            List<T_Data_Transportation_Dispatch_Sheet> dispatchSheets = dispatchSheetDaoMapper.findByScheduleId(schedule_sheet_id);
            // 用户刚注册，没有个人信息
            if (person == null) {
                memberIdentity = "3"; // 没有车辆
                ecode = "1000";
                result.put("memberIdentity", memberIdentity);
                result.put("ecode", ecode);
            } else {
                if (dispatchSheets.size() != 0) {
                    // 判断会员是否为车辆管理者
                    truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
                    if (truck.getManager_member_id().equals(member.getRelevance_info_id())) {
                        memberIdentity = "0"; // 管理者

                        //获取司机
                        for (T_Data_Transportation_Dispatch_Sheet dispatchSheet : dispatchSheets) {
                            if (dispatchSheet.getReceiveMemberId().equals(person.getPerson_id())) {
                                memberIdentity = "2"; // 管理者&行驶司机
                                break;
                            }
                        }
                        ecode = "1000";
                        result.put("memberIdentity", memberIdentity);
                        result.put("ecode", ecode);
                    } else {
                        //获取司机
                        for (T_Data_Transportation_Dispatch_Sheet dispatchSheet : dispatchSheets) {
                            if (dispatchSheet.getReceiveMemberId().equals(person.getPerson_id())) {
                                memberIdentity = "1"; // 行驶司机
                                break;
                            }
                        }
                        ecode = "1000";
                        result.put("memberIdentity", memberIdentity);
                        result.put("ecode", ecode);
                    }
                } else {
                    ecode = "3000"; // 查找不到调度单
                    result.put("ecode", ecode);
                }
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        logger.info("json=" + json);
        return json;
    }


    /**
     * 方法名称：queryUserRoleList
     * 内容摘要：查询用户角色管理列表信息
     *
     * @return List<T_Data_User_Role_Manager>
     */
    public List<T_Data_User_Role_Manager> queryUserRoleList(HashMap<String, String> conditions) {
        List<T_Data_User_Role_Manager> userRoleManagerList = null;
        try {
            userRoleManagerList = this.memberDaoMapper.queryUserRoleList(conditions);

        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return userRoleManagerList;

    }

    /**
     * 方法名称：updateHeadPic
     * 内容摘要：上传会员图像
     *
     * @return boolean
     */
    public boolean updateHeadPic(T_Data_Member member) {
        boolean b = new Boolean(true);
        try {
            this.memberDaoMapper.updateHeadPic(member);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：findMemberAccount
     * 内容摘要： 个人账户检索（支付）
     *
     * @return String
     */
    @Override
    public String findMemberAccount(String member_name) {
        logger.info("member_name=" + member_name);
        String json = null;
        String ecode = null;
        String member_account_amount = null;
        String password_status = null;
        T_Data_Member member = null;
        Map result = new HashMap();
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            ecode = "1000";
            member_account_amount = member.getMember_account_amount() + "";
            // 判断支付密码是否为空
            if (member.getPayment_password() == null || member.getPayment_password().equals("")) {
                password_status = "0"; // 没有密码
            } else {
                password_status = "1"; // 有密码
            }
            result.put("ecode", ecode);
            result.put("member_account_amount", member_account_amount);
            result.put("password_status", password_status);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
            ecode = "2000";
            result.put("ecode", ecode);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：setPaymentPassword
     * 内容摘要：账户支付密码设置（支付）
     *
     * @return String
     */
    @Override
    public String setPaymentPassword(String account_name, String payment_password, String account_type) {
        String json = null;
        String ecode = null;
        T_Data_Member member = null;
        T_Data_Truck truck = null;
        Map result = new HashMap();
        try {
            // 判断是否是个人账户
            if (account_type.equals("0")) {
                member = this.memberDaoMapper.findMemberByName(account_name);
                member.setPayment_password(MD5Util.md5(payment_password));
                member.setLast_update(DateUtil.getDate());
                member.setLast_update_user_id("M:" + account_name);
                this.memberDaoMapper.updateMember(member);
                logger.info(MessageUtil.saveInfo);
                ecode = "1000"; // 设置密码成功
            } else {
                truck = this.truckDaoMapper.findTruckByPlateNumber(account_name);
                truck.setPayment_password(MD5Util.md5(payment_password));
                truck.setLast_update(DateUtil.getDate());
                truck.setLast_update_user_id("M:" + account_name);
                this.truckDaoMapper.updateTruck(truck);
                logger.info(MessageUtil.saveInfo);
                ecode = "1000"; // 设置密码成功
            }
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.saveInfoError);
            ecode = "2000"; // 设置密码失败
        }
        result.put("ecode", ecode);
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：editPaymentPassword
     * 内容摘要：账户支付密码修改（支付）
     *
     * @return String
     */
    @Override
    public String editPaymentPassword(String account_name, String old_payment_password,
                                      String new_payment_password, String account_type) {
        String json = null;
        String ecode = null;
        T_Data_Member member = null;
        T_Data_Truck truck = null;
        Map result = new HashMap();
        try {
            // 判断是否是个人账户
            if (account_type.equals("0")) {
                member = this.memberDaoMapper.findMemberByName(account_name);
                // 判断个人账户输入的旧支付密码是否正确
                if (member.getPayment_password().equals(MD5Util.md5(old_payment_password))) {
                    member.setPayment_password(MD5Util.md5(new_payment_password));
                    member.setLast_update(DateUtil.getDate());
                    member.setLast_update_user_id("M:" + account_name);
                    this.memberDaoMapper.updateMember(member);
                    logger.info(MessageUtil.saveInfo);
                    ecode = "1000"; // 修改个人账户支付密码成功
                } else {
                    ecode = "3001"; // 旧密码输入错误
                }
            } else {
                truck = this.truckDaoMapper.findTruckByPlateNumber(account_name);
                // 判断个人账户输入的旧支付密码是否正确
                if (truck.getPayment_password().equals(MD5Util.md5(old_payment_password))) {
                    truck.setPayment_password(MD5Util.md5(new_payment_password));
                    truck.setLast_update(DateUtil.getDate());
                    truck.setLast_update_user_id("M:" + account_name);
                    this.truckDaoMapper.updateTruck(truck);
                    logger.info(MessageUtil.saveInfo);
                    ecode = "1000"; // 修改车辆账户支付密码成功
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.saveInfoError);
            ecode = "2000"; // 修改密码失败
        }
        result.put("ecode", ecode);
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：checkMemberAccount
     * 内容摘要：检测个人账户金额是否有充足金额满足提现（支付）
     *
     * @return String
     */
    @Override
    public boolean checkMemberAccount(String member_name, String total_fee) {
        boolean b = new Boolean(true);


        return false;
    }

    /**
     * 方法名称：checkPaymentPassword
     * 内容摘要：进行密码是否正确的判断，实现记录密码连续输入错误次数（支付）
     *
     * @return String
     */
    @Transactional
    public String checkPaymentPassword(String member_name, String payment_password) {
        logger.info("member_name=" + member_name);
        logger.info("payment_password=" + payment_password);
        String json = null;
        String ecode = null;
        T_Data_Member member = null;
        T_Data_Truck truck = null;
        T_Data_Sys_Info sysInfo = null;
        T_Data_Truck_Payment_Error_Password_Commit_History tDataTruckPaymentErrorPasswordCommitHistory = null;
        String errorPaymentPasswordMaxTime = null; // 系统表支付密码每天可接受错误次数
        String todayErrorCount = null;             // 支付密码错误次数
        Map result = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        payment_password = MD5Util.md5(payment_password);
        Date date_server = DateUtil.getDate();
        try {
            sysInfo = this.sysInfoDaoMapper.findSysInfo();
            errorPaymentPasswordMaxTime = sysInfo.getError_payment_password_max_time();
            member = this.memberDaoMapper.findMemberByName(member_name);
            String paymentPassword = member.getPayment_password();         // 调用接口时数据库密码不为空
            Date lastPayingDate = member.getLast_paying_date();            // 获取最新进行支付日期
            boolean b = DateUtil.compareTime(lastPayingDate, date_server);
            // 判断最新支付日期和服务器当前日期是否不同
            if (!b) {
                // 最新支付日期设置为当前服务器的时间，同天支付密码错误次数设置为0
                member.setCurrent_day_error_payment_password_commit_count("0");
                member.setLast_paying_date(date_server);
                member.setLast_update(date_server);
                member.setLast_update_user_id("M:" + member_name);
                this.memberDaoMapper.updateMember(member);
            }
            // 比较会员信息表和系统信息表中的支付密码错误次数是否不同或同时等于0
            todayErrorCount = member.getCurrent_day_error_payment_password_commit_count();
            if (!todayErrorCount.equals(errorPaymentPasswordMaxTime) ||
                    (todayErrorCount.equals("0") && errorPaymentPasswordMaxTime.equals("0"))) {
                // 支付密码是否输入正确
                if (paymentPassword.equals(payment_password)) {
                    // 支付密码输入正确，更新次数为0
                    member.setCurrent_day_error_payment_password_commit_count("0");
                    member.setLast_update_user_id("M:" + member_name);
                    member.setLast_update(date_server);
                    member.setLast_paying_date(date_server);
                    this.memberDaoMapper.updateMember(member);
                    ecode = "1000";
                    result.put("ecode", ecode);
                } else {
                    // 支付密码输入错误，次数加1
                    todayErrorCount = String.valueOf(Integer.parseInt(todayErrorCount) + 1);
                    member.setCurrent_day_error_payment_password_commit_count(todayErrorCount);
                    member.setLast_update(date_server);
                    member.setLast_update_user_id("M:" + member_name);
                    this.memberDaoMapper.updateMember(member);
                    ecode = "1004";
                    result.put("ecode", ecode);
                    result.put("todayErrorCount", todayErrorCount);
                }
            } else {
                // 会员信息表和系统信息表中的支付密码错误次数二者相同且不等于0
                ecode = "1005"; // 密码次数超过n次
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000"; // 系统错误
            result.put("ecode", ecode);
            logger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
        } finally {
            json = JSONUtil.toJSONString(result);
            logger.info("json=" + json);
            return json;
        }
    }

    /**
     * 方法名称：findMemberAccountDetail
     * 内容摘要：会员账户明细检索_2.0
     *
     * @param member_name 会员名
     * @param date        日期
     * @return String 返回值jsons
     */
    public String findMemberAccountDetail(String member_name, String date) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;    // 获取车辆
        List<T_Data_Member_Payment_History> memberPaymentHistoryList = null;
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            if (member != null) {
                String member_id = member.getMember_id();
                memberPaymentHistoryList = memberPaymentHistoryDaoMapper.findMemberPaymentHistoryByIDAndDate(member_id, date);
                List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
                for (T_Data_Member_Payment_History memberPaymentHistory : memberPaymentHistoryList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("history_number", memberPaymentHistory.getHistory_number()); // 获取历史编号
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    String currentTime = df.format(memberPaymentHistory.getCreate_time());
                    results.put("create_time", currentTime); // 获取产生时间
                    String payment_type = memberPaymentHistory.getPayment_type();
                    if (payment_type.equals("0") || payment_type.equals("1")) {
                        if (payment_type.equals("0")) {
                            results.put("payment_type", "充值"); // 获取类别
                            results.put("amount", "+" + memberPaymentHistory.getAmount()); // 获取金额
                        } else if (payment_type.equals("1")) {
                            results.put("payment_type", "提现"); // 获取类别
                            results.put("amount", "-" + memberPaymentHistory.getAmount()); // 获取金额
                        }
                        String targetId = memberPaymentHistory.getTarget_id();  // 获取对象信息ID
                        String target_id = targetId.substring(1, targetId.length());    // 去除对象信息ID中首字符B
                        int str = Integer.parseInt(target_id);  // 转int去除对象信息ID中部位0
                        String account_no = String.valueOf(str);
                        T_Data_Member_Bank_Account bankAccount = memberBankAccountDaoMapper.findMemberBankAccountByAccountNo(account_no);   // 获取会员银行卡信息表
                        String bank_account = bankAccount.getBank_account();    // 获取银行卡号
                        int bankAccountLenth = bank_account.length();   // 获取银行卡号长度
                        int lastIndex = bankAccountLenth - 4;
                        String card_last = bank_account.substring(lastIndex, bankAccountLenth);         //卡号后四位
                        String card_top = bank_account.substring(0, 6);                                 //卡号前六位
                        results.put("target", card_top + "****" + card_last); // 获取对象信息ID
                    } else if (payment_type.equals("2") || payment_type.equals("3")) {
                        if (payment_type.equals("2")) {
                            results.put("payment_type", "车辆资金分配"); // 获取类别
                            results.put("amount", "-" + memberPaymentHistory.getAmount()); // 获取金额
                        } else if (payment_type.equals("3")) {
                            results.put("payment_type", "车辆资金回收"); // 获取类别
                            results.put("amount", "+" + memberPaymentHistory.getAmount()); // 获取金额
                        }
                        String targetId = memberPaymentHistory.getTarget_id();
                        String truck_id = targetId.substring(1, targetId.length());
                        T_Data_Truck truck = truckDaoMapper.findTruckByID(truck_id);
                        if (truck != null) {
                            results.put("target", truck.getPlate_number()); // 获取目标
                        } else {
                            results.put("target", ""); // 获取目标
                        }
                    } else {
                        if (payment_type.equals("4")) {
                            results.put("payment_type", "运费收入"); // 获取类别
                            results.put("amount", "+" + memberPaymentHistory.getAmount()); // 获取金额
                        } else if (payment_type.equals("5")) {
                            results.put("payment_type", "运输成本扣除"); // 获取类别
                            results.put("amount", "-" + memberPaymentHistory.getAmount()); // 获取金额
                        } else {
                            results.put("payment_type", memberPaymentHistory.getPayment_type()); // 获取类别
                            results.put("amount", memberPaymentHistory.getAmount() + ""); // 获取金额
                        }
                        results.put("target", ""); // 获取目标
                    }
                    String payment_result = memberPaymentHistory.getPayment_result();
                    if (payment_result.equals("0")) {
                        payment_result = "成功";
                    } else if (payment_result.equals("1")) {
                        payment_result = "失败";
                    } else if (payment_result.equals("2")) {
                        payment_result = "等待支付结果";
                    }
                    results.put("payment_result", payment_result); // 获取支付结果
                    lists.add(results);
                }
                ecode = "1000"; // 成功响应！
                result.put("ecode", ecode);
                result.put("result", lists);
            } else {
                ecode = "3000"; // 信息不存在！
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法描述：查询会员账户资金量（财务金融）
     */
    public double queryMemberAccountAmountSum() {
        double memberAccountAmountSum = 0.00;
        try {
            memberAccountAmountSum = this.memberDaoMapper.queryMemberAccountAmountSum();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return memberAccountAmountSum;
    }

    /**
     * 方法名称: queryAccountDetail
     * 方法描述：查询所有会员账户信息
     * @return List<T_Data_Member>
     */
    public List<T_Data_Member> queryAccountDetail(){
        List<T_Data_Member> memberList = null;
        try {
            memberList = this.memberDaoMapper.queryAccountDetail();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return memberList;
    }

    /**
     * 方法描述:交易记录查询
     * @param conditions
     * @return
     */
    @Override
    public List queryTransactionRecords(HashMap<String, String> conditions) {
        List<T_Data_Transaction_Info>  transactionRecordsList = null;
        try {
            transactionRecordsList = this.memberDaoMapper.queryTransactionRecords(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return transactionRecordsList;
    }


}
