/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OwnerCompanyServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-01-17
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.OwnerCompanyDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Company;
import com.cn.gazelle.logistics.service.OwnerCompanyService;
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
 * 类 名 称：OwnerCompanyServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.OwnerCompanyService", targetNamespace = "http://service.logistics.gazelle.cn.com/")

public class OwnerCompanyServiceImpl implements OwnerCompanyService {

    // Log初始化
    Logger logger = Logger.getLogger(OwnerCompanyServiceImpl.class);

    @Resource
    private OwnerCompanyDaoMapper ownerCompanyDaoMapper;

    @Override
    @Transactional
    /**
     * 方法名称：findOwnerCompanyList
     * 内容摘要：查询公司货主信息
     * @param contact_name    姓名
     * @param company_name 公司名称
     * @param contact_mobile_phone    手机联系号码
     * @return T_Data_Person 个人信息
     */
    public List<T_Data_Company> findOwnerCompanyList(String contact_name, String company_name, String contact_mobile_phone) {
        List<T_Data_Company> ownerCompanyList =null;
        try{
            ownerCompanyList=ownerCompanyDaoMapper.findOwnerCompanyList(contact_name, company_name, contact_mobile_phone);
        }catch ( Exception e){
            logger.info(e.getMessage());
        }
        return ownerCompanyList;
    }

    @Override
    /**
     * 方法名称：saveCompanyOwner
     * 内容摘要：保存公司货主信息
     * @param parms    姓名
     * @param usernamer 公司名称
     */
    public int saveCompanyOwner(String parms, String username) {
        Gson gson=new Gson();
        int a=1;
        try {
            Map<String, Object> data = gson.fromJson(parms, new TypeToken<Map<String, Object>>() {
            }.getType());
            String company_name = (String) data.get("company_name");
            String contact_name = (String) data.get("contact_name");
            String contact_mobile_phone = (String) data.get("contact_mobile_phone");
            String business_licence = (String) data.get("business_licence");
//            String business_licence_pic_id = (String) data.get("business_licence_pic_id");
            String company_fixed_phone = (String) data.get("company_fixed_phone");
            String contact_sex = (String) data.get("contact_sex");
            String province = (String) data.get("province");
            String city = (String) data.get("city");
            String area = (String) data.get("area");
            String townStreet = (String) data.get("townStreet");
            T_Data_Company company = new T_Data_Company();
            company.setCompany_id(UUIDUtil.getUUID());
            company.setCompany_name(company_name);
            company.setBusiness_licence(business_licence);
            company.setBusiness_licence_pic_id("/companyShipperPic/20160819161625BC52A63783864A1F85B4EA8D9DC8EECC.jpg");
            company.setContact_sex(contact_sex);
            company.setCompany_fixed_phone(company_fixed_phone);
            company.setContact_name(contact_name);
            company.setContact_mobile_phone(contact_mobile_phone);
            company.setCompany_type("2");
            company.setProvince_id(province);
            company.setCity_id(city);
            company.setArea_id(area);
            company.setTown_street(townStreet);
            company.setRegister_time(DateUtil.getDate());
            company.setStatus("2");
            company.setDelete_flag("0");
            company.setLast_update(DateUtil.getDate());
            company.setLast_update_user_id(username);
            a = ownerCompanyDaoMapper.saveOwner(company);
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
     * 方法名称：findCompanyShipperByID
     * 内容摘要：根据id货主公司货主信息
     * @param company_id    公司货主id
     */
    @Override
    public T_Data_Company findCompanyShipperByID(String company_id) {

        T_Data_Company companyShipperByID=null;
        try {
            companyShipperByID= ownerCompanyDaoMapper.findCompanyShipperByID(company_id);
        }catch (Exception e){
            logger.error(ShipperManager_Message.seacheInfoError);
        }
        return companyShipperByID;
    }
}