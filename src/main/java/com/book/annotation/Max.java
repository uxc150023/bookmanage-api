package com.book.annotation;

import java.lang.annotation.*;

/**
 * 数值最大值
 * <p>
 * File: Max.java<br/>
 * Description: <br/>
 * <p>
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @version 1.0
 * @date 2013-4-21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Documented
public @interface Max {

  /**
   * 错误消息
   *
   * @return
   */
  String message() default "超出最大值";

  /**
   * 最大值
   *
   * @return
   */
  long value();
}
