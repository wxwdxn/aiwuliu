/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CargoYardManagerMatchDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货场调度人员会员匹配信息查询接口实现
 * 设计文件：
 * 完成日期：2016-04-25
 * 作    者：YK
 * 内容摘要：货场调度人员会员匹配信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Cargo_Yard_Manager_Match;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: CargoYardManagerMatchDaoMapper
 * 内容摘要: 货场调度人员会员匹配信息管理页面
 * 方法描述：该类有6个方法：
 *         01 findById                                         根据ID查询
 *         02 saveCargoYardManagerMatch                        增加货场调度人员会员匹配信息
 *         03 cargoYardManagerMatchDel                         根据ID删除货场调度人员会员匹配信息
 *         04 findAllCargoYardManagerMatch                     查询货场调度人员会员匹配信息
 *         05 findAllCargoYardManagerMatchRowsCount            查询货场总记录数
 *         06 findAll                                          查询所有的货场不分页
 * @author YK
 */
public interface CargoYardManagerMatchDaoMapper {

    // 根据ID查询
    public T_Data_Cargo_Yard_Manager_Match findById(@Param(value = "manager_id") String manager_id);

    // 增加货场调度人员会员匹配信息
    public void saveCargoYardManagerMatch(T_Data_Cargo_Yard_Manager_Match match);

    // 根据ID删除货场调度人员会员匹配信息
    public void cargoYardManagerMatchDel(@Param(value = "manager_id") String manager_id);

    // 更新货场调度人员会员匹配信息
    public void updateCargoYardManagerMatch(T_Data_Cargo_Yard_Manager_Match match);

    // 查询货场调度人员会员匹配信息
    public List<T_Data_Cargo_Yard_Manager_Match> findAllCargoYardManagerMatch(@Param(value = "searchType") String searchType, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 查询货场总记录数
    public Integer findAllCargoYardManagerMatchRowsCount(@Param(value = "searchType") String searchType, @Param(value = "name") String name);

    // 查询所有的货场不分页
    public List<T_Data_Cargo_Yard_Manager_Match> findAll();

}
