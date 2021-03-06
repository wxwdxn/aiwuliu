/**
 * 版权所有：版权所有(C) 2015
 * 文件名称: CargoService.Java
 * 系统编号：Z0001002
 * 系统名称：物流管理平台
 * 模块编号：02
 * 模块名称：货场信息管理
 * 设计文件：
 * 完成日期：2016-03-01
 * 作    者：WXW
 * 内容摘要：货场信息查询
 */
package com.cn.gazelle.logistics.service;

import com.cn.gazelle.logistics.pojo.T_Master_Cargo_Yard;

import javax.jws.WebService;
import java.util.List;

/**
 * 类 名 称: CargoService
 * 内容摘要: 货场信息管理
 * 方法描述：该类有7个方法：
 *         01 findUserByID                   根据ID查货场信息
 *         02 findCargoByName                根据货场名查找货场信息
 *         03 findAllCargo                   查找所有货场信息
 *         04 findCargoRowsCount             查询货场总记录数
 *         05 saveCargo                      保存货场信息
 *         06 updateCargo                    更新货场信息
 *         07 CargoDel                       删除货场信息
 * @author WXW
 */
@WebService
public interface CargoService {
   
    //增加货场
    int saveCargo(T_Master_Cargo_Yard cargo_yard);
    
    //删除货场信息
    void CargoDel(String cargo_id);
  
    //编辑货场信息
    boolean updateCargo(T_Master_Cargo_Yard cargo_yard);

    //查询所有的货场不分页
    List<T_Master_Cargo_Yard> findCargoList();

    //根据ID查询
    T_Master_Cargo_Yard findById(String cargo_id);

    //根据位置模糊查询货场
    List<T_Master_Cargo_Yard> getFrightYardByPosition( String province_id ,String city_id,String area_id,String town_street);

}
