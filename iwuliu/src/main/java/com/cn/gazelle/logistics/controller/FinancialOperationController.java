/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：FinancialOperationController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-09-26
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类 名 称：FinancialOperationController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@Controller
@RequestMapping(value = "/financialOperationManager")
public class FinancialOperationController {
    Logger logger = Logger.getLogger(FinancialOperationController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    /**
     * 方法名称：home
     * 内容摘要：财务运营报表
     *
     * @param model model
     * @return string 财务运营报表
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model) {
        return "financialOperationManager/financialOperationManager";
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
    public void financialOperationList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String beginDate,
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


}
