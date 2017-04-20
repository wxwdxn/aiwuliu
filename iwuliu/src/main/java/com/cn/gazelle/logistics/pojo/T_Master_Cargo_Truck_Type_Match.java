/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Master_Cargo_Truck_Type_Match.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货物车厢类型匹配
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者: WXW
 * 内容摘要：货物车厢类型匹配实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Master_Cargo_Truck_Type_Match
 * 内容描述：货物车厢类型匹配实体类
 *@authot WXW
 */
public class T_Master_Cargo_Truck_Type_Match {
    private String matchId;         // 匹配id
    private String cargoTypeId;     // 货物类型id
    private String truckTypeId;     // 货车类型id
    private  int deleteFlag;        //删除标识
    private Date lastUpdate;        //  最终更新时间
    private String lastUpdateUserId;// 最终更新者id


    public String getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(String cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(String truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }
}