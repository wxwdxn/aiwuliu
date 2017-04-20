package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.CargoYardManagerDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Cargo_Yard_Manager;
import com.cn.gazelle.logistics.service.CargoYardManagerService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.CargoYardManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by YK on 2016/2/24.
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.CargoYardManagerService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class CargoYardManagerServiceImpl implements CargoYardManagerService {
    Logger logger = Logger.getLogger(CargoYardManagerServiceImpl.class);

    @Resource
    private CargoYardManagerDaoMapper cargoYardManagerDaoMapper;

    //根据货站管理人员id查询企业信息
    public String findManagerByID(String manager_id) {
        String str = null;
        T_Data_Cargo_Yard_Manager manager = null;
        try {
            manager = this.cargoYardManagerDaoMapper.findManagerByID(manager_id);
            str = JSONUtil.toJSONString(manager);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return str;
    }

    //查询所有货站管理人员信息
    public List<T_Data_Cargo_Yard_Manager> findAllManager(String search_type, String name, Integer page, Integer rows) {
        List<T_Data_Cargo_Yard_Manager> list = null;
        try {
            list = this.cargoYardManagerDaoMapper.findAllManager(search_type, name, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return list;
    }

    //查询货站管理人员总记录数
    public Integer findAllManagerRowsCount(String search_type, String name) {
        int c = 0;
        try {
            c = this.cargoYardManagerDaoMapper.findAllManagerRowsCount(search_type, name);
        } catch (Exception e) {
            logger.error(CargoYardManager_Message.getSelectManagerCountError + e.getMessage());
        }
        return c;
    }

    //保存货站管理人员信息
    public boolean saveManager(T_Data_Cargo_Yard_Manager manager) {
        boolean b = new Boolean(true);
        try {
            this.cargoYardManagerDaoMapper.saveManager(manager);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    //更新货站管理人员信息
    public boolean updateManager(T_Data_Cargo_Yard_Manager manager) {
        boolean b = new Boolean(true);
        try {
            this.cargoYardManagerDaoMapper.updateManager(manager);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    //删除货站管理人员信息
    public void delManager(String manager_id) {
        try {
            this.cargoYardManagerDaoMapper.delManager(manager_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }

    }
}
