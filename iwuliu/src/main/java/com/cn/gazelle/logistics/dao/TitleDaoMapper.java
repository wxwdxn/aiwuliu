/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TitleDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：会员电子协议标题基础信息管理
 * 设计文件：
 * 完成日期：2016-05-17
 * 作    者: YK
 * 标题摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Electronic_Agreement_Title;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称：TitleDaoMapper
 * 标题描述：会员电子协议标题基础信息管理DAO层
 * 方法描述：该类有 9 个方法
 *          01 findTitleByID                                           根据ID查找协议标题信息
 *          02 findTitleByName                                         根据电子协议编号查找协议标题信息
 *          03 findAllTitle                                            查找所有协议标题信息(有参)
 *          04 findAll                                                 查找所有协议标题信息(无参)
 *          05 findAllContentRowsCount                                 查找符合条件的协议标题总记录数
 *          06 saveTitle                                               保存协议标题信息
 *          07 updateTitle                                             更新协议标题信息
 *          08 delTitle                                                删除协议标题信息
 *          09 findTitleByMemberType                                   根据用户类型查找协议标题
 *@authot YK
 */
public interface TitleDaoMapper {

    // 根据ID查找协议标题信息
    T_Master_Electronic_Agreement_Title findTitleByID(@Param("title_id") String title_id);

    // 根据电子协议编号查找协议标题信息
    T_Master_Electronic_Agreement_Title findTitleByName(@Param("electronic_agreement_title") String electronic_agreement_title);

    // 查找所有协议标题信息(有参)
    List<T_Master_Electronic_Agreement_Title> findAllTitle(@Param("electronic_agreement_title") String electronic_agreement_title, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查找所有协议标题信息(无参)
    List<T_Master_Electronic_Agreement_Title> findAll();

    // 查找符合条件的协议标题总记录数
    Integer findAllTitleRowsCount(@Param("electronic_agreement_title") String electronic_agreement_title);

    // 保存协议标题信息
    int saveTitle(T_Master_Electronic_Agreement_Title title);

    // 更新协议标题信息
    void updateTitle(T_Master_Electronic_Agreement_Title title);

    // 删除协议标题信息
    void delTitle(String title_id);

    // 根据用户类型查找协议标题
    T_Master_Electronic_Agreement_Title findTitleByMemberType(@Param("member_type") String member_type);


}
