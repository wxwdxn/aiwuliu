package com.cn.gazelle.logistics.service.impl;


import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.DebitCardPayService;
import com.cn.gazelle.logistics.util.*;
import com.cn.gazelle.logistics.util.rbUtil.config.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能描述:银行卡支付
 * Created by zf on 2016/12/15.
 */

@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.DebitCardPayService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class DebitCardPayServiceImpl implements DebitCardPayService {

    Logger logger = Logger.getLogger(DebitCardPayServiceImpl.class);

    @Resource
    private MemberDaoMapper memberDaoMapper;

    @Resource
    private PersonDaoMapper personDaoMapper;

    @Resource
    private MemberBankAccountDaoMapper memberBankAccountDaoMapper;

    @Resource
    private DicdataDaoMapper dicdataDaoMapper;

    @Resource
    private SysInfoDaoMapper  sysInfoDaoMapper;

    @Resource
    private MemberPaymentHistoryDaoMapper memberPaymentHistoryDaoMapper;



    /**
     * 方法描述:绑定银行卡(正式)
     * @param card_no            银行卡号
     * @param mobile_phone       预留手机号
     * @param member_name        会员名
     * @param bank_code          银行代码
     * @return
     */
    @Override
    public String debitIdentify(String card_no, String mobile_phone, String member_name, String bank_code) {

        String ecode = null;
        Map result = new HashMap();
        String res = null;
        T_Data_Member_Bank_Account memberBankAccount = new T_Data_Member_Bank_Account();
        try {
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
            String member_id = member.getMember_id();
            String person_name = person.getPerson_name();
            String id_card_number = person.getId_card_number();
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", ReapalConfig.merchant_id);
            map.put("version", ReapalConfig.identify_version);
            map.put("card_no", card_no);    //银行卡号
            map.put("owner", person_name);
            map.put("cert_type", "01"); //默认
            map.put("cert_no", id_card_number); //身份证号
            map.put("phone", mobile_phone);
            map.put("identify_id", ReapalUtil.getUUID());
            //请求后缀接口地址
            String url = "/fast/identify";
            //返回结果
            String post = ReapalSubmit.identifyBuildSubmit(map, url);
            // System.out.println("返回结果post==========>" + post);
            logger.info(post);
            // 解密返回的数据
            res = Decipher.decryptData(post);

            //将json字符串转换成json对象,获取相应的key值
            JSONObject obj = new JSONObject(res);
            String result_code = (String) obj.get("result_code");

            logger.info(result_code);
            if (result_code.equals("0000")) {  //认证成功
                ecode = "1000";
                result.put("ecode", ecode);
                memberBankAccount.setMember_id(member_id);
                memberBankAccount.setBank_id(bank_code);
                memberBankAccount.setBank_account(card_no);
                memberBankAccount.setBank_save_mobile_phone(mobile_phone);
                memberBankAccount.setBind_id("0");
                memberBankAccount.setDelete_flag("0");
                memberBankAccount.setLast_update(DateUtil.getDate());
                memberBankAccount.setLast_update_user_id("M:" + member_name);
                memberBankAccountDaoMapper.saveMemberBankAccount(memberBankAccount);//存入数据库
            } else {                             //认证失败
                ecode = "1001";                 //输入信息不匹配
                result.put("ecode", ecode);
            }
            logger.info(res);
            System.out.println("解密返回的数据==========>" + res);
            logger.info("执行identify方法");
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode", ecode);
        }
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法描述:实名认证
     * @param cert_no
     * @param personnName
     * @return
     */

    public String personIdentify(String cert_no, String personnName) {

        String ecode = null;
        Map result = new HashMap();
        String res = null;
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", ReapalConfig.merchant_id);
            map.put("version", ReapalConfig.identify_version);
            map.put("owner", personnName);
            map.put("cert_type", "01"); //默认
            map.put("cert_no",cert_no);
            map.put("identify_id", ReapalUtil.getUUID());
            //请求后缀接口地址
            String url = "/fast/realname";
            //返回结果
            String post = ReapalSubmit.identifyBuildSubmit(map, url);
            // System.out.println("返回结果post==========>" + post);
            logger.info(post);
            // 解密返回的数据
            res = Decipher.decryptData(post);

            //将json字符串转换成json对象,获取相应的key值
            JSONObject obj = new JSONObject(res);
            String result_code = (String) obj.get("result_code");

            logger.info(result_code);
            if (result_code.equals("0000")) {  //认证成功
                ecode = "1000";
                result.put("ecode", ecode);
            } else {                             //认证失败
                ecode = "1001";                 //输入信息不匹配
                result.put("ecode", ecode);
            }
            logger.info(res);
            System.out.println("解密返回的数据==========>" + res);
            logger.info("执行identify方法");
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode", ecode);
        }
        return JSONUtil.toJSONString(result);
    }





    /**
     * 方法描述:根据会员名查询平台下绑卡列表信息
     *
     * @param member_name  会员名
     * @return
     */
    @Override
    public String searchList(String member_name) {
        List bankList = new ArrayList();
        String ecode = null;
        Map result = new HashMap();
        try {
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            String member_id = member.getMember_id();
            List<T_Data_Member_Bank_Account> memberBankAccountList = memberBankAccountDaoMapper.findAll(member_id);
            if(!memberBankAccountList.isEmpty()&& memberBankAccountList!=null ){
                for (T_Data_Member_Bank_Account memberBankAccount : memberBankAccountList) {
                    Map<String, String> bankMap = new HashMap<String, String>();
                    String bank_code = memberBankAccount.getBank_id();
                    String bank_account = memberBankAccount.getBank_account();
                    String bank_save_mobile_phone = memberBankAccount.getBank_save_mobile_phone();
                    String bind_id = memberBankAccount.getBind_id();
                    String account_no = memberBankAccount.getAccount_no();
                    int bankAccountLenth = bank_account.length();
                    int lastIndex = bankAccountLenth - 4;
                    String card_last = bank_account.substring(lastIndex, bankAccountLenth);        //卡号后四位
                    String card_top = bank_account.substring(0, 6);                               //卡号前六位
                    List<T_Sys_Dicdata> bank_nameList = dicdataDaoMapper.findAllDicdataByID("40C440A05FD14E7CA187B00BD9EDEE21", bank_code);
                    T_Sys_Dicdata bank = bank_nameList.get(0);
                    String bank_name = bank.getDicdata_name();
                    bankMap.put("bank_card_type", "0");
                    bankMap.put("bank_name", bank_name);
                    bankMap.put("bank_code", bank_code);
                    bankMap.put("bind_id", bind_id);
                    bankMap.put("card_last", card_last);
                    bankMap.put("card_top", card_top);
                    bankMap.put("phone", bank_save_mobile_phone);
                    bankMap.put("account_no",account_no);
                    bankList.add(bankMap);
                }
            }//else{
            //  bankList.add("");
            // }
            ecode = "1000";                        //查询成功
            result.put("ecode", ecode);
            result.put("bind_card_list", bankList);
        } catch (Exception e) {
            result.put("ecode", "2000");             //查询失败

        }
        logger.info(JSONUtil.toJSONString(result));
        return JSONUtil.toJSONString(result);
    }


    /**
     * 方法描述:根据会员名查询融宝绑定银行卡列表
     * @param member_name     会员名
     * @return
     */
    @Override
    public String searchDebitList(String member_name) {
        String ecode = null;
        Map result = new HashMap();
        String res = null;
        try {
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            String member_id = member.getMember_id();
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", ReapalConfig.merchant_id);
            map.put("version", ReapalConfig.identify_version);
            map.put("member_id", member_id);
            map.put("bank_card_type", "0");//0 储蓄卡  1 信用卡
            //请求接口
            String url = "/fast/bindcard/list";
            //返回结果
            String post = ReapalSubmit.identifyBuildSubmit(map, url);
            System.out.println("返回结果post==========>" + post);
            //解密返回的数据
            res = Decipher.decryptData(post);
            Gson gson = new Gson();
            Map<String, Object> data = gson.fromJson(res,new TypeToken<Map<String, Object>>(){}.getType());
            List<Map<String, String>> list = (List<Map<String, String>>) data.get("bind_card_list");
            System.out.println("解密返回的数据==========>" + res);
            logger.info(res);
            logger.info(list);

            result.put("bind_card_list", list);
            result.put("ecode", "1000");        //查询列表成功
            logger.info(result);
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode", ecode);
        }
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法描述:银行卡代付接口
     * @param member_name 会员名
     * @param trans_time  交易时间
     * @param batch_no
     * @param batch_count
     * @param pay_type
     * @param batch_amount
     * @param bankJson
     * @return
     */
    @Override
    public String debitAgentPay(String member_name, String trans_time, String batch_no, String batch_count, String pay_type, String batch_amount, String bankJson) {
        String res = null;
        String ecode = null;
        Map result = new HashMap();
        // Json解析
        Gson gson = new Gson();
        Type typeClass = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> bankMap = gson.fromJson(bankJson, typeClass);
        try {
            String url = "/agentpay/agentpay/pay";
            String content = bankMap.get("order_no") + bankMap.get("card_no") + bankMap.get("open_name") + bankMap.get("bank")
                    + bankMap.get("branch_bank") + bankMap.get("subbranch_bank") + bankMap.get("withdraw_type") + bankMap.get("withdraw _money")
                    + bankMap.get("money_type") + bankMap.get("province") + bankMap.get("city") + bankMap.get("mobile_phone") + bankMap.get("cert_type") + bankMap.get("cert_no");
            //String content1 = "1,62220215080205389633,jack-cooper,工商银行,分行,支行,私,0.01,CNY,北京,北京,18910116131,身份证,420321199202150718,0001,12306,hehe";
            Map<String, String> map = new HashMap<String, String>(0);
            map.put("charset", ReapalConfig.charset);
            map.put("trans_time", trans_time);
            map.put("notify_url", "http://localhost:9020/agent-client/test/rpresult");
            map.put("batch_no", batch_no);
            map.put("batch_count", batch_count);
            map.put("batch_amount", batch_amount);
            map.put("pay_type", pay_type);
            map.put("content", content);
            // 生成签名
            String post = ReapalSubmit.agentPayBuildSubmit(map, url);
            res = Decipher.decryptData(post);
            //将json字符串转换成json对象,获取相应的key值
            JSONObject obj = new JSONObject(res);
            String result_code = (String) obj.get("result_code");
            logger.info(result_code);
            if (result_code.equals("0000")) {  //提交成功
                ecode = "1000";
                result.put("ecode", ecode);
            } else {                             //提交失败
                ecode = "1001";                 //输入信息不匹配
                result.put("ecode", ecode);
            }
            logger.info(res);
            System.out.println("解密返回的数据==========>" + res);
            logger.info("执行agentpay方法");
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode", ecode);
        }
        return JSONUtil.toJSONString(result);
    }


    /**
     * 方法描述:银行卡签约(正式)
     * @param member_name  会员名
     * @param debitJson    json格式数据
     * @param account_no   绑卡编号
     * @return
     */
    @Override
    public String debitSign(String member_name,String debitJson,String account_no) {
        String res = null;
        String ecode = null;
        Map result = new HashMap();
        logger.info(debitJson);
        // Json解析
        Gson gson = new Gson();
        Type typeClass = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> debitMap = gson.fromJson(debitJson, typeClass);
        try {
            T_Data_Member_Bank_Account memberBankAccount = memberBankAccountDaoMapper.findMemberBankAccountByAccountNo(account_no);
            String card_no = memberBankAccount.getBank_account();
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
            String member_id = member.getMember_id();
            String person_name = person.getPerson_name();
            String id_card_number = person.getId_card_number();
            String phone = person.getPerson_mobile_phone();
           // Double  total = Double.parseDouble(debitMap.get("total_fee"));
            BigDecimal bd1 = new BigDecimal(debitMap.get("total_fee"));
            logger.info("你输入的金额 单位:元"+bd1);
            BigDecimal bd2 = new BigDecimal(String.valueOf(100));
            System.out.println(bd1.multiply(bd2).intValue());
            int fee = bd1.multiply(bd2).intValue();
            System.out.println("转换之后的金额:"+fee);
            String total_fee =  Integer.toString(fee);
            logger.info("total_fee(传到融宝的金额 单位:分)="+total_fee);
            String order_no = RadomUtil.getOrderNo();
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", ReapalConfig.merchant_id);
            map.put("version", ReapalConfig.identify_version);
            map.put("card_no",card_no);
            map.put("owner", person_name);
            map.put("cert_type", "01"); // 默认
            map.put("cert_no",id_card_number);
            map.put("phone", phone);
            map.put("order_no",order_no);
            map.put("transtime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put("currency", "156"); // 默认
            map.put("title", debitMap.get("title"));
            map.put("body", debitMap.get("body"));
            map.put("member_id", member_id);// 用户id
            map.put("terminal_type", debitMap.get("terminal_type"));// 终端类型
            map.put("terminal_info", debitMap.get("terminal_info")); // 终端标识
            map.put("notify_url", ReapalConfig.notify_url);
            map.put("member_ip",debitMap.get("member_ip"));// 用户IP
            map.put("seller_email", ReapalConfig.seller_email);
            map.put("total_fee", total_fee);// 金额
            map.put("token_id", debitMap.get("token_id"));
            logger.info(map);
            // 请求后缀接口地址
            String url = "/fast/debit/portal";
            // 返回结果
            String post = ReapalSubmit.identifyBuildSubmit(map, url);
            logger.info(map);
            System.out.println("返回结果post==========>" + post);
            // 解密返回的数据
            res = Decipher.decryptData(post);
            System.out.println("解密返回的数据==========>" + res);
            //将json字符串转换成json对象,获取相应的key值
            JSONObject obj = new JSONObject(res);
            String result_code = (String) obj.get("result_code");
            String bind_id = (String) obj.get("bind_id");
            logger.info(result_code);
            if (result_code.equals("0000")) {  //银行卡签约成功
                memberBankAccount.setBind_id(bind_id);
                // memberBankAccount.setBank_account(debitMap.get("card_no"));
                // memberBankAccount.setMember_id(member_id);
                memberBankAccountDaoMapper.updateMemberBankAccountByMemberId(memberBankAccount);
                ecode = "1000";
                result.put("ecode", ecode);
                result.put("orderNo",order_no);
            } else {                             //银行卡签约失败
                ecode = "1001";
                result.put("ecode", ecode);
            }
            logger.info(res);
            System.out.println("解密返回的数据==========>" + res);
            logger.info("执行debitSign方法");
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode", ecode);
        }
        return JSONUtil.toJSONString(result);
    }


    /**
     * 方法描述:绑卡签约(正式)
     * @param member_name   会员名
     * @param debitJson     json格式数据
     * @return
     */
    @Override
    public String bindCard(String member_name,String debitJson,String account_no) {
        String res = null;
        String ecode = null;
        Map result = new HashMap();
       // T_Data_Member_Bank_Account memberBankAccount = new T_Data_Member_Bank_Account();
        T_Data_Member_Bank_Account memberBankAccount = null;
        // Json解析
        Gson gson = new Gson();
        Type typeClass = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> debitMap = gson.fromJson(debitJson, typeClass);
        try {
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
            String member_id = member.getMember_id();
           // memberBankAccount =  memberBankAccountDaoMapper.findMemberBankAccountByBankAccountAndMemberId(debitMap.get("card_no"),member_id);
            memberBankAccount = memberBankAccountDaoMapper.findMemberBankAccountByAccountNo(account_no);
            String bind_id = memberBankAccount.getBind_id();
            String order_no = RadomUtil.getOrderNo();
            BigDecimal bd1 = new BigDecimal(debitMap.get("total_fee"));
            logger.info("你输入的金额 单位:元"+bd1);
            BigDecimal bd2 = new BigDecimal(String.valueOf(100));
            System.out.println(bd1.multiply(bd2).intValue());
            int fee = bd1.multiply(bd2).intValue();
            logger.info("转换之后的金额 单位:分"+fee);
            String total_fee =  Integer.toString(fee);
            logger.info("total_fee(传到融宝的金额 单位:分)="+total_fee);
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", ReapalConfig.merchant_id);
            map.put("version", ReapalConfig.identify_version);
            map.put("member_id", member_id);
            map.put("bind_id", bind_id);
            map.put("order_no",order_no);
            map.put("transtime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put("currency", "156"); // 默认
            map.put("title",  debitMap.get("title"));
            map.put("body", debitMap.get("body"));
            map.put("terminal_type", debitMap.get("terminal_type"));// 终端类型
            map.put("terminal_info", debitMap.get("terminal_info")); // 终端标识
            map.put("notify_url", ReapalConfig.notify_url);
            map.put("member_ip", debitMap.get("member_ip"));// 用户IP
            map.put("seller_email", ReapalConfig.seller_email);
            map.put("total_fee", total_fee);    //金额
            map.put("token_id",  debitMap.get("token_id"));
            // 请求接口
            String url = "/fast/bindcard/portal";
            // 返回的数据
            String post = ReapalSubmit.identifyBuildSubmit(map, url);
            System.out.println("返回结果post==========>" + post);
            // 解密返回的数据
            res = Decipher.decryptData(post);
            System.out.println("解密返回的数据==========>" + res);
            //将json字符串转换成json对象,获取相应的key值
            JSONObject obj = new JSONObject(res);
            String result_code = (String) obj.get("result_code");
            logger.info(result_code);
            if (result_code.equals("0000")) {  //绑卡签约成功
                ecode = "1000";
                result.put("ecode", ecode);
                result.put("orderNo",order_no);
            } else {                           //绑卡签约失败
                ecode = "1001";
                result.put("ecode", ecode);
            }
            logger.info(res);
            System.out.println("解密返回的数据==========>" + res);
            logger.info("执行bindCard方法");
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode", ecode);
        }
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法描述:确认支付(正式)
     * @param member_name    会员名
     * @param order_no       订单号
     * @param check_code     验证码
     * @param amount         支付金额
     * @param account_no     绑卡编号
     * @return
     */
    @Override
    public  String  confirmPay(String member_name,String order_no,String check_code,String amount,String account_no)  {
        String res = null;
        String ecode = null;
        Map result = new HashMap();
        T_Data_Member_Payment_History memberPaymentHistory = new T_Data_Member_Payment_History();
        try {
            T_Data_Member  member = memberDaoMapper.findMemberByName(member_name);
            String member_id = member.getMember_id();
            T_Data_Member_Bank_Account memberBankAccount = memberBankAccountDaoMapper.findMemberBankAccountByAccountNo(account_no);
            String bank_account  = memberBankAccount.getBank_account();
            String target_id = TargetIDUtil.getTargetID(account_no);
         //   Double memberAmount = member.getMember_account_amount(); //获取会员账户资金量
            Double payAmount = Double.parseDouble(amount);           //支付金额
           // Double amountCount = memberAmount + payAmount;
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", ReapalConfig.merchant_id);
            map.put("version", ReapalConfig.identify_version);
            map.put("order_no",order_no);
            map.put("check_code",check_code); //6位
            //请求接口
            String url = "/fast/pay";
            //返回结果
            String post = ReapalSubmit.identifyBuildSubmit(map,url);
            System.out.println("返回结果post==========>" + post);
            //解密返回的数据
            res = Decipher.decryptData(post);
            System.out.println("解密返回的数据==========>" + res);
            //将json字符串转换成json对象,获取相应的key值
            JSONObject obj = new JSONObject(res);
            String result_code = (String) obj.get("result_code");
            logger.info(result_code);
            if (result_code.equals("0000")) {  //成功
//                member.setMember_account_amount(amountCount);  //更新会员信息表
//                member.setLast_update(DateUtil.getDate());
//                memberDaoMapper.updateMember(member);
                memberPaymentHistory.setAmount(payAmount);    //插入支付数据到会员支付历史信息表
                memberPaymentHistory.setMember_id(member_id);
                memberPaymentHistory.setCreate_time(DateUtil.getDate());
                memberPaymentHistory.setDelete_flag("0");
                memberPaymentHistory.setLast_update(DateUtil.getDate());
                memberPaymentHistory.setLast_update_user_id("M"+member_name);
                memberPaymentHistory.setPayment_result("2");
                memberPaymentHistory.setReturn_result("0");
                memberPaymentHistory.setPayment_type("0");
                memberPaymentHistory.setTarget_bank_account(bank_account);
                memberPaymentHistory.setTarget_id(target_id);
                memberPaymentHistory.setThird_party_order_id(order_no);
                memberPaymentHistoryDaoMapper.saveMemberPaymentHistory(memberPaymentHistory);
                ecode = "1000";
                result.put("ecode", ecode);
                result.put("time",DateUtil.date2HourString(DateUtil.getDate()));
            } else {                           //失败
                ecode = "1001";
                result.put("ecode", ecode);
                result.put("time",DateUtil.date2HourString(DateUtil.getDate()));

            }
            logger.info(res);
            System.out.println("解密返回的数据==========>" + res);
            logger.info("执行confirmPay方法");
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode", ecode);
            result.put("time",DateUtil.date2HourString(DateUtil.getDate()));
        }
        return JSONUtil.toJSONString(result);
    }

    /**
     * 方法描述:获取验证码
     * @param member_name  会员名
     * @param debitJson    json格式数据
     * @param account_no   绑卡编号
     * @return
     */
    @Override
    public String identifyDifferent(String member_name,String debitJson,String account_no ){
        String dataJson = null;
        String ecode = null;
        Map result = new HashMap();
        try {
            String dlDebitJson = searchList(member_name);      //获取项目平台下绑定的银行卡列表
            String rbDebitJson = searchDebitList(member_name); //获取融宝下绑定的银行卡列表
            Gson gson = new Gson();
            Map<String, Object> rbData = gson.fromJson(rbDebitJson, new TypeToken<Map<String, Object>>() {}.getType());
            List<Map<String,String>> rbDebitList = (List<Map<String, String>>) rbData.get("bind_card_list");
            logger.info(rbDebitList);
            if(rbDebitList!=null&&!rbDebitList.isEmpty() && "1".equals( identifyDebitList( member_name,account_no))){//项目平台下的银行卡存在于融宝绑卡列表中
                dataJson =  bindCard(member_name,debitJson,account_no);
            }else{                                                                     //项目平台下的银行卡不存在于融宝绑卡列表中
                dataJson =  debitSign(member_name,debitJson,account_no);
            }
        } catch (JsonSyntaxException e) {
            ecode = "2000";       // 系统异常
            result.put("ecode",ecode);
            dataJson = JSONUtil.toJSONString(result);
        }
        return dataJson;
    }

    /**
     * 方法描述:当融宝银行卡列表和平台下银行卡不为空时,比较项目平台下的银行卡是否在融宝银行卡列表中
     * @param member_name  会员名
     * @param account_no   绑卡编号
     * @return
     */

    public String  identifyDebitList(String member_name,String account_no){
        String flag = null;
        String rbDebitJson = searchDebitList(member_name); //获取融宝下绑定的银行卡列表
        Gson gson = new Gson();
        Map<String, Object> rbData = gson.fromJson(rbDebitJson, new TypeToken<Map<String, Object>>() {}.getType());
        List<Map<String,String>> rbDebitList = (List<Map<String, String>>) rbData.get("bind_card_list");
        logger.info(rbDebitList);
        if(!account_no.equals("")&&account_no!=null){
            T_Data_Member_Bank_Account memberBankAccount = memberBankAccountDaoMapper.findMemberBankAccountByAccountNo(account_no);
            String dl_bind_id = memberBankAccount.getBind_id();
            for (Map<String,String> rbDebit :rbDebitList) {
                String rb_bind_id = rbDebit.get("bind_id");
                if (dl_bind_id.equals(rb_bind_id)){
                    flag = "1";
                }else{
                    flag = "0";
                }
            }
        }else{
            flag="0";
        }
        return flag;
    }

    /**
     * 方法描述:重新发送短信
     * @param order_no    订单号
     * @return
     */
    public   String  reSms(String order_no){
        String res = null;
        String ecode = null;
        Map result = new HashMap();
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id", ReapalConfig.merchant_id);
            map.put("version", ReapalConfig.identify_version);
            //订单号
            map.put("order_no",order_no);
            //请求接口
            String url = "/fast/sms";
            //返回结果
            String post = ReapalSubmit.identifyBuildSubmit(map,url);
            System.out.println("返回结果post==========>" + post);
            //解密返回的数据
            res = Decipher.decryptData(post);
            System.out.println("解密返回的数据==========>" + res);
            JSONObject obj = new JSONObject(res);
            String result_code = (String) obj.get("result_code");
            if (result_code.equals("0000")){
                ecode = "1000";
                result.put("ecode",ecode); //发送成功
            }else{
                ecode = "1001";        //发送失败
                result.put("ecode",ecode);
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode",ecode); //系统异常
        }
        return JSONUtil.toJSONString(result);
    }


    /**
     * 方法描述:判断支付密码
     * @param member_name
     * @param password
     * @return
     */
    public String identifyUtil(String member_name,String password ){
        String  lastPayDate;
        String ecode = null;
        Map result = new HashMap();
        try {
            password = MD5Util.md5(password);
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            String paymentPassword = member.getPayment_password();         //获取支付密码
            Date lastPayingDate  = member.getLast_paying_date();              //获取最新进行支付日期
            if(lastPayingDate == null){     //最新进行支付日期为空
                lastPayDate = "";
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                lastPayDate = sdf.format(lastPayingDate);
            }
            String todayErrorCount1 = member.getCurrent_day_error_payment_password_commit_count();//获取同天支付密码错误次数
            int todayErrorCount = Integer.parseInt(todayErrorCount1);
            T_Data_Sys_Info sysInfo = sysInfoDaoMapper.findSysInfo();
            String  errorPaymentPasswordMaxTime1 = sysInfo.getError_payment_password_max_time(); //获取支付密码每天可接受错误次数
            int errorPaymentPasswordMaxTime = Integer.parseInt(errorPaymentPasswordMaxTime1);
            Date sysDate1 = DateUtil.getDate();                  //获取系统日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String  sysDate = sdf.format(sysDate1);
            if(!"".equals(lastPayDate) && lastPayDate.equals(sysDate)){ //日期相同

                if(todayErrorCount < errorPaymentPasswordMaxTime || (todayErrorCount==0 && errorPaymentPasswordMaxTime==0 ) ){
                    //二者次数不同或同时等于0
                    if(paymentPassword.equals(password)){//支付密码通过检测
                        member.setCurrent_day_error_payment_password_commit_count("0");
                        memberDaoMapper.updateMember(member);
                        ecode="2003";
                        result.put("ecode",ecode);

                    }else{                               //支付密码未通过检测
                        todayErrorCount=todayErrorCount+1;
                        String todayCount =  String.valueOf(todayErrorCount);
                        member.setCurrent_day_error_payment_password_commit_count(todayCount);
                        ecode = "2002";
                        result.put("ecode",ecode);       //密码错误
                    }
                }else{
                    //二者次数相同且不等于0
                    ecode="2001";
                    result.put("ecode",ecode);
                    result.put("errorCount",errorPaymentPasswordMaxTime1);
                }
            }else{                                            //日期不同
                member.setCurrent_day_error_payment_password_commit_count("0");
                member.setLast_paying_date(sysDate1);
                memberDaoMapper.updateMember(member);
            }
        } catch (NumberFormatException e) {
            ecode="2000";                                  //系统异常
            result.put("ecode",ecode);
        }
        return JSONUtil.toJSONString(result);
    }

    /**
     *方法描述:招行银行卡卡密鉴权
     * @param member_name  会员名
     * @param card_no      银行卡账号
     * @param order_no     订单编号
     * @param terminal_type  终端类型
     * @return
     */

    public String certificate(String member_name,String card_no,String order_no,String terminal_type){
        String key = ReapalConfig.key;
        String res = null;
        String ecode = null;
        Map<String, String> resultMap = null;
        try {
            resultMap = new HashMap<String, String>();
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            String member_id = member.getMember_id();
            T_Data_Member_Bank_Account memberBankAccount =  memberBankAccountDaoMapper.findMemberBankAccountByBankAccountAndMemberId(card_no,member_id);
            String bind_id = memberBankAccount.getBind_id();
            String merchant_id = ReapalConfig.merchant_id;
            Map<String, String> map = new HashMap<String, String>();
            map.put("merchant_id",merchant_id);
            map.put("terminal_type", terminal_type);
            map.put("order_no", order_no);
            map.put("bind_id", bind_id);
            map.put("member_id", member_id);
            map.put("return_url", ReapalConfig.certify_return_url);
            map.put("notify_url", ReapalConfig.certify_notify_url);
            map.put("version", ReapalConfig.identify_version);
            String url = "/fast/certificate";
            String post = ReapalSubmit.identifyBuildSubmit(map,url);
            res = Decipher.decryptData(post);
            JSONObject jsonObject = new JSONObject(res);
            //System.out.println("jsonObjectjsonObjectjsonObjectjsonObject========" + jsonObject);
            String bank_card_type = jsonObject.getString("bank_card_type");
            String bank_code = jsonObject.getString("bank_code");
            String bank_name = jsonObject.getString("bank_name");
            bind_id = jsonObject.getString("bind_id");
            String card_last = jsonObject.getString("card_last");
            member_id = jsonObject.getString("member_id");
            merchant_id = jsonObject.getString("merchant_id");
            order_no = jsonObject.getString("order_no");
            String phone = jsonObject.getString("phone");
            String result_code = jsonObject.getString("result_code");
            String result_msg = jsonObject.getString("result_msg");
            String total_fee = jsonObject.getString("total_fee");
            String trade_no = jsonObject.getString("trade_no");
            String sign = jsonObject.getString("sign");
            if(result_code=="0000"){
                resultMap.put("bank_card_type", bank_card_type);
                resultMap.put("bank_code", bank_code);
                resultMap.put("bank_name", bank_name);
                resultMap.put("bind_id",  bind_id);
                resultMap.put("card_last", card_last);
                resultMap.put("member_id", member_id);
                resultMap.put("merchant_id", merchant_id);
                resultMap.put("order_no", order_no);
                resultMap.put("phone", phone);
                resultMap.put("result_code", result_code);
                resultMap.put("result_msg", result_msg);
                resultMap.put("total_fee", total_fee);
                resultMap.put("trade_no", trade_no);
                resultMap.put("ecode","1000");  //成功
            }else{
                resultMap.put("ecode","1001");  //失败
            }
        } catch (Exception e) {
            resultMap.put("ecode","2000");     //异常
        }
        return JSONUtil.toJSONString(resultMap);
    }


    /**
     * 方法描述:取消绑卡
     * @param member_name 会员名
     * @param account_no  绑卡编号
     * @return
     */
    @Override
    public String cannelDebit(String member_name,String account_no ) {
        String res = null;
        String ecode = null;
        Map result = new HashMap();

        try {
            T_Data_Member_Bank_Account memberBankAccount = memberBankAccountDaoMapper.findMemberBankAccountByAccountNo(account_no);
            String  bind_id = memberBankAccount.getBind_id();
            if("0".equals(bind_id)){                              //平台下的银行卡不在融宝平台下解绑卡
//                memberBankAccountDaoMapper.delMemberBankAccountByBindId(bind_id);
                memberBankAccountDaoMapper.delMemberBankAccount(account_no);
                ecode = "1000";
                result.put("ecode",ecode);
            }else {                                               //平台下的银行卡在融宝平台下解绑卡
                Map<String, String> map = new HashMap<String, String>();
                map.put("merchant_id", ReapalConfig.merchant_id);
                map.put("version", ReapalConfig.identify_version);
                map.put("member_id", member_name);
                map.put("bind_id", bind_id);
                //请求接口
                String url = "/fast/cancle/bindcard";
                //返回的数据
                String post = ReapalSubmit.identifyBuildSubmit(map,url);
                System.out.println("返回结果post==========>" + post);
                //解密返回的数据
                res = Decipher.decryptData(post);
                System.out.println("解密返回的数据==========>" + res);
                JSONObject obj = new JSONObject(res);
                String result_code =(String)obj.get("result_code");
                logger.info(result_code);
                if (result_code.equals("0000")){  //解绑成功
                    memberBankAccountDaoMapper.delMemberBankAccountByBindId(bind_id);
                    ecode = "1000";
                    result.put("ecode",ecode);
                }else{                             //解绑失败
                    ecode = "1001";
                    result.put("ecode",ecode);
                }
                logger.info(res);
                System.out.println("解密返回的数据==========>" + res);
                logger.info("执行cannelDebit方法");
            }
        } catch (Exception e) {
            ecode = "2000";                     //系统错误
            result.put("ecode",ecode);
        }
        return JSONUtil.toJSONString(result);
    }
}




