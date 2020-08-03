package com.book.annotation;

import java.lang.annotation.*;

/**
 * 需要对参数进行body签名注解
 * 自定义注解 拦截Controller
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMd5Sign {

  String description() default "";


} 