package com.cn.gazelle.logistics.interceptor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CYH on 2015/12/4.
 */

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    Logger logger = Logger.getLogger(LoginInterceptor.class);
    private static final String LOGIN_URL="/iwuliu/login/timeout";

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        logger.info("进入spring拦截器preHandle方法");

        //获取session中的用户登录信息
        Object obj =  request.getSession().getAttribute("username");
        if (obj==null||"".equals(obj.toString())) {
            logger.info("非法访问已被拦截，系统将跳转到登录页面！" + request.getServletPath());
            response.sendRedirect(LOGIN_URL);
            return false;
        }  else
            return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.info("进入系统的postHandle方法");
        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("最后一步执行……");
    }
}
