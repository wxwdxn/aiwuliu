package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.CargoYardManagerMatchDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Cargo_Yard_Manager_Match;
import com.cn.gazelle.logistics.service.CargoYardManagerMatchService;
import com.cn.gazelle.logistics.util.message.ManagerMatch_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by YK on 2016/4/25.
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.CargoYardManagerMatchService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class CargoYardManagerMatchServiceImpl implements CargoYardManagerMatchService {
    Logger logger = Logger.getLogger(MemberServiceImpl.class);
    @Resource
    private CargoYardManagerMatchDaoMapper cargoYardManagerMatchDaoMapper;

    //增加货场调度人员会员匹配信息
    public boolean saveCargoYardManagerMatch(T_Data_Cargo_Yard_Manager_Match match) {
        boolean b = new Boolean(true);
        try {
            this.cargoYardManagerMatchDaoMapper.saveCargoYardManagerMatch(match);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    //根据ID删除货场调度人员会员匹配信息
    public void cargoYardManagerMatchDel(String manager_id) {
        try {
            this.cargoYardManagerMatchDaoMapper.cargoYardManagerMatchDel(manager_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }

    }

    //更新货场调度人员会员匹配信息
    public boolean updateCargoYardManagerMatch(T_Data_Cargo_Yard_Manager_Match match) {
        boolean b = new Boolean(true);
        try {
            this.cargoYardManagerMatchDaoMapper.updateCargoYardManagerMatch(match);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    //查询货场调度人员会员匹配信息
    public List<T_Data_Cargo_Yard_Manager_Match> findAllCargoYardManagerMatch(String search_type, String name, Integer page, Integer rows) {
        List<T_Data_Cargo_Yard_Manager_Match> list = null;
        try {
            list = this.cargoYardManagerMatchDaoMapper.findAllCargoYardManagerMatch(search_type, name, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return list;
    }

    //查询货场调度人员会员匹配信息总记录数
    public Integer findAllCargoYardManagerMatchRowsCount(String search_type, String name) {
        int c = 0;
        try {
            c = this.cargoYardManagerMatchDaoMapper.findAllCargoYardManagerMatchRowsCount(search_type, name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(ManagerMatch_Message.getSelectManagerMatchCountError + e.getMessage());
        }

        return c;
    }

    //查询所有的货场调度人员会员匹配信息不分页
    public List<T_Data_Cargo_Yard_Manager_Match> findAll() {
        List<T_Data_Cargo_Yard_Manager_Match> list = null;
        try {
            list = this.findAll();
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return list;
    }
}
