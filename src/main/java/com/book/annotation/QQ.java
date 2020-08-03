package com.book.annotation;

import java.lang.annotation.*;

/**
 * 是qq号码
 * <p>
 * File: QQ.java<br/>
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
public @interface QQ {

  /**
   * 返回错误信息
   *
   * @return
   */
  String message() default "qq号码不正确";
}
