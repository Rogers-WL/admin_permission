package com.rog.authority.configuration.validationconfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 12:14
 **/
@Documented
//自定义注解，指定注解的实现类
@Constraint(validatedBy = PhoneValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Phone {
    String message() default "请输入正确的手机号";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        Phone[] value();
    }
}
