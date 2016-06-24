package com.flyrish.hades.exception;

import org.acegisecurity.AuthenticationException;

public class FamliyUserNotSupportException extends AuthenticationException{

	public FamliyUserNotSupportException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	public FamliyUserNotSupportException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
