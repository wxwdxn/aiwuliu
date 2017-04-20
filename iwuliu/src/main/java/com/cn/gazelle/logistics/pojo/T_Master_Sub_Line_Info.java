package com.cn.gazelle.logistics.pojo;

import java.util.Date;
/**
 * 类 名 称: T_Master_Sub_Line_Info
 * 内容摘要: 干线线路实体类
 * @author YK
 */
public class T_Master_Sub_Line_Info {
    private String line_id;                            // ID

    private String operate_main_line_id;               // 运营干线ID

    private Integer line_no;                           // 路线编号

    private String remark;                             // 说明

    private double average_gas_consumption;             // 平均天然气消耗量

    private double average_diesel_fuel_consumption;     // 平均柴油消耗量

    private double average_highway_tolls;               // 平均高速过路费

    private double average_national_road_tolls;         // 平均国道过路费

    private double average_national_road_fine;          // 平均国道罚款

    private String delete_flag;                         // 删除标识符

    private Date last_update;                           // 最终更新日

    private String last_update_user_id;                  // 最终更新者ID

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getOperate_main_line_id() {
        return operate_main_line_id;
    }

    public void setOperate_main_line_id(String operate_main_line_id) {
        this.operate_main_line_id = operate_main_line_id;
    }

    public Integer getLine_no() {
        return line_no;
    }

    public void setLine_no(Integer line_no) {
        this.line_no = line_no;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getAverage_gas_consumption() {
        return average_gas_consumption;
    }

    public void setAverage_gas_consumption(double average_gas_consumption) {
        this.average_gas_consumption = average_gas_consumption;
    }

    public double getAverage_diesel_fuel_consumption() {
        return average_diesel_fuel_consumption;
    }

    public void setAverage_diesel_fuel_consumption(double average_diesel_fuel_consumption) {
        this.average_diesel_fuel_consumption = average_diesel_fuel_consumption;
    }

    public double getAverage_highway_tolls() {
        return average_highway_tolls;
    }

    public void setAverage_highway_tolls(double average_highway_tolls) {
        this.average_highway_tolls = average_highway_tolls;
    }

    public double getAverage_national_road_tolls() {
        return average_national_road_tolls;
    }

    public void setAverage_national_road_tolls(double average_national_road_tolls) {
        this.average_national_road_tolls = average_national_road_tolls;
    }

    public double getAverage_national_road_fine() {
        return average_national_road_fine;
    }

    public void setAverage_national_road_fine(double average_national_road_fine) {
        this.average_national_road_fine = average_national_road_fine;
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
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