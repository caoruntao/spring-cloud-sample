package com.crt.spring.cloud.hystrix.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeoutException;

/**
 * @author crt
 * @date 2020/9/10 9:32 上午
 */
public class ErrorInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        boolean equals = "/middle/say".equals(request.getRequestURI());
        if(equals && ex instanceof TimeoutException){
            PrintWriter writer = response.getWriter();
            String errorResult = errorSay("by interceptor");
            writer.print(errorResult);
            writer.flush();
        }
    }

    public String errorSay(String message){
        return String.format("Error %s", message);
    }
}
