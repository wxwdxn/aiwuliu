package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Error_Quick_Response_Code_Scan_Record;

/**
 * 描述:错误二维码扫描记录信息接口
 * Created by zf on 2016/12/20.
 */
public interface ErrorQuickResponseCodeScanRecordDaoMapper {
    /**
     * 保存错误二维码扫描记录信息
     * @param scanRecord
     */
    void saveScanRecord(T_Data_Error_Quick_Response_Code_Scan_Record scanRecord);
}
