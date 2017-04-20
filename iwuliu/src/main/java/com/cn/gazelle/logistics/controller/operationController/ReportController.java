/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ReportController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-26
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_Report_Manager_Table;
import com.cn.gazelle.logistics.service.ReportService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称：ReportController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *@authot YK
 */
@Controller
@RequestMapping(value = "/reportManager")
public class ReportController {
    Logger logger = Logger.getLogger(ReportController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private ReportService reportService;

    /**
     * 方法名称：reportManager_home
     * 内容摘要：报表管理页面。
     *
     * @param model model
     * @return string 报表管理页面
     */
    @RequestMapping(value = "home")
    public String reportManager_home(ModelMap model) {
        return "operationManager/reportManager/reportManager";
    }

    /**
     * 方法名称：truckTransportCapacity_home
     * 内容摘要：卡车运力管理页面。
     *
     * @param model model
     * @return string 卡车运力管理页面
     */
    @RequestMapping(value = "truckTransportCapacity_home")
    public String truckTransportCapacityManager_home(ModelMap model) {
        return "operationManager/truckTransportCapacityManager/truckTransportCapacityManager";
    }

     /**
     * 方法名称：driverAttendanceManager_home
     * 内容摘要：司机出勤率。
     *
     * @param model model
     * @return string 司机出勤率页面
     */
    @RequestMapping(value = "driverAttendanceManager_home")
    public String driverAttendanceManager_home(ModelMap model) {
        return "operationManager/driverAttendanceManager/driverAttendanceManager";
    }

    /**
     * 方法名称：driverCreditManager_home
     * 内容摘要：司机信用。
     *
     * @param model model
     * @return string 司机信用页面
     */
    @RequestMapping(value = "driverCreditManager_home")
    public String driverCreditManager_home(ModelMap model) {
        return "operationManager/driverCreditManager/driverCreditManager";
    }

    /**
     * 方法名称：vehicleOperationManager_home
     * 内容摘要：车辆运营。
     *
     * @param model model
     * @return string 车辆运营页面
     */
    @RequestMapping(value = "vehicleOperationManager_home")
    public String vehicleOperationManager_home(ModelMap model) {
        return "operationManager/vehicleOperationManager/vehicleOperationManager";
    }

    /**
     * 方法名称：truckOperationList
     * 内容摘要：车辆运营列表页。
     *
     * @param request   request
     * @param response  response
     * @param endDate   起始时间
     * @param beginDate 终止时间
     * @param model     model
     */
    @RequestMapping(value = "truckOperationList")
    public void truckOperationList(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(required = false, defaultValue = "") String beginDate,
                                  @RequestParam(required = false, defaultValue = "") String endDate,
                                  ModelMap model) {
        String fullFileName = request.getSession().getServletContext().getRealPath("/") + "truckOperationData.json";
        List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
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

        Gson gson = new Gson();
        // json转为带泛型的list
        List<Map<String, String>> list = gson.fromJson(buffer.toString(), new TypeToken<List<Map<String, String>>>() {
        }.getType());

        String date = null;
        long dateLong;
        if (!beginDate.equals("") && !endDate.equals("")) {
            for (int i = 0; i < list.size(); i++) {
                date = list.get(i).get("ccsj");
                dateLong = Long.valueOf(date.replaceAll("[-\\s:]", ""));
                if (dateLong >= Long.valueOf(beginDate.replaceAll("[-\\s:]", ""))
                        && dateLong <= Long.valueOf(endDate.replaceAll("[-\\s:]", ""))) {
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));

        } else {
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }
    }


    /**
     * 方法名称：truckOperationExcel
     * 内容摘要：导出车辆运营报表excel
     *
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "truckOperationExcel")
    public void truckOperationExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/truckOperationExcel.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);

            try {
                String fullFileName = request.getSession().getServletContext().getRealPath("/") + "truckOperationData.json";

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
                    int i = 2;
                    for (Map<String, String> map : list) {
                        Row row = sheet.createRow(i);
                        i++;
                        row.createCell(0).setCellValue(map.get("fzsj"));
                        row.createCell(1).setCellValue(map.get("cph"));
                        row.createCell(2).setCellValue(map.get("cx"));
                        row.createCell(3).setCellValue(map.get("ccsj"));
                        row.createCell(4).setCellValue(map.get("wcsj"));
                        row.createCell(5).setCellValue(map.get("yszds"));
                        row.createCell(6).setCellValue(map.get("yzzje"));
                        row.createCell(7).setCellValue(map.get("jyzss"));
                        row.createCell(8).setCellValue(map.get("jqzs"));
                        row.createCell(9).setCellValue(map.get("yqje"));
                        row.createCell(10).setCellValue(map.get("glzcs"));
                        row.createCell(11).setCellValue(map.get("glzfy"));
                        row.createCell(12).setCellValue(map.get("wxzje"));
                        row.createCell(13).setCellValue(map.get("byzje"));
                        row.createCell(14).setCellValue(map.get("qtfy"));
                        row.createCell(15).setCellValue(map.get("zsx"));
                        row.createCell(16).setCellValue(map.get("zlc"));
                        row.createCell(17).setCellValue(map.get("xfzje"));
                        row.createCell(18).setCellValue(map.get("zyf"));
                        row.createCell(19).setCellValue(map.get("zsy"));
                        row.createCell(20).setCellValue(map.get("bz"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("车辆运营报表", "UTF-8");
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
     * 方法名称：financialOperationManager_home
     * 内容摘要：财务运营。
     *
     * @param model model
     * @return string 财务运营页面
     */
    @RequestMapping(value = "financialOperationManager_home")
    public String financialOperationManager_home(ModelMap model) {
        return "operationManager/financialOperationManager/financialOperationManager";
    }

    /**
     * 方法名称：financialOperationList
     * 内容摘要：财务运营列表页。
     *
     * @param request   request
     * @param response  response
     * @param endDate   起始时间
     * @param beginDate 终止时间
     * @param model     model
     */
    @RequestMapping(value = "financialOperationList")
    public void financialOperationList(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(required = false, defaultValue = "") String beginDate,
                                       @RequestParam(required = false, defaultValue = "") String endDate,
                                       ModelMap model) {
        System.out.println("beginDate=" + beginDate);
        System.out.println("endDate=" + endDate);
        String fullFileName = request.getSession().getServletContext().getRealPath("/")+"financial.json";
        Map result = new HashMap();
        List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
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

        System.out.println(buffer.toString());
        Gson gson = new Gson();
        // json转为带泛型的list
        List<Map<String, String>> list = gson.fromJson(buffer.toString(), new TypeToken<List<Map<String, String>>>() {
        }.getType());
        String date = null;
        long dateLong;
        if (!beginDate.equals("") && !endDate.equals("")) {
            for (int i = 0; i < list.size(); i++) {
                date = list.get(i).get("rq");
                dateLong = Long.valueOf(date.replaceAll("[-\\s:]", ""));
                if (dateLong >= Long.valueOf(beginDate.replaceAll("[-\\s:]", ""))
                        && dateLong <= Long.valueOf(endDate.replaceAll("[-\\s:]", ""))) {
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));

        } else {
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }
    }
    /**
     * 方法名称：financialOperationExport
     * 内容摘要：导出财务运营报表excel
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "financialOperationExport")
    public void financialOperationExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/financialOperationExport.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);

            try {
                String fullFileName = "F:\\2.1最新版\\src\\main\\webapp\\financial.json";

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
                        row.createCell(0).setCellValue(map.get("xh"));
                        row.createCell(1).setCellValue(map.get("rq"));
                        row.createCell(2).setCellValue(map.get("srye"));
                        row.createCell(3).setCellValue(map.get("sryf"));
                        row.createCell(4).setCellValue(map.get("srbx"));
                        row.createCell(5).setCellValue(map.get("srcc"));
                        row.createCell(6).setCellValue(map.get("srzx"));
                        row.createCell(7).setCellValue(map.get("srtc"));
                        row.createCell(8).setCellValue(map.get("srhj"));
                        row.createCell(9).setCellValue(map.get("zcrl"));
                        row.createCell(10).setCellValue(map.get("zcwx"));
                        row.createCell(11).setCellValue(map.get("zcby"));
                        row.createCell(12).setCellValue(map.get("zcbx"));
                        row.createCell(13).setCellValue(map.get("zcgl"));
                        row.createCell(14).setCellValue(map.get("zcxc1"));
                        row.createCell(15).setCellValue(map.get("zczc"));
                        row.createCell(16).setCellValue(map.get("zcxc"));
                        row.createCell(17).setCellValue(map.get("zcsh"));
                        row.createCell(18).setCellValue(map.get("zcrg"));
                        row.createCell(19).setCellValue(map.get("zcdb"));
                        row.createCell(20).setCellValue(map.get("zchj"));
                        row.createCell(21).setCellValue(map.get("shloc"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("财务运营报表", "UTF-8");
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
     * 方法名称：userOperationManager_home
     * 内容摘要：用户运营。
     *
     * @param model model
     * @return string 用户运营页面
     */
    @RequestMapping(value = "userOperationManager_home")
    public String userOperationManager_home(ModelMap model) {
        return "operationManager/userOperationManager/userOperationManager";
    }

    /**
     * 方法名称：userOperationList
     * 内容摘要：用户运营列表页。
     *
     * @param request   request
     * @param response  response
     * @param endDate   起始时间
     * @param beginDate 终止时间
     * @param model     model
     */
    @RequestMapping(value = "userOperationList")
    public void userOperationList(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(required = false, defaultValue = "") String beginDate,
                                  @RequestParam(required = false, defaultValue = "") String endDate,
                                  ModelMap model) {
        System.out.println("beginDate=" + beginDate);
        System.out.println("endDate=" + endDate);
        String fullFileName = request.getSession().getServletContext().getRealPath("/") + "user.json";
        List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
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

        System.out.println(buffer.toString());
        Gson gson = new Gson();
        // json转为带泛型的list
        List<Map<String, String>> list = gson.fromJson(buffer.toString(), new TypeToken<List<Map<String, String>>>() {
        }.getType());

        String date = null;
        long dateLong;
        if (!beginDate.equals("") && !endDate.equals("")) {
            for (int i = 0; i < list.size(); i++) {
                date = list.get(i).get("rq");
                dateLong = Long.valueOf(date.replaceAll("[-\\s:]", ""));
                if (dateLong >= Long.valueOf(beginDate.replaceAll("[-\\s:]", ""))
                        && dateLong <= Long.valueOf(endDate.replaceAll("[-\\s:]", ""))) {
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));

        } else {
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }
    }

    /**
     * 方法名称：userOperationExport
     * 内容摘要：导出用户运营报表excel
     *
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "userOperationExport")
    public void userOperationExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                String fullFileName = request.getSession().getServletContext().getRealPath("/") + "user.json";
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
                        row.createCell(0).setCellValue(map.get("rq"));
                        row.createCell(1).setCellValue(map.get("sjzc"));
                        row.createCell(2).setCellValue(map.get("sjls"));
                        row.createCell(3).setCellValue(map.get("sjhy"));
                        row.createCell(4).setCellValue(map.get("sjsd"));
                        row.createCell(5).setCellValue(map.get("sjtg"));
                        row.createCell(6).setCellValue(map.get("czzc"));
                        row.createCell(7).setCellValue(map.get("czls"));
                        row.createCell(8).setCellValue(map.get("czhy"));
                        row.createCell(9).setCellValue(map.get("czsd"));
                        row.createCell(10).setCellValue(map.get("cztg"));
                        row.createCell(11).setCellValue(map.get("czyl"));
                        row.createCell(12).setCellValue(map.get("czxf"));
                        row.createCell(13).setCellValue(map.get("hzzc"));
                        row.createCell(14).setCellValue(map.get("hzls"));
                        row.createCell(15).setCellValue(map.get("hzjy"));
                        row.createCell(16).setCellValue(map.get("hzsd"));
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


     /**
     * 方法名称：platformAndSettlementManager_home
     * 内容摘要：平台结算页面。
     *
     * @param model model
     * @return string 平台结算页面
     */
    @RequestMapping(value = "platformAndSettlementManager_home")
    public String platformAndSettlementManager_home(ModelMap model) {
        return "operationManager/platformAndSettlementManager/platformAndSettlementManager";
    }

    /**
     * 方法名称：platformSettlementList
     * 内容摘要：平台结算列表页。
     *
     * @param request   request
     * @param response  response
     * @param endDate   起始时间
     * @param beginDate 终止时间
     * @param model     model
     */
    @RequestMapping(value = "platformSettlementList")
    public void platformSettlementList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String beginDate,
                                       @RequestParam(required = false, defaultValue = "") String endDate,
                                       ModelMap model) {
        System.out.println("beginDate=" + beginDate);
        System.out.println("endDate=" + endDate);
        String fullFileName = request.getSession().getServletContext().getRealPath("/") + "platform.json";
        Map result = new HashMap();
        List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
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

        System.out.println(buffer.toString());
        Gson gson = new Gson();
        // json转为带泛型的list
        List<Map<String, String>> list = gson.fromJson(buffer.toString(), new TypeToken<List<Map<String, String>>>() {
        }.getType());

        String date = null;
        long dateLong;
        if (!beginDate.equals("") && !endDate.equals("")) {
            for (int i = 0; i < list.size(); i++) {
                date = list.get(i).get("rq");
                dateLong = Long.valueOf(date.replaceAll("[-\\s:]", ""));
                if (dateLong >= Long.valueOf(beginDate.replaceAll("[-\\s:]", ""))
                        && dateLong <= Long.valueOf(endDate.replaceAll("[-\\s:]", ""))) {
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));

        } else {
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }
    }

