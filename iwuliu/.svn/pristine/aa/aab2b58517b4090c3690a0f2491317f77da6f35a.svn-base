/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：estimatesContractController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-27
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.util.*;
import com.cn.gazelle.logistics.util.message.TransportationContractManager_Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * 类 名 称: estimatesContractManager
 * 内容摘要: 运输合同的管理：增，删，改，查
 * 方法描述：该类有8个方法：
 *         01 home              运输合同页面调用
 *         02 home_new          运输合同页面调用(分角色进入)
 *         03 contractList         查询所有的运输合同
 *         04 transportationContractList 分页查询所有的运输合同列表
 *         05 transportationContractSave 保存运输合同
 *         06 transportationContractUpdate 更新运输合同
 *         07 transportationContractDel    删除运输合同
 *         08 home_edite          审核运输合同
 * @author WXW
 */
@Controller
@RequestMapping(value = "/estimatesContractManager")
public class EstimatesContractController {
    Logger logger=Logger.getLogger("EstimatesContractController.class");
    @Resource
    private DummyContractsDaoMapper dummyContractsDaoMapper;
    @Resource
    private DummyPlanDaoMapper dummyPlanDaoMapper;
    @Resource
    private CompanyDaoMapper companyDaoMapper;
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;
    @Resource
    private CargoTypeDaoMapper cargoTypeDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;

