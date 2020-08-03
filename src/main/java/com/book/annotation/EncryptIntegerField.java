package com.book.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @description: 安全字段注解
 * 加在需要加密/解密的字段上
 * @author: Mr.Wang
 * @create: 2019-03-28
 **/
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface EncryptIntegerField {

  /**
   * 返回错误信息
   */
  String cacheKey() default "hospital_";

}