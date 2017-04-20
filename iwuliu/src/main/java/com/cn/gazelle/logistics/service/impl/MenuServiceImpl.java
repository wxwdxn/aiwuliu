/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: MenuServiceImpl.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：菜单信息查询接口声明
 * 设计文件：
 * 完成日期：2016-01-07
 * 作    者：YK
 * 内容摘要：菜单信息查询
 */
package com.cn.gazelle.logistics.service.impl;

import com.cn.gazelle.logistics.dao.MenuDaoMapper;
import com.cn.gazelle.logistics.pojo.T_Sys_Menu;
import com.cn.gazelle.logistics.service.MenuService;
import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.message.MenuManager_Message;
import com.cn.gazelle.logistics.util.message.base.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: MenuServiceImpl
 * 内容摘要: 菜单信息查询接口
 * 方法描述：该类有12个方法：
 * 01 findMenuByID                        根据ID查询菜单信息
 * 02 findMenuByMenuName                  根据菜单名查询菜单信息
 * 03 findParentRoot                      查询菜单父节点
 * 04 findAllParentRoot                   查询所有父节点
 * 05 findChildRoot                       查找菜单子节点
 * 06 findGroupNameOfUser                 查询用户所在分组
 * 07 findAllMenu                         查询所有菜单信息
 * 08 findAllMenuRowsCount                查询菜单信息总记录数
 * 09 findAllMenuSearchCount              查询符合条件的总记录数
 * 10 saveMenu                            保存菜单信息
 * 11 updateMenu                          更新菜单信息
 * 12 delMenu                             删除菜单信息
 *
 * @author YK
 */
@Service
@WebService(endpointInterface = "com.cn.gazelle.logistics.service.MenuService", targetNamespace = "http://service.logistics.gazelle.cn.com/")
public class MenuServiceImpl implements MenuService {
    Logger logger = Logger.getLogger(MenuServiceImpl.class);

    @Resource
    private MenuDaoMapper menuDaoMapper;

    /**
     * 方法名称：findMenuByID
     * 内容摘要：根据ID查询菜单信息。
     *
     * @param menu_id 菜单id
     * @return String 菜单信息json
     */
    public String findMenuByID(String menu_id) {
        T_Sys_Menu menu = null;
        String str = null;
        try {
            menu = this.menuDaoMapper.findMenuByID(menu_id);
            str = JSONUtil.toJSONString(menu);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return str;
    }

    /**
     * 方法名称：findMenuByMenuName
     * 内容摘要：根据菜单名查询菜单信息
     *
     * @param menu_name 菜单名称
     * @return T_Sys_Menu 菜单信息
     */
    public T_Sys_Menu findMenuByMenuName(String menu_name) {
        T_Sys_Menu menu = null;
        try {
            menu = this.menuDaoMapper.findMenuByMenuName(menu_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return menu;
    }

    /**
     * 方法名称：findParentRoot
     * 内容摘要：查询菜单父节点
     *
     * @param group_name 组名
     * @return List<T_Sys_Menu> 菜单信息列
     */
    public List<T_Sys_Menu> findParentRoot(String group_name) {
        List<T_Sys_Menu> menuList = null;
        try {
            menuList = this.menuDaoMapper.findParentRoot(group_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return menuList;
    }

    /**
     * 方法名称：findAllParentRoot
     * 内容摘要：查询菜单父节点
     *
     * @return List<T_Sys_Menu> 菜单信息列
     */
    public List<T_Sys_Menu> findAllParentRoot() {
        List<T_Sys_Menu> menuList = null;
        try {
            menuList = this.menuDaoMapper.findAllParentRoot();
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return menuList;
    }

    /**
     * 方法名称：findChildRoot
     * 内容摘要：查询菜单子节点
     *
     * @return List<T_Sys_Menu> 菜单信息列
     */
    public List<T_Sys_Menu> findChildRoot(String menu_pid, String group_name) {
        List<T_Sys_Menu> menuList = null;
        try {
            menuList = this.menuDaoMapper.findChildRoot(menu_pid, group_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return menuList;
    }

    /**
     * 方法名称：findGroupNameOfUser
     * 内容摘要：查询用户所在分组
     *
     * @param login_name 登录用户名
     * @return T_Sys_Menu 菜单对象
     */
    public T_Sys_Menu findGroupNameOfUser(String login_name) {
        T_Sys_Menu menu = null;
        try {
            menu = this.menuDaoMapper.findGroupNameOfUser(login_name);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return menu;
    }

    //查询所有菜单信息

    /**
     * 方法名称：findAllMenu
     * 内容摘要：查询所有企业信息
     *
     * @param menu_name 菜单名称
     * @param page      页面页数
     * @param rows      页面显示条数
     * @return List<T_Sys_Menu>  公司信息list
     */
    public List<T_Sys_Menu> findAllMenu(String menu_name, Integer page, Integer rows) {
        List<T_Sys_Menu> menuList = null;
        try {
            menuList = this.menuDaoMapper.findAllMenu(menu_name, page, rows);
            logger.info(MessageUtil.seacheInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.seacheInfoError + e.getMessage());
        }
        return menuList;
    }

    /**
     * 方法名称：findAllMenuRowsCount
     * 内容摘要：查询菜单信息总记录数
     *
     * @return Integer  菜单信息总记录数
     */
    public Integer findAllMenuRowsCount() {
        int count = 0;
        try {
            count = this.menuDaoMapper.findAllMenuRowsCount();
        } catch (Exception e) {
            logger.error(MenuManager_Message.getSelectMenuCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：findAllMenuSearchCount
     * 内容摘要：查询符合条件的总记录数
     *
     * @param menu_name 菜单名称
     * @return Integer  菜单信息总记录数
     */
    public Integer findAllMenuSearchCount(String menu_name) {
        int count = 0;
        try {
            count = this.menuDaoMapper.findAllMenuSearchCount(menu_name);
        } catch (Exception e) {
            logger.error(MenuManager_Message.getSelectMenuCountError + e.getMessage());
        }
        return count;
    }

    /**
     * 方法名称：saveMenu
     * 内容摘要：保存菜单信息
     *
     * @param menu 菜单信息
     */
    public boolean saveMenu(T_Sys_Menu menu) {
        boolean b = new Boolean(true);
        try {
            this.menuDaoMapper.saveMenu(menu);
            logger.info(MessageUtil.saveInfo);
        } catch (Exception e) {
            b = false;
            logger.error(MessageUtil.saveInfoError + e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：updateMenu
     * 内容摘要：更新菜单信息
     *
     * @param menu 菜单信息
     */
    public boolean updateMenu(T_Sys_Menu menu) {
        boolean b = new Boolean(true);
        try {
            this.menuDaoMapper.updateMenu(menu);
        } catch (Exception e) {
            b = false;
            logger.error(e.getMessage());
        }
        return b;
    }

    /**
     * 方法名称：delMenu
     * 内容摘要：删除菜单信息
     *
     * @param menu_id 菜单id
     */
    public void delMenu(String menu_id) {
        try {
            this.menuDaoMapper.delMenu(menu_id);
            logger.info(MessageUtil.delInfo);
        } catch (Exception e) {
            logger.error(MessageUtil.delInfoError + e.getMessage());
        }
    }
}
