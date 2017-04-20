/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: DictionaryController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：数据字典管理页面
 * 设计文件：
 * 完成日期：2016-01-18
 * 作    者：YK
 * 内容摘要：数据字典管理页面的CRUD
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.pojo.T_Sys_Dictionary;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.DictionaryService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.TruckManager_Message;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: DictionaryController
 * 内容摘要: 数据字典管理页面的CRUD
 * 方法描述：该类有26个方法：
 * 01 home                               字典管理主页
 * 02 dictionaryList                     字典列表页
 * 03 dictionarySave                     保存字典信息
 * 04 dictionaryUpdate                   更新字典信息
 * 05 dictionaryDel                      删除字典信息
 * 06 dicdataList                        字典数据列表页
 * 07 dicdataSave                        保存字典数据信息
 * 08 dicdataUpdate                      更新字典数据信息
 * 09 dicdataDel                         删除数据角色信息
 * 10 sexJson                            获取性别json
 * 11 statusJson                         获取审核状态json
 * 12 provinceJson                       获取全国行政区代码省json
 * 13 cityJson                           获取对应省的城市json
 * 14 areaJson                           获取城市对应的区县json
 * 15 securityJson                       获取安全等级json
 * 16 account_status_json                获取账户状态json
 * 17 lock_type_json                     获取锁定状态json
 * 18 scheduleJson                       获取调度单状态json
 * 19 orderJson                          获取订单状态json
 * 20 balanceType                        获取结算方式json
 * 21 cargoJson                          获取货物种类json
 * 22 scheduleStatus                     获取调度单添加状态json
 * 23 orderStatus                        获取订单状态json
 * 24 contractStatus                     获取合同状态json
 * 25 truckStatusJson                    获取车辆审核状态json
 * 26 repairStatusJsonJson               获取车辆修理状态json
 * 27 stationStatusJson                  获取服务站审核状态json
 * 28 truckLengthIdJson                  车辆长度json
 * 29 truckTypeIdJson                    车辆类型json
 * 30 userTypeJson                       用户类型json
 * 31 firstPartyTypeJson                 获取合同中的甲方企业类型
 * 32 truckTypeJson                      车辆型号json
 *
 * @author YK
 */
@Controller
@RequestMapping(value = "/dictionaryManager")
public class DictionaryController {
    Logger logger = Logger.getLogger(DictionaryController.class);

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private DicdataService dicdataService;

