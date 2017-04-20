package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 描述:错误二维码扫描记录信息实体类
 * Created by zf on 2016/12/20.
 */
public class T_Data_Error_Quick_Response_Code_Scan_Record {
    private String record_number;                    //记录编号
    private String member_id;                        //会员ID
    private Date scan_time;                          //扫描时间
    private String quick_response_code_content;      //二维码内容
    private Double terminal_longitude;               //终端经度
    private Double terminal_latitude;                //终端纬度
    private String analysis_result;                  //分析结果
    private String delete_flag;                      //删除标识符
    private Date last_update;                        //最终更新日
    private String last_update_user_id;              //最终更新者ID

    public String getRecord_number() {
        return record_number;
    }

    public void setRecord_number(String record_number) {
        this.record_number = record_number;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public Date getScan_time() {
        return scan_time;
    }

    public void setScan_time(Date scan_time) {
        this.scan_time = scan_time;
    }

    public String getQuick_response_code_content() {
        return quick_response_code_content;
    }

    public void setQuick_response_code_content(String quick_response_code_content) {
        this.quick_response_code_content = quick_response_code_content;
    }

    public Double getTerminal_longitude() {
        return terminal_longitude;
    }

    public void setTerminal_longitude(Double terminal_longitude) {
        this.terminal_longitude = terminal_longitude;
    }

    public Double getTerminal_latitude() {
        return terminal_latitude;
    }

    public void setTerminal_latitude(Double terminal_latitude) {
        this.terminal_latitude = terminal_latitude;
    }

    public String getAnalysis_result() {
        return analysis_result;
    }

    public void setAnalysis_result(String analysis_result) {
        this.analysis_result = analysis_result;
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
