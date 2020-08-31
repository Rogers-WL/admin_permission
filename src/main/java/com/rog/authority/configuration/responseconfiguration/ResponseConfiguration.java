package com.rog.authority.configuration.responseconfiguration;

import com.alibaba.fastjson.JSON;
import com.rog.authority.configuration.exceptionconfiguration.ErrorResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 15:02
 **/
//如果全部异常处理返回json，那么可以使用 @RestControllerAdvice 代替 @ControllerAdvice ，
// 这样在方法上就可以不需要添加 @ResponseBody。
@ControllerAdvice(basePackages = "com.tyn.blog" )
public class ResponseConfiguration implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (o instanceof String){
            return JSON.toJSON(ResponseResult.success(o));
        } else if(o instanceof ErrorResult){
            ErrorResult error = (ErrorResult) o;
            return ResponseResult.fail(error.getStatus(),error.getMessage());
        }

        return ResponseResult.success(o);

    }
}
