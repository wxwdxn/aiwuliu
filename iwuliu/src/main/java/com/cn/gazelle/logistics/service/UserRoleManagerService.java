/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：UserRoleManagerServiceImpl.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-09
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.service;

import javax.jws.WebService;

/**
 * 类 名 称：UserRoleManagerServiceImpl
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
@WebService
public interface UserRoleManagerService {
    // 用户角色新增
    int saveUserRoleInfo(String info, String username);

    // 用户角色编辑
    int updateUserRoleInfo(String member_id, String user_id, String info, String username);

    // 用户角色删除
    int delUserRoleInfo(String member_id);

}
