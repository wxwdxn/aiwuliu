/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Transportation_Schedule_Sheet.Java
 * 系统编号：Z0001002
 * 系统名称：调度单信息表实体类
 * 模块编号：01
 * 模块名称：调度单信息表实体类
 * 设计文件：
 * 完成日期：2016-03-10
 * 作    者：WXW
 * 内容摘要：调度单信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Transportation_Schedule_Sheet
 * 内容摘要: 调度单信息表实体类
 * @author WXW
 */
public class T_Data_Transportation_Schedule_Sheet {
    private String scheduleSheetId;//
    private String schedule_plan_number;//所属编号
    private String belongScheduleSheetId;//所属调度单ID
    private String sendMemberId;//发出对象会员ID
    private String receiveMemberId;//接受对象会员ID
    private String selectedLineNo; //选择线路编号
    private double allocateTotal;//调度数量
    private String scheduleTruckId;//调度车辆ID
    private String createTime;//创建时间
    private String acceptTime;// 接单时间
    private String refuseTime;//拒绝时间
    private String setOffTime;//出发时间
    private double loadingCargoAmount;//装货时货物数量
    private String loadingStartTime;//装货开始时间
    private String loadingEndTime;//装货结束时间
    private String loadingProofPath;//装货磅单保存路径
    private String loadLongitude;//装货经度
    private String loadLatitude;//装货纬度
    private double unLoadingCargoAmount;//卸货时货物数量
    private String unloadingStartTime;//卸货开始时间
    private String unloadingEndTime;//卸货结束时间
    private String unloadingProofPath;//卸货磅单保存路径
    private String unloadLongitude;//装货经度
    private String unloadLatitude;//装货纬度
    private String transportUnitPrice;//运输单价


    private int  status;//调度单状态
    private String payedTime;//支付时间
    private int deleteFlag;//删除标识
    private Date lastUpdate;//最终更新日
    private String lastUpdateUserId;//最终更新者ID
    private String code;//编号

    public String getLoadLongitude() {
        return loadLongitude;
    }

    public void setLoadLongitude(String loadLongitude) {
        this.loadLongitude = loadLongitude;
    }

    public String getLoadLatitude() {
        return loadLatitude;
    }

    public void setLoadLatitude(String loadLatitude) {
        this.loadLatitude = loadLatitude;
    }

    public String getUnloadLongitude() {
        return unloadLongitude;
    }

    public void setUnloadLongitude(String unloadLongitude) {
        this.unloadLongitude = unloadLongitude;
    }

    public String getUnloadLatitude() {
        return unloadLatitude;
    }

    public void setUnloadLatitude(String unloadLatitude) {
        this.unloadLatitude = unloadLatitude;
    }


    public String getScheduleSheetId() {
        return scheduleSheetId;
    }

    public void setScheduleSheetId(String scheduleSheetId) {
        this.scheduleSheetId = scheduleSheetId;
    }

    public String getSchedule_plan_number() {
        return schedule_plan_number;
    }

    public void setSchedule_plan_number(String schedule_plan_number) {
        this.schedule_plan_number = schedule_plan_number;
    }

    public String getBelongScheduleSheetId() {
        return belongScheduleSheetId;
    }

    public void setBelongScheduleSheetId(String belongScheduleSheetId) {
        this.belongScheduleSheetId = belongScheduleSheetId;
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

    public String getSelectedLineNo() {
        return selectedLineNo;
    }

    public void setSelectedLineNo(String selectedLineNo) {
        this.selectedLineNo = selectedLineNo;
    }

    public double getAllocateTotal() {
        return allocateTotal;
    }

    public void setAllocateTotal(double allocateTotal) {
        this.allocateTotal = allocateTotal;
    }

    public String getScheduleTruckId() {
        return scheduleTruckId;
    }

    public void setScheduleTruckId(String scheduleTruckId) {
        this.scheduleTruckId = scheduleTruckId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getRefuseTime() {
        return refuseTime;
    }

    public void setRefuseTime(String refuseTime) {
        this.refuseTime = refuseTime;
    }

    public String getSetOffTime() {
        return setOffTime;
    }

    public void setSetOffTime(String setOffTime) {
        this.setOffTime = setOffTime;
    }

    public double getLoadingCargoAmount() {
        return loadingCargoAmount;
    }

    public void setLoadingCargoAmount(double loadingCargoAmount) {
        this.loadingCargoAmount = loadingCargoAmount;
    }

    public String getLoadingStartTime() {
        return loadingStartTime;
    }

    public void setLoadingStartTime(String loadingStartTime) {
        this.loadingStartTime = loadingStartTime;
    }

    public String getLoadingEndTime() {
        return loadingEndTime;
    }

    public void setLoadingEndTime(String loadingEndTime) {
        this.loadingEndTime = loadingEndTime;
    }

    public String getLoadingProofPath() {
        return loadingProofPath;
    }

    public void setLoadingProofPath(String loadingProofPath) {
        this.loadingProofPath = loadingProofPath;
    }

    public double getUnLoadingCargoAmount() {
        return unLoadingCargoAmount;
    }

    public void setUnLoadingCargoAmount(double unLoadingCargoAmount) {
        this.unLoadingCargoAmount = unLoadingCargoAmount;
    }

    public String getUnloadingStartTime() {
        return unloadingStartTime;
    }

    public void setUnloadingStartTime(String unloadingStartTime) {
        this.unloadingStartTime = unloadingStartTime;
    }

    public String getUnloadingEndTime() {
        return unloadingEndTime;
    }

    public void setUnloadingEndTime(String unloadingEndTime) {
        this.unloadingEndTime = unloadingEndTime;
    }

    public String getUnloadingProofPath() {
        return unloadingProofPath;
    }

    public void setUnloadingProofPath(String unloadingProofPath) {
        this.unloadingProofPath = unloadingProofPath;
    }

    public String getTransportUnitPrice() {
        return transportUnitPrice;
    }

    public void setTransportUnitPrice(String transportUnitPrice) {
        this.transportUnitPrice = transportUnitPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(String payedTime) {
        this.payedTime = payedTime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}