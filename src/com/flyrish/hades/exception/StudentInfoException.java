package com.flyrish.hades.exception;

import org.acegisecurity.AuthenticationException;

public class StudentInfoException extends AuthenticationException {

	public StudentInfoException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	public StudentInfoException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
