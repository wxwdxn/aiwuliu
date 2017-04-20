/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MemberDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员信息查询接口实现
 * 设计文件：
 * 完成日期：2016-01-02
 * 作    者：YK
 * 内容摘要：会员信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_User_Role_Manager;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: MemberDaoMapper
 * 内容摘要: 会员信息管理页面
 * 方法描述：该类有16个方法：
 *         01 findMerberByRelevance_info_id                 根据关联信息id查询
 *         02 findMemberByMember_belong_code                根据用户所属关系id查询
 *         03 findMemberByID                                根据会员id查询会员信息
 *         04 storeMemberByID                               根据会员id实现存储过程
 *         05 findMemberByName                              根据会员名查询会员信息
 *         06 findMemberByPasswordAndName                   根据会员名和密码查询会员信息
 *         07 findMemberByNameAndPassword                   根据会员名、会员密码和用户状态查询会员信息
 *         08 findMemberByMobilePhone                       通过手机号查找会员信息
 *         09 findAllSubMemberInfo                          通过用户所属关系编码和用户类型查找所有会员
 *         10 findAllMember                                 查询所有会员信息
 *         11 findAll                                       查询所有会员信息（不分页）
 *         12 findAllMemberRowsCount                        查询会员总记录数
 *         13 saveMember                                    保存会员信息
 *         14 updateMember                                  更新会员信息
 *         15 updatePassword                                重置会员密码
 *         16 delMember                                     删除会员信息
 *         17 updateAgreementCode                           更新电子协议编码
 *         18 updateHeadPic                                 上传会员图像
 * @author YK
 */
public interface MemberDaoMapper {

    // 根据关联信息id查询
    T_Data_Member findMerberByRelevance_info_id(@Param(value = "relevance_info_id") String relevance_info_id);

    // 根据用户所属关系id查询
    List<T_Data_Member> findMemberByMember_belong_code(@Param(value = "member_belong_code") String member_belong_code);

    // 根据会员id查询会员信息
    T_Data_Member findMemberByID(@Param(value = "member_id") String member_id);

    // 根据会员id实现存储过程
    T_Data_Member storeMemberByID(@Param(value = "member_id") String member_id);

    // 根据会员名查询会员信息（根据会员名默认查找一条信息）
    T_Data_Member findMemberByName(@Param(value = "member_name") String member_name);

    // 根据会员名和密码查询会员信息
    T_Data_Member findMemberByPasswordAndName(@Param(value = "member_name") String member_name, @Param(value = "member_password") String member_password);

    // 根据会员名、会员密码和用户状态查询会员信息
    T_Data_Member findMemberByNameAndPassword(@Param(value = "member_name") String member_name, @Param(value = "member_password") String member_password, @Param(value = "member_type") String member_type);

    // 通过手机号查找会员信息
    T_Data_Member findMemberByMobilePhone(@Param(value = "mobile_phone") String mobile_phone);

    // 通过用户所属关系编码和用户类型查找所有会员
    List<T_Data_Member> findAllSubMemberInfo(@Param(value = "member_type") String member_type, @Param(value = "member_belong_code") String member_belong_code);

    // 查询所有会员信息（不分页）
    List<T_Data_Member> findAll();

    // 保存会员信息
    void saveMember(T_Data_Member member);

    // 更新会员信息
    void updateMember(T_Data_Member member);

    // 重置会员密码
    void updatePassword(@Param(value = "member_name") String member_name, @Param(value = "member_password") String member_password);

    // 删除会员信息
    void delMember(@Param(value = "member_id") String member_id);

    // 更新电子协议编码
    void updateAgreementCode(@Param("member_name") String member_name, @Param(value = "signed_electronica_agreement_no") String signed_electronica_agreement_no);

    // 上传会员图像
    void updateHeadPic(T_Data_Member member);

    // 查询用户角色管理列表信息
    List<T_Data_User_Role_Manager> queryUserRoleList(HashMap<String, String> conditions);

    // 查询会员账户资金量（财务金融）
    double queryMemberAccountAmountSum();

    // 个人账户明细（虚拟账目查看）
    List<T_Data_Member> queryAccountDetail();

    //交易记录查询
    List queryTransactionRecords (HashMap<String, String> conditions);


}