    /**
     * 方法名称：settlementDetailList
     * 内容摘要：平台结算列表页。
     *
     * @param request   request
     * @param response  response
     * @param beginDateDetail   起始时间
     * @param endDateDetail     终止时间
     * @param model     model
     */
    @RequestMapping(value = "settlementDetailList")
    public void settlementDetailList(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = false, defaultValue = "") String settlementNum,
                                     @RequestParam(required = false, defaultValue = "") String itemName,
                                     @RequestParam(required = false, defaultValue = "") String beginDateDetail,
                                     @RequestParam(required = false, defaultValue = "") String endDateDetail,
                                     ModelMap model) {
        System.out.println("settlementNum=" + settlementNum);
        System.out.println("itemName=" + itemName);
        System.out.println("beginDateDetail=" + beginDateDetail);
        System.out.println("endDateDetail=" + endDateDetail);
        String fullFileName = request.getSession().getServletContext().getRealPath("/") + "settlement.json";
        Map result = new HashMap();
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
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

        System.out.println(buffer.toString());
        Gson gson = new Gson();
        // json转为带泛型的list
        List<Map<String, String>> list = gson.fromJson(buffer.toString(), new TypeToken<List<Map<String, String>>>() {
        }.getType());
        if(!settlementNum.equals("")&&itemName.equals("")&&(beginDateDetail.equals("")||endDateDetail.equals(""))){
            List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
            for (int i = 0;i<list.size();i++){
                if (settlementNum.equals(list.get(i).get("js"))){
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));
        }
        else if (settlementNum.equals("")&&!itemName.equals("")&&(beginDateDetail.equals("")||endDateDetail.equals(""))){
            List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
            for (int i = 0;i<list.size();i++){
                if (itemName.equals(list.get(i).get("mc"))){
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));

        }
        else if (settlementNum.equals("")&&itemName.equals("")&&(!beginDateDetail.equals("")&&!endDateDetail.equals(""))){
            List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
            for (int i = 0;i<list.size();i++){
                String date = list.get(i).get("ds");
                long dateLong = Long.valueOf(date.replaceAll("[-\\s:]",""));
                if (dateLong>=Long.valueOf(beginDateDetail.replaceAll("[-\\s:]",""))
                        &&dateLong<=Long.valueOf(endDateDetail.replaceAll("[-\\s:]",""))){
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));
        }
        else if (!settlementNum.equals("")&&!itemName.equals("")&&(beginDateDetail.equals("")||endDateDetail.equals(""))){
            List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
            for (int i = 0;i<list.size();i++){
                if (settlementNum.equals(list.get(i).get("js"))&&itemName.equals(list.get(i).get("mc"))){
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));
        }
        else if (!settlementNum.equals("")&&itemName.equals("")&&(!beginDateDetail.equals("")&&!endDateDetail.equals(""))){
            List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
            for (int i = 0;i<list.size();i++){
                String date = list.get(i).get("ds");
                long dateLong = Long.valueOf(date.replaceAll("[-\\s:]",""));
                if (dateLong>=Long.valueOf(beginDateDetail.replaceAll("[-\\s:]",""))
                        &&dateLong<=Long.valueOf(endDateDetail.replaceAll("[-\\s:]",""))
                        &&settlementNum.equals(list.get(i).get("js"))){
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));
        }
        else if (settlementNum.equals("")&&!itemName.equals("")&&(!beginDateDetail.equals("")&&!endDateDetail.equals(""))){
            List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
            for (int i = 0;i<list.size();i++){
                String date = list.get(i).get("ds");
                long dateLong = Long.valueOf(date.replaceAll("[-\\s:]",""));
                if (dateLong>=Long.valueOf(beginDateDetail.replaceAll("[-\\s:]",""))
                        &&dateLong<=Long.valueOf(endDateDetail.replaceAll("[-\\s:]",""))
                        &&itemName.equals(list.get(i).get("mc"))){
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));
        }
        else if (!settlementNum.equals("")&&!itemName.equals("")&&(!beginDateDetail.equals("")&&!endDateDetail.equals(""))){
            List<Map<String, String>> list_search = new ArrayList<Map<String, String>>();
            for (int i = 0;i<list.size();i++){
                String date = list.get(i).get("ds");
                long dateLong = Long.valueOf(date.replaceAll("[-\\s:]",""));
                if (dateLong>=Long.valueOf(beginDateDetail.replaceAll("[-\\s:]",""))
                        &&dateLong<=Long.valueOf(endDateDetail.replaceAll("[-\\s:]",""))
                        &&itemName.equals(list.get(i).get("mc"))&&settlementNum.equals(list.get(i).get("js"))){
                    list_search.add(list.get(i));
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list_search));
        }
        else {
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        }
    }

    /**
     * 方法名称：dispatchDetailList
     * 内容摘要：平台结算列表页。
     *
     * @param request   request
     * @param response  response
     * @param model     model
     */
    @RequestMapping(value = "dispatchDetailList")
    public void dispatchDetailList(HttpServletRequest request, HttpServletResponse response,
                                   ModelMap model) {
        String fullFileName = request.getSession().getServletContext().getRealPath("/") + "dispatch.json";
        Map result = new HashMap();
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
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

        System.out.println(buffer.toString());
        Gson gson = new Gson();
        // json转为带泛型的list
        List<Map<String, String>> list = gson.fromJson(buffer.toString(), new TypeToken<List<Map<String, String>>>() {
        }.getType());
        ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
    }
    /**
     * 方法名称：platformExport
     * 内容摘要：导出结算单汇总表excel
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "platformExport")
    public void platformExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/platformExport.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);

            try {
                String fullFileName = request.getSession().getServletContext().getRealPath("/") + "platform.json";
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
                    int i = 1;  // 起始行数，0为第一行
                    for (Map<String, String> map : list) {
                        Row row = sheet.createRow(i);
                        i++;
                        row.createCell(0).setCellValue(map.get("rq"));
                        row.createCell(1).setCellValue(map.get("dd"));
                        row.createCell(2).setCellValue(map.get("zc"));
                        row.createCell(3).setCellValue(map.get("ys"));
                        row.createCell(4).setCellValue(map.get("je"));
                        row.createCell(5).setCellValue(map.get("yz"));
                        row.createCell(6).setCellValue(map.get("zr"));
                        row.createCell(7).setCellValue(map.get("js"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("结算单汇总表", "UTF-8");
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
     * 方法名称：settlementExport
     * 内容摘要：导出结算单明细表excel
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "settlementExport")
    public void settlementExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/settlementExport.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);

            try {
                String fullFileName = request.getSession().getServletContext().getRealPath("/") + "settlement.json";
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
                    int i = 1;  // 起始行数，0为第一行
                    for (Map<String, String> map : list) {
                        Row row = sheet.createRow(i);
                        i++;
                        row.createCell(0).setCellValue(map.get("js"));
                        row.createCell(1).setCellValue(map.get("dd"));
                        row.createCell(2).setCellValue(map.get("ds"));
                        row.createCell(3).setCellValue(map.get("mc"));
                        row.createCell(4).setCellValue(map.get("zl"));
                        row.createCell(5).setCellValue(map.get("ms"));
                        row.createCell(6).setCellValue(map.get("cs"));
                        row.createCell(7).setCellValue(map.get("ys"));
                        row.createCell(8).setCellValue(map.get("je1"));
                        row.createCell(9).setCellValue(map.get("yz"));
                        row.createCell(10).setCellValue(map.get("je"));
                        row.createCell(11).setCellValue(map.get("fp"));
                        row.createCell(12).setCellValue(map.get("zt"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("结算单明细表", "UTF-8");
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
     * 方法名称：dispatchExport
     * 内容摘要：导出派单明细表excel
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "dispatchExport")
    public void dispatchExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String domainPath = request.getSession().getServletContext().getRealPath("/");
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            domainPath = domainPath.substring(0, domainPath.lastIndexOf(File.separator));
            String baseUrl = domainPath + File.separator + this.baseUrl;
            String path = baseUrl + "/ExportExcel/dispatchExport.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            Workbook work = new HSSFWorkbook(in);
            // 得到excel的第0张表
            Sheet sheet = work.getSheetAt(0);

            try {
                String fullFileName = request.getSession().getServletContext().getRealPath("/") + "dispatch.json";
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
                    int i = 1;  // 起始行数，0为第一行
                    for (Map<String, String> map : list) {
                        Row row = sheet.createRow(i);
                        i++;
                        row.createCell(0).setCellValue(map.get("dd"));
                        row.createCell(1).setCellValue(map.get("pd"));
                        row.createCell(2).setCellValue(map.get("pc"));
                        row.createCell(3).setCellValue(map.get("cp"));
                        row.createCell(4).setCellValue(map.get("gb"));
                        row.createCell(5).setCellValue(map.get("sj"));
                        row.createCell(6).setCellValue(map.get("ys"));
                        row.createCell(7).setCellValue(map.get("je"));
                        row.createCell(8).setCellValue(map.get("yz"));
                        row.createCell(9).setCellValue(map.get("js"));
                        row.createCell(10).setCellValue(map.get("fp"));
                        row.createCell(11).setCellValue(map.get("zt"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String address = simpleFormat.format(new Date());
            OutputStream os = response.getOutputStream();// 取得输出流
            response.setContentType("application/vnd.ms-excel");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("派单单明细表", "UTF-8");
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
     * 方法名称：drivingBehavior_home
     * 内容摘要：驾驶行为分析页面。
     *
     * @param model model
     * @return string 驾驶行为分析页面
     */
    @RequestMapping(value = "drivingBehavior_home")
    public String drivingBehavior_home(ModelMap model) {
        return "operationManager/drivingBehaviorManager/drivingBehaviorManager";
    }


    /**
     * 方法名称：reportManagerList
     * 内容摘要：报表管理页面。
     *
     * @param model model
     * @return string 报表管理页面
     */
    @RequestMapping(value = "reportManagerList")
    public void reportManagerList(@RequestParam(required = false, defaultValue = "") String report_name,
                                        @RequestParam(required = false, defaultValue = "") String create_time_start,
                                        @RequestParam(required = false, defaultValue = "") String create_time_end,
                                        HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        System.out.println("report_name=" + report_name);
        System.out.println("create_time_start=" + create_time_start);
        System.out.println("create_time_end=" + create_time_end);
        List<T_Data_Report_Manager_Table> reportManagerTableList = null;
        HashMap<String, String> conditions = new HashMap<String, String>();
        try {
            conditions.put("report_name", report_name);
            conditions.put("create_time_start", create_time_start);
            conditions.put("create_time_end", create_time_end);
            logger.info(JSONUtil.toJSON(conditions));
            reportManagerTableList = this.reportService.getReportManagerInfo(conditions);
            logger.info("list=" + JSONUtil.toJSONString(reportManagerTableList));
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(reportManagerTableList));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }
}
