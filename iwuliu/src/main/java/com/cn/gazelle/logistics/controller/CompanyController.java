/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CompanyController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：物流公司管理页面
 * 设计文件：
 * 完成日期：2016-02-19
 * 作    者：YK
 * 内容摘要：物流公司管理页面的CRUD
 */

package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.TransportationOrder_Message;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 类 名 称: CompanyController
 * 内容摘要: 物流公司管理页面的CRUD
 * 方法描述：该类有2个方法：
 * 01 companys                  所有的公司列表 不分页 用于编辑回显
 * 02 companyTruckList          所有的公司列表下的车辆
 *
 * @author YK
 */
@Controller
@RequestMapping(value = "/companyManager")
public class CompanyController {
    Logger logger = Logger.getLogger(CompanyController.class);

    @Resource
    private CompanyService companyService;
    @Resource
    private TruckService truckService;
    @Resource
    private TruckImmediateService truckImmediateService;
    @Resource
    private SysInfoService sysInfoService;
    @Resource
    private ScheduleSheetService scheduleSheetService;
    @Resource
    private DicdataService dicdataService;


    /**
     * 方法名称：manualTruckPlan
     * 内容摘要：跳转手动车辆分配计划页面。
     * @return string planNumber。
     */
    @RequestMapping(method = RequestMethod.GET, value = "manualTruckPlan")
    public String manualTruckPlan(ModelMap model, String truckId) {
        return "operationManager/vehiclePlanManuallyAssign/vehiclePlanManuallyAssign";
    }

    /**
     * 方法名称：manualTruckPlanDetails
     * 内容摘要：跳转手动车辆详情计划页面。
     * @return string planNumber。
     */
    @RequestMapping(method = RequestMethod.GET, value = "manualTruckPlanDetails")
    public String manualTruckPlanDetails(ModelMap model, String truckId) {
        return "operationManager/vehiclePlanDetail/vehiclePlanDetail";
    }

