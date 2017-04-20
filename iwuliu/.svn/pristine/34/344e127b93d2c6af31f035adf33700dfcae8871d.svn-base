/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: ManagerMatchController.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：01
 * 模块名称：货场调度人员会员匹配信息管理页面
 * 设计文件：
 * 完成日期：2016-04-25
 * 作    者：YK
 * 内容摘要：货场调度人员会员匹配信息管理页面的CRUD
 */
package com.cn.gazelle.logistics.controller;

import com.cn.gazelle.logistics.dao.CargoDaoMapper;
import com.cn.gazelle.logistics.dao.CargoYardManagerDaoMapper;
import com.cn.gazelle.logistics.dao.CargoYardManagerMatchDaoMapper;
import com.cn.gazelle.logistics.dao.MemberDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Cargo_Yard_Manager_Match;
import com.cn.gazelle.logistics.pojo.T_Data_JsonResult;
import com.cn.gazelle.logistics.pojo.T_Data_Member;
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Yard;
import com.cn.gazelle.logistics.service.CargoYardManagerMatchService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.base.LogUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: ManagerMatchController
 * 内容摘要: 货场调度人员会员匹配信息管理页面的CRUD
 * 方法描述：该类有5个方法：
 *         01 home                      货场调度人员会员匹配信息管理主页
 *         02 managerMatchList          货场调度人员会员匹配信息列表页
 *         03 managerMatchSave          保存货场调度人员会员匹配信息
 *         04 managerMatchUpdate        更新货场调度人员会员匹配信息
 *         05 managerMatchDel           删除货场调度人员会员匹配信息
 * @author YK
 */
@Controller
@RequestMapping(value = "/managerMatchManager")
public class ManagerMatchController {
    Logger logger = Logger.getLogger(ManagerMatchController.class);

    @Resource
    private CargoYardManagerMatchService cargoYardManagerMatchService;

    @Resource
    private CargoYardManagerMatchDaoMapper cargoYardManagerMatchDaoMapper;

    @Resource
    private CargoDaoMapper cargoDaoMapper;

    @Resource
    private MemberDaoMapper memberDaoMapper;

    /**
     * 方法名称：home
     * 内容摘要：货场调度人员会员匹配信息管理主页。
     * @param model model
     * @return string 货场调度人员会员匹配信息管理主页
     */
    @RequestMapping(value = "home")
    public String home(ModelMap model){
        return "managerMatchManager/home";
    }

    /**
     * 方法名称：managerMatchList
     * 内容摘要：货场调度人员会员匹配信息列表页。
     * @param request request
     * @param response response
     * @param search_type 搜索类型
     * @param name 搜索名称
     * @param page 页面页数
     * @param rows 页面显示条数
     * @param model model
     * */
    @RequestMapping(value = "managerMatchList")
    public void managerMatchList(HttpServletRequest request, HttpServletResponse response,@RequestParam(required = false,defaultValue = "") String search_type, @RequestParam(required = false,defaultValue = "") String name, @RequestParam(required = false,defaultValue = "1") Integer page, @RequestParam(required = false,defaultValue = "10") Integer rows, ModelMap model){
        List<T_Data_Cargo_Yard_Manager_Match> list = null;
        T_Master_Cargo_Yard yard = null;
        T_Data_Member member = null;
        try {
            Map result = new HashMap();
            list = cargoYardManagerMatchService.findAllCargoYardManagerMatch(search_type,name,(page-1)*rows,rows);
            for (T_Data_Cargo_Yard_Manager_Match match:list){
                // 工作货场名称的回显
                yard = this.cargoDaoMapper.findById(match.getYard_id());
                if (yard!=null){
                    match.setYard_id(yard.getCargo_name());
                }
                else {
                    match.setYard_id("none");
                }
                // 会员名称的回显
                member = this.memberDaoMapper.findMemberByID(match.getManager_member_id());
                if (member!=null){
                    match.setManager_member_id(member.getMember_name());
                }
                else {
                    match.setManager_member_id("none");
                }

            }
            result.put("rows",list);
            result.put("total",cargoYardManagerMatchService.findAllCargoYardManagerMatchRowsCount(search_type,name));
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(result));
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
    }

    /**
     * 方法名称：managerMatchSave
     * 内容摘要：保存货场调度人员会员匹配信息。
     * @param match 货场调度人员会员匹配信息
     * @param request request
     * @param response response
     * @param session session
     * @param model model
     * */
    @RequestMapping(value = "managerMatchSave")
    @ResponseBody
    public T_Data_JsonResult managerMatchSave(T_Data_Cargo_Yard_Manager_Match match, HttpServletRequest request, HttpServletResponse response,HttpSession session,
                                              ModelMap model){
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        try {
            match.setManager_id(UUIDUtil.getUUID());
            match.setLast_update(DateUtil.getDate());
            match.setLast_update_user_id("U:"+ session.getAttribute("userName"));
            boolean b = cargoYardManagerMatchService.saveCargoYardManagerMatch(match);
            if (b){
                jsonResult.setResult(0);
            }else {
                jsonResult.setResult(1);
            }
        } catch (Exception e) {
            logger.error(LogUtil.saveErr+e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 方法名称：managerMatchUpdate
     * 内容摘要：更新货场调度人员会员匹配信息。
     * @param match 货场调度人员会员匹配信息
     * @param request request
     * @param response response
     * @param session session
     * @param model model
     * */
    @RequestMapping(value = "managerMatchUpdate")
    @ResponseBody
    public T_Data_JsonResult managerMatchUpdate(T_Data_Cargo_Yard_Manager_Match match,HttpServletRequest request,HttpServletResponse response,HttpSession session,
                                                ModelMap model ){
        T_Data_JsonResult jsonResult = new T_Data_JsonResult();
        T_Data_Cargo_Yard_Manager_Match match_old = null;
        try {
            match_old = this.cargoYardManagerMatchDaoMapper.findById(match.getManager_id());
            if (this.memberDaoMapper.findMemberByID(match.getManager_member_id())==null){
                match.setManager_member_id(match_old.getManager_member_id());
            }
            if (this.cargoDaoMapper.findById(match.getYard_id())==null){
                match.setYard_id(match_old.getYard_id());
            }
            match.setLast_update_user_id("U:"+ session.getAttribute("userName"));
            boolean b = cargoYardManagerMatchService.updateCargoYardManagerMatch(match);
            if (b){
                jsonResult.setResult(0);
            }else {
                jsonResult.setResult(1);
            }
        } catch (Exception e) {
            logger.error(LogUtil.updataErr+e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 方法名称：managerMatchDel
     * 内容摘要：删除货场调度人员会员匹配信息。
     * @param temID 批量删除id
     * @param request request
     * @param response response
     * */
    @RequestMapping(value = "managerMatchDel")
    public void managerMatchDel(String temID,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        String[] manager_id = temID.split(",");
        try {
            for (int i = 0;i<manager_id.length;i++) {

                cargoYardManagerMatchService.cargoYardManagerMatchDel(manager_id[i]);
            }
        } catch (Exception e) {
            logger.error(LogUtil.delErr+e.getMessage());
        }

    }


}
