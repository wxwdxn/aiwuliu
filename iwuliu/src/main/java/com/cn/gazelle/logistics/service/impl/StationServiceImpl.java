/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: StationServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：油气站查询接口实现
 * 设计文件：
 * 完成日期：2016-02-25
 * 作    者：QJ
 * 内容摘要：油气站查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.ErrorQuickResponseCodeScanRecordDaoMapper;
import com.cn.gazelle.logistics.dao.MemberDaoMapper;
import com.cn.gazelle.logistics.dao.StationDaoMapper;
import com.cn.gazelle.logistics.dao.AllianceBusinessDaoMapper;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.StationService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.StationManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类 名 称: StationServiceImpl
 * 内容摘要: 油气站查询接口
 * 方法描述：该类有7个方法：
 *         01 findStationByID                   根据油气站ID查找油气站信息
 *         02 findStationByName                 根据油气站名称查找油气站信息
 *         03 findAllRepairStation              查询符合条件的油气站列表信息（默认查询所有油气站）
 *         04 findAllRepairStationRowsCount     查询油气站记录数(包含有条件和无条件)
 *         05 saveStation                       增加油气站信息
 *         06 updateStation                     更新油气站信息
 *         07 delStation                        删除油气站信息
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.StationService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class StationServiceImpl implements StationService {
    // Log初始化
    Logger logger = Logger.getLogger(StationServiceImpl.class);

    @Resource
    private StationDaoMapper stationDaoMapper;          // 数据访问层
    @Resource
    private AllianceBusinessDaoMapper allianceBusinessDaoMapper;
    @Resource
    private MemberDaoMapper memberDaoMapper;
    @Resource
    private ErrorQuickResponseCodeScanRecordDaoMapper errorQuickResponseCodeScanRecordDaoMapper;

    /**
     * 方法名称：findStationByID
     * 内容摘要：根据油气站ID查找油气站信息
     * @param station_id 油气站id
     * @return T_Master_Service_Station 油气站信息
     */
    public T_Master_Service_Station findStationByID(String station_id) {
        T_Master_Service_Station station = null;
        try {
            station = this.stationDaoMapper.findStationByID(station_id);
            logger.info(StationManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(StationManager_Message.searchErr + e.getMessage());
        }
        return station;
    }

    /**
     * 方法名称：findStationByName
     * 内容摘要：根据油气站名称查询油气站
     * @param station_name 油气站名称
     * @return T_Master_Service_Station 油气站信息
     */
    public T_Master_Service_Station findStationByName(String station_name) {
        T_Master_Service_Station station = null;
        try {
            station = this.stationDaoMapper.findStationByName(station_name);
            logger.info(StationManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(StationManager_Message.searchErr + e.getMessage());
        }
        return station;
    }

    /**
     * 方法名称：findAllStation
     * 内容摘要：查询符合条件的油气站列表信息（默认查询所有油气站）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @param page 页面页数
     * @param rows 页面显示条数
     * @return List<T_Master_Service_Station>  油气站信息列
     */
//    public List<T_Master_Service_Station> findAllStation(String search_type, String name, Integer page, Integer rows) {
//        List<T_Master_Service_Station> list = null;
//        try {
//            list = this.stationDaoMapper.findAllStation(search_type, name, page, rows);
//            logger.info(StationManager_Message.searchDone);
//        } catch (Exception e) {
//            logger.error(StationManager_Message.searchErr + e.getMessage());
//        }
//        return list;
//    }

    /**
     * 方法名称：findAllStationRowsCount
     * 内容摘要：查询符合条件的油气站列表信息（默认查询所有油气站）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @return Integer  油气站信息记录数
     */
//    public Integer findAllStationRowsCount(String search_type, String name) {
//        int c = 0;
//        try {
//            c = this.stationDaoMapper.findAllStationRowsCount(search_type, name);
//        } catch (Exception e) {
//            logger.error(StationManager_Message.getSelectStationCountError + e.getMessage());
//        }
//        return c;
//    }

    /**
     * 方法名称：saveStation
     * 内容摘要：增加油气站信息
     * @param station 油气站信息
     */
    public boolean saveStation(T_Master_Service_Station station) {
        boolean b = new Boolean(true);
        try {
            this.stationDaoMapper.saveStation(station);
            logger.info(StationManager_Message.saveDone);
        } catch (Exception e) {
            b = false;
            logger.error(StationManager_Message.saveErr + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateStation
     * 内容摘要：更新油气站信息
     * @param station 油气站信息
     */
    public boolean updateStation(T_Master_Service_Station station) {
        boolean b = new Boolean(true);
        try {
            this.stationDaoMapper.updateStation(station);
            logger.info(StationManager_Message.updataDone);
        } catch (Exception e) {
            b = false;
            logger.error(StationManager_Message.updataErr + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delStation
     * 内容摘要：删除油气站信息
     * @param station_id 油气站id
     */
    public void delStation(String station_id) {
        try {
            this.stationDaoMapper.delStation(station_id);
            logger.info(StationManager_Message.delDone);
        } catch (Exception e) {
            logger.error(StationManager_Message.delErr + e.getMessage());
        }
    }

    /**
     * 方法名称：findStationList
     * 内容摘要：根据条件查询线下加盟服务站列表
     *
     * @return findRouterList 线路列表
     */
    public List<T_Master_Alliance_Business_Management> findStationList(HashMap<String, String> conditions) {
        List<T_Master_Alliance_Business_Management> stationList = null;
        try {
            stationList = this.allianceBusinessDaoMapper.findStationList(conditions);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return stationList;
    }

    /**
     * 方法名称：findAllianceBusinessById
     * 内容摘要：根据ID查询线下加盟服务站
     * @param station_id 线下加盟服务站ID
     * @return T_Master_Alliance_Business_Management 线下加盟服务站信息
     */
    public T_Master_Alliance_Business_Management findAllianceBusinessById(String station_id) {
        T_Master_Alliance_Business_Management station = null;
        try {
            station = this.allianceBusinessDaoMapper.findAllianceBusinessById(station_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return station;
    }

    /**
     * 方法名称：saveAllianceBusiness
     * 内容摘要：新增线下加盟服务站
     *
     * @param list     线下加盟服务站信息
     * @param userName 用户名
     * @return flag
     */
    @Transactional
    public int saveAllianceBusiness(String list, String userName) {
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(list, new TypeToken<Map<String, Object>>() {
        }.getType());
        int count_station = 0;
        int flag = 0;
        try {
            String station_name = (String) data.get("station_name");
            T_Master_Service_Station stationOfBD = this.stationDaoMapper.findStationByName(station_name);
            if (stationOfBD == null) {
                T_Master_Service_Station station = new T_Master_Service_Station();
                station.setStation_id("S" + UUIDUtil.getUUID());
                station.setStation_name(station_name);
                String station_type_string = (String) data.get("station_type");
                int station_type = Integer.parseInt(station_type_string);
                station.setStation_type(station_type);
                String province_id = (String) data.get("province_id");
                station.setProvince_id(province_id);
                String city_id = (String) data.get("city_id");
                station.setCity_id(city_id);
                String area_id = (String) data.get("area_id");
                station.setArea_id(area_id);
                String town_street = (String) data.get("town_street");
                station.setTown_street(town_street);
                String longitude = (String) data.get("longitude");
                station.setLongitude(longitude);
                String latitude = (String) data.get("latitude");
                station.setLatitude(latitude);
                String work_begin_time = (String) data.get("work_begin_time");
                station.setWork_begin_time(work_begin_time);
                String work_end_time = (String) data.get("work_end_time");
                station.setWork_end_time(work_end_time);
                String contact_name = (String) data.get("contact_name");
                station.setContact_name(contact_name);
                String contact_phone = (String) data.get("contact_phone");
                station.setContact_phone(contact_phone);
                String bank_id = (String) data.get("bank_id");
                station.setBank_id(bank_id);
                String bank_account = (String) data.get("bank_account");
                station.setBank_account(bank_account);
                String bank_account_person_name = (String) data.get("bank_account_person_name");
                station.setBank_account_person_name(bank_account_person_name);
                String bank_account_person_id = (String) data.get("bank_account_person_id");
                station.setBank_account_person_id(bank_account_person_id);
                station.setLast_update(DateUtil.getDate());
                station.setLast_update_user_id("U:" + userName);
                count_station = stationDaoMapper.saveStation(station);
                if (count_station == 1) {
                    flag = 1;   // 保存成功
                } else {
                    flag = 0;   // 保存失败
                    throw new RuntimeException(flag + "");
                }
            } else {
                flag = 2; //服务站名称重复
                throw new RuntimeException(String.valueOf(flag));
            }
        } catch (Exception e) {
            if (e.getMessage().equals("0")){
                flag = -1;
            }else if (e.getMessage().equals("2")){
                flag = 0;
            }
            throw new RuntimeException(flag + "");
        }
        return flag;
    }

    /**
     * 方法名称：upDateAllianceBusiness
     * 内容摘要：编辑线下加盟服务站
     *
     * @param list     线下加盟服务站信息
     * @param userName 用户名
     * @return flag
     */
    @Transactional
    public int upDateAllianceBusiness(String list, String userName) {
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(list, new TypeToken<Map<String, Object>>() {
        }.getType());
        T_Master_Service_Station station = null;
        T_Master_Service_Station stationOfBD = null;
        int count_station = 0;
        int flag = 0;
        try {
            String station_id = (String) data.get("station_id");
            station = this.stationDaoMapper.findStationByID(station_id);
            if (station != null) {
                String station_name = (String) data.get("station_name");
                stationOfBD = this.stationDaoMapper.findStationByName(station_name);
                if (station.getStation_name().equals(station_name) || stationOfBD == null) {
                    station.setStation_name(station_name);
                    String station_type_string = (String) data.get("station_type");
                    int station_type =Integer.parseInt(station_type_string);
                    station.setStation_type(station_type);
                    String province_id = (String) data.get("province_id");
                    station.setProvince_id(province_id);
                    String city_id = (String) data.get("city_id");
                    station.setCity_id(city_id);
                    String area_id = (String) data.get("area_id");
                    station.setArea_id(area_id);
                    String town_street = (String) data.get("town_street");
                    station.setTown_street(town_street);
                    String longitude = (String) data.get("longitude");
                    station.setLongitude(longitude);
                    String latitude = (String) data.get("latitude");
                    station.setLatitude(latitude);
                    String work_begin_time = (String) data.get("work_begin_time");
                    station.setWork_begin_time(work_begin_time);
                    String work_end_time = (String) data.get("work_end_time");
                    station.setWork_end_time(work_end_time);
                    String contact_name = (String) data.get("contact_name");
                    station.setContact_name(contact_name);
                    String contact_phone = (String) data.get("contact_phone");
                    station.setContact_phone(contact_phone);
                    String bank_id = (String) data.get("bank_id");
                    station.setBank_id(bank_id);
                    String bank_account = (String) data.get("bank_account");
                    station.setBank_account(bank_account);
                    String bank_account_person_name = (String) data.get("bank_account_person_name");
                    station.setBank_account_person_name(bank_account_person_name);
                    String bank_account_person_id = (String) data.get("bank_account_person_id");
                    station.setBank_account_person_id(bank_account_person_id);
                    station.setLast_update(DateUtil.getDate());
                    station.setLast_update_user_id("U:" + userName);
                    count_station = stationDaoMapper.updateStation(station);
                    if (count_station == 1) {
                        flag = 1;   // 保存成功
                    } else {
                        flag = 0;   // 保存失败
                        throw new RuntimeException(String.valueOf(flag));
                    }
                } else {
                    flag = 3; //服务站名称重复
                    throw new RuntimeException(String.valueOf(flag));
                }
            } else {
                flag = 2; // 下加盟服务站不存在
            }
        } catch (Exception e) {
            if (e.getMessage().equals("0")){
                flag = -1;
            }else if (e.getMessage().equals("2")){
                flag = 0;
            }else if (e.getMessage().equals("3")){
                flag = 3;
            }
            throw new RuntimeException(String.valueOf(flag));
        }
        return flag;
    }

    /**
     * 方法名称：findStationByQRCode
     * 内容摘要：根据线下加盟服务站ID二维码查询线下加盟服务站信息（支付）
     *
     * @param station_id 根据线下加盟服务站ID二维码
     * @return String 返回值jsons
     */
    @Transactional
    public String findStationByQRCode(String station_id, String member_name, String longitude, String latitude) {
        String json = null;
        String ecode = null;
        Map result = new HashMap();
        T_Master_Service_Station station = null;
        T_Master_Service_Station_Position serviceStation = null;
        T_Data_Member member = null;
        try {
            member = memberDaoMapper.findMemberByName(member_name);
            station = stationDaoMapper.findStationByID(station_id);
            if (member != null) {
                if (station != null) {
                    serviceStation = stationDaoMapper.findStationByIDAndLatitudeAndLongitude(station_id, latitude, longitude);
                    if (serviceStation != null) {
                        String juli = serviceStation.getJuli();//获取经纬度与线下加盟服务商距离
                        int distance =Integer.parseInt(juli);
                        if (distance < 500) {
                            // 在商户500范围内
                            ecode = "1000"; // 成功响应！
                            result.put("station_name", serviceStation.getStation_name());
                        } else {
                            ecode = "3002"; // 请在该商户附近进行消费行为！
                        }
                    } else {
                        ecode = "3002"; // 请在该商户附近进行消费行为！
                    }
                } else {
                    // 查找不到线下加盟服务站，插入错误二维码扫描记录信息表
                    T_Data_Error_Quick_Response_Code_Scan_Record errorQRCodeScanRecord = new T_Data_Error_Quick_Response_Code_Scan_Record();
                    errorQRCodeScanRecord.setMember_id(member.getRelevance_info_id());//会员ID
                    errorQRCodeScanRecord.setScan_time(DateUtil.getDate());//扫描时间
                    errorQRCodeScanRecord.setQuick_response_code_content(station_id);//二维码内容
                    double terminal_longitude = Double.parseDouble(longitude);
                    errorQRCodeScanRecord.setTerminal_longitude(terminal_longitude);//终端经度
                    double terminal_latitude = Double.parseDouble(latitude);
                    errorQRCodeScanRecord.setTerminal_latitude(terminal_latitude);//终端纬度
                    errorQRCodeScanRecord.setDelete_flag("0");//删除标识符
                    errorQRCodeScanRecord.setLast_update(DateUtil.getDate());//最终更新日
                    errorQRCodeScanRecord.setLast_update_user_id("M:" + member_name);//最终更新者ID
                    errorQuickResponseCodeScanRecordDaoMapper.saveScanRecord(errorQRCodeScanRecord);
                    ecode = "3001"; // 该二维码并不属于平台商户二维码的范畴！
                }
            } else {
                ecode = "3000"; // 用户信息不存在！
            }
        } catch (Exception e) {
            ecode = "2000"; // 系统错误
            logger.error(e.getMessage());
        } finally {
            result.put("ecode", ecode);
            json = JSONUtil.toJSONString(result);
            return json;
        }
    }
}