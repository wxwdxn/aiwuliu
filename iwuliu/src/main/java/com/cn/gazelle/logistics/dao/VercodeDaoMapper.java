package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Vercode;

/**
 * 类 名 称: UserDaoMapper
 * 内容摘要: 登录页的用户信息及验证码验证
 * 方法描述：该类有3个方法：
 *         01 saveVercode                    保存手机验证码信息
 *         02 findVercode                    根据手机号查询验证码信息
 *         03 updateVercode                  根据手机号更新手机验证码信息
 * Created by zf on 2016/12/7.
 */
public interface VercodeDaoMapper {

    //保存手机验证码信息
    void saveVercode(T_Data_Vercode vercode);

    //根据手机号查询验证码信息
     T_Data_Vercode findVercode(String moble_phone);

   //根据手机号更新手机验证码信息
    void updateVercode(String moble_phone);
}
