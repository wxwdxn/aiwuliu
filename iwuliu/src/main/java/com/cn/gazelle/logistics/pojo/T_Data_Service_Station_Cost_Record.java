package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * Created by YK on 2016/3/4.
 */
public class T_Data_Service_Station_Cost_Record {

    private String record_id;//id
    private String service_station_id;//服务站id
    private String truck_id;//车辆id
    private String cost_item;//消费项目
    private double total;//数量
    private double unit_price;//单价
    private double cost_total;//总体费用
    private String type;//费用状态
    private double platform_advanced_cost;//平台垫付金额
    private Date cost_time;//消费时间
    private Date repay_time;//偿还时间
    private double repay_total;//偿还金额
    private Date last_update;//最终更新日
    private String last_update_user_id;//最终更新者id

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getService_station_id() {
        return service_station_id;
    }

    public void setService_station_id(String service_station_id) {
        this.service_station_id = service_station_id;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public String getCost_item() {
        return cost_item;
    }

    public void setCost_item(String cost_item) {
        this.cost_item = cost_item;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getCost_total() {
        return cost_total;
    }

    public void setCost_total(double cost_total) {
        this.cost_total = cost_total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPlatform_advanced_cost() {
        return platform_advanced_cost;
    }

    public void setPlatform_advanced_cost(double platform_advanced_cost) {
        this.platform_advanced_cost = platform_advanced_cost;
    }

    public Date getCost_time() {
        return cost_time;
    }

    public void setCost_time(Date cost_time) {
        this.cost_time = cost_time;
    }

    public Date getRepay_time() {
        return repay_time;
    }

    public void setRepay_time(Date repay_time) {
        this.repay_time = repay_time;
    }

    public double getRepay_total() {
        return repay_total;
    }

    public void setRepay_total(double repay_total) {
        this.repay_total = repay_total;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getLast_update_user_id() {
        return last_update_user_id;
    }

    public void setLast_update_user_id(String last_update_user_id) {
        this.last_update_user_id = last_update_user_id;
    }
}
