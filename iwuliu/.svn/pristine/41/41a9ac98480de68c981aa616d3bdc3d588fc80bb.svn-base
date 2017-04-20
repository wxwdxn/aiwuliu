package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Service_Station_Cost_Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by YK on 2016/3/4.
 */
public interface StationCostRecordDaoMapper {
    //根据记录id查询消费信息
    public T_Data_Service_Station_Cost_Record findCostRecordByID(@Param(value = "record_id") String record_id);

    //根据车辆id查询消费信息
    public T_Data_Service_Station_Cost_Record findCostRecordByTruckID(@Param(value = "truck_id")String truck_id);

    //查询所有消费信息
    public List<T_Data_Service_Station_Cost_Record> findAllCostRecord(@Param(value ="truck_id") String truck_id, @Param("page") Integer page, @Param("rows") Integer rows);

    //查询消费信息总记录数
    public Integer findAllCostRecordRowsCount(@Param(value = "truck_id") String truck_id);

    //保存消费信息
    public void saveCostRecord(T_Data_Service_Station_Cost_Record record);

    //更新消费信息
    public void updateCostRecord(T_Data_Service_Station_Cost_Record record);

    //删除消费信息
    public void delCostRecord(@Param(value = "record_id") String record_id);
}
