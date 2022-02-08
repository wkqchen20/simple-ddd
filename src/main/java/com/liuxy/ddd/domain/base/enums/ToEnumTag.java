package com.liuxy.ddd.domain.base.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface ToEnumTag {

    String key();

    boolean errorWhenNotFound() default true;

}
