package com.flyrish.hades.exception;

import org.acegisecurity.AuthenticationException;

public class KeyServerException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * KeyServerException
	 * @param msg
	 */
	public KeyServerException(String msg) {
		super(msg);
	}
	
	/**
	 * KeyServerException
	 * @param msg
	 * @param t
	 */
	public KeyServerException(String msg, Throwable t)
    {
        super(msg, t);
    }

}
