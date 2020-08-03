package com.book.annotation;

import java.lang.annotation.*;

/**
 * 过去的时间
 * <p>
 * File: Past.java<br/>
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
public @interface Past {

  /**
   * 返回错误消息
   *
   * @return
   */
  String message() default "必须是过去的时间";
}
