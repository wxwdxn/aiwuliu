/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：ContentDaoMapper.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：会员电子协议内容基础信息管理
 * 设计文件：
 * 完成日期：2016-05-12
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Electronic_Agreement_Content;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称：ContentDaoMapper
 * 内容描述：会员电子协议内容基础信息管理DAO层
 * 方法描述：该类有 9 个方法
 *          01 findContentByID                           根据ID查找协议内容信息
 *          02 findContentByNo                           根据电子协议编号查找协议内容信息
 *          03 findAllContent                            查找所有协议内容信息(有参)
 *          04 findAll                                   查找所有协议内容信息(无参)
 *          05 findAllContentRowsCount                   查找符合条件的协议内容总记录数
 *          06 saveContent                               保存协议内容信息
 *          07 updateContent                             更新协议内容信息
 *          08 delContent                                删除协议内容信息
 *          09 findContentByMemberType                   根据用户类型查找协议内容信息
 *@authot YK
 */
public interface ContentDaoMapper {

    // 根据ID查找协议内容信息
    T_Master_Electronic_Agreement_Content findContentByID(@Param("content_id") String content_id);

    // 根据电子协议编号查找协议内容信息
    T_Master_Electronic_Agreement_Content findContentByNo(@Param("member_type") String member_type, @Param("electronic_agreement_version") String electronic_agreement_version);

    // 查找所有协议内容信息(有参)
    List<T_Master_Electronic_Agreement_Content> findAllContent(@Param("electronic_agreement_version") String electronic_agreement_version, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查找所有协议内容信息(无参)
    List<T_Master_Electronic_Agreement_Content> findAll();

    // 查找符合条件的协议内容总记录数
    Integer findAllContentRowsCount(@Param("electronic_agreement_version") String electronic_agreement_version);

    // 保存协议内容信息
    int saveContent(T_Master_Electronic_Agreement_Content content);

    // 更新协议内容信息
    void updateContent(T_Master_Electronic_Agreement_Content content);

    // 删除协议内容信息
    void delContent(String content_id);

    // 根据用户类型查找协议内容信息
    List<T_Master_Electronic_Agreement_Content> findContentByMemberType(@Param("member_type") String member_type);



}
