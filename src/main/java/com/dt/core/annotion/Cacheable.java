package com.dt.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.dt.core.cache.ExpireTime;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Cacheable {

	public String key() default ""; // 缓存key

	public ExpireTime expire() default ExpireTime.NONE; // 缓存时效,默认无限期

}

