package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.T_Data_Service_Station_Cost_Record;
import com.cn.gazelle.logistics.service.StationCostRecordService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.asm.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YK on 2016/3/4.
 */
@Controller
@RequestMapping(value = "/stationCostManager")
public class StationCostControlelr {
    Logger logger = Logger.getLogger(StationCostControlelr.class);

    @Resource
    private StationCostRecordService stationCostRecordService;

    //线下加盟服务站消费主页
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "stationCostManager/home";
    }

    //线下加盟服务站消费列表页
    @RequestMapping(value = "/stationCostList")
    public void stationList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String truck_id, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer rows, ModelMap model) {
        List list = null;
        try {
            //封装查询结果
            Map result = new HashMap();
            list = stationCostRecordService.findAllCostRecord(truck_id, (page - 1) * rows, rows);
            result.put("rows", list);
            result.put("total", stationCostRecordService.findAllCostRecordRowsCount(truck_id));
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    //保存服务站消费信息
    @RequestMapping(value = "stationCostSave")
    public void stationCostSave(T_Data_Service_Station_Cost_Record record, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            record.setRecord_id(UUIDUtil.getUUID());
            record.setLast_update(DateUtil.getDate());
            record.setCost_time(DateUtil.getDate());
            record.setRepay_time(DateUtil.getDate());
            record.setLast_update_user_id(UUIDUtil.getUUID());
            stationCostRecordService.saveCostRecord(record);
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
    }

    //更新服务站消费信息
    @RequestMapping(value = "stationCostUpdate")
    public void stationCostUpdate(T_Data_Service_Station_Cost_Record record, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            record.setLast_update(DateUtil.getDate());
            record.setCost_time(DateUtil.getDate());
            record.setRepay_time(DateUtil.getDate());
            record.setLast_update_user_id(UUIDUtil.getUUID());
            stationCostRecordService.updateCostRecord(record);
            logger.info("更新成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    //删除服务站消费信息
    @RequestMapping(value = "stationCostDel")
    public void stationCostDel(String record_id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            stationCostRecordService.delCostRecord(record_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(LogUtil.delErr + e.getMessage());
        }
    }
}
