/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DrivingRecordServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆驾驶记录信息查询接口实现
 * 设计文件：
 * 完成日期：2016-05-17
 * 作    者：QJ
 * 内容摘要：车辆驾驶记录信息查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.DrivingRecordDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Driving_Record;
import com.cn.gazelle.logistics.service.DrivingRecordService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.DrivingRecordManager_Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 类 名 称: DrivingRecordServiceImpl
 * 内容摘要: 车辆驾驶记录信息查询接口
 * 方法描述：该类有7个方法：
 *         01 findDrivingRecordByID            根据车辆驾驶记录ID查找车辆驾驶记录信息
 *         02 findDrivingRecordByTruckID       根据卡车ID查询车辆驾驶记录信息列
 *         03 findAllDrivingRecord             查询符合条件的车辆驾驶记录列表信息（默认查询所有车辆驾驶记录）
 *         04 findAllDrivingRecordRowsCount    查询车辆驾驶记录记录数(包含有条件和无条件)
 *         05 saveDrivingRecord                增加车辆驾驶记录信息
 *         06 updateDrivingRecord              更新车辆驾驶记录信息
 *         07 delDrivingRecord                 删除车辆驾驶记录信息
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.DrivingRecordService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class DrivingRecordServiceImpl implements DrivingRecordService {
    public static String code;                                              // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(DrivingRecordServiceImpl.class);

    @Resource
    private DrivingRecordDaoMapper drivingRecordDaoMapper;        // 数据访问层

    /**
     * 方法名称：findDrivingRecordByID
     * 内容摘要：根据车辆驾驶记录ID查找车辆驾驶记录信息
     * @param record_id 车辆驾驶记录id
     * @return T_Data_Driving_Record 车辆驾驶记录信息
     */
    public String findDrivingRecordByID(String record_id) {
        T_Data_Driving_Record drivingRecord = null;
        String str = null;
        try {
            drivingRecord = this.drivingRecordDaoMapper.findDrivingRecordByID(record_id);
            str = JSONUtil.toJSONString(drivingRecord);
            logger.info(DrivingRecordManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(DrivingRecordManager_Message.searchErr + e.getMessage());
        }
        return str;
    }

    /**
     * 方法名称：findDrivingRecordByTruckID
     * 内容摘要：根据卡车ID查询车辆驾驶记录信息列
     * @param truck_id 卡车ID
     * @return List<T_Data_Driving_Record> list 车辆驾驶记录信息
     */
    public List<T_Data_Driving_Record> findDrivingRecordByTruckID(String truck_id) {
        List<T_Data_Driving_Record> list = null;
        try {
            list = this.drivingRecordDaoMapper.findDrivingRecordByTruckID(truck_id);
            logger.info(DrivingRecordManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(DrivingRecordManager_Message.searchErr + e.getMessage());
        }
        return list;
    }

    /**
     * 方法名称：findAllDrivingRecord
     * 内容摘要：查询符合条件的车辆驾驶记录列表信息（默认查询所有车辆驾驶记录）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @param page 页面页数
     * @param rows 页面显示条数
     * @return List<T_Data_Driving_Record>  车辆驾驶记录信息列
     */
    public List<T_Data_Driving_Record> findAllDrivingRecord(String search_type, String name, Integer page, Integer rows) {
        List<T_Data_Driving_Record> list = null;
        try {
            list = this.drivingRecordDaoMapper.findAllDrivingRecord(search_type, name, page, rows);
            logger.info(DrivingRecordManager_Message.searchDone);
        } catch (Exception e) {
            logger.error(DrivingRecordManager_Message.searchErr + e.getMessage());
        }
        return list;
    }


    /**
     * 方法名称：findAllDrivingRecordRowsCount
     * 内容摘要：查询符合条件的车辆驾驶记录列表信息（默认查询所有车辆驾驶记录）
     * @param search_type 搜索类型
     * @param name 查询类型
     * @return Integer  车辆驾驶记录信息记录数
     */
    public Integer findAllDrivingRecordRowsCount(String search_type, String name) {
        int c = 0;
        try {
            c = this.drivingRecordDaoMapper.findAllDrivingRecordRowsCount(search_type, name);
        } catch (Exception e) {
            logger.error(DrivingRecordManager_Message.getSelectDrivingRecordCountError + e.getMessage());
        }
        return c;
    }

    /**
     * 方法名称：saveDrivingRecord
     * 内容摘要：增加车辆驾驶记录信息
     * @param drivingRecord 车辆驾驶记录
     */
    public boolean saveDrivingRecord(T_Data_Driving_Record drivingRecord) {
        boolean b = new Boolean(true);
        try {
            this.drivingRecordDaoMapper.saveDrivingRecord(drivingRecord);
            logger.info(DrivingRecordManager_Message.saveDone);
        } catch (Exception e) {
            b = false;
            logger.error(DrivingRecordManager_Message.saveErr + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateDrivingRecord
     * 内容摘要：更新车辆驾驶记录信息
     * @param drivingRecord 车辆驾驶记录
     */
    public boolean updateDrivingRecord(T_Data_Driving_Record drivingRecord) {
        boolean b = new Boolean(true);
        try {
            this.drivingRecordDaoMapper.updateDrivingRecord(drivingRecord);
            logger.info(DrivingRecordManager_Message.updataDone);
        } catch (Exception e) {
            b = false;
            logger.error(DrivingRecordManager_Message.updataErr + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delDrivingRecord
     * 内容摘要：删除车辆驾驶记录信息
     * @param record_id 车辆驾驶记录id
     */
    public void delDrivingRecord(String record_id) {
        try {
            this.drivingRecordDaoMapper.delDrivingRecord(record_id);
            logger.info(DrivingRecordManager_Message.delDone);
        } catch (Exception e) {
            logger.error(DrivingRecordManager_Message.delErr + e.getMessage());
        }
    }
}