/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：operationContarctController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-07
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.CargoTypeService;
import com.cn.gazelle.logistics.service.CompanyService;
import com.cn.gazelle.logistics.service.PersonService;
import com.cn.gazelle.logistics.service.TransportationContractService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.CargoTruckTypeManager_Message;
import com.cn.gazelle.logistics.util.message.TransportationContractManager_Message;
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
import java.util.Map;

/**
 * 类 名 称：operationContarctController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/operationContractManager")
public class OperationContractController {
    Logger logger = Logger.getLogger(OperationContractController.class);
    @Resource//运输合同
    private TransportationContractService contractService;
    @Resource
    private PersonService personService;
    @Resource
    private CompanyService companyService;
    @Resource
    private CargoTypeService cargoTypeService;

    /**
     * 方法名称：transportationContractSave
     * 内容摘要：保存运输合同。
     * @param request                request
     * @param response               response
     */
    @ResponseBody
    @RequestMapping(value = "saveContractPlan")
    public T_Data_JsonResult saveContractPlan(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult result = new T_Data_JsonResult();
        int i=-1;
        try{
            String list2 = request.getParameter("list");
            i = contractService.saveTransportationContract(list2, "U:" + request.getSession().getAttribute("username"));
        } catch (Exception e) {
            logger.error(TransportationContractManager_Message.SaveTransportatinContractError + e.getMessage());
            i = Integer.parseInt(e.getMessage());
        }
        if (i == 1) {
            result.setResult(1);//保存成功
            logger.info(CargoTruckTypeManager_Message.SaveCargoTruckDone);
        } else if (i == 0) {
            result.setResult(0);//保存重复
            logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
        } else if (i == -1) {
            result.setResult(2);//保存失败
            logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
        }
        return  result;
    }

    /**
     * 方法名称：transportationContractList
     * 内容摘要：获取运输合同列表（分页）。
     *
     * @param request      request
     * @param response     response
     * @param model
     */
    @RequestMapping(value = "/transportationContractList")
    public void transportationContractList2(HttpServletRequest request, HttpServletResponse response,  ModelMap model) {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            List<T_Data_Transportation_Contract> contractList = contractService.findAllTransportationContract();
            for (T_Data_Transportation_Contract contract : contractList) {
                HashMap map = new HashMap();
                map.put("contract_id", contract.getContract_id());
                map.put("code", contract.getCode());
                //甲方关联信息(放在数据字典之前)
                String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                String firstPartyType = contract.getFirst_party_type();
                if (firstPartyType.equals("0")) {//个人
                    T_Data_Person dataPerson = personService.findPersonByID(relevanceInfoId);
                    String person_name = dataPerson.getPerson_name();
                    map.put("first_party_relevance_info_id", person_name);
                } else {//公司
                    T_Data_Company company = companyService.findCompanyByID(relevanceInfoId);
                    String company_name = company.getCompany_name();
                    map.put("first_party_relevance_info_id", company_name);
                }
                //货物类型
                T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(contract.getCargo_type_id());
                String typeName="null";
                if(cargoType!=null){
                    typeName = cargoType.getCargoTypeName();
                }
                map.put("cargo_type_id", typeName);
                list.add(map);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(TransportationContractManager_Message.getTransportatinContractError + e.getMessage());
        }
    }

    /**
     * 方法名称：updateContractOrAddLineInfo
     * 内容摘要：修改运输合同干线单价和添加干线。
     * @param request                request
     * @param response               response
     */
    @ResponseBody
    @RequestMapping(value = "updateContractOrAddLineInfo")
    public T_Data_JsonResult updateContractOrAddLineInfo( HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult result = new T_Data_JsonResult();
        int i=-1;
        try{
            String list2 = request.getParameter("list");
            i = contractService.updateContractOrAddLineInfo(list2, "U:" + request.getSession().getAttribute("username"));
        } catch (Exception e) {
            logger.error(TransportationContractManager_Message.SaveTransportatinContractError + e.getMessage());
            i = Integer.parseInt(e.getMessage());
        }
        if (i == 1) {
            result.setResult(1);//保存成功
            logger.info(CargoTruckTypeManager_Message.SaveCargoTruckDone);
        } else if (i == 0) {
            result.setResult(0);//保存重复
            logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
        } else if (i == -1) {
            result.setResult(2);//保存失败
            logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
        }
        return  result;
    }
}