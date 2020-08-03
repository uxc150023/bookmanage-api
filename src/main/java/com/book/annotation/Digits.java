package com.book.annotation;

import java.lang.annotation.*;

/**
 * 必须是数字，可能是整数，小数
 * <p>
 * File: Digits.java<br/>
 * Description: <br/>
 * <p>
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @version 1.0
 * @date 2013-4-22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Documented
public @interface Digits {

  /**
   * 返回消息
   *
   * @return
   */
  String message() default "必须是数字";

  /**
   * 整数位数
   *
   * @return
   */
  int integer();

  /**
   * 小数位数
   *
   * @return
   */
  int fraction();
}
