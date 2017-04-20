/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: StationService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：油气站查询接口声明
 * 设计文件：
 * 完成日期：2016-03-02
 * 作    者：QJ
 * 内容摘要：油气站查询
 */

package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Alliance_Business_Management;
import com.cn.gazelle.logistics.pojo.T_Master_Service_Station;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: StationService
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
@WebService
public interface StationService {

    // 根据油气站ID查找油气站信息
    public T_Master_Service_Station findStationByID(String station_id);

    // 根据油气站名称查找油气站信息
    public T_Master_Service_Station findStationByName(String station_name);

//    // 查询符合条件的油气站列表信息（默认查询所有油气站）
//    public List<T_Master_Service_Station> findAllStation(String search_type, String name, Integer page, Integer rows);
//
//    // 查询油气站记录数(包含有条件和无条件)
//    public Integer findAllStationRowsCount(String search_type, String name);

    // 增加油气站信息
    public boolean saveStation(T_Master_Service_Station station);

    // 更新油气站信息
    public boolean updateStation(T_Master_Service_Station station);

    // 删除油气站信息
    public void delStation(String station_id);

    // 根据条件查询线下加盟服务站列表
    List<T_Master_Alliance_Business_Management> findStationList(HashMap<String, String> conditions);

    // 根据ID查询线下加盟服务站
    T_Master_Alliance_Business_Management findAllianceBusinessById(String station_id);

    // 新增线下加盟服务站
    int saveAllianceBusiness(String list, String userName);

    // 编辑线下加盟服务站
    int upDateAllianceBusiness(String list, String userName);

    // 根据线下加盟服务站ID二维码查询线下加盟服务站信息（支付）
    String findStationByQRCode(String station_id, String member_name, String longitude, String latitude);
}