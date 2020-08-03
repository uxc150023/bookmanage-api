package com.book.annotation;

import java.lang.annotation.*;

/**
 *  app展示的标记
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Documented
public @interface ShowProperty {

}
