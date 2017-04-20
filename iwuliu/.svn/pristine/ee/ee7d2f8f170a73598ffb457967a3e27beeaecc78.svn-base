/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CompanyDaoMapper.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：物流公司信息查询接口实现
 * 设计文件：
 * 完成日期：2016-02-18
 * 作    者：YK
 * 内容摘要：物流公司信息查询
 */
package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类 名 称: CompanyDaoMapper
 * 内容摘要: 公司信息管理页面
 * 方法描述：该类有14个方法：
 *         01 findCompanyByID                 根据企业id查询企业信息
 *         02 findCompanyByName               根据企业名查询企业信息
 *         03 findCompanyName                 查询所有的企业名（无参）
 *         04 findAllCompany                  查询所有企业信息（有参）
 *         05 findAllCompanyRowsCount         查询企业总记录数
 *         06 saveCompany                     保存企业信息
 *         07 updateCompany                   更新企业信息
 *         08 examineCompany                  审核企业信息
 *         09 delCompany                      删除企业信息
 *         10 findAllShipper                  查询所有货主信息
 *         11 findAllShipperRowsCount         货主列表总数
 *         12 findCompanyList                 查询所有企业信息不分页
 *         13 findShipperList                 查询所有货主信息不分页
 *         14 findShipperByName               根据货主名称查询
 * @author YK
 */
public interface CompanyDaoMapper {

    // 根据企业id查询企业信息
    T_Data_Company findCompanyByID(@Param(value = "company_id") String company_id);

    // 根据企业名查询企业信息
    T_Data_Company findCompanyByName(@Param(value = "company_name") String company_name,@Param(value = "company_type")String company_type);

    // 查询所有的企业名
    List<T_Data_Company>  findCompanyName(@Param(value = "company_type")String company_type);

    // 查询所有企业信息
    List<T_Data_Company> findAllCompany(@Param(value = "search_type") String search_type, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows,@Param(value = "company_type")String company_type);

    // 查询企业总记录数
    Integer findAllCompanyRowsCount(@Param(value = "search_type") String search_type, @Param(value = "name") String name,@Param(value = "company_type")String company_type);

    // 保存企业信息
    int saveCompany(T_Data_Company company);

    // 更新企业信息
    void updateCompany(T_Data_Company company);

    // 审核企业信息
    void examineCompany(T_Data_Company company);

    // 删除企业信息
    void delCompany(@Param(value = "company_id") String company_id);

    // 物流公司列表查询（页面）
    List<T_Data_Company> queryCompanyList(@Param(value = "company_name") String company_name,
                                          @Param(value = "contact_name") String contact_name,
                                          @Param(value = "contact_mobile_phone") String contact_mobile_phone,
                                          @Param(value = "business_licence") String business_licence,
                                          @Param(value = "city_id") String city_id
    );
    // 物流公司详情页回显
    T_Data_Company findCompanyDetailByID(@Param(value = "company_id") String company_id);

    //==============================================================================

    // 查询所有货主信息
    List<T_Data_Company> findAllShipper(@Param(value = "searchType") String searchType, @Param(value = "name") String name, @Param("page") Integer page, @Param("rows") Integer rows);

    // 货主列表总数
    Integer findAllShipperRowsCount(@Param(value = "searchType") String searchType, @Param(value = "name") String name);

    // 查询所有企业信息不分页
    List<T_Data_Company> findCompanyList();

    // 查询所有企业货主信息不分页
    List<T_Data_Company> findCompanyShipperList();

    // 根据企业id查询企业货主信息
    T_Data_Company findCompanyShipperByID(@Param(value = "company_id") String company_id);

    // 根据货主名称查询
    T_Data_Company findShipperByName(@Param(value = "contact_name") String contact_name);

    // 保存企业货主信息
    int saveCompanyShipper(T_Data_Company company);

    // 更新企业货主信息
    void updateCompanyShipper(T_Data_Company company);
}
