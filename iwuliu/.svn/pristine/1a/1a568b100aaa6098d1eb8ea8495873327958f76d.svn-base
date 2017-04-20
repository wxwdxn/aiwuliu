package com.cn.gazelle.logistics.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CYH on 2015/12/21.
 */
public class ResponseUtil {

    public static void outWrite(HttpServletResponse response, Object o){
        Logger logger = Logger.getLogger(ResponseUtil.class);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out= null;
        try{
            out = response.getWriter();
            out.println(o.toString());
        }catch (Exception e){
            logger.error("Response输入错误！");
        }

        out.flush();
        out.close();
    }
}
