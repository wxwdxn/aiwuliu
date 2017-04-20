/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：OfflineLogisticsInfoManagerController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2017-03-02
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.pojo.T_Data_Company;
import com.cn.gazelle.logistics.service.CompanyService;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 类 名 称：OfflineLogisticsInfoManagerController
 * 内容描述：
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot YK
 */
@Controller
@RequestMapping(value = "/offlineLogisticsInfoManager")
public class OfflineLogisticsInfoManagerController {
    Logger logger = Logger.getLogger(OfflineLogisticsInfoManagerController.class);
    @Resource
    private CompanyService companyService;

    /**
     * 方法名称：offlineLogistics_home
     * 内容摘要：线下物流公司页面。
     *
     * @param model model
     * @return string 线下物流公司页面
     */
    @RequestMapping(value = "home")
    public String offlineLogistics_home(ModelMap model) {
        return "operationManager/offlineLogisticsInfoManager/offlineLogisticsInfoManager";
    }

    /**
     * 方法名称：getOfflineCompanyInfo
     * 内容摘要：获取线下物流公司详情页面。
     *
     * @param model model
     * @return string 获取线下物流公司详情页面
     */
    @ResponseBody
    @RequestMapping(value="getOfflineCompanyInfo")
    public T_Data_Company getOfflineCompanyInfo(HttpServletRequest request,
                                                HttpServletResponse response,
                                                HttpSession session,ModelMap model){
        List<T_Data_Company> companyList = null;
        T_Data_Company company = null;
        String company_id = null;
        try {
            String useName = (String) request.getSession().getAttribute("username");
            // 通过登录手机号查找对应的物流公司
            companyList = this.companyService.queryCompanyList("","",useName,"","");
            company = companyList.get(0);
            company = this.companyService.findCompanyDetailByID(company.getCompany_id());
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(e.getMessage() + MessageUtil.seacheInfoError);
        }
        return company;
    }

}

