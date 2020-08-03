package com.book.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Auther: miaoy
 * @Date: 2019/7/31 13:43
 * @Description: 安全字段注解 加在需要加密/解密的方法上
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface DesMethod {
  /**
   * 是否需要脱敏
   * @return
   */
  boolean isSensitive() default false;
}