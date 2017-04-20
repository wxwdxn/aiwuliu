/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：freightingYard.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-20
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.CargoService;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.OperateMainLineService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.CargoManager_Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
 * 类 名 称：freightingYard
 * 内容描述： 货场管理
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/freightYardManager")
public class FreightYardController {
    Logger logger=Logger.getLogger("GoodTypeController.class");
    @Resource
    private CargoService cargoService;
    @Resource
    private DicdataService dicdataService;

    @Resource
    private OperateMainLineService operateMainLineService;

    /**
     * 方法名称：freightYardManager
     * 内容摘要：货场管理页面。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String freightYardManager(ModelMap model) {
        return "operationManager/freightYardManager/freightYardManager";
    }
    /**
     * 方法名称：freightYardManagerDetail
     * 内容摘要：货场详情页面。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "freightYardDetail")
    public String freightYardManagerDetail(ModelMap model,String freightYardId) {
        return "operationManager/freightYardManagerDetail/freightYardManagerDetail";
    }
    /**
     * 方法名称：freightYardManagerNewAdd
     * 内容摘要：货场新增页面。
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "freightYardNewAdd")
    public String freightYardManagerNewAdd(ModelMap model) {
        return "operationManager/freightYardManagerNewAdd/freightYardManagerNewAdd";
    }

    /**
     * 方法名 称：getFrightYardByPosition
     * 内容描述：根据位置查询 货场信息
     * 方法描述：该类有 个方法
     *          01
     *          02
     *@authot WXW
     */
    @RequestMapping(value = "/getFrightYardByPosition")
    @ResponseBody
    public void getFrightYardByPosition(@RequestParam (defaultValue = "",required = false)String province,
                                        @RequestParam (defaultValue = "",required = false)String city,
                                        @RequestParam (defaultValue = "",required = false)String area,
                                        @RequestParam (defaultValue = "",required = false)String street,
                                        HttpServletRequest request, HttpServletResponse response, ModelMap model){

        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_area = new ArrayList<T_Sys_Dicdata>();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        try {
            List<T_Master_Cargo_Yard> yardList = cargoService.getFrightYardByPosition(province, city, area, street);
            if (yardList.size()!=0){
                for (T_Master_Cargo_Yard yard:yardList){
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    String freightYardId = yard.getCargo_id();
                    //经度
                    String longitude = yard.getLongitude();
                    //纬度
                    String latitude = yard.getLatitude();
                    //获取省市县
                    dicdataList_province = this.dicdataService.findAllDicdataByID("2EE02FDAE3BE4F06B8CFDF4425BA74BF", yard.getProvince_id());
                    dicdataList_city = this.dicdataService.findAllDicdataByID("66029BA5DC964A15B29852FA077327C0", yard.getCity_id());
                    dicdataList_area = this.dicdataService.findAllDicdataByID("D7FAF287624242ECB3304B6A62414779", yard.getArea_id());
                    String dicdataProvince = dicdataList_province.get(0).getDicdata_name();
                    String dicdataCity = dicdataList_city.get(0).getDicdata_name();
                    String dicdataArea= dicdataList_area.get(0).getDicdata_name();
                    String townStreet = yard.getTown_street();
                    hashMap.put("freightYardId",freightYardId);
                    hashMap.put("longitude",longitude);
                    hashMap.put("latitude",latitude);
                    hashMap.put("position",dicdataProvince+dicdataCity+dicdataArea);
                    hashMap.put("townStreet",townStreet);
                    list.add(hashMap);
                }
            }

            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }


    /**
     * 方法名称：saveFreightYard
     * 内容摘要：保存货场
     *
     * @return string 新增货场
     */
    @ResponseBody
    @RequestMapping(value = "/saveFreightYard")
    public T_Data_JsonResult saveFreightYard( HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        String cargoTypeDetail = request.getParameter("list");
        Gson gson=new Gson();
        T_Data_JsonResult result = new T_Data_JsonResult();
        try{
            Map<String, Object> data = gson.fromJson(cargoTypeDetail, new TypeToken<Map<String, Object>>() {
            }.getType());
            String province =(String) data.get("province");
            String city =(String) data.get("city");
            String area =(String) data.get("area");
            String townStreet =(String) data.get("townStreet");
            String beginStart =(String) data.get("beginStart");
            String beginEnd =(String) data.get("beginEnd");
            String loadCost =(String) data.get("loadCost");
            String loadPumpCost =(String) data.get("loadPumpCost");
            String loadCount =(String) data.get("loadCount");
            String endFinsh =(String) data.get("endFinsh");
            String endStart =(String) data.get("endStart");
            String unloadCost =(String) data.get("unloadCost");
            String unloadPumpCost =(String) data.get("unloadPumpCost");
            String unloadCount =(String) data.get("unloadCount");
            String longitude =(String) data.get("longitude");
            String latitude =(String) data.get("latitude");
            String averageLoad =(String) data.get("averageLoad");
            String averageUnload =(String) data.get("averageUnload");
            String freightYardName =(String) data.get("freightYardName");
            T_Master_Cargo_Yard cargoYard = new T_Master_Cargo_Yard();
            cargoYard.setCargo_id(UUIDUtil.getUUID());
            cargoYard.setCargo_name(freightYardName);
            cargoYard.setProvince_id(province);
            cargoYard.setCity_id(city);
            cargoYard.setArea_id(area);
            cargoYard.setTown_street(townStreet);
            cargoYard.setLoad_begin_time(beginStart);
            cargoYard.setLoad_end_time(beginEnd);
            cargoYard.setLoad_cost(Integer.valueOf(loadCost));
            cargoYard.setLoad_pump_cost(Integer.valueOf(loadPumpCost));
            cargoYard.setAverage_load_time(Integer.valueOf(averageLoad));
            cargoYard.setLoad_truck_number_per_day(Integer.valueOf(loadCount));
            cargoYard.setUnload_begin_time(endStart);
            cargoYard.setUnload_end_time(endFinsh);
            cargoYard.setUnload_cost(Integer.valueOf(unloadCost));
            cargoYard.setUnload_pump_cost(Integer.valueOf(unloadPumpCost));
            cargoYard.setAverage_unload_time(Integer.valueOf(averageUnload));
            cargoYard.setUnload_truck_number_per_day(Integer.valueOf(unloadCount));
            cargoYard.setLongitude(longitude);
            cargoYard.setLatitude(latitude);
            cargoYard.setLast_update_user_id("U:"+request.getSession().getAttribute("username"));
            cargoYard.setDeleteFlag(0);
            cargoYard.setLast_update(DateUtil.getDate());
           int b = cargoService.saveCargo(cargoYard);
            if (b==1){
                logger.info(CargoManager_Message.saveInfo);
                result.setResult(1);//成功
            }else if (b==0){
                logger.info(CargoManager_Message.saveInfoError);
                result.setResult(0);//重复
            }else {
                logger.info(CargoManager_Message.saveInfoError);
                result.setResult(2);//失败
            }
        }catch (Exception e){
            logger.info(e.getMessage());
            result.setResult(2);//失败
        }
        return result;
    }

    /**
     * 方法名 称：getCargoTypeByCarTypeId
     * 内容描述：根据货场id 获取详情
     * 方法描述：该类有 个方法
     *          01
     *          02
     *@authot WXW
     */
    @RequestMapping(value = "/getFreightYardById")
    public void getFreightYardById(HttpServletRequest request,HttpServletResponse response,String freightYardById){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_area = new ArrayList<T_Sys_Dicdata>();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            T_Master_Cargo_Yard cargoYard = cargoService.findById(freightYardById);
            hashMap.put("CargoName",cargoYard.getCargo_name()) ;
            String provinceId = cargoYard.getProvince_id();
            String cityId = cargoYard.getCity_id();
            String areaId = cargoYard.getArea_id();
            dicdataList_province = this.dicdataService.findAllDicdataByID("2EE02FDAE3BE4F06B8CFDF4425BA74BF", provinceId);
            dicdataList_city = this.dicdataService.findAllDicdataByID("66029BA5DC964A15B29852FA077327C0", cityId);
            dicdataList_area = this.dicdataService.findAllDicdataByID("D7FAF287624242ECB3304B6A62414779", areaId);
            hashMap.put("position",dicdataList_province.get(0).getDicdata_name()+dicdataList_city.get(0).getDicdata_name()+dicdataList_area.get(0).getDicdata_name()) ;
            hashMap.put("townStreet",cargoYard.getTown_street()) ;
            hashMap.put("beginTime",cargoYard.getLoad_begin_time()) ;
            hashMap.put("endTime",cargoYard.getLoad_end_time()) ;
            hashMap.put("loadCost",String.valueOf(cargoYard.getLoad_cost())) ;
            hashMap.put("loadPumpCost",String.valueOf(cargoYard.getLoad_pump_cost())) ;
            hashMap.put("loadPerDay",String.valueOf(cargoYard.getLoad_truck_number_per_day()) );
            hashMap.put("loadAverage",String.valueOf(cargoYard.getAverage_load_time())) ;
            hashMap.put("unloadBeginTime",cargoYard.getUnload_begin_time()) ;
            hashMap.put("unloadendTime",cargoYard.getUnload_end_time()) ;
            hashMap.put("unloadCost",String.valueOf(cargoYard.getUnload_cost())) ;
            hashMap.put("unloadPumpCost",String.valueOf(cargoYard.getUnload_pump_cost())) ;
            hashMap.put("unloadPerDay",String.valueOf(cargoYard.getUnload_truck_number_per_day())) ;
            hashMap.put("unloadAverage",String.valueOf(cargoYard.getAverage_unload_time())) ;
            hashMap.put("longitude",cargoYard.getLongitude()) ;
            hashMap.put("latitude",cargoYard.getLatitude()) ;
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(hashMap));
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    /**
     * 方法名称：updateGoodType
     * 内容摘要：更新货物类型
     *
     * @return string 更新货场信息
     */
    @ResponseBody
    @RequestMapping(value = "/updateFreightYard")
    public T_Data_JsonResult updateGoodType( HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult result = new T_Data_JsonResult();
        String cargoTypeDetail = request.getParameter("list");
        Gson gson=new Gson();
        try{
            Map<String, Object> data = gson.fromJson(cargoTypeDetail, new TypeToken<Map<String, Object>>() {
            }.getType());
            String CargoName=(String) data.get("CargoName");
            String beginStart =(String) data.get("beginTime");
            String beginEnd =(String) data.get("endTime");
            String loadCost =(String) data.get("loadCost");
            String loadPumpCost =(String) data.get("loadPumpCost");
            String loadCount =(String) data.get("loadPerDay");
            String endFinsh =(String) data.get("unloadendTime");
            String endStart =(String) data.get("unloadBeginTime");
            String unloadCost =(String) data.get("unloadCost");
            String unloadPumpCost =(String) data.get("unloadPumpCost");
            String unloadCount =(String) data.get("unloadPerDay");
            String freightYardId =(String) data.get("freightYardId");
            T_Master_Cargo_Yard cargoYard = cargoService.findById(freightYardId);
            cargoYard.setCargo_name(CargoName);
            cargoYard.setLoad_begin_time(beginStart);
            cargoYard.setLoad_end_time(beginEnd);
            cargoYard.setLoad_cost(Integer.valueOf(loadCost));
            cargoYard.setLoad_pump_cost(Integer.valueOf(loadPumpCost));
            cargoYard.setLoad_truck_number_per_day(Integer.valueOf(loadCount));
            cargoYard.setUnload_begin_time(endStart);
            cargoYard.setUnload_end_time(endFinsh);
            cargoYard.setUnload_cost(Integer.valueOf(unloadCost));
            cargoYard.setUnload_pump_cost(Integer.valueOf(unloadPumpCost));
            cargoYard.setUnload_truck_number_per_day(Integer.valueOf(unloadCount));
            cargoYard.setLast_update_user_id("U:"+request.getSession().getAttribute("username"));
            cargoYard.setLast_update(DateUtil.getDate());
            boolean b = cargoService.updateCargo(cargoYard);
            if (b){
                logger.info(CargoManager_Message.saveInfo);
                result.setResult(1);//成功
            }else {
                logger.info(CargoManager_Message.saveInfoError);
                result.setResult(2);//失败
            }
        }catch (Exception e){
            logger.info(e.getMessage());
            result.setResult(2);//失败
        }
        return result;
    }

    /**
     * 方法名 称：
     * 内容描述：获取所有的货场信息list
     * 方法描述：该类有 个方法
     *          01
     *          02
     *@authot WXW
     */
    @RequestMapping(value = "/list")
    @Transactional
    public void getFrightYardlist(HttpServletRequest request, HttpServletResponse response){
        List<T_Sys_Dicdata> dicdataList_province = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_city = new ArrayList<T_Sys_Dicdata>();
        List<T_Sys_Dicdata> dicdataList_area = new ArrayList<T_Sys_Dicdata>();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        try {
            List<T_Master_Cargo_Yard> yardList = cargoService.findCargoList();
            if (yardList.size()!=0){
                for (T_Master_Cargo_Yard yard:yardList){
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    String freightYardId = yard.getCargo_id();
                    //经度
                    String longitude = yard.getLongitude();
                    //纬度
                    String latitude = yard.getLatitude();
                    //获取省市县
                    dicdataList_province = this.dicdataService.findAllDicdataByID("2EE02FDAE3BE4F06B8CFDF4425BA74BF", yard.getProvince_id());
                    dicdataList_city = this.dicdataService.findAllDicdataByID("66029BA5DC964A15B29852FA077327C0", yard.getCity_id());
                    dicdataList_area = this.dicdataService.findAllDicdataByID("D7FAF287624242ECB3304B6A62414779", yard.getArea_id());
                    String dicdataProvince = dicdataList_province.get(0).getDicdata_name();
                    String dicdataCity = dicdataList_city.get(0).getDicdata_name();
                    String dicdataArea= dicdataList_area.get(0).getDicdata_name();
                    String townStreet = yard.getTown_street();
                    hashMap.put("freightYardId",freightYardId);
                    hashMap.put("longitude",longitude);
                    hashMap.put("latitude",latitude);
                    hashMap.put("position",dicdataProvince+dicdataCity+dicdataArea);
                    hashMap.put("townStreet",townStreet);
                    list.add(hashMap);
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
    /**
     * 方法名称：cargoDel
     * 内容摘要：删除货场。
     * @param request  request
     * @param response response
     * @param freightYardID freightYardID
     * @param model
     */
    @RequestMapping(value = "cargoDel")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult cargoDel(String freightYardID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String [] cargo_id=freightYardID.split(",");
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            for (int i=0;i<cargo_id.length;i++){
                cargoService.CargoDel(cargo_id[i]);
            }
            result.setResult(0);
        } catch (Exception e) {
            result.setResult(1);
            logger.info(CargoManager_Message.DelCargoError + e.getMessage());
        }
        return  result;
    }

    /**
     * 方法名称：carList
     * 内容摘要：获取货场列表（拥有下拉选择的回显）。
     * @param request  request
     * @param response response
     * @return string 货场列表
     */
    @RequestMapping(value = "carList")
    public String carList(HttpServletRequest request, HttpServletResponse response) {
        List<T_Master_Cargo_Yard> carList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            //封装查询结果
            carList = cargoService.findCargoList();
            if (carList.size() > 0 && i < carList.size()) {
                for (T_Master_Cargo_Yard cargo : carList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", cargo.getCargo_id());
                    results.put("name", cargo.getCargo_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.info(CargoManager_Message.getCargoError+e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }


    /**
     * 方法名称：loadCargoByLineId
     * 内容摘要：根据干线id获取装货货场列表（拥有下拉选择的回显）。
     * @param request  request
     * @param response response
     * @return string 货场列表
     */
    @RequestMapping(value = "loadCargoByLineId")
    public String loadCargoByLineId(HttpServletRequest request, HttpServletResponse response) {
        String parameter = request.getParameter("operate_main_line_id");
        List<T_Master_Cargo_Yard> yardList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            if (parameter.trim().length()!=0){
                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(parameter);
                String startProvinceId = mainLine.getStart_province_id();
                String startCityId = mainLine.getStart_city_id();
                yardList = cargoService.getFrightYardByPosition(startProvinceId, startCityId, "", "");
                //封装查询结果
                if (yardList.size() > 0 && i < yardList.size()) {
                    for (T_Master_Cargo_Yard cargo : yardList) {
                        Map<String, String> results = new HashMap<String, String>();
                        results.put("id", cargo.getCargo_id());
                        results.put("name", cargo.getCargo_name());
                        lists.add(results);
                        i++;
                    }
                }
                ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
            }

        } catch (Exception e) {
            logger.info(CargoManager_Message.getCargoError+e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }

    /**
     * 方法名称：unloadCargoByLineId
     * 内容摘要：根据干线id获取卸货货场列表（拥有下拉选择的回显）。
     * @param request  request
     * @param response response
     * @return string 货场列表
     */
    @RequestMapping(value = "unloadCargoByLineId")
    public String unloadCargoByLineId(HttpServletRequest request, HttpServletResponse response) {
        String parameter = request.getParameter("operate_main_line_id");
        List<T_Master_Cargo_Yard> yardList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            if (parameter.trim().length()!=0){
                T_Master_Operate_Main_Line mainLine = operateMainLineService.findOperateMainLineById(parameter);
                String finishProvinceId = mainLine.getFinish_province_id();
                String finishCityId = mainLine.getFinish_city_id();
                yardList = cargoService.getFrightYardByPosition(finishProvinceId, finishCityId, "", "");
                //封装查询结果
                if (yardList.size() > 0 && i < yardList.size()) {
                    for (T_Master_Cargo_Yard cargo : yardList) {
                        Map<String, String> results = new HashMap<String, String>();
                        results.put("id", cargo.getCargo_id());
                        results.put("name", cargo.getCargo_name());
                        lists.add(results);
                        i++;
                    }
                }
                ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
            }

        } catch (Exception e) {
            logger.info(CargoManager_Message.getCargoError+e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }


}