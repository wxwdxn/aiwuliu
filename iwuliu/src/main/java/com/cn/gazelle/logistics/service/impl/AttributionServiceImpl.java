/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: AttributionServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：车辆归属地查询接口实现
 * 设计文件：
 * 完成日期：2016-03-16
 * 作    者：QJ
 * 内容摘要：车辆归属地查询
 */

package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.AttributionDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Dicdata;
import com.cn.gazelle.logistics.service.AttributionService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 类 名 称: UserServiceImpl
 * 内容摘要: 车辆归属地查询接口实现
 * 方法描述：该类有2个方法：
 *         01 findDicdataByDicdataName              通过字典名称查询字典数据信息
 *         02 findAttributionByPlateNumber          根据车牌号检索车辆所属城市(备注信息)
 * @author QJ
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.AttributionService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class AttributionServiceImpl implements AttributionService {
    public static String code;      // 全局变量
    // Log初始化
    Logger logger = Logger.getLogger(AttributionServiceImpl.class);

    @Resource
    private AttributionDaoMapper attributionDaoMapper;          // 数据访问层

    /**
     * 方法名称：findDicdataByDicdataName
     * 内容摘要：通过字典名称查询字典数据信息。
     * @param dicdata_name 字典名称
     * @return dicdataName 字典信息
     */
    public T_Sys_Dicdata findDicdataByDicdataName(String dicdata_name) {
        T_Sys_Dicdata dicdataName = null;           // 字典信息
        try {
            dicdataName = this.attributionDaoMapper.findDicdataByDicdataName(dicdata_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return dicdataName;
    }

    /**
     * 方法名称：findAttributionByPlateNumber
     * 内容摘要：根据车牌号检索车辆所属城市(备注信息)
     * @param plate_number  车牌号
     * @return String       车辆归属地
     */
    public String findAttributionByPlateNumber(String plate_number) {
        String json = null;
        Map result = new HashMap();
        T_Sys_Dicdata attribution = null;
        String dicdata_name = null;
        String dicdata_remark = null;
        String remark = null;
        String ecode = null;
        try {
            String str = plate_number;
            String subStr = str.substring(0,2);
            dicdata_name = subStr;
            attribution = attributionDaoMapper.findDicdataByDicdataName(dicdata_name);
            if (attribution == null) {
                ecode = "3000";   // 查询不到车辆归属地
                result.put("ecode", ecode);
                result.put("dicdata_remark", "查询不到车辆归属地");
            } else if (attribution != null){
                remark = attribution.getDicdata_remark();
                if(remark != null){
                    ecode = "1000";   // 响应成功
                    result.put("ecode", ecode);
                    result.put("dicdata_remark", remark);
                }else {
                    ecode = "3000";   // 查询不到车辆归属地
                    result.put("ecode", ecode);
                    result.put("dicdata_remark", "查询不到车辆归属地");
                }
            }
        } catch (Exception e) {
            ecode = "2000";   // 查询不到车辆归属地
            result.put("ecode", ecode);
            result.put("dicdata_remark", "响应失败");
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
            }
        json = JSONUtil.toJSONString(result);
        return json;
    }
}
