/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Master_Operate_Main_Line.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：运营干线基础信息实体类
 * 设计文件：
 * 完成日期：2016-06-17
 * 作    者: QJ
 * 内容摘要：运营干线基础信息实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Master_Operate_Main_Line
 * 内容摘要：运营干线基础信息实体类
 * @author QJ
 */
public class T_Master_Operate_Main_Line {
    private String operate_main_line_id;// ID
    private String operate_main_line_name;// 路线简称
    private String start_province_id;// 起点省ID
    private String start_city_id;// 起点城市ID
    private String finish_province_id;// 终点省ID
    private String finish_city_id;// 终点城市ID
    private int delete_flag;// 删除标识符
    private Date last_update;// 最终更新日
    private String last_update_user_id;// 最终更新者ID

    public String getOperate_main_line_id() {
        return operate_main_line_id;
    }

    public void setOperate_main_line_id(String operate_main_line_id) {
        this.operate_main_line_id = operate_main_line_id;
    }

    public String getOperate_main_line_name() {
        return operate_main_line_name;
    }

    public void setOperate_main_line_name(String operate_main_line_name) {
        this.operate_main_line_name = operate_main_line_name;
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

    public String getFinish_province_id() {
        return finish_province_id;
    }

    public void setFinish_province_id(String finish_province_id) {
        this.finish_province_id = finish_province_id;
    }

    public String getFinish_city_id() {
        return finish_city_id;
    }

    public void setFinish_city_id(String finish_city_id) {
        this.finish_city_id = finish_city_id;
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