package com.book.annotation;

import java.lang.annotation.*;

/**
 * 数值大小范围
 * <p>
 * File: Size.java<br/>
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
public @interface Range {

  /**
   * 错误消息
   *
   * @return
   */
  String message() default "数值不在指定的范围";

  /**
   * 最小值
   *
   * @return
   */
  long min() default 0;

  /**
   * 最大值
   *
   * @return
   */
  long max() default Long.MAX_VALUE;
}
