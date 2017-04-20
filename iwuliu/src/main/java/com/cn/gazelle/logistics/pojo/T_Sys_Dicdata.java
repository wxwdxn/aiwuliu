/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Sys_Dicdata.Java
 * 系统编号：Z0001002
 * 系统名称：字典数据实体类
 * 模块编号：01
 * 模块名称：数据字典管理页面
 * 设计文件：
 * 完成日期：2016-01-20
 * 作    者：YK
 * 内容摘要：字典数据实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Sys_Dicdata
 * 内容摘要: 字典数据实体类
 * @author YK
 */
public class T_Sys_Dicdata {
    private String dicdata_id;              //字典数据编码
    private String dictionary_type;         //字典类型
    private String dictionary_id;           //字典编码
    private String dicdata_name;            //字典名称
    private String dicdata_code;            //字典代码值
    private Date dicdata_date;              //添加日期
    private String delete_states;           //删除状态
    private String dicdata_remark;          //备注

    public String getDictionary_id() {
        return dictionary_id;
    }

    public void setDictionary_id(String dictionary_id) {
        this.dictionary_id = dictionary_id;
    }

    public String getDictionary_type() {
        return dictionary_type;
    }

    public void setDictionary_type(String dictionary_type) {
        this.dictionary_type = dictionary_type;
    }

    public String getDicdata_id() {
        return dicdata_id;
    }

    public void setDicdata_id(String dicdata_id) {
        this.dicdata_id = dicdata_id;
    }

    public String getDicdata_name() {
        return dicdata_name;
    }

    public void setDicdata_name(String dicdata_name) {
        this.dicdata_name = dicdata_name;
    }

    public String getDicdata_code() {
        return dicdata_code;
    }

    public void setDicdata_code(String dicdata_code) {
        this.dicdata_code = dicdata_code;
    }

    public Date getDicdata_date() {
        return dicdata_date;
    }

    public void setDicdata_date(Date dicdata_date) {
        this.dicdata_date = dicdata_date;
    }

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getDicdata_remark() {
        return dicdata_remark;
    }

    public void setDicdata_remark(String dicdata_remark) {
        this.dicdata_remark = dicdata_remark;
    }
}
