package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.MemberLoginRecordDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Data_Member_Login_Record;
import com.cn.gazelle.logistics.service.MemberLoginRecordService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.MemberLoginRecord_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by YK on 2016/2/22.
 */
@Service
@WebService(endpointInterface= "com.cn.gazelle.logistics.service.MemberLoginRecordService",targetNamespace="http://service.logistics.gazelle.cn.com/")
public class MemberLoginRecordServiceImpl implements MemberLoginRecordService {

    Logger logger = Logger.getLogger(MemberLoginRecordServiceImpl.class);

    @Resource
    private MemberLoginRecordDaoMapper memberLoginRecordDaoMapper;


    //根据履历id查询会员登录记录信息
    public String findRecordByID(String record_id) {
        T_Data_Member_Login_Record record = null;
        String str = null;
        try {
            record = this.memberLoginRecordDaoMapper.findRecordByID(record_id);
            str = JSONUtil.toJSONString(record);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return str;

    }

    //根据履历名查询会员登录记录信息
    public T_Data_Member_Login_Record findRecordByName(String record_name) {
        T_Data_Member_Login_Record record = null;
        try {
            record = this.memberLoginRecordDaoMapper.findRecordByName(record_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return record;
    }

    //查询所有会员登录记录信息
    public List<T_Data_Member_Login_Record> findAllRecord(String record_name, Integer page, Integer rows) {
        List<T_Data_Member_Login_Record> list = null;
        try {
            list = this.memberLoginRecordDaoMapper.findAllRecord(record_name,page,rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError+e.getMessage());
        }
        return list;
    }

    //查询会员登录记录总记录数
    public Integer findAllRecordRowsCount(String record_name) {
        int c = 0;
        try {
            c = this.memberLoginRecordDaoMapper.findAllRecordRowsCount(record_name);
        } catch (Exception e) {
            logger.error(MemberLoginRecord_Message.getSelectMemberLoginRecordCountError+e.getMessage());
        }
        return c;

    }

    //保存会员登录记录信息
    public void saveRecord(T_Data_Member_Login_Record record) {
        try {
            this.memberLoginRecordDaoMapper.saveRecord(record);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.saveInfoError+e.getMessage());
        }

    }

    //更新会员登录记录信息
    public void updateRecord(T_Data_Member_Login_Record record) {
        try {
            this.memberLoginRecordDaoMapper.updateRecord(record);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    //删除会员登录记录信息
    public void delRecord(String record_id) {
        try {
            this.memberLoginRecordDaoMapper.delRecord(record_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError+e.getMessage());
        }
    }
}
