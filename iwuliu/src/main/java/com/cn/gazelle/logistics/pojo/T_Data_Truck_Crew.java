/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Truck_Crew.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：卡车车组实体类
 * 模块名称：01
 * 设计文件：
 * 完成日期：2016-08-10
 * 作    者: QJ
 * 内容摘要：卡车车组实体类
 */
package com.cn.gazelle.logistics.pojo;

/**
 * 类 名 称：T_Data_Truck_Crew
 * 内容描述：卡车车组实体类
 *@authot QJ
 */
public class T_Data_Truck_Crew {
    private String plate_number;                    // 车牌号
    private String manager_member_id;               // 车辆管理者ID
    private int truck_crew_number;                  // 车组成员人数
    private int standby_driver_number;              // 待机司机人数
    private int driving_driver_number;              // 行驶司机人数
    private int pending_driver_number;              // 待确认司机人数

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getManager_member_id() {
        return manager_member_id;
    }

    public void setManager_member_id(String manager_member_id) {
        this.manager_member_id = manager_member_id;
    }

    public int getTruck_crew_number() {
        return truck_crew_number;
    }

    public void setTruck_crew_number(int truck_crew_number) {
        this.truck_crew_number = truck_crew_number;
    }

    public int getStandby_driver_number() {
        return standby_driver_number;
    }

    public void setStandby_driver_number(int standby_driver_number) {
        this.standby_driver_number = standby_driver_number;
    }

    public int getDriving_driver_number() {
        return driving_driver_number;
    }

    public void setDriving_driver_number(int driving_driver_number) {
        this.driving_driver_number = driving_driver_number;
    }

    public int getPending_driver_number() {
        return pending_driver_number;
    }

    public void setPending_driver_number(int pending_driver_number) {
        this.pending_driver_number = pending_driver_number;
    }
}