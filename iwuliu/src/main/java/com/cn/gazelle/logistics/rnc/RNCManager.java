/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：RNCManager.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-12-30
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.rnc;


import com.cn.gazelle.logistics.util.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 类 名 称：RNCManager
 * 内容描述：实名认证（聚合第三方接口）
 * 方法描述：该类有 个方法
 * 01
 * 02
 *
 * @authot YK
 */
public class RNCManager {
    private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    private static final String APPKEY = "72fcd5290fecca4ecb4e93c5e1fbf232";
    // 身份证实名认证接口请求地址
    private static final String rnc_url = "http://op.juhe.cn/idcard/query";
    // 请求方法
    private static final String req_method = "GET";


    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    // 实名认证接口
    public static boolean realNameAuthentication(String realname, String idcard) {
        /**
         *
         *错误码	说明
         210301	库中无此身份证记录
         210302	第三方服务器异常
         210303	服务器维护
         210304	参数异常
         210305	网络错误，请重试
         210306	参数错误，具体参照reason
         错误码	说明	旧版本（resultcode）
         10001	错误的请求KEY	101
         10002	该KEY无请求权限	102
         10003	KEY过期	103
         10004	错误的OPENID	104
         10005	应用未审核超时，请提交认证	105
         10007	未知的请求源	107
         10008	被禁止的IP	108
         10009	被禁止的KEY	109
         10011	当前IP请求超过限制	111
         10012	请求超过次数限制	112
         10013	测试KEY超过请求限制	113
         10014	系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)	114
         10020	接口维护	120
         10021	接口停用	121
         * **/

        boolean b = new Boolean(true);
        String rs = null;              // net()方法返回值
        String res = null;             // 1：匹配 2：不匹配
        String error_code = null;      // 聚合数据返回代码值
        Map params = new HashMap();    // 参数
        Map result = new HashMap();    // 聚合数据返回结果集
        Gson gson = new Gson();
        try {
            params.put("realname", realname);
            params.put("idcard", idcard);
            params.put("key", APPKEY);
            rs = net(rnc_url, params, req_method);
            Map<String, Object> data = gson.fromJson(rs, new TypeToken<Map<String, Object>>() {
            }.getType());
            System.out.println("data=" + JSONUtil.toJSONString(data));
            error_code = String.valueOf(data.get("error_code"));
            System.out.println("error_code=" + error_code);
            //  判断接口是否调用成功
            if (error_code.equals("0.0")) {
                result = (Map) data.get("result");
                res = String.valueOf(result.get("res"));
                // 判断信息是否匹配
                if (res.equals("1.0")) {
                    b = true;
                } else if (res.equals("2.0")) {
                    b = false;
                }
            } else {
                b = false;
            }
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }


    public static void main(String[] args) {
        String idcard = "130634199003223557";
        String realname = "王旭伟";

        try {
            boolean b = realNameAuthentication(realname, idcard);
            System.out.println("b=" + b);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//    rs={"reason":"参数异常","result":null,"error_code":210304}
}

