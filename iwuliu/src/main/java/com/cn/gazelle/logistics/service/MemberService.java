/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MemberService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员信息查询接口声明
 * 设计文件：
 * 完成日期：2015-12-30
 * 作    者：YK
 * 内容摘要：会员信息查询
 */

package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_User_Role_Manager;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: MemberService
 * 内容摘要: 会员信息查询接口
 * 方法描述：该类有19个方法：
 * 01 findMemberByID                 根据会员ID查询会员信息
 * 02 storeMemberByID                根据会员id实现存储过程
 * 03 findMemberByName               根据会员名查询会员信息
 * 05 findAll                        查询所有会员信息（不分页）
 * 06 findMemberByNameAndPassword    根据会员名和会员密码查询会员信息
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
 * 20 updatePassword                 重置密码
 *
 * @author YK
 */
@WebService
public interface MemberService {

    // 根据会员ID查询会员信息
    T_Data_Member findMemberByID(String member_id);

    // 根据会员id实现存储过程
    T_Data_Member storeMemberByID(String member_id);

    // 根据会员名查询会员信息
    T_Data_Member findMemberByName(String member_name);

    // 查询所有会员信息（不分页）
    List<T_Data_Member> findAll();

    // 根据会员名和会员密码查询会员信息
    T_Data_Member findMemberByNameAndPassword(String member_name, String member_password, String member_type);

    // 会员登录
    String memberLogin(String memberJson);

    // 找回密码
    String findPassword(String memberJson);

    // 注册会员信息
    String saveMember(String memberJson, String company_name);

    // 当前公司管理账号信息的修改
    String editMember(T_Data_Member member);

    // 更新会员信息
    boolean updateMember(T_Data_Member member);

    // 删除会员信息
    void delMember(String member_id);

    // 获取验证码(个人)
    String phoneCode(String tel, String request_type);

    // 获取验证码(公司)
    String phoneCode2(String phoneNumber, String company_name);

    // 验证验证码
    String vertifyPhoneCode(String phoneCode, String mobile_phone);

    // 下属人员的添加
    String addSubMember(String member_name, String member_subName);

    // 下属人员的删除
    String delSubMember(String member_name, String member_subName);

    // 根据账号检索下属人员列表
    String findAllSubMember(String member_name, int member_identity, int business_supervise_function, int begin_rows, int end_rows);

    // 根据账号获取车队长列表
    String findCarCaptain(String member_name);

    // 根据账号检索下属司机(无绑定车辆)列表
    String findSubBuser(String member_name);

    // 重置密码
    boolean updatePassword(String member_name, String member_password);

    // 查找会员身份，管理者或行驶司机
    String findMemberIdentity(String member_name);

    // 根据派车单查找会员身份，管理者或行驶司机
    String findMemberIdentityByScheduleSheet(String member_name, String plate_number, String schedule_sheet_id);

    // 查询用户角色管理列表信息
    List<T_Data_User_Role_Manager> queryUserRoleList(HashMap<String, String> conditions);

    // 上传会员图像
    boolean updateHeadPic(T_Data_Member member);

    // 个人账户检索（支付）
    String findMemberAccount(String member_name);

    // 账户支付密码设置（支付）
    String setPaymentPassword(String account_name, String payment_password, String account_type);

    // 账户支付密码修改（支付）
    String editPaymentPassword(String account_name, String old_payment_password,
                               String new_payment_password, String account_type);

    // 检测个人账户金额是否有充足金额满足提现（支付）
    boolean checkMemberAccount(String member_name,String total_fee);

    // 账户密码检索（支付）
    String checkPaymentPassword(String member_name, String payment_password);

    // 会员账户明细检索_2.0
    public String findMemberAccountDetail(String member_name, String date);

    // 查询会员账户资金量（财务金融）
    double queryMemberAccountAmountSum();

    // 个人账户明细（虚拟账目查看）
    List<T_Data_Member> queryAccountDetail();

    //交易记录查询
    List queryTransactionRecords (HashMap<String, String> conditions);


}
