/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: T_Data_Contract_Line_Match.Java
 * 系统编号：Z0001002
 * 系统名称：合同线路匹配信息表实体类
 * 模块编号：01
 * 模块名称：合同线路匹配信息表实体类
 * 设计文件：
 * 完成日期：2016-04-21
 * 作    者：WXW
 * 内容摘要：合同线路匹配信息表实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称: T_Data_Contract_Line_Match
 * 内容摘要: 合同线路匹配信息表实体类
 * @author WXW
 */
public class T_Data_Contract_Line_Match {
    private  String matchId;//合同线路匹配id
    private  String contractId;//合同id
    private  String lineId;//线路id
    private  int deleteFlag;//删除标识
    private Date lastUpdate;//更新时间
    private String lastUpdateUserId;//最终更新者id

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getLineId() {
        return lineId;
    }
    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }
}
