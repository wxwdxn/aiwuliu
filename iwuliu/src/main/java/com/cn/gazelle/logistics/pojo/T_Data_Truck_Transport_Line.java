/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Truck_Transport_Line.Java
 * 系统编号：Z0001002
 * 系统名称：常跑路线信息实体类
 * 模块编号：01
 * 模块名称：常跑路线信息管理页面
 * 设计文件：
 * 完成日期：2016-03-20
 * 作    者：QJ
 * 内容摘要：常跑路线信息实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Truck_Transport_Line
 * 内容摘要: 常跑路线信息实体类
 * @author QJ
 */
public class T_Data_Truck_Transport_Line {
    private String line_id;                 // ID
    private String truck_id;                // 卡车ID
    private String start_province_id;       // 起点省份ID
    private String start_city_id;           // 起点城市ID
    private String end_province_id;         // 终点省份ID
    private String end_city_id;             // 重点城市ID
    private int delete_flag;                        // 删除标识符
    private Date last_update;               // 最终更新日
    private String last_update_user_id;     // 最终更新者ID

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public String getStart_province_id() {
        return start_province_id;
    }

    public void setStart_province_id(String start_province_id) {
        this.start_province_id = start_province_id;
    }

    public String getStart_city_id() {
        return start_city_id;
    }

    public void setStart_city_id(String start_city_id) {
        this.start_city_id = start_city_id;
    }

    public String getEnd_province_id() {
        return end_province_id;
    }

    public void setEnd_province_id(String end_province_id) {
        this.end_province_id = end_province_id;
    }

    public String getEnd_city_id() {
        return end_city_id;
    }

    public void setEnd_city_id(String end_city_id) {
        this.end_city_id = end_city_id;
    }

    public int getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(int delete_flag) {
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
