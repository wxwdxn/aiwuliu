/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Transportation_Dispatch_Sheet.Java
 * 系统编号：Z0001002
 * 系统名称：派车单信息表实体类
 * 模块编号：01
 * 模块名称：派车单信息表实体类
 * 设计文件：
 * 完成日期：2016-03-14
 * 作    者：WXW
 * 内容摘要：派车单信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Transportation_Dispatch_Sheet
 * 内容摘要: 派车单信息表实体类
 * @author WXW
 */
public class T_Data_Transportation_Dispatch_Sheet {
    private String dispatchSheetId;//id
    private String scheduleSheetId;//调度单ID
    private String sendMemberId;//发出会员id
    private String receiveMemberId;//接收会员id
    private String deleteFlag;//删除标识
    private String dispatchSheetStatus;//派车单状态
    private Date lastUpdate;
    private String lastUpdateUserId;
    private String DCode;

    public String getDispatchSheetId() {
        return dispatchSheetId;
    }

    public void setDispatchSheetId(String dispatchSheetId) {
        this.dispatchSheetId = dispatchSheetId;
    }

    public String getScheduleSheetId() {
        return scheduleSheetId;
    }

    public void setScheduleSheetId(String scheduleSheetId) {
        this.scheduleSheetId = scheduleSheetId;
    }

    public String getSendMemberId() {
        return sendMemberId;
    }

    public void setSendMemberId(String sendMemberId) {
        this.sendMemberId = sendMemberId;
    }

    public String getReceiveMemberId() {
        return receiveMemberId;
    }

    public void setReceiveMemberId(String receiveMemberId) {
        this.receiveMemberId = receiveMemberId;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDispatchSheetStatus() {
        return dispatchSheetStatus;
    }

    public void setDispatchSheetStatus(String dispatchSheetStatus) {
        this.dispatchSheetStatus = dispatchSheetStatus;
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

    public String getDCode() {
        return DCode;
    }

    public void setDCode(String DCode) {
        this.DCode = DCode;
    }

}
