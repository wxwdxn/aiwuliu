/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TargetIDUtil.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-28
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.util;

/**
 * 类 名 称：TargetIDUtil
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot YK
 */
public class TargetIDUtil {
    public static final String getTargetID(String account_no){
        StringBuffer needContent = new StringBuffer("0");
        int bankLength = account_no.length();
        int needLength = 32 - bankLength;
        for(int i =1;i<needLength;i++){
            needContent.append("0");
        }
        String target_id = "B"+needContent+account_no;
        return target_id;
    }
}


