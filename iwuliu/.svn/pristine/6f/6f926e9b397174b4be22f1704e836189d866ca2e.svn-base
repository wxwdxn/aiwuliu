/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DispatchSheetDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：调度单信息管理和app接口的实现
 * 设计文件：
 * 完成日期：2016-03-14
 * 作    者：WXW
 * 内容摘要：调度单信息管理和app接口的实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Dispatch_Sheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: DispatchSheetDaoMapper
 * 内容摘要: 调度单信息管理
 * 方法描述：该类有8个方法：
 *         01 findDispatchSheetByID                  根据ID查调度单信息
 *         02 findDispatchByMemId                    根据会员id查询派车单信息
 *         03 updateDispatchSheetStatus              更新派车单的状态
 *         04 findAllDispatchSheet                   查找所有调度单信息（分页）
 *         05 findAllDispatchSheetRowsCount          查询调度单总记录数
 *         06 saveDispatchSheet                      保存调度单信息
 *         07 updateDispatchSheet                    更新调度单信息
 *         08 delDispatchSheet                       删除调度单信息
 * @author WXW
 */
public interface DispatchSheetDaoMapper {

    // 根据派车单id查询派车单信息
    T_Data_Transportation_Dispatch_Sheet findDispatchSheetByID(@Param(value = "dispatchSheetId") String dispatchSheetId);

    // 根据状态和接收会员id查询 卸货中 派车单信息
    T_Data_Transportation_Dispatch_Sheet findUnloadingDispatchByMemId(@Param(value = "receiveMemberId") String receiveMemberId);


    // 保存派车单信息
    void saveDispatchSheet(T_Data_Transportation_Dispatch_Sheet dispatchSheet);

    // 更新派车单信息
    void updateDispatchSheet(T_Data_Transportation_Dispatch_Sheet dispatchSheet);

    // 删除派车单信息
    void delDispatchSheet(@Param(value = "dispatchSheetId") String dispatchSheetId);


    // 获取派车单信息根据调度单id
    List<T_Data_Transportation_Dispatch_Sheet> findByScheduleId(@Param(value = "scheduleSheetId") String scheduleSheetId);


    // 根据会员id查询派车单信息
    List<T_Data_Transportation_Dispatch_Sheet> findDispatchByMemIdList(@Param(value = "receiveMemberId") String receiveMemberId);


    // 根据接受者id查询未完成的派车单信息
    List<T_Data_Transportation_Dispatch_Sheet> findUnfinishDispatchByMendId(@Param(value = "receiveMemberId") String receiveMemberId);
}
