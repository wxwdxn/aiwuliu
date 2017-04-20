/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Sys_Dictionary.Java
 * 系统编号：Z0001002
 * 系统名称：字典实体类
 * 模块编号：01
 * 模块名称：数据字典页面
 * 设计文件：
 * 完成日期：2016-01-18
 * 作    者：YK
 * 内容摘要：字典实体类
 */

package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Sys_Dictionary
 * 内容摘要: 字典实体类
 * @author YK
 */
public class T_Sys_Dictionary {

    private String dictionary_id;           // 字典编码
    private String dictionary_type;         // 字典类型
    private Date dictionary_date;           // 字典添加日期
    private String delete_states;           // 删除状态
    private String dictionary_remark;       // 备注

    public String getDelete_states() {
        return delete_states;
    }

    public void setDelete_states(String delete_states) {
        this.delete_states = delete_states;
    }

    public String getDictionary_remark() {
        return dictionary_remark;
    }

    public void setDictionary_remark(String dictionary_remark) {
        this.dictionary_remark = dictionary_remark;
    }

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

    public Date getDictionary_date() {
        return dictionary_date;
    }

    public void setDictionary_date(Date dictionary_date) {
        this.dictionary_date = dictionary_date;
    }
}
