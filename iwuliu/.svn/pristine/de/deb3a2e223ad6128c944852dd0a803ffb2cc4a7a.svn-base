/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CargoServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货场管理
 * 设计文件：
 * 完成日期：2016-03-01
 * 作    者：WXW
 * 内容摘要：货场管理
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.CargoDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Yard;
import com.cn.gazelle.logistics.service.CargoService;
import com.cn.gazelle.logistics.util.DateUtil;
import com.cn.gazelle.logistics.util.message.CargoManager_Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: CargoServiceImpl
 * 内容摘要: 货场信息管理
 * 方法描述：该类有7个方法：
 *         01 saveCargo                     保存货场信息
 *         02 CargoDel                      删除货场信息
 *         03 updateCargo                   更新货场信息
 *         04 findCargoList                 查询所有的货场不分页
 *         05 findById                      根据id获取货物类型
 *         06 getFrightYardByPosition       根据位置获取货物类型
 * @author WXW
 */

@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.CargoService",targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class CargoServiceImpl implements CargoService {
    // Log初始化
    Logger logger=Logger.getLogger(CargoServiceImpl.class);
    @Resource
    private CargoDaoMapper cargoDaoMapper;    //数据访问层

    /**
     * 方法名称：saveCargo
     * 内容摘要：保存货场信息
     * @param cargo_yard 货场信息
     */
    @Transactional
    public int saveCargo(T_Master_Cargo_Yard cargo_yard) {
        int i=1;
        try {
            i = this.cargoDaoMapper.saveCargo(cargo_yard);
            logger.info(CargoManager_Message.SaveCargoDone);
        } catch (Exception e) {
            i=2;
            logger.error(CargoManager_Message.SaveCargoError+e.getMessage());
        }
        return i;
    }
    
    /**
     * 方法名称：CargoDel
     * 内容摘要：删除货场信息
     * @param cargo_id 货场id
     */
    @Transactional
    public void CargoDel(String cargo_id) {
        try {
            cargoDaoMapper.cargoDel(cargo_id);
            logger.info(CargoManager_Message.DelCargoDone);
        } catch (Exception e) {
            logger.error(CargoManager_Message.DelCargoError+e.getMessage());
        }
    }
    
    /**
     * 方法名称：updateCargo
     * 内容摘要：更新货场信息
     * @param cargo_yard 货场信息
     */
    @Transactional
    public boolean updateCargo(T_Master_Cargo_Yard cargo_yard) {
        boolean a=new Boolean(true);
        try {
            cargo_yard.setLast_update(DateUtil.getDate());
            cargoDaoMapper.updateCargo(cargo_yard);
        } catch (Exception e) {
            a=false;
            logger.error(CargoManager_Message.UpdateCargoError+e.getMessage());
        }
        return a;
    }
    /**
     * 方法名称：findCargoList
     * 内容摘要：查询所有的货场不分页
     */
    public List<T_Master_Cargo_Yard> findCargoList() {
        List<T_Master_Cargo_Yard> cargoList=null;
        try {
            cargoList= cargoDaoMapper.findCargoList();
        } catch (Exception e) {
            logger.error(CargoManager_Message.seacheInfoError+e.getMessage());
        }

        return cargoList;
    }

    /**
     * 方法名称：findById
     * 内容摘要：根据id获取货物类型
     * @param cargo_id 货场信息
     */
    @Transactional
    public T_Master_Cargo_Yard findById(String cargo_id) {
        T_Master_Cargo_Yard cargoYard=null;
        try {
            cargoYard= cargoDaoMapper.findById(cargo_id);
        }catch ( Exception e){
            logger.error(CargoManager_Message.saveInfoError+e.getMessage());
        }
        return cargoYard;
    }

    /**
     * 方法名称：getFrightYardByPosition
     * 内容摘要：根据位置获取货物类型
     * @param province_id 货
     * @param city_id
     * @param area_id
     * @param town_street
     */
    @Transactional
    public List<T_Master_Cargo_Yard> getFrightYardByPosition(String province_id, String city_id, String area_id, String town_street) {
        List<T_Master_Cargo_Yard> frightYardByPosition=null;
        try {
            frightYardByPosition= cargoDaoMapper.getFrightYardByPosition(province_id, city_id, area_id, town_street);
        }catch ( Exception e){
            logger.error(CargoManager_Message.saveInfoError+e.getMessage());
        }
        return frightYardByPosition;
    }
}
