/**
 * 项目名称:  [Logistics]
 * 包:        [com.cn.gazelle.logistics.util.message]
 * 类名称:    [RepairStationManager_Message]
 * 类描述:    [Log输出内容]
 * 创建人:    [QJ]
 * 创建时间:  [2016/5/4 10:50]
 */

package com.cn.gazelle.logistics.util.message;

import com.cn.gazelle.logistics.util.message.base.MessageUtil;

/**
 * 类 名 称: RepairStationManager_Message
 * 内容摘要: Log输出内容
 * @author QJ
 */
public class RepairStationManager_Message extends MessageUtil {

    // 操作成功信息 Done结尾
    public static final String searchDone = "查询维修站成功";
    public static final String saveDone = "保存维修站成功";
    public static final String updataDone = "更新维修站成功";
    public static final String delDone = "删除维修站成功";
    public static final String examineDone = "审核维修站成功";

    // 通知信息 Info结尾

    // 警告信息 Warn结尾

    // 异常信息 Err结尾
    public static final String searchErr = "查询维修站失败：";
    public static final String saveErr = "保存维修站失败：";
    public static final String updataErr = "更新维修站失败：";
    public static final String delErr = "删除维修站失败：";
    public static final String examineErr = "审核维修站失败：";

    public static final String getSelectStationCountError = "维修站总记录数查询失败！";
}
