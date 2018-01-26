package com.dt.tool.util.exception;

 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

import com.dt.tool.util.support.StrKit;

 

/**
 * 异常工具类
 * 
 * @author Looly
 *
 */
public final class ExceptionUtil {
	
	private ExceptionUtil(){};

	/**
	 * 获得完整消息，包括异常名
	 * 
	 * @param e 异常
	 * @return 完整消息
	 */
	public static String getMessage(Throwable e) {
		 
		return StrKit.format("{}: {}", e.getClass().getSimpleName(), e.getMessage());
	}
	
	/**
	 * 使用运行时异常包装编译异常
	 * @param throwable 异常
	 * @return 运行时异常
	 */
	public static RuntimeException wrapRuntime(Throwable throwable){
		if(throwable instanceof RuntimeException){
			return (RuntimeException) throwable;
		}else{
			return new RuntimeException(throwable);
		}
	}
	
	/**
	 * 包装一个异常
	 * @param throwable 异常
	 * @param wrapThrowable 包装后的异常类
	 * @return 包装后的异常
	 * @since 3.3.0
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Throwable> T wrap(Throwable throwable, Class<T> wrapThrowable) {
		if(wrapThrowable.isInstance(throwable)) {
			return (T) throwable;
		}
		return (T) throwable;
	}
 
 

	
	/**
	 * 剥离反射引发的InvocationTargetException、UndeclaredThrowableException中间异常，返回业务本身的异常
	 * @param wrapped 包装的异常
	 * @return 剥离后的异常
	 */
	public static Throwable unwrap(Throwable wrapped) {
		Throwable unwrapped = wrapped;
		while (true) {
			if (unwrapped instanceof InvocationTargetException) {
				unwrapped = ((InvocationTargetException) unwrapped).getTargetException();
			} else if (unwrapped instanceof UndeclaredThrowableException) {
				unwrapped = ((UndeclaredThrowableException) unwrapped).getUndeclaredThrowable();
			} else {
				return unwrapped;
			}
		}
	}
	
	/**
	 * 获取当前栈信息
	 * 
	 * @return 当前栈信息
	 */
	public static StackTraceElement[] getStackElements() {
//		return (new Throwable()).getStackTrace();
		return Thread.currentThread().getStackTrace();
	}
	
	
	 
	 
}

