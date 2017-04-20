/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OBDequipmentController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-08-22
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.dao.TruckDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment;
import com.cn.gazelle.logistics.service.OBDequipmentService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：OBDequipmentController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/OBDequipmentManager")
public class OBDequipmentController {

    Logger logger=Logger.getLogger(OBDequipmentController.class);

    @Resource
    private OBDequipmentService obDequipmentService;

    @Resource
    private TruckDaoMapper truckDaoMapper;

    /**
     * 方法名称：home
     * 内容摘要：OBD列表管理页面（分页）。
     * @return string OBD列表管理页面
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "OBDequipmentManager/OBDequipmentManager";
    }

    /**
     * 方法名称：OBDList
     * 内容摘要：获取OBD设备列表（拥有下拉选择的回显）。
     * @param request  request
     * @param response response
     * @return string OBD设备列表
     */
    @RequestMapping(value = "OBDList")
    public String OBDList(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_OBD_Equipment> OBDList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            //封装查询结果
            OBDList = obDequipmentService.findAllOBDList();
            if (OBDList.size() > 0 && i < OBDList.size()) {
                for (T_Data_OBD_Equipment obdEquipment : OBDList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", obdEquipment.getEquipment_id());
                    results.put("name", obdEquipment.getObd_id());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }

    /**
     * 方法名称：OBDequipmentList
     * 内容摘要：获取OBD设备列表（分页）。
     * @param request    request
     * @param response   response
     * @param page
     * @param rows
     * @param model
     */
//    @RequestMapping(value = "/OBDequipmentList")
//    public void obdEquipmentList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String typeId,@RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer rows, ModelMap model) {
//        List<T_Data_OBD_Equipment> list = null;
//        try {
//            list = obDequipmentService.findAllOBD(typeId,name, (page - 1) * rows, rows);
//            for (T_Data_OBD_Equipment obdEquipment : list) {
//                String relatedTruckId = obdEquipment.getRelatedTruckId();
//                if (relatedTruckId!=null){
//                    T_Data_Truck truck = truckDaoMapper.findTruckByID(relatedTruckId);
//                    String plate_number = truck.getPlate_number();
//                    obdEquipment.setRelatedTruckId(plate_number);
//                }
//            }
//            //封装查询结果
//            Map result = new HashMap();
//            result.put("rows", list);
//            result.put("total", obDequipmentService.findOBDpagesRows(typeId,name));
//            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
//        } catch (Exception e) {
//            logger.error(MessageUtil.seacheInfoError+e.getMessage());
//        }
//    }
//
//    /**
//     * 方法名称：saveOBDEquipment
//     * 内容摘要：保存货场。
//     * @param request  request
//     * @param response response
//     * @param obdEquipment    obd  T_Data_JsonResult
//     */
//    @RequestMapping(value = "obdEquipmentSave")
//    @ResponseBody
//    public T_Data_JsonResult saveOBDEquipment(T_Data_OBD_Equipment obdEquipment, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
//        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
//        int b=0;
//        try {
//            obdEquipment.setLastUpdate(DateUtil.getDate());
//            obdEquipment.setLastUpdateUserId("U:"+request.getSession().getAttribute("userName"));
//            b= obDequipmentService.saveOBDEquipment(obdEquipment);
//            if (b == 1){
//                jsonResult.setResult(0);//保存成功
//            }else if (b==0){
//                jsonResult.setResult(2);//保存重复
//            }else if (b==-1){
//                jsonResult.setResult(1);//保存失败
//            }
//            logger.info(MessageUtil.saveInfo);
//        } catch (Exception e) {
//            b=-1;
//            logger.error(MessageUtil.saveInfoError+ e.getMessage());
//
//        }
//        return jsonResult;
//    }
//
//    /**
//     * 方法名称：obdEquipmentTypeUpdate
//     * 内容摘要：更新obd设备。
//     * @param request  request
//     * @param response response
//     * @param obdEquipment   obdEquipment
//     * @param model    model
//     */
//    @ResponseBody
//    @RequestMapping(value = "obdEquipmentUpdate")
//    public T_Data_JsonResult obdEquipmentUpdate(T_Data_OBD_Equipment obdEquipment,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
//        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
//        try {
//            // pc端最终的更新者
//            obdEquipment.setLastUpdateUserId("U:"+request.getSession().getAttribute("userName"));
//            obdEquipment.setLastUpdate(DateUtil.getDate());
//            String relatedTruckId = obdEquipment.getRelatedTruckId();
//            T_Data_Truck dataTruck = truckDaoMapper.findTruckByPlateNumber(relatedTruckId);
//            if (dataTruck!=null){
//                String truckId = dataTruck.getTruck_id();
//                obdEquipment.setRelatedTruckId(truckId);
//            }
//            boolean b = obDequipmentService.updateOBDInfo(obdEquipment);
//            if (b){
//                jsonResult.setResult(0);
//            }else {
//                jsonResult.setResult(1);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//        return jsonResult;
//    }
    /**
     * 方法名称：cargoDel
     * 内容摘要：删除obd设备。
     * @param request  request
     * @param response response
     * @param equipmentID 批量删除obd设备
     * @param model
     */
    @RequestMapping(value = "equipmentDel")
    public void cargoDel(String equipmentID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] equipmentId = equipmentID.split(",");
        try {
            for (int i = 0; i < equipmentId.length; i++) {
                obDequipmentService.OBDInfoDel(equipmentId[i]);
            }
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }
}