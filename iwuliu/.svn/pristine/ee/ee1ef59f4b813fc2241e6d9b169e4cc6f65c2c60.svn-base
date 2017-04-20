package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WXW on 2017/1/16.
 * 个人货主信息
 */
public interface OwnerDaoMapper {
    // 根据多个条件条件查找个人货主信息 拼接
    List<T_Data_Person> findOwnerList(
            @Param(value = "person_name") String person_name,
            @Param(value = "id_card_number") String id_card_number,
            @Param(value = "person_mobile_phone") String person_mobile_phone);


    //保存个人货主
    int saveOwner(T_Data_Person person);

    //根据id查询个人货主
    T_Data_Person findPersonShipperById(@Param(value = "person_id") String person_id);

}
