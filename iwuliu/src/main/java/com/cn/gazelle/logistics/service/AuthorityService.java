/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: AuthorityService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：权限信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-05
 * 作    者：YK
 * 内容摘要：权限信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_Authority;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: AuthorityService
 * 内容摘要: 权限信息查询接口
 * 方法描述：该类有7个方法：
 *         01 findAuthorityByID                 根据ID查找权限信息
 *         02 findAllAuthority                  查找所有权限信息
 *         03 findAllAuthorityRowsCount         查找权限总记录数
 *         04 findAllAuthoritySearchCount       查找符合条件的权限总记录数
 *         05 saveAuthority                     保存权限信息
 *         06 updateAuthority                   更新权限信息
 *         07 delAuthority                      删除权限信息
 * @author YK
 */
@WebService
public interface AuthorityService {

    // 根据ID查找权限信息
    String findAuthorityByID(String authority_id);

    // 查找所有权限信息
    List findAllAuthority(String authority_name, Integer page, Integer rows);

    // 查找权限总记录数
    Integer findAllAuthorityRowsCount();

    // 查找符合条件的权限总记录数
    Integer findAllAuthoritySearchCount(String authority_name);

    // 保存权限信息
    int saveAuthority(T_Sys_Authority authority);

    // 更新权限信息
    boolean updateAuthority(T_Sys_Authority authority);

    // 删除权限信息
    void delAuthority(String authority_id);
}
