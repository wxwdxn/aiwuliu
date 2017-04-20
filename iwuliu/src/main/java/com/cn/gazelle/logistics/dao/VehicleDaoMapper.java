package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Company_Truck_Info;
import com.cn.gazelle.logistics.pojo.T_Data_Company_Vehicle_Driver;
import com.cn.gazelle.logistics.pojo.T_Data_Logistics_Vehicle;
import com.cn.gazelle.logistics.pojo.T_Data_Vehicle;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: VehicleDaoMapper
 * 内容摘要: 车辆管理页面
 * 方法描述：该类有2个方法：
 * 01 findVehicleListCompany  根据条件查找车辆列表(物流公司)
 * 02 findVehicleListDriver   根据条件查找车辆列表(司机)
 * 03 findCompanyVehicleList  根据条件查找物流公司车辆列表
 *
 * @author QJ
 */
public interface VehicleDaoMapper {
    // 根据条件查找车辆列表(物流公司)
    List<T_Data_Vehicle> findVehicleListCompany(HashMap<String,String> conditions);

    // 根据条件查找车辆列表(司机)
    List<T_Data_Vehicle> findVehicleListDriver(HashMap<String,String> conditions);

    // 根据条件查找物流公司车辆列表
    List<T_Data_Logistics_Vehicle> findCompanyVehicleList(HashMap<String,String> conditions);

    // 查询物流公司下无车辆的司机列表（去除车辆管理者）
    List<T_Data_Company_Vehicle_Driver> findCompanyVehicleDriverList(HashMap<String,String> conditions);

    // 查询物流公司下无车辆的司机和车辆管理者列表
    List<T_Data_Company_Vehicle_Driver> findCompanyVehicleDriverAndManagerList(HashMap<String,String> conditions);

    // 查找公司下所有审核通过的车辆
    List<T_Data_Company_Truck_Info> findTrucksOfCompany(@Param(value = "plate_number")String plate_number,
                                                        @Param(value = "company_id")String company_id);


    // 查找公司下所有验证中的车辆
    List<T_Data_Vehicle> findAuthenticaTrucksOfCompany( @Param(value = "company_id")String company_id,
                                                        @Param(value = "company_name")String company_name
    );
}
