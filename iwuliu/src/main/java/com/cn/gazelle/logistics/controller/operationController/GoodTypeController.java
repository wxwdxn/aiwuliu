/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：GoodTypeController.java
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
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Truck_Type_Match;
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Type;
import com.cn.gazelle.logistics.service.CargoTruckTypeService;
import com.cn.gazelle.logistics.service.CargoTypeService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.CargoManager_Message;
import com.cn.gazelle.logistics.util.message.CargoTypeManager_Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 类 名 称：GoodTypeController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/goodTypeManager")
public class GoodTypeController {
    Logger logger=Logger.getLogger("GoodTypeController.class");

    @Resource
    private CargoTypeService cargoTypeService;
    @Resource
    private CargoTruckTypeService cargoTruckTypeService;
    /**
     * 方法名称：goodTypeManager
     * 内容摘要：货物类型匹配新增。
     *
     * @return string 货物类型
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String goodTypeManager(ModelMap model) {
        return "operationManager/goodTypeManager/goodTypeManager";
    }

    /**
     * 方法名称：goodTypeAdd
     * 内容摘要：新增货物类型
     *
     * @return string 新增货物类型
     */
    @RequestMapping(method = RequestMethod.GET, value = "goodTypehome")
    public String goodTypeAdd(ModelMap model) {
        return "operationManager/goodTypeManagerNewAddMatch/goodTypeManagerNewAddMatch";
    }

