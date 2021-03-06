/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: PersonServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：个人信息查询接口声明
 * 设计文件：
 * 完成日期：2015-02-18
 * 作    者：YK
 * 内容摘要：公司信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.rnc.RNCManager;
import com.cn.gazelle.logistics.service.PersonService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.MD5Util;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.PersonManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 类 名 称: PersonServiceImpl
 * 内容摘要: 个人信息查询接口
 * 方法描述：该类有9个方法：
 * 01 findPersonByID                 根据个人id查询个人信息
 * 02 findPersonByName               根据个人名查询个人信息
 * 03 findAllPerson                  查询所有个人信息
 * 04 findAllPersonRowsCount         查询个人总记录数
 * 05 savePerson                     个人信息的保存
 * 06 findPersonByMemberName         根据会员名称检索个人信息
 * 07 editPerson                     司机版个人信息的验证与修改
 * 08 updatePerson                   更新个人信息
 * 09 delPerson                      删除个人信息
 * 10 findOperationDriversManagerList查询运营司机管理列表
 *
 * @author YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.PersonService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class PersonServiceImpl implements PersonService {
    // Log初始化
    Logger logger = Logger.getLogger(PersonServiceImpl.class);
    @Resource
    private PersonDaoMapper personDaoMapper;                 // 数据访问层
    @Resource
    private MemberDaoMapper memberDaoMapper;                 // 数据访问层
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;               // 数据访问层
    @Resource
    private CompanyDaoMapper companyDaoMapper;               // 数据访问层
    @Resource
    private TruckDaoMapper truckDaoMapper;                   // 数据访问层
    @Resource
    private VehicleDaoMapper vehicleDaoMapper;               // 数据访问层


    /**
     * 方法名称：findPersonByID
     * 内容摘要：根据个人id查询企业信息。
     *
     * @param person_id 个人id
     * @return String 个人信息json
     */
    public T_Data_Person findPersonByID(String person_id) {
        T_Data_Person person = null;
        try {
            person = this.personDaoMapper.findPersonByID(person_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return person;
    }

    /**
     * 方法名称：findPersonByPhone
     * 内容摘要：根据个人手机号查询个人信息。
     *
     * @param person_mobile_phone 手机号码
     * @param person_type         身份类型
     * @return String 个人信息json
     */
    public T_Data_Person findPersonByPhone(String person_mobile_phone, String person_type) {
        T_Data_Person person = null;
        try {
            person = this.personDaoMapper.findPersonByPhone(person_mobile_phone, person_type);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return person;
    }

    /**
     * 方法名称：findPersonByName
     * 内容摘要：根据个人名查询个人信息
     *
     * @param person_name    个人名称
     * @param id_card_number 个人名称
     * @param person_type    个人名称
     * @return T_Data_Person 个人信息
     */
    public T_Data_Person findPersonByName(String person_name, String id_card_number, String person_type) {
        T_Data_Person person = null;
        try {
            person = this.personDaoMapper.findPersonByName(person_name, id_card_number, person_type);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return person;
    }

    /**
     * 方法名称：findAllPerson
     * 内容摘要：查询所有个人信息
     *
     * @param search_type 搜索类型
     * @param name        查询类型
     * @param page        页面页数
     * @param rows        页面显示条数
     * @return List<T_Data_Person>  个人信息list
     */
    public List<T_Data_Person> findAllPerson(String search_type, String name, Integer page, Integer rows, String person_type) {
        List<T_Data_Person> personList = null;
        try {
            personList = this.personDaoMapper.findAllPerson(search_type, name, page, rows, person_type);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return personList;
    }

    /**
     * 方法名称：findAllPersonRowsCount
     * 内容摘要：查询个人总记录数
     *
     * @param search_type 搜索类型
     * @param name        查询类型
     * @return Integer  个人信息记录数
     */
    public Integer findAllPersonRowsCount(String search_type, String name, String person_type) {
        int count = 0;
        try {
            count = this.personDaoMapper.findAllPersonRowsCount(search_type, name, person_type);
        } catch (Exception e) {
            logger.error(PersonManager_Message.getSelectPersonCountError + e.getMessage());
        }
        return count;

    }

    /**
     * 方法名称：savePerson
     * 内容摘要：个人信息的保存
     *
     * @param person 个人信息
     * @return Boolean
     */
    public boolean savePerson(T_Data_Person person) {
        boolean b = new Boolean(true);
        try {
            this.personDaoMapper.savePerson(person);
            b = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            b = false;
        }
        return b;
    }

    /**
     * 方法名称：findPersonByMemberName
     * 内容摘要：根据会员名称检索个人信息
     *
     * @param member_name 会员名称
     * @return String  返回值json
     */
    public String findPersonByMemberName(String member_name) {
        T_Data_Member member = null;
        T_Data_Person person = null;
        String person_id = null;
        String json = null;
        String ecode = null;
        Map result = new HashMap();
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            person_id = member.getRelevance_info_id();
            person = this.personDaoMapper.findPersonByID(person_id);
            if (person == null) {
                ecode = "3005"; // 个人信息为空
                result.put("ecode", ecode);
            } else if (person.getPerson_mobile_phone().equals("none")) {
                ecode = "3005"; // 个人信息为空
                result.put("ecode", ecode);
            } else {
                String[] longID = member.getMember_belong_code().split("/");
                if (longID.length == 1) {
                    result.put("company_supName", "none");
                    result.put("person_supName", "none");
                } else {
                    T_Data_Company supCompany = this.companyDaoMapper.findCompanyByID(longID[0]);
                    if (supCompany != null) {
                        result.put("company_supName", supCompany.getCompany_name());
                    } else {
                        result.put("company_supName", "none");
                    }
                    T_Data_Person supPerson = this.personDaoMapper.findPersonByID(longID[longID.length - 2]);
                    if (supPerson != null) {
                        result.put("person_supName", supPerson.getPerson_name());
                    } else {
                        result.put("person_supName", "none");
                    }
                }

                ecode = "1000"; // 响应成功
                result.put("ecode", ecode);
                result.put("object1", person);
            }

        } catch (Exception e) {
            ecode = "2000"; // 响应失败
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        logger.info("ecode=" + ecode);
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：editPerson
     * 内容摘要：司机版个人信息的提交
     *
     * @param member_name 会员名
     * @param personJson  json
     * @return String  返回值json
     */
    @Transactional
    public String editPerson(String member_name, String personJson) {
        String ecode = null;
        String json = null;
        Map result = new HashMap();
        T_Data_Member member = null;                // 会员注册信息
        T_Data_Person person_old = null;            // 数据库对象
        // personJson解析
        Gson gson = new Gson();
        Type typeClass = new TypeToken<T_Data_Person>() {
        }.getType();
        T_Data_Person person_new = gson.fromJson(personJson, typeClass);
        logger.info("personJson=" + JSONUtil.toJSONString(person_new));
        try {
            Date server_time = DateUtil.getDate(); // 服务器当前时间
            member = this.memberDaoMapper.findMemberByName(member_name);
            String account_type = member.getAccount_type();
            String relevance_info_id = member.getRelevance_info_id();
            person_old = this.personDaoMapper.findPersonByID(relevance_info_id);
            // 判断个人信息是否未录入
            if (person_old == null) {
                person_new.setPerson_id(UUIDUtil.getUUID());
                person_new.setSubmit_relate_time(null);             // 提交车辆关联时间
                person_new.setConfirm_relate_time(null);            // 确认车辆关联时间
                person_new.setSubmit_verify_time(server_time);      // 提交审核时间
                person_new.setVerify_passed_time(server_time);      // 审核通过时间，默认审核通过
                person_new.setVerify_refused_time(null);            // 审核失败时间
                person_new.setPerson_type(member.getMember_type());
                person_new.setPerson_mobile_phone(member_name);
                person_new.setLast_update(server_time);
                person_new.setLast_update_user_id("M:" + member_name);
                // 审核状态
                person_new.setVerify_status("2"); // app添加司机默认审核通过
                this.personDaoMapper.savePerson(person_new);
                if (account_type == "1") {
                    member.setRelevance_info_id(person_new.getPerson_id());
                    member.setMember_belong_code(member.getMember_belong_code() + "/" + person_new.getPerson_id());
                    this.memberDaoMapper.updateMember(member);
                } else {
                    member.setRelevance_info_id(person_new.getPerson_id());
                    member.setMember_belong_code(person_new.getPerson_id());
                    this.memberDaoMapper.updateMember(member);
                    this.memberDaoMapper.storeMemberByID(member.getMember_id());
                }
                ecode = "1000"; // 录入成功
                result.put("ecode", ecode);
//                result.put("verify_status", "2");   // 默认审核通过，暂时未用到
            } else {
                // 修改个人信息待开发
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
//            result.put("verify_status", "0");  // 暂时未用到
            logger.error(e.getMessage() + MessageUtil.saveInfoError);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
        }
        json = JSONUtil.toJSONString(result);
        return json;
//        String ecode = null;
//        String json = null;
//        Map result = new HashMap();
//        T_Data_Member member = null;
//        T_Data_Person person_old = null;            // 数据库对象
//        String account_type = null;                 // 账户类型
//        // personJson解析
//        Gson gson = new Gson();
//        Type typeClass = new TypeToken<T_Data_Person>() {
//        }.getType();
//        T_Data_Person person_new = gson.fromJson(personJson, typeClass);
//        logger.info("personJson="+JSONUtil.toJSONString(person_new));
//        try {
//            member = this.memberDaoMapper.findMemberByName(member_name);
//            account_type = member.getAccount_type();
//            person_old = this.personDaoMapper.findPersonByID(member.getRelevance_info_id());
//            // 判断个人信息是不是首次录入
//            if (person_old == null) {
//                person_new.setSubmit_relate_time(null);
//                person_new.setConfirm_relate_time(null);
//                person_new.setSubmit_verify_time(null);
//                person_new.setVerify_refused_time(null);
//                person_new.setVerify_passed_time(null);
//                person_new.setPerson_mobile_phone(member_name);
//                person_new.setVerify_status("1");                   // 审核状态
//                person_new.setPerson_id(UUIDUtil.getUUID());
//                person_new.setLast_update(DateUtil.getDate());
//                person_new.setLast_update_user_id("M:" + member_name);
//                person_new.setPerson_type(member.getMember_type());
//                person_new.setDelete_flag("0");
//                this.personDaoMapper.savePerson(person_new);
//                if (account_type == "1") {
//                    member.setRelevance_info_id(person_new.getPerson_id());
//                    member.setMember_belong_code(member.getMember_belong_code() + "/" + person_new.getPerson_id());
//                    this.memberDaoMapper.updateMember(member);
//                } else {
//                    member.setRelevance_info_id(person_new.getPerson_id());
//                    member.setMember_belong_code(person_new.getPerson_id());
//                    this.memberDaoMapper.updateMember(member);
//                    this.memberDaoMapper.storeMemberByID(member.getMember_id());
//                }
//                ecode = "1000"; // 录入成功
//                result.put("ecode", ecode);
//                result.put("verify_status", "1");
//            } else {
//                person_old.setPerson_name(person_new.getPerson_name());
//                person_old.setPerson_mobile_phone(member_name);
//                person_old.setSex(person_new.getSex());
//                person_old.setId_card_number(person_new.getId_card_number());
//                person_old.setDriver_licence_number(person_new.getDriver_licence_number());
//                person_old.setQualification_certificate_number(person_new.getQualification_certificate_number());
//                ecode = "1000"; // 录入成功
//                result.put("verify_status", person_old.getVerify_status());
//                this.personDaoMapper.updatePerson(person_old);
//                member.setRelevance_info_id(person_old.getPerson_id());
//                this.memberDaoMapper.updateMember(member);
//                this.memberDaoMapper.storeMemberByID(member.getMember_id());
//                result.put("ecode", ecode);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 提交失败
//            result.put("ecode", ecode);
//            result.put("verify_status", "0");
//            logger.error(e.getMessage());
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//        带实名认证功能
//        String ecode = null;
//        String json = null;
//        Map result = new HashMap();
//        T_Data_Member member = null;
//        T_Data_Person person_old = null;
//        String account_type = null;
//        boolean b = new Boolean(true);
//        // personJson解析
//        Gson gson = new Gson();
//        Type typeClass = new TypeToken<T_Data_Person>() {
//        }.getType();
//        T_Data_Person person_new = gson.fromJson(personJson, typeClass);
//        try {
//            member = this.memberDaoMapper.findMemberByName(member_name);
//            account_type = member.getAccount_type();
//            person_old = this.personDaoMapper.findPersonByID(member.getRelevance_info_id());
//            // 判断个人信息是不是首次录入
//            if (person_old == null) {
//                b = RNCManager.realNameAuthentication(person_new.getPerson_name(),person_new.getId_card_number());
//                // 判断身份证和姓名是否匹配（实名认证）
//                if (b){
//                    person_new.setSubmit_relate_time(null);
//                    person_new.setConfirm_relate_time(null);
//                    person_new.setSubmit_verify_time(null);
//                    person_new.setVerify_refused_time(null);
//                    person_new.setVerify_passed_time(null);
//                    person_new.setPerson_mobile_phone(member_name);
//                    person_new.setVerify_status("1");
//                    person_new.setPerson_id(UUIDUtil.getUUID());
//                    person_new.setLast_update(DateUtil.getDate());
//                    person_new.setLast_update_user_id("M:" + member_name);
//                    person_new.setPerson_type(member.getMember_type());
//                    person_new.setDelete_flag("0");
//                    this.personDaoMapper.savePerson(person_new);
//                    if (account_type == "1") {
//                        member.setRelevance_info_id(person_new.getPerson_id());
//                        member.setMember_belong_code(member.getMember_belong_code() + "/" + person_new.getPerson_id());
//                        this.memberDaoMapper.updateMember(member);
//                    } else {
//                        member.setRelevance_info_id(person_new.getPerson_id());
//                        member.setMember_belong_code(person_new.getPerson_id());
//                        this.memberDaoMapper.updateMember(member);
//                        this.memberDaoMapper.storeMemberByID(member.getMember_id());
//                    }
//                    ecode = "1000"; // 录入成功
//                    result.put("ecode", ecode);
//                    result.put("verify_status", "1");
//                } else {
//                    ecode ="1004"; // 实名认证失败
//                    result.put("ecode", ecode);
//                    result.put("verify_status", "0");
//                }
//            } else {
//                person_old.setPerson_name(person_new.getPerson_name());
//                person_old.setPerson_mobile_phone(member_name);
//                person_old.setSex(person_new.getSex());
//                person_old.setId_card_number(person_new.getId_card_number());
//                person_old.setDriver_licence_number(person_new.getDriver_licence_number());
//                person_old.setQualification_certificate_number(person_new.getQualification_certificate_number());
//                ecode = "1000"; // 录入成功
//                result.put("verify_status", person_old.getVerify_status());
//                this.personDaoMapper.updatePerson(person_old);
//                member.setRelevance_info_id(person_old.getPerson_id());
//                this.memberDaoMapper.updateMember(member);
//                this.memberDaoMapper.storeMemberByID(member.getMember_id());
//                result.put("ecode", ecode);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 提交失败
//            result.put("ecode", ecode);
//            result.put("verify_status", "0");
//            logger.error(e.getMessage());
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
    }

    /**
     * 方法名称：updatePerson
     * 内容摘要：更新个人信息
     *
     * @param person person对象
     */
    public boolean updatePerson(T_Data_Person person) {
        boolean b = new Boolean(true);
        try {
            this.personDaoMapper.updatePerson(person);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delPerson
     * 内容摘要：删除个人信息
     *
     * @param person_id 个人id
     */
    public void delPerson(String person_id) {
        try {
            this.personDaoMapper.delPerson(person_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }

    }

    /**
     * 方法名称：queryDrivingBehaviorInfo
     * 内容摘要：联合查询会员、个人和车的信息
     *
     * @param person_name  个人姓名
     * @param plate_number 车牌号
     */
    public List<HashMap<String, String>> queryDrivingBehaviorInfo(String person_name, String plate_number) {
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        try {
            result = this.personDaoMapper.queryDrivingBehaviorInfo(person_name, plate_number);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
        return result;
    }

    /**
     * 方法名称：findOperationDriversManagerList
     * 内容摘要：查询运营司机管理列表
     */
    public List<T_Data_Operation_Drivers_Manager> findOperationDriversManagerList(HashMap<String, String> conditions) {
        List<T_Data_Operation_Drivers_Manager> operationDriversManagerList = null;
        try {
            operationDriversManagerList = this.personDaoMapper.findOperationDriversManagerList(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return operationDriversManagerList;
    }

    /**
     * 方法名称：findPersonShippers
     * 内容摘要：查询所有的个人货主
     */
    public List<T_Data_Person> findPersonShippers() {
        List<T_Data_Person> personList = null;
        try {
            personList = this.personDaoMapper.findPersonShippers();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return personList;
    }

    /**
     * 方法名称:saveDriverInfo
     * 方法描述：物流司机管理添加司机
     *
     * @param info
     * @param userName
     * @param pathMap
     * @return int
     */
    @Transactional
    public int saveDriverInfo(String info, String userName, HashMap pathMap) {
        Gson gson = new Gson();
        int flag = 0;
        T_Data_Member member = null;
        T_Data_Person person = new T_Data_Person();
        T_Data_Member m = new T_Data_Member();
        try {
            // 解析info json数据
            Map<String, String> data = gson.fromJson(info, new TypeToken<Map<String, String>>() {
            }.getType());
            logger.info("data=" + JSONUtil.toJSONString(data));
            String person_name = (String) data.get("add_person_name");
            String person_mobile_phone = (String) data.get("add_person_mobile_phone");
            String id_card_number = (String) data.get("add_id_card_number");
            String driver_licence_number = (String) data.get("add_driver_licence_number");
            String qualification_certificate_number = (String) data.get("add_qualification_certificate_number");
            String sex = (String) data.get("add_sex");
            member = this.memberDaoMapper.findMemberByName(person_mobile_phone);
            // 判断司机是否加入平台
            if (member != null) {
                flag = 2; // 司机已加入平台
                throw new RuntimeException(String.valueOf(flag));
            } else {
                // 查找对应的物流公司
                T_Data_Member company_memebr = this.memberDaoMapper.findMemberByName(userName.split(":")[1]);
                String company_id = company_memebr.getRelevance_info_id();
                // 保存个人信息
                String person_id = UUIDUtil.getUUID();
                Date server_time = DateUtil.getDate();          // 服务器当前时间
                person.setPerson_id(person_id);
                person.setSex(sex);
                person.setSubmit_verify_time(server_time);      // 提交审核时间
                person.setPerson_type("1");
                person.setPerson_mobile_phone(person_mobile_phone);
                person.setPerson_name(person_name);
                person.setId_card_number(id_card_number);
                person.setDriver_licence_number(driver_licence_number);
                person.setQualification_certificate_number(qualification_certificate_number);
                // 保存图片路径
                person.setId_card_front_pic_id((String) pathMap.get("id_card_front_path"));
                person.setId_card_back_pic_id((String) pathMap.get("id_card_back_path"));
                person.setDriver_licence_main_pic_id((String) pathMap.get("driver_licence_main_path"));
                person.setDriver_licence_sub_pic_id((String) pathMap.get("driver_licence_sub_path"));
                person.setQualification_certificate_number_pic_id((String) pathMap.get("qualification_certificate_number_path"));
                person.setLast_update(server_time);
                person.setLast_update_user_id(userName);
                // 驾驶状态和审核状态
                person.setDriving_status("0"); // 未确认
                person.setVerify_status("1");  // 验证中
                // 写入数据库
                this.personDaoMapper.savePerson(person);
                // 生成相应的会员信息
                m.setMember_id(UUIDUtil.getUUID());
                m.setMember_name(person_mobile_phone);
                m.setMember_password(MD5Util.md5("000000")); // 设置默认密码000000
                m.setMobile_phone(person_mobile_phone);
                m.setAccount_type("0"); // 个人
                m.setRelevance_info_id(person_id);
                m.setMember_type("1"); // 司机
                // 绑定物流公司
                m.setMember_belong_code(company_id + "/" + person_id);
                m.setMember_business_manage_code(company_id + "/" + person_id);
                // 关联上级会员处理
                m.setBelong_request_result("0");  // 已提交
                m.setBelong_request_submit_time(server_time);
                m.setRegister_time(server_time);
                m.setAccount_status("0"); // 正常
                m.setPayment_password(MD5Util.md5("000000")); // 设置默认密码000000
                m.setDelete_flag("0");
                m.setLast_update_user_id(userName);
                m.setLast_update(server_time);
                this.memberDaoMapper.saveMember(m);
                flag = 1;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            if (e.getMessage().equals("0")) {
                flag = 0;
            } else if (e.getMessage().equals("2")) {
                flag = 2;
            } else {
                flag = -1;
            }
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：findOperationDriversManagerList
     * 内容摘要：查询物流司机管理列表
     */
    @Transactional
    public List<T_Data_Logistics_Drivers_Manager> findDriversList(HashMap<String, String> conditions) {
        List<T_Data_Logistics_Drivers_Manager> driversManagerList = null;
        T_Data_Member company_member = null;
        String company_id = null;
        try {
            company_member = this.memberDaoMapper.findMemberByName(conditions.get("userName"));
            if (company_member != null) {
                company_id = company_member.getRelevance_info_id();
                // 判断企业id是否为平台所在企业id
                if(company_id.equals("742C9E4DFC6940689A5D0F7CF6A69649")) {
                    conditions.put("company_id", "");
                } else {
                    conditions.put("company_id", company_id);
                }
                driversManagerList = this.personDaoMapper.findDriversList(conditions);
                logger.info(MessageUtil.seacheInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.seacheInfoError);
        }
        return driversManagerList;
    }

    /**
     * 方法名称:editDriverInfo
     * 方法描述：物流司机管理添加司机
     *
     * @param info
     * @param userName
     * @param pathMap
     * @return int
     */
    @Transactional
    public int editDriverInfo(String info, String userName, HashMap pathMap) {
        Gson gson = new Gson();
        int flag = 0;
        T_Data_Person person = null;
        try {
            // 解析info json数据
            Map<String, String> data = gson.fromJson(info, new TypeToken<Map<String, String>>() {
            }.getType());
            logger.info("data=" + JSONUtil.toJSONString(data));
            String person_id = (String) data.get("person_id");
            String person_name = (String) data.get("person_name");
            String id_card_number = (String) data.get("id_card_number");
            String driver_licence_number = (String) data.get("driver_licence_number");
            String qualification_certificate_number = (String) data.get("qualification_certificate_number");
            String sex = (String) data.get("sex");
            person = this.personDaoMapper.findPersonByID(person_id);
            person.setPerson_name(person_name);
            person.setId_card_number(id_card_number);
            person.setDriver_licence_number(driver_licence_number);
            person.setQualification_certificate_number(qualification_certificate_number);
            person.setSex(sex);
            // 判断是否更换了图片
            if (pathMap.get("id_card_front_path") != null) {
                person.setId_card_front_pic_id(pathMap.get("id_card_front_path").toString());
            }
            if (pathMap.get("id_card_back_path") != null) {
                person.setId_card_back_pic_id(pathMap.get("id_card_back_path").toString());
            }
            if (pathMap.get("driver_licence_main_path") != null) {
                person.setDriver_licence_main_pic_id(pathMap.get("driver_licence_main_path").toString());
            }
            if (pathMap.get("driver_licence_sub_path") != null) {
                person.setDriver_licence_sub_pic_id(pathMap.get("driver_licence_sub_path").toString());
            }
            if (pathMap.get("qualification_certificate_number_path") != null) {
                person.setQualification_certificate_number_pic_id(pathMap.get("qualification_certificate_number_path").toString());
            }
            this.personDaoMapper.updatePerson(person);
            flag = 1;
            logger.info(MessageUtil.saveInfo);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            flag = -1;
            logger.error(MessageUtil.saveInfoError);
        }
        return flag;
    }

    /**
     * 方法名称:findTrucksOfCompany
     * 方法描述：查找公司下所有审核通过的车辆
     *
     * @param member_name
     * @return T_Data_Truck
     */
    public List<T_Data_Company_Truck_Info> findTrucksOfCompany(String plate_number, String member_name) {
        List<T_Data_Company_Truck_Info> truckList = null;
        T_Data_Member member = null;
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            truckList = this.vehicleDaoMapper.findTrucksOfCompany(plate_number, member.getRelevance_info_id());
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.seacheInfoError);
        }
        return truckList;
    }

    /**
     * 方法名称:unbundlingDriver
     * 方法描述：解绑公司下的司机
     *
     * @param person_id
     * @param useName
     * @return int
     */
    @Transactional
    public int unbundlingDriver(String person_id, String useName) {
        List<T_Data_Company_Truck_Info> truckList = null;
        T_Data_Person person = null;
        T_Data_Member member = null;
        int flag = 0;
        Date server_time = DateUtil.getDate();
        try {
            person = this.personDaoMapper.findPersonByID(person_id);
            // 解除司机关联的车辆
            person.setDriving_truck_id("");
            person.setDriving_status("0");
            person.setSubmit_relate_time(null);
            person.setConfirm_relate_time(null);
            person.setSubmit_verify_time(null);
            person.setLast_update(server_time);
            person.setLast_update_user_id(useName);
            this.personDaoMapper.updatePerson(person);
            // 解除司机会员和公司的关联
            member = this.memberDaoMapper.findMemberByName(person.getPerson_mobile_phone());
            member.setMember_belong_code(member.getRelevance_info_id());
            member.setMember_business_manage_code(member.getRelevance_info_id());
            member.setBelong_request_result(null);
            member.setBelong_request_submit_time(null);
            member.setBelong_request_deal_time(null);
            member.setBelong_request_failure_result("");
            member.setLast_update(server_time);
            member.setLast_update_user_id(useName);
            this.memberDaoMapper.updateMember(member);
            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();
            flag = -1;
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称: findVerifyingDrivers
     * 方法描述：物流公司审核验证中的司机列表
     *
     * @param
     * @return
     */
    public List<T_Data_Person> findVerifyingDrivers(String company_id, String company_name) {
        List<T_Data_Person> driversList = null;
        logger.info("company_id==" + company_id);
        try {
            driversList = this.personDaoMapper.findVerifyingDrivers(company_id, company_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.seacheInfoError);
        }
        return driversList;
    }

    /**
     * 方法名称:agreeDriver
     * 方法描述：物流公司审核通过司机
     *
     * @param person_id
     * @param username
     * @return int
     */
    public int agreeDriver(String person_id, String username) {
        int flag = 0;
        T_Data_Person person = null;
        T_Data_Member member = null;
        Date server_time = DateUtil.getDate();
        try {
            person = this.personDaoMapper.findPersonByID(person_id);
            // 审核通过司机的审核状态为验证通过，驾驶状态为待机
            person.setVerify_status("2");
            person.setDriving_status("1");
            person.setVerify_passed_time(server_time);
            person.setLast_update(server_time);
            person.setLast_update_user_id(username);
            this.personDaoMapper.updatePerson(person);
            flag = 1;
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            flag = -1;
            logger.error(MessageUtil.seacheInfoError);
        }
        return flag;
    }

    /**
     * 方法名称:disagreeDriver
     * 方法描述：物流公司拒绝通过司机
     *
     * @param person_id
     * @param username
     * @return int
     */
    public int disagreeDriver(String person_id, String verify_refused_reason, String username) {
        int flag = 0;
        T_Data_Person person = null;
        T_Data_Member member = null;
        Date server_time = DateUtil.getDate();
        try {
            person = this.personDaoMapper.findPersonByID(person_id);
            // 审核通过司机的审核状态为验证通过，驾驶状态为待机
            person.setVerify_status("3");   // 不合格
            person.setDriving_status("3");  // 被拒绝
            person.setVerify_refused_time(server_time);
            person.setVerify_refused_reason(verify_refused_reason);
            person.setLast_update(server_time);
            person.setLast_update_user_id(username);
            this.personDaoMapper.updatePerson(person);
            flag = 1;
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            flag = -1;
            logger.error(MessageUtil.seacheInfoError);
        }
        return flag;
    }

    /**
     * 方法名称: findVerifyingTrucks
     * 方法描述：物流公司审核验证中的车辆列表
     *
     * @param
     * @return
     */
    public List<T_Data_Vehicle> findVerifyingTrucks(String company_id, String company_name) {
        List<T_Data_Vehicle> trucksList = null;
        logger.info("company_id==" + company_id);
        try {
            trucksList = this.vehicleDaoMapper.findAuthenticaTrucksOfCompany(company_id, company_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.seacheInfoError);
        }
        return trucksList;
    }

    /**
     * 方法名称:agreeTruck
     * 方法描述：物流公司审核通过车辆
     *
     * @param plate_number
     * @param username
     * @return int
     */
    public int agreeTruck(String plate_number, String username) {
        int flag = 0;
        T_Data_Truck truck = null;
        Date server_time = DateUtil.getDate();
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            truck.setVerify_status("2");
            truck.setVerify_passed_time(server_time);
            truck.setLast_update(server_time);
            truck.setLast_update_user_id(username);
            this.truckDaoMapper.updateTruck(truck);
            flag = 1;
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            flag = -1;
            logger.error(MessageUtil.seacheInfoError);
        }
        return flag;
    }

    /**
     * 方法名称:disagreeTruck
     * 方法描述：物流公司拒绝通过车辆
     *
     * @param plate_number
     * @param username
     * @return int
     */
    public int disagreeTruck(String plate_number, String verify_refused_reason, String username) {
        int flag = 0;
        T_Data_Truck truck = null;
        Date server_time = DateUtil.getDate();
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            truck.setVerify_status("3");
            truck.setVerify_refused_reason(verify_refused_reason);
            truck.setVerify_refused_time(server_time);
            truck.setLast_update(server_time);
            truck.setLast_update_user_id(username);
            this.truckDaoMapper.updateTruck(truck);
            flag = 1;
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            flag = -1;
            logger.error(MessageUtil.seacheInfoError);
        }
        return flag;
    }
}

