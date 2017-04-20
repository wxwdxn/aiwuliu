package com.cn.gazelle.logistics.service;


import com.cn.gazelle.logistics.pojo.T_Data_Vehicle_Detail;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: VehicleDetailService
 * 内容摘要: 车辆详情查询接口
 * 方法描述：该类有1个方法：
 * 01 findVehicleDetail   根据条件查找车辆详情
 *
 * @author QJ
 */
@WebService
public interface VehicleDetailService {
    // 根据条件查找车辆详情
    T_Data_Vehicle_Detail findVehicleDetail(String plate_number, String organization_type);
}
