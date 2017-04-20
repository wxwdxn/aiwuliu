/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：MemberRemarkServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：会员备注基础信息查询接口声明
 * 设计文件：
 * 完成日期：2016-08-15
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.MemberDaoMapper;
import com.cn.gazelle.logistics.dao.MemberRemarkDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Data_Member_Remark;
import com.cn.gazelle.logistics.service.MemberRemarkService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.MemberRemarkManager_Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 类 名 称：MemberRemarkServiceImpl
 * 内容描述：会员备注基础信息查询
 * 方法描述：该类有8个方法
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
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.MemberRemarkService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class MemberRemarkServiceImpl implements MemberRemarkService {
    // Log初始化
    Logger logger = Logger.getLogger(MemberRemarkServiceImpl.class);
    @Resource
    private MemberRemarkDaoMapper memberRemarkDaoMapper;  // 数据访问层
    @Resource
    private MemberDaoMapper memberDaoMapper;  // 数据访问层

    /**
     * 方法名称：findMemberRemarkByRemarkNumber
     * 内容摘要：根据备注编号查询会员备注信息。
     * @param remark_number   备注编号
     * @return T_Data_Member_Remark 会员备注信息
     */
    public T_Data_Member_Remark findMemberRemarkByRemarkNumber(String remark_number) {
        return memberRemarkDaoMapper.findMemberRemarkByRemarkNumber(remark_number);
    }

    /**
     * 方法名称：findMemberRemarkByMemberId
     * 内容摘要：根据会员ID查询会员备注信息
     * @param member_id  会员ID
     * @return List<T_Data_Member_Remark> 会员备注信息列
     */
    public List<T_Data_Member_Remark> findMemberRemarkByMemberId(String member_id) {
        return memberRemarkDaoMapper.findMemberRemarkByMemberId(member_id);
    }

    /**
     * 方法名称：findAllMemberRemark
     * 内容摘要：查询符合条件的会员备注列表信息（默认查询所有会员备注列表信息）。
     * @param search_type   搜索类型
     * @param name  查询类型
     * @param page  页面页数
     * @param rows  页面显示条数
     * @return List<T_Master_Operate_Main_Line>  会员备注信息列
     */
    public List<T_Data_Member_Remark> findAllMemberRemark(String search_type, String name, Integer page, Integer rows) {
        return memberRemarkDaoMapper.findAllMemberRemark(search_type, name, page, rows);
    }

    /**
     * 方法名称：findAllMemberRemarkRowsCount
     * 内容摘要：查询会员备注信息总记录数
     * @param search_type   搜索类型
     * @param name  查询类型
     * @return Integer 会员备注记录数
     */
    public Integer findAllMemberRemarkRowsCount(String search_type, String name) {
        return memberRemarkDaoMapper.findAllMemberRemarkRowsCount(search_type, name);
    }

    /**
     * 方法名称：saveMemberRemark
     * 内容摘要：保存会员备注基础信息
     * @param member_remark 会员备注基础信息
     */
    public boolean saveMemberRemark(T_Data_Member_Remark member_remark) {
        boolean a = new Boolean(true);
        try {
            this.memberRemarkDaoMapper.saveMemberRemark(member_remark);
            logger.info(MemberRemarkManager_Message.SaveMemberRemarkDone);
        } catch (Exception e) {
            a=false;
            logger.error(MemberRemarkManager_Message.SaveMemberRemarkError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：updateMemberRemark
     * 内容摘要：更新会员备注基础信息
     * @param member_remark 会员备注基础信息
     */
    public boolean updateMemberRemark(T_Data_Member_Remark member_remark) {
        boolean a=new Boolean(true);
        try {
            member_remark.setLast_update(DateUtil.getDate());
            memberRemarkDaoMapper.updateMemberRemark(member_remark);
        } catch (Exception e) {
            a=false;
            logger.error(MemberRemarkManager_Message.UpdateMemberRemarkError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delMemberRemark
     * 内容摘要：根据备注编号删除会员备注基础信息
     * @param remark_number  备注编号
     */
    public void delMemberRemark(String remark_number) {
        try {
            memberRemarkDaoMapper.delMemberRemark(remark_number);
            logger.info(MemberRemarkManager_Message.DelMemberRemarkDone);
        } catch (Exception e) {
            logger.error(MemberRemarkManager_Message.DelMemberRemarkError+e.getMessage());
        }
    }


    public String uploadUserNotes(String member_name, String remark_content) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;
        String member_id = null;
        T_Data_Member_Remark member_remark = new T_Data_Member_Remark();
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            if (member != null) {
                member_id = member.getRelevance_info_id();
                member_remark.setMember_id(member_id);// 会员ID
                member_remark.setRemark_content(remark_content);// 备注内容
                member_remark.setAdd_time(DateUtil.getDate());// 添加时间
                member_remark.setDelete_flag(0);// 删除标识符，0：未删除
                member_remark.setLast_update(DateUtil.getDate());// 最终更新日
                member_remark.setLast_update_user_id("M:" + member_name);// 最终更新者
                this.memberRemarkDaoMapper.saveMemberRemark(member_remark);

                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                logger.info(MemberRemarkManager_Message.SaveMemberRemarkDone);
            } else {
                ecode = "3000";   // 会员不存在
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";       // 保存备注信息失败
            result.put("ecode", ecode);
            logger.error(MemberRemarkManager_Message.SaveMemberRemarkError+e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
}