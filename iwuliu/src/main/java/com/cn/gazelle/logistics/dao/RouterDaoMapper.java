package com.cn.gazelle.logistics.dao;

import com.cn.gazelle.logistics.pojo.T_Data_Router;
import com.cn.gazelle.logistics.pojo.T_Data_Router_Detail;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: RouterDaoMapper
 * 内容摘要: 线路管理页面
 * 方法描述：该类有2个方法：
 * 01 findRouterList        根据条件查找线路列表
 * 02 findRouterDetailList  运营干线ID查询干线、线路、节点信息
 *
 * @author QJ
 */
public interface RouterDaoMapper {
    // 根据条件查找线路列表
    List<T_Data_Router> findRouterList(HashMap<String, String> conditions);

    // 运营干线ID查询干线、线路、节点信息
    List<T_Data_Router_Detail> findRouterDetailList(@Param(value = "operate_main_line_id") String operate_main_line_id);
}
