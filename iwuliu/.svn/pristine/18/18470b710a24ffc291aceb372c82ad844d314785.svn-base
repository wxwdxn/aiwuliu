/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MemberPositionService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员定位基础信息查询接口声明
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者：QJ
 * 内容摘要：会员定位基础信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Member_Position;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

/**
 * 类 名 称: MemberPositionService
 * 内容摘要: 会员定位基础信息查询
 * 方法描述：该类有8个方法：
 *         01 findMemberPositionByPositionNumber         根据位置No查询会员定位信息
 *         02 findMemberPositionByMemberId               根据会员ID查询会员定位信息
 *         03 findAllMemberPosition                      查询符合条件的会员定位列表信息（默认查询所有会员定位列表信息）
 *         04 findAllMemberPositionRowsCount             查询会员定位信息总记录数
 *         05 saveMemberPosition                         保存会员定位基础信息
 *         06 updateMemberPosition                       更新会员定位基础信息
 *         07 delMemberPosition                          根据位置No删除会员定位基础信息
 *         08 uploadMobilePhoneLocation                  手机位置上传
 * @author QJ
 */
@WebService
public interface MemberPositionService {
    // 根据位置No查询会员定位信息
    T_Data_Member_Position findMemberPositionByPositionNumber(String position_number);

    // 根据会员ID查询会员定位信息
    List<T_Data_Member_Position> findMemberPositionByMemberId(String member_id);

    // 查询符合条件的会员定位列表信息（默认查询所有会员定位列表信息）
    List<T_Data_Member_Position> findAllMemberPosition(String search_type, String name, Integer page, Integer rows);

    // 查询会员定位信息总记录数
    Integer findAllMemberPositionRowsCount(String search_type, String name);

    // 保存会员定位基础信息
    boolean saveMemberPosition(T_Data_Member_Position member_position);

    // 更新会员定位基础信息
    boolean updateMemberPosition(T_Data_Member_Position member_position);

    // 根据位置No删除会员定位基础信息
    void delMemberPosition(String position_number);

    // 手机位置上传
    String uploadMobilePhoneLocation(String member_name, String longitude, String latitude, String client_acquisition_time);
}
