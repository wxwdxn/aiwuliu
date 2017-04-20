/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：CargoTypeLineServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-03
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.CargoTypeLineDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_CargoType_Line;
import com.cn.gazelle.logistics.service.CargoTypeLineService;
import com.cn.gazelle.logistics.util.JSONUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称：CargoTypeLineServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.CargoTypeLineService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class CargoTypeLineServiceImpl implements CargoTypeLineService{

    @Resource
    private CargoTypeLineDaoMapper cargoTypeLineDaoMapper;
    //根据干线、货物类型查询这条干线的运输车辆个数
    @Transactional

    public String getLineInfo() {
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    Map map2 = new HashMap();
    String json = null;
    String ecode = null;
    try {
        //格式化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<T_Data_CargoType_Line> lineInfo = cargoTypeLineDaoMapper.getLineInfo();
        Date date = new Date();
        for (T_Data_CargoType_Line cargoTypeLine:lineInfo){
            Map map = new HashMap();
            //装货时间
            Date beginDate = cargoTypeLine.getBeginDate();
            if (beginDate.getTime()<date.getTime()){
                map.put("date",format.format(date));
            }else {
                map.put("date",format.format(beginDate));
            }
            double total = cargoTypeLine.getTotal();
            map.put("cargoType",cargoTypeLine.getCargoTypeName());
            map.put("mainLine",cargoTypeLine.getLineName());
            map.put("total",total+"吨");
            map.put("name",cargoTypeLine.getName());
            list.add(map);
        }
        ecode = "1000";
        map2.put("list",list);
        map2.put("ecode",ecode);
    } catch (Exception e) {
        ecode = "2000";
        map2.put("ecode",ecode);
    }
        json = JSONUtil.toJSONString(map2);
        return json;
    }

}