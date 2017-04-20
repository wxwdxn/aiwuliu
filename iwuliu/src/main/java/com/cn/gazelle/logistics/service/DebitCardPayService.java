package com.cn.gazelle.logistics.service;

import javax.jws.WebService;


@WebService
public interface DebitCardPayService {
    /**
     * 方法描述:绑定银行卡
     * @param card_no
     * @param person_mobile_phone
     * @param member_name
     * @param bank_code
     * @return
     */
   // String  debitIdentify( );
    String  debitIdentify(String card_no,String person_mobile_phone,String member_name,String bank_code);

    //实名认证
    String personIdentify(String cert_no, String personnName);

    /**
     *方法描述:根据会员名查询平台下绑定银行卡列表信息
     * @param member_name
     * @return
     */
    String searchList(String member_name);
    /**
     * 方法描述:根据会员名查找融宝绑定银行卡列表信息
     * @param member_name
     * @return
     */
    String searchDebitList(String member_name);

    /**
     * 方法描述:银行卡代付接口
     * @param member_name
     * @param trans_time
     * @param batch_no
     * @param batch_count
     * @param pay_type
     * @param batch_amount
     * @param bankJson
     * @return
     */

    String debitAgentPay(String member_name,String trans_time,String batch_no,String batch_count,String pay_type,String batch_amount,String bankJson);

    /**
     * 方法描述:银行卡签约
     * @param member_name
     * @param debitJson
     * @return
     */
    String  debitSign(String member_name,String debitJson,String account_no);

    /**
     * 方法描述:绑卡签约
     * @param member_name
     * @param debitJson
     * @return
     */
    String  bindCard(String member_name,String debitJson,String account_no);

    /**
     * 确认支付
     * @param member_name
     * @param order_no
     * @param check_code
     * @return
     */

    String  confirmPay(String member_name,String order_no,String check_code,String amount,String account_no);

    /**
     * 方法描述:判断项目平台下银行卡是否存在于融宝绑定银行卡列表中(发送验证码)
     * @param member_name
     * @param debitJson
     * @return
     */
    String identifyDifferent(String member_name,String debitJson,String account_no);

    /**
     * 方法描述:重新发送短信
     * @param order_no
     * @return
     */
    String  reSms(String order_no);


    /**
     * 方法描述:取消绑卡
     * @param member_name
     * @param bind_id
     * @return
     */
    String cannelDebit(String member_name,String bind_id);



}
