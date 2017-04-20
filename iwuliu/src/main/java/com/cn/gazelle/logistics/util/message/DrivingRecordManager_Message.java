/**
 * 项目名称:  [Logistics]
 * 包:        [com.cn.gazelle.logistics.util.message]
 * 类名称:    [DrivingRecordManager_Message]
 * 类描述:    [Log输出内容]
 * 创建人:    [QJ]
 * 创建时间:  [2016/5/17 17:24]
 */

package com.cn.gazelle.logistics.util.message;

import com.cn.gazelle.logistics.util.message.base.MessageUtil;

/**
 * 类 名 称: DrivingRecordManager_Message
 * 内容摘要: Log输出内容
 * @author QJ
 */
public class DrivingRecordManager_Message extends MessageUtil {

    // 操作成功信息 Done结尾
    public static final String searchDone = "查询车辆驾驶记录成功";
    public static final String saveDone = "保存车辆驾驶记录成功";
    public static final String updataDone = "更新车辆驾驶记录成功";
    public static final String delDone = "删除车辆驾驶记录成功";
    public static final String examineDone = "审核车辆驾驶记录成功";

    // 通知信息 Info结尾

    // 警告信息 Warn结尾

    // 异常信息 Err结尾
    public static final String searchErr = "查询车辆驾驶记录失败：";
    public static final String saveErr = "保存车辆驾驶记录失败：";
    public static final String updataErr = "更新车辆驾驶记录失败：";
    public static final String delErr = "删除车辆驾驶记录失败：";
    public static final String examineErr = "审核车辆驾驶记录失败：";

    public static final String getSelectDrivingRecordCountError = "车辆驾驶记录总记录数查询失败！";
}
