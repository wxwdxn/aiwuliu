/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Data_Driving_Record.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：车辆驾驶记录信息管理页面
 * 设计文件：
 * 完成日期：2016-05-13
 * 作    者: QJ
 * 内容摘要：车辆驾驶记录信息实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Data_Driving_Record
 * 内容描述：车辆驾驶记录信息实体类
 * @author QJ
 */
public class T_Data_Driving_Record {
    private String record_id;                       // 车辆驾驶记录id
    private String truck_id;                        // 车辆id
    private String person_info_id;                  // 个人信息id
    private String start_driving_time;              // 开始驾驶时间
    private String finish_driving_time;             // 结束驾驶时间
    private int delete_flag;                        // 删除标识符
    private Date last_update;                       // 最终更新日
    private String last_update_user_id;             // 最终更新者ID

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public String getPerson_info_id() {
        return person_info_id;
    }

    public void setPerson_info_id(String person_info_id) {
        this.person_info_id = person_info_id;
    }

    public String getStart_driving_time() {
        return start_driving_time;
    }

    public void setStart_driving_time(String start_driving_time) {
        this.start_driving_time = start_driving_time;
    }

    public String getFinish_driving_time() {
        return finish_driving_time;
    }

    public void setFinish_driving_time(String finish_driving_time) {
        this.finish_driving_time = finish_driving_time;
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