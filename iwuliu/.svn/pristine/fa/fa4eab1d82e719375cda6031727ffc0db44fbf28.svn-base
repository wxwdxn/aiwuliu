/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ScheduleSheetController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-14
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 类 名 称：ScheduleSheetController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/ScheduleSheetManager")
public class ScheduleSheetPlanController {

    Logger logger=Logger.getLogger("ScheduleSheetController.class");
    @Resource
    private SchedulePlanService schedulePlanService;
    @Resource
    private TruckService truckService ;
    @Resource
    private TruckCarriageTypeService truckCarriageTypeService;
    @Resource
    private ScheduleSheetService scheduleSheetService;
    @Resource
    private DicdataService dicdataService;
    @Resource
    private TruckImmediateService truckImmediateService;
    @Resource
    private TransportationOrderService orderService;
    @Resource
    private CargoService cargoService;
    @Resource
    private OperateMainLineService operateMainLineService;
    @Resource
    private CargoTypeService cargoTypeService;
    @Resource
    private TransportationContractService transportationContractService;
    /**
     *保存货物运输计划调度单信息表
     * @param request
     * @param response
     * @param modelMap
     */
    @ResponseBody
    @RequestMapping(value = "/addScheduleSheet")
    public T_Data_JsonResult addScheduleSheet(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        T_Data_JsonResult result = new T_Data_JsonResult();
        String parameter = request.getParameter("list");
        int i=0;
        try{
           i= schedulePlanService.addScheduleSheet(parameter,"U:"+request.getSession().getAttribute("username"));
            if (i==1){
                result.setResult(1);
            }
        }catch (Exception e){
            result.setResult(2);
            logger.info(e.getMessage());
        }
        return  result;
    }

