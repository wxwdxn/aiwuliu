/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MemberPositionDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员定位信息管理实现
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者：QJ
 * 内容摘要：会员定位信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Member_Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: MemberPositionDaoMapper
 * 内容摘要: 会员定位信息管理实现
 * 方法描述：该类有8个方法：
 *         01 findMemberPositionByPositionNumber         根据位置No查询会员定位信息
 *         02 findMemberPositionByMemberId                  根据会员ID查询会员定位信息
 *         03 findAllMemberPosition                      查询符合条件的会员定位列表信息（默认查询所有会员定位列表信息）
 *         04 findAllMemberPositionRowsCount             查询会员定位信息总记录数
 *         05 saveMemberPosition                         保存会员定位基础信息
 *         06 updateMemberPosition                       更新会员定位基础信息
 *         07 delMemberPosition                          根据位置No删除会员定位基础信息
 *         08 findLastMemberPositionByMemberId              根据会员ID倒叙查询最近10条会员定位信息
 * @author QJ
 */
public interface MemberPositionDaoMapper {
    // 根据位置No查询会员定位信息
    public T_Data_Member_Position findMemberPositionByPositionNumber(@Param(value = "position_number") String position_number);

    // 根据会员ID查询会员定位信息
    public List<T_Data_Member_Position> findMemberPositionByMemberId(@Param(value = "member_id") String member_id);

    // 查询符合条件的会员定位列表信息（默认查询所有会员定位列表信息）
    public List<T_Data_Member_Position> findAllMemberPosition(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询会员定位信息总记录数
    public Integer findAllMemberPositionRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存会员定位基础信息
    public void saveMemberPosition(T_Data_Member_Position member_position);

    // 更新会员定位基础信息
    public void updateMemberPosition(T_Data_Member_Position member_position);

    // 根据位置No删除会员定位基础信息
    public void delMemberPosition(@Param(value = "position_number") String position_number);

    // 根据会员ID倒叙查询最近10条会员定位信息
    public List<T_Data_Member_Position> findLastMemberPositionByMemberId(@Param(value = "member_id") String member_id);
}
