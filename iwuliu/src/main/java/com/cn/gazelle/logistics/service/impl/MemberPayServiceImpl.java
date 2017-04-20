package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.MemberBankAccountDaoMapper;
import com.cn.gazelle.logistics.dao.MemberPaymentHistoryDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Company_AccountInfo;
import com.cn.gazelle.logistics.pojo.T_Data_Member_Bank_Account;
import com.cn.gazelle.logistics.pojo.T_Data_Member_Payment_History;
import com.cn.gazelle.logistics.service.MemberPayService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.cxf.common.i18n.Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 功能描述:会员支付信息管理
 * Created by zf on 2016/12/12.
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.MemberPayService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class MemberPayServiceImpl implements MemberPayService {


    // Log初始化
    Logger logger = Logger.getLogger(MemberPayServiceImpl.class);

    @Resource
    private MemberBankAccountDaoMapper memberBankAccountDaoMapperMapper;                 // 数据访问层
    @Resource
    private MemberPaymentHistoryDaoMapper memberPaymentHistoryDaoMapper;

    /**
     * 根据绑定编号查询会员银行卡信息
     * @param account_no
     * @return
     */
    @Override
    public T_Data_Member_Bank_Account findMemberBankAccountByAccountNo(String account_no) {
        T_Data_Member_Bank_Account memberBankAccount  = null;
        try {
            memberBankAccount = memberBankAccountDaoMapperMapper.findMemberBankAccountByAccountNo(account_no);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);
        }
        return memberBankAccount;
    }

    /**
     * 根据会员ID查询会员银行卡信息
     * @param member_id
     * @return
     */
    @Override
    public List<T_Data_Member_Bank_Account> findAll(String member_id) {
        List<T_Data_Member_Bank_Account> member_bank_accounts = null;
        try {
            member_bank_accounts = memberBankAccountDaoMapperMapper.findAll(member_id);
            logger.error(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);
        }
        return member_bank_accounts;
    }

    /**
     * 保存会员银行卡信息
     * @param memberBankAccount
     */
    @Override
    public void saveMemberBankAccount(T_Data_Member_Bank_Account memberBankAccount) {
        try {
            memberBankAccountDaoMapperMapper.saveMemberBankAccount(memberBankAccount);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.saveInfoError);
        }
    }

    /**
     * 更新会员银行卡信息
     * @param memberBankAccount
     */
    @Override
    public void updateMemberBankAccount(T_Data_Member_Bank_Account memberBankAccount) {
        try {
            memberBankAccountDaoMapperMapper.updateMemberBankAccount( memberBankAccount);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(MessageUtil.saveInfo);
        }

    }

    /**
     * 根据绑定编号删除会员银行卡信息
     * @param account_no
     */
    @Override
    public void delMemberBankAccount(String account_no) {
        try {
            memberBankAccountDaoMapperMapper.delMemberBankAccount(account_no);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.delInfoError);
        }
    }

    /**
     * 根据条件查询公司信息
     * @param conditions
     * @return
     */
    @Override
    public List<T_Data_Company_AccountInfo> findAccountInfo(HashMap<String, String> conditions) {
        List<T_Data_Company_AccountInfo> companyAccountInfos = null;
        try {
            companyAccountInfos = memberBankAccountDaoMapperMapper.findAccountInfo(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.seacheInfoError);
        }

        return companyAccountInfos;
    }

    /**
     * 根据绑定编号查询会员支付历史信息
     * @param history_number
     * @return
     */
    @Override
    public T_Data_Member_Payment_History findMemberPaymentHistoryByAccountNo(String history_number) {
        T_Data_Member_Payment_History memberPaymentHistory = null;
        try {
            memberPaymentHistory = memberPaymentHistoryDaoMapper.findMemberPaymentHistoryByAccountNo(history_number);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);
        }
        return memberPaymentHistory;
    }
    //根据订单编号查询会员支付历史信息
    @Override
    public T_Data_Member_Payment_History findMemberPayMentHistoryByOrderNo(String order_no) {
        T_Data_Member_Payment_History memberPaymentHistory = null;
        try {
            memberPaymentHistory = memberPaymentHistoryDaoMapper.findMemberPayMentHistoryByOrderNo(order_no);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.seacheInfoError);
        }
        return memberPaymentHistory;
    }

    /**
     * 根据时间范围获取会员支付历史信息
     * @param conditions
     * @return
     */
    @Override
    public List<T_Data_Member_Payment_History> findHistoryAll(HashMap<String, String> conditions) {
        List<T_Data_Member_Payment_History>  memberPaymentHistoryList = null;
        try {
            memberPaymentHistoryList = memberPaymentHistoryDaoMapper.findHistoryAll(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageUtil.seacheInfoError);
        }
        return memberPaymentHistoryList;
    }

    /**
     * 保存会员支付历史信息
     * @param memberPaymentHistory
     */
    @Override
    public void saveMemberPaymentHistory(T_Data_Member_Payment_History memberPaymentHistory) {
        try {
            memberPaymentHistoryDaoMapper.saveMemberPaymentHistory(memberPaymentHistory);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.saveInfoError);
        }
    }

    /**
     * 更新会员支付历史信息
     * @param memberPaymentHistory
     */
    @Override
    public void updateMemberPaymentHistory(T_Data_Member_Payment_History memberPaymentHistory) {

        try {
            memberPaymentHistoryDaoMapper.updateMemberPaymentHistory(memberPaymentHistory);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.saveInfoError);
        }
    }

    /**
     * 根据绑定编号删除会员支付历史信息
     * @param history_number
     */
    @Override
    public void delMemberPaymentHistory(String history_number) {

        try {
            memberPaymentHistoryDaoMapper.delMemberPaymentHistory(history_number);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(MessageUtil.delInfoError);
        }
    }
}