    /**
     *通过所属计划编号查询已查看的订单
     * @param request
     * @param response
     * @param modelMap
     */
    @RequestMapping(value = "findLookSheet")
    public void findLookSheet(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        List<T_Sys_Dicdata> dicdataList_status = null;
        List<T_Sys_Dicdata> dicdataList_status2 = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String parameter = request.getParameter("scheduleNumber");
        try {
            List<T_Data_Transportation_Schedule_Sheet_Plan> List = schedulePlanService.findLookSheet(parameter);
            if (List.size()!=0){
                for (T_Data_Transportation_Schedule_Sheet_Plan sheetPlan:List) {
                    HashMap map = new HashMap();
                    String scheduleTruckId = sheetPlan.getScheduleTruckId();
                    T_Data_Truck truck = truck = truckService.findTruckByID(scheduleTruckId);
                    //牌号
                    String plateNumber = truck.getPlate_number();
                    map.put("plateNumber", plateNumber);
                    String typeId = truck.getTruck_carriage_type_id();
                    String typeName = truckCarriageTypeService.findTruckCarriageTypeByID(typeId).getTruck_carriage_type_name();
                    //车厢类型
                    map.put("typeName", typeName);
                    //运输状态
                    String status = scheduleSheetService.findMinStatusByTruckId(truck.getTruck_id());
                    if (status != null) {
                        dicdataList_status = this.dicdataService.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", status);
                        String statusName = dicdataList_status.get(0).getDicdata_name();
                        map.put("statusName", statusName);
                    } else {
                        //车辆无调度单
                        map.put("statusName", "已完成");
                    }
                    //经纬度查询车辆位置
                    T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plateNumber);
                    if (truckImmediate != null) {
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
                        map.put("position", position);
                    }
                    //调度单计划的状态
                    int status2 = sheetPlan.getStatus();
                    dicdataList_status2 = this.dicdataService.findAllDicdataByID("8D931010B1C844B6BAC9ED5A753D276D", String.valueOf(status2));
                    String stat = dicdataList_status2.get(0).getDicdata_name();
                    map.put("status", stat);
                    list.add(map);
                }
            }

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }

    }




    /**
     * 通过所属计划编号查询未查看的订单
     * @param request
     * @param response
     * @param modelMap
     */
    @RequestMapping(value = "findUnLookSheet")
    public void findUnLookSheet(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        List<T_Sys_Dicdata> dicdataList_status = null;
        List<T_Sys_Dicdata> dicdataList_status2 = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String parameter = request.getParameter("scheduleNumber");

        try {
            List<T_Data_Transportation_Schedule_Sheet_Plan> List = schedulePlanService.findUnLookSheet(parameter);
            if (List.size()!=0){
                for (T_Data_Transportation_Schedule_Sheet_Plan sheetPlan:List) {
                    HashMap map = new HashMap();
                    String planNumber = sheetPlan.getPlanNumber();
                    map.put("planNumber", planNumber);
                    String scheduleTruckId = sheetPlan.getScheduleTruckId();
                    T_Data_Truck truck = truck = truckService.findTruckByID(scheduleTruckId);
                    //牌号
                    String plateNumber = truck.getPlate_number();
                    map.put("plateNumber", plateNumber);
                    String typeId = truck.getTruck_carriage_type_id();
                    String typeName = truckCarriageTypeService.findTruckCarriageTypeByID(typeId).getTruck_carriage_type_name();
                    //车厢类型
                    map.put("typeName", typeName);
                    //运输状态
                    String status = scheduleSheetService.findMinStatusByTruckId(truck.getTruck_id());
                    if (status != null) {
                        dicdataList_status = this.dicdataService.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", status);
                        String statusName = dicdataList_status.get(0).getDicdata_name();
                        map.put("statusName", statusName);
                    } else {
                        //车辆无调度单
                        map.put("statusName", "已完成");
                    }
                    //经纬度查询车辆位置
                    T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plateNumber);
                    if (truckImmediate != null) {
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
                        map.put("position", position);
                    }
                    //调度单计划的状态
                    int status2 = sheetPlan.getStatus();
                    dicdataList_status2 = this.dicdataService.findAllDicdataByID("8D931010B1C844B6BAC9ED5A753D276D", String.valueOf(status2));
                    String stat = dicdataList_status2.get(0).getDicdata_name();
                    map.put("status", stat);
                    list.add(map);
                }
            }

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }

    }
    /**
     * 方法名称：OrderTableDel
     * 内容摘要：撤销运单。
     * @param request  request
     * @param response response
     * @param OrderTableID OrderTableID
     * @param model
     */
    @RequestMapping(value = "OrderTableDel")
    @ResponseBody
    public T_Data_JsonResult OrderTableDel(String OrderTableID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String [] planNumber=OrderTableID.split(",");
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            for (int i=0;i<planNumber.length;i++){
                schedulePlanService.deleteScheduleSheet(planNumber[i]);
            }
            result.setResult(0);
        } catch (Exception e) {
            result.setResult(1);
            logger.info( e.getMessage());
        }
        return  result;
    }


    /**
     *通过车辆id查询已查看的订单
     * @param request
     * @param response
     * @param modelMap
     */
    @RequestMapping(value = "findLookSheetByTruckId")
    public void findLookSheetByTruckId(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<T_Sys_Dicdata> dicdataList_status2 = null;
        String truckId = request.getParameter("truckId");
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<T_Data_Transportation_Schedule_Sheet_Plan> List = schedulePlanService.findLookSheetByTruckId(truckId);
            if (List.size()!=0){
                for (T_Data_Transportation_Schedule_Sheet_Plan sheetPlan:List) {
                    HashMap map = new HashMap();
                    String schedulePlanNumber = sheetPlan.getSchedule_plan_number();
                    map.put("schedulePlanNumber", schedulePlanNumber);
                    T_Data_Transportation_Plan order = orderService.findBySchedulePlanNumber(schedulePlanNumber);
                    String contract_id = order.getContract_id();
                    //合同
                    T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
                    String cargo_type_id = contract.getCargo_type_id();
                    T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                    //货物名称
                    map.put("cargoTypeName", cargoType.getCargoTypeName());
                    String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                    T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
                    map.put("unloadingName", byId.getCargo_name());
                    //干线
                    String lineId = order.getOperate_main_line_id();
                    String lineName = operateMainLineService.findOperateMainLineById(lineId).getOperate_main_line_name();
                    //装货货场
                    String cargoYardId = order.getLoading_cargo_yard_id();
                    T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                    map.put("lineName", lineName);
                    map.put("lastUpdate", format.format(sheetPlan.getBeginScheduleTime()));
                    map.put("loadingName", cargoYard.getCargo_name());
                    double value = order.getCargo_total() - order.getSchedule_cargo_total();
                    if(value<=0){
                        map.put("unRedistributeTotal", "0");
                    }else {
                        map.put("unRedistributeTotal", String.valueOf(value));
                    }
                    map.put("price", order.getTransport_unit_price());
                    map.put("beginDate", order.getLoading_begin_date());
                    //调度单计划的状态
                    int status2 = sheetPlan.getStatus();
                    dicdataList_status2 = this.dicdataService.findAllDicdataByID("8D931010B1C844B6BAC9ED5A753D276D", String.valueOf(status2));
                    String stat = dicdataList_status2.get(0).getDicdata_name();
                    map.put("status", stat);
                    list.add(map);
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }

    }




    /**
     * 通过车辆id查询未查看的订单
     * @param request
     * @param response
     * @param modelMap
     */
    @RequestMapping(value = "findUnLookSheetByTruckId")
    public void findUnLookSheetByTruckId(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<T_Sys_Dicdata> dicdataList_status2 = null;
        String truckId = request.getParameter("truckId");
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<T_Data_Transportation_Schedule_Sheet_Plan> List = schedulePlanService.findUnLookSheetByTruckId(truckId);
            if (List.size()!=0){
                for (T_Data_Transportation_Schedule_Sheet_Plan sheetPlan:List) {
                    HashMap map = new HashMap();
                    String schedulePlanNumber = sheetPlan.getSchedule_plan_number();
                    map.put("planNumber", sheetPlan.getPlanNumber());
                    map.put("schedulePlanNumber", schedulePlanNumber);
                    T_Data_Transportation_Plan order = orderService.findBySchedulePlanNumber(schedulePlanNumber);
                    String contract_id = order.getContract_id();
                    //合同
                    T_Data_Transportation_Contract contract = transportationContractService.findById(contract_id);
                    String cargo_type_id = contract.getCargo_type_id();
                    T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargo_type_id);
                    //货物名称
                    map.put("cargoTypeName", cargoType.getCargoTypeName());
                    String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                    T_Master_Cargo_Yard byId = cargoService.findById(unloading_cargo_yard_id);
                    map.put("unloadingName", byId.getCargo_name());
                    //干线
                    String lineId = order.getOperate_main_line_id();
                    String lineName = operateMainLineService.findOperateMainLineById(lineId).getOperate_main_line_name();
                    //装货货场
                    String cargoYardId = order.getLoading_cargo_yard_id();
                    T_Master_Cargo_Yard cargoYard = cargoService.findById(cargoYardId);
                    map.put("lastUpdate", format.format(sheetPlan.getBeginScheduleTime()));
                    map.put("lineName", lineName);
                    map.put("loadingName", cargoYard.getCargo_name());
                    double value = order.getCargo_total() - order.getSchedule_cargo_total();
                    if(value<=0){
                        map.put("unRedistributeTotal", "0");
                    }else {
                        map.put("unRedistributeTotal", String.valueOf(value));
                    }

                    map.put("price", order.getTransport_unit_price());
                    map.put("beginDate", order.getLoading_begin_date());
                    //调度单计划的状态
                    int status2 = sheetPlan.getStatus();
                    dicdataList_status2 = this.dicdataService.findAllDicdataByID("8D931010B1C844B6BAC9ED5A753D276D", String.valueOf(status2));
                    String stat = dicdataList_status2.get(0).getDicdata_name();
                    map.put("status", stat);
                    list.add(map);
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }
}