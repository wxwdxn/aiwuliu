/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：卡车查询接口实现
 * 设计文件：
 * 完成日期：2016-03-03
 * 作    者：QJ
 * 内容摘要：卡车查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.TruckService;
import com.cn.gazelle.logistics.util.*;
import com.cn.gazelle.logistics.util.message.TruckManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.cn.gazelle.logistics.util.rbUtil.config.Decipher;
import com.cn.gazelle.logistics.util.rbUtil.config.ReapalConfig;
import com.cn.gazelle.logistics.util.rbUtil.config.ReapalSubmit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称: TruckServiceImpl
 * 内容摘要: 卡车信息查询接口
 * 方法描述：该类有55个方法：
 * 01 findTruckByID                     根据绑定车辆id查询卡车信息
 * 02 findTruckByPlateNumber            根据车牌号查找卡车信息
 * 03 findAllTruck                      查询符合条件的卡车列表信息（默认查询所有卡车）
 * 04 findAllTruckRowsCount             查询卡车记录数(包含有条件和无条件)
 * 05 saveTruck                         增卡车信息
 * 06 updateTruck                       更新卡车信息
 * 07 delTruck                          删除卡车信息
 * 08 findTruckByOwnerMemberID          通过车主会员关联ID查询卡车信息列
 * 09 findTruckByDepositMemberID        通过托管对象会员关联ID查询卡车信息列
 * 10 findTruckByManagerMemberID        通过车辆管理者会员关联ID查询卡车信息列表
 * 11 findTruckByMember                 根据账号、车辆的所属身份、车辆状态获取车辆列表
 * 12 findTruckDataByPlateNumber        根据车牌号查找卡车相关信息
 * 13 editTruckByMember                 根据账号修改车辆托管对象以及车辆管理者的信息
 * 14 addTruckInfo                      添加卡车信息
 * 15 setTruckInfo                      车辆信息的设定
 * 16 delTruckInfo                      车辆所属身份信息的删除
 * 17 findWaitTruckByMember             根据账号获取待单中或仅有一单，有司机，未维修的车辆列表信息
 * 18 findLoadWeightByMember            根据账号获取待单有司机车辆运力
 * 19 findTruckByMemberAndPlateNumber   根据账号名、车辆的所属身份、车牌号模糊参数，获取模糊查询后的车辆列表
 * 20 findTruckDataByMember             根据账户名获取我驾驶车辆信息
 * 21 setRepairStatus                   设定车辆修理状态
 * 22 findAllTruckTypeName              检索车辆车类型
 * 23 findAllTruckTypeLength            检索所有车长
 * 24 searchTruckDataByPlateNumber      根据车牌号检索车辆详细信息
 * 25 searchTruckDataByMemberName       根据账号和雇佣关系检索车辆详细信息
 * 26 addTruckInformation               添加卡车的信息
 * 27 searchDriverOften                 检索常跑司机列表(2.0车组成员)
 * 28 editManagerMember                 车辆管理者变更
 * 29 truckDriversChange                车辆行使司机变更
 * 30 joinTheDriver                     通过车牌号和会员号申请加入常跑司机
 * 31 searchDriverByPlateNumber         通过车牌号检索待确认司机列表
 * 32 judgeJoinDriver                   确认是否允许加入常跑司机
 * 33 relieveTruckAndDriver             解除司机与车辆的关系
 * 34 findAllTruckFuelType              检索车辆燃料类型
 * 35 setTruckPermanentAddress          设置车辆的常驻地址
 * 36 editTruckInfo                     修改车辆基本信息
 * 37 findManagerTruckListByMember      根据账号名查询管理者车辆列表_v2.0
 * 38 saveTruckInformation              添加卡车的信息_v2.0
 * 39 searchDriverList                  检索常跑司机列表_v2.0
 * 40 findTruckDataByMemberName         根据账号和雇佣关系检索车辆详细信息_v2.0
 * 41 findTruckDataByTruckPlateNumber   根据车牌号检索车辆详细信息_v2.0
 * 42 relieveTruckAndMember             解除会员与车辆的关系_v2.0
 * 43 findAddTruckAuthority             根据车牌号会员名检索添加车辆权限_v2.0
 * 44 findAllInsuranceCompany           检索所有保险公司
 * 45 findAllTruckBrand                 检索所有车辆品牌
 * 46 findAllTruckModeByTruckBrandId    根据品牌ID查询所有的卡车型号
 * 47 findManagerTruckListByDriver      根据账号名查询司机车辆列表_v2.0
 * 48 findByCargoTypeAndLineId          查询符合条件的卡车列表信息（货物类型，干线）
 * 49 findTruckAccount                  车辆账户检索（支付）
 * 50 rechargeTruckAccount              车辆账户充值（支付）
 * 51 withdrawalsTruckAccount           车辆账户提现（支付）
 * 52 findTruckAccountDetail            车辆账户明细检索（支付）
 * 53 checkTruckPaymentPassword         车辆账户密码检索（支付）
 * 54 findAllBank                       检索所有银行
 * 55 scanQRCodePayment                 车辆扫描二维码支付（支付）
 *
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckServiceImpl implements TruckService {
    private static final String AGENTPAY_URL = "/agentpay/pay";  // 代付请求地址
    private static final String RESULT_CODE_SUCCESS = "0000";
    public static String code;                                              // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(TruckServiceImpl.class);
    // 数据访问层
    @Resource
    private TruckDaoMapper truckDaoMapper;
    @Resource
    private MemberDaoMapper memberDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;
    @Resource
    private ScheduleSheetDaoMapper scheduleSheetDaoMapper;
    @Resource
    private AttributionDaoMapper attributionDaoMapper;
    @Resource
    private CompanyDaoMapper companyDaoMapper;
    @Resource
    private TruckTransportLineDaoMapper truckTransportLineDaoMapper;
    @Resource
    private TransportationOrderDaoMapper transportationOrderDaoMapper;
    @Resource
    private CargoDaoMapper cargoDaoMapper;
    @Resource
    private DispatchSheetDaoMapper dispatchSheetDaoMapper;
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;
    @Resource
    private TruckCarriageTypeDaoMapper truckCarriageTypeDaoMapper;
    @Resource
    private TruckModelDaoMapper truckModelDaoMapper;
    @Resource
    private MemberPaymentHistoryDaoMapper memberPaymentHistoryDaoMapper;
    @Resource
    private TruckPaymentHistoryDaoMapper truckPaymentHistoryDaoMapper;
    @Resource
    private StationDaoMapper stationDaoMapper;
    @Resource
    private SysInfoDaoMapper sysInfoDaoMapper;
    @Resource
    private TruckPaymentErrorPasswordCommitHistoryDaoMapper truckPaymentErrorPasswordCommitHistoryDaoMapper;
    @Resource
    private TruckImmediateDaoMapper truckImmediateDaoMapper;
    @Resource
    private TransportationScheduleFinanceRecordDaoMapper transportationScheduleFinanceRecordDaoMapper;

    /**
     * 方法名称：findTruckByID
     * 内容摘要：根据绑定车辆id查询卡车信息
     *
     * @param truck_id 绑定车辆id
     * @return T_Data_Truck 卡车信息
     */
    public T_Data_Truck findTruckByID(String truck_id) {
        T_Data_Truck truck = null;
        try {
            truck = this.truckDaoMapper.findTruckByID(truck_id);
            logger.info(TruckManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        return truck;
    }

    /**
     * 方法名称：findTruckByPlateNumber
     * 内容摘要：根据车牌号查找卡车信息
     *
     * @param plate_number 车牌号
     * @return T_Data_Truck 卡车信息
     */
    public T_Data_Truck findTruckByPlateNumber(String plate_number) {
        T_Data_Truck truck = null;
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            logger.info(TruckManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        return truck;
    }

    /**
     * 方法名称：findAllTruck
     * 内容摘要：查询符合条件的卡车列表信息（默认查询所有卡车）
     * @return List<T_Data_Truck>   卡车信息列表
     */
    public List<T_Data_Truck> findAllTruck() {
        List<T_Data_Truck> list = null;
        try {
            list = this.truckDaoMapper.findAllTruck();
            logger.info(TruckManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findAllTruckRowsCount
     * 内容摘要：查询符合条件的卡车列表信息（默认查询所有卡车）
     *
     * @param search_type 搜索类型
     * @param name        查询类型
     * @return Integer  卡车信息记录数
     */
    public Integer findAllTruckRowsCount(String search_type, String name) {
        int c = 0;
        try {
            c = this.truckDaoMapper.findAllTruckRowsCount(search_type, name);
        } catch (Exception e) {
            logger.error(TruckManager_Message.getSelectTruckCountError + e.getMessage());
        }
        return c;
    }

    /**
     * 方法名称：saveTruck
     * 内容摘要：增卡车信息
     *
     * @param truck 卡车信息
     */
    public int saveTruck(T_Data_Truck truck) {
        int count = 0;
        try {
            count = this.truckDaoMapper.saveTruck(truck);
            if (count == 1) {
                logger.info(MessageUtil.saveInfo);
            } else if (count == 0) {
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(TruckManager_Message.saveErr + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateTruck
     * 内容摘要：更新卡车信息
     *
     * @param truck 卡车信息
     */
    public boolean updateTruck(T_Data_Truck truck) {
        boolean b = new Boolean(true);
        try {
            this.truckDaoMapper.updateTruck(truck);
            logger.info(TruckManager_Message.updataDone);
        } catch (Exception e) {
            b = false;
            logger.error(TruckManager_Message.updataErr + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delTruck
     * 内容摘要： 删除卡车信息
     *
     * @param truck_id 绑定车辆ID
     */
    public void delTruck(String truck_id) {
        try {
            this.truckDaoMapper.delTruck(truck_id);
            logger.info(TruckManager_Message.delDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.delErr + e.getMessage());
        }
    }

    /**
     * 方法名称：findTruckByOwnerMemberID
     * 内容摘要：通过车主会员关联ID查询卡车信息列
     *
     * @param owner_member_id 车主会员关联ID
     * @return List<T_Data_Truck> 卡车信息列
     */
    public List<T_Data_Truck> findTruckByOwnerMemberID(String owner_member_id) {
        List<T_Data_Truck> list = null;
        try {
            list = this.truckDaoMapper.findTruckByOwnerMemberID(owner_member_id);
            logger.info(TruckManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findTruckByDepositMemberID
     * 内容摘要：通过托管对象会员关联ID查询卡车信息列
     *
     * @param deposit_member_id 托管对象会员关联ID
     * @return List<T_Data_Truck> 卡车信息列
     */
    public List<T_Data_Truck> findTruckByDepositMemberID(String deposit_member_id) {
        List<T_Data_Truck> list = null;
        try {
            list = this.truckDaoMapper.findTruckByDepositMemberID(deposit_member_id);
            logger.info(TruckManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findTruckByManagerMemberID
     * 内容摘要：通过车辆管理者会员关联ID查询卡车信息列表
     *
     * @param manager_member_id 车辆管理者会员关联ID
     * @return List<T_Data_Truck> 卡车信息列
     */
    //通过车辆管理者会员ID查询卡车信息列表
    public List<T_Data_Truck> findTruckByManagerMemberID(String manager_member_id) {
        List<T_Data_Truck> list = null;
        try {
            list = this.truckDaoMapper.findTruckByManagerMemberID(manager_member_id);
            logger.info(TruckManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findTruckByMember
     * 内容摘要：根据账号、车辆的所属身份、车辆状态获取车辆列表
     * @param member_name 账号名
     * @param identity 车辆所属身份
     * @param begin_rows 分页显示起始数
     * @param end_rows 分页显示终止数
     * @return String 返回值json
     */
//    public String findTruckByMember(String member_name, String identity, Integer begin_rows, Integer end_rows) {
//        Map result = new HashMap();
//        String json = null;
//        String ecode = null;
//        T_Data_Member member = null;        // 获取账户
//        try {
//            member = memberDaoMapper.findMemberByName(member_name);
//            if (member == null) {
//                ecode = "3000";             // 用户信息不存在！
//                result.put("ecode", ecode);
//            } else {
//                List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
//                // 车辆所属身份判断
//                Integer identity1 = Integer.valueOf(identity);
//                try {
//                    if (identity1 == 0) {
//                        // 车主或车辆托管者
//                        try {
//                            String relevance_info_id = member.getRelevance_info_id();   // 获取车主或车辆托管者ID
//                            List<T_Data_Truck> ownerList = null;
//                            List<T_Data_Truck> depositList = null;
//                            ownerList = truckDaoMapper.findTruckByOwnerMemberID(relevance_info_id); // 获得车主所有车辆信息列表
//                            depositList = truckDaoMapper.findTruckByDepositMemberID(relevance_info_id); // 获得车辆托管者所有车辆信息列表
//                            List<T_Data_Truck> ordList = null;
//                            ordList = truckDaoMapper.findTruckByOwnerMemberIDDepositMemberID(relevance_info_id);
//                            depositList.addAll(ordList);    // 合并去重
//
//                            for (T_Data_Truck trucks : depositList) {
//                                Map<String, String> results = new HashMap<String, String>();
//                                results.put("plate_number", trucks.getPlate_number() + ""); // 获得管理车辆车牌
//                                T_Master_Truck_Model tm = null; // 车辆型号
//                                String ttid = null; // 车辆类型ID
//                                T_Master_Truck_Type tt = null;  // 车辆类型
//                                String tmid = trucks.getTruck_model_id();   // 车辆型号ID
//                                tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                                ttid = tm.getTruck_type_id();
//                                tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                                results.put("truck_type_name", tt.getTruck_type_name() + "");   // 获得车型
//                                String trucksId = trucks.getTruck_id();
//                                List<T_Data_Transportation_Schedule_Sheet> scheduleSheet = null;
//                                scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetByScheduleTruckID(trucksId);
//
//                                if (scheduleSheet != null) {
//                                    int i = 0;//判断是否待单
//                                    for (T_Data_Transportation_Schedule_Sheet status_list : scheduleSheet) {
//                                        String truckStatus = status_list.getStatus();
//                                        if (Integer.parseInt(truckStatus) < 7) {
//                                            i = 1;//未待单
//                                            results.put("status", "0"); // 获取车辆状态
//                                        }
//                                    }
//                                    if(i == 0) {
//                                        results.put("status", "1"); // 获取车辆状态
//                                    }
//                                }
//                                lists.add(results);
//                            }
//                        } catch (Exception e) {
//                            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
//                        }
//                    } else if (identity1 == 1) {
//                        // 车辆管理者
//                        try {
//                            String relevance_info_id = member.getRelevance_info_id();   // 获取车辆管理者ID
//                            List<T_Data_Truck> list = null;
//                            list = truckDaoMapper.findTruckByManagerMemberID(relevance_info_id);    // 获得车辆管理者所有车辆信息列表
//                            for (T_Data_Truck trucks : list) {
//                                Map<String, String> results = new HashMap<String, String>();
//                                results.put("plate_number", trucks.getPlate_number() + ""); // 获得管理车辆车牌
//                                T_Master_Truck_Model tm = null; // 车辆型号
//                                String ttid = null; // 车辆类型ID
//                                T_Master_Truck_Type tt = null;  // 车辆类型
//                                String tmid = trucks.getTruck_model_id();   // 车辆型号ID
//                                tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                                ttid = tm.getTruck_type_id();
//                                tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                                results.put("truck_type_name", tt.getTruck_type_name() + "");   // 获得车型
//                                String trucksId = trucks.getTruck_id();
//                                List<T_Data_Transportation_Schedule_Sheet> scheduleSheet = null;
//                                scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetByScheduleTruckID(trucksId);
//                                if (scheduleSheet != null) {
//                                    int i = 0;//判断是否待单
//                                    for (T_Data_Transportation_Schedule_Sheet status_list : scheduleSheet) {
//                                        String truckStatus = status_list.getStatus();
//                                        if (Integer.parseInt(truckStatus) < 7) {
//                                            i = 1;//未待单
//                                            results.put("status", "0"); // 获取车辆状态
//                                        }
//                                    }
//                                    if(i == 0) {
//                                        results.put("status", "1"); // 获取车辆状态
//                                    }
//                                }
//                                lists.add(results);
//                            }
//                        } catch (Exception e) {
//                            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
//                        }
//                    } else if (identity1 == 2) {
//                        // 常跑司机
//                        Map<String, String> results = new HashMap<String, String>();
//                        String person_id = member.getRelevance_info_id();
//                        T_Data_Person person = personDaoMapper.findPersonByID(person_id);
//                        String truckId = person.getTruck_id();
//                        T_Data_Truck truck = truckDaoMapper.findTruckByID(truckId);
//                        results.put("plate_number", truck.getPlate_number() + "");  // 获得车牌号
//                        T_Master_Truck_Model tm = null; // 车辆型号
//                        String ttid = null; // 车辆类型ID
//                        T_Master_Truck_Type tt = null;  // 车辆类型
//                        String tmid = truck.getTruck_model_id();   // 车辆型号ID
//                        tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                        ttid = tm.getTruck_type_id();
//                        tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                        results.put("truck_type_name", tt.getTruck_type_name() + "");   // 获得车型
//                        List<T_Data_Transportation_Schedule_Sheet> scheduleSheet = null;
//                        scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetByScheduleTruckID(truckId);
//                        if (scheduleSheet != null) {
//                            int i = 0;  // 判断是否待单
//                            for (T_Data_Transportation_Schedule_Sheet status_list : scheduleSheet) {
//                                String truckStatus = status_list.getStatus();
//                                if (Integer.parseInt(truckStatus) < 7) {
//                                    i = 1;  // 未待单
//                                    results.put("status", "0"); // 获取车辆状态
//                                }
//                            }
//                            if(i == 0) {
//                                results.put("status", "1"); // 获取车辆状态
//                            }
//                            lists.add(results);
//                        }
//                    } else {
//                        ecode = "3001"; // 车辆所属身份获取失败
//                        result.put("ecode", ecode);
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getIdentityErr + e.getMessage());
//                }
//                ecode = "1000"; // 成功响应！
//                result.put("ecode", ecode);
//                result.put("object1", lists);
//                logger.info(TruckManager_Message.getTruckDone);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 获取数据失败！
//            result.put("ecode", ecode);
//            logger.error(TruckManager_Message.getTruckErr);
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//    }

    /**
     * 方法名称：findTruckDataByPlateNumber
     * 内容摘要：根据车牌号查找卡车相关信息
     * @param plate_number 车牌号
     * @return String 返回值json
     */
//    public String findTruckDataByPlateNumber(String plate_number) {
//        Map result = new HashMap();
//        Map results = new HashMap();
//        String json = null;
//        String ecode = null;
//        T_Data_Truck plateNumber = null;    // 获取车辆
//        try {
//            plateNumber = truckDaoMapper.findTruckByPlateNumber(plate_number);
//            if (plateNumber == null) {
//                ecode = "3000"; // 车辆信息不存在！
//                result.put("ecode", ecode);
//            } else {
//                plateNumber = truckDaoMapper.findTruckByPlateNumber(plate_number);
//                // 获取车长度、载重、体积、行驶证照片、修理状态
//                results.put("truck_length", plateNumber.getTruck_length());
//                results.put("load_weight", plateNumber.getLoad_weight());
//                results.put("volume", plateNumber.getVolume());
//                results.put("driving_licence_pic_id", plateNumber.getDriving_licence_pic_id() + "");
//                results.put("repair_status", plateNumber.getRepair_status() + "");
//
//                try {
//                    // 获取所属城市
//                    String dicdata_name = null;
//                    String remark = null;
//                    T_Sys_Dicdata attribution = null;
//
//                    String str = plate_number;
//                    String subStr = str.substring(0, 2);
//                    dicdata_name = subStr;
//                    attribution = attributionDaoMapper.findDicdataByDicdataName(dicdata_name);
//                    remark = attribution.getDicdata_remark();
//                    results.put("dicdata_remark", remark);
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckCityErr + e.getMessage());
//                }
//
//                try {
//                    // 获取车型
//                    T_Master_Truck_Model tm = null; // 车辆型号
//                    String ttid = null; // 车辆类型ID
//                    T_Master_Truck_Type tt = null;  // 车辆类型
//                    String tmid = plateNumber.getTruck_model_id();   // 车辆型号ID
//                    tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                    ttid = tm.getTruck_type_id();
//                    tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                    results.put("truck_type_name", tt.getTruck_type_name() + "");
//                    results.put("truck_model_name", tm.getModel_name() + "");
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
//                }
//
//                try {
//                    // 获取车主、车主电话
//                    String ownerMemberID = null;
//                    ownerMemberID = plateNumber.getOwner_member_id();
//                    T_Data_Member member = null;
//                    member = memberDaoMapper.findMerberByRelevance_info_id(ownerMemberID);
//                    String accountType1 = member.getAccount_type();
//                    String relevance_info_id = member.getRelevance_info_id();
//                    String name = null;
//                    String mobilePhone = null;
//                    Integer accountType = Integer.valueOf(accountType1);
//                    if (accountType == 0) {
//                        // 个人信息
//                        T_Data_Person person = null;
//                        person = personDaoMapper.findPersonByID(relevance_info_id);
//                        results.put("person_name_owner", person.getPerson_name() + "");
//                        results.put("person_mobile_phone_owner", person.getPerson_mobile_phone() + "");
//                    } else if (accountType == 1) {
//                        // 公司信息
//                        T_Data_Company company = null;
//                        company = companyDaoMapper.findCompanyByID(relevance_info_id);
//                        results.put("person_name_owner", company.getCompany_name() + "");
//                        results.put("person_mobile_phone_owner", company.getContact_mobile_phone() + "");
//                    } else {
//                        ecode = "3001"; // 车主信息获取失败
//                        result.put("ecode", ecode);
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckOwnerErr + e.getMessage());
//                }
//
//                try {
//                    // 获取托管对象、托管对象电话
//                    String depositMemberID = null;
//                    depositMemberID = plateNumber.getDeposit_member_id();
//                    T_Data_Member member = null;
//                    member = memberDaoMapper.findMerberByRelevance_info_id(depositMemberID);
//                    String accountType1 = member.getAccount_type();
//                    String relevance_info_id = member.getRelevance_info_id();
//                    Integer accountType = Integer.valueOf(accountType1);
//                    if (accountType == 0) {
//                        // 个人信息
//                        T_Data_Person person = null;
//                        person = personDaoMapper.findPersonByID(relevance_info_id);
//                        results.put("person_name_deposit", person.getPerson_name() + "");
//                        results.put("person_mobile_phone_deposit", person.getPerson_mobile_phone() + "");
//                    } else if (accountType == 1) {
//                        // 公司信息
//                        T_Data_Company company = null;
//                        company = companyDaoMapper.findCompanyByID(relevance_info_id);
//                        results.put("person_name_deposit", company.getCompany_name() + "");
//                        results.put("person_mobile_phone_deposit", company.getContact_mobile_phone() + "");
//                    } else {
//                        ecode = "3002"; // 托管对象信息获取失败
//                        result.put("ecode", ecode);
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckDepositErr + e.getMessage());
//                }
//
//                try {
//                    // 获取车辆管理者、车辆管理者电话
//                    String managerMemberID = null;
//                    managerMemberID = plateNumber.getManager_member_id();
//                    T_Data_Member member = null;
//                    member = memberDaoMapper.findMerberByRelevance_info_id(managerMemberID);
//                    String accountType1 = member.getAccount_type();
//                    String relevance_info_id = member.getRelevance_info_id();
//                    Integer accountType = Integer.valueOf(accountType1);
//                    if (accountType == 0) {
//                        // 个人信息
//                        T_Data_Person person = null;
//                        person = personDaoMapper.findPersonByID(relevance_info_id);
//                        results.put("person_name_manager", person.getPerson_name() + "");
//                        results.put("person_mobile_phone_manager", person.getPerson_mobile_phone() + "");
//                    } else if (accountType == 1) {
//                        // 公司信息
//                        T_Data_Company company = null;
//                        company = companyDaoMapper.findCompanyByID(relevance_info_id);
//                        results.put("person_name_manager", company.getCompany_name() + "");
//                        results.put("person_mobile_phone_manager", company.getContact_mobile_phone() + "");
//                    } else {
//                        ecode = "3003"; // 车辆管理者信息获取失败
//                        result.put("ecode", ecode);
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckManagerErr + e.getMessage());
//                }
//
//                try {
//                    // 获取常跑司机
//                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
//                    String truckId = null;
//                    truckId = plateNumber.getTruck_id();
//                    String truck_id = truckId;
//                    List<T_Data_Person> list = null;
//                    list = personDaoMapper.findPersonByTruckId(truck_id);
//                    for (T_Data_Person person : list) {
//                        Map<String, String> resultss = new HashMap<String, String>();
//                        T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
//                        String member_name_drive = drive.getMember_name();
//                        resultss.put("member_name", member_name_drive + "");
//                        resultss.put("person_name_driver", person.getPerson_name() + "");
//                        listss.add(resultss);
//                    }
//                    results.put("driver", listss);
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
//                }
//
//                try {
//                    // 常跑路线
//                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
//                    String truckId = null;
//                    truckId = plateNumber.getTruck_id();
//                    String truck_id = truckId;
//                    List<T_Data_Truck_Transport_Line> list = null;
//                    list = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck_id);
//                    for (T_Data_Truck_Transport_Line line : list) {
//                        Map<String, String> resultss = new HashMap<String, String>();
//                        resultss.put("start_city", line.getStart_city_id() + "");
//                        resultss.put("end_city", line.getEnd_city_id() + "");
//                        listss.add(resultss);
//                    }
//                    results.put("line", listss);
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckLineErr + e.getMessage());
//                }
//                ecode = "1000"; // 成功响应！
//                result.put("ecode", ecode);
//                result.put("object1", results);
//                logger.info(TruckManager_Message.getTruckDone);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 获取数据失败！
//            result.put("ecode", ecode);
//            logger.error(TruckManager_Message.getTruckErr);
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//    }

    /**
     * 方法名称：editTruckByMember
     * 内容摘要：根据账号修改车辆托管对象以及车辆管理者的信息
     * @param member_name 账号名
     * @param plate_number 车牌号
     * @param deposit_member_name 托管对象
     * @param manager_member_name 车辆管理者
     * @param truck_member_name_json 常跑司机
     * @param line_json 常跑路线
     * @param status 车辆状态
     * @param repair_status 修理状态
     * @return String 返回值ecode
     */
//    public String editTruckByMember(String member_name, String plate_number, String deposit_member_name,
//                                    String manager_member_name, String truck_member_name_json, String line_json,
//                                    Integer status, Integer repair_status) {
//        String ecode = null;
//        T_Data_Truck truck = null;
//        T_Data_Truck truck2 = null;
//        T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
//        List<T_Data_Person> person_list = null;     // 司机
//        List<T_Data_Person> person_list2 = null;    // 司机
//        T_Data_Member depositMember = null;
//        T_Data_Member managerMember = null;
//        String deposit_member_id = null;
//        String manager_member_id = null;
//        String truck_id = null;
//        List<T_Data_Person> person_list3 = null;
//
//        try {
//            truck2 = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
//            depositMember = this.memberDaoMapper.findMemberByName(deposit_member_name);
//            managerMember = this.memberDaoMapper.findMemberByName(manager_member_name);
//            // 获取当前（旧）托管对象会员名
//            T_Data_Member oldDepositMember = memberDaoMapper.findMerberByRelevance_info_id(truck2.getDeposit_member_id());
//            String oldDepositMemberName = oldDepositMember.getMember_name();
//            // 获取当前（旧）管理者会员名
//            T_Data_Member oldManagerMember = memberDaoMapper.findMerberByRelevance_info_id(truck2.getManager_member_id());
//            String oldManagerMemberName = oldManagerMember.getMember_name();
//
//            // 托管对象修改
//            if ((depositMember != null) && (status != 0)) {
//                deposit_member_id = depositMember.getRelevance_info_id();
//                truck2.setDeposit_member_id(deposit_member_id);
//                // 修改后删除常管理者
//                truck2.setManager_member_id(deposit_member_id);
//                this.truckDaoMapper.updateTruck(truck2);
//                // 修改后删除常跑司机的列表
//                person_list = this.personDaoMapper.findPersonByTruckId(truck2.getTruck_id());
//                // 旧司机列表
//                List<T_Data_Person> oldPersonList = person_list;
//
//                for (T_Data_Person person : person_list) {
//                    // 删除车辆原司机未完成派车单
//                    T_Data_Member member2 = this.memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
//                    List<T_Data_Transportation_Dispatch_Sheet> dispatchSheets = this.dispatchSheetDaoMapper.findDispatchByMemId(member2.getRelevance_info_id());
//                    if (dispatchSheets != null && dispatchSheets.size() != 0) {
//                        for (T_Data_Transportation_Dispatch_Sheet dispatchSheet : dispatchSheets) {
//                            T_Data_Transportation_Schedule_Sheet sheet = this.scheduleSheetDaoMapper.findScheduleSheetById(dispatchSheet.getScheduleSheetId());
//                            if (Integer.parseInt(sheet.getStatus()) < 7) {
//                                this.dispatchSheetDaoMapper.delDispatchByScheduleId(dispatchSheet.getScheduleSheetId());
//                            }
//                        }
//                    }
//                    // 删除车辆原司机
//                    person.setTruck_id("");
//                    this.personDaoMapper.updatePerson(person);
//                }
//                ecode = "1000"; // 修改成功
//                logger.info(TruckManager_Message.updataDepositDone);
//
//                if (!oldManagerMemberName.equals(member_name)) {
//                    // 给撤销的管理者（非自己）推送消息
//                    Map<String, String> extrasMap = new HashMap<String, String>();
//                    extrasMap.put("type", "carList");
//                    ManagePush.pushToAI(oldManagerMemberName, member_name + "已撤销你" + plate_number + "管理权限", extrasMap);
//                }
//
//                // 清除常跑司机推送消息
//                Map<String, String> extrasMap = new HashMap<String, String>();
//                for (T_Data_Person oldPerson : oldPersonList) {
//                    extrasMap.put("type", "carList");
//                    T_Data_Member oldMember = memberDaoMapper.findMerberByRelevance_info_id(oldPerson.getPerson_id());
//                    String oldMemberName = oldMember.getMember_name();
//                    DriverPush.pushToAI(oldMemberName, member_name + "已撤销你" + plate_number + "驾驶权限", extrasMap);
//                }
//            }
//
//            // 管理者修改
//            if ((managerMember != null) && (status != 0)) {
//                manager_member_id = managerMember.getRelevance_info_id();
//                truck2.setManager_member_id(manager_member_id);
//                this.truckDaoMapper.updateTruck(truck2);
//                // 修改后删除常跑司机的列表
//                person_list = this.personDaoMapper.findPersonByTruckId(truck2.getTruck_id());
//                // 旧司机列表
//                List<T_Data_Person> oldPersonList = person_list;
//                for (T_Data_Person person : person_list) {
//                    // 删除车辆原司机未完成派车单
//                    T_Data_Member member2 = this.memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
//                    List<T_Data_Transportation_Dispatch_Sheet> dispatchSheets = this.dispatchSheetDaoMapper.findDispatchByMemId(member2.getRelevance_info_id());
//                    if (dispatchSheets != null && dispatchSheets.size() != 0) {
//                        for (T_Data_Transportation_Dispatch_Sheet dispatchSheet : dispatchSheets) {
//                            T_Data_Transportation_Schedule_Sheet sheet = this.scheduleSheetDaoMapper.findScheduleSheetById(dispatchSheet.getScheduleSheetId());
//                            if (Integer.parseInt(sheet.getStatus()) < 7) {
//                                this.dispatchSheetDaoMapper.delDispatchByScheduleId(dispatchSheet.getScheduleSheetId());
//                            }
//                        }
//                    }
//                    // 删除车辆原司机
//                    person.setTruck_id("");
//                    this.personDaoMapper.updatePerson(person);
//                }
//
//                ecode = "1000"; // 修改成功
//                logger.info(TruckManager_Message.updataManagerDone);
//
//                if (member_name != manager_member_name) {
//                    // 给新管理者（非自己）推送消息
//                    Map<String, String> extrasMap = new HashMap<String, String>();
//                    extrasMap.put("type", "carDetail");
//                    extrasMap.put("plate_number", plate_number);
//                    ManagePush.pushToAI(manager_member_name, member_name + "已给你" + plate_number + "管理权限", extrasMap);
//                }
//
//                if (!oldManagerMemberName.equals(member_name)) {
//                    // 给撤销的管理者（非自己）推送消息
//                    Map<String, String> extrasMap = new HashMap<String, String>();
//                    extrasMap.put("type", "carList");
//                    ManagePush.pushToAI(oldManagerMemberName, member_name + "已撤销你" + plate_number + "管理权限", extrasMap);
//                }
//
//                // 清除常跑司机推送消息
//                Map<String, String> extrasMap = new HashMap<String, String>();
//                for (T_Data_Person oldPerson : oldPersonList) {
//                    extrasMap.put("type", "carList");
//                    T_Data_Member oldMember = memberDaoMapper.findMerberByRelevance_info_id(oldPerson.getPerson_id());
//                    String oldMemberName = oldMember.getMember_name();
//                    DriverPush.pushToAI(oldMemberName, member_name + "已撤销你" + plate_number + "驾驶权限", extrasMap);
//                }
//            }
//
//            try {
//                // 修改常跑司机
//                // 解析json为数组
//                Gson gson = new Gson();
//                String[] list = gson.fromJson(truck_member_name_json, String[].class);
//                truck2 = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
//                person_list = this.personDaoMapper.findPersonByTruckId(truck2.getTruck_id());
//                // 旧司机列表
//                List<T_Data_Person> oldPersonList = person_list;
//                if (list != null) {
//                    for (T_Data_Person person : person_list) {
//                        // 删除车辆原司机未完成派车单
//                        T_Data_Member member2 = this.memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
//                        List<T_Data_Transportation_Dispatch_Sheet> dispatchSheets = this.dispatchSheetDaoMapper.findDispatchByMemId(member2.getRelevance_info_id());
//                        if (dispatchSheets != null && dispatchSheets.size() != 0) {
//                            for (T_Data_Transportation_Dispatch_Sheet dispatchSheet : dispatchSheets) {
//                                T_Data_Transportation_Schedule_Sheet sheet = this.scheduleSheetDaoMapper.findScheduleSheetById(dispatchSheet.getScheduleSheetId());
//                                if (Integer.parseInt(sheet.getStatus()) < 7) {
//                                    this.dispatchSheetDaoMapper.delDispatchByScheduleId(dispatchSheet.getScheduleSheetId());
//                                }
//                            }
//                        }
//                        // 删除车辆原司机
//                        person.setTruck_id("");
//                        this.personDaoMapper.updatePerson(person);
//                        logger.info(TruckManager_Message.delDriverDone);
//                    }
//                }
//
//                if (list != null) {
//                    String truck_member_name = null;
//                    for (int i = 0; i < list.length; i++) {
//                        truck_member_name = list[i];
//                        T_Data_Member truck_member = this.memberDaoMapper.findMemberByName(truck_member_name);
//                        String person_id = truck_member.getRelevance_info_id();
//                        T_Data_Person person5 = this.personDaoMapper.findPersonByID(person_id);
//
//                        // 当前车辆未完成派车单发给当前设定的司机
//                        List<T_Data_Transportation_Schedule_Sheet> schedule_sheets = this.scheduleSheetDaoMapper.findScheduleSheetByScheduleTruckID(truck2.getTruck_id());
//                        if (schedule_sheets != null && schedule_sheets.size() != 0) {
//                            for (T_Data_Transportation_Schedule_Sheet schedule_sheet : schedule_sheets) {
//                                if (Integer.parseInt(schedule_sheet.getStatus()) < 7) {
//                                    // 生成派车单
//                                    T_Data_Transportation_Dispatch_Sheet dispatchSheet = new T_Data_Transportation_Dispatch_Sheet();
//                                    dispatchSheet.setScheduleSheetId(schedule_sheet.getScheduleSheetId());
//                                    dispatchSheet.setDCode(CodeUtil.DCode(new Date()));
//                                    dispatchSheet.setDispatchSheetId(UUIDUtil.getUUID());
//                                    dispatchSheet.setLastUpdate(DateUtil.getDate());
//                                    dispatchSheet.setReceiveMemberId(truck_member.getRelevance_info_id());
//                                    dispatchSheet.setSendMemberId(member.getRelevance_info_id());
//                                    dispatchSheet.setLastUpdateUserId("1");
//                                    this.dispatchSheetDaoMapper.saveDispatchSheet(dispatchSheet);
//                                }
//                            }
//                        }
//                        person5.setTruck_id(truck2.getTruck_id());
//                        this.personDaoMapper.updatePerson(person5);
//                    }
//                    ecode = "1000"; // 修改成功
//                    logger.info(TruckManager_Message.updataDriverDone);
//
//                    // 清除常跑司机推送消息
//                    if (oldPersonList != null) {
//                        Map<String, String> extrasMap = new HashMap<String, String>();
//                        List oldPersonName = null;
//                        for (T_Data_Person oldPerson : oldPersonList) {
//                            extrasMap.put("type", "carList");
//                            T_Data_Member oldMember = memberDaoMapper.findMerberByRelevance_info_id(oldPerson.getPerson_id());
//                            String oldMemberName = oldMember.getMember_name();
//                            DriverPush.pushToAI(oldMemberName, member_name + "已撤销你" + plate_number + "驾驶权限", extrasMap);
//                        }
//                    }
//
//                    if (list != null) {
//                        // 新常跑司机推送消息
//                        String truck_member_name1 = null;
//                        for (int i = 0; i < list.length; i++) {
//                            truck_member_name1 = list[i];
//                            if (truck_member_name1 != null) {
//                                Map<String, String> extrasMap = new HashMap<String, String>();
//                                extrasMap.put("type", "carDetail");
//                                extrasMap.put("plate_number", plate_number);
//                                DriverPush.pushToAI(truck_member_name1, member_name + "已给你" + plate_number + "驾驶权限", extrasMap);
//                            }
//                        }
//                    }
//                }
//
//                // 车辆常跑路线json解析
//                Type typeClass = new TypeToken<List<T_Data_Truck_Transport_Line>>() {
//                }.getType();
//                List<T_Data_Truck_Transport_Line> line_list = gson.fromJson(line_json, typeClass);
//                T_Data_Truck_Transport_Line line = new T_Data_Truck_Transport_Line();   // 车辆长跑路线
//                List<T_Data_Truck_Transport_Line> line_id_list = null;
//                if (line_list != null && line_list.size() != 0) {
//                    line_id_list = this.truckTransportLineDaoMapper.findTruckTransportByTruckID(truck2.getTruck_id());
//                    for (T_Data_Truck_Transport_Line line_id : line_id_list) {
//                        String lineID = line_id.getLine_id();
//                        this.truckTransportLineDaoMapper.delTruckTransportLine(lineID);
//                    }
//                    for (int i = 0; i < line_list.size(); i++) {
//                        line.setStart_city_id(line_list.get(i).getStart_city_id());
//                        line.setEnd_city_id(line_list.get(i).getEnd_city_id());
//                        line.setLine_id(UUIDUtil.getUUID());
//                        line.setTruck_id(truck2.getTruck_id());
//                        line.setLast_update(DateUtil.getDate());
//                        line.setLast_update_user_id("1");
//                        this.truckTransportLineDaoMapper.saveTruckTransportLine(line);
//                    }
//                    ecode = "1000"; // 修改成功
//                    logger.info(TruckManager_Message.updataLineDone);
//                }
//                try {
//                    truck2 = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
//                    if (repair_status != null && repair_status == 0) {
//                        truck2.setRepair_status("0");   // 正常运营
//                        this.truckDaoMapper.updateTruck(truck2);
//                        ecode = "1000"; // 车辆修理状态设定成功
//                    } else if (repair_status != null && repair_status == 1) {
//                        truck2.setRepair_status("1");   // 修理中
//                        this.truckDaoMapper.updateTruck(truck2);
//                        ecode = "1000"; // 车辆修理状态设定成功
//                    } else if (repair_status == null) {
//                        truck2.setRepair_status(truck2.getRepair_status());
//                        ecode = "1000";
//                    } else {
//                        ecode = "3000"; // 修改车辆参数错误
//                    }
//                } catch (Exception e) {
//                    ecode = "2000"; // 响应失败
//                    logger.error(TruckManager_Message.getTruckRepairErr + e.getMessage());
//                }
//            } catch (Exception e) {
//                ecode = "2000"; // 修改失败
//                logger.error(TruckManager_Message.getTruckDriverLineErr + e.getMessage());
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 修改失败
//            logger.error(TruckManager_Message.updataErr + e.getMessage());
//        }
//        return ecode;
//    }

    /**
     * 方法名称：addTruckInfo
     * 内容摘要：添加卡车信息
     * @param member_name 账号名
     * @param truck 卡车
     * @param truck_type_name 车辆类型
     * @param model_name 车辆型号
     * @param person_name 车主
     * @param person_mobile_phone 车主电话
     * @return String 返回值ecode
     */
//    public String addTruckInfo(String member_name, T_Data_Truck truck, String truck_type_name, String model_name, String person_name, String person_mobile_phone) {
//        // 添加卡车信息时, person_name是个人或者公司，member_name是车主或者公司的管理账号(注:个人信息表的truck_id)
//        // 添加卡车信息，member_name三种情况：1、管车版公司；2、管车版个人；3、司机个人；第一种情况车主是公司名称；个人添加车辆时车主是本人
//        String ecode = null;
//        T_Data_Person person = null;
//        T_Data_Company company = null;
//        T_Data_Member member = null;
//        T_Master_Truck_Model model = null;
//        T_Master_Truck_Type type = null;
//        T_Data_Truck truck2 = null;
//        String owner_member_id = null;  // 车主会员id
//        String plate_number = truck.getPlate_number();
//
//        try {
//            truck2 = this.truckDaoMapper.findTruckByPlateNumber(plate_number);  // 通过车牌号查找车辆，避免重复添加车辆
//            member = this.memberDaoMapper.findMemberByName(member_name);
//            if (truck2 == null) {
//                // 确定车主会员id
//                Integer accountType = Integer.valueOf(member.getAccount_type());
//                if (accountType == 0) {
//                    owner_member_id = member.getRelevance_info_id();
//                    truck.setOwner_member_id(owner_member_id);  // 车主会员关联ID，车辆和车主会员关联id关联
//                }
//                // 公司账号，车主是公司注册名称
//                else if (accountType == 1) {
//                    owner_member_id = member.getRelevance_info_id();
//                    truck.setOwner_member_id(owner_member_id);  // 车主会员关联ID，车辆和车主会员id关联
//                } else {
//                    logger.info(TruckManager_Message.getTruckOwnerWarn);
//                }
//                model = this.truckModelDaoMapper.findTruckModelByName(model_name);
//                truck.setTruck_model_id(model.getModel_id());   // 车辆型号ID
//                truck.setTruck_id(UUIDUtil.getUUID());          // 绑定车辆ID,司机和车辆id关联
//                truck.setDeposit_member_id(owner_member_id);    // 新增车辆托管对象为自己
//                truck.setManager_member_id(owner_member_id);    // 新增车辆管理者为自己
//                truck.setStatus("0");
//                truck.setLast_update(DateUtil.getDate());
//                truck.setLast_update_user_id("1");
//                truck.setRepair_status("0");
//                this.truckDaoMapper.saveTruck(truck);
//                ecode = "1000";   // 响应成功
//                logger.info(TruckManager_Message.saveDone);
//            } else {
//                ecode = "3002";   // 车辆已添加
//            }
//        } catch (Exception e) {
//            ecode = "2000";       // 保存卡车信息失败
//            logger.error(TruckManager_Message.saveErr + e.getMessage());
//        }
//        return ecode;
//    }

    /**
     * 方法名称：setTruckInfo
     * 内容摘要：车辆信息的设定
     *
     * @param member_name            账号名
     * @param plate_number           车牌号
     * @param deposit_member_name    托管对象
     * @param manager_member_name    车辆管理者
     * @param truck_member_name_json 常跑司机
     * @param line_json              常跑路线
     * @return String 返回值ecode
     */
    public String setTruckInfo(String member_name, String plate_number, String deposit_member_name, String manager_member_name, String truck_member_name_json, String line_json) {
        String ecode = null;
        T_Data_Truck truck = null;
        T_Data_Member deposit_member = null;    // 托管人员会员
        T_Data_Member manager_member = null;    // 管理人员会员
        T_Data_Member truck_member = null;      // 长跑司机会员
        String truck_member_name = null;        // 长跑司机会员账户
        T_Data_Person person = null;            // 长跑司机信息
        String truck_id = null;
        String person_id = null;

        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            deposit_member = this.memberDaoMapper.findMemberByName(deposit_member_name);
            if (deposit_member != null) {
                truck.setDeposit_member_id(deposit_member.getRelevance_info_id());
            }
            manager_member = this.memberDaoMapper.findMemberByName(manager_member_name);
            if (manager_member != null) {
                truck.setManager_member_id(manager_member.getRelevance_info_id());
            }

            // 解析json为数组
            Gson gson = new Gson();
            String[] list = gson.fromJson(truck_member_name_json, String[].class);
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    truck_member_name = list[i];
                    truck_member = this.memberDaoMapper.findMemberByName(truck_member_name);
                    person_id = truck_member.getRelevance_info_id();
                    person = this.personDaoMapper.findPersonByID(person_id);
                    person.setDriving_truck_id(truck.getTruck_id());
                    this.personDaoMapper.updatePerson(person);
                }
            }
            this.truckDaoMapper.updateTruck(truck);

            // 车辆常跑路线json解析
            Type typeClass = new TypeToken<List<T_Data_Truck_Transport_Line>>() {
            }.getType();
            List<T_Data_Truck_Transport_Line> line_list = gson.fromJson(line_json, typeClass);
            T_Data_Truck_Transport_Line line = new T_Data_Truck_Transport_Line();   // 车辆长跑路线
            if (line_list != null && line_list.size() != 0) {
                for (int i = 0; i < line_list.size(); i++) {
                    line.setStart_city_id(line_list.get(i).getStart_city_id());
                    line.setEnd_city_id(line_list.get(i).getEnd_city_id());
                    line.setLine_id(UUIDUtil.getUUID());
                    line.setTruck_id(truck.getTruck_id());
                    line.setLast_update(DateUtil.getDate());
                    line.setLast_update_user_id("M:" + member_name);
                    this.truckTransportLineDaoMapper.saveTruckTransportLine(line);
                }
            }
            ecode = "1000"; // 车辆信息的设定成功
            logger.info(TruckManager_Message.saveDone);
        } catch (Exception e) {
            ecode = "2000"; // 保存卡车信息失败
            logger.error(TruckManager_Message.saveErr + e.getMessage());
        }
        return ecode;
    }

    /**
     * 方法名称：delTruckInfo
     * 内容摘要：车辆所属身份信息的删除
     *
     * @param member_name  账号名
     * @param plate_number 车牌号
     * @return String 返回值ecode
     */
    public String delTruckInfo(String member_name, String plate_number) {
        String ecode = null;
        T_Data_Member member = null;            // 车辆所属身份信息
        String member_id = null;                // 会员id
        T_Data_Truck truck = null;              // 车牌对应的车辆信息
        List<T_Data_Person> person_list = null; // 司机
        String truck_id = null;
        List<T_Data_Truck> list = null;
        try {
            member = this.memberDaoMapper.findMemberByName(member_name);
            member_id = member.getRelevance_info_id();
            list = this.truckDaoMapper.findTruckByOwnerMemberID(member_id);
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 卡车信息
            truck_id = truck.getTruck_id(); // 卡车id
            // 当member_name的车辆所属身份为车主时，删除车辆的全部信息；member_name为托管对象时，删除车辆的托管对象、 车辆管理者、常跑司机等信息；
            if (list != null && list.size() != 0) {
                this.truckDaoMapper.delTruck(truck_id); // 删除车辆的全部信息
                ecode = "1000"; // 响应成功
                logger.info(TruckManager_Message.updataDone);
            } else {
                truck.setDeposit_member_id("");
                truck.setManager_member_id("");
                truck.setLast_update(DateUtil.getDate());
                // 删除车辆的托管对象，车辆管理者信息,即更新车辆信息
                this.truckDaoMapper.updateTruck(truck);
                person_list = this.personDaoMapper.findPersonByTruckId(truck_id);   // 司机信息
                for (T_Data_Person person : person_list) {
                    person.setDriving_truck_id("");
                    this.personDaoMapper.updatePerson(person);
                }
                ecode = "1000"; // 响应成功
                logger.info(TruckManager_Message.updataDone);
            }
        } catch (Exception e) {
            ecode = "2000"; // 响应失败
            logger.error(TruckManager_Message.updataErr + e.getMessage());
        }
        return ecode;
    }

    /**
     * 方法名称：findWaitTruckByMember
     * 内容摘要：根据账号获取待单中或仅有一单，有司机，未维修的车辆列表信息
     * @param member_name 账号名
     * @return String 返回值json
     */
//    public String findWaitTruckByMember(String member_name) {
//        Map result = new HashMap();
//        String json = null;
//        String ecode = null;
//        T_Data_Member member = null;    // 获取账户
//
//        try {
//            member = memberDaoMapper.findMemberByName(member_name);
//            if (member == null) {
//                ecode = "3000"; // 用户信息不存在！
//                result.put("ecode", ecode);
//                result.put("result", "用户信息不存在！");
//            } else {
//                List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
//
//                try {
//                    String member_id = member.getRelevance_info_id();   // 获取车辆管理者ID
//                    List<T_Data_Truck> list = null;
//                    list = truckDaoMapper.findTruckByManagerMemberID(member_id);    // 获得车辆管理者所有车辆信息列表
//                    List<T_Data_Transportation_Schedule_Sheet> scheduleSheet = null;
//
//                    for (T_Data_Truck trucks : list) {
//                        Map<String, String> results = new HashMap<String, String>();
//                        T_Master_Truck_Model tm = null;
//                        String ttid = null;
//                        T_Master_Truck_Type tt = null;
//                        List<T_Data_Person> havePerson = null;
//                        String tmid = trucks.getTruck_model_id();
//                        tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                        ttid = tm.getTruck_type_id();
//                        tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                        String trucksId = trucks.getTruck_id();
//                        int scheduleSheetNumber = 0;
//                        scheduleSheetNumber = scheduleSheetDaoMapper.findScheduleFinishedByTruckIdAndStatusCount(trucksId);
//                        havePerson = personDaoMapper.findPersonByTruckId(trucksId);
//                        String repair_status = trucks.getRepair_status();
//
//                        if (scheduleSheetNumber == 0 && havePerson != null && havePerson.size() != 0 && repair_status.equals("0")) {
//                            results.put("plate_number", trucks.getPlate_number() + "");     // 获得车辆车牌
//                            results.put("truck_type_name", tt.getTruck_type_name() + "");   // 获得车型
//                            results.put("load_weight", trucks.getLoad_weight() + "");       // 获得载重量
//                            lists.add(results);
//                        } else if (scheduleSheetNumber == 1 && havePerson != null && havePerson.size() != 0 && repair_status.equals("0")) {
//                            results.put("plate_number", trucks.getPlate_number() + "");     // 获得车辆车牌
//                            results.put("truck_type_name", tt.getTruck_type_name() + "");   // 获得车型
//                            results.put("load_weight", trucks.getLoad_weight() + "");       // 获得载重量
//                            T_Data_Transportation_Schedule_Sheet scheduleSheet2 = scheduleSheetDaoMapper.findScheduleFinishedByTruckIdAndStatus(trucks.getTruck_id());
//                            T_Data_Transportation_Plan order = transportationOrderDaoMapper.findOrderById(scheduleSheet2.getOrderId());
//                            T_Master_Transportation_Line line = transportationLineDaoMapper.findTransportLineById(order.getLine_id());
//                            T_Master_Cargo_Yard cargo = cargoDaoMapper.findById(line.getEnd_cargo_yard_id());
//                            String yard_name = cargo.getCargo_name();
//                            results.put("yard_name", yard_name);        // 获得货场名称
//                            lists.add(results);
//                        }
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckErr + e.getMessage());
//                }
//                ecode = "1000"; // 成功响应！
//                result.put("ecode", ecode);
//                result.put("result", lists);
//                logger.info(TruckManager_Message.getTruckDone);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 获取数据失败！
//            result.put("ecode", ecode);
//            logger.error(TruckManager_Message.getTruckErr);
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//    }

    /**
     * 方法名称：findLoadWeightByMember
     * 内容摘要：根据账号名获取待单有司机车辆运力
     *
     * @param member_name 账号名
     * @return Int LoadWeight 运力
     */
    public int findLoadWeightByMember(String member_name) {
        T_Data_Member member = null;    // 获取账户
        int LoadWeight = 0;
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            if (member == null) {
                logger.info(TruckManager_Message.getMemberWarn);
            } else {
                try {
                    String member_id = member.getRelevance_info_id();   // 获取车辆管理者ID
                    List<T_Data_Truck> list = null;
                    list = truckDaoMapper.findTruckByManagerMemberID(member_id);    // 获得车辆管理者所有车辆信息列表
                    List<T_Data_Transportation_Schedule_Sheet> scheduleSheet = null;

                    for (T_Data_Truck trucks : list) {
                        String trucksId = trucks.getTruck_id();
                        scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetByScheduleTruckID(trucksId);
                        List<T_Data_Person> personHaveTruck = personDaoMapper.findPersonByTruckId(trucksId);    // 查询所有司机

                        if (scheduleSheet != null && personHaveTruck != null && personHaveTruck.size() != 0) {
                            int i = 0;  // 判断是否待单

                            for (T_Data_Transportation_Schedule_Sheet status_list : scheduleSheet) {
                                int truckStatus = status_list.getStatus();
                                if (truckStatus < 7) {
                                    i = 1;  // 未待单
                                }
                            }
                            if (i == 0) {
                                double d = Double.parseDouble(trucks.getLoad_weight());
                                LoadWeight += d;
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckErr + e.getMessage());
                }
                logger.info(TruckManager_Message.getTruckDone);
            }
        } catch (Exception e) {
            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
        }
        return LoadWeight;
    }

    /**
     * 方法名称：findTruckByMemberAndPlateNumber
     * 内容摘要：根据账号、车辆的所属身份、车牌号模糊参数，获取模糊查询后的车辆列表
     * @param member_name 账号名
     * @param identity 车辆的所属身份
     * @param begin_rows 分页显示起始数
     * @param end_rows 分页显示终止数
     * @param plate_number 车牌号模糊查询参数
     * @return String 返回值json
     */
//    public String findTruckByMemberAndPlateNumber(String member_name, Integer identity, Integer begin_rows, Integer end_rows, String plate_number) {
//        Map result = new HashMap();
//        String json = null;
//        String ecode = null;
//        T_Data_Member member = null;    // 获取账户
//        try {
//            member = memberDaoMapper.findMemberByName(member_name);
//            if (member == null) {
//                ecode = "3000"; // 用户信息不存在！
//                result.put("ecode", ecode);
//                result.put("result", "用户信息不存在！");
//            } else {
//                List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
//                try {
//                    // 车辆所属身份判断
//                    if (identity == 0) {
//                        // 车主或车辆托管者
//                        try {
//                            String member_id = member.getRelevance_info_id();   // 获取车主或车辆托管者ID
//                            List<T_Data_Truck> ownerList = null;
//                            List<T_Data_Truck> depositList = null;
//                            ownerList = truckDaoMapper.findTruckByOwnerMemberIdAndPlateNumber(member_id, plate_number);//获得车主所有车辆信息列表
//                            depositList = truckDaoMapper.findTruckByDepositMemberIdAndPlateNumber(member_id, plate_number);//获得车辆托管者所有车辆信息列表
//                            List<T_Data_Truck> ordList = null;
//                            ordList = truckDaoMapper.findTruckByOwnerMemberIDDepositMemberIDAndPlateNumber(member_id, plate_number);
//                            depositList.addAll(ordList);    //合并去重
//
//                            for (T_Data_Truck trucks : depositList) {
//                                Map<String, String> results = new HashMap<String, String>();
//                                results.put("plate_number", trucks.getPlate_number() + ""); // 获得管理车辆车牌
//                                T_Master_Truck_Model tm = null; //车辆型号
//                                String ttid = null;             //车辆类型ID
//                                T_Master_Truck_Type tt = null;  //车辆类型
//                                String tmid = trucks.getTruck_model_id();   //车辆类型ID
//                                tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                                ttid = tm.getTruck_type_id();
//                                tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                                results.put("truck_type_name", tt.getTruck_type_name() + "");   // 获得车型
//
//                                String trucksId = trucks.getTruck_id();
//                                List<T_Data_Transportation_Schedule_Sheet> scheduleSheet = null;
//                                scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetByScheduleTruckID(trucksId);
//
//                                if (scheduleSheet != null) {
//                                    int i = 0;  // 判断是否待单
//                                    for (T_Data_Transportation_Schedule_Sheet status_list : scheduleSheet) {
//                                        String truckStatus = status_list.getStatus();
//                                        if (Integer.parseInt(truckStatus) < 7) {
//                                            i = 1;  // 未待单
//                                            results.put("status", "0"); // 获取车辆状态
//                                        }
//                                    }
//                                    if (i == 0) {
//                                        results.put("status", "1"); // 获取车辆状态
//                                    }
//                                }
//                                lists.add(results);
//                            }
//                        } catch (Exception e) {
//                            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
//                        }
//                    } else if (identity == 1) {
//                        // 车辆管理者
//                        try {
//                            String member_id = member.getMember_id();   // 获取车辆管理者ID
//                            List<T_Data_Truck> list = null;
//                            list = truckDaoMapper.findTruckByManagerMemberIdAndPlateNumber(member_id, plate_number);    // 获得车辆管理者所有车辆信息列表
//
//                            for (T_Data_Truck trucks : list) {
//                                Map<String, String> results = new HashMap<String, String>();
//                                results.put("plate_number", trucks.getPlate_number() + ""); // 获得管理车辆车牌
//                                T_Master_Truck_Model tm = null; //车辆型号
//                                String ttid = null;             //车辆类型ID
//                                T_Master_Truck_Type tt = null;  //车辆类型
//                                String tmid = trucks.getTruck_model_id();   //车辆类型ID
//                                tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                                ttid = tm.getTruck_type_id();
//                                tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                                results.put("truck_type_name", tt.getTruck_type_name() + "");   // 获得车型
//                                String trucksId = trucks.getTruck_id();
//                                List<T_Data_Transportation_Schedule_Sheet> scheduleSheet = null;
//                                scheduleSheet = scheduleSheetDaoMapper.findScheduleSheetByScheduleTruckID(trucksId);
//
//                                if (scheduleSheet != null) {
//                                    int i = 0;  // 判断是否待单
//                                    for (T_Data_Transportation_Schedule_Sheet status_list : scheduleSheet) {
//                                        String truckStatus = status_list.getStatus();
//                                        if (Integer.parseInt(truckStatus) < 7) {
//                                            i = 1;  // 未待单
//                                            results.put("status", "0"); // 获取车辆状态
//                                        }
//                                    }
//                                    if (i == 0) {
//                                        results.put("status", "1"); // 获取车辆状态
//                                    }
//                                }
//                                lists.add(results);
//                            }
//                        } catch (Exception e) {
//                            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
//                        }
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getIdentityErr + e.getMessage());
//                }
//                ecode = "1000"; // 成功响应！
//                result.put("ecode", ecode);
//                result.put("result", lists);
//                logger.info(TruckManager_Message.getTruckDone);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 获取数据失败！
//            result.put("ecode", ecode);
//            logger.error(TruckManager_Message.getTruckErr);
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//    }

    /**
     * 方法名称：findTruckDataByMember
     * 内容摘要：根据账户名获取我驾驶车辆信息
     * @param member_name 账户名
     * @return String 返回值json
     */
//    public String findTruckDataByMember(String member_name) {
//        Map result = new HashMap();
//        Map results = new HashMap();
//        String json = null;
//        String ecode = null;
//        T_Data_Truck plateNumber = null;    // 获取车辆
//        T_Data_Member member_sj = memberDaoMapper.findMemberByName(member_name);
//        T_Data_Person person_sj = personDaoMapper.findPersonByID(member_sj.getRelevance_info_id());
//        T_Data_Truck truck_sj = truckDaoMapper.findTruckByID(person_sj.getTruck_id());
//        String plate_number = truck_sj.getPlate_number();
//        try {
//            plateNumber = truckDaoMapper.findTruckByPlateNumber(plate_number);
//            if (plateNumber == null) {
//                ecode = "3000"; // 车辆信息不存在！
//                result.put("ecode", ecode);
//            } else {
//                plateNumber = truckDaoMapper.findTruckByPlateNumber(plate_number);
//                // 获取车长度、载重、体积、行驶证照片、修理状态
//                results.put("truck_length", plateNumber.getTruck_length());
//                results.put("load_weight", plateNumber.getLoad_weight());
//                results.put("volume", plateNumber.getVolume());
//                results.put("driving_licence_pic_id", plateNumber.getDriving_licence_pic_id() + "");
//                results.put("repair_status", plateNumber.getRepair_status() + "");
//
//                try {
//                    // 获取所属城市
//                    String dicdata_name = null;
//                    String remark = null;
//                    T_Sys_Dicdata attribution = null;
//
//                    String str = plate_number;
//                    String subStr = str.substring(0, 2);
//                    dicdata_name = subStr;
//                    attribution = attributionDaoMapper.findDicdataByDicdataName(dicdata_name);
//                    remark = attribution.getDicdata_remark();
//                    results.put("dicdata_remark", remark);
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckCityErr + e.getMessage());
//                }
//
//                try {
//                    // 获取车型
//                    T_Master_Truck_Model tm = null; // 车辆型号
//                    String ttid = null; // 车辆类型ID
//                    T_Master_Truck_Type tt = null;  // 车辆类型
//                    String tmid = plateNumber.getTruck_model_id();   // 车辆型号ID
//                    tm = truckModelDaoMapper.findTruckModelByID(tmid);
//                    ttid = tm.getTruck_type_id();
//                    tt = truckTypeDaoMapper.findTruckTypeByID(ttid);
//                    results.put("truck_type_name", tt.getTruck_type_name() + "");
//                    results.put("truck_model_name", tm.getModel_name() + "");
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
//                }
//
//                try {
//                    // 获取车主、车主电话
//                    String ownerMemberID = null;
//                    ownerMemberID = plateNumber.getOwner_member_id();
//                    T_Data_Member member = null;
//                    member = memberDaoMapper.findMerberByRelevance_info_id(ownerMemberID);
//                    String accountType1 = member.getAccount_type();
//                    String relevance_info_id = member.getRelevance_info_id();
//                    String name = null;
//                    String mobilePhone = null;
//                    Integer accountType = Integer.valueOf(accountType1);
//                    if (accountType == 0) {
//                        // 个人信息
//                        T_Data_Person person = null;
//                        person = personDaoMapper.findPersonByID(relevance_info_id);
//                        results.put("person_name_owner", person.getPerson_name() + "");
//                        results.put("person_mobile_phone_owner", person.getPerson_mobile_phone() + "");
//                    } else if (accountType == 1) {
//                        // 公司信息
//                        T_Data_Company company = null;
//                        company = companyDaoMapper.findCompanyByID(relevance_info_id);
//                        results.put("person_name_owner", company.getCompany_name() + "");
//                        results.put("person_mobile_phone_owner", company.getContact_mobile_phone() + "");
//                    } else {
//                        ecode = "3001"; // 车主信息获取失败
//                        result.put("ecode", ecode);
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckOwnerErr + e.getMessage());
//                }
//
//                try {
//                    // 获取托管对象、托管对象电话
//                    String depositMemberID = null;
//                    depositMemberID = plateNumber.getDeposit_member_id();
//                    T_Data_Member member = null;
//                    member = memberDaoMapper.findMerberByRelevance_info_id(depositMemberID);
//                    String accountType1 = member.getAccount_type();
//                    String relevance_info_id = member.getRelevance_info_id();
//                    Integer accountType = Integer.valueOf(accountType1);
//                    if (accountType == 0) {
//                        // 个人信息
//                        T_Data_Person person = null;
//                        person = personDaoMapper.findPersonByID(relevance_info_id);
//                        results.put("person_name_deposit", person.getPerson_name() + "");
//                        results.put("person_mobile_phone_deposit", person.getPerson_mobile_phone() + "");
//                    } else if (accountType == 1) {
//                        // 公司信息
//                        T_Data_Company company = null;
//                        company = companyDaoMapper.findCompanyByID(relevance_info_id);
//                        results.put("person_name_deposit", company.getCompany_name() + "");
//                        results.put("person_mobile_phone_deposit", company.getContact_mobile_phone() + "");
//                    } else {
//                        ecode = "3002"; // 托管对象信息获取失败
//                        result.put("ecode", ecode);
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckDepositErr + e.getMessage());
//                }
//
//                try {
//                    // 获取车辆管理者、车辆管理者电话
//                    String managerMemberID = null;
//                    managerMemberID = plateNumber.getManager_member_id();
//                    T_Data_Member member = null;
//                    member = memberDaoMapper.findMerberByRelevance_info_id(managerMemberID);
//                    String accountType1 = member.getAccount_type();
//                    String relevance_info_id = member.getRelevance_info_id();
//                    Integer accountType = Integer.valueOf(accountType1);
//                    if (accountType == 0) {
//                        // 个人信息
//                        T_Data_Person person = null;
//                        person = personDaoMapper.findPersonByID(relevance_info_id);
//                        results.put("person_name_manager", person.getPerson_name() + "");
//                        results.put("person_mobile_phone_manager", person.getPerson_mobile_phone() + "");
//                    } else if (accountType == 1) {
//                        // 公司信息
//                        T_Data_Company company = null;
//                        company = companyDaoMapper.findCompanyByID(relevance_info_id);
//                        results.put("person_name_manager", company.getCompany_name() + "");
//                        results.put("person_mobile_phone_manager", company.getContact_mobile_phone() + "");
//                    } else {
//                        ecode = "3003"; // 车辆管理者信息获取失败
//                        result.put("ecode", ecode);
//                    }
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckManagerErr + e.getMessage());
//                }
//
//                try {
//                    // 获取常跑司机
//                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
//                    String truckId = null;
//                    truckId = plateNumber.getTruck_id();
//                    String truck_id = truckId;
//                    List<T_Data_Person> list = null;
//                    list = personDaoMapper.findPersonByTruckId(truck_id);
//                    for (T_Data_Person person : list) {
//                        Map<String, String> resultss = new HashMap<String, String>();
//                        T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
//                        String member_name_drive = drive.getMember_name();
//                        resultss.put("member_name", member_name_drive + "");
//                        resultss.put("person_name_driver", person.getPerson_name() + "");
//                        listss.add(resultss);
//                    }
//                    results.put("driver", listss);
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
//                }
//
//                try {
//                    // 常跑路线
//                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
//                    String truckId = null;
//                    truckId = plateNumber.getTruck_id();
//                    String truck_id = truckId;
//                    List<T_Data_Truck_Transport_Line> list = null;
//                    list = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck_id);
//                    for (T_Data_Truck_Transport_Line line : list) {
//                        Map<String, String> resultss = new HashMap<String, String>();
//                        resultss.put("start_city", line.getStart_city_id() + "");
//                        resultss.put("end_city", line.getEnd_city_id() + "");
//                        listss.add(resultss);
//                    }
//                    results.put("line", listss);
//                } catch (Exception e) {
//                    logger.error(TruckManager_Message.getTruckLineErr + e.getMessage());
//                }
//                ecode = "1000"; // 成功响应！
//                result.put("ecode", ecode);
//                result.put("object1", results);
//                logger.info(TruckManager_Message.getTruckDone);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 获取数据失败！
//            result.put("ecode", ecode);
//            logger.error(TruckManager_Message.getTruckErr);
//        }
//        json = JSONUtil.toJSONString(result);
//        return json;
//    }

    /**
     * 方法名称：setRepairStatus
     * 内容摘要：设定车辆修理状态
     * @param plate_number 车牌号
     * @param repair_status 修理状态
     * @return String 返回值ecode
     */
//    public String setRepairStatus(String plate_number, Integer repair_status) {
//        String ecode = null;
//        T_Data_Truck truck = null;
//        try {
//            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
//            if (repair_status == 0) {
//                truck.setRepair_status("0");    // 正常运营
//                this.truckDaoMapper.updateTruck(truck);
//                ecode = "1000"; // 车辆修理状态设定成功
//            } else if (repair_status == 1) {
//                truck.setRepair_status("1");//修理中
//                this.truckDaoMapper.updateTruck(truck);
//                ecode = "1000"; // 车辆修理状态设定成功
//                logger.info(TruckManager_Message.updataDone);
//            } else {
//                ecode = "3000"; // 参数错误
//                logger.info(TruckManager_Message.getRepairWarn);
//            }
//        } catch (Exception e) {
//            ecode = "2000"; // 响应失败
//            logger.error(TruckManager_Message.updataErr + e.getMessage());
//        }
//        return ecode;
//    }

    /**
     * 方法名称：findAllTruckTypeName
     * 内容摘要：检索车辆车类型
     *
     * @return String 返回值json
     */
    public String findAllTruckTypeName() {
        String json = null;
        Map result = new HashMap();
        List<T_Sys_Dicdata> list = null;
        List truckTypeList = new ArrayList();
        String ecode = null;
        try {
            list = this.dicdataDaoMapper.findAllDicdataByCode("7EA67370E68D493386B56F9C0E97A943", "%%");
            for (T_Sys_Dicdata truckType : list) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("truck_type_id", truckType.getDicdata_id());
                results.put("truck_type_name", truckType.getDicdata_name());
                truckTypeList.add(results);
            }
            ecode = "1000";   // 响应成功
            result.put("ecode", ecode);
            result.put("object1", truckTypeList);
        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findAllTruckTypeLength
     * 内容摘要：检索所有车长
     *
     * @return String 返回值json
     */
    public String findAllTruckTypeLength() {
        String json = null;
        Map result = new HashMap();
        List<T_Sys_Dicdata> list = null;
        List truckTypeLengthList = new ArrayList();
        String ecode = null;
        try {
            list = this.dicdataDaoMapper.findAllDicdataTruckTypeLengthByCode("28E55171F7E24C95956D277C275B24B5", "%%");
            for (T_Sys_Dicdata truckTypeLength : list) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("truck_length_id", truckTypeLength.getDicdata_id());
                results.put("truck_length_name", truckTypeLength.getDicdata_name());
                truckTypeLengthList.add(results);
            }
            ecode = "1000";   // 响应成功
            result.put("ecode", ecode);
            result.put("object1", truckTypeLengthList);
        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：searchTruckDataByPlateNumber
     * 内容摘要：根据车牌号检索车辆详细信息
     *
     * @param plate_number 车牌号
     * @return String 返回值json
     */
    public String searchTruckDataByPlateNumber(String plate_number) {
        Map result = new HashMap();
        Map results = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;    // 获取车辆
        try {
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            if (truck == null) {
                ecode = "3000"; // 车辆信息不存在！
                result.put("ecode", ecode);
            } else {
                truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
                results.put("plate_number", truck.getPlate_number() + "");                       // 获取车牌号
                results.put("load_weight", truck.getLoad_weight() + "");                         // 获取重量
                results.put("load_volume", truck.getLoad_volume() + "");                         // 获取体积
                results.put("driving_licence", truck.getDriving_licence() + "");                 // 获取行驶证号
                results.put("driving_licence_pic_id", truck.getDriving_licence_first_page_save_path() + "");   // 获取行驶证照片
                results.put("truck_pic_id", truck.getTruck_first_pic_save_path() + "");                       // 获取车辆照片
                results.put("resident_province_id", truck.getResident_province_id() + "");       // 获取常驻省
                results.put("resident_city_id", truck.getResident_city_id() + "");               // 获取常驻城市
                results.put("resident_area_id", truck.getResident_area_id() + "");               // 获取常驻区县
                results.put("operate_status", truck.getOperate_status() + "");                   // 获取车辆状态
                results.put("verify_status", truck.getVerify_status() + "");                     // 获取审核状态
                results.put("verify_refused_reason", truck.getVerify_refused_reason() + "");     // 获取锁定理由

                try {
                    // 获取所属城市
                    String dicdata_name = null;
                    String remark = null;
                    T_Sys_Dicdata attribution = null;

                    String str = plate_number;
                    String subStr = str.substring(0, 2);
                    dicdata_name = subStr;
                    attribution = attributionDaoMapper.findDicdataByDicdataName(dicdata_name);
                    remark = attribution.getDicdata_remark();
                    results.put("dicdata_remark", remark);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckCityErr + e.getMessage());
                }

                try {
                    // 获取车类型
                    Map resultss = new HashMap();
                    T_Sys_Dicdata truckType = null;
                    truckType = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_type_id());
                    resultss.put("truck_type_id", truckType.getDicdata_id() + "");
                    resultss.put("truck_type_name", truckType.getDicdata_name() + "");
                    results.put("car_type", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车长
                    Map resultss = new HashMap();
                    T_Sys_Dicdata truckLength = null;
                    truckLength = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_length_id());
                    resultss.put("truck_length_id", truckLength.getDicdata_id() + "");
                    resultss.put("truck_length_name", truckLength.getDicdata_name() + "");
                    results.put("car_length", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车辆燃料类型
                    Map resultss = new HashMap();
                    T_Sys_Dicdata truckFuel = null;
                    truckFuel = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_fuel_type_id());
                    resultss.put("truck_fuel_type_id", truckFuel.getDicdata_id() + "");
                    resultss.put("truck_fuel_type_name", truckFuel.getDicdata_name() + "");
                    results.put("car_fuel_type", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车厢类型
                    Map resultss = new HashMap();
                    T_Master_Truck_Carriage_Type truckCarriageType = null;
                    truckCarriageType = this.truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck.getTruck_carriage_type_id());
                    resultss.put("truck_carriage_type_id", truckCarriageType.getTruck_carriage_type_id() + "");
                    resultss.put("truck_carriage_type_name", truckCarriageType.getTruck_carriage_type_name() + "");
                    results.put("carriage_type", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车主、车主电话
                    String ownerMemberID = null;
                    ownerMemberID = truck.getOwner_member_id();
                    T_Data_Member member = null;
                    if (ownerMemberID != null) {
                        member = memberDaoMapper.findMerberByRelevance_info_id(ownerMemberID);
                        String accountType1 = member.getAccount_type();
                        String relevance_info_id = member.getRelevance_info_id();
                        String name = null;
                        String mobilePhone = null;
                        Integer accountType = Integer.valueOf(accountType1);
                        if (accountType == 0) {
                            // 个人信息
                            T_Data_Person person = null;
                            person = personDaoMapper.findPersonByID(relevance_info_id);
                            results.put("owner_person_name", person.getPerson_name() + "");
                            results.put("owner_member_name", person.getPerson_mobile_phone() + "");
                        } else if (accountType == 1) {
                            // 公司信息
                            T_Data_Company company = null;
                            company = companyDaoMapper.findCompanyByID(relevance_info_id);
                            results.put("owner_person_name", company.getCompany_name() + "");
                            results.put("owner_member_name", company.getContact_mobile_phone() + "");
                        } else {
                            ecode = "3001"; // 车主信息获取失败
                            result.put("ecode", ecode);
                        }
                    }
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckOwnerErr + e.getMessage());
                }

                try {
                    // 获取托管对象、托管对象电话
                    String depositMemberID = null;
                    depositMemberID = truck.getDeposit_member_id();
                    T_Data_Member member = null;
                    if (depositMemberID != null) {
                        member = memberDaoMapper.findMerberByRelevance_info_id(depositMemberID);
                        String accountType1 = member.getAccount_type();
                        String relevance_info_id = member.getRelevance_info_id();
                        Integer accountType = Integer.valueOf(accountType1);
                        if (accountType == 0) {
                            // 个人信息
                            T_Data_Person person = null;
                            person = personDaoMapper.findPersonByID(relevance_info_id);
                            results.put("deposit_person_name", person.getPerson_name() + "");
                            results.put("deposit_member_name", person.getPerson_mobile_phone() + "");
                        } else if (accountType == 1) {
                            // 公司信息
                            T_Data_Company company = null;
                            company = companyDaoMapper.findCompanyByID(relevance_info_id);
                            results.put("deposit_person_name", company.getCompany_name() + "");
                            results.put("deposit_member_name", company.getContact_mobile_phone() + "");
                        } else {
                            ecode = "3002"; // 托管对象信息获取失败
                            result.put("ecode", ecode);
                        }
                    }
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckDepositErr + e.getMessage());
                }

                try {
                    // 获取车辆管理者、车辆管理者电话
                    String managerMemberID = null;
                    managerMemberID = truck.getManager_member_id();
                    T_Data_Member member = null;
                    if (managerMemberID != null) {
                        member = memberDaoMapper.findMerberByRelevance_info_id(managerMemberID);
                        String accountType1 = member.getAccount_type();
                        String relevance_info_id = member.getRelevance_info_id();
                        Integer accountType = Integer.valueOf(accountType1);
                        if (accountType == 0) {
                            // 个人信息
                            T_Data_Person person = null;
                            person = personDaoMapper.findPersonByID(relevance_info_id);
                            results.put("manager_person_name", person.getPerson_name() + "");
                            results.put("manager_member_name", person.getPerson_mobile_phone() + "");
                        } else if (accountType == 1) {
                            // 公司信息
                            T_Data_Company company = null;
                            company = companyDaoMapper.findCompanyByID(relevance_info_id);
                            results.put("manager_person_name", company.getCompany_name() + "");
                            results.put("manager_member_name", company.getContact_mobile_phone() + "");
                        } else {
                            ecode = "3003"; // 车辆管理者信息获取失败
                            result.put("ecode", ecode);
                        }
                    }
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckManagerErr + e.getMessage());
                }

                try {
                    // 获取常跑司机
                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                    String truckId = null;
                    truckId = truck.getTruck_id();
                    String truck_id = truckId;
                    List<T_Data_Person> list = null;
                    list = personDaoMapper.findPersonByTruckId(truck_id);
                    for (T_Data_Person person : list) {
                        if (Integer.valueOf(person.getDriving_status()) == 1 || Integer.valueOf(person.getDriving_status()) == 2) {
                            Map<String, String> resultss = new HashMap<String, String>();
                            T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                            String member_name_drive = drive.getMember_name();
                            resultss.put("member_name", member_name_drive + "");// 获取司机账号
                            resultss.put("person_name", person.getPerson_name() + "");// 获取司机姓名
                            listss.add(resultss);
                        }
                    }
                    results.put("driver_often_array", listss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
                }

                try {
                    // 常跑路线
                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                    String truckId = null;
                    truckId = truck.getTruck_id();
                    String truck_id = truckId;
                    List<T_Data_Truck_Transport_Line> list = null;
                    list = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck_id);
                    for (T_Data_Truck_Transport_Line line : list) {
                        Map<String, String> resultss = new HashMap<String, String>();

//                        List<T_Sys_Dicdata> dicdataList_start_province = null;  // 起点省
                        List<T_Sys_Dicdata> dicdataList_start_city = null;      // 起点城市
//                        List<T_Sys_Dicdata> dicdatalist_end_province = null;    // 终点省
                        List<T_Sys_Dicdata> dicdataList_end_city = null;        // 终点城市

//                        // 起点省的回显
//                        dicdataList_start_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getStart_province_id());
//                        if (dicdataList_start_province != null && dicdataList_start_province.size() != 0) {
//                            line.setStart_province_id(dicdataList_start_province.get(0).getDicdata_name());
//                        } else {
//                            line.setStart_province_id("none");
//                        }
                        // 起点市的回显
                        dicdataList_start_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getStart_city_id());
                        if (dicdataList_start_city != null && dicdataList_start_city.size() != 0) {
                            line.setStart_city_id(dicdataList_start_city.get(0).getDicdata_name());
                        } else {
                            line.setStart_city_id("none");
                        }
//                        // 终点省的回显
//                        dicdatalist_end_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getEnd_province_id());
//                        if (dicdatalist_end_province != null && dicdatalist_end_province.size() != 0) {
//                            line.setEnd_province_id(dicdatalist_end_province.get(0).getDicdata_name());
//                        } else {
//                            line.setEnd_province_id("none");
//                        }
                        // 终点市的回显
                        dicdataList_end_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getEnd_city_id());
                        if (dicdataList_end_city != null && dicdataList_end_city.size() != 0) {
                            line.setEnd_city_id(dicdataList_end_city.get(0).getDicdata_name());
                        } else {
                            line.setEnd_city_id("none");
                        }

                        resultss.put("line_id", line.getLine_id() + "");// 获取线路ID
                        resultss.put("start_city", line.getStart_city_id() + "");// 获取开始城市
                        resultss.put("end_city", line.getEnd_city_id() + "");// 获取结束城市
                        listss.add(resultss);
                    }
                    results.put("line_array", listss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckLineErr + e.getMessage());
                }

                try {
                    // 获取行驶司机信息
                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                    String truckId = null;
                    truckId = truck.getTruck_id();
                    String truck_id = truckId;
                    List<T_Data_Person> list = null;
                    list = personDaoMapper.findPersonByTruckId(truck_id);
                    for (T_Data_Person person : list) {
                        if (Integer.valueOf(person.getDriving_status()) == 2) {
                            Map<String, String> resultss = new HashMap<String, String>();
                            T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                            String member_name_drive = drive.getMember_name();
                            resultss.put("member_name", member_name_drive + "");// 获取司机账号
                            resultss.put("person_name", person.getPerson_name() + "");// 获取司机姓名
                            listss.add(resultss);
                        }
                    }
                    results.put("driver_driving_array", listss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getDriversErr + e.getMessage());
                }

                try {
                    // 获取待确认司机信息
                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                    String truckId = null;
                    truckId = truck.getTruck_id();
                    String truck_id = truckId;
                    List<T_Data_Person> list = null;
                    list = personDaoMapper.findPersonByTruckId(truck_id);
                    int driveNumber = 0; // 待确认司机数量
                    for (T_Data_Person person : list) {
                        if (Integer.valueOf(person.getDriving_status()) == 0) {
                            driveNumber++;
                        }
                    }
                    results.put("join_drivers_number", driveNumber + "");
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getJoinDriversNumberErr + e.getMessage());
                }

                ecode = "1000"; // 成功响应！
                result.put("ecode", ecode);
                result.put("object1", results);
                logger.info(TruckManager_Message.getTruckDone);
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：searchTruckDataByMemberName
     * 内容摘要：根据账号和雇佣关系检索车辆详细信息
     *
     * @param member_name 账号
     * @param is_employ   是否雇用
     * @return String 返回值json
     */
    public String searchTruckDataByMemberName(String member_name, String is_employ) {
        Map result = new HashMap();
        Map results = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;      // 获取车辆
        T_Data_Member drivingMember = null;    // 获取用户
        T_Data_Person drivingPerson = null;    // 获取司机
        drivingMember = memberDaoMapper.findMemberByName(member_name);
        drivingPerson = personDaoMapper.findPersonByID(drivingMember.getRelevance_info_id());
        try {
            if ("2".equals(drivingPerson.getVerify_status())) {
                truck = truckDaoMapper.findTruckByID(drivingPerson.getDriving_truck_id());
                if (truck == null) {
                    ecode = "3000"; // 车辆信息不存在！
                    result.put("ecode", ecode);
                } else if ("1".equals(drivingPerson.getDriving_status()) || "2".equals(drivingPerson.getDriving_status())) {
                    truck = truckDaoMapper.findTruckByID(drivingPerson.getDriving_truck_id());
                    results.put("plate_number", truck.getPlate_number() + "");                       // 获取车牌号
                    results.put("load_weight", truck.getLoad_weight() + "");                         // 获取重量
                    results.put("load_volume", truck.getLoad_volume() + "");                         // 获取体积
                    results.put("driving_licence", truck.getDriving_licence() + "");                 // 获取行驶证号
                    results.put("driving_licence_pic_id", truck.getDriving_licence_first_page_save_path() + "");   // 获取行驶证照片
                    results.put("truck_pic_id", truck.getTruck_first_pic_save_path() + "");                       // 获取车辆照片
                    results.put("resident_province_id", truck.getResident_province_id() + "");       // 获取常驻省
                    results.put("resident_city_id", truck.getResident_city_id() + "");               // 获取常驻城市
                    results.put("resident_area_id", truck.getResident_area_id() + "");               // 获取常驻区县
                    results.put("operate_status", truck.getOperate_status() + "");                   // 获取车辆状态
                    results.put("verify_status", truck.getVerify_status() + "");                     // 获取审核状态
                    results.put("verify_refused_reason", truck.getVerify_refused_reason() + "");     // 获取锁定理由

                    try {
                        // 获取所属城市
                        String dicdata_name = null;
                        String remark = null;
                        T_Sys_Dicdata attribution = null;

                        String str = truck.getPlate_number();
                        String subStr = str.substring(0, 2);
                        dicdata_name = subStr;
                        attribution = attributionDaoMapper.findDicdataByDicdataName(dicdata_name);
                        remark = attribution.getDicdata_remark();
                        results.put("dicdata_remark", remark);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckCityErr + e.getMessage());
                    }

                    try {
                        // 获取车类型
                        Map resultss = new HashMap();
                        T_Sys_Dicdata truckType = null;
                        truckType = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_type_id());
                        resultss.put("truck_type_id", truckType.getDicdata_id() + "");
                        resultss.put("truck_type_name", truckType.getDicdata_name() + "");
                        results.put("car_type", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车长
                        Map resultss = new HashMap();
                        T_Sys_Dicdata truckLength = null;
                        truckLength = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_length_id());
                        resultss.put("truck_length_id", truckLength.getDicdata_id() + "");
                        resultss.put("truck_length_name", truckLength.getDicdata_name() + "");
                        results.put("car_length", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车厢类型
                        Map resultss = new HashMap();
                        T_Master_Truck_Carriage_Type truckCarriageType = null;
                        truckCarriageType = this.truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck.getTruck_carriage_type_id());
                        resultss.put("truck_carriage_type_id", truckCarriageType.getTruck_carriage_type_id() + "");
                        resultss.put("truck_carriage_type_name", truckCarriageType.getTruck_carriage_type_name() + "");
                        results.put("carriage_type", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车辆燃料类型
                        Map resultss = new HashMap();
                        T_Sys_Dicdata truckFuel = null;
                        truckFuel = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_fuel_type_id());
                        resultss.put("truck_fuel_type_id", truckFuel.getDicdata_id() + "");
                        resultss.put("truck_fuel_type_name", truckFuel.getDicdata_name() + "");
                        results.put("car_fuel_type", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    Integer is_employ1 = Integer.valueOf(is_employ);
                    if (is_employ1 == 1) {
                        // 0为个人，1为受雇佣。受雇佣时，返回车主、托管者相关信息
                        try {
                            // 获取车主、车主电话
                            String ownerMemberID = null;
                            ownerMemberID = truck.getOwner_member_id();
                            T_Data_Member member = null;
                            if (ownerMemberID != null) {
                                member = memberDaoMapper.findMerberByRelevance_info_id(ownerMemberID);
                                String accountType1 = member.getAccount_type();
                                String relevance_info_id = member.getRelevance_info_id();
                                String name = null;
                                String mobilePhone = null;
                                Integer accountType = Integer.valueOf(accountType1);
                                if (accountType == 0) {
                                    // 个人信息
                                    T_Data_Person person = null;
                                    person = personDaoMapper.findPersonByID(relevance_info_id);
                                    results.put("owner_person_name", person.getPerson_name() + "");
                                    results.put("owner_member_name", person.getPerson_mobile_phone() + "");
                                } else if (accountType == 1) {
                                    // 公司信息
                                    T_Data_Company company = null;
                                    company = companyDaoMapper.findCompanyByID(relevance_info_id);
                                    results.put("owner_person_name", company.getCompany_name() + "");
                                    results.put("owner_member_name", company.getContact_mobile_phone() + "");
                                } else {
                                    ecode = "3001"; // 车主信息获取失败
                                    result.put("ecode", ecode);
                                }
                            }
                        } catch (Exception e) {
                            logger.error(TruckManager_Message.getTruckOwnerErr + e.getMessage());
                        }

                        try {
                            // 获取托管对象、托管对象电话
                            String depositMemberID = null;
                            depositMemberID = truck.getDeposit_member_id();
                            T_Data_Member member = null;
                            if (depositMemberID != null) {
                                member = memberDaoMapper.findMerberByRelevance_info_id(depositMemberID);
                                String accountType1 = member.getAccount_type();
                                String relevance_info_id = member.getRelevance_info_id();
                                Integer accountType = Integer.valueOf(accountType1);
                                if (accountType == 0) {
                                    // 个人信息
                                    T_Data_Person person = null;
                                    person = personDaoMapper.findPersonByID(relevance_info_id);
                                    results.put("deposit_person_name", person.getPerson_name() + "");
                                    results.put("deposit_member_name", person.getPerson_mobile_phone() + "");
                                } else if (accountType == 1) {
                                    // 公司信息
                                    T_Data_Company company = null;
                                    company = companyDaoMapper.findCompanyByID(relevance_info_id);
                                    results.put("deposit_person_name", company.getCompany_name() + "");
                                    results.put("deposit_member_name", company.getContact_mobile_phone() + "");
                                } else {
                                    ecode = "3002"; // 托管对象信息获取失败
                                    result.put("ecode", ecode);
                                }
                            }
                        } catch (Exception e) {
                            logger.error(TruckManager_Message.getTruckDepositErr + e.getMessage());
                        }
                    }

                    try {
                        // 获取车辆管理者、车辆管理者电话
                        String managerMemberID = null;
                        managerMemberID = truck.getManager_member_id();
                        T_Data_Member member = null;
                        if (managerMemberID != null) {
                            member = memberDaoMapper.findMerberByRelevance_info_id(managerMemberID);
                            String accountType1 = member.getAccount_type();
                            String relevance_info_id = member.getRelevance_info_id();
                            Integer accountType = Integer.valueOf(accountType1);
                            if (accountType == 0) {
                                // 个人信息
                                T_Data_Person person = null;
                                person = personDaoMapper.findPersonByID(relevance_info_id);
                                results.put("manager_person_name", person.getPerson_name() + "");
                                results.put("manager_member_name", person.getPerson_mobile_phone() + "");
                            } else if (accountType == 1) {
                                // 公司信息
                                T_Data_Company company = null;
                                company = companyDaoMapper.findCompanyByID(relevance_info_id);
                                results.put("manager_person_name", company.getCompany_name() + "");
                                results.put("manager_member_name", company.getContact_mobile_phone() + "");
                            } else {
                                ecode = "3003"; // 车辆管理者信息获取失败
                                result.put("ecode", ecode);
                            }
                        }
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckManagerErr + e.getMessage());
                    }

                    try {
                        // 获取常跑司机
                        List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                        String truckId = null;
                        truckId = truck.getTruck_id();
                        String truck_id = truckId;
                        List<T_Data_Person> list = null;
                        list = personDaoMapper.findPersonByTruckId(truck_id);
                        for (T_Data_Person person : list) {
                            if (Integer.valueOf(person.getDriving_status()) == 1 || Integer.valueOf(person.getDriving_status()) == 2) {
                                Map<String, String> resultss = new HashMap<String, String>();
                                T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                                String member_name_drive = drive.getMember_name();
                                resultss.put("member_name", member_name_drive + "");// 获取司机账号
                                resultss.put("person_name", person.getPerson_name() + "");// 获取司机姓名
                                listss.add(resultss);
                            }
                        }
                        results.put("driver_often_array", listss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
                    }

                    try {
                        // 常跑路线
                        List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                        String truckId = null;
                        truckId = truck.getTruck_id();
                        String truck_id = truckId;
                        List<T_Data_Truck_Transport_Line> list = null;
                        list = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck_id);
                        for (T_Data_Truck_Transport_Line line : list) {
                            Map<String, String> resultss = new HashMap<String, String>();


                            List<T_Sys_Dicdata> dicdataList_start_province = null;  // 起点省
                            List<T_Sys_Dicdata> dicdataList_start_city = null;      // 起点城市
                            List<T_Sys_Dicdata> dicdatalist_end_province = null;    // 终点省
                            List<T_Sys_Dicdata> dicdataList_end_city = null;        // 终点城市

                            // 起点省的回显
                            dicdataList_start_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getStart_province_id());
                            if (dicdataList_start_province != null && dicdataList_start_province.size() != 0) {
                                line.setStart_province_id(dicdataList_start_province.get(0).getDicdata_name());
                            } else {
                                line.setStart_province_id("none");
                            }
                            // 起点市的回显
                            dicdataList_start_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getStart_city_id());
                            if (dicdataList_start_city != null && dicdataList_start_city.size() != 0) {
                                line.setStart_city_id(dicdataList_start_city.get(0).getDicdata_name());
                            } else {
                                line.setStart_city_id("none");
                            }
                            // 终点省的回显
                            dicdatalist_end_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getEnd_province_id());
                            if (dicdatalist_end_province != null && dicdatalist_end_province.size() != 0) {
                                line.setEnd_province_id(dicdatalist_end_province.get(0).getDicdata_name());
                            } else {
                                line.setEnd_province_id("none");
                            }
                            // 终点市的回显
                            dicdataList_end_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getEnd_city_id());
                            if (dicdataList_end_city != null && dicdataList_end_city.size() != 0) {
                                line.setEnd_city_id(dicdataList_end_city.get(0).getDicdata_name());
                            } else {
                                line.setEnd_city_id("none");
                            }

                            resultss.put("line_id", line.getLine_id() + "");// 获取线路ID
                            resultss.put("start_province_id", line.getStart_province_id() + "");// 获取开始省份
                            resultss.put("start_city_id", line.getStart_city_id() + "");// 获取开始城市
                            resultss.put("end_province_id", line.getEnd_province_id() + "");// 获取结束省份
                            resultss.put("end_city_id", line.getEnd_city_id() + "");// 获取结束城市
                            listss.add(resultss);
                        }
                        results.put("line_array", listss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckLineErr + e.getMessage());
                    }

                    try {
                        // 获取行驶司机信息
                        List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                        String truckId = null;
                        truckId = truck.getTruck_id();
                        String truck_id = truckId;
                        List<T_Data_Person> list = null;
                        list = personDaoMapper.findPersonByTruckId(truck_id);
                        for (T_Data_Person person : list) {
                            int drivingStatus = Integer.valueOf(person.getDriving_status());
                            if (drivingStatus == 2) {
                                Map<String, String> resultss = new HashMap<String, String>();
                                T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                                String member_name_drive = drive.getMember_name();
                                resultss.put("member_name", member_name_drive + "");// 获取司机账号
                                resultss.put("person_name", person.getPerson_name() + "");// 获取司机姓名
                                listss.add(resultss);
                            }
                        }
                        results.put("driver_driving_array", listss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getDriversErr + e.getMessage());
                    }

                    try {
                        // 获取待确认司机信息
                        List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                        String truckId = null;
                        truckId = truck.getTruck_id();
                        String truck_id = truckId;
                        List<T_Data_Person> list = null;
                        list = personDaoMapper.findPersonByTruckId(truck_id);
                        int driveNumber = 0; // 待确认司机数量
                        for (T_Data_Person person : list) {
                            if (Integer.valueOf(person.getDriving_status()) == 0) {
                                driveNumber++;
                            }
                        }
                        results.put("join_drivers_number", driveNumber + "");
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getJoinDriversNumberErr + e.getMessage());
                    }

                    ecode = "1000"; // 成功响应！
                    result.put("ecode", ecode);
                    result.put("object1", results);
                    logger.info(TruckManager_Message.getTruckDone);
                } else if ("0".equals(drivingPerson.getDriving_status())) {
                    truck = truckDaoMapper.findTruckByID(drivingPerson.getDriving_truck_id());
                    results.put("plate_number", truck.getPlate_number() + "");                       // 获取车牌号
                    ecode = "4000"; // 成功响应！
                    result.put("ecode", ecode);
                    result.put("object1", results);
                }
            } else {
                ecode = "3006"; // 用户未审核通过！
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：addTruckInformation
     * 内容摘要：添加卡车的信息
     *
     * @param member_name       账号名
     * @param truckStringObject 卡车
     * @return String 返回值json
     */
    public String addTruckInformation(String member_name, String truckStringObject) {
        // 添加卡车信息时, person_name是个人或者公司，member_name是车主或者公司的管理账号(注:个人信息表的truck_id)
        // 添加卡车信息，member_name三种情况：1、管车版公司；2、管车版个人；3、司机个人；第一种情况车主是公司名称；个人添加车辆时车主是本人
        T_Data_Truck truck = null;
        if (truckStringObject != null && truckStringObject.trim().length() > 0) {
            truck = new Gson().fromJson(truckStringObject, T_Data_Truck.class);
        }
        if (truck == null) {
            return null;
        }

        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Person person = null;
        T_Data_Company company = null;
        T_Data_Member member = null;
        T_Data_Truck truck2 = null;
        String manager_member_id = null;  // 管理者ID
        String plate_number = truck.getPlate_number();

        try {
            truck2 = this.truckDaoMapper.findTruckByPlateNumber(plate_number);  // 通过车牌号查找车辆，避免重复添加车辆
            member = this.memberDaoMapper.findMemberByName(member_name);
            if (truck2 == null) {
                manager_member_id = member.getRelevance_info_id();
                truck.setTruck_id(UUIDUtil.getUUID());              // 绑定车辆ID,司机和车辆id关联
                truck.setOperate_status("0");                       // 车辆运营状态
                // 配合一期，此处修改审核通过了
                truck.setVerify_status("2");                        // 车辆审核状态
                truck.setManager_member_id(manager_member_id);      // 新增车辆管理者为自己
                truck.setSubmit_verify_time(DateUtil.getDate());    // 新增提交审核时间
                truck.setDelete_flag(0);                            // 新建删除标识符，0：未删除
                truck.setLast_update(DateUtil.getDate());           // 新增最终更新日
                truck.setLast_update_user_id("M:" + member_name);   // 新增最终更新者
                person = this.personDaoMapper.findPersonByID(member.getRelevance_info_id());
                person.setDriving_truck_id(truck.getTruck_id());    // 绑定车辆ID
                person.setDriving_status("1");                      // 新增车辆的司机驾驶状态默认为“1：待机”
                person.setSubmit_verify_time(DateUtil.getDate());   // 提交车辆关联时间为新增车辆时间
                person.setConfirm_relate_time(DateUtil.getDate());  // 确认车辆关联时间为新增车辆时间
                person.setLast_update_user_id("M:" + member_name);  // 最终更新者ID
                this.truckDaoMapper.saveTruck(truck);
                this.personDaoMapper.updatePerson(person);
                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                logger.info(TruckManager_Message.saveDone);
            } else {
                ecode = "3002";   // 车辆已添加
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";       // 保存卡车信息失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.saveErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：searchDriverOften
     * 内容摘要：检索常跑司机列表(2.0车组成员)
     *
     * @param plate_number 车牌号
     * @return String 返回值json
     */
    public String searchDriverOften(String plate_number) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;  // 获取车辆
        List<T_Data_Person> list = null;
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            list = this.personDaoMapper.findPersonByTruckId(truck.getTruck_id());
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
            for (T_Data_Person person : list) {
                if (Integer.valueOf(person.getDriving_status()) == 1 || Integer.valueOf(person.getDriving_status()) == 2) {
                    Map<String, String> results = new HashMap<String, String>();
                    T_Data_Member member = this.memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                    if (!member.getRelevance_info_id().equals(truck.getManager_member_id())) {
                        results.put("member_name", member.getMember_name());
                        results.put("person_name", person.getPerson_name());
                        lists.add(results);
                    }
                }
            }
            Map<String, String> resultss = new HashMap<String, String>();
            T_Data_Member managerMember = memberDaoMapper.findMerberByRelevance_info_id(truck.getManager_member_id());
            resultss.put("member_name", managerMember.getMember_name());
            T_Data_Person managerPerson = personDaoMapper.findPersonByID(truck.getManager_member_id());
            resultss.put("person_name", managerPerson.getPerson_name());
            lists.add(resultss);

            ecode = "1000"; // 成功响应！
            result.put("ecode", ecode);
            result.put("object1", lists);
            logger.info(TruckManager_Message.getTruckDone);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：editManagerMember
     * 内容摘要：车辆管理者变更
     *
     * @param plate_number            车牌号
     * @param old_manager_member_name 原管理者账号
     * @param new_manager_member_name 新管理者账号
     * @return String 返回值json
     */
    public String editManagerMember(String plate_number, String old_manager_member_name, String new_manager_member_name) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
        try {
            T_Data_Member managerMember = memberDaoMapper.findMemberByName(new_manager_member_name);
            truck.setManager_member_id(managerMember.getRelevance_info_id());
            truck.setLast_update(DateUtil.getDate());
            this.truckDaoMapper.updateTruck(truck);
            ecode = "1000"; // 获取数据成功！
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：truckDriversChange
     * 内容摘要：车辆行使司机变更
     *
     * @param plate_number            车牌号
     * @param new_driving_driver_list 会员和个人集合
     * @return String 返回值json
     */
    public String truckDriversChange(String plate_number, String new_driving_driver_list) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
        // 解析json为数组
        Gson gson = new Gson();
        try {
            if (new_driving_driver_list != null && new_driving_driver_list.trim().length() > 0) {
                // json转为带泛型的list
                List<Map<String, String>> retList = gson.fromJson(new_driving_driver_list, new TypeToken<List<Map<String, String>>>() {
                }.getType());

                // 将原行驶司机变更为常跑司机
                List<T_Data_Person> driversList = personDaoMapper.findPersonByTruckId(truck.getTruck_id());
                if (driversList != null && driversList.size() > 0) {
                    for (T_Data_Person driver : driversList) {
                        if ("2".equals(driver.getDriving_status())) {
                            driver.setDriving_status("1");
                            this.personDaoMapper.updatePerson(driver);
                        }
                    }
                }

                // 新行使司机列表中对象变更为该车的行驶司机
                for (Map<String, String> map : retList) {
                    String member_name = map.get("member_name");
                    String person_name = map.get("person_name");
                    T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
                    T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
                    person.setDriving_status("2");
                    this.personDaoMapper.updatePerson(person);
                }
            }
            ecode = "1000"; // 获取数据失败！
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：joinTheDriver
     * 内容摘要：通过车牌号和会员号申请加入常跑司机
     *
     * @param plate_number 车牌号
     * @param member_name  账号
     * @return String 返回值json
     */
    public String joinTheDriver(String plate_number, String member_name) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
        try {
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
            person.setDriving_truck_id(truck.getTruck_id());    // 关联车辆ID
            person.setDriving_status("0");                      // 驾驶状态（0：未确认）
            person.setSubmit_verify_time(DateUtil.getDate());   // 提交车辆关联时间
            person.setLast_update_user_id("M:" + member_name);    // 最终更新者ID
            this.personDaoMapper.updatePerson(person);
            ecode = "1000";
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：searchDriverByPlateNumber
     * 内容摘要：通过车牌号检索待确认司机列表
     *
     * @param plate_number 车牌号
     * @return String 返回值json
     */
    public String searchDriverByPlateNumber(String plate_number) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;  // 获取车辆
        List<T_Data_Person> list = null;
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            list = this.personDaoMapper.findPersonByTruckId(truck.getTruck_id());
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
            if (list != null && list.size() > 0) {
                for (T_Data_Person person : list) {
                    if ("0".equals(person.getDriving_status())) {
                        Map<String, String> results = new HashMap<String, String>();
                        T_Data_Member member = this.memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                        results.put("member_name", member.getMember_name());
                        results.put("person_name", person.getPerson_name());
                        lists.add(results);
                    }
                }
            }
            ecode = "1000"; // 成功响应！
            result.put("ecode", ecode);
            result.put("object1", lists);
            logger.info(TruckManager_Message.getTruckDone);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：judgeJoinDriver
     * 内容摘要：确认是否允许加入常跑司机
     *
     * @param plate_number 车牌号
     * @param member_name  会员名
     * @param accept_join  请求参数
     * @return String 返回值json
     */
    public String judgeJoinDriver(String plate_number, String member_name, String accept_join) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
        try {
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
            if ("0".equals(accept_join)) {
                // 不同意
//                person.setDriving_truck_id(null);                     // 关联车辆ID
                person.setDriving_status("3");                     // 行驶状态
                this.personDaoMapper.updatePerson(person);
            } else if ("1".equals(accept_join)) {
                // 同意
                person.setDriving_status("1");                      // 驾驶状态（1：待机）
                person.setConfirm_relate_time(DateUtil.getDate());  // 确认车辆关联时间
                this.personDaoMapper.updatePerson(person);
            }
            ecode = "1000";
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：relieveTruckAndDriver
     * 内容摘要：解除司机与车辆的关系
     *
     * @param plate_number 车牌号
     * @param member_name  账号
     * @return String 返回值json
     */
    public String relieveTruckAndDriver(String plate_number, String member_name) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
        try {
            Integer count = this.scheduleSheetDaoMapper.findScheduleUnFinishedByTruckIdAndStatusCount(truck.getTruck_id());   // 获取未完成订单的个数
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
            String manager = truck.getManager_member_id();
            List<T_Data_Person> personList = personDaoMapper.findPersonByTruckId(truck.getTruck_id());  // 获取车辆全部关联司机列表
//            ArrayList driverList = new ArrayList(); // 常跑司机列表
//            ArrayList applicantList = new ArrayList(); // 申请者列表
            int driverNumber = 0;  // 判断常跑司机人数
            if (personList != null && personList.size() > 0) {
                for (T_Data_Person persons : personList) {
                    if ("1".equals(persons.getDriving_status()) || "2".equals(persons.getDriving_status())) {
                        driverNumber++;
                    }
                }
            }
            if (count.equals(0)) {
                if (manager.equals(person.getPerson_id())) {
                    // 当解除关系者是车辆管理者时
                    if (driverNumber > 1) {
                        // 有其他常跑司机
                        ecode = "3000";
                        result.put("ecode", ecode);
                    } else if (driverNumber == 1) {
                        // 无其他常跑司机
                        truck.setDelete_flag(1);                            // 逻辑删除车辆
                        person.setDriving_truck_id("");                     // 关联车辆ID
                        person.setDriving_status("0");                      // 驾驶状态设为待确认
                        person.setLast_update_user_id("M:" + member_name);    // 最终更新者ID
                        this.personDaoMapper.updatePerson(person);
                        this.truckDaoMapper.updateTruck(truck);

                        if (personList != null && personList.size() > 0) {
                            // 清除待确认关联司机
                            for (T_Data_Person persons : personList) {
                                if ("0".equals(persons.getDriving_status())) {
                                    persons.setDriving_truck_id("");
                                    this.personDaoMapper.updatePerson(persons);
                                }
                            }
                        }
                        ecode = "1000";
                        result.put("ecode", ecode);
                    }
                } else if (!manager.equals(person.getPerson_id())) {
                    // 当解除关系者不是车辆管理者时
                    person.setDriving_truck_id("");                     // 关联车辆ID
                    person.setDriving_status("0");                      // 驾驶状态设为待确认
                    person.setLast_update_user_id("M:" + member_name);    // 最终更新者ID
                    this.personDaoMapper.updatePerson(person);
                    ecode = "1000";
                    result.put("ecode", ecode);
                }
            } else {
                ecode = "3001"; // 车辆有未完成调度单
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findAllTruckFuelType
     * 内容摘要：检索车辆燃料类型
     *
     * @return String 返回值json
     */
    public String findAllTruckFuelType() {
        String json = null;
        Map result = new HashMap();
        List<T_Sys_Dicdata> list = null;
        List truckFuelTypeList = new ArrayList();
        String ecode = null;
        try {
            list = this.dicdataDaoMapper.findAllDicdataByCode("317058F404DB406DAD8242FFD0FCD6A7", "%%");
            for (T_Sys_Dicdata truckFuelType : list) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("truck_fuel_type_id", truckFuelType.getDicdata_id());
                results.put("truck_fuel_type_name", truckFuelType.getDicdata_name());
                truckFuelTypeList.add(results);
            }
            ecode = "1000";   // 响应成功
            result.put("ecode", ecode);
            result.put("object1", truckFuelTypeList);
        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：setTruckPermanentAddress
     * 内容摘要：设置车辆的常驻地址
     *
     * @param plate_number         车牌号
     * @param resident_province_id 常驻地址省
     * @param resident_city_id     常驻地址市
     * @param resident_area_id     常驻地址区
     * @return String 返回值json
     */
    public String setTruckPermanentAddress(String plate_number, String resident_province_id, String resident_city_id, String resident_area_id) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
        try {
            truck.setResident_province_id(resident_province_id);
            truck.setResident_city_id(resident_city_id);
            truck.setResident_area_id(resident_area_id);
            truck.setLast_update(DateUtil.getDate());
            this.truckDaoMapper.updateTruck(truck);
            ecode = "1000"; // 获取数据成功！
            result.put("ecode", ecode);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：editTruckInfo
     * 内容摘要：修改车辆基本信息
     *
     * @param member_name       用户名
     * @param truckStringObject 卡车
     * @return String 返回值json
     */
    public String editTruckInfo(String member_name, String truckStringObject) {
        T_Data_Truck truck = null;
        if (truckStringObject != null && truckStringObject.trim().length() > 0) {
            truck = new Gson().fromJson(truckStringObject, T_Data_Truck.class);
        }
        if (truck == null) {
            return null;
        }

        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Person person = null;
        T_Data_Company company = null;
        T_Data_Member member = null;
        T_Data_Truck truck2 = null;
        String manager_member_id = null;  // 管理者ID
        String plate_number = truck.getPlate_number();

        try {
            truck2 = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            member = this.memberDaoMapper.findMemberByName(member_name);
            if (truck2 != null) {
                if ("0".equals(truck2.getVerify_status()) || "3".equals(truck2.getVerify_status())) {
                    manager_member_id = member.getRelevance_info_id();
                    truck2.setTruck_length_id(truck.getTruck_length_id());                  // 修改车辆长度ID
                    truck2.setTruck_type_id(truck.getTruck_type_id());                      // 修改车辆类型ID
                    truck2.setTruck_fuel_type_id(truck.getTruck_fuel_type_id());            // 修改车辆燃料类型ID
                    truck2.setTruck_carriage_type_id(truck.getTruck_carriage_type_id());    // 修改车辆厢型ID
                    truck2.setDriving_licence(truck.getDriving_licence());                  // 修改行驶证号
                    truck2.setLoad_weight(truck.getLoad_weight());                          // 修改载货重量
                    truck2.setLoad_volume(truck.getLoad_volume());                          // 修改载货体积
                    truck2.setTruck_first_pic_save_path(truck.getTruck_first_pic_save_path());                        // 修改车辆照片
                    truck2.setDriving_licence_first_page_save_path(truck.getDriving_licence_first_page_save_path());    // 修改行驶证照片
                    truck2.setSubmit_verify_time(DateUtil.getDate());                       // 修改提交审核时间
                    truck2.setLast_update(DateUtil.getDate());                               // 修改最终更新日
                    truck2.setLast_update_user_id("M:" + member_name);                       // 修改最终更新者
                    this.truckDaoMapper.updateTruck(truck2);
                    ecode = "1000";   // 响应成功
                    result.put("ecode", ecode);
                    logger.info(TruckManager_Message.saveDone);
                } else if ("1".equals(truck2.getVerify_status())) {
                    ecode = "3001";   // 车辆正在审核中
                    result.put("ecode", ecode);
                } else if ("2".equals(truck2.getVerify_status())) {
                    ecode = "3002";   // 车辆已经验证通过
                    result.put("ecode", ecode);
                }
            } else {
                ecode = "3000";   // 车辆数据不存在
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";       // 保存卡车信息失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.saveErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findManagerTruckListByMember
     * 内容摘要：根据账号名查询管理者车辆列表_v2.0
     *
     * @param member_name 会员名
     * @return String 返回值jsons
     */
    public String findManagerTruckListByMember(String member_name) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;    // 获取账户
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
            String member_id = member.getRelevance_info_id(); // 获取会员关联ID
            List<T_Data_Truck> truckList = truckDaoMapper.findTruckByManagerMemberID(member_id);
            for (T_Data_Truck truck : truckList) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("truck_id", truck.getTruck_id()); // 获取车辆ID
                results.put("plate_number", truck.getPlate_number()); // 获取车牌号
                String loadWeight = truck.getLoad_weight().substring(0, truck.getLoad_weight().indexOf("."));
                results.put("load_weight", loadWeight); // 获取载货重量
                List<T_Sys_Dicdata> dicdata_status = dicdataDaoMapper.findAllDicdataByCode("0D1DFD26128E4E35BBECA6D1F4FCE4FE", truck.getVerify_status());// 获取车辆审核状态
                results.put("verify_status", dicdata_status.get(0).getDicdata_name()); // 获取车辆审核状态
                String truck_type_id = truck.getTruck_type_id(); // 获取车辆类型ID
                T_Sys_Dicdata dicdata_type = dicdataDaoMapper.findDicdataByID(truck_type_id);
                results.put("truck_type_name", dicdata_type.getDicdata_name()); // 获取车辆类型
                String truck_carriage_type_id = truck.getTruck_carriage_type_id(); // 获取车厢类型ID
                T_Master_Truck_Carriage_Type truckCarriageType = truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck_carriage_type_id);
                results.put("truck_carriage_type_name", truckCarriageType.getTruck_carriage_type_name()); // 获取车厢类型
                lists.add(results);
            }
            ecode = "1000"; // 成功响应！
            result.put("ecode", ecode);
            result.put("result", lists);
            logger.info(TruckManager_Message.getTruckDone);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：saveTruckInformation
     * 内容摘要：添加卡车的信息_v2.0
     *
     * @param member_name       账号名
     * @param truckStringObject 卡车
     * @return String 返回值json
     */
    public String saveTruckInformation(String member_name, String truckStringObject) {
        T_Data_Truck truck = null;
        if (truckStringObject != null && truckStringObject.trim().length() > 0) {
            truck = new Gson().fromJson(truckStringObject, T_Data_Truck.class);
        }
        if (truck == null) {
            return null;
        }

        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Person person = null;
        T_Data_Company company = null;
        T_Data_Member member = null;
        T_Data_Truck truck2 = null;
        String manager_member_id = null;  // 管理者ID
        String plate_number = truck.getPlate_number();

        try {
            truck2 = this.truckDaoMapper.findTruckByPlateNumber(plate_number);  // 通过车牌号查找车辆，避免重复添加车辆
            member = this.memberDaoMapper.findMemberByName(member_name);
            if (truck2 == null) {
                manager_member_id = member.getRelevance_info_id();
                truck.setTruck_id(UUIDUtil.getUUID());              // 绑定车辆ID,司机和车辆id关联
                truck.setOperate_status("0");                       // 车辆运营状态
                // 此处配合暂定新增的车辆设置“瞪羚集团”公司为车主
                truck.setOwner_member_id("742C9E4DFC6940689A5D0F7CF6A69649");             // 车主ID
                // 配合一期，此处修改审核通过了
                truck.setVerify_status("2");                        // 车辆审核状态
                truck.setManager_member_id(manager_member_id);      // 新增车辆管理者为自己
                truck.setSubmit_verify_time(DateUtil.getDate());    // 新增提交审核时间
                truck.setDelete_flag(0);                            // 新建删除标识符，0：未删除
                truck.setLast_update(DateUtil.getDate());           // 新增最终更新日
                truck.setLast_update_user_id("M:" + member_name);   // 新增最终更新者
                this.truckDaoMapper.saveTruck(truck);
                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                logger.info(TruckManager_Message.saveDone);
            } else {
                ecode = "3002";   // 车辆已添加
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";       // 保存卡车信息失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.saveErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：searchDriverList
     * 内容摘要：检索常跑司机列表_v2.0
     *
     * @param plate_number 车牌号
     * @return String 返回值json
     */
    public String searchDriverList(String plate_number) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;  // 获取车辆
        List<T_Data_Person> list = null;
        List<T_Data_Transportation_Schedule_Sheet> scheduleSheets = null;   // 获取调度单信息
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            list = this.personDaoMapper.findPersonByTruckId(truck.getTruck_id());
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
            if (list.size() != 0) {
                for (T_Data_Person person : list) {
                    if (!person.getPerson_id().equals(truck.getManager_member_id())) {
                        if (Integer.valueOf(person.getDriving_status()) == 1 || Integer.valueOf(person.getDriving_status()) == 2) {
                            Map<String, String> results = new HashMap<String, String>();
                            T_Data_Member member = this.memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                            results.put("member_name", member.getMember_name());
                            results.put("person_name", person.getPerson_name());
                            lists.add(results);
                        }
                    }
                }
            }

            // 判断车辆管理者是否接单
            int j = 0;
            String managerMemberId = truck.getManager_member_id();
            List<T_Data_Transportation_Dispatch_Sheet> dispatchSheetsList = dispatchSheetDaoMapper.findDispatchByMemIdList(managerMemberId);
            if (dispatchSheetsList.size() == 0) {
                j = 1;
            } else {
                j = 0;
            }


//            scheduleSheets = scheduleSheetDaoMapper.findUnFinishSheetByTruckID(truck.getTruck_id());
//            if (scheduleSheets.size()==0) {
//                j = 1;
//            } else {
//                for (T_Data_Transportation_Schedule_Sheet schedule_sheet : scheduleSheets) {
//                    T_Data_Transportation_Dispatch_Sheet dispatchSheet = dispatchSheetDaoMapper.findByScheduleId(schedule_sheet.getScheduleSheetId());
//                    //获取司机
//                    if (dispatchSheet == null) {
//                        j = 1;
//                    } else {
//                        String receiveMemberIds = dispatchSheet.getReceiveMemberId();
//                        String[] receiveMemberId = receiveMemberIds.split("/");
//                        for (int i = 0; i < receiveMemberId.length; i++) {
//                            if (!managerMemberId.equals(receiveMemberId[i])) {
//                                j = 0;
//                            } else {
//                                j = 1;
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
            if (j == 1) {
                Map<String, String> resultss = new HashMap<String, String>();
                T_Data_Member managerMember = memberDaoMapper.findMerberByRelevance_info_id(managerMemberId);
                resultss.put("member_name", managerMember.getMember_name());
                T_Data_Person managerPerson = personDaoMapper.findPersonByID(managerMemberId);
                resultss.put("person_name", managerPerson.getPerson_name());
                lists.add(resultss);
            }

            if (lists.size() == 0) {
                //没有司机
                ecode = "4000";
                result.put("ecode", ecode);
            } else {
                ecode = "1000"; // 成功响应！
                result.put("ecode", ecode);
                result.put("object1", lists);
                logger.info(TruckManager_Message.getTruckDone);
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findTruckDataByMemberName
     * 内容摘要：根据账号和雇佣关系检索车辆详细信息_v2.0
     *
     * @param member_name 账号
     * @return String 返回值json
     */
    public String findTruckDataByMemberName(String member_name) {
        Map result = new HashMap();
        Map results = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;      // 获取车辆
        T_Data_Member drivingMember = null;    // 获取用户
        T_Data_Person drivingPerson = null;    // 获取司机
        drivingMember = memberDaoMapper.findMemberByName(member_name);
        drivingPerson = personDaoMapper.findPersonByID(drivingMember.getRelevance_info_id());
        try {
            if ("2".equals(drivingPerson.getVerify_status())) {
                truck = truckDaoMapper.findTruckByID(drivingPerson.getDriving_truck_id());
                if (truck == null) {
                    ecode = "3000"; // 车辆信息不存在！
                    result.put("ecode", ecode);
                } else if ("1".equals(drivingPerson.getDriving_status()) || "2".equals(drivingPerson.getDriving_status())) {
                    truck = truckDaoMapper.findTruckByID(drivingPerson.getDriving_truck_id());
                    results.put("plate_number", truck.getPlate_number() + "");                       // 获取车牌号
                    results.put("load_weight", truck.getLoad_weight() + "");                         // 获取重量
                    results.put("driving_licence_first_page_save_path", truck.getDriving_licence_first_page_save_path() + "");   // 获取行驶证首页照片
                    results.put("driving_licence_second_page_save_path", truck.getDriving_licence_second_page_save_path() + "");   // 获取行驶证第二页照片
                    results.put("vehicle_identify_number", truck.getVehicle_identify_number() + ""); // 获取车架号
                    results.put("engine_number", truck.getEngine_number() + "");                     // 获取发动机号
                    results.put("truck_first_pic_save_path", truck.getTruck_first_pic_save_path() + "");                       // 获取车辆第一张照片
                    results.put("truck_second_pic_save_path", truck.getTruck_second_pic_save_path() + "");                       // 获取车辆第二张照片
                    results.put("truck_third_pic_save_path", truck.getTruck_third_pic_save_path() + "");                       // 获取车辆第三张照片
                    results.put("resident_province_id", truck.getResident_province_id() + "");       // 获取常驻省
                    results.put("resident_city_id", truck.getResident_city_id() + "");               // 获取常驻城市
                    results.put("resident_area_id", truck.getResident_area_id() + "");               // 获取常驻区县
                    results.put("operate_status", truck.getOperate_status() + "");                   // 获取车辆状态
                    results.put("verify_status", truck.getVerify_status() + "");                     // 获取审核状态
                    results.put("verify_refused_reason", truck.getVerify_refused_reason() + "");     // 获取锁定理由

                    try {
                        // 获取车类型
                        Map resultss = new HashMap();
                        T_Sys_Dicdata truckType = null;
                        truckType = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_type_id());
                        resultss.put("truck_type_id", truckType.getDicdata_id() + "");
                        resultss.put("truck_type_name", truckType.getDicdata_name() + "");
                        results.put("car_type", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车长
                        Map resultss = new HashMap();
                        T_Sys_Dicdata truckLength = null;
                        truckLength = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_length_id());
                        resultss.put("truck_length_id", truckLength.getDicdata_id() + "");
                        resultss.put("truck_length_name", truckLength.getDicdata_name() + "");
                        results.put("car_length", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车厢类型
                        Map resultss = new HashMap();
                        T_Master_Truck_Carriage_Type truckCarriageType = null;
                        truckCarriageType = this.truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck.getTruck_carriage_type_id());
                        resultss.put("truck_carriage_type_id", truckCarriageType.getTruck_carriage_type_id() + "");
                        resultss.put("truck_carriage_type_name", truckCarriageType.getTruck_carriage_type_name() + "");
                        results.put("carriage_type", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车辆燃料类型
                        Map resultss = new HashMap();
                        T_Sys_Dicdata truckFuel = null;
                        truckFuel = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_fuel_type_id());
                        resultss.put("truck_fuel_type_id", truckFuel.getDicdata_id() + "");
                        resultss.put("truck_fuel_type_name", truckFuel.getDicdata_name() + "");
                        results.put("car_fuel_type", resultss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车品牌+型号
                        T_Data_Truck_Model truck_model = null;
                        T_Sys_Dicdata truckModel = null;
                        truck_model = this.truckModelDaoMapper.findTruckModelByNo(truck.getTruck_model_no());
                        if (truck_model != null) {
                            truckModel = this.dicdataDaoMapper.findDicdataByID(truck_model.getTruck_brand_id());
                            if (truckModel != null) {
                                results.put("truck_model", truckModel.getDicdata_name() + truck_model.getTruck_model_name() + "");
                            } else {
                                results.put("truck_model", truck_model.getTruck_model_name() + "");
                            }
                        } else {
                            results.put("truck_model", "");
                        }
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取保险公司
                        T_Sys_Dicdata insurance_company = null;
                        insurance_company = this.dicdataDaoMapper.findDicdataByID(truck.getInsurance_company_id());
                        if (insurance_company != null) {
                            results.put("insurance_company_id", insurance_company.getDicdata_id() + "");
                            results.put("insurance_company_name", insurance_company.getDicdata_name() + "");
                        } else {
                            results.put("insurance_company_id", "");
                            results.put("insurance_company_name", "");
                        }
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                    }

                    try {
                        // 获取车辆管理者、车辆管理者电话
                        String managerMemberID = null;
                        managerMemberID = truck.getManager_member_id();
                        T_Data_Member member = null;
                        member = memberDaoMapper.findMerberByRelevance_info_id(managerMemberID);
                        String accountType1 = member.getAccount_type();
                        String relevance_info_id = member.getRelevance_info_id();
                        Integer accountType = Integer.valueOf(accountType1);
                        if (accountType == 0) {
                            // 个人信息
                            T_Data_Person person = null;
                            person = personDaoMapper.findPersonByID(relevance_info_id);
                            results.put("manager_person_name", person.getPerson_name() + "");
                            results.put("manager_member_name", person.getPerson_mobile_phone() + "");
                        } else if (accountType == 1) {
                            // 公司信息
                            T_Data_Company company = null;
                            company = companyDaoMapper.findCompanyByID(relevance_info_id);
                            results.put("manager_person_name", company.getCompany_name() + "");
                            results.put("manager_member_name", company.getContact_mobile_phone() + "");
                        } else {
                            ecode = "3003"; // 车辆管理者信息获取失败
                            result.put("ecode", ecode);
                        }
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckManagerErr + e.getMessage());
                    }

                    try {
                        // 获取常跑司机
                        List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                        String truckId = null;
                        truckId = truck.getTruck_id();
                        String truck_id = truckId;
                        List<T_Data_Person> list = null;
                        list = personDaoMapper.findPersonByTruckId(truck_id);
                        T_Data_Member managerMember = null;
                        T_Data_Person managerPerson = null;
                        for (T_Data_Person person : list) {
                            if (Integer.valueOf(person.getDriving_status()) == 1 || Integer.valueOf(person.getDriving_status()) == 2) {
                                Map<String, String> resultss = new HashMap<String, String>();
                                T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                                String member_name_drive = drive.getMember_name();
                                resultss.put("member_name", member_name_drive + "");// 获取司机账号
                                resultss.put("person_name", person.getPerson_name() + "");// 获取司机姓名
                                listss.add(resultss);
                            }
                        }
                        // 获取车组中管理者信息
                        managerMember = memberDaoMapper.findMerberByRelevance_info_id(truck.getManager_member_id());
                        managerPerson = personDaoMapper.findPersonByID(truck.getManager_member_id());
                        if (managerMember != null && managerPerson != null) {
                            Map<String, String> resultss = new HashMap<String, String>();
                            resultss.put("member_name", managerMember.getMember_name() + "");// 获取管理者账号
                            resultss.put("person_name", managerPerson.getPerson_name() + "");// 获取管理者姓名
                            listss.add(resultss);
                            results.put("driver_often_array", listss);
                        }
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
                    }

                    try {
                        // 常跑路线
                        List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                        String truckId = null;
                        truckId = truck.getTruck_id();
                        String truck_id = truckId;
                        List<T_Data_Truck_Transport_Line> list = null;
                        list = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck_id);
                        for (T_Data_Truck_Transport_Line line : list) {
                            Map<String, String> resultss = new HashMap<String, String>();

                            List<T_Sys_Dicdata> dicdataList_start_province = null;  // 起点省
                            List<T_Sys_Dicdata> dicdataList_start_city = null;      // 起点城市
                            List<T_Sys_Dicdata> dicdatalist_end_province = null;    // 终点省
                            List<T_Sys_Dicdata> dicdataList_end_city = null;        // 终点城市

                            // 起点省的回显
                            dicdataList_start_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getStart_province_id());
                            if (dicdataList_start_province != null && dicdataList_start_province.size() != 0) {
                                line.setStart_province_id(dicdataList_start_province.get(0).getDicdata_name());
                            } else {
                                line.setStart_province_id("none");
                            }
                            // 起点市的回显
                            dicdataList_start_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getStart_city_id());
                            if (dicdataList_start_city != null && dicdataList_start_city.size() != 0) {
                                line.setStart_city_id(dicdataList_start_city.get(0).getDicdata_name());
                            } else {
                                line.setStart_city_id("none");
                            }
                            // 终点省的回显
                            dicdatalist_end_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getEnd_province_id());
                            if (dicdatalist_end_province != null && dicdatalist_end_province.size() != 0) {
                                line.setEnd_province_id(dicdatalist_end_province.get(0).getDicdata_name());
                            } else {
                                line.setEnd_province_id("none");
                            }
                            // 终点市的回显
                            dicdataList_end_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getEnd_city_id());
                            if (dicdataList_end_city != null && dicdataList_end_city.size() != 0) {
                                line.setEnd_city_id(dicdataList_end_city.get(0).getDicdata_name());
                            } else {
                                line.setEnd_city_id("none");
                            }

                            resultss.put("line_id", line.getLine_id() + "");// 获取线路ID
                            resultss.put("start_province_id", line.getStart_province_id() + "");// 获取开始省份
                            resultss.put("start_city_id", line.getStart_city_id() + "");// 获取开始城市
                            resultss.put("end_province_id", line.getEnd_province_id() + "");// 获取结束省份
                            resultss.put("end_city_id", line.getEnd_city_id() + "");// 获取结束城市
                            listss.add(resultss);
                        }
                        results.put("line_array", listss);
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getTruckLineErr + e.getMessage());
                    }

                    try {
                        // 获取待确认司机信息
                        List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                        String truckId = null;
                        truckId = truck.getTruck_id();
                        String truck_id = truckId;
                        List<T_Data_Person> list = null;
                        list = personDaoMapper.findPersonByTruckId(truck_id);
                        int driveNumber = 0; // 待确认司机数量
                        for (T_Data_Person person : list) {
                            if (Integer.valueOf(person.getDriving_status()) == 0) {
                                driveNumber++;
                            }
                        }
                        results.put("join_drivers_number", driveNumber + "");
                    } catch (Exception e) {
                        logger.error(TruckManager_Message.getJoinDriversNumberErr + e.getMessage());
                    }

                    ecode = "1000"; // 成功响应！
                    result.put("ecode", ecode);
                    result.put("object1", results);
                    logger.info(TruckManager_Message.getTruckDone);
                } else if ("0".equals(drivingPerson.getDriving_status())) {
                    truck = truckDaoMapper.findTruckByID(drivingPerson.getDriving_truck_id());
                    results.put("plate_number", truck.getPlate_number() + "");                       // 获取车牌号
                    ecode = "4000"; // 成功响应！
                    result.put("ecode", ecode);
                    result.put("object1", results);
                }
            } else {
                ecode = "3006"; // 用户未审核通过！
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findTruckDataByTruckPlateNumber
     * 内容摘要：根据车牌号检索车辆详细信息_v2.0
     *
     * @param plate_number 车牌号
     * @return String 返回值json
     */
    public String findTruckDataByTruckPlateNumber(String plate_number) {
        Map result = new HashMap();
        Map results = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;    // 获取车辆
        try {
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            if (truck == null) {
                ecode = "3000"; // 车辆信息不存在！
                result.put("ecode", ecode);
            } else {
                truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
                results.put("plate_number", truck.getPlate_number() + "");                       // 获取车牌号
                results.put("load_weight", truck.getLoad_weight() + "");                         // 获取重量
                results.put("driving_licence_first_page_save_path", truck.getDriving_licence_first_page_save_path() + "");   // 获取行驶证首页照片
                results.put("driving_licence_second_page_save_path", truck.getDriving_licence_second_page_save_path() + "");   // 获取行驶证第二页照片
                results.put("vehicle_identify_number", truck.getVehicle_identify_number() + ""); // 获取车架号
                results.put("engine_number", truck.getEngine_number() + "");                     // 获取发动机号
                results.put("truck_first_pic_save_path", truck.getTruck_first_pic_save_path() + "");                       // 获取车辆第一张照片
                results.put("truck_second_pic_save_path", truck.getTruck_second_pic_save_path() + "");                       // 获取车辆第二张照片
                results.put("truck_third_pic_save_path", truck.getTruck_third_pic_save_path() + "");                       // 获取车辆第三张照片
                results.put("resident_province_id", truck.getResident_province_id() + "");       // 获取常驻省
                results.put("resident_city_id", truck.getResident_city_id() + "");               // 获取常驻城市
                results.put("resident_area_id", truck.getResident_area_id() + "");               // 获取常驻区县
                results.put("operate_status", truck.getOperate_status() + "");                   // 获取车辆状态
                results.put("verify_status", truck.getVerify_status() + "");                     // 获取审核状态
                results.put("verify_refused_reason", truck.getVerify_refused_reason() + "");     // 获取锁定理由

                try {
                    // 获取车类型
                    Map resultss = new HashMap();
                    T_Sys_Dicdata truckType = null;
                    truckType = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_type_id());
                    resultss.put("truck_type_id", truckType.getDicdata_id() + "");
                    resultss.put("truck_type_name", truckType.getDicdata_name() + "");
                    results.put("car_type", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车品牌+型号
                    T_Data_Truck_Model truck_model = null;
                    T_Sys_Dicdata truckModel = null;
                    truck_model = this.truckModelDaoMapper.findTruckModelByNo(truck.getTruck_model_no());
                    if (truck_model != null) {
                        truckModel = this.dicdataDaoMapper.findDicdataByID(truck_model.getTruck_brand_id());
                        if (truckModel != null) {
                            results.put("truck_model", truckModel.getDicdata_name() + truck_model.getTruck_model_name() + "");
                        } else {
                            results.put("truck_model", truck_model.getTruck_model_name() + "");
                        }
                    } else {
                        results.put("truck_model", "");
                    }
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车长
                    Map resultss = new HashMap();
                    T_Sys_Dicdata truckLength = null;
                    truckLength = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_length_id());
                    resultss.put("truck_length_id", truckLength.getDicdata_id() + "");
                    resultss.put("truck_length_name", truckLength.getDicdata_name() + "");
                    results.put("car_length", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车辆燃料类型
                    Map resultss = new HashMap();
                    T_Sys_Dicdata truckFuel = null;
                    truckFuel = this.dicdataDaoMapper.findDicdataByID(truck.getTruck_fuel_type_id());
                    resultss.put("truck_fuel_type_id", truckFuel.getDicdata_id() + "");
                    resultss.put("truck_fuel_type_name", truckFuel.getDicdata_name() + "");
                    results.put("car_fuel_type", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取保险公司
                    T_Sys_Dicdata insurance_company = null;
                    insurance_company = this.dicdataDaoMapper.findDicdataByID(truck.getInsurance_company_id());
                    if (insurance_company != null) {
                        results.put("insurance_company_id", insurance_company.getDicdata_id() + "");
                        results.put("insurance_company_name", insurance_company.getDicdata_name() + "");
                    } else {
                        results.put("insurance_company_id", "");
                        results.put("insurance_company_name", "");
                    }
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车厢类型
                    Map resultss = new HashMap();
                    T_Master_Truck_Carriage_Type truckCarriageType = null;
                    truckCarriageType = this.truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck.getTruck_carriage_type_id());
                    resultss.put("truck_carriage_type_id", truckCarriageType.getTruck_carriage_type_id() + "");
                    resultss.put("truck_carriage_type_name", truckCarriageType.getTruck_carriage_type_name() + "");
                    results.put("carriage_type", resultss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckModelErr + e.getMessage());
                }

                try {
                    // 获取车辆管理者、车辆管理者电话
                    String managerMemberID = null;
                    managerMemberID = truck.getManager_member_id();
                    T_Data_Member member = null;
                    member = memberDaoMapper.findMerberByRelevance_info_id(managerMemberID);
                    String accountType1 = member.getAccount_type();
                    String relevance_info_id = member.getRelevance_info_id();
                    Integer accountType = Integer.valueOf(accountType1);
                    if (accountType == 0) {
                        // 个人信息
                        T_Data_Person person = null;
                        person = personDaoMapper.findPersonByID(relevance_info_id);
                        results.put("manager_person_name", person.getPerson_name() + "");
                        results.put("manager_member_name", person.getPerson_mobile_phone() + "");
                    } else if (accountType == 1) {
                        // 公司信息
                        T_Data_Company company = null;
                        company = companyDaoMapper.findCompanyByID(relevance_info_id);
                        results.put("manager_person_name", company.getCompany_name() + "");
                        results.put("manager_member_name", company.getContact_mobile_phone() + "");
                    } else {
                        ecode = "3003"; // 车辆管理者信息获取失败
                        result.put("ecode", ecode);
                    }
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckManagerErr + e.getMessage());
                }

                try {
                    // 获取常跑司机
                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                    String truckId = null;
                    truckId = truck.getTruck_id();
                    String truck_id = truckId;
                    List<T_Data_Person> list = null;
                    list = personDaoMapper.findPersonByTruckId(truck_id);
                    T_Data_Member managerMember = null;
                    T_Data_Person managerPerson = null;
                    managerMember = memberDaoMapper.findMerberByRelevance_info_id(truck.getManager_member_id());
                    managerPerson = personDaoMapper.findPersonByID(truck.getManager_member_id());
                    for (T_Data_Person person : list) {
                        if (!person.getPerson_id().equals(managerMember.getRelevance_info_id())) {
                            if (Integer.valueOf(person.getDriving_status()) == 1 || Integer.valueOf(person.getDriving_status()) == 2) {
                                Map<String, String> resultss = new HashMap<String, String>();
                                T_Data_Member drive = memberDaoMapper.findMerberByRelevance_info_id(person.getPerson_id());
                                String member_name_drive = drive.getMember_name();
                                resultss.put("member_name", member_name_drive + "");// 获取司机账号
                                resultss.put("person_name", person.getPerson_name() + "");// 获取司机姓名
                                listss.add(resultss);
                            }
                        }
                    }
                    // 获取车组中管理者信息
                    if (managerMember != null && managerPerson != null) {
                        Map<String, String> resultss = new HashMap<String, String>();
                        resultss.put("member_name", managerMember.getMember_name() + "");// 获取管理者账号
                        resultss.put("person_name", managerPerson.getPerson_name() + "");// 获取管理者姓名
                        listss.add(resultss);
                        results.put("driver_often_array", listss);
                    }
                    results.put("driver_often_array", listss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckDriverErr + e.getMessage());
                }

                try {
                    // 常跑路线
                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                    String truckId = null;
                    truckId = truck.getTruck_id();
                    String truck_id = truckId;
                    List<T_Data_Truck_Transport_Line> list = null;
                    list = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck_id);
                    for (T_Data_Truck_Transport_Line line : list) {
                        Map<String, String> resultss = new HashMap<String, String>();

                        List<T_Sys_Dicdata> dicdataList_start_province = null;  // 起点省
                        List<T_Sys_Dicdata> dicdataList_start_city = null;      // 起点城市
                        List<T_Sys_Dicdata> dicdatalist_end_province = null;    // 终点省
                        List<T_Sys_Dicdata> dicdataList_end_city = null;        // 终点城市

                        // 起点省的回显
                        dicdataList_start_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getStart_province_id());
                        if (dicdataList_start_province != null && dicdataList_start_province.size() != 0) {
                            line.setStart_province_id(dicdataList_start_province.get(0).getDicdata_name());
                        } else {
                            line.setStart_province_id("none");
                        }
                        // 起点市的回显
                        dicdataList_start_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getStart_city_id());
                        if (dicdataList_start_city != null && dicdataList_start_city.size() != 0) {
                            line.setStart_city_id(dicdataList_start_city.get(0).getDicdata_name());
                        } else {
                            line.setStart_city_id("none");
                        }
                        // 终点省的回显
                        dicdatalist_end_province = this.dicdataDaoMapper.findAllDicdataByCode("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getEnd_province_id());
                        if (dicdatalist_end_province != null && dicdatalist_end_province.size() != 0) {
                            line.setEnd_province_id(dicdatalist_end_province.get(0).getDicdata_name());
                        } else {
                            line.setEnd_province_id("none");
                        }
                        // 终点市的回显
                        dicdataList_end_city = this.dicdataDaoMapper.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", line.getEnd_city_id());
                        if (dicdataList_end_city != null && dicdataList_end_city.size() != 0) {
                            line.setEnd_city_id(dicdataList_end_city.get(0).getDicdata_name());
                        } else {
                            line.setEnd_city_id("none");
                        }

                        resultss.put("line_id", line.getLine_id() + "");// 获取线路ID
                        resultss.put("start_province_id", line.getStart_province_id() + "");// 获取开始省份
                        resultss.put("start_city_id", line.getStart_city_id() + "");// 获取开始城市
                        resultss.put("end_province_id", line.getEnd_province_id() + "");// 获取结束省份
                        resultss.put("end_city_id", line.getEnd_city_id() + "");// 获取结束城市
                        listss.add(resultss);
                    }
                    results.put("line_array", listss);
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getTruckLineErr + e.getMessage());
                }

                try {
                    // 获取待确认司机信息
                    List<Map<String, String>> listss = new ArrayList<Map<String, String>>(); // 列表数据
                    String truckId = null;
                    truckId = truck.getTruck_id();
                    String truck_id = truckId;
                    List<T_Data_Person> list = null;
                    list = personDaoMapper.findPersonByTruckId(truck_id);
                    int driveNumber = 0; // 待确认司机数量
                    for (T_Data_Person person : list) {
                        if (Integer.valueOf(person.getDriving_status()) == 0) {
                            driveNumber++;
                        }
                    }
                    results.put("join_drivers_number", driveNumber + "");
                } catch (Exception e) {
                    logger.error(TruckManager_Message.getJoinDriversNumberErr + e.getMessage());
                }

                ecode = "1000"; // 成功响应！
                result.put("ecode", ecode);
                result.put("object1", results);
                logger.info(TruckManager_Message.getTruckDone);
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：relieveTruckAndMember
     * 内容摘要：解除会员与车辆的关系_v2.0
     *
     * @param plate_number 车牌号
     * @param member_name  账号
     * @return String 返回值json
     */
    public String relieveTruckAndMember(String plate_number, String member_name) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
        List<T_Data_Person> personList = null;                              // 获取司机信息
        List<T_Data_Transportation_Schedule_Sheet> scheduleSheets = null;   // 获取调度单信息
        List<T_Data_Truck_Transport_Line> truckTransportLineList = null;       // 获取常跑路线信息
        double cashAmount = 0;
        try {
            Integer count = this.scheduleSheetDaoMapper.findScheduleUnFinishedByTruckIdAndStatusCount(truck.getTruck_id());   // 获取未完成订单的个数
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
            String manager = truck.getManager_member_id();
            personList = personDaoMapper.findPersonByTruckId(truck.getTruck_id());  // 获取车辆全部关联司机列表
            truckTransportLineList = truckTransportLineDaoMapper.findTruckTransportByTruckID(truck.getTruck_id());// 获取车辆全部常跑路线列表
            if (manager.equals(person.getPerson_id())) {
                // 当解除关系者是车辆管理者时
                if (count.equals(0)) {
                    // 无未完成订单
                    if (personList != null && personList.size() > 0) {
                        // 清除关联司机
                        for (T_Data_Person persons : personList) {
                            persons.setDriving_truck_id(null);
                            persons.setDriving_status(null);
                            persons.setLast_update(DateUtil.getDate());
                            persons.setLast_update_user_id("M:" + member_name);
                            this.personDaoMapper.updatePerson(persons);
                        }
                    }
                    if (truckTransportLineList != null && truckTransportLineList.size() > 0) {
                        // 清除车辆常跑路线
                        for (T_Data_Truck_Transport_Line truckTransportLine : truckTransportLineList) {
                            truckTransportLine.setLast_update(DateUtil.getDate());
                            truckTransportLine.setLast_update_user_id("M:" + member_name);
                            truckTransportLine.setDelete_flag(1);
                            this.truckTransportLineDaoMapper.updateTruckTransportLine(truckTransportLine);
                        }
                    }

                    cashAmount = truck.getCash_amount();    // 获取卡车现金额度
                    truck.setCash_amount(0);                                // 卡车现金额度清0
                    truck.setDelete_flag(1);                               // 逻辑删除车辆
                    truck.setLast_update(DateUtil.getDate());              // 最终跟新日
                    truck.setLast_update_user_id("M:" + member_name);      // 最终更新者ID

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
                        memberPaymentHistory.setLast_update_user_id("M:" + member_name);
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
                        truckPaymentHistory.setLast_update_user_id("M:" + member_name);
                        truckPaymentHistoryDaoMapper.saveTruckPaymentHistory(truckPaymentHistory);
                    }
                    this.truckDaoMapper.updateTruck(truck);
                    this.memberDaoMapper.updateMember(member);
                    ecode = "1000";
                    result.put("ecode", ecode);
                } else {
                    ecode = "3000"; // 车辆有未完成调度单
                    result.put("ecode", ecode);
                }
            } else if (!manager.equals(person.getPerson_id())) {
                // 当解除关系者不是车辆管理者时
                scheduleSheets = scheduleSheetDaoMapper.findUnFinishSheetByTruckID(truck.getTruck_id());
                if (scheduleSheets.size() == 0) {
                    ecode = "1000";
                } else {
                    for (T_Data_Transportation_Schedule_Sheet schedule_sheet : scheduleSheets) {
                        List<T_Data_Transportation_Dispatch_Sheet> dispatchSheetsList = dispatchSheetDaoMapper.findDispatchByMemIdList(schedule_sheet.getScheduleSheetId());
                        for (T_Data_Transportation_Dispatch_Sheet dispatchSheet : dispatchSheetsList) {
                            //获取司机
                            String receiveMemberIds = dispatchSheet.getReceiveMemberId();
                            if (person.getPerson_id().equals(receiveMemberIds)) {
                                ecode = "3001"; // 有未完成调度单
                                break;
                            } else {
                                ecode = "1000";
                            }
                        }
                    }
                }
                if ("1000".equals(ecode)) {
                    person.setDriving_truck_id(null);                         // 关联车辆ID
                    person.setDriving_status(null);                           // 驾驶状态
                    person.setLast_update(DateUtil.getDate());              // 最终跟新日
                    person.setLast_update_user_id("M:" + member_name);      // 最终更新者ID
                    this.personDaoMapper.updatePerson(person);
                    result.put("ecode", ecode);
                } else if ("3001".equals(ecode)) {
                    result.put("ecode", ecode);
                }
            }
        } catch (Exception e) {
            ecode = "2000";
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    // 根据车牌号会员名检索添加车辆权限_v2.0

    /**
     * 方法名称：findAddTruckAuthority
     * 内容摘要：根据车牌号会员名检索添加车辆权限_v2.0
     *
     * @param plate_number 车牌号
     * @param member_name  账号
     * @return String 返回值json
     */
    public String findAddTruckAuthority(String plate_number, String member_name) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        T_Data_Truck truck = null;  // 获取车辆
        List<T_Data_Truck> truckList = null;    // 获取管理车辆列表
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);   // 获取车辆
            T_Data_Member member = memberDaoMapper.findMemberByName(member_name);   // 获取会员
            T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());   // 获取个人
            if (truck == null && person != null) {
                ecode = "1000"; // 车辆可以添加
                result.put("ecode", ecode);
            } else if (truck != null && person != null) {
                truckList = truckDaoMapper.findTruckByManagerMemberID(member.getRelevance_info_id());
                if (truckList != null && truckList.size() != 0) {
                    ecode = "4000"; // 车辆已被添加，管理者无法添加
                    result.put("ecode", ecode);
                } else {
                    ecode = "3000"; // 申请加入此车辆
                    result.put("ecode", ecode);
                    result.put("plate_number", plate_number);
                    T_Data_Person manager_person = personDaoMapper.findPersonByID(truck.getManager_member_id());
                    result.put("manager_person_name", manager_person.getPerson_name());
                    result.put("manager_person_phone", manager_person.getPerson_mobile_phone());
                }
            }
        } catch (Exception e) {
            ecode = "2000"; // 响应错误
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findAllInsuranceCompany
     * 内容摘要：检索所有保险公司
     *
     * @return String 返回值json
     */
    public String findAllInsuranceCompany() {
        String json = null;
        Map result = new HashMap();
        List<T_Sys_Dicdata> list = null;
        List insuranceCompanyList = new ArrayList();
        String ecode = null;
        try {
            list = this.dicdataDaoMapper.findAllDicdataByCode("925A0C34595A45E0BD450B8E4C84DEC6", "%%");
            for (T_Sys_Dicdata insuranceCompany : list) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("insurance_company_id", insuranceCompany.getDicdata_id());
                results.put("insurance_company_name", insuranceCompany.getDicdata_name());
                insuranceCompanyList.add(results);
            }
            ecode = "1000";   // 响应成功
            result.put("ecode", ecode);
            result.put("object1", insuranceCompanyList);
        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findAllTruckBrand
     * 内容摘要：检索所有车辆品牌
     *
     * @return String 返回值json
     */
    public String findAllTruckBrand() {
        String json = null;
        Map result = new HashMap();
        List<T_Sys_Dicdata> list = null;
        List truckBrandList = new ArrayList();
        String ecode = null;
        try {
            list = this.dicdataDaoMapper.findAllDicdataByCode("60F8199A3A4D4136AC4B06BE97A1F974", "%%");
            for (T_Sys_Dicdata truckBrand : list) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("truck_brand_id", truckBrand.getDicdata_id());
                results.put("truck_brand_name", truckBrand.getDicdata_name());
                truckBrandList.add(results);
            }
            ecode = "1000";   // 响应成功
            result.put("ecode", ecode);
            result.put("object1", truckBrandList);
        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }


    /**
     * 方法名称：findAllTruckModeByTruckBrandId
     * 内容摘要：根据品牌ID查询所有的卡车型号
     *
     * @return String 返回值json
     */
    public String findAllTruckModeByTruckBrandId(String truck_brand_id) {
        String json = null;
        List<T_Data_Truck_Model> truckModelList = null;
        List<Map<String, String>> truckModels = new ArrayList<Map<String, String>>();
        Map result = new HashMap();
        String ecode = null;
        try {
            truckModelList = this.truckModelDaoMapper.findTruckModelByBrandId(truck_brand_id);
            for (T_Data_Truck_Model truckModel : truckModelList) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("truck_model_id", truckModel.getTruck_model_no() + "");
                results.put("truck_model_name", truckModel.getTruck_model_name());
                truckModels.add(results);
            }
            if (truckModels != null && truckModels.size() != 0) {
                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                result.put("object1", truckModels);
            }
        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findManagerTruckListByDriver
     * 内容摘要：根据账号名查询司机车辆列表_v2.0
     *
     * @param member_name 会员名
     * @return String 返回值jsons
     */
    public String findManagerTruckListByDriver(String member_name) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;    // 获取账户
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
            String member_id = member.getRelevance_info_id(); // 获取会员关联ID
            T_Data_Person driver = personDaoMapper.findPersonByID(member_id);
            String truck_id = driver.getDriving_truck_id();
            T_Data_Truck truck = truckDaoMapper.findTruckByID(truck_id);
            Map<String, String> results = new HashMap<String, String>();
            results.put("plate_number", truck.getPlate_number()); // 获取车牌号
            results.put("load_weight", truck.getLoad_weight()); // 获取载货重量
            String truck_type_id = truck.getTruck_type_id(); // 获取车辆类型ID
            T_Sys_Dicdata dicdata_type = dicdataDaoMapper.findDicdataByID(truck_type_id);
            results.put("truck_type_name", dicdata_type.getDicdata_name()); // 获取车辆类型
            String truck_carriage_type_id = truck.getTruck_carriage_type_id(); // 获取车厢类型ID
            T_Master_Truck_Carriage_Type truckCarriageType = truckCarriageTypeDaoMapper.findTruckCarriageTypeByID(truck_carriage_type_id);
            results.put("truck_carriage_type_name", truckCarriageType.getTruck_carriage_type_name()); // 获取车厢类型
            T_Data_Person person = personDaoMapper.findPersonByID(member_id);
            String driving_status = person.getDriving_status();
            if (driving_status.equals("1") || driving_status.equals("2")) {
                // 以审核
                results.put("driving_status", "已验证"); // 获取个人与车辆关联审核状态
            } else if (driving_status.equals("3")) {
                // 被拒绝
                results.put("driving_status", "被拒绝"); // 获取个人与车辆关联审核状态
            } else {
                // 待审核
                results.put("driving_status", "待审核"); // 获取个人与车辆关联审核状态
            }
            lists.add(results);
            ecode = "1000"; // 成功响应！
            result.put("ecode", ecode);
            result.put("result", lists);
            logger.info(TruckManager_Message.getTruckDone);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckManager_Message.getTruckErr);
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findByCargoTypeAndLineId
     * 内容摘要：查询符合条件的卡车列表信息（货物类型，干线）
     *
     * @return List<T_Data_Truck>   卡车信息列表
     */
    public List<T_Data_Truck> findByCargoTypeAndLineId(String cargo_type_id, String lineId) {
        List<T_Data_Truck> list = null;
        try {
            list = this.truckDaoMapper.findByCargoTypeAndLineId(cargo_type_id, lineId);
            logger.info(TruckManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findTruckAccount
     * 内容摘要：车辆账户检索_2.0
     *
     * @param member_name 会员名
     * @param identity    会员身份
     * @return String 返回值jsons
     */
    @Transactional
    public String findTruckAccount(String member_name, String identity) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;    // 获取账户
        T_Data_Person person = null;    // 获取用户
        String password_status = null;
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            if (member != null) {
                List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
                String member_id = member.getRelevance_info_id(); // 获取会员关联ID
                person = personDaoMapper.findPersonByID(member_id);
                if (person != null) {
                    if (identity.equals("0") || identity.equals("2")) {
                        // 车辆管理者
                        List<T_Data_Truck> truckList = truckDaoMapper.findTruckByManagerMemberID(member_id);
                        for (T_Data_Truck truck : truckList) {
                            Map<String, String> results = new HashMap<String, String>();
                            // 判断支付密码是否为空
                            if (truck.getPayment_password() == null || truck.getPayment_password().equals("")) {
                                password_status = "0"; // 没有密码
                            } else {
                                password_status = "1"; // 有密码
                            }
                            results.put("plate_number", truck.getPlate_number()); // 获取车牌号
                            results.put("cash_amount", truck.getCash_amount() + ""); // 获取卡车现金额度
                            results.put("password_status", password_status); // 获取是否拥有支付密码
                            lists.add(results);
                        }
                    } else {
                        String truck_id = person.getDriving_truck_id();
                        T_Data_Truck truck = truckDaoMapper.findTruckByID(truck_id);
                        Map<String, String> results = new HashMap<String, String>();
                        // 判断支付密码是否为空
                        if (truck.getPayment_password() == null || truck.getPayment_password().equals("")) {
                            password_status = "0"; // 没有密码
                        } else {
                            password_status = "1"; // 有密码
                        }
                        results.put("plate_number", truck.getPlate_number()); // 获取车牌号
                        results.put("cash_amount", truck.getCash_amount() + ""); // 获取卡车现金额度
                        results.put("password_status", password_status); // 获取是否拥有支付密码
                        lists.add(results);
                    }
                } else {
                    ecode = "3000"; // 用户信息不存在！
                }
                ecode = "1000"; // 成功响应！
                result.put("result", lists);
            } else {
                ecode = "3000"; // 用户信息不存在！
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            logger.error(TruckManager_Message.getTruckErr);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
        } finally {
            result.put("ecode", ecode);
            json = JSONUtil.toJSONString(result);
            return json;
        }
    }

    /**
     * 方法名称：rechargeTruckAccount
     * 内容摘要：车辆账户充值_2.0
     *
     * @param member_name  会员名
     * @param plate_number 车牌号
     * @param amount       金额
     * @return String 返回值jsons
     */
    @Transactional
    public String rechargeTruckAccount(String member_name, String plate_number, String amount) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;    // 获取账户
        T_Data_Truck truck = null;      // 获取车辆
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            if (member != null && truck != null) {
                Double memberAccountAmount = member.getMember_account_amount();
                Double cashAmount = truck.getCash_amount();
                double amount_double = Double.parseDouble(amount);
                if (memberAccountAmount < amount_double) {
                    ecode = "1004"; // 金额不足！
                } else {
                    // 支付金额足够时
                    member.setMember_account_amount(memberAccountAmount - amount_double);// 更新会员账户资金量
                    member.setLast_update(DateUtil.getDate());// 更新最终更新日
                    member.setLast_update_user_id("M:" + member_name);// 更新最终更新者ID
                    truck.setCash_amount(cashAmount + amount_double);// 更新卡车现金额度
                    truck.setLast_update(DateUtil.getDate());// 更新最终更新日
                    truck.setLast_update_user_id("M:" + member_name);// 更新最终更新者ID
                    memberDaoMapper.updateMember(member);
                    truckDaoMapper.updateTruck(truck);

                    String thirdPartyOrderID = RadomUtil.getOrderNo();

                    T_Data_Member_Payment_History memberPaymentHistory = new T_Data_Member_Payment_History();// 创建会员支付历史信息
                    memberPaymentHistory.setMember_id(member.getMember_id());
                    memberPaymentHistory.setPayment_type("2");
                    memberPaymentHistory.setTarget_id("T" + truck.getTruck_id());
                    memberPaymentHistory.setAmount(amount_double);
                    memberPaymentHistory.setCreate_time(DateUtil.getDate());
                    memberPaymentHistory.setReturn_result("2");
                    memberPaymentHistory.setPayment_result("0");
                    memberPaymentHistory.setThird_party_order_id(thirdPartyOrderID);
                    memberPaymentHistory.setDelete_flag("0");
                    memberPaymentHistory.setLast_update(DateUtil.getDate());
                    memberPaymentHistory.setLast_update_user_id("M:" + member_name);
                    this.memberPaymentHistoryDaoMapper.saveMemberPaymentHistory(memberPaymentHistory);

                    T_Data_Truck_Payment_History truckPaymentHistory = new T_Data_Truck_Payment_History();// 创建车辆支付历史信息
                    truckPaymentHistory.setTruck_id(truck.getTruck_id());
                    truckPaymentHistory.setPayment_type("0");
                    truckPaymentHistory.setTarget_id("M" + member.getMember_id());
                    truckPaymentHistory.setAmount(amount_double);
                    truckPaymentHistory.setCreate_time(DateUtil.getDate());
                    truckPaymentHistory.setReturn_result("2");
                    truckPaymentHistory.setPayment_result("0");
                    truckPaymentHistory.setThird_party_order_id(thirdPartyOrderID);
                    truckPaymentHistory.setDelete_flag("0");
                    truckPaymentHistory.setLast_update(DateUtil.getDate());
                    truckPaymentHistory.setLast_update_user_id("M:" + member_name);
                    truckPaymentHistoryDaoMapper.saveTruckPaymentHistory(truckPaymentHistory);

                    ecode = "1000"; // 成功！
                }
            } else {
                ecode = "1001"; // 用户或车辆信息不存在！
            }
        } catch (Exception e) {
            ecode = "2000"; // 失败！
            logger.error(TruckManager_Message.getTruckErr);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
        } finally {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
            String time = df.format(DateUtil.getDate());
            result.put("ecode", ecode);
            result.put("time", time); // 获取产生时间
            json = JSONUtil.toJSONString(result);
            return json;
        }
    }

    /**
     * 方法名称：withdrawalsTruckAccount
     * 内容摘要：车辆账户提现_2.0
     *
     * @param member_name  会员名
     * @param plate_number 车牌号
     * @param amount       金额
     * @return String 返回值jsons
     */
    @Transactional
    public String withdrawalsTruckAccount(String member_name, String plate_number, String amount) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Member member = null;    // 获取账户
        T_Data_Truck truck = null;      // 获取车辆
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            if (member != null && truck != null) {
                Double memberAccountAmount = member.getMember_account_amount();
                Double cashAmount = truck.getCash_amount();
                double amount_double = Double.parseDouble(amount);
                if (cashAmount < amount_double) {
                    ecode = "1004"; // 金额不足！
                } else {
                    // 支付金额足够时
                    truck.setCash_amount(cashAmount - amount_double);// 更新卡车现金额度
                    truck.setLast_update(DateUtil.getDate());// 更新最终更新日
                    truck.setLast_update_user_id("M:" + member_name);// 更新最终更新者ID
                    member.setMember_account_amount(memberAccountAmount + amount_double);// 更新会员账户资金量
                    member.setLast_update(DateUtil.getDate());// 更新最终更新日
                    member.setLast_update_user_id("M:" + member_name);// 更新最终更新者ID
                    memberDaoMapper.updateMember(member);
                    truckDaoMapper.updateTruck(truck);

                    String thirdPartyOrderID = RadomUtil.getOrderNo();

                    T_Data_Member_Payment_History memberPaymentHistory = new T_Data_Member_Payment_History();// 创建会员支付历史信息
                    memberPaymentHistory.setMember_id(member.getMember_id());
                    memberPaymentHistory.setPayment_type("3");
                    memberPaymentHistory.setTarget_id("T" + truck.getTruck_id());
                    memberPaymentHistory.setAmount(amount_double);
                    memberPaymentHistory.setCreate_time(DateUtil.getDate());
                    memberPaymentHistory.setReturn_result("2");
                    memberPaymentHistory.setPayment_result("0");
                    memberPaymentHistory.setThird_party_order_id(thirdPartyOrderID);
                    memberPaymentHistory.setLast_update(DateUtil.getDate());
                    memberPaymentHistory.setLast_update_user_id("M:" + member_name);
                    memberPaymentHistoryDaoMapper.saveMemberPaymentHistory(memberPaymentHistory);

                    T_Data_Truck_Payment_History truckPaymentHistory = new T_Data_Truck_Payment_History();// 创建车辆支付历史信息
                    truckPaymentHistory.setTruck_id(truck.getTruck_id());
                    truckPaymentHistory.setPayment_type("1");
                    truckPaymentHistory.setTarget_id("M" + member.getMember_id());
                    truckPaymentHistory.setAmount(amount_double);
                    truckPaymentHistory.setCreate_time(DateUtil.getDate());
                    truckPaymentHistory.setReturn_result("2");
                    truckPaymentHistory.setPayment_result("0");
                    truckPaymentHistory.setThird_party_order_id(thirdPartyOrderID);
                    truckPaymentHistory.setDelete_flag("0");
                    truckPaymentHistory.setLast_update(DateUtil.getDate());
                    truckPaymentHistory.setLast_update_user_id("M:" + member_name);
                    truckPaymentHistoryDaoMapper.saveTruckPaymentHistory(truckPaymentHistory);

                    ecode = "1000"; // 成功！
                }
            } else {
                ecode = "1001"; // 用户或车辆信息不存在！
            }
        } catch (Exception e) {
            ecode = "2000"; // 失败！
            logger.error(TruckManager_Message.getTruckErr);
        } finally {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
            String time = df.format(DateUtil.getDate());
            result.put("ecode", ecode);
            result.put("time", time); // 获取产生时间
            json = JSONUtil.toJSONString(result);
            return json;
        }
    }

    /**
     * 方法名称：findTruckAccountDetail
     * 内容摘要：车辆账户明细检索_2.0
     *
     * @param plate_number 车牌号
     * @param date         日期
     * @return String 返回值jsons
     */
    @Transactional
    public String findTruckAccountDetail(String plate_number, String date) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;    // 获取车辆
        List<T_Data_Truck_Payment_History> truckPaymentHistoryList = null;
        try {
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            if (truck != null) {
                String truck_id = truck.getTruck_id();
                truckPaymentHistoryList = truckPaymentHistoryDaoMapper.findTruckPaymentHistoryByTruckIDAndDate(truck_id, date);
                List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
                for (T_Data_Truck_Payment_History truckPaymentHistory : truckPaymentHistoryList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("history_number", truckPaymentHistory.getHistory_number()); // 获取历史编号
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    String currentTime = df.format(truckPaymentHistory.getCreate_time());
                    results.put("create_time", currentTime); // 获取产生时间
                    String payment_type = truckPaymentHistory.getPayment_type();
                    if (payment_type.equals("2")) {
                        results.put("amount", "-" + truckPaymentHistory.getAmount()); // 获取金额
                        T_Sys_Dicdata dicdata = dicdataDaoMapper.findDicdataByID(truckPaymentHistory.getFinacial_flow_type());
                        results.put("payment_type", dicdata.getDicdata_name()); // 获取类别
                        String targetId = truckPaymentHistory.getTarget_id();
                        T_Master_Service_Station station = stationDaoMapper.findStationByID(targetId);
                        if (station != null) {
                            results.put("target", station.getStation_name()); // 获取目标
                        } else {
                            results.put("target", ""); // 获取目标
                        }
                    } else {
                        if (payment_type.equals("0")) {
                            results.put("amount", "+" + truckPaymentHistory.getAmount()); // 获取金额
                            results.put("payment_type", "车辆资金分配"); // 获取类别
                        } else if (payment_type.equals("1")) {
                            results.put("amount", "-" + truckPaymentHistory.getAmount()); // 获取金额
                            results.put("payment_type", "车辆资金回收"); // 获取类别
                        }
                        String targetId = truckPaymentHistory.getTarget_id();
                        String member_id = targetId.substring(1, targetId.length());
                        T_Data_Member member = memberDaoMapper.findMemberByID(member_id);
                        T_Data_Person person = personDaoMapper.findPersonByID(member.getRelevance_info_id());
                        if (person != null) {
                            results.put("target", person.getPerson_name()); // 获取目标
                        } else {
                            results.put("target", ""); // 获取目标
                        }
                    }
                    String payment_result = truckPaymentHistory.getPayment_result();
                    if (payment_result.equals("0")) {
                        payment_result = "成功";
                    } else if (payment_result.equals("1")) {
                        payment_result = "失败";
                    } else if (payment_result.equals("2")) {
                        payment_result = "等待支付结果";
                    }
                    results.put("payment_result", payment_result); // 获取支付结果
                    lists.add(results);
                }
                ecode = "1000"; // 成功响应！
                result.put("result", lists);
            } else {
                ecode = "3000"; // 车辆信息不存在！
            }
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            logger.error(TruckManager_Message.getTruckErr);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
        } finally {
            result.put("ecode", ecode);
            json = JSONUtil.toJSONString(result);
            return json;
        }
    }

    /**
     * 方法名称：checkTruckPaymentPassword
     * 内容摘要：车辆账户密码检索
     *
     * @return String
     */
    @Transactional
    public String checkTruckPaymentPassword(String plate_number, String payment_password, String member_name) {
        String json = null;
        String ecode = null;
        T_Data_Member member = null;
        T_Data_Truck truck = null;
        T_Data_Sys_Info sysInfo = null;
        String errorPaymentPasswordMaxTime = null; // 系统表支付密码每天可接受错误次数
        String todayErrorCount = null;             // 支付密码错误次数
        Map result = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        payment_password = MD5Util.md5(payment_password);
        Date date_server = DateUtil.getDate();
        try {
            sysInfo = this.sysInfoDaoMapper.findSysInfo();
            errorPaymentPasswordMaxTime = sysInfo.getError_payment_password_max_time();
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            String paymentPassword = truck.getPayment_password();         // 调用接口时数据库密码不为空
            // 比较卡车支付密码错误输入历史信息表中当天输出错误的条数和系统信息表中的支付密码错误次数是否不同或同时等于0
            String create_time = sdf.format(new Date());
            int count = this.truckPaymentErrorPasswordCommitHistoryDaoMapper.findErrorPasswordCommitHistoryByIDAndTimeCount(plate_number, create_time);
            todayErrorCount = String.valueOf(count);
            if (!todayErrorCount.equals(errorPaymentPasswordMaxTime) ||
                    (todayErrorCount.equals("0") && errorPaymentPasswordMaxTime.equals("0"))) {
                // 支付密码是否输入正确
                if (paymentPassword.equals(payment_password)) {
                    // 支付密码输入正确
                    ecode = "1000";
                } else {
                    // 支付密码输入错误
                    T_Data_Truck_Payment_Error_Password_Commit_History truckPaymentErrorPasswordCommitHistory = new T_Data_Truck_Payment_Error_Password_Commit_History();
                    truckPaymentErrorPasswordCommitHistory.setTruck_id(truck.getTruck_id());
                    member = memberDaoMapper.findMemberByName(member_name);
                    if (member != null) {
                        truckPaymentErrorPasswordCommitHistory.setDeal_member_id(member.getMember_id());
                    } else {
                        truckPaymentErrorPasswordCommitHistory.setDeal_member_id("M:" + member_name);
                    }
                    truckPaymentErrorPasswordCommitHistory.setDelete_flag("0");
                    truckPaymentErrorPasswordCommitHistory.setCreate_time(date_server);
                    truckPaymentErrorPasswordCommitHistory.setLast_update(date_server);
                    truckPaymentErrorPasswordCommitHistory.setLast_update_user_id("M:" + member_name);

                    ecode = "1004";// 支付密码输入错误
                }
            } else {
                // 卡车支付密码错误输入历史信息表中当天输出错误的条数系统信息表中的支付密码错误次数二者相同且不等于0
                ecode = "1005"; // 密码次数超过n次
            }
        } catch (Exception e) {
            ecode = "2000"; // 系统错误
            logger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
        } finally {
            result.put("ecode", ecode);
            json = JSONUtil.toJSONString(result);
            return json;
        }
    }

    /**
     * 方法名称：findAllBank
     * 内容摘要：检索所有银行信息
     *
     * @return String 返回值json
     */
    public String findAllBank() {
        String json = null;
        Map result = new HashMap();
        List<T_Sys_Dicdata> list = null;
        List bankList = new ArrayList();
        String ecode = null;
        try {
            list = this.dicdataDaoMapper.findAllDicdataByCode("40C440A05FD14E7CA187B00BD9EDEE21", "%%");
            for (T_Sys_Dicdata bank : list) {
                Map<String, String> results = new HashMap<String, String>();
                results.put("bank_name", bank.getDicdata_name());
                results.put("bank_code", bank.getDicdata_code());
                bankList.add(results);
            }
            ecode = "1000";   // 响应成功
            result.put("ecode", ecode);
            result.put("object1", bankList);
        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            result.put("ecode", ecode);
            logger.error(e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：scanQRCodePayment
     * 内容摘要：车辆扫描二维码支付（支付）
     *
     * @param member_name        会员名
     * @param station_id         线下加盟商ID
     * @param amount             金额
     * @param plate_number       车牌号
     * @param finacial_flow_type 支出类型ID
     * @param longitude          经度
     * @param latitude           纬度
     * @return String 返回值json
     */
    @Transactional
    public String scanQRCodePayment(String member_name, String station_id, String amount, String plate_number, String finacial_flow_type, String longitude, String latitude) {
        String json = null;
        String ecode = null;
        Map result = new HashMap();
        T_Master_Service_Station station = null;
        T_Data_Truck truck = null;
        T_Data_Member member = null;
        T_Data_Truck_Immediate truckImmediate = null;
        Map<String, String> rbMap = new HashMap<String, String>(0);                  // 融宝map
        String order_no = null;                                         // 订单号
        String content = null;                                          // 批次明细
        try {
            station = stationDaoMapper.findStationByID(station_id);
            truck = truckDaoMapper.findTruckByPlateNumber(plate_number);
            member = memberDaoMapper.findMemberByName(member_name);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            double money = Double.parseDouble(amount);
            if (truck != null && station != null && member != null) {
                double cash_amount = truck.getCash_amount();
                if (cash_amount >= money) {
                    // 支付资金充裕
                    T_Data_Truck_Payment_History truckPaymentHistory = new T_Data_Truck_Payment_History();// 创建车辆支付历史信息
                    String bank_account = station.getBank_account();
                    String bank_account_person_name = station.getBank_account_person_name();
                    String bank_account_person_id = station.getBank_account_person_id();
                    String bank_id = station.getBank_id();
                    String bank_name = dicdataDaoMapper.findAllDicdataByCode("40C440A05FD14E7CA187B00BD9EDEE21", bank_id).get(0).getDicdata_name();

                    // 调用融宝接口进行提现(默认审核通过)
                    rbMap.put("charset", ReapalConfig.charset);
                    rbMap.put("trans_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    rbMap.put("notify_url", ReapalConfig.station_agent_pay_notify_url);
                    rbMap.put("batch_no", RadomUtil.getBatchNo());
                    rbMap.put("batch_count", "1");
                    rbMap.put("batch_amount", amount);
                    rbMap.put("pay_type", "1");
                    order_no = RadomUtil.getOrderNo();
                    content = ReapalConfig.order_no + "," + bank_account + "," + bank_account_person_name + "," + bank_name + "," + "" +
                            "," + "" + "," + ReapalConfig.withdraw_type + "," + amount + "," + ReapalConfig.money_type
                            + "," + "" + "," + "" + "," + "" + "," + ReapalConfig.card_type + "," + bank_account_person_id + "," +
                            "" + "," + order_no + "," + "0";
                    rbMap.put("content", content);
                    // 生成签名
                    String post = ReapalSubmit.agentPayBuildSubmit(rbMap, AGENTPAY_URL);
                    String res = Decipher.decryptData(post);
                    //将json字符串转换成json对象,获取相应的key值
                    JSONObject obj = new JSONObject(res);
                    String result_code = (String) obj.get("result_code");
                    logger.info(result_code);

                    // 判断是否提交成功
                    if (result_code.equals(RESULT_CODE_SUCCESS)) {
                        // 融宝是否提交成功
                        truck.setCash_amount(cash_amount - money);
                        truck.setLast_update(DateUtil.getDate());
                        truck.setLast_update_user_id("M:" + member_name);
                        truckDaoMapper.updateTruck(truck);// 更新卡车现金额度

                        truckPaymentHistory.setReturn_result("0");// 回传状态为以提交
                        truckPaymentHistory.setPayment_result("2");// 卡车支付历史信息表(支付结果等待支付)
                        ecode = "1000";   // 支付成功
                    } else {
                        // 融宝是否提交失败
                        ecode = "1002";   // 支付失败
                        truckPaymentHistory.setReturn_result("2");// 回传状态为不需要回传
                        truckPaymentHistory.setPayment_result("1");// 卡车支付历史信息表(支付结果失败)
                        truckPaymentHistory.setFailure_result(res);// 失败原因
                    }
                    logger.info("解密返回的数据==========>" + res);
                    // 插入卡车支付历史信息表
                    truckPaymentHistory.setTruck_id(truck.getTruck_id());
                    truckPaymentHistory.setPayment_type("2");
                    truckPaymentHistory.setFinacial_flow_type(finacial_flow_type);
                    truckPaymentHistory.setTarget_id(station_id);
                    truckPaymentHistory.setTarget_bank_account(bank_account);
                    truckPaymentHistory.setAmount(money);
                    truckPaymentHistory.setCreate_time(DateUtil.getDate());
                    truckImmediate = truckImmediateDaoMapper.findTruckImmediateByPlateNumber(plate_number);// 获取卡车即时信息
                    if (truckImmediate != null) {
                        truckPaymentHistory.setTruck_longitude(truckImmediate.getLongitude());
                        truckPaymentHistory.setTruck_latitude(truckImmediate.getLatitude());
                    }
                    double lng = Double.parseDouble(longitude);
                    double lat = Double.parseDouble(latitude);
                    truckPaymentHistory.setTerminal_longitude(lng);
                    truckPaymentHistory.setTerminal_latitude(lat);
                    truckPaymentHistory.setDeal_person_id(member.getRelevance_info_id());
                    truckPaymentHistory.setReturn_result("0");
                    truckPaymentHistory.setThird_party_order_id(order_no);
                    truckPaymentHistory.setDelete_flag("0");
                    truckPaymentHistory.setLast_update(DateUtil.getDate());
                    truckPaymentHistory.setLast_update_user_id("M:" + member_name);
                    truckPaymentHistoryDaoMapper.saveTruckPaymentHistory(truckPaymentHistory);

                    //根据车辆id查询未完成的调度单
                    List<T_Data_Transportation_Schedule_Sheet> unFinishSheetList = scheduleSheetDaoMapper.findUnFinishSheetByTruckID(truck.getTruck_id());
                    if (truckPaymentHistory.getPayment_result().equals("0") && unFinishSheetList.size() != 0) {
                        // 支付成功时&&有未完成调度单
                        // 插入支出数据到货物运输调度单财务流水信息表
                        T_Data_Transportation_Schedule_Finance_Record transportationScheduleFinanceRecord = new T_Data_Transportation_Schedule_Finance_Record();
                        transportationScheduleFinanceRecord.setFinacial_flow_id(UUIDUtil.getUUID());
                        transportationScheduleFinanceRecord.setSchedule_id(unFinishSheetList.get(0).getScheduleSheetId());
                        transportationScheduleFinanceRecord.setFinacial_flow_direction("5");// 流水方向:5(支出)
                        transportationScheduleFinanceRecord.setFinacial_flow_type(finacial_flow_type);
                        transportationScheduleFinanceRecord.setTruck_payment_history_number(truckPaymentHistory.getHistory_number());// 卡车支付历史编号
                        transportationScheduleFinanceRecord.setTarget_service_station_id(station_id);
                        transportationScheduleFinanceRecord.setAmount(money);
                        transportationScheduleFinanceRecord.setCreate_time(format.format(DateUtil.getDate()));
                        truckImmediate = truckImmediateDaoMapper.findTruckImmediateByPlateNumber(plate_number);// 获取卡车即时信息
                        if (truckImmediate != null) {
                            transportationScheduleFinanceRecord.setLongitude(Double.toString(truckImmediate.getLongitude()));
                            transportationScheduleFinanceRecord.setLatitude(Double.toString(truckImmediate.getLatitude()));
                        }
                        transportationScheduleFinanceRecord.setDeal_person_id(member.getRelevance_info_id());
                        transportationScheduleFinanceRecord.setDelete_flag(0);
                        transportationScheduleFinanceRecord.setLast_update(DateUtil.getDate());
                        transportationScheduleFinanceRecord.setLast_update_user_id("M:" + member_name);
                        transportationScheduleFinanceRecordDaoMapper.saveFinacialFlow(transportationScheduleFinanceRecord);
                    }
                } else {
                    ecode = "1004";   // 金额不足
                }
            } else {
                ecode = "1001";   // 信息不存在
            }

        } catch (Exception e) {
            ecode = "2000";   // 响应失败
            logger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚
        } finally {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
            String time = df.format(DateUtil.getDate());
            result.put("ecode", ecode);
            result.put("time", time); // 获取产生时间
            json = JSONUtil.toJSONString(result);
            return json;
        }
    }

    /**
     * 方法名称: queryCashAmount
     * 方法描述：查询车辆账户总额度
     * @return double
     */
    public double queryCashAmount() {
        double CashAmount = 0.00;
        try {
            CashAmount = this.truckDaoMapper.queryCashAmount();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return CashAmount;
    }
}