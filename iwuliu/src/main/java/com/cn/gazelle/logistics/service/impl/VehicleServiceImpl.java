/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: VehicleServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆管理查询接口实现
 * 设计文件：
 * 完成日期：2016-11-10
 * 作    者：QJ
 * 内容摘要：车辆管理查询
 */

package com.cn.gazelle.logistics.service.impl;


import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.VehicleService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.RadomUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.TruckManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: VehicleServiceImpl
 * 内容摘要: 车辆管理查询接口
 * 方法描述：该类有2个方法：
 * 01 findVehicleListCompany   根据条件查找车辆列表(物流公司)
 * 02 findVehicleListDriver   根据条件查找车辆列表(司机)
 *
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.VehicleService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class VehicleServiceImpl implements VehicleService {
    public static String code;                                 // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(VehicleServiceImpl.class);

    @Resource
    private VehicleDaoMapper vehicleDaoMapper;          // 数据访问层
    @Resource
    private TruckDaoMapper truckDaoMapper;              // 数据访问层
    @Resource
    private MemberDaoMapper memberDaoMapper;            // 数据访问层
    @Resource
    private PersonDaoMapper personDaoMapper;            // 数据访问层
    @Resource
    private ScheduleSheetDaoMapper scheduleSheetDaoMapper;// 数据访问层
    @Resource
    private TruckTransportLineDaoMapper truckTransportLineDaoMapper;// 数据访问层
    @Resource
    private MemberPaymentHistoryDaoMapper memberPaymentHistoryDaoMapper;// 数据访问层
    @Resource
    private TruckPaymentHistoryDaoMapper truckPaymentHistoryDaoMapper;// 数据访问层

    /**
     * 方法名称：findVehicleListCompany
     * 内容摘要：根据条件查找车辆列表(物流公司)
     *
     * @return findVehicleListCompany 车辆列表（物流公司）
     */
    public List<T_Data_Vehicle> findVehicleListCompany(HashMap<String, String> conditions) {
        List<T_Data_Vehicle> findVehicleListCompany = null;
        try {
            findVehicleListCompany = this.vehicleDaoMapper.findVehicleListCompany(conditions);
            logger.info(TruckManager_Message.seacheInfo);
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return findVehicleListCompany;
    }

    /**
     * 方法名称：findVehicleListDriver
     * 内容摘要：根据条件查找车辆列表(司机)
     *
     * @return findVehicleListDriver 车辆列表（司机）
     */
    public List<T_Data_Vehicle> findVehicleListDriver(HashMap<String, String> conditions) {
        List<T_Data_Vehicle> findVehicleListDriver = null;
        try {
            findVehicleListDriver = this.vehicleDaoMapper.findVehicleListDriver(conditions);
            logger.info(TruckManager_Message.seacheInfo);
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return findVehicleListDriver;
    }

    /**
     * 方法名称：findCompanyVehicleList
     * 内容摘要：根据条件查找物流公司车辆列表
     *
     * @return companyVehicleList 车辆列表（司机）
     */
    public List<T_Data_Logistics_Vehicle> findCompanyVehicleList(HashMap<String, String> conditions) {
        List<T_Data_Logistics_Vehicle> companyVehicleList = null;
        try {
            companyVehicleList = this.vehicleDaoMapper.findCompanyVehicleList(conditions);
            logger.info(TruckManager_Message.seacheInfo);
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return companyVehicleList;
    }

    /**
     * 方法名称：saveTruckInfo
     * 内容摘要：物流公司新增车辆信息（页面）
     */
    @Transactional
    public int saveTruckInfo(String truckInfo, String userName, HashMap picPathMap) {
        Gson gson = new Gson();
        int flag = 0;
        T_Data_Member member = null;
        try {
            member = memberDaoMapper.findMemberByName(userName);
            if (member != null) {
                String company_id = member.getRelevance_info_id();
                T_Data_Truck truck = gson.fromJson(truckInfo, new TypeToken<T_Data_Truck>() {
                }.getType());
                truck.setDriving_licence_first_page_save_path((String) picPathMap.get("driving_licence_first_page_save_path"));
                truck.setDriving_licence_second_page_save_path((String) picPathMap.get("driving_licence_second_page_save_path"));
                truck.setTruck_first_pic_save_path((String) picPathMap.get("truck_first_pic_save_path"));
                truck.setTruck_second_pic_save_path((String) picPathMap.get("truck_second_pic_save_path"));
                truck.setTruck_third_pic_save_path((String) picPathMap.get("truck_third_pic_save_path"));
                truck.setTruck_id(UUIDUtil.getUUID());              // 绑定车辆ID,司机和车辆id关联
                truck.setOwner_member_id(company_id);               // 默认为none
                truck.setOperate_status("0");                       // 车辆运营状态
                truck.setVerify_status("1");                        // 车辆审核状态
                truck.setManager_member_id("none");                 // 默认为none
                truck.setDeposit_member_id(company_id);             // 默认为none
                truck.setSubmit_verify_time(DateUtil.getDate());    // 新增提交审核时间
                truck.setDelete_flag(0);                            // 新建删除标识符，0：未删除
                truck.setLast_update(DateUtil.getDate());           // 新增最终更新日
                truck.setLast_update_user_id("U:" + userName);      // 新增最终更新者
                flag = this.truckDaoMapper.saveTruck(truck);
            } else {
                flag = 2;
            }
            if (flag == 1) {//保存成功
            } else if (flag == 0) {//重复
                throw new RuntimeException(String.valueOf(flag));
            } else if (flag == -1) {
                throw new RuntimeException(String.valueOf(flag));
            }
        } catch (RuntimeException e) {
            if (e.getMessage().equals("0")) {
                flag = 0;
            } else {
                flag = -1;
            }
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：editTruckInfo
     * 内容摘要：物流公司详情编辑车辆信息（页面）
     */
    @Transactional
    public int editTruckInfo(String truckInfo, String userName, HashMap pathMap) {
        Gson gson = new Gson();
        int flag = 0;
        T_Data_Member member = null;
        T_Data_Truck truck = null;
        try {
            member = memberDaoMapper.findMemberByName(userName);
            if (member != null) {
                String company_id = member.getRelevance_info_id();
                // 解析info json数据
                Map<String, String> data = gson.fromJson(truckInfo, new TypeToken<Map<String, String>>() {
                }.getType());
                String plate_number = (String) data.get("plate_number");
                String driving_licence = (String) data.get("driving_licence");
                String load_weight = (String) data.get("load_weight");
                String vehicle_identify_number = (String) data.get("vehicle_identify_number");
                String engine_number = (String) data.get("engine_number");
                String truck_type_id = (String) data.get("truck_type_id");
                String truck_model_no = (String) data.get("truck_model_no");
                String truck_carriage_type_id = (String) data.get("truck_carriage_type_id");
                String truck_length_id = (String) data.get("truck_length_id");
                String truck_fuel_type_id = (String) data.get("truck_fuel_type_id");
                String insurance_company_id = (String) data.get("insurance_company_id");
                truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
                truck.setDriving_licence(driving_licence);
                truck.setLoad_weight(load_weight);
                truck.setVehicle_identify_number(vehicle_identify_number);
                truck.setEngine_number(engine_number);
                truck.setTruck_type_id(truck_type_id);
                truck.setTruck_model_no(truck_model_no);
                truck.setTruck_carriage_type_id(truck_carriage_type_id);
                truck.setTruck_length_id(truck_length_id);
                truck.setTruck_fuel_type_id(truck_fuel_type_id);
                truck.setInsurance_company_id(insurance_company_id);
                truck.setSubmit_verify_time(DateUtil.getDate());    // 新增提交审核时间
                truck.setLast_update(DateUtil.getDate());           // 新增最终更新日
                truck.setLast_update_user_id("U:" + userName);   // 新增最终更新者
                truck.setVerify_status("1");

                // 判断是否更换了图片
                if (pathMap.get("driving_licence_first_page_path") != null) {
                    truck.setDriving_licence_first_page_save_path(pathMap.get("driving_licence_first_page_path").toString());
                }
                if (pathMap.get("driving_licence_second_page_path") != null) {
                    truck.setDriving_licence_second_page_save_path(pathMap.get("driving_licence_second_page_path").toString());
                }
                if (pathMap.get("truck_first_pic_path") != null) {
                    truck.setTruck_first_pic_save_path(pathMap.get("truck_first_pic_path").toString());
                }
                if (pathMap.get("truck_second_pic_path") != null) {
                    truck.setTruck_second_pic_save_path(pathMap.get("truck_second_pic_path").toString());
                }
                if (pathMap.get("truck_third_pic_path") != null) {
                    truck.setTruck_third_pic_save_path(pathMap.get("truck_third_pic_path").toString());
                }
                this.truckDaoMapper.updateTruck(truck);
                flag = 1;
                logger.info(MessageUtil.saveInfo);
            } else {
                flag = 2;
            }
            if (flag == 1) {//保存成功
            } else if (flag == 0) {//重复
                throw new RuntimeException(String.valueOf(flag));
            } else if (flag == -1) {
                throw new RuntimeException(String.valueOf(flag));
            }
        } catch (RuntimeException e) {
            if (e.getMessage().equals("0")) {
                flag = 0;
            } else {
                flag = -1;
            }
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：findCompanyVehicleDriverList
     * 内容摘要：查询物流公司下无车辆的司机列表（去除车辆管理者）
     *
     * @return findCompanyVehicleDriverList 当前物流公司下无车辆司机列表
     */
    public List<T_Data_Company_Vehicle_Driver> findCompanyVehicleDriverList(HashMap<String, String> conditions) {
        List<T_Data_Company_Vehicle_Driver> companyVehicleDriverList = null;
        T_Data_Member member = null;
        try {
            companyVehicleDriverList = this.vehicleDaoMapper.findCompanyVehicleDriverList(conditions);
            logger.info(TruckManager_Message.seacheInfo);
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return companyVehicleDriverList;
    }

    /**
     * 方法名称：findCompanyVehicleDriverAndManagerList
     * 内容摘要：查询物流公司下无车辆的司机列表
     *
     * @return findCompanyVehicleDriverAndManagerList 当前物流公司下无车辆司机列表
     */
    public List<T_Data_Company_Vehicle_Driver> findCompanyVehicleDriverAndManagerList(HashMap<String, String> conditions) {
        List<T_Data_Company_Vehicle_Driver> companyVehicleDriverList = null;
        T_Data_Member member = null;
        try {
            companyVehicleDriverList = this.vehicleDaoMapper.findCompanyVehicleDriverAndManagerList(conditions);
            logger.info(TruckManager_Message.seacheInfo);
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return companyVehicleDriverList;
    }

    /**
     * 方法名称：logisticsVehicleDriverSetUp
     * 内容摘要：设定为车组成员
     * @param person_mobile_phone 设定为车组成员的手机号
     */
    @Transactional
    public void logisticsVehicleDriverSetUp(String person_mobile_phone, String plate_number, String username) {
        T_Data_Truck truck = null;
        T_Data_Person person = null;
        try {
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            person = personDaoMapper.findPersonByPhone(person_mobile_phone, "1");
            person.setDriving_truck_id(truck.getTruck_id());
            person.setDriving_status("1");
            person.setLast_update(DateUtil.getDate());
            person.setLast_update_user_id("U:" + username);
            this.personDaoMapper.updatePerson(person);
        } catch (Exception e) {
            logger.error(TruckManager_Message.updataErr + e.getMessage());
        }
    }

    /**
     * 方法名称：logisticsVehicleManagerSetUp
     * 内容摘要：设定为车辆管理者
     * @param person_mobile_phone 设定为车辆管理者的手机号
     */
    @Transactional
    public void logisticsVehicleManagerSetUp(String person_mobile_phone, String plate_number, String username) {
        T_Data_Truck truck = null;
        T_Data_Person person = null;
        try {
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            person = personDaoMapper.findPersonByPhone(person_mobile_phone, "1");
            truck.setManager_member_id(person.getPerson_id());
            truck.setLast_update(DateUtil.getDate());
            truck.setLast_update_user_id("U:" + username);
            this.truckDaoMapper.updateTruck(truck);
        } catch (Exception e) {
            logger.error(TruckManager_Message.updataErr + e.getMessage());
        }
    }

    /**
     * 方法名称：logisticsVehicleUnbound
     * 内容摘要：物流公司解绑车辆相关人员
     * @param plate_number 车牌号
     */
    @Transactional
    public int logisticsVehicleUnbound(String plate_number, String username) {
        T_Data_Truck truck = null;                                          // 获取车辆信息
        T_Data_Person person = null;
        List<T_Data_Person> personList = null;                              // 获取司机信息
        List<T_Data_Transportation_Schedule_Sheet> scheduleSheets = null;   // 获取调度单信息
        List<T_Data_Truck_Transport_Line> truckTransportLineList = null;    // 获取常跑路线信息
        int flag = 0;
        double cashAmount = 0;
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            T_Data_Member member = memberDaoMapper.findMemberByMobilePhone(username);
            if (plate_number != null) {
                Integer count = this.scheduleSheetDaoMapper.findScheduleUnFinishedByTruckIdAndStatusCount(truck.getTruck_id());   // 获取未完成订单的个数
                personList = personDaoMapper.findPersonByTruckId(truck.getTruck_id());  // 获取车辆全部关联司机列表
                truckTransportLineList = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck.getTruck_id());// 获取车辆全部常跑路线列表
                if (count.equals(0)) {
                    // 无未完成订单
                    if (personList != null && personList.size() > 0) {
                        // 清除关联司机
                        for (T_Data_Person persons : personList) {
                            persons.setDriving_truck_id(null);
                            persons.setDriving_status(null);
                            persons.setLast_update(DateUtil.getDate());
                            persons.setLast_update_user_id("U:" + username);
                            this.personDaoMapper.updatePerson(persons);
                        }
                    }
                    if (truckTransportLineList != null && truckTransportLineList.size() > 0) {
                        // 清除车辆常跑路线
                        for (T_Data_Truck_Transport_Line truckTransportLine : truckTransportLineList) {
                            truckTransportLine.setLast_update(DateUtil.getDate());
                            truckTransportLine.setLast_update_user_id("U:" + username);
                            truckTransportLine.setDelete_flag(1);
                            this.truckTransportLineDaoMapper.updateTruckTransportLine(truckTransportLine);
                        }
                    }
                    cashAmount = truck.getCash_amount();    // 获取卡车现金额度
                    truck.setCash_amount(0);                                // 卡车现金额度清0
                    truck.setDelete_flag(1);                               // 逻辑删除车辆
                    truck.setLast_update(DateUtil.getDate());              // 最终跟新日
                    truck.setLast_update_user_id("U:" + username);      // 最终更新者ID
                    if (cashAmount != 0) {
                        String thirdPartyOrderID = RadomUtil.getOrderNo();
                        truck.setCash_amount(0);                                // 卡车现金额度清0
                        // 将剩余卡车现金额度转移到会员的会员账户资金量
                        double amount = member.getMember_account_amount() + cashAmount;
                        member.setMember_account_amount(amount);

                        T_Data_Member_Payment_History memberPaymentHistory = new T_Data_Member_Payment_History();// 创建会员支付历史信息
                        memberPaymentHistory.setMember_id(member.getRelevance_info_id());
                        memberPaymentHistory.setPayment_type("3");
                        memberPaymentHistory.setTarget_id("T" + truck.getTruck_id());
                        memberPaymentHistory.setAmount(cashAmount);
                        memberPaymentHistory.setCreate_time(DateUtil.getDate());
                        memberPaymentHistory.setReturn_result("2");
                        memberPaymentHistory.setPayment_result("0");
                        memberPaymentHistory.setThird_party_order_id(thirdPartyOrderID);
                        memberPaymentHistory.setDelete_flag("0");
                        memberPaymentHistory.setLast_update(DateUtil.getDate());
                        memberPaymentHistory.setLast_update_user_id("U:" + username);
                        memberPaymentHistoryDaoMapper.saveMemberPaymentHistory(memberPaymentHistory);

                        T_Data_Truck_Payment_History truckPaymentHistory = new T_Data_Truck_Payment_History();// 创建车辆支付历史信息
                        truckPaymentHistory.setTruck_id(truck.getTruck_id());
                        truckPaymentHistory.setPayment_type("1");
                        truckPaymentHistory.setTarget_id("M" + member.getMember_id());
                        truckPaymentHistory.setAmount(cashAmount);
                        truckPaymentHistory.setCreate_time(DateUtil.getDate());
                        truckPaymentHistory.setReturn_result("2");
                        truckPaymentHistory.setPayment_result("0");
                        truckPaymentHistory.setThird_party_order_id(thirdPartyOrderID);
                        truckPaymentHistory.setDelete_flag("0");
                        truckPaymentHistory.setLast_update(DateUtil.getDate());
                        truckPaymentHistory.setLast_update_user_id("U:" + username);
                        truckPaymentHistoryDaoMapper.saveTruckPaymentHistory(truckPaymentHistory);
                    }
                    this.truckDaoMapper.updateTruck(truck);
                    this.memberDaoMapper.updateMember(member);
                    flag = 1;
                } else {
                    flag = 2; //有未完成订单
                    throw new RuntimeException(String.valueOf(flag));
                }
            } else {
                flag = 3; //车辆不存在
                throw new RuntimeException(String.valueOf(flag));
            }
        } catch (Exception e) {
            if (e.getMessage().equals("0")){
                flag = 0;
            }else if (e.getMessage().equals("2")){
                flag = 2;//有未完成订单
            }else if (e.getMessage().equals("3")){
                flag = 3;
            }
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：logisticsVehicleManagerUnbound
     * 内容摘要：解绑车辆管理者
     * @param plate_number 车牌号
     */
    @Transactional
    public int logisticsVehicleManagerUnbound(String plate_number, String username) {
        T_Data_Truck truck = null;                                          // 获取车辆信息
        T_Data_Person person = null;
        List<T_Data_Transportation_Schedule_Sheet> scheduleSheets = null;   // 获取调度单信息
        int flag = 0;
        double cashAmount = 0;
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            T_Data_Member member = memberDaoMapper.findMemberByMobilePhone(username);
            if (plate_number != null) {
                Integer count = this.scheduleSheetDaoMapper.findScheduleUnFinishedByTruckIdAndStatusCount(truck.getTruck_id());   // 获取未完成订单的个数
                if (count.equals(0)) {
                    // 无未完成订单
                    truck.setManager_member_id("none");                     // 解绑车辆管理者
                    truck.setLast_update(DateUtil.getDate());               // 最终跟新日
                    truck.setLast_update_user_id("U:" + username);          // 最终更新者ID
                    this.truckDaoMapper.updateTruck(truck);
                    flag = 1;
                } else {
                    flag = 2; //有未完成订单
                    throw new RuntimeException(String.valueOf(flag));
                }
            } else {
                flag = 3; //车辆不存在
                throw new RuntimeException(String.valueOf(flag));
            }
        } catch (Exception e) {
            if (e.getMessage().equals("0")){
                flag = 0;
            }else if (e.getMessage().equals("2")){
                flag = 2;//有未完成订单
            }else if (e.getMessage().equals("3")){
                flag = 3;
            }
            throw new RuntimeException(flag + "");
        }
        return flag;
    }
}