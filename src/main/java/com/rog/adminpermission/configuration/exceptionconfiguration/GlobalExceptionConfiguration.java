package com.rog.adminpermission.configuration.exceptionconfiguration;

import com.rog.adminpermission.configuration.exceptionconfiguration.exception.SelfAccountException;
import com.rog.adminpermission.constant.sys.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/23 19:10
 **/
@ControllerAdvice
public class GlobalExceptionConfiguration {
    private  final Logger log = LoggerFactory.getLogger(GlobalExceptionConfiguration.class);

    //修改返回网络状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //对某一类异常的处理
    @ExceptionHandler(Throwable.class)
    public ErrorResult throwableExHandler(Throwable e, HttpServletRequest request){
        ErrorResult errorResult = ErrorResult.fail(ResultCode.SYSTEM_ERROR,e);
        log.error("请求路径"+request.getRequestURL()+"异常"+e);
        return errorResult;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SelfAccountException.class)
    public ErrorResult selfAccountExHandler(SelfAccountException e,HttpServletRequest request){
        ErrorResult error = ErrorResult.builder().status(e.getCode())
                .message(e.getMessage())
                .exception(e.getClass().getName()).build();
        log.warn("请求路径"+request.getRequestURL()+"异常"+e);
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult methodArgumentNotValidExHandler(MethodArgumentNotValidException e, HttpServletRequest request){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder message = new StringBuilder();
        fieldErrors.forEach(s ->{
            String defaultMessage = s.getDefaultMessage();
            message.append(defaultMessage).append(";");
        });
        log.warn("请求路径"+request.getRequestURL()+"异常"+e);
        return ErrorResult.fail(ResultCode.PARAM_IS_INVALID,e,message.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalArgumentExHandler(IllegalArgumentException e, HttpServletRequest request){
        ErrorResult error = ErrorResult.builder().status(2000)
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("请求路径"+request.getRequestURL()+"异常"+e);
        return error;
    }
}
