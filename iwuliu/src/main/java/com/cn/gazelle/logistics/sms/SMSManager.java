package com.cn.gazelle.logistics.sms;

import com.cn.gazelle.logistics.util.JSONUtil;
import com.cn.gazelle.logistics.util.PropertiesUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CYH on 2016/1/11.
 */
public class SMSManager {
    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

    //通用发送接口的http地址
    private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "http://yunpian.com/v1/voice/send.json";

    //apikey
    //private static String APIKEY = "3897bcc2d757ec7c7f91276709cb22ed";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    /**
     * 取账户信息
     */
    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 通用接口发短信
     */
    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        String tlp = "您的验证码是:"+text;
        System.out.print(tlp);
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", new PropertiesUtil().getPropertiesKey("phone-msg.properties","key"));
        params.put("text", tlp);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }


    /**
     * 通用接口发短信  重构
     */
    public static String sendSms(String text, String mobile) throws IOException {
        String tlp = "您的验证码是:"+text;
        System.out.println("短信已发送："+tlp);
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", new PropertiesUtil().getPropertiesKey("phone-msg.properties","key"));
        params.put("text", tlp);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 短信模板发送短信 两参
     */
    public static String tplSendSms(String tel,String code,String mobile) throws IOException {
        String tpl_value = URLEncoder.encode("#tel#",ENCODING) +"="
                + URLEncoder.encode(tel, ENCODING) + "&"
                + URLEncoder.encode("#code#",ENCODING) + "="
                + URLEncoder.encode(code,ENCODING);
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", new PropertiesUtil().getPropertiesKey("phone-msg.properties","key"));
        params.put("tpl_id", String.valueOf(new PropertiesUtil().getPropertiesKey("phone-msg.properties","tpl_id")));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }


    /**
     * 基于HttpClient 4.3的通用POST方法
     */
    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("短信平台返回："+ JSONUtil.toJSONString(responseText));
        return responseText;
    }
}