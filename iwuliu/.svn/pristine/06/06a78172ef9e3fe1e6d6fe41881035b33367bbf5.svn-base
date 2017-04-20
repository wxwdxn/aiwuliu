/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：logisticsAccountManager.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-03-06
 * 作    者: zf
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：logisticsAccountManager
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@author zf
 */
@Controller
@RequestMapping(value = "/accountCenterManager")
public class AccountCenterController {

    Logger logger = Logger.getLogger(AccountCenterController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;


    @Resource
    private CompanyService companyService;

    @Resource
    private MemberService memberService;

    @Resource
    private TruckService truckService;

    @Resource
    private MemberPayService memberPayService;

    @Resource
    private DicdataService dicdataService;

    @Resource
    private TruckPayService truckPayService;

    /**
     * 功能：物流公司账户管理主页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "home")
    public String accountManager_home(ModelMap model) {
        return "operationManager/accountCenter/accountCenter";
    }

    /**
     * 功能：物流公司账户列表
     *
     * @param response
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "accountList")
    public void accountList(@RequestParam(required = false, defaultValue = "") String account_type,
                            @RequestParam(required = false, defaultValue = "") String account_name,
                            @RequestParam(required = false, defaultValue = "") String start_time,
                            @RequestParam(required = false, defaultValue = "") String end_time,
                            @RequestParam(required = false, defaultValue = "") String account_dept,
                            HttpServletResponse response, HttpServletRequest request) {

        System.out.println("account_type" + account_type);                                  //账户类型
        System.out.println("account_name" + account_name);                                  //账户名
        System.out.println("start_time" + start_time);
        System.out.println("end_time" + end_time);
        System.out.println("account_dept" + account_dept);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String member_name = request.getSession().getAttribute("username").toString();
        logger.info(member_name);
        List<T_Data_Company_AccountInfo> companyAccountInfos = null;
        List<T_Data_Truck_AccountInfo> truckAccountInfos = null;
        try {
            T_Data_Member member = memberService.findMemberByName(member_name);
            String member_id = member.getMember_id();                                  //会员id
//            List<T_Data_Member_Bank_Account> memberBankAccounts  = memberPayService.findAll(member_id);
            String company_id = member.getRelevance_info_id();                         //公司id

            if ("车主账户".equals(account_type)) {
                HashMap<String, String> conditions = new HashMap<String, String>();
//                for (T_Data_Member_Bank_Account memberBankAccount:memberBankAccounts){     //遍历银行账户
//                    Map<String,String> companyInfo  =  new HashMap<String,String>();
//                    String bank_id = memberBankAccount.getBank_id();                       //银行id
//                    List<T_Sys_Dicdata> bank = dicdataService.findAllDicdataByCode( "40C440A05FD14E7CA187B00BD9EDEE21",bank_id);
//                    T_Sys_Dicdata dicdata = bank.get(0);
//                    String bank_name = dicdata.getDicdata_name();                          //银行名
//                    String bank_no = memberBankAccount.getBank_account();                  //银行账户
                conditions.put("account_name", account_name);
                if(account_dept.equals("0")){
                    conditions.put("company_id", "");
                }else{
                    conditions.put("company_id", account_dept);
                }
                conditions.put("start_time", start_time);
                conditions.put("end_time", end_time);
                BigDecimal income = new BigDecimal(0);
                BigDecimal withdraw = new BigDecimal(0);
                BigDecimal pay = new BigDecimal(0);
                companyAccountInfos = memberPayService.findAccountInfo(conditions);
                if (!companyAccountInfos.isEmpty() && companyAccountInfos.size() != 0) {
                    for (T_Data_Company_AccountInfo dataCompanyAccountInfo : companyAccountInfos) {
                        Map<String, String> companyInfo = new HashMap<String, String>();
                        companyInfo.put("account_name", dataCompanyAccountInfo.getAccount_name());
                        companyInfo.put("account_dept", dataCompanyAccountInfo.getAccount_name());
                        companyInfo.put("account_type", account_type);
                        companyInfo.put("bank_info", dataCompanyAccountInfo.getBank_info());
                        if(dataCompanyAccountInfo.getAccount_money() == null ||Double.toString(dataCompanyAccountInfo.getAccount_money()).equals("")){
                            dataCompanyAccountInfo.setAccount_money(0.0);
                        }
                        companyInfo.put("account_money", String.valueOf(dataCompanyAccountInfo.getAccount_money()));
                        if(dataCompanyAccountInfo.getAccount_income() == null ||Double.toString(dataCompanyAccountInfo.getAccount_income()).equals("")){
                            dataCompanyAccountInfo.setAccount_income(0.0);
                        }
                       // income = income.add(new BigDecimal(Double.toString(dataCompanyAccountInfo.getAccount_income())));

                        if(dataCompanyAccountInfo.getAccount_withdraw() == null || Double.toString(dataCompanyAccountInfo.getAccount_withdraw()).equals("")){
                            dataCompanyAccountInfo.setAccount_withdraw(0.0);
                        }
                       // withdraw = withdraw.add(new BigDecimal(Double.toString(dataCompanyAccountInfo.getAccount_withdraw())));
                        if(dataCompanyAccountInfo.getAccount_pay()== null ||Double.toString(dataCompanyAccountInfo.getAccount_pay()).equals("")){
                            dataCompanyAccountInfo.setAccount_pay(0.0);
                        }
                      //  pay = pay.add(new BigDecimal(Double.toString(dataCompanyAccountInfo.getAccount_pay())));
                        companyInfo.put("account_income",String.valueOf(dataCompanyAccountInfo.getAccount_income()) );
                        companyInfo.put("account_withdraw",String.valueOf(dataCompanyAccountInfo.getAccount_withdraw()));
                        companyInfo.put("account_pay",String.valueOf(dataCompanyAccountInfo.getAccount_pay()));
                        list.add(companyInfo);
                    }
                    ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
                }
            } else if ("车辆账户".equals(account_type)) {
//                T_Data_Company company = companyService.findCompanyByID(company_id);
                HashMap<String, String> conditions = new HashMap<String, String>();
                conditions.put("account_name", account_name);
                if(account_dept.equals("0")){
                    conditions.put("company_id", "");
                }else{
                    conditions.put("company_id", account_dept);
                }
//                conditions.put("company_id", company_id);
                conditions.put("start_time", start_time);
                conditions.put("end_time", end_time);
                truckAccountInfos = truckPayService.findTruckAccountInfo(conditions);
                BigDecimal income = new BigDecimal(0);
                BigDecimal pay = new BigDecimal(0);

                if (!truckAccountInfos.isEmpty() && truckAccountInfos.size() != 0) {
                    for (T_Data_Truck_AccountInfo datatruckAccountInfo : truckAccountInfos) {
                        Map<String, String> truckAccountInfo = new HashMap<String, String>();
                        truckAccountInfo.put("account_name", datatruckAccountInfo.getAccount_name());
                        truckAccountInfo.put("account_type", account_type);
                        truckAccountInfo.put("account_dept", datatruckAccountInfo.getAccount_dept());
                        if(datatruckAccountInfo.getAccount_money() == null || Double.toString(datatruckAccountInfo.getAccount_money()).equals("")){
                            datatruckAccountInfo.setAccount_money(0.0);
                        }

                        if(datatruckAccountInfo.getAccount_income() == null || Double.toString(datatruckAccountInfo.getAccount_income()).equals("")){
                            datatruckAccountInfo.setAccount_income(0.0);
                        }
//                        income = income.add(new BigDecimal(Double.toString(datatruckAccountInfo.getAccount_income())));
                        if(datatruckAccountInfo.getAccount_pay() == null || Double.toString(datatruckAccountInfo.getAccount_pay()).equals("")){
                            datatruckAccountInfo.setAccount_pay(0.0);
                        }
//                        pay = pay.add(new BigDecimal(Double.toString(datatruckAccountInfo.getAccount_pay())));
                        truckAccountInfo.put("account_money", String.valueOf(datatruckAccountInfo.getAccount_money()));
                        truckAccountInfo.put("account_income",String.valueOf(datatruckAccountInfo.getAccount_income()));
                        truckAccountInfo.put("account_pay",String.valueOf(datatruckAccountInfo.getAccount_pay()));
                        list.add(truckAccountInfo);
                    }
                    ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
                }
            }
            logger.info(list);
        } catch (Exception e) {
            logger.info(MessageUtil.seacheInfoError);
            e.printStackTrace();
        }
        ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
    }

    /**
     * 功能：查询公司名
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "searchCompanyName")
    @ResponseBody
    public Map<String, String> searchCompanyName(HttpServletResponse response, HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();
        String member_name = request.getSession().getAttribute("username").toString();
        try {
            T_Data_Member member = memberService.findMemberByName(member_name);
            String company_id = member.getRelevance_info_id();
            T_Data_Company company = companyService.findCompanyDetailByID(company_id);
            String company_name = company.getCompany_name();
            result.put("company_name", company_name);
            result.put("company_id",company_id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(MessageUtil.seacheInfoError);
        }
        logger.info(result);
        return result;
    }

    /**
     * 方法描述：获取物流公司列表信息
     * @return
     */
    @RequestMapping(value = "searchCompanyList")
    @ResponseBody
    public String searchCompanyList(){
       List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        List<T_Data_Company> companyList= null;
        try {
            companyList=companyService.findCompanyList();
            for (T_Data_Company company:companyList) {
                Map<String, String> result = new HashMap<String, String>();
                result.put("company_id",company.getCompany_id());
                result.put("company_name",company.getCompany_name());
                list.add(result);
            }
            logger.info(list);
            logger.info(JSONUtil.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(MessageUtil.seacheInfoError);
        }
        return JSONUtil.toJSONString(list);
    }

}



