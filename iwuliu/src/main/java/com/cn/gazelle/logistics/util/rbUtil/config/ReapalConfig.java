package com.cn.gazelle.logistics.util.rbUtil.config;

/* *
 *功能：设置帐户有关信息及返回路径（基础配置页面）
 *版本：3.1.3
 *日期：2015-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究融宝支付接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.访问融宝支付商户后台，然后用您的签约融宝支付账号登陆(注册邮箱号).
 *2.点击导航栏中的“商家服务”，即可查看

 * */

import java.io.File;

public class ReapalConfig {
    public static String path = (System.getProperty("user.dir")).substring(0, System.getProperty("user.dir").lastIndexOf(File.separator));

    // 回调地址
//    public static String url = "http://localhost:8080/Logistics";
    public static String url = "http://59.110.60.97/Logistics";

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 联调的商户ID，由纯数字组成的字符串
//     public static String merchant_id = "100000000000147";
    //public static String merchant_id = "100000000011015";

    //正式商户ID
    public static String merchant_id = "100000001300598";

    // 联调的交易安全检验码，由数字和字母组成的64位字符串
//    public static String key = "g0be2385657fa355af68b74e9913a1320af82gb7ae5f580g79bffd04a402ba8f";
    //public static String key ="e977ade964836408243b5g2444848f7b39d09fb41c77ae2e327ffb16f905e117";

    // 正式交易安全检验码
    public static String key = "481140c101gfb90405ae097c1f42fe937f62b34ecf4498415g0a7a58c431gbag";

    // 签约融宝支付账号或卖家收款融宝支付帐户
    public static String seller_email = "850138237@qq.com";
    // 支付通知地址，由商户提供
    public static String notify_url = url + "/repal/notify";

    //卡密认证同步通知地址,由商户提供
    public static String certify_return_url = "http://10.168.15.61:8080/reapal-api/certificateResult.jsp";
    //卡密认证异步通知地址,由商户提供
    public static String certify_notify_url = "http://10.168.15.61:8080/reapal-api/certificateResult.jsp";
    //卡密认证请求地址   正式地址
    public static String certify_url = "http://testapi.reapal.com/fast/certificate";
    // 代付异步通知地址
    public static String agent_pay_notify_url = url + "/agentPay/payback";
    // 代付异步通知地址(车辆扫码支付线下加盟服务商)
    public static String station_agent_pay_notify_url = url + "/agentPay/stationPayBack";
    // 商户私钥
    public static String privateKey = path + "/webapps/Logistics/WEB-INF/cert/user-rsa.pfx";
//    	public static String privateKey = "H:/Tomcat8/apache-tomcat-8.0.30/webapps/DL256Download/cert/itrus001.pfx";
    // 测试的私钥密码
//    public static String password = "123456";
    // 正式的私钥密码
    public static String password = "dl256";
    // 测试环境地址
//    public static String rongpay_api = "http://testapi.reapal.com";
//    public static String agentpay_api = "http://testagentpay.reapal.com";

    //正式环境地址
    public static String rongpay_api = "http://api.reapal.com";
    public static String agentpay_api = "https://agentpay.reapal.com/";


    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    // 融宝公钥 正式环境不用更换
    public static String pubKeyUrl = path + "/webapps/Logistics/WEB-INF/cert/rongbao.cer";
//    	public static String pubKeyUrl = "H:/Tomcat8/apache-tomcat-8.0.30/webapps/DL256Download/cert/itrus001.cer";
    // 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
    public static String transport = "http";
    // 绑卡接口版本号
    public static String identify_version = "3.1.3";
    // 代付接口版本号
    public static String agentPay_version = "1.0";
    // 字符编码格式 目前支持 utf-8
    public static String charset = "utf-8";
    // 签名方式 不需修改
    public static String sign_type = "MD5";
    // 批次序号
    public static String order_no = "1";
    // 公/私
    public static String withdraw_type = "私";     // 默认
    // 币种
    public static String money_type = "CNY";
    // 证件类型
    public static String card_type = "身份证";


}
