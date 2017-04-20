package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Member_Login_Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by YK on 2016/2/22.
 */
public interface MemberLoginRecordDaoMapper {
    //根据履历id查询会员登录记录信息
    public T_Data_Member_Login_Record findRecordByID(@Param(value = "record_id") String record_id);

    //根据会员名查询会员信息
    public T_Data_Member_Login_Record findRecordByName(@Param(value = "record_name")String record_name);

    //查询所有会员登录记录信息
    public List<T_Data_Member_Login_Record> findAllRecord(@Param("record_name") String record_name, @Param("page") Integer page, @Param("rows") Integer rows);

    //查询会员登录记录总记录数
    public Integer findAllRecordRowsCount(@Param(value = "record_name") String record_name);

    //保存会员登录记录信息
    public void saveRecord(T_Data_Member_Login_Record record);

    //更新会员登录记录信息
    public void updateRecord(T_Data_Member_Login_Record record);

    //删除会员登录记录信息
    public void delRecord(@Param(value = "record_id") String record_id);

}
