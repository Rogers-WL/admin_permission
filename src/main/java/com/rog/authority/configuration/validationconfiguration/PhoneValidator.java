package com.rog.authority.configuration.validationconfiguration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 13:46
 **/
public class PhoneValidator implements ConstraintValidator<Phone,String> {

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$"
    );

    @Override
    public void initialize(Phone constraintAnnotation) {
        // TODO:  初始化操作
    }

  
  /**
   * @Description //TODO
   * @author Rogers_WL
   * @Date  2020/5/24 14:06
   */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //只验证手机号格式，不验证是否为空
        if(s == null || s.length()==0) {
            return true;
        }
        Matcher m = PHONE_PATTERN.matcher(s);
        return m.matches();
    }
}
