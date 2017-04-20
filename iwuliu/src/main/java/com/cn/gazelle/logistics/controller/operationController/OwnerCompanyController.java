/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OwnerCompanyController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-17
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_Company;
import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.OwnerCompanyService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称：OwnerCompanyController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/ownerCompanyManager")
public class OwnerCompanyController {

    Logger logger=Logger.getLogger(OwnerCompanyController.class);
    @Resource
    private OwnerCompanyService ownerCompanyService;

    @Resource
    private DicdataService dicdataService;

    /**
     * 方法名 称：saveOwnerCompany
     * 内容描述：保存 货主信息
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @ResponseBody
    @RequestMapping(value = "/saveOwnerCompany")
    public T_Data_JsonResult saveGoodType(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult result = new T_Data_JsonResult();
        String owner = request.getParameter("list");
        try {
            int i = ownerCompanyService.saveCompanyOwner(owner,"U:" + request.getSession().getAttribute("username"));
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
            if (e.getMessage().equals("0")){
                result.setResult(0);
            }else {
                result.setResult(2);
            }

            logger.info(MessageUtil.saveInfoError);
        }
        return  result;
    }

    /**
     * 方法名 称：findCompanyOwnerDetailByCompanyId
     * 内容描述：获取货主详情
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @ResponseBody
    @RequestMapping(value = "/findCompanyOwnerDetailByCompanyId")
    public void findCompanyOwnerDetailByCompanyId(HttpServletRequest request,HttpServletResponse response,String companyId){
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_area = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_sex = new ArrayList<T_Sys_Dicdata>();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            T_Data_Company companyShipper= ownerCompanyService.findCompanyShipperByID(companyId);

            String provinceId = companyShipper.getProvince_id();
            String cityId = companyShipper.getCity_id();
            String areaId = companyShipper.getArea_id();
            dicdataList_province = this.dicdataService.findAllDicdataByID("2EE02FDAE3BE4F06B8CFDF4425BA74BF", provinceId);
            dicdataList_city = this.dicdataService.findAllDicdataByID("66029BA5DC964A15B29852FA077327C0", cityId);

            dicdataList_area = this.dicdataService.findAllDicdataByID("D7FAF287624242ECB3304B6A62414779", areaId);
            hashMap.put("types","公司货主") ;
            hashMap.put("provinceId",dicdataList_province.get(0).getDicdata_name()) ;
            hashMap.put("cityId",dicdataList_city.get(0).getDicdata_name()) ;
            hashMap.put("areaId",dicdataList_area.get(0).getDicdata_name()) ;
            hashMap.put("townStreet",companyShipper.getTown_street()) ;
            hashMap.put("contactName",companyShipper.getContact_name()) ;
            hashMap.put("licence",companyShipper.getBusiness_licence()) ;
            hashMap.put("fixPhone",companyShipper.getCompany_fixed_phone()) ;
            hashMap.put("phone",companyShipper.getContact_mobile_phone()) ;
            String sex = companyShipper.getContact_sex();
            dicdataList_sex = this.dicdataService.findAllDicdataByID("F26ACEE58D0041169AD0A183AAF83D10", sex);
            hashMap.put("sex",dicdataList_sex.get(0).getDicdata_name()) ;
            hashMap.put("companyName",companyShipper.getCompany_name()) ;
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(hashMap));
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }

}