    /**
     * 方法名称：saveGoodType
     * 内容摘要：保存货物类型
     *
     * @return string 新增货物类型
     */
    @ResponseBody
    @RequestMapping(value = "/saveGoodType")
    public T_Data_JsonResult saveGoodType(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws ParseException {
        T_Data_JsonResult result = new T_Data_JsonResult();
        String cargoTypeDetail = request.getParameter("list");
        try{
            int i= cargoTypeService.addCargoType(cargoTypeDetail,"U:"+request.getSession().getAttribute("username"));
            if (i==1){
                result.setResult(1);
                logger.info(CargoTypeManager_Message.SaveCargoTypeDone);
            }else if (i==0){
                result.setResult(0);
                logger.info(CargoTypeManager_Message.SaveCargoTypeError);
            }else{
                result.setResult(2);
                logger.info(CargoTypeManager_Message.SaveCargoTypeError);
            }
        }catch (Exception e){
            result.setResult(2);
            logger.info(CargoTypeManager_Message.SaveCargoTypeError);
            logger.info(e.getMessage());
        }
        return  result;
    }

    /**
     * 方法名称：findCargoList
     * 内容摘要：查询货物类型。
     * @param request  request
     * @param response response
     * @param model
     */
    @RequestMapping(value = "list")
    public void findCargoList(HttpServletRequest request, HttpServletResponse response,  ModelMap model){
        Map result=new HashMap();
        try {
            List<T_Master_Cargo_Type> cargoTypeLists = cargoTypeService.findCargoTypeLists();
            List<Map<String, String>> list=new ArrayList<Map<String, String>>();
            for (T_Master_Cargo_Type cargoType:cargoTypeLists){
                HashMap<String, String> hashMap = new HashMap<String, String>();
                String cargoTypeName = cargoType.getCargoTypeName();
                String cargoTypeDesc = cargoType.getCargoTypeDesc();
                String cargoTypeUnitE = cargoType.getCargoTypeUnitE();
                String cargoTypeUnitC = cargoType.getCargoTypeUnitC();
                hashMap.put("cargoTypeId",cargoType.getCargoTypeId());
                hashMap.put("cargoTypeName",cargoTypeName);
                hashMap.put("cargoTypeDesc",cargoTypeDesc);
                hashMap.put("cargoTypeUnitE",cargoTypeUnitE);
                hashMap.put("cargoTypeUnitC",cargoTypeUnitC);
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
    @RequestMapping(value = "findCargoById")
    public void findCargoById(HttpServletRequest request, HttpServletResponse response,  ModelMap model,String cargoId){
        Map result=new HashMap();
        try {
            T_Master_Cargo_Type cargoType = cargoTypeService.findCargoType(cargoId);
            HashMap<String, String> hashMap = new HashMap<String, String>();
            String cargoTypeName = cargoType.getCargoTypeName();
            String cargoTypeDesc = cargoType.getCargoTypeDesc();
            String cargoTypeUnitE = cargoType.getCargoTypeUnitE();
            String cargoTypeUnitC = cargoType.getCargoTypeUnitC();
            hashMap.put("cargoTypeName",cargoTypeName);
            hashMap.put("cargoTypeDesc",cargoTypeDesc);
            hashMap.put("cargoTypeUnitE",cargoTypeUnitE);
            hashMap.put("cargoTypeUnitC",cargoTypeUnitC);
            result.put("cargoType",hashMap);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    /**
     * 方法名称：cargoTypeList
     * 内容摘要：获取货物类型列表（拥有下拉选择的回显）。
     * @param request  request
     * @param response response
     * @return string 货物类型列表
     */
    @RequestMapping(value = "cargoTypeList")
    public String cargoTypeList(HttpServletRequest request, HttpServletResponse response) {
        List<T_Master_Cargo_Type> cargoTypeList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            //封装查询结果
            cargoTypeList = cargoTypeService.findCargoTypeLists();
            if (cargoTypeList.size() > 0 && i < cargoTypeList.size()) {
                for (T_Master_Cargo_Type cargoType : cargoTypeList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", cargoType.getCargoTypeId());
                    results.put("name", cargoType.getCargoTypeName());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.info(CargoTypeManager_Message.getCargoTypeError+e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }

    /**
     * 方法名称：cargoDel
     * 内容摘要：删除货物类型。
     * @param request  request
     * @param response response
     * @param cargoTypeID 批量删除货物类型
     * @param model
     */
    @RequestMapping(value = "cargoTypeDel")
    @ResponseBody
    public T_Data_JsonResult cargoDel(String cargoTypeID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] cargoTypeId = cargoTypeID.split(",");
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            for (int i = 0; i < cargoTypeId.length; i++) {
                cargoTruckTypeService.delByCargoId(cargoTypeId[i]);
                cargoTypeService.delCargoType(cargoTypeId[i]);
            }
            result.setResult(0);
        } catch (Exception e) {
            logger.info(CargoManager_Message.DelCargoError + e.getMessage());
            result.setResult(1);
        }
        return result;
    }

    /**
     * 方法名称：cargoTypeListByTruckCarriageTypeId
     * 内容摘要：根据车厢类型车厢类型ID获取未关联的货物类型列表（拥有下拉选择的回显）。
     * @param request  request
     * @param response response
     * @return string 货物类型列表
     */
    @RequestMapping(value = "cargoTypeLists")
    public String cargoTypeListByTruckCarriageTypeId(String truckTypeId, HttpServletRequest request, HttpServletResponse response) {
        List<T_Master_Cargo_Type> cargoTypeList = null;
        List<T_Master_Cargo_Truck_Type_Match> truckTypeMatchList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            cargoTypeList = cargoTypeService.findCargoTypeLists();
            truckTypeMatchList = cargoTruckTypeService.findMatchByTruck(truckTypeId);
            if (cargoTypeList.size() > 0 && i < cargoTypeList.size()) {
                for (T_Master_Cargo_Type cargoType : cargoTypeList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", cargoType.getCargoTypeId());
                    results.put("name", cargoType.getCargoTypeName());
                    lists.add(results);
                    i++;
                    for (T_Master_Cargo_Truck_Type_Match truckTypeMatch : truckTypeMatchList) {
                        if (truckTypeMatch.getCargoTypeId().equals(cargoType.getCargoTypeId())){
                            lists.remove(results);
                        }
                    }
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.info(CargoTypeManager_Message.getCargoTypeError+e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }
}