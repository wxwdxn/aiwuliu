/**
 * 项目名称:  [Logistics]
 * 包:        [com.cn.gazelle.logistics.util.message]
 * 类名称:    [TruckPositionManager_Message]
 * 类描述:    [Log输出内容]
 * 创建人:    [QJ]
 * 创建时间:  [2016/6/24 ]
 */
package com.cn.gazelle.logistics.util.message;

import com.cn.gazelle.logistics.util.message.base.MessageUtil;

/**
 * 类 名 称：TruckPositionManager_Message
 * 内容描述：Log输出内容
 * @author QJ
 */
public class TruckPositionManager_Message extends MessageUtil {
    // 提示信息
    public static final String getTruckPositionDone="查询卡车定位成功！";
    public static final String getTruckPositionError="查询卡车定位信息失败！";
    public static final String DelTruckPositionDone="删除卡车定位信息成功！";
    public static final String DelTruckPositionError="删除卡车定位信息失败！";
    public static final String SaveTruckPositionDone="保存卡车定位信息成功！";
    public static final String SaveTruckPositionError="保存卡车定位信息失败！";
    public static final String UpdateTruckPositionDone="更新卡车定位信息成功！";
    public static final String UpdateTruckPositionError="更新卡车定位信息失败！";
    public static final String FindTruckPositionRowsCountDone="查询卡车定位信息的总记录数成功！";
    public static final String FindTruckPositionRowsCountError="查询卡车定位信息总记录数失败！";
}