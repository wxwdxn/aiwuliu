/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MemberRemarkDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员备注信息管理实现
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者：QJ
 * 内容摘要：会员备注信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Member_Remark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: MemberRemarkDaoMapper
 * 内容摘要: 会员备注信息管理实现
 * 方法描述：该类有8个方法：
 *         01 findMemberRemarkByRemarkNumber        根据备注编号查询会员备注信息
 *         02 findMemberRemarkByMemberId            根据会员ID查询会员备注信息
 *         03 findAllMemberRemark                   查询符合条件的会员备注列表信息（默认查询所有会员备注列表信息）
 *         04 findAllMemberRemarkRowsCount          查询会员备注信息总记录数
 *         05 saveMemberRemark                      保存会员备注基础信息
 *         06 updateMemberRemark                    更新会员备注基础信息
 *         07 delMemberRemark                       根据备注编号删除会员备注基础信息
 *         08 findLastMemberRemarkByMemberId        根据会员ID倒叙查询最近10条会员备注信息
 * @author QJ
 */
public interface MemberRemarkDaoMapper {
    // 根据备注编号查询会员定位信息
    public T_Data_Member_Remark findMemberRemarkByRemarkNumber(@Param(value = "remark_number") String remark_number);

    // 根据会员ID查询会员定位信息
    public List<T_Data_Member_Remark> findMemberRemarkByMemberId(@Param(value = "member_id") String member_id);

    // 查询符合条件的会员定位列表信息（默认查询所有会员定位列表信息）
    public List<T_Data_Member_Remark> findAllMemberRemark(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询会员定位信息总记录数
    public Integer findAllMemberRemarkRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存会员定位基础信息
    public void saveMemberRemark(T_Data_Member_Remark member_remark);

    // 更新会员定位基础信息
    public void updateMemberRemark(T_Data_Member_Remark member_remark);

    // 根据备注编号删除会员定位基础信息
    public void delMemberRemark(@Param(value = "remark_number") String remark_number);

    // 根据会员ID倒叙查询最近10条会员定位信息
    public List<T_Data_Member_Remark> findLastMemberRemarkByMemberId(@Param(value = "member_id") String member_id);
}
