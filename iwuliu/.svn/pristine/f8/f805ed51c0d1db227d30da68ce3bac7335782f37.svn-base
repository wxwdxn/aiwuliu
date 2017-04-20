/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: StationDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：服务站（油气站、维修站）信息查询接口实现
 * 设计文件：
 * 完成日期：2016-02-24
 * 作    者：QJ
 * 内容摘要：油气站信息查询
 */

package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Service_Station;
import com.cn.gazelle.logistics.pojo.T_Master_Service_Station_Position;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: StationDaoMapper
 * 内容摘要: 服务站（油气站、维修站）信息管理页面
 * 方法描述：该类有13个方法：
 *         01 findStationByID                   根据油气站ID查询油气站信息
 *         02 findStationByName                 根据油气站名称查询油气站信息
 *         03 findAllStation                    查询所有油气站所有信息
 *         04 findAllStationRowsCount           查询油气站记录数
 *         05 saveStation                       保存油气站信息
 *         06 updateStation                     更新油气站信息
 *         07 examineStation                    审核油气站
 *         08 examineRepairStation              审核维修站
 *         09 delStation                        删除油气站信息
 *         10 findAllRepairStation              查询所有维修站信息
 *         11 findAllRepairStationRowsCount     查询维修站记录数
 *         12 findStationList                   查询所有油气站信息不分页
 *         13 findRepairStationList             查询所有服务站信息不分页
 * @author QJ
 */
public interface StationDaoMapper {
    // 根据油气站ID查询油气站信息
    public T_Master_Service_Station findStationByID(@Param(value = "station_id") String station_id);

    // 根据油气站名称查询油气站信息
    public T_Master_Service_Station findStationByName(@Param(value = "station_name") String station_name);

//    // 查询所有油气站所有信息
//    public List<T_Master_Service_Station> findAllStation(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);
//
//    // 查询油气站记录数
//    public Integer findAllStationRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);
//
    // 保存油气站信息
    public int saveStation(T_Master_Service_Station station);

    // 更新油气站信息
    public int updateStation(T_Master_Service_Station station);
//
//    // 审核油气站
//    public void examineStation(T_Master_Service_Station station);
//
//    // 审核维修站
//    public void examineRepairStation(T_Master_Service_Station station);
//
    // 删除油气站信息
    public void delStation(@Param(value = "station_id") String station_id);
//
//    // 查询所有维修站信息
//    public List<T_Master_Service_Station> findAllRepairStation(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);
//
//    // 查询维修站记录数
//    public Integer findAllRepairStationRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);
//
//    // 查询所有服务站信息不分页
//    public List<T_Master_Service_Station> findRepairStationList();

    // 根据ID、经纬度查找线下加盟服务商信息
    T_Master_Service_Station_Position findStationByIDAndLatitudeAndLongitude(@Param(value = "station_id") String station_id, @Param(value = "lat") String lat, @Param(value = "lng") String lng);
}