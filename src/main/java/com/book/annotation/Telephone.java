package com.book.annotation;

import java.lang.annotation.*;

/**
 * 电话号码
 * <p>
 * File: Telephone.java<br/>
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
public @interface Telephone {

  /**
   * 返回错误信息
   *
   * @return
   */
  String message() default "电话号码格式不正确";
}
