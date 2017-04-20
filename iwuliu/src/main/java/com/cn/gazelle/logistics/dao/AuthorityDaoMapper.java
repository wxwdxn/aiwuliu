/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: AuthorityDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：权限信息查询接口实现
 * 设计文件：
 * 完成日期：2016-01-05
 * 作    者：YK
 * 内容摘要：权限信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Sys_Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: AuthorityDaoMapper
 * 内容摘要: 权限信息管理页面
 * 方法描述：该类有7个方法：
 *         01 findAuthorityByID                          根据ID查找权限信息
 *         02 findAuthorityByAuthorityName               根据权限名查找信息
 *         03 findAllAuthority                           查找所有权限信息
 *         04 findAllAuthorityRowsCount                  查找权限总记录数
 *         05 findAllAuthoritySearchCount                查找符合条件的权限总记录数
 *         06 saveAuthority                              保存权限信息
 *         07 delAuthority                               删除权限信息
 *@author YK
 */
public interface AuthorityDaoMapper {

    // 根据ID查找权限信息
    T_Sys_Authority findAuthorityByID(@Param("authority_id") String authority_id);

    // 根据权限名查找信息
    T_Sys_Authority findAuthorityByAuthorityName(@Param("authority_name") String authority_name);

    // 查找所有权限信息
    List<T_Sys_Authority> findAllAuthority(@Param("authority_name") String authority_name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查找权限总记录数
    Integer findAllAuthorityRowsCount();

    // 查找符合条件的权限总记录数
    Integer findAllAuthoritySearchCount(@Param("authority_name") String authority_name);

    // 保存权限信息
    int saveAuthority(T_Sys_Authority authority);

    // 更新权限信息
    void updateAuthority(T_Sys_Authority authority);

    // 删除权限信息
    void delAuthority(String authority_id);

}
