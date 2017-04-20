package com.cn.gazelle.logistics.service;

/**
 * Created by YK on 2016/2/22.
 */

import com.cn.gazelle.logistics.pojo.T_Data_Member_Login_Record;

import java.util.List;

public interface MemberLoginRecordService {
    //根据履历id查询会员登录记录信息
    public String findRecordByID( String record_id);

    //根据履历名查询会员登录记录信息
    public T_Data_Member_Login_Record findRecordByName(String record_name);

    //查询所有会员登录记录信息
    public List<T_Data_Member_Login_Record> findAllRecord(String record_name,Integer page,  Integer rows);

    //查询会员登录记录总记录数
    public Integer findAllRecordRowsCount( String record_name);

    //保存会员登录记录信息
    public void saveRecord(T_Data_Member_Login_Record record);

    //更新会员登录记录信息
    public void updateRecord(T_Data_Member_Login_Record record);

    //删除会员登录记录信息
    public void delRecord(String record_id);
}
