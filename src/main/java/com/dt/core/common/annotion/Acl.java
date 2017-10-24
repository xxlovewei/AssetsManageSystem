package com.dt.core.common.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: algernonking
 * @date: 2017年8月6日 下午3:22:48
 * @Description: Acl必须要设置,否则不能访问
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Acl {
	public static String TYPE_ALLOW = "allow";
	public static String TYPE_DENY = "deny";

	String value() default TYPE_DENY;
}
