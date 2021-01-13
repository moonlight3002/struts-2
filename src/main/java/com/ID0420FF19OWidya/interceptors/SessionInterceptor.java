package com.ID0420FF19OWidya.interceptors;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor
{
    public String intercept(final ActionInvocation invocation) throws Exception {
        final Map<String, Object> session = invocation.getInvocationContext().getSession();
        System.out.println("in interceptor");
        if (session.get((Object)"userData") != null) {
            System.out.println("session not null");
            return invocation.invoke();
        }
        return "sessionInvalid";
    }
}