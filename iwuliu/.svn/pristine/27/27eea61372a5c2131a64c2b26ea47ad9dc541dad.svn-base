/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：DataDictionaryController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-24
 * 作    者: QJ
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.dao.DicdataDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.pojo.T_Sys_Dictionary;
import com.cn.gazelle.logistics.service.DataDictionaryService;
import com.cn.gazelle.logistics.service.DicdataService;
import com.cn.gazelle.logistics.service.DictionaryService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.OperateMainLineManager_Message;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称：DataDictionaryController
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot QJ
 */
@Controller
@RequestMapping(value = "/dataDictionary")
public class DataDictionaryController {
    Logger logger = Logger.getLogger(DataDictionaryController.class);
    @Value("#{setting[baseUrl]}")
    private String baseUrl;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private DicdataService dicdataService;
    @Resource
    private DataDictionaryService dataDictionaryService;

    /**
     * 方法名称：dataDictionaryList
     * 内容摘要：字典管理列表。
     */
    @RequestMapping(value = "/dataDictionaryList")
    public void dataDictionaryList(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false, defaultValue = "") String dictionary_type,
                                   ModelMap model) {
        List<T_Sys_Dictionary> dictionaryList = null;
        try {
            dictionaryList = dictionaryService.findDictionaryByDictionaryType(dictionary_type);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(dictionaryList));
        } catch (Exception e) {
            logger.error(LogUtil.searchErr + e.getMessage());
        }
    }

    /**
     * 方法名称：dataDictionaryNewAdd_home
     * 内容摘要：新增数据字典页面
     *
     * @param model model
     * @return string 新增数据字典页面
     */
    @RequestMapping(value = "dataDictionaryNewAdd_home")
    public String dataDictionaryNewAdd_home(ModelMap model) {
        return "singleLoginManager/dataDictionaryNewAdd/dataDictionaryNewAdd";
    }

    /**
     * 方法名称：dataDictionaryDetail_home
     * 内容摘要：详情/编辑数据字典页面
     *
     * @param model model
     * @return string 详情/编辑数据字典页面
     */
    @RequestMapping(value = "dataDictionaryDetail_home")
    public String dataDictionaryDetail_home(ModelMap model) {
        return "singleLoginManager/dataDictionaryDetail/dataDictionaryDetail";
    }

    /**
     * 方法名称：saveDictionary
     * 内容摘要：新增字典信息。
     */
    @RequestMapping(value = "saveDictionary")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult saveDictionary(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        int count = 1;
        int count1 = 1;
        try {
            flag = this.dataDictionaryService.saveDictionary(list, (String) request.getSession().getAttribute("username"));
        } catch (Exception e) {
            e.getMessage();
            logger.info(e.getMessage());
            flag = Integer.parseInt(e.getMessage());
        }
        logger.info("flag=" + flag);
        // 判断是否插入数据
        if (flag == 1) {
            jsonResult.setResult(0);
            logger.info(MessageUtil.saveInfo);
        }
        // 判断是否插入数据失败
        else if (flag == -1) {
            jsonResult.setResult(1);
        }
        // 判断是否重复添加数据
        else if (flag == 0) {
            jsonResult.setResult(2);
        } else {
            jsonResult.setResult(3);
        }
        return jsonResult;
    }

    /**
     * 方法名称：dicdataList
     * 内容摘要：字典管理列表。
     */
    @RequestMapping(value = "/dicdataList")
    public void dicdataList(HttpServletRequest request, HttpServletResponse response, String dictionary_id,
                            ModelMap model) {
        List<T_Sys_Dicdata> dicdataList = null;

        try {
            dicdataList = dicdataService.findDicdataByDictionaryID(dictionary_id);
            List<Map<String, String>> dicdataLists = new ArrayList<Map<String, String>>();
            if (dicdataList != null && dicdataList.size() != 0) {
                for (int i = 0; i < dicdataList.size(); i++) {
                    HashMap<String, String> conditions = new HashMap<String, String>();
                    conditions.put("dicdata_name", dicdataList.get(i).getDicdata_name());
                    conditions.put("dicdata_code", dicdataList.get(i).getDicdata_code());
                    conditions.put("dicdata_date", dicdataList.get(i).getDicdata_date() + "");
                    conditions.put("dicdata_remark", dicdataList.get(i).getDicdata_remark());
                    conditions.put("dicdata_id", dicdataList.get(i).getDicdata_id());
                    dicdataLists.add(conditions);
                }
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(dicdataLists));
        } catch (Exception e) {
            logger.error(OperateMainLineManager_Message.getOperateMainLineError + e.getMessage());
        }
    }

    /**
     * 方法名称：getDictionaryType
     * 内容摘要：获取字典类型。
     */
    @RequestMapping(value = "/getDictionaryType")
    public void getDictionaryType(@RequestParam("dictionary_id") String dictionary_id,
                                  HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        dictionary_id = URLDecoder.decode(dictionary_id, "UTF-8");
        T_Sys_Dictionary dictionary = null;
        try {
            dictionary = dictionaryService.findDictionaryByID(dictionary_id);
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(dictionary));
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
    }


    /**
     * 方法名称：addDicdata
     * 内容摘要：新增字典数据信息。
     */
    @RequestMapping(value = "addDicdata")
    @ResponseBody
    @Transactional
    public T_Data_JsonResult addDicdata(String list, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        int flag = 0;
        int count = 1;
        int count1 = 1;
        try {
            flag = this.dataDictionaryService.addDicdata(list, (String) request.getSession().getAttribute("username"));
        } catch (Exception e) {
            e.getMessage();
            logger.info(e.getMessage());
            flag = Integer.parseInt(e.getMessage());
        }
        logger.info("flag=" + flag);
        // 判断是否插入数据
        if (flag == 1) {
            jsonResult.setResult(0);
            logger.info(MessageUtil.saveInfo);
        }
        // 判断是否插入数据失败
        else if (flag == -1) {
            jsonResult.setResult(1);
        }
        // 判断是否重复添加数据
        else if (flag == 0) {
            jsonResult.setResult(2);
        } else {
            jsonResult.setResult(3);
        }
        return jsonResult;
    }

    /**
     * 方法名称：dictionaryDel
     * 内容摘要：删除字典信息。
     *
     * @param temID    批量删除字典id
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "dictionaryDel")
    public T_Data_JsonResult dictionaryDel(String temID, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String[] dictionary_id = temID.split(",");
        T_Data_JsonResult result = new T_Data_JsonResult();
        try {
            for (int i = 0; i < dictionary_id.length; i++) {
                List<T_Sys_Dicdata> dicdataList = null;
                dicdataList = dicdataService.findDicdataByDictionaryID(dictionary_id[i]);
                for (T_Sys_Dicdata dicdata : dicdataList) {
                    dicdataService.delDicdata(dicdata.getDicdata_id());
                }
                dictionaryService.delDictionary(dictionary_id[i]);
            }
            result.setResult(0);
        } catch (Exception e) {
            logger.error(LogUtil.delErr + e.getMessage());
            result.setResult(1);
        }
        return result;
    }

    /**
     * 方法名称：dicdataDel
     * 内容摘要：删除字典数据
     *
     * @param temID    批量删除字典数据id
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "dicdataDel")
    public void dicdataDel(String temID, HttpServletRequest request, HttpServletResponse response,
                           ModelMap model) {
        String[] dicdata_id = temID.split(",");
        try {
            for (int i = 0; i < dicdata_id.length; i++) {
                dicdataService.delDicdata(dicdata_id[i]);
            }
        } catch (Exception e) {
            logger.error(LogUtil.delErr + e.getMessage());
        }
    }
}