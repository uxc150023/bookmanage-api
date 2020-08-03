package com.book.annotation;

import java.lang.annotation.*;

/**
 * 不允许为null
 * <p>
 * File: NotNull.java<br/>
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
public @interface NotNull {

  /**
   * 返回错误消息
   *
   * @return
   */
  String message() default "值不能为空";

  int subCode() default 31000;

}
