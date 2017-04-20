package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Service_Station_Cost_Record;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by YK on 2016/3/4.
 */
@WebService
public interface StationCostRecordService {
    //根据记录id查询消费信息
    public String findCostRecordByID(String record_id);

    //根据车辆id查询消费信息
    public T_Data_Service_Station_Cost_Record findCostRecordByTruckID(String truck_id);

    //查询所有消费信息
    public List<T_Data_Service_Station_Cost_Record> findAllCostRecord( String truck_id, Integer page,Integer rows);

    //查询消费信息总记录数
    public Integer findAllCostRecordRowsCount(String truck_id);

    //保存消费信息
    public void saveCostRecord(T_Data_Service_Station_Cost_Record record);

    //更新消费信息
    public void updateCostRecord(T_Data_Service_Station_Cost_Record record);

    //删除消费信息
    public void delCostRecord(String record_id);
}
