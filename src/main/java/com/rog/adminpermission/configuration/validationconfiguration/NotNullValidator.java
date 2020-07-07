package com.rog.adminpermission.configuration.validationconfiguration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 14:34
 **/
public class NotNullValidator implements ConstraintValidator<NotNull, String> {

    @Override
    public void initialize(NotNull constraintAnnotation) {
        // TODO: 初始化操作
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // TODO:  具体逻辑
        if(value == null || value.isEmpty()){
            return false;
        }
        return true;
    }
}
