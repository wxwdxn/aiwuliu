/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ContentServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：会员电子协议内容基础信息service层
 * 设计文件：
 * 完成日期：2016-05-18
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.CompanyDaoMapper;
import com.cn.gazelle.logistics.dao.ContentDaoMapper;
import com.cn.gazelle.logistics.dao.MemberDaoMapper;
import com.cn.gazelle.logistics.dao.TitleDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Master_Electronic_Agreement_Content;
import com.cn.gazelle.logistics.pojo.T_Master_Electronic_Agreement_Title;
import com.cn.gazelle.logistics.service.ContentService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：ContentServiceImpl
 * 内容描述：会员电子协议内容基础信息setvice层
 * 方法描述：该类有 个方法
 *          01 saveContent                          保存协议内容信息
 *          02 updateContent                        更新协议内容信息
 *          03 getAgreementContent                  获取注册协议
 *          04 judgeAgreementContent                判断是否是最新协议
 *          05 signAgreementContent                 签订电子协议
 *@authot YK
 */
@Service
@WebService(endpointInterface= "com.cn.gazelle.logistics.service.ContentService",targetNamespace="http://service.logistics.gazelle.cn.com/")
public class ContentServiceImpl implements ContentService {
    // Log初始化
    Logger logger = Logger.getLogger(ContentServiceImpl.class);
    @Resource
    private ContentDaoMapper contentDaoMapper;
    @Resource
    private TitleDaoMapper titleDaoMapper;
    @Resource
    private MemberDaoMapper memberDaoMapper;

    /**
     * 方法名称：saveContent
     * 内容摘要：保存协议内容信息
     * @param content 协议内容信息
     */
    public int saveContent(T_Master_Electronic_Agreement_Content content) {
        int count = 0;
        try {
            count = this.contentDaoMapper.saveContent(content);
            if (count == 1){
                logger.info(MessageUtil.saveInfo);
            }else if (count ==0){
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateContent
     * 内容摘要：更新协议内容信息
     * @param content 协议内容信息
     */
    public boolean updateContent(T_Master_Electronic_Agreement_Content content) {
        boolean b = new Boolean(true);
        try {
            this.contentDaoMapper.updateContent(content);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：getAgreementContent
     * 内容摘要：获取注册协议
     * @param member_type 用户类型
     */
    public String getAgreementContent(String member_type) {
        List<T_Master_Electronic_Agreement_Content> contentList = null;
        T_Master_Electronic_Agreement_Title title = null;
        Map result = new HashMap();
        Map<String,String> results = new HashMap<String, String>();
        String ecode = null;
        logger.info("member_type = "+member_type);
        try {
            contentList = this.contentDaoMapper.findContentByMemberType(member_type);
            results.put("electronic_agreement_version",contentList.get(0).getElectronic_agreement_version());
            results.put("electronic_agreement_content",contentList.get(0).getElectronic_agreement_content());
            title = this.titleDaoMapper.findTitleByMemberType(member_type);
            results.put("electronic_agreement_title",title.getElectronic_agreement_title());
            ecode = "1000";
            result.put("ecode",ecode);
            result.put("object1",results);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode);
            result.put("object1","none");
            logger.error(LogUtil.searchErr+e.getMessage());
        }
        logger.info("ecode="+ecode);
        logger.info(JSONUtil.toJSONString(result));
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：judgeAgreementContent
     * 内容摘要：判断是否是最新协议
     * @param member_name 会员名
     */
    public String judgeAgreementContent(String member_name) {
        T_Data_Member member = null;
        List<T_Master_Electronic_Agreement_Content> contentList = null;
        T_Master_Electronic_Agreement_Title title = null;
        String ecode = null;
        String baseUrl = "/AgreementContent/";
        Map result = new HashMap();
        Map<String,String> results = new HashMap<String, String>();
        logger.info("member_name="+member_name);
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            contentList = this.contentDaoMapper.findContentByMemberType(member.getMember_type());
            // 判断是否是最新的协议
            if (member.getSigned_electronica_agreement_no().equals(contentList.get(0).getElectronic_agreement_version_no())){
                ecode = "1000";
                result.put("ecode",ecode);
            }
            else {
                ecode = "4000";// 不是最新的协议
                result.put("ecode",ecode);
                results.put("electronic_agreement_version",contentList.get(0).getElectronic_agreement_version());
                results.put("electronic_agreement_content",baseUrl+contentList.get(0).getElectronic_agreement_content());
                title = this.titleDaoMapper.findTitleByMemberType(member.getMember_type());
                results.put("electronic_agreement_title",title.getElectronic_agreement_title());
                result.put("object1",results);
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode);
            logger.error(LogUtil.searchErr+e.getMessage());
        }
        logger.info("ecode="+ecode);
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法名称：signAgreementContent
     * 内容摘要：签订电子协议
     * @param member_name 会员名
     * @param electronic_agreement_version 协议版本
     */
    public String signAgreementContent(String member_name, String electronic_agreement_version) {
        T_Data_Member member = null;
        T_Master_Electronic_Agreement_Content content = null;
        String ecode = null;
        Map result = new HashMap();
        logger.info("member_name="+member_name);
        logger.info("electronic_agreement_version="+electronic_agreement_version);
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            content = this.contentDaoMapper.findContentByNo(member.getMember_type(),electronic_agreement_version);
            this.memberDaoMapper.updateAgreementCode(member_name , content.getElectronic_agreement_version_no());
            ecode = "1000";
            result.put("ecode",ecode);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode);
            logger.error(LogUtil.searchErr+e.getMessage());
        }
        logger.info("ecode="+ecode);
        return JSONUtil.toJSONString(result);
    }
}
