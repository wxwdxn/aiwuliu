package com.cn.gazelle.logistics.service;


import com.cn.gazelle.logistics.pojo.T_Data_Router;
import com.cn.gazelle.logistics.pojo.T_Data_Router_Detail;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

/**
 * 类 名 称: RouterService
 * 内容摘要: 线路管理接口
 * 方法描述：该类有4个方法：
 * 01 findRouterList        根据条件查找线路列表
 * 02 findRouterDetailList  运营干线ID查询干线、线路、节点信息
 * 03 saveRouterInfo        新增线路信息
 * 04 saveRouterInfo        新增线路信息
 *
 * @author QJ
 */
@WebService
public interface RouterService {
    // 根据条件查找线路列表
    List<T_Data_Router> findRouterList(HashMap<String, String> conditions);

    // 根据条件查找线路列表
    List<T_Data_Router_Detail> findRouterDetailList(String operate_main_line_id);

    // 新增线路信息
    int saveRouterInfo(String list, String userName);

    // 编辑线路信息
    int upDateRouterInfo(String list, String userName);
}
