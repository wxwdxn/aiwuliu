/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Master_Service_Station_Position.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-28
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

/**
 * 类 名 称：T_Master_Service_Station_Position
 * 内容描述：线下加盟商距离实体类
 * 方法描述：该类有 个方法
 *          01 
 *@authot QJ
 */
public class T_Master_Service_Station_Position {
    private String station_name;            // 服务站名
    private String longitude;               // 经度
    private String latitude;                // 纬度
    private String juli;                    // 距离

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getJuli() {
        return juli;
    }

    public void setJuli(String juli) {
        this.juli = juli;
    }
}