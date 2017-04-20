/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DispatchSheetServiceImpl
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：调度单信息管理和app接口的实现
 * 设计文件：
 * 完成日期：2016-03-14
 * 作    者：WXW
 * 内容摘要：调度单信息管理和app接口的实现
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.DispatchSheetService;
import com.cn.gazelle.logistics.service.SysInfoService;
import com.cn.gazelle.logistics.util.CodeUtil;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.DispatchSheet_Message;
import com.cn.gazelle.logistics.util.message.ScheduleSheetManager_Message;
import com.cn.gazelle.logistics.util.message.TransportationOrder_Message;
import com.cn.gazelle.logistics.util.message.TransportationScheduleFinanceRecordManager_Message;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * 类 名 称: DispatchSheetServiceImpl
 * 内容摘要: 调度单信息管理和app接口的实现
 * 方法描述：该类有7个方法：
 *         01 findDispatchSheetByID                  根据ID查派车单信息
 *         02 findDispatchAppByName                  根据会员账号查找派车单信息
 *         03 findAllDispatchSheet                   查找所有派车单信息
 *         04 findAllDispatchSheetRowsCount          查询派车单总记录数
 *         05 saveDispatchSheet                      保存派车单信息
 *         06 updateDispatchSheet                    更新派车单信息
 *         07 delDispatchSheet                       删除派车单信息
 *         08 findDispatchSheetAppBydispatchSheetId  根据派单表id查询app相关信息
 *         09 updateDispatchSheetStatus              根据id更新状态
 *         10 DispatchSheetShare                     派车单的共享
 *         11 findScheduleSheetFinishedCount         查询已完成订单的个数
 *         12 searchStatus                           检索账号的运营状态
 *         13 switchStatus                           切换运营状态请求
 *         14 findDispatchList                       获取当前用户的订单列表
 *         15 refreshOrderSheet                      根据车辆管理者账号刷新页面
 *         16 getSheetDetails                        获取订单详情
 *         17 searchLine                             线路检索
 *         18 findFinishScheduleSheet                查询已经完成的调度单下的派车单
 *         19 sheetOtherExperses                     运单其他消费查询
 *         20 transportationExperses                 运单运输费用查询
 *         21 receiveSheet                           接单
 *         22 finishCount                            运单完成数量查询
 *         23 changeSheetStatus2                      变更运单运输转态
 *         24 expenseType                            消费类型检索
 *         25 expenseSave                            消费票据提交
 *         26 expenseList                            消费列表检索
 *         27 expenseDetails                         消费详情查询
 * @author WXW
 */

@Service
@WebService(endpointInterface="com.cn.gazelle.logistics.service.DispatchSheetService",targetNamespace="http://service.logistics.gazelle.cn.com/")
public class DispatchSheetServiceImpl implements DispatchSheetService {
    // log的初始化
    Logger logger = Logger.getLogger("DispatchSheetServiceImpl.class");

    @Resource
    private DispatchSheetDaoMapper dispatchSheetDaoMapper;
    @Resource
    private MemberDaoMapper memberDaoMapper;
    @Resource
    private ScheduleSheetDaoMapper scheduleSheetDaoMapper;
    @Resource
    private CargoDaoMapper cargoDaoMapper;
    @Resource
    private TransportationContractDaoMapper contractDaoMapper;
    @Resource
    private TransportationOrderDaoMapper transportationOrderDaoMapper;
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;
    @Resource
    private CargoTypeDaoMapper cargoTypeDaoMapper;
    @Resource
    private OperateMainLineDaoMapper operateMainLineDaoMapper;
    @Resource
    private TruckDaoMapper truckDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;
    @Resource
    private SubLineInfoDaoMapper subLineInfoDaoMapper;
    @Resource
    private SubLineNodeDaoMapper subLineNodeDaoMapper;
    @Resource
    private TruckTransportLineDaoMapper truckTransportLineDaoMapper;
    @Resource
    private CargoTruckTypeMatchMapper cargoTruckTypeMatchMapper;
    @Resource
    private TransportationScheduleFinanceRecordDaoMapper finacialFlowDaoMapper;
    @Resource
    private StationDaoMapper stationDaoMapper;
    @Resource
    private TruckPositionDaoMapper truckPositionDaoMapper;
    @Resource
    private OBDequipmentDaoMapper obDequipmentDaoMapper;
    @Resource
    private TruckCarriageTypeDaoMapper truckCarriageTypeDaoMapper;
    @Resource
    private SysInfoService sysInfoService;
    @Resource
    private OBDEquipmentTruckBindingDaoMapper obdEquipmentTruckBindingDaoMapper;
    @Resource
    private SchedulePlanDaoMapper schedulePlanDaoMapper;

    /**
     * 方法名称：findDispatchSheetByID
     * 内容摘要：根据id查找派车单信息。
     *
     * @param dispatchSheetId 派车单id
     * @return dispatchSheet 派车单信息
     */
    @Transactional
    public T_Data_Transportation_Dispatch_Sheet findDispatchSheetByID(String dispatchSheetId) {
        T_Data_Transportation_Dispatch_Sheet dispatchSheet = null;
        try {
            dispatchSheet = dispatchSheetDaoMapper.findDispatchSheetByID(dispatchSheetId);
            logger.info(DispatchSheet_Message.getDispatchSheetDone);
        } catch (Exception e) {
            logger.info(DispatchSheet_Message.getDispatchSheetError + e.getMessage());
        }
        return dispatchSheet;
    }

