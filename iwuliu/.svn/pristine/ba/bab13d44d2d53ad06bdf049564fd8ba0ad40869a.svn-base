/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：VehicleController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：车辆管理
 * 设计文件：
 * 完成日期：2016-11-10
 * 作    者: QJ
 * 内容摘要：车辆管理页面
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.TruckManager_Message;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称：VehicleController
 * 内容描述：车辆管理页面
 * 方法描述：该类有11个方法
 * 01 vehicleManager_home           车辆管理页面调用
 * 02 vehicleManagerDetail_home     车辆管理详情页面调用
 * 03 vehicleManagerNewAdd_home     车辆管理新增页面调用
 * 04 vehicleList                   车辆管理列表
 * 05 truckModelList                根据品牌ID查询所有的卡车型号列表不分页用于下拉的回显操作
 * 06 truckTypeIdJson               车辆类型json
 * 07 truckBrandJson                车辆品牌json
 * 08 vehicleExport                 导出车辆管理信息
 * 09 vehicleDetail                 车辆详情内容
 * 10 updateVehicleInfo             更新（审核）车辆信息
 * 11 truckModelLists               查询所有的卡车型号列表不分页用于下拉的回显操作
 * @authot QJ
 */
@Controller
@RequestMapping(value = "/vehicleManager")
public class VehicleController {
    Logger logger = Logger.getLogger(VehicleController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private VehicleService vehicleService;
    @Resource
    private TruckModelService truckModelService;
    @Resource
    private DicdataService dicdataService;
    @Resource
    private VehicleDetailService vehicleDetailService;
    @Resource
    private TruckService truckService;

    /**
     * 方法名称：vehicleManager_home
     * 内容摘要：车辆管理页面。
     *
     * @return string 车辆管理页面
     */
    @RequestMapping(value = "home")
    public String vehicleManager_home(ModelMap model) {
        return "operationManager/vehicleManager/vehicleManager";
    }

    /**
     * 方法名称：vehicleManagerDetail_home
     * 内容摘要：车辆管理详情页面。
     *
     * @return string 车辆管理详情页面
     */
    @RequestMapping(value = "vehicleManagerDetail_home")
    public String vehicleManagerDetail_home(ModelMap model) {
        return "operationManager/vehicleManagerDetail/vehicleManagerDetail";
    }

    /**
     * 方法名称：vehicleManagerNewAdd_home
     * 内容摘要：车辆管理新增页面。
     *
     * @return string 车辆管理新增页面
     */
    @RequestMapping(value = "vehicleManagerNewAdd_home")
    public String vehicleManagerNewAdd_home(ModelMap model) {
        return "operationManager/vehicleManagerNewAdd/vehicleManagerNewAdd";
    }

    /**
     * 方法名称：vehicleList
     * 内容摘要：车辆管理列表。
     */
    @RequestMapping(value = "/vehicleList")
    public void vehicleList(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(required = false, defaultValue = "") String plate_number,
                            @RequestParam(required = false, defaultValue = "") String organization_type,
                            @RequestParam(required = false, defaultValue = "") String organization_name,
                            @RequestParam(required = false, defaultValue = "") String owner_name,
                            @RequestParam(required = false, defaultValue = "") String truck_brand,
                            @RequestParam(required = false, defaultValue = "") String truck_model_name,
                            @RequestParam(required = false, defaultValue = "") String truck_type,
                            @RequestParam(required = false, defaultValue = "") String start_city,
                            @RequestParam(required = false, defaultValue = "") String end_city,
                            @RequestParam(required = false, defaultValue = "") String verify_status,
                            ModelMap model) {

        List<T_Data_Vehicle> list = new ArrayList<T_Data_Vehicle>();
        List<T_Data_Vehicle> vehicleListCompany = null;
        List<T_Data_Vehicle> vehicleListDriver = null;
        HashMap<String, String> conditions = new HashMap<String, String>();

        try {
            conditions.put("plate_number", plate_number);               // 车牌号
            conditions.put("organization_type", organization_type);     // 机构类型
            conditions.put("organization_name", organization_name);     // 车辆型号
            conditions.put("owner_name", owner_name);                   // 车主姓名
            conditions.put("truck_brand", truck_brand);                 // 车辆品牌
            conditions.put("truck_model_name", truck_model_name);       // 车辆型号
            conditions.put("truck_type", truck_type);                   // 车类型
            conditions.put("start_city", start_city);                   // 常跑干线（起始城市）
            conditions.put("end_city", end_city);                       // 常跑干线（终止城市）
            conditions.put("verify_status", verify_status);             // 审核状态

            if (organization_type.equals("全部")) {
                vehicleListCompany = vehicleService.findVehicleListCompany(conditions);
                vehicleListDriver = vehicleService.findVehicleListDriver(conditions);
                list.addAll(vehicleListCompany);
                list.addAll(vehicleListDriver);
            } else if (organization_type.equals("物流公司")) {
                list = vehicleService.findVehicleListCompany(conditions);
            } else if (organization_type.equals("司机")) {
                list = vehicleService.findVehicleListDriver(conditions);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：truckModelList
     * 内容摘要：根据品牌ID查询所有的卡车型号列表不分页用于下拉的回显操作
     *
     * @param request  request
     * @param response response
     * @return string 卡车型号列表
     */
    @RequestMapping(value = "truckModelList")
    public String truckModelList(String truck_brand_id, HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Truck_Model> truckModelList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            //封装查询结果
            truckModelList = truckModelService.findTruckModelByBrandId(truck_brand_id);
            if (truckModelList.size() > 0 && i < truckModelList.size()) {
                for (T_Data_Truck_Model truckModel : truckModelList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", truckModel.getTruck_model_no() + "");
                    results.put("name", truckModel.getTruck_model_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }

    /**
     * 方法名称：truckModelLists
     * 内容摘要：查询所有的卡车型号列表不分页用于下拉的回显操作
     *
     * @param request  request
     * @param response response
     * @return string 卡车型号列表
     */
    @RequestMapping(value = "truckModelLists")
    public String truckModelLists(HttpServletRequest request, HttpServletResponse response) {
        List<T_Data_Truck_Model> truckModelList = null;
        int i = 0;
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        try {
            //封装查询结果
            truckModelList = truckModelService.findTruckModelLists();
            if (truckModelList.size() > 0 && i < truckModelList.size()) {
                for (T_Data_Truck_Model truckModel : truckModelList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", truckModel.getTruck_model_no() + "");
                    results.put("name", truckModel.getTruck_model_name());
                    lists.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(lists));
        } catch (Exception e) {
            logger.error(TruckManager_Message.seacheInfoError + e.getMessage());
        }
        return JSONUtil.toJSONString(lists);
    }

    /**
     * 方法名称：truckTypeIdJson
     * 内容摘要：车辆类型json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckTypeIdJson")
    public String truckTypeIdJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Sys_Dicdata> dicdataList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            dicdataList = this.dicdataService.findAllDicdataByID("7EA67370E68D493386B56F9C0E97A943", "%%");
            if (dicdataList.size() > 0 && i < dicdataList.size()) {
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", dicdata.getDicdata_id());
                    results.put("name", dicdata.getDicdata_name());
                    list.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
    }

    /**
     * 方法名称：truckBrandJson
     * 内容摘要：车辆品牌json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckBrandJson")
    public String truckBrandJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Sys_Dicdata> dicdataList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            dicdataList = this.dicdataService.findAllDicdataByID("60F8199A3A4D4136AC4B06BE97A1F974", "%%");
            if (dicdataList.size() > 0 && i < dicdataList.size()) {
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", dicdata.getDicdata_id());
                    results.put("name", dicdata.getDicdata_name());
                    list.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
    }

    /**
     * 方法名称：vehicleExport
     * 内容摘要：导出车辆管理到信息excel
     *
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "vehicleExport")
    public void vehicleExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("domainPath=" + domainPath);
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/vehicleExport.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            List<T_Data_Vehicle> list = new ArrayList<T_Data_Vehicle>();
            List<T_Data_Vehicle> vehicleListCompany = null;
            List<T_Data_Vehicle> vehicleListDriver = null;
            HashMap<String, String> conditions = new HashMap<String, String>();
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);
            try {
                // 将数据写入excel
                vehicleListCompany = vehicleService.findVehicleListCompany(conditions);
                vehicleListDriver = vehicleService.findVehicleListDriver(conditions);
                list.addAll(vehicleListCompany);
                list.addAll(vehicleListDriver);

                int i = 1;  // 起始行数，0为第一行
                for (T_Data_Vehicle vehicle : list) {
                    Row row = sheet.createRow(i);
                    i++;
                    row.createCell(0).setCellValue(vehicle.getPlate_number());
                    row.createCell(1).setCellValue(vehicle.getTruck_brand());
                    row.createCell(2).setCellValue(vehicle.getTruck_model_name());
                    row.createCell(3).setCellValue(vehicle.getOrganization_type());
                    row.createCell(4).setCellValue(vehicle.getOrganization_name());
                    row.createCell(5).setCellValue(vehicle.getOwner_name());
                    row.createCell(6).setCellValue(vehicle.getTruck_type());
                    row.createCell(7).setCellValue(vehicle.getTruck_carriage_type());
                    row.createCell(8).setCellValue(vehicle.getTransport_line());
                    row.createCell(9).setCellValue(vehicle.getVerify_status());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("车辆管理管理", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + address + ".xls");
            work.write(os);
            in.close();
            os.close();

        } catch (FileNotFoundException e) {
            System.out.println("文件路径错误");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件输入流错误");
            e.printStackTrace();
        }
    }

    /**
     * 方法名称：vehicleDetail
     * 内容摘要：车辆详情内容。
     */
    @RequestMapping(value = "/vehicleDetail")
    @ResponseBody
    @Transactional
    public void vehicleDetail(@RequestParam("plate_number") String plate_number, @RequestParam("organization_type") String organization_type,
                              HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        plate_number = URLDecoder.decode(plate_number, "UTF-8");
        organization_type = URLDecoder.decode(organization_type, "UTF-8");
        T_Data_Vehicle_Detail vehicleDetail = null;
        try {
            vehicleDetail = vehicleDetailService.findVehicleDetail(plate_number, organization_type);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(vehicleDetail));
        } catch (Exception e) {
            logger.error(TruckManager_Message.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：updateVehicleInfo
     * 内容摘要：更新（审核）车辆信息
     */
    @ResponseBody
    @RequestMapping(value = "/updateVehicleInfo")
    @Transactional
    public T_Data_JsonResult updateVehicleInfo(@RequestParam(required = false, defaultValue = "") String plate_number,
                                               @RequestParam(required = false, defaultValue = "") String verify_status,
                                               @RequestParam(required = false, defaultValue = "") String verify_refused_reason,
                                               HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        T_Data_Truck truck = null;
        try{
            truck = this.truckService.findTruckByPlateNumber(plate_number);
            if (truck!=null){
                if (verify_status.equals("3")) {
                    truck.setVerify_refused_time(DateUtil.getDate());
                } else if (verify_status.equals("2")) {
                    truck.setVerify_passed_time(DateUtil.getDate());
                }
                truck.setVerify_refused_reason(verify_refused_reason);
                truck.setVerify_status(verify_status);
                truck.setLast_update(DateUtil.getDate());
                truck.setLast_update_user_id("U:" + request.getSession().getAttribute("username"));
                boolean b = this.truckService.updateTruck(truck);
                if (b){
                    jsonResult.setResult(0);// 审核成功
                }else {
                    jsonResult.setResult(1);// 审核失败
                }
            }
        }catch (Exception e){
            jsonResult.setResult(1);// 审核失败
            logger.error(e.getMessage());
        }
        return jsonResult;
    }


}