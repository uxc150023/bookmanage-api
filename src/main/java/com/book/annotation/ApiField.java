package com.book.annotation;

import java.lang.annotation.*;

/**
 * 数据结构属性注解。
 * <p>
 * File: ApiField.java<br/>
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
public @interface ApiField {

  /**
   * JSON列表属性映射名称
   *
   * @return
   */
  public String value() default "";

}
