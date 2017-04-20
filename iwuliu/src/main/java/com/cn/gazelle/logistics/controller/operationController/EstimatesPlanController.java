/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：EstimatesPlanController.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-11-27
 * 作    者: WXW
 * 内容摘要：
 */
package com.cn.gazelle.logistics.controller.operationController;

import com.cn.gazelle.logistics.dao.*;
import com.cn.gazelle.logistics.pojo.*;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.ResponseUtil;
import com.cn.gazelle.logistics.util.message.TransportationOrder_Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 类 名 称：EstimatesPlanController
 * 内容描述：预估派单
 * 方法描述：该类有 个方法
 *          01 
 *          02
 *@authot WXW
 */
@Controller
@RequestMapping(value = "/estimatesPlanManager")
public class EstimatesPlanController {
    Logger logger=Logger.getLogger("EstimatesPlanController.class");
    @Resource
    private DummyPlanDaoMapper dummyPlanDaoMapper;
    @Resource
    private DummyContractsDaoMapper dummyContractsDaoMapper;
    @Resource
    private PersonDaoMapper personDaoMapper;
    @Resource
    private CargoTypeDaoMapper cargoTypeDaoMapper;
    @Resource
    private CargoDaoMapper cargoDaoMapper;
    @Resource
    private OperateMainLineDaoMapper operateMainLineDaoMapper;




    /**
     * 方法名称：operationPlanManager_home
     * 内容摘要：运维合同计划。
     *
     * @return string 运维合同计划
     */
    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String operationPlanManager_home(ModelMap model) {
        return "operationManager/estimatesPlanManager/estimatesPlanManager";
    }

    /**
     * 方法名称：transportationContractList
     * 内容摘要：获取订单列表（分页）。所属计划编号为空
     * @param request  request
     */
    @RequestMapping(value = "/OrderListBelongNull")
    public void OrderListBelongNull(HttpServletRequest request,HttpServletResponse response) {
        try {
            List<T_Data_transportation_Plan_Dummy> orderList = dummyPlanDaoMapper.findOrderListBelongNull();

            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (T_Data_transportation_Plan_Dummy order : orderList) {
                String contract_id = order.getContract_id();
                //合同
                T_Data_Transportation_Contract_Dummy contract = dummyContractsDaoMapper.findById(contract_id);
                String relevanceInfoId = contract.getFirst_party_relevance_info_id();
                T_Data_Person person = personDaoMapper.findPersonByID(relevanceInfoId);
                String cargo_type_id = contract.getCargo_type_id();
                T_Master_Cargo_Type cargoType = cargoTypeDaoMapper.findCargoType(cargo_type_id);
                HashMap map = new HashMap();
                String schedule_plan_number = order.getSchedule_plan_number();
                map.put("schedule_plan_number", schedule_plan_number);
                //合同
                map.put("code", contract.getCode());
                map.put("name", person.getPerson_name());
                map.put("cargo_type_id", cargoType.getCargoTypeName());
                map.put("loading_begin_date", contract.getBegin_date());
                map.put("loading_contact_name",order.getLoading_contact_name());
                map.put("loading_contact_phone", order.getLoading_contact_phone());
                map.put("unloading_finish_date", order.getUnloading_finish_date());
                map.put("unloading_contact_name",order.getUnloading_contact_name());
                map.put("unloading_contact_phone", order.getUnloading_contact_phone());
                String unloading_cargo_yard_id = order.getUnloading_cargo_yard_id();
                T_Master_Cargo_Yard byId = cargoDaoMapper.findById(unloading_cargo_yard_id);
                map.put("unloading_cargo_yard_id", byId.getCargo_name());

                //干线
                String lineId = order.getOperate_main_line_id();
                String lineName = operateMainLineDaoMapper.findOperateMainLineById(lineId).getOperate_main_line_name();
                //装货货场
                String cargoYardId = order.getLoading_cargo_yard_id();
                T_Master_Cargo_Yard cargoYard = cargoDaoMapper.findById(cargoYardId);

                map.put("lineName", lineName);
                map.put("loading_cargo_yard_id", cargoYard.getCargo_name());
                map.put("transport_unit_price", order.getTransport_unit_price());
                map.put("cargo_total", order.getCargo_total());
                list.add(map);
            }
            ResponseUtil.outWrite(response, JSONUtil.toJSONString(list));
        } catch (Exception e) {
            logger.info(TransportationOrder_Message.getTransportationOrderError + e.getMessage());
        }
    }


}