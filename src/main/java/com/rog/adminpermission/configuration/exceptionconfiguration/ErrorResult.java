package com.rog.adminpermission.configuration.exceptionconfiguration;

import com.rog.adminpermission.constant.sys.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 作为异常捕获返回结果
 * @Author Rogers
 * @Date 2020/5/23 18:51
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {
    private int status;
    private String message;
    private String exception;

    public static ErrorResult fail(ResultCode resultCode,Throwable e,String message){
        ErrorResult result = ErrorResult.fail(resultCode,e);
        result.setMessage(message);
        return result;
    }

    public static ErrorResult fail(ResultCode resultCode,Throwable e){
        ErrorResult result = new ErrorResult();
        result.setStatus(resultCode.code());
        result.setMessage(resultCode.message());
        result.setException(e.getClass().getName());
        return result;
    }
}
