/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationOrderController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：订单管理页面
 * 完成日期：2016-03-24
 * 作    者：WXW
 * 内容摘要：订单的管理
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.TransportationOrder_Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
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

/**
 * 类 名 称: TransportationOrderController
 * 内容摘要: 订单的管理：增，删，改，查
 * 方法描述：该类有7个方法：
 *         01 home              订单页面调用
 *         02 home_new          订单添加页面调用(分角色进入)
 *         03 orderList         查询所有的订单
 *         04 transportationOrderList 分页查询所有的订单列表
 *         05 transportationOrderSave 保存订单
 *         06 transportationOrderUpdate 审核订单
 *         07 transportationOrderDel   删除订单
 * @author WXW
 */
@Controller
@RequestMapping(value = "/transportationOrderManager")
public class TransportationOrderController {
    Logger logger = Logger.getLogger(TransportationOrderController.class);

    @Resource
    private TransportationOrderService transportationOrderService;
    @Resource
    private TransportationContractService transportationContractService;
    @Resource
    private DicdataService dicdataService;
    @Resource
    private CargoService cargoService;
    @Resource
    private OperateMainLineService operateMainLineService;
    @Resource
    private PersonService personService;
    @Resource
    private CargoTypeService cargoTypeService;
    @Resource
    private TruckService truckService;
    @Resource
    private TruckCarriageTypeService  truckCarriageTypeService;
    @Resource
    private ScheduleSheetService  scheduleSheetService;
    @Resource
    private TruckImmediateService  truckImmediateService;
    @Resource
    private CargoTruckTypeService  cargoTruckTypeService;
    @Resource
    private TruckTransportLineService  truckTransportLineService;
    @Resource
    private SysInfoService sysInfoService;
    @Resource
    private OBDEquipmentTruckBindingService obdEquipmentTruckBindingService;
    @Resource
    private TransportationOrderDaoMapper transportationOrderDaoMapper;
    @Resource
    private TransportationContractDaoMapper contractDaoMapper;
    @Resource
    private OperateMainLineDaoMapper operateMainLineDaoMapper;
    @Resource
    private  OwnerCompanyDaoMapper ownerCompanyDaoMapper;





    /**
     * 方法名称：manualOrderDetails
     * 内容摘要：跳转手动分配计划页面。
     * @return string planNumber。
     */
    @RequestMapping(method = RequestMethod.GET, value = "manualOrderDetail_home")
    public String manualOrderDetails(ModelMap model, String planNumber) {
        return "operationManager/transportPlanManuallyAssign/transportPlanManuallyAssign";
    }
    /**
     * 方法名称：lookManualOrder
     * 内容摘要：跳转详情页面。
     * @return string planNumber。
     */
    @RequestMapping(method = RequestMethod.GET, value = "lookManualOrder")
    public String lookManualOrder(ModelMap model, String planNumber) {
        return "operationManager/transportPlanDetail/transportPlanDetail";
    }

