/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ScheduleSheetServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：调度单的管理和app接口的定义
 * 设计文件：
 * 完成日期：2016-03-10
 * 作    者：WXW
 * 内容摘要：调度单的管理和app接口的定义
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.ScheduleSheetService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.ScheduleSheetManager_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// import jpush.ManagePush;
/**
 * 类 名 称: ScheduleSheetService
 * 内容摘要: 调度单的管理和app接口的定义
 * 方法描述：该类有8个方法：
 *         01 saveScheduleSheet                       保存调度单信息
 *         02 updateScheduleSheet                     调度单更新
 *         03 delScheduleSheet                        删除调度单信息
 *         04 delScheduleSheet                        根据车辆查询已完成车辆的调度单
 *         05 dispatchSheetSameOpints2                 返回完成和运输中派车单详情共同字段提取
 *         06 findFinishScheduleSheetDetails           查询已完成的订单详情
 *         07 findScheduleByConditions                根据多个条件查询调度单信息
 *         08 findScheduleSheetById                   根据id查询调度单信息
 *
 * @author WXW
 */
@Service
@WebService(endpointInterface ="com.cn.gazelle.logistics.service.ScheduleSheetService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class ScheduleSheetServiceImpl implements ScheduleSheetService {
    // log初始化
    Logger logger = Logger.getLogger(ScheduleSheetServiceImpl.class);
    
    @Resource
    private ScheduleSheetDaoMapper scheduleSheetDaoMapper;
    @Resource
    private MemberDaoMapper memberDaoMapper;
    @Resource
    private TransportationOrderDaoMapper transportationOrderDaoMapper;
    @Resource
    private CargoDaoMapper cargoDaoMapper;
    @Resource
    private TransportationContractDaoMapper contractDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;
    @Resource
    private TruckDaoMapper truckDaoMapper;
    @Resource
    private  DispatchSheetDaoMapper dispatchSheetDaoMapper;
    @Resource
    private  DicdataDaoMapper  dicdataDaoMapper;
    @Resource
    private  CargoTypeDaoMapper  cargoTypeDaoMapper;
    @Resource
    private  TruckCarriageTypeDaoMapper truckCarriageTypeDaoMapper;
    @Resource
    private OperateMainLineDaoMapper operateMainLineDaoMapper;
    @Resource
    private TransportationScheduleFinanceRecordDaoMapper finacialFlowDaoMapper;



    /**
     * 方法名称：saveScheduleSheet
     * 内容摘要：保存调度单信息
     * @param scheduleSheet 调度单信息
     */
    @Transactional
    public boolean saveScheduleSheet(T_Data_Transportation_Schedule_Sheet scheduleSheet) {
        Boolean a = new Boolean(true);
        try {
            scheduleSheet.setStatus(2);//保存时设置成分发中
            scheduleSheetDaoMapper.saveScheduleSheet(scheduleSheet);
            logger.info(ScheduleSheetManager_Message.SaveScheduleSheetDone);
        } catch (Exception e) {
            a=false;
            logger.error(ScheduleSheetManager_Message.FindScheduleSheetRowsCountError + e.getMessage());
        }
            return a;
    }

    /**
     * 方法名称：updateScheduleSheet
     * 内容摘要：调度单更新
     * @param scheduleSheet 调度单信息
     */
    @Transactional
    public boolean updateScheduleSheet(T_Data_Transportation_Schedule_Sheet scheduleSheet) {
        Boolean a = new Boolean(true);
        try {
            scheduleSheetDaoMapper.updateScheduleSheet(scheduleSheet);
            logger.info(ScheduleSheetManager_Message.UpdateScheduleSheetDone);
        } catch (Exception e) {
            a=false;
            logger.error(ScheduleSheetManager_Message.UpdateScheduleSheetError+e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delScheduleSheet
     * 内容摘要：删除调度单信息
     * @param scheduleSheetId 调度单id
     */
    @Transactional
    public void delScheduleSheet(String scheduleSheetId) {
        try {
            scheduleSheetDaoMapper.delScheduleSheet(scheduleSheetId);
            logger.info(ScheduleSheetManager_Message.DelScheduleSheetDone);
        } catch (Exception e) {
            logger.error(ScheduleSheetManager_Message.DelScheduleSheetError + e.getMessage());
        }
    }


    /**
     * 方法名称：delScheduleSheet
     * 内容摘要：根据车辆查询已完成车辆的调度单
     * @param member_name
     * @param timeType
     * @param time
     * @param status
     * @param personType
     * @return
     */
    @Transactional
    public String findFinishedScheduleSheetByTruckId(String member_name,String personType, String timeType, String time, String status) {
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_status = new ArrayList<T_Sys_Dicdata>();
        List<Map<String, String>> listes = new ArrayList<Map<String, String>>();// 列表数据
        String json = null;
        String ecode = null;
        Map map=new HashMap();
        Map result = new HashMap();
        try {
            T_Data_Member dataMember = memberDaoMapper.findMemberByName(member_name);
            String personId = dataMember.getRelevance_info_id();
            if (!personId.equals(null)){
                //个人信息存在
                T_Data_Person dataPerson = personDaoMapper.findPersonByID(personId);
                String drivingTruckId = dataPerson.getDriving_truck_id();
                //车辆管理者
                if (personType.equals("0")  || personType.equals("2")){
                    List<T_Data_Truck> managerTruckList = truckDaoMapper.findTruckByManagerMemberID2(personId);
                    if (managerTruckList.size()!=0){
                        for (T_Data_Truck truck:managerTruckList){
                            String truckId = truck.getTruck_id();
                            List<T_Data_Transportation_Schedule_Sheet> list=null;
                            if (status.equals("0")){//查询已经完成的列表
                                if (timeType.equals("0")){//查询全部
                                    list = scheduleSheetDaoMapper.allFinshedRecord(truckId);
                                }else if (timeType.equals("1")){//查询最近一周
                                    list = scheduleSheetDaoMapper.oneWeekFinshedRecord(truckId);
                                }else if (timeType.equals("2")){//按月份查询
                                    list = scheduleSheetDaoMapper.monthFinshedRecord(truckId,time);
                                }else if (timeType.equals("3")){//按年查询
                                    list = scheduleSheetDaoMapper.yearFinshedRecord(truckId,time);
                                }
                                if (list.size()!=0){
                                    for (T_Data_Transportation_Schedule_Sheet scheduleSheet:list){
                                        map = dispatchSheetSameOpints2(scheduleSheet);
                                        listes.add(map);
                                    }
                                }
                                ecode="1000";
                                result.put("ecode", ecode);
                                logger.info("获取已完成列表成功");
                                result.put("orderList", listes);

                            }else if (status.equals("1")){//运输中的列表
                                if (timeType.equals("0")){//查询全部
                                    list = scheduleSheetDaoMapper.allUnfinshedRecord(truckId);
                                }else if (timeType.equals("1")){//查询最近一周
                                    list = scheduleSheetDaoMapper.oneWeekUnfinshedRecord(truckId);
                                }else if (timeType.equals("2")){//按月份查询
                                    list = scheduleSheetDaoMapper.monthUnfinshedRecord(truckId,time);
                                }else if (timeType.equals("3")){//按年查询
                                    list = scheduleSheetDaoMapper.yearUnfinshedRecord(truckId,time);
                                }
                                if (list.size()!=0){
                                    for (T_Data_Transportation_Schedule_Sheet scheduleSheet:list){
                                        map = dispatchSheetSameOpints2(scheduleSheet);
                                        listes.add(map);
                                    }
                                }
                                ecode="1000";
                                result.put("ecode", ecode);
                                logger.info("获取运输中运单成功");
                                result.put("orderList", listes);

                            }
                        }
                    }else {
                        //管理者没有车辆
                        ecode="1000";
                        result.put("orderList", listes);
                    }
                    //司机
                }else if (personType.equals("1") || personType.equals("5") || personType.equals("3") || personType.equals("4")){
                    List<T_Data_Transportation_Schedule_Sheet> list=null;
                    if (status.equals("0")){//查询已经完成的列表
                        if (timeType.equals("0")){//查询全部
                            list = scheduleSheetDaoMapper.allFinshedRecordByReceviceMember(personId);
                        }else if (timeType.equals("1")){//查询最近一周
                            list = scheduleSheetDaoMapper.oneWeekFinshedRecordByReceviceMember(personId);
                        }else if (timeType.equals("2")){//按月份查询
                            list = scheduleSheetDaoMapper.monthFinshedRecordByReceviceMember(personId,time);
                        }else if (timeType.equals("3")){//按年查询
                            list = scheduleSheetDaoMapper.yearFinshedRecordByReceviceMember(personId,time);
                        }
                        if (list.size()!=0){
                            for (T_Data_Transportation_Schedule_Sheet scheduleSheet:list){
                                map = dispatchSheetSameOpints2(scheduleSheet);
                                listes.add(map);
                            }
                        }
                        ecode="1000";
                        result.put("ecode", ecode);
                        logger.info("获取已完成列表成功");
                        result.put("orderList", listes);

                    }else if (status.equals("1")){//运输中的列表
                        if (timeType.equals("0")){//查询全部
                            list = scheduleSheetDaoMapper.allUnfinshedRecordByReceviceMember(personId);
                        }else if (timeType.equals("1")){//查询最近一周
                            list = scheduleSheetDaoMapper.oneWeekUnfinshedRecordByReceviceMember(personId);
                        }else if (timeType.equals("2")){//按月份查询
                            list = scheduleSheetDaoMapper.monthUnfinshedRecordByReceviceMember(personId,time);
                        }else if (timeType.equals("3")){//按年查询
                            list = scheduleSheetDaoMapper.yearUnfinshedRecordByReceviceMember(personId,time);
                        }
                        if (list.size()!=0){
                            for (T_Data_Transportation_Schedule_Sheet scheduleSheet:list){
                                map = dispatchSheetSameOpints2(scheduleSheet);
                                listes.add(map);
                            }
                        }
                        ecode="1000";
                        result.put("ecode", ecode);
                        logger.info("获取运输中运单成功");
                        result.put("orderList", listes);

                    }
                }else{
                    ecode = "4000";//当前无运单
                    result.put("ecode", ecode);
                }
            }else{
                //个人信息不存在
                ecode="1000";
                result.put("ecode", ecode);
                result.put("orderList", listes);
            }

        } catch (Exception e) {
            ecode="2000";
            result.put("ecode", ecode);
            logger.info("查询失败"+e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
    /**
     * 方法名 称：dispatchSheetSameOpints2
     * 内容描述：返回完成和运输中派车单详情共同字段提取
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @Transactional
    private Map dispatchSheetSameOpints2(T_Data_Transportation_Schedule_Sheet scheduleSheet) {
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_status = new ArrayList<T_Sys_Dicdata>();
        Map<String, String> results = new HashMap<String, String>();
        String truckId = scheduleSheet.getScheduleTruckId();
        T_Data_Truck truck = truckDaoMapper.findTruckByID2(truckId);
        //派车单idrefreshOrderSheet2
        results.put("scheduleId", scheduleSheet.getScheduleSheetId());
        results.put("code", scheduleSheet.getCode());
        //车牌
        results.put("plateNumber", truck.getPlate_number());
        //车厢类型
        T_Master_Truck_Carriage_Type carriageType = truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck.getTruck_carriage_type_id());
        results.put("carriageTypeName", carriageType.getTruck_carriage_type_name());
        String orderId = scheduleSheet.getSchedule_plan_number();
        results.put("orderId",orderId);
        //获取订单
        T_Data_Transportation_Plan orderListSort = transportationOrderDaoMapper.findOrderById(orderId);

        //订单单价
        int price = (int) orderListSort.getTransport_unit_price();
        results.put("price",price+"元/吨");

        //获取装货货场
        String loading_cargo_yard_id = orderListSort.getLoading_cargo_yard_id();
        T_Master_Cargo_Yard loadCargoYard = cargoDaoMapper.findById(loading_cargo_yard_id);
        //装货场纬度
        results.put("loadLatitude", loadCargoYard.getLatitude());
        //经度
        results.put("loadLongitude", loadCargoYard.getLongitude());
        double lat2 = Double.parseDouble(loadCargoYard.getLatitude());
        double lon2 = Double.parseDouble(loadCargoYard.getLongitude());

        //装货联系电话
        String loadingContactPhone = orderListSort.getLoading_contact_phone();
        results.put("loadingContactPhone", loadingContactPhone);
        //卸货场
        String unloadingCargoYardId = orderListSort.getUnloading_cargo_yard_id();
        T_Master_Cargo_Yard unLoadCargoYard = cargoDaoMapper.findById(unloadingCargoYardId);
        //装货场纬度
        results.put("unloadLatitude", unLoadCargoYard.getLatitude());
        //经度
        results.put("unloadLongitude", unLoadCargoYard.getLongitude());
        //卸货场经纬度
        Double  lat = Double.parseDouble(unLoadCargoYard.getLatitude());
        Double  lon = Double.parseDouble(unLoadCargoYard.getLongitude());
        //根据经纬度查询装卸货场之间的干线距离
        String distance = cargoDaoMapper.findDistance(lat, lon, lat2, lon2);
        //获取小数点后2位数
        DecimalFormat df=new DecimalFormat(".##");
        double doubleValue = Double.valueOf(distance)/1000;
        String length=df.format(doubleValue);
        results.put("distance", length+"km");
        //装货联系电话
        String unloadingContactPhone = orderListSort.getUnloading_contact_phone();
        results.put("unloadingContactPhone", unloadingContactPhone);

        String loading_begin_date = orderListSort.getLoading_begin_date();
        String unloading_finish_date = orderListSort.getUnloading_finish_date();
        results.put("loadingStartTime", loading_begin_date);
        results.put("unloadingEndTime", unloading_finish_date);
        String lineId = orderListSort.getOperate_main_line_id();
        T_Master_Operate_Main_Line mainLine2 = operateMainLineDaoMapper.findOperateMainLineById(lineId);
        //获取起点省市
        String provinceId = mainLine2.getStart_province_id();
        String cityId = mainLine2.getStart_city_id();
        dicdataList_province = this.dicdataDaoMapper.findAllDicdataByID("2EE02FDAE3BE4F06B8CFDF4425BA74BF", provinceId);
        dicdataList_city = this.dicdataDaoMapper.findAllDicdataByID("66029BA5DC964A15B29852FA077327C0", cityId);
        results.put("startProvinceId", dicdataList_province.get(0).getDicdata_name());
        results.put("startCityId", dicdataList_city.get(0).getDicdata_name());
        // 终点省市
        String province_id = mainLine2.getFinish_province_id();
        String city_id = mainLine2.getFinish_city_id();
        dicdataList_province = this.dicdataDaoMapper.findAllDicdataByID("2EE02FDAE3BE4F06B8CFDF4425BA74BF", province_id);
        dicdataList_city = this.dicdataDaoMapper.findAllDicdataByID("66029BA5DC964A15B29852FA077327C0", city_id);
        results.put("endProvinceId", dicdataList_province.get(0).getDicdata_name());
        results.put("endCityId", dicdataList_city.get(0).getDicdata_name());
        //合同
        String contractId2 = orderListSort.getContract_id();
        T_Data_Transportation_Contract transportationContract = contractDaoMapper.findById(contractId2);
        // 获取类型ID
        String cargo_type_id = transportationContract.getCargo_type_id();
        results.put("cargoTypeId", cargoTypeDaoMapper.findCargoType(cargo_type_id).getCargoTypeName());
        String cargoTypeUnitC = cargoTypeDaoMapper.findCargoType(cargo_type_id).getCargoTypeUnitC();
        //载重量
        results.put("loadWeight", truck.getLoad_weight()+cargoTypeUnitC);
        //状态
        int status = scheduleSheet.getStatus();
        dicdataList_status = this.dicdataDaoMapper.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", status+"");
        results.put("status", dicdataList_status.get(0).getDicdata_name());

        return results;
    }
    /**
     * 方法名 称：findFinishScheduleSheetDetails
     * 内容描述：查询已完成的订单详情
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @Transactional
    public  String findFinishScheduleSheetDetails(String member_name, String scheduleId){
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_status = new ArrayList<T_Sys_Dicdata>();
        String json = null;
        String ecode = null;
        Map result = new HashMap();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map map=new HashMap();
            T_Data_Transportation_Schedule_Sheet scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetById(scheduleId);
            String orderId = scheduleSheet.getSchedule_plan_number();
            T_Data_Transportation_Plan transportationOrder = transportationOrderDaoMapper.findOrderById(orderId);
            String acceptTime = scheduleSheet.getAcceptTime();
            Date date = format.parse(acceptTime);
            String accpetDate = format.format(date);
            //待装货时间
            String setOffTime = scheduleSheet.getSetOffTime();
            map.put("setOffTime",setOffTime);
            //装货开始时间
            String loadingStartTime = scheduleSheet.getLoadingStartTime();
            map.put("loadingStartTime",loadingStartTime);
            //装货结束时间
            String loadingEndTime = scheduleSheet.getLoadingEndTime();
            map.put("loadingEndTime",loadingEndTime);
            //载货重量
            double loadingCargoAmount = scheduleSheet.getLoadingCargoAmount();
            map.put("loadingCargoAmount",loadingCargoAmount+"T");
            //装货场工作时间
            String loadingCargoYardId = transportationOrder.getLoading_cargo_yard_id();
            T_Master_Cargo_Yard loadCargoYard = cargoDaoMapper.findById(loadingCargoYardId);
            String loadBeginTime = loadCargoYard.getLoad_begin_time();
            String loadEndTime = loadCargoYard.getLoad_end_time();
            map.put("loadTime",loadBeginTime+"-"+loadEndTime);
            result.put("loadList",map);
            //卸货场工作时间
            String unloadingCargoYardId = transportationOrder.getUnloading_cargo_yard_id();
            T_Master_Cargo_Yard unloadCargoYard = cargoDaoMapper.findById(unloadingCargoYardId);
            String unloadBeginTime = unloadCargoYard.getUnload_begin_time();
            String unloadEndTime = unloadCargoYard.getUnload_end_time();
            String unloadingStartTime = scheduleSheet.getUnloadingStartTime();
            String unloadingEndTime = scheduleSheet.getUnloadingEndTime();

            Map map2=new HashMap();
            map2.put("unloadingStartTime",unloadingStartTime);
            map2.put("unloadingEndTime",unloadingEndTime);
            map2.put("unloadTime",unloadBeginTime+"-"+unloadEndTime);
            double unLoadingCargoAmount = scheduleSheet.getUnLoadingCargoAmount();
            //卸货数量
            map2.put("unLoadingCargoAmount",unLoadingCargoAmount+"T");
            result.put("unloadList",map2);
            //获取流水列表
            ArrayList<String> list = new ArrayList();
            List<T_Data_Transportation_Schedule_Finance_Record> scheduleFinanceRecordList = finacialFlowDaoMapper.findFinacialFlowByScheduleId(scheduleId);
            for (T_Data_Transportation_Schedule_Finance_Record financeRecord:scheduleFinanceRecordList){
                String flowType = financeRecord.getFinacial_flow_type();
                //添加消费类型
                list.add(flowType);
            }
            //list 消费类型去重
            List<String> tempList= new ArrayList<String>();
            for(String flowTypeId:list){
                if(!tempList.contains(flowTypeId)){
                    tempList.add(flowTypeId);
                }
            }
            ArrayList<Map> typeList = new ArrayList();
            int i=0;
            for (String typeId:tempList){
                HashMap hashMap = new HashMap();
                T_Sys_Dicdata dicdata = dicdataDaoMapper.findDicdataByID(typeId);
                String name = dicdata.getDicdata_name();
                int amount = finacialFlowDaoMapper.findAmountByScheduleIdAndTypeId(scheduleId, typeId);
                Double valueOfamount = Double.valueOf(amount);
                i+=amount;
                hashMap.put("name",name);
                hashMap.put("amount",String.valueOf(valueOfamount));
                typeList.add(hashMap);
            }
            HashMap map3 = new HashMap();
           // map3.put("oilCost","加油费");
//            map3.put("oilVolume","35升");
//            map3.put("oilUnitPrice","78￥/升");
         //   map3.put("fuleCost","加气费");
//            map3.put("fuleVolume","35立方");
//            map3.put("fuleUnitPrice","63￥/立方");
            map3.put("totalAmount",i);
            map3.put("ETCAmount","0");
            map3.put("platformETCAmount","0");
            map3.put("fuelPlatformCost","0");
            map3.put("typeList",typeList);
            result.put("flowList",map3);
            int loadCost = loadCargoYard.getLoad_cost();
            int loadPumpCost = loadCargoYard.getLoad_pump_cost();
            int unloadCost = unloadCargoYard.getUnload_cost();
            int unloadPumpCost = unloadCargoYard.getUnload_pump_cost();
            int totalMoney=loadCost+loadPumpCost+unloadCost+unloadPumpCost+300;
            Map map4=new HashMap();
            map4.put("loadCost",loadCost);
            map4.put("loadPumpCost",loadPumpCost);
            map4.put("unloadCost",unloadCost);
            map4.put("unloadPumpCost",unloadPumpCost);
            map4.put("loadOrtherCost","100");
            map4.put("unloadOrtherCost","100");
            map4.put("informationCost","100");
            map4.put("totalMoney",totalMoney);
            result.put("moneyList",map4);
            ecode="1000";
            result.put("ecode", ecode);
            result.put("acceptTime",accpetDate);
            //全部费用
            int cost=totalMoney+i;
            result.put("cost",cost);
            result.put("lineNo",scheduleSheet.getSelectedLineNo());
        }catch (Exception e) {
            ecode="2000";
            result.put("ecode", ecode);
            logger.info("查询失败"+e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
    /**
     * 方法名 称：findScheduleByConditions
     * 内容描述：根据多个条件查询调度单信息
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @Transactional
    public List<T_Data_SchedingSheet> findScheduleByConditions(String plateNumber, String planNumber, String lineName, String modelName, String receveEnd, String receveStart,String person ,String personPhone) {
        List<T_Data_SchedingSheet> sheetList=null;
        try {
            sheetList= scheduleSheetDaoMapper.findScheduleByConditions(plateNumber, planNumber, lineName, modelName, receveEnd, receveStart,person,personPhone);
        }catch (Exception e){
            logger.error(ScheduleSheetManager_Message.saveInfoError);
        }
        return sheetList;
    }

    /**
     * 方法名 称：findScheduleSheetById
     * 内容描述：根据id查询调度单信息
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @Transactional
    public T_Data_Transportation_Schedule_Sheet findScheduleSheetById(String scheduleSheetId) {
        T_Data_Transportation_Schedule_Sheet scheduleSheet =null;
        try {
            scheduleSheet= scheduleSheetDaoMapper.findScheduleSheetById(scheduleSheetId);
        }catch (Exception e){
            logger.error(ScheduleSheetManager_Message.saveInfoError);
        }
        return scheduleSheet;
    }
    /**
     * 方法名 称：findMinStatusByTruckId
     * 内容描述：根据车辆id查询最小的状态值
     * 方法描述：该类有 个方法
     *@authot WXW
     */
    @Override
    @Transactional
    public String findMinStatusByTruckId( String truckId) {
        String minStatus=null;
        try {
            minStatus= scheduleSheetDaoMapper.findMinStatusByTruckId(truckId);
        }catch (Exception e){
            logger.error(ScheduleSheetManager_Message.saveInfoError);
        }
        return minStatus;
    }

}
