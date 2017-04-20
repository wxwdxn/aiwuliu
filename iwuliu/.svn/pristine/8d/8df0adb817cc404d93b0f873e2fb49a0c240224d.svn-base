/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TruckHawkeyeFunctionController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-08-10
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.zgtUtil.GPSUtile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称：TruckHawkeyeFuntionController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/truckHawkeyeFunctionManager")
public class TruckHawkeyeFunctionController {

    @Resource
    private TruckDaoMapper truckDaoMapper;
    @Resource
    private OBDequipmentDaoMapper obDequipmentDaoMapper;
    @Resource
    private TruckPositionDaoMapper truckPositionDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;
    @Resource
    private CompanyDaoMapper companyDaoMapper;
    @Resource
    private TruckImmediateService truckImmediateService;
    @Resource
    private TruckCarriageTypeService carriageTypeService;
    @Resource
    private  OBDEquipmentTruckBindingDaoMapper obdEquipmentTruckBindingDaoMapper;
    @Resource
    private DicdataService dicdataService;
    @Resource
    private TruckTransportLineService truckTransportLineService;
    @Resource
    private TruckModelService truckModelService;
    @Resource
    private OperateMainLineDaoMapper operateMainLineDaoMapper;
    @Resource
    private ScheduleSheetDaoMapper scheduleSheetDaoMapper;
    @Resource
    private TransportationOrderDaoMapper transportationOrderDaoMapper;
    @Resource
    private TransportationContractService contractService;
    @Resource
    private CargoTypeDaoMapper cargoTypeDaoMapper;
    @Resource
    private DispatchSheetDaoMapper dispatchSheetDaoMapper;
    Logger logger = Logger.getLogger(TruckHawkeyeFunctionController.class);

