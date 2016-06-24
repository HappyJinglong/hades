package com.flyrish.hades.exception;

import org.acegisecurity.AuthenticationException;
/**
 * 没有初高中学段时，抛出的异常
 * @author zengdeqiang
 *
 */
public class HasNotFoundHigOrMidException extends AuthenticationException{

	public HasNotFoundHigOrMidException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	public HasNotFoundHigOrMidException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