    /**
     * 方法名称：saveDispatchSheet
     * 内容摘要：添加派车单
     *
     * @param dispatchSheet 派车单信息
     */
    @Transactional
    public boolean saveDispatchSheet(T_Data_Transportation_Dispatch_Sheet dispatchSheet) {
        Boolean a = new Boolean(true);
        try {
            Date date = new Date();
            dispatchSheet.setDCode(CodeUtil.DCode(date));
            dispatchSheetDaoMapper.saveDispatchSheet(dispatchSheet);
            logger.info(DispatchSheet_Message.SaveDispatchSheetDone);
        } catch (Exception e) {
            a = false;
            logger.info(DispatchSheet_Message.SaveDispatchSheetError + e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：updateDispatchSheet
     * 内容摘要：更新派车单信息
     *
     * @param dispatchSheet 派车单信息
     */
    @Transactional
    public boolean updateDispatchSheet(T_Data_Transportation_Dispatch_Sheet dispatchSheet) {
        Boolean a = new Boolean(true);
        try {
            dispatchSheetDaoMapper.updateDispatchSheet(dispatchSheet);
            logger.info(DispatchSheet_Message.UpdateDispatchSheetStatusDone);
        } catch (Exception e) {
            a = false;
            logger.info(DispatchSheet_Message.UpdateDispatchSheetStatusError + e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：delDispatchSheet
     * 内容摘要：删除派车单信息
     *
     * @param dispatchSheetId 派车单id
     */
    @Transactional
    public void delDispatchSheet(String dispatchSheetId) {
        try {
            dispatchSheetDaoMapper.delDispatchSheet(dispatchSheetId);
            logger.info(DispatchSheet_Message.DelDispatchSheetDone);
        } catch (Exception e) {
            logger.info(DispatchSheet_Message.DelDispatchSheetError + e.getMessage());
        }

    }

    /**
     * 根据车辆管理者账号刷新订单页面
     *
     * @param member_name
     * @return
     */
    @Transactional
    public String refreshOrderSheet2(String member_name, String lats, String lons) {
        logger.info(member_name);
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        String message = null;
        double lat = 0;
        double lon = 0;
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_status = new ArrayList<T_Sys_Dicdata>();
        // 获取会员
        T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
        if (member == null) {
            ecode = "3005"; // 用户名不存在！
            result.put("ecode", ecode);
            result.put("result", "用户名不存在！");
        } else {

            List<Map<String, String>> listes = new ArrayList<Map<String, String>>();// 列表数据
            try {
                List<Map<String, String>> lists = new ArrayList<Map<String, String>>();// 列表数据
                List<T_Data_Transportation_OrderList> orderListTmp = null;
                // 关联id
                String relevanceInfoId = member.getRelevance_info_id();
                String managerMemberId = null;
                //根据管理者id查询名下的车辆
                List<T_Data_Truck> managerTruckList = truckDaoMapper.findTruckByManagerMemberID(relevanceInfoId);
                if (managerTruckList.size() != 0) {
                    //方便设置ecode为null
                    int m = 0;
                    for (T_Data_Truck trcuk : managerTruckList) {
                        logger.info("车牌："+trcuk.getPlate_number());
                        List<T_Data_Transportation_OrderList> orderList = new ArrayList<T_Data_Transportation_OrderList>();
                        m++;
                        String truckId = trcuk.getTruck_id();
                        //根据车id查看货物运输计划调度单信息表是否有手动派的订单(已经查看过的 1)
                        T_Data_Transportation_Schedule_Sheet_Plan plan = schedulePlanDaoMapper.findLookSheetByTruckIdAndStatus(truckId);
                        //根据车id查看货物运输计划调度单信息表是否有手动派的订单(未查看过的 )
                        List<T_Data_Transportation_Schedule_Sheet_Plan> List = schedulePlanDaoMapper.findUnLookSheetByTruckId(truckId);
                        //根据需要的参数拼接的对象
                        T_Data_Transportation_OrderList orderByPlanAndConId = new T_Data_Transportation_OrderList();
                        if (plan != null) {//手动派的订单(已经查看过的 状态为 1 )
                            String schedulePlanNumber = plan.getSchedule_plan_number();
                            T_Data_Transportation_Plan transportationPlan = transportationOrderDaoMapper.findBySchedulePlanNumber(schedulePlanNumber);
                            String contractId = transportationPlan.getContract_id();
                            orderByPlanAndConId = transportationOrderDaoMapper.findOrderByPlanAndConId(schedulePlanNumber, contractId);
                            Map map = orderSameOpints2(orderByPlanAndConId, trcuk);
                            String error =(String) map.get("error");
                            if (!error.equals("1")){//error 为1 代表获取出错
                                map.put("flag","0");//标记手动派单
                                //添加手动派单的对象
                                listes.add(map);
                                result.put("orderList", listes);
                                ecode = "1000"; // 成功响应！
                                result.put("ecode", ecode);

                            }

                        } else if (List.size() != 0) {//手动派的订单(未查看过的 )
                            T_Data_Transportation_Schedule_Sheet_Plan sheetPlan = List.get(0);
                            sheetPlan.setStatus(1);
                            sheetPlan.setLastUpdate(DateUtil.getDate());
                            sheetPlan.setLastUpdateUserId("M:"+member_name);
                            schedulePlanDaoMapper.updateScheduleSheet(sheetPlan);
                            String schedulePlanNumber = sheetPlan.getSchedule_plan_number();
                            T_Data_Transportation_Plan transportationPlan = transportationOrderDaoMapper.findBySchedulePlanNumber(schedulePlanNumber);
                            String contractId = transportationPlan.getContract_id();
                            orderByPlanAndConId = transportationOrderDaoMapper.findOrderByPlanAndConId(schedulePlanNumber, contractId);
                            Map map = orderSameOpints2(orderByPlanAndConId, trcuk);
                            String error =(String) map.get("error");
                            if (!error.equals("1")){//error 为1 代表获取出错
                                map.put("flag","0");//标记手动派单
                                //添加手动派单的对象
                                listes.add(map);
                                result.put("orderList", listes);
                                ecode = "1000"; // 成功响应！
                                result.put("ecode", ecode);

                            }
                        } else {//没有手动的派单
                            //车厢类型
                            String carriageTypeId = trcuk.getTruck_carriage_type_id();
                            managerMemberId = trcuk.getManager_member_id();
                            //管理者查询可以获取的派车单
                            T_Data_OBD_Equipment_Truck_Binding truckBinding = obdEquipmentTruckBindingDaoMapper.findOBDTruckBindingInfoByTruckID(truckId);
                            //根据车辆定位信息和手机的定位信息进行判断
                            if (truckBinding != null || !lats.equals("")) {
                                if (truckBinding != null) {
                                    String equipmentId = truckBinding.getEquipment_id();
                                    T_Data_OBD_Equipment obdEquipment = obDequipmentDaoMapper.findById(equipmentId);
                                    //车辆定位设备
                                    String obdId = obdEquipment.getObd_id();
                                    T_Data_Truck_Position truckPosition = truckPositionDaoMapper.findLatestTruckPositionByOBD(obdId);
                                    if (truckPosition!= null) {
                                        //获取经纬度
                                        String latitude = truckPosition.getLatitude();
                                        String longitude = truckPosition.getLongitude();
                                        // 车辆定位经纬度
                                        lat = Double.parseDouble(latitude);
                                        lon = Double.parseDouble(longitude);
                                    }
                                }
                                if (lat == 0 && !lats.equals("")) {
                                    //手机的定位
                                    lat = Double.parseDouble(lats);
                                    lon = Double.parseDouble(lons);
                                }
                                //拒单操作  该车辆的拒单时间判断
                                //最新拒单时间
                                Date deniedScheduleLastTime = trcuk.getDenied_schedule_last_time();
                                //获取系统信息表
                                T_Data_Sys_Info sysInfo  = sysInfoService.findAll();
                                //获取系统信息表中的冷却时间
                                int coolDownTime = sysInfo.getDenied_cool_down_time();
                                long time = 0;
                                if (deniedScheduleLastTime != null) {
                                    time = deniedScheduleLastTime.getTime() + coolDownTime;
                                }
                                long nowTime = new Date().getTime();
                                //拒单时间+车辆拒单冷却时间大于当前时间才可以刷单
                                if (deniedScheduleLastTime == null || time >= nowTime) {
                                    //以下操作根据经纬度查询订单
                                    if (truckId != null || truckId.trim().length() != 0) {
                                        //获取干线
                                        List<T_Data_Truck_Transport_Line> transportLineList = truckTransportLineDaoMapper.findTruckTransportByTruckID(truckId);
                                        //获取车厢类型
                                        if (carriageTypeId != null || carriageTypeId.trim().length() != 0) {
                                            List<T_Master_Cargo_Truck_Type_Match> matchByTruckList = cargoTruckTypeMatchMapper.findMatchByTruck(carriageTypeId);
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
                                                                orderListTmp = transportationOrderDaoMapper.findOrderByLineIdAndConId2(operate_main_line_id, contractId, lat, lon);
                                                                if (orderListTmp.size()!=0){
                                                                    //放到订单list中
                                                                    T_Data_Transportation_OrderList orderList1 = orderListTmp.get(0);
                                                                    orderList.add(orderList1);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            ecode = "1000";
                                            message = "当前车辆没有填写车厢类型信息";
                                        }
                                    } else {
                                        ecode = "1000";
                                        message = "当前用户没有填写车辆信息";
                                    }
                                } else {
                                    ecode = "4000";
                                    message = "当前车辆拒单中";
                                }
                            } else {
                                //都没有定位信息
                                ecode = "1000";
                                message = "请发送定位信息";
                            }
                            if (m > 1) {
                                ecode = null;
                            }

                            //ecode 为null 添加list添加订单成功
                            if (ecode == null) {
                                Integer minStatus = 0;
                                //有订单且 是管理者
                                if (managerMemberId.equals(relevanceInfoId)) {
                                    //根据管理者当前车辆id查询最小状态
                                    String statusByMemId = scheduleSheetDaoMapper.findMinStatusByTruckId(truckId);
                                    //获取调度单的最小状态
                                    if (statusByMemId != null) {
                                        minStatus = Integer.valueOf(statusByMemId);
                                    }
                                    //当前用户没有可以接到的订单
                                    if (orderList.size() == 0) {
                                        ecode = "1000";
                                        result.put("ecode", ecode);
                                        logger.info(trcuk.getPlate_number()+"当前无列表");
                                        result.put("orderList", listes);
                                    } else {
                                        //new 个新对象方便根据距离排序
                                        T_Data_Transportation_OrderList sort = new T_Data_Transportation_OrderList();
                                        //根据距离倒序排序
                                        List<T_Data_Transportation_OrderList> mySort = sort.mySort(orderList);
                                        //拒单时间为null 拒单时间加上惩罚时间没加到条件中
                                        //调度单状态'0：待运输 1：已出发 2：装货中 3：运输中 4：卸货中 5：已完成
                                        if (minStatus > 3 || statusByMemId == null) {
                                            T_Data_Transportation_OrderList orderListSort = mySort.get(0);
                                            Map map = orderSameOpints2(orderListSort, trcuk);
                                            String error =(String) map.get("error");
                                            if (!error.equals("1")){//error 为1 代表获取出错
                                                map.put("flag","1");//自动刷单
                                                listes.add(map);
                                            }

                                        }
                                        result.put("orderList", listes);
                                        ecode = "1000"; // 成功响应！
                                        result.put("ecode", ecode);
                                        logger.info(DispatchSheet_Message.getDispatchSheetDone);
                                    }
                                } else {
                                    ecode = "1011";
                                    logger.info(TransportationOrder_Message.getTransportationOrderInfo);
                                    result.put("ecode", ecode);
                                    logger.info("当前无列表");
                                }
                            }
                        }
                    }
                } else {
                    ecode = "1011";
                    logger.info(TransportationOrder_Message.getTransportationOrderInfo);
                    result.put("ecode", ecode);
                    logger.info("当前无列表");
                }
            } catch (Exception e) {
                logger.info(ScheduleSheetManager_Message.getScheduleSheetError);
                ecode = "2000"; // 响应错误！
                listes=null;
                result.put("ecode", ecode);
                result.put("orderList", listes);
                logger.info(e.getMessage());

            }
        }
        json = JSONUtil.toJSONString(result);
        logger.info("刷单接口"+json);
        return json;
    }

    //获取调度单详情
    @Transactional
    public String getSheetDetails2(String member_name, String scheduleSheetId,String plateNumber) {
        Map result=new HashMap();
        String ecode=null;
        String json=null;
        try {
            List<T_Data_date2> list=new ArrayList<T_Data_date2>();
            T_Data_Transportation_Schedule_Sheet scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetById(scheduleSheetId);
            String acceptTime = scheduleSheet.getAcceptTime();
            String setOffTime = scheduleSheet.getSetOffTime();
            String loadingStartTime = scheduleSheet.getLoadingStartTime();
            String loadingEndTime = scheduleSheet.getLoadingEndTime();
            String unloadingStartTime = scheduleSheet.getUnloadingStartTime();
            String unloadingEndTime = scheduleSheet.getUnloadingEndTime();
            String loadingProofPath = scheduleSheet.getLoadingProofPath();
            String unloadingProofPath = scheduleSheet.getUnloadingProofPath();
            List<T_Data_Transportation_Schedule_Finance_Record> scheduleFinanceRecordList = finacialFlowDaoMapper.findFinacialFlowByScheduleId(scheduleSheetId);
            for (T_Data_Transportation_Schedule_Finance_Record financeRecord:scheduleFinanceRecordList){
                T_Data_date2 date = new T_Data_date2();
                String createTime = financeRecord.getCreate_time();
                String flowType = financeRecord.getFinacial_flow_type();
                String flowId = financeRecord.getFinacial_flow_id();
                T_Sys_Dicdata dicdata = dicdataDaoMapper.findDicdataByID(flowType);
                String dicdataName = dicdata.getDicdata_name();
                date.setName(dicdataName);
                date.setCreateTime(createTime);
                date.setFlowId(flowId);
                date.setType("0");
                list.add(date);
            }
            T_Data_date2 date1 = new T_Data_date2();
            date1.setName("接单");
            date1.setCreateTime(acceptTime);
            date1.setFlowId("");
            date1.setType("1");
            list.add(date1);
            T_Data_date2 date2 = new T_Data_date2();
            date2.setName("选好线路");
            date2.setCreateTime(acceptTime);
            date2.setFlowId("");
            date2.setType("1");
            list.add(date2);
            if (setOffTime!=null){
                T_Data_date2 date3 = new T_Data_date2();
                date3.setName("去装货");
                date3.setCreateTime(setOffTime);
                date3.setType("1");
                date3.setFlowId("");
                list.add(date3);
            }
            if (loadingStartTime!=null){
                T_Data_date2 date4 = new T_Data_date2();
                date4.setName("装货中");
                date4.setCreateTime(loadingStartTime);
                date4.setType("1");
                date4.setFlowId("");
                list.add(date4);
            }
            if (loadingEndTime!=null){
                T_Data_date2 date5 = new T_Data_date2();
                date5.setName("装货完成");
                date5.setCreateTime(loadingEndTime);
                date5.setType("0");
                date5.setFileUrl(loadingProofPath);
                date5.setFlowId("");
                list.add(date5);
            }
            if (unloadingStartTime!=null){
                T_Data_date2 date6 = new T_Data_date2();
                date6.setName("到达卸货场");
                date6.setCreateTime(unloadingStartTime);
                date6.setType("1");
                date6.setFlowId("");
                list.add(date6);
            }
            if (unloadingEndTime!=null){
                T_Data_date2 date7 = new T_Data_date2();
                date7.setName("卸货完成");
                date7.setCreateTime(unloadingEndTime);
                date7.setType("0");
                date7.setFileUrl(unloadingProofPath);
                date7.setFlowId("");
                list.add(date7);
            }
            T_Data_date2 data = new T_Data_date2();
            List<T_Data_date2> dateSort = data.dateSort(list);
            //接单和选好路线时间相同，排序后发现接单放在了第二个，所以移除再设置
            dateSort.remove(1);
            dateSort.add(0,date1);
            T_Data_Truck truck = truckDaoMapper.findTruckByPlateNumber(plateNumber);
            //根据车辆id查询未完成的调度单
            String truck_id = truck.getTruck_id();
            List<T_Data_Transportation_Schedule_Sheet> scheduleByTruckIdAndStatus = scheduleSheetDaoMapper.findUnFinishSheetByTruckID(truck_id);
            //0代表可以进行操作 1代表状态卸货中 2代表没有指派司机操作 3司机卸货中
            String flag="0";
            if (scheduleByTruckIdAndStatus.size()>1){
                T_Data_Transportation_Schedule_Sheet scheduleSheetById = scheduleSheetDaoMapper.findScheduleSheetById(scheduleSheetId);
                int status = scheduleSheetById.getStatus();
                if (status==0){
                    flag="1";
                }
            }
            List<T_Data_Transportation_Dispatch_Sheet> sheetList = dispatchSheetDaoMapper.findByScheduleId(scheduleSheetId);
            if (sheetList.size()==0){
                flag="2";
            }else {
                for (T_Data_Transportation_Dispatch_Sheet dispatchSheet:sheetList){
                    String receiveMemberId = dispatchSheet.getReceiveMemberId();
                    //根据状态和接收会员id查询  卸货中  派车单信息
                    T_Data_Transportation_Dispatch_Sheet unloadingDispatchByMemId = dispatchSheetDaoMapper.findUnloadingDispatchByMemId(receiveMemberId);
                    if (unloadingDispatchByMemId!=null){
                        //不为null，说明该司机卸货中，不能进行改变状态
                        //卸货中的调度单id
                        String unloadingDispatchByMemIdScheduleSheetId = unloadingDispatchByMemId.getScheduleSheetId();
                        if (!unloadingDispatchByMemIdScheduleSheetId.equals(scheduleSheetId)){
                            flag="3";
                            break;
                        }
                    }
                }
            }
            T_Data_Transportation_Schedule_Sheet scheduleSheetById = scheduleSheetDaoMapper.findScheduleSheetById(scheduleSheetId);
            result.put("status", scheduleSheetById.getStatus());
            result.put("loadTotal", scheduleSheetById.getLoadingCargoAmount());
            result.put("isUse", flag);
            ecode = "1000";//获取详情成功
            result.put("ecode", ecode);
            result.put("list",dateSort);
            logger.info("获取订单详情成功"+result);
        } catch (Exception e) {
            ecode = "2000";//获取详情失败
            result.put("ecode", ecode);
            logger.info("获取订单详情失败"+e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 干线线路检索
     *
     * @param member_name
     * @param orderId
     * @return
     */
    @Transactional
    public String searchLine(String member_name, String orderId) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        try {
            T_Data_Transportation_Plan orderById = transportationOrderDaoMapper.findOrderById(orderId);
            // 运营干线ID
            String mainLineId = orderById.getOperate_main_line_id();
            List list = new ArrayList();
            //获取多个干线路线
            List<T_Master_Sub_Line_Info> mainLineInfoList = subLineInfoDaoMapper.findSubLineInfoByOperateID(mainLineId);
            for (T_Master_Sub_Line_Info mainLineInfo : mainLineInfoList) {
                List lists = new ArrayList();
                String lineId = mainLineInfo.getLine_id();
                Integer lineNo = mainLineInfo.getLine_no();
                Map map = new HashMap();
                //线路id和线路编号
                map.put("lineId", lineId);
                map.put("lineNo", lineNo);
                List<T_Master_Sub_Line_Detail> subLineNodeList = subLineNodeDaoMapper.findSubLineNodeByInfoID(lineId);
                for (T_Master_Sub_Line_Detail subLineNode : subLineNodeList) {
                    String nodeNo = subLineNode.getNode_no();
                    Map results = new HashMap();
                    //线路节点编号
                    results.put("nodeNo", nodeNo);
                    lists.add(results);
                }
                map.put("lists", lists);
                list.add(map);
            }
            result.put("list", list);
            ecode = "1000"; // 成功响应！
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000"; // 成功响应！
            result.put("ecode", ecode);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
    //接单
    @Transactional
    private Map receiveSheet2(String member_name,String  plateNumber, String orderId, String lineNo,String flag) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        try {
            //车辆管理者
            T_Data_Member memberByName = memberDaoMapper.findMemberByName(member_name);
            String infoId = memberByName.getRelevance_info_id();
            //格式化时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取干线节点
            T_Master_Sub_Line_Info lineInfo = subLineInfoDaoMapper.findSubLineInfoByID(lineNo);
            //接单的二级订单
            T_Data_Transportation_Plan order = transportationOrderDaoMapper.findOrderById(orderId);
            String planNumber = order.getBelong_schedule_plan_number();
            //获取一级运单计划
            T_Data_Transportation_Plan schedulePlanNumber = transportationOrderDaoMapper.findBySchedulePlanNumber(planNumber);
            String contract_id = schedulePlanNumber.getContract_id();
            //货物合同
            T_Data_Transportation_Contract contract = contractDaoMapper.findById(contract_id);
            //获取车辆id
            T_Data_Truck truckByPlateNumber = truckDaoMapper.findTruckByPlateNumber(plateNumber);
            double loadWeight = Double.parseDouble(truckByPlateNumber.getLoad_weight());
            String truck_id = truckByPlateNumber.getTruck_id();
            //判断有无可用司机
            int j=0;//用司机
            List<T_Data_Transportation_Dispatch_Sheet> unfinishDispatchByMendId = dispatchSheetDaoMapper.findUnfinishDispatchByMendId(infoId);
            if (unfinishDispatchByMendId.size() != 0) {
                List<T_Data_Person> persons1 = personDaoMapper.findPersonByIDAndType(truck_id,"1");
                List<T_Data_Person> persons2 = personDaoMapper.findPersonByIDAndType(truck_id,"2");
                if (persons1.size()==0 && persons2.size()==0) {
                    j = 1;//无司机
                }
            }
            if (j==0){//可以派车
                //检查已承接的数量
                double scheduleCargoTotal = order.getSchedule_cargo_total();//已调度的
                //已分配的
                double redistribute_cargo_total = order.getRedistribute_cargo_total();
                int cargo_total = order.getCargo_total();
                if (cargo_total>scheduleCargoTotal+redistribute_cargo_total || flag.equals("0") ){
                    T_Data_Transportation_Schedule_Sheet scheduleSheet = new T_Data_Transportation_Schedule_Sheet();
                    scheduleSheet.setScheduleSheetId(UUIDUtil.getUUID());
                    scheduleSheet.setSchedule_plan_number(orderId);
                    scheduleSheet.setReceiveMemberId(infoId);
                    scheduleSheet.setAllocateTotal(loadWeight);
                    scheduleSheet.setScheduleTruckId(truckByPlateNumber.getTruck_id());
                    scheduleSheet.setSelectedLineNo(String.valueOf(lineInfo.getLine_no()));
                    scheduleSheet.setCreateTime(format.format(DateUtil.getDate()));
                    scheduleSheet.setAcceptTime(format.format(DateUtil.getDate()));
                    scheduleSheet.setTransportUnitPrice(String.valueOf(order.getTransport_unit_price()));
                    scheduleSheet.setStatus(0);
                    scheduleSheet.setLastUpdate(DateUtil.getDate());
                    scheduleSheet.setLastUpdateUserId("M:"+member_name);
                    Date date=new Date();
                    scheduleSheet.setCode(CodeUtil.SCode(date));
                    scheduleSheetDaoMapper.saveScheduleSheet(scheduleSheet);
                    order.setSchedule_cargo_total(scheduleCargoTotal+loadWeight);
                    order.setLast_update(DateUtil.getDate());
                    //二级计划
                    if (scheduleCargoTotal==0) {
                        order.setStart_schedule_time(format.format(DateUtil.getDate()));
                        order.setStatus("3");
                    }
                    if (scheduleCargoTotal+loadWeight+redistribute_cargo_total>=cargo_total){
                        order.setStatus("4");
                    }
                    transportationOrderDaoMapper.updateTransportateOrder(order);
                    //一级计划更新
                    double scheduleCargoTotal1 = schedulePlanNumber.getSchedule_cargo_total();
                    int cargo_total1 = schedulePlanNumber.getCargo_total();
                    double redistribute_cargo_total1 = schedulePlanNumber.getRedistribute_cargo_total();
                    schedulePlanNumber.setSchedule_cargo_total(scheduleCargoTotal1+loadWeight);

                    if (scheduleCargoTotal1==0){
                        //设置开始时间
                        schedulePlanNumber.setStart_schedule_time(format.format(DateUtil.getDate()));
                        schedulePlanNumber.setStatus("3");
                    }
                    if (scheduleCargoTotal1+loadWeight+redistribute_cargo_total1>=cargo_total1){
                        order.setStatus("4");
                    }
                    transportationOrderDaoMapper.updateTransportateOrder(schedulePlanNumber);

                    //更新手动派单的状态
                    if (flag.equals("0")){
                        T_Data_Transportation_Schedule_Sheet_Plan sheetPlan = schedulePlanDaoMapper.findLookSheetByTruckIdAndStatus(truck_id);
                        sheetPlan.setStatus(2);
                        sheetPlan.setLastUpdate(DateUtil.getDate());
                        sheetPlan.setLastUpdateUserId("M:"+member_name);
                        schedulePlanDaoMapper.updateScheduleSheet(sheetPlan);
                    }
                    ecode = "1000";//接单成功
                    result.put("ecode", ecode);
                    String scheduleSheetId = scheduleSheet.getScheduleSheetId();
                    result.put("scheduleSheetId", scheduleSheetId);
                    logger.info("接单成功");
                }else{
                    ecode = "3000";//接单失败
                    result.put("ecode", ecode);
                    logger.info("此运单已被他人抢先");
                }
            }else {//不可以派车
                ecode = "4000";//该车无可派司机
                result.put("ecode", ecode);
                logger.info("该车无可派司机");
            }

        } catch (Exception e) {
            ecode = "2000";//接单失败
            result.put("ecode", ecode);
            logger.info("接单失败" + e);
        }

        logger.info("ecode:" + ecode);
        // json = JSONUtil.toJSONString(result);
        return result;
    }
    @Transactional
    //指派司机  designateDriver
    private Map designateDriver(String plateNumber ,String scheduleId,String driverList){
        Map result=new HashMap();
        String ecode=null;
        String json=null;
        Gson gson=new Gson();
        try {
            T_Data_Transportation_Schedule_Sheet scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetById(scheduleId);
            String receiveMemberId = scheduleSheet.getReceiveMemberId();
            T_Data_Truck truck = truckDaoMapper.findTruckByPlateNumber(plateNumber);
            String managerMemberId = truck.getManager_member_id();
            if (driverList!=null) {
                // json转为带泛型的list 解析司机列表
                List<Map<String, String>> retList = gson.fromJson(driverList, new TypeToken<List<Map<String, String>>>() {
                }.getType());
                // 新行使司机列表中对象变更为该车的行驶司机
                if (retList.size() != 0) {
                    int i=0;
                    for (Map<String, String> map : retList) {
                        i++;
                        String member_name = map.get("member_name");
                        T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
                        String infoId = member.getRelevance_info_id();
                        T_Data_Person person = personDaoMapper.findPersonByID(infoId);
                        if (!infoId.equals(managerMemberId)) {
                            //司机
                            person.setDriving_status("2");
                            personDaoMapper.updatePerson(person);
                        } else {//管理者
                            person.setDriving_truck_id(truck.getTruck_id());
                            person.setDriving_status("2");
                            personDaoMapper.updatePerson(person);
                        }
                        T_Data_Transportation_Dispatch_Sheet dispatchSheet = new T_Data_Transportation_Dispatch_Sheet();
                        dispatchSheet.setDispatchSheetId(UUIDUtil.getUUID());
                        dispatchSheet.setLastUpdateUserId("M:" + member_name);
                        dispatchSheet.setLastUpdate(DateUtil.getDate());
                        dispatchSheet.setDispatchSheetStatus("0");
                        dispatchSheet.setReceiveMemberId(infoId);
                        dispatchSheet.setDeleteFlag("0");
                        Date date = new Date();
                        if (i==1){
                            dispatchSheet.setDCode(CodeUtil.DCode(date));
                        }else{
                            dispatchSheet.setDCode(CodeUtil.DCode(date)+i);
                        }
                        dispatchSheet.setSendMemberId(receiveMemberId);
                        dispatchSheet.setScheduleSheetId(scheduleId);
                        dispatchSheetDaoMapper.saveDispatchSheet(dispatchSheet);
                    }
                    ecode = "1000";
                    result.put("ecode", ecode);
                    logger.info("车辆指派多个司机成功");
                }
            }
        } catch (JsonSyntaxException e) {
            ecode = "2000";//指派司机失败
            result.put("ecode", ecode);
            logger.info("车辆指派司机失败"+e.getMessage());
        }
        //  json = JSONUtil.toJSONString(result);
        return result;
    }

    /**
     * 方法名称：findDispatchList
     * 内容摘要：获取当前用户的订单列表
     *
     * @param member_name 会员账号
     * @return json 返回值（1000 代表成功 其他则代表失败）
     */
    @Transactional
    public String findDispatchList2(String member_name) {
        logger.info(member_name);
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_status = new ArrayList<T_Sys_Dicdata>();
        int count=0;
        // 获取会员
        T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
        if (member == null) {
            ecode = "3005"; // 用户名不存在！
            result.put("ecode", ecode);
            result.put("result", "用户名不存在！");
        } else {
            try {
                List<Map<String, String>> lists = new ArrayList<Map<String, String>>();// 列表数据
                // 关联id
                String relevanceInfoId = member.getRelevance_info_id();
                if (relevanceInfoId!=null) {
                    T_Data_Person person = personDaoMapper.findPersonByID(relevanceInfoId);
                    //当前用户的驾驶状态
                    String drivingStatus = person.getDriving_status();
                    //根据管理者id查询名下的车辆
                    List<T_Data_Truck> managerTruckList = truckDaoMapper.findTruckByManagerMemberID(relevanceInfoId);
                    if (managerTruckList.size()!=0){
                        //如果不为0 则说明身份是管理者
                        for(T_Data_Truck truck:managerTruckList){
                            String truck_id = truck.getTruck_id();
                            T_Data_Truck dataTruck = truckDaoMapper.findTruckByID(truck_id);
                            //根据车辆id查询未完成的调度单
                            List<T_Data_Transportation_Schedule_Sheet> unFinishSheetList = scheduleSheetDaoMapper.findUnFinishSheetByTruckID(truck_id);
                            //派车单个数
                            int i = unFinishSheetList.size();
                            count+=i;
                            if(unFinishSheetList.size()!=0){
                                for (T_Data_Transportation_Schedule_Sheet unFinishSheet: unFinishSheetList){
                                    Map map = ScheduleSheetSameOpints(unFinishSheet, dataTruck);
                                    lists.add(map);
                                }
                            }
                            result.put("scheduleSheetList", lists);
                            ecode = "1000"; // 成功响应！
                            result.put("ecode", ecode);
                            logger.info(DispatchSheet_Message.getDispatchSheetDone);
                        }
                        result.put("count", count);
                    }else{
                        //司机关联车辆id
                        String truckId = person.getDriving_truck_id();
                        if (truckId!=null){
                            T_Data_Truck truck = truckDaoMapper.findTruckByID(truckId);
                            if (truck!=null){
                                //驾驶状态为2 或者 会员关联id等于车辆管理者id 返回派车单
                                if (drivingStatus.equals("2")) {
                                    //根据车辆id查询未完成的调度单 小于5
                                    List<T_Data_Transportation_Schedule_Sheet> unFinishSheetList = scheduleSheetDaoMapper.findUnFinishSheetByTruckID(truck.getTruck_id());
                                    if(unFinishSheetList.size()!=0){
                                        for (T_Data_Transportation_Schedule_Sheet unFinishSheet: unFinishSheetList){
                                            Map map = ScheduleSheetSameOpints(unFinishSheet, truck);
                                            lists.add(map);
                                        }
                                    }
                                    result.put("scheduleSheetList", lists);
                                    ecode = "1000"; // 成功响应！
                                    result.put("ecode", ecode);
                                    logger.info(DispatchSheet_Message.getDispatchSheetDone);
                                }else {//驾驶状态为 1 常跑司机
                                    result.put("scheduleSheetList", lists);
                                    ecode = "1000"; // 成功响应！
                                    result.put("ecode", ecode);
                                    logger.info(DispatchSheet_Message.getDispatchSheetDone);
                                }
                            }else {
                                //有车辆Id 但是无车辆 说明解除车辆关系
                                ecode = "4000"; // 成功响应！
                                result.put("ecode", ecode);
                                logger.info(DispatchSheet_Message.getDispatchSheetDone+ecode);
                            }

                        }else {
                            //当前无车辆 没有注册车辆信息
                            ecode = "4000"; // 成功响应！
                            result.put("ecode", ecode);
                            logger.info(DispatchSheet_Message.getDispatchSheetDone+ecode);
                        }
                    }
                } else {
                    //没有注册个人信息
                    result.put("scheduleSheetList", lists);
                    ecode = "3000"; // 成功响应！
                    result.put("ecode", ecode);
                    logger.info(DispatchSheet_Message.getDispatchSheetDone);
                }
            } catch (Exception e) {
                logger.info(ScheduleSheetManager_Message.getScheduleSheetError+e.getMessage());
                ecode = "2000"; // 响应错误！
                result.put("ecode", ecode);
            }
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 运单转态变更
     *
     * @param member_name
     * @param scheduleId
     * @param status
     * @param loadingProofPath
     * @param unloadingProofPath
     * @param loadingCargoAmount
     * @param unloadingCargoAmount
     * @return json
     */
    @Transactional
    public String changeSheetStatus2(String member_name, String scheduleId, String status, String loadingProofPath,
                                     String unloadingProofPath, String loadingCargoAmount, String unloadingCargoAmount,String longitude,String latitude) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            T_Data_Member memberByName = memberDaoMapper.findMemberByName(member_name);
            String infoId = memberByName.getRelevance_info_id();
            T_Data_Transportation_Schedule_Sheet scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetById(scheduleId);
            if (scheduleSheet != null) {
                //二级计划
                T_Data_Transportation_Plan order
                        = transportationOrderDaoMapper.findOrderById(scheduleSheet.getSchedule_plan_number());
                //一级计划
                T_Data_Transportation_Plan schedulePlanNumber = transportationOrderDaoMapper.findBySchedulePlanNumber(order.getBelong_schedule_plan_number());

                // 调度单状态
                scheduleSheet.setStatus(Integer.valueOf(status));
                if (status.equals("1")) {//出发时间
                    scheduleSheet.setSetOffTime(format.format(DateUtil.getDate()));
                } else if (status.equals("2")) {//装货时间
                    scheduleSheet.setLoadingStartTime(format.format(DateUtil.getDate()));
                } else if (status.equals("3")) {//装货结束时间
                    scheduleSheet.setLoadingEndTime(format.format(DateUtil.getDate()));
                } else if (status.equals("4")) {//卸货开始时间
                    scheduleSheet.setUnloadingStartTime(format.format(DateUtil.getDate()));
                } else if (status.equals("5")) {//卸货结束时间 完成时间
                    scheduleSheet.setUnloadingEndTime(format.format(DateUtil.getDate()));
                    String scheduleTruckId = scheduleSheet.getScheduleTruckId();
                    T_Data_Truck truck = truckDaoMapper.findTruckByID(scheduleTruckId);
                    String managerMemberId = truck.getManager_member_id();
                    if(managerMemberId.equals(infoId)){
                        //判断为管理者
                        T_Data_Person dataPerson = personDaoMapper.findPersonByID(managerMemberId);
                        dataPerson.setDriving_truck_id("");
                        dataPerson.setDriving_status("1");
                        //卸货中  更新车辆管理者
                        personDaoMapper.updatePerson(dataPerson);
                    }else {
                        //司机
                        List<T_Data_Transportation_Dispatch_Sheet> dispatchSheetList = dispatchSheetDaoMapper.findByScheduleId(scheduleId);
                        if (dispatchSheetList.size()!=0){
                            for (T_Data_Transportation_Dispatch_Sheet dispatchSheet:dispatchSheetList){
                                String receiveMemberId = dispatchSheet.getReceiveMemberId();
                                T_Data_Person dataPerson = personDaoMapper.findPersonByID(receiveMemberId);
                                // 更新司机状态
                                dataPerson.setDriving_status("1");
                                personDaoMapper.updatePerson(dataPerson);
                            }
                        }
                    }
                }
                if (loadingProofPath.trim().length() != 0) {
                    //装货的经纬度
                    scheduleSheet.setLoadingProofPath(loadingProofPath);
                    scheduleSheet.setLoadLongitude(longitude);
                    scheduleSheet.setLoadLatitude(latitude);
                }
                if (unloadingProofPath.trim().length() != 0) {
                    //卸货的图片和经纬度
                    scheduleSheet.setUnloadingProofPath(unloadingProofPath);
                    scheduleSheet.setUnloadLongitude(longitude);
                    scheduleSheet.setUnloadLatitude(latitude);
                }
                if (loadingCargoAmount.trim().length() != 0) {
                    //设置最新的完成数量
                    double cargoTotal = order.getTransported_cargo_total();
                    double total = schedulePlanNumber.getTransported_cargo_total();
                    //调度单
                    scheduleSheet.setLoadingCargoAmount(Double.parseDouble(loadingCargoAmount));
                    //二级计划
                    order.setTransported_cargo_total(cargoTotal+Double.parseDouble(loadingCargoAmount));
                    //一级计划
                    schedulePlanNumber.setTransported_cargo_total(total+Double.parseDouble(loadingCargoAmount));

                }
                if (unloadingCargoAmount.trim().length() != 0) {
                    //设置最新的卸货完成数量
                    double cargoTotal = order.getTransport_completed_cargo_total();
                    double schedule_cargo_total = order.getSchedule_cargo_total();
                    double redistribute_cargo_total = order.getRedistribute_cargo_total();
                    //货物总量
                    int bigCargoTotal = order.getCargo_total();
                    double total = schedulePlanNumber.getTransport_completed_cargo_total();
                    //调度单
                    scheduleSheet.setUnLoadingCargoAmount(Double.parseDouble(unloadingCargoAmount));
                    //二级计划
                    order.setTransport_completed_cargo_total(cargoTotal+ Double.parseDouble(unloadingCargoAmount));
                    if (schedule_cargo_total+redistribute_cargo_total>=bigCargoTotal){
                        order.setFinish_time(format.format(DateUtil.getDate()));
                    }
                    //一级计划
                    schedulePlanNumber.setTransport_completed_cargo_total(total+Double.parseDouble(unloadingCargoAmount));
                    //根据一级编号获取所有从属计划的最小状态
                    String minStatusByBelongSchedulePlanNumber = transportationOrderDaoMapper.findMinStatusByBelongSchedulePlanNumber(order.getBelong_schedule_plan_number());
                    if (minStatusByBelongSchedulePlanNumber.equals("4")){
                        //设置一级的状态完成
                        schedulePlanNumber.setFinish_time(format.format(DateUtil.getDate()));
                    }
                }
                scheduleSheet.setLastUpdate(DateUtil.getDate());
                //更新调度单
                scheduleSheetDaoMapper.updateScheduleSheet(scheduleSheet);
                //更新二级计划
                order.setLast_update(DateUtil.getDate());
                transportationOrderDaoMapper.updateTransportateOrder(order);
                //更新一级计划
                schedulePlanNumber.setLast_update(DateUtil.getDate());
                transportationOrderDaoMapper.updateTransportateOrder(schedulePlanNumber);
                //合同id
                String contract_id = schedulePlanNumber.getContract_id();
                T_Data_Transportation_Contract contract = contractDaoMapper.findById(contract_id);
                String minStatusByContractId = transportationOrderDaoMapper.findMinStatusByContractId(contract_id);
                if (minStatusByContractId.equals("4")){
                    contract.setLast_update(DateUtil.getDate());
                    contract.setStatus_update_time(DateUtil.getDate());
                    contract.setStatus("3");
                    contractDaoMapper.updateTransportationContract(contract);
                }
            }
            ecode = "1000";
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
    //消费票据信息提交           2222222222添加了经纬度
    @Transactional
    public String expenseSave(String member_name, String scheduleId, String dicdataId, String amount, String disbursement_voucher_image_save_path,String longitude,String latitude) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            T_Data_Transportation_Schedule_Finance_Record finacialFlow = new T_Data_Transportation_Schedule_Finance_Record();
            finacialFlow.setFinacial_flow_id(UUIDUtil.getUUID());
            finacialFlow.setSchedule_id(scheduleId);
            finacialFlow.setFinacial_flow_type(dicdataId);
            finacialFlow.setAmount(Double.valueOf(amount));
            finacialFlow.setCreate_time(format.format(DateUtil.getDate()));
            finacialFlow.setFinacial_flow_direction("4");
            finacialFlow.setDisbursement_voucher_image_save_path(disbursement_voucher_image_save_path);
            finacialFlow.setLongitude(longitude);
            finacialFlow.setLatitude(latitude);
            finacialFlow.setDeal_person_id(memberDaoMapper.findMemberByName(member_name).getRelevance_info_id());
            finacialFlow.setLast_update(DateUtil.getDate());
            finacialFlow.setLast_update_user_id("M" + member_name);
            finacialFlowDaoMapper.saveFinacialFlow(finacialFlow);
            ecode = "1000";
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            result.put("message", TransportationScheduleFinanceRecordManager_Message.saveFinacialFlowError);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
    //消费类型检索
    @Transactional
    public String expenseType(String member_name) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        try {
            List<T_Sys_Dicdata> dicdataList = dicdataDaoMapper.findDicdataByDictionaryID("ED69C824041D4894A00DE4A1D654A902");
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>();//列表数据
            for (T_Sys_Dicdata dicdata : dicdataList) {
                Map<String, String> results = new HashMap();
                String dicdataId = dicdata.getDicdata_id();
                String dicdataName = dicdata.getDicdata_name();
                results.put("dicdataId", dicdataId);
                results.put("dicdataName", dicdataName);
                lists.add(results);
                result.put("list", lists);
            }
            ecode = "1000";
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            result.put("dicdataName", "检索失败");
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }
    //消费详情查询 流水id
    @Transactional
    public String expenseDetails(String member_name, String flowId) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        try {
            T_Data_Transportation_Schedule_Finance_Record finacialFlow = finacialFlowDaoMapper.findFinacialFlowById(flowId);
            String createTime = finacialFlow.getCreate_time();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result.put("createTime", createTime);
            String targetServiceStationId = finacialFlow.getTarget_service_station_id();
            T_Master_Service_Station station = stationDaoMapper.findStationByID(targetServiceStationId);
            if (station != null) {
                String stationName = station.getStation_name();
                result.put("stationName", stationName);
            } else {
                result.put("stationName", " ");
            }
            Double amount = finacialFlow.getAmount();
            result.put("amount", Double.toString(amount));
            String remark = finacialFlow.getRemark();
            result.put("remark", remark);
            String dealPersonId = finacialFlow.getDeal_person_id();
            T_Data_Person dataPerson = personDaoMapper.findPersonByID(dealPersonId);
            String personName = dataPerson.getPerson_name();
            result.put("personName", personName);
            result.put("expenseAmount", "0");
            result.put("expenseUnit", "0");
            result.put("price", "0");
            String path = finacialFlow.getDisbursement_voucher_image_save_path();
            result.put("fileUrl", path);
            ecode = "1000";
            result.put("ecode", ecode);
        } catch (Exception e) {
            result.clear();
            ecode = "2000";
            result.put("ecode", ecode);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    //拒单功能添加
    @Transactional
    public String refuseSheet(String plateNumber) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;
        try {
            truck = truckDaoMapper.findTruckByPlateNumber(plateNumber);
            truck.setDenied_schedule_count(truck.getDenied_schedule_count()+1);
            truck.setDenied_schedule_last_time(DateUtil.getDate());
            ecode="1000";
            result.put("ecode",ecode);
            logger.info("拒单成功");
        } catch (Exception e) {
            ecode="2000";
            result.put("ecode",ecode);
            logger.info("拒单失败");
            e.printStackTrace();
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    //接单和派单
    @Transactional
    public String receiveSheet2AndDesignateDriver(String member_name,String  plateNumber, String orderId, String lineNo,String driverList,String flag){
        Map result = new HashMap();
        String json = null;
        String ecode = "2000";
        Map map = receiveSheet2(member_name, plateNumber, orderId, lineNo,flag);
        String ecodeValue = String.valueOf(map.get("ecode"));
        String  scheduleSheetId = String.valueOf(map.get("scheduleSheetId"));
        if (ecodeValue=="1000"){
            Map map2 = designateDriver(plateNumber,scheduleSheetId, driverList);
            String ecodeValue2 = String.valueOf(map2.get("ecode"));
            if (ecodeValue2.equals("1000")){
                ecode="1000";
                logger.info("接单和指派司机成功");
            }else {
                ecode="2000";
                //派单失败，删除调度单
                scheduleSheetDaoMapper.delScheduleSheet(scheduleSheetId);
                logger.info("指派司机失败");
            }
        }else if(ecodeValue.equals("3000") ) {
            logger.info("该订单失效");
            ecode="3000";
        }else if(ecodeValue.equals("4000") ){
            logger.info("无可用司机");
            ecode="4000";
        }else{
            logger.info("接单失败");
        }
        result.put("ecode",ecode);
        json = JSONUtil.toJSONString(result);
        return json;
    }
    //刷单详情共同字段提取
    @Transactional
    private Map orderSameOpints2(T_Data_Transportation_OrderList orderListSort,T_Data_Truck truck) {
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_status = new ArrayList<T_Sys_Dicdata>();
        Map<String, String> results = new HashMap<String, String>();
        try{
            //车牌
            results.put("plateNumber", truck.getPlate_number());
            //车厢类型
            T_Master_Truck_Carriage_Type carriageType = truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck.getTruck_carriage_type_id());
            String truck_carriage_type_name="null";
            if(carriageType!=null){
                truck_carriage_type_name= carriageType.getTruck_carriage_type_name();
            }
            results.put("carriageTypeName", truck_carriage_type_name);
            String orderId = orderListSort.getSchedule_plan_number();
            results.put("orderId",orderId);
            //订单单价
            int price = (int) orderListSort.getTransport_unit_price();
            results.put("price",price+"元/吨");

            //获取装货货场
            String loading_cargo_yard_id = orderListSort.getLoading_cargo_yard_id();
            T_Master_Cargo_Yard loadCargoYard = cargoDaoMapper.findById(loading_cargo_yard_id);
            String latitude= "39.992706";
            String longitude="116.396574";
            if (loadCargoYard!=null){
                latitude = loadCargoYard.getLatitude();
                longitude = loadCargoYard.getLongitude();
            }
            //装货场纬度116.396574, 39.992706
            results.put("loadLatitude", latitude);

            //经度
            results.put("loadLongitude", longitude);
            double lat2 = Double.parseDouble(latitude);
            double lon2 = Double.parseDouble(longitude);

            //装货联系电话
            String loadingContactPhone = orderListSort.getLoading_contact_phone();
            results.put("loadingContactPhone", loadingContactPhone);
            //卸货场
            String unloadingCargoYardId = orderListSort.getUnloading_cargo_yard_id();
            T_Master_Cargo_Yard unLoadCargoYard = cargoDaoMapper.findById(unloadingCargoYardId);
            String unlatitude= "39.992706";
            String unlongitude="116.396574";
            if (loadCargoYard!=null){
                unlatitude = unLoadCargoYard.getLatitude();
                unlongitude = unLoadCargoYard.getLongitude();
            }
            //装货场纬度
            results.put("unloadLatitude", unlatitude);
            //经度
            results.put("unloadLongitude", unlongitude);
            //卸货场经纬度
            Double  lat = Double.parseDouble(unlatitude);
            Double  lon = Double.parseDouble(unlongitude);
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
            T_Master_Cargo_Type cargoType = cargoTypeDaoMapper.findCargoType(cargo_type_id);
            String cargoTypeName="null";
            String cargoTypeUnitC="null";
            if (cargoType!=null){
                cargoTypeName = cargoType.getCargoTypeName();
                cargoTypeUnitC = cargoType.getCargoTypeUnitC();
            }
            results.put("cargoTypeId", cargoTypeName);
            //载重量
            results.put("loadWeight", truck.getLoad_weight()+cargoTypeUnitC);
            results.put("error","0");
        }catch (Exception e){
            e.getMessage();
            results.put("error","1");
        }

        return results;
    }
    //返回派车单详情共同字段提取
    @Transactional
    private Map ScheduleSheetSameOpints(T_Data_Transportation_Schedule_Sheet scheduleSheet ,T_Data_Truck truck) {
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_status = new ArrayList<T_Sys_Dicdata>();
        Map<String, String> results = new HashMap<String, String>();
        T_Data_Transportation_Plan orderListSort = transportationOrderDaoMapper.findOrderById(scheduleSheet.getSchedule_plan_number());
        results.put("scheduleId", scheduleSheet.getScheduleSheetId());
        //车牌
        results.put("plateNumber", truck.getPlate_number());
        //车厢类型
        T_Master_Truck_Carriage_Type carriageType = truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck.getTruck_carriage_type_id());
        results.put("carriageTypeName", carriageType.getTruck_carriage_type_name());
        String orderId = orderListSort.getSchedule_plan_number();
        //  results.put("orderId",orderId);
        //订单单价
        int price = (int) orderListSort.getTransport_unit_price();
        results.put("price", price + "元/吨");

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
        Double lat = Double.parseDouble(unLoadCargoYard.getLatitude());
        Double lon = Double.parseDouble(unLoadCargoYard.getLongitude());
        //根据经纬度查询装卸货场之间的干线距离
        String distance = cargoDaoMapper.findDistance(lat, lon, lat2, lon2);
        //获取小数点后2位数
        DecimalFormat df = new DecimalFormat(".##");
        double doubleValue = Double.valueOf(distance) / 1000;
        String length = df.format(doubleValue);
        results.put("distance", length + "km");
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
        results.put("loadWeight", truck.getLoad_weight() + cargoTypeUnitC);
        //状态
        int status = scheduleSheet.getStatus();
        dicdataList_status = this.dicdataDaoMapper.findAllDicdataByID("6339B4F9A2A04D80A2DDF25F44BA81C1", String.valueOf(status));
        results.put("status", dicdataList_status.get(0).getDicdata_name());
        results.put("statusNum", String.valueOf(status));

        return results;

//    /**
//     * 运单其他费用查询sheetOtherExperses
//     *
//     * @param member_name
//     * @param orderId
//     * @return
//     */
//    @Transactional
//    public String sheetOtherExperses(String member_name, String orderId) {
//        Map result = new HashMap();
//        String json = null;
//        String ecode = null;
//        T_Data_Transportation_Plan order = null;
//        try {
//            //获取订单
//            order = transportationOrderDaoMapper.findOrderById(orderId);
//            String loadingCargoYardId = order.getLoading_cargo_yard_id();
//            T_Master_Cargo_Yard yard = cargoDaoMapper.findById(loadingCargoYardId);
//            int loadPumpCost = yard.getLoad_pump_cost();
//            int loadCost = yard.getLoad_cost();
//            result.put("loadPumpCost", loadPumpCost + "");
//            result.put("loadCost", loadCost + "");
//            //装货其他费用
//            result.put("loadOtherExperses", "0");
//            String unloadingCargoYardId = order.getUnloading_cargo_yard_id();
//            T_Master_Cargo_Yard cargoYard = cargoDaoMapper.findById(unloadingCargoYardId);
//            int unloadCost = cargoYard.getUnload_cost();
//            int unloadPumpCost = cargoYard.getUnload_pump_cost();
//            result.put("unloadCost", unloadCost);
//            result.put("unloadPumpCost", unloadPumpCost);
//            result.put("unloadOtherExperses", "0");
//            result.put("cost", loadPumpCost + loadCost + unloadCost + unloadPumpCost + "");
//            ecode = "1000";
//            result.put("ecode", ecode);
//        } catch (Exception e) {
//            ecode = "2000";
//            result.put("ecode", ecode);
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//    }
//
//
//
//    /**
//     * 消费列表检索
//     *
//     * @param member_name
//     * @param dispatchId
//     * @return
//     */
//    @Transactional
//    public String expenseList(String member_name, String dispatchId) {
//        Map result = new HashMap();
//        String json = null;
//        String ecode = null;
//        try {
//            List<T_Sys_Dicdata> dicdataList = new ArrayList<T_Sys_Dicdata>();
//            List<Map<String, String>> lists = new ArrayList<Map<String, String>>();// 列表数据
//            T_Data_Transportation_Dispatch_Sheet dispatchSheet = dispatchSheetDaoMapper.findDispatchSheetByID(dispatchId);
//            String scheduleSheetId = dispatchSheet.getScheduleSheetId();
//            List<T_Data_Transportation_Schedule_Finance_Record> finacialFlowList = finacialFlowDaoMapper.findFinacialFlowByScheduleId(scheduleSheetId);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            for (T_Data_Transportation_Schedule_Finance_Record finacialFlow : finacialFlowList) {
//                Map<String, String> results = new HashMap<String, String>();
//                String flowType = finacialFlow.getFinacial_flow_type();
//                T_Sys_Dicdata dicdata = dicdataDaoMapper.findDicdataByID(flowType);
//                //流水类别
//                results.put("expenseType", dicdata.getDicdata_name());
//                //交易对象
//                results.put("tradingObject", "手动添加");
//                //交易编号
//                String flowId = finacialFlow.getFinacial_flow_id();
//                results.put("flowId", flowId);
//                //交易时间
//                String createTime = finacialFlow.getCreate_time();
//                results.put("createTime", format.format(createTime));
//                //金额
//                Double amount = finacialFlow.getAmount();
//                results.put("amount", Double.toString(amount));
//                lists.add(results);
//            }
//            result.put("list", lists);
//            ecode = "1000";
//            result.put("ecode", ecode);
//        } catch (Exception e) {
//            ecode = "2000";
//            result.put("ecode", ecode);
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//    }


    }

}