    /**
     * 方法名称：companyTruckList
     * 内容摘要：所有的公司列表下的车辆
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "/companyTruckList")
    public void companyTruckList(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Company> companys = null;
        int i = 0;
        Map<String, List> result = new HashMap();
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            companys = companyService.findCompanyList();
            if (companys.size() > 0 && i < companys.size()) {
                for (T_Data_Company company : companys) {
                    Map results = new HashMap();
                    List<T_Data_Truck> truckList = truckService.findTruckByOwnerMemberID(company.getCompany_id());
                    if (truckList.size() != 0) {
                        results.put("id", company.getCompany_id());
                        results.put("name", company.getCompany_name());
                        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                        for (T_Data_Truck truck : truckList) {
                            Map map = new HashMap();
                            String plateNumber = truck.getPlate_number();
                            T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plateNumber);
                            if (truckImmediate != null) {
                                Date lastUpdate = truckImmediate.getLast_update();
                                long lastUpdateTime = lastUpdate.getTime();
                                long time = new Date().getTime();
                                long flag = time - lastUpdateTime;
                                //更新时间和系统时间对比 5分钟
                                if (flag > 300000) {
                                    //车辆车辆熄火 0
                                    map.put("flag", "2");
                                } else if(flag < 300000) {
                                    //车辆行驶中 1
                                    map.put("flag", "1");
                                }
                                String alarm_type = truckImmediate.getAlarm_type();
                                if (alarm_type!=null){
                                    //根据警报时间
                                    Date alarmTime = truckImmediate.getAlarm_time();
                                    long alarmTimeTime = alarmTime.getTime();
                                    long times = time - alarmTimeTime;
                                    if (times<300000){
                                        //车辆报警
                                        map.put("flag", "3");
                                    }

                                }
                                map.put("plateNumber", plateNumber);
                                list.add(map);
                            }else {
                                //车辆车辆熄火 0
                                map.put("flag", "2");
                                map.put("plateNumber", plateNumber);
                                list.add(map);
                            }
                        }
                        results.put("listNumber", list);
                        lists.add(results);
                    }

                }
            }
            result.put("list", lists);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：manualTruckSheet
     * 内容摘要：手动车辆派单
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "/manualTruckSheet")
    public void manualTruckSheet(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Company> companys = null;
        List<T_Sys_Dicdata> dicdataList_status = null;
        int i = 0;
        Map<String, List> result = new HashMap();
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            companys = companyService.findCompanyList();
            if (companys.size() > 0 && i < companys.size()) {
                for (T_Data_Company company : companys) {
                    Map results = new HashMap();
                    List<T_Data_Truck> truckList = truckService.findTruckByOwnerMemberID(company.getCompany_id());
                    if (truckList.size() != 0) {
                        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                        for (T_Data_Truck truck : truckList) {
                            Integer value=0;
                            Map map = new HashMap();
                            String plateNumber = truck.getPlate_number();
                            map.put("plateNumber",plateNumber);
                            map.put("truckId",truck.getTruck_id());
                            String loadWeight = truck.getLoad_weight();
                            map.put("loadWeight",loadWeight);
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
                                map.put("flag","2");//车辆拒单中
                            }
                            String status = scheduleSheetService.findMinStatusByTruckId(truck.getTruck_id());
                            //运输状态
                            if (status!=null){
                                dicdataList_status = this.dicdataService.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", status);
                                String statusName = dicdataList_status.get(0).getDicdata_name();
                                map.put("statusName",statusName);
                                value = Integer.valueOf(status);
                                if (value<4){
                                    map.put("flag","1");//车辆有未完成订单
                                }
                            }else {
                                //车辆无调度单
                                map.put("statusName","已完成");
                            }
                            T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plateNumber);
                            //经纬度查询车辆位置
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
                            }else {
                                map.put("position","");
                            }
                            if (value>4 ||status==null){
                                //状态大于4  已完成
                                lists.add(map);
                            }
                        }
                    }
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：manualUnFinishedTruckSheet
     * 内容摘要：手动车辆派单不可用
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "/manualUnFinishedTruckSheet")
    public void manualUnFinishedTruckSheet(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Company> companys = null;
        List<T_Sys_Dicdata> dicdataList_status = null;
        int i = 0;
        Map<String, List> result = new HashMap();
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            companys = companyService.findCompanyList();
            if (companys.size() > 0 && i < companys.size()) {
                for (T_Data_Company company : companys) {
                    Map results = new HashMap();
                    List<T_Data_Truck> truckList = truckService.findTruckByOwnerMemberID(company.getCompany_id());
                    if (truckList.size() != 0) {
                        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                        for (T_Data_Truck truck : truckList) {
                            Integer value=10;
                            Map map = new HashMap();
                            String plateNumber = truck.getPlate_number();
                            map.put("plateNumber",plateNumber);
                            map.put("truckId",truck.getTruck_id());
                            String loadWeight = truck.getLoad_weight();
                            map.put("loadWeight",loadWeight);
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
                                map.put("flag","2");//车辆拒单中
                            }
                            String status = scheduleSheetService.findMinStatusByTruckId(truck.getTruck_id());

                            //运输状态
                            if (status!=null){
                                value= Integer.valueOf(status);
                                dicdataList_status = this.dicdataService.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", status);
                                String statusName= dicdataList_status.get(0).getDicdata_name();
                                map.put("statusName",statusName);
                                if (value<4){
                                    map.put("flag","1");//车辆有未完成订单
                                }
                            }else {
                                //车辆无调度单
                                map.put("statusName","已完成");
                            }
                            T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plateNumber);
                            //经纬度查询车辆位置
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
                            }else {
                                map.put("position","");
                            }
                            if (value<4 && status!=null){
                                //未完成的
                                lists.add(map);
                            }
                        }
                    }
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称： manualTruckDetails
     * 内容摘要：车辆手动派单详情
     * @param request  request
     */
    @RequestMapping(value = "/manualTruckDetails")
    public void manualOrderDetails(HttpServletRequest request,HttpServletResponse response,String truckId) {
        List<T_Sys_Dicdata> dicdataList_status = null;
        try {

            T_Data_Truck dataTruck = truckService.findTruckByID(truckId);
            HashMap map = new HashMap();
            String plateNumber = dataTruck.getPlate_number();
            String loadWeight = dataTruck.getLoad_weight();
            map.put("loadWeight",loadWeight);
            map.put("plateNumber",plateNumber);
            String status = scheduleSheetService.findMinStatusByTruckId(truckId);
            //运输状态
            if (status!=null){
                dicdataList_status = this.dicdataService.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", status);
                String statusName = dicdataList_status.get(0).getDicdata_name();
                map.put("status",statusName);
            }else {
                //车辆无调度单
                map.put("status","已完成");
            }
            T_Data_Truck_Immediate truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plateNumber);
            //经纬度查询车辆位置
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

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(map));
        } catch (Exception e) {
            logger.error(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }


    /**
     * 方法名称：companys
     * 内容摘要：所有的公司列表 不分页 用于编辑回显。
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "companys")
    public void companys(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Company> companys = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            companys = companyService.findCompanyList();
            if (companys.size() > 0 && i < companys.size()) {
                for (T_Data_Company company : companys) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", company.getCompany_id());
                    results.put("name", company.getCompany_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：oneCompanyTruckList
     * 内容摘要：公司下的车辆列表 不分页 用于编辑回显。
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "oneCompanyTruckList")
    public void oneCompanyTruckList(HttpServletRequest request, HttpServletResponse response,String companyId) {
        List<T_Data_Truck> truckList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
            truckList = truckService.findTruckByOwnerMemberID(companyId);
            if (truckList.size() > 0 && i < truckList.size()) {
                for (T_Data_Truck truck : truckList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", truck.getTruck_id());
                    results.put("name", truck.getPlate_number());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：CompanyShippers
     * 内容摘要：公司货主 不分页 用于编辑回显。
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "CompanyShippers")
    public void CompanyShippers(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Company> companyShipperList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            // 封装查询结果
           companyShipperList = companyService.findCompanyShipperList();
            if (companyShipperList.size() > 0 && i < companyShipperList.size()) {
                for (T_Data_Company shipper : companyShipperList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", shipper.getCompany_id());
                    results.put("name", shipper.getCompany_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }
}
