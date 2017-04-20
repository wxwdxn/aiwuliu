/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MenuService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：菜单信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-07
 * 作    者：YK
 * 内容摘要：菜单信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Sys_Menu;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: MenuService
 * 内容摘要: 菜单信息查询接口
 * 方法描述：该类有12个方法：
 *         01 findMenuByID                        根据ID查询菜单信息
 *         02 findMenuByMenuName                  根据菜单名查询菜单信息
 *         03 findParentRoot                      查询菜单父节点
 *         04 findAllParentRoot                   查询所有父节点
 *         05 findChildRoot                       查找菜单子节点
 *         06 findGroupNameOfUser                 查询用户所在分组
 *         07 findAllMenu                         查询所有菜单信息
 *         08 findAllMenuRowsCount                查询菜单信息总记录数
 *         09 findAllMenuSearchCount              查询符合条件的总记录数
 *         10 saveMenu                            保存菜单信息
 *         11 updateMenu                          更新菜单信息
 *         12 delMenu                             删除菜单信息
 * @author YK
 */
@WebService
public interface MenuService {

    // 根据ID查询菜单信息
    public String findMenuByID(String menu_id);

    // 根据菜单名查询菜单信息
    public T_Sys_Menu findMenuByMenuName(String menu_name);

    // 查询菜单父节点
    public List<T_Sys_Menu> findParentRoot(String group_name);

    // 查询所有父节点
    public List<T_Sys_Menu> findAllParentRoot();

    // 查找菜单子节点
    public List<T_Sys_Menu> findChildRoot(String menu_pid, String group_name);

    // 查询用户所在分组
    public T_Sys_Menu findGroupNameOfUser(String login_name);

    // 查询所有菜单信息
    public List<T_Sys_Menu> findAllMenu(String menu_name, Integer page, Integer rows);

    // 查询菜单信息总记录数
    public Integer findAllMenuRowsCount();

    // 查询符合条件的总记录数
    public Integer findAllMenuSearchCount(String menu_name);

    // 保存菜单信息
    public boolean saveMenu(T_Sys_Menu menu);

    // 更新菜单信息
    public boolean updateMenu(T_Sys_Menu menu);

    // 删除菜单信息
    public void delMenu(String menu_id);
}
