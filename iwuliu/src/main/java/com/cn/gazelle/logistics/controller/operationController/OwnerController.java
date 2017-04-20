/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OwnerController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-16
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_Company;
import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Data_Person;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.OwnerCompanyService;
import com.cn.gazelle.logistics.service.OwnerManagerService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类 名 称：OwnerController
 * 内容描述： 个人货主
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/ownerManager")
public class OwnerController {
    Logger logger=Logger.getLogger(OwnerController.class);
    @Resource
    private OwnerManagerService ownerManagerService;

    @Resource
    private OwnerCompanyService ownerCompanyService;


    @Resource
    private DicdataService dicdataService;


    /**
     * 方法名称：ownerManager
     * 内容摘要：货主管理页面。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String ownerManager(ModelMap model) {
        return "operationManager/ownerManager/ownerManager";
    }

    /**
     * 方法名称：ownerManagerDetail
     * 内容摘要：货主详情页面。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "ownerManagerDetail")
    public String ownerManagerDetail(ModelMap model) {
        return "operationManager/ownerManagerDetail/ownerManagerDetail";
    }

    /**
     * 方法名称：ownerManagerNewAdd
     * 内容摘要：货主新增页面。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "ownerManagerNewAdd")
    public String ownerManagerNewAdd(ModelMap model) {
        return "operationManager/ownerManagerNewAdd/ownerManagerNewAdd";
    }

    /**
     * 方法名 称：getOwnerList
     * 内容描述：根据条件查询 货主信息
     * 方法描述：该类有 个方法
     *          01
     *          02
     *@authot WXW
     */
    @RequestMapping(value = "/getOwnerList")
    @Transactional
    public void getOwnerList(@RequestParam(required = false,defaultValue = "") String ownerName,
                             @RequestParam(required = false,defaultValue = "") String person_name,
                             @RequestParam(required = false,defaultValue = "") String id_card_number,
                             @RequestParam(required = false,defaultValue = "") String person_mobile_phone,
                             @RequestParam(required = false,defaultValue = "") String types,
                             HttpServletRequest request, HttpServletResponse response){
//        String parameter = request.getParameter("params");
          List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//        Gson gson=new Gson();
//        Map<String, Object> data = gson.fromJson(parameter, new TypeToken<Map<String, Object>>() {
//        }.getType());
        try {
//            String company_name = (String)data.get("ownerName");
//            String person_name = (String)data.get("person_name");
//            String id_card_number = (String)data.get("id_card_number");
//            String person_mobile_phone = (String)data.get("person_mobile_phone");
//            String types = (String)data.get("types");
            if (types.equals("0")){
                List<T_Data_Person> ownerList = ownerManagerService.findOwnerList(person_name, id_card_number, person_mobile_phone);
                if (ownerList.size()!=0){
                    for (T_Data_Person owner:ownerList){
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        String personMobilePhone = owner.getPerson_mobile_phone();
                        String personName = owner.getPerson_name();
                        String idCardNumber = owner.getId_card_number();
                        String personId = owner.getPerson_id();
                        hashMap.put("personId",personId);
                        hashMap.put("personName",personName);
                        hashMap.put("idCardNumber",idCardNumber);
                        hashMap.put("personMobilePhone",personMobilePhone);
                        //个人货主
                        hashMap.put("type","个人货主");
                        hashMap.put("ownerName","");
                        hashMap.put("companyId","");
                        list.add(hashMap);
                    }
                }
            }else {
                List<T_Data_Company> ownerCompanyList = ownerCompanyService.findOwnerCompanyList(person_name, ownerName, person_mobile_phone);
                if (ownerCompanyList.size()!=0){
                    for (T_Data_Company company:ownerCompanyList){
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        String companyName = company.getCompany_name();
                        String contact_name = company.getContact_name();
                        String contact_mobile_phone = company.getContact_mobile_phone();
                        String company_id = company.getCompany_id();
                        hashMap.put("companyId",company_id);
                        hashMap.put("ownerName",companyName);
                        hashMap.put("personName",contact_name);
                        hashMap.put("personMobilePhone",contact_mobile_phone);
                        hashMap.put("idCardNumber","");
                        hashMap.put("personId","");
                        //公司货主
                        hashMap.put("type","公司货主");
                        list.add(hashMap);
                    }
                }
            }

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    /**
     * 方法名 称：saveOwner
     * 内容描述：保存个人货主信息
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @ResponseBody
    @RequestMapping(value = "/saveOwner")
    public T_Data_JsonResult saveGoodType(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult result = new T_Data_JsonResult();
        String owner = request.getParameter("list");
        try {
            int i = ownerManagerService.saveOwner(owner,"U:" + request.getSession().getAttribute("username"));
            if (i==1){
                result.setResult(1);
                logger.info(MessageUtil.saveInfo);
            }else if (i==0){
                result.setResult(0);
                logger.info(MessageUtil.saveDoubleInfoError);
            }else{
                result.setResult(2);
                logger.info(MessageUtil.saveInfoError);
            }
        }catch (Exception e){
            result.setResult(2);
            logger.info(MessageUtil.saveInfoError);
        }
        return  result;
    }

    /**
     * 方法名 称：findPersonShipperById
     * 内容描述：获取货主详情 货主信息
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @ResponseBody
    @RequestMapping(value = "/findPersonShipperById")
    public void findPersonShipperById(HttpServletRequest request,HttpServletResponse response,String personId){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        List<T_Sys_Dicdata> dicdataList_sex = new ArrayList<T_Sys_Dicdata>();
        try {
            T_Data_Person personShipper = ownerManagerService.findPersonShipperById(personId);
            hashMap.put("types","个人货主") ;
            String sex = personShipper.getSex();
            dicdataList_sex = this.dicdataService.findAllDicdataByID("F26ACEE58D0041169AD0A183AAF83D10", sex);
            hashMap.put("sex",dicdataList_sex.get(0).getDicdata_name()) ;
            hashMap.put("name",personShipper.getPerson_name()) ;
            hashMap.put("phone",personShipper.getPerson_mobile_phone()) ;
            hashMap.put("number",personShipper.getId_card_number()) ;
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(hashMap));
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }

}