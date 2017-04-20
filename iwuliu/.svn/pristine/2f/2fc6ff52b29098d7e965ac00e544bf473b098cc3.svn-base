package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Alliance_Business_Management;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by QJ on 2016/12/19.
 */
public interface AllianceBusinessDaoMapper {
    // 根据条件查询线下加盟服务站列表
    public List<T_Master_Alliance_Business_Management> findStationList(HashMap<String, String> conditions);

    // 根据ID查询线下加盟服务站
    T_Master_Alliance_Business_Management findAllianceBusinessById(@Param("station_id") String station_id);
}
