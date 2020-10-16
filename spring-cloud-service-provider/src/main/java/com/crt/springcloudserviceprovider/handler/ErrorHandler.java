package com.crt.springcloudserviceprovider.handler;

import com.crt.springcloudserviceprovider.pojo.ResultData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeoutException;

/**
 * @author Reed
 * @date 2020/10/16 2:00 下午
 * 全局异常处理
 */
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(InterruptedException.class)
    public ResultData handlerInterruptedException(HttpServletRequest req, HttpServletResponse rep, InterruptedException exception){
        return ResultData.error(400, null);
    }
    @ExceptionHandler(TimeoutException.class)
    public ResultData<String> handlerhandlerInterruptedException(HttpServletRequest req, HttpServletResponse rep, TimeoutException exception){
        return ResultData.error(400, "Error occurred");
    }


}
