package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Person;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by WXW on 2017/1/16.
 * 个人货主信息
 */
@WebService
public interface OwnerManagerService {

    // 根据多个条件条件查找个人信息 拼接
    List<T_Data_Person> findOwnerList(String person_name,String id_card_number,String person_mobile_phone);

    //保存个人货主信息
    int saveOwner(String parms,String username);

    //根据id查询个人货主
    T_Data_Person findPersonShipperById(String person_id);
}
