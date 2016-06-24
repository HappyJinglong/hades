package com.flyrish.hades.exception;
/**
 * 强制性异常
 * @author zengdeqiang
 *
 */
public class ForceException extends Exception {

	public ForceException() {
		super();
	}

	public ForceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForceException(String message) {
		super(message);
	}

	public ForceException(Throwable cause) {
		super(cause);
	}
	
}