    // ===字典数据json文件 开始
    // 通用
    public String getJson(String dictionary_id) {
        List<T_Sys_Dicdata> dicdataList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            dicdataList = this.dicdataService.findAllDicdataByID(dictionary_id, "%%");
            if (dicdataList.size() > 0 && i < dicdataList.size()) {
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", dicdata.getDicdata_code());
                    results.put("name", dicdata.getDicdata_name());
                    list.add(results);
                    i++;
                }
            }
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
    }

    /**
     * 方法名称：sexJson
     * 内容摘要：获取性别json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "sexJson")
    public String sexJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("F26ACEE58D0041169AD0A183AAF83D10"));
        return getJson("F26ACEE58D0041169AD0A183AAF83D10");
    }

    /**
     * 方法名称：statusJson
     * 内容摘要：获取审核状态json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "statusJson")
    public String statusJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("A4773A46F84C458B8E195CCACE596836"));
        return getJson("A4773A46F84C458B8E195CCACE596836");
    }

    /**
     * 方法名称：provinceJson
     * 内容摘要：获取全国行政区代码省json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "provinceJson")
    public String provinceJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("2EE02FDAE3BE4F06B8CFDF4425BA74BF"));
        return getJson("2EE02FDAE3BE4F06B8CFDF4425BA74BF");
    }

    /**
     * 方法名称：cityJson
     * 内容摘要：
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "cityJson")
    public String cityJson(String dicdata_code, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String json = null;
        List<T_Sys_Dicdata> dicdataList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            dicdataList = this.dicdataService.findAllDicdataByCode("66029BA5DC964A15B29852FA077327C0", dicdata_code.substring(0, 2) + "%00");
            if (dicdataList.size() > 0 && i < dicdataList.size()) {
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", dicdata.getDicdata_code());
                    results.put("name", dicdata.getDicdata_name());
                    list.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return json;
    }

    /**
     * 方法名称：areaJson
     * 内容摘要：获取城市对应的区县json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "areaJson")
    public String areaJson(String dicdata_code, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String json = null;
        List<T_Sys_Dicdata> dicdataList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            dicdataList = this.dicdataService.findAllDicdataByCode("D7FAF287624242ECB3304B6A62414779", dicdata_code.substring(0, 4) + "%");
            if (dicdataList.size() > 0 && i < dicdataList.size()) {
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", dicdata.getDicdata_code());
                    results.put("name", dicdata.getDicdata_name());
                    list.add(results);
                    i++;
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return json;
    }

    /**
     * 方法名称：securityJson
     * 内容摘要：获取安全等级json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "securityJson")
    public String securityJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("44F719A7CCDD446695890BB18DAFD8B5"));
        return getJson("44F719A7CCDD446695890BB18DAFD8B5");
    }

    /**
     * 方法名称：account_status_json
     * 内容摘要：获取账户状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "account_status_json")
    public String account_status_json(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("D2C45BF156874BA8BC7B984BDC5B09B9"));
        return getJson("F26ACEE58D0041169AD0A183AAF83D10");
    }

    /**
     * 方法名称：lock_type_json
     * 内容摘要：获取锁定状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "lock_type_json")
    public String lock_type_json(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("DC15F52E3C5D4A999DF509FC1A4F1D12"));
        return getJson("DC15F52E3C5D4A999DF509FC1A4F1D12");
    }

    /**
     * 方法名称：scheduleJson
     * 内容摘要：获取调度单状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "scheduleJson")
    public String scheduleJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("6339B4F9A2A04D80A2DDF25F44BA81C1"));
        return getJson("6339B4F9A2A04D80A2DDF25F44BA81C1");
    }

    /**
     * 方法名称：orderJson
     * 内容摘要：获取订单全部状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "orderJson")
    public String orderJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("462EA35D1B0A4DC68F1EFC0852E10FDD"));
        return getJson("462EA35D1B0A4DC68F1EFC0852E10FDD");
    }

    /**
     * 方法名称：balanceType
     * 内容摘要：获取结算方式json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "balanceType")
    public String balanceType(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("28E83A5CA95A47F8BEA1C75800D9FADE"));
        return getJson("28E83A5CA95A47F8BEA1C75800D9FADE");
    }

    /**
     * 方法名称：cargoJson
     * 内容摘要：获取货物种类json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "cargoJson")
    public String cargoJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("12F3C6977F7A427AB456454CE89BB956"));
        return getJson("12F3C6977F7A427AB456454CE89BB956");
    }

    /**
     * 方法名称：scheduleStatus
     * 内容摘要：获取调度单添加状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "scheduleStatus")
    public String scheduleStatus(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("A38DB8E8BBB6492AA46C6167D0C45D88"));
        return getJson("A38DB8E8BBB6492AA46C6167D0C45D88");
    }

    /**
     * 方法名称：orderStatus
     * 内容摘要：获取订单添加状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "orderStatus")
    public String orderStatus(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("295127050CA24AB8AF69580654DE7F45"));
        return getJson("295127050CA24AB8AF69580654DE7F45");
    }

    /**
     * 方法名称：contractStatus
     * 内容摘要：获取合同状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "contractStatus")
    public String contractStatus(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("08193584819C436894F6DBB77B2E8AA1"));
        return getJson("08193584819C436894F6DBB77B2E8AA1");
    }

    /**
     * 方法名称：truckStatusJson
     * 内容摘要：获取车辆审核状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckStatusJson")
    public String truckStatusJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("0D1DFD26128E4E35BBECA6D1F4FCE4FE"));
        return getJson("0D1DFD26128E4E35BBECA6D1F4FCE4FE");
    }

    /**
     * 方法名称：repairStatusJsonJson
     * 内容摘要：获取车辆修理状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "repairStatusJson")
    public String repairStatusJsonJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("1F039E8A4082473FA2C859E521A4A0E4"));
        return getJson("1F039E8A4082473FA2C859E521A4A0E4");
    }

    /**
     * 方法名称：stationStatusJson
     * 内容摘要：获取服务站审核状态json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "stationStatusJson")
    public String stationStatusJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("B928CE5365ED4619AFB467A6BBBD3377"));
        return getJson("B928CE5365ED4619AFB467A6BBBD3377");
    }

    /**
     * 方法名称：truckLengthIdJson
     * 内容摘要：车辆长度json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckLengthIdJson")
    @ResponseBody
    public String truckLengthIdJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Sys_Dicdata> dicdataList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            dicdataList = this.dicdataService.findAllDicdataByID("28E55171F7E24C95956D277C275B24B5", "%%");
            if (dicdataList.size() > 0 && i < dicdataList.size()) {
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", dicdata.getDicdata_id());
                    results.put("name", dicdata.getDicdata_name());
                    list.add(results);
                    i++;
                }
            }
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
    }

    /**
     * 方法名称：truckFuelTypejson
     * 内容摘要：车辆燃料类型json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckFuelTypejson")
    @ResponseBody
    public String truckFuelTypejson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<T_Sys_Dicdata> dicdataList = null;
        int i = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            dicdataList = this.dicdataService.findAllDicdataByID("317058F404DB406DAD8242FFD0FCD6A7", "%%");
            if (dicdataList.size() > 0 && i < dicdataList.size()) {
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    Map<String, String> results = new HashMap<String, String>();
                    results.put("id", dicdata.getDicdata_id());
                    results.put("name", dicdata.getDicdata_name());
                    list.add(results);
                    i++;
                }
            }
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
    }
    /**
     * 方法名称：truckTypeJson
     * 内容摘要：车辆类型json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckTypeJson")
    @ResponseBody
    public String truckTypeJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
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
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
        return JSONUtil.toJSONString(list);
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
        ResponseUtil.outWrite(response, getJson("7EA67370E68D493386B56F9C0E97A943"));
        return getJson("7EA67370E68D493386B56F9C0E97A943");
    }

    /**
     * 方法名称：insuranceCompanyjson
     * 内容摘要：保险公司json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "insuranceCompanyjson")
    public String insuranceCompanyjson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("925A0C34595A45E0BD450B8E4C84DEC6"));
        return getJson("925A0C34595A45E0BD450B8E4C84DEC6");
    }

    /**
     * 方法名称：userTypeJson
     * 内容摘要：用户类型json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "userTypeJson")
    public String userTypeJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("741808870D6F488AA5BEB89502B1AD14"));
        return getJson("741808870D6F488AA5BEB89502B1AD14");
    }

    /**
     * 方法名称：firstPartyTypeJson
     * 内容摘要：获取合同中的甲方企业类型
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "firstPartyTypeJson")
    public String reJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("8065E8F5873A4CBCA0D472389F824F79"));
        return getJson("8065E8F5873A4CBCA0D472389F824F79");
    }

    /**
     * 方法名称：telephoneTypeJson
     * 内容摘要：紧急电话类型
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "telephoneTypeJson")
    public String telephoneTypeJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("292ACDD721D643E9AA173FE719FEDB79"));
        return getJson("292ACDD721D643E9AA173FE719FEDB79");
    }

    /**
     * 方法名称：phoneTypeJson
     * 内容摘要：获取紧急电话类型json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "phoneTypeJson")
    public String phoneTypeJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("292ACDD721D643E9AA173FE719FEDB79"));
        return getJson("292ACDD721D643E9AA173FE719FEDB79");
    }

    /**
     * 方法名称：dispatchStatusJson
     * 内容摘要：查询派车单状态
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "dispatchStatusJson")
    public String dispatchStatusJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("B8EECA266DA94CDF9AAAC29782EE460C"));
        return getJson("B8EECA266DA94CDF9AAAC29782EE460C");
    }

    /**
     * 方法名称：truckCarriageUseableJson
     * 内容摘要：可用状态json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckCarriageUseableJson")
    public String truckCarriageUseableJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("DF338ECE3FC54C9581B52641E2BDD721"));
        return getJson("DF338ECE3FC54C9581B52641E2BDD721");
    }

    /**
     * 方法名称：truckPositionStatusJson
     * 内容摘要：车辆定位状态json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckPositionStatusJson")
    public String truckPositionStatusJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("8468DAB2B3484B3BA5244AD01C06744F"));
        return getJson("8468DAB2B3484B3BA5244AD01C06744F");
    }

    /**
     * 方法名称：obdBrandJson
     * 内容摘要：设备品牌json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "obdBrandJson")
    public String obdBrandJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("F26ACEE58D0041169AD0A183AAF83D11"));
        return getJson("F26ACEE58D0041169AD0A183AAF83D11");
    }

    /**
     * 方法名称：userJson
     * 内容摘要：用户json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "userJson")
    public String userJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("741808870D6F488AA5BEB89502B1AD15"));
        return getJson("741808870D6F488AA5BEB89502B1AD15");
    }

    /**
     * 方法名称：funRoleJson
     * 内容摘要：用户json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "funRoleJson")
    public String funRoleJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("F26ACEE58D0041169AD0A183AAF83D13"));
        return getJson("F26ACEE58D0041169AD0A183AAF83D13");
    }

    /**
     * 方法名称：bankJson
     * 内容摘要：获取银行json。
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "bankJson")
    public String bankJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("40C440A05FD14E7CA187B00BD9EDEE21"));
        return getJson("40C440A05FD14E7CA187B00BD9EDEE21");
    }


    /**
     * 方法名称：dicdataExportExcel
     * 内容摘要：导出字典数据excel
     *
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "dicdataExportExcel")
    public void dicdataExportExcel(String dictionary_id, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        // 传递中文参数编码
        String codedFileName = java.net.URLEncoder.encode("字典数据表", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
        List<T_Sys_Dicdata> list = this.dicdataService.findDicdataByDictionaryID(dictionary_id);
        // 定义一个工作薄
        Workbook workbook = new HSSFWorkbook();
        // 创建一个sheet页
        Sheet sheet = workbook.createSheet("字典数据");
        // 创建一行
        Row row = sheet.createRow(0);
        // 在本行赋值 以0开始
        row.createCell(0).setCellValue("字典名称");
        row.createCell(1).setCellValue("字典代码值");
        row.createCell(2).setCellValue("添加时间");
        row.createCell(3).setCellValue("备注");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 遍历输出
        for (int i = 1; i <= list.size(); i++) {
            T_Sys_Dicdata dicdata = list.get(i - 1);
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(dicdata.getDicdata_name());
            row.createCell(1).setCellValue(dicdata.getDicdata_code());
            row.createCell(2).setCellValue(format.format(dicdata.getDicdata_date()));
            row.createCell(3).setCellValue(dicdata.getDicdata_remark());
        }
        sheet.autoSizeColumn((short) 0); // 调整第一列宽度
        sheet.autoSizeColumn((short) 1); // 调整第二列宽度
        sheet.autoSizeColumn((short) 2); // 调整第三列宽度
        sheet.autoSizeColumn((short) 3); // 调整第四列宽度
        OutputStream fOut = response.getOutputStream();
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
    }

    /**
     * 方法名称：dictionaryExportExcel
     * 内容摘要：导出数据字典表excel
     *
     * @param response response
     * @throws IOException
     */
    @RequestMapping(value = "dictionaryExportExcel")
    public void dictionaryExportExcel(String temID, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        // 传递中文参数编码
        String codedFileName = java.net.URLEncoder.encode("数据字典表", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
        // 定义一个工作薄
        Workbook workbook = new HSSFWorkbook();
        // 创建一个sheet页
        Sheet sheet = workbook.createSheet("数据字典");
        // 创建一行
        Row row = sheet.createRow(0);
        // 在本行赋值 以0开始
        row.createCell(0).setCellValue("字典编码");
        row.createCell(1).setCellValue("字典类型");
        row.createCell(2).setCellValue("字典添加日期");
        row.createCell(3).setCellValue("备注");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (temID != null) {
            List<T_Sys_Dictionary> list = new ArrayList<T_Sys_Dictionary>();
            String[] dictionary_id = temID.split(",");
            for (int i = 0; i < dictionary_id.length; i++) {
                T_Sys_Dictionary dictionary1 = dictionaryService.findDictionaryByID(dictionary_id[i]);
                list.add(dictionary1);
            }
            // 遍历输出
            for (int i = 1; i <= list.size(); i++) {
                T_Sys_Dictionary dictionary = list.get(i - 1);
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(dictionary.getDictionary_id());
                row.createCell(1).setCellValue(dictionary.getDictionary_type());
                row.createCell(2).setCellValue(format.format(dictionary.getDictionary_date()));
                row.createCell(3).setCellValue(dictionary.getDictionary_remark());
            }
            sheet.autoSizeColumn((short) 0); // 调整第一列宽度
            sheet.autoSizeColumn((short) 1); // 调整第二列宽度
            sheet.autoSizeColumn((short) 2); // 调整第三列宽度
            sheet.autoSizeColumn((short) 3); // 调整第四列宽度
            OutputStream fOut = response.getOutputStream();
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
        } else {
            List<T_Sys_Dictionary> list = this.dictionaryService.findDictionaryList();
            // 遍历输出
            for (int i = 1; i <= list.size(); i++) {
                T_Sys_Dictionary dictionary = list.get(i - 1);
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(dictionary.getDictionary_id());
                row.createCell(1).setCellValue(dictionary.getDictionary_type());
                row.createCell(2).setCellValue(format.format(dictionary.getDictionary_date()));
                row.createCell(3).setCellValue(dictionary.getDictionary_remark());
            }
            sheet.autoSizeColumn((short) 0); // 调整第一列宽度
            sheet.autoSizeColumn((short) 1); // 调整第二列宽度
            sheet.autoSizeColumn((short) 2); // 调整第三列宽度
            sheet.autoSizeColumn((short) 3); // 调整第四列宽度
            OutputStream fOut = response.getOutputStream();
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
        }
    }

    /**
     * 方法名称：dictionaryExportPdf
     * 内容摘要：导出数据字典表pdf
     *
     * @param response response
     * @throws Exception
     */
    @RequestMapping(value = "dictionaryExportPdf")
    public void dictionaryExportPdf(String temID, HttpServletResponse response) throws Exception {
        // 传递中文参数编码
        String codedFileName = java.net.URLEncoder.encode("数据字典表", "UTF-8");
        response.setContentType("application/pdf;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + codedFileName + ".pdf");
        Rectangle rect = new Rectangle(PageSize.A4);// 设置页面大小
        Document doc = new Document(rect);
        doc.setMargins(10, 10, 10, 10); // 左，右，上，下
        PdfWriter writer = PdfWriter.getInstance(doc, response.getOutputStream());// 创建书写器(Writer)与document对象关联，通过书写器可以将文档写入磁盘中

        //设置作者信息
        doc.addAuthor("瞪羚科技");
        //设置文档创建日期
        doc.addCreationDate();
        //设置标题
        doc.addTitle("数据字典表");
        //设置值主题
        doc.addSubject("数据字典表Pdf");

        //构建页脚
        HeaderFooter footer = new HeaderFooter(new Phrase(), true);
        //设置页脚是否有边框
        //0表示无
        //1上边框
        //2下边框
        //3上下边框都有 默认都有
        //设置页脚是否有边框
        footer.setBorder(0);
        //footer.setBorder(1);
        //footer.setBorder(2);
        //footer.setBorder(3);
        //设置页脚的对齐方式
        footer.setAlignment(Element.ALIGN_CENTER);
        //将页脚添加到文档中
        doc.setFooter(footer);

        //设置字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 创建字体，设置family，size，style,还可以设置color
        Font titleChinese = new Font(bfChinese, 20, Font.BOLD);
        Font BoldChinese = new Font(bfChinese, 14, Font.BOLD);
        Font subBoldFontChinese = new Font(bfChinese, 8, Font.BOLD);

        //打开文档开始写内容
        doc.open();

        //构建一段落
        Paragraph par3 = new Paragraph("数据字典表", fontChinese);
        //设置局中对齐
        par3.setAlignment(Element.ALIGN_CENTER);
        //添加到文档
        doc.add(par3);

        //创建一个四列的表格
        Table table = new Table(4);
        //设置边框
        table.setBorder(1);
        table.setPadding(2);//边距:单元格的边线与单元格内容的边距

        //创建表头

        Cell cell1 = new Cell(new Phrase("字典编码", fontChinese));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        cell1.setHeader(true);
        cell1.setBackgroundColor(Color.RED);

        Cell cell2 = new Cell(new Phrase("字典类型", fontChinese));
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHeader(true);
        cell2.setBackgroundColor(Color.RED);

        Cell cell3 = new Cell(new Phrase("字典添加日期", fontChinese));
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHeader(true);
        cell3.setBackgroundColor(Color.RED);

        Cell cell4 = new Cell(new Phrase("备注", fontChinese));
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHeader(true);
        cell4.setBackgroundColor(Color.RED);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        //添加此代码后每页都会显示表头
        table.endHeaders();
        // 日期时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (temID != null) {
            List<T_Sys_Dictionary> list = new ArrayList<T_Sys_Dictionary>();
            String[] dictionary_id = temID.split(",");
            for (int i = 0; i < dictionary_id.length; i++) {
                T_Sys_Dictionary dictionary1 = this.dictionaryService.findDictionaryByID(dictionary_id[i]);
                list.add(dictionary1);
            }
            //循环向表格中添加记录 4列的表格
            for (int i = 1; i <= list.size(); i++) {
                T_Sys_Dictionary dictionary = list.get(i - 1);
                //设置字典编码单元格
                Cell cell11 = new Cell(new Phrase(dictionary.getDictionary_id(), fontChinese));
                //设置字典类型单元格
                Cell cell22 = new Cell(new Phrase(dictionary.getDictionary_type(), fontChinese));
                //设置字典添加日期单元格
                Cell cell33 = new Cell(new Phrase(format.format(dictionary.getDictionary_date()), fontChinese));
                //设置备注单元格
                Cell cell44 = new Cell(new Phrase(dictionary.getDictionary_remark(), fontChinese));

                //单元格水平对齐方式
                cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
                //单元格垂直对齐方式
                cell11.setVerticalAlignment(Element.ALIGN_CENTER);

                cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell22.setVerticalAlignment(Element.ALIGN_CENTER);

                cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell33.setVerticalAlignment(Element.ALIGN_CENTER);

                cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell44.setVerticalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell11);
                table.addCell(cell22);
                table.addCell(cell33);
                table.addCell(cell44);
            }
            //将表格添加到新的文档
            doc.add(table);

            //创建新的一页
            // doc.newPage();
            //添加图片
            // Image image=Image.getInstance("D://图片路径");
            //添加到文档
            // doc.add(image);
            //设置对象方式
            // image.setAlignment(Element.ALIGN_CENTER);

            doc.close();
            writer.close();
        } else {
            List<T_Sys_Dictionary> list = this.dictionaryService.findDictionaryList();
            //循环向表格中添加记录 4列的表格
            for (int i = 1; i <= list.size(); i++) {
                T_Sys_Dictionary dictionary = list.get(i - 1);
                //设置字典编码单元格
                Cell cell11 = new Cell(new Phrase(dictionary.getDictionary_id(), fontChinese));
                //设置字典类型单元格
                Cell cell22 = new Cell(new Phrase(dictionary.getDictionary_type(), fontChinese));
                //设置字典添加日期单元格
                Cell cell33 = new Cell(new Phrase(format.format(dictionary.getDictionary_date()), fontChinese));
                //设置备注单元格
                Cell cell44 = new Cell(new Phrase(dictionary.getDictionary_remark(), fontChinese));
                //单元格水平对齐方式
                cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
                //单元格垂直对齐方式
                cell11.setVerticalAlignment(Element.ALIGN_CENTER);
                cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell22.setVerticalAlignment(Element.ALIGN_CENTER);
                cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell33.setVerticalAlignment(Element.ALIGN_CENTER);
                cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell44.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell11);
                table.addCell(cell22);
                table.addCell(cell33);
                table.addCell(cell44);
            }
            //将表格添加到新的文档
            doc.add(table);
            doc.close();
            writer.close();
        }
    }

    /**
     * 方法名称：dicdataExportPdf
     * 内容摘要：导出字典数据表pdf
     *
     * @param response response
     * @throws Exception
     */
    @RequestMapping(value = "dicdataExportPdf")
    public void dicdataExportPdf(String dictionary_id, HttpServletResponse response) throws Exception {
        // 传递中文参数编码
        String codedFileName = java.net.URLEncoder.encode("字典数据表", "UTF-8");
        response.setContentType("application/pdf;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + codedFileName + ".pdf");
        Rectangle rect = new Rectangle(PageSize.A4);// 设置页面大小
        Document doc = new Document(rect);
        doc.setMargins(10, 10, 10, 10); // 左，右，上，下
        PdfWriter writer = PdfWriter.getInstance(doc, response.getOutputStream());// 创建书写器(Writer)与document对象关联，通过书写器可以将文档写入磁盘中

        List<T_Sys_Dicdata> list = this.dicdataService.findDicdataByDictionaryID(dictionary_id);

        //设置作者信息
        doc.addAuthor("瞪羚科技");
        //设置文档创建日期
        doc.addCreationDate();
        //设置标题
        doc.addTitle("字典数据表");
        //设置值主题
        doc.addSubject("字典数据表Pdf");

        //构建页脚
        HeaderFooter footer = new HeaderFooter(new Phrase(), true);
        //设置页脚是否有边框
        //0表示无
        //1上边框
        //2下边框
        //3上下边框都有 默认都有
        //设置页脚是否有边框
        footer.setBorder(0);
        //footer.setBorder(1);
        //footer.setBorder(2);
        //footer.setBorder(3);
        //设置页脚的对齐方式
        footer.setAlignment(Element.ALIGN_CENTER);
        //将页脚添加到文档中
        doc.setFooter(footer);

        //设置字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 创建字体，设置family，size，style,还可以设置color
        Font titleChinese = new Font(bfChinese, 20, Font.BOLD);
        Font BoldChinese = new Font(bfChinese, 14, Font.BOLD);
        Font subBoldFontChinese = new Font(bfChinese, 8, Font.BOLD);

        //打开文档开始写内容
        doc.open();

        //构建一段落
        Paragraph par3 = new Paragraph("字典数据表", fontChinese);
        //设置局中对齐
        par3.setAlignment(Element.ALIGN_CENTER);
        //添加到文档
        doc.add(par3);

        //创建一个四列的表格
        Table table = new Table(4);
        //设置边框
        table.setBorder(1);
        table.setPadding(2);//边距:单元格的边线与单元格内容的边距

        //创建表头

        Cell cell1 = new Cell(new Phrase("字典名称", fontChinese));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        cell1.setHeader(true);
        cell1.setBackgroundColor(Color.RED);

        Cell cell2 = new Cell(new Phrase("字典代码值", fontChinese));
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHeader(true);
        cell2.setBackgroundColor(Color.RED);

        Cell cell3 = new Cell(new Phrase("添加时间", fontChinese));
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHeader(true);
        cell3.setBackgroundColor(Color.RED);

        Cell cell4 = new Cell(new Phrase("备注", fontChinese));
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHeader(true);
        cell4.setBackgroundColor(Color.RED);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        //添加此代码后每页都会显示表头
        table.endHeaders();
        // 日期时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //循环向表格中添加记录 4列的表格
        for (int i = 1; i <= list.size(); i++) {
            T_Sys_Dicdata dicdata = list.get(i - 1);
            //设置字典编码单元格
            Cell cell11 = new Cell(new Phrase(dicdata.getDicdata_name(), fontChinese));
            //设置字典类型单元格
            Cell cell22 = new Cell(new Phrase(dicdata.getDicdata_code(), fontChinese));
            //设置字典添加日期单元格
            Cell cell33 = new Cell(new Phrase(format.format(dicdata.getDicdata_date()), fontChinese));
            //设置备注单元格
            Cell cell44 = new Cell(new Phrase(dicdata.getDicdata_remark(), fontChinese));

            //单元格水平对齐方式
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            //单元格垂直对齐方式
            cell11.setVerticalAlignment(Element.ALIGN_CENTER);

            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell22.setVerticalAlignment(Element.ALIGN_CENTER);

            cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell33.setVerticalAlignment(Element.ALIGN_CENTER);

            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell44.setVerticalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell11);
            table.addCell(cell22);
            table.addCell(cell33);
            table.addCell(cell44);
        }
        //将表格添加到新的文档
        doc.add(table);

        //创建新的一页
        // doc.newPage();
        //添加图片
        // Image image=Image.getInstance("D://图片路径");
        //添加到文档
        // doc.add(image);
        //设置对象方式
        // image.setAlignment(Element.ALIGN_CENTER);

        doc.close();
        writer.close();
    }


    /**
     * 方法名称：truckBrandJson
     * 内容摘要：车辆品牌json
     *
     * @param request  request
     * @param response response
     * @param model    model
     */
    @RequestMapping(value = "truckBrandJson")
    public String truckBrandJson(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        ResponseUtil.outWrite(response, getJson("60F8199A3A4D4136AC4B06BE97A1F974"));
        return getJson("60F8199A3A4D4136AC4B06BE97A1F974");
    }
}


