package com.dt.core.common.exception;


/** 
* @author 作者 Lank 
* @version 创建时间：2017年8月2日 上午6:35:33 
* 类说明 
*/
public class ReflectException extends RuntimeException {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = -6213149635297151442L;

    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectException() {
        super();
    }

    public ReflectException(Throwable cause) {
        super(cause);
    }
}
