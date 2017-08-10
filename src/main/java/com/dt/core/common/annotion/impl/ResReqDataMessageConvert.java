package com.dt.core.common.annotion.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class ResReqDataMessageConvert extends AbstractGenericHttpMessageConverter<Object> {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
		return ((Class) type).isAssignableFrom(ReqData.class);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
		return ((Class) type).isAssignableFrom(ResData.class);
	}

	public List<MediaType> getSupportedMediaTypes() {
		return Collections.singletonList(MediaType.ALL);
	}

	protected boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ResData.class);
	}

	public Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return readMap(inputMessage);
	}

	private Object readMap(HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		Charset cs = Charset.forName("UTF-8");
		StringBuilder stringBuilder = new StringBuilder();
		InputStream inputStream = inputMessage.getBody();
		byte[] b = new byte[1024];
		int length;
		while ((length = inputStream.read(b)) != -1) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(length);
			byteBuffer.put(b, 0, length);
			byteBuffer.flip();
			stringBuilder.append(cs.decode(byteBuffer).array());
		}
		String[] list = stringBuilder.toString().split(";");
		Map<String, String> map = new HashMap<String, String>(list.length);
		for (String entry : list) {
			String[] keyValue = entry.split(",");
			map.put(keyValue[0], keyValue[1]);
		}
		ReqData requestData = new ReqData();
		requestData.setData(map);
		return requestData;
	}

	public void writeInternal(Object o, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		StringBuffer str = null;
		ResData res = (ResData) o;	
	
		//后期支持不同类型
		if (res.TYPE_VALUE.equals(ResData.TYPE_JSON) ){
			str=new StringBuffer(res.asJson());
		}else{
			str=new StringBuffer();
		}
		outputMessage.getBody().write(str.toString().getBytes());
	}

	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return readMap(inputMessage);
	}
}
