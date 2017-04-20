/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：WorkConditionDetailServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-06
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.WorkConditionDetailDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Work_Condition_Detail;
import com.cn.gazelle.logistics.service.WorkConditionDetailService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.base.ConditionTypeUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;

/**
 * 类 名 称：WorkConditionDetailServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01 saveWorkConditionDetail                        保存卡车工况明细信息
 *
 * @authot YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.WorkConditionDetailService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class WorkConditionDetailServiceImpl implements WorkConditionDetailService {
    // Log初始化
    Logger logger = Logger.getLogger(WorkConditionDetailServiceImpl.class);
    @Resource
    private WorkConditionDetailDaoMapper workConditionDetailDaoMapper;  // 数据访问层

    /**
     * 方法名称：saveWorkConditionDetail
     * 内容摘要：保存卡车工况明细信息
     *
     * @param work_condition_detail 车工况明细信息
     */
    public boolean saveWorkConditionDetail(T_Data_Work_Condition_Detail work_condition_detail) {
        boolean a = new Boolean(true);
        try {
            this.workConditionDetailDaoMapper.saveWorkConditionDetail(work_condition_detail);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            a = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return a;
    }

    /**
     * 方法名称：updateWorkConditionDetail
     * 内容摘要：更新卡车工况明细信息
     *
     * @param work_condition_detail 车工况明细信息
     */
    public boolean updateWorkConditionDetail(T_Data_Work_Condition_Detail work_condition_detail) {
        boolean b = new Boolean(true);
        try {
            this.workConditionDetailDaoMapper.updateWorkConditionDetail(work_condition_detail);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delWorkConditionDetail
     * 内容摘要：删除卡车工况明细信息
     *
     * @param work_condition_number work_condition_number
     */
    public boolean delWorkConditionDetail(String work_condition_number) {
        boolean b = new Boolean(true);
        try {
            this.workConditionDetailDaoMapper.delWorkConditionDetail(work_condition_number);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：queryWorkConditionDetailOfDriving
     * 内容摘要：查询卡车工况详细信息（驾驶行为分析使用）
     *
     * @param obd_id obd_id
     */
    public HashMap<String, String> queryWorkConditionDetailOfDriving(String obd_id, String start_time, String end_time) {
        HashMap<String, String> result = new HashMap<String, String>();
        logger.info("obd_id="+obd_id);
        logger.info("start_time="+start_time);
        logger.info("end_time="+end_time);
        int over_speed_count = 0;                   // 超速次数
        int rapid_acceleration_count = 0;           // 急加速次数
        int rapid_deceleration_count = 0;           // 急减速次数
        int high_engine_speed_count = 0;            // 转速过高次数
        int over_speed_duration = 0;                // 超速时长
        int fatigue_driving_count = 0;              // 疲劳驾驶提醒次数
        int idling_count = 0;                       // 怠速次数
        int idling_duration = 0;                    // 怠速时长
        int turns_count = 0;                        // 急转弯次数
        int trip_count = 0;                         // 空挡滑行次数
        int trip_duration = 0;                      // 空挡滑行时长
        try {
            over_speed_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.overSpeedDuration, obd_id, start_time, end_time);
            rapid_acceleration_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.rapidAcceleration, obd_id, start_time, end_time);
            rapid_deceleration_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.rapidDeceleration, obd_id, start_time, end_time);
            high_engine_speed_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.highEngineSpeed, obd_id, start_time, end_time);
            over_speed_duration = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailSum(ConditionTypeUtil.overSpeedDuration, obd_id, start_time, end_time);
            fatigue_driving_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.fatigueDriving, obd_id, start_time, end_time);
            idling_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.idlingDuration, obd_id, start_time, end_time);
            idling_duration = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailSum(ConditionTypeUtil.idlingDuration, obd_id, start_time, end_time);
            turns_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.idlingDuration, obd_id, start_time, end_time);
            trip_count = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailCount(ConditionTypeUtil.tripDurationSum, obd_id, start_time, end_time);
            trip_duration = this.workConditionDetailDaoMapper
                    .queryWorkConditionDetailSum(ConditionTypeUtil.tripDurationSum, obd_id, start_time, end_time);
            result.put("over_speed_count", over_speed_count + "");
            result.put("rapid_acceleration_count", rapid_acceleration_count + "");
            result.put("rapid_deceleration_count", rapid_deceleration_count + "");
            result.put("high_engine_speed_count", high_engine_speed_count + "");
            result.put("over_speed_duration", over_speed_duration + "");
            result.put("fatigue_driving_count", fatigue_driving_count + "");
            result.put("idling_count", idling_count + "");
            result.put("idling_duration", idling_duration + "");
            result.put("turns_count", turns_count + "");
            result.put("trip_count", trip_count + "");
            result.put("trip_duration", trip_duration + "");
            logger.info(MessageUtil.seacheInfo);
            logger.info(JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return result;
    }

}
