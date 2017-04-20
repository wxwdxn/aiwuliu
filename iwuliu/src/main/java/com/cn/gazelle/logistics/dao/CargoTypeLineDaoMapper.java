package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_CargoType_Line;

import java.util.List;

/**
 * Created by WXW on 2016/11/3.
 */
public interface CargoTypeLineDaoMapper {

    //根据干线、货物类型查询这条干线的运输车辆个数
    List <T_Data_CargoType_Line>getLineInfo();
}
