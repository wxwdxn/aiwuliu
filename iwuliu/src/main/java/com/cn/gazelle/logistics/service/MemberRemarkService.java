/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MemberRemarkService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员备注基础信息查询接口声明
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者：QJ
 * 内容摘要：会员备注基础信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Member_Remark;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: MemberRemarkService
 * 内容摘要: 会员备注基础信息查询
 * 方法描述：该类有8个方法：
 *         01 findMemberRemarkByRemarkNumber           根据备注编号查询会员备注信息
 *         02 findMemberRemarkByMemberId               根据会员ID查询会员备注信息
 *         03 findAllMemberRemark                      查询符合条件的会员备注列表信息（默认查询所有会员备注列表信息）
 *         04 findAllMemberRemarkRowsCount             查询会员备注信息总记录数
 *         05 saveMemberRemark                         保存会员备注基础信息
 *         06 updateMemberRemark                       更新会员备注基础信息
 *         07 delMemberRemark                          根据备注编号删除会员备注基础信息
 *         08 uploadUserNotes                          上传用户备注
 * @author QJ
 */
@WebService
public interface MemberRemarkService {
    // 根据备注编号查询会员备注信息
    T_Data_Member_Remark findMemberRemarkByRemarkNumber(String remark_number);

    // 根据会员ID查询会员备注信息
    List<T_Data_Member_Remark> findMemberRemarkByMemberId(String member_id);

    // 查询符合条件的会员备注列表信息（默认查询所有会员备注列表信息）
    List<T_Data_Member_Remark> findAllMemberRemark(String search_type, String name, Integer page, Integer rows);

    // 查询会员备注信息总记录数
    Integer findAllMemberRemarkRowsCount(String search_type, String name);

    // 保存会员备注基础信息
    boolean saveMemberRemark(T_Data_Member_Remark member_remark);

    // 更新会员备注基础信息
    boolean updateMemberRemark(T_Data_Member_Remark member_remark);

    // 根据备注编号删除会员备注基础信息
    void delMemberRemark(String remark_number);

    // 上传用户备注
    String uploadUserNotes(String member_name, String remark_content);
}
