package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Company;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by WXW on 2017/1/17.
 *公司货主
 */
@WebService
public interface OwnerCompanyService {

    // 根据多个条件条件查找公司货主信息 拼接
    List<T_Data_Company> findOwnerCompanyList(String contact_name,String company_name, String contact_mobile_phone);

    //保存公司货主
    int saveCompanyOwner(String parms,String  username);

    //根据id查询公司货主
    T_Data_Company findCompanyShipperByID(String company_id);
}
