package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.StationCostRecordDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Service_Station_Cost_Record;
import com.cn.gazelle.logistics.service.StationCostRecordService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.CostRecordManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by YK on 2016/3/4.
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.StationCostRecordService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class StationCostRecordServiceImpl implements StationCostRecordService {
    Logger logger = Logger.getLogger(StationCostRecordServiceImpl.class);

    @Resource
    private StationCostRecordDaoMapper stationCostRecordDaoMapper;

    //根据记录id查询消费信息
    public String findCostRecordByID(String record_id) {
        String str = null;
        T_Data_Service_Station_Cost_Record record = null;
        try {
            record = this.stationCostRecordDaoMapper.findCostRecordByID(record_id);
            str = JSONUtil.toJSONString(record);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return str;
    }
    //根据车辆id查询消费信息
    public T_Data_Service_Station_Cost_Record findCostRecordByTruckID(String truck_id) {
        T_Data_Service_Station_Cost_Record record = null;
        try {
            record = this.stationCostRecordDaoMapper.findCostRecordByTruckID(truck_id);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return record;
    }
    //查询所有消费信息
    public List<T_Data_Service_Station_Cost_Record> findAllCostRecord(String truck_id, Integer page, Integer rows) {
        List<T_Data_Service_Station_Cost_Record> list = null;
        try {
            list = this.stationCostRecordDaoMapper.findAllCostRecord(truck_id,page,rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return list;
    }

    //查询消费信息总记录数
    public Integer findAllCostRecordRowsCount(String truck_id) {
        int c = 0;
        try {
            c = this.stationCostRecordDaoMapper.findAllCostRecordRowsCount(truck_id);
        } catch (Exception e) {
            logger.error(CostRecordManager_Message.getSelectCostRecordCountError+e.getMessage());

        }
        return c;
    }

    //保存消费信息
    public void saveCostRecord(T_Data_Service_Station_Cost_Record record) {
        try {
            this.stationCostRecordDaoMapper.saveCostRecord(record);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError+e.getMessage());
        }
    }

    //更新消费信息
    public void updateCostRecord(T_Data_Service_Station_Cost_Record record) {
        try {
            this.stationCostRecordDaoMapper.updateCostRecord(record);
            logger.info("更新成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    //删除消费信息
    public void delCostRecord(String record_id) {
        try {
            this.stationCostRecordDaoMapper.delCostRecord(record_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError+e.getMessage());
        }

    }
}
