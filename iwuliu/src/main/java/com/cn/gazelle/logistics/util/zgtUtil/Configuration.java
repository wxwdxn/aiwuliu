package com.cn.gazelle.logistics.util.zgtUtil;

import java.util.ResourceBundle;

/**
 * 访问配置文件 - 单例
 * Created by YK on 2016/8/16
 */

public class Configuration {
	
	private static ResourceBundle rb    			= null;
	private volatile static Configuration instance	= null;
	
	private Configuration(String configFile) {
		rb = ResourceBundle.getBundle(configFile);
	}
	
	public static Configuration getInstance(String configFile) {
		if(instance == null) {
			synchronized(Configuration.class) {
				if(instance == null) {
					instance = new Configuration(configFile);
				}
			}
		}
		return instance;
	}
	
	public String getValue(String key) {
		return (rb.getString(key));
	}
}
