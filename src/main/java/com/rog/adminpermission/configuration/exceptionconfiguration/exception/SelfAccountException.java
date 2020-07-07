package com.rog.adminpermission.configuration.exceptionconfiguration.exception;

import com.rog.adminpermission.constant.sys.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/23 19:04
 **/

@Data
public class SelfAccountException extends RuntimeException{
    private int code;
    private String message;

    public SelfAccountException(ResultCode resultCode){
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public SelfAccountException(int code, String message){
        this.code = code;
        this.message = message;
    }
}
