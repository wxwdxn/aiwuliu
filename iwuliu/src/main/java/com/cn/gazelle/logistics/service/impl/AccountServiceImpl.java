/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：AccountServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-03-07
 * 作    者: zf
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.pojo.T_Data_Company;
import com.cn.gazelle.logistics.pojo.T_Data_Truck;
import com.cn.gazelle.logistics.service.AccountService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称：AccountServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@author zf
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.AccountService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class AccountServiceImpl implements AccountService {


    @Override
    public T_Data_Company findCompanyAccountByName(String member_name) {
        return null;
    }

    @Override
    public List<T_Data_Truck> findCompanyTruck(String member_id) {
        return null;
    }
}

