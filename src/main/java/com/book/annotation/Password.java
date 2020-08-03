package com.book.annotation;

import java.lang.annotation.*;

/**
 * 密码字段
 * <p>
 * File: Password.java<br/>
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
public @interface Password {

  /**
   * 返回错误信息
   *
   * @return
   */
  String message() default "密码不符合要求";

  /**
   * 密码格式验证
   *
   * @return
   */
  int subCode() default 32000;
}
