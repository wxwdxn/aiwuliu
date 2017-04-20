package com.cn.gazelle.logistics.util.rbUtil.config;

import com.alibaba.fastjson.JSON;
import com.cn.gazelle.logistics.util.JSONUtil;

import java.util.Map;

/* *
 *类名：ReapalSubmit
 *功能：融宝各接口请求提交类
 *详细：构造融宝各接口发送请求数据，获取远程HTTP数据
 *版本：3.1.2
 *日期：2015-08-15
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究融宝接口使用，只是提供一个参考。
 */

public class ReapalSubmit {
	/**
	 * 发送请求
	 * 
	 * @return 返回结果
	 */
	public static String identifyBuildSubmit(Map<String, String> para, String url)
			throws Exception {
		// 生成签名
		String mysign = Md5Utils.BuildMysign(para, ReapalConfig.key);
		System.out.println("********************签名sign为：" + mysign);
		para.put("sign_type", "MD5");
		para.put("sign", mysign);
		// 将map转化为json 串
		String json = JSON.toJSONString(para);
		// 加密数据
		Map<String, String> maps = Decipher.encryptData(json);
		maps.put("merchant_id", ReapalConfig.merchant_id);
		// 发送请求 并接收
		System.out.println("********************请求参数为：" + maps);
		String post = HttpClientUtil.post(ReapalConfig.rongpay_api + url, maps);
		return post;
	}

	public static String agentPayBuildSubmit(Map<String, String> para, String url)
			throws Exception {
		String mysign = Md5Utils.BuildMysign(para, ReapalConfig.key);
		System.out.println("********************签名sign为：" + mysign);
		para.put("sign_type", "MD5");
		para.put("sign", mysign);
		// 将map转化为json 串
		String json = JSONUtil.toJSONString(para);
		// 加密数据
		Map<String, String> maps = Decipher.encryptData(json);
		maps.put("merchant_id", ReapalConfig.merchant_id);
		maps.put("version",ReapalConfig.agentPay_version);
		//logger.info(maps);
		String post = HttpClientUtil.post(ReapalConfig.agentpay_api+url,maps);
		return post;
	}
}