/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: OperateMainLineDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运营干线基础信息管理实现
 * 设计文件：
 * 完成日期：2016-06-17
 * 作    者：QJ
 * 内容摘要：运营干线基础信息管理实现
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Master_Operate_Main_Line;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: OperateMainLineDaoMapper
 * 内容摘要: 运营干线基础信息管理
 * 方法描述：该类有9个方法：
 *         01 findOperateMainLineById           根据ID查运营干线基础信息
 *         02 findOperateMainLineByName         根据线路简称查询运营干线基础信息
 *         03 findAllOperateMainLine            查询符合条件的运营干线列表信息（默认查询运营干线列表信息）
 *         04 findAllOperateMainLineRowsCount   查询运营干线基础信息总记录数
 *         05 saveOperateMainLine               保存运营干线基础信息
 *         06 updateOperateMainLine             更新运营干线基础信息
 *         07 delOperateMainLine                根据ID删除运营干线基础信息
 *         08 findOperateMainLineList           查询所有运营干线信息不分页
 *         09 findOperateMainLineByStartCityAndFinishCity 根据起点城市ID终点城市ID查询运营干线基础信息
 * @author QJ
 */
public interface OperateMainLineDaoMapper {
    // 根据ID查询运营干线基础信息
    T_Master_Operate_Main_Line findOperateMainLineById(@Param(value = "operate_main_line_id") String operate_main_line_id);

    // 根据线路简称查询运营干线基础信息
    T_Master_Operate_Main_Line findOperateMainLineByName(@Param(value = "operate_main_line_name") String operate_main_line_name);

    // 查询符合条件的运营干线列表信息（默认查询运营干线列表信息）
    List<T_Master_Operate_Main_Line> findAllOperateMainLine(@Param("search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询运营干线基础信息总记录数
    Integer findAllOperateMainLineRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name);

    // 保存运营干线基础信息
    int saveOperateMainLine(T_Master_Operate_Main_Line operate_main_line);

    // 更新运营干线基础信息
    int updateOperateMainLine(T_Master_Operate_Main_Line operate_main_line);

    // 根据ID删除运营干线基础信息
    void delOperateMainLine(@Param(value = "operate_main_line_id") String operate_main_line_id);

    // 查询所有运营干线信息不分页
    List<T_Master_Operate_Main_Line> findOperateMainLineList();

    // 根据起点城市ID终点城市ID查询运营干线基础信息
    T_Master_Operate_Main_Line findOperateMainLineByStartCityAndFinishCity(@Param(value = "start_city_id") String start_city_id, @Param(value = "finish_city_id") String finish_city_id);
}
