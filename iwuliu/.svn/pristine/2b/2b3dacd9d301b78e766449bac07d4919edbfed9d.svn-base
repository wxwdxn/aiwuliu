/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：DriverController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-10
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Data_Operation_Drivers_Manager;
import com.cn.gazelle.logistics.pojo.T_Data_Person;
import com.cn.gazelle.logistics.pojo.T_Data_Vehicle_Detail;
import com.cn.gazelle.logistics.service.PersonService;
import com.cn.gazelle.logistics.service.VehicleDetailService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
 * 类 名 称：DriverController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/driverManager")
public class DriverController {
    Logger logger = Logger.getLogger(DriverController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private PersonService personService;
    @Resource
    private VehicleDetailService vehicleDetailService;

    /**
     * 方法名称：driverManager_home
     * 内容摘要：司机管理页面。
     *
     * @param model model
     * @return string 司机管理详情页面
     */
    @RequestMapping(value = "home")
    public String driverManager_home(ModelMap model) {
        return "operationManager/driverManager/driverManager";
    }

    /**
     * 方法名称：driverManagerDetail_home
     * 内容摘要：司机管理详情页面。
     *
     * @param model model
     * @return string 司机管理详情页面
     */
    @RequestMapping(value = "driverManagerDetail_home")
    public String driverManagerDetail_home(ModelMap model) {
        return "operationManager/driverManagerDetail/driverManagerDetail";
    }

    /**
     * 方法名称：driverManagerNewAdd_home
     * 内容摘要：司机管理新增页面。
     *
     * @param model model
     * @return string 司机管理新增页面
     */
    @RequestMapping(value = "driverManagerNewAdd_home")
    public String driverManagerNewAdd_home(ModelMap model) {
        return "operationManager/driverManagerNewAdd/driverManagerNewAdd";
    }

    /**
     * 方法名称：driverManagerList
     * 内容摘要：司机管理页面列表。
     *
     * @param model model
     * @return string 司机管理页面列表
     */
    @RequestMapping(value = "driverManagerList")
    public void driverManagerList(@RequestParam(required = false, defaultValue = "") String organization_type,
                                  @RequestParam(required = false, defaultValue = "") String company_name,
                                  @RequestParam(required = false, defaultValue = "") String owner_name,
                                  @RequestParam(required = false, defaultValue = "") String crew_name,
                                  @RequestParam(required = false, defaultValue = "") String ID_type,
                                  @RequestParam(required = false, defaultValue = "") String ID_num,
                                  @RequestParam(required = false, defaultValue = "") String plate_number,
                                  @RequestParam(required = false, defaultValue = "") String verify_status,
                                  HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        System.out.println("organization_type=" + organization_type);
        System.out.println("company_name=" + company_name);
        System.out.println("owner_name=" + owner_name);
        System.out.println("crew_name=" + crew_name);
        System.out.println("ID_type=" + ID_type);
        System.out.println("ID_num=" + ID_num);
        System.out.println("plate_number=" + plate_number);
        System.out.println("verify_status=" + verify_status);
        List<T_Data_Operation_Drivers_Manager> driversManagerList = null;
        HashMap<String, String> conditions = new HashMap<String, String>();
        String id_card_number = null;            // 身份证
        String driver_licence_number = null;     // 驾驶证档案编号
        try {
            conditions.put("organization_type", organization_type);              // 机构类型
            conditions.put("company_name", company_name);                        // 机构名称
            conditions.put("owner_name", owner_name);                            // 车主姓名
            conditions.put("crew_name", crew_name);                              // 车组成员
            // 0 代表身份证类型
            if (ID_type.equals("0")) {
                conditions.put("id_card_number", ID_num);
            }
            // 1 代表驾驶证类型
            else if (ID_type.equals("1")) {
                conditions.put("driver_licence_number", ID_num);
            }
            conditions.put("plate_number", plate_number);
            conditions.put("verify_status", verify_status);
            logger.info(JSONUtil.toJSON(conditions));
            driversManagerList = this.personService.findOperationDriversManagerList(conditions);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(driversManagerList));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：driverExport
     * 内容摘要：导出司机管理报表到excel
     *
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "driverExport")
    public void driverExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("domainPath=" + domainPath);
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/driverExport.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            List<T_Data_Operation_Drivers_Manager> operationDriversManagerList = null;
            HashMap<String, String> conditions = new HashMap<String, String>();
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);
            try {
                // 将数据写入excel
                operationDriversManagerList = this.personService.findOperationDriversManagerList(conditions);
                int i = 2;  // 起始行数，0为第一行
                for (T_Data_Operation_Drivers_Manager operationDriversManager : operationDriversManagerList) {
                    Row row = sheet.createRow(i);
                    i++;
                    row.createCell(0).setCellValue(operationDriversManager.getOrganization_type());
                    row.createCell(1).setCellValue(operationDriversManager.getCompany_name());
                    row.createCell(2).setCellValue(operationDriversManager.getOwner_name());
                    row.createCell(3).setCellValue(operationDriversManager.getOwner_phone());
                    row.createCell(4).setCellValue(operationDriversManager.getCrew_name());
                    row.createCell(5).setCellValue(operationDriversManager.getCrew_phone());
                    row.createCell(6).setCellValue("身份证");
                    row.createCell(7).setCellValue(operationDriversManager.getId_card_number());
                    row.createCell(8).setCellValue("驾驶证");
                    row.createCell(9).setCellValue(operationDriversManager.getDriver_licence_number());
                    row.createCell(9).setCellValue(operationDriversManager.getPlate_number());
                    row.createCell(10).setCellValue(operationDriversManager.getTruck_carriage_type_name());
                    row.createCell(11).setCellValue(operationDriversManager.getLine());
                    row.createCell(12).setCellValue(operationDriversManager.getDriving_status());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("运营司机管理", "UTF-8");
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
     * 方法名称：getDriverDetailInfo
     * 内容摘要：获取司机管理详情信息
     *
     * @param response response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "getDriverDetailInfo")
    public Map<String, String> getDriverDetailInfo(@RequestParam("detailInfo") String detailInfo,
                                                   HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
//        String detailInfo = request.getParameter("detailInfo");
        detailInfo = URLDecoder.decode(detailInfo, "UTF-8");
        logger.info("detailInfo=" + detailInfo);
        Map<String, String> result = new HashMap<String, String>();
        Map<String, String> truckInfoMap = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
        T_Data_Person person_owner = null;
        T_Data_Person person_crew = null;
        T_Data_Vehicle_Detail vehicleDetail = null;
        // 解析json
        Gson gson = new Gson();
        try {
            // json转为带泛型的map
            List<Map<String, String>> detailList = gson.fromJson(detailInfo, new TypeToken<List<Map<String, String>>>() {
            }.getType());
            // 车辆信息
            logger.info("plate_number=" + detailList.get(0).get("plate_number"));
            vehicleDetail = this.vehicleDetailService.findVehicleDetail(detailList.get(0).get("plate_number"),
                    detailList.get(0).get("organization_type"));
            // json转为带泛型的map
            truckInfoMap = gson.fromJson(JSONUtil.toJSONString(vehicleDetail), new TypeToken<Map<String, String>>() {
            }.getType());
            // 人员信息--------------
            result.put("organization_type", detailList.get(0).get("organization_type"));
            result.put("company_name", detailList.get(0).get("company_name"));
            result.put("owner_name", detailList.get(0).get("owner_name"));
            result.put("owner_phone", detailList.get(0).get("owner_phone"));
            result.put("id_card_number", detailList.get(0).get("id_card_number"));
            result.put("driver_licence_number", detailList.get(0).get("driver_licence_number"));
            // 查找车主的身份证正反面
            person_owner = this.personService.findPersonByName(detailList.get(0).get("owner_name"),
                    detailList.get(0).get("id_card_number"), "1");
            if (person_owner != null) {
                result.put("id_card_front_pic_id", person_owner.getId_card_front_pic_id());
                result.put("id_card_back_pic_id", person_owner.getId_card_back_pic_id());
                result.put("driver_licence_main_pic_id", person_owner.getDriver_licence_main_pic_id());
                result.put("driver_licence_sub_pic_id", person_owner.getDriver_licence_sub_pic_id());
                result.put("qualification_certificate_number_pic_id", person_owner.getQualification_certificate_number_pic_id());
                result.put("qualification_certificate_number", person_owner.getQualification_certificate_number());
            }
            // ---------------人员信息
            // 车组成员信息----------------
            result.put("crew_name", detailList.get(0).get("crew_name"));
            result.put("crew_phone", detailList.get(0).get("crew_phone"));
            person_crew = this.personService.findPersonByPhone(detailList.get(0).get("crew_phone"), "1");
            if (person_crew != null) {
                result.put("id_card_number_crew", person_crew.getId_card_number());
                result.put("id_card_front_pic_id_crew", person_crew.getId_card_front_pic_id());
                result.put("id_card_back_pic_id_crew", person_crew.getId_card_back_pic_id());
                result.put("driver_licence_number_crew", person_crew.getDriver_licence_number());
                result.put("driver_licence_main_pic_id_crew", person_crew.getDriver_licence_main_pic_id());
                result.put("driver_licence_sub_pic_id_crew", person_crew.getDriver_licence_sub_pic_id());
                result.put("qualification_certificate_number_pic_id_crew", person_crew.getQualification_certificate_number_pic_id());
                result.put("qualification_certificate_number_crew", person_crew.getQualification_certificate_number());
                result.put("verify_refused_reason", person_crew.getVerify_refused_reason());
                result.put("driving_status", person_crew.getDriving_status());
                if (person_crew.getSubmit_relate_time() != null) {
                    result.put("submit_relate_time", sdf.format(person_crew.getSubmit_relate_time()));
                }
                if (person_crew.getConfirm_relate_time() != null) {
                    result.put("confirm_relate_time", sdf.format(person_crew.getConfirm_relate_time()));
                }
                result.put("verify_refused_reason", person_crew.getVerify_refused_reason());
            }
            result.put("verify_status", detailList.get(0).get("crew_phone"));
            // -------------车组成员信息
            result.putAll(truckInfoMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("================" + JSONUtil.toJSONString(result));
        return result;
    }

    /**
     * 方法名称：driverVerify
     * 内容摘要：司机审核。
     *
     * @return string 司机审核
     */
    @RequestMapping(value = "driverVerify")
    @ResponseBody
    public T_Data_JsonResult driverManagerList(@RequestParam(required = false, defaultValue = "") String crew_phone,
                                               @RequestParam(required = false, defaultValue = "") String driving_status,
                                               @RequestParam(required = false, defaultValue = "") String verify_refused_reason,
                                               HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        System.out.println("crew_phone=" + crew_phone);
        System.out.println("driving_status=" + driving_status);
        System.out.println("verify_refused_reason=" + verify_refused_reason);
        T_Data_Person person_crew = null;
        try {
            person_crew = this.personService.findPersonByPhone(crew_phone, "1");
            if (person_crew != null) {
                person_crew.setDriving_status(driving_status);
                person_crew.setVerify_refused_reason(verify_refused_reason);
                boolean b = this.personService.updatePerson(person_crew);
                if (b) {
                    jsonResult.setResult(0);
                } else {
                    jsonResult.setResult(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return jsonResult;
    }

}
