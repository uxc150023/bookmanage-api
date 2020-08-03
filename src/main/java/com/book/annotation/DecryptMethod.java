package com.book.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @description:安全字段注解 加在需要加密/解密的方法上
 * 实现自动加密解密
 * @author: Mr.Wang
 * @create: 2018-
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface DecryptMethod {
  /**
   * 是否需要脱敏
   * @return
   */
  boolean isSensitive() default false;
}