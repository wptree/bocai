package com.bocai.exception;

@SuppressWarnings("serial")
public class NeedLoginException extends BaseException {
	
	public NeedLoginException(){}

	public NeedLoginException(String msg){
		super(msg);
	}
}