    /**
     * 方法名称：estimatesContractManager
     * 内容摘要：预估合同。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String estimatesContractManager(ModelMap model) {
        return "operationManager/estimatesContractManager/estimatesContractManager";
    }
    /**
     * 方法名称：intelligentDispatchNew_home
     * 内容摘要：运输合同——添加。
     *
     * @return string 运输合同——添加
     */
    @RequestMapping(method = RequestMethod.GET, value = "estimatesContractManagerNewAdd_home")
    public String operationContractManagerNewAdd_home(ModelMap model) {
        return "operationManager/estimatesContractManagerNewAdd/estimatesContractManagerNewAdd";
    }
    /**
     * 方法名称：intelligentDispatchNew_home
     * 内容摘要：运输合同——详情。
     * @return string 运输合同——详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "estimatesContractManagerDetail_home")
    public String operationContractManagerDetail_home(ModelMap model,String contract_id) {
        return "operationManager/estimatesContractManagerDetail/estimatesContractManagerDetail";
    }



    /**
     * 方法名称：estimatesContractTableList
     * 内容摘要：获取运输合同列表（分页）。
     *
     * @param request      request
     * @param response     response
     * @param searchType   (查询的字段名)
     * @param name（查询的字段值）
     * @param page
     * @param rows
     * @param model
     */
    @RequestMapping(value = "/estimatesContractTableList")
    public void estimatesContractTableList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String searchType, @RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer rows, ModelMap model) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        List<T_Sys_Dicdata> dicdataList_type = null;
        List<T_Sys_Dicdata> dicdataList_cargo = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            List<T_Data_Transportation_Contract_Dummy> contractList = dummyContractsDaoMapper.findAllTransportationContract(searchType, name, (page - 1) * rows, rows);
            for (T_Data_Transportation_Contract_Dummy contract : contractList) {
                HashMap map = new HashMap();
                map.put("contract_id", contract.getContract_id());
                map.put("code", contract.getCode());
                //甲方关联信息(放在数据字典之前)
                String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                String firstPartyType = contract.getFirst_party_type();
                if (firstPartyType.equals("0")) {//个人
                    T_Data_Person dataPerson = personDaoMapper.findPersonByID(relevanceInfoId);
                    String person_name = dataPerson.getPerson_name();
                    map.put("first_party_relevance_info_id", person_name);
                } else {//公司
                    T_Data_Company company = companyDaoMapper.findCompanyByID(relevanceInfoId);
                    String company_name = company.getCompany_name();
                    map.put("first_party_relevance_info_id", company_name);
                }
                //货物类型
                T_Master_Cargo_Type cargoType = cargoTypeDaoMapper.findCargoType(contract.getCargo_type_id());
                String typeName = cargoType.getCargoTypeName();
                map.put("cargo_type_id", typeName);
                list.add(map);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info(TransportationContractManager_Message.getTransportatinContractError + e.getMessage());
        }
    }

    /**
     * 方法名称：saveContractPlan
     * 内容摘要：保存运输合同计划。
     * @param request                request
     * @param response               response
     */
    @ResponseBody
    @RequestMapping(value = "saveContractPlan")
    public void saveContractPlan(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            String list2 = request.getParameter("list");
            Gson gson=new Gson();
            Map<String, Object> data = gson.fromJson(list2, new TypeToken<Map<String, Object>>() {
            }.getType());

            Map map =(Map) data.get("htbh");
            String code =(String) map.get("code");
            String cargo_type_id = (String)map.get("cargo_type_id");
            String first_party_relevance_info_id =(String) map.get("first_party_relevance_info_id");

            T_Data_Transportation_Contract_Dummy contract = new T_Data_Transportation_Contract_Dummy();
            contract.setContract_id(UUIDUtil.getUUID());
            contract.setCode(code);
            contract.setFirst_party_relevance_info_id(first_party_relevance_info_id);
            contract.setFirst_party_type("0");
            contract.setBalance_type("0");
            contract.setCargo_type_id(cargo_type_id);
            contract.setStatus("1");
            contract.setCreate_date(format.format( DateUtil.getDate()));
            contract.setBalance_cycle("0");
            contract.setBegin_date("2016-10-28");
            contract.setCargo_total(0);
            contract.setContact_name("null");
            contract.setContact_phone("null");
            contract.setDeleteFlag(0);
            contract.setLast_update(DateUtil.getDate());
            contract.setUnit_price(100);
            contract.setStatus_update_time(DateUtil.getDate());
            contract.setLast_update_user_id("0");
            contract.setEnd_date("2016-10-28");
            contract.setContact_name("null");
            dummyContractsDaoMapper.saveDummyContract(contract);

            //运输计划
            Map map2 =(Map)data.get("line");
            List list1 =(List) map2.get("ganxian");
            int size = list1.size();
            for (int i=0;i<size;i++){
                String planCode = CodeUtil.TCode(DateUtil.getDate());
                Map map3 =(Map) list1.get(i);
                //总的计划
                String operate_main_line_id = (String)map3.get("operate_main_line_id");
                String loading_cargo_yard_id = (String)map3.get("loading_cargo_yard_id");
                String loading_begin_date = (String)map3.get("loading_begin_date");
                String loading_contact_name = (String)map3.get("loading_contact_name");
                String loading_contact_phone = (String)map3.get("loading_contact_phone");
                String unloading_cargo_yard_id = (String)map3.get("unloading_cargo_yard_id");
                String unloading_finish_date = (String)map3.get("unloading_finish_date");
                String unloading_contact_name = (String)map3.get("unloading_contact_name");
                String unloading_contact_phone = (String)map3.get("unloading_contact_phone");
                String transport_unit_price = (String)map3.get("transport_unit_price");
                String cargo_total = (String)map3.get("cargo_total");
                T_Data_transportation_Plan_Dummy transportationPlan = new T_Data_transportation_Plan_Dummy();
                transportationPlan.setContract_id(contract.getContract_id());
                transportationPlan.setOperate_main_line_id(operate_main_line_id);
                transportationPlan.setLoading_cargo_yard_id(loading_cargo_yard_id);
                transportationPlan.setLoading_begin_date(loading_begin_date);
                transportationPlan.setLoading_contact_name(loading_contact_name);
                transportationPlan.setLoading_contact_phone(loading_contact_phone);
                transportationPlan.setUnloading_cargo_yard_id(unloading_cargo_yard_id);
                transportationPlan.setUnloading_finish_date(unloading_finish_date);
                transportationPlan.setUnloading_contact_name(unloading_contact_name);
                transportationPlan.setUnloading_contact_phone(unloading_contact_phone);
                transportationPlan.setTransport_unit_price(Double.valueOf(transport_unit_price));
                transportationPlan.setCargo_total(Integer.valueOf(cargo_total));
                transportationPlan.setStatus("1");
                transportationPlan.setDeleteFlag(0);
                transportationPlan.setLast_update(DateUtil.getDate());
                transportationPlan.setLast_update_user_id("00000");
                transportationPlan.setCode(planCode);
                dummyPlanDaoMapper.saveTransportateOrder(transportationPlan);
                Date beginDate = format.parse(loading_begin_date);
                Date finishDate = format.parse(unloading_finish_date);
                long day = finishDate.getTime() - beginDate.getTime();
                int  dayCount =(int) day / (24 * 60 * 60 * 1000);
                int total = Integer.valueOf(cargo_total);
                int minTotal = total / dayCount;
                long startTime = beginDate.getTime();
                for (int j=0;j<dayCount;j++){
                    //分割装货时间按天
                    long startDate = startTime + 24 * 60 * 60 * 1000*j;
                    Date date = new Date(startDate);
                    loading_begin_date = format.format(date);
                    T_Data_transportation_Plan_Dummy transportationPlan2 = new T_Data_transportation_Plan_Dummy();
                    transportationPlan2.setContract_id(contract.getContract_id());
                    transportationPlan2.setBelong_schedule_plan_number(transportationPlan.getSchedule_plan_number());
                    transportationPlan2.setOperate_main_line_id(operate_main_line_id);
                    transportationPlan2.setLoading_cargo_yard_id(loading_cargo_yard_id);
                    transportationPlan2.setLoading_begin_date(loading_begin_date);
                    transportationPlan2.setLoading_contact_name(loading_contact_name);
                    transportationPlan2.setLoading_contact_phone(loading_contact_phone);
                    transportationPlan2.setUnloading_cargo_yard_id(unloading_cargo_yard_id);
                    transportationPlan2.setUnloading_finish_date(unloading_finish_date);
                    transportationPlan2.setUnloading_contact_name(unloading_contact_name);
                    transportationPlan2.setUnloading_contact_phone(unloading_contact_phone);
                    transportationPlan2.setTransport_unit_price(Double.valueOf(transport_unit_price));
                    //平均分配数量
                    transportationPlan2.setCargo_total(minTotal);
                    transportationPlan2.setStatus("1");
                    transportationPlan2.setDeleteFlag(0);
                    transportationPlan2.setLast_update(DateUtil.getDate());
                    transportationPlan2.setLast_update_user_id("00000");
                    transportationPlan2.setCode(CodeUtil.TCode(DateUtil.getDate()));
                    dummyPlanDaoMapper.saveTransportateOrder(transportationPlan2);
                }

            }
        } catch (Exception e) {
            logger.info(TransportationContractManager_Message.SaveTransportatinContractError + e.getMessage());
        }
    }

}
