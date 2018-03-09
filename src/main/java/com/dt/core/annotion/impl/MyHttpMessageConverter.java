package com.dt.core.annotion.impl;

/**
 * @author: jinjie
 * @date: 2018年3月9日 下午1:02:33
 * @Description: TODO
 */
/*public class MyHttpMessageConverter implements MappingJackson2HttpMessageConverter {
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// 使用 Jackson 的 ObjectMapper 将 Java 对象转换成 Json String
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(object);
		outputMessage.getBody().write(json.getBytes());
	}
};
*/