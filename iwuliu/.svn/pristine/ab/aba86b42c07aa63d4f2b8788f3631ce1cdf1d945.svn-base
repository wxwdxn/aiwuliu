/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TruckTransportLineServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆常跑路线查询接口实现
 * 设计文件：
 * 完成日期：2016-03-31
 * 作    者：QJ
 * 内容摘要：车辆常跑路线查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DicdataDaoMapper;
import com.cn.gazelle.logistics.dao.TruckDaoMapper;
import com.cn.gazelle.logistics.dao.TruckTransportLineDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Truck;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Transport_Line;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.TruckTransportLineService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.TruckTransportLineManager_Message;
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

/**
 * 类 名 称: TruckTransportLineServiceImpl
 * 内容摘要: 车辆常跑路线查询接口
 * 方法描述：该类有10个方法：
 *         01 findTruckTransportByLineID            根据常跑路线ID查找车辆常跑路线信息
 *         02 findTruckTransportByTruckID           根据卡车ID查询车辆常跑路线信息列
 *         03 findAllTruckTransportLine             查询符合条件的车辆常跑路线列表信息（默认查询所有车辆常跑路线）
 *         04 findAllTruckTransportLineRowsCount    查询车辆常跑路线记录数(包含有条件和无条件)
 *         05 saveTruckTransportLine                增加车辆常跑路线信息
 *         06 updateTruckTransportLine              更新车辆常跑路线信息
 *         07 delTruckTransportLine                 删除车辆常跑路线信息
 *         08 searchTruckTransportLine              检索车辆常跑路线
 *         09 addTruckTransportLine                 添加车辆常跑路线
 *         10 delTruckTransportLineByID             通过ID删除车辆常跑路线
 *         11 findTruckTransportByTruckIDAndCity    根据卡车ID、起点终点城市查询车辆常跑路线信息
 *
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TruckTransportLineService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TruckTransportLineServiceImpl implements TruckTransportLineService {
    public static String code;                                              // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(TruckTransportLineServiceImpl.class);

    @Resource
    private TruckTransportLineDaoMapper truckTransportLineDaoMapper;        // 数据访问层
    @Resource
    private TruckDaoMapper truckDaoMapper;                                  // 数据访问层
    @Resource
    private DicdataDaoMapper dicdataDaoMapper;                              // 数据访问层

    /**
     * 方法名称：findTruckTransportByLineID
     * 内容摘要：根据常跑路线ID查找车辆常跑路线信息
     * @param line_id 车辆常跑路线id
     * @return T_Data_Truck_Transport_Line 车辆常跑路线信息
     */
    public String findTruckTransportByLineID(String line_id) {
        T_Data_Truck_Transport_Line truckTransportLine = null;
        String str = null;
        try {
            truckTransportLine = this.truckTransportLineDaoMapper.findTruckTransportByLineID(line_id);
            str = JSONUtil.toJSONString(truckTransportLine);
            logger.info(TruckTransportLineManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckTransportLineManager_Message.searchErr + e.getMessage());
        }
        return str;
    }

    /**
     * 方法名称：findTruckTransportByTruckID
     * 内容摘要：根据卡车ID查询车辆常跑路线信息列
     * @param truck_id 卡车ID
     * @return List<T_Data_Truck_Transport_Line> list 车辆常跑路线信息
     */
    public List<T_Data_Truck_Transport_Line> findTruckTransportByTruckID(String truck_id) {
        List<T_Data_Truck_Transport_Line> list = null;
        try {
            list = this.truckTransportLineDaoMapper.findTruckTransportByTruckID(truck_id);
            logger.info(TruckTransportLineManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckTransportLineManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findAllTruckTransportLine
     * 内容摘要：查询符合条件的车辆常跑路线列表信息（默认查询所有车辆常跑路线）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @param page 页面页数
     * @param rows 页面显示条数
     * @return List<T_Data_Truck_Transport_Line>  车辆常跑路线信息列
     */
    public List<T_Data_Truck_Transport_Line> findAllTruckTransportLine(String search_type, String name, Integer page, Integer rows) {
        List<T_Data_Truck_Transport_Line> list = null;
        try {
            list = this.truckTransportLineDaoMapper.findAllTruckTransportLine(search_type, name, page, rows);
            logger.info(TruckTransportLineManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckTransportLineManager_Message.searchErr + e.getMessage());
        }
        return list;
    }


    /**
     * 方法名称：findAllTruckTransportLineRowsCount
     * 内容摘要：查询符合条件的车辆常跑路线列表信息（默认查询所有车辆常跑路线）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @return Integer  车辆常跑路线信息记录数
     */
    public Integer findAllTruckTransportLineRowsCount(String search_type, String name) {
        int c = 0;
        try {
            c = this.truckTransportLineDaoMapper.findAllTruckTransportLineRowsCount(search_type, name);
        } catch (Exception e) {
            logger.error(TruckTransportLineManager_Message.getSelectTruckTransportLineCountError + e.getMessage());
        }
        return c;
    }

    /**
     * 方法名称：saveTruckTransportLine
     * 内容摘要：增加车辆常跑路线信息
     * @param truckTransportLine 车辆常跑路线
     */
    public int saveTruckTransportLine(T_Data_Truck_Transport_Line truckTransportLine) {
        int count = 0;
        try {
            count = this.truckTransportLineDaoMapper.saveTruckTransportLine(truckTransportLine);
            if (count == 1){
                logger.info(MessageUtil.saveInfo);
            }else if (count ==0){
                logger.info(MessageUtil.saveDoubleInfoError);
            }
        } catch (Exception e) {
            count = -1;
            logger.error(TruckTransportLineManager_Message.saveErr + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：updateTruckTransportLine
     * 内容摘要：更新车辆常跑路线信息
     * @param truckTransportLine 车辆常跑路线
     */
    public boolean updateTruckTransportLine(T_Data_Truck_Transport_Line truckTransportLine) {
        boolean b = new Boolean(true);
        try {
            this.truckTransportLineDaoMapper.updateTruckTransportLine(truckTransportLine);
            logger.info(TruckTransportLineManager_Message.updataDone);
        } catch (Exception e) {
            b = false;
            logger.error(TruckTransportLineManager_Message.updataErr + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delTruckTransportLine
     * 内容摘要：删除车辆常跑路线信息
     * @param line_id 车辆常跑路线id
     */
    public void delTruckTransportLine(String line_id) {
        try {
            this.truckTransportLineDaoMapper.delTruckTransportLine(line_id);
            logger.info(TruckTransportLineManager_Message.delDone);
        } catch (Exception e) {
            logger.error(TruckTransportLineManager_Message.delErr + e.getMessage());
        }
    }


    /**
     * 方法名称：searchTruckTransportLine
     * 内容摘要：检索车辆常跑路线
     * @param member_name   账号
     * @param plate_number  车牌号
     * @return String 返回值json
     */
    public String searchTruckTransportLine(String member_name, String plate_number) {
        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = null;  // 获取车辆
        List<T_Data_Truck_Transport_Line> list = null;
        try {
            truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
            list = this.truckTransportLineDaoMapper.findTruckTransportByTruckID(truck.getTruck_id());
            List<Map<String, String>> lists = new ArrayList<Map<String, String>>(); // 列表数据
            for (T_Data_Truck_Transport_Line line : list) {
                Map<String, String> results = new HashMap<String, String>();

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

                results.put("line_id", line.getLine_id());
                results.put("start_province_id", line.getStart_province_id());
                results.put("start_city_id", line.getStart_city_id());
                results.put("end_province_id", line.getEnd_province_id());
                results.put("end_city_id", line.getEnd_city_id());
                lists.add(results);
            }
            ecode = "1000"; // 成功响应！
            result.put("ecode", ecode);
            result.put("object1", lists);
            logger.info(TruckTransportLineManager_Message.searchDone);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckTransportLineManager_Message.searchErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：addTruckTransportLine
     * 内容摘要：添加车辆常跑路线
     * @param member_name   用户名
     * @param plate_number  车牌号
     * @param lineStringObject  常跑路线
     * @return String 返回值json
     */
    public String addTruckTransportLine(String member_name, String plate_number, String lineStringObject) {
        T_Data_Truck_Transport_Line line = null;
        if (lineStringObject != null && lineStringObject.trim().length() > 0) {
            line = new Gson().fromJson(lineStringObject, T_Data_Truck_Transport_Line.class);
        }
        if (line == null) {
            return null;
        }

        Map result = new HashMap();
        String json = null;
        String ecode = null;
        T_Data_Truck truck = this.truckDaoMapper.findTruckByPlateNumber(plate_number);
        int i = 1;

        try {
            // 起点省
            T_Sys_Dicdata dicdata_start_province = this.dicdataDaoMapper.findAllDicdataByID2("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getStart_province_id());
            if (dicdata_start_province != null) {
                line.setStart_province_id(dicdata_start_province.getDicdata_code());
                // 起点市
                T_Sys_Dicdata dicdata_start_city = this.dicdataDaoMapper.findDicdataByDicdataName("66029BA5DC964A15B29852FA077327C0", line.getStart_city_id(), dicdata_start_province.getDicdata_code().substring(0, 2) + "%00");
                if (dicdata_start_city != null) {
                    line.setStart_city_id(dicdata_start_city.getDicdata_code());
                } else {
//                    line.setStart_city_id(line.getStart_city_id());
                    i = 0;
                }
            }
            // 终点省
            T_Sys_Dicdata dicdata_end_province = this.dicdataDaoMapper.findAllDicdataByID2("2EE02FDAE3BE4F06B8CFDF4425BA74BF", line.getEnd_province_id());
            if (dicdata_end_province != null) {
                line.setEnd_province_id(dicdata_end_province.getDicdata_code());
                // 终点市
                T_Sys_Dicdata dicdata_end_city = this.dicdataDaoMapper.findDicdataByDicdataName("66029BA5DC964A15B29852FA077327C0", line.getEnd_city_id(), dicdata_end_province.getDicdata_code().substring(0, 2) + "%00");
                if (dicdata_end_city != null) {
                    line.setEnd_city_id(dicdata_end_city.getDicdata_code());
                } else {
//                    line.setEnd_city_id(line.getEnd_city_id());
                    i = 0;
                }
            }

            if(i == 1){
            line.setLine_id(UUIDUtil.getUUID());                // 路线id
            line.setTruck_id(truck.getTruck_id());              // 车辆ID
            line.setLast_update(DateUtil.getDate());            // 新增最终更新日
            line.setLast_update_user_id("M:" + member_name);    // 新增最终更新者
            this.truckTransportLineDaoMapper.saveTruckTransportLine(line);
                ecode = "1000";   // 响应成功
                result.put("ecode", ecode);
                logger.info(TruckTransportLineManager_Message.saveDone);
            } else {
                ecode = "3000";   // 获取城市错误
                result.put("ecode", ecode);
            }
        } catch (Exception e) {
            ecode = "2000";       // 保存失败
            result.put("ecode", ecode);
            logger.error(TruckTransportLineManager_Message.saveErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：delTruckTransportLineByID
     * 内容摘要：通过ID删除车辆常跑路线
     * @param member_name   会员名
     * @param plate_number  车牌号
     * @param line_id   要删除的车辆常跑路线ID列表
     * @return String 返回值json
     */
    public String delTruckTransportLineByID(String member_name, String plate_number, String line_id) {
        Map result = new HashMap();
        String ecode = null;
        String json = null;
        // 解析json为数组
        Gson gson = new Gson();
        try {
            if (line_id != null && line_id.trim().length() > 0) {
                // json转为带泛型的list
                List<Map<String, String>> retList = gson.fromJson(line_id, new TypeToken<List<Map<String, String>>>() {
                }.getType());
                // 删除所选车辆常跑路线
                for (Map<String, String > map : retList) {
                    String line_ids = map.get("line_id");
                    this.truckTransportLineDaoMapper.delTruckTransportLine(line_ids);
                }
            }
            ecode = "1000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.info(TruckTransportLineManager_Message.delDone);
        } catch (Exception e) {
            ecode = "2000"; // 获取数据失败！
            result.put("ecode", ecode);
            logger.error(TruckTransportLineManager_Message.delErr + e.getMessage());
        }
        json = JSONUtil.toJSONString(result);
        return json;
    }

    /**
     * 方法名称：findTruckTransportByTruckIDAndCity
     * 内容摘要：根据卡车ID、起点终点城市查询车辆常跑路线信息
     * @param truck_id 卡车ID
     * @param start_city_id 起点城市
     * @param end_city_id   终点城市
     * @return List<T_Data_Truck_Transport_Line> list 车辆常跑路线信息
     */
    public List<T_Data_Truck_Transport_Line> findTruckTransportByTruckIDAndCity(String truck_id, String start_city_id, String end_city_id){
        List<T_Data_Truck_Transport_Line> list = null;
        try {
            list = this.truckTransportLineDaoMapper.findTruckTransportByTruckIDAndCity(truck_id, start_city_id, end_city_id);
            logger.info(TruckTransportLineManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(TruckTransportLineManager_Message.searchErr + e.getMessage());
        }
        return list;
    }
}