/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：drivingBehaviorAnalysisController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-10-19
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment;
import com.cn.gazelle.logistics.pojo.T_Data_OBD_Equipment_Truck_Binding;
import com.cn.gazelle.logistics.service.*;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称：drivingBehaviorAnalysisController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/drivingBehaviorAnalysisManager")
public class DrivingBehaviorAnalysisController {
    Logger logger = Logger.getLogger(DrivingBehaviorAnalysisController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private PersonService personService;
    @Resource
    private WorkConditionDetailService workConditionDetailService;
    @Resource
    private OBDequipmentService obdEquipmentService;
    @Resource
    private OBDEquipmentTruckBindingService obdEquipmentTruckBindingService;
    @Resource
    private TruckPositionService truckPositionService;

    /**
     * 方法名称：drivingBehaviorAnalysisList
     * 内容摘要：驾驶行为分析列表页。
     *
     * @param request      request
     * @param response     response
     * @param person_name  司机
     * @param plate_number 车牌
     * @param start_time   起始时间
     * @param end_time     终止时间
     * @param model        model
     */
    @RequestMapping(value = "drivingBehaviorAnalysisList")
    public void userOperationList(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(required = false, defaultValue = "") String person_name,
                                  @RequestParam(required = false, defaultValue = "") String plate_number,
                                  @RequestParam(required = false, defaultValue = "") String start_time,
                                  @RequestParam(required = false, defaultValue = "") String end_time,
                                  ModelMap model) {
        System.out.println("person_name=" + person_name);
        System.out.println("plate_number=" + plate_number);
        System.out.println("start_time=" + start_time);
        System.out.println("end_time=" + end_time);
        List<HashMap<String, String>> personInforList = new ArrayList<HashMap<String, String>>();
        List<HashMap<String, String>> drivingBehaviorInforList = new ArrayList<HashMap<String, String>>();
        T_Data_OBD_Equipment_Truck_Binding obdEquipmentTruckBinding = null;
        T_Data_OBD_Equipment obdEquipment = null;
        int driving_days = 0;
        long startTime=System.currentTimeMillis();   //获取开始时间
        try {
            personInforList = this.personService.queryDrivingBehaviorInfo(person_name, plate_number);
            logger.info("personInforList=" + personInforList);
            for (HashMap<String, String> personInfo : personInforList) {
                HashMap<String, String> drivingBehaviorInfor = new HashMap<String, String>(); // 循环一次要初始化一次
                HashMap<String, String> workConditionDetailInfo = new HashMap<String, String>(); // 循环一次要初始化一次
                obdEquipmentTruckBinding = this.obdEquipmentTruckBindingService.findOBDTruckBindingInfoByTruckID
                        (personInfo.get("TRUCK_ID"));
                // 判断车辆是否绑定obd设备
                if (obdEquipmentTruckBinding != null) {
                    drivingBehaviorInfor.put("member_name", personInfo.get("MEMBER_NAME"));
                    drivingBehaviorInfor.put("person_name", personInfo.get("PERSON_NAME"));
                    drivingBehaviorInfor.put("truck_id", personInfo.get("TRUCK_ID"));
                    drivingBehaviorInfor.put("plate_number", personInfo.get("PLATE_NUMBER"));
                    obdEquipment = this.obdEquipmentService.findById(obdEquipmentTruckBinding.getEquipment_id());
                    workConditionDetailInfo = this.workConditionDetailService
                            .queryWorkConditionDetailOfDriving(obdEquipment.getObd_id(), start_time, end_time);
                    driving_days = this.truckPositionService.findDrivingDaysByObdId(obdEquipment.getObd_id(), start_time, end_time);
                    drivingBehaviorInfor.put("driving_days", driving_days + "");
                    drivingBehaviorInfor.putAll(workConditionDetailInfo);
                    drivingBehaviorInforList.add(drivingBehaviorInfor);
                }
            }
            logger.info("drivingBehaviorInforList=" + JSONUtil.toJSONString(drivingBehaviorInforList));
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(drivingBehaviorInforList));
            long endTime=System.currentTimeMillis();   //获取开始时间
            logger.info("代码运行时间："+(endTime-startTime)/60000 + "分钟");
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
    }

    /**
     * 方法名称：drivingBehaviorAnalysisListExport
     * 内容摘要：导出驾驶行为分析报表excel
     *
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "drivingBehaviorAnalysisListExport")
    public void drivingBehaviorAnalysisListExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("domainPath=" + domainPath);
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/userOperationExport.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);

            try {
                String fullFileName = request.getSession().getServletContext().getRealPath("/") + "behaviorAna.json";
                File file = new File(fullFileName);
                Scanner scanner = null;
                StringBuilder buffer = new StringBuilder();
                try {
                    scanner = new Scanner(file, "UTF-8");
                    while (scanner.hasNextLine()) {
                        buffer.append(scanner.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                } finally {
                    if (scanner != null) {
                        scanner.close();
                    }
                }
                String tempData = buffer.toString();

                // 解析json为数组
                Gson gson = new Gson();

                if (tempData.trim().length() > 0) {
                    // json转为带泛型的list
                    List<Map<String, String>> list = gson.fromJson(tempData, new TypeToken<List<Map<String, String>>>() {
                    }.getType());
                    // 将数据写入excel
                    int i = 2;  // 起始行数，0为第一行
                    for (Map<String, String> map : list) {
                        Row row = sheet.createRow(i);
                        i++;
                        row.createCell(0).setCellValue(map.get("yhm"));
                        row.createCell(1).setCellValue(map.get("sj"));
                        row.createCell(2).setCellValue(map.get("jsts"));
                        row.createCell(3).setCellValue(map.get("pjdf"));
                        row.createCell(4).setCellValue(map.get("csts"));
                        row.createCell(5).setCellValue(map.get("jjscs"));
                        row.createCell(6).setCellValue(map.get("jscs"));
                        row.createCell(7).setCellValue(map.get("zsggcs"));
                        row.createCell(8).setCellValue(map.get("cscbsc"));
                        row.createCell(9).setCellValue(map.get("cssc"));
                        row.createCell(10).setCellValue(map.get("pljscs"));
                        row.createCell(11).setCellValue(map.get("pljssc"));
                        row.createCell(12).setCellValue(map.get("jzwcs"));
                        row.createCell(13).setCellValue(map.get("jbdcs"));
                        row.createCell(14).setCellValue(map.get("jy"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("用户运营报表", "UTF-8");
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

}
