/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：CarTypeController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-16
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Master_Truck_Carriage_Type;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.CargoTruckTypeService;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.TruckCarriageTypeService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.TruckCarriageTypeManager_Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 类 名 称：CarTypeController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/carTypeManager")
public class CarTypeController {

    Logger logger = Logger.getLogger("CarTypeController.class");
    @Resource
    private TruckCarriageTypeService truckCarriageTypeService;
    @Resource
    private DicdataService dicdataService;
    @Resource
    private CargoTruckTypeService cargoTruckTypeService;

    /**
     * 方法名称：carTypeManager
     * 内容摘要：车厢类型。
     *
     * @return string 车厢类型
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String carTypeManager(ModelMap model) {
        return "operationManager/carTypeManager/carTypeManager";
    }

    /**
     * 方法名称：carTypeDetails
     * 内容摘要：跳转车厢类型详情匹配页面。
     *
     * @return string 车厢类型详情匹配页面。
     */
    @RequestMapping(method = RequestMethod.GET, value = "detail_home")
    public String carTypeDetails(ModelMap model, String carTypeId) {
        return "operationManager/carTypeManagerDetail/carTypeManagerDetail";
    }

    /**
     * 方法名称：carTypeAdd
     * 内容摘要：跳转车厢类型新增页面。
     *
     * @return string 跳转车厢类型新增页面。
     */
    @RequestMapping(method = RequestMethod.GET, value = "addHome")
    public String carTypeAdd(ModelMap model) {
        return "operationManager/carTypeManagerNewAdd/carTypeManagerNewAdd";
    }

    /**
     * 方法名称：addMatchHome
     * 内容摘要：跳转车厢类型匹配新增页面。
     *
     * @return string 跳转车厢类型匹配新增页面。
     */
    @RequestMapping(method = RequestMethod.GET, value = "addMatchHome")
    public String addMatchHome(ModelMap model) {
        return "operationManager/carTypeManagerNewAddMatch/carTypeManagerNewAddMatch";
    }

    /**
     * 方法名称：saveCarType
     * 内容摘要：保存车厢类型
     *
     * @return string 新增车厢类型
     */
    @ResponseBody
    @RequestMapping(value = "/saveCarType")
    public T_Data_JsonResult saveGoodType(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult result = new T_Data_JsonResult();
        String cargoTypeDetail = request.getParameter("list");
        try {
            int i = truckCarriageTypeService.saveTruckCarriageType(cargoTypeDetail,"U:" + request.getSession().getAttribute("username"));
            if (i==1){
                result.setResult(1);
                logger.info(TruckCarriageTypeManager_Message.saveDone);
            }else if (i==0){
                result.setResult(0);
                logger.info(TruckCarriageTypeManager_Message.saveDoubleInfoError);
            }else{
                result.setResult(2);
                logger.info(TruckCarriageTypeManager_Message.saveErr);
            }
        }catch (Exception e){
            result.setResult(2);
            logger.info(TruckCarriageTypeManager_Message.saveErr);
        }
        return  result;
    }
    /**
     * 方法名称：findTruckTypeList
     * 内容摘要：查询所有的车厢类型。
     * @param request  request
     * @param response response
     * @param model
     */
    @RequestMapping(value = "list")
    public void findTruckTypeList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Sys_Dicdata> dicdataList_useable = null;
        try {
            List<T_Master_Truck_Carriage_Type> typeList = truckCarriageTypeService.findAllTruckCarriageType2();
            List<Map<String, String>> list=new ArrayList<Map<String, String>>();
            for (T_Master_Truck_Carriage_Type carriageType:typeList){
                HashMap<String, String> hashMap = new HashMap<String, String>();
                String name2 = carriageType.getTruck_carriage_type_name();
                String truckTypeId = carriageType.getTruck_carriage_type_id();
                String typeDesc = carriageType.getTruck_carriage_type_desc();
                dicdataList_useable=  dicdataService.findAllDicdataByID("DF338ECE3FC54C9581B52641E2BDD721", carriageType.getTruck_carriage_useable());
                hashMap.put("carTypeId",truckTypeId);
                hashMap.put("carTypeName",name2);
                hashMap.put("carTypeDesc",typeDesc);
                hashMap.put("carTypeUseable",dicdataList_useable.get(0).getDicdata_name());
                list.add(hashMap);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    /**
     * 方法名称：findCargoById
     * 内容摘要：根据id查询货物类型。
     * @param request  request
     * @param response response
     * @param model
     */
    @RequestMapping(value = "findTruckTypeById")
    public void findCargoById(HttpServletRequest request, HttpServletResponse response,  ModelMap model,String truckTypeId){
        Map result=new HashMap();
        try {
            T_Master_Truck_Carriage_Type carriageType = truckCarriageTypeService.findTruckCarriageTypeByID(truckTypeId);
            HashMap<String, String> hashMap = new HashMap<String, String>();
            String carTypeDesc = carriageType.getTruck_carriage_type_desc();
            String typeName = carriageType.getTruck_carriage_type_name();
            hashMap.put("carTypeDesc",carTypeDesc);
            result.put("truckType",hashMap);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * 方法名称：truckCarriageTypeDel
     * 内容摘要：删除车厢类型信息。
     * @param temID  车厢类型ID
     * @param request        request
     * @param response       response
     * @param model          model
     */

    @ResponseBody
    @RequestMapping(value = "truckCarriageTypeDel")
    public T_Data_JsonResult truckCarriageTypeDel(String temID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] truck_carriage_type_id = temID.split(",");
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            for (int i = 0; i < truck_carriage_type_id.length; i++) {
                String ID = truck_carriage_type_id[i];
                cargoTruckTypeService.delByTruckId(ID);
                truckCarriageTypeService.delTruckCarriageType(ID);
                logger.info(TruckCarriageTypeManager_Message.delDone);
            }
            result.setResult(0);
        } catch (Exception e) {
            logger.info(TruckCarriageTypeManager_Message.delErr + e.getMessage());
        }
        return result;
    }
}

