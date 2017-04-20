/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Transportation_Schedule_Sheet_Plan_Dummy.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-30
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Transportation_Schedule_Sheet_Plan
 * 内容描述：货物运输计划调度单信息表
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
public class T_Data_Transportation_Schedule_Sheet_Plan {
    private String planNumber;//计划编号
    private String schedule_plan_number;//所属编号
    private String scheduleTruckId;//调度车辆ID
    private Date beginScheduleTime;//派发时间
    private int  status;//调度单状态'0：未查看，1：已查看，2：已接受，3：已拒绝（此状态当前不考虑）
    private int deleteFlag;//删除标识
    private Date lastUpdate;//最终更新日
    private String lastUpdateUserId;//最终更新者ID
    public Date getBeginScheduleTime() {
        return beginScheduleTime;
    }

    public void setBeginScheduleTime(Date beginScheduleTime) {
        this.beginScheduleTime = beginScheduleTime;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getSchedule_plan_number() {
        return schedule_plan_number;
    }

    public void setSchedule_plan_number(String schedule_plan_number) {
        this.schedule_plan_number = schedule_plan_number;
    }

    public String getScheduleTruckId() {
        return scheduleTruckId;
    }

    public void setScheduleTruckId(String scheduleTruckId) {
        this.scheduleTruckId = scheduleTruckId;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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