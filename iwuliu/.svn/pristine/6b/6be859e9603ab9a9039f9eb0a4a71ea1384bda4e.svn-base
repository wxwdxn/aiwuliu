package com.cn.gazelle.logistics.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by CYH on 2016/1/15.
 */
public class PropertiesUtil {
    public String getPropertiesKey(String propertiesfile,String key) {
        Properties pps = new Properties();
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream("/"+propertiesfile);
            if (in != null) {
                pps.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pps.getProperty(key);
    }

}
