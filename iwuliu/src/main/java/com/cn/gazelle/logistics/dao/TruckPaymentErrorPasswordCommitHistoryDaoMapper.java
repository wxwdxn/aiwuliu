package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_Payment_Error_Password_Commit_History;
import org.apache.ibatis.annotations.Param;

/**
 * 描述:卡车支付密码错误输入历史接口
 * Created by zf on 2016/12/20.
 */
public interface TruckPaymentErrorPasswordCommitHistoryDaoMapper {

     // 方法描述:保存卡车支付密码错误输入历史信息
    void saveTruckPaymentErrorPasswordCommitHistory(T_Data_Truck_Payment_Error_Password_Commit_History truckPaymentErrorPasswordCommitHistory);

    // 根据历史编号查询卡车支付密码错误输入历史信息

    T_Data_Truck_Payment_Error_Password_Commit_History findTruckPaymentErrorPasswordCommitHistoryByHistoryNumber(String history_number);

    // 通过卡车id和发生时间查找错误次数
    int findErrorPasswordCommitHistoryByIDAndTimeCount(@Param(value = "truck_id") String truck_id,
                                                  @Param(value = "create_time") String create_time);
}
