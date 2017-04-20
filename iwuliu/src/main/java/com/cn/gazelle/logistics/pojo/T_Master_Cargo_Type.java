/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：T_Master_Cargo_Type.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者: WXW
 * 内容摘要：货物类型的实体类
 */
package com.cn.gazelle.logistics.pojo;

import java.util.Date;

/**
 * 类 名 称：T_Master_Cargo_Type
 * 内容描述：货物类型的实体类
 *@authot WXW
 */
public class T_Master_Cargo_Type {
    private String cargoTypeId;         // 货物种类ID
    private String cargoTypeName;       // 货物种类名称
    private String cargoTypeDesc;       // 货物种类描述
    private String cargoTypeUnitC;      // 货物种类国际单位
    private String cargoTypeUnitE;      // 货物种类中文单位
    private  int deleteFlag;            //删除标识
    private Date lastUpdate;            // 最终更新日
    private String lastUpdateUserId;    // 最终更新者ID

    public String getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(String cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public String getCargoTypeName() {
        return cargoTypeName;
    }

    public void setCargoTypeName(String cargoTypeName) {
        this.cargoTypeName = cargoTypeName;
    }

    public String getCargoTypeDesc() {
        return cargoTypeDesc;
    }

    public void setCargoTypeDesc(String cargoTypeDesc) {
        this.cargoTypeDesc = cargoTypeDesc;
    }

    public String getCargoTypeUnitC() {
        return cargoTypeUnitC;
    }

    public void setCargoTypeUnitC(String cargoTypeUnitC) {
        this.cargoTypeUnitC = cargoTypeUnitC;
    }

    public String getCargoTypeUnitE() {
        return cargoTypeUnitE;
    }

    public void setCargoTypeUnitE(String cargoTypeUnitE) {
        this.cargoTypeUnitE = cargoTypeUnitE;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
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