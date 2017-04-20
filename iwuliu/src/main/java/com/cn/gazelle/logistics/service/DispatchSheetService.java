/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DispatchSheetService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：调度单信息管理和app接口的定义
 * 设计文件：
 * 完成日期：2016-03-14
 * 作    者：WXW
 * 内容摘要：调度单信息管理和app接口的实现
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Dispatch_Sheet;

import javax.jws.WebService;

/**
* 类 名 称: DispatchSheetService
* 内容摘要: 调度单信息管理和app接口的实现
* 方法描述：该类有7个方法：
*         01 findDispatchSheetByID                  根据ID查派车单信息
*         02 findDispatchAppByName                  根据会员账号查找派车单信息
*         03 findAllDispatchSheet                   查找所有派车单信息
*         04 findAllDispatchSheetRowsCount          查询派车单总记录数
*         05 saveDispatchSheet                      保存派车单信息
*         06 updateDispatchSheet                    更新派车单信息
*         07 delDispatchSheet                       删除派车单信息
*         08 findDispatchSheetAppBydispatchSheetId  根据派单表id查询app相关信息
*         09 updateDispatchSheetStatus              根据id更新状态
*         10 DispatchSheetShare                     派车单的共享
*         11 findScheduleSheetFinishedCount         查询已完成订单的个数
*         12 searchStatus                           检索派单接收状态
*         13 switchStatus                           订单切换派单接收状态请求
*         14 findDispatchList                       获取当前用户的订单列表
*         15 refreshDispatchSheet                   根据车辆管理者账号刷新派单
*         16 getSheetDetails                        获取订单详情
*         17 searchLine                             线路检索
*         18 findFinishScheduleSheet                查询已经完成的调度单
*         20 transportationExperses                 运单运输费用查询
*         21 receiveSheet                           接单
*         22 finishCount                            运单完成数量查询
*         23 changeSheetStatus                      变更运单运输转态
 *        24expenseType                            消费类型检索
 *        25 expenseSave                            消费票据提交
 *        27 expenseDetails                         消费详情查询
*
* @author WXW
*/

@WebService
public interface DispatchSheetService {

  // 根据派车单id查询派车单信息
  T_Data_Transportation_Dispatch_Sheet findDispatchSheetByID(String dispatchSheetId);

  // 保存派车单信息
  boolean saveDispatchSheet(T_Data_Transportation_Dispatch_Sheet dispatchSheet);

  // 更新派车单信息
  boolean updateDispatchSheet(T_Data_Transportation_Dispatch_Sheet dispatchSheet);

  // 删除派车单信息
  void delDispatchSheet(String dispatchSheetId);

    //线路检索 司机版
    String searchLine(String member_name, String orderId);


    //根据车辆管理者账号刷新派单
    String refreshOrderSheet2(String member_name, String lats, String lons);

   //消费类型检索
   String expenseType(String member_name);

   //消费票据信息提交          2222222222添加了经纬度
   String expenseSave(String member_name, String dispatchId, String dicdataId, String amount, String disbursement_voucher_image_save_path,String longitude,String latitude);

    //获取订单详情
    String getSheetDetails2(String member_name, String dispatchId,String plateNumber);

    //消费详情查询 流水id
   String expenseDetails(String member_name, String flowId);

    //获取当前用户未完成的
   String findDispatchList2(String member_name);

    //改变车辆状态
    String changeSheetStatus2(String member_name, String dispatchId, String status, String loadingProofPath,
                              String unloadingProofPath, String loadingCargoAmount, String unloadingCargoAmount,String longitude,String latitude);

   // 拒单请求
   String refuseSheet(String plateNumber) ;

    //接单和指派司机
    String receiveSheet2AndDesignateDriver(String member_name,String  plateNumber, String orderId, String lineNo,String driverList,String flag);
}
