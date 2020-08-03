package com.book.annotation;

import java.lang.annotation.*;

/**
 * api字段描述
 * <p>
 * date 2013-4-21
 *
 * @version 1.0
 */
@Retention(RetentionPolicy.CLASS)
@Target(value = {ElementType.FIELD})
@Documented
public @interface ApiDesc {

  /**
   * 文档内容
   *
   * @return
   */
  String value() default "";
}
