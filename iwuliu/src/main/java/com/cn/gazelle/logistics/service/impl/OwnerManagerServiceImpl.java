/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OwnerManagerServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-16
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.OwnerCompanyDaoMapper;
import com.cn.gazelle.logistics.dao.OwnerDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Person;
import com.cn.gazelle.logistics.service.OwnerManagerService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.CargoTruckTypeManager_Message;
import com.cn.gazelle.logistics.util.message.ShipperManager_Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：OwnerManagerServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.OwnerManagerService", targetNamespace = "http://service.logistics.gazelle.cn.com/")

public class OwnerManagerServiceImpl implements OwnerManagerService{
    // Log初始化
    Logger logger = Logger.getLogger(OwnerManagerServiceImpl.class);
    @Resource
    private OwnerDaoMapper ownerDaoMapper;
    @Resource
    private OwnerCompanyDaoMapper ownerCompanyDaoMapper;

    @Transactional
    @Override
    public List<T_Data_Person> findOwnerList(String person_name, String id_card_number, String person_mobile_phone) {
        List<T_Data_Person> ownerList = null;
        try{
            ownerList= ownerDaoMapper.findOwnerList(person_name, id_card_number, person_mobile_phone);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ownerList;
    }

    @Override
    public int saveOwner(String parms, String username) {
        Gson gson=new Gson();
        int a=1;
        try {
            Map<String, Object> data = gson.fromJson(parms, new TypeToken<Map<String, Object>>() {
            }.getType());
            String personName = (String) data.get("personName");
            String phone = (String) data.get("phone");
            String number = (String) data.get("number");
            String sex = (String) data.get("sex");
            T_Data_Person person = new T_Data_Person();
            person.setPerson_id(UUIDUtil.getUUID());
            person.setPerson_name(personName);
            person.setPerson_mobile_phone(phone);
            person.setId_card_number(number);
            person.setSex(sex);
            person.setLast_update(DateUtil.getDate());
            person.setLast_update_user_id(username);
            a = ownerDaoMapper.saveOwner(person);
            if (a == 1) {//保存成功
                logger.info(CargoTruckTypeManager_Message.SaveCargoTruckDone);
            } else if (a == 0) {//重复
                logger.info(CargoTruckTypeManager_Message.SaveCargoTruckError);
                throw new RuntimeException();
            } else if (a == -1) {
                logger.error(CargoTruckTypeManager_Message.SaveCargoTruckError);
                throw new RuntimeException();
            }
        } catch (Exception e) {
            if (a==0){
                throw new RuntimeException("0");
            }else {
                throw new RuntimeException("-1");
            }

        }
        return a;
    }
    /**
     * 方法名称：findPersonShipperById
     * 内容摘要：根据id货主个人货主信息
     * @param person_id    货主id
     */
    @Override
    public T_Data_Person findPersonShipperById(String person_id) {
        T_Data_Person person=null;
        try {
            person= ownerDaoMapper.findPersonShipperById(person_id);
        }catch (Exception e){
            logger.error(ShipperManager_Message.seacheInfoError);
        }
        return person;
    }
}