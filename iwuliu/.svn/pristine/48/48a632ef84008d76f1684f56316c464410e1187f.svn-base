/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: OperateMainLineService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：运营干线基础信息查询接口声明
 * 设计文件：
 * 完成日期：2016-06-17
 * 作    者：QJ
 * 内容摘要：运营干线基础信息查询
 */

package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Operate_Main_Line;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: OperateMainLineService
 * 内容摘要: 运营干线基础信息查询
 * 方法描述：该类有8个方法：
 *         01 findOperateMainLineById           根据ID查运营干线基础信息
 *         02 findOperateMainLineByName         根据线路简称查询运营干线基础信息
 *         03 findAllOperateMainLine            查询符合条件的运营干线列表信息（默认查询运营干线列表信息）
 *         04 findAllOperateMainLineRowsCount   查询运营干线基础信息总记录数
 *         05 saveOperateMainLine               保存运营干线基础信息
 *         06 updateOperateMainLine             更新运营干线基础信息
 *         07 delOperateMainLine                根据ID删除运营干线基础信息
 *         08 delSubLineInfoByOperateMainLineId 根据运营干线ID删除干线路线基础信息干线路线节点信息
 * @author QJ
 */
@WebService
public interface OperateMainLineService {
    // 根据ID查运营干线基础信息
    T_Master_Operate_Main_Line findOperateMainLineById(String operate_main_line_id);

    // 根据线路简称查询运营干线基础信息
    T_Master_Operate_Main_Line findOperateMainLineByName(String operate_main_line_name);

    // 查询符合条件的运营干线列表信息（默认查询运营干线列表信息）
    List<T_Master_Operate_Main_Line> findAllOperateMainLine(String search_type, String name, Integer page, Integer rows);

    // 查询运营干线基础信息总记录数
    public Integer findAllOperateMainLineRowsCount(String search_type, String name);

    // 保存运营干线基础信息
    int saveOperateMainLine(T_Master_Operate_Main_Line operate_main_line);

    // 更新运营干线基础信息
    boolean updateOperateMainLine(T_Master_Operate_Main_Line operate_main_line);

    // 根据ID删除运营干线基础信息
    void delOperateMainLine(String operate_main_line_id);

    // 根据运营干线ID删除干线路线基础信息干线路线节点信息
    void delSubLineInfoByOperateMainLineId(String operate_main_line_id);

}
