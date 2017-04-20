package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * Created by zf on 2016/12/7.
 */
public class T_Data_Vercode {
    private String  vercode_id;  //短信验证码ID
    private String  member_name; //用户名
    private String  mobile_phone; //手机号
    private String  ver_code; //短信验证码
    private String  delete_flag; //删除标识符
    private Date post_time; //验证码发送时间

    public String getVercode_id() {
        return vercode_id;
    }

    public void setVercode_id(String vercode_id) {
        this.vercode_id = vercode_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getVer_code() {
        return ver_code;
    }

    public void setVer_code(String ver_code) {
        this.ver_code = ver_code;
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
    }

    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }
}
