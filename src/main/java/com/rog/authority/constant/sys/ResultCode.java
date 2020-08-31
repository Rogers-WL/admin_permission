package com.rog.authority.constant.sys;

/**
 * @Description: //TODO
 * @Author: Rogers_WL
 * @Date: 2020/5/22 15:44
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "成功"),

    /* 系统500错误*/
    SYSTEM_ERROR(10000, "系统异常，请稍后重试"),


    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),


    /* 用户错误：20001-29999*/
    USER_HAS_EXISTED(20001, "用户已存在");

    private int code;
    private String message;

    ResultCode( int code, String message) {
        this.code = code;
        this.message = message;
    }
    //java会默认使用this.code
    public int code(){
        return code;
    }
    public String message(){
        return this.message;
    }
    public int getCode(String name){
        for (ResultCode r:ResultCode.values()) {
            if (r.name().equals(name)) {
                return r.code;
            }
        }
        return -1;
    }
    public String getMessage(String name){
        for (ResultCode r:ResultCode.values()){
            if (r.name().equals(name)){
                return r.message;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.name();
    }

}