    //车辆鹰眼功能页面
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "truckHawkeyeFunctionManager/truckHawkeyeFunctionManager";
    }

    //根据车牌号模糊查询     结构树
    @RequestMapping(value = "getPosition")
    public Map getPosition(@RequestParam(required = false, defaultValue = "") String plate_number, HttpServletRequest request, HttpServletResponse response, ModelMap model){
        // 封装查询结果
        Map<String, List> result = new HashMap();
        //获取车辆
       List<T_Data_Truck>  truckList = null;

        try {
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
            String  detailInfo = request.getParameter("plate_number");
            detailInfo = URLDecoder.decode(detailInfo, "UTF-8");
            //车牌号不为空
            if(!detailInfo.equals("")){
                //车主id不为空
                truckList = truckDaoMapper.findTruckByMuHuNumberAndOwerNotNull(plate_number);
                if (truckList.size()!=0){
                    //用于除重公司Id
                    List ObjList=new ArrayList();
                    for(T_Data_Truck truck :truckList) {
                        Map map = new HashMap();
                        String owner_member_id = truck.getOwner_member_id();
                        if (owner_member_id != null) {
                            //如果不包含公司id
                            if (!ObjList.contains(owner_member_id)){
                                ObjList.add(owner_member_id);
                                T_Data_Company company = companyDaoMapper.findCompanyByID(owner_member_id);
                                map.put("id", company.getCompany_id());
                                map.put("name", company.getCompany_name());
                                List<T_Data_Truck> dataTruckList = truckDaoMapper.findTruckByOwnerMemberIdAndPlateNumber(owner_member_id, plate_number);
                                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                                for (T_Data_Truck dataTruck:dataTruckList){
                                    Map map2 = new HashMap();
                                    map2.put("plateNumber",dataTruck.getPlate_number());
                                    list.add(map2);
                                }
                                map.put("listNumber",list);
                                lists.add(map);
                            }
                        }
                    }
                }else{//查不到车辆只显示公司
                    List<T_Data_Company> companyList = companyDaoMapper.findCompanyList();
                    for (T_Data_Company company:companyList){
                        Map map = new HashMap();
                        map.put("id", company.getCompany_id());
                        map.put("name", company.getCompany_name());
                        lists.add(map);
                    }
                }
                result.put("list",lists);
            }else{
                //车牌号为空 查询所有的车辆公司车辆
                List<T_Data_Company> companyList = companyDaoMapper.findCompanyList();
                for (T_Data_Company company : companyList) {
                    Map results = new HashMap();
                    results.put("id", company.getCompany_id());
                    results.put("name", company.getCompany_name());
                    List<T_Data_Truck> truckLists = truckDaoMapper.findTruckByOwnerMemberID(company.getCompany_id());
                    if (truckLists.size()!=0){
                        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                        for (T_Data_Truck truck:truckLists){
                            Map map = new HashMap();
                            String plateNumber = truck.getPlate_number();
                            map.put("plateNumber",plateNumber);
                            list.add(map);
                        }
                        results.put("listNumber",list);
                    }
                    lists.add(results);
                }
                result.put("list",lists);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.error("获取经纬度失败"+e.getMessage());
        }
        return result;
    }


    //根据车牌号 时间 查询
    @RequestMapping(value = "getPositiones")
    public Map getPositiones(@RequestParam(required = true, defaultValue = "") String[] arr, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        // 封装查询结果
        Map<String, List> result = new HashMap();
        //获取车辆
        try {
            String  detailInfo = request.getParameter("arr");
            detailInfo = URLDecoder.decode(detailInfo, "UTF-8");
            String[] split = detailInfo.split(",");
            String plate_number = split[0];
            String start_time = split[1];
            String end_time = split[2];
            T_Data_Truck truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            double latitude = 0;
            double longitude =0;
            Map resultse = new HashMap();
            List list = new ArrayList<Map<String, String>>();
            List<Map<String, String>> liste = new ArrayList<Map<String, String>>();
            if (truck!= null) {
                String truck_id = truck.getTruck_id();
                //根据车辆id获取绑定的OBD设备
                T_Data_OBD_Equipment_Truck_Binding truckBinding = obdEquipmentTruckBindingDaoMapper.findOBDTruckBindingInfoByTruckID(truck_id);
                if (truckBinding!=null){
                    String equipment_id = truckBinding.getEquipment_id();
                    T_Data_OBD_Equipment obdEquipment = obDequipmentDaoMapper.findById(equipment_id);
                    if (obdEquipment != null) {
                        //获取OBD设备的id
                        String obdId = obdEquipment.getObd_id();
                        //根据obd设备id获取最新的车辆位置 默认查询10条根据设备接收时间
                        List<T_Data_Truck_Position> lastTruckPositionByObdId = truckPositionDaoMapper.findTruckPositionDateByObdId(obdId,start_time,end_time);
                        if (lastTruckPositionByObdId.size() != 0) {
                            for (T_Data_Truck_Position truckPosition : lastTruckPositionByObdId) {
                                Map results = new HashMap();
                                //获取最新的经纬度

                                longitude = Double.valueOf(truckPosition.getLongitude());
                                latitude = Double.valueOf(truckPosition.getLatitude());
                                double[] latlng = new double[2];
                                double[] latLon = GPSUtile.transform(latitude, longitude, latlng);
                                double lat = latLon[0];
                                double lon = latLon[1];
                                results.put("latitude",lat );
                                results.put("longitude", lon);
                                liste.add(results);
                            }
                            resultse.put("LatLon", liste);
                            String manager_member_id = truck.getManager_member_id();
                            T_Data_Person person = personDaoMapper.findPersonByID(manager_member_id);
                            if (person!=null){
                                String personName = person.getPerson_name();
                                String phone = person.getPerson_mobile_phone();
                                //车辆管理者
                                resultse.put("personName",personName);
                                resultse.put("phone",phone);
                                //车牌
                                resultse.put("plateNumber",plate_number);

                                //车厢类型
                                String typeId = truck.getTruck_carriage_type_id();
                                String typeName = carriageTypeService.findTruckCarriageTypeByID(typeId).getTruck_carriage_type_name();
                                resultse.put("carriageTypeName",typeName);

                                T_Data_Truck_Immediate  truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plate_number);

                                if(truckImmediate!=null){
                                    //车辆品牌
                                    resultse.put("brandName",truckImmediate.getTruck_brand_name());
                                   //速度
                                    resultse.put("speed",truckImmediate.getSpeed()+"km/h");
                                }else {
                                    //车辆品牌
                                    resultse.put("brandName","暂无");
                                }
                            }
                            resultse.put("flag","0");
                        }else {
                            //查询对应时间行驶距离较短
                            resultse.put("flag","1");
                        }
                    }
                }else {
                    //车辆没有绑定设备
                    resultse.put("flag","3");
                }
            }else{
                //车辆不存在
                resultse.put("flag","2");
            }
            list.add(resultse);
            result.put("list", list);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
            logger.info("获取经纬度成功");

        } catch (Exception e) {
            logger.error("获取经纬度失败" + e.getMessage());
        }
        return result;
    }

    //查询多个车辆
    @RequestMapping(value = "getPositionList")
    @ResponseBody
    public void getPositionList(@RequestParam(required = false, defaultValue = "") String[] plateNumberList, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
        // 封装查询结果
        Map<String, List> result = new HashMap();
        //获取车辆
        T_Data_Truck_Immediate truckImmediate  = null;
        try {
            DecimalFormat df1 = new DecimalFormat("0.00");
            String  detailInfo = request.getParameter("plateNumberList");
            detailInfo = URLDecoder.decode(detailInfo, "UTF-8");
            String[] split = detailInfo.split(",");
            List list=new ArrayList<Map<String,String>>();
            for (String plate_number:split){
                HashMap map=new HashMap();
                //车辆及时信息表
                truckImmediate = truckImmediateService.findTruckImmediateByPlateNumber(plate_number);
                if (truckImmediate!=null){
                    T_Data_Truck truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
                    if (truck!=null){
                        String manager_member_id = truck.getManager_member_id();
                        T_Data_Person person = personDaoMapper.findPersonByID(manager_member_id);
                        if (person!=null){
                            String personName = person.getPerson_name();
                            String phone = person.getPerson_mobile_phone();
                            Date lastUpdate = truckImmediate.getLast_update();
                            Double direction = Double.valueOf(truckImmediate.getDirection())-90;
                            long lastUpdateTime = lastUpdate.getTime();
                            long time = new Date().getTime();
                            long flag = time - lastUpdateTime;
                            //更新时间和系统时间对比 5分钟
                            if (flag>300000){
                                //车辆车辆熄火 0
                                map.put("flag","2");
                                map.put("speed",0+"km/h");
                            }else {
                                //车辆行驶中 1
                                map.put("flag","1");
                                map.put("speed",truckImmediate.getSpeed()+"km/h");
                            }
                            String alarm_type = truckImmediate.getAlarm_type();
                            map.put("alarm","无");
                            if (alarm_type!=null){
                                //根据警报时间
                                Date alarmTime = truckImmediate.getAlarm_time();
                                long alarmTimeTime = alarmTime.getTime();
                                long times = time - alarmTimeTime;
                                if (times<300000){
                                    //车辆报警
                                    map.put("flag", "3");
                                    List<T_Sys_Dicdata> allDicdataByCode = dicdataService.findAllDicdataByCode("0F890349CF774AA18ECB0D45E79C8F0C", alarm_type);
                                    String alarm = allDicdataByCode.get(0).getDicdata_name();
                                    map.put("alarm",alarm);
                                    map.put("speed",truckImmediate.getSpeed()+"km/h");
                                }
                            }
                            //车辆管理者
                            map.put("personName",personName);
                            map.put("phone",phone);
                            //车牌
                            map.put("plateNumber",plate_number);
                            //车辆品牌
                            map.put("brandName",truckImmediate.getTruck_brand_name());
                            //车厢类型
                            map.put("carriageTypeName",truckImmediate.getTruck_carriage_type_name());
                            double longitude = truckImmediate.getLongitude();
                            double latitude = truckImmediate.getLatitude();
                            double[] latlng = new double[2];
                            double[] latLon = GPSUtile.transform(latitude, longitude, latlng);
                            double lat = latLon[0];
                            double lon = latLon[1];
                            map.put("latitude",lat);
                            map.put("longitude",lon);
                            map.put("direction",direction);
                            //油耗
                            double fuel_consumption_statistics = truckImmediate.getFuel_consumption_statistics();
                            map.put("fuelConsumption",df1.format(fuel_consumption_statistics));
                            //胎压
                            double tire_pressure = truckImmediate.getTire_pressure();
                            map.put("tirePressure",tire_pressure);
                            list.add(map);
                        }
                    }
                }else {
                    T_Data_Truck truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
                    String carriageTypeId = truck.getTruck_carriage_type_id();
                    String typeName = carriageTypeService.findTruckCarriageTypeByID(carriageTypeId).getTruck_carriage_type_name();
                    if (truck!=null){
                        String manager_member_id = truck.getManager_member_id();
                        T_Data_Person person = personDaoMapper.findPersonByID(manager_member_id);
                        if (person!=null){
                            String personName = person.getPerson_name();
                            String phone = person.getPerson_mobile_phone();
                            //车辆车辆熄火 0
                            map.put("flag","2");
                            //车辆管理者
                            map.put("personName",personName);
                            map.put("phone",phone);
                            //车牌
                            map.put("plateNumber",plate_number);
                            //车辆品牌
                            map.put("brandName","");
                            //车厢类型
                            map.put("carriageTypeName",typeName);
                            //速度
                            map.put("speed",0+"km/h");
                            map.put("latitude",39.90923);
                            map.put("longitude",116.397428);
                            map.put("alarm","暂无");
                            //油耗
                            map.put("fuelConsumption","暂无");
                            //胎压
                            map.put("tirePressure","暂无");
                            list.add(map);
                        }
                    }
                }
            }
            result.put("list",list);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
            logger.info("获取经纬度成功");

        } catch (Exception e) {
            logger.error("获取经纬度失败"+e.getMessage());
        }
    }

    //根据车牌获取车辆相关信息
    @RequestMapping(value = "getTruckInfo")
    @ResponseBody
    public void getTruckInfo(@RequestParam(required = false, defaultValue = "") String carNumber, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
        // 封装查询结果
        Map<String, Object> result = new HashMap();
        //获取车辆
        try {
            DecimalFormat df1 = new DecimalFormat("0.00");
            String  detailInfo = request.getParameter("carNumber");
            detailInfo = URLDecoder.decode(detailInfo, "UTF-8");
            List list=new ArrayList<Map<String,String>>();
            HashMap map = new HashMap();
            T_Data_Truck truck = truckDaoMapper.findTruckByPlateNumber(detailInfo);
            if (truck != null) {
                String truckId = truck.getTruck_id();
                //车牌
                map.put("plateNumber", detailInfo);
                //车辆品牌
                String truckModelNo = truck.getTruck_model_no();
                T_Data_Truck_Model truckModelByNo = truckModelService.findTruckModelByNo(truckModelNo);
                String truckModelName = truckModelByNo.getTruck_model_name();
                map.put("brandName", truckModelName);
                //车厢类型
                String truck_carriage_type_id = truck.getTruck_carriage_type_id();
                T_Master_Truck_Carriage_Type carriageType = carriageTypeService.findTruckCarriageTypeByID(truck_carriage_type_id);
                String truckCarriageTypeName = carriageType.getTruck_carriage_type_name();
                map.put("carriageTypeName", truckCarriageTypeName);
                //车辆类型
                String truck_type_id = truck.getTruck_type_id();
                T_Sys_Dicdata dicdata_type = dicdataService.findDicdataByID(truck_type_id);
                String typeName = dicdata_type.getDicdata_name();
                map.put("typeName", typeName);

                //燃料类型
                String truck_fuel_type_id = truck.getTruck_fuel_type_id();
                T_Sys_Dicdata dicdata_types = dicdataService.findDicdataByID(truck_fuel_type_id);
                String fuleName = dicdata_types.getDicdata_name();
                map.put("fuleName", fuleName);
                //车长
                String truck_length_id = truck.getTruck_length_id();
                T_Sys_Dicdata dicdataLength = dicdataService.findDicdataByID(truck_length_id);
                String lengthName = dicdataLength.getDicdata_name();
                map.put("lengthName", lengthName);
                //车架号
                String vehicle_identify_number = truck.getVehicle_identify_number();
                map.put("vehicle_identify_number", vehicle_identify_number);
                //载重
                String load_weight = truck.getLoad_weight();
                map.put("load_weight", load_weight);
                //发动机号
                String engine_number = truck.getEngine_number();
                map.put("engine_number", engine_number);
                //常跑干线
                List<T_Data_Truck_Transport_Line> transportLineList = truckTransportLineService.findTruckTransportByTruckID(truckId);
               for (int i=0;i<transportLineList.size();i++){
                   HashMap lineMap = new HashMap();
                   String startCityId = transportLineList.get(i).getStart_city_id();
                   String endCityId = transportLineList.get(i).getEnd_city_id();
                   T_Master_Operate_Main_Line mainLine = operateMainLineDaoMapper.findOperateMainLineByStartCityAndFinishCity(startCityId, endCityId);
                  if (mainLine!=null){
                      String lineName = mainLine.getOperate_main_line_name();
                      lineMap.put("lineName",lineName);
                      list.add(lineMap);
                  }
               }
                map.put("list", list);
                //行驶证第一页和第二页
                String firstPageSavePath = truck.getDriving_licence_first_page_save_path();
                map.put("firstPageSavePath", firstPageSavePath);
                String secondPageSavePath = truck.getDriving_licence_second_page_save_path();
                map.put("secondPageSavePath", secondPageSavePath);
                //车辆照片
                String truckFirstPicSavePath = truck.getTruck_first_pic_save_path();
                map.put("truckFirstPicSavePath", truckFirstPicSavePath);
                String truckSecondPicSavePath = truck.getTruck_second_pic_save_path();
                map.put("truckSecondPicSavePath", truckSecondPicSavePath);
                String truckThirdPicSavePath = truck.getTruck_third_pic_save_path();
                map.put("truckThirdPicSavePath", truckThirdPicSavePath);
                result.put("list",map);
            }

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
            logger.info("获取车辆信息成功");

        } catch (Exception e) {
            logger.error("获取车辆信息失败"+e.getMessage());
        }
    }


    //根据车牌获取车辆相关信息
    @RequestMapping(value = "getTruckSheetInfo")
    @ResponseBody
    public void getTruckSheetInfo(@RequestParam(required = false, defaultValue = "") String carNumber, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
        // 封装查询结果
        Map<String, Object> result = new HashMap();
        //获取车辆
        try {

            T_Data_Truck truckByPlateNumber = truckDaoMapper.findTruckByPlateNumber(carNumber);
            String truckId = truckByPlateNumber.getTruck_id();
            String managerMemberId = truckByPlateNumber.getManager_member_id();
            List list2=new ArrayList<Map<String,String>>();
            //车主
            String managerMember = personDaoMapper.findPersonByID(managerMemberId).getPerson_name();
            List<T_Data_Transportation_Schedule_Sheet> list = scheduleSheetDaoMapper.allFinshedRecord(truckId);
            if (list.size()!=0){
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (T_Data_Transportation_Schedule_Sheet scheduleSheet: list){
                    HashMap map = new HashMap();
                    String code = scheduleSheet.getCode();
                    //运单号
                    map.put("code",code);
                    String schedulePlanNumber = scheduleSheet.getSchedule_plan_number();
                    String scheduleSheetId = scheduleSheet.getScheduleSheetId();
                    T_Data_Transportation_Plan transportationPlan = transportationOrderDaoMapper.findBySchedulePlanNumber(schedulePlanNumber);
                    String contractId = transportationPlan.getContract_id();
                    T_Data_Transportation_Contract contract = contractService.findById(contractId);
                    String first_party_type = contract.getFirst_party_type();
                    String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                    String company=null;
                    if (first_party_type.equals("0")){//个人
                        T_Data_Person person = personDaoMapper.findPersonByID(relevanceInfoId);
                        company = person.getPerson_name();
                    }else {//公司
                        T_Data_Company companyByID = companyDaoMapper.findCompanyByID(relevanceInfoId);
                        company = companyByID.getCompany_name();
                    }
                    //车牌 和公司
                    map.put("carNumber",carNumber);
                    map.put("company",company);
                    //货物类型
                    String cargo_type_id = contract.getCargo_type_id();
                    String cargoTypeName = cargoTypeDaoMapper.findCargoType(cargo_type_id).getCargoTypeName();
                    map.put("goodName",cargoTypeName);
                    //车主
                    map.put("managerMember",managerMember);
                    //干线
                    String operate_main_line_id = transportationPlan.getOperate_main_line_id();
                    String mainLineName = operateMainLineDaoMapper.findOperateMainLineById(operate_main_line_id).getOperate_main_line_name();
                    map.put("mainLineName",mainLineName);
                    //完成时间
                    String unloadingEndTime = scheduleSheet.getUnloadingEndTime();
                    Date parse = format.parse(unloadingEndTime);
                    map.put("unloadingEndTime",format.format(parse));
                    //单价
                    String transportUnitPrice = scheduleSheet.getTransportUnitPrice();
                    map.put("transportUnitPrice",transportUnitPrice);
                    List<T_Data_Transportation_Dispatch_Sheet> sheetList = dispatchSheetDaoMapper.findByScheduleId(scheduleSheetId);
                    String reciveMember="";
                    for (int i=0;i< sheetList.size() ;i++){
                        String receiveMemberId = sheetList.get(i).getReceiveMemberId();
                        T_Data_Person person = personDaoMapper.findPersonByID(receiveMemberId);
                        String personName = person.getPerson_name();
                        if (i>0){
                            reciveMember+="，"+personName;
                        }else {
                            reciveMember+=personName;
                        }
                    }
                    map.put("reciveMember",reciveMember);
                    list2.add(map);
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list2));
            logger.info("获取车辆信息成功");

        } catch (Exception e) {
            logger.error("获取车辆信息失败"+e.getMessage());
        }
    }

}