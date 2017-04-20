package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WXW on 2017/1/17.
 * 公司货主
 */
public interface OwnerCompanyDaoMapper {

    // 根据多个条件条件查找公司货主信息 拼接
    List<T_Data_Company> findOwnerCompanyList(
            @Param(value = "contact_name") String contact_name,
            @Param(value = "company_name") String company_name,
            @Param(value = "contact_mobile_phone") String contact_mobile_phone);

    //保存公司货主
    int saveOwner(T_Data_Company company);

    //根据id查询公司货主
    T_Data_Company findCompanyShipperByID(@Param(value = "company_id") String company_id);
}
