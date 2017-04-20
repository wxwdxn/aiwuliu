package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Truck_AccountInfo;
import com.cn.gazelle.logistics.pojo.T_Data_Truck_Payment_History;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zf on 2016/12/14.
 */
@WebService
public interface TruckPayService {

    //根据历史编号查询卡车支付历史信息
    T_Data_Truck_Payment_History findTruckPaymentHistoryByAccountNo(String history_number);

    //根据卡车Id查询卡车支付历史信息
    List<T_Data_Truck_Payment_History> findAll(String truck_id);

    //查询时间范围内的卡车支付历史信息
    List<T_Data_Truck_Payment_History> findHistoryAll(HashMap<String, String> conditions);

    //根据条件查询车辆账户信息
    List<T_Data_Truck_AccountInfo> findTruckAccountInfo(HashMap<String, String> conditions);

    //保存卡车支付历史信息
    void saveTruckPaymentHistory(T_Data_Truck_Payment_History truckPaymentHistory);

    //更新卡车支付历史信息
    void updateTruckPaymentHistory(T_Data_Truck_Payment_History truckPaymentHistory);

    //根据历史编号删除卡车支付历史信息
    void delTruckPaymentHistory(String history_number);

    //根据第三方订单ID查询卡车支付历史信息
    T_Data_Truck_Payment_History findTruckPaymentHistoryByThirdPartyOrderId(String third_party_order_id);
}
