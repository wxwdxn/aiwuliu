/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: TransportationContractServiceImpl
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运输合同信息管理实现
 * 设计文件：
 * 完成日期：2016-03-04
 * 作    者：WXW
 * 内容摘要：运输合同信息管理实现
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.TransportationContractDaoMapper;
import com.cn.gazelle.logistics.dao.TransportationOrderDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Contract;
import com.cn.gazelle.logistics.pojo.T_Data_Transportation_Plan;
import com.cn.gazelle.logistics.service.TransportationContractService;
import com.cn.gazelle.logistics.util.CodeUtil;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.UUIDUtil;
import com.cn.gazelle.logistics.util.message.TransportationContractManager_Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类 名 称: TransportationContractService
 * 内容摘要: 运输合同信息管理
 * 方法描述：该类有5个方法：
 *         01 findAllTransportationContract                   查找所有运输合同信息（分页）
 *         02 findAllTransportationContractRowsCount          查询运输合同总记录数
 *         03 saveTransportationContract                      保存运输合同信息
 *         04 updateTransportationContract                    更新运输合同信息
 *         05 delTransportationContract                       删除运输合同信息
 * @author WXW
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.TransportationContractService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class TransportationContractServiceImpl implements TransportationContractService {
    // Log初始化
    Logger logger = Logger.getLogger(TransportationContractServiceImpl.class);
    @Resource
    private TransportationContractDaoMapper contractDaoMapper;
    @Resource
    private TransportationOrderDaoMapper transportationOrderDaoMapper;

    /**
     * 方法名称：saveTransportationContract
     * 内容摘要：保存运输合同信息
     */
    @Transactional
    public int saveTransportationContract(String parameter ,String userName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson=new Gson();
        int a=0;
        try {
            Map<String, Object> data = gson.fromJson(parameter, new TypeToken<Map<String, Object>>() {
            }.getType());
            Map map =(Map) data.get("htbh");
            String code =(String) map.get("code");
            String cargo_type_id = (String)map.get("cargo_type_id");
            String first_party_relevance_info_id =(String) map.get("first_party_relevance_info_id");
            String first_party_type =(String) map.get("first_party_type");
            String balance_type =(String) map.get("balance_type");
            String contact_name =(String) map.get("contact_name");
            String contact_phone =(String) map.get("contact_phone");
            String start_date =(String) map.get("start_date");
            String end_Date =(String) map.get("end_Date");
            T_Data_Transportation_Contract contract = new T_Data_Transportation_Contract();
            contract.setContract_id(UUIDUtil.getUUID());
            contract.setCode(code);
            contract.setFirst_party_relevance_info_id(first_party_relevance_info_id);
            contract.setFirst_party_type(first_party_type);
            contract.setBalance_type(balance_type);
            contract.setCargo_type_id(cargo_type_id);
            contract.setStatus("1");
            contract.setCreate_date(format.format( DateUtil.getDate()));
            contract.setBalance_cycle("0");
            contract.setBegin_date(start_date);
            contract.setCargo_total(0);
            contract.setContact_name(contact_name);
            contract.setContact_phone(contact_phone);
            contract.setDeleteFlag(0);
            contract.setLast_update(DateUtil.getDate());
            contract.setUnit_price(100);
            contract.setStatus_update_time(DateUtil.getDate());
            contract.setLast_update_user_id(userName);
            contract.setEnd_date(end_Date);
            a= contractDaoMapper.saveTransportationContract(contract);
            if (a==1){
                //合同保存成功
                logger.info(TransportationContractManager_Message.SaveTransportatinContractInfo);
                //运输计划
                Map map2 =(Map)data.get("line");
                List list1 =(List) map2.get("ganxian");
                int size = list1.size();
                int cargoTatal=0;
                //没有干线信息只保留合同
                if (size!=0){
                    for (int i=0;i<size;i++){
                        String planCode = CodeUtil.TCode(DateUtil.getDate());
                        Map map3 =(Map) list1.get(i);
                        //总的计划
                        String operate_main_line_id = (String)map3.get("operate_main_line_id");
                        String loading_cargo_yard_id = (String)map3.get("loading_cargo_yard_id");
                        String loading_begin_date = (String)map3.get("loading_begin_date");
                        String loading_contact_name = (String)map3.get("loading_contact_name");
                        String loading_contact_phone = (String)map3.get("loading_contact_phone");
                        String unloading_cargo_yard_id = (String)map3.get("unloading_cargo_yard_id");
                        String unloading_finish_date = (String)map3.get("unloading_finish_date");
                        String unloading_contact_name = (String)map3.get("unloading_contact_name");
                        String unloading_contact_phone = (String)map3.get("unloading_contact_phone");
                        String transport_unit_price = (String)map3.get("transport_unit_price");
                        String cargo_total = (String)map3.get("cargo_total");
                        Integer cargo_Total = Integer.valueOf(cargo_total);
                        cargoTatal+=cargo_Total;
                        T_Data_Transportation_Plan transportationPlan = new T_Data_Transportation_Plan();
                        transportationPlan.setContract_id(contract.getContract_id());
                        transportationPlan.setAverage_transportation_time("0");
                        transportationPlan.setOperate_main_line_id(operate_main_line_id);
                        transportationPlan.setLoading_cargo_yard_id(loading_cargo_yard_id);
                        transportationPlan.setLoading_begin_date(loading_begin_date);
                        transportationPlan.setLoading_contact_name(loading_contact_name);
                        transportationPlan.setLoading_contact_phone(loading_contact_phone);
                        transportationPlan.setUnloading_cargo_yard_id(unloading_cargo_yard_id);
                        transportationPlan.setUnloading_finish_date(unloading_finish_date);
                        transportationPlan.setUnloading_contact_name(unloading_contact_name);
                        transportationPlan.setUnloading_contact_phone(unloading_contact_phone);
                        transportationPlan.setTransport_unit_price(Double.valueOf(transport_unit_price));
                        transportationPlan.setCargo_total(cargo_Total);
                        transportationPlan.setRedistribute_cargo_total(0);
                        transportationPlan.setStatus("1");
                        transportationPlan.setDeleteFlag(0);
                        transportationPlan.setLast_update(DateUtil.getDate());
                        transportationPlan.setLast_update_user_id(userName);
                        transportationPlan.setCode(planCode);
                        transportationOrderDaoMapper.saveTransportateOrder(transportationPlan);
                        Date beginDate = format.parse(loading_begin_date);
                        Date finishDate = format.parse(unloading_finish_date);
                        long day = finishDate.getTime() - beginDate.getTime();
                        long min=24 * 60 * 60 * 1000;
                        long dayCount = day / min;
                        int total = Integer.valueOf(cargo_total);
                        int i1 = (int)dayCount + 1;
                        int minTotal = total /i1 ;
                        long startTime = beginDate.getTime();
                        for (int j=0;j<i1;j++){
                            //分割装货时间按天
                            long startDate = startTime + 24 * 60 * 60 * 1000*j;
                            Date date = new Date(startDate);
                            loading_begin_date = format.format(date);
                            T_Data_Transportation_Plan transportationPlan2 = new T_Data_Transportation_Plan();
                            transportationPlan2.setContract_id(contract.getContract_id());
                            transportationPlan2.setBelong_schedule_plan_number(transportationPlan.getSchedule_plan_number());
                            transportationPlan2.setOperate_main_line_id(operate_main_line_id);
                            transportationPlan2.setLoading_cargo_yard_id(loading_cargo_yard_id);
                            transportationPlan2.setAverage_transportation_time("0");
                            transportationPlan2.setLoading_begin_date(loading_begin_date);
                            transportationPlan2.setLoading_contact_name(loading_contact_name);
                            transportationPlan2.setLoading_contact_phone(loading_contact_phone);
                            transportationPlan2.setUnloading_cargo_yard_id(unloading_cargo_yard_id);
                            transportationPlan2.setUnloading_finish_date(unloading_finish_date);
                            transportationPlan2.setUnloading_contact_name(unloading_contact_name);
                            transportationPlan2.setUnloading_contact_phone(unloading_contact_phone);
                            transportationPlan2.setTransport_unit_price(Double.valueOf(transport_unit_price));
                            transportationPlan2.setRedistribute_cargo_total(0.0);
                            //平均分配数量
                            transportationPlan2.setCargo_total(minTotal);
                            transportationPlan2.setStatus("1");
                            transportationPlan2.setDeleteFlag(0);
                            transportationPlan2.setLast_update(DateUtil.getDate());
                            transportationPlan2.setLast_update_user_id(userName);
                            transportationPlan2.setCode(CodeUtil.TCode(DateUtil.getDate()));
                            transportationOrderDaoMapper.saveTransportateOrder(transportationPlan2);
                        }
                    }
                }
                //根据干线更新合同数量
                contract.setCargo_total(cargoTatal);
                contractDaoMapper.updateTransportationContract(contract);
            }else if (a==0){//合同重复
                logger.info(TransportationContractManager_Message.SaveTransportatinContractError);
                throw new RuntimeException("0");
            }else if (a==-1){//添加失败
                logger.info(TransportationContractManager_Message.SaveTransportatinContractError);
                throw new RuntimeException("-1");
            }
        } catch (Exception e) {
            logger.error(TransportationContractManager_Message.SaveTransportatinContractError+e.getMessage());
            if (a==0){
                throw new RuntimeException("0");
            }else {
                throw new RuntimeException("-1");
            }
        }
        return a;
    }
    /**
     * 方法名称：updateContractOrAddLineInfo
     * 内容摘要：修改运输合同干线单价和添加干线。
     * @param parameter                parameter
     * @param userName               userName
     */
    @Override
    @Transactional
    public int updateContractOrAddLineInfo(String parameter, String userName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson=new Gson();
        int a=1;
        try {
            Map<String, Object> data = gson.fromJson(parameter, new TypeToken<Map<String,Object>>() {
            }.getType());
            Map map = (Map) data.get("info");
            String contractId =(String) map.get("contractId");
            T_Data_Transportation_Contract contract = contractDaoMapper.findById(contractId);
            //单价
            List<Map<String,String>> price =(List) map.get("price");
            if (price.size()!=0){
                for (int i=0;i<price.size();i++) {
                    Map<String, String> map1 = price.get(i);
                    String schedulePlanNumber = map1.get("schedulePlanNumber");
                    String unitPrice = map1.get("price");
                    T_Data_Transportation_Plan transportationPlan = transportationOrderDaoMapper.findBySchedulePlanNumber(schedulePlanNumber);
                    transportationPlan.setTransport_unit_price(Double.valueOf(unitPrice));
                    transportationOrderDaoMapper.updateTransportateOrder(transportationPlan);
                    List<T_Data_Transportation_Plan> planList = transportationOrderDaoMapper.findOrderListByBelongCode(schedulePlanNumber);
                    for (T_Data_Transportation_Plan order : planList){
                        order.setTransport_unit_price(Double.valueOf(unitPrice));
                        //更新二级运单价格
                        transportationOrderDaoMapper.updateTransportateOrder(order);
                    }
                }
            }
            //干线
            List<Map<String,String>>  addNew =(List) map.get("newAdd");
            int cargo=0;
            if (addNew.size()!=0) {
                for (int i = 0; i < addNew.size(); i++) {
                    Map<String, String> map1 = addNew.get(i);
                    String operate_main_line_id = map1.get("operate_main_line_id");
                    String loading_cargo_yard_id = map1.get("loading_cargo_yard_id");
                    String loading_begin_date = map1.get("loading_begin_date");
                    String loading_contact_name = map1.get("loading_contact_name");
                    String loading_contact_phone = map1.get("loading_contact_phone");
                    String unloading_cargo_yard_id = map1.get("unloading_cargo_yard_id");
                    String unloading_finish_date = map1.get("unloading_finish_date");
                    String unloading_contact_name = map1.get("unloading_contact_name");
                    String unloading_contact_phone = map1.get("unloading_contact_phone");
                    String transport_unit_price = map1.get("transport_unit_price");
                    String cargo_total = map1.get("cargo_total");
                    Integer cargoTotal = Integer.valueOf(cargo_total);
                    cargo+=cargoTotal;
                    T_Data_Transportation_Plan plan = new T_Data_Transportation_Plan();
                    plan.setContract_id(contractId);
                    plan.setOperate_main_line_id(operate_main_line_id);
                    plan.setLoading_cargo_yard_id(loading_cargo_yard_id);
                    plan.setLoading_begin_date(loading_begin_date);
                    plan.setLoading_contact_name(loading_contact_name);
                    plan.setLoading_contact_phone(loading_contact_phone);
                    plan.setUnloading_cargo_yard_id(unloading_cargo_yard_id);
                    plan.setUnloading_contact_name(unloading_contact_name);
                    plan.setUnloading_finish_date(unloading_finish_date);
                    plan.setUnloading_contact_phone(unloading_contact_phone);
                    plan.setTransport_unit_price(Double.valueOf(transport_unit_price));
                    plan.setCargo_total(cargoTotal);
                    plan.setCreate_time(DateUtil.getDate());
                    plan.setStatus("1");
                    plan.setDeleteFlag(0);
                    plan.setLast_update(DateUtil.getDate());
                    plan.setLast_update_user_id(userName);
                    plan.setCode(CodeUtil.TCode(DateUtil.getDate()));
                    transportationOrderDaoMapper.saveTransportateOrder(plan);
                    Date beginDate = format.parse(loading_begin_date);
                    Date finishDate = format.parse(unloading_finish_date);
                    long day = finishDate.getTime() - beginDate.getTime();
                    int  dayCount =(int) day / (24 * 60 * 60 * 1000);
                    int i1 = dayCount + 1;
                    int minTotal = cargoTotal /i1 ;
                    long startTime = beginDate.getTime();
                    for (int j=0;j<i1;j++){
                        //分割装货时间按天
                        long startDate = startTime + 24 * 60 * 60 * 1000*j;
                        Date date = new Date(startDate);
                        loading_begin_date = format.format(date);
                        T_Data_Transportation_Plan transportationPlan2 = new T_Data_Transportation_Plan();
                        transportationPlan2.setContract_id(contract.getContract_id());
                        transportationPlan2.setBelong_schedule_plan_number(plan.getSchedule_plan_number());
                        transportationPlan2.setOperate_main_line_id(operate_main_line_id);
                        transportationPlan2.setLoading_cargo_yard_id(loading_cargo_yard_id);
                        transportationPlan2.setAverage_transportation_time("0");
                        transportationPlan2.setLoading_begin_date(loading_begin_date);
                        transportationPlan2.setLoading_contact_name(loading_contact_name);
                        transportationPlan2.setLoading_contact_phone(loading_contact_phone);
                        transportationPlan2.setUnloading_cargo_yard_id(unloading_cargo_yard_id);
                        transportationPlan2.setUnloading_finish_date(unloading_finish_date);
                        transportationPlan2.setUnloading_contact_name(unloading_contact_name);
                        transportationPlan2.setUnloading_contact_phone(unloading_contact_phone);
                        transportationPlan2.setTransport_unit_price(Double.valueOf(transport_unit_price));
                        transportationPlan2.setRedistribute_cargo_total(0.0);
                        //平均分配数量
                        transportationPlan2.setCargo_total(minTotal);
                        transportationPlan2.setStatus("1");
                        transportationPlan2.setDeleteFlag(0);
                        transportationPlan2.setLast_update(DateUtil.getDate());
                        transportationPlan2.setLast_update_user_id(userName);
                        transportationPlan2.setCode(CodeUtil.TCode(DateUtil.getDate()));
                        transportationOrderDaoMapper.saveTransportateOrder(transportationPlan2);
                    }
                }
            }
            int total = contract.getCargo_total();
            //根据新增的干线货物数量更新合同货物数量
            int totals = total + cargo;
            contract.setCargo_total(totals);
            contractDaoMapper.updateTransportationContract(contract);
        }catch (Exception e){
            logger.error(TransportationContractManager_Message.SaveTransportatinContractError+e.getMessage());
            throw new RuntimeException("-1");
        }
        return a;
    }

    /**
     * 方法名称：findById
     * 内容摘要：根据id查询运输合同
     * @param contractId 合同id
     * @return T_Data_Transportation_Contract 运输合同信息列表
     */
    @Transactional
    public T_Data_Transportation_Contract findById(String contractId) {
        T_Data_Transportation_Contract contract=null;
        try {
            contract= contractDaoMapper.findById(contractId);
        }catch (Exception e){
            logger.error( TransportationContractManager_Message.seacheInfoError);
        }
        return contract;
    }
    /**
     * 方法名称：findById
     * 内容摘要：根据id查询运输合同
     * @param code code
     * @return T_Data_Transportation_Contract 运输合同信息列表
     */
    @Transactional
    public T_Data_Transportation_Contract findByCode(String code) {
        T_Data_Transportation_Contract contract=null;
        try {
            contract= contractDaoMapper.findByCode(code);
        }catch (Exception e){
            logger.error( TransportationContractManager_Message.seacheInfoError+e.getMessage());
        }
        return contract;
    }

    /**
     * 方法名称：findAllCargo
     * 内容摘要：查找所有运输合同
     * @return list 运输合同信息列表
     */
    @Transactional
    public List<T_Data_Transportation_Contract> findAllTransportationContract() {
        List<T_Data_Transportation_Contract> list= null;
        try {
            list = contractDaoMapper.findAllTransportationContract();
            logger.info(TransportationContractManager_Message.getTransportatinContractInfo);
        } catch (Exception e) {
            logger.error(TransportationContractManager_Message.getTransportatinContractError+e.getMessage());
        }
        return list;
    }

}
