/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：MemberPositionServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员定位基础信息查询接口声明
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.MemberDaoMapper;
import com.cn.gazelle.logistics.dao.MemberPositionDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_Member_Position;
import com.cn.gazelle.logistics.service.MemberPositionService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.MemberPositionManager_Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 类 名 称：MemberPositionServiceImpl
 * 内容描述：会员定位基础信息查询
 * 方法描述：该类有8个方法
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
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.MemberPositionService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class MemberPositionServiceImpl implements MemberPositionService {
    // Log初始化
    Logger logger = Logger.getLogger(MemberPositionServiceImpl.class);
    @Resource
    private MemberPositionDaoMapper memberPositionDaoMapper;  // 数据访问层
    @Resource
    private MemberDaoMapper memberDaoMapper;  // 数据访问层

    /**
     * 方法名称：findMemberPositionByPositionNumber
     * 内容摘要：根据位置No查询会员定位信息。
     * @param position_number   位置No
     * @return T_Data_Member_Position 会员定位信息
     */
    public T_Data_Member_Position findMemberPositionByPositionNumber(String position_number) {
        return memberPositionDaoMapper.findMemberPositionByPositionNumber(position_number);
    }

    /**
     * 方法名称：findMemberPositionByMemberId
     * 内容摘要：根据会员ID查询会员定位信息
     * @param member_id  会员ID
     * @return List<T_Data_Member_Position> 会员定位信息列
     */
    public List<T_Data_Member_Position> findMemberPositionByMemberId(String member_id) {
        return memberPositionDaoMapper.findMemberPositionByMemberId(member_id);
    }

    /**
     * 方法名称：findAllMemberPosition
     * 内容摘要：查询符合条件的会员定位列表信息（默认查询所有会员定位列表信息）。
     * @param search_type   搜索类型
     * @param name  查询类型
     * @param page  页面页数
     * @param rows  页面显示条数
     * @return List<T_Master_Operate_Main_Line>  会员定位信息列
     */
    public List<T_Data_Member_Position> findAllMemberPosition(String search_type, String name, Integer page, Integer rows) {
        return memberPositionDaoMapper.findAllMemberPosition(search_type, name, page, rows);
    }

    /**
     * 方法名称：findAllMemberPositionRowsCount
     * 内容摘要：查询会员定位信息总记录数
     * @param search_type   搜索类型
     * @param name  查询类型
     * @return Integer 会员定位记录数
     */
    public Integer findAllMemberPositionRowsCount(String search_type, String name) {
        return memberPositionDaoMapper.findAllMemberPositionRowsCount(search_type, name);
    }

    /**
     * 方法名称：saveMemberPosition
     * 内容摘要：保存会员定位基础信息
     * @param member_position 会员定位基础信息
     */
    public boolean saveMemberPosition(T_Data_Member_Position member_position) {
        boolean a = new Boolean(true);
        try {
            this.memberPositionDaoMapper.saveMemberPosition(member_position);
            logger.info(MemberPositionManager_Message.SaveMemberPositionDone);
        } catch (Exception e) {
            a=false;
            logger.error(MemberPositionManager_Message.SaveMemberPositionError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：updateMemberPosition
     * 内容摘要：更新会员定位基础信息
     * @param member_position 会员定位基础信息
     */
    public boolean updateMemberPosition(T_Data_Member_Position member_position) {
        boolean a=new Boolean(true);
        try {
            member_position.setLast_update(DateUtil.getDate());
            memberPositionDaoMapper.updateMemberPosition(member_position);
        } catch (Exception e) {
            a=false;
            logger.error(MemberPositionManager_Message.UpdateMemberPositionError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delMemberPosition
     * 内容摘要：根据位置No删除会员定位基础信息
     * @param position_number  位置No
     */
    public void delMemberPosition(String position_number) {
        try {
            memberPositionDaoMapper.delMemberPosition(position_number);
            logger.info(MemberPositionManager_Message.DelMemberPositionDone);
        } catch (Exception e) {
            logger.error(MemberPositionManager_Message.DelMemberPositionError+e.getMessage());
        }
    }

    /**
     * 方法名称：uploadMobilePhoneLocation
     * 内容摘要：根据位置No删除会员定位基础信息
     * @param member_name   账号
     * @param longitude 经度
     * @param latitude  纬度
     * @param client_acquisition_time   客户端采集时间
     * @return String 返回值json
     */
    public String uploadMobilePhoneLocation(String member_name, String longitude, String latitude, String client_acquisition_time) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;
        String member_id = null;
        T_Data_Member_Position member_position = new T_Data_Member_Position();
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            if (member != null) {
                member_id = member.getRelevance_info_id();
                member_position.setMember_id(member_id);// 会员ID
                member_position.setLongitude(longitude);// 经度
                member_position.setLatitude(latitude);// 纬度
                java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                Date date =  formatter.parse(client_acquisition_time);
                member_position.setClient_acquisition_time(date);// 客户端采集时间
                member_position.setServer_acquisition_time(DateUtil.getDate());// 服务器采集时间
                member_position.setDelete_flag(0);// 删除标识符，0：未删除
                member_position.setLast_update(DateUtil.getDate());// 最终更新日
                member_position.setLast_update_user_id("M:" + member_name);// 最终更新者
                this.memberPositionDaoMapper.saveMemberPosition(member_position);
                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                logger.info(MemberPositionManager_Message.SaveMemberPositionDone);
            } else {
                ecode = "3000";   // 会员不存在
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";       // 保存定位信息失败
            result.put("ecode", ecode);
            logger.error(MemberPositionManager_Message.SaveMemberPositionError+e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
}