    /**
     * 方法名称：transportationOrderDel
     * 内容摘要：删除订单。
     * @param request  request
     * @param response response
     * @param order_ID order_ID
     * @param model
     */
    @RequestMapping(value = "transportationOrderDel")
    public void transportationOrderDel(String order_ID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] order_id=order_ID.split(",");
        try {
            for (int i=0;i<order_id.length;i++){
                transportationOrderService.delTransportateOrderById(order_id[i]);
            }
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.DelTransportationOrderError + e.getMessage());
        }
    }

    /**
     * 方法名称：transportationContractList
     * 内容摘要：获取订单列表（分页）。 二级运输计划根据所属计划编号
     * @param request  request
     * @param response response

     */
    @RequestMapping(value = "/transportationOrderListBelongNotNull")
    public void findOrderListByContractIdAndBelongNotNull(HttpServletRequest request, HttpServletResponse response,String schedule_plan_number) {
        List<T_Sys_Dicdata> dicdataList_status = null;

        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<T_Data_Transportation_Plan> orderList = transportationOrderService.findOrderListByBelongCode(schedule_plan_number);
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
            for (T_Data_Transportation_Plan order : orderList) {
                String contract_id = order.getContract_id();
                //合同
                T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
                String first_party_type = contract.getFirst_party_type();
                String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                String name="";
                if (first_party_type.equals("0")){
                    T_Data_Person person = personService.findPersonByID(relevanceInfoId);
                    name=person.getPerson_name();
                }else if (first_party_type.equals("1")){
                    T_Data_Company company = ownerCompanyDaoMapper.findCompanyShipperByID(relevanceInfoId);
                   name=company.getCompany_name();
                }

                String cargo_type_id = contract.getCargo_type_id();
                T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                //干线
                String lineId = order.getOperate_main_line_id();
                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId);
                List<T_Data_Truck> truckList = truckService.findByCargoTypeAndLineId(cargo_type_id, lineId);
                //因运单的干线和货物类型相同，判断车辆是否存在即可
                if (truckList.size()!=0){
                    //装货货场
                    String cargoYardId = order.getLoading_cargo_yard_id();
                    T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                    String loading_begin_date = order.getLoading_begin_date();
                    String unloading_finish_date = order.getUnloading_finish_date();
                    Date endDate = format.parse(unloading_finish_date);
                    unloading_finish_date= format.format(endDate);
                    Date beginDate = format.parse(loading_begin_date);
                    Date date = new Date();
                    if ( beginDate.getTime()<date.getTime()){
                        loading_begin_date=format.format(date);
                    }
                    String loading_contact_phone = order.getLoading_contact_phone();
                    String unloading_contact_phone = order.getUnloading_contact_phone();
                    String loading_contact_name = order.getLoading_contact_name();
                    String unloading_contact_name = order.getUnloading_contact_name();
                    String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                    T_Master_Cargo_Yard uncargo_yard = cargoService.findById(unloading_cargo_yard_id);
                    for (T_Data_Truck truck:truckList){
                        HashMap map = new HashMap();
                        String plate_number = truck.getPlate_number();
                        String truck_length_id = truck.getTruck_length_id();
                        String length = dicdataService.findDicdataByID(truck_length_id).getDicdata_name();
                        String truck_carriage_type_id = truck.getTruck_carriage_type_id();
                        T_Master_Truck_Carriage_Type carriageType = truckCarriageTypeService.findTruckCarriageTypeByID(truck_carriage_type_id);
                        //合同
                        map.put("code",contract.getCode());
                        map.put("name",name);
                        if (cargoType!=null){
                            map.put("cargo_type_id",cargoType.getCargoTypeName());
                        }else{
                            map.put("cargo_type_id","null");
                        }

                        map.put("plate_number",plate_number);
                        map.put("length",length);
                        map.put("carriageName",carriageType.getTruck_carriage_type_name());
                        map.put("loading_begin_date",loading_begin_date);
                        map.put("unloading_finish_date",unloading_finish_date);
                        if (mainLine!=null){
                            map.put("lineName",mainLine.getOperate_main_line_name());
                        }else {
                            map.put("lineName","null");
                        }
                        if (cargoYard==null){
                            map.put("loading_cargo_yard_id","null");
                        }else {
                            map.put("loading_cargo_yard_id",cargoYard.getCargo_name());
                        }
                        if (uncargo_yard==null){
                            map.put("unloading_cargo_yard_id","null");
                        }else {
                            map.put("unloading_cargo_yard_id",uncargo_yard.getCargo_name());
                        }
                        map.put("transport_unit_price", order.getTransport_unit_price());
                        map.put("cargo_total",order.getCargo_total());
                        map.put("loading_contact_phone", loading_contact_phone);
                        map.put("unloading_contact_phone",unloading_contact_phone);
                        map.put("loading_contact_name", loading_contact_name);
                        map.put("unloading_contact_name",unloading_contact_name);
                        //订单状态
                        dicdataList_status = dicdataService.findAllDicdataByID("462EA35D1B0A4DC68F1EFC0852E10FDD", order.getStatus());
                        map.put("status",dicdataList_status.get(0).getDicdata_name() );
                        map.put("completedCargoTatal", order.getTransport_completed_cargo_total());
                        map.put("transportCargoTotal", order.getTransported_cargo_total());
                        list.add(map);
                    }
                }else{
                    break;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info("错误：" +e.getMessage());
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }

    }
    /**
     * 方法名称：transportationContractList
     * 内容摘要：获取订单列表（分页）。所属计划编号为空根据合同id 一级运输计划根据合同号查询
     * @param request  request

     */
    @RequestMapping(value = "/transportationOrderListBelongNull")
    public void transportationOrderListBelongNull(HttpServletRequest request,HttpServletResponse response,String contract_id) {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<T_Data_Transportation_Plan> orderList = transportationOrderService.findOrderListByContractIdAndBelongNull(contract_id);
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            if (orderList.size()!=0) {
                //合同
                T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
                String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                String first_party_type = contract.getFirst_party_type();
                String name="";
                if (first_party_type.equals("0")){
                    T_Data_Person person = personService.findPersonByID(relevanceInfoId);
                    name=person.getPerson_name();
                }else if (first_party_type.equals("1")){
                    T_Data_Company company = ownerCompanyDaoMapper.findCompanyShipperByID(relevanceInfoId);
                    name= company.getCompany_name();
                }

                String cargo_type_id = contract.getCargo_type_id();
                T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                String cargoTypeName="null";
                if (cargoType!=null){
                    cargoTypeName = cargoType.getCargoTypeName();
                }
                for (T_Data_Transportation_Plan order : orderList) {
                    HashMap map = new HashMap();
                    //合同
                    map.put("code", contract.getCode());
                    map.put("name", name);
                    map.put("cargo_type_id",cargoTypeName );
                    map.put("loading_begin_date", format.format(format.parse(order.getLoading_begin_date())));
                    map.put("loading_contact_name",order.getLoading_contact_name());
                    map.put("loading_contact_phone", order.getLoading_contact_phone());
                    map.put("unloading_finish_date", format.format(format.parse(order.getUnloading_finish_date())));
                    map.put("unloading_contact_name",order.getUnloading_contact_name());
                    map.put("unloading_contact_phone", order.getUnloading_contact_phone());
                    String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                    T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
                    String cargo_name="null";
                    if (byId!=null){
                        cargo_name = byId.getCargo_name();
                    }
                    map.put("unloading_cargo_yard_id",cargo_name );
                    //干线
                    String lineId = order.getOperate_main_line_id();
                    T_Master_Operate_Main_Line operateMainLineById = operateMainLineService.findOperateMainLineById(lineId);
                    String lineName="null";
                    if (operateMainLineById!=null){
                        lineName = operateMainLineById.getOperate_main_line_name();
                    }
                    //装货货场
                    String cargoYardId = order.getLoading_cargo_yard_id();
                    T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                    String cargo_name1="null";
                    if (cargoYard!=null){
                        cargo_name1 = cargoYard.getCargo_name();
                    }
                    map.put("lineName", lineName);
                    map.put("loading_cargo_yard_id", cargo_name1);
                    map.put("transport_unit_price", order.getTransport_unit_price());
                    map.put("cargo_total", order.getCargo_total());
                    list.add(map);
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }


    /**
     * 方法名称：transportationContractList
     * 内容摘要：获取详情（分页）。所属计划编号为空根据合同计划Id 所有一级运输计划
     * @param request  request
     * @param response response

     */
    @RequestMapping(value = "/transportationOrderListBelongNullDetail")
    public void transportationOrderListBelongNullDetail(HttpServletRequest request, HttpServletResponse response,String contract_id) {
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long startTime = System.currentTimeMillis();
            List<T_Sys_Dicdata> dicdataByID = null;
            List<T_Data_Transportation_Plan> orderList = transportationOrderService.findOrderListByContractIdAndBelongNull(contract_id);
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            //合同
            T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
            String first_party_type = contract.getFirst_party_type();
            String relevanceInfoId = contract.getFirst_party_relevance_info_id();
            if (first_party_type.equals("0")){
                hashMap.put("personType","个人货主");
                T_Data_Person person = personService.findPersonByID(relevanceInfoId);
                hashMap.put("name",person.getPerson_name());
            }else if (first_party_type.equals("1")){
                hashMap.put("personType","公司货主");
                T_Data_Company company = ownerCompanyDaoMapper.findCompanyShipperByID(relevanceInfoId);
                hashMap.put("name", company.getCompany_name());
            }
            String contact_name = contract.getContact_name();
            hashMap.put("contactName",contact_name);
            String balanceType = contract.getBalance_type();
            dicdataByID = dicdataService.findAllDicdataByID("28E83A5CA95A47F8BEA1C75800D9FADE", balanceType);
            String dicdataName = dicdataByID.get(0).getDicdata_name();
            hashMap.put("balanceType",dicdataName);
            String contactPhone = contract.getContact_phone();
            hashMap.put("contactPhone",contactPhone);
            String beginDate = contract.getBegin_date();
            hashMap.put("beginDate",beginDate);
            String end_date = contract.getEnd_date();
            hashMap.put("end_date",end_date);
            hashMap.put("code",contract.getCode());
            String cargo_type_id = contract.getCargo_type_id();
            T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
            hashMap.put("cargo_type_id",cargoType.getCargoTypeName());
            long startTime2 = System.currentTimeMillis();
            for (T_Data_Transportation_Plan order : orderList) {
                String schedulePlanNumber = order.getSchedule_plan_number();
                HashMap map = new HashMap();
                //干线
                String lineId = order.getOperate_main_line_id();
                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId);
                String lineName="null";
                if (mainLine!=null){
                    lineName= mainLine.getOperate_main_line_name();
                }
                //装货货场
                String loading_begin_date = order.getLoading_begin_date();
                String unloading_finish_date = order.getUnloading_finish_date();
                String loading_contact_name = order.getLoading_contact_name();
                String unloading_contact_name = order.getUnloading_contact_name();
                String loading_contact_phone = order.getLoading_contact_phone();
                String unloading_contact_phone = order.getUnloading_contact_phone();
                String loading_cargo_yard_id = order.getLoading_cargo_yard_id();
                String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                T_Master_Cargo_Yard cargoYard = cargoService.findById(loading_cargo_yard_id);
                String loadCargoName="null";
                if (cargoYard!=null){
                    loadCargoName = cargoYard.getCargo_name();
                }
                T_Master_Cargo_Yard uncargo_yard = cargoService.findById(unloading_cargo_yard_id);
                String unloadCargoName ="null";
                if (uncargo_yard!=null){
                    unloadCargoName = uncargo_yard.getCargo_name();
                }
                map.put("schedulePlanNumber",schedulePlanNumber);
                map.put("loadCargoName",loadCargoName);
                map.put("unloadCargoName",unloadCargoName);
                map.put("loading_contact_phone",loading_contact_phone);
                map.put("unloading_contact_phone",unloading_contact_phone);
                map.put("loading_contact_name",loading_contact_name);
                map.put("unloading_contact_name",unloading_contact_name);
                map.put("loading_begin_date",loading_begin_date);
                map.put("unloading_finish_date",format.format(format.parse(order.getUnloading_finish_date())));
                map.put("lineName",lineName);
                map.put("transport_unit_price", order.getTransport_unit_price());
                map.put("cargo_total",order.getCargo_total());
                list.add(map);
            }
            hashMap.put("list",list);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(hashMap));
            long startTime3 = System.currentTimeMillis();
            System.out.println(startTime3-startTime);
            System.out.println(startTime2-startTime);
            System.out.println(startTime3-startTime2);
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }

    }


    /**
     * 方法名称：transportationContractList
     * 内容摘要：获取计划列表（分页）。所属计划编号为空
     * @param request  request
     */
    @RequestMapping(value = "/OrderListBelongNull")
    public void OrderListBelongNull(HttpServletRequest request,HttpServletResponse response) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<T_Data_Transportation_Plan> orderList = transportationOrderService.findOrderListBelongNull();
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (T_Data_Transportation_Plan order : orderList) {
                String contract_id = order.getContract_id();
                //合同
                T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
                String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                T_Data_Person person = personService.findPersonByID(relevanceInfoId);
                String personName="null";
                if (person!=null){
                    personName= person.getPerson_name();
                }
                String cargo_type_id = contract.getCargo_type_id();
                T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                String cargoTypeName="null";
                if (cargoType!=null){
                    cargoTypeName= cargoType.getCargoTypeName();
                }
                HashMap map = new HashMap();
                String schedule_plan_number = order.getSchedule_plan_number();
                map.put("schedule_plan_number", schedule_plan_number);
                //合同
                map.put("code", contract.getCode());
                map.put("name", personName);
                map.put("cargo_type_id", cargoTypeName);
                map.put("loading_begin_date",format.format(format.parse(order.getLoading_begin_date())) );
                map.put("loading_contact_name",order.getLoading_contact_name());
                map.put("loading_contact_phone", order.getLoading_contact_phone());
                map.put("unloading_finish_date", format.format(format.parse(order.getUnloading_finish_date())));
                map.put("unloading_contact_name",order.getUnloading_contact_name());
                map.put("unloading_contact_phone", order.getUnloading_contact_phone());
                String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
                String cargo_name="null";
                if (byId!=null){
                    cargo_name= byId.getCargo_name();
                }
                map.put("unloading_cargo_yard_id", cargo_name);
                //订单状态
                dicdataList_status = dicdataService.findAllDicdataByID("462EA35D1B0A4DC68F1EFC0852E10FDD", order.getStatus());
                map.put("status",dicdataList_status.get(0).getDicdata_name() );
                map.put("completedCargoTatal", String.valueOf(order.getTransport_completed_cargo_total()));
                map.put("transportCargoTotal", String.valueOf(order.getTransported_cargo_total()));
                //干线
                String lineId = order.getOperate_main_line_id();

                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId);
                String lineName="null";
                if (mainLine!=null){
                    map.put("lineName", mainLine.getOperate_main_line_name());
                }else {
                    map.put("lineName", lineName);
                }
                //装货货场
                String cargoYardId = order.getLoading_cargo_yard_id();
                T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                String cargoName="null";
                if (cargoYard!=null){
                    cargoName = cargoYard.getCargo_name();
                }

                map.put("loading_cargo_yard_id", cargoName);
                map.put("transport_unit_price", order.getTransport_unit_price());
                map.put("cargo_total", order.getCargo_total());
                list.add(map);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }

    /**
     *findPlanByConditions
     * 内容描述：根据条件模糊查询一级计划信息
     * 方法描述：该类有 个方法
     *          01
     *          02
     *@authot WXW
     */
    @ResponseBody
    @RequestMapping(value = "findPlanByConditions")
    public void findPlanByConditions( @RequestParam(required = false, defaultValue = "") String code,
                                      @RequestParam(required = false, defaultValue = "") String lineId,
                                      @RequestParam(required = false, defaultValue = "") String status,
                                      HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String contractId="";
            logger.info("code   ==="+code);
            if (code.trim().length() !=0){
                T_Data_Transportation_Contract transportationContract = transportationContractService.findByCode(code);
                contractId = transportationContract.getContract_id();
            }


            List<T_Data_Transportation_Plan> orderList = transportationOrderService.findPlanByConditions(contractId,lineId,status);
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (T_Data_Transportation_Plan order : orderList) {
                String contract_id = order.getContract_id();
                //合同
                T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
                String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                String cargo_type_id = contract.getCargo_type_id();
                T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                HashMap map = new HashMap();
                if (cargoType!=null){
                    map.put("cargo_type_id", cargoType.getCargoTypeName());
                }else {
                    map.put("cargo_type_id", "null");
                }
                String schedule_plan_number = order.getSchedule_plan_number();
                map.put("schedule_plan_number", schedule_plan_number);
                //合同
                map.put("code", contract.getCode());
                T_Data_Person person = personService.findPersonByID(relevanceInfoId);
                if (person!=null){
                    map.put("name", person.getPerson_name());
                }else {
                    map.put("name", "null");
                }
                map.put("loading_begin_date", format.format(format.parse(order.getLoading_begin_date())) );
                map.put("loading_contact_name",order.getLoading_contact_name());
                map.put("loading_contact_phone", order.getLoading_contact_phone());
                map.put("unloading_finish_date", format.format(format.parse(order.getUnloading_finish_date())));
                map.put("unloading_contact_name",order.getUnloading_contact_name());
                map.put("unloading_contact_phone", order.getUnloading_contact_phone());
                String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
                if (byId!=null){
                    map.put("unloading_cargo_yard_id", byId.getCargo_name());
                }else{
                    map.put("unloading_cargo_yard_id", "null");
                }
                //订单状态
                dicdataList_status = dicdataService.findAllDicdataByID("462EA35D1B0A4DC68F1EFC0852E10FDD", order.getStatus());
                map.put("status",dicdataList_status.get(0).getDicdata_name() );
                map.put("completedCargoTatal", order.getTransport_completed_cargo_total());
                map.put("transportCargoTotal", order.getTransported_cargo_total());

                //干线
                String lineId2 = order.getOperate_main_line_id();
                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId2);
                if (mainLine!=null){
                    String lineName = mainLine.getOperate_main_line_name();
                    map.put("lineName", lineName);
                }else{
                    map.put("lineName", "null");
                }
                //装货货场
                String cargoYardId = order.getLoading_cargo_yard_id();
                T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                if (cargoYard!=null){
                    map.put("loading_cargo_yard_id", cargoYard.getCargo_name());
                }else {
                    map.put("loading_cargo_yard_id", "null");
                }

                map.put("transport_unit_price", order.getTransport_unit_price());
                map.put("cargo_total", order.getCargo_total());
                list.add(map);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }


    /**
     * 方法名称： manualOrderList
     * 内容摘要：手动派单列表（未过期，未完成的计划）
     * @param request  request
     */
    @RequestMapping(value = "/manualOrderList")
    public void manualOrderList(HttpServletRequest request,HttpServletResponse response) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        try {
            List<T_Data_Transportation_Plan> orderList = transportationOrderService.manualOrderList();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (T_Data_Transportation_Plan order : orderList) {
                String contract_id = order.getContract_id();
                //合同
                T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);

                String cargo_type_id = contract.getCargo_type_id();

                HashMap map = new HashMap();
                //合同
                map.put("planNumber", order.getSchedule_plan_number());
                T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                if (cargoType!=null){
                    map.put("cargo_type_id", cargoType.getCargoTypeName());
                }else {
                    map.put("cargo_type_id","null");
                }
                String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
                if (byId!=null){
                    map.put("unloading_cargo_yard_id", byId.getCargo_name());
                }else {
                    map.put("unloading_cargo_yard_id", "null");
                }
                //干线
                String lineId = order.getOperate_main_line_id();

                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId);
                if (mainLine!=null){
                    map.put("lineName", mainLine.getOperate_main_line_name());
                }else {
                    map.put("lineName", "null");
                }
                //装货货场
                String cargoYardId = order.getLoading_cargo_yard_id();
                T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                if (cargoYard==null){
                    map.put("loading_cargo_yard_id","null");
                }else {
                    map.put("loading_cargo_yard_id", cargoYard.getCargo_name());
                }
                map.put("redistributeTotal",String.valueOf(order.getRedistribute_cargo_total()));
                map.put("unRedistributeTotal", String.valueOf(order.getCargo_total()-order.getSchedule_cargo_total()));
                map.put("price", order.getTransport_unit_price());
                map.put("beginDate", format.format(format.parse(order.getLoading_begin_date())) );
                list.add(map);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info("错误："+e.getMessage());
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }
    /**
     * 方法名称： manualFinishedOrderList
     * 内容摘要：手动派单列表（已派单完成的完成的计划）
     * @param request  request
     */
    @RequestMapping(value = "/manualFinishedOrderList")
    public void manualFinishedOrderList(HttpServletRequest request,HttpServletResponse response) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        try {
            List<T_Data_Transportation_Plan> orderList = transportationOrderService.manualFinishedOrderList();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (T_Data_Transportation_Plan order : orderList) {
                String contract_id = order.getContract_id();
                //合同
                T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);

                String cargo_type_id = contract.getCargo_type_id();
                T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                HashMap map = new HashMap();
                //合同
                map.put("planNumber", order.getSchedule_plan_number());
                map.put("cargo_type_id", cargoType.getCargoTypeName());
                String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
                map.put("unloading_cargo_yard_id", byId.getCargo_name());
                //干线
                String lineId = order.getOperate_main_line_id();
                String lineName = operateMainLineService.findOperateMainLineById(lineId).getOperate_main_line_name();
                //装货货场
                String cargoYardId = order.getLoading_cargo_yard_id();
                T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                double redistributeCargoTotal = order.getRedistribute_cargo_total();
                map.put("lineName", lineName);
                map.put("loading_cargo_yard_id", cargoYard.getCargo_name());
                map.put("redistributeTotal",String.valueOf(redistributeCargoTotal));
                double unRedistributeTotal = order.getCargo_total() - order.getSchedule_cargo_total() - redistributeCargoTotal;
                if (unRedistributeTotal<0){
                    map.put("unRedistributeTotal", "0");
                }else {
                    map.put("unRedistributeTotal", String.valueOf(unRedistributeTotal));
                }
                map.put("price", order.getTransport_unit_price());
                map.put("beginDate", format.format(format.parse(order.getLoading_begin_date())) );
                list.add(map);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }


    /**
     * 方法名称： manualOrderDetails
     * 内容摘要：手动派单详情
     * @param request  request
     */
    @RequestMapping(value = "/manualOrderDetails")
    public void manualOrderDetails(HttpServletRequest request,HttpServletResponse response,String planNumber) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        try {
            T_Data_Transportation_Plan order = transportationOrderService.findBySchedulePlanNumber(planNumber);
            String contract_id = order.getContract_id();
            //合同
            T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
            String cargo_type_id = contract.getCargo_type_id();

            HashMap map = new HashMap();
            //合同
            map.put("planNumber", order.getSchedule_plan_number());
            map.put("cargoType", cargo_type_id);
            T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
            if (cargoType!=null){
                map.put("cargo_type_id", cargoType.getCargoTypeName());
            }else {
                map.put("cargo_type_id",null);
            }
            String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
            T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
            if (byId!=null){
                map.put("unloading_cargo_yard_id", byId.getCargo_name());
            }else {
                map.put("unloading_cargo_yard_id", "null");
            }

            //干线
            String lineId = order.getOperate_main_line_id();
            T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId);
            if (mainLine!=null){
                map.put("lineName", mainLine.getOperate_main_line_name());
            }else {
                map.put("lineName", "null");
            }
            //装货货场
            String cargoYardId = order.getLoading_cargo_yard_id();
            T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
            if (cargoYard!=null){
                map.put("loading_cargo_yard_id", cargoYard.getCargo_name());
            }else {
                map.put("loading_cargo_yard_id", "null");
            }

            map.put("lineId", lineId);
            map.put("redistributeTotal",String.valueOf(order.getRedistribute_cargo_total()));
            double value = order.getCargo_total() - order.getSchedule_cargo_total();
            if (value <= 0){
                map.put("unRedistributeTotal", "0");
            }else{
                map.put("unRedistributeTotal", String.valueOf(value));
            }
            map.put("price", order.getTransport_unit_price());
            map.put("beginDate", order.getLoading_begin_date());
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(map));
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }
    /**
     * 方法名称： positionByNumber
     * 内容摘要：手动派单车牌号输入后的判断获取相关信息（车厢，位置，运输状态）
     * @param   params
     */
    @ResponseBody
    @RequestMapping(value = "/positionByParams")
    public String  positionByNumber(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true, defaultValue = "") String[] params) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        HashMap map = new HashMap();
        try {
            String plateNumber=params[0];
            String lineId=params[1];
            String cargoTypeId=params[2];

            T_Data_Truck truck = truckService.findTruckByPlateNumber(plateNumber);
            if (truck!=null){
                //拒单操作  该车辆的拒单时间判断
                //最新拒单时间
                Date deniedScheduleLastTime = truck.getDenied_schedule_last_time();
                //获取系统信息表
                T_Data_Sys_Info sysInfo = sysInfoService.findAll();
                //获取系统信息表中的冷却时间
                int coolDownTime = sysInfo.getDenied_cool_down_time();
                long time = 0;
                if (deniedScheduleLastTime != null) {
                    time = deniedScheduleLastTime.getTime() + coolDownTime;
                }
                long nowTime = new Date().getTime();
                //拒单时间+车辆拒单冷却时间大于当前时间才可以刷单
                if (deniedScheduleLastTime != null || time >= nowTime) {
                    map.put("flag","4");//车辆拒单中
                }
                String typeId = truck.getTruck_carriage_type_id();
                String typeName = truckCarriageTypeService.findTruckCarriageTypeByID(typeId).getTruck_carriage_type_name();
                //车厢类型
                map.put("typeName",typeName);
                String status = scheduleSheetService.findMinStatusByTruckId(truck.getTruck_id());
                T_Master_Cargo_Truck_Type_Match match = cargoTruckTypeService.findMatchByTruckId(typeId, cargoTypeId);
                if (match==null){
                    map.put("flag","2");//车厢类型不匹配
                }
                //运输状态
                if (status!=null){
                    dicdataList_status = this.dicdataService.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", status);
                    String statusName = dicdataList_status.get(0).getDicdata_name();
                    map.put("statusName",statusName);
                    Integer value = Integer.valueOf(status);
                    if (value<4){
                        map.put("flag","1");//车辆有未完成订单
                    }
                }else {
                    //车辆无调度单
                    map.put("statusName","已完成");
                }
                //常跑路线判断
                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId);
                String startCityId = mainLine.getStart_city_id();
                String finishCityId = mainLine.getFinish_city_id();
                List<T_Data_Truck_Transport_Line> lineList = truckTransportLineService.findTruckTransportByTruckIDAndCity(truck.getTruck_id(), startCityId, finishCityId);
                if (lineList.size()==0){
                    //车辆干线不匹配
                    map.put("flag","3");
                }
                //经纬度查询车辆位置
                T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plateNumber);
                if (truckImmediate!=null) {
                    double latitude = truckImmediate.getLatitude();
                    double longitude = truckImmediate.getLongitude();
                    String responses = null;
                    Map<String, Object> retList = null;
                    String gg = String.valueOf(longitude);
                    String dd = String.valueOf(latitude);
                    String addressStr = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + gg + ',' + dd + "&key=5107d8c6eaacd4c780ed2a820e3680fc&radius=1000&extensions=all&roadlevel=1&batch=false";
                    Gson gson = new Gson();
                    HttpClient client = new HttpClient();
                    HttpMethod method = new GetMethod(addressStr);
                    client.executeMethod(method);
                    responses = method.getResponseBodyAsString();
                    method.releaseConnection();
                    retList = gson.fromJson(responses, new TypeToken<Map<String, Object>>() {
                    }.getType());
                    Map<String, Object> ret = (Map<String, Object>) retList.get("regeocode");
                    String position = (String) ret.get("formatted_address");
                    map.put("position",position);
                }
            }else {
                //车辆不存在
                map.put("flag","0");
            }

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(map));
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
        return JSONUtil.toJSONString(map);
    }



    /**
     * 方法名称：sheetPlanByTruck
     * 内容摘要：根据车辆获取可以运输的计划编号(手动车辆派单页面)
     * @param request  request

     */
    @RequestMapping(value = "/sheetPlanByTruck")
    public void sheetPlanByTruck(HttpServletRequest request,HttpServletResponse response,String truckId) {
        List orderListTmp = null;
        List<T_Data_Transportation_OrderList> orderList = new ArrayList<T_Data_Transportation_OrderList>();
        try {
            T_Data_Truck truck = truckService.findTruckByID(truckId);
            //车厢类型
            String carriageTypeId = truck.getTruck_carriage_type_id();
            //管理者查询可以获取的派车单
            T_Data_OBD_Equipment_Truck_Binding truckBinding = obdEquipmentTruckBindingService.findOBDTruckBindingInfoByTruckID(truckId);
            //根据车辆定位信息和手机的定位信息进行判断
            if (truckBinding != null) {
                //车辆定位设备
                T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(truck.getPlate_number());
                if (truckImmediate!=null) {
                    //获取经纬度
                    Double latitude = truckImmediate.getLatitude();
                    Double longitude = truckImmediate.getLongitude();
                    //获取干线
                    List<T_Data_Truck_Transport_Line> transportLineList = truckTransportLineService.findTruckTransportByTruckID(truckId);
                    //获取车厢类型
                    if (carriageTypeId != null || carriageTypeId.trim().length() != 0) {
                        List<T_Master_Cargo_Truck_Type_Match> matchByTruckList = cargoTruckTypeService.findMatchByTruck(carriageTypeId);
                        //遍历订单
                        for (int i = 0; i < transportLineList.size(); i++) {
                            //获取常跑路线的起点和终点城市
                            String startCityId = transportLineList.get(i).getStart_city_id();
                            String endCityId = transportLineList.get(i).getEnd_city_id();
                            //遍历车辆匹配表
                            for (int k = 0; k < matchByTruckList.size(); k++) {
                                //货物类型id
                                String cargoTypeId = matchByTruckList.get(k).getCargoTypeId();
                                //根据货物类型获取合同
                                List<T_Data_Transportation_Contract> contractList = contractDaoMapper.findByCargoTypeId(cargoTypeId);
                                //遍历合同
                                for (int j = 0; j < contractList.size(); j++) {
                                    String contractId = contractList.get(j).getContract_id();
                                    //获取干线的起点和终点城市
                                    T_Master_Operate_Main_Line mainLine = operateMainLineDaoMapper.findOperateMainLineByStartCityAndFinishCity(startCityId, endCityId);
                                    if (mainLine != null) {
                                        String operate_main_line_id = mainLine.getOperate_main_line_id();
                                        if (contractId != null && operate_main_line_id != null) {
                                            //根据合同和干线获取可以操作的订单（调度数量<总重量） 且订单状态为审核通过
                                            orderListTmp = transportationOrderDaoMapper.findOrderByLineIdAndConId2(operate_main_line_id, contractId, latitude, longitude);
                                            //放到订单list中
                                            orderList.addAll(orderListTmp);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }else{//没有绑定的虚拟 设备---------------------
                T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber("晋AA0043");
                if (truckImmediate!=null) {
                    //获取经纬度
                    Double latitude = truckImmediate.getLatitude();
                    Double longitude = truckImmediate.getLongitude();
                    //获取干线
                    List<T_Data_Truck_Transport_Line> transportLineList = truckTransportLineService.findTruckTransportByTruckID(truckId);
                    //获取车厢类型
                    if (carriageTypeId != null || carriageTypeId.trim().length() != 0) {
                        List<T_Master_Cargo_Truck_Type_Match> matchByTruckList = cargoTruckTypeService.findMatchByTruck(carriageTypeId);
                        //遍历订单
                        for (int i = 0; i < transportLineList.size(); i++) {
                            //获取常跑路线的起点和终点城市
                            String startCityId = transportLineList.get(i).getStart_city_id();
                            String endCityId = transportLineList.get(i).getEnd_city_id();
                            //遍历车辆匹配表
                            for (int k = 0; k < matchByTruckList.size(); k++) {
                                //货物类型id
                                String cargoTypeId = matchByTruckList.get(k).getCargoTypeId();
                                //根据货物类型获取合同
                                List<T_Data_Transportation_Contract> contractList = contractDaoMapper.findByCargoTypeId(cargoTypeId);
                                //遍历合同
                                for (int j = 0; j < contractList.size(); j++) {
                                    String contractId = contractList.get(j).getContract_id();
                                    //获取干线的起点和终点城市
                                    T_Master_Operate_Main_Line mainLine = operateMainLineDaoMapper.findOperateMainLineByStartCityAndFinishCity(startCityId, endCityId);
                                    if (mainLine != null) {
                                        String operate_main_line_id = mainLine.getOperate_main_line_id();
                                        if (contractId != null && operate_main_line_id != null) {
                                            //根据合同和干线获取可以操作的订单（调度数量<总重量） 且订单状态为审核通过
                                            orderListTmp = transportationOrderDaoMapper.findOrderByLineIdAndConId2(operate_main_line_id, contractId, latitude, longitude);
                                            //放到订单list中
                                            orderList.addAll(orderListTmp);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            List<Map<String, String>> list=new ArrayList<Map<String, String>>();
            for (T_Data_Transportation_OrderList plan:orderList){
                HashMap<String, String> hashMap = new HashMap<String, String>();
                String planNumber = plan.getSchedule_plan_number();
                hashMap.put("id",planNumber);
                hashMap.put("name",planNumber);
                list.add(hashMap);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }


    /**
     * 方法名称： manualTruckDetailsByPlan
     * 内容摘要：手动车辆派单根据编号获取详情
     * @param request  request
     */
    @RequestMapping(value = "/manualTruckDetailsByPlan")
    public void manualTruckDetailsByPlan(HttpServletRequest request,HttpServletResponse response,String planNumber) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        try {
            T_Data_Transportation_Plan order = transportationOrderService.findBySchedulePlanNumber(planNumber);
            String contract_id = order.getContract_id();
            //合同
            T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
            String cargo_type_id = contract.getCargo_type_id();

            HashMap map = new HashMap();
            //货物名称
            T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
            if (cargoType!=null){
                map.put("cargoTypeName", cargoType.getCargoTypeName());
            }else {
                map.put("cargoTypeName", "null");
            }
            String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
            T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
            if (cargoType!=null){
                map.put("unloadingName", byId.getCargo_name());
            }else {
                map.put("unloadingName","null");
            }
            //干线
            String lineId = order.getOperate_main_line_id();
            T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(lineId);
            if (mainLine!=null){
                map.put("lineName", mainLine.getOperate_main_line_name());
            }else {
                map.put("lineName", "null");
            }
            //装货货场
            String cargoYardId = order.getLoading_cargo_yard_id();
            T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
            if (cargoYard!=null){
                map.put("loadingName", cargoYard.getCargo_name());
            }else{
                map.put("loadingName", "nulll");
            }
            map.put("unRedistributeTotal", String.valueOf(order.getCargo_total()-order.getSchedule_cargo_total()));
            map.put("price", order.getTransport_unit_price());
            map.put("beginDate", order.getLoading_begin_date());
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(map));
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }
}