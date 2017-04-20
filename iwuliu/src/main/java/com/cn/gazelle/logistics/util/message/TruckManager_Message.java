/**
 * 项目名称:  [Logistics]
 * 包:        [com.cn.gazelle.logistics.util.message]
 * 类名称:    [TruckManager_Message]
 * 类描述:    [Log输出内容]
 * 创建人:    [QJ]
 * 创建时间:  [2016/3/3 10:50]
 */

package com.cn.gazelle.logistics.util.message;

import com.cn.gazelle.logistics.util.message.base.MessageUtil;

/**
 * 类 名 称: TruckManager_Message
 * 内容摘要: Log输出内容
 * @author QJ
 */
public class TruckManager_Message extends MessageUtil {

    // 操作成功信息 Done结尾
    public static final String searchDone = "查询卡车信息成功";
    public static final String saveDone = "保存卡车信息成功";
    public static final String updataDone = "更新卡车信息成功";
    public static final String delDone = "删除卡车信息成功";
    public static final String examineDone = "审核卡车信息成功";
    public static final String getTruckDone = "获取车辆信息成功";
    public static final String updataDepositDone = "更新托管对象成功";
    public static final String updataManagerDone = "更新托管对象成功";
    public static final String delDriverDone = "清除车辆原司机成功";
    public static final String updataDriverDone = "保存车辆常跑司机成功";
    public static final String updataLineDone = "保存车辆常跑路线修成功";

    // 通知信息 Info结尾

    // 警告信息 Warn结尾
    public static final String getTruckOwnerWarn = "车主身份未知";
    public static final String getMemberWarn = "车主身份未知";
    public static final String getRepairWarn = "修理状态参数未知";

    // 异常信息 Err结尾
    public static final String searchErr = "查询卡车信息失败：";
    public static final String saveErr = "保存卡车信息失败：";
    public static final String updataErr = "更新卡车信息失败：";
    public static final String delErr = "删除卡车信息失败：";
    public static final String examineErr = "审核卡车信息失败：";

    public static final String getSelectTruckCountError = "卡车总记录数查询失败：";
    public static final String getTruckErr = "获取车辆信息失败";
    public static final String getIdentityErr = "车辆所属身份错误：";
    public static final String getTruckCityErr = "获取所属城市失败：";
    public static final String getTruckModelErr = "获取车型失败：";
    public static final String getTruckOwnerErr = "获取车主信息失败：";
    public static final String getTruckDepositErr = "获取托管对象信息失败：";
    public static final String getTruckManagerErr = "获取车辆管理者信息失败：";
    public static final String getTruckDriverErr = "获取常跑司机信息失败：";
    public static final String getTruckLineErr = "获取车辆常跑路线信息失败：";
    public static final String getTruckRepairErr = "获取车辆修理状态信息错误：";
    public static final String getTruckDriverLineErr = "修改车辆常跑司机/常跑路线信息错误：";
    public static final String getJoinDriversNumberErr = "获取待确认司机信息错误：";
    public static final String getDriversErr = "获取行驶司机信息错误：";
}
