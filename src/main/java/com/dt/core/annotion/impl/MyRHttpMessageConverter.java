package com.dt.core.annotion.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import com.dt.core.common.base.R;

/**
 * @author: jinjie
 * @date: 2018年3月9日 下午1:02:33
 * @Description: TODO
 */
public class MyRHttpMessageConverter {
	private static final Logger _logger = LoggerFactory.getLogger(MyRHttpMessageConverter.class);

	public MappingJackson2HttpMessageConverter init() {
		return new MappingJackson2HttpMessageConverter() {
			/**
			 * 重写Jackson消息转换器的writeInternal方法
			 * SpringMVC选定了具体的消息转换类型后,会调用具体类型的write方法,将Java对象转换后写入返回内容
			 */
			@Override
			protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {
				Charset charset = this.getContentTypeCharset(outputMessage.getHeaders().getContentType());
				// 只有R进入
				if (object instanceof String) {
					_logger.info(
							"在MyResponseBodyAdvice进行转换时返回值变成String了,不能用原来选定消息转换器进行转换,直接使用StringHttpMessageConverter转换");
					// StringHttpMessageConverter中就是用以下代码写的
					StreamUtils.copy((String) object, charset, outputMessage.getBody());
				} else if (object instanceof R) {
					StreamUtils.copy(((R) object).asJsonStr(), charset, outputMessage.getBody());
				} else {
					super.writeInternal(object, type, outputMessage);
				}
			}

			@Override
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
				return ((Class) type).isAssignableFrom(R.class);
			}

			private Charset getContentTypeCharset(MediaType contentType) {
				return contentType != null && contentType.getCharset() != null ? contentType.getCharset()
						: this.getDefaultCharset();
			}
		};
	}
};
