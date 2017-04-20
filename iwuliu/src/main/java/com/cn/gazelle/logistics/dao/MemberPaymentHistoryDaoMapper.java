package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Member_Payment_History;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zf on 2016/12/12.
 */
public interface MemberPaymentHistoryDaoMapper {

    //根据绑定编号查询会员支付历史信息
    T_Data_Member_Payment_History findMemberPaymentHistoryByAccountNo(String history_number);

    //根据第三方订单Id查询会员支付历史信息
    T_Data_Member_Payment_History findMemberPayMentHistoryByOrderNo(String order_no);

    //根据会员Id查询会员支付历史信息
    List<T_Data_Member_Payment_History> findAll(String member_id);



    //保存会员支付历史信息
    void saveMemberPaymentHistory(T_Data_Member_Payment_History memberPaymentHistory);

    //更新会员支付历史信息
    void updateMemberPaymentHistory(T_Data_Member_Payment_History memberPaymentHistory);

    //根据绑定编号删除会员支付历史信息
    void delMemberPaymentHistory(String history_number);

    //根据时间范围查询会员支付历史信息
    public List<T_Data_Member_Payment_History> findHistoryAll(HashMap<String, String> conditions);

    // 根据会员关联Id和产生时间查询会员支付历史信息
    List<T_Data_Member_Payment_History> findMemberPaymentHistoryByIDAndDate(@Param(value = "member_id") String member_id,@Param(value = "date") String date);
}
