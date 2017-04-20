package com.cn.gazelle.logistics.interceptor;

/**
 * Created by CYH on 2015/12/4.
 */

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.soap.SOAPException;

import org.apache.cxf.interceptor.Fault;
import org.apache.ws.security.WSPasswordCallback;

public class WsAuthInterceptor implements CallbackHandler {

    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback wspa = (WSPasswordCallback) callbacks[i];
            String identifier = wspa.getIdentifier();
            int usage = wspa.getUsage();
            if (usage == WSPasswordCallback.USERNAME_TOKEN) {
                // 密钥方式USERNAME_TOKEN
                // 从cxf2.4.x后校验方式改为cxf内部实现校验，不必自己比较password是否相同

                /**
                 * 如果和客户端不同将抛出
                 * org.apache.ws.security.WSSecurityException
                 */
                wspa.setPassword("dl256");
            } else if (usage == WSPasswordCallback.SIGNATURE) {
                // 密钥方式SIGNATURE
                wspa.setPassword("123456");
            }
            if ("dl256".equals(identifier)) {
                // 从cxf2.4.x后校验方式改为cxf内部实现校验，不必自己比较password是否相同
                System.out.println("------" + identifier + " 验证通过，放行------");
            } else {
                System.out.println("-------" + identifier + " 验证不通过，抛出异常----");
                SOAPException exception = new SOAPException("WSAuthHandler:非法操作!");
                throw new Fault(exception);
            }
        }
    }

}