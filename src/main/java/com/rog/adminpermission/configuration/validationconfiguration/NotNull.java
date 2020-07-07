package com.rog.adminpermission.configuration.validationconfiguration;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 14:32
 **/
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//指定注解的实现类
@Constraint(validatedBy = NotNullValidator.class)
public @interface NotNull {
    // TODO:  可以添加其它扩展的属性
    String message() default "Value cannot be null";


    // TODO:  groups 和 payload 这两个parameter 必须包含
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}